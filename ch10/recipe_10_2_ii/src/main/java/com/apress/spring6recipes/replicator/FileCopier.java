package com.apress.spring6recipes.replicator;

import java.nio.file.Path;

public interface FileCopier {

	void copyFile(Path srcFile, Path destDir);
}
