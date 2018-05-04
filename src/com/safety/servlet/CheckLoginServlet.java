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
		if("login".equals(action)){//��½
			login(request,response);
		}else if("Refresh".equals(action)){//ˢ��
			Refresh(request,response);
		}else if("NOFunction".equals(action)){//��δ����
			NOFunction(request,response);
		}

	}
	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setContentType("text/html;charset=GBK");
		HttpSession session = request.getSession(); 
		/* ����ȡ��jspҳ�洫���Ĳ�����Ϣ */
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		String code = request.getParameter("code");
		if(!"".equals(code)&&code!=null){
			code =code.toUpperCase();
		}
//		String sessionCode = (String) session.getAttribute("code");
		String errMessage = "";
		/* ��֤������Ϣ�������к���ȷ�� */
		if (username.equals(""))
			errMessage += "�û�������Ϊ��!";
		if (password.equals(""))
			errMessage += "�������벻��Ϊ��!";
//		if (!code.equals(sessionCode.toUpperCase()))
//			errMessage += "��֤�����벻��ȷ!";
		if (password.indexOf("'") != -1)
			errMessage += "�벻Ҫ����sqlע�빥��!";
		/* �����֤û��ͨ��ת����½ҳ����ʾ������Ϣ */
		if (!errMessage.equals("")) {
			request.setAttribute("errMessage", errMessage);
			RequestDispatcher wm = request.getRequestDispatcher("login.jsp");
			wm.forward(request, response);
			return;
		}
		/*����ǹ���Ա����û��������붼��֤�ɹ�������session��ֵȻ���ض��򵽹�����ҳ*/
		if (ContentZzxxDao.checkLogin(username,password)) {
			// ����Ƿ����ڲ�ͬ�������������ͬ�û�Id���û���½  
			List onlineUsers = MySessionServlet.getOnlineUsers();  
			for(int i = 0; i < onlineUsers.size(); i++){  
			    // �����û���ͼ  
				ContentZzxx onlineUserView = (ContentZzxx)onlineUsers.get(i);  
			    // �����û�Session  
			    HttpSession onlineUserSession = onlineUserView.getUserSession();  
			    // ͬһ�û�  
			    if(username.equals(onlineUserView.getUsername())){  
			        try{  
			            if(!onlineUserSession.getId().equals(request.getSession().getId())){  
			                // ǿ��ע��֮ǰ��½�û����������û��������û��б�ɾ��  
			                onlineUserSession.invalidate();  
			            }  
			        }catch(IllegalStateException ise){}  
			        break;  
			    }      
			}  
			//��ѯ�Ƿ���δ�ظ����ļ�
			WjglDao wjglDao = new WjglDao();
			int  MyWjglHF = wjglDao.queryWjglWHFCount(username);
			session.setAttribute("MyWjglHF", MyWjglHF);
			//��ѯ�Ƿ���δ��˵��ش���Ŀ�걨�ļ�
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			int  MyZdxmsbSH = zdxmsbDao.queryMyZdxmsbSHCount(username);
			session.setAttribute("MyZdxmsbSH", MyZdxmsbSH);
			//��ѯ�Ƿ���δ�ظ��Ļ���֪ͨ
			GztzDao gztzDao = new GztzDao();
			int  MyGztzHF = gztzDao.queryGztzWHFCount(username);
			session.setAttribute("MyGztzHF", MyGztzHF);
			//��ѯ�Ƿ���δ�Ķ��Ķ���֪ͨ
			MessageDao messageDao = new MessageDao();
			int  MyMessage = messageDao.MyMessageCount(username);
			session.setAttribute("MyMessage", MyMessage);
			//��ѯ�Ƿ���δ�Ķ����ʼ�
			EmailDao emailDao = new EmailDao();
			int  MyEmail = emailDao.MyEmailCount(username);
			session.setAttribute("MyEmail", MyEmail);
			//��ѯ���˲�����Ϣ
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = ContentZzxxDao.queryThisZzxxByUserName(username);
			session.setAttribute("UserInfo", Zzxx);
			//��ѯ����Ȩ��
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(username);
			session.setAttribute("UserPer", permissions);
			//��ѯ��ҳͼƬ��¼
			ArrayList<PostInformation> Informat = new ArrayList<PostInformation>();
			PostInformationDao postInformationDao = new PostInformationDao();
			Informat = postInformationDao.queryInformatFirst();
			session.setAttribute("Informat", Informat);
			//��ѯ������Ļ
			TopScroll topScroll = new TopScroll();
			topScroll = postInformationDao.queryTopScroll();
			if(topScroll.getId()!=0){
				session.setAttribute("gddirection", topScroll.getDirection());
				session.setAttribute("gdspeed", topScroll.getSpeed());
				session.setAttribute("gdcontent", topScroll.getContent());
			}
			//֪ͨ���棨�ļ�������5��
			ArrayList<Wjglxf> Wjglsy = new ArrayList<Wjglxf>();
			Wjglsy = wjglDao.queryWjglSy(5);
			session.setAttribute("Wjglsy", Wjglsy);
			//���ɷ��� ��5��
			ArrayList<ContentFl> FlSy = new ArrayList<ContentFl>();
			ContentFlDao contentFlDao = new ContentFlDao();
			FlSy = contentFlDao.queryFlSy(5);
			session.setAttribute("FlSy", FlSy);
			//ѧϰ԰�� ��5��
			ArrayList<LearningCorner> LearningCornerSy = new ArrayList<LearningCorner>();
			LearningCornerDao learningCornerDao = new LearningCornerDao();
			LearningCornerSy = learningCornerDao.queryLcSy(5);
			session.setAttribute("LearningCornerSy", LearningCornerSy);
			
			//��ȡ��¼IP��ַ
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
			//��¼��¼��־
			String logStr = "��¼�û���"+Zzxx.getName()+";�û�����"+username+";IP��ַ��"+ip;
			getServletContext().log(logStr);
			response.sendRedirect("main.jsp");
		} else {
			errMessage += "�û����������������������";
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
		//��ȡ��ǰ�û�
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String username = UserInfo.getUsername(); 
			//��ѯ�Ƿ���δ�ظ����ļ�
			WjglDao wjglDao = new WjglDao();
			int  MyWjglHF = wjglDao.queryWjglWHFCount(username);
			session.setAttribute("MyWjglHF", MyWjglHF);
			//��ѯ�Ƿ���δ��˵��ش���Ŀ�걨�ļ�
			ZdxmsbDao zdxmsbDao = new ZdxmsbDao();
			int  MyZdxmsbSH = zdxmsbDao.queryMyZdxmsbSHCount(username);
			session.setAttribute("MyZdxmsbSH", MyZdxmsbSH);
			//��ѯ�Ƿ���δ�ظ��Ļ���֪ͨ
			GztzDao gztzDao = new GztzDao();
			int  MyGztzHF = gztzDao.queryGztzWHFCount(username);
			session.setAttribute("MyGztzHF", MyGztzHF);
			//��ѯ�Ƿ���δ�Ķ��Ķ���֪ͨ
			MessageDao messageDao = new MessageDao();
			int  MyMessage = messageDao.MyMessageCount(username);
			session.setAttribute("MyMessage", MyMessage);
			//��ѯ�Ƿ���δ�Ķ����ʼ�
			EmailDao emailDao = new EmailDao();
			int  MyEmail = emailDao.MyEmailCount(username);
			session.setAttribute("MyEmail", MyEmail);
			//��ѯ���˲�����Ϣ
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = ContentZzxxDao.queryThisZzxxByUserName(username);
			session.setAttribute("UserInfo", Zzxx);
			//��ѯ����Ȩ��
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(username);
			session.setAttribute("UserPer", permissions);
			//��ѯ��ҳͼƬ��¼
			ArrayList<PostInformation> Informat = new ArrayList<PostInformation>();
			PostInformationDao postInformationDao = new PostInformationDao();
			Informat = postInformationDao.queryInformatFirst();
			session.setAttribute("Informat", Informat);
			//��ѯ������Ļ
			TopScroll topScroll = new TopScroll();
			topScroll = postInformationDao.queryTopScroll();
			if(topScroll.getId()!=0){
				session.setAttribute("gddirection", topScroll.getDirection());
				session.setAttribute("gdspeed", topScroll.getSpeed());
				session.setAttribute("gdcontent", topScroll.getContent());
			}
			//֪ͨ���棨�ļ�������5��
			ArrayList<Wjglxf> Wjglsy = new ArrayList<Wjglxf>();
			Wjglsy = wjglDao.queryWjglSy(5);
			session.setAttribute("Wjglsy", Wjglsy);
			//���ɷ��� ��5��
			ArrayList<ContentFl> FlSy = new ArrayList<ContentFl>();
			ContentFlDao contentFlDao = new ContentFlDao();
			FlSy = contentFlDao.queryFlSy(5);
			session.setAttribute("FlSy", FlSy);
			//ѧϰ԰�� ��5��
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
	 *  ��δ����
	 * 
	 */
	protected void NOFunction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			request.setAttribute("result", "������δ����");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
		}
	}
}
