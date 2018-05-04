package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentFlDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.EmailDao;
import com.safety.dao.GztzDao;
import com.safety.dao.LearningCornerDao;
import com.safety.dao.MessageDao;
import com.safety.dao.PermissionsDao;
import com.safety.dao.PostInformationDao;
import com.safety.dao.WjglDao;
import com.safety.dao.ZdxmsbDao;
import com.safety.entity.ContentFl;
import com.safety.entity.ContentZzxx;
import com.safety.entity.LearningCorner;
import com.safety.entity.Permissions;
import com.safety.entity.PostInformation;
import com.safety.entity.TopScroll;
import com.safety.entity.Wjglxf;
 


public class CheckLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("login".equals(action)){//登陆
			login(request,response);
		}else if("Refresh".equals(action)){//刷新
			Refresh(request,response);
		}else if("NOFunction".equals(action)){//暂未开放
			NOFunction(request,response);
		}

	}
	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setContentType("text/html;charset=GBK");
		HttpSession session = request.getSession(); 
		/* 首先取得jsp页面传来的参数信息 */
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		String code = request.getParameter("code");
		if(!"".equals(code)&&code!=null){
			code =code.toUpperCase();
		}
//		String sessionCode = (String) session.getAttribute("code");
		String errMessage = "";
		/* 验证输入信息的完整行和正确性 */
		if (username.equals(""))
			errMessage += "用户名不能为空!";
		if (password.equals(""))
			errMessage += "密码输入不能为空!";
