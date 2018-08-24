package com.leonyip.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class ServiceLocator implements BeanFactoryAware {
	private static BeanFactory beanFactory = null;
	private static ServiceLocator servlocator = null;

	public void setBeanFactory(BeanFactory factory) throws BeansException {
		beanFactory = factory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static ServiceLocator getInstance() {
		if (servlocator == null)
			servlocator = (ServiceLocator) beanFactory
					.getBean("serviceLocator");
		return servlocator;
	}

	public static Object getService(String servName) {
		return beanFactory.getBean(servName);
	}

	public static Object getService(String servName, Class clazz) {
		return beanFactory.getBean(servName, clazz);
	}
}