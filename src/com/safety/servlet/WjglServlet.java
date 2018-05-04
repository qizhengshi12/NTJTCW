package com.safety.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MessagePlatForm;
import com.safety.dao.WjglDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.WjglTJ;
import com.safety.entity.Wjglhf;
import com.safety.entity.Wjglxf;


public class WjglServlet  extends HttpServlet{
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
		if("getWjglList".equals(action)){//�����ļ������б�
			getWjglList(request,response);
		}else if("getWjglXFList".equals(action)){//�����·��ļ������б�
			getWjglXFList(request,response);
		}if("getWjglJSList".equals(action)){//��ظ��ļ������б�
			getWjglJSList(request,response);
		}else if("showWjglXF".equals(action)){//�·��ļ������鿴
			showWjglXF(request,response);
		}else if("insertWjglXF".equals(action)){//�·��ļ���������
			insertWjglXF(request,response);
		}else if("deleteWjglXF".equals(action)){//�·��ļ�����ɾ��
			deleteWjglXF(request,response);
		}else if("showWjglHF".equals(action)){//�ظ��ļ������鿴
			showWjglHF(request,response);
		}else if("saveWjglHF".equals(action)){//�ύ�ظ�
			saveWjglHF(request,response);
		}else if("downLoadXF".equals(action)){//�����ļ��·�
			downLoadXF(request,response);
		}else if("downLoadHF".equals(action)){//�����ļ��ظ�
			downLoadHF(request,response);
		}else if("getWjglTJList".equals(action)){// ���·��ļ�����ͳ���ۺ��б�
			getWjglTJList(request,response);
		}else if("getWjglCGList".equals(action)){//���˲ݸ��ļ������б�
			getWjglCGList(request,response);
		}else if("editWjglCG".equals(action)){//���˲ݸ��ļ������༭
			editWjglCG(request,response);
		}else if("insertWjglCG".equals(action)){//���˲ݸ��ļ���������
			insertWjglCG(request,response);
		}else if("wjglXFInsert".equals(action)){//��ת���·��ļ�ҳ��
			wjglXFInsert(request,response);
		}
	}
	/*
	 * �����ļ������б�
	 * 
	 */
	protected void getWjglList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ѯ�·��б�
			ArrayList<Wjglxf> WjglList = new ArrayList<Wjglxf>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxwjlx = request.getParameter("cxwjlx");
			String cxwjh = request.getParameter("cxwjh");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//��Ϊ0�������ҳ���룻1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("0".equals(flag)||"1".equals(flag)||"".equals(srbt)||srbt==null){
				//ͨ�������ѯ
				cxwjmc = "";
				cxwjlx = "";
				cxwjh = "";
				cxcompany = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " where fszt='1' ";
			}else{
				srbt= srbt + " and fszt='1' ";
			}
			countAll = wjglDao.queryWjglXFListByBtCount(srbt);
			WjglList = wjglDao.queryWjglXFListByBt(srbt, begin, pageSize);
			request.setAttribute("WjglList", WjglList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("flag", flag);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxwjlx", cxwjlx);
			request.setAttribute("cxwjh", cxwjh);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglList.jsp").forward(request,
					response);
		}
	}
	/*
	 * ��ת���·��ļ�ҳ��
	 * 
	 */
	protected void wjglXFInsert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
