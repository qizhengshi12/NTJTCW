package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import com.safety.dao.BbdzDao;
import com.safety.dao.BbsbCwbbDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MenuDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.Bbdzmb;
import com.safety.entity.ContentZzxx;
import com.safety.entity.CwbbLrb;
import com.safety.entity.CwbbLrbsj;
import com.safety.entity.CwbbSgjfb;
import com.safety.entity.CwbbSydwzycwzbb;
import com.safety.entity.CwbbSydwzycwzbbHzb;
import com.safety.entity.CwbbYszxb;
import com.safety.entity.CwbbYszxbHzb;
import com.safety.entity.CwbbYszxbZxm;
import com.safety.entity.CwbbZcfzb;
import com.safety.entity.CwbbZcfzbfz;
import com.safety.entity.CwbbZcfzbzc;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class BbsbCwbbServlet  extends HttpServlet{
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
		if("getCwbb".equals(action)){//财务报表
			getCwbb(request,response);
		}else if("resetMenu".equals(action)){//重置菜单
			resetMenu(request,response);
		}else if("getYszxb".equals(action)){//预算执行表——列表
			getYszxb(request,response);
		}else if("yszxbEdit".equals(action)){//预算执行表——新增或修改
			yszxbEdit(request,response);
		}else if("yszxbSave".equals(action)){//预算执行表——保存页面
			yszxbSave(request,response);
		}else if("yszxbShow".equals(action)){//预算执行表——查看
			yszxbShow(request,response);
		}else if("yszxbDelete".equals(action)){//预算执行表——删除
			yszxbDelete(request,response);
		}else if("getCwbbYszxbExcel".equals(action)){//预算执行表——导出EXCEL
			getCwbbYszxbExcel(request,response);
		}else if("readYszxbExcel".equals(action)){//预算执行表——解析EXCEL
			readYszxbExcel(request,response);
		}else if("yszxbReturn".equals(action)){//预算执行表——驳回
			yszxbReturn(request,response);
		}else if("getYszxbHzb".equals(action)){//预算执行表汇总表——列表
			getYszxbHzb(request,response);
		}else if("YszxbHzbSave".equals(action)){//预算执行表汇总表——生成数据
			YszxbHzbSave(request,response);
		}else if("YszxbHzbShow".equals(action)){//预算执行表汇总表——查看
			YszxbHzbShow(request,response);
		}else if("YszxbHzbDelete".equals(action)){//预算执行表汇总表——删除
			YszxbHzbDelete(request,response);
		}else if("getCwbbYszxbHzbExcel".equals(action)){//预算执行表汇总表——导出EXCEL
			getCwbbYszxbHzbExcel(request,response);
		}else if("getCwbbSgjfb".equals(action)){//三公经费表——列表（左边是菜单）
			getCwbbSgjfb(request,response);
		}else if("getSgjfb".equals(action)){//三公经费表——列表
			getSgjfb(request,response);
		}else if("resetMenuSgjf".equals(action)){//三公经费表——重置菜单
			resetMenuSgjf(request,response);
		}else if("sgjfbEdit".equals(action)){//三公经费表——新增或修改
			sgjfbEdit(request,response);
		}else if("sgjfbSave".equals(action)){//三公经费表——保存页面
			sgjfbSave(request,response);
		}else if("sgjfbShow".equals(action)){//三公经费表——查看
			sgjfbShow(request,response);
		}else if("sgjfbDelete".equals(action)){//三公经费表——删除
			sgjfbDelete(request,response);
		}else if("getCwbbSgjfbExcel".equals(action)){//三公经费表——导出EXCEL
			getCwbbSgjfbExcel(request,response);
		}else if("readSgjfbExcel".equals(action)){//三公经费表——解析EXCEL
			readSgjfbExcel(request,response);
		}else if("sgjfbReturn".equals(action)){//三公经费表——驳回
			sgjfbReturn(request,response);
		}else if("getSydwzycwzbb".equals(action)){//事业单位主要财务指标表——列表
			getSydwzycwzbb(request,response);
		}else if("sydwzycwzbbEdit".equals(action)){//事业单位主要财务指标表——新增或修改
			sydwzycwzbbEdit(request,response);
		}else if("sydwzycwzbbSave".equals(action)){//事业单位主要财务指标表——保存页面
			sydwzycwzbbSave(request,response);
		}else if("sydwzycwzbbShow".equals(action)){//事业单位主要财务指标表——查看
			sydwzycwzbbShow(request,response);
		}else if("sydwzycwzbbDelete".equals(action)){//事业单位主要财务指标表——删除
			sydwzycwzbbDelete(request,response);
		}else if("getCwbbSydwzycwzbbExcel".equals(action)){//事业单位主要财务指标表——导出EXCEL
			getCwbbSydwzycwzbbExcel(request,response);
		}else if("readSydwzycwzbbExcel".equals(action)){//事业单位主要财务指标表——解析EXCEL
			readSydwzycwzbbExcel(request,response);
		}else if("sydwzycwzbbReturn".equals(action)){//事业单位主要财务指标表——驳回
			sydwzycwzbbReturn(request,response);
		}else if("getSydwzycwzbbHzb".equals(action)){//交通事业单位主要财务指标状况汇总表——列表
			getSydwzycwzbbHzb(request,response);
		}else if("SydwzycwzbbHzbSave".equals(action)){//交通事业单位主要财务指标状况汇总表——生成数据
			SydwzycwzbbHzbSave(request,response);
		}else if("SydwzycwzbbHzbShow".equals(action)){//交通事业单位主要财务指标状况汇总表——查看
			SydwzycwzbbHzbShow(request,response);
		}else if("SydwzycwzbbHzbDelete".equals(action)){//交通事业单位主要财务指标状况汇总表——删除
			SydwzycwzbbHzbDelete(request,response);
		}else if("getCwbbSydwzycwzbbHzbExcel".equals(action)){//交通事业单位主要财务指标状况汇总表——导出EXCEL
			getCwbbSydwzycwzbbHzbExcel(request,response);
		}else if("getLrb".equals(action)){//损益表——列表
			getLrb(request,response);
		}else if("LrbEdit".equals(action)){//损益表——新增或修改
			LrbEdit(request,response);
		}else if("LrbSave".equals(action)){//损益表——保存页面
			LrbSave(request,response);
		}else if("LrbReturn".equals(action)){//损益表——驳回
			LrbReturn(request,response);
		}else if("LrbShow".equals(action)){//损益表——查看
			LrbShow(request,response);
		}else if("LrbDelete".equals(action)){//损益表——删除
			LrbDelete(request,response);
		}else if("getCwbbLrbQYJTExcel".equals(action)){//损益表——导出EXCEL（汽运集团）
			getCwbbLrbQYJTExcel(request,response);
		}else if("readLrbQYJTExcel".equals(action)){//损益表——解析EXCEL（汽运集团）
			readLrbQYJTExcel(request,response);
		}else if("getCwbbLrbTZGSExcel".equals(action)){//损益表——导出EXCEL（投资公司）
			getCwbbLrbTZGSExcel(request,response);
		}else if("readLrbTZGSExcel".equals(action)){//损益表——解析EXCEL（投资公司）
			readLrbTZGSExcel(request,response);
		}else if("getCwbbLrbZWGSExcel".equals(action)){//损益表——导出EXCEL（站务公司）
			getCwbbLrbZWGSExcel(request,response);
		}else if("readLrbZWGSExcel".equals(action)){//损益表——解析EXCEL（站务公司）
			readLrbZWGSExcel(request,response);
		}else if("getCwbbLrbCZGSExcel".equals(action)){//损益表——导出EXCEL（出租公司）
			getCwbbLrbCZGSExcel(request,response);
		}else if("readLrbCZGSExcel".equals(action)){//损益表——解析EXCEL（出租公司）
			readLrbCZGSExcel(request,response);
		}else if("getCwbbLrbJCZXExcel".equals(action)){//损益表——导出EXCEL（检测中心）
			getCwbbLrbJCZXExcel(request,response);
		}else if("readLrbJCZXExcel".equals(action)){//损益表——解析EXCEL（检测中心）
			readLrbJCZXExcel(request,response);
		}else if("getCwbbLrbGJGSExcel".equals(action)){//损益表——导出EXCEL（公交公司）
			getCwbbLrbGJGSExcel(request,response);
		}else if("readLrbGJGSExcel".equals(action)){//损益表——解析EXCEL（公交公司）
			readLrbGJGSExcel(request,response);
		}else if("getZcfzb".equals(action)){//资产负债表——列表
			getZcfzb(request,response);
		}else if("ZcfzbEdit".equals(action)){//资产负债表——新增或修改
			ZcfzbEdit(request,response);
		}else if("ZcfzbSave".equals(action)){//资产负债表——保存页面
			ZcfzbSave(request,response);
		}else if("ZcfzbReturn".equals(action)){//资产负债表——驳回
			ZcfzbReturn(request,response);
		}else if("ZcfzbShow".equals(action)){//资产负债表——查看
			ZcfzbShow(request,response);
		}else if("ZcfzbDelete".equals(action)){//资产负债表——删除
			ZcfzbDelete(request,response);
		}else if("getCwbbZcfzbQYJTExcel".equals(action)){//资产负债表——导出EXCEL（汽运集团）
			getCwbbZcfzbQYJTExcel(request,response);
		}else if("readZcfzbQYJTExcel".equals(action)){//资产负债表——解析EXCEL（汽运集团）
			readZcfzbQYJTExcel(request,response);
		}else if("getCwbbZcfzbZWGSExcel".equals(action)){//资产负债表——导出EXCEL（站务公司）
			getCwbbZcfzbZWGSExcel(request,response);
		}else if("readZcfzbZWGSExcel".equals(action)){//资产负债表——解析EXCEL（站务公司）
			readZcfzbZWGSExcel(request,response);
		}else if("getCwbbZcfzbTZGSExcel".equals(action)){//资产负债表——导出EXCEL（投资公司）
			getCwbbZcfzbTZGSExcel(request,response);
		}else if("readZcfzbTZGSExcel".equals(action)){//资产负债表——解析EXCEL（投资公司）
			readZcfzbTZGSExcel(request,response);
		}else if("getCwbbZcfzbCZGSExcel".equals(action)){//资产负债表——导出EXCEL（出租公司）
			getCwbbZcfzbCZGSExcel(request,response);
		}else if("readZcfzbCZGSExcel".equals(action)){//资产负债表——解析EXCEL（出租公司）
			readZcfzbCZGSExcel(request,response);
		}else if("getCwbbZcfzbJCZXExcel".equals(action)){//资产负债表——导出EXCEL（检测中心）
			getCwbbZcfzbJCZXExcel(request,response);
		}else if("readZcfzbJCZXExcel".equals(action)){//资产负债表——解析EXCEL（检测中心）
			readZcfzbJCZXExcel(request,response);
		}else if("getCwbbZcfzbGJGSExcel".equals(action)){//资产负债表——导出EXCEL（公交公司）
			getCwbbZcfzbGJGSExcel(request,response);
		}else if("readZcfzbGJGSExcel".equals(action)){//资产负债表——解析EXCEL（公交公司）
			readZcfzbGJGSExcel(request,response);
		}else if("DHC_PushNum".equals(action)){//董洪超计算器——录入数据EXCEL
			DHC_PushNum(request,response);
		}
	}

	/*
	 *  刷新菜单树
	 * 
	 */
	protected void getCwbb(HttpServletRequest request,
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
			str = menuDao.QueryAllMenuInfoName("node_cwbb");
			//查询自定义财务报表模板
			ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
			BbdzDao bbdzDao = new BbdzDao();
			BbdzmbList = bbdzDao.queryBbdzmbByLx("cwbb");
			
			request.setAttribute("BbdzmbList", BbdzmbList);
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/getCwbb.jsp").forward(request,
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
			menuDao.DeleteMenuName("node_cwbb");
			menuDao.ResetZzxxMenu("node_cwbb",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getCwbb&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  预算执行表
	 * 
	 */
	protected void getYszxb(HttpServletRequest request,
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
			ArrayList<CwbbYszxb> CwbbYszxbList = new ArrayList<CwbbYszxb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
			String cxcompany = request.getParameter("cxcompany");
			String cxtj = request.getParameter("cxtj");
			String menuname = request.getParameter("menuname");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbCwbbDao.queryCwbbYszxbListByBtCount(srbt);
			CwbbYszxbList = bbsbCwbbDao.queryCwbbYszxbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbYszxbList", CwbbYszxbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbYszxbList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void yszxbEdit(HttpServletRequest request,
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
			//部分会计可以为多家单位新增预算执行表，带入的指定月内容需要区分
			String srcompany = request.getParameter("srcompany");
			String sryear = request.getParameter("sryear");
			String srmonth = request.getParameter("srmonth");
			CwbbYszxb cwbbYszxb = new CwbbYszxb();
			ArrayList<CwbbYszxbZxm> CwbbYszxbZxmList = new ArrayList<CwbbYszxbZxm>();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			if(!"".equals(id)&&id!=null){
				cwbbYszxb = bbsbCwbbDao.queryCwbbYszxbByID(Integer.parseInt(id));
				//子项目列表
				if(!"".equals(cwbbYszxb.getXmid())&&cwbbYszxb.getXmid()!=null){
					CwbbYszxbZxmList =  bbsbCwbbDao.queryCwbbYszxbZxmByIDList(cwbbYszxb.getXmid());
				}
				
			}else{
				if("".equals(srcompany)||srcompany==null){
					srcompany = UserInfo.getCompany();
				}
				//新增时，将上月内容带入
//				Calendar cal=Calendar.getInstance();
//				int nowYear = cal.get(Calendar.YEAR);
//				int nowMonth = cal.get(Calendar.MONTH)+1;
				//通过时间和单位查询（1月份不用带入）
				if(!"1".equals(srmonth)){
					cwbbYszxb = bbsbCwbbDao.queryCwbbYszxbByDWSJ(Integer.parseInt(sryear), Integer.parseInt(srmonth)-1, srcompany);
					if(!"".equals(cwbbYszxb.getXmid())&&cwbbYszxb.getXmid()!=null){
						CwbbYszxbZxmList =  bbsbCwbbDao.queryCwbbYszxbZxmByIDList(cwbbYszxb.getXmid());
					}
					//将无用数据置空
					cwbbYszxb.setXmid(null);
				}
				cwbbYszxb.setCzrdw(srcompany);
				cwbbYszxb.setYear(Integer.parseInt(sryear));
				cwbbYszxb.setMonth(Integer.parseInt(srmonth));
				
			}
			request.setAttribute("cwbbYszxb", cwbbYszxb);
			request.setAttribute("CwbbYszxbZxmList", CwbbYszxbZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbYszxbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void yszxbSave(HttpServletRequest request,
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
			String cwbbYszxb_id = request.getParameter("cwbbYszxb_id");
			String bt= request.getParameter("bt");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String newcompany = request.getParameter("newcompany");
			String jb1 = request.getParameter("jb1");
			String jb2 = request.getParameter("jb2");
			String jb3 = request.getParameter("jb3");
			String jb4 = request.getParameter("jb4");
			String gz1 = request.getParameter("gz1");
			String gz2 = request.getParameter("gz2");
			String gz3 = request.getParameter("gz3");
			String gz4 = request.getParameter("gz4");
			String sp1 = request.getParameter("sp1");
			String sp2 = request.getParameter("sp2");
			String sp3 = request.getParameter("sp3");
			String sp4 = request.getParameter("sp4");
			String bz1 = request.getParameter("bz1");
			String bz2 = request.getParameter("bz2");
			String bz3 = request.getParameter("bz3");
			String bz4 = request.getParameter("bz4");
			String xm1 = request.getParameter("xm1");
			String xm2 = request.getParameter("xm2");
			String xm3 = request.getParameter("xm3");
			String xm4 = request.getParameter("xm4");
			String hj1 = request.getParameter("hj1");
			String hj2 = request.getParameter("hj2");
			String hj3 = request.getParameter("hj3");
			String hj4 = request.getParameter("hj4");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱3：驳回
			CwbbYszxb cwbbYszxb = new CwbbYszxb();
			cwbbYszxb.setBt(bt);
			cwbbYszxb.setYear(StrToInt(year));
			cwbbYszxb.setMonth(StrToInt(month));
			cwbbYszxb.setCzr(UserInfo.getName());
			cwbbYszxb.setCzrID(UserInfo.getUsername());
			cwbbYszxb.setCzrdw(newcompany);
			cwbbYszxb.setCzsj(data1);
			cwbbYszxb.setJb1(jb1);
			cwbbYszxb.setJb2(jb2);
			cwbbYszxb.setJb3(jb3);
			cwbbYszxb.setJb4(jb4);
			cwbbYszxb.setGz1(gz1);
			cwbbYszxb.setGz2(gz2);
			cwbbYszxb.setGz3(gz3);
			cwbbYszxb.setGz4(gz4);
			cwbbYszxb.setSp1(sp1);
			cwbbYszxb.setSp2(sp2);
			cwbbYszxb.setSp3(sp3);
			cwbbYszxb.setSp4(sp4);
			cwbbYszxb.setBz1(bz1);
			cwbbYszxb.setBz2(bz2);
			cwbbYszxb.setBz3(bz3);
			cwbbYszxb.setBz4(bz4);
			cwbbYszxb.setXm1(xm1);
			cwbbYszxb.setXm2(xm2);
			cwbbYszxb.setXm3(xm3);
			cwbbYszxb.setXm4(xm4);
			cwbbYszxb.setHj1(hj1);
			cwbbYszxb.setHj2(hj2);
			cwbbYszxb.setHj3(hj3);
			cwbbYszxb.setHj4(hj4);
			cwbbYszxb.setTjzt(tjzt);
			//子项目数据
			String xmid = request.getParameter("xmid");//项目id
			String zxmmc[]=request.getParameterValues("zxmmc"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String zxm1[]=request.getParameterValues("zxma");
			String zxm2[]=request.getParameterValues("zxmb");
			String zxm3[]=request.getParameterValues("zxmc");
			String zxm4[]=request.getParameterValues("zxmd");
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//删除原有子项目记录，保存新内容
			if (!"".equals(xmid)&&xmid!=null){
				bbsbCwbbDao.deleteCwbbYszxbZxmByIDList(xmid);
			}
			xmid="";
			int zxmid=0;
			CwbbYszxbZxm cwbbYszxbZxm = new CwbbYszxbZxm();
			if(zxmmc!=null){
				for(int i=0; i<zxmmc.length; i++){
					cwbbYszxbZxm.setZxmmc(zxmmc[i]);
					cwbbYszxbZxm.setZxm1(zxm1[i]);
					cwbbYszxbZxm.setZxm2(zxm2[i]);
					cwbbYszxbZxm.setZxm3(zxm3[i]);
					cwbbYszxbZxm.setZxm4(zxm4[i]);
					zxmid = bbsbCwbbDao.insertCwbbYszxbZxm(cwbbYszxbZxm);
					if("".equals(xmid)){
						xmid = zxmid+"";
					}else{
						xmid = xmid+","+zxmid;
					}
				}
			}
			cwbbYszxb.setXmid(xmid);
			//新增或修改主表
			if(!"".equals(cwbbYszxb_id)&&cwbbYszxb_id!=null){
				cwbbYszxb.setId(Integer.parseInt(cwbbYszxb_id));
				bbsbCwbbDao.updateCwbbYszxb(cwbbYszxb);
			}else{
				cwbbYszxb_id = bbsbCwbbDao.insertCwbbYszxb(cwbbYszxb)+"";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getYszxb&flag=1&menuname="+newcompany).forward(request,
					response);
			
		}
	}

	/*
	 *  进入驳回
	 * 
	 */
	protected void yszxbReturn(HttpServletRequest request,
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
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbCwbbDao.updateCwbbYszxbRet(id);
				String dxnr = UserInfo.getName()+"：您有一份预算执行表被驳回，请及时查看";
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
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getYszxb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}


	 /*
	 *  进入查看页面
	 * 
	 */
	protected void yszxbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		CwbbYszxb cwbbYszxb = new CwbbYszxb();
		ArrayList<CwbbYszxbZxm> CwbbYszxbZxmList = new ArrayList<CwbbYszxbZxm>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			cwbbYszxb = bbsbCwbbDao.queryCwbbYszxbByID(Integer.parseInt(id));
			//子项目列表
			if(!"".equals(cwbbYszxb.getXmid())&&cwbbYszxb.getXmid()!=null){
				CwbbYszxbZxmList =  bbsbCwbbDao.queryCwbbYszxbZxmByIDList(cwbbYszxb.getXmid());
			}
		}
		request.setAttribute("cwbbYszxb", cwbbYszxb);
		request.setAttribute("CwbbYszxbZxmList", CwbbYszxbZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbYszxbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void yszxbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		String xmid = request.getParameter("xmid");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbYszxbById(Integer.parseInt(id));
		//删除子项目记录
		if(!"".equals(xmid)&&xmid!=null){
			bbsbCwbbDao.deleteCwbbYszxbZxmByIDList(xmid);
		}
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getYszxb&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbYszxbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		CwbbYszxb cwbbYszxb = new CwbbYszxb();
		ArrayList<CwbbYszxbZxm> CwbbYszxbZxmList = new ArrayList<CwbbYszxbZxm>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(exc_id)&&exc_id!=null){
			cwbbYszxb = bbsbCwbbDao.queryCwbbYszxbByID(Integer.parseInt(exc_id));
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
				WritableSheet wsheet = wwb.createSheet("预算执行表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbYszxb.getYear()+"年1-"+cwbbYszxb.getMonth()+"月"+cwbbYszxb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbYszxb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(4,1,"单位：万元",format3);
				wsheet.addCell(label);

				label = new Label(0,2,"",format4);
				wsheet.addCell(label);
				label = new Label(1,2,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(2,2,"支出数",format4);
				wsheet.addCell(label);
				label = new Label(3,2,"结余数",format4);
				wsheet.addCell(label);
				label = new Label(4,2,"支出比例（%)",format4);
				wsheet.addCell(label);
				
				label = new Label(0,3,"一、基本支出",format4);
				wsheet.addCell(label);
				label = new Label(1,3,cwbbYszxb.getJb1(),format5);
				wsheet.addCell(label);
				label = new Label(2,3,cwbbYszxb.getJb2(),format5);
				wsheet.addCell(label);
				label = new Label(3,3,cwbbYszxb.getJb3(),format5);
				wsheet.addCell(label);
				label = new Label(4,3,cwbbYszxb.getJb4(),format5);
				wsheet.addCell(label);
				
				label = new Label(0,4," 1、工资福利支出",format5);
				wsheet.addCell(label);
				label = new Label(1,4,cwbbYszxb.getGz1(),format5);
				wsheet.addCell(label);
				label = new Label(2,4,cwbbYszxb.getGz2(),format5);
				wsheet.addCell(label);
				label = new Label(3,4,cwbbYszxb.getGz3(),format5);
				wsheet.addCell(label);
				label = new Label(4,4,cwbbYszxb.getGz4(),format5);
				wsheet.addCell(label);
				
				label = new Label(0,5," 2、商品和服务支出",format5);
				wsheet.addCell(label);
				label = new Label(1,5,cwbbYszxb.getSp1(),format5);
				wsheet.addCell(label);
				label = new Label(2,5,cwbbYszxb.getSp2(),format5);
				wsheet.addCell(label);
				label = new Label(3,5,cwbbYszxb.getSp3(),format5);
				wsheet.addCell(label);
				label = new Label(4,5,cwbbYszxb.getSp4(),format5);
				wsheet.addCell(label);
				
				label = new Label(0,6," 3、对个人和家庭补助支出",format5);
				wsheet.addCell(label);
				label = new Label(1,6,cwbbYszxb.getBz1(),format5);
				wsheet.addCell(label);
				label = new Label(2,6,cwbbYszxb.getBz2(),format5);
				wsheet.addCell(label);
				label = new Label(3,6,cwbbYszxb.getBz3(),format5);
				wsheet.addCell(label);
				label = new Label(4,6,cwbbYszxb.getBz4(),format5);
				wsheet.addCell(label);
				
				label = new Label(0,7,"",format5);
				wsheet.addCell(label);
				label = new Label(1,7,"",format5);
				wsheet.addCell(label);
				label = new Label(2,7,"",format5);
				wsheet.addCell(label);
				label = new Label(3,7,"",format5);
				wsheet.addCell(label);
				label = new Label(4,7,"",format5);
				wsheet.addCell(label);
				
				label = new Label(0,8,"二、项目支出",format4);
				wsheet.addCell(label);
				label = new Label(1,8,cwbbYszxb.getXm1(),format5);
				wsheet.addCell(label);
				label = new Label(2,8,cwbbYszxb.getXm2(),format5);
				wsheet.addCell(label);
				label = new Label(3,8,cwbbYszxb.getXm3(),format5);
				wsheet.addCell(label);
				label = new Label(4,8,cwbbYszxb.getXm4(),format5);
				wsheet.addCell(label);

				//子项目列表
				if(!"".equals(cwbbYszxb.getXmid())&&cwbbYszxb.getXmid()!=null){
					CwbbYszxbZxmList =  bbsbCwbbDao.queryCwbbYszxbZxmByIDList(cwbbYszxb.getXmid());
				}
				for(int i=0; i<CwbbYszxbZxmList.size(); i++){
					label = new Label(0,i+9,CwbbYszxbZxmList.get(i).getZxmmc(),format5);
					wsheet.addCell(label);
					label = new Label(1,i+9,CwbbYszxbZxmList.get(i).getZxm1(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+9,CwbbYszxbZxmList.get(i).getZxm2(),format5);
					wsheet.addCell(label);
					label = new Label(3,i+9,CwbbYszxbZxmList.get(i).getZxm3(),format5);
					wsheet.addCell(label);
					label = new Label(4,i+9,CwbbYszxbZxmList.get(i).getZxm4(),format5);
					wsheet.addCell(label);
				}
				int j = CwbbYszxbZxmList.size();
				label = new Label(0,j+9,"合计",format6);
				wsheet.addCell(label);
				label = new Label(1,j+9,cwbbYszxb.getHj1(),format6);
				wsheet.addCell(label);
				label = new Label(2,j+9,cwbbYszxb.getHj2(),format6);
				wsheet.addCell(label);
				label = new Label(3,j+9,cwbbYszxb.getHj3(),format6);
				wsheet.addCell(label);
				label = new Label(4,j+9,cwbbYszxb.getHj4(),format6);
				wsheet.addCell(label);
				//合并单元格
				wsheet.mergeCells(0, 0, 4, 0);
				//设置列宽
				wsheet.setColumnView(0, 30);
				wsheet.setColumnView(1, 12);
				wsheet.setColumnView(2, 12);
				wsheet.setColumnView(3, 12);
				wsheet.setColumnView(4, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<j+8; k++){
					wsheet.setRowView(k+2, 400, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readYszxbExcel(HttpServletRequest request,
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
			String cwbbYszxb_id = request.getParameter("cwbbYszxb_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String URL = request.getParameter("URL");  
			String newcompany = request.getParameter("newcompany");  
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
			CwbbYszxb cwbbYszxb = new CwbbYszxb();
			cwbbYszxb.setCzrdw(newcompany);
			ArrayList<CwbbYszxbZxm> CwbbYszxbZxmList = new ArrayList<CwbbYszxbZxm>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
//			if(sheet!=null&&sheet.length>0){    
//				//对每个工作表进行循环    
//				for(int i=0; i<sheet.length; i++)
//					//得到当前工作表的行数    
//					for(int j=0;j<sheet[i].getRows(); j++){
//						//得到当前行的所有单元格    
//						Cell[] cells = sheet[i].getRow(j);    
//						if(cells!=null&&cells.length>0){    
//							//对每个单元格进行循环    
//							for(int k=0;k<cells.length;k++){
//								//读取当前单元格的值    
//								String cellValue = cells[k].getContents();    
//								sb.append(cellValue+"\t");    
//							}    
//						}    
//						sb.append("\r\n");    
//				}    
//				sb.append("\r\n");    
//			}
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行高
				int c = sheet[0].getColumns();//列长
				if(r<10||c<5){
					result = "请检查文件是否正确";
				}else{
					cwbbYszxb.setBt("预算执行表");
					cwbbYszxb.setJb1(sheet[0].getCell(1,3).getContents());
					cwbbYszxb.setJb2(sheet[0].getCell(2,3).getContents());
					cwbbYszxb.setJb3(sheet[0].getCell(3,3).getContents());
					cwbbYszxb.setJb4(sheet[0].getCell(4,3).getContents());
					cwbbYszxb.setGz1(sheet[0].getCell(1,4).getContents());
					cwbbYszxb.setGz2(sheet[0].getCell(2,4).getContents());
					cwbbYszxb.setGz3(sheet[0].getCell(3,4).getContents());
					cwbbYszxb.setGz4(sheet[0].getCell(4,4).getContents());
					cwbbYszxb.setSp1(sheet[0].getCell(1,5).getContents());
					cwbbYszxb.setSp2(sheet[0].getCell(2,5).getContents());
					cwbbYszxb.setSp3(sheet[0].getCell(3,5).getContents());
					cwbbYszxb.setSp4(sheet[0].getCell(4,5).getContents());
					cwbbYszxb.setBz1(sheet[0].getCell(1,6).getContents());
					cwbbYszxb.setBz2(sheet[0].getCell(2,6).getContents());
					cwbbYszxb.setBz3(sheet[0].getCell(3,6).getContents());
					cwbbYszxb.setBz4(sheet[0].getCell(4,6).getContents());
					cwbbYszxb.setXm1(sheet[0].getCell(1,8).getContents());
					cwbbYszxb.setXm2(sheet[0].getCell(2,8).getContents());
					cwbbYszxb.setXm3(sheet[0].getCell(3,8).getContents());
					cwbbYszxb.setXm4(sheet[0].getCell(4,8).getContents());
					if(!"".equals(cwbbYszxb_id)&&cwbbYszxb_id!=null){
						cwbbYszxb.setId(Integer.parseInt(cwbbYszxb_id));
					}
					cwbbYszxb.setYear(StrToInt(year));
					cwbbYszxb.setMonth(StrToInt(month));
					
					for(int i=9;i<r; i++){
						CwbbYszxbZxm cwbbYszxbZxm = new CwbbYszxbZxm();
						if("合计".equals(sheet[0].getCell(0,i).getContents())){
							cwbbYszxb.setHj1(sheet[0].getCell(1,i).getContents());
							cwbbYszxb.setHj2(sheet[0].getCell(2,i).getContents());
							cwbbYszxb.setHj3(sheet[0].getCell(3,i).getContents());
							cwbbYszxb.setHj4(sheet[0].getCell(4,i).getContents());
							//直接结束循环
							i=r;
						}else{
							cwbbYszxbZxm.setZxmmc(sheet[0].getCell(0,i).getContents());
							cwbbYszxbZxm.setZxm1(sheet[0].getCell(1,i).getContents());
							cwbbYszxbZxm.setZxm2(sheet[0].getCell(2,i).getContents());
							cwbbYszxbZxm.setZxm3(sheet[0].getCell(3,i).getContents());
							cwbbYszxbZxm.setZxm4(sheet[0].getCell(4,i).getContents());
							CwbbYszxbZxmList.add(cwbbYszxbZxm);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbYszxb", cwbbYszxb);
			request.setAttribute("CwbbYszxbZxmList", CwbbYszxbZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbYszxbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  预算执行表汇总表——列表
	 * 
	 */
	protected void getYszxbHzb(HttpServletRequest request,
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
			ArrayList<CwbbYszxbHzb> CwbbYszxbHzbList = new ArrayList<CwbbYszxbHzb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
//			String cxcompany = request.getParameter("cxcompany");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
//				cxcompany = "";
			}
			countAll = bbsbCwbbDao.queryCwbbYszxbHzbListByBtCount(srbt);
			CwbbYszxbHzbList = bbsbCwbbDao.queryCwbbYszxbHzbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbYszxbHzbList", CwbbYszxbHzbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
//			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbYszxbHzbList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void YszxbHzbSave(HttpServletRequest request,
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
			//生成新数据或者更新旧数据
			String cwbbYszxbHzb_id = request.getParameter("cwbbYszxbHzb_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			if(!"".equals(year)&&year!=null&&!"".equals(month)&&month!=null){
				//根据时间查询各单位数据
				//公路管理处
				String glc = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"公路管理处");
				//航道管理处
				String hdc = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"航道管理处");
				//地方海事局
				String hsj = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"地方海事局");
				//运输管理处
				String ygc = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"运输管理处");
				//交通工程建设管理处
				String jsc = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"交通工程建设管理处");
				//交通工程质量监督处
				String zjc = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"交通工程质量监督处");
				//会计中心
				String kjzx = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"交通会计服务中心");
				//信息中心
				String xxzx = bbsbCwbbDao.queryCwbbYszxbBySjDW(StrToInt(year),StrToInt(month),"交通信息中心");
				cwbbYszxbHzb.setGlc(glc);
				cwbbYszxbHzb.setHdc(hdc);
				cwbbYszxbHzb.setHsj(hsj);
				cwbbYszxbHzb.setYgc(ygc);
				cwbbYszxbHzb.setJsc(jsc);
				cwbbYszxbHzb.setZjc(zjc);
				cwbbYszxbHzb.setKjzx(kjzx);
				cwbbYszxbHzb.setXxzx(xxzx);
				cwbbYszxbHzb.setYear(StrToInt(year));
				cwbbYszxbHzb.setMonth(StrToInt(month));
				cwbbYszxbHzb.setCzr(UserInfo.getName());
				cwbbYszxbHzb.setCzrID(UserInfo.getUsername());
				cwbbYszxbHzb.setCzrdw(UserInfo.getCompany());
				cwbbYszxbHzb.setCzsj(data1);
				if(!"".equals(cwbbYszxbHzb_id)&&cwbbYszxbHzb_id!=null){
					//更新
					cwbbYszxbHzb.setId(Integer.parseInt(cwbbYszxbHzb_id));
					bbsbCwbbDao.updateCwbbYszxbHzb(cwbbYszxbHzb);
				}else{
					//删除原有记录
					bbsbCwbbDao.deleteCwbbYszxbHzbByTime(StrToInt(year),StrToInt(month));
					//新增最新记录
					bbsbCwbbDao.insertCwbbYszxbHzb(cwbbYszxbHzb);
				}
			}else{
				result = "日期错误，生成失败";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getYszxbHzb&flag=1").forward(request,
					response);
			
		}
	}

	 /*
	 *  进入查看页面
	 * 
	 */
	protected void YszxbHzbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			cwbbYszxbHzb = bbsbCwbbDao.queryCwbbYszxbHzbByID(Integer.parseInt(id));
		}
		request.setAttribute("cwbbYszxbHzb", cwbbYszxbHzb);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbYszxbHzbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void YszxbHzbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbYszxbHzbById(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getYszxbHzb&flag=1");
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbYszxbHzbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(exc_id)&&exc_id!=null){
			cwbbYszxbHzb = bbsbCwbbDao.queryCwbbYszxbHzbByID(Integer.parseInt(exc_id));
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
				WritableSheet wsheet = wwb.createSheet("事业单位主要财务指标表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.CENTRE);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				//设置表头
				Label label = new Label(0,0,"预算执行表汇总表",format1);
				wsheet.addCell(label);
				label = new Label(0,1,cwbbYszxbHzb.getYear()+"年"+cwbbYszxbHzb.getMonth()+"月",format2);
				wsheet.addCell(label);
				
				label = new Label(16,2,"单位：万元",format3);
				wsheet.addCell(label);

				label = new Label(0,3,"序号",format4);
				wsheet.addCell(label);
				label = new Label(1,3,"支出类别",format4);
				wsheet.addCell(label);
				label = new Label(2,3,"公路处",format4);
				wsheet.addCell(label);
				label = new Label(4,3,"航道处",format4);
				wsheet.addCell(label);
				label = new Label(6,3,"海事局",format4);
				wsheet.addCell(label);
				label = new Label(8,3,"运管处",format4);
				wsheet.addCell(label);
				label = new Label(10,3,"建设处",format4);
				wsheet.addCell(label);
				label = new Label(12,3,"质监处",format4);
				wsheet.addCell(label);
				label = new Label(14,3,"会计中心",format4);
				wsheet.addCell(label);
				label = new Label(16,3,"信息中心",format4);
				wsheet.addCell(label);

				label = new Label(2,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(3,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(4,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(5,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(6,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(7,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(8,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(9,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(10,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(11,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(12,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(13,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(14,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(15,4,"进度数",format4);
				wsheet.addCell(label);
				label = new Label(16,4,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(17,4,"进度数",format4);
				wsheet.addCell(label);
				
				label = new Label(0,5,"1",format4);
				wsheet.addCell(label);
				label = new Label(1,5,"基本支出",format5);
				wsheet.addCell(label);
				label = new Label(0,6,"2",format4);
				wsheet.addCell(label);
				label = new Label(1,6,"项目支出",format5);
				wsheet.addCell(label);
				//公路处
				label = new Label(2,5,cwbbYszxbHzb.getGlc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(3,5,cwbbYszxbHzb.getGlc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(2,6,cwbbYszxbHzb.getGlc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(3,6,cwbbYszxbHzb.getGlc().split("#;")[3],format4);
				wsheet.addCell(label);
				//航道处
				label = new Label(4,5,cwbbYszxbHzb.getHdc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(5,5,cwbbYszxbHzb.getHdc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(4,6,cwbbYszxbHzb.getHdc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(5,6,cwbbYszxbHzb.getHdc().split("#;")[3],format4);
				wsheet.addCell(label);
				//海事局
				label = new Label(6,5,cwbbYszxbHzb.getHsj().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(7,5,cwbbYszxbHzb.getHsj().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(6,6,cwbbYszxbHzb.getHsj().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(7,6,cwbbYszxbHzb.getHsj().split("#;")[3],format4);
				wsheet.addCell(label);
				//运管处
				label = new Label(8,5,cwbbYszxbHzb.getYgc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(9,5,cwbbYszxbHzb.getYgc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(8,6,cwbbYszxbHzb.getYgc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(9,6,cwbbYszxbHzb.getYgc().split("#;")[3],format4);
				wsheet.addCell(label);
				//建设处
				label = new Label(10,5,cwbbYszxbHzb.getJsc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(11,5,cwbbYszxbHzb.getJsc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(10,6,cwbbYszxbHzb.getJsc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(11,6,cwbbYszxbHzb.getJsc().split("#;")[3],format4);
				wsheet.addCell(label);
				//质监处
				label = new Label(12,5,cwbbYszxbHzb.getZjc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(13,5,cwbbYszxbHzb.getZjc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(12,6,cwbbYszxbHzb.getZjc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(13,6,cwbbYszxbHzb.getZjc().split("#;")[3],format4);
				wsheet.addCell(label);
				//会计中心
				label = new Label(14,5,cwbbYszxbHzb.getKjzx().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(15,5,cwbbYszxbHzb.getKjzx().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(14,6,cwbbYszxbHzb.getKjzx().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(15,6,cwbbYszxbHzb.getKjzx().split("#;")[3],format4);
				wsheet.addCell(label);
				//信息中心
				label = new Label(16,5,cwbbYszxbHzb.getXxzx().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(17,5,cwbbYszxbHzb.getXxzx().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(16,6,cwbbYszxbHzb.getXxzx().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(17,6,cwbbYszxbHzb.getXxzx().split("#;")[3],format4);
				wsheet.addCell(label);
				//合并单元格（列、行、列、行）
				wsheet.mergeCells(0, 0, 17, 0);
				wsheet.mergeCells(0, 1, 17, 1);
				wsheet.mergeCells(0, 3, 0, 4);
				wsheet.mergeCells(1, 3, 1, 4);
				wsheet.mergeCells(2, 3, 3, 3);
				wsheet.mergeCells(4, 3, 5, 3);
				wsheet.mergeCells(6, 3, 7, 3);
				wsheet.mergeCells(8, 3, 9, 3);
				wsheet.mergeCells(10, 3, 11, 3);
				wsheet.mergeCells(12, 3, 13, 3);
				wsheet.mergeCells(14, 3, 15, 3);
				wsheet.mergeCells(16, 3, 17, 3);
				//设置列宽
				wsheet.setColumnView(0, 5);
				wsheet.setColumnView(1, 10);
				for(int c=2; c<10; c++){
					wsheet.setColumnView(c, 15);
				}
				//设置行宽
				for(int r=0; r<8; r++){
					wsheet.setRowView(r, 400, false);
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
	 *  三公经费表——刷新菜单树
	 * 
	 */
	protected void getCwbbSgjfb(HttpServletRequest request,
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
			str = menuDao.QueryAllMenuInfoName("node_sgjf");
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/getCwbbSgjfb.jsp").forward(request,
					response);
		}
	}

	/*
	 *  重置菜单（三公经费表）
	 * 
	 */
	protected void resetMenuSgjf(HttpServletRequest request,
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
			menuDao.DeleteMenuName("node_sgjf");
			menuDao.ResetZzxxMenu("node_sgjf",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getCwbbSgjfb&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  三公经费表——列表
	 * 
	 */
	protected void getSgjfb(HttpServletRequest request,
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
			
		}else if(UserPer.getSgjfsy().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//查询列表
			ArrayList<CwbbSgjfb> CwbbSgjfbList = new ArrayList<CwbbSgjfb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			String srbt = request.getParameter("srbt");
//			String cxyear = request.getParameter("cxyear");
//			String cxmonth = request.getParameter("cxmonth");
			String cxcompany = request.getParameter("cxcompany");
//			String menuname = request.getParameter("menuname");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
//				cxyear = "";
//				cxmonth = "";
//				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
			}
			countAll = bbsbCwbbDao.queryCwbbSgjfbListByBtCount(srbt);
			CwbbSgjfbList = bbsbCwbbDao.queryCwbbSgjfbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbSgjfbList", CwbbSgjfbList);
			request.setAttribute("srbt", srbt);
//			request.setAttribute("cxyear", cxyear);
//			request.setAttribute("cxmonth", cxmonth);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSgjfbList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void sgjfbEdit(HttpServletRequest request,
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
			//部分会计可以为多家单位新增本表，带入的上月内容需要区分
			String srcompany = request.getParameter("srcompany");
			CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			if(!"".equals(id)&&id!=null){
				cwbbSgjfb = bbsbCwbbDao.queryCwbbSgjfbByID(Integer.parseInt(id));
			}else{
				if("".equals(srcompany)||srcompany==null){
					srcompany = UserInfo.getCompany();
				}
				//新增时，将上次内容带入
				Calendar cal=Calendar.getInstance();
				int nowYear = cal.get(Calendar.YEAR);
				int nowMonth = cal.get(Calendar.MONTH)+1;
				//通过单位查询（1月份不用带入）
				if(nowMonth!=1){
					cwbbSgjfb = bbsbCwbbDao.queryCwbbSgjfbByDWSJ(nowYear,srcompany);
				}
				cwbbSgjfb.setCzrdw(srcompany);
			}
			request.setAttribute("cwbbSgjfb", cwbbSgjfb);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSgjfbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void sgjfbSave(HttpServletRequest request,
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
			String cwbbSgjfb_id = request.getParameter("cwbbSgjfb_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String newcompany = request.getParameter("newcompany");
			String hj1 = request.getParameter("hj1");
			String hj2 = request.getParameter("hj2");
			String hj3 = request.getParameter("hj3");
			String cgf1 = request.getParameter("cgf1");
			String cgf2 = request.getParameter("cgf2");
			String cgf3 = request.getParameter("cgf3");
			String jdf1 = request.getParameter("jdf1");
			String jdf2 = request.getParameter("jdf2");
			String jdf3 = request.getParameter("jdf3");
			String jbzc1 = request.getParameter("jbzc1");
			String jbzc2 = request.getParameter("jbzc2");
			String jbzc3 = request.getParameter("jbzc3");
			String xmzc1 = request.getParameter("xmzc1");
			String xmzc2 = request.getParameter("xmzc2");
			String xmzc3 = request.getParameter("xmzc3");
			String ycf1 = request.getParameter("ycf1");
			String ycf2 = request.getParameter("ycf2");
			String ycf3 = request.getParameter("ycf3");
			String gzf1 = request.getParameter("gzf1");
			String gzf2 = request.getParameter("gzf2");
			String gzf3 = request.getParameter("gzf3");
			String whf1 = request.getParameter("whf1");
			String whf2 = request.getParameter("whf2");
			String whf3 = request.getParameter("whf3");
			String hyf1 = request.getParameter("hyf1");
			String hyf2 = request.getParameter("hyf2");
			String hyf3 = request.getParameter("hyf3");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱3：驳回
			CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
			cwbbSgjfb.setYear(StrToInt(year));
			cwbbSgjfb.setMonth(StrToInt(month));
			cwbbSgjfb.setCzr(UserInfo.getName());
			cwbbSgjfb.setCzrID(UserInfo.getUsername());
			cwbbSgjfb.setCzrdw(newcompany);
			cwbbSgjfb.setCzsj(data1);
			cwbbSgjfb.setHj1(hj1);
			cwbbSgjfb.setHj2(hj2);
			cwbbSgjfb.setHj3(hj3);
			cwbbSgjfb.setCgf1(cgf1);
			cwbbSgjfb.setCgf2(cgf2);
			cwbbSgjfb.setCgf3(cgf3);
			cwbbSgjfb.setJdf1(jdf1);
			cwbbSgjfb.setJdf2(jdf2);
			cwbbSgjfb.setJdf3(jdf3);
			cwbbSgjfb.setJbzc1(jbzc1);
			cwbbSgjfb.setJbzc2(jbzc2);
			cwbbSgjfb.setJbzc3(jbzc3);
			cwbbSgjfb.setXmzc1(xmzc1);
			cwbbSgjfb.setXmzc2(xmzc2);
			cwbbSgjfb.setXmzc3(xmzc3);
			cwbbSgjfb.setYcf1(ycf1);
			cwbbSgjfb.setYcf2(ycf2);
			cwbbSgjfb.setYcf3(ycf3);
			cwbbSgjfb.setGzf1(gzf1);
			cwbbSgjfb.setGzf2(gzf2);
			cwbbSgjfb.setGzf3(gzf3);
			cwbbSgjfb.setWhf1(whf1);
			cwbbSgjfb.setWhf2(whf2);
			cwbbSgjfb.setWhf3(whf3);
			cwbbSgjfb.setHyf1(hyf1);
			cwbbSgjfb.setHyf2(hyf2);
			cwbbSgjfb.setHyf3(hyf3);
			cwbbSgjfb.setTjzt(tjzt);
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//新增或修改主表
			if(!"".equals(cwbbSgjfb_id)&&cwbbSgjfb_id!=null){
				cwbbSgjfb.setId(Integer.parseInt(cwbbSgjfb_id));
				bbsbCwbbDao.updateCwbbSgjfb(cwbbSgjfb);
			}else{
				cwbbSgjfb_id = bbsbCwbbDao.insertCwbbSgjfb(cwbbSgjfb)+"";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getCwbbSgjfb").forward(request,
					response);
			
		}
	}

	/*
	 * 进入驳回
	 * 
	 */
	protected void sgjfbReturn(HttpServletRequest request,
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
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbCwbbDao.updateCwbbSgjfbRet(id);
				String dxnr = UserInfo.getName()+"：您有一份三公经费表被驳回，请及时查看";
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
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getCwbbSgjfb").forward(request,
					response);
		}
	}

	 /*
	 *  进入查看页面
	 * 
	 */
	protected void sgjfbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			cwbbSgjfb = bbsbCwbbDao.queryCwbbSgjfbByID(Integer.parseInt(id));
		}
		request.setAttribute("cwbbSgjfb", cwbbSgjfb);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSgjfbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void sgjfbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbSgjfbById(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getSgjfb&flag=1&cxcompany="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbSgjfbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(exc_id)&&exc_id!=null){
			cwbbSgjfb = bbsbCwbbDao.queryCwbbSgjfbByID(Integer.parseInt(exc_id));
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
				WritableSheet wsheet = wwb.createSheet("三公经费表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbSgjfb.getYear()+"年1-"+cwbbSgjfb.getMonth()+"月“三公”经费预算财政拨款情况表",format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbSgjfb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,"单位：万元",format3);
				wsheet.addCell(label);

				label = new Label(0,2,"项目",format4);
				wsheet.addCell(label);
				label = new Label(1,2,"预算数",format4);
				wsheet.addCell(label);
				label = new Label(2,2,"支出数",format4);
				wsheet.addCell(label);
				label = new Label(3,2,"支出比例%",format4);
				wsheet.addCell(label);
				
				label = new Label(0,3,"合	计",format4);
				wsheet.addCell(label);
				label = new Label(1,3,cwbbSgjfb.getHj1(),format4);
				wsheet.addCell(label);
				label = new Label(2,3,cwbbSgjfb.getHj2(),format4);
				wsheet.addCell(label);
				label = new Label(3,3,cwbbSgjfb.getHj3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,4,"1、因公出国（境）费用",format5);
				wsheet.addCell(label);
				label = new Label(1,4,cwbbSgjfb.getCgf1(),format4);
				wsheet.addCell(label);
				label = new Label(2,4,cwbbSgjfb.getCgf2(),format4);
				wsheet.addCell(label);
				label = new Label(3,4,cwbbSgjfb.getCgf3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,5,"2、公务接待费",format5);
				wsheet.addCell(label);
				label = new Label(1,5,cwbbSgjfb.getJdf1(),format4);
				wsheet.addCell(label);
				label = new Label(2,5,cwbbSgjfb.getJdf2(),format4);
				wsheet.addCell(label);
				label = new Label(3,5,cwbbSgjfb.getJdf3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,6,"其中：（1）基本支出列支",format5);
				wsheet.addCell(label);
				label = new Label(1,6,cwbbSgjfb.getJbzc1(),format4);
				wsheet.addCell(label);
				label = new Label(2,6,cwbbSgjfb.getJbzc2(),format4);
				wsheet.addCell(label);
				label = new Label(3,6,cwbbSgjfb.getJbzc3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,7,"      （2）项目支出列支",format5);
				wsheet.addCell(label);
				label = new Label(1,7,cwbbSgjfb.getXmzc1(),format4);
				wsheet.addCell(label);
				label = new Label(2,7,cwbbSgjfb.getXmzc2(),format4);
				wsheet.addCell(label);
				label = new Label(3,7,cwbbSgjfb.getXmzc3(),format4);
				wsheet.addCell(label);

				
				label = new Label(0,8,"3、公务用车费",format5);
				wsheet.addCell(label);
				label = new Label(1,8,cwbbSgjfb.getYcf1(),format4);
				wsheet.addCell(label);
				label = new Label(2,8,cwbbSgjfb.getYcf2(),format4);
				wsheet.addCell(label);
				label = new Label(3,8,cwbbSgjfb.getYcf3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,9,"其中：（1）车辆购置费",format5);
				wsheet.addCell(label);
				label = new Label(1,9,cwbbSgjfb.getGzf1(),format4);
				wsheet.addCell(label);
				label = new Label(2,9,cwbbSgjfb.getGzf2(),format4);
				wsheet.addCell(label);
				label = new Label(3,9,cwbbSgjfb.getGzf3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,10,"      （2）车辆运行维护费",format5);
				wsheet.addCell(label);
				label = new Label(1,10,cwbbSgjfb.getWhf1(),format4);
				wsheet.addCell(label);
				label = new Label(2,10,cwbbSgjfb.getWhf2(),format4);
				wsheet.addCell(label);
				label = new Label(3,10,cwbbSgjfb.getWhf3(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,11,"4、会议费",format5);
				wsheet.addCell(label);
				label = new Label(1,11,cwbbSgjfb.getHyf1(),format4);
				wsheet.addCell(label);
				label = new Label(2,11,cwbbSgjfb.getHyf2(),format4);
				wsheet.addCell(label);
				label = new Label(3,11,cwbbSgjfb.getHyf3(),format4);
				wsheet.addCell(label);

				//合并单元格
				wsheet.mergeCells(0, 0, 3, 0);
				//设置列宽
				wsheet.setColumnView(0, 45);
				wsheet.setColumnView(1, 20);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<10; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readSgjfbExcel(HttpServletRequest request,
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
			String cwbbSgjfb_id = request.getParameter("cwbbSgjfb_id");
			String year = request.getParameter("year");
			String czrdw = request.getParameter("czrdw");
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
			CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
			cwbbSgjfb.setCzrdw(czrdw);
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行高
				int c = sheet[0].getColumns();//列长
				if(r<12||c<3){
					result = "请检查文件是否正确";
				}else{
					cwbbSgjfb.setHj1(sheet[0].getCell(1,3).getContents());
					cwbbSgjfb.setHj2(sheet[0].getCell(2,3).getContents());
					cwbbSgjfb.setCgf1(sheet[0].getCell(1,4).getContents());
					cwbbSgjfb.setCgf2(sheet[0].getCell(2,4).getContents());
					cwbbSgjfb.setJdf1(sheet[0].getCell(1,5).getContents());
					cwbbSgjfb.setJdf2(sheet[0].getCell(2,5).getContents());
					cwbbSgjfb.setJbzc1(sheet[0].getCell(1,6).getContents());
					cwbbSgjfb.setJbzc2(sheet[0].getCell(2,6).getContents());
					cwbbSgjfb.setXmzc1(sheet[0].getCell(1,7).getContents());
					cwbbSgjfb.setXmzc2(sheet[0].getCell(2,7).getContents());
					cwbbSgjfb.setYcf1(sheet[0].getCell(1,8).getContents());
					cwbbSgjfb.setYcf2(sheet[0].getCell(2,8).getContents());
					cwbbSgjfb.setGzf1(sheet[0].getCell(1,9).getContents());
					cwbbSgjfb.setGzf2(sheet[0].getCell(2,9).getContents());
					cwbbSgjfb.setWhf1(sheet[0].getCell(1,10).getContents());
					cwbbSgjfb.setWhf2(sheet[0].getCell(2,10).getContents());
					cwbbSgjfb.setHyf1(sheet[0].getCell(1,11).getContents());
					cwbbSgjfb.setHyf2(sheet[0].getCell(2,11).getContents());
					if(!"".equals(cwbbSgjfb_id)&&cwbbSgjfb_id!=null){
						cwbbSgjfb.setId(Integer.parseInt(cwbbSgjfb_id));
					}
					cwbbSgjfb.setYear(StrToInt(year));
					cwbbSgjfb.setMonth(StrToInt(month));
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbSgjfb", cwbbSgjfb);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSgjfbEdit.jsp").forward(request,
					response);
		}
	}

	/*
	 *  事业单位主要财务指标表——列表
	 * 
	 */
	protected void getSydwzycwzbb(HttpServletRequest request,
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
			ArrayList<CwbbSydwzycwzbb> CwbbSydwzycwzbbList = new ArrayList<CwbbSydwzycwzbb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
			String cxcompany = request.getParameter("cxcompany");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbCwbbDao.queryCwbbSydwzycwzbbListByBtCount(srbt);
			CwbbSydwzycwzbbList = bbsbCwbbDao.queryCwbbSydwzycwzbbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbSydwzycwzbbList", CwbbSydwzycwzbbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSydwzycwzbbList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void sydwzycwzbbEdit(HttpServletRequest request,
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
			//部分会计可以为多家单位新增本表
			String srcompany = request.getParameter("srcompany");
			if("".equals(srcompany)||srcompany==null){
				srcompany = UserInfo.getCompany();
			}
			CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			if(!"".equals(id)&&id!=null){
				cwbbSydwzycwzbb = bbsbCwbbDao.queryCwbbSydwzycwzbbByID(Integer.parseInt(id));
			}else{
				cwbbSydwzycwzbb.setCzrdw(srcompany);
			}
			request.setAttribute("cwbbSydwzycwzbb", cwbbSydwzycwzbb);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSydwzycwzbbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void sydwzycwzbbSave(HttpServletRequest request,
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
			String cwbbSydwzycwzbb_id = request.getParameter("cwbbSydwzycwzbb_id");
			String bt= request.getParameter("bt");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String newcompany = request.getParameter("newcompany");
			String zc = request.getParameter("zc");
			String zq = request.getParameter("zq");
			String dwtz = request.getParameter("dwtz");
			String gdzc = request.getParameter("gdzc");
			String hbzj = request.getParameter("hbzj");
			String fzze = request.getParameter("fzze");
			String fzye = request.getParameter("fzye");
			String jzc = request.getParameter("jzc");
			String jzczy = request.getParameter("jzczy");
			String jzcsy = request.getParameter("jzcsy");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱3：驳回
			CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
			cwbbSydwzycwzbb.setBt(bt);
			cwbbSydwzycwzbb.setYear(StrToInt(year));
			cwbbSydwzycwzbb.setMonth(StrToInt(month));
			cwbbSydwzycwzbb.setCzr(UserInfo.getName());
			cwbbSydwzycwzbb.setCzrID(UserInfo.getUsername());
			cwbbSydwzycwzbb.setCzrdw(newcompany);
			cwbbSydwzycwzbb.setCzsj(data1);
			cwbbSydwzycwzbb.setZc(zc);
			cwbbSydwzycwzbb.setZq(zq);
			cwbbSydwzycwzbb.setDwtz(dwtz);
			cwbbSydwzycwzbb.setGdzc(gdzc);
			cwbbSydwzycwzbb.setHbzj(hbzj);
			cwbbSydwzycwzbb.setFzze(fzze);
			cwbbSydwzycwzbb.setFzye(fzye);
			cwbbSydwzycwzbb.setJzc(jzc);
			cwbbSydwzycwzbb.setJzczy(jzczy);
			cwbbSydwzycwzbb.setJzcsy(jzcsy);
			cwbbSydwzycwzbb.setTjzt(tjzt);
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//新增或修改主表
			if(!"".equals(cwbbSydwzycwzbb_id)&&cwbbSydwzycwzbb_id!=null){
				cwbbSydwzycwzbb.setId(Integer.parseInt(cwbbSydwzycwzbb_id));
				bbsbCwbbDao.updateCwbbSydwzycwzbb(cwbbSydwzycwzbb);
			}else{
				cwbbSydwzycwzbb_id = bbsbCwbbDao.insertCwbbSydwzycwzbb(cwbbSydwzycwzbb)+"";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getSydwzycwzbb&flag=1&menuname="+newcompany).forward(request,
					response);
			
		}
	}

	/*
	 * 进入驳回
	 * 
	 */
	protected void sydwzycwzbbReturn(HttpServletRequest request,
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
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbCwbbDao.updateCwbbSydwzycwzbbRet(id);
				String dxnr = UserInfo.getName()+"：您有一份事业单位主要指标表被驳回，请及时查看";
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
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getSydwzycwzbb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void sydwzycwzbbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			cwbbSydwzycwzbb = bbsbCwbbDao.queryCwbbSydwzycwzbbByID(Integer.parseInt(id));
		}
		request.setAttribute("cwbbSydwzycwzbb", cwbbSydwzycwzbb);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSydwzycwzbbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void sydwzycwzbbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbSydwzycwzbbById(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getSydwzycwzbb&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbSydwzycwzbbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(exc_id)&&exc_id!=null){
			cwbbSydwzycwzbb = bbsbCwbbDao.queryCwbbSydwzycwzbbByID(Integer.parseInt(exc_id));
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
				WritableSheet wsheet = wwb.createSheet("事业单位主要财务指标表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	
				
				//设置表头
				Label label = new Label(0,0,cwbbSydwzycwzbb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,cwbbSydwzycwzbb.getYear()+"年"+cwbbSydwzycwzbb.getMonth()+"月",format6);
				wsheet.addCell(label);
				
				label = new Label(0,2,"单位："+cwbbSydwzycwzbb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(2,2,"单位：万元",format3);
				wsheet.addCell(label);

				label = new Label(0,3,"序号",format4);
				wsheet.addCell(label);
				label = new Label(1,3,"指标名称",format4);
				wsheet.addCell(label);
				label = new Label(2,3,"金额",format4);
				wsheet.addCell(label);
				
				label = new Label(0,4,"1",format4);
				wsheet.addCell(label);
				label = new Label(1,4,"资产总额（总规模）",format5);
				wsheet.addCell(label);
				label = new Label(2,4,cwbbSydwzycwzbb.getZc(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,5,"2",format4);
				wsheet.addCell(label);
				label = new Label(1,5,"债权总额",format5);
				wsheet.addCell(label);
				label = new Label(2,5,cwbbSydwzycwzbb.getZq(),format4);
				wsheet.addCell(label);

				
				label = new Label(0,6,"3",format4);
				wsheet.addCell(label);
				label = new Label(1,6,"对外投资总额",format5);
				wsheet.addCell(label);
				label = new Label(2,6,cwbbSydwzycwzbb.getDwtz(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,7,"4",format4);
				wsheet.addCell(label);
				label = new Label(1,7,"固定资产总额",format5);
				wsheet.addCell(label);
				label = new Label(2,7,cwbbSydwzycwzbb.getGdzc(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,8,"5",format4);
				wsheet.addCell(label);
				label = new Label(1,8,"货币资金",format5);
				wsheet.addCell(label);
				label = new Label(2,8,cwbbSydwzycwzbb.getHbzj(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,9,"6",format4);
				wsheet.addCell(label);
				label = new Label(1,9,"负债总额",format5);
				wsheet.addCell(label);
				label = new Label(2,9,cwbbSydwzycwzbb.getFzze(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,10,"7",format4);
				wsheet.addCell(label);
				label = new Label(1,10,"负债总额中银行贷款余额",format5);
				wsheet.addCell(label);
				label = new Label(2,10,cwbbSydwzycwzbb.getFzye(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,11,"8",format4);
				wsheet.addCell(label);
				label = new Label(1,11,"净资产总额",format5);
				wsheet.addCell(label);
				label = new Label(2,11,cwbbSydwzycwzbb.getJzc(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,12,"9",format4);
				wsheet.addCell(label);
				label = new Label(1,12,"净资产中专用基金总额",format5);
				wsheet.addCell(label);
				label = new Label(2,12,cwbbSydwzycwzbb.getJzczy(),format4);
				wsheet.addCell(label);
				
				label = new Label(0,13,"10",format4);
				wsheet.addCell(label);
				label = new Label(1,13,"净资产中事业结余总额",format5);
				wsheet.addCell(label);
				label = new Label(2,13,cwbbSydwzycwzbb.getJzcsy(),format4);
				wsheet.addCell(label);

				//合并单元格
				wsheet.mergeCells(0, 0, 2, 0);
				wsheet.mergeCells(0, 1, 2, 1);
				//设置列宽
				wsheet.setColumnView(0, 20);
				wsheet.setColumnView(1, 40);
				wsheet.setColumnView(2, 40);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				for(int k=1; k<14; k++){
					wsheet.setRowView(k, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 

	public void readSydwzycwzbbExcel(HttpServletRequest request,
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
			String cwbbSydwzycwzbb_id = request.getParameter("cwbbSydwzycwzbb_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String URL = request.getParameter("URL");
			String newcompany = request.getParameter("newcompany");  
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
			CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
			cwbbSydwzycwzbb.setCzrdw(newcompany);
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行高
				int c = sheet[0].getColumns();//列长
				if(r<14||c<3){
					result = "请检查文件是否正确";
				}else{
					cwbbSydwzycwzbb.setBt("交通事业单位主要财务指标表");
					cwbbSydwzycwzbb.setZc(sheet[0].getCell(2,4).getContents());
					cwbbSydwzycwzbb.setZq(sheet[0].getCell(2,5).getContents());
					cwbbSydwzycwzbb.setDwtz(sheet[0].getCell(2,6).getContents());
					cwbbSydwzycwzbb.setGdzc(sheet[0].getCell(2,7).getContents());
					cwbbSydwzycwzbb.setHbzj(sheet[0].getCell(2,8).getContents());
					cwbbSydwzycwzbb.setFzze(sheet[0].getCell(2,9).getContents());
					cwbbSydwzycwzbb.setFzye(sheet[0].getCell(2,10).getContents());
					cwbbSydwzycwzbb.setJzc(sheet[0].getCell(2,11).getContents());
					cwbbSydwzycwzbb.setJzczy(sheet[0].getCell(2,12).getContents());
					cwbbSydwzycwzbb.setJzcsy(sheet[0].getCell(2,13).getContents());
					if(!"".equals(cwbbSydwzycwzbb_id)&&cwbbSydwzycwzbb_id!=null){
						cwbbSydwzycwzbb.setId(Integer.parseInt(cwbbSydwzycwzbb_id));
					}
					cwbbSydwzycwzbb.setYear(StrToInt(year));
					cwbbSydwzycwzbb.setMonth(StrToInt(month));
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbSydwzycwzbb", cwbbSydwzycwzbb);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSydwzycwzbbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  交通事业单位主要财务指标状况汇总表——列表
	 * 
	 */
	protected void getSydwzycwzbbHzb(HttpServletRequest request,
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
			ArrayList<CwbbSydwzycwzbbHzb> CwbbSydwzycwzbbHzbList = new ArrayList<CwbbSydwzycwzbbHzb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
//			String cxcompany = request.getParameter("cxcompany");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
//				cxcompany = "";
			}
			countAll = bbsbCwbbDao.queryCwbbSydwzycwzbbHzbListByBtCount(srbt);
			CwbbSydwzycwzbbHzbList = bbsbCwbbDao.queryCwbbSydwzycwzbbHzbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbSydwzycwzbbHzbList", CwbbSydwzycwzbbHzbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
//			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSydwzycwzbbHzbList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void SydwzycwzbbHzbSave(HttpServletRequest request,
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
			//生成新数据或者更新旧数据
			String cwbbSydwzycwzbbHzb_id = request.getParameter("cwbbSydwzycwzbbHzb_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			if(!"".equals(year)&&year!=null&&!"".equals(month)&&month!=null){
				//根据时间查询各单位数据
				//公路管理处
				String glc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"公路管理处");
				//航道管理处
				String hdc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"航道管理处");
				//地方海事局
				String hsj = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"地方海事局");
				//运输管理处
				String ygc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"运输管理处");
				//交通工程建设管理处
				String jsc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"交通工程建设管理处");
				//交通工程质量监督处
				String zjc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"交通工程质量监督处");
				//会计中心
				String kjzx = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"交通会计服务中心");
				//信息中心
				String xxzx = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(StrToInt(year),StrToInt(month),"交通信息中心");
				cwbbSydwzycwzbbHzb.setGlc(glc);
				cwbbSydwzycwzbbHzb.setHdc(hdc);
				cwbbSydwzycwzbbHzb.setHsj(hsj);
				cwbbSydwzycwzbbHzb.setYgc(ygc);
				cwbbSydwzycwzbbHzb.setJsc(jsc);
				cwbbSydwzycwzbbHzb.setZjc(zjc);
				cwbbSydwzycwzbbHzb.setKjzx(kjzx);
				cwbbSydwzycwzbbHzb.setXxzx(xxzx);
				cwbbSydwzycwzbbHzb.setYear(StrToInt(year));
				cwbbSydwzycwzbbHzb.setMonth(StrToInt(month));
				cwbbSydwzycwzbbHzb.setCzr(UserInfo.getName());
				cwbbSydwzycwzbbHzb.setCzrID(UserInfo.getUsername());
				cwbbSydwzycwzbbHzb.setCzrdw(UserInfo.getCompany());
				cwbbSydwzycwzbbHzb.setCzsj(data1);
				if(!"".equals(cwbbSydwzycwzbbHzb_id)&&cwbbSydwzycwzbbHzb_id!=null){
					//更新
					cwbbSydwzycwzbbHzb.setId(Integer.parseInt(cwbbSydwzycwzbbHzb_id));
					bbsbCwbbDao.updateCwbbSydwzycwzbbHzb(cwbbSydwzycwzbbHzb);
				}else{
					//删除原有记录
					bbsbCwbbDao.deleteCwbbSydwzycwzbbHzbByTime(StrToInt(year),StrToInt(month));
					//新增最新记录
					bbsbCwbbDao.insertCwbbSydwzycwzbbHzb(cwbbSydwzycwzbbHzb);
				}
			}else{
				result = "日期错误，生成失败";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getSydwzycwzbbHzb&flag=1").forward(request,
					response);
			
		}
	}

	 /*
	 *  进入查看页面
	 * 
	 */
	protected void SydwzycwzbbHzbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			cwbbSydwzycwzbbHzb = bbsbCwbbDao.queryCwbbSydwzycwzbbHzbByID(Integer.parseInt(id));
		}
		request.setAttribute("cwbbSydwzycwzbbHzb", cwbbSydwzycwzbbHzb);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbSydwzycwzbbHzbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void SydwzycwzbbHzbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbSydwzycwzbbHzbById(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getSydwzycwzbbHzb&flag=1");
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbSydwzycwzbbHzbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String exc_id = request.getParameter("exc_id");
		CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(exc_id)&&exc_id!=null){
			cwbbSydwzycwzbbHzb = bbsbCwbbDao.queryCwbbSydwzycwzbbHzbByID(Integer.parseInt(exc_id));
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
				WritableSheet wsheet = wwb.createSheet("事业单位主要财务指标表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.CENTRE);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				//设置表头
				Label label = new Label(0,0,"交通事业单位主要财务指标状况汇总表",format1);
				wsheet.addCell(label);
				label = new Label(0,1,cwbbSydwzycwzbbHzb.getYear()+"年"+cwbbSydwzycwzbbHzb.getMonth()+"月",format2);
				wsheet.addCell(label);
				
				label = new Label(9,2,"单位：万元",format3);
				wsheet.addCell(label);

				label = new Label(0,3,"序号",format4);
				wsheet.addCell(label);
				label = new Label(1,3,"指标名称",format4);
				wsheet.addCell(label);
				label = new Label(2,3,"公路处",format4);
				wsheet.addCell(label);
				label = new Label(3,3,"航道处",format4);
				wsheet.addCell(label);
				label = new Label(4,3,"海事局",format4);
				wsheet.addCell(label);
				label = new Label(5,3,"运管处",format4);
				wsheet.addCell(label);
				label = new Label(6,3,"建设处",format4);
				wsheet.addCell(label);
				label = new Label(7,3,"质监处",format4);
				wsheet.addCell(label);
				label = new Label(8,3,"会计中心",format4);
				wsheet.addCell(label);
				label = new Label(9,3,"信息中心",format4);
				wsheet.addCell(label);
				
				label = new Label(0,4,"1",format4);
				wsheet.addCell(label);
				label = new Label(1,4,"资产总额（总规模）",format5);
				wsheet.addCell(label);
				label = new Label(0,5,"2",format4);
				wsheet.addCell(label);
				label = new Label(1,5,"债权总额",format5);
				wsheet.addCell(label);
				label = new Label(0,6,"3",format4);
				wsheet.addCell(label);
				label = new Label(1,6,"对外投资总额",format5);
				wsheet.addCell(label);
				label = new Label(0,7,"4",format4);
				wsheet.addCell(label);
				label = new Label(1,7,"固定资产总额",format5);
				wsheet.addCell(label);
				label = new Label(0,8,"5",format4);
				wsheet.addCell(label);
				label = new Label(1,8,"货币资金",format5);
				wsheet.addCell(label);
				label = new Label(0,9,"6",format4);
				wsheet.addCell(label);
				label = new Label(1,9,"负债总额",format5);
				wsheet.addCell(label);
				label = new Label(0,10,"7",format4);
				wsheet.addCell(label);
				label = new Label(1,10,"负债总额中银行贷款余额",format5);
				wsheet.addCell(label);
				label = new Label(0,11,"8",format4);
				wsheet.addCell(label);
				label = new Label(1,11,"净资产总额",format5);
				wsheet.addCell(label);
				label = new Label(0,12,"9",format4);
				wsheet.addCell(label);
				label = new Label(1,12,"净资产中专用基金总额",format5);
				wsheet.addCell(label);
				label = new Label(0,13,"10",format4);
				wsheet.addCell(label);
				label = new Label(1,13,"净资产中事业结余总额",format5);
				wsheet.addCell(label);
				//公路处
				label = new Label(2,4,cwbbSydwzycwzbbHzb.getGlc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(2,5,cwbbSydwzycwzbbHzb.getGlc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(2,6,cwbbSydwzycwzbbHzb.getGlc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(2,7,cwbbSydwzycwzbbHzb.getGlc().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(2,8,cwbbSydwzycwzbbHzb.getGlc().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(2,9,cwbbSydwzycwzbbHzb.getGlc().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(2,10,cwbbSydwzycwzbbHzb.getGlc().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(2,11,cwbbSydwzycwzbbHzb.getGlc().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(2,12,cwbbSydwzycwzbbHzb.getGlc().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(2,13,cwbbSydwzycwzbbHzb.getGlc().split("#;")[9],format4);
				wsheet.addCell(label);
				//航道处
				label = new Label(3,4,cwbbSydwzycwzbbHzb.getHdc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(3,5,cwbbSydwzycwzbbHzb.getHdc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(3,6,cwbbSydwzycwzbbHzb.getHdc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(3,7,cwbbSydwzycwzbbHzb.getHdc().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(3,8,cwbbSydwzycwzbbHzb.getHdc().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(3,9,cwbbSydwzycwzbbHzb.getHdc().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(3,10,cwbbSydwzycwzbbHzb.getHdc().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(3,11,cwbbSydwzycwzbbHzb.getHdc().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(3,12,cwbbSydwzycwzbbHzb.getHdc().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(3,13,cwbbSydwzycwzbbHzb.getHdc().split("#;")[9],format4);
				wsheet.addCell(label);
				//海事局
				label = new Label(4,4,cwbbSydwzycwzbbHzb.getHsj().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(4,5,cwbbSydwzycwzbbHzb.getHsj().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(4,6,cwbbSydwzycwzbbHzb.getHsj().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(4,7,cwbbSydwzycwzbbHzb.getHsj().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(4,8,cwbbSydwzycwzbbHzb.getHsj().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(4,9,cwbbSydwzycwzbbHzb.getHsj().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(4,10,cwbbSydwzycwzbbHzb.getHsj().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(4,11,cwbbSydwzycwzbbHzb.getHsj().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(4,12,cwbbSydwzycwzbbHzb.getHsj().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(4,13,cwbbSydwzycwzbbHzb.getHsj().split("#;")[9],format4);
				wsheet.addCell(label);
				//运管处
				label = new Label(5,4,cwbbSydwzycwzbbHzb.getYgc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(5,5,cwbbSydwzycwzbbHzb.getYgc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(5,6,cwbbSydwzycwzbbHzb.getYgc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(5,7,cwbbSydwzycwzbbHzb.getYgc().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(5,8,cwbbSydwzycwzbbHzb.getYgc().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(5,9,cwbbSydwzycwzbbHzb.getYgc().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(5,10,cwbbSydwzycwzbbHzb.getYgc().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(5,11,cwbbSydwzycwzbbHzb.getYgc().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(5,12,cwbbSydwzycwzbbHzb.getYgc().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(5,13,cwbbSydwzycwzbbHzb.getYgc().split("#;")[9],format4);
				wsheet.addCell(label);
				//建设处
				label = new Label(6,4,cwbbSydwzycwzbbHzb.getJsc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(6,5,cwbbSydwzycwzbbHzb.getJsc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(6,6,cwbbSydwzycwzbbHzb.getJsc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(6,7,cwbbSydwzycwzbbHzb.getJsc().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(6,8,cwbbSydwzycwzbbHzb.getJsc().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(6,9,cwbbSydwzycwzbbHzb.getJsc().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(6,10,cwbbSydwzycwzbbHzb.getJsc().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(6,11,cwbbSydwzycwzbbHzb.getJsc().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(6,12,cwbbSydwzycwzbbHzb.getJsc().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(6,13,cwbbSydwzycwzbbHzb.getJsc().split("#;")[9],format4);
				wsheet.addCell(label);
				//质监处
				label = new Label(7,4,cwbbSydwzycwzbbHzb.getZjc().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(7,5,cwbbSydwzycwzbbHzb.getZjc().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(7,6,cwbbSydwzycwzbbHzb.getZjc().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(7,7,cwbbSydwzycwzbbHzb.getZjc().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(7,8,cwbbSydwzycwzbbHzb.getZjc().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(7,9,cwbbSydwzycwzbbHzb.getZjc().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(7,10,cwbbSydwzycwzbbHzb.getZjc().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(7,11,cwbbSydwzycwzbbHzb.getZjc().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(7,12,cwbbSydwzycwzbbHzb.getZjc().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(7,13,cwbbSydwzycwzbbHzb.getZjc().split("#;")[9],format4);
				wsheet.addCell(label);
				//会计中心
				label = new Label(8,4,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(8,5,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(8,6,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(8,7,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(8,8,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(8,9,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(8,10,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(8,11,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(8,12,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(8,13,cwbbSydwzycwzbbHzb.getKjzx().split("#;")[9],format4);
				wsheet.addCell(label);
				//信息中心
				label = new Label(9,4,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[0],format4);
				wsheet.addCell(label);
				label = new Label(9,5,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[1],format4);
				wsheet.addCell(label);
				label = new Label(9,6,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[2],format4);
				wsheet.addCell(label);
				label = new Label(9,7,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[3],format4);
				wsheet.addCell(label);
				label = new Label(9,8,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[4],format4);
				wsheet.addCell(label);
				label = new Label(9,9,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[5],format4);
				wsheet.addCell(label);
				label = new Label(9,10,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[6],format4);
				wsheet.addCell(label);
				label = new Label(9,11,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[7],format4);
				wsheet.addCell(label);
				label = new Label(9,12,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[8],format4);
				wsheet.addCell(label);
				label = new Label(9,13,cwbbSydwzycwzbbHzb.getXxzx().split("#;")[9],format4);
				wsheet.addCell(label);
				//合并单元格
				wsheet.mergeCells(0, 0, 9, 0);
				wsheet.mergeCells(0, 1, 9, 1);
				//设置列宽
				wsheet.setColumnView(0, 5);
				wsheet.setColumnView(1, 30);
				for(int c=2; c<10; c++){
					wsheet.setColumnView(c, 15);
				}
				//设置行宽
				for(int r=0; r<14; r++){
					wsheet.setRowView(r, 400, false);
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
	 *  损益表——列表
	 * 
	 */
	protected void getLrb(HttpServletRequest request,
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
			ArrayList<CwbbLrb> CwbbLrbList = new ArrayList<CwbbLrb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
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
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbCwbbDao.queryCwbbLrbListByBtCount(srbt);
			CwbbLrbList = bbsbCwbbDao.queryCwbbLrbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbLrbList", CwbbLrbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void LrbEdit(HttpServletRequest request,
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			String hzbz = request.getParameter("hzbz");//后缀标识
			if(!"".equals(id)&&id!=null){
				cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(id));
				//数据列表
				CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(id));
				hzbz = cwbbLrb.getHzbz();
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrb"+hzbz+"Edit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void LrbSave(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String bt= request.getParameter("bt");
			String sbsj = request.getParameter("sbsj");
			String hzbz = request.getParameter("hzbz");//后缀标识
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱3：驳回
			
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setBt(bt);
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			cwbbLrb.setHzbz(hzbz);//各单位后缀标识
			cwbbLrb.setTjzt(tjzt);
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//新增或修改主表
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
				bbsbCwbbDao.updateCwbbLrb(cwbbLrb);
				bbsbCwbbDao.deleteCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
			}else{
				cwbbLrb_id = bbsbCwbbDao.insertCwbbLrb(cwbbLrb)+"";
			}
			
			//损益表数据
			String bys[]=request.getParameterValues("bys"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String bnljs[]=request.getParameterValues("bnljs");
			String sntqs[]=request.getParameterValues("sntqs");
			//删除原有数据，保存新内容
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			if(bys!=null){
				for(int i=0; i<bys.length; i++){
					CwbbLrbsj cwbbLrbsj = new CwbbLrbsj();
					cwbbLrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
					cwbbLrbsj.setBys(bys[i]);
					cwbbLrbsj.setBnljs(bnljs[i]);
					cwbbLrbsj.setSntqs(sntqs[i]);
					CwbbLrbsjList.add(cwbbLrbsj);
				}
				bbsbCwbbDao.insertCwbbLrbsj(CwbbLrbsjList);
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getLrb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}

	/*
	 *  进入驳回
	 * 
	 */
	protected void LrbReturn(HttpServletRequest request,
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
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbCwbbDao.updateCwbbLrbRet(id);
				String dxnr = UserInfo.getName()+"：您有一份利润表被驳回，请及时查看";
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
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getLrb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}

	 /*
	 *  进入查看页面
	 * 
	 */
	protected void LrbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		String hzbz = request.getParameter("hzbz");//后缀标识
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(id));
		}
		request.setAttribute("cwbbLrb", cwbbLrb);
		request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
		request.setAttribute("result", result);
		
		
		
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrb"+hzbz+"Show.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void LrbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbLrbById(Integer.parseInt(id));
		//删除子项目记录
		bbsbCwbbDao.deleteCwbbLrbsjByLrbID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getLrb&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbLrbQYJTExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbLrb_id = request.getParameter("cwbbLrb_id");
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(cwbbLrb_id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
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
				WritableSheet wsheet = wwb.createSheet("损益表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbLrb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbLrb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(2,1,cwbbLrb.getSbsj().toString().substring(0,4)+"年"+cwbbLrb.getSbsj().toString().substring(5,7)+"月",format3);
				wsheet.addCell(label);
				label = new Label(4,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"本月数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"本年累计数",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"上年同期数",format5);
				wsheet.addCell(label);
				
				//2016年9月12日，汽运集团金平要求变更利润表格式，增加两项
				if(CwbbLrbsjList.size()<21){
					label = new Label(0,3,"一、主营业务收入",format6);
					wsheet.addCell(label);
					label = new Label(0,4," 减：主营业务成本",format6);
					wsheet.addCell(label);
					label = new Label(0,5,"     营业税金及附加",format6);
					wsheet.addCell(label);
					label = new Label(0,6,"二、主营业务利润",format6);
					wsheet.addCell(label);
					label = new Label(0,7," 加：其他业务利润",format6);
					wsheet.addCell(label);
					label = new Label(0,8,"     其中：其他业务收入",format6);
					wsheet.addCell(label);
					label = new Label(0,9," 减：营业费用",format6);
					wsheet.addCell(label);
					label = new Label(0,10,"     管理费用",format6);
					wsheet.addCell(label);
					label = new Label(0,11,"     财务费用",format6);
					wsheet.addCell(label);
					label = new Label(0,12,"三、营业利润",format6);
					wsheet.addCell(label);
					label = new Label(0,13," 加：投资收益",format6);
					wsheet.addCell(label);
					label = new Label(0,14,"     补贴收入",format6);
					wsheet.addCell(label);
					label = new Label(0,15,"     其中：补贴前亏损企业补贴收入",format6);
					wsheet.addCell(label);
					label = new Label(0,16,"     营业外收入",format6);
					wsheet.addCell(label);
					label = new Label(0,17," 减：营业外支出",format6);
					wsheet.addCell(label);
					label = new Label(0,18,"四、利润总额",format6);
					wsheet.addCell(label);
					label = new Label(0,19,"     其中：补贴后亏损企业亏损总额",format6);
					wsheet.addCell(label);
					label = new Label(0,20," 减：所得税",format6);
					wsheet.addCell(label);
					label = new Label(0,21,"     少数股东损益",format6);
					wsheet.addCell(label);
					label = new Label(0,22,"五、净利润",format6);
					wsheet.addCell(label);
				}else{
					//2016年9月12日后的新表
					label = new Label(0,3,"一、主营业务收入",format6);
					wsheet.addCell(label);
					label = new Label(0,4," 减：主营业务成本",format6);
					wsheet.addCell(label);
					label = new Label(0,5,"     营业税金及附加",format6);
					wsheet.addCell(label);
					label = new Label(0,6,"二、主营业务利润",format6);
					wsheet.addCell(label);
					label = new Label(0,7," 加：其他业务利润",format6);
					wsheet.addCell(label);
					label = new Label(0,8,"     其中：其他业务收入",format6);
					wsheet.addCell(label);
					label = new Label(0,9," 加：公允价值变动收益（损失以“-”填列）",format6);
					wsheet.addCell(label);
					label = new Label(0,10," 减：营业费用",format6);
					wsheet.addCell(label);
					label = new Label(0,11,"     管理费用",format6);
					wsheet.addCell(label);
					label = new Label(0,12,"     财务费用",format6);
					wsheet.addCell(label);
					label = new Label(0,13,"     资产减值损失",format6);
					wsheet.addCell(label);
					label = new Label(0,14,"三、营业利润",format6);
					wsheet.addCell(label);
					label = new Label(0,15," 加：投资收益",format6);
					wsheet.addCell(label);
					label = new Label(0,16,"     补贴收入",format6);
					wsheet.addCell(label);
					label = new Label(0,17,"     其中：补贴前亏损企业补贴收入",format6);
					wsheet.addCell(label);
					label = new Label(0,18,"     营业外收入",format6);
					wsheet.addCell(label);
					label = new Label(0,19," 减：营业外支出",format6);
					wsheet.addCell(label);
					label = new Label(0,20,"四、利润总额",format6);
					wsheet.addCell(label);
					label = new Label(0,21,"     其中：补贴后亏损企业亏损总额",format6);
					wsheet.addCell(label);
					label = new Label(0,22," 减：所得税",format6);
					wsheet.addCell(label);
					label = new Label(0,23,"     少数股东损益",format6);
					wsheet.addCell(label);
					label = new Label(0,24,"五、净利润",format6);
					wsheet.addCell(label);
				}
				for(int i=0; i<CwbbLrbsjList.size(); i++){
					label = new Label(1,i+3,i+1+"",format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbLrbsjList.get(i).getBys(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbLrbsjList.get(i).getBnljs(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbLrbsjList.get(i).getSntqs(),format7);
					wsheet.addCell(label);
				}
			

				//合并单元格
				wsheet.mergeCells(0, 0, 4, 0);
				//设置列宽
				wsheet.setColumnView(0, 60);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 30);
				wsheet.setColumnView(3, 30);
				wsheet.setColumnView(4, 30);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<CwbbLrbsjList.size()+1; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readLrbQYJTExcel(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbLrb.setBt("损益表");
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
			}
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<23||c<5){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<22; i++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBys(sheet[0].getCell(2,i+num).getContents());
							Lrbsj.setBnljs(sheet[0].getCell(3,i+num).getContents());
							Lrbsj.setSntqs(sheet[0].getCell(4,i+num).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbQYJTEdit.jsp").forward(request,
					response);
		}
	}

	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbLrbTZGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbLrb_id = request.getParameter("cwbbLrb_id");
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(cwbbLrb_id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
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
				WritableSheet wsheet = wwb.createSheet("损益表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbLrb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbLrb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(2,1,cwbbLrb.getSbsj().toString().substring(0,4)+"年"+cwbbLrb.getSbsj().toString().substring(5,7)+"月",format3);
				wsheet.addCell(label);
				label = new Label(3,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"本月数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"本年累计数",format5);
				wsheet.addCell(label);

				label = new Label(0,3,"一、主营业务收入",format6);
				wsheet.addCell(label);
				label = new Label(0,4," 减：主营业务成本",format6);
				wsheet.addCell(label);
				label = new Label(0,5,"     主营业务税金及附加",format6);
				wsheet.addCell(label);
				label = new Label(0,6,"二、主营业务利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,7," 加：其他业务利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,8," 减：营业费用",format6);
				wsheet.addCell(label);
				label = new Label(0,9,"     管理费用",format6);
				wsheet.addCell(label);
				label = new Label(0,10,"     财务费用",format6);
				wsheet.addCell(label);
				label = new Label(0,11,"三、营业利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,12," 加：投资收益（损失以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,13,"     补贴收入",format6);
				wsheet.addCell(label);
				label = new Label(0,14,"     营业外收入",format6);
				wsheet.addCell(label);
				label = new Label(0,15," 减：营业外支出",format6);
				wsheet.addCell(label);
				label = new Label(0,16,"四、利润总额（亏损总额以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,17," 减：所得税",format6);
				wsheet.addCell(label);
				label = new Label(0,18,"五、净利润（净亏损以“-”号填列）",format6);
				wsheet.addCell(label);

				label = new Label(0,19,"",format2);
				wsheet.addCell(label);
				label = new Label(0,20,"补充资料：",format2);
				wsheet.addCell(label);

				label = new Label(0,21,"项目：",format5);
				wsheet.addCell(label);
				label = new Label(1,21,"本年累计数",format5);
				wsheet.addCell(label);
				label = new Label(3,21,"上年实际数",format5);
				wsheet.addCell(label);
				label = new Label(0,22,"1、出售、处置部门或被投资单位所得收益",format6);
				wsheet.addCell(label);
				label = new Label(0,23,"2、自然灾害发生的损失",format6);
				wsheet.addCell(label);
				label = new Label(0,24,"3、会计政策变更增加（或减少）利润总额",format6);
				wsheet.addCell(label);
				label = new Label(0,25,"4、会计估计变更增加（或减少）利润总额",format6);
				wsheet.addCell(label);
				label = new Label(0,26,"5、债务重组损失",format6);
				wsheet.addCell(label);
				label = new Label(0,27,"6、其他",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<16; i++){
					label = new Label(1,i+3,i+1+"",format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbLrbsjList.get(i).getBys(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbLrbsjList.get(i).getBnljs(),format7);
					wsheet.addCell(label);
				}
				for(int j=16; j<22; j++){
					label = new Label(1,j+6,CwbbLrbsjList.get(j).getBnljs(),format7);
					wsheet.addCell(label);
					label = new Label(3,j+6,CwbbLrbsjList.get(j).getSntqs(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 3, 0);
				wsheet.mergeCells(0, 19, 3, 19);
				wsheet.mergeCells(0, 20, 3, 20);
				wsheet.mergeCells(1, 21, 2, 21);
				wsheet.mergeCells(1, 22, 2, 22);
				wsheet.mergeCells(1, 23, 2, 23);
				wsheet.mergeCells(1, 24, 2, 24);
				wsheet.mergeCells(1, 25, 2, 25);
				wsheet.mergeCells(1, 26, 2, 26);
				wsheet.mergeCells(1, 27, 2, 27);
				//设置列宽
				wsheet.setColumnView(0, 60);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 30);
				wsheet.setColumnView(3, 30);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<27; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readLrbTZGSExcel(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setBt("损益表");
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
			}
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<28||c<4){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num1=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num1=i;
							break;
						}
					}
					if(num1!=0){
						for(int i=0; i<16; i++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBys(sheet[0].getCell(2,i+num1).getContents());
							Lrbsj.setBnljs(sheet[0].getCell(3,i+num1).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
					//找到指标 补充资料：
					int num2=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("补充资料：".equals(sheet[0].getCell(0,i).getContents())){
							num2=i;
							break;
						}
					}
					if(num2!=0){
						for(int j=0; j<6; j++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBnljs(sheet[0].getCell(1,j+num2+2).getContents());
							Lrbsj.setSntqs(sheet[0].getCell(3,j+num2+2).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbTZGSEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbLrbZWGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbLrb_id = request.getParameter("cwbbLrb_id");
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(cwbbLrb_id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
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
				WritableSheet wsheet = wwb.createSheet("损益表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbLrb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbLrb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(2,1,cwbbLrb.getSbsj().toString().substring(0,4)+"年"+cwbbLrb.getSbsj().toString().substring(5,7)+"月",format3);
				wsheet.addCell(label);
				label = new Label(3,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"本月数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"本年累计数",format5);
				wsheet.addCell(label);

				label = new Label(0,3,"一、主营业务收入",format6);
				wsheet.addCell(label);
				label = new Label(0,4," 减：主营业务成本",format6);
				wsheet.addCell(label);
				label = new Label(0,5,"     主营业务税金及附加",format6);
				wsheet.addCell(label);
				label = new Label(0,6,"二、主营业务利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,7," 加：其他业务利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,8," 减：营业费用",format6);
				wsheet.addCell(label);
				label = new Label(0,9,"     管理费用",format6);
				wsheet.addCell(label);
				label = new Label(0,10,"     财务费用",format6);
				wsheet.addCell(label);
				label = new Label(0,11,"三、营业利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,12," 加：投资收益（损失以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,13,"     补贴收入",format6);
				wsheet.addCell(label);
				label = new Label(0,14,"     营业外收入",format6);
				wsheet.addCell(label);
				label = new Label(0,15," 减：营业外支出",format6);
				wsheet.addCell(label);
				label = new Label(0,16,"四、利润总额（亏损总额以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,17," 减：所得税",format6);
				wsheet.addCell(label);
				label = new Label(0,18,"五、净利润（净亏损以“-”号填列）",format6);
				wsheet.addCell(label);

				label = new Label(0,19,"",format2);
				wsheet.addCell(label);
				label = new Label(0,20,"补充资料：",format2);
				wsheet.addCell(label);

				label = new Label(0,21,"项目：",format5);
				wsheet.addCell(label);
				label = new Label(1,21,"本年累计数",format5);
				wsheet.addCell(label);
				label = new Label(3,21,"上年实际数",format5);
				wsheet.addCell(label);
				label = new Label(0,22,"1、出售、处置部门或被投资单位所得收益",format6);
				wsheet.addCell(label);
				label = new Label(0,23,"2、自然灾害发生的损失",format6);
				wsheet.addCell(label);
				label = new Label(0,24,"3、会计政策变更增加（或减少）利润总额",format6);
				wsheet.addCell(label);
				label = new Label(0,25,"4、会计估计变更增加（或减少）利润总额",format6);
				wsheet.addCell(label);
				label = new Label(0,26,"5、债务重组损失",format6);
				wsheet.addCell(label);
				label = new Label(0,27,"6、其他",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<16; i++){
					label = new Label(1,i+3,i+1+"",format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbLrbsjList.get(i).getBys(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbLrbsjList.get(i).getBnljs(),format7);
					wsheet.addCell(label);
				}
				for(int j=16; j<22; j++){
					label = new Label(1,j+6,CwbbLrbsjList.get(j).getBnljs(),format7);
					wsheet.addCell(label);
					label = new Label(3,j+6,CwbbLrbsjList.get(j).getSntqs(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 3, 0);
				wsheet.mergeCells(0, 19, 3, 19);
				wsheet.mergeCells(0, 20, 3, 20);
				wsheet.mergeCells(1, 21, 2, 21);
				wsheet.mergeCells(1, 22, 2, 22);
				wsheet.mergeCells(1, 23, 2, 23);
				wsheet.mergeCells(1, 24, 2, 24);
				wsheet.mergeCells(1, 25, 2, 25);
				wsheet.mergeCells(1, 26, 2, 26);
				wsheet.mergeCells(1, 27, 2, 27);
				//设置列宽
				wsheet.setColumnView(0, 60);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 30);
				wsheet.setColumnView(3, 30);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<26; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readLrbZWGSExcel(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setBt("损益表");
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
			}
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<28||c<4){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num1=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num1=i;
							break;
						}
					}
					if(num1!=0){
						for(int i=0; i<16; i++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBys(sheet[0].getCell(2,i+num1).getContents());
							Lrbsj.setBnljs(sheet[0].getCell(3,i+num1).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}

					//找到指标 补充资料：
					int num2=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("补充资料：".equals(sheet[0].getCell(0,i).getContents())){
							num2=i;
							break;
						}
					}
					if(num2!=0){
						for(int j=0; j<6; j++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBnljs(sheet[0].getCell(1,j+num2+2).getContents());
							Lrbsj.setSntqs(sheet[0].getCell(3,j+num2+2).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbZWGSEdit.jsp").forward(request,
					response);
		}
	}

	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbLrbCZGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbLrb_id = request.getParameter("cwbbLrb_id");
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(cwbbLrb_id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
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
				WritableSheet wsheet = wwb.createSheet("损益表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbLrb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbLrb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(2,1,cwbbLrb.getSbsj().toString().substring(0,4)+"年"+cwbbLrb.getSbsj().toString().substring(5,7)+"月",format3);
				wsheet.addCell(label);
				label = new Label(3,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"本月数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"本年累计数",format5);
				wsheet.addCell(label);

				label = new Label(0,3,"一、主营业务收入",format6);
				wsheet.addCell(label);
				label = new Label(0,4," 减：营业成本",format6);
				wsheet.addCell(label);
				label = new Label(0,5,"     营业税金及附加",format6);
				wsheet.addCell(label);
				label = new Label(0,6,"二、主营业务利润",format6);
				wsheet.addCell(label);
				label = new Label(0,7," 加：其他业务利润",format6);
				wsheet.addCell(label);
				label = new Label(0,8," 减：管理费用",format6);
				wsheet.addCell(label);
				label = new Label(0,9,"     财务费用",format6);
				wsheet.addCell(label);
				label = new Label(0,10,"三、营业利润",format6);
				wsheet.addCell(label);
				label = new Label(0,11," 加：投资收益",format6);
				wsheet.addCell(label);
				label = new Label(0,12,"     营业外收入",format6);
				wsheet.addCell(label);
				label = new Label(0,13," 减：营业外支出",format6);
				wsheet.addCell(label);
				label = new Label(0,14," 加：以前年度损益调整",format6);
				wsheet.addCell(label);
				label = new Label(0,15,"四、利润总额",format6);
				wsheet.addCell(label);
				label = new Label(0,16," 减：所得税",format6);
				wsheet.addCell(label);
				label = new Label(0,17,"五、净利润",format6);
				wsheet.addCell(label);

				for(int i=0; i<15; i++){
					label = new Label(1,i+3,i+1+"",format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbLrbsjList.get(i).getBys(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbLrbsjList.get(i).getBnljs(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 3, 0);
				//设置列宽
				wsheet.setColumnView(0, 60);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 30);
				wsheet.setColumnView(3, 30);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<18; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readLrbCZGSExcel(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setBt("损益表");
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
			}
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<16||c<4){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int j=0; j<15; j++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBys(sheet[0].getCell(2,j+num).getContents());
							Lrbsj.setBnljs(sheet[0].getCell(3,j+num).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbCZGSEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbLrbJCZXExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbLrb_id = request.getParameter("cwbbLrb_id");
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(cwbbLrb_id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
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
				WritableSheet wsheet = wwb.createSheet("损益表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbLrb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbLrb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(2,1,cwbbLrb.getSbsj().toString().substring(0,4)+"年"+cwbbLrb.getSbsj().toString().substring(5,7)+"月",format3);
				wsheet.addCell(label);
				label = new Label(3,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"本年累计金额",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"本月数",format5);
				wsheet.addCell(label);

				label = new Label(0,3,"一、营业收入",format6);
				wsheet.addCell(label);
				label = new Label(0,4," 减：营业成本",format6);
				wsheet.addCell(label);
				label = new Label(0,5,"     营业税金及附加",format6);
				wsheet.addCell(label);
				label = new Label(0,6,"     其中：消费税",format6);
				wsheet.addCell(label);
				label = new Label(0,7,"           营业税",format6);
				wsheet.addCell(label);
				label = new Label(0,8,"           城市维护建设税",format6);
				wsheet.addCell(label);
				label = new Label(0,9,"           资源税",format6);
				wsheet.addCell(label);
				label = new Label(0,10,"           土地增值税",format6);
				wsheet.addCell(label);
				label = new Label(0,11,"           城镇土地使用税、房产税、车船税、印花税",format6);
				wsheet.addCell(label);
				label = new Label(0,12,"           教育费附加、矿产资源补偿费、排污费",format6);
				wsheet.addCell(label);
				label = new Label(0,13,"     销售费用",format6);
				wsheet.addCell(label);
				label = new Label(0,14,"     其中：商品维修费",format6);
				wsheet.addCell(label);
				label = new Label(0,15,"           广告费和业务宣传费",format6);
				wsheet.addCell(label);
				label = new Label(0,16,"     管理费用",format6);
				wsheet.addCell(label);
				label = new Label(0,17,"     其中：开办费",format6);
				wsheet.addCell(label);
				label = new Label(0,18,"           业务招待费",format6);
				wsheet.addCell(label);
				label = new Label(0,19,"           研究费用",format6);
				wsheet.addCell(label);
				label = new Label(0,20,"     财务费用",format6);
				wsheet.addCell(label);
				label = new Label(0,21,"     其中：利息费用（收入以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,22," 加：投资收益（损失以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,23,"二、营业利润（亏损以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,24," 加：营业外收入",format6);
				wsheet.addCell(label);
				label = new Label(0,25,"     其中：政府补助",format6);
				wsheet.addCell(label);
				label = new Label(0,26," 减：营业外支出",format6);
				wsheet.addCell(label);
				label = new Label(0,27,"     其中：坏账损失",format6);
				wsheet.addCell(label);
				label = new Label(0,28,"           无法收回的长期债券投资损失",format6);
				wsheet.addCell(label);
				label = new Label(0,29,"           无法收回的长期股权投资损失",format6);
				wsheet.addCell(label);
				label = new Label(0,30,"           自然灾害等不可抗力因素造成的损失",format6);
				wsheet.addCell(label);
				label = new Label(0,31,"           税收滞纳金",format6);
				wsheet.addCell(label);
				label = new Label(0,32,"三、利润总额（亏损总额以“-”号填列）",format6);
				wsheet.addCell(label);
				label = new Label(0,33," 减：所得税费用",format6);
				wsheet.addCell(label);
				label = new Label(0,34,"四、净利润（净亏损以“-”号填列）",format6);
				wsheet.addCell(label);

				for(int i=0; i<32; i++){
					label = new Label(1,i+3,i+1+"",format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbLrbsjList.get(i).getBnljs(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbLrbsjList.get(i).getBys(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 3, 0);
				//设置列宽
				wsheet.setColumnView(0, 60);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 30);
				wsheet.setColumnView(3, 30);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<33; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readLrbJCZXExcel(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setBt("损益表");
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
			}
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<35||c<4){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<32; i++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBnljs(sheet[0].getCell(2,i+num).getContents());
							Lrbsj.setBys(sheet[0].getCell(3,i+num).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbJCZXEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbLrbGJGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbLrb_id = request.getParameter("cwbbLrb_id");
		CwbbLrb cwbbLrb = new CwbbLrb();
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
			cwbbLrb = bbsbCwbbDao.queryCwbbLrbByID(Integer.parseInt(cwbbLrb_id));
			CwbbLrbsjList =  bbsbCwbbDao.queryCwbbLrbsjByLrbID(Integer.parseInt(cwbbLrb_id));
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
				WritableSheet wsheet = wwb.createSheet("损益表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbLrb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbLrb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbLrb.getSbsj().toString().substring(0,4)+"年"+cwbbLrb.getSbsj().toString().substring(5,7)+"月",format3);
				wsheet.addCell(label);
				label = new Label(4,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(1,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"本月实绩",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"本年累计",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"上年累计",format5);
				wsheet.addCell(label);

				label = new Label(0,2,"",format6);
				wsheet.addCell(label);
				label = new Label(0,3,"一、",format6);
				wsheet.addCell(label);
				label = new Label(0,4,"",format6);
				wsheet.addCell(label);
				label = new Label(0,5,"",format6);
				wsheet.addCell(label);
				label = new Label(0,6,"二、",format6);
				wsheet.addCell(label);
				label = new Label(0,7,"",format6);
				wsheet.addCell(label);
				label = new Label(0,8,"",format6);
				wsheet.addCell(label);
				label = new Label(0,9,"",format6);
				wsheet.addCell(label);
				label = new Label(0,10,"",format6);
				wsheet.addCell(label);
				label = new Label(0,11,"",format6);
				wsheet.addCell(label);
				label = new Label(0,12,"",format6);
				wsheet.addCell(label);
				label = new Label(0,13,"",format6);
				wsheet.addCell(label);
				label = new Label(0,14,"",format6);
				wsheet.addCell(label);
				label = new Label(0,15,"",format6);
				wsheet.addCell(label);
				label = new Label(0,16,"三、",format6);
				wsheet.addCell(label);
				label = new Label(0,17,"",format6);
				wsheet.addCell(label);
				label = new Label(0,18,"",format6);
				wsheet.addCell(label);
				label = new Label(0,19,"",format6);
				wsheet.addCell(label);
				label = new Label(0,20,"",format6);
				wsheet.addCell(label);
				label = new Label(0,21,"",format6);
				wsheet.addCell(label);
				label = new Label(0,22,"四、",format6);
				wsheet.addCell(label);
				label = new Label(0,23,"",format6);
				wsheet.addCell(label);
				label = new Label(0,24,"五、",format6);
				wsheet.addCell(label);
				label = new Label(1,3,"营业总收入",format6);
				wsheet.addCell(label);
				label = new Label(1,4," 其中：主营业务收入",format6);
				wsheet.addCell(label);
				label = new Label(1,5,"       其他业务收入",format6);
				wsheet.addCell(label);
				label = new Label(1,6,"营业总成本",format6);
				wsheet.addCell(label);
				label = new Label(1,7,"   主营业务成本",format6);
				wsheet.addCell(label);
				label = new Label(1,8,"   其他业务成本",format6);
				wsheet.addCell(label);
				label = new Label(1,9,"   营业税金及附加",format6);
				wsheet.addCell(label);
				label = new Label(1,11,"   销售费用",format6);
				wsheet.addCell(label);
				label = new Label(1,11,"   管理费用",format6);
				wsheet.addCell(label);
				label = new Label(1,12,"   财务费用",format6);
				wsheet.addCell(label);
				label = new Label(1,13,"        其中：利息支出",format6);
				wsheet.addCell(label);
				label = new Label(1,14,"              利息收入",format6);
				wsheet.addCell(label);
				label = new Label(1,15,"   投资收益",format6);
				wsheet.addCell(label);
				label = new Label(1,16,"营业利润",format6);
				wsheet.addCell(label);
				label = new Label(1,17,"   加：营业外收入",format6);
				wsheet.addCell(label);
				label = new Label(1,18,"      其中：非流动性资产处置利得",format6);
				wsheet.addCell(label);
				label = new Label(1,19,"            政府补助（补贴收入）",format6);
				wsheet.addCell(label);
				label = new Label(1,21,"   减：营业外支出",format6);
				wsheet.addCell(label);
				label = new Label(1,21,"      其中：非流动性资产处置损失",format6);
				wsheet.addCell(label);
				label = new Label(1,22,"利润总额",format6);
				wsheet.addCell(label);
				label = new Label(1,23,"   减：所得税费用",format6);
				wsheet.addCell(label);
				label = new Label(1,24,"净利润",format6);
				wsheet.addCell(label);

				for(int i=0; i<22; i++){
					label = new Label(2,i+3,CwbbLrbsjList.get(i).getBys(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbLrbsjList.get(i).getBnljs(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbLrbsjList.get(i).getSntqs(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 4, 0);
				wsheet.mergeCells(0, 1, 1, 1);
				//设置列宽
				wsheet.setColumnView(0, 5);
				wsheet.setColumnView(1, 60);
				wsheet.setColumnView(2, 30);
				wsheet.setColumnView(3, 30);
				wsheet.setColumnView(4, 30);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<23; k++){
					wsheet.setRowView(k+2, 500, false);
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
	
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readLrbGJGSExcel(HttpServletRequest request,
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
			String cwbbLrb_id = request.getParameter("cwbbLrb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbLrb cwbbLrb = new CwbbLrb();
			cwbbLrb.setBt("损益表");
			cwbbLrb.setCzr(UserInfo.getName());
			cwbbLrb.setCzrID(UserInfo.getUsername());
			cwbbLrb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbLrb.setCzsj(data1);
			cwbbLrb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
				cwbbLrb.setId(Integer.parseInt(cwbbLrb_id));
			}
			ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<23||c<5){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if(sheet[0].getCell(0,i).getContents().indexOf("一")!=-1){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<22; i++){
							CwbbLrbsj Lrbsj = new CwbbLrbsj();
							Lrbsj.setBys(sheet[0].getCell(2,i+num).getContents());
							Lrbsj.setBnljs(sheet[0].getCell(3,i+num).getContents());
							Lrbsj.setSntqs(sheet[0].getCell(4,i+num).getContents());
							if(!"".equals(cwbbLrb_id)&&cwbbLrb_id!=null){
								Lrbsj.setLrbID(Integer.parseInt(cwbbLrb_id));
							}
							CwbbLrbsjList.add(Lrbsj);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbLrb", cwbbLrb);
			request.setAttribute("CwbbLrbsjList", CwbbLrbsjList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbLrbGJGSEdit.jsp").forward(request,
					response);
		}
	}

	/*
	 *  资产负债表——列表
	 * 
	 */
	protected void getZcfzb(HttpServletRequest request,
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
			ArrayList<CwbbZcfzb> CwbbZcfzbList = new ArrayList<CwbbZcfzb>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
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
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbCwbbDao.queryCwbbZcfzbListByBtCount(srbt);
			CwbbZcfzbList = bbsbCwbbDao.queryCwbbZcfzbListByBt(srbt, begin, pageSize);
			request.setAttribute("CwbbZcfzbList", CwbbZcfzbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void ZcfzbEdit(HttpServletRequest request,
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
			String hzbz = request.getParameter("hzbz");//后缀标识
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			if(!"".equals(id)&&id!=null){
				cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(Integer.parseInt(id));
				//数据列表
				CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(Integer.parseInt(id));
				CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(Integer.parseInt(id));
				hzbz = cwbbZcfzb.getHzbz();
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzb"+hzbz+"Edit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void ZcfzbSave(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String bt= request.getParameter("bt");
			String sbsj = request.getParameter("sbsj");
			String hzbz = request.getParameter("hzbz");//后缀标识
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱3：驳回
			
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt(bt);
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			cwbbZcfzb.setHzbz(hzbz);//各单位后缀标识
			cwbbZcfzb.setTjzt(tjzt);
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//新增或修改主表
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
				bbsbCwbbDao.updateCwbbZcfzb(cwbbZcfzb);
				bbsbCwbbDao.deleteCwbbZcfzbzcByZcfzbID(Integer.parseInt(cwbbZcfzb_id));
				bbsbCwbbDao.deleteCwbbZcfzbfzByZcfzbID(Integer.parseInt(cwbbZcfzb_id));
			}else{
				cwbbZcfzb_id = bbsbCwbbDao.insertCwbbZcfzb(cwbbZcfzb)+"";
			}
			//损益表数据
			String mc1[]=request.getParameterValues("mc1");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内 
			String hc1[]=request.getParameterValues("hc1");
			String ncs1[]=request.getParameterValues("ncs1");
			String qms1[]=request.getParameterValues("qms1");
			String mc2[]=request.getParameterValues("mc2");
			String hc2[]=request.getParameterValues("hc2");
			String ncs2[]=request.getParameterValues("ncs2");
			String qms2[]=request.getParameterValues("qms2");
			//删除原有数据，保存新内容
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			if(mc1!=null){
				for(int i=0; i<mc1.length; i++){
					CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
					cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
					cwbbZcfzbzc.setMc(mc1[i]);
					cwbbZcfzbzc.setHc(hc1[i]);
					cwbbZcfzbzc.setNcs(ncs1[i]);
					cwbbZcfzbzc.setQms(qms1[i]);
					CwbbZcfzbzcList.add(cwbbZcfzbzc);
				}
				bbsbCwbbDao.insertCwbbZcfzbzc(CwbbZcfzbzcList);
			}

			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			if(mc2!=null){
				for(int i=0; i<mc2.length; i++){
					CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
					cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
					cwbbZcfzbfz.setMc(mc2[i]);
					cwbbZcfzbfz.setHc(hc2[i]);
					cwbbZcfzbfz.setNcs(ncs2[i]);
					cwbbZcfzbfz.setQms(qms2[i]);
					CwbbZcfzbfzList.add(cwbbZcfzbfz);
				}
			}
			bbsbCwbbDao.insertCwbbZcfzbfz(CwbbZcfzbfzList);

			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getZcfzb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/*
	 *  进入驳回
	 * 
	 */
	protected void ZcfzbReturn(HttpServletRequest request,
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
			BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbCwbbDao.updateCwbbZcfzbzcRet(id);
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
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbCwbbServlet?action=getZcfzb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void ZcfzbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		String hzbz = request.getParameter("hzbz");//后缀标识
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
		}
		request.setAttribute("cwbbZcfzb", cwbbZcfzb);
		request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
		request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzb"+hzbz+"Show.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void ZcfzbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		bbsbCwbbDao.deleteCwbbZcfzbById(Integer.parseInt(id));
		//删除子项目记录
		bbsbCwbbDao.deleteCwbbZcfzbzcByZcfzbID(Integer.parseInt(id));
		bbsbCwbbDao.deleteCwbbZcfzbfzByZcfzbID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbCwbbServlet?action=getZcfzb&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbZcfzbQYJTExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
			int ID = Integer.parseInt(cwbbZcfzb_id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
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
				WritableSheet wsheet = wwb.createSheet("资产负债表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbZcfzb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbZcfzb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbZcfzb.getSbsj()+"",format3);
				wsheet.addCell(label);
				label = new Label(7,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"资        产",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"期末数",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"负债及所有者权益",format5);
				wsheet.addCell(label);
				label = new Label(5,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(6,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(7,2,"期末数",format5);
				wsheet.addCell(label);
				for(int i=0; i<CwbbZcfzbzcList.size(); i++){
					label = new Label(0,i+3,CwbbZcfzbzcList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(1,i+3,CwbbZcfzbzcList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbZcfzbzcList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbZcfzbzcList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbZcfzbfzList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,CwbbZcfzbfzList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+3,CwbbZcfzbfzList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(7,i+3,CwbbZcfzbfzList.get(i).getQms(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(3, 1, 4, 1);
				//设置列宽
				wsheet.setColumnView(0, 35);
				wsheet.setColumnView(1, 6);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				wsheet.setColumnView(4, 35);
				wsheet.setColumnView(5, 6);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<41; k++){
					wsheet.setRowView(k+2, 500, false);
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
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readZcfzbQYJTExcel(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt("资产负债表");
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
			}
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<43||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<41; i++){
							CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
							cwbbZcfzbzc.setMc(sheet[0].getCell(0,i+num-1).getContents());
							cwbbZcfzbzc.setHc(sheet[0].getCell(1,i+num-1).getContents());
							cwbbZcfzbzc.setNcs(sheet[0].getCell(2,i+num-1).getContents());
							cwbbZcfzbzc.setQms(sheet[0].getCell(3,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbzcList.add(cwbbZcfzbzc);
							
							CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
							cwbbZcfzbfz.setMc(sheet[0].getCell(4,i+num-1).getContents());
							cwbbZcfzbfz.setHc(sheet[0].getCell(5,i+num-1).getContents());
							cwbbZcfzbfz.setNcs(sheet[0].getCell(6,i+num-1).getContents());
							cwbbZcfzbfz.setQms(sheet[0].getCell(7,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbfzList.add(cwbbZcfzbfz);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbQYJTEdit.jsp").forward(request,
					response);
		}
	}

	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbZcfzbZWGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
			int ID = Integer.parseInt(cwbbZcfzb_id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
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
				WritableSheet wsheet = wwb.createSheet("资产负债表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbZcfzb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbZcfzb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbZcfzb.getSbsj()+"",format3);
				wsheet.addCell(label);
				label = new Label(7,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"资        产",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"期末数",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"负债及所有者权益（或股东权益）",format5);
				wsheet.addCell(label);
				label = new Label(5,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(6,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(7,2,"期末数",format5);
				wsheet.addCell(label);
				for(int i=0; i<38; i++){
					label = new Label(0,i+3,CwbbZcfzbzcList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(1,i+3,CwbbZcfzbzcList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbZcfzbzcList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbZcfzbzcList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbZcfzbfzList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,CwbbZcfzbfzList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+3,CwbbZcfzbfzList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(7,i+3,CwbbZcfzbfzList.get(i).getQms(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(3, 1, 4, 1);
				//设置列宽
				wsheet.setColumnView(0, 35);
				wsheet.setColumnView(1, 6);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				wsheet.setColumnView(4, 35);
				wsheet.setColumnView(5, 6);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<39; k++){
					wsheet.setRowView(k+2, 500, false);
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
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readZcfzbZWGSExcel(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt("资产负债表");
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
			}
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<41||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<38; i++){
							CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
							cwbbZcfzbzc.setMc(sheet[0].getCell(0,i+num-1).getContents());
							cwbbZcfzbzc.setHc(sheet[0].getCell(1,i+num-1).getContents());
							cwbbZcfzbzc.setNcs(sheet[0].getCell(2,i+num-1).getContents());
							cwbbZcfzbzc.setQms(sheet[0].getCell(3,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbzcList.add(cwbbZcfzbzc);
							
							CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
							cwbbZcfzbfz.setMc(sheet[0].getCell(4,i+num-1).getContents());
							cwbbZcfzbfz.setHc(sheet[0].getCell(5,i+num-1).getContents());
							cwbbZcfzbfz.setNcs(sheet[0].getCell(6,i+num-1).getContents());
							cwbbZcfzbfz.setQms(sheet[0].getCell(7,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbfzList.add(cwbbZcfzbfz);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbZWGSEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbZcfzbTZGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
			int ID = Integer.parseInt(cwbbZcfzb_id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
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
				WritableSheet wsheet = wwb.createSheet("资产负债表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbZcfzb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbZcfzb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbZcfzb.getSbsj()+"",format3);
				wsheet.addCell(label);
				label = new Label(7,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"资        产",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"期末数",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"负债及所有者权益（或股东权益）",format5);
				wsheet.addCell(label);
				label = new Label(5,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(6,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(7,2,"期末数",format5);
				wsheet.addCell(label);
				for(int i=0; i<38; i++){
					label = new Label(0,i+3,CwbbZcfzbzcList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(1,i+3,CwbbZcfzbzcList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbZcfzbzcList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbZcfzbzcList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbZcfzbfzList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,CwbbZcfzbfzList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+3,CwbbZcfzbfzList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(7,i+3,CwbbZcfzbfzList.get(i).getQms(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(3, 1, 4, 1);
				//设置列宽
				wsheet.setColumnView(0, 35);
				wsheet.setColumnView(1, 6);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				wsheet.setColumnView(4, 35);
				wsheet.setColumnView(5, 6);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<39; k++){
					wsheet.setRowView(k+2, 500, false);
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
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readZcfzbTZGSExcel(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt("资产负债表");
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
			}
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<41||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<38; i++){
							CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
							cwbbZcfzbzc.setMc(sheet[0].getCell(0,i+num-1).getContents());
							cwbbZcfzbzc.setHc(sheet[0].getCell(1,i+num-1).getContents());
							cwbbZcfzbzc.setNcs(sheet[0].getCell(2,i+num-1).getContents());
							cwbbZcfzbzc.setQms(sheet[0].getCell(3,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbzcList.add(cwbbZcfzbzc);
							
							CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
							cwbbZcfzbfz.setMc(sheet[0].getCell(4,i+num-1).getContents());
							cwbbZcfzbfz.setHc(sheet[0].getCell(5,i+num-1).getContents());
							cwbbZcfzbfz.setNcs(sheet[0].getCell(6,i+num-1).getContents());
							cwbbZcfzbfz.setQms(sheet[0].getCell(7,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbfzList.add(cwbbZcfzbfz);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbTZGSEdit.jsp").forward(request,
					response);
		}
	}

	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbZcfzbCZGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
			int ID = Integer.parseInt(cwbbZcfzb_id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
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
				WritableSheet wsheet = wwb.createSheet("资产负债表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbZcfzb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbZcfzb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbZcfzb.getSbsj()+"",format3);
				wsheet.addCell(label);
				label = new Label(7,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"资        产",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"期末数",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"负债及所有者权益",format5);
				wsheet.addCell(label);
				label = new Label(5,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(6,2,"年初数",format5);
				wsheet.addCell(label);
				label = new Label(7,2,"期末数",format5);
				wsheet.addCell(label);
				for(int i=0; i<36; i++){
					label = new Label(0,i+3,CwbbZcfzbzcList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(1,i+3,CwbbZcfzbzcList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbZcfzbzcList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbZcfzbzcList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbZcfzbfzList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,CwbbZcfzbfzList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+3,CwbbZcfzbfzList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(7,i+3,CwbbZcfzbfzList.get(i).getQms(),format7);
					wsheet.addCell(label);
				}
				label = new Label(0,39,CwbbZcfzbzcList.get(36).getMc(),format4);
				wsheet.addCell(label);
				label = new Label(3,39,CwbbZcfzbzcList.get(36).getHc(),format4);
				wsheet.addCell(label);
				label = new Label(4,39,"元；",format2);
				wsheet.addCell(label);
				label = new Label(0,40,CwbbZcfzbzcList.get(36).getNcs(),format4);
				wsheet.addCell(label);
				label = new Label(3,40,CwbbZcfzbzcList.get(36).getQms(),format4);
				wsheet.addCell(label);
				label = new Label(4,40,"元。",format2);
				wsheet.addCell(label);
				//合并单元格
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(3, 1, 4, 1);
				wsheet.mergeCells(0, 39, 2, 39);
				wsheet.mergeCells(0, 40, 2, 40);
				//设置列宽
				wsheet.setColumnView(0, 35);
				wsheet.setColumnView(1, 6);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				wsheet.setColumnView(4, 35);
				wsheet.setColumnView(5, 6);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<37; k++){
					wsheet.setRowView(k+2, 500, false);
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
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readZcfzbCZGSExcel(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt("资产负债表");
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
			}
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<39||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<36; i++){
							CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
							cwbbZcfzbzc.setMc(sheet[0].getCell(0,i+num-1).getContents());
							cwbbZcfzbzc.setHc(sheet[0].getCell(1,i+num-1).getContents());
							cwbbZcfzbzc.setNcs(sheet[0].getCell(2,i+num-1).getContents());
							cwbbZcfzbzc.setQms(sheet[0].getCell(3,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbzcList.add(cwbbZcfzbzc);
							
							CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
							cwbbZcfzbfz.setMc(sheet[0].getCell(4,i+num-1).getContents());
							cwbbZcfzbfz.setHc(sheet[0].getCell(5,i+num-1).getContents());
							cwbbZcfzbfz.setNcs(sheet[0].getCell(6,i+num-1).getContents());
							cwbbZcfzbfz.setQms(sheet[0].getCell(7,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbfzList.add(cwbbZcfzbfz);
						}
						//补充资料
						CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
						cwbbZcfzbzc.setMc(sheet[0].getCell(0,35+num).getContents());
						cwbbZcfzbzc.setHc(sheet[0].getCell(3,35+num).getContents());
						cwbbZcfzbzc.setNcs(sheet[0].getCell(0,36+num).getContents());
						cwbbZcfzbzc.setQms(sheet[0].getCell(3,36+num).getContents());
						if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
							cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
						}
						CwbbZcfzbzcList.add(cwbbZcfzbzc);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbCZGSEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbZcfzbJCZXExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
			int ID = Integer.parseInt(cwbbZcfzb_id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
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
				WritableSheet wsheet = wwb.createSheet("资产负债表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbZcfzb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbZcfzb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbZcfzb.getSbsj()+"",format3);
				wsheet.addCell(label);
				label = new Label(7,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"资        产",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"期末余额",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"年初余额",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"负债和所有者权益",format5);
				wsheet.addCell(label);
				label = new Label(5,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(6,2,"期末余额",format5);
				wsheet.addCell(label);
				label = new Label(7,2,"年初余额",format5);
				wsheet.addCell(label);
				for(int i=0; i<32; i++){
					label = new Label(0,i+3,CwbbZcfzbzcList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(1,i+3,CwbbZcfzbzcList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbZcfzbzcList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbZcfzbzcList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbZcfzbfzList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,CwbbZcfzbfzList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+3,CwbbZcfzbfzList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(7,i+3,CwbbZcfzbfzList.get(i).getNcs(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(3, 1, 4, 1);
				//设置列宽
				wsheet.setColumnView(0, 35);
				wsheet.setColumnView(1, 6);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				wsheet.setColumnView(4, 35);
				wsheet.setColumnView(5, 6);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<33; k++){
					wsheet.setRowView(k+2, 500, false);
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
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readZcfzbJCZXExcel(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt("资产负债表");
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
			}
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<35||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<32; i++){
							CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
							cwbbZcfzbzc.setMc(sheet[0].getCell(0,i+num-1).getContents());
							cwbbZcfzbzc.setHc(sheet[0].getCell(1,i+num-1).getContents());
							cwbbZcfzbzc.setQms(sheet[0].getCell(2,i+num-1).getContents());
							cwbbZcfzbzc.setNcs(sheet[0].getCell(3,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbzcList.add(cwbbZcfzbzc);
							
							CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
							cwbbZcfzbfz.setMc(sheet[0].getCell(4,i+num-1).getContents());
							cwbbZcfzbfz.setHc(sheet[0].getCell(5,i+num-1).getContents());
							cwbbZcfzbfz.setQms(sheet[0].getCell(6,i+num-1).getContents());
							cwbbZcfzbfz.setNcs(sheet[0].getCell(7,i+num-1).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbfzList.add(cwbbZcfzbfz);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbJCZXEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getCwbbZcfzbGJGSExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
			int ID = Integer.parseInt(cwbbZcfzb_id);
			cwbbZcfzb = bbsbCwbbDao.queryCwbbZcfzbByID(ID);
			CwbbZcfzbzcList =  bbsbCwbbDao.queryCwbbZcfzbzcByZcfzbID(ID);
			CwbbZcfzbfzList =  bbsbCwbbDao.queryCwbbZcfzbfzByZcfzbID(ID);
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
				WritableSheet wsheet = wwb.createSheet("资产负债表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font7 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.RIGHT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,cwbbZcfzb.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"单位："+cwbbZcfzb.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(3,1,cwbbZcfzb.getSbsj()+"",format3);
				wsheet.addCell(label);
				label = new Label(7,1,"单位：元",format4);
				wsheet.addCell(label);

				label = new Label(0,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(1,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(2,2,"期末余额",format5);
				wsheet.addCell(label);
				label = new Label(3,2,"期初余额",format5);
				wsheet.addCell(label);
				label = new Label(4,2,"项        目",format5);
				wsheet.addCell(label);
				label = new Label(5,2,"行次",format5);
				wsheet.addCell(label);
				label = new Label(6,2,"期末余额",format5);
				wsheet.addCell(label);
				label = new Label(7,2,"期初余额",format5);
				wsheet.addCell(label);
				for(int i=0; i<53; i++){
					label = new Label(0,i+3,CwbbZcfzbzcList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(1,i+3,CwbbZcfzbzcList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+3,CwbbZcfzbzcList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(3,i+3,CwbbZcfzbzcList.get(i).getNcs(),format7);
					wsheet.addCell(label);
					label = new Label(4,i+3,CwbbZcfzbfzList.get(i).getMc(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,CwbbZcfzbfzList.get(i).getHc(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+3,CwbbZcfzbfzList.get(i).getQms(),format7);
					wsheet.addCell(label);
					label = new Label(7,i+3,CwbbZcfzbfzList.get(i).getNcs(),format7);
					wsheet.addCell(label);
				}

				//合并单元格
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(3, 1, 4, 1);
				//设置列宽
				wsheet.setColumnView(0, 35);
				wsheet.setColumnView(1, 6);
				wsheet.setColumnView(2, 20);
				wsheet.setColumnView(3, 20);
				wsheet.setColumnView(4, 35);
				wsheet.setColumnView(5, 6);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<54; k++){
					wsheet.setRowView(k+2, 500, false);
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
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readZcfzbGJGSExcel(HttpServletRequest request,
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
			String cwbbZcfzb_id = request.getParameter("cwbbZcfzb_id");
			String sbsj = request.getParameter("sbsj");
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
			CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
			cwbbZcfzb.setBt("资产负债表");
			cwbbZcfzb.setCzr(UserInfo.getName());
			cwbbZcfzb.setCzrID(UserInfo.getUsername());
			cwbbZcfzb.setCzrdw(UserInfo.getCompany());
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			cwbbZcfzb.setCzsj(data1);
			cwbbZcfzb.setSbsj(DateFormat(sbsj));
			if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
				cwbbZcfzb.setId(Integer.parseInt(cwbbZcfzb_id));
			}
			ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
			ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<54||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标1
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("1".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					if(num!=0){
						for(int i=0; i<53; i++){
							CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
							cwbbZcfzbzc.setMc(sheet[0].getCell(0,i+num).getContents());
							cwbbZcfzbzc.setHc(sheet[0].getCell(1,i+num).getContents());
							cwbbZcfzbzc.setQms(sheet[0].getCell(2,i+num).getContents());
							cwbbZcfzbzc.setNcs(sheet[0].getCell(3,i+num).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbzc.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbzcList.add(cwbbZcfzbzc);
							
							CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
							cwbbZcfzbfz.setMc(sheet[0].getCell(4,i+num).getContents());
							cwbbZcfzbfz.setHc(sheet[0].getCell(5,i+num).getContents());
							cwbbZcfzbfz.setQms(sheet[0].getCell(6,i+num).getContents());
							cwbbZcfzbfz.setNcs(sheet[0].getCell(7,i+num).getContents());
							if(!"".equals(cwbbZcfzb_id)&&cwbbZcfzb_id!=null){
								cwbbZcfzbfz.setZcfzbID(Integer.parseInt(cwbbZcfzb_id));
							}
							CwbbZcfzbfzList.add(cwbbZcfzbfz);
						}
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/FinancialStatements/cwbbZcfzbGJGSEdit.jsp").forward(request,
					response);
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void DHC_PushNum(HttpServletRequest request,
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

			Float[][] resultArr = null;//最终结果（二维数组）
			int r = 0;//行数
			int c = 0;//列数
			
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

			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				r = sheet[0].getRows();//行数
				c = sheet[0].getColumns();//列数
				resultArr = new Float[r-2][c-1];//最终结果（二维数组）
				//获取数据
				for(int i=1; i<c; i++){//每列
					Float beginNum=null;//基础数
					Float chaNum = null;//差值
					Float resultAbsNum=null;//平均值
					for(int j=2; j<r; j++){//每行
						Float resultNum=null;//总和（每次都要回零）
						beginNum = Float.parseFloat(sheet[0].getCell(i,j).getContents());
						for(int m=2; m<r; m++){
							chaNum = beginNum-Float.parseFloat(sheet[0].getCell(i,m).getContents());
							if(resultNum!=null){
								resultNum = resultNum + Math.abs(chaNum);
							}else{
								resultNum = Math.abs(chaNum);
							}
						}

						resultAbsNum = resultNum/(r-2);
						resultArr[j-2][i-1]=resultAbsNum;
//						System.out.println("@@@@@@@@@@@@@@@"+resultAbsNum);
					}
				}
				
				//最后关闭资源，释放内存    
				wb.close();
			}
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
				WritableSheet wsheet = wwb.createSheet("董洪超",1);
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 8, WritableFont.NO_BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				Label label = new Label(0,0,resultArr[0][0].toString(),format1);
				//设置表头
				for(int i=0; i<r-2; i++){
					for(int j=0; j<c-1; j++){
						label = new Label(j,i,resultArr[i][j].toString(),format1);
						wsheet.addCell(label);
					}
				}

				//设置列宽
//				wsheet.setColumnView(0, 20);
				//设置行宽
//				wsheet.setRowView(0, 680, false);
//				wsheet.setRowView(1, 400, false);
//				for(int k=0; k<54; k++){
//					wsheet.setRowView(k+2, 500, false);
//				}
				
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
			
			
			
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
//			request.setAttribute("cwbbZcfzb", cwbbZcfzb);
//			request.setAttribute("CwbbZcfzbzcList", CwbbZcfzbzcList);
//			request.setAttribute("CwbbZcfzbfzList", CwbbZcfzbfzList);
//			request.setAttribute("r", r-2);
//			request.setAttribute("c", c-1);
//			request.setAttribute("resultArr", resultArr);
//			request.getRequestDispatcher("/jsp/FinancialStatements/DHC_GetNum.jsp").forward(request,
//					response);
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
