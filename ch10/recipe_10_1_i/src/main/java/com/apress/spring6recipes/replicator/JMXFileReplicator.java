package com.apress.spring6recipes.replicator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JMXFileReplicator implements FileReplicator {

	private String srcDir;
	private String destDir;
	private FileCopier fileCopier;

	public String getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

	public String getDestDir() {
		return destDir;
	}

	public void setDestDir(String destDir) {
		this.destDir = destDir;
	}

	public FileCopier getFileCopier() {
		return fileCopier;
	}

	public void setFileCopier(FileCopier fileCopier) {
		this.fileCopier = fileCopier;
	}

	@Override
	public synchronized void replicate() throws IOException {
		var files = Path.of(srcDir);

		try (var fileList = Files.list(files)) {
			fileList.filter(Files::isRegularFile)
							.forEach(it -> fileCopier.copyFile(it, Path.of(destDir)));

		}
	}
}