//		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
//		}else if(UserPer.getWjgl().indexOf("1")==-1){
//			response.setContentType("text/html;charset=gb2312");
//			request.setAttribute("result", "�޲�ѯȨ��");
//			request.getRequestDispatcher("desk.jsp").forward(request,
//					response);
//			
		}else{
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglXFInsert.jsp").forward(request,
					response);
		}
	}
	/*
	 * ���·��ļ������б�
	 * 
	 */
	protected void getWjglXFList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ѯ�·��б�
			ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxwjlx = request.getParameter("cxwjlx");
			String cxwjh = request.getParameter("cxwjh");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)||"".equals(srbt)||srbt==null){
				//ͨ�������ѯ
				cxwjmc = "";
				cxwjlx = "";
				cxwjh = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " where fqrID ='"+UserInfo.getUsername()+"' and fszt='1'";
			}else{
				srbt= srbt + " and fszt='1' ";
			}
			countAll = wjglDao.queryWjglXFListByBtCount(srbt);
			WjglxfList = wjglDao.queryWjglXFListByBt(srbt, begin, pageSize);
			request.setAttribute("WjglxfList", WjglxfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxwjlx", cxwjlx);
			request.setAttribute("cxwjh", cxwjh);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglXFList.jsp").forward(request,
					response);
		}
	}
	/*
	 * ��ظ��ļ������б�
	 * 
	 */
	protected void getWjglJSList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxhfzt = request.getParameter("cxhfzt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//��Ϊ1����Ӳ˵����룻��Ϊ2������ҳ����
			String flag = request.getParameter("flag");
			if("1".equals(flag)||"".equals(srbt)||srbt==null){
				//ͨ�������ѯ
				cxwjmc = "";
				cxhfzt = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt="";
			}
			if("2".equals(flag)){
				//ͨ�������ѯ
				cxwjmc = "";
				cxhfzt = "δ�ظ�";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " and hfzt ='δ�ظ�'";
			}
			//�Ƿ���Ҫ�ظ����Ȳ�ѯ�ظ��б�
			ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
			wjglhfList = wjglDao.queryWjglhfByHfrID(UserInfo.getUsername(),srbt, begin, pageSize);
			countAll = wjglDao.queryWjglhfByHfrIDCount(UserInfo.getUsername(),srbt);
			request.setAttribute("wjglhfList", wjglhfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxhfzt", cxhfzt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglJSList.jsp").forward(request,
					response);
		}
	}
	/*
	 * ���·��ļ�����ͳ���ۺ��б�
	 * 
	 */
	protected void getWjglTJList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			WjglDao wjglDao = new WjglDao();
			//��ѯ��λ�б�
			ArrayList<WjglTJ> CompanyList = new ArrayList<WjglTJ>();
			CompanyList = wjglDao.QueryCompanyNameById("node_zzxx",0);
			//��ѯ�·��б�
			ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 50;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			//ͨ�������ѯ
			String srbt = request.getParameter("srbt");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			if("".equals(srbt)){
				cxssrq1="";
				cxssrq2="";
			}
			
			countAll = wjglDao.queryWjglTJListByBtCount("�ֻ���",srbt);
			WjglxfList = wjglDao.queryWjglTJListByBt("�ֻ���",srbt, begin, pageSize);
			//�Ե�λΪ׼�������
			ArrayList<String> NewList = new ArrayList<String>();
			String NewStr = "";
			for(int i=0; i<WjglxfList.size(); i++){
				NewStr = "";
				//�ļ�ID���ļ�����
				int wjid = WjglxfList.get(i).getId();
				NewStr = wjid+";"+ WjglxfList.get(i).getWjmc();
				//�·���λ
				for(int j=0; j<CompanyList.size(); j++){
					String dwmc = CompanyList.get(j).getName();
					int counthf = CompanyList.get(j).getCounthf();
					int countcs = CompanyList.get(j).getCountcs();
					//��ѯ�ظ��ļ�
					Wjglhf wjglhf = wjglDao.queryWjglhfByIDMC(wjid,dwmc);
					if(wjglhf.getId()!=0){
						NewStr = NewStr+";"+wjglhf.getHfzt()+";"+wjglhf.getSfcs();
						if("δ�ظ�".equals(wjglhf.getHfzt())){
							CompanyList.get(j).setCounthf(counthf+1);
						}
						if("��ʱ".equals(wjglhf.getSfcs())){
							CompanyList.get(j).setCountcs(countcs+1);
						}
						
					}else{
						NewStr = NewStr+";"+"&nbsp"+";"+"&nbsp";
					}
				}
				//�ظ����
				NewList.add(NewStr);
			}
			request.setAttribute("NewList", NewList);
			request.setAttribute("CompanyList", CompanyList);
			request.setAttribute("result", result);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglTJList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �·��ļ���������
	 * 
	 */
	protected void insertWjglXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String wjmc = request.getParameter("wjmc");//�ļ�����
			String wjlx = request.getParameter("wjlx");//�ļ�����
			String fwdw = UserInfo.getCompany();//���ĵ�λ
			String wjh = request.getParameter("wjh");//���ĺ�
			String hfqx = request.getParameter("hfqx");//�ظ�����
			String ry = request.getParameter("ry");//�ظ���Ա
			String ryID = request.getParameter("ryID");//�ظ���ԱIDlist
			String fqr = UserInfo.getName();//������
			String fqrID = UserInfo.getUsername();//������ID
			String gzyq = request.getParameter("gzyq");//����Ҫ��
			String FileUrl = request.getParameter("FileUrl");//������ַ
			String fszt = request.getParameter("fszt");//�Ƿ��͡���1�����ͣ�2������ݸ壻
			String SMSFlag = request.getParameter("SMSFlag");//�Ƿ��Ͷ��ţ�0�����ͣ�1�������ͣ�
			//�����·���¼
			WjglDao wjglDao = new WjglDao();
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Wjglxf wjglxf = new Wjglxf();
			wjglxf.setWjmc(wjmc);
			wjglxf.setFqsj(data1);//����ʱ��
			wjglxf.setWjlx(wjlx);
			wjglxf.setFwdw(fwdw);
			wjglxf.setWjh(wjh);
			wjglxf.setFqr(fqr);
			wjglxf.setFqrID(fqrID);
			wjglxf.setGzyq(gzyq);
			wjglxf.setFileUrl(FileUrl);
			wjglxf.setFszt(fszt);
			wjglxf.setHfry(ry);
			 
			int wjglxfID = 0;
			//��Ҫ�ظ�
			if(!"".equals(hfqx)&&hfqx!=null){
				//׼���·�
				String[] hfryList = ryID.split(",");
				//ȥ���ظ���
				List list = Arrays.asList(hfryList);
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
				wjglxf.setHfryID(hfidNewList);
				wjglxf.setWhfryID(hfidNewList);
				wjglxf.setHfqx(DateFormat(hfqx));
				wjglxfID = wjglDao.insertWjglxf1(wjglxf);
				//��������ʱ������Ҫ����
				if("1".equals(fszt)){
					Iterator<String> hfid1=hfidList.iterator();
					ArrayList<Wjglhf> WjglhfList = new ArrayList<Wjglhf>();
					while(hfid1.hasNext()){
						ContentZzxx Zzxx = new ContentZzxx();
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						Zzxx = contentZzxxDao.queryZzxxByUserName(hfid1.next());
						//��ʼ�·�
						Wjglhf wjglhf = new Wjglhf();
						wjglhf.setWjmc(wjmc);
						wjglhf.setWjID(wjglxfID);
						wjglhf.setHfr(Zzxx.getName());
						wjglhf.setHfrID(Zzxx.getUsername());
						wjglhf.setSfcs("δ��ʱ");
						wjglhf.setHfzt("δ�ظ�");
						wjglhf.setCyzt("δ����");
						wjglhf.setHfqx(DateFormat(hfqx));
						wjglhf.setCompany(Zzxx.getCompany());
						wjglhf.setCompanyID(Zzxx.getCompanyID());
						WjglhfList.add(wjglhf);
						//�ж��Ƿ񷢶���
						if("0".equals(SMSFlag)){
							//���Ͷ���
							String nr = fqr+"������һ������"+wjglxf.getHfqx()+"֮ǰ�ظ����ļ����뼰ʱ�鿴";
							messagePlatForm.submitShortMessage(Zzxx.getPhone(),nr);
						}
						
					}
					wjglDao.insertWjglhf(WjglhfList);
				}
			}else{
				wjglxfID = wjglDao.insertWjglxf2(wjglxf);
			}
			if("1".equals(fszt)){
				request.getRequestDispatcher("WjglServlet?action=getWjglXFList&result='�����ɹ�'&srbt=").forward(request,
						response);
			}else{
				request.getRequestDispatcher("WjglServlet?action=getWjglCGList&result='�����ɹ�'&srbt=").forward(request,
						response);
			}
		}
	}
	/*
	 *  �·������б����鿴
	 * 
	 */
	protected void showWjglXF(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String flag = request.getParameter("flag");
			if(flag==null)flag="0";
			Wjglxf wjglxf = new Wjglxf();
			WjglDao wjglDao = new WjglDao();
			if(!"".equals(id)&&id!=null){
				wjglxf = wjglDao.queryWjglXFByID(Integer.parseInt(id));
			}
			//���лظ����
			ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
			if("1".equals(flag)){
				wjglhfList = wjglDao.queryWjglhfByWJID(Integer.parseInt(id));
			}
			//�ظ�
			Wjglhf wjglhf = new Wjglhf();
			if("2".equals(flag)||"3".equals(flag)){
				wjglhf = wjglDao.queryWjglhfByWJIDHF(Integer.parseInt(id), UserInfo.getUsername());
			}
			//�ѻظ�
			request.setAttribute("wjglhfList", wjglhfList);
			request.setAttribute("wjglhf", wjglhf);
			request.setAttribute("wjglxf", wjglxf);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglXFShow.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  �·������б���ɾ��
	 * 
	 */
	protected void deleteWjglXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String FileUrl = request.getParameter("FileUrl");
		String address = request.getParameter("address");
		WjglDao wjglDao = new WjglDao();
		if(!"".equals(id)&&id!=null){
			wjglDao.DeleteWjglXFById(Integer.parseInt(id));
			//�ظ�����Ҳɾ��
			ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
			wjglhfList = wjglDao.queryWjglhfByWJID(Integer.parseInt(id));
			for(int i=0;i<wjglhfList.size();i++){
				wjglDao.DeleteWjglHFById(wjglhfList.get(i).getId());
				if(!"".equals(wjglhfList.get(i).getFileUrl())&&(wjglhfList.get(i).getFileUrl())!=null){
					String str = wjglhfList.get(i).getFileUrl();
					String[] strList =  str.split(";");
					for(int k=0;k<strList.length; k++){
						//ɾ�������ļ�
						String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjglhf/"+strList[k];
						File file = new File(FullFilePath);  
						// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
						if (file.isFile() && file.exists()) {  
							file.delete();  
						}
					}
				}
			}
			
		}
		if(!"".equals(FileUrl)&&FileUrl!=null){
			String[] FileUrlStr = FileUrl.split(";");
			//ɾ�������ļ�
			for(int j=0;j<FileUrlStr.length; j++){
				String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjgl/"+FileUrlStr[j];
				File file = new File(FullFilePath);  
				// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
				if (file.isFile() && file.exists()) {  
					file.delete();  
				}
			}
		}
		
		if("1".equals(address)){
			RequestDispatcher rd = request.getRequestDispatcher("WjglServlet?action=getWjglList&flag=1");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("WjglServlet?action=getWjglXFList&flag=1");
			rd.forward(request, response);
		}
	}

	
	/*
	 * �����ļ����·���
	 * 
	 */
	protected void downLoadXF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
