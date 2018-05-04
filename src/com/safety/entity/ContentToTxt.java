package com.safety.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;


public class ContentToTxt {
	/*
	 * 写入内容
	 */
	public static String WriteTxt(String txtContent, String path, ServletContext context){
		//返回创建地址
		String FileURL = "";
		//写入成功标志
		boolean flag = false;
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		format.setLenient(false);
		String strResponseDate = format.format(date);
		java.util.Random r=new java.util.Random(); 
		String newname = strResponseDate+r.nextInt(10)+".txt";
		// 创建文件
		String dir = context.getRealPath("UserFile") + "/" + path;
		File file = new File(dir, newname);
		//准备写入内容
		FileOutputStream fos = null; 
		try {
			fos = new FileOutputStream(file);
			fos.write(txtContent.getBytes("GBK"));
			fos.close();
			flag = true;
		} catch (Exception e) {  
		// TODO: handle exception  
			e.printStackTrace();  
		}
		if(flag){
			FileURL = path + "/" + newname;
		}

		return FileURL;
	}

	/*
	 * 读取内容
	 */
	public static String ReadTxt(String FileURL, ServletContext context) throws IOException{
		//返回内容
		String content = "";
		// 创建文件
		String FullFilePath = context.getRealPath("UserFile") + "/" + FileURL;
		File file = new File(FullFilePath);
		//准备读出内容
		FileReader fileReader=null;  
		BufferedReader bufferedReader=null;  
		try{  
			fileReader=new FileReader(file);  
			bufferedReader=new BufferedReader(fileReader);  
			try{  
				String read=null;  
				while((read=bufferedReader.readLine())!=null){  
					content=content+read+"\r\n";  
				}  
			}catch(Exception e){  
				e.printStackTrace();  
			}  
		}catch(Exception e){  
			e.printStackTrace();  
		}finally{  
			if(bufferedReader!=null){  
				bufferedReader.close();  
			}  
			if(fileReader!=null){  
				fileReader.close();  
			}  
		}  
		return content;
	}
	/*
	 * 更新内容
	 */
	public static void UpdateTxt(String txtContent, String FileURL, ServletContext context) throws IOException{
		// 打开文件
		String FullFilePath = context.getRealPath("UserFile") + "/" + FileURL;
		File file = new File(FullFilePath);
		//准备读出内容
		//准备写入内容
		FileOutputStream fos = null; 
		try {
			fos = new FileOutputStream(file);
			fos.write(txtContent.getBytes("GBK"));
			fos.close();
		} catch (Exception e) {  
		// TODO: handle exception  
			e.printStackTrace();  
		}
	}
}
