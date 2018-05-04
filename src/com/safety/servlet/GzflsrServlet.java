package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.safety.dao.GzflsrDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Gzflsr;
import com.safety.entity.GzflsrZxm;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class GzflsrServlet  extends HttpServlet{
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
		if("getGzflsr".equals(action)){//工资福利
			getGzflsr(request,response);
		}else if("resetMenu".equals(action)){//重置菜单
			resetMenu(request,response);
		}else if("getGzflsrList".equals(action)){//工资福利收入——列表
			getGzflsrList(request,response);
		}else if("gzflsrEdit".equals(action)){//工资福利收入——新增或修改
			gzflsrEdit(request,response);
		}else if("gzflsrSave".equals(action)){//工资福利收入——保存页面
			gzflsrSave(request,response);
		}else if("gzflsrShow".equals(action)){//工资福利收入——查看
			gzflsrShow(request,response);
		}else if("gzflsrDelete".equals(action)){//工资福利收入——删除
			gzflsrDelete(request,response);
		}else if("getGzflsrExcel".equals(action)){//工资福利收入——导出EXCEL
			getGzflsrExcel(request,response);
		}else if("readGzflsrExcel".equals(action)){//工资福利收入——解析EXCEL
			readGzflsrExcel(request,response);
		}
	}

	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getGzflsr(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getGzfl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfoName("node_gzflsr");
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/WagesBenefits/getGzflsr.jsp").forward(request,
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
			menuDao.DeleteMenuName("node_gzflsr");
			menuDao.ResetZzxxMenu("node_gzflsr",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("GzflsrServlet?action=getGzflsr&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  工资福利收入
	 * 
	 */
	protected void getGzflsrList(HttpServletRequest request,
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
			//查询列表
			ArrayList<Gzflsr> GzflsrList = new ArrayList<Gzflsr>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			GzflsrDao gzflsrDao = new GzflsrDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
			String cxcompany = request.getParameter("cxcompany");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt='2' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = gzflsrDao.queryGzflsrListByBtCount(srbt);
			GzflsrList = gzflsrDao.queryGzflsrListByBt(srbt, begin, pageSize);
			request.setAttribute("GzflsrList", GzflsrList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("cxmonth", cxmonth);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/WagesBenefits/gzflsrList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void gzflsrEdit(HttpServletRequest request,
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
			String result = request.getParameter("result");
			Gzflsr gzflsr = new Gzflsr();
			ArrayList<GzflsrZxm> GzflsrZxmList = new ArrayList<GzflsrZxm>();
			GzflsrDao gzflsrDao = new GzflsrDao();
			if(!"".equals(id)&&id!=null){
				gzflsr = gzflsrDao.queryGzflsrByID(Integer.parseInt(id));
				//子项目列表
				if(!"".equals(gzflsr.getXmid())&&gzflsr.getXmid()!=null){
					GzflsrZxmList =  gzflsrDao.queryGzflsrZxmByIDList(gzflsr.getXmid());
				}
				
			}
			request.setAttribute("gzflsr", gzflsr);
			request.setAttribute("GzflsrZxmList", GzflsrZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/WagesBenefits/gzflsrEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void gzflsrSave(HttpServletRequest request,
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
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String Gzflsr_id = request.getParameter("Gzflsr_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			Gzflsr gzflsr = new Gzflsr();
			gzflsr.setYear(StrToInt(year));
			gzflsr.setMonth(StrToInt(month));
			gzflsr.setCzr(UserInfo.getName());
			gzflsr.setCzrID(UserInfo.getUsername());
			gzflsr.setCzrdw(UserInfo.getCompany());
			gzflsr.setTjzt(tjzt);
			gzflsr.setCzsj(data1);
			//子项目数据
			String xmid = request.getParameter("xmid");//项目id
			String zgxm[]=request.getParameterValues("zgxm"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String gwgz[]=request.getParameterValues("gwgz");
			String xjgz[]=request.getParameterValues("xjgz");
			String gwjt[]=request.getParameterValues("gwjt");
			String shbt[]=request.getParameterValues("shbt");
			String jljx[]=request.getParameterValues("jljx");
			String zfbt[]=request.getParameterValues("zfbt");
			String ct[]=request.getParameterValues("ct");
			String ybbt[]=request.getParameterValues("ybbt");
			String gjj[]=request.getParameterValues("gjj");
			String qtsr[]=request.getParameterValues("qtsr");
			String yfhj[]=request.getParameterValues("yfhj");
			GzflsrDao gzflsrDao = new GzflsrDao();
			//删除原有子项目记录，保存新内容
			if (!"".equals(xmid)&&xmid!=null){
				gzflsrDao.deleteGzflsrZxmByIDList(xmid);
			}
			xmid="";
			int zxmid=0;
			GzflsrZxm gzflsrZxm = new GzflsrZxm();
			if(zgxm!=null){
				for(int i=0; i<zgxm.length; i++){
					gzflsrZxm.setZgxm(zgxm[i]);
					gzflsrZxm.setGwgz(gwgz[i]);
					gzflsrZxm.setXjgz(xjgz[i]);
					gzflsrZxm.setGwjt(gwjt[i]);
					gzflsrZxm.setShbt(shbt[i]);
					gzflsrZxm.setJljx(jljx[i]);
					gzflsrZxm.setZfbt(zfbt[i]);
					gzflsrZxm.setCt(ct[i]);
					gzflsrZxm.setYbbt(ybbt[i]);
					gzflsrZxm.setGjj(gjj[i]);
					gzflsrZxm.setQtsr(qtsr[i]);
					gzflsrZxm.setYfhj(yfhj[i]);
					zxmid = gzflsrDao.insertGzflsrZxm(gzflsrZxm);
					if("".equals(xmid)){
						xmid = zxmid+"";
					}else{
						xmid = xmid+","+zxmid;
					}
				}
			}
			gzflsr.setXmid(xmid);
			//新增或修改主表
			if(!"".equals(Gzflsr_id)&&Gzflsr_id!=null){
				gzflsr.setId(Integer.parseInt(Gzflsr_id));
				gzflsrDao.updateGzflsr(gzflsr);
			}else{
				Gzflsr_id = gzflsrDao.insertGzflsr(gzflsr)+"";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("GzflsrServlet?action=getGzflsr").forward(request,
					response);
			
		}
	}


	 /*
	 *  进入查看页面
	 * 
	 */
	protected void gzflsrShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		Gzflsr gzflsr = new Gzflsr();
		ArrayList<GzflsrZxm> GzflsrZxmList = new ArrayList<GzflsrZxm>();
		GzflsrDao gzflsrDao = new GzflsrDao();
		if(!"".equals(id)&&id!=null){
			gzflsr = gzflsrDao.queryGzflsrByID(Integer.parseInt(id));
			//子项目列表
			if(!"".equals(gzflsr.getXmid())&&gzflsr.getXmid()!=null){
				GzflsrZxmList =  gzflsrDao.queryGzflsrZxmByIDList(gzflsr.getXmid());
			}
		}
		request.setAttribute("gzflsr", gzflsr);
		request.setAttribute("GzflsrZxmList", GzflsrZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/WagesBenefits/gzflsrShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void gzflsrDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String xmid = request.getParameter("xmid");
		GzflsrDao gzflsrDao = new GzflsrDao();
		gzflsrDao.deleteGzflsrById(Integer.parseInt(id));
		//删除子项目记录
		if(!"".equals(xmid)&&xmid!=null){
			gzflsrDao.deleteGzflsrZxmByIDList(xmid);
		}
		RequestDispatcher rd = request.getRequestDispatcher("GzflsrServlet?action=getGzflsrList&flag=1&cxcompany="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	
	/**
	* 生成信息的XLS
	*
	*/
	public void getGzflsrExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		Gzflsr gzflsr = new Gzflsr();
		ArrayList<GzflsrZxm> GzflsrZxmList = new ArrayList<GzflsrZxm>();
		GzflsrDao gzflsrDao = new GzflsrDao();
		if(!"".equals(exc_id)&&exc_id!=null){
			gzflsr = gzflsrDao.queryGzflsrByID(Integer.parseInt(exc_id));
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
				WritableSheet wsheet = wwb.createSheet("工资福利收入统计表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setWrap(true);//自动换行
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setWrap(true);//自动换行
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);//自动换行
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,gzflsr.getYear()+"年1-"+gzflsr.getMonth()+"月工资福利收入统计表",format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+gzflsr.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(12,1,"单位：元",format3);
				wsheet.addCell(label);

				label = new Label(0,2,"编号",format4);
				wsheet.addCell(label);
				label = new Label(1,2,"职工姓名",format4);
				wsheet.addCell(label);
				label = new Label(2,2,"岗位工资",format4);
				wsheet.addCell(label);
				label = new Label(3,2,"薪级工资",format4);
				wsheet.addCell(label);
				label = new Label(4,2,"基础性绩效工资",format4);
				wsheet.addCell(label);
				label = new Label(6,2,"奖励性绩效工资",format4);
				wsheet.addCell(label);
				label = new Label(7,2,"改革性补贴",format4);
				wsheet.addCell(label);
				label = new Label(10,2,"公积金",format4);
				wsheet.addCell(label);
				label = new Label(11,2,"其它收入",format4);
				wsheet.addCell(label);
				label = new Label(12,2,"应发合计",format4);
				wsheet.addCell(label);
				

				label = new Label(4,3,"岗位津贴",format4);
				wsheet.addCell(label);
				label = new Label(5,3,"生活补贴",format4);
				wsheet.addCell(label);
				label = new Label(7,3,"住房补贴",format4);
				wsheet.addCell(label);
				label = new Label(8,3,"车贴",format4);
				wsheet.addCell(label);
				label = new Label(9,3,"医保补贴",format4);
				wsheet.addCell(label);

				//子项目列表
				if(!"".equals(gzflsr.getXmid())&&gzflsr.getXmid()!=null){
					GzflsrZxmList =  gzflsrDao.queryGzflsrZxmByIDList(gzflsr.getXmid());
				}
				for(int i=0; i<GzflsrZxmList.size(); i++){
					label = new Label(0,i+4,i+1+"",format5);
					wsheet.addCell(label);
					label = new Label(1,i+4,GzflsrZxmList.get(i).getZgxm(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+4,GzflsrZxmList.get(i).getGwgz(),format5);
					wsheet.addCell(label);
					label = new Label(3,i+4,GzflsrZxmList.get(i).getXjgz(),format5);
					wsheet.addCell(label);
					label = new Label(4,i+4,GzflsrZxmList.get(i).getGwjt(),format5);
					wsheet.addCell(label);
					label = new Label(5,i+4,GzflsrZxmList.get(i).getShbt(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+4,GzflsrZxmList.get(i).getJljx(),format5);
					wsheet.addCell(label);
					label = new Label(7,i+4,GzflsrZxmList.get(i).getZfbt(),format5);
					wsheet.addCell(label);
					label = new Label(8,i+4,GzflsrZxmList.get(i).getCt(),format5);
					wsheet.addCell(label);
					label = new Label(9,i+4,GzflsrZxmList.get(i).getYbbt(),format5);
					wsheet.addCell(label);
					label = new Label(10,i+4,GzflsrZxmList.get(i).getGjj(),format5);
					wsheet.addCell(label);
					label = new Label(11,i+4,GzflsrZxmList.get(i).getQtsr(),format5);
					wsheet.addCell(label);
					label = new Label(12,i+4,GzflsrZxmList.get(i).getYfhj(),format5);
					wsheet.addCell(label);
				}
				label = new Label(0,GzflsrZxmList.size()+4,"注一：奖励性绩效工资是指各单位按照职工收入考核办法确定的每月按系数定额发放的奖励性绩效工资。",format2);
				wsheet.addCell(label);
				label = new Label(0,GzflsrZxmList.size()+5,"注二：其它收入包括除每月按系数定额发放的奖励性绩效工资以外的奖励性绩效工资以及各类以奖励、补贴、福利等名义发放的个人收入及物资。",format2);
				wsheet.addCell(label);
				//合并单元格
				wsheet.mergeCells(0, 0, 12, 0);
				wsheet.mergeCells(0, GzflsrZxmList.size()+4, 12, GzflsrZxmList.size()+4);
				wsheet.mergeCells(0, GzflsrZxmList.size()+5, 12, GzflsrZxmList.size()+5);
				wsheet.mergeCells(0, 1, 5, 1);
				wsheet.mergeCells(4, 2, 5, 2);
				wsheet.mergeCells(7, 2, 9, 2);
				wsheet.mergeCells(0, 2, 0, 3);
				wsheet.mergeCells(1, 2, 1, 3);
				wsheet.mergeCells(2, 2, 2, 3);
				wsheet.mergeCells(3, 2, 3, 3);
				wsheet.mergeCells(6, 2, 6, 3);
				wsheet.mergeCells(10, 2, 10, 3);
				wsheet.mergeCells(11, 2, 11, 3);
				wsheet.mergeCells(12, 2, 12, 3);
				//设置列宽
				for(int j=0; j<13; j++){
					wsheet.setColumnView(j, 10);
				}
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				wsheet.setRowView(2, 600, false);
				wsheet.setRowView(3, 600, false);
				for(int k=0; k<GzflsrZxmList.size(); k++){
					wsheet.setRowView(k+4, 400, false);
				}
				wsheet.setRowView(GzflsrZxmList.size()+4, 600, false);
				wsheet.setRowView(GzflsrZxmList.size()+5, 600, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readGzflsrExcel(HttpServletRequest request,
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
			String gzflsr_id = request.getParameter("gzflsr_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
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
			Gzflsr gzflsr = new Gzflsr();
			ArrayList<GzflsrZxm> GzflsrZxmList = new ArrayList<GzflsrZxm>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行高
				int c = sheet[0].getColumns();//列长
				if(r<4||c<13){
					result = "请检查文件是否正确";
				}else{
					if(!"".equals(gzflsr_id)&&gzflsr_id!=null){
						gzflsr.setId(Integer.parseInt(gzflsr_id));
					}
					gzflsr.setYear(StrToInt(year));
					gzflsr.setMonth(StrToInt(month));
					
					for(int i=4;i<r-2; i++){
						GzflsrZxm gzflsrZxm = new GzflsrZxm();
						gzflsrZxm.setZgxm(sheet[0].getCell(1,i).getContents());
						gzflsrZxm.setGwgz(sheet[0].getCell(2,i).getContents());
						gzflsrZxm.setXjgz(sheet[0].getCell(3,i).getContents());
						gzflsrZxm.setGwjt(sheet[0].getCell(4,i).getContents());
						gzflsrZxm.setShbt(sheet[0].getCell(5,i).getContents());
						gzflsrZxm.setJljx(sheet[0].getCell(6,i).getContents());
						gzflsrZxm.setZfbt(sheet[0].getCell(7,i).getContents());
						gzflsrZxm.setCt(sheet[0].getCell(8,i).getContents());
						gzflsrZxm.setYbbt(sheet[0].getCell(9,i).getContents());
						gzflsrZxm.setGjj(sheet[0].getCell(10,i).getContents());
						gzflsrZxm.setQtsr(sheet[0].getCell(11,i).getContents());
						gzflsrZxm.setYfhj(sheet[0].getCell(12,i).getContents());
						GzflsrZxmList.add(gzflsrZxm);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("gzflsr", gzflsr);
			request.setAttribute("GzflsrZxmList", GzflsrZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/WagesBenefits/gzflsrEdit.jsp").forward(request,
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
	private int StrToInt(String str){
		if("".equals(str)||str==null){
			return 0;
		}else{
			return Integer.parseInt(str);
		}
	}
}
