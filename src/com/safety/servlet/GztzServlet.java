package com.safety.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.GztzDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Gztzhf;
import com.safety.entity.PaginationTool;
import com.safety.entity.Gztz;
import com.safety.entity.Permissions;


public class GztzServlet  extends HttpServlet{
	/**
	 * ����ƽ̨
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
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
		if("getGztzXFList".equals(action)){//�·�֪ͨ�б�
			getGztzXFList(request,response);
		}else if("insertGztzXF".equals(action)){//����֪ͨ
			insertGztzXF(request,response);
		}else if("showGztzXF".equals(action)){//�鿴֪ͨ
			showGztzXF(request,response);
		}else if("deleteGztzXF".equals(action)){//ɾ��֪ͨ
			deleteGztzXF(request,response);
		}else if("showGztzHF".equals(action)){//�鿴�ظ����
			showGztzHF(request,response);
		}else if("getGztzJSList".equals(action)){//����֪ͨ�б�
			getGztzJSList(request,response);
		}else if("updateGztzHF".equals(action)){//�ظ�
			updateGztzHF(request,response);
		}
	}
	
	/*
	 * �·�֪ͨ�б�
	 * 
	 */
	protected void getGztzXFList(HttpServletRequest request,
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
			
		}else if(UserPer.getGztz().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ѯ����֪ͨ�б�
			ArrayList<Gztz> GztzList = new ArrayList<Gztz>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String srbt = request.getParameter("srbt");
			String cxmc = request.getParameter("cxmc");
			String cxdd = request.getParameter("cxdd");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				srbt="";
				cxmc = "";
				cxdd = "";
				cxssrq1 = "";
				cxssrq2 = "";
			}
			GztzDao gztzDao = new GztzDao();
			countAll = gztzDao.queryGztzXFListCount(srbt,UserInfo.getUsername());
			GztzList = gztzDao.queryGztzXFList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("GztzList", GztzList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxmc", cxmc);
			request.setAttribute("cxdd", cxdd);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzXFList.jsp").forward(request,
					response);
		}
	}
	

	/*
	 *  ����֪ͨ
	 * 
	 */
	protected void insertGztzXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String tzmc = request.getParameter("tzmc");
			String tznr = request.getParameter("tznr");
			String tzsj = request.getParameter("tzsj");
			String tzdd = request.getParameter("tzdd");
			String tzry = request.getParameter("ry");
			String tzryID = request.getParameter("ryID");
			String SMSFlag = request.getParameter("SMSFlag");//�Ƿ��Ͷ��ţ�0�����ͣ�1�������ͣ�
			//�����·���¼
			GztzDao gztzDao = new GztzDao();
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Gztz gztz = new Gztz();
			gztz.setTzmc(tzmc);
			gztz.setTznr(tznr);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Timestamp ts = null;
			try {
				ts = new Timestamp(df.parse(tzsj).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gztz.setTzsj(ts);
			gztz.setTzdd(tzdd);
			gztz.setTzry(tzry);
			gztz.setCzr(UserInfo.getName());
			gztz.setCzrID(UserInfo.getUsername());
			gztz.setCzsj(data1);
			int GztzID = 0;
			//׼��֪ͨ
			String[] tzryIDList = tzryID.split(",");
			//ȥ���ظ���
			List list = Arrays.asList(tzryIDList);
			HashSet<String> hfidList = new HashSet<String>(list);
			Iterator<String> hfid=hfidList.iterator();
			String hfidNewList = "";
			while(hfid.hasNext()){
				if("".equals(hfidNewList)){
					hfidNewList = hfid.next();
				}else{
					hfidNewList = hfidNewList+","+ hfid.next();
				}
			}
			gztz.setTzryID(hfidNewList);
			GztzID = gztzDao.insertGztz(gztz);
			Iterator<String> hfid1=hfidList.iterator();
			ArrayList<Gztzhf> GztzhfList =  new ArrayList<Gztzhf>();
			while(hfid1.hasNext()){
				ContentZzxx Zzxx = new ContentZzxx();
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				Zzxx = contentZzxxDao.queryZzxxByUserName(hfid1.next());
				//��ʼ֪ͨ
				Gztzhf gztzhf = new Gztzhf();
				gztzhf.setHfr(Zzxx.getName());
				gztzhf.setHfrID(Zzxx.getUsername());
				gztzhf.setTzid(GztzID);
				gztzhf.setTzmc(tzmc);
				GztzhfList.add(gztzhf);
				
				//�ж��Ƿ񷢶���
				if("0".equals(SMSFlag)){
					//���Ͷ���
					String nr = UserInfo.getName()+"������һ���µĻ���֪ͨ���뼰ʱ�鿴";
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),nr);
				}
				
			}
			gztzDao.insertGztzhf(GztzhfList);
			//������֪ͨ�ظ�IDList�������·���¼��
			
			
			request.getRequestDispatcher("GztzServlet?action=getGztzXFList&result='�����ɹ�'&srbt=").forward(request,
					response);
		}
	}

	
	/*
	 *  ɾ��֪ͨ
	 * 
	 */
	protected void deleteGztzXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		GztzDao gztzDao = new GztzDao();
		if(!"".equals(id)&&id!=null){
			gztzDao.DeleteGztzById(Integer.parseInt(id));
		}
		RequestDispatcher rd = request.getRequestDispatcher("GztzServlet?action=getGztzXFList&result='ɾ���ɹ�'&srbt=");
		rd.forward(request, response);
		return;
	}


	/*
	 *  ����ID�鿴֪ͨ
	 * 
	 */
	protected void showGztzXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			// TODO Auto-generated method stub
			String gztzid = request.getParameter("gztzid");
			GztzDao gztzDao = new GztzDao();
			//�ظ�
			Gztz gztz = new Gztz();
			gztz = gztzDao.queryGztzByID(Integer.parseInt(gztzid));
			request.setAttribute("gztz", gztz);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzXFShow.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �鿴�ظ����
	 * 
	 */
	protected void showGztzHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			// TODO Auto-generated method stub
			String tzid = request.getParameter("tzid");
			GztzDao gztzDao = new GztzDao();
			//�ظ�
			ArrayList<Gztzhf> GztzhfList = new ArrayList<Gztzhf>();
			if(!"".equals(tzid)&&tzid!=null){
				GztzhfList = gztzDao.queryGztzXFByIDList(Integer.parseInt(tzid));
			}
			request.setAttribute("GztzhfList", GztzhfList);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzHFShow.jsp").forward(request,
					response);
		}
	}
	
	/*
	 * ����֪ͨ�б�
	 * 
	 */
	protected void getGztzJSList(HttpServletRequest request,
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
			
		}else if(UserPer.getGztz().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ѯ���յ��Ĺ���֪ͨ�б�
			ArrayList<Gztzhf> GztzhfList = new ArrayList<Gztzhf>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			String srbt = request.getParameter("srbt");
			String cxmc = request.getParameter("cxmc");
			String sfhf = request.getParameter("sfhf");//0:���У�1:�ѻظ���2��δ�ظ���
			if("".equals(srbt)){
				cxmc="";
				sfhf="0";
			}
			GztzDao gztzDao = new GztzDao();
			countAll = gztzDao.queryGztzJSListCount(srbt,UserInfo.getUsername());
			GztzhfList = gztzDao.queryGztzJSList(srbt,UserInfo.getUsername(), begin, pageSize);
			request.setAttribute("GztzhfList", GztzhfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("sfhf", sfhf);
			request.setAttribute("cxmc", cxmc);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/gztzJSList.jsp").forward(request,
					response);
		}
	}
	
	
	/*
	 *  �ύ�ظ�
	 * 
	 */
	protected void updateGztzHF(HttpServletRequest request,
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
			String hfnr = request.getParameter("hfnr");
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//�޸�
			GztzDao gztzDao = new GztzDao();
			Gztzhf gztzhf = new Gztzhf();
			gztzhf.setId(Integer.parseInt(id));
			gztzhf.setHfnr(hfnr);
			gztzhf.setHfsj(data1);
			gztzDao.updateGztzHF(gztzhf);
			
			request.getRequestDispatcher("GztzServlet?action=getGztzJSList&sfhf=0").forward(request,
					response);
		}
	}
	private java.sql.Date DateFormat(String DString){
		if(!"".equals(DString)&&DString!=null){
			java.util.Date date = null;
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				date = format.parse(DString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date resDate = new java.sql.Date(date.getTime());
			return resDate;
		}else{
			return null;
		}
	}
	//�Ƚ�ʱ��
	private String DateCompare(String DString, String now){
		String res="δ��ʱ";
		java.util.Date date = null;
		java.util.Date nowdate=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			date = format.parse(DString);
			nowdate = format.parse(now.substring(0, 10));
			if(date.getTime()<nowdate.getTime()){
				res="��ʱ";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
