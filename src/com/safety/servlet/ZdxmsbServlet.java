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

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MenuDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.dao.ZdxmsbDao;
import com.safety.entity.Checkid;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.Zdxmsb;
import com.safety.entity.ZdxmsbGqbg;
import com.safety.entity.ZdxmsbGqbgGk;
import com.safety.entity.ZdxmsbXydbdydb;
import com.safety.entity.ZdxmsbXydbdydbGk;
import com.safety.entity.ZdxmsbZczbbg;
import com.safety.entity.ZdxmsbZddwtz;
import com.safety.entity.ZdxmsbZdrz;
import com.safety.entity.ZdxmsbZwcz;

public class ZdxmsbServlet  extends HttpServlet{
	/**
	 * 短信平台
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
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
		if("getZdxmsb".equals(action)){//重大项目申报
			getZdxmsb(request,response);
		}else if("resetMenu".equals(action)){//重置菜单
			resetMenu(request,response);
		}if("getZdxmsbList".equals(action)){//重大项目申报——列表
			getZdxmsbList(request,response);
		}else if("ZdxmsbShow".equals(action)){//重大项目申报——查看
			ZdxmsbShow(request,response);
		}else if("ZdxmsbEdit".equals(action)){//重大项目申报——新增或修改页面
			ZdxmsbEdit(request,response);
		}else if("ZdxmsbDelete".equals(action)){//重大项目申报——删除
			ZdxmsbDelete(request,response);
		}else if("ZdxmsbSH".equals(action)){//重大项目申报——审批
			ZdxmsbSH(request,response);
		}else if("downLoad".equals(action)){//附件下载
			downLoad(request,response);
		}else if("ZdxmsbZczbbgSave".equals(action)){//重大项目申报——注册资本变更——保存页面
			ZdxmsbZczbbgSave(request,response);
		}else if("ZdxmsbZddwtzSave".equals(action)){//重大项目申报——重大对外投资——保存页面
			ZdxmsbZddwtzSave(request,response);
		}else if("ZdxmsbZdrzSave".equals(action)){//重大项目申报——重大融资——保存页面
			ZdxmsbZdrzSave(request,response);
		}else if("ZdxmsbGqbgSave".equals(action)){//重大项目申报——股权变更——保存页面
			ZdxmsbGqbgSave(request,response);
		}else if("ZdxmsbZwczSave".equals(action)){//重大项目申报——债务重组——保存页面
			ZdxmsbZwczSave(request,response);
		}else if("ZdxmsbXydbdydbSave".equals(action)){//重大项目申报——信誉担保和重大资产抵押担保——保存页面
			ZdxmsbXydbdydbSave(request,response);
		}
	}

	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getZdxmsb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getCwbb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfoName("node_zdxm");
			String flag = request.getParameter("flag");
			request.setAttribute("menuList", str);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/getZdxmsb.jsp").forward(request,
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
			menuDao.DeleteMenuName("node_zdxm");
			menuDao.ResetZzxxMenu("node_zdxm",data1,UserInfo.getName(),UserInfo.getUsername());
			RequestDispatcher rd = request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  重大项目申报——列表
	 * 
	 */
	protected void getZdxmsbList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getZdxmsb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('无查询权限！');</script>");
		}else{
			//查询列表
			ArrayList<Zdxmsb> ZdxmsbList = new ArrayList<Zdxmsb>();
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
			String cxcompany = request.getParameter("cxcompany");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt='2' and  czrID = '"+UserInfo.getUsername()+"')) ";
			}
			//查阅其他单位的权限
