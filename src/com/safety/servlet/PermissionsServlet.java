package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.PermissionsDao;
import com.safety.entity.Checkid;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Permissions;

public class PermissionsServlet  extends HttpServlet{
	/**
	 * ����Servlet����
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
		if("getPermissions".equals(action)){//ϵͳȨ�޽���
			getPermissions(request,response);
		}else if("PermissionsEdit".equals(action)){//�������޸�ϵͳȨ��
			PermissionsEdit(request,response);
		}else if("PermissionsSave".equals(action)){//����ϵͳȨ��
			PermissionsSave(request,response);
		}else if("getCheckID".equals(action)){//���Ȩ�޽���
			getCheckID(request,response);
		}else if("CheckIDSave".equals(action)){//�������Ȩ��
			CheckIDSave(request,response);
		}
	}

	/*
	 *  Ȩ���б�
	 * 
	 */
	protected void getPermissions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			if("����Ա".equals(UserInfo.getRoles())){
				request.getRequestDispatcher("/jsp/Permissions/getPermissions.jsp").forward(request,
						response);
			}else{
				request.setAttribute("result", "�޲���Ȩ��");
				request.getRequestDispatcher("desk.jsp").forward(request,
						response);
			}
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void PermissionsEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String username = request.getParameter("username");
			String result = request.getParameter("result");
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			if(!"".equals(username)&&username!=null){
				permissions = permissionsDao.queryPermissionsByUsername(username);
			}
			permissions.setUsername(username);
			request.setAttribute("permissions", permissions);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/Permissions/permissionsEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void PermissionsSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String username = request.getParameter("username");
			