//		String srbt = request.getParameter("srbt");
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjgl/"+URL;
		File file = new File(FullFilePath);  
		if (!"".equals(URL)&&URL!=null&&file.exists()) {
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
        	response.setContentType("text/html;charset=gb2312");
    		PrintWriter out = response.getWriter(); 
    		out.println("<script>alert('�ļ���ʧЧ���޷����أ�');</script>");
        }
	}
	/*
	 * �����ļ����ظ���
	 * 
	 */
	protected void downLoadHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String FullFilePath = request.getRealPath("/UserFile") + "/StatisticsFile/wjglhf/"+URL;
		File file = new File(FullFilePath);  
		if (!"".equals(URL)&&URL!=null&&file.exists()) {
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
        	response.setContentType("text/html;charset=gb2312");
    		PrintWriter out = response.getWriter(); 
    		out.println("<script>alert('�ļ���ʧЧ���޷����أ�');</script>");
        }
	}

	/*
	 *  ����ID�鿴����ظ�����
	 * 
	 */
	protected void showWjglHF(HttpServletRequest request,
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
			String hfid = request.getParameter("hfid");
			WjglDao wjglDao = new WjglDao();
			//�ظ�
			Wjglhf wjglhf = new Wjglhf();
			if(!"".equals(hfid)&&hfid!=null){
				wjglhf = wjglDao.queryWjglhfByHFID(Integer.parseInt(hfid));
			}
			request.setAttribute("wjglhf", wjglhf);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglHFShow.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �ύ�ظ�
	 * 
	 */
	protected void saveWjglHF(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String FileUrl = request.getParameter("FileUrl");
			String hfnr = request.getParameter("hfnr");
			String id = request.getParameter("id");
			String wjid = request.getParameter("wjid");
			String whfid = request.getParameter("whfid");
			String hfqx = request.getParameter("hfqx");
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Date  data1=new java.sql.Date(date.getTime());
			//�޸�
			WjglDao wjglDao = new WjglDao();
			Wjglhf wjglhf = new Wjglhf();
			wjglhf.setId(Integer.parseInt(id));
			wjglhf.setFileUrl(FileUrl);
			wjglhf.setHfnr(hfnr);
			wjglhf.setHfsj(data1);
			wjglhf.setHfzt("�ѻظ�");
	//		//�Ƚ�����ʱ��
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
			String now = format.format(date);
			wjglhf.setSfcs(DateCompare(hfqx,now));
			wjglDao.updateWjglHF(wjglhf);
			String[] whfidList = whfid.split(",");
			String whfidNew = "";
			for(int i=0; i<whfidList.length; i++){
				if(!(UserInfo.getUsername()).equals(whfidList[i])){
					if("".equals(whfidNew)){
						whfidNew=whfidList[i];
					}else{
						whfidNew=whfidNew+","+whfidList[i];
					}
				}
			}
			//����δ�ظ�ID�ֶ�
			wjglDao.updateWjglWhfID(wjid,whfidNew);
			
			request.getRequestDispatcher("WjglServlet?action=getWjglJSList&flag=1").forward(request,
					response);
		}
	}

	/*
	 * ���˲ݸ��ļ������б�
	 * 
	 */
	protected void getWjglCGList(HttpServletRequest request,
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
			
		}else if(UserPer.getWjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//��ѯ�·��б�
			ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			WjglDao wjglDao = new WjglDao();
			String srbt = request.getParameter("srbt");
			String cxwjmc = request.getParameter("cxwjmc");
			String cxwjlx = request.getParameter("cxwjlx");
			String cxwjh = request.getParameter("cxwjh");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)||"".equals(srbt)||srbt==null){
				//ͨ�������ѯ
				cxwjmc = "";
				cxwjlx = "";
				cxwjh = "";
				cxssrq1 = "";
				cxssrq2 = "";
				srbt= " where fqrID ='"+UserInfo.getUsername()+"' and fszt='2'";
			}else{
				srbt= srbt + " and fszt='2' ";
			}
			countAll = wjglDao.queryWjglXFListByBtCount(srbt);
			WjglxfList = wjglDao.queryWjglXFListByBt(srbt, begin, pageSize);
			request.setAttribute("WjglxfList", WjglxfList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxwjmc", cxwjmc);
			request.setAttribute("cxwjlx", cxwjlx);
			request.setAttribute("cxwjh", cxwjh);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglCGList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  ���˲ݸ��ļ������༭
	 * 
	 */
	protected void editWjglCG(HttpServletRequest request,
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
			String id = request.getParameter("id");
			Wjglxf wjglxf = new Wjglxf();
			WjglDao wjglDao = new WjglDao();
			if(!"".equals(id)&&id!=null){
				wjglxf = wjglDao.queryWjglXFByID(Integer.parseInt(id));
			}
			request.setAttribute("wjglxf", wjglxf);
			request.getRequestDispatcher("/jsp/StatisticsFile/wjglCGInsert.jsp").forward(request,
					response);
		}
	}

	/*
	 *  ���˲ݸ��ļ���������
	 * 
	 */
	protected void insertWjglCG(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			String id = request.getParameter("id");//�ļ�ID
			String wjmc = request.getParameter("wjmc");//�ļ�����
			String wjlx = request.getParameter("wjlx");//�ļ�����
			String fwdw = UserInfo.getCompany();//���ĵ�λ
			String wjh = request.getParameter("wjh");//���ĺ�
			String hfqx = request.getParameter("hfqx");//�ظ�����
			String ry = request.getParameter("ry");//�ظ���Ա
			String ryID = request.getParameter("ryID");//�ظ���ԱIDlist
			String fqr = UserInfo.getName();//������
			String fqrID = UserInfo.getUsername();//������ID
			String gzyq = request.getParameter("gzyq");//����Ҫ��
			String FileUrl = request.getParameter("FileUrl");//������ַ
			String fszt = request.getParameter("fszt");//�Ƿ��͡���1�����ͣ�2������ݸ壻
			String SMSFlag = request.getParameter("SMSFlag");//�Ƿ��Ͷ��ţ�0�����ͣ�1�������ͣ�
			//�����·���¼
			WjglDao wjglDao = new WjglDao();
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Wjglxf wjglxf = new Wjglxf();
			wjglxf.setId(Integer.parseInt(id));
			wjglxf.setWjmc(wjmc);
			wjglxf.setFqsj(data1);//����ʱ��
			wjglxf.setWjlx(wjlx);
			wjglxf.setFwdw(fwdw);
			wjglxf.setWjh(wjh);
			wjglxf.setFqr(fqr);
			wjglxf.setFqrID(fqrID);
			wjglxf.setGzyq(gzyq);
			wjglxf.setFileUrl(FileUrl);
			wjglxf.setFszt(fszt);
			wjglxf.setHfry(ry);
			 
			//��Ҫ�ظ�
			if(!"".equals(hfqx)&&hfqx!=null){
				//׼���·�
				String[] hfryList = ryID.split(",");
				//ȥ���ظ���
				List list = Arrays.asList(hfryList);
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
				wjglxf.setHfryID(hfidNewList);
				wjglxf.setWhfryID(hfidNewList);
				wjglxf.setHfqx(DateFormat(hfqx));
				wjglDao.updateWjglxf1(wjglxf);
				//��������ʱ������Ҫ����
				if("1".equals(fszt)){
					Iterator<String> hfid1=hfidList.iterator();
					ArrayList<Wjglhf> WjglhfList = new ArrayList<Wjglhf>();
					while(hfid1.hasNext()){
						ContentZzxx Zzxx = new ContentZzxx();
						ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
						Zzxx = contentZzxxDao.queryZzxxByUserName(hfid1.next());
						//��ʼ�·�
						Wjglhf wjglhf = new Wjglhf();
						wjglhf.setWjmc(wjmc);
						wjglhf.setWjID(Integer.parseInt(id));
						wjglhf.setHfr(Zzxx.getName());
						wjglhf.setHfrID(Zzxx.getUsername());
						wjglhf.setSfcs("δ��ʱ");
						wjglhf.setHfzt("δ�ظ�");
						wjglhf.setCyzt("δ����");
						wjglhf.setHfqx(DateFormat(hfqx));
						wjglhf.setCompany(Zzxx.getCompany());
						wjglhf.setCompanyID(Zzxx.getCompanyID());
						WjglhfList.add(wjglhf);
						//�ж��Ƿ񷢶���
						if("0".equals(SMSFlag)){
							//���Ͷ���
							String nr = fqr+"������һ������"+wjglxf.getHfqx()+"֮ǰ�ظ����ļ����뼰ʱ�鿴";
							messagePlatForm.submitShortMessage(Zzxx.getPhone(),nr);
						}
						
					}
					wjglDao.insertWjglhf(WjglhfList);
				}
			}else{
				wjglDao.updateWjglxf2(wjglxf);
			}
			if("1".equals(fszt)){
				request.getRequestDispatcher("WjglServlet?action=getWjglXFList&result='�����ɹ�'&srbt=").forward(request,
						response);
			}else{
				request.getRequestDispatcher("WjglServlet?action=getWjglCGList&result='�����ɹ�'&srbt=").forward(request,
						response);
			}
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
