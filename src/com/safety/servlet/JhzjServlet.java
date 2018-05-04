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

import com.safety.dao.JhzjDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Jhzj;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class JhzjServlet  extends HttpServlet{
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
		if("getJhzj".equals(action)){//计划总结列表
			getJhzj(request,response);
		}else if("query".equals(action)){
			queryJhzj(request,response);
		}else if("resetMenu".equals(action)){
			resetMenu(request,response);
		}else if("jhzjEdit".equals(action)){//新增或修改页面
			jhzjEdit(request,response);
		}else if("jhzjSave".equals(action)){///保存页面
			jhzjSave(request,response);
		}else if("jhzjShow".equals(action)){//查看计划
			jhzjShow(request,response);
		}else if("jhzjDelete".equals(action)){//删除计划
			jhzjDelete(request,response);
		}else if("downLoad".equals(action)){//下载数据
			downLoad(request,response);
		}
	}
	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getJhzj(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MenuDao menuDao = new MenuDao();
		String result = request.getParameter("result");
		// List
		String str="";
		str = menuDao.QueryAllMenuInfoName("node_jhzj");
		ArrayList<Jhzj> JhzjList = new ArrayList<Jhzj>();
		request.setAttribute("menuList", str);
		request.setAttribute("result", result);
		request.setAttribute("JhzjList", JhzjList);
		request.getRequestDispatcher("/jsp/StatisticsFile/jhzj.jsp").forward(request,
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
			menuDao.DeleteMenuName("node_jhzj");
			menuDao.ResetZzxxMenu("node_jhzj",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("JhzjServlet?action=getJhzj&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  计划总结列表
	 * 
	 */
	protected void queryJhzj(HttpServletRequest request,
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
			
		}else if(UserPer.getJhzj().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('无查询权限！');</script>");
			
		}else{
			//查询列表
			ArrayList<Jhzj> JhzjList = new ArrayList<Jhzj>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			JhzjDao jhzjDao = new JhzjDao();
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
				countAll = jhzjDao.queryJhzjListByBtCount(srbt);
				JhzjList = jhzjDao.queryJhzjListByBt(srbt, begin, pageSize);
//				flag = "1";
			}
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_jhzj");
			request.setAttribute("menuList", str);
			
			
			request.setAttribute("JhzjList", JhzjList);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("flag", flag);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticsFile/jhzjList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void jhzjEdit(HttpServletRequest request,
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
			Jhzj jhzj = new Jhzj();
//			jhzj.setFatherid(fatherid);
			JhzjDao jhzjDao = new JhzjDao();
			if(!"".equals(id)&&id!=null){
				jhzj = jhzjDao.queryJhzjByID(Integer.parseInt(id));
			}
			request.setAttribute("jhzj", jhzj);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticsFile/jhzjEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void jhzjSave(HttpServletRequest request,
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
			String jhzj_id = request.getParameter("jhzj_id");
			String jhmc = request.getParameter("jhmc");
			String jhlx1 = request.getParameter("jhlx1");
			String jhlx2 = request.getParameter("jhlx2");
			String jhnr = request.getParameter("jhnr");
			String FileUrl = request.getParameter("FileUrl");
			String jhsj1 = request.getParameter("jhsj1");
			String jhsj2 = request.getParameter("jhsj2");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
//			String fatherid = request.getParameter("fatherid");
			
			Jhzj jhzj = new Jhzj();
			jhzj.setJhmc(jhmc);
			jhzj.setJhlx1(jhlx1);
			jhzj.setJhlx2(jhlx2);
			jhzj.setJhnr(jhnr);
			jhzj.setFileUrl(FileUrl);
			jhzj.setJhsj1(DateFormat(jhsj1));
			jhzj.setJhsj2(DateFormat(jhsj2));
			jhzj.setCzr(UserInfo.getName());
			jhzj.setCzrID(UserInfo.getUsername());
			jhzj.setCzrdw(UserInfo.getCompany());
			jhzj.setTjzt(tjzt);
//			jhzj.setFatherid(fatherid);
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			jhzj.setCzsj(data1);
			
			JhzjDao jhzjDao = new JhzjDao();
			if(!"".equals(jhzj_id)&&jhzj_id!=null&&!"0".equals(jhzj_id)){
				jhzj.setId(Integer.parseInt(jhzj_id));
				jhzjDao.updateJhzj(jhzj);
			}else{
				int InsertID = jhzjDao.insertJhzj(jhzj);
//				MenuDao menuDao = new MenuDao();
//				menuDao.InsertMenuContextById("node_jhzj", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("JhzjServlet?action=getJhzj").forward(request,
					response);
			
		}
	}


	 /*
	 *  进入查看页面
	 * 
	 */
	protected void jhzjShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		Jhzj jhzj = new Jhzj();
		JhzjDao jhzjDao = new JhzjDao();
		if(!"".equals(id)&&id!=null){
			jhzj = jhzjDao.queryJhzjByID(Integer.parseInt(id));
		}
		request.setAttribute("jhzj", jhzj);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticsFile/jhzjShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void jhzjDelete(HttpServletRequest request,
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
					String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/jhzj/"+FileUrlStr[j];
					File file = new File(FullFilePath);  
					// 路径为文件且不为空则进行删除  
					if (file.isFile() && file.exists()) {  
						file.delete();  
					}
				}
			}
			JhzjDao jhzjDao = new JhzjDao();
			jhzjDao.DeleteJhzjById(Integer.parseInt(id));
			//删除计划总结菜单栏内容
//			if(JhzjDao.DeleteJhzjById(Integer.parseInt(id)) > 0){
//				//更新菜单栏存储项
//				MenuDao menuDao =new MenuDao();
//				menuDao.UpdateMenuContextById("node_jhzj", fatherid, id,data1,UserInfo.getName(),UserInfo.getUsername());
//				//返回处理结果
//			} 
			RequestDispatcher rd = request.getRequestDispatcher("JhzjServlet?action=query&flag=1");
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
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/jhzj/"+URL;
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
