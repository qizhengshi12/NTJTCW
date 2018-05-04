package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.HtbbDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.HtbbSetTime;
import com.safety.entity.Permissions;

public class HtbbServlet   extends HttpServlet{
	/**
	 * ����Servlet����
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("getHtbb".equals(action)){//��̨����
			getHtbb(request,response);
		}else if("setTime".equals(action)){//����������
			setTime(request,response);
		}else if("SaveHtbb".equals(action)){//������޸�
			SaveHtbb(request,response);
		}
	}

	/*
	 *  ��̨����
	 * 
	 */
	protected void getHtbb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if("����Ա".equals(UserInfo.getRoles())||"��������".equals(UserInfo.getRoles())||"��Ƹ�����".equals(UserInfo.getRoles())){
			String result = request.getParameter("result");
			// List
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatementsManager/getHtbb.jsp").forward(request,
					response);
		}else{
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ����������
	 * 
	 */
	protected void setTime(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String bbID = request.getParameter("bbID");
			//��ѯ��Ա����
			HtbbSetTime htbbSetTime = new HtbbSetTime();
			HtbbDao htbbDao = new HtbbDao();
			htbbSetTime = htbbDao.querySetTime(Integer.parseInt(bbID));
			htbbSetTime.setBbID(Integer.parseInt(bbID));
			if("1".equals(bbID)){
				htbbSetTime.setBbmc("Ԥ��ִ�б�");
			}else if("2".equals(bbID)){
				htbbSetTime.setBbmc("��ҵ��λ��Ҫ����ָ���");
			}else if("3".equals(bbID)){
				htbbSetTime.setBbmc("�������ѱ�");
			}else if("4".equals(bbID)){
				htbbSetTime.setBbmc("�ʲ���ծ��");
			}else if("5".equals(bbID)){
				htbbSetTime.setBbmc("�����");
			}else if("6".equals(bbID)){
				htbbSetTime.setBbmc("��ʵ�շ����߼����Բ��");
			}else if("7".equals(bbID)){
				htbbSetTime.setBbmc("��Ҫ����ָ��ͳ�Ʊ������С��˹ܴ���");
			}else if("8".equals(bbID)){
				htbbSetTime.setBbmc("��Ҫ����ָ��ͳ�Ʊ���������");
			}else if("9".equals(bbID)){
				htbbSetTime.setBbmc("���˳�վ������ͳ�Ʊ����˼��ţ�");
			}else if("10".equals(bbID)){
				htbbSetTime.setBbmc("����ǩ֤�¶�ͬ�ȱ����¾֣�");
			}
			if(htbbSetTime.getTznr()==null){
				htbbSetTime.setTzry("");
				htbbSetTime.setTzryID("");
			}
			request.setAttribute("htbbSetTime", htbbSetTime);
			request.getRequestDispatcher("/jsp/StatementsManager/setTime.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ������޸�
	 * 
	 */
	protected void SaveHtbb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String ID = request.getParameter("ID");
			String bbID = request.getParameter("bbID");
			String setlx = request.getParameter("setlx");
			String mday = request.getParameter("mday");
			String qmonth = request.getParameter("qmonth");
			String qmday = request.getParameter("qmday");
			String yday = request.getParameter("yday");
			String tznr = request.getParameter("tznr");
			String tzry = request.getParameter("tzry");
			String tzryID = request.getParameter("tzryID");
			//��ʱ��������Ϊ�գ��޷�����
			if(!"4".equals(setlx)){
				yday="2000-01-01";
			}
			
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			HtbbDao htbbDao = new HtbbDao();
			HtbbSetTime htbbSetTime = new HtbbSetTime();
			htbbSetTime.setBbID(Integer.parseInt(bbID));
			htbbSetTime.setMday(Integer.parseInt(mday));
			htbbSetTime.setQmonth(Integer.parseInt(qmonth));
			htbbSetTime.setQmday(Integer.parseInt(qmday));
			htbbSetTime.setSetlx(Integer.parseInt(setlx));
			htbbSetTime.setYday(DateFormat(yday));
			htbbSetTime.setTznr(tznr);
			htbbSetTime.setTzry(tzry);
			htbbSetTime.setTzryID(tzryID);
			htbbSetTime.setCzr(UserInfo.getName());
			htbbSetTime.setCzrID(UserInfo.getUsername());
			htbbSetTime.setCzsj(data1);
			//������޸�
			if("0".equals(ID)||ID==null){
				htbbDao.insertSetTime(htbbSetTime);
			}else{
				htbbSetTime.setId(Integer.parseInt(ID));
				htbbDao.updateSetTime(htbbSetTime);
			}

			request.setAttribute("result", "���óɹ�");
			request.getRequestDispatcher("HtbbServlet?action=setTime&bbID="+bbID).forward(request,
					response);
		}
	}

	private java.sql.Date DateFormat(String DString){
		if(!"".equals(DString)&&DString!=null){
			java.util.Date date = null;
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				date = format.parse(DString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date resDate = new java.sql.Date(date.getTime());
			return resDate;
		}else{
			return null;
		}
	}
}
