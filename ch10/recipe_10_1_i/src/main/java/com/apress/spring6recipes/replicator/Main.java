package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.FileReplicatorConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.JMException;
import javax.management.ObjectName;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.RequiredModelMBean;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class Main {

	public static void main(String[] args) throws IOException {
		var cfg = FileReplicatorConfig.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var documentReplicator = ctx.getBean(FileReplicator.class);

			try {
				var mbeanServer = ManagementFactory.getPlatformMBeanServer();
				var objectName = new ObjectName("bean:name=documentReplicator");

				var mbean = new RequiredModelMBean();
				mbean.setManagedResource(documentReplicator, "objectReference");

				var srcDirDescriptor = new DescriptorSupport(
								"name=SrcDir", "descriptorType=attribute",
								"getMethod=getSrcDir", "setMethod=setSrcDir");
				var srcDirInfo = new ModelMBeanAttributeInfo(
								"SrcDir", "java.lang.String", "Source directory",
								true, true, false, srcDirDescriptor);

				var destDirDescriptor = new DescriptorSupport(
								"name=DestDir", "descriptorType=attribute",
								"getMethod=getDestDir", "setMethod=setDestDir");
				var destDirInfo = new ModelMBeanAttributeInfo(
								"DestDir", "java.lang.String", "Destination directory",
								true, true, false, destDirDescriptor);

				var getSrcDirInfo = new ModelMBeanOperationInfo(
								"Get source directory",
								FileReplicator.class.getMethod("getSrcDir"));
				var setSrcDirInfo = new ModelMBeanOperationInfo(
								"Set source directory",
								FileReplicator.class.getMethod("setSrcDir", String.class));
				var getDestDirInfo = new ModelMBeanOperationInfo(
								"Get destination directory",
								FileReplicator.class.getMethod("getDestDir"));
				var setDestDirInfo = new ModelMBeanOperationInfo(
								"Set destination directory",
								FileReplicator.class.getMethod("setDestDir", String.class));
				var replicateInfo = new ModelMBeanOperationInfo(
								"Replicate files",
								FileReplicator.class.getMethod("replicate"));

				var mbeanInfo = new ModelMBeanInfoSupport(
								"FileReplicator", "File replicator",
								new ModelMBeanAttributeInfo[]{srcDirInfo, destDirInfo},
								null,
								new ModelMBeanOperationInfo[]{getSrcDirInfo, setSrcDirInfo,
												getDestDirInfo, setDestDirInfo, replicateInfo},
								null
				);
				mbean.setModelMBeanInfo(mbeanInfo);

				mbeanServer.registerMBean(mbean, objectName);
			} catch (JMException | InvalidTargetObjectTypeException |
			         NoSuchMethodException ex) {
				System.err.println(ex);
			}
		}
		System.in.read();
	}
}
