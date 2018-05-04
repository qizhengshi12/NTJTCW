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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MessagePlatForm;
import com.safety.dao.WjglDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.WjglTJ;
import com.safety.entity.Wjglhf;
import com.safety.entity.Wjglxf;


public class WjglServlet  extends HttpServlet{
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
		if("getWjglList".equals(action)){//所有文件——列表
			getWjglList(request,response);
		}else if("getWjglXFList".equals(action)){//本人下发文件——列表
			getWjglXFList(request,response);
		}if("getWjglJSList".equals(action)){//需回复文件——列表
			getWjglJSList(request,response);
		}else if("showWjglXF".equals(action)){//下发文件——查看
			showWjglXF(request,response);
		}else if("insertWjglXF".equals(action)){//下发文件——新增
			insertWjglXF(request,response);
		}else if("deleteWjglXF".equals(action)){//下发文件——删除
			deleteWjglXF(request,response);
		}else if("showWjglHF".equals(action)){//回复文件——查看
			showWjglHF(request,response);
		}else if("saveWjglHF".equals(action)){//提交回复
			saveWjglHF(request,response);
		}else if("downLoadXF".equals(action)){//下载文件下发
			downLoadXF(request,response);
		}else if("downLoadHF".equals(action)){//下载文件回复
			downLoadHF(request,response);
		}else if("getWjglTJList".equals(action)){// 已下发文件——统计综合列表
			getWjglTJList(request,response);
		}else if("getWjglCGList".equals(action)){//本人草稿文件——列表
			getWjglCGList(request,response);
		}else if("editWjglCG".equals(action)){//本人草稿文件——编辑
			editWjglCG(request,response);
		}else if("insertWjglCG".equals(action)){//本人草稿文件——新增
			insertWjglCG(request,response);
		}else if("wjglXFInsert".equals(action)){//跳转到下发文件页面
			wjglXFInsert(request,response);
		}
	}
	/*
	 * 所有文件——列表
	 * 
	 */
	protected void getWjglList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//查询下发列表
			ArrayList<Wjglxf> WjglList = new ArrayList<Wjglxf>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxwjlx = request.getParameter("cxwjlx");
			String cxwjh = request.getParameter("cxwjh");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//若为0：则从首页进入；1：则从菜单进入
			String flag = request.getParameter("flag");
			if("0".equals(flag)||"1".equals(flag)||"".equals(srbt)||srbt==null){
				//通过标题查询
				cxwjmc = "";
				cxwjlx = "";
				cxwjh = "";
				cxcompany = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " where fszt='1' ";
			}else{
				srbt= srbt + " and fszt='1' ";
			}
			countAll = wjglDao.queryWjglXFListByBtCount(srbt);
			WjglList = wjglDao.queryWjglXFListByBt(srbt, begin, pageSize);
			request.setAttribute("WjglList", WjglList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("flag", flag);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxwjlx", cxwjlx);
			request.setAttribute("cxwjh", cxwjh);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglList.jsp").forward(request,
					response);
		}
	}
	/*
	 * 跳转到下发文件页面
	 * 
	 */
	protected void wjglXFInsert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
