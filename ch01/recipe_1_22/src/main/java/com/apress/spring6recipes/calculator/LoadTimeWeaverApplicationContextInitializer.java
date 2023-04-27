package com.apress.spring6recipes.calculator;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.weaving.AspectJWeavingEnabler;
import org.springframework.context.weaving.DefaultContextLoadTimeWeaver;

public class LoadTimeWeaverApplicationContextInitializer implements ApplicationContextInitializer<AnnotationConfigApplicationContext> {
	@Override
	public void initialize(AnnotationConfigApplicationContext applicationContext) {
		var beanClassLoader = applicationContext.getBeanFactory().getBeanClassLoader();
		var ltw = new DefaultContextLoadTimeWeaver(beanClassLoader);
		AspectJWeavingEnabler.enableAspectJWeaving(ltw, beanClassLoader);
	}
}
