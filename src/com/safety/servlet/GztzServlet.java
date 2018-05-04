package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.GztzDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Gztzhf;
import com.safety.entity.PaginationTool;
import com.safety.entity.Gztz;
import com.safety.entity.Permissions;


public class GztzServlet  extends HttpServlet{
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
		if("getGztzXFList".equals(action)){//下发通知列表
			getGztzXFList(request,response);
		}else if("insertGztzXF".equals(action)){//新增通知
			insertGztzXF(request,response);
		}else if("showGztzXF".equals(action)){//查看通知
			showGztzXF(request,response);
		}else if("deleteGztzXF".equals(action)){//删除通知
			deleteGztzXF(request,response);
		}else if("showGztzHF".equals(action)){//查看回复情况
			showGztzHF(request,response);
		}else if("getGztzJSList".equals(action)){//接收通知列表
			getGztzJSList(request,response);
		}else if("updateGztzHF".equals(action)){//回复
			updateGztzHF(request,response);
		}
	}
	
	/*
	 * 下发通知列表
	 * 
	 */
	protected void getGztzXFList(HttpServletRequest request,
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
			
		}else if(UserPer.getGztz().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//查询工作通知列表
			ArrayList<Gztz> GztzList = new ArrayList<Gztz>();
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
			String cxmc = request.getParameter("cxmc");
			String cxdd = request.getParameter("cxdd");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				srbt="";
				cxmc = "";
				cxdd = "";
				cxssrq1 = "";
				cxssrq2 = "";
			}
			GztzDao gztzDao = new GztzDao();
			countAll = gztzDao.queryGztzXFListCount(srbt,UserInfo.getUsername());
			GztzList = gztzDao.queryGztzXFList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("GztzList", GztzList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxmc", cxmc);
			request.setAttribute("cxdd", cxdd);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzXFList.jsp").forward(request,
					response);
		}
	}
	

	/*
	 *  新增通知
	 * 
	 */
	protected void insertGztzXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String tzmc = request.getParameter("tzmc");
			String tznr = request.getParameter("tznr");
			String tzsj = request.getParameter("tzsj");
			String tzdd = request.getParameter("tzdd");
			String tzry = request.getParameter("ry");
			String tzryID = request.getParameter("ryID");
			String SMSFlag = request.getParameter("SMSFlag");//是否发送短信（0：发送；1：不发送）
			//保存下发记录
			GztzDao gztzDao = new GztzDao();
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Gztz gztz = new Gztz();
			gztz.setTzmc(tzmc);
			gztz.setTznr(tznr);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Timestamp ts = null;
			try {
				ts = new Timestamp(df.parse(tzsj).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gztz.setTzsj(ts);
			gztz.setTzdd(tzdd);
			gztz.setTzry(tzry);
			gztz.setCzr(UserInfo.getName());
			gztz.setCzrID(UserInfo.getUsername());
			gztz.setCzsj(data1);
			int GztzID = 0;
			//准备通知
			String[] tzryIDList = tzryID.split(",");
			//去掉重复项
			List list = Arrays.asList(tzryIDList);
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
			gztz.setTzryID(hfidNewList);
			GztzID = gztzDao.insertGztz(gztz);
			Iterator<String> hfid1=hfidList.iterator();
			ArrayList<Gztzhf> GztzhfList =  new ArrayList<Gztzhf>();
			while(hfid1.hasNext()){
				ContentZzxx Zzxx = new ContentZzxx();
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				Zzxx = contentZzxxDao.queryZzxxByUserName(hfid1.next());
				//开始通知
				Gztzhf gztzhf = new Gztzhf();
				gztzhf.setHfr(Zzxx.getName());
				gztzhf.setHfrID(Zzxx.getUsername());
				gztzhf.setTzid(GztzID);
				gztzhf.setTzmc(tzmc);
				GztzhfList.add(gztzhf);
				
				//判断是否发短信
				if("0".equals(SMSFlag)){
					//发送短信
					String nr = UserInfo.getName()+"：您有一个新的会议通知，请及时查看";
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),nr);
				}
				
			}
			gztzDao.insertGztzhf(GztzhfList);
			//将工作通知回复IDList保存至下发记录中
			
			
			request.getRequestDispatcher("GztzServlet?action=getGztzXFList&result='操作成功'&srbt=").forward(request,
					response);
		}
	}

	
	/*
	 *  删除通知
	 * 
	 */
	protected void deleteGztzXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		GztzDao gztzDao = new GztzDao();
		if(!"".equals(id)&&id!=null){
			gztzDao.DeleteGztzById(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("GztzServlet?action=getGztzXFList&result='删除成功'&srbt=");
		rd.forward(request, response);
		return;
	}


	/*
	 *  根据ID查看通知
	 * 
	 */
	protected void showGztzXF(HttpServletRequest request,
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
			String gztzid = request.getParameter("gztzid");
			GztzDao gztzDao = new GztzDao();
			//回复
			Gztz gztz = new Gztz();
			gztz = gztzDao.queryGztzByID(Integer.parseInt(gztzid));
			request.setAttribute("gztz", gztz);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzXFShow.jsp").forward(request,
					response);
		}
	}
	/*
	 *  查看回复情况
	 * 
	 */
	protected void showGztzHF(HttpServletRequest request,
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
			String tzid = request.getParameter("tzid");
			GztzDao gztzDao = new GztzDao();
			//回复
			ArrayList<Gztzhf> GztzhfList = new ArrayList<Gztzhf>();
			if(!"".equals(tzid)&&tzid!=null){
				GztzhfList = gztzDao.queryGztzXFByIDList(Integer.parseInt(tzid));
			}
			request.setAttribute("GztzhfList", GztzhfList);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzHFShow.jsp").forward(request,
					response);
		}
	}
	
	/*
	 * 接收通知列表
	 * 
	 */
	protected void getGztzJSList(HttpServletRequest request,
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
			
		}else if(UserPer.getGztz().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//查询接收到的工作通知列表
			ArrayList<Gztzhf> GztzhfList = new ArrayList<Gztzhf>();
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
			String cxmc = request.getParameter("cxmc");
			String sfhf = request.getParameter("sfhf");//0:所有；1:已回复；2：未回复；
			if("".equals(srbt)){
				cxmc="";
				sfhf="0";
			}
			GztzDao gztzDao = new GztzDao();
			countAll = gztzDao.queryGztzJSListCount(srbt,UserInfo.getUsername());
			GztzhfList = gztzDao.queryGztzJSList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("GztzhfList", GztzhfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("sfhf", sfhf);
			request.setAttribute("cxmc", cxmc);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzJSList.jsp").forward(request,
					response);
		}
	}
	
	
	/*
	 *  提交回复
	 * 
	 */
	protected void updateGztzHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");
			String hfnr = request.getParameter("hfnr");
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//修改
			GztzDao gztzDao = new GztzDao();
			Gztzhf gztzhf = new Gztzhf();
			gztzhf.setId(Integer.parseInt(id));
			gztzhf.setHfnr(hfnr);
			gztzhf.setHfsj(data1);
			gztzDao.updateGztzHF(gztzhf);
			
			request.getRequestDispatcher("GztzServlet?action=getGztzJSList&sfhf=0").forward(request,
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
