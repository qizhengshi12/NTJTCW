package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.SendMessage;

public class MessageServlet  extends HttpServlet{
	/**
	 * ����ƽ̨
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
	/**
	 * ����Servlet����
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("newMessage".equals(action)){//���ŷ��ͽ���
			newMessage(request,response);
		}else if("sendMessage".equals(action)){//�����¶���
			sendMessage(request,response);
		}else if("MyMessage".equals(action)){//�ѽ��ն���
			MyMessage(request,response);
		}else if("updateMyMessage".equals(action)){//�ѽ��ն���_����״̬
			updateMyMessage(request,response);
		}else if("deleteMyMessage".equals(action)){//�ѽ��ն���_ɾ��
			deleteMyMessage(request,response);
		}else if("deleteSomeMyMessage".equals(action)){//�ѽ��ն���_����ɾ��
			deleteSomeMyMessage(request,response);
		}else if("allSendMessage".equals(action)){//�ѷ��Ͷ���
			allSendMessage(request,response);
		}else if("deleteSendMessage".equals(action)){//�ѷ��Ͷ���_ɾ��
			deleteSendMessage(request,response);
		}else if("deleteSomeSendMessage".equals(action)){//�ѷ��Ͷ���_����ɾ��
			deleteSomeSendMessage(request,response);
		}else if("sendMessageOne".equals(action)){//���͵������ţ�������֯��Ϣ������뷢���ţ�
			sendMessageOne(request,response);
		}else if("forwarding".equals(action)){//ת��
			forwarding(request,response);
		}
	}

	/*
	 *  ���ŷ��ͽ���
	 * 
	 */
	protected void newMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			request.getRequestDispatcher("/jsp/Message/sendMessage.jsp").forward(request,
					response);
		}
	}

	/*
	 *  �����¶���
	 * 
	 */
	protected void sendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
		}else{
			String dxnr = request.getParameter("dxnr");
			dxnr = dxnr.replaceAll("\r\n", "<br>");
			String ryID = request.getParameter("ryID");
			String ry = request.getParameter("ry");
//			String sj = request.getParameter("sj");
//			String fssj = request.getParameter("fssj");
			SendMessage sendMessage = new SendMessage();
			MessageDao messageDao = new MessageDao();
			sendMessage.setCzr(UserInfo.getName());
			sendMessage.setCzrID(UserInfo.getUsername());
			sendMessage.setSffs("δ����");//����״̬
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			sendMessage.setCzsj(data1);

			//��������ռ���
			ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
			String[] tzryIDList = ryID.split(",");
			String phoneList ="";
			for(int i=0;i<tzryIDList.length;i++){
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(tzryIDList[i]);
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("δ����");
				MyMessageList.add(myMessage);
				//��ѯ����
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(tzryIDList[i]);
			}
			//��������ռ���
			messageDao.insertMyMessage(MyMessageList);

			// TODO Auto-generated method stub
			//���Ͷ���
			messagePlatForm.submitShortMessageMany(phoneList,UserInfo.getName()+"��"+dxnr);
			sendMessage.setSffs("�ѷ���");
			sendMessage.setFssj(data1);
			sendMessage.setDxnr(dxnr);
			sendMessage.setJsr(ry);
			sendMessage.setJsrID(ryID);
			messageDao.insertMessage(sendMessage);
			result = "���ͳɹ�";
			request.setAttribute("result", result);			
			request.getRequestDispatcher("/jsp/Message/sendMessage.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���͵������ţ�������֯��Ϣ������뷢���ţ�
	 * 
	 */
	protected void sendMessageOne(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "����ʧ��";
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
		}else{
			String phone = request.getParameter("phone");
			String dxnr = request.getParameter("dxnr");
			String ryID = request.getParameter("ryID");
			String ry = request.getParameter("ry");
			SendMessage sendMessage = new SendMessage();
			MessageDao messageDao = new MessageDao();
			sendMessage.setCzr(UserInfo.getName());
			sendMessage.setCzrID(UserInfo.getUsername());
			sendMessage.setSffs("δ����");//����״̬
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			sendMessage.setCzsj(data1);

			// TODO Auto-generated method stub
			//���Ͷ���
			String res = messagePlatForm.submitShortMessage(phone,UserInfo.getName()+"��"+dxnr);
//			String res = "1";
			if("1".equals(res)){
				//�����ռ���
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(ryID);
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("δ����");
				messageDao.insertMyMessageOne(myMessage);
				//���뷢����
				sendMessage.setSffs("�ѷ���");
				sendMessage.setFssj(data1);
				sendMessage.setDxnr(dxnr);
				sendMessage.setJsr(ry);
				sendMessage.setJsrID(ryID);
				messageDao.insertMessage(sendMessage);
				result = "���ͳɹ�";
			}

			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.print(result);
			out.flush();  
			out.close();
		}
	}

	/*
	 *  �ѽ��ܶ���
	 * 
	 */
	protected void MyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
		}else{
			
			//��ѯ�ռ����б�
			ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String srbt = request.getParameter("srbt");
			String dxnr = request.getParameter("dxnr");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			MessageDao messageDao = new MessageDao();
			countAll = messageDao.queryMyMessageListCount(srbt,UserInfo.getUsername());
			MyMessageList = messageDao.queryMyMessageList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("MyMessageList", MyMessageList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("dxnr", dxnr);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			
			request.getRequestDispatcher("/jsp/Message/myMessage.jsp").forward(request,
					response);
		}
	}
	/*
	 * AJAX
	 * �ѽ��ն���_����״̬
	 */
	protected void updateMyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(id)&&id!=null){
			messageDao.updateMyMessageById(Integer.parseInt(id));
		}
		
	}
	/*
	 *  ɾ���ռ���
	 * 
	 */
	protected void deleteMyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(id)&&id!=null){
			messageDao.DeleteMyMessageById(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=MyMessage&result='ɾ���ɹ�'&srbt=");
		rd.forward(request, response);
		return;
	}
	/*
	 *  ɾ���ռ��䣨����ɾ����
	 * 
	 */
	protected void deleteSomeMyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String IDList = request.getParameter("IDList");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(IDList)&&IDList!=null){
			messageDao.DeleteMyMessageByIDList(IDList);
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=MyMessage&result='ɾ���ɹ�'&srbt=");
		rd.forward(request, response);
		return;
	}
	/*
	 *  �ѷ��Ͷ���
	 * 
	 */
	protected void allSendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
		}else{
			
			//��ѯ�������б�
			ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String srbt = request.getParameter("srbt");
			String dxnr = request.getParameter("dxnr");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			MessageDao messageDao = new MessageDao();
			countAll = messageDao.querySendMessageListCount(srbt,UserInfo.getUsername());
			SendMessageList = messageDao.querySendMessageList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("SendMessageList", SendMessageList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("dxnr", dxnr);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			
			request.getRequestDispatcher("/jsp/Message/allSendMessage.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ɾ��������
	 * 
	 */
	protected void deleteSendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(id)&&id!=null){
			messageDao.DeleteSendMessageById(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=allSendMessage&result='ɾ���ɹ�'&srbt=");
		rd.forward(request, response);
		return;
	}

	/*
	 *  ɾ�������䣨����ɾ����
	 * 
	 */
	protected void deleteSomeSendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String IDList = request.getParameter("IDList");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(IDList)&&IDList!=null){
			messageDao.DeleteSendMessageByIDList(IDList);
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=allSendMessage&result='ɾ���ɹ�'&srbt=");
		rd.forward(request, response);
		return;
	}
	
	/*
	 *  ����ת��
	 * 
	 */
	protected void forwarding(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String dxnr = request.getParameter("dxnr");
			dxnr = dxnr.replaceAll("<br>", "\n");
			request.setAttribute("dxnr", dxnr);
			request.getRequestDispatcher("/jsp/Message/sendMessage.jsp").forward(request,
					response);
		}
	}
}