//		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
//		}else if(UserPer.getWjgl().indexOf("1")==-1){
//			response.setContentType("text/html;charset=gb2312");
//			request.setAttribute("result", "无查询权限");
//			request.getRequestDispatcher("desk.jsp").forward(request,
//					response);
//			
		}else{
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglXFInsert.jsp").forward(request,
					response);
		}
	}
	/*
	 * 已下发文件——列表
	 * 
	 */
	protected void getWjglXFList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//查询下发列表
			ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxwjlx = request.getParameter("cxwjlx");
			String cxwjh = request.getParameter("cxwjh");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)||"".equals(srbt)||srbt==null){
				//通过标题查询
				cxwjmc = "";
				cxwjlx = "";
				cxwjh = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " where fqrID ='"+UserInfo.getUsername()+"' and fszt='1'";
			}else{
				srbt= srbt + " and fszt='1' ";
			}
			countAll = wjglDao.queryWjglXFListByBtCount(srbt);
			WjglxfList = wjglDao.queryWjglXFListByBt(srbt, begin, pageSize);
			request.setAttribute("WjglxfList", WjglxfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxwjlx", cxwjlx);
			request.setAttribute("cxwjh", cxwjh);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglXFList.jsp").forward(request,
					response);
		}
	}
	/*
	 * 需回复文件——列表
	 * 
	 */
	protected void getWjglJSList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxhfzt = request.getParameter("cxhfzt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//若为1：则从菜单进入；若为2：从首页进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)||"".equals(srbt)||srbt==null){
				//通过标题查询
				cxwjmc = "";
				cxhfzt = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt="";
			}
			if("2".equals(flag)){
				//通过标题查询
				cxwjmc = "";
				cxhfzt = "未回复";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " and hfzt ='未回复'";
			}
			//是否需要回复（先查询回复列表）
			ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
			wjglhfList = wjglDao.queryWjglhfByHfrID(UserInfo.getUsername(),srbt, begin, pageSize);
			countAll = wjglDao.queryWjglhfByHfrIDCount(UserInfo.getUsername(),srbt);
			request.setAttribute("wjglhfList", wjglhfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxhfzt", cxhfzt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglJSList.jsp").forward(request,
					response);
		}
	}
	/*
	 * 已下发文件——统计综合列表
	 * 
	 */
	protected void getWjglTJList(HttpServletRequest request,
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
			WjglDao wjglDao = new WjglDao();
			//查询单位列表
			ArrayList<WjglTJ> CompanyList = new ArrayList<WjglTJ>();
			CompanyList = wjglDao.QueryCompanyNameById("node_zzxx",0);
			//查询下发列表
			ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 50;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			//通过标题查询
			String srbt = request.getParameter("srbt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			if("".equals(srbt)){
				cxssrq1="";
				cxssrq2="";
			}
			
			countAll = wjglDao.queryWjglTJListByBtCount("局机关",srbt);
			WjglxfList = wjglDao.queryWjglTJListByBt("局机关",srbt, begin, pageSize);
			//以单位为准制作表格
			ArrayList<String> NewList = new ArrayList<String>();
			String NewStr = "";
			for(int i=0; i<WjglxfList.size(); i++){
				NewStr = "";
				//文件ID和文件名称
				int wjid = WjglxfList.get(i).getId();
				NewStr = wjid+";"+ WjglxfList.get(i).getWjmc();
				//下发单位
				for(int j=0; j<CompanyList.size(); j++){
					String dwmc = CompanyList.get(j).getName();
					int counthf = CompanyList.get(j).getCounthf();
					int countcs = CompanyList.get(j).getCountcs();
					//查询回复文件
					Wjglhf wjglhf = wjglDao.queryWjglhfByIDMC(wjid,dwmc);
					if(wjglhf.getId()!=0){
						NewStr = NewStr+";"+wjglhf.getHfzt()+";"+wjglhf.getSfcs();
						if("未回复".equals(wjglhf.getHfzt())){
							CompanyList.get(j).setCounthf(counthf+1);
						}
						if("超时".equals(wjglhf.getSfcs())){
							CompanyList.get(j).setCountcs(countcs+1);
						}
						
					}else{
						NewStr = NewStr+";"+"&nbsp"+";"+"&nbsp";
					}
				}
				//回复结果
				NewList.add(NewStr);
			}
			request.setAttribute("NewList", NewList);
			request.setAttribute("CompanyList", CompanyList);
			request.setAttribute("result", result);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglTJList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  下发文件——新增
	 * 
	 */
	protected void insertWjglXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String wjmc = request.getParameter("wjmc");//文件名称
			String wjlx = request.getParameter("wjlx");//文件类型
			String fwdw = UserInfo.getCompany();//发文单位
			String wjh = request.getParameter("wjh");//发文号
			String hfqx = request.getParameter("hfqx");//回复期限
			String ry = request.getParameter("ry");//回复人员
			String ryID = request.getParameter("ryID");//回复人员IDlist
			String fqr = UserInfo.getName();//发起人
			String fqrID = UserInfo.getUsername();//发起人ID
			String gzyq = request.getParameter("gzyq");//工作要求
			String FileUrl = request.getParameter("FileUrl");//附件地址
			String fszt = request.getParameter("fszt");//是否发送——1：发送；2：保存草稿；
			String SMSFlag = request.getParameter("SMSFlag");//是否发送短信（0：发送；1：不发送）
			//保存下发记录
			WjglDao wjglDao = new WjglDao();
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Wjglxf wjglxf = new Wjglxf();
			wjglxf.setWjmc(wjmc);
			wjglxf.setFqsj(data1);//发起时间
			wjglxf.setWjlx(wjlx);
			wjglxf.setFwdw(fwdw);
			wjglxf.setWjh(wjh);
			wjglxf.setFqr(fqr);
			wjglxf.setFqrID(fqrID);
			wjglxf.setGzyq(gzyq);
			wjglxf.setFileUrl(FileUrl);
			wjglxf.setFszt(fszt);
			wjglxf.setHfry(ry);
			 
			int wjglxfID = 0;
			//需要回复
			if(!"".equals(hfqx)&&hfqx!=null){
				//准备下发
				String[] hfryList = ryID.split(",");
				//去掉重复项
				List list = Arrays.asList(hfryList);
				HashSet<String> hfidList = new HashSet<String>(list);
				Iterator<String> hfid=hfidList.iterator();
				String hfidNewList = "";
				while(hfid.hasNext()){
					if("".equals(hfidNewList)){
						hfidNewList = hfid.next();
					}else{
						hfidNewList = hfidNewList+","+ hfid.next();
					}
				}
				wjglxf.setHfryID(hfidNewList);
				wjglxf.setWhfryID(hfidNewList);
				wjglxf.setHfqx(DateFormat(hfqx));
				wjglxfID = wjglDao.insertWjglxf1(wjglxf);
				//仅仅保存时，不需要发送
				if("1".equals(fszt)){
					Iterator<String> hfid1=hfidList.iterator();
					ArrayList<Wjglhf> WjglhfList = new ArrayList<Wjglhf>();
					while(hfid1.hasNext()){
						ContentZzxx Zzxx = new ContentZzxx();
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						Zzxx = contentZzxxDao.queryZzxxByUserName(hfid1.next());
						//开始下发
						Wjglhf wjglhf = new Wjglhf();
						wjglhf.setWjmc(wjmc);
						wjglhf.setWjID(wjglxfID);
						wjglhf.setHfr(Zzxx.getName());
						wjglhf.setHfrID(Zzxx.getUsername());
						wjglhf.setSfcs("未超时");
						wjglhf.setHfzt("未回复");
						wjglhf.setCyzt("未查阅");
						wjglhf.setHfqx(DateFormat(hfqx));
						wjglhf.setCompany(Zzxx.getCompany());
						wjglhf.setCompanyID(Zzxx.getCompanyID());
						WjglhfList.add(wjglhf);
						//判断是否发短信
						if("0".equals(SMSFlag)){
							//发送短信
							String nr = fqr+"：您有一份需在"+wjglxf.getHfqx()+"之前回复的文件，请及时查看";
							messagePlatForm.submitShortMessage(Zzxx.getPhone(),nr);
						}
						
					}
					wjglDao.insertWjglhf(WjglhfList);
				}
			}else{
				wjglxfID = wjglDao.insertWjglxf2(wjglxf);
			}
			if("1".equals(fszt)){
				request.getRequestDispatcher("WjglServlet?action=getWjglXFList&result='操作成功'&srbt=").forward(request,
						response);
			}else{
				request.getRequestDispatcher("WjglServlet?action=getWjglCGList&result='操作成功'&srbt=").forward(request,
						response);
			}
		}
	}
	/*
	 *  下发材料列表——查看
	 * 
	 */
	protected void showWjglXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			// TODO Auto-generated method stub
			String id = request.getParameter("id");
			String flag = request.getParameter("flag");
			if(flag==null)flag="0";
			Wjglxf wjglxf = new Wjglxf();
			WjglDao wjglDao = new WjglDao();
			if(!"".equals(id)&&id!=null){
				wjglxf = wjglDao.queryWjglXFByID(Integer.parseInt(id));
			}
			//所有回复情况
			ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
			if("1".equals(flag)){
				wjglhfList = wjglDao.queryWjglhfByWJID(Integer.parseInt(id));
			}
			//回复
			Wjglhf wjglhf = new Wjglhf();
			if("2".equals(flag)||"3".equals(flag)){
				wjglhf = wjglDao.queryWjglhfByWJIDHF(Integer.parseInt(id), UserInfo.getUsername());
			}
			//已回复
			request.setAttribute("wjglhfList", wjglhfList);
			request.setAttribute("wjglhf", wjglhf);
			request.setAttribute("wjglxf", wjglxf);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglXFShow.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  下发材料列表——删除
	 * 
	 */
	protected void deleteWjglXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String FileUrl = request.getParameter("FileUrl");
		String address = request.getParameter("address");
		WjglDao wjglDao = new WjglDao();
		if(!"".equals(id)&&id!=null){
			wjglDao.DeleteWjglXFById(Integer.parseInt(id));
			//回复内容也删除
			ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
			wjglhfList = wjglDao.queryWjglhfByWJID(Integer.parseInt(id));
			for(int i=0;i<wjglhfList.size();i++){
				wjglDao.DeleteWjglHFById(wjglhfList.get(i).getId());
				if(!"".equals(wjglhfList.get(i).getFileUrl())&&(wjglhfList.get(i).getFileUrl())!=null){
					String str = wjglhfList.get(i).getFileUrl();
					String[] strList =  str.split(";");
					for(int k=0;k<strList.length; k++){
						//删除下载文件
						String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjglhf/"+strList[k];
						File file = new File(FullFilePath);  
						// 路径为文件且不为空则进行删除  
						if (file.isFile() && file.exists()) {  
							file.delete();  
						}
					}
				}
			}
			
		}
		if(!"".equals(FileUrl)&&FileUrl!=null){
			String[] FileUrlStr = FileUrl.split(";");
			//删除下载文件
			for(int j=0;j<FileUrlStr.length; j++){
				String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjgl/"+FileUrlStr[j];
				File file = new File(FullFilePath);  
				// 路径为文件且不为空则进行删除  
				if (file.isFile() && file.exists()) {  
					file.delete();  
				}
			}
		}
		
		if("1".equals(address)){
			RequestDispatcher rd = request.getRequestDispatcher("WjglServlet?action=getWjglList&flag=1");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("WjglServlet?action=getWjglXFList&flag=1");
			rd.forward(request, response);
		}
	}

	
	/*
	 * 下载文件（下发）
	 * 
	 */
	protected void downLoadXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
