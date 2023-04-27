package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.FileCopier;
import com.apress.spring6recipes.replicator.FileReplicator;
import com.apress.spring6recipes.replicator.JMXFileReplicator;
import com.apress.spring6recipes.replicator.NioFileCopier;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
	public NioFileCopier fileCopier() {
		return new NioFileCopier();
	}

	@Bean
	public FileReplicator documentReplicator(FileCopier fileCopier) {
		var fRep = new JMXFileReplicator();
		fRep.setSrcDir(srcDir);
		fRep.setDestDir(destDir);
		fRep.setFileCopier(fileCopier);
		return fRep;
	}

	@PostConstruct
	public void verifyDirectoriesExist() throws IOException {
		Files.createDirectories(Path.of(srcDir));
		Files.createDirectories(Path.of(destDir));
	}
}
