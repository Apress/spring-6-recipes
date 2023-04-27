package com.apress.spring6recipes.replicator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ManagedResource(
				description = "File replicator",
				objectName = "bean:name=fileCopier,type=JMXFileReplicator")
public class JMXFileReplicator implements FileReplicator {

	private String srcDir;
	private String destDir;
	private FileCopier fileCopier;

	@ManagedAttribute(description = "Get source directory")
	public String getSrcDir() {
		return srcDir;
	}

	@ManagedAttribute(description = "Set source directory")
	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

	@ManagedAttribute(description = "Get destination directory")
	public String getDestDir() {
		return destDir;
	}

	@ManagedAttribute(description = "Set destination directory")
	public void setDestDir(String destDir) {
		this.destDir = destDir;
	}

	public FileCopier getFileCopier() {
		return fileCopier;
	}

	public void setFileCopier(FileCopier fileCopier) {
		this.fileCopier = fileCopier;
	}

	@ManagedOperation(description = "Replicate files")
	public synchronized void replicate() throws IOException {
		var files = Path.of(srcDir);

		try (var fileList = Files.list(files)) {
			fileList.filter(Files::isRegularFile)
							.forEach(it -> fileCopier.copyFile(it, Path.of(destDir)));

		}
	}
}
