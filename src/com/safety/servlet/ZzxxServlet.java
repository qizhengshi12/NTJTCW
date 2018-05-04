package com.safety.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MenuDao;
import com.safety.dao.PermissionsDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Menu;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;






public class ZzxxServlet extends HttpServlet{
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
		if("getCompany".equals(action)){//刷新左侧组织结构图
			getCompany(request,response);
		}else if("query".equals(action)){//查询人员
			query(request,response);
		}else if("CXCompany".equals(action)){//查询单位列表（用于条件查询）
			CXCompany(request,response);
		}else if("insertMenu".equals(action)){//新增菜单
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){//修改菜单
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){//删除菜单
			deleteMenu(request,response);
		}else if("showPerson".equals(action)){//进入查看人员详细信息页面
			showPerson(request,response);
		}else if("insertPerson".equals(action)){//新增用户
			insertPerson(request,response);
		}else if("editPerson".equals(action)){//进入编辑页面
			editPerson(request,response);
		}else if("updatePerson".equals(action)){//更新用户信息
			updatePerson(request,response);
		}else if("deletePerson".equals(action)){//删除用户
			deletePerson(request,response);
		}else if("sortNum".equals(action)){//排列顺序
			sortNum(request,response);
		}else if("FindSameUser".equals(action)){//注册时用AJAX确认用户名未重名
			FindSameUser(request,response);
		}else if("FindCompany".equals(action)){//查询单位列表（用于新增编辑人员）
			FindCompany(request,response);
		}else if("FindDepart1".equals(action)){//查询二级单位
			FindDepart1(request,response);
		}else if("FindDepart2".equals(action)){//查询三级单位
			FindDepart2(request,response);
		}else if("FindDepart3".equals(action)){//查询四级单位
			FindDepart3(request,response);
		}else if("getMyAccount".equals(action)){//进入我的账户
			getMyAccount(request,response);
		}else if("editMyAccount".equals(action)){//进入我的账户——编辑页面
			editMyAccount(request,response);
		}else if("saveMyAccount".equals(action)){//进入我的账户—— 保存页面
			saveMyAccount(request,response);
		}else if("editPassWord".equals(action)){//进入我的账户—— 修改密码
			editPassWord(request,response);
		}else if("getThreeSelectFree".equals(action)){//打开人员选择框（不固定返回栏）
			getThreeSelectFree(request,response);
		}else if("getLeftSelect".equals(action)){//查询左选择栏
			getLeftSelect(request,response);
		}else if("fastChoose".equals(action)){//快捷选择信息员等
			fastChoose(request,response);
		}
	}
	
	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNodezzxx().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_zzxx");
			ArrayList<ContentZzxx> zzxxList = new ArrayList<ContentZzxx>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("zzxxList", zzxxList);
			request.getRequestDispatcher("/jsp/BasicInformation/zzxx.jsp").forward(request,
					response);
		}
	}
	/*
	 *  查询内容
	 * 
	 */

	protected void query(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//通过菜单项的ID号查询
		String MenuId = request.getParameter("MenuId");
		//通过条件查询
		String srbt = request.getParameter("srbt");

		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
		}else if(UserPer.getContentzzxx().indexOf("1")==-1){
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
			/*设置查询条件*/
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			//存储查询结果
			ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
			//通过左侧树查询
			if(!"".equals(MenuId)&&MenuId!=null){
				//获取菜单ID号列表
				MenuDao menuDao =new MenuDao();
				String idList = menuDao.QueryMenuById("node_zzxx", MenuId);
				if(!"".equals(idList)&&idList!=null){
					countAll = contentZzxxDao.queryZzxxByIdListCount(idList);
					ZzxxList = contentZzxxDao.queryZzxxByIdList(idList, begin, pageSize);
				}
			}
			//通过标题内容查询
			else {
				countAll = contentZzxxDao.queryZzxxByBtCount(srbt);
				ZzxxList = contentZzxxDao.queryZzxxByBt(srbt, begin, pageSize);
				flag = "1";
			}
			//获取当前用户
	//		String username = (String) request.getSession().getAttribute("adminUsername");
			//获取用户权限
	//		PermissionsDao permissionsDao= new PermissionsDao();
	//		String permissions = permissionsDao.QueryPermissionsById("flfg", username);
	//		request.setAttribute("permissions", permissions);
			
	
			request.setAttribute("ZzxxList", ZzxxList);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("flag", flag);
			
			request.setAttribute("MenuId", MenuId);//前台页面左侧树ID
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
			request.getRequestDispatcher("/jsp/BasicInformation/zzxxList.jsp").forward(request,
					response);
		}
	}

	/*
	 * AJAX
	 * 查询单位列表（用于条件查询）
	 */
	protected void CXCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany("0");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"CXCompanyName(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=请选择=</option>");
		out.print("<option value=''>所有单位</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
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
	/*
	 *  新增单位或部门
	 * 
	 */
	protected void insertMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String ParentID = request.getParameter("ParentID");
		String result= "新增成功";
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
			menuDao.InsertMenu("node_zzxx", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),description);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getCompany&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 * 修改部门
	 * 
	 */
	protected void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
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
			menuDao.UpdateMenuNameById("node_zzxx", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),description);
			//修改个人信息中相应的单位或部门名称
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			contentZzxxDao.updateZzxxCompany1(id, name);
			contentZzxxDao.updateZzxxCompany2(id, name);
			contentZzxxDao.updateZzxxCompany3(id, name);
			contentZzxxDao.updateZzxxCompany4(id, name);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getCompany&result="+result);
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
		String idList = menuDao.QueryMenuById("node_zzxx", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_zzxx", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_zzxx", Integer.parseInt(id));
				result = "删除成功";
			}else{
				result = "无法删除！（请先删除子项内容）";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getCompany&result="+result);
		rd.forward(request, response);
		return ;
	}

	/*
	 *  进入查看人员详细信息页面
	 * 
	 */
	protected void showPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ID
		String id = request.getParameter("id");
		
		/*设置查询条件*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//存储查询结果
		ContentZzxx zzxx = new ContentZzxx();
		zzxx = contentZzxxDao.queryZzxxByID(Integer.parseInt(id));
		
		request.setAttribute("zzxx", zzxx);
		request.getRequestDispatcher("/jsp/BasicInformation/showPerson.jsp").forward(request,
				response);

	}
	/*
	 *  新增人员
	 * 
	 */
	protected void insertPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String education = request.getParameter("education");
		String birth = request.getParameter("birth");
		String pcard = request.getParameter("pcard");
		String school = request.getParameter("school");
		String worktime = request.getParameter("worktime");
		String workphone = request.getParameter("workphone");
		String joblevel = request.getParameter("joblevel");
		String phone = request.getParameter("phone");
		String company = request.getParameter("company");
		String companyID = request.getParameter("companyID");
		String depart1 = request.getParameter("depart1");
		String departID1 = request.getParameter("departID1");
		String depart2 = request.getParameter("depart2");
		String departID2 = request.getParameter("departID2");
		String depart3 = request.getParameter("depart3");
		String departID3 = request.getParameter("departID3");
		String job = request.getParameter("job");
		String jobdes = request.getParameter("jobdes");
		String roles = request.getParameter("roles");
		String username = request.getParameter("user");
		ContentZzxx Zzxx = new ContentZzxx();
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			Zzxx.setCzr(UserInfo.getName());
			Zzxx.setCzrID(UserInfo.getUsername());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Zzxx.setCzsj(data1);
			Zzxx.setName(name);
			Zzxx.setSex(sex);
			Zzxx.setEducation(education);
			Zzxx.setPcard(pcard);
			Zzxx.setSchool(school);
			Zzxx.setWorkphone(workphone);
			Zzxx.setJoblevel(joblevel);
			Zzxx.setBirth(DateFormat(birth));
			Zzxx.setWorktime(DateFormat(worktime));
			Zzxx.setPhone(phone);
			Zzxx.setJob(job);
			Zzxx.setJobdes(jobdes);
			Zzxx.setUsername(username);
			Zzxx.setPassword("111111");
			//照片默认路径
			if("男".equals(sex)){
				Zzxx.setFileURL("BasicInformation/zp1.jpg");
			}else{
				Zzxx.setFileURL("BasicInformation/zp2.jpg");
			}
			Zzxx.setRoles(roles);
			Zzxx.setCompany(company);
			Zzxx.setCompanyID(companyID);
			Zzxx.setDepart1(depart1);
			Zzxx.setDepartID1(departID1);
			Zzxx.setDepart2(depart2);
			Zzxx.setDepartID2(departID2);
			Zzxx.setDepart3(depart3);
			Zzxx.setDepartID3(departID3);
			String MenuID = "";
			if(!"".equals(departID3)&&departID3!=null){
				MenuID = departID3;
			}else if(!"".equals(departID2)&&departID2!=null){
				MenuID = departID2;
			}else{
				MenuID = departID1;
			}
			Zzxx.setFatherid(MenuID);
			//查询已有的序号数目
			MenuDao menuDao = new MenuDao();
			int sortnum = menuDao.QueryCountById("content_zzxx", MenuID);
			Zzxx.setSortnum(sortnum+1);
			//新增至数据库
			ContentZzxxDao ContentZzxxDao = new ContentZzxxDao();
			int InsertID = ContentZzxxDao.insertZzxx(Zzxx);
			menuDao.InsertMenuContextById("node_zzxx", MenuID, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
			
			//新增权限
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(roles);
			permissions.setUsername(username);
			permissions.setCzr(UserInfo.getName());
			permissions.setCzrID(UserInfo.getUsername());
			permissions.setCzsj(data1);
			permissionsDao.insertPermissions(permissions);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=showPerson&id="+InsertID);
			rd.forward(request, response);
		}
	}
	/*
	 *  进入编辑页面
	 * 
	 */
	protected void editPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ID
		String id = request.getParameter("id");
		
		/*设置查询条件*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//存储查询结果
		ContentZzxx zzxx = new ContentZzxx();
		zzxx = contentZzxxDao.queryZzxxByID(Integer.parseInt(id));
		
		request.setAttribute("zzxx", zzxx);
		request.getRequestDispatcher("/jsp/BasicInformation/editPerson.jsp").forward(request,
				response);

	}
	/*
	 *  更新人员信息（管理员或者信息员）
	 * 
	 */
	protected void updatePerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String personid = request.getParameter("personid");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String education = request.getParameter("education");
		String birth = request.getParameter("birth");
		String pcard = request.getParameter("pcard");
		String school = request.getParameter("school");
		String worktime = request.getParameter("worktime");
		String workphone = request.getParameter("workphone");
		String joblevel = request.getParameter("joblevel");
		String phone = request.getParameter("phone");
		String company = request.getParameter("company");
		String companyID = request.getParameter("companyID");
		String depart1 = request.getParameter("depart1");
		String departID1 = request.getParameter("departID1");
		String depart2 = request.getParameter("depart2");
		String departID2 = request.getParameter("departID2");
		String depart3 = request.getParameter("depart3");
		String departID3 = request.getParameter("departID3");
		String job = request.getParameter("job");
		String jobdes = request.getParameter("jobdes");
		String roles = request.getParameter("roles");
		String username = request.getParameter("user");
		String fatherid = request.getParameter("fatherid");
		ContentZzxx Zzxx = new ContentZzxx();
		Zzxx.setId(Integer.parseInt(personid));
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			Zzxx.setCzr(UserInfo.getName());
			Zzxx.setCzrID(UserInfo.getUsername());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Zzxx.setCzsj(data1);
			Zzxx.setName(name);
			Zzxx.setSex(sex);
			Zzxx.setEducation(education);
			Zzxx.setPcard(pcard);
			Zzxx.setSchool(school);
			Zzxx.setWorkphone(workphone);
			Zzxx.setJoblevel(joblevel);
			Zzxx.setBirth(DateFormat(birth));
			Zzxx.setWorktime(DateFormat(worktime));
			Zzxx.setPhone(phone);
			Zzxx.setJob(job);
			Zzxx.setJobdes(jobdes);
			Zzxx.setUsername(username);
			Zzxx.setRoles(roles);
			Zzxx.setCompany(company);
			Zzxx.setCompanyID(companyID);
			Zzxx.setDepart1(depart1);
			Zzxx.setDepartID1(departID1);
			Zzxx.setDepart2(depart2);
			Zzxx.setDepartID2(departID2);
			Zzxx.setDepart3(depart3);
			Zzxx.setDepartID3(departID3);
			String MenuID = "";
			if(!"".equals(departID3)&&departID3!=null){
				MenuID = departID3;
			}else if(!"".equals(departID2)&&departID2!=null){
				MenuID = departID2;
			}else{
				MenuID = departID1;
			}
			Zzxx.setFatherid(MenuID);
			ContentZzxxDao ContentZzxxDao = new ContentZzxxDao();
			//若修改单位和部门
			if(!fatherid.equals(MenuID)){
				//查询已有的序号数目
				MenuDao menuDao = new MenuDao();
				int sortnum = menuDao.QueryCountById("content_zzxx", MenuID);
				Zzxx.setSortnum(sortnum+1);
				//新增至新部门
				menuDao.InsertMenuContextById("node_zzxx", MenuID, Integer.parseInt(personid),data1,UserInfo.getName(),UserInfo.getUsername());
				//更新原部门
				menuDao.UpdateMenuContextById("node_zzxx", fatherid, personid,data1,UserInfo.getName(),UserInfo.getUsername());
			}
			ContentZzxxDao.updateZzxx(Zzxx);
			
			//更新权限
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(roles);
			permissions.setUsername(username);
			permissions.setCzr(UserInfo.getName());
			permissions.setCzrID(UserInfo.getUsername());
			permissions.setCzsj(data1);
			permissionsDao.updatePermissions(permissions);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=showPerson&id="+personid);
			rd.forward(request, response);
		}
	}


	/*
	 *  删除内容
	 * 
	 */
	protected void deletePerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		// TODO Auto-generated method stub
		String ContendId = request.getParameter("id");
		String fatherid = request.getParameter("fatherid");
		String user = request.getParameter("user");
		
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
			//删除法律法规内容栏
			if(ContentZzxxDao.DeleteZzxxById(Integer.parseInt(ContendId)) > 0){
				//更新菜单栏存储项
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_zzxx", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//删除权限
				PermissionsDao permissionsDao = new PermissionsDao();
				permissionsDao.DeletePermissionsByUsername(user);
				//返回处理结果
				request.setAttribute("result", "信息删除成功!");
			} else {
				request.setAttribute("result", "信息删除失败!");
			}
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=query&MenuId="+fatherid);
			rd.forward(request, response);
		}
	}
	
		/*
		 *  排列顺序
		 * 
		 */
		protected void sortNum(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//获取删除项的ID号和菜单项ID
			// TODO Auto-generated method stub
			String sortList = request.getParameter("sortList");
			String idList = request.getParameter("idList");
			String fatherid = request.getParameter("fatherid");
			//获取当前用户
			HttpSession session = request.getSession(); 
			ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");

			if(UserInfo==null||"".equals(UserInfo)){
				response.setContentType("text/html;charset=gb2312");
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
				
			}else{
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				String[] sortStr = sortList.split(",");
				String[] idSrt = idList.split(",");
				for(int i=0; i<sortStr.length; i++){ 
					contentZzxxDao.updateZzxxSort(Integer.parseInt(idSrt[i]),Integer.parseInt(sortStr[i]));
				}
				RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=query&MenuId="+fatherid);
				rd.forward(request, response);
			}
		}
	/*
	 * AJAX
	 * 判断用户是否存在
	 */
	protected void FindSameUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String jg = "true";
		boolean result =false;
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		
		result= contentZzxxDao.FindSameUser(user);
		if(result){
			jg = "用户名已存在";
		}
		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		
		out.print(jg);
		  
		out.flush();  
		out.close();
		
	}

	/*
	 * AJAX
	 * 查询一级单位列表
	 */
	protected void FindCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseCompany(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=请选择=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}

	/*
	 * AJAX
	 * 查询二级单位列表
	 */
	protected void FindDepart1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseDepart1(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=请选择=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}
	/*
	 * AJAX
	 * 查询三级单位列表
	 */
	protected void FindDepart2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseDepart2(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=请选择=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}
	/*
	 * AJAX
	 * 查询四级单位列表
	 */
	protected void FindDepart3(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseDepart3(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=请选择=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}
	/*
	 *  查询账户
	 * 
	 */
	protected void getMyAccount(HttpServletRequest request,
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
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = contentZzxxDao.queryZzxxByID(UserInfo.getId());
			
			request.setAttribute("Zzxx", Zzxx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/BasicInformation/showMyAccount.jsp").forward(request,
					response);
		}
	}
	/*
	 *  编辑账户
	 * 
	 */
	protected void editMyAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = contentZzxxDao.queryZzxxByID(UserInfo.getId());
			
			request.setAttribute("Zzxx", Zzxx);
			request.getRequestDispatcher("/jsp/BasicInformation/editMyAccount.jsp").forward(request,
					response);
		}
	}

	/*
	 *  保存账户
	 * 
	 */
	protected void saveMyAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ZHID = request.getParameter("ZHID");
		String birth = request.getParameter("birth");
		String pcard = request.getParameter("pcard");
		String education = request.getParameter("education");
		String school = request.getParameter("school");
		String worktime = request.getParameter("worktime");
		String phone = request.getParameter("phone");
		String workphone = request.getParameter("workphone");
		String job = request.getParameter("job");
		String jobdes = request.getParameter("jobdes");
		String FileURL = request.getParameter("FileURL");
		String TempURL = request.getParameter("TempURL");
		
		String result= "修改成功";
		ContentZzxx Zzxx = new ContentZzxx();
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");

		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			Zzxx.setCzr(UserInfo.getName());
			Zzxx.setCzrID(UserInfo.getUsername());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Zzxx.setCzsj(data1);
			Zzxx.setId(Integer.parseInt(ZHID));
			Zzxx.setEducation(education);
			Zzxx.setPcard(pcard);
			Zzxx.setSchool(school);
			Zzxx.setWorkphone(workphone);
			Zzxx.setBirth(DateFormat(birth));
			Zzxx.setWorktime(DateFormat(worktime));
			Zzxx.setPhone(phone);
			Zzxx.setJob(job);
			Zzxx.setJobdes(jobdes);
			String FullFilePath = request.getRealPath("/UserFile");
			if(!"".equals(TempURL)&&TempURL!=null){
				if(!"".equals(FileURL)&&FileURL!=null){
					delFile(FullFilePath+"/"+FileURL);
				}
				FileURL = TempURL.replaceAll("temp", "photo");
				copyFile(FullFilePath+"/"+TempURL, FullFilePath+"/"+FileURL);   
		        delFile(FullFilePath+"/"+TempURL);   
			}
			Zzxx.setFileURL(FileURL);
			ContentZzxxDao ContentZzxxDao = new ContentZzxxDao();
			ContentZzxxDao.updateMyAccount(Zzxx);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getMyAccount&result="+result);
			rd.forward(request, response);
		}
	}

	public void copyFile(String oldPath, String newPath) {   
        try {   
            int bytesum = 0;   
            int byteread = 0;   
            File oldfile = new File(oldPath);   
            if (oldfile.exists()) { // 文件存在时   
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件   
                FileOutputStream fs = new FileOutputStream(newPath);   
                byte[] buffer = new byte[1024];   
                while ((byteread = inStream.read(buffer)) != -1) {   
                    bytesum += byteread; // 字节数 文件大小   
                    fs.write(buffer, 0, byteread);   
                }   
                inStream.close();   
            }   
        } catch (Exception e) {   
            System.out.println("复制单个文件操作出错 ");   
            e.printStackTrace();   
  
        }   
  
    }  
	 public void delFile(String filePathAndName) {   
         try {   
             String filePath = filePathAndName;   
             filePath = filePath.toString();   
             java.io.File myDelFile = new java.io.File(filePath);   
             myDelFile.delete();   
   
         } catch (Exception e) {   
             System.out.println("删除文件操作出错 ");   
             e.printStackTrace();   
   
         }   
     }   
	/*
	 *  修改密码
	 */
	protected void editPassWord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String oldPS = request.getParameter("oldPS");
		String newPS = request.getParameter("newPS");
		String result= "";
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			String PS = contentZzxxDao.queryPWByID(UserInfo.getId());
			if(PS.equals(oldPS)){
				contentZzxxDao.updatePWByID(newPS, UserInfo.getId());
				result= "修改成功";
				request.getRequestDispatcher("ZzxxServlet?action=getMyAccount&result="+result).forward(request,
						response);
			}else{
				result= "原密码输入不正确";
				request.getRequestDispatcher("/jsp/BasicInformation/editPassWord.jsp?result="+result).forward(request,
						response);
			}
		}
	}
	/*
	 *  新建菜单——用于添加人员（不固定返回栏）
	 * 
	 */
	protected void getThreeSelectFree(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String backname = request.getParameter("name");
		String backid = request.getParameter("id");
		MenuDao menuDao = new MenuDao();
		// List
		String str="";
		str = menuDao.QueryAllMenuInfo("node_zzxx");
		request.setAttribute("menuList", str);
		request.setAttribute("backid", backid);
		request.setAttribute("backname", backname);
		request.getRequestDispatcher("/jsp/ThreeSelectFree.jsp").forward(request,
				response);
		return;
	}
	/*
	 * 查询左选择栏
	 * 
	 */

	protected void getLeftSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//通过菜单项的ID号查询
		String MenuId = request.getParameter("MenuId");
