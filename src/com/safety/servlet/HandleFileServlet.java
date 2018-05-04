package com.safety.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;


public class HandleFileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
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
		if("HandleFile".equals(action)){
			HandleFile(request,response);
		}else if("DeleteFile".equals(action)){
			DeleteFile(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("HandleFileSEC".equals(action)){//上传一份，并复制一份
//			HandleFileSEC(request,response);
		}else if("HandlePhoto".equals(action)){
			HandlePhoto(request,response);
		}else if("MultiFiles".equals(action)){
			MultiFiles(request,response);
		}
	}

	protected void HandleFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取路径
		String path = request.getParameter("path");
		// 用于返回结果
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 1. 创建工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2. 创建FileUpload对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. 判断是否是上传表单
		boolean b = upload.isMultipartContent(request);
		if (!b) {
			// 不是文件上传
			request.setAttribute("message", "对不起，不是文件上传表单！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}
		// 是文件上传表单
		// 4. 解析request，获得FileItem项
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5. 遍历集合
		for (FileItem item : fileitems) {
			// 判断是不是普通字段
			if (!item.isFormField()) {
				// String name = item.getFieldName();
				// String value = item.getString();
				// // 手工的转换了
				// value = new String(value.getBytes("iso-8859-1"),"utf-8");
				// System.out.println(name + "=" + value);
				// } else {
				// 文件上传字段
				 // 获取上传文件的名字                   
                String value = item.getName(); // 1,获取路径                    
                int start = value.lastIndexOf( "\\" );// 2,索引到最后一个反斜杠
                String filename = value.substring( start+1 );//3, 截取(+1是去掉反斜杠) 
                // 生成文件名
                start = filename.lastIndexOf( "." );    // 索引到最后一个点
                filename = filename.substring( 0, start )    // 不含扩展名的文件
                			+"-NTJTCW-"
                            + UUID.randomUUID().toString()  // 随机数
                            + filename.substring( start );  // 扩展名
//                    file = new File(FullPath, filename);  
//				String filename = item.getName();
//				String fileLX = "";
				// 获取当前时间
//				java.util.Date date = new java.util.Date();
//				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//				format.setLenient(false);
//				String strResponseDate = format.format(date);
//				java.util.Random r=new java.util.Random(); 
//				String newname = strResponseDate+r.nextInt(10);
				// 获得文件类型
//				if (filename.lastIndexOf(".") != -1) {
//					fileLX = filename.substring(filename.lastIndexOf(".") + 1);
//					newname = newname + "." + fileLX;
//				}
				// 创建文件
				ServletContext context = getServletContext();
				String dir = context.getRealPath("UserFile") + "/" + path;
				File file = new File(dir, filename);
				if (file.exists()) {
					out.print("<script>");
					out.print("window.parent.callback('1')");
					out.print("</script>");
					out.close();
				} else {
					file.createNewFile();
					// 获得流，读取数据写入文件
					InputStream in = item.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					int len;
					byte[] buffer = new byte[1024];
					while ((len = in.read(buffer)) > 0)
						fos.write(buffer, 0, len);
					fos.close();
					in.close();
					// 上传成功，返回1
					out.print("<script>");
					out.print("window.parent.callback('" + path + "/" + filename
							+ "')");
//					out.print("window.parent.callback('"+ filename
//							+ "')");
					out.print("</script>");
					out.close();
				}
				item.delete(); // 删除临时文件
			}
		}
	}

//	protected void HandleFileSEC(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		// 获取路径
//		String path = request.getParameter("path");
//		String pathSEC = request.getParameter("pathSEC");
//		// 用于返回结果
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		// 1. 创建工厂类
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		// 2. 创建FileUpload对象
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		// 3. 判断是否是上传表单
//		boolean b = upload.isMultipartContent(request);
//		if (!b) {
//			// 不是文件上传
//			request.setAttribute("message", "对不起，不是文件上传表单！");
//			request.getRequestDispatcher("/message.jsp").forward(request,
//					response);
//			return;
//		}
//		// 是文件上传表单
//		// 4. 解析request，获得FileItem项
//		List<FileItem> fileitems = null;
//		try {
//			fileitems = upload.parseRequest(request);
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// 5. 遍历集合
//		for (FileItem item : fileitems) {
//			// 判断是不是普通字段
//			if (!item.isFormField()) {
//				// String name = item.getFieldName();
//				// String value = item.getString();
//				// // 手工的转换了
//				// value = new String(value.getBytes("iso-8859-1"),"utf-8");
//				// System.out.println(name + "=" + value);
//				// } else {
//				// 文件上传字段
//				String filename = item.getName();
//				String fileLX = "";
//				// 获取当前时间
//				java.util.Date date = new java.util.Date();
//				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//				format.setLenient(false);
//				String strResponseDate = format.format(date);
//				java.util.Random r=new java.util.Random(); 
//				String newname = strResponseDate+r.nextInt(10);
//				// 获得文件类型
//				if (filename.lastIndexOf(".") != -1) {
//					fileLX = filename.substring(filename.lastIndexOf(".") + 1);
//					newname = newname + "." + fileLX;
//				}
//				// 创建文件
//				ServletContext context = getServletContext();
//				String dir = context.getRealPath("UserFile") + "/" + path;
//				String dirSEC = context.getRealPath("UserFile") + "/" + pathSEC;
//				File file = new File(dir, newname);
//				File fileSEC = new File(dirSEC, newname);
//				if (file.exists()) {
//					out.print("<script>");
//					out.print("window.parent.callback('1','1')");
//					out.print("</script>");
//					out.close();
//				} else {
//					//创建第一个文件
//					file.createNewFile();
//					//创建第二个文件
//					fileSEC.createNewFile();
//					// 获得流，读取数据写入文件
//					InputStream in = item.getInputStream();
//					FileOutputStream fos = new FileOutputStream(file);
//					FileOutputStream fosSEC = new FileOutputStream(fileSEC);
//					int len;
//					byte[] buffer = new byte[1024];
//					while ((len = in.read(buffer)) > 0){
//						fos.write(buffer, 0, len);
//						fosSEC.write(buffer, 0, len);
//					}
//					fos.close();
//					fosSEC.close();
//					in.close();
//					// 上传成功，返回1
//					out.print("<script>");
//					out.print("window.parent.callback('" + path + "/" + newname
//							+ "','" + pathSEC + "/" + newname
//							+ "')");
//					out.print("</script>");
//					out.close();
//				}
//				item.delete(); // 删除临时文件
//			}
//		}
//	}
	/** 
	 * 删除单个文件 
	 * @param   dPath-被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	*/  

	protected void DeleteFile(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String path =request.getParameter("path");
		String FullFilePath = request.getRealPath("/UserFile") + "/"+path;
		File file = new File(FullFilePath);  
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
			file.delete();  
		}
		response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
//		out.print("<script>");
//    	out.print("window.parent.callbackdel()");
//    	out.print("</script>");
    	out.close();

	}
	/*
	 * 下载文件——操作说明
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String FullFilePath = request.getRealPath("/UserFile") + "/"+"操作手册V1.doc";
		File file = new File(FullFilePath);  
		if (file.exists()) {
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
        	RequestDispatcher rd = request.getRequestDispatcher("about.jsp");
    		rd.forward(request, response);
    		return ;
        }
	}
	protected void HandlePhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取路径
		String path = request.getParameter("path");
		// 用于返回结果
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 1. 创建工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2. 创建FileUpload对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. 判断是否是上传表单
		boolean b = upload.isMultipartContent(request);
		if (!b) {
			// 不是文件上传
			request.setAttribute("message", "对不起，不是文件上传表单！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}
		// 是文件上传表单
		// 4. 解析request，获得FileItem项
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5. 遍历集合
		for (FileItem item : fileitems) {
			// 判断是不是普通字段
			if (!item.isFormField()) {
				// String name = item.getFieldName();
				// String value = item.getString();
				// // 手工的转换了
				// value = new String(value.getBytes("iso-8859-1"),"utf-8");
				// System.out.println(name + "=" + value);
				// } else {
				// 文件上传字段
				String filename = item.getName();
				String fileLX = "";
				// 获取当前时间
				java.util.Date date = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				format.setLenient(false);
				String strResponseDate = format.format(date);
				java.util.Random r=new java.util.Random(); 
				String newname = strResponseDate+r.nextInt(10);
				// 获得文件类型
				if (filename.lastIndexOf(".") != -1) {
					fileLX = filename.substring(filename.lastIndexOf(".") + 1);
					newname = newname + "." + fileLX;
				}
				// 创建文件
				ServletContext context = getServletContext();
				String dir = context.getRealPath("UserFile") + "/" + path;
				File file = new File(dir, newname);
				if (file.exists()) {
					out.print("<script>");
					out.print("window.parent.callbackPhoto('1')");
					out.print("</script>");
					out.close();
				} else {
					file.createNewFile();
					// 获得流，读取数据写入文件
					InputStream in = item.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					int len;
					byte[] buffer = new byte[1024];
					while ((len = in.read(buffer)) > 0)
						fos.write(buffer, 0, len);
					fos.close();
					in.close();
					// 上传成功，返回1
					out.print("<script>");
					out.print("window.parent.callbackPhoto('" + path + "/" + newname
							+ "')");
					out.print("</script>");
					out.close();
				}
				item.delete(); // 删除临时文件
			}
		}
	}
	
	public void MultiFiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );	// 从request中取时, 以UTF-8编码解析
		// 工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 获取上传文件存放的 目录 , 无则创建
		String path = request.getParameter("path");
		ServletContext context = getServletContext();
		String FullPath = context.getRealPath("UserFile") + "/" + path;
//		System.out.println("FullPath : " + FullPath);
		new java.io.File( path ).mkdir();
        /** 
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
         * 然后再将其真正写到 对应目录的硬盘上 
         */  
		diskFileItemFactory.setRepository( new File( FullPath ) );
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
        diskFileItemFactory.setSizeThreshold( 1024*1024 );
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory); 
        try
        {
            //可上传多个文件
            @SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>) upload.parseRequest( request );
            for (FileItem item : list )
            {
                //二进制类
                if(!item.isFormField()){
                    // 获取上传文件的名字                   
                    String value = item.getName(); // 1,获取路径                    
                    int start = value.lastIndexOf( "\\" );// 2,索引到最后一个反斜杠
                    String filename = value.substring( start+1 );//3, 截取(+1是去掉反斜杠) 
//                    System.out.println( name + "=" + value );
                    File file = null;
                    do {  
                        // 生成文件名
                        start = filename.lastIndexOf( "." );    // 索引到最后一个点
                        filename = filename.substring( 0, start )    // 不含扩展名的文件
                        			+"-NTJTCW-"
                                    + UUID.randomUUID().toString()  // 随机数
                                    + filename.substring( start );  // 扩展名
                        file = new File(FullPath, filename);  
                    } while (file.exists()); 
//                    System.out.println( "filename=" + filename );
                    // 写到磁盘上去
                    item.write(file); 
            		// 用于返回结果
            		response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    response.getWriter().write(filename);
                    out.flush();
                    out.close();
//                    System.out.println( "the upload file's size:" + item.getSize() );
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}


}
