package com.safety.servlet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.dao.TimeTaskDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.SendMessage;



public class MyTaskReal extends TimerTask { 
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
    private static long countReal = 1;

    /**
     * ����ִ��
     */
    public void run() {
       //TODO: ���¿������Ӧ�Ķ�ʱ����
       if (!isRunning) {
    	   Calendar calendar = Calendar.getInstance();
    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	   String dataEnd = df.format(calendar.getTime());
    	   /* HOUR_OF_DAY ָʾһ���е�Сʱ */
    	   calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
    	   String dataBegin = df.format(calendar.getTime());
    	   TimeTaskDao timeTaskDao = new TimeTaskDao();
    	   //��ѯδ���͵Ķ���
    	   ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
    	   SendMessageList = timeTaskDao.querySendSendMessageList(dataBegin, dataEnd);
    	   for(int i=0; i<SendMessageList.size(); i++){
    		   MessageDao messageDao = new MessageDao();
    		   SendMessage sendMessage = new SendMessage();
    		   sendMessage = SendMessageList.get(i);
    		   //��������ռ���
    		   MyMessage myMessage = new MyMessage();
    		   String[] tzryIDList = sendMessage.getJsrID().split(",");
    		   for(int j=0;j<tzryIDList.length;j++){
    			   myMessage.setDxnr(sendMessage.getDxnr());
    			   myMessage.setJsrID(tzryIDList[j]);
    			   myMessage.setFsr(sendMessage.getCzr());
    			   try {
    				   myMessage.setJssj(new Timestamp(df.parse(dataEnd).getTime()));
    			   } catch (ParseException e) {
					// TODO Auto-generated catch block
    				   e.printStackTrace();
    			   }
    			   myMessage.setCybz("δ����");
//    			   messageDao.insertMyMessage(myMessage);
    			   //��ѯ����
    			   ContentZzxx Zzxx = new ContentZzxx();
    			   ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
    			   Zzxx = contentZzxxDao.queryZzxxByUserName(tzryIDList[j]);
    			   //���Ͷ���
    			   messagePlatForm.submitShortMessage(Zzxx.getPhone(),sendMessage.getDxnr());
				}
    		   //���·���״̬
    		   timeTaskDao.updateDXFSZT(sendMessage.getId());
    	   }
    	   
           isRunning = true;
           System.out.println("ʵʱ��ʱ����ִ�е��˵�" + countReal + "��");
           if(countReal==24){
        	   countReal=1;
           }else{
        	   countReal++;
           }
           //TODO: Add any other things you want to do here.
           isRunning = false;
       } else {
           System.out.println("ʵʱ��ʱ����2����ִ������...");
       }
    }
}

