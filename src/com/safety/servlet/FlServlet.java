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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.safety.dao.ContentFlDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentFl;
import com.safety.entity.ContentToTxt;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;





public class FlServlet extends HttpServlet{
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
		if("getFl".equals(action)){
			getFl(request,response);
		}else if("query".equals(action)){
			queryFl(request,response);
		}else if("delete".equals(action)){
			deleteFl(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("saveFl".equals(action)){
			saveFl(request,response);
		}else if("showFl".equals(action)){
			showFl(request,response);
		}else if("editFl".equals(action)){
			editFl(request,response);
		}
	}
	
	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getFl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNodecjfg().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Fl");
			ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("FlList", FlList);
			request.getRequestDispatcher("/jsp/LawInformation/fl.jsp").forward(request,
					response);
		}
	}
	/*
	 *  查询内容
	 * 
	 */
	protected void queryFl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//结果
		String resultList = request.getParameter("resultList");
		//通过菜单项的ID号查询
		String MenuId = request.getParameter("MenuId");
		//通过标题查询
		String srbt = request.getParameter("srbt");
		/*设置查询条件*/
		ContentFlDao contentFlDao = new ContentFlDao();
		//存储查询结果
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();

		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getContentcjfg().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('无查询权限！');</script>");
		}else{
			//分页;
			String flag = "0";//设置入口标志，用来判断查询方式
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			
			//通过左侧树查询
			if(!"".equals(MenuId)&&MenuId!=null){
				//获取法律法规ID号列表
				MenuDao menuDao =new MenuDao();
				String idList = menuDao.QueryMenuById("node_Fl", MenuId);
				if(!"".equals(idList)&&idList!=null){
					countAll = contentFlDao.queryFlByIdListCount(idList);
					FlList = contentFlDao.queryFlByIdList(idList, begin, pageSize);
				}
			}
			//通过标题内容查询
			else if(srbt!=null){
				countAll = contentFlDao.queryFlByBtCount(srbt);
				FlList = contentFlDao.queryFlByBt(srbt, begin, pageSize);
				flag = "1";
			}
			
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Fl");
			request.setAttribute("menuList", str);
			
			request.setAttribute("MenuId", MenuId);//前台页面左侧树ID
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("flag", flag);
			request.setAttribute("FlList", FlList);
			request.setAttribute("resultList", resultList);
			request.getRequestDispatcher("/jsp/LawInformation/flList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  删除内容
	 * 
	 */
	protected void deleteFl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		// TODO Auto-generated method stub
		String ContendId = request.getParameter("id");
		String fatherid = request.getParameter("fatherid");
		String resultList = "信息删除失败!";
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
			String path =request.getParameter("path");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+path;
			File file = new File(FullFilePath);  
			// 路径为文件且不为空则进行删除  
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			
			//删除法律法规内容栏
			if(ContentFlDao.DeleteFlById(Integer.parseInt(ContendId)) > 0){
				//更新菜单栏存储项
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_Fl", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//返回处理结果
				resultList = "信息删除成功!";
			} 
			RequestDispatcher rd = request.getRequestDispatcher("FlServlet?action=query&MenuId="+fatherid+"&resultList="+resultList);
			rd.forward(request, response);
		}
	}
	/*
	 *  新增菜单
	 * 
	 */
	protected void insertMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String ParentID = request.getParameter("ParentID");//获取当前用户
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
			String result= "新增成功";
			MenuDao menuDao = new MenuDao();
			menuDao.InsertMenu("node_Fl", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("FlServlet?action=getFl&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 * 修改菜单
	 * 
	 */
	protected void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String result= "修改成功";
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
			MenuDao menuDao = new MenuDao();
			menuDao.UpdateMenuNameById("node_Fl", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("FlServlet?action=getFl&result="+result);
			rd.forward(request, response);
		}
	}

	/*
	 * 删除菜单
	 * 
	 */
	protected void deleteMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String result = "无法删除！（请先删除包含文件）";
		MenuDao menuDao = new MenuDao();
		int count = 0;
		String idList = menuDao.QueryMenuById("node_Fl", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_Fl", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_Fl", Integer.parseInt(id));
				result = "删除成功";
			}else{
				result = "无法删除！（请先删除子菜单内容）";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("FlServlet?action=getFl&result="+result);
		rd.forward(request, response);
		return ;
	}
	/*
	 * 下载文件
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String fatherid = request.getParameter("fatherid");
		String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
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
        	RequestDispatcher rd = request.getRequestDispatcher("FlServlet?action=query&MenuId="+fatherid+"&resultList="+"文件不存在，无法下载！");
    		rd.forward(request, response);
    		return ;
        }
	}

	/*
	 *  新增法律法规记录（新增或者修改）
	 * 
	 */
	protected void saveFl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "保存失败!";
		//新增或者修改
		String editFl_id = request.getParameter("editFl_id");
		
		//获取内容
		String bt = request.getParameter("bt");
		String wh = request.getParameter("wh");
		String ssrq = request.getParameter("ssrq");
		String fbdw = request.getParameter("fbdw");
		String FlTxt = request.getParameter("FlTxt");
		String FileUrl = request.getParameter("FileUrl");
		String fatherid = request.getParameter("fatherid");
		// List
		ContentFl Fl = new ContentFl();
		Fl.setBt(bt);
		Fl.setWh(wh);
		Fl.setSsrq(DateFormat(ssrq));
		Fl.setFbdw(fbdw);
		Fl.setFatherid(fatherid);
		//获取当前时间
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			Fl.setCzr(UserInfo.getName());
			Fl.setCzrID(UserInfo.getUsername());
			Fl.setCzsj(data1);
			ContentFlDao contentFlDao = new ContentFlDao();
			if("".equals(editFl_id)||editFl_id==null){
				//新增
				if(!"".equals(FlTxt)&&FlTxt!=null){
					// 创建文件并写入
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(FlTxt, "LawInformation/fl", context);
					Fl.setFileUrl(FileUrl);
				}else{
					Fl.setFileUrl("");
				}
				//存储类型
				Fl.setLx("法律");
				int InsertID = contentFlDao.insertFl(Fl);
				MenuDao menuDao = new MenuDao();
				menuDao.InsertMenuContextById("node_fl", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
				result = "保存成功!";
			}else{
				//修改
				if(!"".equals(FileUrl)&&FileUrl!=null){
					// 打开文件并写入
					ServletContext context = getServletContext();
					ContentToTxt.UpdateTxt(FlTxt, FileUrl, context);
					Fl.setFileUrl(FileUrl);
				}else if(!"".equals(FlTxt)&&FlTxt!=null){
					// 创建文件并写入
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(FlTxt, "LawInformation/fl", context);
					Fl.setFileUrl(FileUrl);
				}else{
					Fl.setFileUrl("");
				}
				Fl.setId(Integer.parseInt(editFl_id));
				boolean effectCount = contentFlDao.updateFl(Fl);
				if(effectCount){
					result = "保存成功!";
				}
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("FlServlet?action=getFl&result="+result);
			rd.forward(request, response);
		}
	}
	

	/*
	 *  查看某一条法律法规内容
	 * 
	 */
	protected void showFl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//通过菜单项的ID号查询
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");//若为1：则从首页界面进入查看
		//存储查询结果
		ContentFl Fl = new ContentFl();
		/*设置查询条件*/
		ContentFlDao contentFlDao = new ContentFlDao();
		//通过id查询
		if(!"".equals(id)&&id!=null){
			Fl = contentFlDao.queryFlByID(Integer.parseInt(id));
			if(!"".equals(Fl.getFileUrl())&&Fl.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String FlTxt = ContentToTxt.ReadTxt(Fl.getFileUrl(), context);
				Fl.setFlTxt(FlTxt);
			}else{
				Fl.setFlTxt("");
			}
		}
		
		request.setAttribute("Fl", Fl);
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("/jsp/LawInformation/flShow.jsp").forward(request,
				response);
	}

	/*
	 *  编辑某一条法律法规内容
	 * 
	 */
	protected void editFl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//通过菜单项的ID号查询
		String id = request.getParameter("id");
		//存储查询结果
		ContentFl Fl = new ContentFl();
		/*设置查询条件*/
		ContentFlDao contentFlDao = new ContentFlDao();
		//通过id查询
		if(!"".equals(id)&&id!=null){
			Fl = contentFlDao.queryFlByID(Integer.parseInt(id));
			if(!"".equals(Fl.getFileUrl())&&Fl.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String FlTxt = ContentToTxt.ReadTxt(Fl.getFileUrl(), context);
				Fl.setFlTxt(FlTxt);
			}else{
				Fl.setFlTxt("");
			}
		}
		
		request.setAttribute("Fl", Fl);
		request.getRequestDispatcher("/jsp/LawInformation/flEdit.jsp").forward(request,
				response);
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
