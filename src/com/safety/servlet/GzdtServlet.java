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

import com.safety.dao.GzdtDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Gzdt;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class GzdtServlet  extends HttpServlet{
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
		if("getGzdt".equals(action)){
			getGzdt(request,response);
		}else if("query".equals(action)){//������̬�б�
			queryGzdt(request,response);
		}else if("resetMenu".equals(action)){//���ò˵�
			resetMenu(request,response);
		}else if("gzdtEdit".equals(action)){//�������޸�ҳ��
			gzdtEdit(request,response);
		}else if("gzdtSave".equals(action)){///����ҳ��
			gzdtSave(request,response);
		}else if("gzdtShow".equals(action)){//�鿴������̬
			gzdtShow(request,response);
		}else if("gzdtDelete".equals(action)){//ɾ��������̬
			gzdtDelete(request,response);
		}else if("downLoad".equals(action)){//��������
			downLoad(request,response);
		}
	}
	/*
	 *  ˢ�²˵���
	 * 
	 */
	protected void getGzdt(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MenuDao menuDao = new MenuDao();
		String result = request.getParameter("result");
		// List
		String str="";
		str = menuDao.QueryAllMenuInfoName("node_Gzdt");
		ArrayList<Gzdt> GzdtList = new ArrayList<Gzdt>();
		request.setAttribute("menuList", str);
		request.setAttribute("result", result);
		request.setAttribute("GzdtList", GzdtList);
		request.getRequestDispatcher("/jsp/StatisticsFile/gzdt.jsp").forward(request,
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
			menuDao.DeleteMenuName("node_Gzdt");
			menuDao.ResetZzxxMenu("node_Gzdt",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("GzdtServlet?action=getGzdt&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  ������̬�б�
	 * 
	 */
	protected void queryGzdt(HttpServletRequest request,
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
			
		}else if(UserPer.getGzdt().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('�޲�ѯȨ�ޣ�');</script>");
			
		}else{
			//��ѯ�б�
			ArrayList<Gzdt> GzdtList = new ArrayList<Gzdt>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			GzdtDao gzdtDao = new GzdtDao();
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
				countAll = gzdtDao.queryGzdtListByBtCount(srbt);
				GzdtList = gzdtDao.queryGzdtListByBt(srbt, begin, pageSize);
//				flag = "1";
			}
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_gzdt");
			request.setAttribute("menuList", str);
			
			request.setAttribute("GzdtList", GzdtList);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
//			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			request.getRequestDispatcher("/jsp/StatisticsFile/gzdtList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void gzdtEdit(HttpServletRequest request,
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
			Gzdt gzdt = new Gzdt();
//			gzdt.setFatherid(fatherid);
			GzdtDao gzdtDao = new GzdtDao();
			if(!"".equals(id)&&id!=null){
				gzdt = gzdtDao.queryGzdtByID(Integer.parseInt(id));
			}
			request.setAttribute("gzdt", gzdt);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticsFile/gzdtEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void gzdtSave(HttpServletRequest request,
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
			String gzdt_id = request.getParameter("gzdt_id");
			String wjmc = request.getParameter("wjmc");
			String wjnr = request.getParameter("wjnr");
			String FileUrl = request.getParameter("FileUrl");
			String tjzt = request.getParameter("tjzt");//1:�ύ2������ݸ���
//			String fatherid = request.getParameter("fatherid");
			
			Gzdt gzdt = new Gzdt();
			gzdt.setWjmc(wjmc);
			gzdt.setWjnr(wjnr);
			gzdt.setFileUrl(FileUrl);
			gzdt.setCzr(UserInfo.getName());
			gzdt.setCzrID(UserInfo.getUsername());
			gzdt.setCzrdw(UserInfo.getCompany());
			gzdt.setTjzt(tjzt);
//			gzdt.setFatherid(fatherid);
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			gzdt.setCzsj(data1);
			
			GzdtDao gzdtDao = new GzdtDao();
			if(!"".equals(gzdt_id)&&gzdt_id!=null&&!"0".equals(gzdt_id)){
				gzdt.setId(Integer.parseInt(gzdt_id));
				gzdtDao.updateGzdt(gzdt);
			}else{
				int InsertID = gzdtDao.insertGzdt(gzdt);
//				MenuDao menuDao = new MenuDao();
//				menuDao.InsertMenuContextById("node_Gzdt", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("GzdtServlet?action=getGzdt").forward(request,
					response);
			
		}
	}


	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void gzdtShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		Gzdt gzdt = new Gzdt();
		GzdtDao gzdtDao = new GzdtDao();
		if(!"".equals(id)&&id!=null){
			gzdt = gzdtDao.queryGzdtByID(Integer.parseInt(id));
		}
		request.setAttribute("gzdt", gzdt);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticsFile/gzdtShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void gzdtDelete(HttpServletRequest request,
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
					String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/gzdt/"+FileUrlStr[j];
					File file = new File(FullFilePath);  
					// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
					if (file.isFile() && file.exists()) {  
						file.delete();  
					}
				}
			}
			GzdtDao gzdtDao = new GzdtDao();
			gzdtDao.DeleteGzdtById(Integer.parseInt(id));
			//ɾ��������̬�˵�������
//			if(GzdtDao. > 0){
				//���²˵����洢��
//				MenuDao menuDao =new MenuDao();
//				menuDao.UpdateMenuContextById("node_Gzdt", fatherid, id,data1,UserInfo.getName(),UserInfo.getUsername());
				//���ش�����
//			} 
			
			RequestDispatcher rd = request.getRequestDispatcher("GzdtServlet?action=query&flag=1");
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
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/gzdt/"+URL;
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
