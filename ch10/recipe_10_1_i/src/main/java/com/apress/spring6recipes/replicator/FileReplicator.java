package com.apress.spring6recipes.replicator;

import java.io.IOException;

public interface FileReplicator {

	String getSrcDir();
	void setSrcDir(String srcDir);

	String getDestDir();
	void setDestDir(String destDir);

	void replicate() throws IOException;
}
