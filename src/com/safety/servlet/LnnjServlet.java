package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.safety.dao.LnnjDao;
import com.safety.dao.MenuDao;
import com.safety.entity.CglibBean;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Njfb;
import com.safety.entity.NjfbConsolidations;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class LnnjServlet  extends HttpServlet{
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
		if("getNjfb".equals(action)){//历年年鉴发布列表
			getNjfb(request,response);
		}else if("query".equals(action)){
			queryLnnj(request,response);
		}else if("getNjgl".equals(action)){//历年年鉴管理列表
			getNjgl(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("lnnjEdit".equals(action)){//新增或修改页面（通用）
			LnnjEdit(request,response);
		}else if("lnnjSave".equals(action)){///保存页面（通用）
			LnnjSave(request,response);
		}else if("lnnjShow".equals(action)){//查看（通用）
			LnnjShow(request,response);
		}else if("lnnjDelete".equals(action)){//删除（通用）
			LnnjDelete(request,response);
		}else if("getNjfbExcel".equals(action)){//导出EXCEL（通用）
			getNjfbExcel(request,response);
		}else if("readNjfbExcel".equals(action)){//解析EXCEL（通用）
			readNjfbExcel(request,response);
		}
	}
	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getNjfb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNjfb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_njfb");
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfb.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  历年年鉴列表
	 * 
	 */
	protected void queryLnnj(HttpServletRequest request,
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
			
		}else if(UserPer.getNjfb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('无查询权限！');</script>");
			
		}else{
			//通过菜单项的ID号查询
			String MenuId = request.getParameter("MenuId");
			//通过条件查询
			String srbt = request.getParameter("srbt");
			//查询列表
			ArrayList<Njfb> NjfbList = new ArrayList<Njfb>();
			//分页;
//			String flag = "0";//设置入口标志，用来判断查询方式
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			/*设置查询条件*/
			LnnjDao lnnjDao = new LnnjDao();
			//通过左侧树查询
			if(!"".equals(MenuId)&&MenuId!=null){
				//获取菜单ID号列表
//				MenuDao menuDao =new MenuDao();
//				String idList = menuDao.QueryMenuById("node_njfb", MenuId);
				if(!"".equals(MenuId)&&MenuId!=null){
					countAll = lnnjDao.queryNjfbByIdListCount(MenuId);
					NjfbList = lnnjDao.queryNjfbByIdList(MenuId, begin, pageSize);
				}
			}
			//通过标题内容查询
			else {
				countAll = lnnjDao.queryNjfbByBtCount(srbt);
				NjfbList = lnnjDao.queryNjfbByBt(srbt, begin, pageSize);
//				flag = "1";
			}
			
			request.setAttribute("NjfbList", NjfbList);
//			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("srbt", srbt);//前台页面输入的查询内容
			request.setAttribute("MenuId", MenuId);//前台页面左侧树ID
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
//			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbList.jsp").forward(request,
					response);
		}
	}



	//TODO
	/*
	 *  进入年鉴管理页面
	 * 
	 */
	protected void getNjgl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_njfb");
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njgl.jsp").forward(request,
					response);
		}
	}
	/*
	 *  新增菜单
	 * 
	 */
	protected void insertMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String bbls = request.getParameter("bbls");
		String ParentID = request.getParameter("ParentID");//获取父节点ID
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
			menuDao.InsertMenu("node_njfb", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),bbls);
			if(!"".equals(bbls)&&!"null".equals(bbls)&&bbls!=null){
				LnnjDao lnnjDao = new LnnjDao();
				//查询是否有相应行数的列表
				boolean res = lnnjDao.queryNjfbLs(Integer.parseInt(bbls));
				if(!res){
					//新增相应行数到已有表数据，避免下次新建表时，重复操作
					lnnjDao.insertNjfbLs(Integer.parseInt(bbls));
					//新增相应行数的列表
					lnnjDao.createNjfbByBbls(Integer.parseInt(bbls));
				}
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjgl&result="+result);
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
		String bbls = request.getParameter("bbls");
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
			menuDao.UpdateMenuNameById("node_njfb", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),bbls);
			
			RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjgl&result="+result);
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
		LnnjDao lnnjDao = new LnnjDao();
		int count = 0;
		int countAll = lnnjDao.queryNjfbByIdListCount(id);
		if(countAll==0){
			count = menuDao.QueryCountByFatherId("node_njfb", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_njfb", Integer.parseInt(id));
				result = "删除成功";
			}else{
				result = "无法删除！（请先删除子菜单内容）";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjgl&result="+result);
		rd.forward(request, response);
		return ;
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void LnnjEdit(HttpServletRequest request,
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
			String bbls = request.getParameter("bbls");//报表列数
			String fatherid = request.getParameter("fatherid");
			String result = request.getParameter("result");
			LnnjDao lnnjDao = new LnnjDao();
			
			ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
			Njfb njfb = new Njfb();
			ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
			if(!"".equals(id)&&id!=null){
				njfb = lnnjDao.queryNjfbById(Integer.parseInt(id));
				//合并项列表
				njfbCLList = lnnjDao.queryNjfbCLById(Integer.parseInt(id));
				//子项目列表
				lnnjDTList =  lnnjDao.queryNjfbDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls));
			}
			request.setAttribute("njfb", njfb);
			request.setAttribute("lnnjDTList", lnnjDTList);
			request.setAttribute("njfbCLList", njfbCLList);
			request.setAttribute("bbls", bbls);
			request.setAttribute("fatherid", fatherid);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void LnnjSave(HttpServletRequest request,
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
			String njID = request.getParameter("njID");
			String bt = request.getParameter("bt");
			String year = request.getParameter("year");
			String fatherid = request.getParameter("fatherid");
			String bbls = request.getParameter("bbls");
			Njfb njfb = new Njfb();
			njfb.setBt(bt);
			njfb.setYear(Integer.parseInt(year));
			njfb.setFatherid(fatherid);
			int num = Integer.parseInt(bbls);
			njfb.setBbls(num);
			njfb.setCzr(UserInfo.getName());
			njfb.setCzrID(UserInfo.getUsername());
			njfb.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			njfb.setCzsj(data1);
			LnnjDao lnnjDao = new LnnjDao();
			int xmid = 0;
			//新增或修改主表
			if(!"".equals(njID)&&njID!=null){
				xmid = Integer.parseInt(njID);
				njfb.setId(xmid);
				lnnjDao.updateNjfb(njfb);
			}else{
				xmid = lnnjDao.insertNjfb(njfb);
//				MenuDao menuDao = new MenuDao();
//				menuDao.InsertMenuContextById("node_njfb", fatherid, xmid,data1,UserInfo.getName(),UserInfo.getUsername());
			}
			//删除原有子项目记录，保存新内容
			if (!"".equals(njID)&&njID!=null){
				lnnjDao.deleteNjfbDTByIDList(xmid,num);
			}
			HashMap propertyMap = new HashMap();
			// 设置类成员属性       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("xmid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=num; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
//			CglibBean bean = new CglibBean(propertyMap);
			//赋值
			String zb1[]=request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			if(zb1!=null){
				for(int j=0; j<zb1.length; j++){
					CglibBean bean = new CglibBean(propertyMap);
					bean.setValue("zb1", zb1[j]);
					bean.setValue("xmid", xmid);
					lnnjDTList.add(bean);
				}
				for(int i=2; i<=num; i++){
					String zb[]=request.getParameterValues("zb"+i); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
					for(int j=0; j<zb.length; j++){
						lnnjDTList.get(j).setValue("zb"+i, zb[j]);
					}
				}
	
				lnnjDao.insertNjfbDT(lnnjDTList,num);
			}
			//存储行列合并项内容
			String column1[]=request.getParameterValues("column1");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内 
			String row1[]=request.getParameterValues("row1");
			String column2[]=request.getParameterValues("column2");
			String row2[]=request.getParameterValues("row2");
			//删除原有数据，保存新内容
			if (!"".equals(njID)&&njID!=null){
				lnnjDao.deleteNjfbJTGKByIDList(xmid);
			}
			ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
			if(column1!=null){
				for(int i=0; i<column1.length; i++){
					NjfbConsolidations njfbCL = new NjfbConsolidations();
					njfbCL.setXmid(xmid);
					if("".equals(column1[i]))column1[i]="-1";
					njfbCL.setColumn1(Integer.parseInt(column1[i]));
					if("".equals(row1[i]))row1[i]="-1";
					njfbCL.setRow1(Integer.parseInt(row1[i]));
					if("".equals(column2[i]))column2[i]="-1";
					njfbCL.setColumn2(Integer.parseInt(column2[i]));
					if("".equals(row2[i]))row2[i]="-1";
					njfbCL.setRow2(Integer.parseInt(row2[i]));
					njfbCLList.add(njfbCL);
				}
				lnnjDao.insertNjfbJTGK(njfbCLList);
			}			
			request.setAttribute("result", result);
			request.getRequestDispatcher("LnnjServlet?action=getNjfb").forward(request,
					response);
			
		}
	}


	 /*
	 *  进入查看页面
	 * 
	 */
	protected void LnnjShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		String result = request.getParameter("result");
		LnnjDao lnnjDao = new LnnjDao();
		ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
		ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
		Njfb njfb = new Njfb();
		if(!"".equals(id)&&id!=null){
			njfb = lnnjDao.queryNjfbById(Integer.parseInt(id));
			//合并项列表
			njfbCLList = lnnjDao.queryNjfbCLById(Integer.parseInt(id));
			//子项目列表
			lnnjDTList =  lnnjDao.queryNjfbDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls));
		}
		//分析合并情况
		int r1 = 0;
		int c1 = 0;
		int r2 = 0;
		int c2 = 0;
		for(int i=0; i<njfbCLList.size(); i++){
			r1 = njfbCLList.get(i).getRow1();
			c1 = njfbCLList.get(i).getColumn1();
			r2 = njfbCLList.get(i).getRow2();
			c2 = njfbCLList.get(i).getColumn2();
			//合并行
			if(c1==c2){
				njfbCLList.get(i).setHl("r");
				njfbCLList.get(i).setHs(r2-r1+1);
			}
			//合并列
			else if(r1==r2){
				njfbCLList.get(i).setHl("c");
				njfbCLList.get(i).setLs(c2-c1+1);
			}
			//合并行列
			else{
				njfbCLList.get(i).setHl("rc");
				njfbCLList.get(i).setHs(r2-r1+1);
				njfbCLList.get(i).setLs(c2-c1+1);
			}
		}
		request.setAttribute("njfb", njfb);
		request.setAttribute("lnnjDTList", lnnjDTList);
		request.setAttribute("njfbCLList", njfbCLList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void LnnjDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			LnnjDao lnnjDao = new LnnjDao();
			if (!"".equals(id)&&id!=null){
				lnnjDao.deleteNjfbDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls));
				lnnjDao.deleteNjfbByID(Integer.parseInt(id));
				lnnjDao.deleteNjfbJTGKByIDList(Integer.parseInt(id));
			}
			RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjfb");
			rd.forward(request, response);
			return;
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readNjfbExcel(HttpServletRequest request,
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
			//导入EXCEL位置
			String sheetNum = request.getParameter("sheetNum");
			//新增或者修改
			String njID = request.getParameter("njID");
			String bt = request.getParameter("bt");
			String year = request.getParameter("year");
			String fatherid = request.getParameter("fatherid");
			String bbls = request.getParameter("bbls");
			Njfb njfb = new Njfb();
			njfb.setBt(bt);
			njfb.setYear(Integer.parseInt(year));
			njfb.setFatherid(fatherid);
			int lc = Integer.parseInt(bbls);
			njfb.setBbls(lc);
			String URL = request.getParameter("URL");  
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);   
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}
			HashMap propertyMap = new HashMap();
			// 设置类成员属性       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("xmid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=lc; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
			ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			int StNm = 0;
			if(!"".equals(sheetNum)&&sheetNum!=null){
				StNm = Integer.parseInt(sheetNum)-1;
			}
			if(sheet!=null&&sheet.length>0){
				int r = sheet[StNm].getRows();//行高
				int c = sheet[StNm].getColumns();//列长
				if(c<lc){
					result = "请检查文件是否正确";
				}else{
					if(!"".equals(njID)&&njID!=null){
						njfb.setId(Integer.parseInt(njID));
					}
					Range[] ranges = sheet[StNm].getMergedCells(); 
				    for(Range space:ranges){
				    	NjfbConsolidations njfbCL = new NjfbConsolidations();
				    	njfbCL.setRow1(space.getTopLeft().getRow()+1);
				    	njfbCL.setColumn1(space.getTopLeft().getColumn()+1);
				    	njfbCL.setRow2(space.getBottomRight().getRow()+1);
				    	njfbCL.setColumn2(space.getBottomRight().getColumn()+1);
				    	njfbCLList.add(njfbCL);
				    } 
//				    Range[] ranges = sheet[StNm].getMergedCells(); 
//				    System.out.println("sheet" + StNm + "包含" + ranges.length + "个区域"); 
//				    for(Range space:ranges){
//					    int i = space.getTopLeft().getRow();
//					    int j = space.getTopLeft().getColumn();
//						System.out.print(sheet[StNm].getCell(j,i).getContents()+"\t"); 
//					    System.out.print(space.getTopLeft().getRow()+1+"行,"); 
//					    System.out.print(space.getTopLeft().getColumn()+1+"列\t"); 
//					    System.out.print(space.getBottomRight().getRow()+1+"行,"); 
//					    System.out.print(space.getBottomRight().getColumn()+1+"列\n"); 
//				    } 
				    
					for(int i=0;i<r; i++){
						CglibBean bean = new CglibBean(propertyMap);
						for(int j=1; j<=lc; j++){
							bean.setValue("zb"+j, sheet[StNm].getCell(j-1,i).getContents());
						}
						lnnjDTList.add(bean);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("njfb", njfb);
			request.setAttribute("lnnjDTList", lnnjDTList);
			request.setAttribute("njfbCLList", njfbCLList);
			request.setAttribute("fatherid", fatherid);
			request.setAttribute("result", result);
			request.setAttribute("bbls", bbls);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbEdit.jsp").forward(request,
					response);
		}
	}
	
	/**
	* 生成信息的XLS
	*
	*/
	public void getNjfbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		LnnjDao lnnjDao = new LnnjDao();
		ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
		ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
		Njfb njfb = new Njfb();
		if(!"".equals(exc_id)&&exc_id!=null){
			njfb = lnnjDao.queryNjfbById(Integer.parseInt(exc_id));
			//合并项列表
			njfbCLList = lnnjDao.queryNjfbCLById(Integer.parseInt(exc_id));
			//子项目列表
			lnnjDTList =  lnnjDao.queryNjfbDTByIDList(Integer.parseInt(exc_id),njfb.getBbls());
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet(njfb.getBt(),1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.CENTRE);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				//设置边框
				format2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
	
				
				//设置表头

				Label label = new Label(0,0,njfb.getYear()+"年"+njfb.getBt(),format1);
				wsheet.addCell(label);
				int num = njfb.getBbls();
				for(int i=0; i<lnnjDTList.size(); i++){
					for(int j=1; j<=num; j++){
						label = new Label(j-1,i+1,lnnjDTList.get(i).getValue("zb"+j)+"",format2);
						wsheet.addCell(label);
					}
				}
				//合并单元格
				wsheet.mergeCells(0, 0, num-1, 0);
				for(int k=0; k<njfbCLList.size(); k++){
					//因为存在标题，行数需要加1
					//又因为数据库存储的是行列数（从1开始），程序设置是序列数（从0开始），所以行数和列数都减1
					//综上所述，行数不变，列数减1
					wsheet.mergeCells(njfbCLList.get(k).getColumn1()-1, njfbCLList.get(k).getRow1(), njfbCLList.get(k).getColumn2()-1, njfbCLList.get(k).getRow2());
				}
				//设置列宽
				for(int n=0; n<num; n++){
					wsheet.setColumnView(n, 20);
				}
				//设置行宽
				wsheet.setRowView(0, 680, false);
				for(int k=0; k<lnnjDTList.size(); k++){
					wsheet.setRowView(k+1, 400, false);
				}
				
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	
}
