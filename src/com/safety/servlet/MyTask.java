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
    private static long count = 1;

    /**
     * 任务执行
     */
    public void run() {
       //TODO: 以下可添加相应的定时任务
       if (!isRunning) {
    	   java.util.Date  date=new java.util.Date();
    	   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
    	   String now = format.format(date);
    	   TimeTaskDao timeTaskDao = new TimeTaskDao();
    	   //文件管理 下发文件回复更新超时信息
    	   String IDList1 = timeTaskDao.querySFCSList(now,"未回复");
    	   if(!"".equals(IDList1)&&IDList1!=null){
	    	   timeTaskDao.updateSFCS(IDList1);
    	   }
    	   //每月15日自动生成预算执行表汇总表
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
    	   //每月15日自动生成交通事业单位主要财务指标状况汇总表
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
    	   //每季度下月9日自动生成上季度市级机关（含下属事业单位）落实收费政策季度自查表汇总表
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
    	   
    	   //每天查看是否需要发送上报报表的通知
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
    			   //每天
    			   HtbbSend(htbbSetTime);
    		   }else if(htbbSetTime.getSetlx()==2){
    			   //每月
    			   if(htbbSetTime.getMday()==day){
    				   HtbbSend(htbbSetTime);
    			   }
    		   }else if(htbbSetTime.getSetlx()==3){
    			   //每季度
    			   if((month-htbbSetTime.getQmonth())%3==0){
        			   if(htbbSetTime.getQmday()==day){
        				   HtbbSend(htbbSetTime);
        			   }
    			   }
    		   }else if(htbbSetTime.getSetlx()==4){
    			   //每年
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
//    	   //上报安全材料更新超时信息
//    	   String IDList3 = timeTaskDao.querySBAQCLList(now);
//    	   if(!"".equals(IDList3)&&IDList3!=null){
//    		   timeTaskDao.updateSBAQCL(IDList3);
//    	   }
//    	   //安全隐患整改更新超时信息
//    	   String IDList4 = timeTaskDao.queryAQYHZGList(now);
//    	   if(!"".equals(IDList4)&&IDList4!=null){
//    		   timeTaskDao.updateAQYHZG(IDList4);
//    	   }
    	   //清理首页窗口新闻
    	   ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
    	   //查询需保留的至少5个记录(5个有图)
    	   String limStr = timeTaskDao.querylimStr();
    	   //查询三个月前的时间
    	   String ltime = getThreeMonth();
    	   //查询需删除的记录
    	   InformatList = timeTaskDao.queryInformatDel(limStr, ltime);
    	   //开始删除记录
    	   File directory = new File("");//设定为当前文件夹
    	   String path = directory.getAbsolutePath();
    	   String FullFilePath = path.replace("bin", "webapps/DYIMS/UserFile");
    	   for(int i=0;i<InformatList.size();i++){
    		   //删除下载文件
        	   String path1 =InformatList.get(i).getFileURL();
        	   delFile(FullFilePath+"/"+path1);
        	   //删除下载图片
        	   String path2 =InformatList.get(i).getPhotoURL();
        	   delFile(FullFilePath+"/"+path2);
        	   //删除记录
        	   timeTaskDao.DeleteInformat(InformatList.get(i).getId());
    	   }
           isRunning = true;
           System.out.println("定时任务执行到了第" + count + "次");
           count++;
           //TODO: Add any other things you want to do here.
           isRunning = false;
       } else {
           System.out.println("定时任务1已在执行中了...");
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
            System.out.println("删除文件操作出错 ");   
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
	 *  每月自动生成预算执行表汇总表
	 * 
	 */
	private void ZdscYszxbHzb(int year, int month){
		CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		//根据时间查询各单位数据
		//公路管理处
		String glc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"公路管理处");
		//航道管理处
		String hdc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"航道管理处");
		//地方海事局
		String hsj = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"地方海事局");
		//运输管理处
		String ygc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"运输管理处");
		//交通工程建设管理处
		String jsc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"交通工程建设管理处");
		//交通工程质量监督处
		String zjc = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"交通工程质量监督处");
		//会计中心
		String kjzx = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"会计中心");
		//信息中心
		String xxzx = bbsbCwbbDao.queryCwbbYszxbBySjDW(year,month,"信息中心");
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
		//获取当前时间
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		cwbbYszxbHzb.setCzsj(data1);
		cwbbYszxbHzb.setCzr("自动汇总");
		//删除原有记录
		bbsbCwbbDao.deleteCwbbYszxbHzbByTime(year,month);
		//新增最新记录
		bbsbCwbbDao.insertCwbbYszxbHzb(cwbbYszxbHzb);
		
		return;
	}
	
    /*
	 *  每月自动生成交通事业单位主要财务指标状况汇总表
	 * 
	 */
	private void ZdscSydwzycwzbbHzb(int year, int month){
		CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
		BbsbCwbbDao bbsbCwbbDao = new BbsbCwbbDao();
		//根据时间查询各单位数据
		//公路管理处
		String glc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"公路管理处");
		//航道管理处
		String hdc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"航道管理处");
		//地方海事局
		String hsj = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"地方海事局");
		//运输管理处
		String ygc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"运输管理处");
		//交通工程建设管理处
		String jsc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"交通工程建设管理处");
		//交通工程质量监督处
		String zjc = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"交通工程质量监督处");
		//会计中心
		String kjzx = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"会计中心");
		//信息中心
		String xxzx = bbsbCwbbDao.queryCwbbSydwzycwzbbBySjDW(year,month,"信息中心");
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
		//获取当前时间
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		cwbbSydwzycwzbbHzb.setCzsj(data1);
		cwbbSydwzycwzbbHzb.setCzr("自动汇总");
		//删除原有记录
		bbsbCwbbDao.deleteCwbbSydwzycwzbbHzbByTime(year,month);
		//新增最新记录
		bbsbCwbbDao.insertCwbbSydwzycwzbbHzb(cwbbSydwzycwzbbHzb);
		
		return;
	}
	
	/*
	 *  每月自动生成落实收费政策季度自查表汇总表
	 * 
	 */
	private void LssfzczcbHzb(int year, int jd){
		//获取当前时间
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
		//将子表中与上述ID匹配的内容查出
		ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
		if(!"".equals(IDStr)){
			sjbbLssfzcZxmList = bbsbSjbbDao.querySjbbLssfzcZxmByXmidList(IDStr);
			bbsbSjbbDao.insertSjbbLssfzcZxmHzb(zbid,sjbbLssfzcZxmList);
		}
//		
		return;
	}

    /*
	 *  需要提醒的报表上传工作
	 * 
	 */
	private void HtbbSend(HtbbSetTime htbbSetTime){
		MessageDao messageDao = new MessageDao();
	  	//加入各人收件箱
		ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
		String phoneList ="";
	  	String[] tzryIDList = htbbSetTime.getTzryID().split(",");
		//获取当前时间
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
	  	for(int i=0;i<tzryIDList.length;i++){
		  	MyMessage myMessage = new MyMessage();
			myMessage.setDxnr(htbbSetTime.getTznr());
			myMessage.setJssj(data1);
		  	myMessage.setJsrID(tzryIDList[i]);
			myMessage.setFsr(htbbSetTime.getCzr());
			myMessage.setCybz("未查阅");
			MyMessageList.add(myMessage);
			//查询号码
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			phoneList = phoneList+"#@"+contentZzxxDao.queryPhoneByUserName(tzryIDList[i]);
	  	}
		messageDao.insertMyMessage(MyMessageList);
		//发送短信
		messagePlatForm.submitShortMessageMany(phoneList,htbbSetTime.getTznr());
	}
   
}

