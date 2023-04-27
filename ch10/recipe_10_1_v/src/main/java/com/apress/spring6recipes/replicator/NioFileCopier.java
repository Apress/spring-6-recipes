package com.apress.spring6recipes.replicator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioFileCopier implements FileCopier {

	public void copyFile(Path srcFile, Path destDir) {
		var destFile = destDir.resolve(srcFile.getFileName());
		try {
			Files.copy(srcFile, destFile);
		} catch (IOException ex) {
			throw new IllegalStateException("Cannot copy file.", ex);
		}
	}
}