//		String srbt = request.getParameter("srbt");
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjgl/"+URL;
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
	 * 下载文件（回复）
	 * 
	 */
	protected void downLoadHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjglhf/"+URL;
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
	 *  根据ID查看具体回复内容
	 * 
	 */
	protected void showWjglHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			// TODO Auto-generated method stub
			String hfid = request.getParameter("hfid");
			WjglDao wjglDao = new WjglDao();
			//回复
			Wjglhf wjglhf = new Wjglhf();
			if(!"".equals(hfid)&&hfid!=null){
				wjglhf = wjglDao.queryWjglhfByHFID(Integer.parseInt(hfid));
			}
			request.setAttribute("wjglhf", wjglhf);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglHFShow.jsp").forward(request,
					response);
		}
	}
	/*
	 *  提交回复
	 * 
	 */
	protected void saveWjglHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String FileUrl = request.getParameter("FileUrl");
			String hfnr = request.getParameter("hfnr");
			String id = request.getParameter("id");
			String wjid = request.getParameter("wjid");
			String whfid = request.getParameter("whfid");
			String hfqx = request.getParameter("hfqx");
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Date  data1=new java.sql.Date(date.getTime());
			//修改
			WjglDao wjglDao = new WjglDao();
			Wjglhf wjglhf = new Wjglhf();
			wjglhf.setId(Integer.parseInt(id));
			wjglhf.setFileUrl(FileUrl);
			wjglhf.setHfnr(hfnr);
			wjglhf.setHfsj(data1);
			wjglhf.setHfzt("已回复");
	//		//比较整改时间
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
			String now = format.format(date);
			wjglhf.setSfcs(DateCompare(hfqx,now));
			wjglDao.updateWjglHF(wjglhf);
			String[] whfidList = whfid.split(",");
			String whfidNew = "";
			for(int i=0; i<whfidList.length; i++){
				if(!(UserInfo.getUsername()).equals(whfidList[i])){
					if("".equals(whfidNew)){
						whfidNew=whfidList[i];
					}else{
						whfidNew=whfidNew+","+whfidList[i];
					}
				}
			}
			//更新未回复ID字段
			wjglDao.updateWjglWhfID(wjid,whfidNew);
			
			request.getRequestDispatcher("WjglServlet?action=getWjglJSList&flag=1").forward(request,
					response);
		}
	}

	/*
	 * 本人草稿文件——列表
	 * 
	 */
	protected void getWjglCGList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//查询下发列表
			ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxwjlx = request.getParameter("cxwjlx");
			String cxwjh = request.getParameter("cxwjh");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)||"".equals(srbt)||srbt==null){
				//通过标题查询
				cxwjmc = "";
				cxwjlx = "";
				cxwjh = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " where fqrID ='"+UserInfo.getUsername()+"' and fszt='2'";
			}else{
				srbt= srbt + " and fszt='2' ";
			}
			countAll = wjglDao.queryWjglXFListByBtCount(srbt);
			WjglxfList = wjglDao.queryWjglXFListByBt(srbt, begin, pageSize);
			request.setAttribute("WjglxfList", WjglxfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxwjlx", cxwjlx);
			request.setAttribute("cxwjh", cxwjh);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglCGList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  本人草稿文件——编辑
	 * 
	 */
	protected void editWjglCG(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			// TODO Auto-generated method stub
			String id = request.getParameter("id");
			Wjglxf wjglxf = new Wjglxf();
			WjglDao wjglDao = new WjglDao();
			if(!"".equals(id)&&id!=null){
				wjglxf = wjglDao.queryWjglXFByID(Integer.parseInt(id));
			}
			request.setAttribute("wjglxf", wjglxf);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglCGInsert.jsp").forward(request,
					response);
		}
	}

	/*
	 *  本人草稿文件——新增
	 * 
	 */
	protected void insertWjglCG(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");//文件ID
			String wjmc = request.getParameter("wjmc");//文件名称
			String wjlx = request.getParameter("wjlx");//文件类型
			String fwdw = UserInfo.getCompany();//发文单位
			String wjh = request.getParameter("wjh");//发文号
			String hfqx = request.getParameter("hfqx");//回复期限
			String ry = request.getParameter("ry");//回复人员
			String ryID = request.getParameter("ryID");//回复人员IDlist
			String fqr = UserInfo.getName();//发起人
			String fqrID = UserInfo.getUsername();//发起人ID
			String gzyq = request.getParameter("gzyq");//工作要求
			String FileUrl = request.getParameter("FileUrl");//附件地址
			String fszt = request.getParameter("fszt");//是否发送——1：发送；2：保存草稿；
			String SMSFlag = request.getParameter("SMSFlag");//是否发送短信（0：发送；1：不发送）
			//保存下发记录
			WjglDao wjglDao = new WjglDao();
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Wjglxf wjglxf = new Wjglxf();
			wjglxf.setId(Integer.parseInt(id));
			wjglxf.setWjmc(wjmc);
			wjglxf.setFqsj(data1);//发起时间
			wjglxf.setWjlx(wjlx);
			wjglxf.setFwdw(fwdw);
			wjglxf.setWjh(wjh);
			wjglxf.setFqr(fqr);
			wjglxf.setFqrID(fqrID);
			wjglxf.setGzyq(gzyq);
			wjglxf.setFileUrl(FileUrl);
			wjglxf.setFszt(fszt);
			wjglxf.setHfry(ry);
			 
			//需要回复
			if(!"".equals(hfqx)&&hfqx!=null){
				//准备下发
				String[] hfryList = ryID.split(",");
				//去掉重复项
				List list = Arrays.asList(hfryList);
				HashSet<String> hfidList = new HashSet<String>(list);
				Iterator<String> hfid=hfidList.iterator();
				String hfidNewList = "";
				while(hfid.hasNext()){
					if("".equals(hfidNewList)){
						hfidNewList = hfid.next();
					}else{
						hfidNewList = hfidNewList+","+ hfid.next();
					}
				}
				wjglxf.setHfryID(hfidNewList);
				wjglxf.setWhfryID(hfidNewList);
				wjglxf.setHfqx(DateFormat(hfqx));
				wjglDao.updateWjglxf1(wjglxf);
				//仅仅保存时，不需要发送
				if("1".equals(fszt)){
					Iterator<String> hfid1=hfidList.iterator();
					ArrayList<Wjglhf> WjglhfList = new ArrayList<Wjglhf>();
					while(hfid1.hasNext()){
						ContentZzxx Zzxx = new ContentZzxx();
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						Zzxx = contentZzxxDao.queryZzxxByUserName(hfid1.next());
						//开始下发
						Wjglhf wjglhf = new Wjglhf();
						wjglhf.setWjmc(wjmc);
						wjglhf.setWjID(Integer.parseInt(id));
						wjglhf.setHfr(Zzxx.getName());
						wjglhf.setHfrID(Zzxx.getUsername());
						wjglhf.setSfcs("未超时");
						wjglhf.setHfzt("未回复");
						wjglhf.setCyzt("未查阅");
						wjglhf.setHfqx(DateFormat(hfqx));
						wjglhf.setCompany(Zzxx.getCompany());
						wjglhf.setCompanyID(Zzxx.getCompanyID());
						WjglhfList.add(wjglhf);
						//判断是否发短信
						if("0".equals(SMSFlag)){
							//发送短信
							String nr = fqr+"：您有一份需在"+wjglxf.getHfqx()+"之前回复的文件，请及时查看";
							messagePlatForm.submitShortMessage(Zzxx.getPhone(),nr);
						}
						
					}
					wjglDao.insertWjglhf(WjglhfList);
				}
			}else{
				wjglDao.updateWjglxf2(wjglxf);
			}
			if("1".equals(fszt)){
				request.getRequestDispatcher("WjglServlet?action=getWjglXFList&result='操作成功'&srbt=").forward(request,
						response);
			}else{
				request.getRequestDispatcher("WjglServlet?action=getWjglCGList&result='操作成功'&srbt=").forward(request,
						response);
			}
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
	//比较时间
	private String DateCompare(String DString, String now){
		String res="未超时";
		java.util.Date date = null;
		java.util.Date nowdate=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			date = format.parse(DString);
			nowdate = format.parse(now.substring(0, 10));
			if(date.getTime()<nowdate.getTime()){
				res="超时";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
