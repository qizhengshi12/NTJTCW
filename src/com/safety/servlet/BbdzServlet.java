package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.safety.dao.BbdzDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.Bbdz;
import com.safety.entity.Bbdzmb;
import com.safety.entity.BbdzmbHb;
import com.safety.entity.BbdzmbJs;
import com.safety.entity.CglibBean;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class BbdzServlet  extends HttpServlet{
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
		if("getBbdzmb".equals(action)){//报表定制模板列表
			getBbdzmb(request,response);
		}else if("editBbdzmb".equals(action)){//报表定制模板——新增或修改
			editBbdzmb(request,response);
		}else if("deleteBbdzmb".equals(action)){//报表定制模板——删除
			deleteBbdzmb(request,response);
		}else if("saveBbdzmb".equals(action)){//报表定制模板——保存
			saveBbdzmb(request,response);
		}else if("showBbdzmb".equals(action)){//报表定制模板——查看
			showBbdzmb(request,response);
		}else if("readBbdzmbExcel".equals(action)){//报表定制模板——解析EXCEL
			readBbdzmbExcel(request,response);
		}else if("getBbdzmbExcel".equals(action)){//报表定制模板——导出EXCEL
			getBbdzmbExcel(request,response);
		}else if("getBbdz".equals(action)){//根据模板所新建内容列表
			getBbdz(request,response);
		}else if("editBbdz".equals(action)){//根据模板新建报表——新增或修改
			editBbdz(request,response);
		}else if("saveBbdz".equals(action)){//根据模板新建报表——保存
			saveBbdz(request,response);
		}else if("deleteBbdz".equals(action)){//根据模板新建报表——删除
			deleteBbdz(request,response);
		}else if("showBbdz".equals(action)){//根据模板新建报表——查看
			showBbdz(request,response);
		}else if("readBbdzExcel".equals(action)){//根据模板新建报表——解析EXCEL
			readBbdzExcel(request,response);
		}else if("getBbdzExcel".equals(action)){//根据模板新建报表——导出EXCEL
			getBbdzExcel(request,response);
		}else if("returnBbdz".equals(action)){//根据模板新建报表——驳回
			returnBbdz(request,response);
		}
	}

	
	
	/*
	 * 报表定制模板列表
	 * 
	 */
	protected void getBbdzmb(HttpServletRequest request,
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
			
		}else if(UserPer.getBbdz().indexOf("1")==-1){//报表定制权限
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//列表
			ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbdzDao bbdzDao = new BbdzDao();
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
			String cxlx = request.getParameter("cxlx");
//			String cxssrq1 = request.getParameter("cxssrq1");
//			String cxssrq2 = request.getParameter("cxssrq2");
			//若从菜单进入
			if("".equals(srbt)||srbt==null){
				//通过标题查询
				cxbt = "";
//				cxssrq1 = "";
//				cxssrq2 = "";
				srbt= " where lx ='"+cxlx+"'";
			}
//			else{
//				srbt= srbt + " and fszt='1' ";
//			}
			BbdzmbList = bbdzDao.queryBbdzmbByBt(srbt, begin, pageSize);
			countAll = bbdzDao.queryBbdzmbByBtCount(srbt);
			request.setAttribute("BbdzmbList", BbdzmbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			request.setAttribute("cxlx", cxlx);
//			request.setAttribute("cxssrq1", cxssrq1);
//			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/CustomReports/getBbdzmb.jsp").forward(request,
					response);
		}
	}
	/*
	 *  报表定制模板——新增或修改
	 * 
	 */
	protected void editBbdzmb(HttpServletRequest request,
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
			String dzls = request.getParameter("dzls");//报表列数
			String lx = request.getParameter("lx");
			String result = request.getParameter("result");
			BbdzDao bbdzDao = new BbdzDao();
			
			Bbdzmb bbdzmb = new Bbdzmb();
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
			if(!"".equals(id)&&id!=null){
				bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(id));
				//合并项列表
				BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(id));
				//计算项列表
				BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(id));
				//子项目列表
				BbdzmbDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(id),Integer.parseInt(dzls));
			}else{
				//查询是否有相应行数的列表
				boolean res1 = bbdzDao.queryBbdzmbLs(Integer.parseInt(dzls));
				if(!res1){
					//新增相应列数的列表（模板）
					bbdzDao.createBbdzmbByDzls(Integer.parseInt(dzls));
				}
				//查询是否有相应行数的列表
				boolean res2 = bbdzDao.queryBbdzmbLsByLx(Integer.parseInt(dzls),lx);
				if(!res2){
					//新增相应行数到已有表数据，避免下次新建表时，重复操作
					bbdzDao.insertBbdzmbLs(Integer.parseInt(dzls),lx);
					//新增相应列数的列表（存储表）
					bbdzDao.createBbdzByDzls(Integer.parseInt(dzls),lx);
				}
			}
			request.setAttribute("bbdzmb", bbdzmb);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzmbDTList", BbdzmbDTList);
			request.setAttribute("dzls", dzls);
			request.setAttribute("lx", lx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzmbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  删除
	 * 
	 */
	protected void deleteBbdzmb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String dzls = request.getParameter("dzls");
		String lx = request.getParameter("lx");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			BbdzDao bbdzDao = new BbdzDao();
			if (!"".equals(id)&&id!=null){
				//删除模板表
				bbdzDao.deleteBbdzmbByID(Integer.parseInt(id));
				//删除模板表——详细内容
				bbdzDao.deleteBbdzmbDTByIDList(Integer.parseInt(id),Integer.parseInt(dzls));
				//删除合并项表
				bbdzDao.deleteBbdzmbHbByIDList(Integer.parseInt(id));
				//删除计算项表
				bbdzDao.deleteBbdzmbJsByIDList(Integer.parseInt(id));
			}
			RequestDispatcher rd = request.getRequestDispatcher("BbdzServlet?action=getBbdzmb&cxlx="+lx);
			rd.forward(request, response);
			return;
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void saveBbdzmb(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String lx = request.getParameter("lx");
			String dzls = request.getParameter("dzls");
			Bbdzmb bbdzmb = new Bbdzmb();
			bbdzmb.setBt(bt);
			bbdzmb.setLx(lx);
			int num = Integer.parseInt(dzls);
			bbdzmb.setDzls(num);
			bbdzmb.setCzr(UserInfo.getName());
			bbdzmb.setCzrID(UserInfo.getUsername());
			bbdzmb.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			bbdzmb.setCzsj(data1);
			BbdzDao bbdzDao = new BbdzDao();
			int zbid = 0;
			//新增或修改主表
			if(!"".equals(id)&&id!=null){
				zbid = Integer.parseInt(id);
				bbdzmb.setId(zbid);
				bbdzDao.updateBbdzmb(bbdzmb);
			}else{
				zbid = bbdzDao.insertBbdzmb(bbdzmb);
			}
			//删除原有子项目记录，保存新内容
			if (!"".equals(id)&&id!=null){
				bbdzDao.deleteBbdzmbDTByIDList(zbid,num);
			}
			HashMap propertyMap = new HashMap();
			// 设置类成员属性       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=num; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
//			CglibBean bean = new CglibBean(propertyMap);
			//赋值
			String zb1[]=request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			if(zb1!=null){
				for(int j=0; j<zb1.length; j++){
					CglibBean bean = new CglibBean(propertyMap);
					bean.setValue("zb1", zb1[j]);
					bean.setValue("zbid", zbid);
					BbdzmbDTList.add(bean);
				}
				for(int i=2; i<=num; i++){
					String zb[]=request.getParameterValues("zb"+i); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
					for(int j=0; j<zb.length; j++){
						BbdzmbDTList.get(j).setValue("zb"+i, zb[j]);
					}
				}
				bbdzDao.insertBbdzmbDT(BbdzmbDTList,num);
			}
			//存储行列合并项内容
			String column1[]=request.getParameterValues("column1");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内 
			String row1[]=request.getParameterValues("row1");
			String column2[]=request.getParameterValues("column2");
			String row2[]=request.getParameterValues("row2");
			//删除原有数据，保存新内容
			if (!"".equals(id)&&id!=null){
				bbdzDao.deleteBbdzmbHbByIDList(zbid);
			}
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			if(column1!=null){
				for(int i=0; i<column1.length; i++){
					BbdzmbHb bbdzmbHb = new BbdzmbHb();
					bbdzmbHb.setZbid(zbid);
					if("".equals(column1[i]))column1[i]="-1";
					bbdzmbHb.setColumn1(Integer.parseInt(column1[i]));
					if("".equals(row1[i]))row1[i]="-1";
					bbdzmbHb.setRow1(Integer.parseInt(row1[i]));
					if("".equals(column2[i]))column2[i]="-1";
					bbdzmbHb.setColumn2(Integer.parseInt(column2[i]));
					if("".equals(row2[i]))row2[i]="-1";
					bbdzmbHb.setRow2(Integer.parseInt(row2[i]));
					BbdzmbHbList.add(bbdzmbHb);
				}
				bbdzDao.insertBbdzmbHb(BbdzmbHbList);
			}

			//存储行列计算项内容
			String columnjs1[]=request.getParameterValues("columnjs1");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内 
			String rowjs1[]=request.getParameterValues("rowjs1");
			String columnjs2[]=request.getParameterValues("columnjs2");
			String rowjs2[]=request.getParameterValues("rowjs2");
			String columnjs3[]=request.getParameterValues("columnjs3");
			String rowjs3[]=request.getParameterValues("rowjs3");
			String jslx[]=request.getParameterValues("jslx");
			String jsfh[]=request.getParameterValues("jsfh");
			//删除原有数据，保存新内容
			if (!"".equals(id)&&id!=null){
				bbdzDao.deleteBbdzmbJsByIDList(zbid);
			}
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			if(columnjs1!=null){
				for(int i=0; i<columnjs1.length; i++){
					BbdzmbJs bbdzmbJs = new BbdzmbJs();
					bbdzmbJs.setZbid(zbid);
					if("".equals(columnjs1[i]))columnjs1[i]="-1";
					bbdzmbJs.setColumn1(Integer.parseInt(columnjs1[i]));
					if("".equals(rowjs1[i]))rowjs1[i]="-1";
					bbdzmbJs.setRow1(Integer.parseInt(rowjs1[i]));
					if("".equals(columnjs2[i]))columnjs2[i]="-1";
					bbdzmbJs.setColumn2(Integer.parseInt(columnjs2[i]));
					if("".equals(rowjs2[i]))rowjs2[i]="-1";
					bbdzmbJs.setRow2(Integer.parseInt(rowjs2[i]));
					if("".equals(columnjs3[i]))columnjs3[i]="-1";
					bbdzmbJs.setColumn3(Integer.parseInt(columnjs3[i]));
					if("".equals(rowjs3[i]))rowjs3[i]="-1";
					bbdzmbJs.setRow3(Integer.parseInt(rowjs3[i]));
					bbdzmbJs.setLx(jslx[i]);
					bbdzmbJs.setFh(jsfh[i]);
					BbdzmbJsList.add(bbdzmbJs);
				}
				bbdzDao.insertBbdzmbJs(BbdzmbJsList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbdzServlet?action=getBbdzmb&cxlx="+lx).forward(request,
					response);
			
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void showBbdzmb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String dzls = request.getParameter("dzls");
		String result = request.getParameter("result");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
		Bbdzmb bbdzmb = new Bbdzmb();
		if(!"".equals(id)&&id!=null){
			bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(id));
			//合并项列表
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(id));
			//计算项列表
			BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(id));
			//子项目列表
			BbdzmbDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(id),Integer.parseInt(dzls));
		}
		//分析合并情况
		int r1 = 0;
		int c1 = 0;
		int r2 = 0;
		int c2 = 0;
		for(int i=0; i<BbdzmbHbList.size(); i++){
			r1 = BbdzmbHbList.get(i).getRow1();
			c1 = BbdzmbHbList.get(i).getColumn1();
			r2 = BbdzmbHbList.get(i).getRow2();
			c2 = BbdzmbHbList.get(i).getColumn2();
			//合并行
			if(c1==c2){
				BbdzmbHbList.get(i).setHl("r");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
			}
			//合并列
			else if(r1==r2){
				BbdzmbHbList.get(i).setHl("c");
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
			//合并行列
			else{
				BbdzmbHbList.get(i).setHl("rc");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
		}
		request.setAttribute("bbdzmb", bbdzmb);
		request.setAttribute("BbdzmbHbList", BbdzmbHbList);
		request.setAttribute("BbdzmbJsList", BbdzmbJsList);
		request.setAttribute("BbdzmbDTList", BbdzmbDTList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/CustomReports/bbdzmbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readBbdzmbExcel(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String lx = request.getParameter("lx");
			String dzls = request.getParameter("dzls");
			Bbdzmb bbdzmb = new Bbdzmb();
			bbdzmb.setBt(bt);
			bbdzmb.setLx(lx);
			int lc = Integer.parseInt(dzls);
			bbdzmb.setDzls(lc);
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
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=lc; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
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
					if(!"".equals(id)&&id!=null){
						bbdzmb.setId(Integer.parseInt(id));
					}
					Range[] ranges = sheet[StNm].getMergedCells(); 
				    for(Range space:ranges){
				    	BbdzmbHb bbdzmbHb = new BbdzmbHb();
				    	bbdzmbHb.setRow1(space.getTopLeft().getRow()+1);
				    	bbdzmbHb.setColumn1(space.getTopLeft().getColumn()+1);
				    	bbdzmbHb.setRow2(space.getBottomRight().getRow()+1);
				    	bbdzmbHb.setColumn2(space.getBottomRight().getColumn()+1);
				    	BbdzmbHbList.add(bbdzmbHb);
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
						BbdzmbDTList.add(bean);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("bbdzmb", bbdzmb);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzmbDTList", BbdzmbDTList);
			request.setAttribute("dzls", dzls);
			request.setAttribute("lx", lx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzmbEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getBbdzmbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
		Bbdzmb bbdzmb = new Bbdzmb();
		if(!"".equals(exc_id)&&exc_id!=null){
			bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(exc_id));
			//合并项列表
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(exc_id));
			//计算项列表
			BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(exc_id));
			//子项目列表
			BbdzmbDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(exc_id),bbdzmb.getDzls());
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
				WritableSheet wsheet = wwb.createSheet(bbdzmb.getBt(),1);
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

				Label label = new Label(0,0,bbdzmb.getBt(),format1);
				wsheet.addCell(label);
				int num = bbdzmb.getDzls();
				for(int i=0; i<BbdzmbDTList.size(); i++){
					for(int j=1; j<=num; j++){
						label = new Label(j-1,i+1,BbdzmbDTList.get(i).getValue("zb"+j)+"",format2);
						wsheet.addCell(label);
					}
				}
				//合并单元格
				wsheet.mergeCells(0, 0, num-1, 0);
				for(int k=0; k<BbdzmbHbList.size(); k++){
					//因为存在标题，行数需要加1
					//又因为数据库存储的是行列数（从1开始），程序设置是序列数（从0开始），所以行数和列数都减1
					//综上所述，行数不变，列数减1
					wsheet.mergeCells(BbdzmbHbList.get(k).getColumn1()-1, BbdzmbHbList.get(k).getRow1(), BbdzmbHbList.get(k).getColumn2()-1, BbdzmbHbList.get(k).getRow2());
				}
				//设置列宽
				for(int n=0; n<num; n++){
					wsheet.setColumnView(n, 20);
				}
				//设置行宽
				wsheet.setRowView(0, 680, false);
				for(int k=0; k<BbdzmbHbList.size(); k++){
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

	/*
	 * 根据模板所新建内容列表
	 * 
	 */
	protected void getBbdz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//新建内容的类型
		String lx = request.getParameter("lx");
		//模板ID
		String zbid = request.getParameter("zbid");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		//权限
		String qx = "";
		if("cwbb".equals(lx)){
			qx = UserPer.getCwbb();
		}else if("sjbb".equals(lx)){
			qx = UserPer.getSjbb();
		}else if("tjbb".equals(lx)){
			qx = UserPer.getTjbb();
		}
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(qx.indexOf("1")==-1){//报表定制权限
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//列表
			ArrayList<Bbdz> BbdzList = new ArrayList<Bbdz>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbdzDao bbdzDao = new BbdzDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) and zbid = "+zbid;
				cxtj = "1";
			}
			countAll = bbdzDao.queryBbdzByBtCount(srbt,lx);
			BbdzList = bbdzDao.queryBbdzByBt(srbt, begin, pageSize,lx);
			request.setAttribute("BbdzList", BbdzList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("zbid", zbid);
			request.setAttribute("lx", lx);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/CustomReports/getBbdz.jsp").forward(request,
					response);
		}
	}
	/*
	 *  根据模板新建报表——新增或修改
	 * 
	 */
	protected void editBbdz(HttpServletRequest request,
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
			String id = request.getParameter("id");//报表ID
			String zbid = request.getParameter("zbid");//模板ID
			String lx = request.getParameter("lx");
			String result = request.getParameter("result");
			BbdzDao bbdzDao = new BbdzDao();
			
			Bbdz bbdz = new Bbdz();
			//将标题和列数预设进去
			Bbdzmb bbdzmb = new Bbdzmb();
			bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(zbid));
			bbdz.setBt(bbdzmb.getBt());
			bbdz.setBbls(bbdzmb.getDzls());
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
			if(!"".equals(zbid)&&zbid!=null){
				//合并项列表
				BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
				//计算项列表
				BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(zbid));
				//子项目列表
				BbdzDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(zbid),bbdz.getBbls());
			}
			if(!"".equals(id)&&id!=null){
				bbdz = bbdzDao.queryBbdzById(Integer.parseInt(id),lx);
				//子项目列表
				BbdzDTList =  bbdzDao.queryBbdzDTByIDList(Integer.parseInt(id),bbdz.getBbls(),lx);
			}
			request.setAttribute("bbdz", bbdz);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzDTList", BbdzDTList);
			request.setAttribute("bbls", bbdz.getBbls());//前台获取的时候，必须是int型
			request.setAttribute("zbid", zbid);
			request.setAttribute("lx", lx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void saveBbdz(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String lx = request.getParameter("lx");
			String zbid = request.getParameter("zbid");
			String bbls = request.getParameter("bbls");
			String sj = request.getParameter("sj");
			String tjzt = request.getParameter("tjzt");
			Bbdz bbdz = new Bbdz();
			bbdz.setBt(bt);
			bbdz.setZbid(Integer.parseInt(zbid));
			bbdz.setSj(DateFormat(sj));
			bbdz.setTjzt(tjzt);
			int num = Integer.parseInt(bbls);
			bbdz.setBbls(num);
			bbdz.setCzr(UserInfo.getName());
			bbdz.setCzrID(UserInfo.getUsername());
			bbdz.setCzrdw(UserInfo.getCompany());
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			bbdz.setCzsj(data1);
			BbdzDao bbdzDao = new BbdzDao();
			int ID = 0;
			//新增或修改主表
			if(!"".equals(id)&&id!=null){
				ID = Integer.parseInt(id);
				bbdz.setId(ID);
				bbdzDao.updateBbdz(bbdz,lx);
			}else{
				ID = bbdzDao.insertBbdz(bbdz,lx);
			}
			//删除原有子项目记录，保存新内容
			bbdzDao.deleteBbdzDTByIDList(ID,num,lx);
			HashMap propertyMap = new HashMap();
			// 设置类成员属性       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=num; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
//			CglibBean bean = new CglibBean(propertyMap);
			//赋值
			String zb1[]=request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			if(zb1!=null){
				for(int j=0; j<zb1.length; j++){
					CglibBean bean = new CglibBean(propertyMap);
					bean.setValue("zb1", zb1[j]);
					bean.setValue("zbid", ID);
					BbdzDTList.add(bean);
				}
				for(int i=2; i<=num; i++){
					String zb[]=request.getParameterValues("zb"+i); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
					for(int j=0; j<zb.length; j++){
						BbdzDTList.get(j).setValue("zb"+i, zb[j]);
					}
				}
				bbdzDao.insertBbdzDT(BbdzDTList,num,lx);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/*
	 *  删除
	 * 
	 */
	protected void deleteBbdz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		String zbid = request.getParameter("zbid");
		String lx = request.getParameter("lx");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			BbdzDao bbdzDao = new BbdzDao();
			if (!"".equals(id)&&id!=null){
				//删除主表
				bbdzDao.deleteBbdzByID(Integer.parseInt(id),lx);
				//删除子表
				bbdzDao.deleteBbdzDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls),lx);
			}
			RequestDispatcher rd = request.getRequestDispatcher("BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+UserInfo.getCompany());
			rd.forward(request, response);
			return;
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void showBbdz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		String zbid = request.getParameter("zbid");
		String lx = request.getParameter("lx");
		String result = request.getParameter("result");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		Bbdz bbdz = new Bbdz();
		if(!"".equals(id)&&id!=null){
			bbdz = bbdzDao.queryBbdzById(Integer.parseInt(id),lx);
			//合并项列表
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
			//子项目列表
			BbdzDTList =  bbdzDao.queryBbdzDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls),lx);
		}
		//分析合并情况
		int r1 = 0;
		int c1 = 0;
		int r2 = 0;
		int c2 = 0;
		for(int i=0; i<BbdzmbHbList.size(); i++){
			r1 = BbdzmbHbList.get(i).getRow1();
			c1 = BbdzmbHbList.get(i).getColumn1();
			r2 = BbdzmbHbList.get(i).getRow2();
			c2 = BbdzmbHbList.get(i).getColumn2();
			//合并行
			if(c1==c2){
				BbdzmbHbList.get(i).setHl("r");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
			}
			//合并列
			else if(r1==r2){
				BbdzmbHbList.get(i).setHl("c");
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
			//合并行列
			else{
				BbdzmbHbList.get(i).setHl("rc");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
		}
		request.setAttribute("lx", lx);
		request.setAttribute("bbdz", bbdz);
		request.setAttribute("BbdzmbHbList", BbdzmbHbList);
		request.setAttribute("BbdzDTList", BbdzDTList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/CustomReports/bbdzShow.jsp").forward(request,
				response);
		
		return;
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readBbdzExcel(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String sj = request.getParameter("sj");
			String lx = request.getParameter("lx");
			String bbls = request.getParameter("bbls");
			String zbid = request.getParameter("zbid");
			Bbdz bbdz = new Bbdz();
			bbdz.setBt(bt);
			bbdz.setZbid(Integer.parseInt(zbid));
			bbdz.setSj(DateFormat(sj));
			int lc = Integer.parseInt(bbls);
			bbdz.setBbls(lc);
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
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=lc; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			if(!"".equals(zbid)&&zbid!=null){
				BbdzDao bbdzDao = new BbdzDao();
				//合并项列表
				BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
				//计算项列表
				BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(zbid));
			}
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
					if(!"".equals(id)&&id!=null){
						bbdz.setId(Integer.parseInt(id));
					}
					//第一行标题默认不读入
					for(int i=1;i<r; i++){
						CglibBean bean = new CglibBean(propertyMap);
						for(int j=1; j<=lc; j++){
							bean.setValue("zb"+j, sheet[StNm].getCell(j-1,i).getContents());
						}
						BbdzDTList.add(bean);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("bbdz", bbdz);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzDTList", BbdzDTList);
			request.setAttribute("bbls", lc);//前台获取的时候，必须是int型
			request.setAttribute("lx", lx);
			request.setAttribute("zbid", zbid);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getBbdzExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		String lx = request.getParameter("lx");
		String zbid = request.getParameter("zbid");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		Bbdz bbdz = new Bbdz();
		if(!"".equals(exc_id)&&exc_id!=null){
			bbdz = bbdzDao.queryBbdzById(Integer.parseInt(exc_id),lx);
			//合并项列表
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
			//子项目列表
			BbdzDTList =  bbdzDao.queryBbdzDTByIDList(Integer.parseInt(exc_id),bbdz.getBbls(),lx);
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
				WritableSheet wsheet = wwb.createSheet(bbdz.getBt(),1);
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

				Label label = new Label(0,0,bbdz.getSj()+"  "+bbdz.getBt(),format1);
				wsheet.addCell(label);
				int num = bbdz.getBbls();
				for(int i=0; i<BbdzDTList.size(); i++){
					for(int j=1; j<=num; j++){
						label = new Label(j-1,i+1,BbdzDTList.get(i).getValue("zb"+j)+"",format2);
						wsheet.addCell(label);
					}
				}
				//合并单元格
				wsheet.mergeCells(0, 0, num-1, 0);
				for(int k=0; k<BbdzmbHbList.size(); k++){
					//因为存在标题，行数需要加1
					//又因为数据库存储的是行列数（从1开始），程序设置是序列数（从0开始），所以行数和列数都减1
					//综上所述，行数不变，列数减1
					wsheet.mergeCells(BbdzmbHbList.get(k).getColumn1()-1, BbdzmbHbList.get(k).getRow1(), BbdzmbHbList.get(k).getColumn2()-1, BbdzmbHbList.get(k).getRow2());
				}
				//设置列宽
				for(int n=0; n<num; n++){
					wsheet.setColumnView(n, 20);
				}
				//设置行宽
				wsheet.setRowView(0, 680, false);
				for(int k=0; k<BbdzmbHbList.size(); k++){
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
	/*
	 *  进入驳回
	 * 
	 */
	protected void returnBbdz(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String lx = request.getParameter("lx");
			String zbid = request.getParameter("zbid");
			BbdzDao bbdzDao = new BbdzDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbdzDao.updateBbdzRet(id,lx);
				String dxnr = UserInfo.getName()+"：您有一份资产负债表被驳回，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				//获取当前时间
				java.util.Date  date=new java.util.Date();
				java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			RequestDispatcher rd = request.getRequestDispatcher("BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+UserInfo.getCompany());
			rd.forward(request, response);
			return;
			
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
