package com.leonyip.core.exception;

import com.leonyip.core.configuration.Constants;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.NestedRuntimeException;

public class BusinessException extends NestedRuntimeException
{
  private String errorCode = "UNKNOW_ERROR";
  protected String[] errorArgs = null;
  private String errorMessage = null;
  private static ResourceBundle rb = ResourceBundle.getBundle(Constants.ERROR_BUNDLE_KEY, LocaleContextHolder.getLocale());

  public BusinessException()
  {
    super("UNKNOW_ERROR");
  }

  public BusinessException(String errorCode, String[] errorArgs) {
    super(errorCode);
    this.errorCode = errorCode;
    this.errorArgs = errorArgs;
  }

  public BusinessException(String errorCode, Throwable t, String[] errorArgs) {
    super(errorCode, t);
    this.errorCode = errorCode;
    this.errorArgs = errorArgs;
  }

  public BusinessException(String errorMessage) {
    super("UNKNOW_ERROR");
    this.errorMessage = errorMessage;
  }

  public BusinessException(String errorMessage, Throwable t) {
    super("UNKNOW_ERROR", t);
    this.errorMessage = errorMessage;
  }

  public String getMessage()
  {
    String message;
    if (StringUtils.isNotBlank(this.errorMessage)) {
      return this.errorMessage;
    }

    try
    {
      message = rb.getString(this.errorCode);
    } catch (MissingResourceException mse) {
      message = "ErrorCode is: " + this.errorCode + ", but can't get the message of the Error Code";
    }

    if (this.errorArgs != null)
      message = MessageFormat.format(message, (Object[])this.errorArgs);

    return message;
  }

  public String getErrorCode()
  {
    return this.errorCode;
  }
}