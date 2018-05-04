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
	 * д������
	 */
	public static String WriteTxt(String txtContent, String path, ServletContext context){
		//���ش�����ַ
		String FileURL = "";
		//д��ɹ���־
		boolean flag = false;
		// ��ȡ��ǰʱ��
		java.util.Date date = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		format.setLenient(false);
		String strResponseDate = format.format(date);
		java.util.Random r=new java.util.Random(); 
		String newname = strResponseDate+r.nextInt(10)+".txt";
		// �����ļ�
		String dir = context.getRealPath("UserFile") + "/" + path;
		File file = new File(dir, newname);
		//׼��д������
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
	 * ��ȡ����
	 */
	public static String ReadTxt(String FileURL, ServletContext context) throws IOException{
		//��������
		String content = "";
		// �����ļ�
		String FullFilePath = context.getRealPath("UserFile") + "/" + FileURL;
		File file = new File(FullFilePath);
		//׼����������
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
	 * ��������
	 */
	public static void UpdateTxt(String txtContent, String FileURL, ServletContext context) throws IOException{
		// ���ļ�
		String FullFilePath = context.getRealPath("UserFile") + "/" + FileURL;
		File file = new File(FullFilePath);
		//׼����������
		//׼��д������
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