			Permissions permissions = new Permissions();
			permissions.setUsername(username);
			permissions.setCzr(UserInfo.getName());
			permissions.setCzrID(UserInfo.getUsername());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			permissions.setCzsj(data1);
			//Ȩ��
			//��֯��ϵ����
			String contentzzxx ="";
			String[] contentzzxxMany = request.getParameterValues("contentzzxx");
			if(contentzzxxMany!=null){
				for (int i=0; i<contentzzxxMany.length; i++){
					contentzzxx += contentzzxxMany[i] + ",";
				}
			}
			permissions.setContentzzxx(contentzzxx);
			//��֯��ϵ�˵�
			String nodezzxx ="";
			String[] nodezzxxMany = request.getParameterValues("nodezzxx");  
			if(nodezzxxMany!=null){ 
				for (int i=0; i<nodezzxxMany.length; i++){
					nodezzxx += nodezzxxMany[i] + ",";
				}
			}
			permissions.setNodezzxx(nodezzxx);
			//�ƾ���������
			String contentcjfg ="";
			String[] contentcjfgMany = request.getParameterValues("contentcjfg");  
			if(contentcjfgMany!=null){   
				for (int i=0; i<contentcjfgMany.length; i++){
					contentcjfg += contentcjfgMany[i] + ",";
				}
			}
			permissions.setContentcjfg(contentcjfg);
			//�ƾ�����˵�
			String nodecjfg ="";
			String[] nodecjfgMany = request.getParameterValues("nodecjfg"); 
			if(nodecjfgMany!=null){
				for (int i=0; i<nodecjfgMany.length; i++){
					nodecjfg += nodecjfgMany[i] + ",";
				}
			}
			permissions.setNodecjfg(nodecjfg);
			//�����ƶ�
			String contentglzd ="";
			String[] contentglzdMany = request.getParameterValues("contentglzd");
			if(contentglzdMany!=null){ 
				for (int i=0; i<contentglzdMany.length; i++){
					contentglzd += contentglzdMany[i] + ",";
				}
			}
			permissions.setContentglzd(contentglzd);
			//�����ƶȲ˵�
			String nodeglzd ="";
			String[] nodeglzdMany = request.getParameterValues("nodeglzd");
			if(nodeglzdMany!=null){ 
				for (int i=0; i<nodeglzdMany.length; i++){
					nodeglzd += nodeglzdMany[i] + ",";
				}
			}
			permissions.setNodeglzd(nodeglzd);
			//�ļ�����
			String wjgl ="";
			String[] wjglMany = request.getParameterValues("wjgl"); 
			if(wjglMany!=null){   
				for (int i=0; i<wjglMany.length; i++){
					wjgl += wjglMany[i] + ",";
				}
			}
			permissions.setWjgl(wjgl);
			//����֪ͨ
			String gztz ="";
			String[] gztzMany = request.getParameterValues("gztz"); 
			if(gztzMany!=null){    
				for (int i=0; i<gztzMany.length; i++){
					gztz += gztzMany[i] + ",";
				}
			}
			permissions.setGztz(gztz);
			//�ƻ��ܽ�
			String jhzj ="";
			String[] jhzjMany = request.getParameterValues("jhzj");
			if(jhzjMany!=null){    
				for (int i=0; i<jhzjMany.length; i++){
					jhzj += jhzjMany[i] + ",";
				}
			}
			permissions.setJhzj(jhzj);
			//������̬
			String gzdt ="";
			String[] gzdtMany = request.getParameterValues("gzdt"); 
			if(gzdtMany!=null){    
				for (int i=0; i<gzdtMany.length; i++){
					gzdt += gzdtMany[i] + ",";
				}
			}
			permissions.setGzdt(gzdt);
			//���񱨱�
			String cwbb ="";
			String[] cwbbMany = request.getParameterValues("cwbb"); 
			if(cwbbMany!=null){     
				for (int i=0; i<cwbbMany.length; i++){
					cwbb += cwbbMany[i] + ",";
				}
			}
			permissions.setCwbb(cwbb);
			//��Ʊ���
			String sjbb ="";
			String[] sjbbMany = request.getParameterValues("sjbb"); 
			if(sjbbMany!=null){     
				for (int i=0; i<sjbbMany.length; i++){
					sjbb += sjbbMany[i] + ",";
				}
			}
			permissions.setSjbb(sjbb);
			//ͳ�Ʊ���
			String tjbb ="";
			String[] tjbbMany = request.getParameterValues("tjbb"); 
			if(tjbbMany!=null){     
				for (int i=0; i<tjbbMany.length; i++){
					tjbb += tjbbMany[i] + ",";
				}
			}
			permissions.setTjbb(tjbb);
			//�ش���Ŀ�걨
			String zdxmsb ="";
			String[] zdxmsbMany = request.getParameterValues("zdxmsb"); 
			if(zdxmsbMany!=null){     
				for (int i=0; i<zdxmsbMany.length; i++){
					zdxmsb += zdxmsbMany[i] + ",";
				}
			}
			permissions.setZdxmsb(zdxmsb);
			//��������ʹ��
			String sgjfsy ="";
			String[] sgjfsyMany = request.getParameterValues("sgjfsy"); 
			if(sgjfsyMany!=null){     
				for (int i=0; i<sgjfsyMany.length; i++){
					sgjfsy += sgjfsyMany[i] + ",";
				}
			}
			permissions.setSgjfsy(sgjfsy);
			//���ʸ���
			String gzfl ="";
			String[] gzflMany = request.getParameterValues("gzfl"); 
			if(gzflMany!=null){     
				for (int i=0; i<gzflMany.length; i++){
					gzfl += gzflMany[i] + ",";
				}
			}
			permissions.setGzfl(gzfl);
			//����ල
			String cwjd ="";
			String[] cwjdMany = request.getParameterValues("cwjd"); 
			if(cwjdMany!=null){     
				for (int i=0; i<cwjdMany.length; i++){
					cwjd += cwjdMany[i] + ",";
				}
			}
			permissions.setCwjd(cwjd);
			//�������з���
			String jjyxfx ="";
			String[] jjyxfxMany = request.getParameterValues("jjyxfx"); 
			if(jjyxfxMany!=null){     
				for (int i=0; i<jjyxfxMany.length; i++){
					jjyxfx += jjyxfxMany[i] + ",";
				}
			}
			permissions.setJjyxfx(jjyxfx);
			//ѧϰ԰��
			String learningcorner ="";
			String[] learningcornerMany = request.getParameterValues("learningcorner"); 
			if(learningcornerMany!=null){       
				for (int i=0; i<learningcornerMany.length; i++){
					learningcorner += learningcornerMany[i] + ",";
				}
			}
			permissions.setLearningcorner(learningcorner);
			//��ҳ����
			String postinformation ="";
			String[] postinformationMany = request.getParameterValues("postinformation"); 
			if(postinformationMany!=null){       
				for (int i=0; i<postinformationMany.length; i++){
					postinformation += postinformationMany[i] + ",";
				}
			}
			permissions.setPostinformation(postinformation);
			//������Ļ
			String topscroll ="";
			String[] topscrollMany = request.getParameterValues("topscroll");
			if(topscrollMany!=null){        
				for (int i=0; i<topscrollMany.length; i++){
					topscroll += topscrollMany[i] + ",";
				}
			}
			permissions.setTopscroll(topscroll);