//		if (!code.equals(sessionCode.toUpperCase()))
//			errMessage += "验证码输入不正确!";
		if (password.indexOf("'") != -1)
			errMessage += "请不要进行sql注入攻击!";
		/* 如果验证没有通过转到登陆页并提示错误信息 */
		if (!errMessage.equals("")) {
			request.setAttribute("errMessage", errMessage);
			RequestDispatcher wm = request.getRequestDispatcher("login.jsp");
			wm.forward(request, response);
			return;
		}
		/*如果是管理员身份用户名和密码都验证成功则设置session的值然后重定向到管理首页*/
		if (ContentZzxxDao.checkLogin(username,password)) {
			// 检测是否是在不同浏览器下已有相同用户Id的用户登陆  
			List onlineUsers = MySessionServlet.getOnlineUsers();  
			for(int i = 0; i < onlineUsers.size(); i++){  
			    // 在线用户视图  
				ContentZzxx onlineUserView = (ContentZzxx)onlineUsers.get(i);  
			    // 在线用户Session  
			    HttpSession onlineUserSession = onlineUserView.getUserSession();  
			    // 同一用户  
			    if(username.equals(onlineUserView.getUsername())){  
			        try{  
			            if(!onlineUserSession.getId().equals(request.getSession().getId())){  
			                // 强制注销之前登陆用户，并将该用户从在线用户列表删除  
			                onlineUserSession.invalidate();  
			            }  
			        }catch(IllegalStateException ise){}  
			        break;  
			    }      
			}  
			//查询是否有未回复的文件
			WjglDao wjglDao = new WjglDao();
			int  MyWjglHF = wjglDao.queryWjglWHFCount(username);
			session.setAttribute("MyWjglHF", MyWjglHF);
			//查询是否有未审核的重大项目申报文件
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			int  MyZdxmsbSH = zdxmsbDao.queryMyZdxmsbSHCount(username);
			session.setAttribute("MyZdxmsbSH", MyZdxmsbSH);
			//查询是否有未回复的会议通知
			GztzDao gztzDao = new GztzDao();
			int  MyGztzHF = gztzDao.queryGztzWHFCount(username);
			session.setAttribute("MyGztzHF", MyGztzHF);
			//查询是否有未阅读的短信通知
			MessageDao messageDao = new MessageDao();
			int  MyMessage = messageDao.MyMessageCount(username);
			session.setAttribute("MyMessage", MyMessage);
			//查询是否有未阅读的邮件
			EmailDao emailDao = new EmailDao();
			int  MyEmail = emailDao.MyEmailCount(username);
			session.setAttribute("MyEmail", MyEmail);
			//查询本人部分信息
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = ContentZzxxDao.queryThisZzxxByUserName(username);
			session.setAttribute("UserInfo", Zzxx);
			//查询本人权限
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(username);
			session.setAttribute("UserPer", permissions);
			//查询首页图片记录
			ArrayList<PostInformation> Informat = new ArrayList<PostInformation>();
			PostInformationDao postInformationDao = new PostInformationDao();
			Informat = postInformationDao.queryInformatFirst();
			session.setAttribute("Informat", Informat);
			//查询滚动字幕
			TopScroll topScroll = new TopScroll();
			topScroll = postInformationDao.queryTopScroll();
			if(topScroll.getId()!=0){
				session.setAttribute("gddirection", topScroll.getDirection());
				session.setAttribute("gdspeed", topScroll.getSpeed());
				session.setAttribute("gdcontent", topScroll.getContent());
			}
			//通知公告（文件管理）放5个
			ArrayList<Wjglxf> Wjglsy = new ArrayList<Wjglxf>();
			Wjglsy = wjglDao.queryWjglSy(5);
			session.setAttribute("Wjglsy", Wjglsy);
			//法律法规 放5个
			ArrayList<ContentFl> FlSy = new ArrayList<ContentFl>();
			ContentFlDao contentFlDao = new ContentFlDao();
			FlSy = contentFlDao.queryFlSy(5);
			session.setAttribute("FlSy", FlSy);
			//学习园地 放5个
			ArrayList<LearningCorner> LearningCornerSy = new ArrayList<LearningCorner>();
			LearningCornerDao learningCornerDao = new LearningCornerDao();
			LearningCornerSy = learningCornerDao.queryLcSy(5);
			session.setAttribute("LearningCornerSy", LearningCornerSy);
			
			//获取登录IP地址
			String ip = request.getHeader("x-forwarded-for");
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			//记录登录日志
			String logStr = "登录用户："+Zzxx.getName()+";用户名："+username+";IP地址："+ip;
			getServletContext().log(logStr);
			response.sendRedirect("main.jsp");
		} else {
			errMessage += "用户名或密码错误，请重新输入";
			request.setAttribute("errMessage",errMessage);
			RequestDispatcher wm = request.getRequestDispatcher("login.jsp");
			wm.forward(request, response);
		}

	}
	protected void Refresh(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setContentType("text/html;charset=GBK");
		HttpSession session = request.getSession(); 
		//获取当前用户
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			String username = UserInfo.getUsername(); 
			//查询是否有未回复的文件
			WjglDao wjglDao = new WjglDao();
			int  MyWjglHF = wjglDao.queryWjglWHFCount(username);
			session.setAttribute("MyWjglHF", MyWjglHF);
			//查询是否有未审核的重大项目申报文件
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			int  MyZdxmsbSH = zdxmsbDao.queryMyZdxmsbSHCount(username);
			session.setAttribute("MyZdxmsbSH", MyZdxmsbSH);
			//查询是否有未回复的会议通知
			GztzDao gztzDao = new GztzDao();
			int  MyGztzHF = gztzDao.queryGztzWHFCount(username);
			session.setAttribute("MyGztzHF", MyGztzHF);
			//查询是否有未阅读的短信通知
			MessageDao messageDao = new MessageDao();
			int  MyMessage = messageDao.MyMessageCount(username);
			session.setAttribute("MyMessage", MyMessage);
			//查询是否有未阅读的邮件
			EmailDao emailDao = new EmailDao();
			int  MyEmail = emailDao.MyEmailCount(username);
			session.setAttribute("MyEmail", MyEmail);
			//查询本人部分信息
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = ContentZzxxDao.queryThisZzxxByUserName(username);
			session.setAttribute("UserInfo", Zzxx);
			//查询本人权限
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(username);
			session.setAttribute("UserPer", permissions);
			//查询首页图片记录
			ArrayList<PostInformation> Informat = new ArrayList<PostInformation>();
			PostInformationDao postInformationDao = new PostInformationDao();
			Informat = postInformationDao.queryInformatFirst();
			session.setAttribute("Informat", Informat);
			//查询滚动字幕
			TopScroll topScroll = new TopScroll();
			topScroll = postInformationDao.queryTopScroll();
			if(topScroll.getId()!=0){
				session.setAttribute("gddirection", topScroll.getDirection());
				session.setAttribute("gdspeed", topScroll.getSpeed());
				session.setAttribute("gdcontent", topScroll.getContent());
			}
			//通知公告（文件管理）放5个
			ArrayList<Wjglxf> Wjglsy = new ArrayList<Wjglxf>();
			Wjglsy = wjglDao.queryWjglSy(5);
			session.setAttribute("Wjglsy", Wjglsy);
			//法律法规 放5个
			ArrayList<ContentFl> FlSy = new ArrayList<ContentFl>();
			ContentFlDao contentFlDao = new ContentFlDao();
			FlSy = contentFlDao.queryFlSy(5);
			session.setAttribute("FlSy", FlSy);
			//学习园地 放5个
			ArrayList<LearningCorner> LearningCornerSy = new ArrayList<LearningCorner>();
			LearningCornerDao learningCornerDao = new LearningCornerDao();
			LearningCornerSy = learningCornerDao.queryLcSy(5);
			session.setAttribute("LearningCornerSy", LearningCornerSy);
			
			response.setContentType("text/html;charset=utf-8");
	    	PrintWriter out = response.getWriter();
	//		out.print("<script>");
	//    	out.print("window.parent.callbackdel()");
	//    	out.print("</script>");
	    	out.close();
		}
	}
	
	/*
	 *  暂未开放
	 * 
	 */
	protected void NOFunction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			request.setAttribute("result", "功能暂未开发");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
		}
	}
}
