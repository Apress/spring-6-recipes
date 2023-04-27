package com.apress.spring6recipes.shop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AuditCheckBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
					throws BeansException {
		var msg = "In AuditCheckBeanPostProcessor.postProcessBeforeInitialization, processing bean type: %s%n";
		System.out.printf(msg, bean.getClass().getName());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
					throws BeansException {
		return bean;
	}
}
