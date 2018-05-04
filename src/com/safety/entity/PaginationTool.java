package com.safety.entity;

import java.util.List;

public class PaginationTool {
	private int pageNo;// ��ǰҳ

	private int pageSize;// ÿҳ��¼��

	private int pageCount;// ��ҳ��

	private int len;// �ܼ�¼��

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
	 * ��װ��ҳ����������һ ��ʽ����ҳ ��һҳ ��һҳ βҳ �� 1/4ҳ go
	 */

	public String printPage() {
		StringBuffer sb = new StringBuffer();
		if (pageCount == 1) {
			sb.append("��ҳ&nbsp;��һҳ&nbsp;��һҳ&nbsp;βҳ");
		} else if (pageNo == 1) {
			sb.append("��ҳ&nbsp;��һҳ <a href='javascript:skipTo(")
			.append(pageNo + 1)
			.append(")'>��һҳ</a> <a href='javascript:skipTo(")
			.append(pageCount)
			.append(")'>βҳ</a>");
		} else if (pageNo == pageCount) {
			sb.append("<a href='javascript:skipTo(")
			.append(1)
			.append(")'>��ҳ</a> <a href='javascript:skipTo(")
			.append(pageNo - 1)
			.append(")'>��һҳ</a> ��һҳ βҳ");
		} else {
			sb.append("<a href='javascript:skipTo(")
			.append(1)
			.append(")'>��ҳ</a> <a href='javascript:skipTo(")
			.append(pageNo - 1)
			.append(")'>��һҳ</a> <a href='javascript:skipTo(")
			.append(pageNo + 1)
			.append(")'>��һҳ</a> <a href='javascript:skipTo(")
			.append(pageCount)
			.append(")'>βҳ</a>");
		}
		sb.append(" ��<input type='text' size='2' id='chooseNum' value='")
		.append(this.pageNo).append("' class='STYLE1'>ҳ/&nbsp;��")
		.append(this.getPageCount()).append("ҳ ");
		if (pageCount != 1) {
			sb.append(" <img alt='��ת' align='middle' width='30px' height='30px' title='��ת' style='cursor: pointer;' src='images/small/twitter_bird.png'  onclick='goNum()' onmousedown=\"this.style.border='solid 1px #A9A9A9'\" onmouseup=\"this.style.border='solid 0px'\">");
		}
		sb.append("</td><td align='right'>��")
		.append(this.len).append("����¼");
		return sb.toString();
	}

	/**
	 * ��װ��ҳ������������ ��ʽ����ҳ 1 2 3 4 5 βҳ �� ҳ go �� ҳ
	 */

//	public String printPage() {
//		this.outPageNo(5);
//		StringBuffer sb = new StringBuffer();
//		sb.append("<tr><td>");
//		if (this.pageNo == 1)
//			sb.append("��ҳ</td>");
//		else
//			sb.append("<a href='javascript:skipTo(1)'>��ҳ</a></td>");
//		for (int i = beginPageNo; i <= endPageNo; i++) {
//			if (i == this.pageNo) {
//				sb.append("<td>").append(i).append("</td>");
//			} else {
//				sb.append("<td><a href='javascript:skipTo(").append(i).append(
//						")'>").append(i).append("</a></td>");
//			}
//		}
//		if (this.pageNo == this.pageCount)
//			sb.append("<td>βҳ</td>");
//		else
//			sb
//					.append("<td><a href='javascript:skipTo(")
//					.append(this.pageCount)
//					.append(")'>βҳ</a></td>")
//					.append(
//							"<td>������<input type='text' id='aaa' size='2' > <input type='submit' name='method' value='go'>ҳ </td><td>��")
//					.append(this.len).append("������</td></tr>");
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
