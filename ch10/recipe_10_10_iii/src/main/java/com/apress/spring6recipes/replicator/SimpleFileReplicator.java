package com.apress.spring6recipes.replicator;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleFileReplicator implements FileReplicator {

	private final FileCopier fileCopier;
	private final String srcDir;
	private final String destDir;

	public SimpleFileReplicator(FileCopier fileCopier, String srcDir, String destDir) {
		this.fileCopier = fileCopier;
		this.srcDir = srcDir;
		this.destDir = destDir;
	}

	@Scheduled(fixedDelay = 60_000)
	public synchronized void replicate() throws IOException {
		var files = Path.of(srcDir);
		try (var fileList = Files.list(files)) {
			fileList.filter(Files::isRegularFile)
							.forEach(it -> fileCopier.copyFile(it, Path.of(destDir)));
		}
	}
}