//		String pid = request.getParameter("pid");
		/*设置查询条件*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//存储查询结果
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		//查询信息员
//		if("0".equals(pid)){
//			ZzxxList = contentZzxxDao.queryXxyByCompanyID(MenuId);
//			for(int i=0;i<ZzxxList.size();i++)
//			{	
//				out.print("<option value='"+ZzxxList.get(i).getUsername()+"'>"+"信息员"+ZzxxList.get(i).getName()+"</option>");						 
//			} 
//		}
		//获取菜单ID号列表
		MenuDao menuDao =new MenuDao();
		String idList = menuDao.QueryMenuById("node_zzxx", MenuId);
		if(!"".equals(idList)&&idList!=null){
			ZzxxList = contentZzxxDao.queryZzxxByIdListALL(idList);
			for(int i=0;i<ZzxxList.size();i++)
			{	
				out.print("<option value='"+ZzxxList.get(i).getUsername()+"'>"+ZzxxList.get(i).getName()+"</option>");						 
			} 
		}
		out.flush();  
		out.close();
	}
	/*
	 * 快捷选择信息员等
	 * 
	 */

	protected void fastChoose(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//通过菜单项的ID号查询
		String roles = request.getParameter("val");
		/*设置查询条件*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//存储查询结果
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		//获取菜单ID号列表
		MenuDao menuDao =new MenuDao();
		if(!"".equals(roles)&&roles!=null){
			ZzxxList = contentZzxxDao.queryZzxxByRoles(roles);
			for(int i=0;i<ZzxxList.size();i++)
			{	
				out.print("<option value='"+ZzxxList.get(i).getUsername()+"'>"+ZzxxList.get(i).getName()+"</option>");						 
			} 
		}
		out.flush();  
		out.close();
	}
}
