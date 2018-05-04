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
	 * �ʼ�ƽ̨
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
		if("NewEmail".equals(action)){//�������ʼ�ҳ��.
			NewEmail(request,response);
		}else if("insertEmail".equals(action)){//�������ʼ�.
			insertEmail(request,response);
		}else if("MyEmail".equals(action)){//��ѯ�ѽ����ʼ�.
			MyEmail(request,response);
		}else if("AllSendEmail".equals(action)){//��ѯ�ѷ����ʼ�.
			AllSendEmail(request,response);
		}else if("DraftEmail".equals(action)){//��ѯ�ݸ��䣨ֻ�з������Դ�ݸ壩
			DraftEmail(request,response);
		}else if("MyDelEmail".equals(action)){//��ѯ����վ��ֻ���ռ����Ի���վ��
			MyDelEmail(request,response);
		}else if("deleteMyEmail".equals(action)){//�ѽ����ʼ�_ɾ��
			deleteMyEmail(request,response);
		}else if("deleteSomeMyEmail".equals(action)){//�ѽ����ʼ�_����ɾ��
			deleteSomeMyEmail(request,response);
		}else if("deleteSendEmail".equals(action)){//�ѷ����ʼ�_ɾ��
			deleteSendEmail(request,response);
		}else if("deleteSomeSendEmail".equals(action)){//�ѷ����ʼ�_����ɾ��
			deleteSomeSendEmail(request,response);
		}else if("ShowEmail".equals(action)){//�鿴�����ʼ���ϸ��Ϣ
			ShowEmail(request,response);
		}else if("downLoadMy".equals(action)){//�����ļ����ռ��䣩.
			downLoadMy(request,response);
		}else if("ShowSendEmail".equals(action)){//�鿴�����ʼ���ϸ��Ϣ
			ShowSendEmail(request,response);
		}else if("downLoadSe".equals(action)){//�����ļ��������䣩.
			downLoadSe(request,response);
		}else if("returnMyEmail".equals(action)){//����վ��ԭ���ռ���
			returnMyEmail(request,response);
		}else if("EditDraftEmail".equals(action)){//�༭�ݸ����ʼ�
			EditDraftEmail(request,response);
		}else if("downLoadEd".equals(action)){//�����ļ����༭�䣩.
			downLoadEd(request,response);
		}else if("insertDraftEmail".equals(action)){//���Ͳݸ����ʼ�
			insertDraftEmail(request,response);
		}else if("EditEmail".equals(action)){//�ظ���ת���ʼ�
			EditEmail(request,response);
		}
	}
	/*
	 *  ��ѯ�ѽ����ʼ�
	 * 
	 */
	protected void MyEmail(HttpServletRequest request,
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
			ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
			//��ҳ;
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
			//�Ӳ˵����룬�޲�ѯ����
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
	 *  ��ѯ�������ʼ�
	 * 
	 */
	protected void MyDelEmail(HttpServletRequest request,
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
			ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
			//��ҳ;
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
			//�Ӳ˵����룬�޲�ѯ����
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
	 *  �������ʼ�ҳ��
	 * 
	 */
	protected void NewEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			request.getRequestDispatcher("/jsp/Email/newEmail.jsp").forward(request,
					response);
		}
	}

	/*
	 *  �������ʼ�
	 * 
	 */
	protected void insertEmail(HttpServletRequest request,
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
			//��ȡ��ǰʱ��
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
			sendEmail.setFszt(fszt);//1����ͨ����  2����ݸ�
			sendEmail.setSczt("1");//Ĭ��δɾ��
			int sendid = emailDao.insertEmail(sendEmail);
			if("1".equals(fszt)){
				//��������ռ���
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
						myEmail.setSczt("1");//Ĭ��δɾ��
						myEmail.setCyzt("1");//1��δ���� 2���Ѳ���
						MyEmailList.add(myEmail);
						//��ѯ����
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
						myEmail.setSczt("1");//Ĭ��δɾ��
						myEmail.setCyzt("1");//1��δ���� 2���Ѳ���
						MyEmailList.add(myEmail);
						//��ѯ����
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(mryIDList[j]);
					}
				}
				//��������ռ���
				emailDao.insertMyEmail(MyEmailList);
	
				// TODO Auto-generated method stub
				//�����ʼ�����
				messagePlatForm.submitShortMessageMany(phoneList,UserInfo.getName()+"������һ�����ʼ���Ҫ���ա�");
			}
			if("1".equals(fszt)){
				result = "���ͳɹ�";
				request.getRequestDispatcher("EmailServlet?action=AllSendEmail&flag=1&result="+result).forward(request,
						response);
			}else{
				result = "����ɹ�";
				request.getRequestDispatcher("EmailServlet?action=DraftEmail&flag=1&result="+result).forward(request,
						response);
			}
		}
	}
	/*
	 *  ɾ���ռ��䣨ֱ��ɾ���ռ��������ջ���վ���ݣ�
	 * 
	 */
	protected void deleteMyEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String cdsc = request.getParameter("cdsc");//�Ƿ񳹵�ɾ��
		String ly = request.getParameter("ly");//��Դҳ��
		EmailDao emailDao = new EmailDao();
		//ɾ��������վ
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
	 *  ɾ���ռ��䣨����ɾ����
	 * 
	 */
	protected void deleteSomeMyEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String IDList = request.getParameter("IDList");
		String cdsc = request.getParameter("cdsc");
		String ly = request.getParameter("ly");//��Դҳ��
		EmailDao emailDao = new EmailDao();
		//ɾ��������վ
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
	 *  ��ѯ�ѷ����ʼ�
	 * 
	 */
	protected void AllSendEmail(HttpServletRequest request,
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
			ArrayList<SendEmail> SendEmailList = new ArrayList<SendEmail>();
			//��ҳ;
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
			//�Ӳ˵����룬�޲�ѯ����
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
	 *  ��ѯ�ݸ��䣨ֻ�з������Դ�ݸ壩
	 * 
	 */
	protected void DraftEmail(HttpServletRequest request,
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
			ArrayList<SendEmail> SendEmailList = new ArrayList<SendEmail>();
			//��ҳ;
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
			//�Ӳ˵����룬�޲�ѯ����
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
	 *  ɾ��������_ֱ��ɾ��
	 * 
	 */
	protected void deleteSendEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String ly = request.getParameter("ly");//��Դҳ��
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
	 *  ɾ�������䣨����ɾ����
	 * 
	 */
	protected void deleteSomeSendEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String IDList = request.getParameter("IDList");
		String ly = request.getParameter("ly");//��Դҳ��
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
	 *  ��ѯ�ѽ����ʼ�_��ϸ��Ϣ
	 * 
	 */
	protected void ShowEmail(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String zt = request.getParameter("zt");//�ж��Ƿ���Ҫ���²���״̬
			String ly = request.getParameter("ly");
			String sendid = request.getParameter("sendid");
			EmailDao emailDao = new EmailDao();
			MyEmail myEmail = new MyEmail();
			myEmail = emailDao.queryMyEmailByID(Integer.parseInt(id));
			if("1".equals(zt)){
				emailDao.updateCyztByID(Integer.parseInt(id));
				//����δ����Ա
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
	 * �����ļ����ռ��䣩
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
            //�Ը�������ʽ��ʾ�û����أ� ����������������Ǹ�servlet ʱ�������Ի�����//ʾ�����ػ��Ǳ��档  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*����ļ����ȴ���0*/
            if (fileLength != 0) {
                /*����������*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*���������*/
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
        	RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=ShowEmail&id="+id+"&result="+"�ļ������ڣ��޷����أ�");
    		rd.forward(request, response);
    		return ;
        }
	}
	
	/*
	 *  ��ѯ�ѷ����ʼ�_��ϸ��Ϣ
	 * 
	 */
	protected void ShowSendEmail(HttpServletRequest request,
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
	 * �����ļ����ռ��䣩
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
            //�Ը�������ʽ��ʾ�û����أ� ����������������Ǹ�servlet ʱ�������Ի�����//ʾ�����ػ��Ǳ��档  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*����ļ����ȴ���0*/
            if (fileLength != 0) {
                /*����������*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*���������*/
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
        	RequestDispatcher rd = request.getRequestDispatcher("EmailServlet?action=ShowSendEmail&id="+id+"&result="+"�ļ������ڣ��޷����أ�");
    		rd.forward(request, response);
    		return ;
        }
	}

	/*
	 *  ����վ��ԭ���ռ���
	 * 
	 */
	protected void returnMyEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
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
	 *  �༭�ݸ����ʼ�
	 * 
	 */
	protected void EditDraftEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
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
	 * �����ļ����༭�䣩
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
            //�Ը�������ʽ��ʾ�û����أ� ����������������Ǹ�servlet ʱ�������Ի�����//ʾ�����ػ��Ǳ��档  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*����ļ����ȴ���0*/
            if (fileLength != 0) {
                /*����������*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*���������*/
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
    		out.println("<script>alert('�ļ���ʧЧ���޷����أ�');</script>");
        }
	}
	/*
	 *  ���Ͳݸ����ʼ�
	 * 
	 */
	protected void insertDraftEmail(HttpServletRequest request,
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
			//��ȡ��ǰʱ��
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
			sendEmail.setFszt(fszt);//1����ͨ����  2����ݸ�
			sendEmail.setSczt("1");//Ĭ��δɾ��
			emailDao.updateEmail(sendEmail);
			if("1".equals(fszt)){
				//��������ռ���
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
						myEmail.setSczt("1");//Ĭ��δɾ��
						myEmail.setCyzt("1");//1��δ���� 2���Ѳ���
						MyEmailList.add(myEmail);
						//��ѯ����
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
						myEmail.setSczt("1");//Ĭ��δɾ��
						myEmail.setCyzt("1");//1��δ���� 2���Ѳ���
						MyEmailList.add(myEmail);
						//��ѯ����
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(mryIDList[j]);
					}
				}
				//��������ռ���
				emailDao.insertMyEmail(MyEmailList);
	
				// TODO Auto-generated method stub
				//�����ʼ�����
				messagePlatForm.submitShortMessageMany(phoneList,UserInfo.getName()+"������һ�����ʼ���Ҫ���ա�");
			}
			if("1".equals(fszt)){
				result = "���ͳɹ�";
				request.getRequestDispatcher("EmailServlet?action=AllSendEmail&flag=1&result="+result).forward(request,
						response);
			}else{
				result = "����ɹ�";
				request.getRequestDispatcher("EmailServlet?action=DraftEmail&flag=1&result="+result).forward(request,
						response);
			}
		}
	}

	/*
	 *  �ظ���ת���ʼ�
	 * 
	 */
	protected void EditEmail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");
			String lx = request.getParameter("lx");//1:�ظ���2���ظ�ȫ����3��ת����
			EmailDao emailDao = new EmailDao();
			SendEmail sendEmail = new SendEmail();
			sendEmail = emailDao.querySendEmailByID(Integer.parseInt(id));
			if("1".equals(lx)){
				sendEmail.setBt("�ظ���"+sendEmail.getBt());
				sendEmail.setJsr(sendEmail.getCzr());
				sendEmail.setJsrID(sendEmail.getCzrID());
				sendEmail.setMsr("");
				sendEmail.setMsrID("");
				sendEmail.setNr("ԭ�����ݣ�<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' />"+sendEmail.getNr()+"<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' /><br>");
			}

			if("2".equals(lx)){
				sendEmail.setBt("�ظ���"+sendEmail.getBt());
				sendEmail.setMsr(sendEmail.getJsr());
				sendEmail.setMsrID(sendEmail.getJsrID());
				sendEmail.setJsr(sendEmail.getCzr());
				sendEmail.setJsrID(sendEmail.getCzrID());
				sendEmail.setNr("ԭ�����ݣ�<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' />"+sendEmail.getNr()+"<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' /><br>");
			}

			if("3".equals(lx)){
				sendEmail.setJsr("");
				sendEmail.setJsrID("");
				sendEmail.setMsr("");
				sendEmail.setMsrID("");
				sendEmail.setNr("ԭ�����ݣ�<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' />"+sendEmail.getNr()+"<hr style='height: 1px; border:none; border-top:1px dashed #5CACEE;' /><br>");
			}
			request.setAttribute("sendEmail", sendEmail);
			request.getRequestDispatcher("/jsp/Email/editEmail.jsp").forward(request,
					response);
		}
	}
}
