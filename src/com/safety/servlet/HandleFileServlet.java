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
		if("HandleFile".equals(action)){
			HandleFile(request,response);
		}else if("DeleteFile".equals(action)){
			DeleteFile(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("HandleFileSEC".equals(action)){//�ϴ�һ�ݣ�������һ��
//			HandleFileSEC(request,response);
		}else if("HandlePhoto".equals(action)){
			HandlePhoto(request,response);
		}else if("MultiFiles".equals(action)){
			MultiFiles(request,response);
		}
	}

	protected void HandleFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ·��
		String path = request.getParameter("path");
		// ���ڷ��ؽ��
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 1. ����������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2. ����FileUpload����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. �ж��Ƿ����ϴ���
		boolean b = upload.isMultipartContent(request);
		if (!b) {
			// �����ļ��ϴ�
			request.setAttribute("message", "�Բ��𣬲����ļ��ϴ�����");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}
		// ���ļ��ϴ���
		// 4. ����request�����FileItem��
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5. ��������
		for (FileItem item : fileitems) {
			// �ж��ǲ�����ͨ�ֶ�
			if (!item.isFormField()) {
				// String name = item.getFieldName();
				// String value = item.getString();
				// // �ֹ���ת����
				// value = new String(value.getBytes("iso-8859-1"),"utf-8");
				// System.out.println(name + "=" + value);
				// } else {
				// �ļ��ϴ��ֶ�
				 // ��ȡ�ϴ��ļ�������                   
                String value = item.getName(); // 1,��ȡ·��                    
                int start = value.lastIndexOf( "\\" );// 2,���������һ����б��
                String filename = value.substring( start+1 );//3, ��ȡ(+1��ȥ����б��) 
                // �����ļ���
                start = filename.lastIndexOf( "." );    // ���������һ����
                filename = filename.substring( 0, start )    // ������չ�����ļ�
                			+"-NTJTCW-"
                            + UUID.randomUUID().toString()  // �����
                            + filename.substring( start );  // ��չ��
//                    file = new File(FullPath, filename);  
//				String filename = item.getName();
//				String fileLX = "";
				// ��ȡ��ǰʱ��
//				java.util.Date date = new java.util.Date();
//				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//				format.setLenient(false);
//				String strResponseDate = format.format(date);
//				java.util.Random r=new java.util.Random(); 
//				String newname = strResponseDate+r.nextInt(10);
				// ����ļ�����
//				if (filename.lastIndexOf(".") != -1) {
//					fileLX = filename.substring(filename.lastIndexOf(".") + 1);
//					newname = newname + "." + fileLX;
//				}
				// �����ļ�
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
					// ���������ȡ����д���ļ�
					InputStream in = item.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					int len;
					byte[] buffer = new byte[1024];
					while ((len = in.read(buffer)) > 0)
						fos.write(buffer, 0, len);
					fos.close();
					in.close();
					// �ϴ��ɹ�������1
					out.print("<script>");
					out.print("window.parent.callback('" + path + "/" + filename
							+ "')");
//					out.print("window.parent.callback('"+ filename
//							+ "')");
					out.print("</script>");
					out.close();
				}
				item.delete(); // ɾ����ʱ�ļ�
			}
		}
	}

