package com.leonyip.core.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtils {
	public static String getWebPath(HttpServletRequest httpRequest) {
		String scheme = httpRequest.getScheme();
		String serverName = httpRequest.getServerName();
		int serverPort = httpRequest.getLocalPort();
		String contextPath = httpRequest.getContextPath();
		return buildFullRequestUrl(scheme, serverName, serverPort, contextPath);
	}

	public static String getFullRequestUrl(HttpServletRequest request) {
		HttpServletRequest r = request;

		return buildFullRequestUrl(r.getScheme(), r.getServerName(), r
				.getServerPort(), r.getContextPath(), r.getRequestURL()
				.toString(), r.getServletPath(), r.getRequestURI(), r
				.getPathInfo(), r.getQueryString());
	}

	public static String getRequestUrl(HttpServletRequest request) {
		HttpServletRequest r = request;

		return buildRequestUrl(r.getServletPath(), r.getRequestURI(), r
				.getContextPath(), r.getPathInfo(), r.getQueryString());
	}

	private static String buildFullRequestUrl(String scheme, String serverName,
			int serverPort, String contextPath, String requestUrl,
			String servletPath, String requestURI, String pathInfo,
			String queryString) {
		boolean includePort = true;

		if (("http".equals(scheme.toLowerCase())) && (serverPort == 80)) {
			includePort = false;
		}

		if (("https".equals(scheme.toLowerCase())) && (serverPort == 443)) {
			includePort = false;
		}

		return scheme
				+ "://"
				+ serverName
				+ ((includePort) ? ":" + serverPort : "")
				+ contextPath
				+ buildRequestUrl(servletPath, requestURI, contextPath,
						pathInfo, queryString);
	}

	private static String buildFullRequestUrl(String scheme, String serverName,
			int serverPort, String contextPath) {
		boolean includePort = true;

		if (("http".equals(scheme.toLowerCase())) && (serverPort == 80)) {
			includePort = false;
		}

		if (("https".equals(scheme.toLowerCase())) && (serverPort == 443)) {
			includePort = false;
		}

		return scheme + "://" + serverName
				+ ((includePort) ? ":" + serverPort : "") + contextPath;
	}

	private static String buildRequestUrl(String servletPath,
			String requestURI, String contextPath, String pathInfo,
			String queryString) {
		String uri = servletPath;

		if (uri == null) {
			uri = requestURI;
			uri = uri.substring(contextPath.length());
		}

		return uri
				+ ((pathInfo == null) ? "" : pathInfo)
				+ ((queryString == null) ? "" : new StringBuilder().append("?")
						.append(queryString).toString());
	}

	public static boolean isMultipart(HttpServletRequest request) {
		String content_type = request.getHeader("Content-Type");

		return ((content_type != null) && (content_type
				.startsWith("multipart/form-data")));
	}
}