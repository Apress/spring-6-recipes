package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;

import java.util.Map;

@Configuration
public class JmxConfig {

	@Bean
	public MBeanExporter mbeanExporter(MBeanInfoAssembler assembler) {
		var mbeanExporter = new MBeanExporter();
		mbeanExporter.setBeans(Map.of("bean:name=documentReplicator", "documentReplicator"));
		mbeanExporter.setAssembler(assembler);
		return mbeanExporter;
	}

	@Bean
	public MBeanInfoAssembler assembler() {
		var assembler = new MethodNameBasedMBeanInfoAssembler();
		assembler.setManagedMethods("getSrcDir", "setSrcDir", "getDestDir", "setDestDir", "replicate");
		return assembler;
	}
}

