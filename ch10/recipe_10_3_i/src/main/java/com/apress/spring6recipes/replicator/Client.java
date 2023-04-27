package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.JmxClientConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class Client {

	public static void main(String[] args) throws Exception {
		var cfg = JmxClientConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var mbeanServerConnection = context.getBean(MBeanServerConnection.class);
			var name = "bean:name=documentReplicator,type=JMXFileReplicator";
			var mbeanName = new ObjectName(name);
			var listener = new ReplicationNotificationListener();
			mbeanServerConnection.addNotificationListener(mbeanName, listener, null, null);
			var srcDir = (String) mbeanServerConnection.getAttribute(mbeanName, "SrcDir");
			var destDir = new Attribute("DestDir", srcDir + "_backup");
			mbeanServerConnection.setAttribute(mbeanName, destDir);
			mbeanServerConnection.invoke(mbeanName, "replicate", null, null);
		}
	}
}
