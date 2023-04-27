package com.apress.spring6recipes.shop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ProductCheckBeanPostProcessor implements BeanPostProcessor {

	private static final String MSG =
					"In ProductCheckBeanPostProcessor.%s, processing Product: %s%n";

	public Object postProcessBeforeInitialization(Object bean, String beanName)
					throws BeansException {
		if (bean instanceof Product product) {
			var productName = product.getName();
			System.out.printf(MSG, "postProcessBeforeInitialization", productName);
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
					throws BeansException {
		if (bean instanceof Product product) {
			var productName = product.getName();
			System.out.printf(MSG, "postProcessAfterInitialization", productName);
		}
		return bean;
	}
}
