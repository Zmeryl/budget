package com.leonyip.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public class BeanUtils {
	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

	public static Field getDeclaredField(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	public static Field getDeclaredField(Class clazz, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		Class superClass = clazz;
		if (superClass != Object.class)
			;
		try {
			return superClass.getDeclaredField(propertyName);
		} catch (NoSuchFieldException e) {
			while (true) {
				superClass = superClass.getSuperclass();
			}

			// throw new NoSuchFieldException("No such field: " +
			// clazz.getName() + '.' + propertyName);
		}
	}

	public static Object forceGetProperty(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);

		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.info("error wont' happen");
		}
		field.setAccessible(accessible);
		return result;
	}

	public static void forceSetProperty(Object object, String propertyName,
			Object newValue) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			logger.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}

	public static Object invokePrivateMethod(Object object, String methodName,
			Object[] params) throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; ++i) {
			types[i] = params[i].getClass();
		}

		Class clazz = object.getClass();
		Method method = null;
		Class superClass = clazz;
		if (superClass != Object.class) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
			} catch (NoSuchMethodException e) {
				while (true) {
					superClass = superClass.getSuperclass();
				}

			}

		}

		if (method == null) {
			throw new NoSuchMethodException("No Such Method:"
					+ clazz.getSimpleName() + methodName);
		}

		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	public static List<Field> getFieldsByType(Object object, Class type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		Field[] arr$ = fields;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			Field field = arr$[i$];
			if (field.getType().isAssignableFrom(type))
				list.add(field);
		}

		return list;
	}

	public static Class getPropertyType(Class type, String name)
			throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	public static String getGetterName(Class type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");

		if (type.getName().equals("boolean"))
			return "is" + StringUtils.capitalize(fieldName);

		return "get" + StringUtils.capitalize(fieldName);
	}

	public static Method getGetterMethod(Class type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName), new Class[0]);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}