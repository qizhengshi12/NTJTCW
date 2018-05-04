package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.HtbbDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.HtbbSetTime;
import com.safety.entity.Permissions;

public class HtbbServlet   extends HttpServlet{
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
		if("getHtbb".equals(action)){//后台报表
			getHtbb(request,response);
		}else if("setTime".equals(action)){//进入管理界面
			setTime(request,response);
		}else if("SaveHtbb".equals(action)){//保存或修改
			SaveHtbb(request,response);
		}
	}

	/*
	 *  后台报表
	 * 
	 */
	protected void getHtbb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if("管理员".equals(UserInfo.getRoles())||"财务负责人".equals(UserInfo.getRoles())||"审计负责人".equals(UserInfo.getRoles())){
			String result = request.getParameter("result");
			// List
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatementsManager/getHtbb.jsp").forward(request,
					response);
		}else{
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入管理界面
	 * 
	 */
	protected void setTime(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String bbID = request.getParameter("bbID");
			//查询人员名单
			HtbbSetTime htbbSetTime = new HtbbSetTime();
			HtbbDao htbbDao = new HtbbDao();
			htbbSetTime = htbbDao.querySetTime(Integer.parseInt(bbID));
			htbbSetTime.setBbID(Integer.parseInt(bbID));
			if("1".equals(bbID)){
				htbbSetTime.setBbmc("预算执行表");
			}else if("2".equals(bbID)){
				htbbSetTime.setBbmc("事业单位主要财务指标表");
			}else if("3".equals(bbID)){
				htbbSetTime.setBbmc("三公经费表");
			}else if("4".equals(bbID)){
				htbbSetTime.setBbmc("资产负债表");
			}else if("5".equals(bbID)){
				htbbSetTime.setBbmc("损益表");
			}else if("6".equals(bbID)){
				htbbSetTime.setBbmc("落实收费政策季度自查表");
			}else if("7".equals(bbID)){
				htbbSetTime.setBbmc("主要运行指标统计表（六县市、运管处）");
			}else if("8".equals(bbID)){
				htbbSetTime.setBbmc("主要运行指标统计表（航道处）");
			}else if("9".equals(bbID)){
				htbbSetTime.setBbmc("客运出站运输量统计表（汽运集团）");
			}else if("10".equals(bbID)){
				htbbSetTime.setBbmc("海事签证月度同比表（海事局）");
			}
			if(htbbSetTime.getTznr()==null){
				htbbSetTime.setTzry("");
				htbbSetTime.setTzryID("");
			}
			request.setAttribute("htbbSetTime", htbbSetTime);
			request.getRequestDispatcher("/jsp/StatementsManager/setTime.jsp").forward(request,
					response);
		}
	}
	/*
	 *  保存或修改
	 * 
	 */
	protected void SaveHtbb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String ID = request.getParameter("ID");
			String bbID = request.getParameter("bbID");
			String setlx = request.getParameter("setlx");
			String mday = request.getParameter("mday");
			String qmonth = request.getParameter("qmonth");
			String qmday = request.getParameter("qmday");
			String yday = request.getParameter("yday");
			String tznr = request.getParameter("tznr");
			String tzry = request.getParameter("tzry");
			String tzryID = request.getParameter("tzryID");
			//若时间类内容为空，无法保存
			if(!"4".equals(setlx)){
				yday="2000-01-01";
			}
			
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			HtbbDao htbbDao = new HtbbDao();
			HtbbSetTime htbbSetTime = new HtbbSetTime();
			htbbSetTime.setBbID(Integer.parseInt(bbID));
			htbbSetTime.setMday(Integer.parseInt(mday));
			htbbSetTime.setQmonth(Integer.parseInt(qmonth));
			htbbSetTime.setQmday(Integer.parseInt(qmday));
			htbbSetTime.setSetlx(Integer.parseInt(setlx));
			htbbSetTime.setYday(DateFormat(yday));
			htbbSetTime.setTznr(tznr);
			htbbSetTime.setTzry(tzry);
			htbbSetTime.setTzryID(tzryID);
			htbbSetTime.setCzr(UserInfo.getName());
			htbbSetTime.setCzrID(UserInfo.getUsername());
			htbbSetTime.setCzsj(data1);
			//保存或修改
			if("0".equals(ID)||ID==null){
				htbbDao.insertSetTime(htbbSetTime);
			}else{
				htbbSetTime.setId(Integer.parseInt(ID));
				htbbDao.updateSetTime(htbbSetTime);
			}

			request.setAttribute("result", "设置成功");
			request.getRequestDispatcher("HtbbServlet?action=setTime&bbID="+bbID).forward(request,
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
