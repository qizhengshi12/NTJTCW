package com.safety.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.PostInformationDao;
import com.safety.entity.Checkid;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.PostInformation;
import com.safety.entity.TopScroll;

public class PostInformationServlet  extends HttpServlet{
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
		if("getInformationList".equals(action)){//�����ѷ�������Ϣ�����б�
			getInformationList(request,response);
		}else if("informatEdit".equals(action)){//
			informatEdit(request,response);
		}else if("postInfSH".equals(action)){//
			postInfSH(request,response);
		}else if("informatSave".equals(action)){//
			informatSave(request,response);
		}else if("informatShow".equals(action)){//
			informatShow(request,response);
		}else if("delete".equals(action)){//
			delete(request,response);
		}else if("downLoad".equals(action)){//
			downLoad(request,response);
		}else if("getTopScroll".equals(action)){//
			getTopScroll(request,response);
		}else if("saveTopScroll".equals(action)){//
			saveTopScroll(request,response);
		}
	}

	/*
	 *  �����ѷ�������Ϣ�����б�
	 * 
	 */
	protected void getInformationList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getPostinformation().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ѯ�б�
			ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			PostInformationDao postInformationDao = new PostInformationDao();
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
			if("".equals(srbt)||srbt==null){
				cxbt ="";
			}
			countAll = postInformationDao.queryInformatListByCount(srbt);
			InformatList = postInformationDao.queryInformatListByBt(srbt, begin, pageSize);
			request.setAttribute("InformatList", InformatList);
			request.setAttribute("result", result);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/PostInformation/informationList.jsp").forward(request,
					response);
		}	
		return;
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void informatEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���������޸�
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		PostInformation postInformation = new PostInformation();
		PostInformationDao postInformationDao = new PostInformationDao();
		//��ѯ������
		Checkid checkid = new Checkid();
		checkid = postInformationDao.queryPostInfSHR();
		if(checkid.getId()==0){
			result = "����ϵ����Ա��Ԥ������������";
		}else{
			if(!"".equals(id)&&id!=null){
				postInformation = postInformationDao.queryInformatByID(Integer.parseInt(id));
			}
			postInformation.setShr(checkid.getPostInfName());
			postInformation.setShrID(checkid.getPostInfID());
		}
		request.setAttribute("postInformation", postInformation);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/PostInformation/informationEdit.jsp").forward(request,
				response);
		return;
	}
	

	/*
	 *  ��ҳ���ڡ�������
	 * 
	 */
	protected void postInfSH(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//����
			String nrid = request.getParameter("nrid");
			String sfsh = request.getParameter("sfsh");
						
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Date  data1=new java.sql.Date(date.getTime());
			String result =  "����ʧ��";
			PostInformationDao postInformationDao = new PostInformationDao();
			if(!"".equals(nrid)&&nrid!=null&&!"0".equals(nrid)){
				postInformationDao.PostInfSH(Integer.parseInt(nrid),sfsh);
				result= "�����ɹ�";
//				int  MyZdxmsbSH = zdxmsbDao.queryMyZdxmsbSHCount(UserInfo.getUsername());
//				session.setAttribute("MyZdxmsbSH", MyZdxmsbSH);
				request.getRequestDispatcher("PostInformationServlet?action=getInformationList&result="+result).forward(request,
						response);
			}
			
		}
	}
	
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void informatSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���������޸�
		String Infor_id = request.getParameter("Infor_id");
		String bt = request.getParameter("bt");
		String nr = request.getParameter("nr");
		String FileUrl = request.getParameter("FileUrl");
		String PhotoURL = request.getParameter("PhotoURL");
		String TempURL = request.getParameter("TempURL");
		String result = request.getParameter("result");
		PostInformation postInformation = new PostInformation();
		postInformation.setBt(bt);
		postInformation.setNr(nr);
		postInformation.setFileURL(FileUrl);
		String shr = request.getParameter("shr");
		String shrID = request.getParameter("shrID");
		postInformation.setShr(shr);//�����
		postInformation.setShrID(shrID);//�����ID
		postInformation.setSfsh("1");//1��δ��ˣ�2������ˣ�3������
		String FullFilePath = request.getRealPath("/UserFile");
		if(!"".equals(TempURL)&&TempURL!=null){
			if(!"".equals(PhotoURL)&&PhotoURL!=null){
				delFile(FullFilePath+"/"+PhotoURL);
			}
			PhotoURL = TempURL.replaceAll("temp", "photo");
			copyFile(FullFilePath+"/"+TempURL, FullFilePath+"/"+PhotoURL);   
	        delFile(FullFilePath+"/"+TempURL);   
		}
		postInformation.setPhotoURL(PhotoURL);
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		postInformation.setTjr(UserInfo.getName());
		postInformation.setTjrID(UserInfo.getUsername());
		postInformation.setDw(UserInfo.getCompany());
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Date  data1=new java.sql.Date(date.getTime());
		postInformation.setTjsj(data1);
		
		PostInformationDao postInformationDao = new PostInformationDao();
		if(!"".equals(Infor_id)&&Infor_id!=null){
			postInformation.setId(Integer.parseInt(Infor_id));
			postInformationDao.updateInformat(postInformation);
		}else{
			Infor_id = postInformationDao.insertInformat(postInformation)+"";
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("PostInformationServlet?action=getInformationList").forward(request,
				response);
		
		return;
	}

	public void copyFile(String oldPath, String newPath) {   
        try {   
            int bytesum = 0;   
            int byteread = 0;   
            File oldfile = new File(oldPath);   
            if (oldfile.exists()) { // �ļ�����ʱ   
                InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�   
                FileOutputStream fs = new FileOutputStream(newPath);   
                byte[] buffer = new byte[1024];   
                while ((byteread = inStream.read(buffer)) != -1) {   
                    bytesum += byteread; // �ֽ��� �ļ���С   
                    fs.write(buffer, 0, byteread);   
                }   
                inStream.close();   
                fs.close();
            }   
        } catch (Exception e) {   
            System.out.println("���Ƶ����ļ��������� ");   
            e.printStackTrace();   
  
        }   
  
    }  
	 public void delFile(String filePathAndName) {   
         try {   
             String filePath = filePathAndName;   
             filePath = filePath.toString();   
             java.io.File myDelFile = new java.io.File(filePath);   
             myDelFile.delete();   
   
         } catch (Exception e) {   
             System.out.println("ɾ���ļ��������� ");   
             e.printStackTrace();   
   
         }   
   
     }   
	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void informatShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		String flag = request.getParameter("flag");//1���鿴��2������
		PostInformation postInformation = new PostInformation();
		PostInformationDao postInformationDao = new PostInformationDao();
		if(!"".equals(id)&&id!=null){
			postInformation = postInformationDao.queryInformatByID(Integer.parseInt(id));
		}
		request.setAttribute("postInformation", postInformation);
		request.setAttribute("flag", flag);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/PostInformation/informationShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		PostInformationDao postInformationDao = new PostInformationDao();
		postInformationDao.DeleteById(Integer.parseInt(id));
		//ɾ�������ļ�
		String FileUrl =request.getParameter("path1");
		String[] FileUrlStr = FileUrl.split(";");
		//ɾ�������ļ�
		for(int j=0;j<FileUrlStr.length; j++){
			String FullPath = request.getRealPath("/UserFile") + "/PostInformation/file/"+FileUrlStr[j];
			File file = new File(FullPath);  
			// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
		}
		//ɾ������ͼƬ
		String FullFilePath = request.getRealPath("/UserFile");
		String path2 =request.getParameter("path2");
		delFile(FullFilePath+"/"+path2);
		RequestDispatcher rd = request.getRequestDispatcher("PostInformationServlet?action=getInformationList");
		rd.forward(request, response);
		return;
	}
	/*
	 * �����ļ�
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
//		String id = request.getParameter("id");
		String FullFilePath = request.getRealPath("/UserFile") + "/PostInformation/file/"+URL;
		File file = new File(FullFilePath);  
		if (!"".equals(URL)&&URL!=null&&file.exists()) {
            String filename = URLEncoder.encode(file.getName(),"utf-8");
            response.reset();
            response.setContentType("application/x-msdownload");
            //�Ը�������ʽ��ʾ�û����أ� ����������������Ǹ�servlet ʱ�������Ի�����//ʾ�����ػ��Ǳ��档  
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
           /*����ļ����ȴ���0*/
            if (fileLength != 0) {
                /*����������*/
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                /*���������*/
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
    		out.println("<script>alert('�ļ���ʧЧ���޷����أ�');</script>");
        }
	}
	/*
	 *  ���������Ļ����
	 * 
	 */
	protected void getTopScroll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getTopscroll().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲���Ȩ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			// List
			PostInformationDao postInformationDao = new PostInformationDao();
			TopScroll topScroll = new TopScroll();
			topScroll = postInformationDao.queryTopScroll();
			request.setAttribute("topScroll", topScroll);
			request.getRequestDispatcher("/jsp/PostInformation/topscroll.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �������޸���Ļ
	 * 
	 */
	protected void saveTopScroll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���������޸�
		String id = request.getParameter("id");
		String direction = request.getParameter("direction");
		String speed = request.getParameter("speed");
		String content = request.getParameter("content");
		TopScroll topScroll = new TopScroll();
		topScroll.setContent(content);
		topScroll.setSpeed(speed);
		topScroll.setDirection(direction);
		
		PostInformationDao postInformationDao = new PostInformationDao();
		if(!"0".equals(id)){//�޸�
			topScroll.setId(Integer.parseInt(id));
			postInformationDao.updateTopScroll(topScroll);
		}else{//����
			postInformationDao.insertTopScroll(topScroll);
		}
		HttpSession session = request.getSession(); 
		//����session
		session.setAttribute("gddirection", direction);
		session.setAttribute("gdspeed", speed);
		session.setAttribute("gdcontent", content);
		request.setAttribute("result", "����ɹ�");
		request.getRequestDispatcher("PostInformationServlet?action=getTopScroll").forward(request,
				response);
		
		return;
	}
}
