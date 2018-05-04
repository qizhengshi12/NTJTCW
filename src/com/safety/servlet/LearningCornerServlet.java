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
		if("getCornerList".equals(action)){//进入学习园地——列表
			getCornerList(request,response);
		}else if("CornerEdit".equals(action)){//新增
			CornerEdit(request,response);
		}else if("CornerSave".equals(action)){//保存
			CornerSave(request,response);
		}else if("CornerShow".equals(action)){//查看
			CornerShow(request,response);
		}else if("delete".equals(action)){//删除
			delete(request,response);
		}else if("downLoad".equals(action)){//下载
			downLoad(request,response);
		}else if("setJH".equals(action)){//设置精华
			setJH(request,response);
		}else if("setGood".equals(action)){//点赞
			setGood(request,response);
		}
	}

	/*
	 *  进入学习园地——列表
	 * 
	 */
	protected void getCornerList(HttpServletRequest request,
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
			
		}else if(UserPer.getLearningcorner().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无操作权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
		}else{
			//查询列表
			ArrayList<LearningCorner> LearningCornerList = new ArrayList<LearningCorner>();
			//分页;
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
	 *  进入新增或修改页面
	 * 
	 */
	protected void CornerEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//新增或者修改
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
	 *  进入保存页面
	 * 
	 */
	protected void CornerSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//新增或者修改
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
		learningCorner.setLx3("1");//1：普通帖 2：精华帖
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
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		learningCorner.setTjr(UserInfo.getName());
		learningCorner.setTjrID(UserInfo.getUsername());
		learningCorner.setDw(UserInfo.getCompany());
		//获取当前时间
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
            if (oldfile.exists()) { // 文件存在时   
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件   
                FileOutputStream fs = new FileOutputStream(newPath);   
                byte[] buffer = new byte[1024];   
                while ((byteread = inStream.read(buffer)) != -1) {   
                    bytesum += byteread; // 字节数 文件大小   
                    fs.write(buffer, 0, byteread);   
                }   
                inStream.close();   
                fs.close();
            }   
        } catch (Exception e) {   
            System.out.println("复制单个文件操作出错 ");   
            e.printStackTrace();   
  
        }   
  
    }  
	 public void delFile(String filePathAndName) {   
         try {   
//        	 File file = new File(filePathAndName);  
//        	 // 路径为文件且不为空则进行删除  
//        	 if (file.isFile() && file.exists()) {  
//        		 file.delete();  
//        	 }
             String filePath = filePathAndName;   
             filePath = filePath.toString();   
             java.io.File myDelFile = new java.io.File(filePath);   
             myDelFile.delete();   
   
         } catch (Exception e) {   
             System.out.println("删除文件操作出错 ");   
             e.printStackTrace();   
   
         }   
   
     }   
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void CornerShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
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
	 *  删除
	 * 
	 */
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		LearningCornerDao LearningCornerDao = new LearningCornerDao();
		LearningCornerDao.DeleteById(Integer.parseInt(id));
		//删除下载文件
		String FileUrl =request.getParameter("path1");
//		delFile(FullFilePath+"/"+path1);
		String[] FileUrlStr = FileUrl.split(";");
		//删除下载文件
		for(int j=0;j<FileUrlStr.length; j++){
			String FullPath = request.getRealPath("/UserFile") + "/LearningCorner/file/"+FileUrlStr[j];
			File file = new File(FullPath);  
			// 路径为文件且不为空则进行删除  
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
		}
		//删除下载图片
		String path2 =request.getParameter("path2");
		String FullFilePath = request.getRealPath("/UserFile");
		delFile(FullFilePath+"/"+path2);
		RequestDispatcher rd = request.getRequestDispatcher("LearningCornerServlet?action=getCornerList&flag=1");
		rd.forward(request, response);
		return;
	}
	/*
	 * 下载文件
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
	 *  加精华
	 * 
	 */
	protected void setJH(HttpServletRequest request,
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
	 *  点赞
	 * 
	 */
	protected void setGood(HttpServletRequest request,
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
