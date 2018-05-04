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



import com.safety.dao.ContentFgDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentFg;
import com.safety.entity.ContentToTxt;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;





public class FgServlet extends HttpServlet{
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
		if("getFg".equals(action)){
			getFg(request,response);
		}else if("query".equals(action)){
			queryFg(request,response);
		}else if("delete".equals(action)){
			deleteFg(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("saveFg".equals(action)){
			saveFg(request,response);
		}else if("showFg".equals(action)){
			showFg(request,response);
		}else if("editFg".equals(action)){
			editFg(request,response);
		}
	}
	
	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getFg(HttpServletRequest request,
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
			str = menuDao.QueryAllMenuInfo("node_Fg");
			ArrayList<ContentFg> FgList = new ArrayList<ContentFg>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("FgList", FgList);
			request.getRequestDispatcher("/jsp/LawInformation/fg.jsp").forward(request,
					response);
		}
	}
	/*
	 *  查询内容
	 * 
	 */
	protected void queryFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//结果
		String resultList = request.getParameter("resultList");
		//通过菜单项的ID号查询
		String MenuId = request.getParameter("MenuId");
		//通过标题查询
		String srbt = request.getParameter("srbt");
		/*设置查询条件*/
		ContentFgDao contentFgDao = new ContentFgDao();
		//存储查询结果
		ArrayList<ContentFg> FgList = new ArrayList<ContentFg>();

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
			String Fgag = "0";//设置入口标志，用来判断查询方式
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
				String idList = menuDao.QueryMenuById("node_Fg", MenuId);
				if(!"".equals(idList)&&idList!=null){
					countAll = contentFgDao.queryFgByIdListCount(idList);
					FgList = contentFgDao.queryFgByIdList(idList, begin, pageSize);
				}
			}
			//通过标题内容查询
			else if(srbt!=null){
				countAll = contentFgDao.queryFgByBtCount(srbt);
				FgList = contentFgDao.queryFgByBt(srbt, begin, pageSize);
				Fgag = "1";
			}
			
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Fg");
			request.setAttribute("menuList", str);
			
			request.setAttribute("MenuId", MenuId);//前台页面左侧树ID
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("Fgag", Fgag);
			request.setAttribute("FgList", FgList);
			request.setAttribute("resultList", resultList);
			request.getRequestDispatcher("/jsp/LawInformation/fgList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  删除内容
	 * 
	 */
	protected void deleteFg(HttpServletRequest request,
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
			if(ContentFgDao.DeleteFgById(Integer.parseInt(ContendId)) > 0){
				//更新菜单栏存储项
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_Fg", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//返回处理结果
				resultList = "信息删除成功!";
			} 
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=query&MenuId="+fatherid+"&resultList="+resultList);
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
			menuDao.InsertMenu("node_Fg", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
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
			menuDao.UpdateMenuNameById("node_Fg", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
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
		String idList = menuDao.QueryMenuById("node_Fg", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_Fg", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_Fg", Integer.parseInt(id));
				result = "删除成功";
			}else{
				result = "无法删除！（请先删除子菜单内容）";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
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
        	RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=query&MenuId="+fatherid+"&resultList="+"文件不存在，无法下载！");
    		rd.forward(request, response);
    		return ;
        }
	}
	/*
	 *  新增法律法规记录（新增或者修改）
	 * 
	 */
	protected void saveFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "保存失败!";
		//新增或者修改
		String editFg_id = request.getParameter("editFg_id");
		
		//获取内容
		String bt = request.getParameter("bt");
		String wh = request.getParameter("wh");
		String ssrq = request.getParameter("ssrq");
		String fbdw = request.getParameter("fbdw");
		String FgTxt = request.getParameter("FgTxt");
		String FileUrl = request.getParameter("FileUrl");
		String fatherid = request.getParameter("fatherid");
		// List
		ContentFg Fg = new ContentFg();
		Fg.setBt(bt);
		Fg.setWh(wh);
		Fg.setSsrq(DateFormat(ssrq));
		Fg.setFbdw(fbdw);
		Fg.setFatherid(fatherid);
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
			Fg.setCzr(UserInfo.getName());
			Fg.setCzrID(UserInfo.getUsername());
			Fg.setCzsj(data1);
			ContentFgDao contentFgDao = new ContentFgDao();
			if("".equals(editFg_id)||editFg_id==null){
				//新增
				if(!"".equals(FgTxt)&&FgTxt!=null){
					// 创建文件并写入
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(FgTxt, "LawInformation/fg", context);
					Fg.setFileUrl(FileUrl);
				}else{
					Fg.setFileUrl("");
				}
				//存储类型
				Fg.setLx("法规");
				int InsertID = contentFgDao.insertFg(Fg);
				MenuDao menuDao = new MenuDao();
				menuDao.InsertMenuContextById("node_Fg", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
				result = "保存成功!";
			}else{
				//修改
				if(!"".equals(FileUrl)&&FileUrl!=null){
					// 打开文件并写入
					ServletContext context = getServletContext();
					ContentToTxt.UpdateTxt(FgTxt, FileUrl, context);
					Fg.setFileUrl(FileUrl);
				}else if(!"".equals(FgTxt)&&FgTxt!=null){
					// 创建文件并写入
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(FgTxt, "LawInformation/fg", context);
					Fg.setFileUrl(FileUrl);
				}else{
					Fg.setFileUrl("");
				}
				Fg.setId(Integer.parseInt(editFg_id));
				boolean effectCount = contentFgDao.updateFg(Fg);
				if(effectCount){
					result = "保存成功!";
				}
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
			rd.forward(request, response);
		}
	}
	

	/*
	 *  查看某一条法律法规内容
	 * 
	 */
	protected void showFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//通过菜单项的ID号查询
		String id = request.getParameter("id");
		//存储查询结果
		ContentFg Fg = new ContentFg();
		/*设置查询条件*/
		ContentFgDao contentFgDao = new ContentFgDao();
		//通过id查询
		if(!"".equals(id)&&id!=null){
			Fg = contentFgDao.queryFgByID(Integer.parseInt(id));
			if(!"".equals(Fg.getFileUrl())&&Fg.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String FgTxt = ContentToTxt.ReadTxt(Fg.getFileUrl(), context);
				Fg.setFgTxt(FgTxt);
			}else{
				Fg.setFgTxt("");
			}
		}
		
		request.setAttribute("Fg", Fg);
		request.getRequestDispatcher("/jsp/LawInformation/fgShow.jsp").forward(request,
				response);
	}

	/*
	 *  编辑某一条法律法规内容
	 * 
	 */
	protected void editFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//通过菜单项的ID号查询
		String id = request.getParameter("id");
		//存储查询结果
		ContentFg Fg = new ContentFg();
		/*设置查询条件*/
		ContentFgDao contentFgDao = new ContentFgDao();
		//通过id查询
		if(!"".equals(id)&&id!=null){
			Fg = contentFgDao.queryFgByID(Integer.parseInt(id));
			if(!"".equals(Fg.getFileUrl())&&Fg.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String FgTxt = ContentToTxt.ReadTxt(Fg.getFileUrl(), context);
				Fg.setFgTxt(FgTxt);
			}else{
				Fg.setFgTxt("");
			}
		}
		
		request.setAttribute("Fg", Fg);
		request.getRequestDispatcher("/jsp/LawInformation/fgEdit.jsp").forward(request,
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
