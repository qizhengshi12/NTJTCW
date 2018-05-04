package com.safety.entity;

import java.util.List;

public class PaginationTool {
	private int pageNo;// 当前页

	private int pageSize;// 每页记录数

	private int pageCount;// 总页数

	private int len;// 总记录数

	private int begin;

	public int getBegin() {
		return begin;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List getList() {

		return null;
	}

	public int getLen() {
		return len;
	}

	public PaginationTool() {
	}

	public PaginationTool(int pageNo, int len, int pageSize) {
		this.pageNo = pageNo;
		this.len = len;
		this.pageSize = pageSize;
		this.execute();
	}

	public void execute() {
		pageCount = (len + pageSize - 1) / pageSize;
		begin = (pageNo - 1) * pageSize;
	}

	/**
	 * 封装分页工具栏方法一 样式：首页 第一页 下一页 尾页 第 1/4页 go
	 */

	public String printPage() {
		StringBuffer sb = new StringBuffer();
		if (pageCount == 1) {
			sb.append("首页&nbsp;第一页&nbsp;下一页&nbsp;尾页");
		} else if (pageNo == 1) {
			sb.append("首页&nbsp;上一页 <a href='javascript:skipTo(")
			.append(pageNo + 1)
			.append(")'>下一页</a> <a href='javascript:skipTo(")
			.append(pageCount)
			.append(")'>尾页</a>");
		} else if (pageNo == pageCount) {
			sb.append("<a href='javascript:skipTo(")
			.append(1)
			.append(")'>首页</a> <a href='javascript:skipTo(")
			.append(pageNo - 1)
			.append(")'>上一页</a> 下一页 尾页");
		} else {
			sb.append("<a href='javascript:skipTo(")
			.append(1)
			.append(")'>首页</a> <a href='javascript:skipTo(")
			.append(pageNo - 1)
			.append(")'>上一页</a> <a href='javascript:skipTo(")
			.append(pageNo + 1)
			.append(")'>下一页</a> <a href='javascript:skipTo(")
			.append(pageCount)
			.append(")'>尾页</a>");
		}
		sb.append(" 第<input type='text' size='2' id='chooseNum' value='")
		.append(this.pageNo).append("' class='STYLE1'>页/&nbsp;共")
		.append(this.getPageCount()).append("页 ");
		if (pageCount != 1) {
			sb.append(" <img alt='跳转' align='middle' width='30px' height='30px' title='跳转' style='cursor: pointer;' src='images/small/twitter_bird.png'  onclick='goNum()' onmousedown=\"this.style.border='solid 1px #A9A9A9'\" onmouseup=\"this.style.border='solid 0px'\">");
		}
		sb.append("</td><td align='right'>共")
		.append(this.len).append("条记录");
		return sb.toString();
	}

	/**
	 * 封装分页工具栏方法二 样式：首页 1 2 3 4 5 尾页 第 页 go 共 页
	 */

//	public String printPage() {
//		this.outPageNo(5);
//		StringBuffer sb = new StringBuffer();
//		sb.append("<tr><td>");
//		if (this.pageNo == 1)
//			sb.append("首页</td>");
//		else
//			sb.append("<a href='javascript:skipTo(1)'>首页</a></td>");
//		for (int i = beginPageNo; i <= endPageNo; i++) {
//			if (i == this.pageNo) {
//				sb.append("<td>").append(i).append("</td>");
//			} else {
//				sb.append("<td><a href='javascript:skipTo(").append(i).append(
//						")'>").append(i).append("</a></td>");
//			}
//		}
//		if (this.pageNo == this.pageCount)
//			sb.append("<td>尾页</td>");
//		else
//			sb
//					.append("<td><a href='javascript:skipTo(")
//					.append(this.pageCount)
//					.append(")'>尾页</a></td>")
//					.append(
//							"<td>跳到第<input type='text' id='aaa' size='2' > <input type='submit' name='method' value='go'>页 </td><td>共")
//					.append(this.len).append("条主题</td></tr>");
//		return sb.toString();
//	}
//
//	protected void outPageNo(int showLen) {
//		int mid = showLen / 2;
//		if (pageCount - pageNo > mid) {
//			beginPageNo = (pageNo > mid) ? pageNo - mid + 1 : 1;
//			endPageNo = beginPageNo + showLen - 1;
//			endPageNo = (endPageNo > pageCount) ? pageCount : endPageNo;
//		} else {
//			beginPageNo = (pageCount > showLen) ? pageCount - showLen + 1 : 1;
//			endPageNo = pageCount;
//		}
//	}
}
