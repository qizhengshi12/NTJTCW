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
	 * 请求Servlet方法
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
		}else if("query".equals(action)){//工作动态列表
			queryGzdt(request,response);
		}else if("resetMenu".equals(action)){//重置菜单
			resetMenu(request,response);
		}else if("gzdtEdit".equals(action)){//新增或修改页面
			gzdtEdit(request,response);
		}else if("gzdtSave".equals(action)){///保存页面
			gzdtSave(request,response);
		}else if("gzdtShow".equals(action)){//查看工作动态
			gzdtShow(request,response);
		}else if("gzdtDelete".equals(action)){//删除工作动态
			gzdtDelete(request,response);
		}else if("downLoad".equals(action)){//下载数据
			downLoad(request,response);
		}
	}
	/*
	 *  刷新菜单树
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
	 *  重置菜单
	 * 
	 */
	protected void resetMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			String result= "重置成功";

			MenuDao menuDao = new MenuDao();
			menuDao.DeleteMenuName("node_Gzdt");
			menuDao.ResetZzxxMenu("node_Gzdt",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("GzdtServlet?action=getGzdt&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  工作动态列表
	 * 
	 */
	protected void queryGzdt(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//结果
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getGzdt().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('无查询权限！');</script>");
			
		}else{
			//查询列表
			ArrayList<Gzdt> GzdtList = new ArrayList<Gzdt>();
			//分页;
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
			//若为0：则从菜单进入
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
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
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
	 *  进入新增或修改页面
	 * 
	 */
	protected void gzdtEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
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
	 *  进入保存页面
	 * 
	 */
	protected void gzdtSave(HttpServletRequest request,
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
			//新增或者修改
			String gzdt_id = request.getParameter("gzdt_id");
			String wjmc = request.getParameter("wjmc");
			String wjnr = request.getParameter("wjnr");
			String FileUrl = request.getParameter("FileUrl");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
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
			//获取当前时间
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
	 *  进入查看页面
	 * 
	 */
	protected void gzdtShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
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
	 *  删除
	 * 
	 */
	protected void gzdtDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String FileUrl = request.getParameter("FileUrl");
//		String fatherid = request.getParameter("fatherid");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//删除下载文件
			if(!"".equals(FileUrl)&&FileUrl!=null){
				String[] FileUrlStr = FileUrl.split(";");
				//删除下载文件
				for(int j=0;j<FileUrlStr.length; j++){
					String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/gzdt/"+FileUrlStr[j];
					File file = new File(FullFilePath);  
					// 路径为文件且不为空则进行删除  
					if (file.isFile() && file.exists()) {  
						file.delete();  
					}
				}
			}
			GzdtDao gzdtDao = new GzdtDao();
			gzdtDao.DeleteGzdtById(Integer.parseInt(id));
			//删除工作动态菜单栏内容
//			if(GzdtDao. > 0){
				//更新菜单栏存储项
//				MenuDao menuDao =new MenuDao();
//				menuDao.UpdateMenuContextById("node_Gzdt", fatherid, id,data1,UserInfo.getName(),UserInfo.getUsername());
				//返回处理结果
//			} 
			
			RequestDispatcher rd = request.getRequestDispatcher("GzdtServlet?action=query&flag=1");
			rd.forward(request, response);
			return;
		}
	}
	/*
	 * 下载文件
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
