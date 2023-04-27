package com.apress.spring6recipes.replicator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import javax.management.Notification;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

@ManagedResource(description = "File replicator")
public class JMXFileReplicator implements FileReplicator, NotificationPublisherAware {

	private final AtomicInteger sequenceNumber = new AtomicInteger();

	private String srcDir;
	private String destDir;
	private FileCopier fileCopier;

	private NotificationPublisher notificationPublisher;

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
		var seqNumber = sequenceNumber.incrementAndGet();
		var notification =new Notification("replication.complete", this, seqNumber);
		notificationPublisher.sendNotification(notification);
	}

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}
}
