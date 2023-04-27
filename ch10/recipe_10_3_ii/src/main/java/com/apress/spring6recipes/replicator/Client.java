package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.JmxClientConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Client {

	public static void main(String[] args) throws Exception {
		var cfg = "bean:name=documentReplicator,type=JMXFileReplicator";
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var fileReplicatorProxy = ctx.getBean(FileReplicator.class);
			var srcDir = fileReplicatorProxy.getSrcDir();
			fileReplicatorProxy.setDestDir(srcDir + "_backup");
			fileReplicatorProxy.replicate();
		}
	}
}
