package com.leonyip.budget.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings( { "serial", "unused" })
public class PaginationTag extends TagSupport {

	/** 处理分页的action path */
	private String actionName;

	/** 页面数 */
	private Integer total;

	/** 当前页码 */
	private Integer num;

	/** 分类的id */
	private Integer catalogId;

	/** 降序排序字段 */
	private String orderByItem;

	/** 其他参数,格式 &key1=value1&key2=value2 */
	private String otherParams;

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setOtherParams(String params) {
		this.otherParams = params;
	}

	public void setOrderByItem(String orderByItem) {
		this.orderByItem = orderByItem;
	}

	public String getActionName() {
		int pos = actionName.indexOf("?");
		if (pos > 0) {
			actionName = actionName.substring(0, pos - 1);
		}
		return actionName + ".jhtml";
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setCatalogId(Integer id) {
		catalogId = id;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public int getPreviousPage() {
		if (num - 1 <= 0) {
			return 1;
		} else {
			return (num - 1);
		}
	}

	public int getNextPage() {
		if (num + 1 >= total) {
			return total;
		} else {
			return (num + 1);
		}
	}

	/**
	 * 产生类似如下的html代码 <div class="turn"><b><a href="#"><< 上一页</a></b><span>1</span>
	 * <a href="#">2</a><a href="#">3</a><b>...</b><a href="#">4</a> <a
	 * href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a>
	 * <a href="#">9</a><a href="#">10</a><b><a href="#">下一页 >></a></b>
	 * </div>
	 * 
	 */
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		/*
		 * if (total > 1) { sb.append("<div class='turn'>"); sb.append("<b><a
		 * href='").append(getActionName())
		 * .append("?pageNo=").append(getPreviousPage()); if (catalogId != null) {
		 * sb.append("&catalogId=").append(catalogId); } if (orderByItem !=
		 * null) { sb.append("&orderByItem=").append(orderByItem); } if
		 * (otherParams != null) { sb.append(otherParams); } sb.append("'><<
		 * 上一页</a>"); } for (int i = 1; i <= total; i++) { if (total == 1) {
		 * break; } if (i == num) { sb.append("<span>").append(i).append("</span>");
		 * continue; }
		 * 
		 * if (num < 10 && i <= 10) { sb.append("<a
		 * href='").append(getActionName()).append( "?pageNo=").append(i); if
		 * (catalogId != null) { sb.append("&catalogId=").append(catalogId); }
		 * if (orderByItem != null) {
		 * sb.append("&orderByItem=").append(orderByItem); } if (otherParams !=
		 * null) { sb.append(otherParams); } sb.append("'>");
		 * sb.append(i).append("</a>"); if (i == 10) { break; } } else { if (i <=
		 * 2 || (i > num - 5 && i <= num + 4) || i > total - 2) { sb.append("<a
		 * href='").append(getActionName()).append( "?pageNo=").append(i); if
		 * (catalogId != null) { sb.append("&catalogId=").append(catalogId); }
		 * if (orderByItem != null) {
		 * sb.append("&orderByItem=").append(orderByItem); } if (otherParams !=
		 * null) { sb.append(otherParams); } sb.append("'>");
		 * sb.append(i).append("</a>"); } else { if (i > 2 && i < num) {
		 * sb.append("<b>...</b>"); i = num - 5; } else { if (i > num + 4 && i <=
		 * total - 2) { sb.append("<b>...</b>"); i = total - 2; } } } } } if
		 * (total > 1) { sb.append("<b><a href='").append(getActionName())
		 * .append("?pageNo=").append(getNextPage()); if (catalogId != null) {
		 * sb.append("&catalogId=").append(catalogId); } if (orderByItem !=
		 * null) { sb.append("&orderByItem=").append(orderByItem); } if
		 * (otherParams != null) { sb.append(otherParams); } sb.append("'>下一页 >></a></b>"); }
		 */
		if (total > 1) {
			sb.append("<tr class='titletop'> ");
			sb.append("<td width='1%'>&nbsp;</td>");
			sb.append("<td>");	
			sb.append("<form id='pageForm' action='" + getActionName()+ "' method='post'>");
			sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td bgcolor='#FFFFFF' class='TdRight'>");
			sb.append("<a href='javascript:page(1)'  class='tex04'");
			sb.append(">首页</a> | ");
			sb.append("<a href='javascript:page(" + getPreviousPage() + ") '  class='tex04'");
			sb.append(">上一页</a> | ");
			sb.append("<a href='javascript:page(" + getNextPage() + ")'  class='tex04'");
			sb.append(">下一页</a> | ");
			sb.append("<a href='javascript:page(" + total + ")'  class='tex04'");
			sb.append(">末页</a> ");
			sb.append("<input id='pageNo' name='pageNo' type='text' style='width:30px;' align='absmiddle' maxlength='3' value='"+num+"'>");
			sb.append("<input name='go' type='button' id='go' value='GO' align='absmiddle' onclick='page(-1)'>");

			if (otherParams != null && !otherParams.equals("")) {
				String[] params = otherParams.split("&");
				String name = "";
				String value = "";
				for (String param : params) {
					name = param.split("=")[0];
					if (param.split("=").length > 1) {
						value = param.split("=")[1];
					}

					sb.append("<input type='hidden' name='" 
							+ name + "' value='" + value + "'>");
				}
			}
			sb.append("</td></tr></table></form></td></tr>");
			sb.append("<script>");
			sb.append("function page(toPageNo){");
			sb.append("if(toPageNo!='-1'){document.getElementById('pageNo').value = toPageNo;}");
			sb.append("if(!isPlus(document.getElementById('pageNo').value)){alert('请您输入大于0的数字');return;}");
			sb.append("if(document.getElementById('pageNo').value > " + total + "){alert('您输入的页数已经超过最大页数，最大页数是" + total + "');return;}");
			sb.append("document.getElementById('pageForm').submit();}");
			
			sb.append("function isPlus(value){");
			sb.append("ValidationExpression=/^[0-9]+$/;");
			sb.append("if (ValidationExpression.test(value))return true;");
			sb.append("return false;");
			sb.append("}");
			
			sb.append("</script>");
		}

		try {
			JspWriter out = pageContext.getOut();
			out.write(sb.toString());
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}

	}

	public String getOtherParams() {
		return otherParams;
	}

}
