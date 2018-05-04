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
	 * 短信平台
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
	/**
	 * 请求Servlet方法
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("newMessage".equals(action)){//短信发送界面
			newMessage(request,response);
		}else if("sendMessage".equals(action)){//发送新短信
			sendMessage(request,response);
		}else if("MyMessage".equals(action)){//已接收短信
			MyMessage(request,response);
		}else if("updateMyMessage".equals(action)){//已接收短信_更新状态
			updateMyMessage(request,response);
		}else if("deleteMyMessage".equals(action)){//已接收短信_删除
			deleteMyMessage(request,response);
		}else if("deleteSomeMyMessage".equals(action)){//已接收短信_批量删除
			deleteSomeMyMessage(request,response);
		}else if("allSendMessage".equals(action)){//已发送短信
			allSendMessage(request,response);
		}else if("deleteSendMessage".equals(action)){//已发送短信_删除
			deleteSendMessage(request,response);
		}else if("deleteSomeSendMessage".equals(action)){//已发送短信_批量删除
			deleteSomeSendMessage(request,response);
		}else if("sendMessageOne".equals(action)){//发送单条短信（用于组织信息点击号码发短信）
			sendMessageOne(request,response);
		}else if("forwarding".equals(action)){//转发
			forwarding(request,response);
		}
	}

	/*
	 *  短信发送界面
	 * 
	 */
	protected void newMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			request.getRequestDispatcher("/jsp/Message/sendMessage.jsp").forward(request,
					response);
		}
	}

	/*
	 *  发送新短信
	 * 
	 */
	protected void sendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
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
			sendMessage.setSffs("未发送");//发送状态
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			sendMessage.setCzsj(data1);

			//加入各人收件箱
			ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
			String[] tzryIDList = ryID.split(",");
			String phoneList ="";
			for(int i=0;i<tzryIDList.length;i++){
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(tzryIDList[i]);
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MyMessageList.add(myMessage);
				//查询号码
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(tzryIDList[i]);
			}
			//加入各人收件箱
			messageDao.insertMyMessage(MyMessageList);

			// TODO Auto-generated method stub
			//发送短信
			messagePlatForm.submitShortMessageMany(phoneList,UserInfo.getName()+"："+dxnr);
			sendMessage.setSffs("已发送");
			sendMessage.setFssj(data1);
			sendMessage.setDxnr(dxnr);
			sendMessage.setJsr(ry);
			sendMessage.setJsrID(ryID);
			messageDao.insertMessage(sendMessage);
			result = "发送成功";
			request.setAttribute("result", result);			
			request.getRequestDispatcher("/jsp/Message/sendMessage.jsp").forward(request,
					response);
		}
	}
	/*
	 *  发送单条短信（用于组织信息点击号码发短信）
	 * 
	 */
	protected void sendMessageOne(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "发送失败";
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
		}else{
			String phone = request.getParameter("phone");
			String dxnr = request.getParameter("dxnr");
			String ryID = request.getParameter("ryID");
			String ry = request.getParameter("ry");
			SendMessage sendMessage = new SendMessage();
			MessageDao messageDao = new MessageDao();
			sendMessage.setCzr(UserInfo.getName());
			sendMessage.setCzrID(UserInfo.getUsername());
			sendMessage.setSffs("未发送");//发送状态
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			sendMessage.setCzsj(data1);

			// TODO Auto-generated method stub
			//发送短信
			String res = messagePlatForm.submitShortMessage(phone,UserInfo.getName()+"："+dxnr);
//			String res = "1";
			if("1".equals(res)){
				//加入收件箱
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(ryID);
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				messageDao.insertMyMessageOne(myMessage);
				//加入发件箱
				sendMessage.setSffs("已发送");
				sendMessage.setFssj(data1);
				sendMessage.setDxnr(dxnr);
				sendMessage.setJsr(ry);
				sendMessage.setJsrID(ryID);
				messageDao.insertMessage(sendMessage);
				result = "发送成功";
			}

			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.print(result);
			out.flush();  
			out.close();
		}
	}

	/*
	 *  已接受短信
	 * 
	 */
	protected void MyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
		}else{
			
			//查询收件箱列表
			ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
			//分页;
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
	 * 已接收短信_更新状态
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
	 *  删除收件箱
	 * 
	 */
	protected void deleteMyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(id)&&id!=null){
			messageDao.DeleteMyMessageById(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=MyMessage&result='删除成功'&srbt=");
		rd.forward(request, response);
		return;
	}
	/*
	 *  删除收件箱（批量删除）
	 * 
	 */
	protected void deleteSomeMyMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String IDList = request.getParameter("IDList");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(IDList)&&IDList!=null){
			messageDao.DeleteMyMessageByIDList(IDList);
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=MyMessage&result='删除成功'&srbt=");
		rd.forward(request, response);
		return;
	}
	/*
	 *  已发送短信
	 * 
	 */
	protected void allSendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
		}else{
			
			//查询发件箱列表
			ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
			//分页;
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
	 *  删除发件箱
	 * 
	 */
	protected void deleteSendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(id)&&id!=null){
			messageDao.DeleteSendMessageById(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=allSendMessage&result='删除成功'&srbt=");
		rd.forward(request, response);
		return;
	}

	/*
	 *  删除发件箱（批量删除）
	 * 
	 */
	protected void deleteSomeSendMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String IDList = request.getParameter("IDList");
		MessageDao messageDao = new MessageDao();
		if(!"".equals(IDList)&&IDList!=null){
			messageDao.DeleteSendMessageByIDList(IDList);
		}
		RequestDispatcher rd = request.getRequestDispatcher("MessageServlet?action=allSendMessage&result='删除成功'&srbt=");
		rd.forward(request, response);
		return;
	}
	
	/*
	 *  短信转发
	 * 
	 */
	protected void forwarding(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String dxnr = request.getParameter("dxnr");
			dxnr = dxnr.replaceAll("<br>", "\n");
			request.setAttribute("dxnr", dxnr);
			request.getRequestDispatcher("/jsp/Message/sendMessage.jsp").forward(request,
					response);
		}
	}
}
