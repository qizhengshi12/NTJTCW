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
	 * 短信平台
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
	  /**
     * 判断任务是否在执行
     */
    private static boolean isRunning = false;

    /**
     * 计数
     */
    private static long countReal = 1;

    /**
     * 任务执行
     */
    public void run() {
       //TODO: 以下可添加相应的定时任务
       if (!isRunning) {
    	   Calendar calendar = Calendar.getInstance();
    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	   String dataEnd = df.format(calendar.getTime());
    	   /* HOUR_OF_DAY 指示一天中的小时 */
    	   calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
    	   String dataBegin = df.format(calendar.getTime());
    	   TimeTaskDao timeTaskDao = new TimeTaskDao();
    	   //查询未发送的短信
    	   ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
    	   SendMessageList = timeTaskDao.querySendSendMessageList(dataBegin, dataEnd);
    	   for(int i=0; i<SendMessageList.size(); i++){
    		   MessageDao messageDao = new MessageDao();
    		   SendMessage sendMessage = new SendMessage();
    		   sendMessage = SendMessageList.get(i);
    		   //加入各人收件箱
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
    			   myMessage.setCybz("未查阅");
//    			   messageDao.insertMyMessage(myMessage);
    			   //查询号码
    			   ContentZzxx Zzxx = new ContentZzxx();
    			   ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
    			   Zzxx = contentZzxxDao.queryZzxxByUserName(tzryIDList[j]);
    			   //发送短信
    			   messagePlatForm.submitShortMessage(Zzxx.getPhone(),sendMessage.getDxnr());
				}
    		   //更新发送状态
    		   timeTaskDao.updateDXFSZT(sendMessage.getId());
    	   }
    	   
           isRunning = true;
           System.out.println("实时定时任务执行到了第" + countReal + "次");
           if(countReal==24){
        	   countReal=1;
           }else{
        	   countReal++;
           }
           //TODO: Add any other things you want to do here.
           isRunning = false;
       } else {
           System.out.println("实时定时任务2已在执行中了...");
       }
    }
}

