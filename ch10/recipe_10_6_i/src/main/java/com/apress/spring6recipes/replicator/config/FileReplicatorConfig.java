package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.FileCopier;
import com.apress.spring6recipes.replicator.NioFileCopier;
import com.apress.spring6recipes.replicator.SimpleFileReplicator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class FileReplicatorConfig {

	@Value("#{systemProperties['user.home']}/docs")
	private String srcDir;
	@Value("#{systemProperties['user.home']}/docs_backup")
	private String destDir;

	@Bean
	public FileCopier fileCopier() {
		return new NioFileCopier();
	}

	@Bean
	public SimpleFileReplicator documentReplicator(FileCopier fileCopier) {
		return new SimpleFileReplicator(fileCopier, srcDir, destDir);
	}

	@PostConstruct
	public void verifyDirectoriesExist() throws IOException {
		Files.createDirectories(Path.of(srcDir));
		Files.createDirectories(Path.of(destDir));
	}
}
