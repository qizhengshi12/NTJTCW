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



import com.safety.dao.ContentGlzdDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentGlzd;
import com.safety.entity.ContentToTxt;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;





public class GlzdServlet extends HttpServlet{
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
		if("getGlzd".equals(action)){
			getGlzd(request,response);
		}else if("resetMenu".equals(action)){//重置菜单
			resetMenu(request,response);
		}else if("query".equals(action)){
			queryGlzd(request,response);
		}else if("delete".equals(action)){
			deleteGlzd(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("saveGlzd".equals(action)){
			saveGlzd(request,response);
		}else if("showGlzd".equals(action)){
			showGlzd(request,response);
		}else if("editGlzd".equals(action)){
			editGlzd(request,response);
		}
	}
	
	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNodeglzd().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Glzd");
			ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("GlzdList", GlzdList);
			request.getRequestDispatcher("/jsp/MngInformation/glzd.jsp").forward(request,
					response);
		}
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
			menuDao.DeleteMenuName("node_glzd");
			menuDao.ResetZzxxMenu("node_glzd",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  查询内容
	 * 
	 */
	protected void queryGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//结果
		String resultList = request.getParameter("resultList");
		//通过菜单项的ID号查询
		String MenuId = request.getParameter("MenuId");
		//通过标题查询
		String srbt = request.getParameter("srbt");
		/*设置查询条件*/
		ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
		//存储查询结果
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
		}else if(UserPer.getContentglzd().indexOf("1")==-1){
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
				//获取单位制度ID号列表
				MenuDao menuDao =new MenuDao();
				//根据权限查询
				if(UserPer.getContentglzd().indexOf("5")!=-1){
					String idList = menuDao.QueryMenuById("node_Glzd", MenuId);
					if(!"".equals(idList)&&idList!=null){
						countAll = contentGlzdDao.queryGlzdByIdListCount(idList);
						GlzdList = contentGlzdDao.queryGlzdByIdList(idList, begin, pageSize);
					}
				}else{
					//查询该节点的单位名称
					int theID = Integer.parseInt(MenuId);
					int parentID = menuDao.QueryPidById("node_Glzd", theID);
					while(parentID!=0){
						theID = parentID;
						parentID = menuDao.QueryPidById("node_Glzd", parentID);
					}
					String company = menuDao.QueryNameById("node_Glzd", theID);
					//本单位人员查询
					if(company.equals(UserInfo.getCompany())){
						String idList = menuDao.QueryMenuById("node_Glzd", MenuId);
						if(!"".equals(idList)&&idList!=null){
							countAll = contentGlzdDao.queryGlzdByIdListCount(idList);
							GlzdList = contentGlzdDao.queryGlzdByIdList(idList, begin, pageSize);
						}
					}else{//外单位人员查询
						String idList = menuDao.QueryMenuById("node_Glzd", MenuId);
						if(!"".equals(idList)&&idList!=null){
							countAll = contentGlzdDao.queryGlzdByIdListCountGK(idList);
							GlzdList = contentGlzdDao.queryGlzdByIdListGK(idList, begin, pageSize);
						}
					}
				}
			}
			//通过标题内容查询
			else if(srbt!=null){
				if(UserPer.getContentglzd().indexOf("5")!=-1){
					countAll = contentGlzdDao.queryGlzdByBtCount(srbt);
					GlzdList = contentGlzdDao.queryGlzdByBt(srbt, begin, pageSize);
					flag = "1";
				}else{
					countAll = contentGlzdDao.queryGlzdByBtCountGK(srbt,UserInfo.getCompany());
					GlzdList = contentGlzdDao.queryGlzdByBtGK(srbt, begin, pageSize,UserInfo.getCompany());
					flag = "1";
				}
			}
			
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Glzd");
			request.setAttribute("menuList", str);
			
