package com.apress.spring6recipes.replicator;

import javax.management.Notification;
import javax.management.NotificationListener;

public class ReplicationNotificationListener implements NotificationListener {

	public void handleNotification(Notification not, Object handback) {
		if (not.getType().startsWith("replication")) {
			System.out.printf("%s %s #%d%n",
							not.getSource(), not.getType(), not.getSequenceNumber());
		}
	}
}