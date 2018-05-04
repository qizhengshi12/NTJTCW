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

import com.safety.dao.LearningCornerDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.LearningCorner;

public class LearningCornerServlet  extends HttpServlet{
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
		if("getCornerList".equals(action)){//����ѧϰ԰�ء����б�
			getCornerList(request,response);
		}else if("CornerEdit".equals(action)){//����
			CornerEdit(request,response);
		}else if("CornerSave".equals(action)){//����
			CornerSave(request,response);
		}else if("CornerShow".equals(action)){//�鿴
			CornerShow(request,response);
		}else if("delete".equals(action)){//ɾ��
			delete(request,response);
		}else if("downLoad".equals(action)){//����
			downLoad(request,response);
		}else if("setJH".equals(action)){//���þ���
			setJH(request,response);
		}else if("setGood".equals(action)){//����
			setGood(request,response);
		}
	}

	/*
	 *  ����ѧϰ԰�ء����б�
	 * 
	 */
	protected void getCornerList(HttpServletRequest request,
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
			
		}else if(UserPer.getLearningcorner().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲���Ȩ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
		}else{
			//��ѯ�б�
			ArrayList<LearningCorner> LearningCornerList = new ArrayList<LearningCorner>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			LearningCornerDao learningCornerDao = new LearningCornerDao();
			String srbt = "";
			String flag = request.getParameter("flag");
			String cxbt = request.getParameter("cxbt");
			String cxlx1 = request.getParameter("cxlx1");
			String cxlx2 = request.getParameter("cxlx2");
			String cxlx3 = request.getParameter("cxlx3");
			String cxczr = request.getParameter("cxczr");
			if("1".equals(flag)){
				cxbt ="";
				cxlx1 ="";
				cxlx2 ="";
				cxlx3 ="";
				cxczr ="";
			}else{
				if(!"".equals(cxbt)){
					srbt = " where bt like '%"+cxbt+"%'";
				}
				if(!"".equals(cxlx1)){
		   			if("".equals(srbt)){
		   				srbt = " where lx1 ='"+cxlx1+"'";
		   			}else{
		   				srbt = srbt + " and lx1 ='"+cxlx1+"'";
		   			}
		   		}
		   		if(!"".equals(cxlx2)){
		   			if("".equals(srbt)){
		   				srbt = " where lx2 ='"+cxlx2+"'";
		   			}else{
		   				srbt = srbt + " and lx2 ='"+cxlx2+"'";
		   			}
		   		}
		   		if(!"".equals(cxlx3)){
		   			if("".equals(srbt)){
		   				srbt = " where lx3 ='"+cxlx3+"'";
		   			}else{
		   				srbt = srbt + " and lx3 ='"+cxlx3+"'";
		   			}
		   		}
		   		if(!"".equals(cxczr)){
		   			if("".equals(srbt)){
		   				srbt = " where tjrID ='"+cxczr+"'";
		   			}else{
		   				srbt = srbt + " and tjrID ='"+cxczr+"'";
		   			}
		   		}
			}
			countAll = learningCornerDao.queryInformatListByCount(srbt);
			LearningCornerList = learningCornerDao.queryInformatListByBt(srbt, begin, pageSize);
			request.setAttribute("CornerList", LearningCornerList);
			request.setAttribute("result", result);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			request.setAttribute("cxlx1", cxlx1);
			request.setAttribute("cxlx2", cxlx2);
			request.setAttribute("cxlx3", cxlx3);
			request.setAttribute("cxczr", cxczr);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/LearningCorner/informationList.jsp").forward(request,
					response);
		}	
		return;
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void CornerEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���������޸�
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		LearningCorner learningCorner = new LearningCorner();
		LearningCornerDao learningCornerDao = new LearningCornerDao();
		if(!"".equals(id)&&id!=null){
			learningCorner = learningCornerDao.queryInformatByID(Integer.parseInt(id));
		}
		request.setAttribute("learningCorner", learningCorner);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/LearningCorner/informationEdit.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void CornerSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���������޸�
		String Corner_id = request.getParameter("Corner_id");
		String bt = request.getParameter("bt");
		String nr = request.getParameter("nr");
		String FileUrl = request.getParameter("FileUrl");
		String PhotoURL = request.getParameter("PhotoURL");
		String TempURL = request.getParameter("TempURL");
		String lx1 = request.getParameter("lx1");
		String lx2 = request.getParameter("lx2");
		String result = request.getParameter("result");
		LearningCorner learningCorner = new LearningCorner();
		learningCorner.setBt(bt);
		learningCorner.setNr(nr);
		learningCorner.setFileURL(FileUrl);
		learningCorner.setLx1(lx1);
		learningCorner.setLx2(lx2);
		learningCorner.setLx3("1");//1����ͨ�� 2��������
		learningCorner.setGood(0);
		learningCorner.setPeople("");
		learningCorner.setPeopleID("");
		String FullFilePath = request.getRealPath("/UserFile");
		if(!"".equals(TempURL)&&TempURL!=null){
			if(!"".equals(PhotoURL)&&PhotoURL!=null){
				delFile(FullFilePath+"/"+PhotoURL);
			}
			PhotoURL = TempURL.replaceAll("temp", "photo");
			copyFile(FullFilePath+"/"+TempURL, FullFilePath+"/"+PhotoURL);   
	        delFile(FullFilePath+"/"+TempURL);   
		}
		learningCorner.setPhotoURL(PhotoURL);
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		learningCorner.setTjr(UserInfo.getName());
		learningCorner.setTjrID(UserInfo.getUsername());
		learningCorner.setDw(UserInfo.getCompany());
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Date  data1=new java.sql.Date(date.getTime());
		learningCorner.setTjsj(data1);
		
		LearningCornerDao learningCornerDao = new LearningCornerDao();
		if(!"".equals(Corner_id)&&Corner_id!=null){
			learningCorner.setId(Integer.parseInt(Corner_id));
			learningCornerDao.updateInformat(learningCorner);
		}else{
			Corner_id = learningCornerDao.insertInformat(learningCorner)+"";
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("LearningCornerServlet?action=getCornerList&flag=1").forward(request,
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
//        	 File file = new File(filePathAndName);  
//        	 // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
//        	 if (file.isFile() && file.exists()) {  
//        		 file.delete();  
//        	 }
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
	protected void CornerShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		LearningCorner learningCorner = new LearningCorner();
		LearningCornerDao learningCornerDao = new LearningCornerDao();
		if(!"".equals(id)&&id!=null){
			learningCorner = learningCornerDao.queryInformatByID(Integer.parseInt(id));
		}
		request.setAttribute("learningCorner", learningCorner);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/LearningCorner/informationShow.jsp").forward(request,
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
		LearningCornerDao LearningCornerDao = new LearningCornerDao();
		LearningCornerDao.DeleteById(Integer.parseInt(id));
		//ɾ�������ļ�
		String FileUrl =request.getParameter("path1");
//		delFile(FullFilePath+"/"+path1);
		String[] FileUrlStr = FileUrl.split(";");
		//ɾ�������ļ�
		for(int j=0;j<FileUrlStr.length; j++){
			String FullPath = request.getRealPath("/UserFile") + "/LearningCorner/file/"+FileUrlStr[j];
			File file = new File(FullPath);  
			// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
		}
		//ɾ������ͼƬ
		String path2 =request.getParameter("path2");
		String FullFilePath = request.getRealPath("/UserFile");
		delFile(FullFilePath+"/"+path2);
		RequestDispatcher rd = request.getRequestDispatcher("LearningCornerServlet?action=getCornerList&flag=1");
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
		String FullFilePath = request.getRealPath("/UserFile") + "/LearningCorner/file/"+URL;
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
	 *  �Ӿ���
	 * 
	 */
	protected void setJH(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");
			String bz = request.getParameter("bz");
			LearningCornerDao learningCornerDao = new LearningCornerDao();
			if(!"".equals(id)&&id!=null){
				learningCornerDao.updateLx3(Integer.parseInt(id),bz);
			}
			RequestDispatcher rd = request.getRequestDispatcher("LearningCornerServlet?action=getCornerList&flag=1");
			rd.forward(request, response);
		}
	}

	/*
	 *  ����
	 * 
	 */
	protected void setGood(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");
			String pp = request.getParameter("pp");
			String ppid = request.getParameter("ppid");
			String num = request.getParameter("num");
			if("".equals(pp)){
				pp = UserInfo.getName();
				ppid = UserInfo.getUsername();
			}else{
				pp = UserInfo.getName()+";"+pp;
				ppid = UserInfo.getUsername()+";"+ppid;
			}
			LearningCorner learningCorner = new LearningCorner();
			LearningCornerDao learningCornerDao = new LearningCornerDao();
			if(!"".equals(id)&&id!=null){
				learningCornerDao.updateGood(Integer.parseInt(id),pp,ppid,Integer.parseInt(num));
				learningCorner = learningCornerDao.queryInformatByID(Integer.parseInt(id));
			}
			request.setAttribute("learningCorner", learningCorner);
			request.getRequestDispatcher("/jsp/LearningCorner/informationShow.jsp").forward(request,
					response);
		}
	}
}