//	protected void HandleFileSEC(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		// ��ȡ·��
//		String path = request.getParameter("path");
//		String pathSEC = request.getParameter("pathSEC");
//		// ���ڷ��ؽ��
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		// 1. ����������
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		// 2. ����FileUpload����
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		// 3. �ж��Ƿ����ϴ���
//		boolean b = upload.isMultipartContent(request);
//		if (!b) {
//			// �����ļ��ϴ�
//			request.setAttribute("message", "�Բ��𣬲����ļ��ϴ�����");
//			request.getRequestDispatcher("/message.jsp").forward(request,
//					response);
//			return;
//		}
//		// ���ļ��ϴ���
//		// 4. ����request�����FileItem��
//		List<FileItem> fileitems = null;
//		try {
//			fileitems = upload.parseRequest(request);
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// 5. ��������
//		for (FileItem item : fileitems) {
//			// �ж��ǲ�����ͨ�ֶ�
//			if (!item.isFormField()) {
//				// String name = item.getFieldName();
//				// String value = item.getString();
//				// // �ֹ���ת����
//				// value = new String(value.getBytes("iso-8859-1"),"utf-8");
//				// System.out.println(name + "=" + value);
//				// } else {
//				// �ļ��ϴ��ֶ�
//				String filename = item.getName();
//				String fileLX = "";
//				// ��ȡ��ǰʱ��
//				java.util.Date date = new java.util.Date();
//				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//				format.setLenient(false);
//				String strResponseDate = format.format(date);
//				java.util.Random r=new java.util.Random(); 
//				String newname = strResponseDate+r.nextInt(10);
//				// ����ļ�����
//				if (filename.lastIndexOf(".") != -1) {
//					fileLX = filename.substring(filename.lastIndexOf(".") + 1);
//					newname = newname + "." + fileLX;
//				}
//				// �����ļ�
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
//					//������һ���ļ�
//					file.createNewFile();
//					//�����ڶ����ļ�
//					fileSEC.createNewFile();
//					// ���������ȡ����д���ļ�
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
//					// �ϴ��ɹ�������1
//					out.print("<script>");
//					out.print("window.parent.callback('" + path + "/" + newname
//							+ "','" + pathSEC + "/" + newname
//							+ "')");
//					out.print("</script>");
//					out.close();
//				}
//				item.delete(); // ɾ����ʱ�ļ�
//			}
//		}
//	}
	/** 
	 * ɾ�������ļ� 
	 * @param   dPath-��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	*/  

	protected void DeleteFile(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String path =request.getParameter("path");
		String FullFilePath = request.getRealPath("/UserFile") + "/"+path;
		File file = new File(FullFilePath);  
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
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
	 * �����ļ���������˵��
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String FullFilePath = request.getRealPath("/UserFile") + "/"+"�����ֲ�V1.doc";
		File file = new File(FullFilePath);  
		if (file.exists()) {
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
        	RequestDispatcher rd = request.getRequestDispatcher("about.jsp");
    		rd.forward(request, response);
    		return ;
        }
	}
	protected void HandlePhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ·��
		String path = request.getParameter("path");
		// ���ڷ��ؽ��
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 1. ����������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2. ����FileUpload����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. �ж��Ƿ����ϴ���
		boolean b = upload.isMultipartContent(request);
		if (!b) {
			// �����ļ��ϴ�
			request.setAttribute("message", "�Բ��𣬲����ļ��ϴ�����");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}
		// ���ļ��ϴ���
		// 4. ����request�����FileItem��
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5. ��������
		for (FileItem item : fileitems) {
			// �ж��ǲ�����ͨ�ֶ�
			if (!item.isFormField()) {
				// String name = item.getFieldName();
				// String value = item.getString();
				// // �ֹ���ת����
				// value = new String(value.getBytes("iso-8859-1"),"utf-8");
				// System.out.println(name + "=" + value);
				// } else {
				// �ļ��ϴ��ֶ�
				String filename = item.getName();
				String fileLX = "";
				// ��ȡ��ǰʱ��
				java.util.Date date = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				format.setLenient(false);
				String strResponseDate = format.format(date);
				java.util.Random r=new java.util.Random(); 
				String newname = strResponseDate+r.nextInt(10);
				// ����ļ�����
				if (filename.lastIndexOf(".") != -1) {
					fileLX = filename.substring(filename.lastIndexOf(".") + 1);
					newname = newname + "." + fileLX;
				}
				// �����ļ�
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
					// ���������ȡ����д���ļ�
					InputStream in = item.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					int len;
					byte[] buffer = new byte[1024];
					while ((len = in.read(buffer)) > 0)
						fos.write(buffer, 0, len);
					fos.close();
					in.close();
					// �ϴ��ɹ�������1
					out.print("<script>");
					out.print("window.parent.callbackPhoto('" + path + "/" + newname
							+ "')");
					out.print("</script>");
					out.close();
				}
				item.delete(); // ɾ����ʱ�ļ�
			}
		}
	}
	
	public void MultiFiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );	// ��request��ȡʱ, ��UTF-8�������
		// ����
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// ��ȡ�ϴ��ļ���ŵ� Ŀ¼ , ���򴴽�
		String path = request.getParameter("path");
		ServletContext context = getServletContext();
		String FullPath = context.getRealPath("UserFile") + "/" + path;
//		System.out.println("FullPath : " + FullPath);
		new java.io.File( path ).mkdir();
        /** 
         * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ�  
         * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ��  
         * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ���� 
         */  
		diskFileItemFactory.setRepository( new File( FullPath ) );
        //���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��  
        diskFileItemFactory.setSizeThreshold( 1024*1024 );
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory); 
        try
        {
            //���ϴ�����ļ�
            @SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>) upload.parseRequest( request );
            for (FileItem item : list )
            {
                //��������
                if(!item.isFormField()){
                    // ��ȡ�ϴ��ļ�������                   
                    String value = item.getName(); // 1,��ȡ·��                    
                    int start = value.lastIndexOf( "\\" );// 2,���������һ����б��
                    String filename = value.substring( start+1 );//3, ��ȡ(+1��ȥ����б��) 
//                    System.out.println( name + "=" + value );
                    File file = null;
                    do {  
                        // �����ļ���
                        start = filename.lastIndexOf( "." );    // ���������һ����
                        filename = filename.substring( 0, start )    // ������չ�����ļ�
                        			+"-NTJTCW-"
                                    + UUID.randomUUID().toString()  // �����
                                    + filename.substring( start );  // ��չ��
                        file = new File(FullPath, filename);  
                    } while (file.exists()); 
//                    System.out.println( "filename=" + filename );
                    // д��������ȥ
                    item.write(file); 
            		// ���ڷ��ؽ��
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
