package com.safety.servlet;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 


import com.safety.dao.BbsbCwbbDao;
import com.safety.dao.BbsbSjbbDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.HtbbDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.dao.TimeTaskDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.CwbbSydwzycwzbbHzb;
import com.safety.entity.CwbbYszxbHzb;
import com.safety.entity.HtbbSetTime;
import com.safety.entity.MyMessage;
import com.safety.entity.PostInformation;
import com.safety.entity.SendMessage;
import com.safety.entity.SjbbLssfzcHzb;
import com.safety.entity.SjbbLssfzcZxm;

public class MyTask extends TimerTask { 
	/**
	 * ����ƽ̨
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
	  /**
     * �ж������Ƿ���ִ��
     */
    private static boolean isRunning = false;

    /**
     * ����
     */
    private static long count = 1;

    /**
     * ����ִ��
     */
    public void run() {
       //TODO: ���¿������Ӧ�Ķ�ʱ����
       if (!isRunning) {
    	   java.util.Date  date=new java.util.Date();
    	   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
    	   String now = format.format(date);
    	   TimeTaskDao timeTaskDao = new TimeTaskDao();
    	   //�ļ����� �·��ļ��ظ����³�ʱ��Ϣ
    	   String IDList1 = timeTaskDao.querySFCSList(now,"δ�ظ�");
    	   if(!"".equals(IDList1)&&IDList1!=null){
	    	   timeTaskDao.updateSFCS(IDList1);
    	   }
    	   //ÿ��15���Զ�����Ԥ��ִ�б���ܱ�
    	   if(judgeTime(15)){
    		   Calendar calendar = Calendar.getInstance();
    		   int year = calendar.get(Calendar.YEAR);
    		   int month = calendar.get(Calendar.MONTH);
    		   if(month==0){
    			   year = year-1;
    			   month = 12;
    		   }
    		   ZdscYszxbHzb(year, month);
    	   }
    	   //ÿ��15���Զ����ɽ�ͨ��ҵ��λ��Ҫ����ָ��״�����ܱ�
    	   if(judgeTime(15)){
    		   Calendar calendar = Calendar.getInstance();
    		   int year = calendar.get(Calendar.YEAR);
    		   int month = calendar.get(Calendar.MONTH);
    		   if(month==0){
    			   year = year-1;
    			   month = 12;
    		   }
    		   ZdscSydwzycwzbbHzb(year, month);
    	   }
    	   //ÿ��������9���Զ������ϼ����м����أ���������ҵ��λ����ʵ�շ����߼����Բ����ܱ�
    	   if(judgeTime(9)){
    		   Calendar calendar = Calendar.getInstance();
    		   int year = calendar.get(Calendar.YEAR);
    		   int month = calendar.get(Calendar.MONTH);
    		   if(month==0){
        		   LssfzczcbHzb(year-1, 4);
    		   }else if(month==3){
        		   LssfzczcbHzb(year, 1);
    		   }else if(month==6){
        		   LssfzczcbHzb(year, 2);
    		   }else if(month==9){
        		   LssfzczcbHzb(year, 3);
    		   }
    	   }
    	   
    	   //ÿ��鿴�Ƿ���Ҫ�����ϱ������֪ͨ
    	   HtbbDao htbbDao = new HtbbDao();
    	   ArrayList<HtbbSetTime> HtbbSetTimeList = new ArrayList<HtbbSetTime>();
    	   HtbbSetTimeList = htbbDao.queryHtbbSetTimeList();
		   HtbbSetTime htbbSetTime = new HtbbSetTime();
    	   for(int i=0; i<HtbbSetTimeList.size(); i++){
    		   Calendar calendar = Calendar.getInstance();
    		   int month = calendar.get(Calendar.MONTH)+1;
    		   int day = calendar.get(Calendar.DAY_OF_MONTH);
    		   htbbSetTime = HtbbSetTimeList.get(i);
    		   if(htbbSetTime.getSetlx()==1){
    			   //ÿ��
    			   HtbbSend(htbbSetTime);
    		   }else if(htbbSetTime.getSetlx()==2){
    			   //ÿ��
    			   if(htbbSetTime.getMday()==day){
    				   HtbbSend(htbbSetTime);
    			   }
    		   }else if(htbbSetTime.getSetlx()==3){
    			   //ÿ����
    			   if((month-htbbSetTime.getQmonth())%3==0){
        			   if(htbbSetTime.getQmday()==day){
        				   HtbbSend(htbbSetTime);
        			   }
    			   }
    		   }else if(htbbSetTime.getSetlx()==4){
    			   //ÿ��
    			   String date1 = htbbSetTime.getYday().toString();
    			   java.util.Date date2=new java.util.Date();
    			   SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    			   String date3 = sdf.format(date2);
    			   int month1 = Integer.parseInt(date1.substring(5, 7));
    			   int day1 = Integer.parseInt(date1.substring(8, 10));
    			   int month2 = Integer.parseInt(date3.substring(5, 7));
    			   int day2 = Integer.parseInt(date3.substring(8, 10));
    			   if (month1 == month2 && day1 == day2) {
    				   HtbbSend(htbbSetTime);
    			   }
    		   }
    	   }
//    	   String IDList2 = timeTaskDao.queryAQJCList(now);
//    	   if(!"".equals(IDList2)&&IDList2!=null){
//    		   timeTaskDao.updateAQJC(IDList2);
//    	   }
//    	   //�ϱ���ȫ���ϸ��³�ʱ��Ϣ
//    	   String IDList3 = timeTaskDao.querySBAQCLList(now);
//    	   if(!"".equals(IDList3)&&IDList3!=null){
//    		   timeTaskDao.updateSBAQCL(IDList3);
//    	   }
//    	   //��ȫ�������ĸ��³�ʱ��Ϣ
//    	   String IDList4 = timeTaskDao.queryAQYHZGList(now);
//    	   if(!"".equals(IDList4)&&IDList4!=null){
//    		   timeTaskDao.updateAQYHZG(IDList4);
//    	   }
    	   //������ҳ��������
    	   ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
    	   //��ѯ�豣��������5����¼(5����ͼ)
    	   String limStr = timeTaskDao.querylimStr();
    	   //��ѯ������ǰ��ʱ��
    	   String ltime = getThreeMonth();
    	   //��ѯ��ɾ���ļ�¼
    	   InformatList = timeTaskDao.queryInformatDel(limStr, ltime);
    	   //��ʼɾ����¼
    	   File directory = new File("");//�趨Ϊ��ǰ�ļ���
    	   String path = directory.getAbsolutePath();
    	   String FullFilePath = path.replace("bin", "webapps/DYIMS/UserFile");
    	   for(int i=0;i<InformatList.size();i++){
    		   //ɾ�������ļ�
        	   String path1 =InformatList.get(i).getFileURL();
        	   delFile(FullFilePath+"/"+path1);
        	   //ɾ������ͼƬ
        	   String path2 =InformatList.get(i).getPhotoURL();
        	   delFile(FullFilePath+"/"+path2);
        	   //ɾ����¼
        	   timeTaskDao.DeleteInformat(InformatList.get(i).getId());
    	   }
           isRunning = true;
           System.out.println("��ʱ����ִ�е��˵�" + count + "��");
           count++;
           //TODO: Add any other things you want to do here.
           isRunning = false;
       } else {
           System.out.println("��ʱ����1����ִ������...");
       }
    }
    private String getThreeMonth(){
    	Date date = new Date();  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	String time = sdf.format(date);  
    	String[] item = time.split("-");  
    	int year  = Integer.parseInt(item[0]);  
    	int month = Integer.parseInt(item[1]);  
    	int day   = Integer.parseInt(item[2]);  
    	if((month - 3) <= 0){  
    		month = month + 12 - 3;  
    		year = year -1;  
    	}else {  
    		month = month - 3;  
    	}  
    	time = year + "-" + month + "-" + day; 
    	return time;
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

    private boolean judgeTime(int day){
    	boolean res = false;
    	Calendar calendar = Calendar.getInstance();
    	if(day==calendar.get(Calendar.DAY_OF_MONTH)){
    		res =true;
    	}
    	return res;
	}
    /*
	 *  ÿ���Զ�����Ԥ��ִ�б���ܱ�
	 * 
	 */
	private void ZdscYszxbHzb(int year, int month){
		CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		//����ʱ���ѯ����λ����
		//��·����
		String glc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"��·����");
		//��������
		String hdc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"��������");
		//�ط����¾�
		String hsj = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"�ط����¾�");
		//�������
		String ygc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"�������");
		//��ͨ���̽������
		String jsc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"��ͨ���̽������");
		//��ͨ���������ල��
		String zjc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"��ͨ���������ල��");
		//�������
		String kjzx = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"�������");
		//��Ϣ����
		String xxzx = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"��Ϣ����");
		cwbbYszxbHzb.setGlc(glc);
		cwbbYszxbHzb.setHdc(hdc);
		cwbbYszxbHzb.setHsj(hsj);
		cwbbYszxbHzb.setYgc(ygc);
		cwbbYszxbHzb.setJsc(jsc);
		cwbbYszxbHzb.setZjc(zjc);
		cwbbYszxbHzb.setKjzx(kjzx);
		cwbbYszxbHzb.setXxzx(xxzx);
		cwbbYszxbHzb.setYear(year);
		cwbbYszxbHzb.setMonth(month);
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		cwbbYszxbHzb.setCzsj(data1);
		cwbbYszxbHzb.setCzr("�Զ�����");
		//ɾ��ԭ�м�¼
		bbsbCwbbDao.deleteCwbbYszxbHzbByTime(year,month);
		//�������¼�¼
		bbsbCwbbDao.insertCwbbYszxbHzb(cwbbYszxbHzb);
		
		return;
	}
	
    /*
	 *  ÿ���Զ����ɽ�ͨ��ҵ��λ��Ҫ����ָ��״�����ܱ�
	 * 
	 */
	private void ZdscSydwzycwzbbHzb(int year, int month){
		CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		//����ʱ���ѯ����λ����
		//��·����
		String glc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"��·����");
		//��������
		String hdc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"��������");
		//�ط����¾�
		String hsj = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"�ط����¾�");
		//�������
		String ygc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"�������");
		//��ͨ���̽������
		String jsc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"��ͨ���̽������");
		//��ͨ���������ල��
		String zjc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"��ͨ���������ල��");
		//�������
		String kjzx = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"�������");
		//��Ϣ����
		String xxzx = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"��Ϣ����");
		cwbbSydwzycwzbbHzb.setGlc(glc);
		cwbbSydwzycwzbbHzb.setHdc(hdc);
		cwbbSydwzycwzbbHzb.setHsj(hsj);
		cwbbSydwzycwzbbHzb.setYgc(ygc);
		cwbbSydwzycwzbbHzb.setJsc(jsc);
		cwbbSydwzycwzbbHzb.setZjc(zjc);
		cwbbSydwzycwzbbHzb.setKjzx(kjzx);
		cwbbSydwzycwzbbHzb.setXxzx(xxzx);
		cwbbSydwzycwzbbHzb.setYear(year);
		cwbbSydwzycwzbbHzb.setMonth(month);
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		cwbbSydwzycwzbbHzb.setCzsj(data1);
		cwbbSydwzycwzbbHzb.setCzr("�Զ�����");
		//ɾ��ԭ�м�¼
		bbsbCwbbDao.deleteCwbbSydwzycwzbbHzbByTime(year,month);
		//�������¼�¼
		bbsbCwbbDao.insertCwbbSydwzycwzbbHzb(cwbbSydwzycwzbbHzb);
		
		return;
	}
	
	/*
	 *  ÿ���Զ�������ʵ�շ����߼����Բ����ܱ�
	 * 
	 */
	private void LssfzczcbHzb(int year, int jd){
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		SjbbLssfzcHzb sjbbLssfzcHzb = new SjbbLssfzcHzb();
		sjbbLssfzcHzb.setYear(year);
		sjbbLssfzcHzb.setJd(jd);
		sjbbLssfzcHzb.setCzr("");
		sjbbLssfzcHzb.setCzrID("");
//		sjbbLssfzcHzb.setCzrdw(UserInfo.getCompany());
		sjbbLssfzcHzb.setCzrphone("");
		sjbbLssfzcHzb.setCzsj(data1);
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		int zbid =bbsbSjbbDao.insertSjbbLssfzcHzb(sjbbLssfzcHzb);
		String IDStr = bbsbSjbbDao.querySjbbLssfzcBySj(year+"",jd+"");
		//���ӱ���������IDƥ������ݲ��
		ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
		if(!"".equals(IDStr)){
			sjbbLssfzcZxmList = bbsbSjbbDao.querySjbbLssfzcZxmByXmidList(IDStr);
			bbsbSjbbDao.insertSjbbLssfzcZxmHzb(zbid,sjbbLssfzcZxmList);
		}
//		
		return;
	}

    /*
	 *  ��Ҫ���ѵı����ϴ�����
	 * 
	 */
	private void HtbbSend(HtbbSetTime htbbSetTime){
		MessageDao messageDao = new MessageDao();
	  	//��������ռ���
		ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
		String phoneList ="";
	  	String[] tzryIDList = htbbSetTime.getTzryID().split(",");
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
	  	for(int i=0;i<tzryIDList.length;i++){
		  	MyMessage myMessage = new MyMessage();
			myMessage.setDxnr(htbbSetTime.getTznr());
			myMessage.setJssj(data1);
		  	myMessage.setJsrID(tzryIDList[i]);
			myMessage.setFsr(htbbSetTime.getCzr());
			myMessage.setCybz("δ����");
			MyMessageList.add(myMessage);
			//��ѯ����
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(tzryIDList[i]);
	  	}
		messageDao.insertMyMessage(MyMessageList);
		//���Ͷ���
		messagePlatForm.submitShortMessageMany(phoneList,htbbSetTime.getTznr());
	}
   
}

