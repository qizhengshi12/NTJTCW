package com.safety.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.CwjdDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Cwjd;
import com.safety.entity.Jjyxfx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class CwjdServlet  extends HttpServlet{
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
		if("getCwjd".equals(action)){
			getCwjd(request,response);
		}else if("query".equals(action)){//����ල�б�
			queryCwjd(request,response);
		}else if("resetMenu".equals(action)){//���ò˵�
			resetMenu(request,response);
		}else if("cwjdEdit".equals(action)){//�������޸�ҳ��
			CwjdEdit(request,response);
		}else if("cwjdSave".equals(action)){///����ҳ��
			CwjdSave(request,response);
		}else if("cwjdShow".equals(action)){//�鿴
			CwjdShow(request,response);
		}else if("cwjdDelete".equals(action)){//ɾ��
			CwjdDelete(request,response);
		}else if("downLoad".equals(action)){//��������
			downLoad(request,response);
		}else if("getJjyxfx".equals(action)){
			getJjyxfx(request,response);
		}else if("queryJjyxfx".equals(action)){//�������з����б�
			queryJjyxfx(request,response);
		}else if("resetMenuJjyxfx".equals(action)){//���ò˵�
			resetMenuJjyxfx(request,response);
		}else if("JjyxfxEdit".equals(action)){//�������޸�ҳ��
			JjyxfxEdit(request,response);
		}else if("JjyxfxSave".equals(action)){///����ҳ��
			JjyxfxSave(request,response);
		}else if("JjyxfxShow".equals(action)){//�鿴
			JjyxfxShow(request,response);
		}else if("JjyxfxDelete".equals(action)){//ɾ��
			JjyxfxDelete(request,response);
		}else if("downLoadJjyxfx".equals(action)){//��������
			downLoadJjyxfx(request,response);
		}
	}
	/*
	 *  ˢ�²˵���
	 * 
	 */
	protected void getCwjd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MenuDao menuDao = new MenuDao();
		String result = request.getParameter("result");
		// List
		String str="";
		str = menuDao.QueryAllMenuInfoName("node_cwjd");
//		ArrayList<Cwjd> CwjdList = new ArrayList<Cwjd>();
		request.setAttribute("menuList", str);
		request.setAttribute("result", result);