			//�������
			String njgl ="";
			String[] njglMany = request.getParameterValues("njgl");
			if(njglMany!=null){        
				for (int i=0; i<njglMany.length; i++){
					njgl += njglMany[i] + ",";
				}
			}
			permissions.setNjgl(njgl);
			
			//�������
			String njfb ="";
			String[] njfbMany = request.getParameterValues("njfb");
			if(njfbMany!=null){        
				for (int i=0; i<njfbMany.length; i++){
					njfb += njfbMany[i] + ",";
				}
			}
			permissions.setNjfb(njfb);
			
			//������
			String bbdz ="";
			String[] bbdzMany = request.getParameterValues("bbdz");
			if(bbdzMany!=null){        
				for (int i=0; i<bbdzMany.length; i++){
					bbdz += bbdzMany[i] + ",";
				}
			}
			permissions.setNjfb(bbdz);
			
			//�Ȳ�ѯ�Ƿ���Ҫ������
			boolean res =false;
			PermissionsDao permissionsDao = new PermissionsDao();
			res = permissionsDao.judgePermissionsByUsername(username);
			result = "����ʧ��";
			if(res){
				if(permissionsDao.updatePermissions(permissions)){
					result = "����ɹ�";
				}
			}else{
				if(permissionsDao.insertPermissions(permissions)){
					result = "����ɹ�";
				}
			}
			request.setAttribute("permissions", permissions);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/Permissions/permissionsEdit.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  ���Ȩ�޽���
	 * 
	 */
	protected void getCheckID(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			if("����Ա".equals(UserInfo.getRoles())){
				//��ѯ��Ա����
				Checkid checkid = new Checkid();
				PermissionsDao permissionsDao = new PermissionsDao();
				checkid = permissionsDao.queryCheckid();
				if(checkid.getId()==0){
					checkid.setZdxmsbID("");
					checkid.setZdxmsbName("");
					checkid.setPostInfID("");
					checkid.setPostInfName("");
				}
				request.setAttribute("checkid", checkid);
				request.getRequestDispatcher("/jsp/Permissions/getCheckID.jsp").forward(request,
						response);
			}else{
				request.setAttribute("result", "�޲���Ȩ��");
				request.getRequestDispatcher("desk.jsp").forward(request,
						response);
			}
		}
	}
	/*
	 *  �������Ȩ��
	 * 
	 */
	protected void CheckIDSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			Checkid checkid = new Checkid();
			checkid.setCzr(UserInfo.getName());
			checkid.setCzrID(UserInfo.getUsername());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			checkid.setCzsj(data1);
			String ID = request.getParameter("ID");
			if(!"0".equals(ID)){
				checkid.setId(Integer.parseInt(ID));
			}
			//Ȩ��
			//�ش���Ŀ�걨
			String zdxmsbID = request.getParameter("zdxmsbID");
			String zdxmsbName = request.getParameter("zdxmsbName");
			String postInfID = request.getParameter("postInfID");
			String postInfName = request.getParameter("postInfName");
			checkid.setZdxmsbID(zdxmsbID);
			checkid.setZdxmsbName(zdxmsbName);
			checkid.setPostInfID(postInfID);
			checkid.setPostInfName(postInfName);

			
			//�Ȳ�ѯ�Ƿ���Ҫ������
			boolean res =false;
			PermissionsDao permissionsDao = new PermissionsDao();
			res = permissionsDao.judgeCheckid();
			result = "����ʧ��";
			if(res){
				if(permissionsDao.updateCheckid(checkid)){
					result = "����ɹ�";
				}
			}else{
				if(permissionsDao.insertCheckid(checkid)){
					result = "����ɹ�";
				}
			}
			checkid = permissionsDao.queryCheckid();
			request.setAttribute("checkid", checkid);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/Permissions/getCheckID.jsp").forward(request,
					response);
		}
	}
}
