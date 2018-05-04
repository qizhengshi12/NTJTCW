package com.safety.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.EmailDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyEmail;
import com.safety.entity.PaginationTool;
import com.safety.entity.SendEmail;

public class EmailServlet  extends HttpServlet{
	/**
	 * 邮件平台
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
		if("NewEmail".equals(action)){//进入新邮件页面.
			NewEmail(request,response);
		}else if("insertEmail".equals(action)){//发送新邮件.
			insertEmail(request,response);
		}else if("MyEmail".equals(action)){//查询已接收邮件.
			MyEmail(request,response);
		}else if("AllSendEmail".equals(action)){//查询已发送邮件.
			AllSendEmail(request,response);
		}else if("DraftEmail".equals(action)){//查询草稿箱（只有发件可以存草稿）
			DraftEmail(request,response);
		}else if("MyDelEmail".equals(action)){//查询回收站（只有收件可以回收站）
			MyDelEmail(request,response);
		}else if("deleteMyEmail".equals(action)){//已接收邮件_删除
			deleteMyEmail(request,response);
		}else if("deleteSomeMyEmail".equals(action)){//已接收邮件_批量删除
			deleteSomeMyEmail(request,response);
		}else if("deleteSendEmail".equals(action)){//已发送邮件_删除
			deleteSendEmail(request,response);
		}else if("deleteSomeSendEmail".equals(action)){//已发送邮件_批量删除
			deleteSomeSendEmail(request,response);
		}else if("ShowEmail".equals(action)){//查看接收邮件详细信息
			ShowEmail(request,response);
		}else if("downLoadMy".equals(action)){//下载文件（收件箱）.
			downLoadMy(request,response);
		}else if("ShowSendEmail".equals(action)){//查看发送邮件详细信息
			ShowSendEmail(request,response);
		}else if("downLoadSe".equals(action)){//下载文件（发件箱）.
			downLoadSe(request,response);
		}else if("returnMyEmail".equals(action)){//回收站还原至收件箱
			returnMyEmail(request,response);
		}else if("EditDraftEmail".equals(action)){//编辑草稿箱邮件
			EditDraftEmail(request,response);
		}else if("downLoadEd".equals(action)){//下载文件（编辑箱）.
			downLoadEd(request,response);
		}else if("insertDraftEmail".equals(action)){//发送草稿箱邮件
			insertDraftEmail(request,response);
		}else if("EditEmail".equals(action)){//回复、转发邮件
			EditEmail(request,response);
		}
	}
	/*
	 *  查询已接收邮件
	 * 
	 */
	protected void MyEmail(HttpServletRequest request,
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
			ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String flag = request.getParameter("flag");
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
//			String cxfsr = request.getParameter("cxfsr");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//从菜单进入，无查询条件
			if("1".equals(flag)){
				srbt=" where sczt ='1'";
				cxbt="";
//				cxfsr="";
				cxssrq1="";
				cxssrq2="";
			}
			EmailDao emailDao = new EmailDao();
			countAll = emailDao.queryMyEmailListCount(srbt,UserInfo.getUsername());
			MyEmailList = emailDao.queryMyEmailList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("MyEmailList", MyEmailList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
//			request.setAttribute("cxfsr", cxfsr);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/Email/myEmail.jsp").forward(request,
					response);
		}
	}

	/*
	 *  查询回收箱邮件
	 * 
	 */
	protected void MyDelEmail(HttpServletRequest request,
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
			ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String flag = request.getParameter("flag");
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//从菜单进入，无查询条件
			if("1".equals(flag)){
				srbt=" where sczt ='2' ";
				cxbt="";
				cxssrq1="";
				cxssrq2="";
			}
			EmailDao emailDao = new EmailDao();
			countAll = emailDao.queryMyEmailListCount(srbt,UserInfo.getUsername());
			MyEmailList = emailDao.queryMyEmailList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("MyEmailList", MyEmailList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/Email/myDelEmail.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新邮件页面
	 * 
	 */
	protected void NewEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			request.getRequestDispatcher("/jsp/Email/newEmail.jsp").forward(request,
					response);
		}
	}

	/*
	 *  发送新邮件
	 * 
	 */
	protected void insertEmail(HttpServletRequest request,
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
			String bt = request.getParameter("bt");
			String nr = request.getParameter("nr");
			String jsr = request.getParameter("jsr");
			String jsrID = request.getParameter("jsrID");
			String msr = request.getParameter("msr");
			String msrID = request.getParameter("msrID");
			String FileUrl =  request.getParameter("FileUrl");
			String fszt =  request.getParameter("fszt");
			SendEmail sendEmail = new SendEmail();
			EmailDao emailDao = new EmailDao();
			sendEmail.setCzr(UserInfo.getName());
			sendEmail.setCzrID(UserInfo.getUsername());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			sendEmail.setCzsj(data1);
			sendEmail.setBt(bt);
			sendEmail.setNr(nr);
			sendEmail.setJsr(jsr);
			sendEmail.setJsrID(jsrID);
			sendEmail.setMsr(msr);
			sendEmail.setMsrID(msrID);
			String alljsr = jsr;
			if(!"".equals(msr)&&msr!=null){
				alljsr = alljsr+","+msr;
			}
			sendEmail.setWdr(alljsr);
			sendEmail.setFileUrl(FileUrl);
			sendEmail.setFszt(fszt);//1：普通发送  2：存草稿
			sendEmail.setSczt("1");//默认未删除
			int sendid = emailDao.insertEmail(sendEmail);
			if("1".equals(fszt)){
				//加入各人收件箱
				ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
				String[] tzryList = jsr.split(",");
				String[] tzryIDList = jsrID.split(",");
				String[] mryList = msr.split(",");
				String[] mryIDList = msrID.split(",");
				String phoneList ="";
				for(int i=0;i<tzryIDList.length;i++){
					if(!"".equals(tzryIDList[i])&&tzryIDList[i]!=null){
						MyEmail myEmail = new MyEmail();
						myEmail.setBt(bt);
						myEmail.setSendid(sendid);
						myEmail.setNr(nr);
						myEmail.setJsr(tzryList[i]);
						myEmail.setJsrID(tzryIDList[i]);
						myEmail.setAllfsr(jsr);
						myEmail.setAllfsrID(jsrID);
						myEmail.setFsr(UserInfo.getName());
						myEmail.setFsrID(UserInfo.getUsername());
						myEmail.setFileUrl(FileUrl);
						myEmail.setJssj(data1);
						myEmail.setSczt("1");//默认未删除
						myEmail.setCyzt("1");//1：未查阅 2：已查阅
						MyEmailList.add(myEmail);
						//查询号码
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(tzryIDList[i]);
					}
				}
				for(int j=0;j<mryIDList.length;j++){
					if(!"".equals(mryIDList[j])&&mryIDList[j]!=null){
						MyEmail myEmail = new MyEmail();
						myEmail.setBt(bt);
						myEmail.setSendid(sendid);
						myEmail.setNr(nr);
						myEmail.setJsr(mryList[j]);
						myEmail.setJsrID(mryIDList[j]);
						myEmail.setAllfsr(jsr);
						myEmail.setAllfsrID(jsrID);
						myEmail.setFsr(UserInfo.getName());
						myEmail.setFsrID(UserInfo.getUsername());
						myEmail.setFileUrl(FileUrl);
						myEmail.setJssj(data1);
						myEmail.setSczt("1");//默认未删除
						myEmail.setCyzt("1");//1：未查阅 2：已查阅
						MyEmailList.add(myEmail);
						//查询号码
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(mryIDList[j]);
					}
				}
				//加入各人收件箱
				emailDao.insertMyEmail(MyEmailList);
	
				// TODO Auto-generated method stub
				//发送邮件提醒
				messagePlatForm.submitShortMessageMany(phoneList,UserInfo.getName()+"：您有一封新邮件需要查收。");
			}
			if("1".equals(fszt)){
				result = "发送成功";
				request.getRequestDispatcher("EmailServlet?action=AllSendEmail&flag=1&result="+result).forward(request,
						response);
			}else{
				result = "保存成功";
				request.getRequestDispatcher("EmailServlet?action=DraftEmail&flag=1&result="+result).forward(request,
						response);
			}
		}
	}
	/*
	 *  删除收件箱（直接删除收件箱或者清空回收站内容）
	 * 
	 */
	protected void deleteMyEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String cdsc = request.getParameter("cdsc");//是否彻底删除
		String ly = request.getParameter("ly");//来源页面
		EmailDao emailDao = new EmailDao();
		//删除到回收站
		if("1".equals(cdsc)){
			if(!"".equals(id)&&id!=null){
				emailDao.UpdateMyEmailByID(Integer.parseInt(id));
			}
		}else{
			if(!"".equals(id)&&id!=null){
				emailDao.DeleteMyEmailById(Integer.parseInt(id));
			}
		}
		if("1".equals(ly)){
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=MyEmail&flag=1");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=MyDelEmail&flag=1");
			rd.forward(request, response);
		}
		return;
	}
	/*
	 *  删除收件箱（批量删除）
	 * 
	 */
	protected void deleteSomeMyEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String IDList = request.getParameter("IDList");
		String cdsc = request.getParameter("cdsc");
		String ly = request.getParameter("ly");//来源页面
		EmailDao emailDao = new EmailDao();
		//删除到回收站
		if("1".equals(cdsc)){
			if(!"".equals(IDList)&&IDList!=null){
				emailDao.UpdateMyEmailByIDList(IDList);
			}
		}else{
			if(!"".equals(IDList)&&IDList!=null){
				emailDao.DeleteMyEmailByIDList(IDList);
			}
		}
		if("1".equals(ly)){
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=MyEmail&flag=1");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=MyDelEmail&flag=1");
			rd.forward(request, response);
		}
		return;
	}
	/*
	 *  查询已发送邮件
	 * 
	 */
	protected void AllSendEmail(HttpServletRequest request,
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
			ArrayList<SendEmail> SendEmailList = new ArrayList<SendEmail>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String flag = request.getParameter("flag");
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//从菜单进入，无查询条件
			if("1".equals(flag)){
				srbt=" where fszt ='1' and sczt='1' ";
				cxbt="";
				cxssrq1="";
				cxssrq2="";
			}
			EmailDao EmailDao = new EmailDao();
			countAll = EmailDao.querySendEmailListCount(srbt,UserInfo.getUsername());
			SendEmailList = EmailDao.querySendEmailList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("SendEmailList", SendEmailList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			
			request.getRequestDispatcher("/jsp/Email/allSendEmail.jsp").forward(request,
					response);
		}
	}

	/*
	 *  查询草稿箱（只有发件可以存草稿）
	 * 
	 */
	protected void DraftEmail(HttpServletRequest request,
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
			ArrayList<SendEmail> SendEmailList = new ArrayList<SendEmail>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String flag = request.getParameter("flag");
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//从菜单进入，无查询条件
			if("1".equals(flag)){
				srbt=" where fszt ='2' ";
				cxbt="";
				cxssrq1="";
				cxssrq2="";
			}
			EmailDao EmailDao = new EmailDao();
			countAll = EmailDao.querySendEmailListCount(srbt,UserInfo.getUsername());
			SendEmailList = EmailDao.querySendEmailList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("SendEmailList", SendEmailList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			
			request.getRequestDispatcher("/jsp/Email/draftEmail.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  删除发件箱_直接删除
	 * 
	 */
	protected void deleteSendEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String ly = request.getParameter("ly");//来源页面
//		String zt = request.getParameter("zt");
		EmailDao EmailDao = new EmailDao();
		if(!"".equals(id)&&id!=null){
			EmailDao.DeleteSendEmailById(Integer.parseInt(id));
		}
		if("1".equals(ly)){
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=AllSendEmail&flag=1");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=DraftEmail&flag=1");
			rd.forward(request, response);
		}
		return;
	}

	/*
	 *  删除发件箱（批量删除）
	 * 
	 */
	protected void deleteSomeSendEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String IDList = request.getParameter("IDList");
		String ly = request.getParameter("ly");//来源页面
		EmailDao EmailDao = new EmailDao();
		if(!"".equals(IDList)&&IDList!=null){
			EmailDao.DeleteSendEmailByIDList(IDList);
		}
		if("1".equals(ly)){
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=AllSendEmail&flag=1");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=DraftEmail&flag=1");
			rd.forward(request, response);
		}
		return;
	}
	
	/*
	 *  查询已接收邮件_详细信息
	 * 
	 */
	protected void ShowEmail(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String zt = request.getParameter("zt");//判断是否需要更新查阅状态
			String ly = request.getParameter("ly");
			String sendid = request.getParameter("sendid");
			EmailDao emailDao = new EmailDao();
			MyEmail myEmail = new MyEmail();
			myEmail = emailDao.queryMyEmailByID(Integer.parseInt(id));
			if("1".equals(zt)){
				emailDao.updateCyztByID(Integer.parseInt(id));
				//更新未读人员
				SendEmail sendEmail = new SendEmail();
				sendEmail = emailDao.querySendEmailByID(Integer.parseInt(sendid));
				String wdr ="";
				String jsrStr[] = sendEmail.getWdr().split(",");
				for(int i=0; i< jsrStr.length; i++){
					if(!UserInfo.getName().equals(jsrStr[i])){
						if("".equals(wdr)){
							wdr = jsrStr[i];
						}else{
							wdr = wdr+","+jsrStr[i];
						}
					}
				}
				emailDao.updateWdrByID(Integer.parseInt(sendid),wdr);
			}
			request.setAttribute("ly", ly);
			request.setAttribute("myEmail", myEmail);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/Email/showEmail.jsp").forward(request,
					response);
		}
	}

	/*
	 * 下载文件（收件箱）
	 * 
	 */
	protected void downLoadMy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String id = request.getParameter("id");
		String FullFilePath = request.getRealPath("/UserFile/SendEmail") + "/"+URL;
		File file = new File(FullFilePath);  
		if (!"".equals(URL)&&URL!=null&&file.exists()) {
            String filename = URLEncoder.encode(file.getName(),"utf-8");
            response.reset();
            response.setContentType("application/x-msdownload");
            //以附件的形式提示用户下载， 就是你在浏览器打开那个servlet 时将弹出对话框提//示你下载还是保存。  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*如果文件长度大于0*/
            if (fileLength != 0) {
                /*创建输入流*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*创建输出流*/
                ServletOutputStream servletOS = response.getOutputStream();
                int readLength;
                while (((readLength = inStream.read(buf)) != -1)) {
                    servletOS.write(buf, 0, readLength);
                }
                inStream.close();
                servletOS.flush();
                servletOS.close();
            }
        }else{
        	RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=ShowEmail&id="+id+"&result="+"文件不存在，无法下载！");
    		rd.forward(request, response);
    		return ;
        }
	}
	
	/*
	 *  查询已发送邮件_详细信息
	 * 
	 */
	protected void ShowSendEmail(HttpServletRequest request,
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
			String id = request.getParameter("id");
			EmailDao emailDao = new EmailDao();
			SendEmail sendEmail = new SendEmail();
			sendEmail = emailDao.querySendEmailByID(Integer.parseInt(id));
			request.setAttribute("sendEmail", sendEmail);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/Email/showSendEmail.jsp").forward(request,
					response);
		}
	}

	/*
	 * 下载文件（收件箱）
	 * 
	 */
	protected void downLoadSe(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String id = request.getParameter("id");
		String FullFilePath = request.getRealPath("/UserFile/SendEmail") + "/"+URL;
		File file = new File(FullFilePath);  
		if (!"".equals(URL)&&URL!=null&&file.exists()) {
            String filename = URLEncoder.encode(file.getName(),"utf-8");
            response.reset();
            response.setContentType("application/x-msdownload");
            //以附件的形式提示用户下载， 就是你在浏览器打开那个servlet 时将弹出对话框提//示你下载还是保存。  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*如果文件长度大于0*/
            if (fileLength != 0) {
                /*创建输入流*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*创建输出流*/
                ServletOutputStream servletOS = response.getOutputStream();
                int readLength;
                while (((readLength = inStream.read(buf)) != -1)) {
                    servletOS.write(buf, 0, readLength);
                }
                inStream.close();
                servletOS.flush();
                servletOS.close();
            }
        }else{
        	RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=ShowSendEmail&id="+id+"&result="+"文件不存在，无法下载！");
    		rd.forward(request, response);
    		return ;
        }
	}

	/*
	 *  回收站还原至收件箱
	 * 
	 */
	protected void returnMyEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		EmailDao EmailDao = new EmailDao();
		if(!"".equals(id)&&id!=null){
			EmailDao.ReturnMyEmailByID(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=MyEmail&flag=1");
		rd.forward(request, response);
		return;
	}
	/*
	 *  编辑草稿箱邮件
	 * 
	 */
	protected void EditDraftEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");
			String ly = request.getParameter("ly");
			EmailDao emailDao = new EmailDao();
			SendEmail sendEmail = new SendEmail();
			sendEmail = emailDao.querySendEmailByID(Integer.parseInt(id));
			request.setAttribute("ly", ly);
			request.setAttribute("sendEmail", sendEmail);
			request.getRequestDispatcher("/jsp/Email/editDraftEmail.jsp").forward(request,
					response);
		}
	}
	

	/*
	 * 下载文件（编辑箱）
	 * 
	 */
	protected void downLoadEd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String FullFilePath = request.getRealPath("/UserFile/SendEmail") + "/"+URL;
		File file = new File(FullFilePath);  
		if (!"".equals(URL)&&URL!=null&&file.exists()) {
            String filename = URLEncoder.encode(file.getName(),"utf-8");
            response.reset();
            response.setContentType("application/x-msdownload");
            //以附件的形式提示用户下载， 就是你在浏览器打开那个servlet 时将弹出对话框提//示你下载还是保存。  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*如果文件长度大于0*/
            if (fileLength != 0) {
                /*创建输入流*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*创建输出流*/
                ServletOutputStream servletOS = response.getOutputStream();
                int readLength;
                while (((readLength = inStream.read(buf)) != -1)) {
                    servletOS.write(buf, 0, readLength);
                }
                inStream.close();
                servletOS.flush();
                servletOS.close();
            }
        }else{
        	response.setContentType("text/html;charset=gb2312");
    		PrintWriter out = response.getWriter(); 
    		out.println("<script>alert('文件已失效，无法下载！');</script>");
        }
	}
	/*
	 *  发送草稿箱邮件
	 * 
	 */
	protected void insertDraftEmail(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String nr = request.getParameter("nr");
			String jsr = request.getParameter("jsr");
			String jsrID = request.getParameter("jsrID");
			String msr = request.getParameter("msr");
			String msrID = request.getParameter("msrID");
			String FileUrl =  request.getParameter("FileUrl");
			String fszt =  request.getParameter("fszt");
			SendEmail sendEmail = new SendEmail();
			EmailDao emailDao = new EmailDao();
			sendEmail.setCzr(UserInfo.getName());
			sendEmail.setCzrID(UserInfo.getUsername());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			sendEmail.setCzsj(data1);
			sendEmail.setId(Integer.parseInt(id));
			sendEmail.setBt(bt);
			sendEmail.setNr(nr);
			sendEmail.setJsr(jsr);
			sendEmail.setJsrID(jsrID);
			sendEmail.setMsr(msr);
			sendEmail.setMsrID(msrID);
			String alljsr = jsr;
			if(!"".equals(msr)&&msr!=null){
				alljsr = alljsr+","+msr;
			}
			sendEmail.setWdr(alljsr);
			sendEmail.setFileUrl(FileUrl);
			sendEmail.setFszt(fszt);//1：普通发送  2：存草稿
			sendEmail.setSczt("1");//默认未删除
			emailDao.updateEmail(sendEmail);
			if("1".equals(fszt)){
				//加入各人收件箱
				ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
				String[] tzryList = jsr.split(",");
				String[] tzryIDList = jsrID.split(",");
				String[] mryList = msr.split(",");
				String[] mryIDList = msrID.split(",");
				String phoneList ="";
				for(int i=0;i<tzryIDList.length;i++){
					if(!"".equals(tzryIDList[i])&&tzryIDList[i]!=null){
						MyEmail myEmail = new MyEmail();
						myEmail.setBt(bt);
						myEmail.setSendid(Integer.parseInt(id));
						myEmail.setNr(nr);
						myEmail.setJsr(tzryList[i]);
						myEmail.setJsrID(tzryIDList[i]);
						myEmail.setAllfsr(jsr);
						myEmail.setAllfsrID(jsrID);
						myEmail.setFsr(UserInfo.getName());
						myEmail.setFsrID(UserInfo.getUsername());
						myEmail.setFileUrl(FileUrl);
						myEmail.setJssj(data1);
						myEmail.setSczt("1");//默认未删除
						myEmail.setCyzt("1");//1：未查阅 2：已查阅
						MyEmailList.add(myEmail);
						//查询号码
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(tzryIDList[i]);
					}
				}
				for(int j=0;j<mryIDList.length;j++){
					if(!"".equals(mryIDList[j])&&mryIDList[j]!=null){
						MyEmail myEmail = new MyEmail();
						myEmail.setBt(bt);
						myEmail.setSendid(Integer.parseInt(id));
						myEmail.setNr(nr);
						myEmail.setJsr(mryList[j]);
						myEmail.setJsrID(mryIDList[j]);
						myEmail.setAllfsr(jsr);
						myEmail.setAllfsrID(jsrID);
						myEmail.setFsr(UserInfo.getName());
						myEmail.setFsrID(UserInfo.getUsername());
						myEmail.setFileUrl(FileUrl);
						myEmail.setJssj(data1);
						myEmail.setSczt("1");//默认未删除
						myEmail.setCyzt("1");//1：未查阅 2：已查阅
						MyEmailList.add(myEmail);
						//查询号码
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(mryIDList[j]);
					}
				}
				//加入各人收件箱
				emailDao.insertMyEmail(MyEmailList);
	
				// TODO Auto-generated method stub
				//发送邮件提醒
				messagePlatForm.submitShortMessageMany(phoneList,UserInfo.getName()+"：您有一封新邮件需要查收。");
			}
			if("1".equals(fszt)){
				result = "发送成功";
				request.getRequestDispatcher("EmailServlet?action=AllSendEmail&flag=1&result="+result).forward(request,
						response);
			}else{
				result = "保存成功";
				request.getRequestDispatcher("EmailServlet?action=DraftEmail&flag=1&result="+result).forward(request,
						response);
			}
		}
	}

	/*
	 *  回复、转发邮件
	 * 
	 */
	protected void EditEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");
			String lx = request.getParameter("lx");//1:回复；2：回复全部；3：转发；
			EmailDao emailDao = new EmailDao();
			SendEmail sendEmail = new SendEmail();
			sendEmail = emailDao.querySendEmailByID(Integer.parseInt(id));
			if("1".equals(lx)){
				sendEmail.setBt("回复："+sendEmail.getBt());
				sendEmail.setJsr(sendEmail.getCzr());
				sendEmail.setJsrID(sendEmail.getCzrID());
				sendEmail.setMsr("");
				sendEmail.setMsrID("");
				sendEmail.setNr("原件内容：<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' />"+sendEmail.getNr()+"<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' /><br>");
			}

			if("2".equals(lx)){
				sendEmail.setBt("回复："+sendEmail.getBt());
				sendEmail.setMsr(sendEmail.getJsr());
				sendEmail.setMsrID(sendEmail.getJsrID());
				sendEmail.setJsr(sendEmail.getCzr());
				sendEmail.setJsrID(sendEmail.getCzrID());
				sendEmail.setNr("原件内容：<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' />"+sendEmail.getNr()+"<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' /><br>");
			}

			if("3".equals(lx)){
				sendEmail.setJsr("");
				sendEmail.setJsrID("");
				sendEmail.setMsr("");
				sendEmail.setMsrID("");
				sendEmail.setNr("原件内容：<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' />"+sendEmail.getNr()+"<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' /><br>");
			}
			request.setAttribute("sendEmail", sendEmail);
			request.getRequestDispatcher("/jsp/Email/editEmail.jsp").forward(request,
					response);
		}
	}
}