//		request.setAttribute("CwjdList", CwjdList);
		request.getRequestDispatcher("/jsp/ChoiTrialInformation/cwjd.jsp").forward(request,
				response);
		return;
	}
	/*
	 *  ���ò˵�
	 * 
	 */
	protected void resetMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			String result= "���óɹ�";

			MenuDao menuDao = new MenuDao();
			menuDao.DeleteMenuName("node_cwjd");
			menuDao.ResetZzxxMenu("node_cwjd",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("CwjdServlet?action=getCwjd&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  ����ල�б�
	 * 
	 */
	protected void queryCwjd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getCwjd().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('�޲�ѯȨ�ޣ�');</script>");
			
		}else{
			//��ѯ�б�
			ArrayList<Cwjd> CwjdList = new ArrayList<Cwjd>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			CwjdDao cwjdDao = new CwjdDao();
			String flag = request.getParameter("flag");
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxtj = request.getParameter("cxtj");
			//��Ϊ0����Ӳ˵�����
			if("1".equals(flag)){
				srbt = " where czrdw ='"+UserInfo.getCompany()+"' and (tjzt='1' or (tjzt='2' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			
			if(srbt!=null){
				countAll = cwjdDao.queryCwjdListByBtCount(srbt);
				CwjdList = cwjdDao.queryCwjdListByBt(srbt, begin, pageSize);
			}
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Cwjd");
			request.setAttribute("menuList", str);
			
			request.setAttribute("CwjdList", CwjdList);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
//			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/cwjdList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void CwjdEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String id = request.getParameter("id");
//			String fatherid = request.getParameter("fatherid");
			String result = request.getParameter("result");
			Cwjd cwjd = new Cwjd();
//			Cwjd.setFatherid(fatherid);
			CwjdDao cwjdDao = new CwjdDao();
			if(!"".equals(id)&&id!=null){
				cwjd = cwjdDao.queryCwjdByID(Integer.parseInt(id));
			}
			request.setAttribute("cwjd", cwjd);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/cwjdEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void CwjdSave(HttpServletRequest request,
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
			//���������޸�
			String Cwjd_id = request.getParameter("Cwjd_id");
			String wjmc = request.getParameter("wjmc");
			String wjnr = request.getParameter("wjnr");
			String FileUrl = request.getParameter("FileUrl");
			String sj = request.getParameter("sj");
			String tjzt = request.getParameter("tjzt");//1:�ύ2������ݸ���
			Cwjd cwjd = new Cwjd();
			cwjd.setWjmc(wjmc);
			cwjd.setWjnr(wjnr);
			cwjd.setFileUrl(FileUrl);
			cwjd.setCzr(UserInfo.getName());
			cwjd.setCzrID(UserInfo.getUsername());
			cwjd.setCzrdw(UserInfo.getCompany());
			cwjd.setSj(DateFormat(sj));
			cwjd.setTjzt(tjzt);
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwjd.setCzsj(data1);
			
			CwjdDao cwjdDao = new CwjdDao();
			if(!"".equals(Cwjd_id)&&Cwjd_id!=null&&!"0".equals(Cwjd_id)){
				cwjd.setId(Integer.parseInt(Cwjd_id));
				cwjdDao.updateCwjd(cwjd);
			}else{
				cwjdDao.insertCwjd(cwjd);
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("CwjdServlet?action=getCwjd").forward(request,
					response);
			
		}
	}


	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void CwjdShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		Cwjd cwjd = new Cwjd();
		CwjdDao cwjdDao = new CwjdDao();
		if(!"".equals(id)&&id!=null){
			cwjd = cwjdDao.queryCwjdByID(Integer.parseInt(id));
		}
		request.setAttribute("cwjd", cwjd);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/ChoiTrialInformation/cwjdShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void CwjdDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String FileUrl = request.getParameter("FileUrl");
//		String fatherid = request.getParameter("fatherid");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//ɾ�������ļ�
			if(!"".equals(FileUrl)&&FileUrl!=null){
				String[] FileUrlStr = FileUrl.split(";");
				//ɾ�������ļ�
				for(int j=0;j<FileUrlStr.length; j++){
					String FullFilePath = request.getRealPath("/UserFile") + "/ChoiTrialInformation/cwjd/"+FileUrlStr[j];
					File file = new File(FullFilePath);  
					// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
					if (file.isFile() && file.exists()) {  
						file.delete();  
					}
				}
			}
			CwjdDao cwjdDao = new CwjdDao();
			//ɾ������ල�˵�������
			cwjdDao.DeleteCwjdById(Integer.parseInt(id));
			RequestDispatcher rd = request.getRequestDispatcher("CwjdServlet?action=query&flag=1");
			rd.forward(request, response);
			return;
		}
	}
	/*
	 * �����ļ�
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
//		String id = request.getParameter("id");
		String FullFilePath = request.getRealPath("/UserFile") + "/ChoiTrialInformation/cwjd/"+URL;
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
	 *  ˢ�²˵���
	 * 
	 */
	protected void getJjyxfx(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MenuDao menuDao = new MenuDao();
		String result = request.getParameter("result");
		// List
		String str="";
		str = menuDao.QueryAllMenuInfoName("node_jjyxfx");
//		ArrayList<Jjyxfx> JjyxfxList = new ArrayList<Jjyxfx>();
		request.setAttribute("menuList", str);
		request.setAttribute("result", result);
//		request.setAttribute("JjyxfxList", JjyxfxList);
		request.getRequestDispatcher("/jsp/StatisticalReports/jjyxfx.jsp").forward(request,
				response);
		return;
	}
	/*
	 *  ���ò˵�
	 * 
	 */
	protected void resetMenuJjyxfx(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			String result= "���óɹ�";

			MenuDao menuDao = new MenuDao();
			menuDao.DeleteMenuName("node_jjyxfx");
			menuDao.ResetZzxxMenu("node_jjyxfx",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("CwjdServlet?action=getJjyxfx&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  �������з����б�
	 * 
	 */
	protected void queryJjyxfx(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getJjyxfx().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('�޲�ѯȨ�ޣ�');</script>");
			
		}else{
			//��ѯ�б�
			ArrayList<Jjyxfx> JjyxfxList = new ArrayList<Jjyxfx>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			CwjdDao cwjdDao = new CwjdDao();
			String flag = request.getParameter("flag");
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxtj = request.getParameter("cxtj");
			//��Ϊ0����Ӳ˵�����
			if("1".equals(flag)){
				srbt = " where czrdw ='"+UserInfo.getCompany()+"' and (tjzt='1' or (tjzt='2' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			
			if(srbt!=null){
				countAll = cwjdDao.queryJjyxfxListByBtCount(srbt);
				JjyxfxList = cwjdDao.queryJjyxfxListByBt(srbt, begin, pageSize);
			}
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Jjyxfx");
			request.setAttribute("menuList", str);
			
			request.setAttribute("JjyxfxList", JjyxfxList);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
//			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			request.getRequestDispatcher("/jsp/StatisticalReports/jjyxfxList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void JjyxfxEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String id = request.getParameter("id");
//			String fatherid = request.getParameter("fatherid");
			String result = request.getParameter("result");
			Jjyxfx jjyxfx = new Jjyxfx();
//			Jjyxfx.setFatherid(fatherid);
			CwjdDao cwjdDao = new CwjdDao();
			if(!"".equals(id)&&id!=null){
				jjyxfx = cwjdDao.queryJjyxfxByID(Integer.parseInt(id));
			}
			request.setAttribute("jjyxfx", jjyxfx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/jjyxfxEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void JjyxfxSave(HttpServletRequest request,
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
			//���������޸�
			String Jjyxfx_id = request.getParameter("Jjyxfx_id");
			String wjmc = request.getParameter("wjmc");
			String wjnr = request.getParameter("wjnr");
			String FileUrl = request.getParameter("FileUrl");
			String sj = request.getParameter("sj");
			String tjzt = request.getParameter("tjzt");//1:�ύ2������ݸ���
			Jjyxfx jjyxfx = new Jjyxfx();
			jjyxfx.setWjmc(wjmc);
			jjyxfx.setWjnr(wjnr);
			jjyxfx.setFileUrl(FileUrl);
			jjyxfx.setCzr(UserInfo.getName());
			jjyxfx.setCzrID(UserInfo.getUsername());
			jjyxfx.setCzrdw(UserInfo.getCompany());
			jjyxfx.setSj(DateFormat(sj));
			jjyxfx.setTjzt(tjzt);
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			jjyxfx.setCzsj(data1);
			
			CwjdDao cwjdDao = new CwjdDao();
			if(!"".equals(Jjyxfx_id)&&Jjyxfx_id!=null&&!"0".equals(Jjyxfx_id)){
				jjyxfx.setId(Integer.parseInt(Jjyxfx_id));
				cwjdDao.updateJjyxfx(jjyxfx);
			}else{
				cwjdDao.insertJjyxfx(jjyxfx);
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("CwjdServlet?action=getJjyxfx").forward(request,
					response);
			
		}
	}


	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void JjyxfxShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		Jjyxfx jjyxfx = new Jjyxfx();
		CwjdDao cwjdDao = new CwjdDao();
		if(!"".equals(id)&&id!=null){
			jjyxfx = cwjdDao.queryJjyxfxByID(Integer.parseInt(id));
		}
		request.setAttribute("jjyxfx", jjyxfx);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/jjyxfxShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void JjyxfxDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String FileUrl = request.getParameter("FileUrl");
//		String fatherid = request.getParameter("fatherid");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//ɾ�������ļ�
			if(!"".equals(FileUrl)&&FileUrl!=null){
				String[] FileUrlStr = FileUrl.split(";");
				//ɾ�������ļ�
				for(int j=0;j<FileUrlStr.length; j++){
					String FullFilePath = request.getRealPath("/UserFile") + "/StatisticalReports/jjyxfx/"+FileUrlStr[j];
					File file = new File(FullFilePath);  
					// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
					if (file.isFile() && file.exists()) {  
						file.delete();  
					}
				}
			}
			CwjdDao cwjdDao = new CwjdDao();
			//ɾ���������з����˵�������
			cwjdDao.DeleteJjyxfxById(Integer.parseInt(id));
			RequestDispatcher rd = request.getRequestDispatcher("CwjdServlet?action=queryJjyxfx&flag=1");
			rd.forward(request, response);
			return;
		}
	}
	/*
	 * �����ļ�
	 * 
	 */
	protected void downLoadJjyxfx(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
//		String id = request.getParameter("id");
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticalReports/jjyxfx/"+URL;
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
