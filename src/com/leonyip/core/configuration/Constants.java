package com.leonyip.core.configuration;

public class Constants extends ConfigurableConstants {
	public static final String ERROR_BUNDLE_KEY;
	public static final String USER_IN_SESSION;
	public static final String ADMIN_IN_SESSION;
	public static final String LOGIN_ACTION;
	public static final String LAST_URL;
	public static final int DEFAULT_PAGE_SIZE = 10;

	static {
		init("/system.properties");

		ERROR_BUNDLE_KEY = getProperty("constant.error_bundle_key",
				"i18n/errors");

		USER_IN_SESSION = getProperty("constant.user_in_session", "user");

		ADMIN_IN_SESSION = getProperty("constant.admin_in_session", "adminTag");

		LOGIN_ACTION = getProperty("constant.login_action", "login");

		LAST_URL = getProperty("constant.last_url", "__LAST_URL__");
	}
}