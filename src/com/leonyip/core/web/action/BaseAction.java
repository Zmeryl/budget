package com.leonyip.core.web.action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.leonyip.core.util.UrlUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	// public abstract String getAuthStr();

	/**
	 * 
	 */
	private static final long serialVersionUID = 8217996179496959326L;

	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	public String getRequestUrl() {
		return UrlUtils.getRequestUrl(getHttpServletRequest());
	}

	public String getFullRequestUrl() {
		return UrlUtils.getFullRequestUrl(getHttpServletRequest());
	}

	public Map<String,Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	protected Object getParameterValue(String parameter) {
		Object[] parameterArray = getParamenterArray(parameter);
		if ((parameterArray != null) && (parameterArray.length == 1))
			return parameterArray[0];

		return getHttpServletRequest().getAttribute(parameter);
	}

	protected Object[] getParamenterArray(String parameter) {
		return ((Object[]) (Object[]) ActionContext.getContext()
				.getParameters().get(parameter));
	}

	// protected String getApplicationProperty(String key)
	// {
	// return DelegatingConfiguration.getString(key);
	// }

	public void setRequestAttribute(String key, Object value) {
		getHttpServletRequest().setAttribute(key, value);
	}

	public Object getSessionObj(Object key) {
		return getSession().get(key);
	}

	protected String getRootDir() {
		String workDir = getHttpServletRequest().getSession()
				.getServletContext().getRealPath("login.jsp");

		int len = workDir.indexOf("login.jsp");
		workDir = workDir.substring(0, len);

		StringBuffer sb = new StringBuffer();
		sb.append(workDir).append("upload").append(File.separator);

		return sb.toString();
	}

	public void setSessionObj(String key, Object value) {
		getSession().put(key, value);
	}
}