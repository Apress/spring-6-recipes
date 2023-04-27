package com.apress.spring6recipes.replicator;

public interface ErrorNotifier {

	void notifyCopyError(String srcDir, String destDir, String filename);
}