//			if(UserPer.getZdxmsb().indexOf("5")==-1){
//				if("".equals(srbt)){
//					srbt =" where czrID='"+UserInfo.getUsername()+"'";
//				}else{
//					srbt =srbt+" and czrID='"+UserInfo.getUsername()+"'";
//				}
//			}
			if("2".equals(flag)){
				//查询未审批的内容（从首页进入）
				srbt=" where shyj='未审批' and tjzt='1' and shrID='"+UserInfo.getUsername()+"'";
				cxcompany = "";
			}
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			countAll = zdxmsbDao.queryZdxmsbListByBtCount(srbt);
			ZdxmsbList = zdxmsbDao.queryZdxmsbListByBt(srbt, begin, pageSize);
			request.setAttribute("ZdxmsbList", ZdxmsbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbList.jsp").forward(request,
					response);
		}
	}

	 /*
	 *  重大项目申报——查看
	 * 
	 */
	protected void ZdxmsbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String lx = request.getParameter("lx");
		String nrid = request.getParameter("nrid");
		String flag = request.getParameter("flag");//1：查看；2：审批
		String result = request.getParameter("result");
		ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
		//1:注册资本变更2:重大对外投资3:重大融资（举债）4:股权变更5:债务重组6:信誉担保和重大资产抵押担保
		if("1".equals(lx)){
			ZdxmsbZczbbg zdxmsbZczbbg = new ZdxmsbZczbbg();
			zdxmsbZczbbg = zdxmsbDao.queryZdxmsbZczbbgByID(Integer.parseInt(nrid));
			request.setAttribute("zdxmsbZczbbg", zdxmsbZczbbg);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZczbbgShow.jsp").forward(request,
					response);
		}else if("2".equals(lx)){
			ZdxmsbZddwtz zdxmsbZddwtz = new ZdxmsbZddwtz();
			zdxmsbZddwtz = zdxmsbDao.queryZdxmsbZddwtzByID(Integer.parseInt(nrid));
			request.setAttribute("zdxmsbZddwtz", zdxmsbZddwtz);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZddwtzShow.jsp").forward(request,
					response);
		}else if("3".equals(lx)){
			ZdxmsbZdrz zdxmsbZdrz = new ZdxmsbZdrz();
			zdxmsbZdrz = zdxmsbDao.queryZdxmsbZdrzByID(Integer.parseInt(nrid));
			request.setAttribute("zdxmsbZdrz", zdxmsbZdrz);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZdrzShow.jsp").forward(request,
					response);
		}else if("4".equals(lx)){
			ZdxmsbGqbg zdxmsbGqbg = new ZdxmsbGqbg();
			ArrayList<ZdxmsbGqbgGk> ZdxmsbGqbgGkList = new ArrayList<ZdxmsbGqbgGk>();
			zdxmsbGqbg = zdxmsbDao.queryZdxmsbGqbgByID(Integer.parseInt(nrid));
			if(!"".equals(zdxmsbGqbg.getGkid())&&zdxmsbGqbg.getGkid()!=null){
				ZdxmsbGqbgGkList =  zdxmsbDao.queryZdxmsbGqbgGkByID(zdxmsbGqbg.getGkid());
			}
			request.setAttribute("zdxmsbGqbg", zdxmsbGqbg);
			request.setAttribute("ZdxmsbGqbgGkList", ZdxmsbGqbgGkList);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbGqbgShow.jsp").forward(request,
					response);
		}else if("5".equals(lx)){
			ZdxmsbZwcz zdxmsbZwcz = new ZdxmsbZwcz();
			zdxmsbZwcz = zdxmsbDao.queryZdxmsbZwczByID(Integer.parseInt(nrid));
			request.setAttribute("zdxmsbZwcz", zdxmsbZwcz);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZwczShow.jsp").forward(request,
					response);
		}else if("6".equals(lx)){
			ZdxmsbXydbdydb zdxmsbXydbdydb = new ZdxmsbXydbdydb();
			ArrayList<ZdxmsbXydbdydbGk> ZdxmsbXydbdydbGkList = new ArrayList<ZdxmsbXydbdydbGk>();
			zdxmsbXydbdydb = zdxmsbDao.queryZdxmsbXydbdydbByID(Integer.parseInt(nrid));
			if(!"".equals(zdxmsbXydbdydb.getGkid())&&zdxmsbXydbdydb.getGkid()!=null){
				ZdxmsbXydbdydbGkList =  zdxmsbDao.queryZdxmsbXydbdydbGkByID(zdxmsbXydbdydb.getGkid());
			}
			request.setAttribute("zdxmsbXydbdydb", zdxmsbXydbdydb);
			request.setAttribute("ZdxmsbXydbdydbGkList", ZdxmsbXydbdydbGkList);
			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbXydbdydbShow.jsp").forward(request,
					response);
		}else{
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('无效数据');</script>");
		}
		
		return;
	}
	
	/*
	 *  重大项目申报——进入新增或修改页面
	 * 
	 */
	protected void ZdxmsbEdit(HttpServletRequest request,
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
			String lx = request.getParameter("lx");
			String nrid = request.getParameter("nrid");
			String result = request.getParameter("result");
			//查询审批人
			Checkid checkid = new Checkid();
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			checkid = zdxmsbDao.queryZdxmsbSHR();
			if(checkid.getId()==0){
				result = "请联系管理员，预先设置审批人";
			}else{
				if("1".equals(lx)){
					ZdxmsbZczbbg zdxmsbZczbbg = new ZdxmsbZczbbg();
					zdxmsbZczbbg.setShr(checkid.getZdxmsbName());
					zdxmsbZczbbg.setShrID(checkid.getZdxmsbID());
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbZczbbg = zdxmsbDao.queryZdxmsbZczbbgByID(Integer.parseInt(nrid));
					}
					request.setAttribute("zdxmsbZczbbg", zdxmsbZczbbg);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZczbbgEdit.jsp").forward(request,
							response);
				}else if("2".equals(lx)){
					ZdxmsbZddwtz zdxmsbZddwtz = new ZdxmsbZddwtz();
					zdxmsbZddwtz.setShr(checkid.getZdxmsbName());
					zdxmsbZddwtz.setShrID(checkid.getZdxmsbID());
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbZddwtz = zdxmsbDao.queryZdxmsbZddwtzByID(Integer.parseInt(nrid));
					}
					request.setAttribute("zdxmsbZddwtz", zdxmsbZddwtz);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZddwtzEdit.jsp").forward(request,
							response);
				}else if("3".equals(lx)){
					ZdxmsbZdrz zdxmsbZdrz = new ZdxmsbZdrz();
					zdxmsbZdrz.setShr(checkid.getZdxmsbName());
					zdxmsbZdrz.setShrID(checkid.getZdxmsbID());
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbZdrz = zdxmsbDao.queryZdxmsbZdrzByID(Integer.parseInt(nrid));
					}
					request.setAttribute("zdxmsbZdrz", zdxmsbZdrz);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZdrzEdit.jsp").forward(request,
							response);
				}else if("4".equals(lx)){
					ZdxmsbGqbg zdxmsbGqbg = new ZdxmsbGqbg();
					ArrayList<ZdxmsbGqbgGk> ZdxmsbGqbgGkList = new ArrayList<ZdxmsbGqbgGk>();
					zdxmsbGqbg.setShr(checkid.getZdxmsbName());
					zdxmsbGqbg.setShrID(checkid.getZdxmsbID());
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbGqbg = zdxmsbDao.queryZdxmsbGqbgByID(Integer.parseInt(nrid));
						if(!"".equals(zdxmsbGqbg.getGkid())&&zdxmsbGqbg.getGkid()!=null){
							ZdxmsbGqbgGkList =  zdxmsbDao.queryZdxmsbGqbgGkByID(zdxmsbGqbg.getGkid());
						}
					}
					request.setAttribute("zdxmsbGqbg", zdxmsbGqbg);
					request.setAttribute("ZdxmsbGqbgGkList", ZdxmsbGqbgGkList);
					request.setAttribute("zdxmsbGqbg", zdxmsbGqbg);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbGqbgEdit.jsp").forward(request,
							response);
				}else if("5".equals(lx)){
					ZdxmsbZwcz zdxmsbZwcz = new ZdxmsbZwcz();
					zdxmsbZwcz.setShr(checkid.getZdxmsbName());
					zdxmsbZwcz.setShrID(checkid.getZdxmsbID());
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbZwcz = zdxmsbDao.queryZdxmsbZwczByID(Integer.parseInt(nrid));
					}
					request.setAttribute("zdxmsbZwcz", zdxmsbZwcz);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbZwczEdit.jsp").forward(request,
							response);
				}else if("6".equals(lx)){
					ZdxmsbXydbdydb zdxmsbXydbdydb = new ZdxmsbXydbdydb();
					ArrayList<ZdxmsbXydbdydbGk> ZdxmsbXydbdydbGkList = new ArrayList<ZdxmsbXydbdydbGk>();
					zdxmsbXydbdydb.setShr(checkid.getZdxmsbName());
					zdxmsbXydbdydb.setShrID(checkid.getZdxmsbID());
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbXydbdydb = zdxmsbDao.queryZdxmsbXydbdydbByID(Integer.parseInt(nrid));
						if(!"".equals(zdxmsbXydbdydb.getGkid())&&zdxmsbXydbdydb.getGkid()!=null){
							ZdxmsbXydbdydbGkList =  zdxmsbDao.queryZdxmsbXydbdydbGkByID(zdxmsbXydbdydb.getGkid());
						}
					}
					request.setAttribute("zdxmsbXydbdydb", zdxmsbXydbdydb);
					request.setAttribute("ZdxmsbXydbdydbGkList", ZdxmsbXydbdydbGkList);
					request.setAttribute("zdxmsbXydbdydb", zdxmsbXydbdydb);
					request.setAttribute("result", result);
					request.getRequestDispatcher("/jsp/ChoiTrialInformation/ZdxmsbXydbdydbEdit.jsp").forward(request,
							response);
				}else{
					response.setContentType("text/html;charset=gb2312");
					PrintWriter out = response.getWriter(); 
					out.println("<script>alert('无效数据');</script>");
				}
				
				
			}
			
		}
	}

	/*
	 *  重大项目申报——删除
	 * 
	 */
	protected void ZdxmsbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String lx = request.getParameter("lx");
		String nrid = request.getParameter("nrid");
		String smj = request.getParameter("smj");
		ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
		if(!"".equals(smj)&&smj!=null){
			String[] FileUrlStr = smj.split(";");
			//删除下载文件
			for(int j=0;j<FileUrlStr.length; j++){
				String FullFilePath = request.getRealPath("/UserFile") + "/ChoiTrialInformation/zdxmsb/"+FileUrlStr[j];
				File file = new File(FullFilePath);  
				// 路径为文件且不为空则进行删除  
				if (file.isFile() && file.exists()) {  
					file.delete();  
				}
			}
		}
		if(!"".equals(id)&&id!=null){
			zdxmsbDao.DeleteZdxmsbById(Integer.parseInt(id));
		}
		if("1".equals(lx)){
			if(!"".equals(nrid)&&nrid!=null){
				zdxmsbDao.DeleteZdxmsbZczbbgById(Integer.parseInt(nrid));
			}
		}else if("2".equals(lx)){
			if(!"".equals(nrid)&&nrid!=null){
				zdxmsbDao.DeleteZdxmsbZddwtzById(Integer.parseInt(nrid));
			}
		}else if("3".equals(lx)){
			if(!"".equals(nrid)&&nrid!=null){
				zdxmsbDao.DeleteZdxmsbZdrzById(Integer.parseInt(nrid));
			}
		}else if("4".equals(lx)){
			if(!"".equals(nrid)&&nrid!=null){
				ZdxmsbGqbg zdxmsbGqbg = new ZdxmsbGqbg();
				zdxmsbGqbg = zdxmsbDao.queryZdxmsbGqbgByID(Integer.parseInt(nrid));
				if(!"".equals(zdxmsbGqbg.getGkid())&&zdxmsbGqbg.getGkid()!=null){
					zdxmsbDao.deleteZdxmsbGqbgGkByIDList(zdxmsbGqbg.getGkid());
				}
				zdxmsbDao.DeleteZdxmsbGqbgById(Integer.parseInt(nrid));
			}
		}else if("5".equals(lx)){
			if(!"".equals(nrid)&&nrid!=null){
				zdxmsbDao.DeleteZdxmsbZwczById(Integer.parseInt(nrid));
			}
		}else if("6".equals(lx)){
			if(!"".equals(nrid)&&nrid!=null){
				ZdxmsbXydbdydb zdxmsbXydbdydb = new ZdxmsbXydbdydb();
				zdxmsbXydbdydb = zdxmsbDao.queryZdxmsbXydbdydbByID(Integer.parseInt(nrid));
				if(!"".equals(zdxmsbXydbdydb.getGkid())&&zdxmsbXydbdydb.getGkid()!=null){
					zdxmsbDao.deleteZdxmsbXydbdydbGkByIDList(zdxmsbXydbdydb.getGkid());
				}
				zdxmsbDao.DeleteZdxmsbXydbdydbById(Integer.parseInt(nrid));
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb");
		rd.forward(request, response);
	}
	
	
	/*
	 *  重大项目申报——审批
	 * 
	 */
	protected void ZdxmsbSH(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//审批
			String lx = request.getParameter("lx");
			String nrid = request.getParameter("nrid");
			String shyj = request.getParameter("shyj");
						
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Date  data1=new java.sql.Date(date.getTime());
			String result =  "审批失败";
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			if(!"".equals(nrid)&&nrid!=null&&!"0".equals(nrid)){
				zdxmsbDao.ZdxmsbSH(Integer.parseInt(nrid),shyj,data1);
				result= "审批成功";
				if("1".equals(lx)){
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbDao.ZdxmsbZczbbgSH(Integer.parseInt(nrid),shyj,data1);
					}
				}else if("2".equals(lx)){
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbDao.ZdxmsbZddwtzSH(Integer.parseInt(nrid),shyj,data1);
					}
				}else if("3".equals(lx)){
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbDao.ZdxmsbZdrzSH(Integer.parseInt(nrid),shyj,data1);
					}
				}else if("4".equals(lx)){
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbDao.ZdxmsbGqbgSH(Integer.parseInt(nrid),shyj,data1);
					}
				}else if("5".equals(lx)){
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbDao.ZdxmsbZwczSH(Integer.parseInt(nrid),shyj,data1);
					}
				}else if("6".equals(lx)){
					if(!"".equals(nrid)&&nrid!=null){
						zdxmsbDao.ZdxmsbXydbdydbSH(Integer.parseInt(nrid),shyj,data1);
					}
				}
				//驳回人员的用户名
				String bhry = request.getParameter("bhry");
				ContentZzxx Zzxx = new ContentZzxx();
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
				String dxnr = UserInfo.getName()+"：您有一份重大事项申报表被审核，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				//获取当前时间
				java.sql.Timestamp  data2=new java.sql.Timestamp(date.getTime());
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data2);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
				int  MyZdxmsbSH = zdxmsbDao.queryMyZdxmsbSHCount(UserInfo.getUsername());
				session.setAttribute("MyZdxmsbSH", MyZdxmsbSH);
				request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsbList&flag=2&result="+result).forward(request,
						response);
			}
			
		}
	}

	/*
	 * 下载文件
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String FullFilePath = request.getRealPath("/UserFile") + "/ChoiTrialInformation/zdxmsb/"+URL;
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
	 *  重大项目申报——注册资本变更——保存
	 * 
	 */
	protected void ZdxmsbZczbbgSave(HttpServletRequest request,
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
			String ZdxmsbZczbbg_id = request.getParameter("ZdxmsbZczbbg_id");
			String zb = request.getParameter("zb");
			String kz = request.getParameter("kz");
			String jz = request.getParameter("jz");
			String bgzb = request.getParameter("bgzb");
			String yqk = request.getParameter("yqk");
			String bgqk = request.getParameter("bgqk");
			String bgyj = request.getParameter("bgyj");
			String gcqk = request.getParameter("gcqk");
			String fajg = request.getParameter("fajg");
			String shr = request.getParameter("shr");
			String shrID = request.getParameter("shrID");
			String shyj = "未审批";
			String smj = request.getParameter("smj");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
						
			ZdxmsbZczbbg zdxmsbZczbbg = new ZdxmsbZczbbg();
			zdxmsbZczbbg.setZb(zb);
			zdxmsbZczbbg.setKz(kz);
			zdxmsbZczbbg.setJz(jz);
			zdxmsbZczbbg.setBgzb(bgzb);
			zdxmsbZczbbg.setYqk(yqk);
			zdxmsbZczbbg.setBgqk(bgqk);
			zdxmsbZczbbg.setBgyj(bgyj);
			zdxmsbZczbbg.setGcqk(gcqk);
			zdxmsbZczbbg.setFajg(fajg);
			zdxmsbZczbbg.setShr(shr);
			zdxmsbZczbbg.setShrID(shrID);
			zdxmsbZczbbg.setShyj(shyj);
			zdxmsbZczbbg.setSmj(smj);
			zdxmsbZczbbg.setCzr(UserInfo.getName());
			zdxmsbZczbbg.setCzrID(UserInfo.getUsername());
			zdxmsbZczbbg.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			zdxmsbZczbbg.setCzsj(data1);
			//重大项目申报表
			Zdxmsb zdxmsb = new Zdxmsb();

			zdxmsb.setLx("1");
			zdxmsb.setSmj(smj);
			zdxmsb.setShr(shr);
			zdxmsb.setShrID(shrID);
			zdxmsb.setShyj(shyj);
			zdxmsb.setTjzt(tjzt);
			zdxmsb.setCzr(UserInfo.getName());
			zdxmsb.setCzrID(UserInfo.getUsername());
			zdxmsb.setCzrdw(UserInfo.getCompany());
			zdxmsb.setCzsj(data1);
			
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			if(!"".equals(ZdxmsbZczbbg_id)&&ZdxmsbZczbbg_id!=null&&!"0".equals(ZdxmsbZczbbg_id)){
				zdxmsbZczbbg.setId(Integer.parseInt(ZdxmsbZczbbg_id));
				zdxmsbDao.updateZdxmsbZczbbg(zdxmsbZczbbg);
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZczbbg_id));
				zdxmsbDao.updateZdxmsb(zdxmsb);
			}else{
				ZdxmsbZczbbg_id = zdxmsbDao.insertZdxmsbZczbbg(zdxmsbZczbbg)+"";
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZczbbg_id));
				zdxmsbDao.insertZdxmsb(zdxmsb);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb").forward(request,
					response);
			
		}
	}
	/*
	 *  重大项目申报——重大对外投资——保存
	 * 
	 */
	protected void ZdxmsbZddwtzSave(HttpServletRequest request,
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
			String ZdxmsbZddwtz_id = request.getParameter("ZdxmsbZddwtz_id");
			String ed = request.getParameter("ed");
			String zyxm = request.getParameter("zyxm");
			String tzmd = request.getParameter("tzmd");
			String jbqk = request.getParameter("jbqk");
			String lzqk = request.getParameter("lzqk");
			String tzxy = request.getParameter("tzxy");
			String shr = request.getParameter("shr");
			String shrID = request.getParameter("shrID");
			String shyj = "未审批";
			String smj = request.getParameter("smj");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
						
			ZdxmsbZddwtz zdxmsbZddwtz = new ZdxmsbZddwtz();
			zdxmsbZddwtz.setEd(ed);
			zdxmsbZddwtz.setZyxm(zyxm);
			zdxmsbZddwtz.setTzmd(tzmd);
			zdxmsbZddwtz.setJbqk(jbqk);
			zdxmsbZddwtz.setLzqk(lzqk);
			zdxmsbZddwtz.setTzxy(tzxy);
			zdxmsbZddwtz.setShr(shr);
			zdxmsbZddwtz.setShrID(shrID);
			zdxmsbZddwtz.setShyj(shyj);
			zdxmsbZddwtz.setSmj(smj);
			zdxmsbZddwtz.setCzr(UserInfo.getName());
			zdxmsbZddwtz.setCzrID(UserInfo.getUsername());
			zdxmsbZddwtz.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			zdxmsbZddwtz.setCzsj(data1);
			//重大项目申报表
			Zdxmsb zdxmsb = new Zdxmsb();

			zdxmsb.setLx("2");
			zdxmsb.setSmj(smj);
			zdxmsb.setShr(shr);
			zdxmsb.setShrID(shrID);
			zdxmsb.setShyj(shyj);
			zdxmsb.setCzr(UserInfo.getName());
			zdxmsb.setCzrID(UserInfo.getUsername());
			zdxmsb.setCzrdw(UserInfo.getCompany());
			zdxmsb.setCzsj(data1);
			zdxmsb.setTjzt(tjzt);
			
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			if(!"".equals(ZdxmsbZddwtz_id)&&ZdxmsbZddwtz_id!=null&&!"0".equals(ZdxmsbZddwtz_id)){
				zdxmsbZddwtz.setId(Integer.parseInt(ZdxmsbZddwtz_id));
				zdxmsbDao.updateZdxmsbZddwtz(zdxmsbZddwtz);
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZddwtz_id));
				zdxmsbDao.updateZdxmsb(zdxmsb);
			}else{
				ZdxmsbZddwtz_id = zdxmsbDao.insertZdxmsbZddwtz(zdxmsbZddwtz)+"";
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZddwtz_id));
				zdxmsbDao.insertZdxmsb(zdxmsb);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb").forward(request,
					response);
			
		}
	}
	/*
	 *  重大项目申报——重大融资——保存
	 * 
	 */
	protected void ZdxmsbZdrzSave(HttpServletRequest request,
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
			String ZdxmsbZdrz_id = request.getParameter("ZdxmsbZdrz_id");
			String ed = request.getParameter("ed");
			String yy = request.getParameter("yy");
			String jhsj1 = request.getParameter("jhsj1");
			String jhsj2 = request.getParameter("jhsj2");
			String ly = request.getParameter("ly");
			String zfqk = request.getParameter("zfqk");
			String dbqk = request.getParameter("dbqk");
			String yqhk = request.getParameter("yqhk");
			String ndhk = request.getParameter("ndhk");
			String ljhk = request.getParameter("ljhk");
			String ye = request.getParameter("ye");
			String shr = request.getParameter("shr");
			String shrID = request.getParameter("shrID");
			String shyj = "未审批";
			String smj = request.getParameter("smj");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
						
			ZdxmsbZdrz zdxmsbZdrz = new ZdxmsbZdrz();
			zdxmsbZdrz.setEd(ed);
			zdxmsbZdrz.setYy(yy);
			zdxmsbZdrz.setJhsj1(DateFormat(jhsj1));
			zdxmsbZdrz.setJhsj2(DateFormat(jhsj2));
			zdxmsbZdrz.setLy(ly);
			zdxmsbZdrz.setZfqk(zfqk);
			zdxmsbZdrz.setDbqk(dbqk);
			zdxmsbZdrz.setYqhk(yqhk);
			zdxmsbZdrz.setNdhk(ndhk);
			zdxmsbZdrz.setLjhk(ljhk);
			zdxmsbZdrz.setYe(ye);
			zdxmsbZdrz.setShr(shr);
			zdxmsbZdrz.setShrID(shrID);
			zdxmsbZdrz.setShyj(shyj);
			zdxmsbZdrz.setSmj(smj);
			zdxmsbZdrz.setCzr(UserInfo.getName());
			zdxmsbZdrz.setCzrID(UserInfo.getUsername());
			zdxmsbZdrz.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			zdxmsbZdrz.setCzsj(data1);
			//重大项目申报表
			Zdxmsb zdxmsb = new Zdxmsb();

			zdxmsb.setLx("3");
			zdxmsb.setSmj(smj);
			zdxmsb.setShr(shr);
			zdxmsb.setShrID(shrID);
			zdxmsb.setShyj(shyj);
			zdxmsb.setCzr(UserInfo.getName());
			zdxmsb.setCzrID(UserInfo.getUsername());
			zdxmsb.setCzrdw(UserInfo.getCompany());
			zdxmsb.setCzsj(data1);
			zdxmsb.setTjzt(tjzt);
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			if(!"".equals(ZdxmsbZdrz_id)&&ZdxmsbZdrz_id!=null&&!"0".equals(ZdxmsbZdrz_id)){
				zdxmsbZdrz.setId(Integer.parseInt(ZdxmsbZdrz_id));
				zdxmsbDao.updateZdxmsbZdrz(zdxmsbZdrz);
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZdrz_id));
				zdxmsbDao.updateZdxmsb(zdxmsb);
			}else{
				ZdxmsbZdrz_id = zdxmsbDao.insertZdxmsbZdrz(zdxmsbZdrz)+"";
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZdrz_id));
				zdxmsbDao.insertZdxmsb(zdxmsb);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb").forward(request,
					response);
			
		}
	}

	/*
	 *  重大项目申报——股权变更——保存
	 * 
	 */
	protected void ZdxmsbGqbgSave(HttpServletRequest request,
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
			String ZdxmsbGqbg_id = request.getParameter("ZdxmsbGqbg_id");
			String gkid = request.getParameter("gkid");
			String ly = request.getParameter("ly");
			String qk = request.getParameter("qk");
			String shr = request.getParameter("shr");
			String shrID = request.getParameter("shrID");
			String shyj = "未审批";
			String smj = request.getParameter("smj");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			ZdxmsbGqbg zdxmsbGqbg = new ZdxmsbGqbg();
			zdxmsbGqbg.setLy(ly);
			zdxmsbGqbg.setQk(qk);
			zdxmsbGqbg.setShr(shr);
			zdxmsbGqbg.setShrID(shrID);
			zdxmsbGqbg.setShyj(shyj);
			zdxmsbGqbg.setSmj(smj);
			zdxmsbGqbg.setCzr(UserInfo.getName());
			zdxmsbGqbg.setCzrID(UserInfo.getUsername());
			zdxmsbGqbg.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			zdxmsbGqbg.setCzsj(data1);
			//重大项目申报表
			Zdxmsb zdxmsb = new Zdxmsb();

			zdxmsb.setLx("4");
			zdxmsb.setSmj(smj);
			zdxmsb.setShr(shr);
			zdxmsb.setShrID(shrID);
			zdxmsb.setShyj(shyj);
			zdxmsb.setCzr(UserInfo.getName());
			zdxmsb.setCzrID(UserInfo.getUsername());
			zdxmsb.setCzrdw(UserInfo.getCompany());
			zdxmsb.setCzsj(data1);
			zdxmsb.setTjzt(tjzt);
			
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			
			String mc[]=request.getParameterValues("mc"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内  
			String yyys[]=request.getParameterValues("yyys");    
			String xz[]=request.getParameterValues("xz");
			String cz[]=request.getParameterValues("cz");
			String hyys[]=request.getParameterValues("hyys");
			String cb[]=request.getParameterValues("cb");
			String yj[]=request.getParameterValues("yj");
			String dj[]=request.getParameterValues("dj");
			//删除原有子项目记录，保存新内容
			if (!"".equals(gkid)&&gkid!=null){
				zdxmsbDao.deleteZdxmsbGqbgGkByIDList(gkid);
			}
			gkid="";
			int xzgkid=0;
			ZdxmsbGqbgGk zdxmsbGqbgGk = new ZdxmsbGqbgGk();
			if(mc!=null){
				for(int i=0; i<mc.length; i++){
					zdxmsbGqbgGk.setMc(mc[i]);
					zdxmsbGqbgGk.setYyys(yyys[i]);
					zdxmsbGqbgGk.setXz(xz[i]);
					zdxmsbGqbgGk.setCz(cz[i]);
					zdxmsbGqbgGk.setHyys(hyys[i]);
					zdxmsbGqbgGk.setCb(cb[i]);
					zdxmsbGqbgGk.setYj(yj[i]);
					zdxmsbGqbgGk.setDj(dj[i]);
					xzgkid = zdxmsbDao.insertZdxmsbGqbgGk(zdxmsbGqbgGk);
					if("".equals(gkid)){
						gkid = xzgkid+"";
					}else{
						gkid = gkid+","+xzgkid;
					}
				}
			}
			zdxmsbGqbg.setGkid(gkid);
			if(!"".equals(ZdxmsbGqbg_id)&&ZdxmsbGqbg_id!=null&&!"0".equals(ZdxmsbGqbg_id)){
				//修改
				zdxmsbGqbg.setId(Integer.parseInt(ZdxmsbGqbg_id));
				zdxmsbDao.updateZdxmsbGqbg(zdxmsbGqbg);
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbGqbg_id));
				zdxmsbDao.updateZdxmsb(zdxmsb);
			}else{
				//新增
				ZdxmsbGqbg_id = zdxmsbDao.insertZdxmsbGqbg(zdxmsbGqbg)+"";
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbGqbg_id));
				zdxmsbDao.insertZdxmsb(zdxmsb);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb").forward(request,
					response);
			
		}
	}
	/*
	 *  重大项目申报——债务重组——保存
	 * 
	 */
	protected void ZdxmsbZwczSave(HttpServletRequest request,
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
			String ZdxmsbZwcz_id = request.getParameter("ZdxmsbZwcz_id");
			String mdyy = request.getParameter("mdyy");
			String nred = request.getParameter("nred");
			String cb = request.getParameter("cb");
			String fa = request.getParameter("fa");
			String qk = request.getParameter("qk");
			String shr = request.getParameter("shr");
			String shrID = request.getParameter("shrID");
			String shyj = "未审批";
			String smj = request.getParameter("smj");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
						
			ZdxmsbZwcz zdxmsbZwcz = new ZdxmsbZwcz();
			zdxmsbZwcz.setMdyy(mdyy);
			zdxmsbZwcz.setNred(nred);
			zdxmsbZwcz.setCb(cb);
			zdxmsbZwcz.setFa(fa);
			zdxmsbZwcz.setQk(qk);
			zdxmsbZwcz.setShr(shr);
			zdxmsbZwcz.setShrID(shrID);
			zdxmsbZwcz.setShyj(shyj);
			zdxmsbZwcz.setSmj(smj);
			zdxmsbZwcz.setCzr(UserInfo.getName());
			zdxmsbZwcz.setCzrID(UserInfo.getUsername());
			zdxmsbZwcz.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			zdxmsbZwcz.setCzsj(data1);
			//重大项目申报表
			Zdxmsb zdxmsb = new Zdxmsb();

			zdxmsb.setLx("5");
			zdxmsb.setSmj(smj);
			zdxmsb.setShr(shr);
			zdxmsb.setShrID(shrID);
			zdxmsb.setShyj(shyj);
			zdxmsb.setCzr(UserInfo.getName());
			zdxmsb.setCzrID(UserInfo.getUsername());
			zdxmsb.setCzrdw(UserInfo.getCompany());
			zdxmsb.setCzsj(data1);
			zdxmsb.setTjzt(tjzt);
			
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			if(!"".equals(ZdxmsbZwcz_id)&&ZdxmsbZwcz_id!=null&&!"0".equals(ZdxmsbZwcz_id)){
				zdxmsbZwcz.setId(Integer.parseInt(ZdxmsbZwcz_id));
				zdxmsbDao.updateZdxmsbZwcz(zdxmsbZwcz);
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZwcz_id));
				zdxmsbDao.updateZdxmsb(zdxmsb);
			}else{
				ZdxmsbZwcz_id = zdxmsbDao.insertZdxmsbZwcz(zdxmsbZwcz)+"";
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbZwcz_id));
				zdxmsbDao.insertZdxmsb(zdxmsb);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb").forward(request,
					response);
			
		}
	}
	/*
	 *  重大项目申报——信誉担保和重大资产抵押担保——保存
	 * 
	 */
	protected void ZdxmsbXydbdydbSave(HttpServletRequest request,
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
			String ZdxmsbXydbdydb_id = request.getParameter("ZdxmsbXydbdydb_id");
			String gkid = request.getParameter("gkid");
			String ly = request.getParameter("ly");
			String nr = request.getParameter("nr");
			String zcze1 = request.getParameter("zcze1");
			String zcze2 = request.getParameter("zcze2");
			String fzze1 = request.getParameter("fzze1");
			String fzze2 = request.getParameter("fzze2");
			String jzcze1 = request.getParameter("jzcze1");
			String jzcze2 = request.getParameter("jzcze2");
			String jlsp1 = request.getParameter("jlsp1");
			String jlsp2 = request.getParameter("jlsp2");
			String zjze1 = request.getParameter("zjze1");
			String zjze2 = request.getParameter("zjze2");
			String ssqk = request.getParameter("ssqk");
			String shr = request.getParameter("shr");
			String shrID = request.getParameter("shrID");
			String shyj = "未审批";
			String smj = request.getParameter("smj");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			ZdxmsbXydbdydb zdxmsbXydbdydb = new ZdxmsbXydbdydb();
			zdxmsbXydbdydb.setLy(ly);
			zdxmsbXydbdydb.setNr(nr);
			zdxmsbXydbdydb.setZcze1(zcze1);
			zdxmsbXydbdydb.setZcze2(zcze2);
			zdxmsbXydbdydb.setFzze1(fzze1);
			zdxmsbXydbdydb.setFzze2(fzze2);
			zdxmsbXydbdydb.setJzcze1(jzcze1);
			zdxmsbXydbdydb.setJzcze2(jzcze2);
			zdxmsbXydbdydb.setJlsp1(jlsp1);
			zdxmsbXydbdydb.setJlsp2(jlsp2);
			zdxmsbXydbdydb.setZjze1(zjze1);
			zdxmsbXydbdydb.setZjze2(zjze2);
			zdxmsbXydbdydb.setSsqk(ssqk);
			zdxmsbXydbdydb.setShr(shr);
			zdxmsbXydbdydb.setShrID(shrID);
			zdxmsbXydbdydb.setShyj(shyj);
			zdxmsbXydbdydb.setSmj(smj);
			zdxmsbXydbdydb.setCzr(UserInfo.getName());
			zdxmsbXydbdydb.setCzrID(UserInfo.getUsername());
			zdxmsbXydbdydb.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			zdxmsbXydbdydb.setCzsj(data1);
			//重大项目申报表
			Zdxmsb zdxmsb = new Zdxmsb();

			zdxmsb.setLx("6");
			zdxmsb.setSmj(smj);
			zdxmsb.setShr(shr);
			zdxmsb.setShrID(shrID);
			zdxmsb.setShyj(shyj);
			zdxmsb.setCzr(UserInfo.getName());
			zdxmsb.setCzrID(UserInfo.getUsername());
			zdxmsb.setCzrdw(UserInfo.getCompany());
			zdxmsb.setCzsj(data1);
			zdxmsb.setTjzt(tjzt);
			
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			
			String mc[]=request.getParameterValues("mc"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内  
			String pgsj[]=request.getParameterValues("pgsj");    
			String jz[]=request.getParameterValues("jz");
			String dbed[]=request.getParameterValues("dbed");
			//删除原有子项目记录，保存新内容
			if (!"".equals(gkid)&&gkid!=null){
				zdxmsbDao.deleteZdxmsbXydbdydbGkByIDList(gkid);
			}
			gkid="";
			int xzgkid=0;
			ZdxmsbXydbdydbGk zdxmsbXydbdydbGk = new ZdxmsbXydbdydbGk();
			if(mc!=null){
				for(int i=0; i<mc.length; i++){
					zdxmsbXydbdydbGk.setMc(mc[i]);
					zdxmsbXydbdydbGk.setPgsj(DateFormat(pgsj[i]));
					zdxmsbXydbdydbGk.setJz(jz[i]);
					zdxmsbXydbdydbGk.setDbed(dbed[i]);
					xzgkid = zdxmsbDao.insertZdxmsbXydbdydbGk(zdxmsbXydbdydbGk);
					if("".equals(gkid)){
						gkid = xzgkid+"";
					}else{
						gkid = gkid+","+xzgkid;
					}
				}
			}
			zdxmsbXydbdydb.setGkid(gkid);
			if(!"".equals(ZdxmsbXydbdydb_id)&&ZdxmsbXydbdydb_id!=null&&!"0".equals(ZdxmsbXydbdydb_id)){
				//修改
				zdxmsbXydbdydb.setId(Integer.parseInt(ZdxmsbXydbdydb_id));
				zdxmsbDao.updateZdxmsbXydbdydb(zdxmsbXydbdydb);
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbXydbdydb_id));
				zdxmsbDao.updateZdxmsb(zdxmsb);
			}else{
				//新增
				ZdxmsbXydbdydb_id = zdxmsbDao.insertZdxmsbXydbdydb(zdxmsbXydbdydb)+"";
				zdxmsb.setNrid(Integer.parseInt(ZdxmsbXydbdydb_id));
				zdxmsbDao.insertZdxmsb(zdxmsb);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("ZdxmsbServlet?action=getZdxmsb").forward(request,
					response);
			
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