			request.setAttribute("MenuId", MenuId);//前台页面左侧树ID
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("flag", flag);
			request.setAttribute("GlzdList", GlzdList);
			request.setAttribute("resultList", resultList);
			request.getRequestDispatcher("/jsp/MngInformation/glzdList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  删除内容
	 * 
	 */
	protected void deleteGlzd(HttpServletRequest request,
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
			
			//删除单位制度内容栏
			if(ContentGlzdDao.DeleteGlzdById(Integer.parseInt(ContendId)) > 0){
				//更新菜单栏存储项
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_Glzd", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//返回处理结果
				resultList = "信息删除成功!";
			} 
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=query&MenuId="+fatherid+"&resultList="+resultList);
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
			menuDao.InsertMenu("node_Glzd", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
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
			menuDao.UpdateMenuNameById("node_Glzd", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
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
		String idList = menuDao.QueryMenuById("node_Glzd", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_Glzd", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_Glzd", Integer.parseInt(id));
				result = "删除成功";
			}else{
				result = "无法删除！（请先删除子菜单内容）";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
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
        	RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=query&MenuId="+fatherid+"&resultList="+"文件不存在，无法下载！");
    		rd.forward(request, response);
    		return ;
        }
	}

	/*
	 *  新增单位制度记录（新增或者修改）
	 * 
	 */
	protected void saveGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "保存失败!";
		//新增或者修改
		String editGlzd_id = request.getParameter("editGlzd_id");
		
		//获取内容
		String bt = request.getParameter("bt");
		String wh = request.getParameter("wh");
		String ssrq = request.getParameter("ssrq");
		String sfgk = request.getParameter("sfgk");
		String GlzdTxt = request.getParameter("GlzdTxt");
		String FileUrl = request.getParameter("FileUrl");
		String fatherid = request.getParameter("fatherid");
		// List
		ContentGlzd Glzd = new ContentGlzd();
		Glzd.setBt(bt);
		Glzd.setWh(wh);
		Glzd.setSsrq(DateFormat(ssrq));
		Glzd.setFatherid(fatherid);
		Glzd.setSfgk(sfgk);
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
			Glzd.setFbdw(UserInfo.getCompany());
			Glzd.setCzr(UserInfo.getName());
			Glzd.setCzrID(UserInfo.getUsername());
			Glzd.setCzsj(data1);
			ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
			if("".equals(editGlzd_id)||editGlzd_id==null){
				//新增
				if(!"".equals(GlzdTxt)&&GlzdTxt!=null){
					// 创建文件并写入
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(GlzdTxt, "MngInformation/glzd", context);
					Glzd.setFileUrl(FileUrl);
				}else{
					Glzd.setFileUrl("");
				}
				int InsertID = contentGlzdDao.insertGlzd(Glzd);
				MenuDao menuDao = new MenuDao();
				menuDao.InsertMenuContextById("node_Glzd", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
				result = "保存成功!";
			}else{
				//修改
				if(!"".equals(FileUrl)&&FileUrl!=null){
					// 打开文件并写入
					ServletContext context = getServletContext();
					ContentToTxt.UpdateTxt(GlzdTxt, FileUrl, context);
					Glzd.setFileUrl(FileUrl);
				}else if(!"".equals(GlzdTxt)&&GlzdTxt!=null){
					// 创建文件并写入
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(GlzdTxt, "MngInformation/glzd", context);
					Glzd.setFileUrl(FileUrl);
				}else{
					Glzd.setFileUrl("");
				}
				Glzd.setId(Integer.parseInt(editGlzd_id));
				boolean effectCount = contentGlzdDao.updateGlzd(Glzd);
				if(effectCount){
					result = "保存成功!";
				}
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
			rd.forward(request, response);
		}
	}
	

	/*
	 *  查看某一条单位制度内容
	 * 
	 */
	protected void showGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//通过菜单项的ID号查询
		String id = request.getParameter("id");
		//存储查询结果
		ContentGlzd Glzd = new ContentGlzd();
		/*设置查询条件*/
		ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
		//通过id查询
		if(!"".equals(id)&&id!=null){
			Glzd = contentGlzdDao.queryGlzdByID(Integer.parseInt(id));
			if(!"".equals(Glzd.getFileUrl())&&Glzd.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String GlzdTxt = ContentToTxt.ReadTxt(Glzd.getFileUrl(), context);
				Glzd.setGlzdTxt(GlzdTxt);
			}else{
				Glzd.setGlzdTxt("");
			}
		}
		
		request.setAttribute("Glzd", Glzd);
		request.getRequestDispatcher("/jsp/MngInformation/glzdShow.jsp").forward(request,
				response);
	}

	/*
	 *  编辑某一条单位制度内容
	 * 
	 */
	protected void editGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//通过菜单项的ID号查询
		String id = request.getParameter("id");
		//存储查询结果
		ContentGlzd Glzd = new ContentGlzd();
		/*设置查询条件*/
		ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
		//通过id查询
		if(!"".equals(id)&&id!=null){
			Glzd = contentGlzdDao.queryGlzdByID(Integer.parseInt(id));
			if(!"".equals(Glzd.getFileUrl())&&Glzd.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String GlzdTxt = ContentToTxt.ReadTxt(Glzd.getFileUrl(), context);
				Glzd.setGlzdTxt(GlzdTxt);
			}else{
				Glzd.setGlzdTxt("");
			}
		}
		
		request.setAttribute("Glzd", Glzd);
		request.getRequestDispatcher("/jsp/MngInformation/glzdEdit.jsp").forward(request,
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
