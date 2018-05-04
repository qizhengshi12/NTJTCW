package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.safety.dao.BbdzDao;
import com.safety.dao.BbsbSjbbDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MenuDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.Bbdzmb;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.SjbbJtyssjdytj;
import com.safety.entity.SjbbJtyssjdytjZxm;
import com.safety.entity.SjbbAll;
import com.safety.entity.SjbbLssfzc;
import com.safety.entity.SjbbLssfzcHzb;
import com.safety.entity.SjbbLssfzcZxm;
import com.safety.entity.SjbbLssfzcZxmHzb;

public class BbsbSjbbServlet  extends HttpServlet{
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
		if("getSjbb".equals(action)){//��Ʊ���
			getSjbb(request,response);
		}else if("resetMenu".equals(action)){//���ò˵�
			resetMenu(request,response);
		}else if("getSjbbAll".equals(action)){//��ͨ���ͳ�ơ����б�1
			getSjbbAll(request,response);
		}else if("SjbbAllEdit".equals(action)){//��ͨ���ͳ�ơ����������޸�
			SjbbAllEdit(request,response);
		}else if("SjbbAllCount".equals(action)){//��ͨ���ͳ�ơ����ϲ�ͳ��
			SjbbAllCount(request,response);
		}else if("SjbbAllSave".equals(action)){//��ͨ���ͳ�ơ�������ҳ��
			SjbbAllSave(request,response);
		}else if("SjbbAllShow".equals(action)){//��ͨ���ͳ�ơ����鿴
			SjbbAllShow(request,response);
		}else if("SjbbAllDelete".equals(action)){//��ͨ���ͳ�ơ���ɾ��
			SjbbAllDelete(request,response);
		}else if("SjbbAllReturn".equals(action)){//��ͨ���ͳ�ơ�������
			SjbbAllReturn(request,response);
		}else if("getSjbbAllExcel".equals(action)){//��ͨ���ͳ�ơ�������EXCEL
			getSjbbAllExcel(request,response);
		}else if("readSjbbAllExcel".equals(action)){//��ͨ���ͳ�ơ�������EXCEL
			readSjbbAllExcel(request,response);
		}else if("getJtyssjdytj".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ����б�6
			getJtyssjdytj(request,response);
		}else if("JtyssjdytjEdit".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ����������޸�
			JtyssjdytjEdit(request,response);
		}else if("JtyssjdytjSave".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ�������ҳ��
			JtyssjdytjSave(request,response);
		}else if("JtyssjdytjShow".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ����鿴
			JtyssjdytjShow(request,response);
		}else if("JtyssjdytjDelete".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ���ɾ��
			JtyssjdytjDelete(request,response);
		}else if("getSjbbJtyssjdytjExcel".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ�������EXCEL
			getSjbbJtyssjdytjExcel(request,response);
		}else if("readJtyssjdytjExcel".equals(action)){//��ͨ���䲿����ƶ���ͳ�Ʊ�������EXCEL
//			readJtyssjdytjExcel(request,response);
		}else if("getLssfzczcb".equals(action)){//�м����أ���������ҵ��λ����ʵ�շ����߼����Բ�����б�
			getLssfzczcb(request,response);
		}else if("lssfzczcbEdit".equals(action)){//��ʵ�շ����߼����Բ�����������޸�
			lssfzczcbEdit(request,response);
		}else if("lssfzczcbSave".equals(action)){//��ʵ�շ����߼����Բ��������ҳ��
			lssfzczcbSave(request,response);
		}else if("lssfzczcbReturn".equals(action)){//��ʵ�շ����߼����Բ��������
			lssfzczcbReturn(request,response);
		}else if("lssfzczcbShow".equals(action)){//��ʵ�շ����߼����Բ�����鿴
			lssfzczcbShow(request,response);
		}else if("lssfzczcbDelete".equals(action)){//��ʵ�շ����߼����Բ����ɾ��
			lssfzczcbDelete(request,response);
		}else if("getSjbbLssfzczcbExcel".equals(action)){//��ʵ�շ����߼����Բ��������EXCEL
			getSjbbLssfzczcbExcel(request,response);
		}else if("readLssfzczcbExcel".equals(action)){//��ʵ�շ����߼����Բ��������EXCEL
			readLssfzczcbExcel(request,response);
		}else if("getLssfzczcbHzb".equals(action)){//��ʵ�շ����߼����Բ�����б�
			getLssfzczcbHzb(request,response);
		}else if("LssfzczcbHzbSave".equals(action)){//��ʵ�շ����߼����Բ����ܱ�����������
			LssfzczcbHzbSave(request,response);
		}else if("LssfzczcbHzbShow".equals(action)){//��ʵ�շ����߼����Բ����ܱ����鿴
			LssfzczcbHzbShow(request,response);
		}else if("LssfzczcbHzbDelete".equals(action)){//��ʵ�շ����߼����Բ����ܱ���ɾ��
			LssfzczcbHzbDelete(request,response);
		}else if("getSjbbLssfzczcbHzbExcel".equals(action)){//��ʵ�շ����߼����Բ����ܱ�������EXCEL
			getSjbbLssfzczcbHzbExcel(request,response);
		}
	}
	/*
	 *  ��Ʊ���
	 * 
	 */
	protected void getSjbb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getSjbb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfoName("node_sjbb");

			//��ѯ�Զ�����񱨱�ģ��
			ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
			BbdzDao bbdzDao = new BbdzDao();
			BbdzmbList = bbdzDao.queryBbdzmbByLx("sjbb");
			
			request.setAttribute("BbdzmbList", BbdzmbList);
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/getSjbb.jsp").forward(request,
					response);
		}
	}

	/*
	 *  ���ò˵�
	 * 
	 */
	protected void resetMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			String result= "���óɹ�";

			MenuDao menuDao = new MenuDao();
			menuDao.DeleteMenuName("node_sjbb");
			menuDao.ResetZzxxMenu("node_sjbb",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("BbsbSjbbServlet?action=getSjbb&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  ��ͨ���ͳ�ơ����б�
	 * 
	 */
	protected void getSjbbAll(HttpServletRequest request,
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
			//��ѯ�б�
			ArrayList<SjbbAll> SjbbAllList = new ArrayList<SjbbAll>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 15;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//ͨ�������ѯ
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			//��Ϊ2���ֻ�����Ƹ����˴Ӳ˵�����
			if("2".equals(flag)){
				//ͨ�������ѯ
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where  tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"') ";
				cxtj = "1";
			}
			countAll = bbsbSjbbDao.querySjbbAllListByBtCount(srbt);
			SjbbAllList = bbsbSjbbDao.querySjbbAllListByBt(srbt, begin, pageSize);
			request.setAttribute("SjbbAllList", SjbbAllList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbAllList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void SjbbAllEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String id = request.getParameter("id");
			String result = request.getParameter("result");
			SjbbAll sjbbAll = new SjbbAll();
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			if(!"".equals(id)&&id!=null){
				sjbbAll = bbsbSjbbDao.querySjbbAllByID(Integer.parseInt(id));
			}
			request.setAttribute("sjbbAll", sjbbAll);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbAllEdit.jsp").forward(request,
					response);
		}
	}
	

	/*
	 *  ����ϲ�ͳ��ҳ��
	 * 
	 */
	protected void SjbbAllCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Date  data1=new java.sql.Date(date.getTime());
			//�ϲ�ͳ��
			String IDList = request.getParameter("IDList");
			String result = request.getParameter("result");
			
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();

			ArrayList<SjbbAll> sjbbAllList = new ArrayList<SjbbAll>();
			sjbbAllList= bbsbSjbbDao.querySjbbAllByIDList(IDList);
			SjbbAll sjbbAll = new SjbbAll();
			sjbbAll = sjbbAllList.get(0);
			sjbbAll.setId(0);
			sjbbAll.setTjfzr("");
			sjbbAll.setDwfzr("");
			sjbbAll.setCzrphone(UserInfo.getWorkphone());
			sjbbAll.setSj(data1);
			sjbbAll.setBt("������ͳ�Ʊ����ϲ���");
			for(int i=1;i<sjbbAllList.size();i++){
				sjbbAll.setZb1(CountNumber(sjbbAll.getZb1(),sjbbAllList.get(i).getZb1()));
				sjbbAll.setZb2(CountNumber(sjbbAll.getZb2(),sjbbAllList.get(i).getZb2()));
				sjbbAll.setZb3(CountNumber(sjbbAll.getZb3(),sjbbAllList.get(i).getZb3()));
				sjbbAll.setZb4(CountNumber(sjbbAll.getZb4(),sjbbAllList.get(i).getZb4()));
				sjbbAll.setZb5(CountNumber(sjbbAll.getZb5(),sjbbAllList.get(i).getZb5()));
				sjbbAll.setZb6(CountNumber(sjbbAll.getZb6(),sjbbAllList.get(i).getZb6()));
				sjbbAll.setZb7(CountNumber(sjbbAll.getZb7(),sjbbAllList.get(i).getZb7()));
				sjbbAll.setZb8(CountNumber(sjbbAll.getZb8(),sjbbAllList.get(i).getZb8()));
				sjbbAll.setZb9(CountNumber(sjbbAll.getZb9(),sjbbAllList.get(i).getZb9()));
				sjbbAll.setZb10(CountNumber(sjbbAll.getZb10(),sjbbAllList.get(i).getZb10()));
				sjbbAll.setZb11(CountNumber(sjbbAll.getZb11(),sjbbAllList.get(i).getZb11()));
				sjbbAll.setZb12(CountNumber(sjbbAll.getZb12(),sjbbAllList.get(i).getZb12()));
				sjbbAll.setZb13(CountNumber(sjbbAll.getZb13(),sjbbAllList.get(i).getZb13()));
				sjbbAll.setZb14(CountNumber(sjbbAll.getZb14(),sjbbAllList.get(i).getZb14()));
				sjbbAll.setZb15(CountNumber(sjbbAll.getZb15(),sjbbAllList.get(i).getZb15()));
				sjbbAll.setZb16(CountNumber(sjbbAll.getZb16(),sjbbAllList.get(i).getZb16()));
				sjbbAll.setZb17(CountNumber(sjbbAll.getZb17(),sjbbAllList.get(i).getZb17()));
				sjbbAll.setZb18(CountNumber(sjbbAll.getZb18(),sjbbAllList.get(i).getZb18()));
				sjbbAll.setZb19(CountNumber(sjbbAll.getZb19(),sjbbAllList.get(i).getZb19()));
				sjbbAll.setZb20(CountNumber(sjbbAll.getZb20(),sjbbAllList.get(i).getZb20()));
				sjbbAll.setZb21(CountNumber(sjbbAll.getZb21(),sjbbAllList.get(i).getZb21()));
				sjbbAll.setZb22(CountNumber(sjbbAll.getZb22(),sjbbAllList.get(i).getZb22()));
				sjbbAll.setZb23(CountNumber(sjbbAll.getZb23(),sjbbAllList.get(i).getZb23()));
				sjbbAll.setZb24(CountNumber(sjbbAll.getZb24(),sjbbAllList.get(i).getZb24()));
				sjbbAll.setZbs1(CountNumber(sjbbAll.getZbs1(),sjbbAllList.get(i).getZbs1()));
				sjbbAll.setZbs2(CountNumber(sjbbAll.getZbs2(),sjbbAllList.get(i).getZbs2()));
				sjbbAll.setZbs3(CountNumber(sjbbAll.getZbs3(),sjbbAllList.get(i).getZbs3()));
				sjbbAll.setZbs4(CountNumber(sjbbAll.getZbs4(),sjbbAllList.get(i).getZbs4()));
				sjbbAll.setZbs5(CountNumber(sjbbAll.getZbs5(),sjbbAllList.get(i).getZbs5()));
				sjbbAll.setZbs6(CountNumber(sjbbAll.getZbs6(),sjbbAllList.get(i).getZbs6()));
				sjbbAll.setZbs7(CountNumber(sjbbAll.getZbs7(),sjbbAllList.get(i).getZbs7()));
				sjbbAll.setZbs8(CountNumber(sjbbAll.getZbs8(),sjbbAllList.get(i).getZbs8()));
				sjbbAll.setZbs9(CountNumber(sjbbAll.getZbs9(),sjbbAllList.get(i).getZbs9()));
				sjbbAll.setZbs10(CountNumber(sjbbAll.getZbs10(),sjbbAllList.get(i).getZbs10()));
				sjbbAll.setZbs11(CountNumber(sjbbAll.getZbs11(),sjbbAllList.get(i).getZbs11()));
				sjbbAll.setZbs12(CountNumber(sjbbAll.getZbs12(),sjbbAllList.get(i).getZbs12()));
				sjbbAll.setZbs13(CountNumber(sjbbAll.getZbs13(),sjbbAllList.get(i).getZbs13()));
				sjbbAll.setZbs14(CountNumber(sjbbAll.getZbs14(),sjbbAllList.get(i).getZbs14()));
				sjbbAll.setZbs15(CountNumber(sjbbAll.getZbs15(),sjbbAllList.get(i).getZbs15()));
				sjbbAll.setZbs16(CountNumber(sjbbAll.getZbs16(),sjbbAllList.get(i).getZbs16()));
				sjbbAll.setZbs17(CountNumber(sjbbAll.getZbs17(),sjbbAllList.get(i).getZbs17()));
				sjbbAll.setZbs18(CountNumber(sjbbAll.getZbs18(),sjbbAllList.get(i).getZbs18()));
				sjbbAll.setZbt1(CountNumber(sjbbAll.getZbt1(),sjbbAllList.get(i).getZbt1()));
				sjbbAll.setZbt2(CountNumber(sjbbAll.getZbt2(),sjbbAllList.get(i).getZbt2()));
				sjbbAll.setZbt3(CountNumber(sjbbAll.getZbt3(),sjbbAllList.get(i).getZbt3()));
				sjbbAll.setZbt4(CountNumber(sjbbAll.getZbt4(),sjbbAllList.get(i).getZbt4()));
				sjbbAll.setZbt5(CountNumber(sjbbAll.getZbt5(),sjbbAllList.get(i).getZbt5()));
				sjbbAll.setZbt6(CountNumber(sjbbAll.getZbt6(),sjbbAllList.get(i).getZbt6()));
				sjbbAll.setZbt7(CountNumber(sjbbAll.getZbt7(),sjbbAllList.get(i).getZbt7()));
				sjbbAll.setZbt8(CountNumber(sjbbAll.getZbt8(),sjbbAllList.get(i).getZbt8()));
				sjbbAll.setZbt9(CountNumber(sjbbAll.getZbt9(),sjbbAllList.get(i).getZbt9()));
				sjbbAll.setZbt10(CountNumber(sjbbAll.getZbt10(),sjbbAllList.get(i).getZbt10()));
				sjbbAll.setZbt11(CountNumber(sjbbAll.getZbt11(),sjbbAllList.get(i).getZbt11()));
				sjbbAll.setZbt12(CountNumber(sjbbAll.getZbt12(),sjbbAllList.get(i).getZbt12()));
				sjbbAll.setZbt13(CountNumber(sjbbAll.getZbt13(),sjbbAllList.get(i).getZbt13()));
				sjbbAll.setZbt14(CountNumber(sjbbAll.getZbt14(),sjbbAllList.get(i).getZbt14()));
				sjbbAll.setZbt15(CountNumber(sjbbAll.getZbt15(),sjbbAllList.get(i).getZbt15()));
				sjbbAll.setZbt16(CountNumber(sjbbAll.getZbt16(),sjbbAllList.get(i).getZbt16()));
				sjbbAll.setZbt17(CountNumber(sjbbAll.getZbt17(),sjbbAllList.get(i).getZbt17()));
				sjbbAll.setZbt18(CountNumber(sjbbAll.getZbt18(),sjbbAllList.get(i).getZbt18()));
				sjbbAll.setZbt19(CountNumber(sjbbAll.getZbt19(),sjbbAllList.get(i).getZbt19()));
				sjbbAll.setZbt20(CountNumber(sjbbAll.getZbt20(),sjbbAllList.get(i).getZbt20()));
				sjbbAll.setZbt21(CountNumber(sjbbAll.getZbt21(),sjbbAllList.get(i).getZbt21()));
				sjbbAll.setZbt22(CountNumber(sjbbAll.getZbt22(),sjbbAllList.get(i).getZbt22()));
				sjbbAll.setZbt23(CountNumber(sjbbAll.getZbt23(),sjbbAllList.get(i).getZbt23()));
				sjbbAll.setZbt24(CountNumber(sjbbAll.getZbt24(),sjbbAllList.get(i).getZbt24()));
				sjbbAll.setZbt25(CountNumber(sjbbAll.getZbt25(),sjbbAllList.get(i).getZbt25()));
				sjbbAll.setZbf1(CountNumber(sjbbAll.getZbf1(),sjbbAllList.get(i).getZbf1()));
				sjbbAll.setZbf2(CountNumber(sjbbAll.getZbf2(),sjbbAllList.get(i).getZbf2()));
				sjbbAll.setZbf3(CountNumber(sjbbAll.getZbf3(),sjbbAllList.get(i).getZbf3()));
				sjbbAll.setZbf4(CountNumber(sjbbAll.getZbf4(),sjbbAllList.get(i).getZbf4()));
				sjbbAll.setZbf5(CountNumber(sjbbAll.getZbf5(),sjbbAllList.get(i).getZbf5()));
				sjbbAll.setZbf6(CountNumber(sjbbAll.getZbf6(),sjbbAllList.get(i).getZbf6()));
				sjbbAll.setZbf7(CountNumber(sjbbAll.getZbf7(),sjbbAllList.get(i).getZbf7()));
				sjbbAll.setZbf8(CountNumber(sjbbAll.getZbf8(),sjbbAllList.get(i).getZbf8()));
				sjbbAll.setZbf9(CountNumber(sjbbAll.getZbf9(),sjbbAllList.get(i).getZbf9()));
				sjbbAll.setZbf10(CountNumber(sjbbAll.getZbf10(),sjbbAllList.get(i).getZbf10()));
				sjbbAll.setZbf11(CountNumber(sjbbAll.getZbf11(),sjbbAllList.get(i).getZbf11()));
				sjbbAll.setZbf12(CountNumber(sjbbAll.getZbf12(),sjbbAllList.get(i).getZbf12()));
				sjbbAll.setZbf13(CountNumber(sjbbAll.getZbf13(),sjbbAllList.get(i).getZbf13()));
				sjbbAll.setZbf14(CountNumber(sjbbAll.getZbf14(),sjbbAllList.get(i).getZbf14()));
				sjbbAll.setZbf15(CountNumber(sjbbAll.getZbf15(),sjbbAllList.get(i).getZbf15()));
				sjbbAll.setZbf16(CountNumber(sjbbAll.getZbf16(),sjbbAllList.get(i).getZbf16()));
				sjbbAll.setZbf17(CountNumber(sjbbAll.getZbf17(),sjbbAllList.get(i).getZbf17()));
				sjbbAll.setZbf18(CountNumber(sjbbAll.getZbf18(),sjbbAllList.get(i).getZbf18()));
				sjbbAll.setZbf19(CountNumber(sjbbAll.getZbf19(),sjbbAllList.get(i).getZbf19()));
				sjbbAll.setZbf20(CountNumber(sjbbAll.getZbf20(),sjbbAllList.get(i).getZbf20()));
				sjbbAll.setZbf21(CountNumber(sjbbAll.getZbf21(),sjbbAllList.get(i).getZbf21()));
				sjbbAll.setZbf22(CountNumber(sjbbAll.getZbf22(),sjbbAllList.get(i).getZbf22()));
				sjbbAll.setZbf23(CountNumber(sjbbAll.getZbf23(),sjbbAllList.get(i).getZbf23()));
				sjbbAll.setZbf24(CountNumber(sjbbAll.getZbf24(),sjbbAllList.get(i).getZbf24()));
				sjbbAll.setZbf25(CountNumber(sjbbAll.getZbf25(),sjbbAllList.get(i).getZbf25()));
				sjbbAll.setZbf26(CountNumber(sjbbAll.getZbf26(),sjbbAllList.get(i).getZbf26()));
				sjbbAll.setZbf27(CountNumber(sjbbAll.getZbf27(),sjbbAllList.get(i).getZbf27()));
				sjbbAll.setZbf28(CountNumber(sjbbAll.getZbf28(),sjbbAllList.get(i).getZbf28()));
				sjbbAll.setZbf29(CountNumber(sjbbAll.getZbf29(),sjbbAllList.get(i).getZbf29()));
				sjbbAll.setZbf30(CountNumber(sjbbAll.getZbf30(),sjbbAllList.get(i).getZbf30()));
				sjbbAll.setZbf31(CountNumber(sjbbAll.getZbf31(),sjbbAllList.get(i).getZbf31()));
				sjbbAll.setZbf32(CountNumber(sjbbAll.getZbf32(),sjbbAllList.get(i).getZbf32()));
				sjbbAll.setZbf33(CountNumber(sjbbAll.getZbf33(),sjbbAllList.get(i).getZbf33()));
				sjbbAll.setZbv1(CountNumber(sjbbAll.getZbv1(),sjbbAllList.get(i).getZbv1()));
				sjbbAll.setZbv2(CountNumber(sjbbAll.getZbv2(),sjbbAllList.get(i).getZbv2()));
				sjbbAll.setZbv3(CountNumber(sjbbAll.getZbv3(),sjbbAllList.get(i).getZbv3()));
				sjbbAll.setZbv4(CountNumber(sjbbAll.getZbv4(),sjbbAllList.get(i).getZbv4()));
				sjbbAll.setZbv5(CountNumber(sjbbAll.getZbv5(),sjbbAllList.get(i).getZbv5()));
				sjbbAll.setZbv6(CountNumber(sjbbAll.getZbv6(),sjbbAllList.get(i).getZbv6()));
				sjbbAll.setZbv7(CountNumber(sjbbAll.getZbv7(),sjbbAllList.get(i).getZbv7()));
				sjbbAll.setZbv8(CountNumber(sjbbAll.getZbv8(),sjbbAllList.get(i).getZbv8()));
				sjbbAll.setZbv9(CountNumber(sjbbAll.getZbv9(),sjbbAllList.get(i).getZbv9()));
				sjbbAll.setZbv10(CountNumber(sjbbAll.getZbv10(),sjbbAllList.get(i).getZbv10()));
				sjbbAll.setZbv11(CountNumber(sjbbAll.getZbv11(),sjbbAllList.get(i).getZbv11()));
				sjbbAll.setZbv12(CountNumber(sjbbAll.getZbv12(),sjbbAllList.get(i).getZbv12()));
				sjbbAll.setZbv13(CountNumber(sjbbAll.getZbv13(),sjbbAllList.get(i).getZbv13()));
				sjbbAll.setZbv14(CountNumber(sjbbAll.getZbv14(),sjbbAllList.get(i).getZbv14()));
				sjbbAll.setZbv15(CountNumber(sjbbAll.getZbv15(),sjbbAllList.get(i).getZbv15()));
				sjbbAll.setZbv16(CountNumber(sjbbAll.getZbv16(),sjbbAllList.get(i).getZbv16()));
				sjbbAll.setZbv17(CountNumber(sjbbAll.getZbv17(),sjbbAllList.get(i).getZbv17()));
				sjbbAll.setZbv18(CountNumber(sjbbAll.getZbv18(),sjbbAllList.get(i).getZbv18()));
				sjbbAll.setZbv19(CountNumber(sjbbAll.getZbv19(),sjbbAllList.get(i).getZbv19()));
				sjbbAll.setZbv20(CountNumber(sjbbAll.getZbv20(),sjbbAllList.get(i).getZbv20()));
			}
			request.setAttribute("sjbbAll", sjbbAll);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbAllEdit.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void SjbbAllSave(HttpServletRequest request,
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
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//���������޸�
			String SjbbAll_id = request.getParameter("SjbbAll_id");
			String bt= request.getParameter("bt");
			String sj = request.getParameter("sj");
			String dwfzr= request.getParameter("dwfzr");
			String tjfzr= request.getParameter("tjfzr");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:�ύ2������ݸ���
			//������ͳ�Ʊ�
			String zb1= request.getParameter("zb1");
			String zb2= request.getParameter("zb2");
			String zb3= request.getParameter("zb3");
			String zb4= request.getParameter("zb4");
			String zb5= request.getParameter("zb5");
			String zb6= request.getParameter("zb6");
			String zb7= request.getParameter("zb7");
			String zb8= request.getParameter("zb8");
			String zb9= request.getParameter("zb9");
			String zb10= request.getParameter("zb10");
			String zb11= request.getParameter("zb11");
			String zb12= request.getParameter("zb12");
			String zb13= request.getParameter("zb13");
			String zb14= request.getParameter("zb14");
			String zb15= request.getParameter("zb15");
			String zb16= request.getParameter("zb16");
			String zb17= request.getParameter("zb17");
			String zb18= request.getParameter("zb18");
			String zb19= request.getParameter("zb19");
			String zb20= request.getParameter("zb20");
			String zb21= request.getParameter("zb21");
			String zb22= request.getParameter("zb22");
			String zb23= request.getParameter("zb23");
			String zb24= request.getParameter("zb24");
			//������֧��Ʊ���			
			String zbs1= request.getParameter("zbs1");
			String zbs2= request.getParameter("zbs2");
			String zbs3= request.getParameter("zbs3");
			String zbs4= request.getParameter("zbs4");
			String zbs5= request.getParameter("zbs5");
			String zbs6= request.getParameter("zbs6");
			String zbs7= request.getParameter("zbs7");
			String zbs8= request.getParameter("zbs8");
			String zbs9= request.getParameter("zbs9");
			String zbs10= request.getParameter("zbs10");
			String zbs11= request.getParameter("zbs11");
			String zbs12= request.getParameter("zbs12");
			String zbs13= request.getParameter("zbs13");
			String zbs14= request.getParameter("zbs14");
			String zbs15= request.getParameter("zbs15");
			String zbs16= request.getParameter("zbs16");
			String zbs17= request.getParameter("zbs17");
			String zbs18= request.getParameter("zbs18");
			//����������Ʊ���			
			String zbt1= request.getParameter("zbt1");
			String zbt2= request.getParameter("zbt2");
			String zbt3= request.getParameter("zbt3");
			String zbt4= request.getParameter("zbt4");
			String zbt5= request.getParameter("zbt5");
			String zbt6= request.getParameter("zbt6");
			String zbt7= request.getParameter("zbt7");
			String zbt8= request.getParameter("zbt8");
			String zbt9= request.getParameter("zbt9");
			String zbt10= request.getParameter("zbt10");
			String zbt11= request.getParameter("zbt11");
			String zbt12= request.getParameter("zbt12");
			String zbt13= request.getParameter("zbt13");
			String zbt14= request.getParameter("zbt14");
			String zbt15= request.getParameter("zbt15");
			String zbt16= request.getParameter("zbt16");
			String zbt17= request.getParameter("zbt17");
			String zbt18= request.getParameter("zbt18");
			String zbt19= request.getParameter("zbt19");
			String zbt20= request.getParameter("zbt20");
			String zbt21= request.getParameter("zbt21");
			String zbt22= request.getParameter("zbt22");
			String zbt23= request.getParameter("zbt23");
			String zbt24= request.getParameter("zbt24");
			String zbt25= request.getParameter("zbt25");
			//����������Ʊ���	
			String zbf1= request.getParameter("zbf1");
			String zbf2= request.getParameter("zbf2");
			String zbf3= request.getParameter("zbf3");
			String zbf4= request.getParameter("zbf4");
			String zbf5= request.getParameter("zbf5");
			String zbf6= request.getParameter("zbf6");
			String zbf7= request.getParameter("zbf7");
			String zbf8= request.getParameter("zbf8");
			String zbf9= request.getParameter("zbf9");
			String zbf10= request.getParameter("zbf10");
			String zbf11= request.getParameter("zbf11");
			String zbf12= request.getParameter("zbf12");
			String zbf13= request.getParameter("zbf13");
			String zbf14= request.getParameter("zbf14");
			String zbf15= request.getParameter("zbf15");
			String zbf16= request.getParameter("zbf16");
			String zbf17= request.getParameter("zbf17");
			String zbf18= request.getParameter("zbf18");
			String zbf19= request.getParameter("zbf19");
			String zbf20= request.getParameter("zbf20");
			String zbf21= request.getParameter("zbf21");
			String zbf22= request.getParameter("zbf22");
			String zbf23= request.getParameter("zbf23");
			String zbf24= request.getParameter("zbf24");
			String zbf25= request.getParameter("zbf25");
			String zbf26= request.getParameter("zbf26");
			String zbf27= request.getParameter("zbf27");
			String zbf28= request.getParameter("zbf28");
			String zbf29= request.getParameter("zbf29");
			String zbf30= request.getParameter("zbf30");
			String zbf31= request.getParameter("zbf31");
			String zbf32= request.getParameter("zbf32");
			String zbf33= request.getParameter("zbf33");
			//��ƻ�����Ա����	
			String zbv1= request.getParameter("zbv1");
			String zbv2= request.getParameter("zbv2");
			String zbv3= request.getParameter("zbv3");
			String zbv4= request.getParameter("zbv4");
			String zbv5= request.getParameter("zbv5");
			String zbv6= request.getParameter("zbv6");
			String zbv7= request.getParameter("zbv7");
			String zbv8= request.getParameter("zbv8");
			String zbv9= request.getParameter("zbv9");
			String zbv10= request.getParameter("zbv10");
			String zbv11= request.getParameter("zbv11");
			String zbv12= request.getParameter("zbv12");
			String zbv13= request.getParameter("zbv13");
			String zbv14= request.getParameter("zbv14");
			String zbv15= request.getParameter("zbv15");
			String zbv16= request.getParameter("zbv16");
			String zbv17= request.getParameter("zbv17");
			String zbv18= request.getParameter("zbv18");
			String zbv19= request.getParameter("zbv19");
			String zbv20= request.getParameter("zbv20");
			
			SjbbAll sjbbAll = new SjbbAll();
			sjbbAll.setBt(bt);
			sjbbAll.setCzr(UserInfo.getName());
			sjbbAll.setCzrID(UserInfo.getUsername());
			sjbbAll.setCzrdw(UserInfo.getCompany());
			sjbbAll.setCzrphone(czrphone);
			sjbbAll.setCzsj(data1);
			sjbbAll.setSj(DateFormat(sj));
			sjbbAll.setDwfzr(dwfzr);
			sjbbAll.setTjfzr(tjfzr);
			sjbbAll.setTjzt(tjzt);
			sjbbAll.setZb1(zb1);
			sjbbAll.setZb2(zb2);
			sjbbAll.setZb3(zb3);
			sjbbAll.setZb4(zb4);
			sjbbAll.setZb5(zb5);
			sjbbAll.setZb6(zb6);
			sjbbAll.setZb7(zb7);
			sjbbAll.setZb8(zb8);
			sjbbAll.setZb9(zb9);
			sjbbAll.setZb10(zb10);
			sjbbAll.setZb11(zb11);
			sjbbAll.setZb12(zb12);
			sjbbAll.setZb13(zb13);
			sjbbAll.setZb14(zb14);
			sjbbAll.setZb15(zb15);
			sjbbAll.setZb16(zb16);
			sjbbAll.setZb17(zb17);
			sjbbAll.setZb18(zb18);
			sjbbAll.setZb19(zb19);
			sjbbAll.setZb20(zb20);
			sjbbAll.setZb21(zb21);
			sjbbAll.setZb22(zb22);
			sjbbAll.setZb23(zb23);
			sjbbAll.setZb24(zb24);
			sjbbAll.setZbs1(zbs1);
			sjbbAll.setZbs2(zbs2);
			sjbbAll.setZbs3(zbs3);
			sjbbAll.setZbs4(zbs4);
			sjbbAll.setZbs5(zbs5);
			sjbbAll.setZbs6(zbs6);
			sjbbAll.setZbs7(zbs7);
			sjbbAll.setZbs8(zbs8);
			sjbbAll.setZbs9(zbs9);
			sjbbAll.setZbs10(zbs10);
			sjbbAll.setZbs11(zbs11);
			sjbbAll.setZbs12(zbs12);
			sjbbAll.setZbs13(zbs13);
			sjbbAll.setZbs14(zbs14);
			sjbbAll.setZbs15(zbs15);
			sjbbAll.setZbs16(zbs16);
			sjbbAll.setZbs17(zbs17);
			sjbbAll.setZbs18(zbs18);
			sjbbAll.setZbt1(zbt1);
			sjbbAll.setZbt2(zbt2);
			sjbbAll.setZbt3(zbt3);
			sjbbAll.setZbt4(zbt4);
			sjbbAll.setZbt5(zbt5);
			sjbbAll.setZbt6(zbt6);
			sjbbAll.setZbt7(zbt7);
			sjbbAll.setZbt8(zbt8);
			sjbbAll.setZbt9(zbt9);
			sjbbAll.setZbt10(zbt10);
			sjbbAll.setZbt11(zbt11);
			sjbbAll.setZbt12(zbt12);
			sjbbAll.setZbt13(zbt13);
			sjbbAll.setZbt14(zbt14);
			sjbbAll.setZbt15(zbt15);
			sjbbAll.setZbt16(zbt16);
			sjbbAll.setZbt17(zbt17);
			sjbbAll.setZbt18(zbt18);
			sjbbAll.setZbt19(zbt19);
			sjbbAll.setZbt20(zbt20);
			sjbbAll.setZbt21(zbt21);
			sjbbAll.setZbt22(zbt22);
			sjbbAll.setZbt23(zbt23);
			sjbbAll.setZbt24(zbt24);
			sjbbAll.setZbt25(zbt25);
			sjbbAll.setZbf1(zbf1);
			sjbbAll.setZbf2(zbf2);
			sjbbAll.setZbf3(zbf3);
			sjbbAll.setZbf4(zbf4);
			sjbbAll.setZbf5(zbf5);
			sjbbAll.setZbf6(zbf6);
			sjbbAll.setZbf7(zbf7);
			sjbbAll.setZbf8(zbf8);
			sjbbAll.setZbf9(zbf9);
			sjbbAll.setZbf10(zbf10);
			sjbbAll.setZbf11(zbf11);
			sjbbAll.setZbf12(zbf12);
			sjbbAll.setZbf13(zbf13);
			sjbbAll.setZbf14(zbf14);
			sjbbAll.setZbf15(zbf15);
			sjbbAll.setZbf16(zbf16);
			sjbbAll.setZbf17(zbf17);
			sjbbAll.setZbf18(zbf18);
			sjbbAll.setZbf19(zbf19);
			sjbbAll.setZbf20(zbf20);
			sjbbAll.setZbf21(zbf21);
			sjbbAll.setZbf22(zbf22);
			sjbbAll.setZbf23(zbf23);
			sjbbAll.setZbf24(zbf24);
			sjbbAll.setZbf25(zbf25);
			sjbbAll.setZbf26(zbf26);
			sjbbAll.setZbf27(zbf27);
			sjbbAll.setZbf28(zbf28);
			sjbbAll.setZbf29(zbf29);
			sjbbAll.setZbf30(zbf30);
			sjbbAll.setZbf31(zbf31);
			sjbbAll.setZbf32(zbf32);
			sjbbAll.setZbf33(zbf33);
			sjbbAll.setZbv1(zbv1);
			sjbbAll.setZbv2(zbv2);
			sjbbAll.setZbv3(zbv3);
			sjbbAll.setZbv4(zbv4);
			sjbbAll.setZbv5(zbv5);
			sjbbAll.setZbv6(zbv6);
			sjbbAll.setZbv7(zbv7);
			sjbbAll.setZbv8(zbv8);
			sjbbAll.setZbv9(zbv9);
			sjbbAll.setZbv10(zbv10);
			sjbbAll.setZbv11(zbv11);
			sjbbAll.setZbv12(zbv12);
			sjbbAll.setZbv13(zbv13);
			sjbbAll.setZbv14(zbv14);
			sjbbAll.setZbv15(zbv15);
			sjbbAll.setZbv16(zbv16);
			sjbbAll.setZbv17(zbv17);
			sjbbAll.setZbv18(zbv18);
			sjbbAll.setZbv19(zbv19);
			sjbbAll.setZbv20(zbv20);
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			//�������޸�����
			if(!"".equals(SjbbAll_id)&&SjbbAll_id!=null){
				sjbbAll.setId(Integer.parseInt(SjbbAll_id));
				bbsbSjbbDao.updateSjbbAll(sjbbAll);
			}else{
				bbsbSjbbDao.insertSjbbAll(sjbbAll);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbSjbbServlet?action=getSjbbAll&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void SjbbAllShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		SjbbAll sjbbAll = new SjbbAll();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			sjbbAll = bbsbSjbbDao.querySjbbAllByID(ID);
		}
		request.setAttribute("sjbbAll", sjbbAll);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/AuditedStatements/SjbbAllShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void SjbbAllDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		bbsbSjbbDao.deleteSjbbAllById(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbSjbbServlet?action=getSjbbAll&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/*
	 *  ���벵��
	 * 
	 */
	protected void SjbbAllReturn(HttpServletRequest request,
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
			String id = request.getParameter("id");
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			//������Ա���û���
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			//�������޸�����
			if(!"".equals(id)&&id!=null){
				bbsbSjbbDao.updateSjbbAllRet(id);
				String dxnr = UserInfo.getName()+"������һ�ݽ�ͨ���ͳ�Ʊ����أ��뼰ʱ�鿴";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//���Ͷ���
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				//��ȡ��ǰʱ��
				java.util.Date  date=new java.util.Date();
				java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("δ����");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbSjbbServlet?action=getSjbbAll&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getSjbbAllExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String SjbbAll_id = request.getParameter("SjbbAll_id");
		SjbbAll sjbbAll = new SjbbAll();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(SjbbAll_id)&&SjbbAll_id!=null){
			int ID = Integer.parseInt(SjbbAll_id);
			sjbbAll = bbsbSjbbDao.querySjbbAllByID(ID);
			//����xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.�趨��response��������ԣ�
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.ȡ��response��OutputStreamʵ�����������ʵ����һ��WritableWorkbook����
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				// ��������Ϊ����
				WritableFont font1 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//ˮƽ����
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//��ֱ����
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.CENTRE);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("����"), 9);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.CENTRE);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("����"), 36);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("����"), 18);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("����"), 16);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

				WritableFont font7 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format7 = new WritableCellFormat(font7);
				format7.setAlignment(jxl.format.Alignment.LEFT);
				format7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format7.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font8 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format8 = new WritableCellFormat(font8);
				format8.setAlignment(jxl.format.Alignment.CENTRE);
				format8.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format8.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font9 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format9 = new WritableCellFormat(font9);
				format9.setAlignment(jxl.format.Alignment.CENTRE);
				format9.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format9.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				WritableFont font10 = new WritableFont(WritableFont.createFont("����"), 9);
				WritableCellFormat format10 = new WritableCellFormat(font10);
				format10.setAlignment(jxl.format.Alignment.LEFT);
				format10.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				//�½�һ�ű��������������
				WritableSheet wsheet = wwb.createSheet("�������",0);
				Label label = new Label(0,0,"��ͨ���ͳ�Ʊ����ƶȲ���",format1);
				wsheet.addCell(label);
				label = new Label(0,1,"��λ����",format2);
				wsheet.addCell(label);
				label = new Label(1,1,"",format2);
				wsheet.addCell(label);
				label = new Label(0,2,"��λ����",format3);
				wsheet.addCell(label);
				label = new Label(1,2,sjbbAll.getCzrdw(),format3);
				wsheet.addCell(label);
				label = new Label(0,3,"��λ������",format3);
				wsheet.addCell(label);
				label = new Label(1,3,sjbbAll.getDwfzr(),format3);
				wsheet.addCell(label);
				label = new Label(0,4,"��Ƹ�����",format3);
				wsheet.addCell(label);
				label = new Label(1,4,sjbbAll.getTjfzr(),format3);
				wsheet.addCell(label);
				label = new Label(0,5,"�����",format3);
				wsheet.addCell(label);
				label = new Label(1,5,sjbbAll.getCzr(),format3);
				wsheet.addCell(label);
				label = new Label(0,6,"��ϵ�绰",format3);
				wsheet.addCell(label);
				label = new Label(1,6,sjbbAll.getCzrphone(),format3);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 1, 0);
				//�����п�
				wsheet.setColumnView(0, 20);
				wsheet.setColumnView(1, 30);
				//�����п�
				for(int k=0; k<7; k++){
					wsheet.setRowView(k, 800, false);
				}
				
				//�½�һ�ű��������������
				wsheet = wwb.createSheet("�������",1);
				label = new Label(0,1,sjbbAll.getBt(),format4);
				wsheet.addCell(label);
				label = new Label(0,3,sjbbAll.getSj().toString().substring(0,4)+"��",format5);
				wsheet.addCell(label);
				label = new Label(0,5,"���λ��"+sjbbAll.getCzrdw(),format5);
				wsheet.addCell(label);
				label = new Label(0,7,"��λ�����ˣ�"+sjbbAll.getDwfzr(),format5);
				wsheet.addCell(label);
				label = new Label(2,7,"��Ƹ����ˣ�"+sjbbAll.getTjfzr(),format5);
				wsheet.addCell(label);
				label = new Label(0,9,"����ˣ�"+sjbbAll.getCzr(),format5);
				wsheet.addCell(label);
				label = new Label(2,9,"������ڣ�"+sjbbAll.getCzsj().toString().substring(0, 10),format5);
				wsheet.addCell(label);
				label = new Label(2,10,"��ϵ�绰��"+sjbbAll.getCzrphone(),format5);
				wsheet.addCell(label);

				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 1, 3, 1);
				wsheet.mergeCells(0, 3, 3, 3);
				wsheet.mergeCells(0, 5, 3, 5);
				//�����п�
				wsheet.setColumnView(0, 30);
				wsheet.setColumnView(1, 25);
				wsheet.setColumnView(2, 25);
				wsheet.setColumnView(3, 20);
				//�����п�
				for(int k=0; k<10; k++){
					wsheet.setRowView(k, 800, false);
				}
				
				//�½�һ�ű�������������ͳ�Ʊ�
				wsheet = wwb.createSheet("SJ1 ������ͳ�Ʊ�(SJB1)",2);	
				//���ñ�ͷ
				label = new Label(0,0,"������ͳ�Ʊ� ",format6);
				wsheet.addCell(label);
				label = new Label(0,1,"ָ������",format8);
				wsheet.addCell(label);
				label = new Label(1,1,"���㵥λ",format8);
				wsheet.addCell(label);
				label = new Label(2,1,"����",format8);
				wsheet.addCell(label);
				label = new Label(3,1,"��ֵ",format8);
				wsheet.addCell(label);
				label = new Label(0,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(1,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,2,"1",format8);
				wsheet.addCell(label);
				label = new Label(0,3,"һ���ڲ���ƻ���",format7);
				wsheet.addCell(label);
				label = new Label(1,3,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,3,"1",format8);
				wsheet.addCell(label);
				label = new Label(3,3,sjbbAll.getZb1(),format9);
				wsheet.addCell(label);
				label = new Label(0,4,"       ���У�רְ����",format7);
				wsheet.addCell(label);
				label = new Label(1,4,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,4,"2",format8);
				wsheet.addCell(label);
				label = new Label(3,4,sjbbAll.getZb2(),format9);
				wsheet.addCell(label);
				label = new Label(0,5,"�����ڲ������Ա",format7);
				wsheet.addCell(label);
				label = new Label(1,5,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,5,"3",format8);
				wsheet.addCell(label);
				label = new Label(3,5,sjbbAll.getZb3(),format9);
				wsheet.addCell(label);
				label = new Label(0,6,"       ���У�רְ��Ա",format7);
				wsheet.addCell(label);
				label = new Label(1,6,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,6,"4",format8);
				wsheet.addCell(label);
				label = new Label(3,6,sjbbAll.getZb4(),format9);
				wsheet.addCell(label);
				label = new Label(0,7,"������������Ŀ",format7);
				wsheet.addCell(label);
				label = new Label(1,7,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,7,"5",format8);
				wsheet.addCell(label);
				label = new Label(3,7,sjbbAll.getZb5(),format9);
				wsheet.addCell(label);
				label = new Label(0,8,"        ���У�������֧���",format7);
				wsheet.addCell(label);
				label = new Label(1,8,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,8,"6",format8);
				wsheet.addCell(label);
				label = new Label(3,8,sjbbAll.getZb6(),format9);
				wsheet.addCell(label);
				label = new Label(0,9,"              �����������",format7);
				wsheet.addCell(label);
				label = new Label(1,9,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,9,"7",format8);
				wsheet.addCell(label);
				label = new Label(3,9,sjbbAll.getZb7(),format9);
				wsheet.addCell(label);
				label = new Label(0,10,"              �����������",format7);
				wsheet.addCell(label);
				label = new Label(1,10,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,10,"8",format8);
				wsheet.addCell(label);
				label = new Label(3,10,sjbbAll.getZb8(),format9);
				wsheet.addCell(label);
				label = new Label(0,11,"              Ч�����",format7);
				wsheet.addCell(label);
				label = new Label(1,11,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,11,"9",format8);
				wsheet.addCell(label);
				label = new Label(3,11,sjbbAll.getZb9(),format9);
				wsheet.addCell(label);
				label = new Label(0,12,"              �ڲ���������",format7);
				wsheet.addCell(label);
				label = new Label(1,12,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,12,"10",format8);
				wsheet.addCell(label);
				label = new Label(3,12,sjbbAll.getZb10(),format9);
				wsheet.addCell(label);
				label = new Label(0,13,"              ��Ϣϵͳ���",format7);
				wsheet.addCell(label);
				label = new Label(1,13,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,13,"11",format8);
				wsheet.addCell(label);
				label = new Label(3,13,sjbbAll.getZb11(),format9);
				wsheet.addCell(label);
				label = new Label(0,14,"              ר����ƣ����飩",format7);
				wsheet.addCell(label);
				label = new Label(1,14,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,14,"12",format8);
				wsheet.addCell(label);
				label = new Label(3,14,sjbbAll.getZb12(),format9);
				wsheet.addCell(label);
				label = new Label(0,15,"              ����",format7);
				wsheet.addCell(label);
				label = new Label(1,15,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,15,"13",format8);
				wsheet.addCell(label);
				label = new Label(3,15,sjbbAll.getZb13(),format9);
				wsheet.addCell(label);
				label = new Label(0,16,"�ġ�����ܽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,16,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,16,"14",format8);
				wsheet.addCell(label);
				label = new Label(3,16,sjbbAll.getZb14(),format9);
				wsheet.addCell(label);
				label = new Label(0,17,"�塢���������",format7);
				wsheet.addCell(label);
				label = new Label(1,17,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,17,"15",format8);
				wsheet.addCell(label);
				label = new Label(3,17,sjbbAll.getZb15(),format9);
				wsheet.addCell(label);
				label = new Label(0,18,"        ���У�Υ����",format7);
				wsheet.addCell(label);
				label = new Label(1,18,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,18,"16",format8);
				wsheet.addCell(label);
				label = new Label(3,18,sjbbAll.getZb16(),format9);
				wsheet.addCell(label);
				label = new Label(0,19,"              ��ʧ�˷ѽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,19,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,19,"17",format8);
				wsheet.addCell(label);
				label = new Label(3,19,sjbbAll.getZb17(),format9);
				wsheet.addCell(label);
				label = new Label(0,20,"              �����淶���",format7);
				wsheet.addCell(label);
				label = new Label(1,20,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,20,"18",format8);
				wsheet.addCell(label);
				label = new Label(3,20,sjbbAll.getZb18(),format9);
				wsheet.addCell(label);
				label = new Label(0,21,"�����ٽ����ս�֧���",format7);
				wsheet.addCell(label);
				label = new Label(1,21,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,21,"19",format8);
				wsheet.addCell(label);
				label = new Label(3,21,sjbbAll.getZb19(),format9);
				wsheet.addCell(label);
				label = new Label(0,22,"�ߡ��ٽ����ƹ����ƶ�",format7);
				wsheet.addCell(label);
				label = new Label(1,22,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,22,"20",format8);
				wsheet.addCell(label);
				label = new Label(3,22,sjbbAll.getZb20(),format9);
				wsheet.addCell(label);
				label = new Label(0,23,"�ˡ�����������������",format7);
				wsheet.addCell(label);
				label = new Label(1,23,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,23,"21",format8);
				wsheet.addCell(label);
				label = new Label(3,23,sjbbAll.getZb21(),format9);
				wsheet.addCell(label);
				label = new Label(0,24,"�š�����˾�����ͼ�����ش���������������",format7);
				wsheet.addCell(label);
				label = new Label(1,24,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,24,"22",format8);
				wsheet.addCell(label);
				label = new Label(3,24,sjbbAll.getZb22(),format9);
				wsheet.addCell(label);
				label = new Label(0,25,"ʮ������˾�����ͼ�����ش�����Ա",format7);
				wsheet.addCell(label);
				label = new Label(1,25,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,25,"23",format8);
				wsheet.addCell(label);
				label = new Label(3,25,sjbbAll.getZb23(),format9);
				wsheet.addCell(label);
				label = new Label(0,26,"ʮһ��ʵ�ʸ�����������",format7);
				wsheet.addCell(label);
				label = new Label(1,26,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,26,"24",format8);
				wsheet.addCell(label);
				label = new Label(3,26,sjbbAll.getZb24(),format9);
				wsheet.addCell(label);

				label = new Label(0,27,"��ע�� 1.����ϵ�������ۺϱ��� �й�ָ��Ӧ���ڻ�����������������ָͬ��֮�͡�",format10);
				wsheet.addCell(label);
				label = new Label(0,28,"2.�����߼���ϵ��01�С�02�У�03�С�04�У�05��=06��+07��+08��+09��+10��+11��+12��+13�У�15�С�16��+17��+18�С�",format10);
				wsheet.addCell(label);
				label = new Label(0,29,"3.����߼���ϵ�������1��05�Сݽ����2��01��+�����3��01��+�����4��05�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,30,"                �����1��03�Сݽ����5��06�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,31,"                �����1��14�Сݽ����2��02��+�����3��05�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,32,"                �����1��16�Сݽ����2��03��+�����3��10��+�����4��09�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,33,"                �����1��17�Сݽ����2��09��+�����3��17��+�����4��13�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,34,"                �����1��18�Сݽ����2��10��+�����3��18��+�����4��17�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,35,"                �����1��19�Сݽ����2��11�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,36,"                �����1��20�Сݽ����2��16��+�����3��21��+�����4��33�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,37,"                �����1��22�Сݽ����2��18�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,38,"                �����1��23�Сݽ����4��27��+�����4��28�С�",format10);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 3, 0);
				wsheet.mergeCells(0, 27, 3, 27);
				wsheet.mergeCells(0, 28, 3, 28);
				wsheet.mergeCells(0, 29, 3, 29);
				wsheet.mergeCells(0, 30, 3, 30);
				wsheet.mergeCells(0, 31, 3, 31);
				wsheet.mergeCells(0, 32, 3, 32);
				wsheet.mergeCells(0, 33, 3, 33);
				wsheet.mergeCells(0, 34, 3, 34);
				wsheet.mergeCells(0, 35, 3, 35);
				wsheet.mergeCells(0, 36, 3, 36);
				wsheet.mergeCells(0, 37, 3, 37);
				wsheet.mergeCells(0, 38, 3, 38);
				//�����п�
				wsheet.setColumnView(0, 45);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 45);
				//�����п�
				wsheet.setRowView(0, 600, false);
		        
				
				//�½�һ�ű�������������֧��Ʊ�
				wsheet = wwb.createSheet("SJ2 ������֧��Ʊ�(SJB2)",3);
				//���ñ�ͷ
				label = new Label(0,0,"������֧��Ʊ��� ",format6);
				wsheet.addCell(label);
				label = new Label(0,1,"ָ������",format8);
				wsheet.addCell(label);
				label = new Label(1,1,"���㵥λ",format8);
				wsheet.addCell(label);
				label = new Label(2,1,"����",format8);
				wsheet.addCell(label);
				label = new Label(3,1,"��ֵ",format8);
				wsheet.addCell(label);
				label = new Label(0,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(1,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,2,"1",format8);
				wsheet.addCell(label);
				label = new Label(0,3,"һ����Ƶ�λ",format7);
				wsheet.addCell(label);
				label = new Label(1,3,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,3,"1",format8);
				wsheet.addCell(label);
				label = new Label(3,3,sjbbAll.getZbs1(),format9);
				wsheet.addCell(label);
				label = new Label(0,4,"��������ܽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,4,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,4,"2",format8);
				wsheet.addCell(label);
				label = new Label(3,4,sjbbAll.getZbs2(),format9);
				wsheet.addCell(label);
				label = new Label(0,5,"�������Υ����",format7);
				wsheet.addCell(label);
				label = new Label(1,5,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,5,"3",format8);
				wsheet.addCell(label);
				label = new Label(3,5,sjbbAll.getZbs3(),format9);
				wsheet.addCell(label);
				label = new Label(0,6,"        ���У�Υ��ı��ʽ���; ",format7);
				wsheet.addCell(label);
				label = new Label(1,6,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,6,"4",format8);
				wsheet.addCell(label);
				label = new Label(3,6,sjbbAll.getZbs4(),format9);
				wsheet.addCell(label);
				label = new Label(0,7,"             �鱨ð��ƭȡ�ʽ�",format7);
				wsheet.addCell(label);
				label = new Label(1,7,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,7,"5",format8);
				wsheet.addCell(label);
				label = new Label(3,7,sjbbAll.getZbs5(),format9);
				wsheet.addCell(label);
				label = new Label(0,8,"             �����ʲ�",format7);
				wsheet.addCell(label);
				label = new Label(1,8,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,8,"6",format8);
				wsheet.addCell(label);
				label = new Label(3,8,sjbbAll.getZbs6(),format9);
				wsheet.addCell(label);
				label = new Label(0,9,"             δ���涨���ա����ɲ�������",format7);
				wsheet.addCell(label);
				label = new Label(1,9,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,9,"7",format8);
				wsheet.addCell(label);
				label = new Label(3,9,sjbbAll.getZbs7(),format9);
				wsheet.addCell(label);
				label = new Label(0,10,"             ���棨��֧����ʵ",format7);
				wsheet.addCell(label);
				label = new Label(1,10,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,10,"8",format8);
				wsheet.addCell(label);
				label = new Label(3,10,sjbbAll.getZbs8(),format9);
				wsheet.addCell(label);
				label = new Label(0,11,"�ġ������ʧ�˷ѽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,11,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,11,"9",format8);
				wsheet.addCell(label);
				label = new Label(3,11,sjbbAll.getZbs9(),format9);
				wsheet.addCell(label);
				label = new Label(0,12,"�塢��������淶���",format7);
				wsheet.addCell(label);
				label = new Label(1,12,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,12,"10",format8);
				wsheet.addCell(label);
				label = new Label(3,12,sjbbAll.getZbs10(),format9);
				wsheet.addCell(label);
				label = new Label(0,13,"�����ٽ����ս�֧���",format7);
				wsheet.addCell(label);
				label = new Label(1,13,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,13,"11",format8);
				wsheet.addCell(label);
				label = new Label(3,13,sjbbAll.getZbs11(),format9);
				wsheet.addCell(label);
				label = new Label(0,14,"�ߡ��´���ƾ���",format7);
				wsheet.addCell(label);
				label = new Label(1,14,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,14,"12",format8);
				wsheet.addCell(label);
				label = new Label(3,14,sjbbAll.getZbs12(),format9);
				wsheet.addCell(label);
				label = new Label(0,15,"�ˡ���ʵ��ƾ���",format7);
				wsheet.addCell(label);
				label = new Label(1,15,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,15,"13",format8);
				wsheet.addCell(label);
				label = new Label(3,15,sjbbAll.getZbs13(),format9);
				wsheet.addCell(label);
				label = new Label(0,16,"�š������ƽ���",format7);
				wsheet.addCell(label);
				label = new Label(1,16,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,16,"14",format8);
				wsheet.addCell(label);
				label = new Label(3,16,sjbbAll.getZbs14(),format9);
				wsheet.addCell(label);
				label = new Label(0,17,"ʮ��������ƽ���",format7);
				wsheet.addCell(label);
				label = new Label(1,17,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,17,"15",format8);
				wsheet.addCell(label);
				label = new Label(3,17,sjbbAll.getZbs15(),format9);
				wsheet.addCell(label);
				label = new Label(0,18,"ʮһ���ٽ����ƹ����ƶ�",format7);
				wsheet.addCell(label);
				label = new Label(1,18,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,18,"16",format8);
				wsheet.addCell(label);
				label = new Label(3,18,sjbbAll.getZbs16(),format9);
				wsheet.addCell(label);
				label = new Label(0,19,"ʮ��������Ԫ����Υ�͵�λ",format7);
				wsheet.addCell(label);
				label = new Label(1,19,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,19,"17",format8);
				wsheet.addCell(label);
				label = new Label(3,19,sjbbAll.getZbs17(),format9);
				wsheet.addCell(label);
				label = new Label(0,20,"ʮ������Ԫ����̰�ۻ�¸����",format7);
				wsheet.addCell(label);
				label = new Label(1,20,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,20,"18",format8);
				wsheet.addCell(label);
				label = new Label(3,20,sjbbAll.getZbs18(),format9);
				wsheet.addCell(label);
				label = new Label(0,21,"��ע��1.����ϵ������֧���ר�����ר���ʽ�ר��ѣ����������Ҳ���иñ�	",format10);
				wsheet.addCell(label);
				label = new Label(0,22,"      2.�����߼���ϵ��03�С�04��+05��+06��+07��+08�С�	",format10);
				wsheet.addCell(label);
				label = new Label(0,23,"      3.����߼���ϵ�������1��05�Сݽ����2��01�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,24,"                      �����1��14�Сݽ����2��02�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,25,"                      �����1��16�Сݽ����2��03�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,26,"                      �����1��17�Сݽ����2��09�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,27,"                      �����1��18�Сݽ����2��10�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,28,"                      �����1��19�Сݽ����2��11�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,29,"                      �����1��20�Сݽ����2��16�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,30,"                      �����1��22�Сݽ����2��18�С�",format10);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 3, 0);
				wsheet.mergeCells(0, 21, 3, 21);
				wsheet.mergeCells(0, 22, 3, 22);
				wsheet.mergeCells(0, 23, 3, 23);
				wsheet.mergeCells(0, 24, 3, 24);
				wsheet.mergeCells(0, 25, 3, 25);
				wsheet.mergeCells(0, 26, 3, 26);
				wsheet.mergeCells(0, 27, 3, 27);
				wsheet.mergeCells(0, 28, 3, 28);
				wsheet.mergeCells(0, 29, 3, 29);
				wsheet.mergeCells(0, 30, 3, 30);
				//�����п�
				wsheet.setColumnView(0, 45);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 45);
				//�����п�
				wsheet.setRowView(0, 600, false);
				
				
				//�½�һ�ű�����������������Ʊ���
				wsheet = wwb.createSheet("SJ3 ����������Ʊ���(SJB3)",4);
				//���ñ�ͷ
				label = new Label(0,0,"����������Ʊ��� ",format6);
				wsheet.addCell(label);
				label = new Label(0,1,"ָ������",format8);
				wsheet.addCell(label);
				label = new Label(1,1,"���㵥λ",format8);
				wsheet.addCell(label);
				label = new Label(2,1,"����",format8);
				wsheet.addCell(label);
				label = new Label(3,1,"��ֵ",format8);
				wsheet.addCell(label);
				label = new Label(0,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(1,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,2,"1",format8);
				wsheet.addCell(label);
				label = new Label(0,3,"һ�������Ŀ",format7);
				wsheet.addCell(label);
				label = new Label(1,3,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,3,"1",format8);
				wsheet.addCell(label);
				label = new Label(3,3,sjbbAll.getZbt1(),format9);
				wsheet.addCell(label);
				label = new Label(0,4,"        ���У������������",format7);
				wsheet.addCell(label);
				label = new Label(1,4,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,4,"2",format8);
				wsheet.addCell(label);
				label = new Label(3,4,sjbbAll.getZbt2(),format9);
				wsheet.addCell(label);
				label = new Label(0,5,"              �������",format7);
				wsheet.addCell(label);
				label = new Label(1,5,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,5,"3",format8);
				wsheet.addCell(label);
				label = new Label(3,5,sjbbAll.getZbt3(),format9);
				wsheet.addCell(label);
				label = new Label(0,6,"              ����ר�����",format7);
				wsheet.addCell(label);
				label = new Label(1,6,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,6,"4",format8);
				wsheet.addCell(label);
				label = new Label(3,6,sjbbAll.getZbt4(),format9);
				wsheet.addCell(label);
				label = new Label(0,7,"��������ܽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,7,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,7,"5",format8);
				wsheet.addCell(label);
				label = new Label(3,7,sjbbAll.getZbt5(),format9);
				wsheet.addCell(label);
				label = new Label(0,8,"        ���У������������",format7);
				wsheet.addCell(label);
				label = new Label(1,8,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,8,"6",format8);
				wsheet.addCell(label);
				label = new Label(3,8,sjbbAll.getZbt6(),format9);
				wsheet.addCell(label);
				label = new Label(0,9,"              �������",format7);
				wsheet.addCell(label);
				label = new Label(1,9,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,9,"7",format8);
				wsheet.addCell(label);
				label = new Label(3,9,sjbbAll.getZbt7(),format9);
				wsheet.addCell(label);
				label = new Label(0,10,"              ����ר�����",format7);
				wsheet.addCell(label);
				label = new Label(1,10,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,10,"8",format8);
				wsheet.addCell(label);
				label = new Label(3,10,sjbbAll.getZbt8(),format9);
				wsheet.addCell(label);
				label = new Label(0,11,"�����˼�Ͷ�ʣ����㣩��",format7);
				wsheet.addCell(label);
				label = new Label(1,11,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,11,"9",format8);
				wsheet.addCell(label);
				label = new Label(3,11,sjbbAll.getZbt9(),format9);
				wsheet.addCell(label);
				label = new Label(0,12,"�ġ����Υ����",format7);
				wsheet.addCell(label);
				label = new Label(1,12,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,12,"10",format8);
				wsheet.addCell(label);
				label = new Label(3,12,sjbbAll.getZbt10(),format9);
				wsheet.addCell(label);
				label = new Label(0,13,"        ���У�����ģ������׼����",format7);
				wsheet.addCell(label);
				label = new Label(1,13,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,13,"11",format8);
				wsheet.addCell(label);
				label = new Label(3,13,sjbbAll.getZbt11(),format9);
				wsheet.addCell(label);
				label = new Label(0,14,"              ��ռŲ�ý����ʽ�",format7);
				wsheet.addCell(label);
				label = new Label(1,14,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,14,"12",format8);
				wsheet.addCell(label);
				label = new Label(3,14,sjbbAll.getZbt12(),format9);
				wsheet.addCell(label);
				label = new Label(0,15,"              �����ʽ���ʵ",format7);
				wsheet.addCell(label);
				label = new Label(1,15,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,15,"13",format8);
				wsheet.addCell(label);
				label = new Label(3,15,sjbbAll.getZbt13(),format9);
				wsheet.addCell(label);
				label = new Label(0,16,"              ����(���У����̳ɱ�",format7);
				wsheet.addCell(label);
				label = new Label(1,16,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,16,"14",format8);
				wsheet.addCell(label);
				label = new Label(3,16,sjbbAll.getZbt14(),format9);
				wsheet.addCell(label);
				label = new Label(0,17,"              ©��˰��",format7);
				wsheet.addCell(label);
				label = new Label(1,17,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,17,"15",format8);
				wsheet.addCell(label);
				label = new Label(3,17,sjbbAll.getZbt15(),format9);
				wsheet.addCell(label);
				label = new Label(0,18,"              �ֺ�Ⱥ����������",format7);
				wsheet.addCell(label);
				label = new Label(1,18,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,18,"16",format8);
				wsheet.addCell(label);
				label = new Label(3,18,sjbbAll.getZbt16(),format9);
				wsheet.addCell(label);
				label = new Label(0,19,"�塢�����ʧ�˷ѽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,19,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,19,"17",format8);
				wsheet.addCell(label);
				label = new Label(3,19,sjbbAll.getZbt17(),format9);
				wsheet.addCell(label);
				label = new Label(0,20,"������������淶���",format7);
				wsheet.addCell(label);
				label = new Label(1,20,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,20,"18",format8);
				wsheet.addCell(label);
				label = new Label(3,20,sjbbAll.getZbt18(),format9);
				wsheet.addCell(label);
				label = new Label(0,21,"�ߡ�Ӧ�黹ԭ�����ʽ�",format7);
				wsheet.addCell(label);
				label = new Label(1,21,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,21,"19",format8);
				wsheet.addCell(label);
				label = new Label(3,21,sjbbAll.getZbt19(),format9);
				wsheet.addCell(label);
				label = new Label(0,22,"        ���У��ѹ黹�ʽ�",format7);
				wsheet.addCell(label);
				label = new Label(1,22,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,22,"20",format8);
				wsheet.addCell(label);
				label = new Label(3,22,sjbbAll.getZbt20(),format9);
				wsheet.addCell(label);
				label = new Label(0,23,"�ˡ��ٽ����ƹ����ƶ�",format7);
				wsheet.addCell(label);
				label = new Label(1,23,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,23,"21",format8);
				wsheet.addCell(label);
				label = new Label(3,23,sjbbAll.getZbt21(),format9);
				wsheet.addCell(label);
				label = new Label(0,24,"�š��´���ƾ�������",format7);
				wsheet.addCell(label);
				label = new Label(1,24,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,24,"22",format8);
				wsheet.addCell(label);
				label = new Label(3,24,sjbbAll.getZbt22(),format9);
				wsheet.addCell(label);
				label = new Label(0,25,"ʮ����ʵ��ƾ�������",format7);
				wsheet.addCell(label);
				label = new Label(1,25,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,25,"23",format8);
				wsheet.addCell(label);
				label = new Label(3,25,sjbbAll.getZbt23(),format9);
				wsheet.addCell(label);
				label = new Label(0,26,"ʮһ�������ƽ�������",format7);
				wsheet.addCell(label);
				label = new Label(1,26,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,26,"24",format8);
				wsheet.addCell(label);
				label = new Label(3,26,sjbbAll.getZbt24(),format9);
				wsheet.addCell(label);
				label = new Label(0,27,"ʮ����������ƽ�������",format7);
				wsheet.addCell(label);
				label = new Label(1,27,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,27,"25",format8);
				wsheet.addCell(label);
				label = new Label(3,27,sjbbAll.getZbt25(),format9);
				wsheet.addCell(label);

				label = new Label(0,28,"��ע��1.����ϵ�����������ר����й�ָ���ָ������������е����ݡ�",format10);
				wsheet.addCell(label);
				label = new Label(0,29,"      2.�����߼���ϵ��01��=02��+03��+04�У�05��=06��+07��+08�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,30,"                      10�С�11��+12��+13��+14��+15��+16�У�19�С�20�С�",format10);
				wsheet.addCell(label);
				label = new Label(0,31,"      3.����߼���ϵ�������1��05�Сݽ����3��01�У������1��14�Сݽ����3��05�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,32,"                      �����1��16�Сݽ����3��10�У������1��17�Сݽ����3��17�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,33,"                      �����1��18�Сݽ����3��18�У������1��20�Сݽ����3��21�С�",format10);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 3, 0);
				wsheet.mergeCells(0, 28, 3, 28);
				wsheet.mergeCells(0, 29, 3, 29);
				wsheet.mergeCells(0, 30, 3, 30);
				wsheet.mergeCells(0, 31, 3, 31);
				wsheet.mergeCells(0, 32, 3, 32);
				wsheet.mergeCells(0, 33, 3, 33);
				//�����п�
				wsheet.setColumnView(0, 45);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 45);
				//�����п�
				wsheet.setRowView(0, 600, false);
		        
				
				//�½�һ�ű�����������������Ʊ���
				wsheet = wwb.createSheet("SJ4 ����������Ʊ���(SJB4)",5);	
				//���ñ�ͷ
				label = new Label(0,0,"����������Ʊ���",format6);
				wsheet.addCell(label);
				label = new Label(0,1,"ָ������",format8);
				wsheet.addCell(label);
				label = new Label(1,1,"���㵥λ",format8);
				wsheet.addCell(label);
				label = new Label(2,1,"����",format8);
				wsheet.addCell(label);
				label = new Label(3,1,"��ֵ",format8);
				wsheet.addCell(label);
				label = new Label(0,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(1,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,2,"1",format8);
				wsheet.addCell(label);
				label = new Label(0,3,"һ�� ��ƾ���������",format7);
				wsheet.addCell(label);
				label = new Label(1,3,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,3,"1",format8);
				wsheet.addCell(label);
				label = new Label(3,3,sjbbAll.getZbf1(),format9);
				wsheet.addCell(label);
				label = new Label(0,4,"         ���У�������λ",format7);
				wsheet.addCell(label);
				label = new Label(1,4,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,4,"2",format8);
				wsheet.addCell(label);
				label = new Label(3,4,sjbbAll.getZbf2(),format9);
				wsheet.addCell(label);
				label = new Label(0,5,"               ��ҵ��λ",format7);
				wsheet.addCell(label);
				label = new Label(1,5,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,5,"3",format8);
				wsheet.addCell(label);
				label = new Label(3,5,sjbbAll.getZbf3(),format9);
				wsheet.addCell(label);
				label = new Label(0,6,"               ��ҵ",format7);
				wsheet.addCell(label);
				label = new Label(1,6,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,6,"4",format8);
				wsheet.addCell(label);
				label = new Label(3,6,sjbbAll.getZbf4(),format9);
				wsheet.addCell(label);
				label = new Label(0,7,"������Ƶ�λ",format7);
				wsheet.addCell(label);
				label = new Label(1,7,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,7,"5",format8);
				wsheet.addCell(label);
				label = new Label(3,7,sjbbAll.getZbf5(),format9);
				wsheet.addCell(label);
				label = new Label(0,8,"        ���У�������λ",format7);
				wsheet.addCell(label);
				label = new Label(1,8,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,8,"6",format8);
				wsheet.addCell(label);
				label = new Label(3,8,sjbbAll.getZbf6(),format9);
				wsheet.addCell(label);
				label = new Label(0,9,"              ��ҵ��λ",format7);
				wsheet.addCell(label);
				label = new Label(1,9,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,9,"7",format8);
				wsheet.addCell(label);
				label = new Label(3,9,sjbbAll.getZbf7(),format9);
				wsheet.addCell(label);
				label = new Label(0,10,"              ��ҵ",format7);
				wsheet.addCell(label);
				label = new Label(1,10,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,10,"8",format8);
				wsheet.addCell(label);
				label = new Label(3,10,sjbbAll.getZbf8(),format9);
				wsheet.addCell(label);
				label = new Label(0,11,"�������Υ����",format7);
				wsheet.addCell(label);
				label = new Label(1,11,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,11,"9",format8);
				wsheet.addCell(label);
				label = new Label(3,11,sjbbAll.getZbf9(),format9);
				wsheet.addCell(label);
				label = new Label(0,12,"        ���У�ֱ������",format7);
				wsheet.addCell(label);
				label = new Label(1,12,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,12,"10",format8);
				wsheet.addCell(label);
				label = new Label(3,12,sjbbAll.getZbf10(),format9);
				wsheet.addCell(label);
				label = new Label(0,13,"              ��������",format7);
				wsheet.addCell(label);
				label = new Label(1,13,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,13,"11",format8);
				wsheet.addCell(label);
				label = new Label(3,13,sjbbAll.getZbf11(),format9);
				wsheet.addCell(label);
				label = new Label(0,14,"              �쵼����",format7);
				wsheet.addCell(label);
				label = new Label(1,14,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,14,"12",format8);
				wsheet.addCell(label);
				label = new Label(3,14,sjbbAll.getZbf12(),format9);
				wsheet.addCell(label);
				label = new Label(0,15,"�ġ������ʧ�˷ѽ��",format7);
				wsheet.addCell(label);
				label = new Label(1,15,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,15,"13",format8);
				wsheet.addCell(label);
				label = new Label(3,15,sjbbAll.getZbf13(),format9);
				wsheet.addCell(label);
				label = new Label(0,16,"        ���У�ֱ������",format7);
				wsheet.addCell(label);
				label = new Label(1,16,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,16,"14",format8);
				wsheet.addCell(label);
				label = new Label(3,16,sjbbAll.getZbf14(),format9);
				wsheet.addCell(label);
				label = new Label(0,17,"              ��������",format7);
				wsheet.addCell(label);
				label = new Label(1,17,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,17,"15",format8);
				wsheet.addCell(label);
				label = new Label(3,17,sjbbAll.getZbf15(),format9);
				wsheet.addCell(label);
				label = new Label(0,18,"              �쵼����",format7);
				wsheet.addCell(label);
				label = new Label(1,18,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,18,"16",format8);
				wsheet.addCell(label);
				label = new Label(3,18,sjbbAll.getZbf16(),format9);
				wsheet.addCell(label);
				label = new Label(0,19,"�塢��������淶���",format7);
				wsheet.addCell(label);
				label = new Label(1,19,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,19,"17",format8);
				wsheet.addCell(label);
				label = new Label(3,19,sjbbAll.getZbf17(),format9);
				wsheet.addCell(label);
				label = new Label(0,20,"        ���У�ֱ������",format7);
				wsheet.addCell(label);
				label = new Label(1,20,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,20,"18",format8);
				wsheet.addCell(label);
				label = new Label(3,20,sjbbAll.getZbf18(),format9);
				wsheet.addCell(label);
				label = new Label(0,21,"             ��������",format7);
				wsheet.addCell(label);
				label = new Label(1,21,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,21,"19",format8);
				wsheet.addCell(label);
				label = new Label(3,21,sjbbAll.getZbf19(),format9);
				wsheet.addCell(label);
				label = new Label(0,22,"             �쵼����",format7);
				wsheet.addCell(label);
				label = new Label(1,22,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,22,"20",format8);
				wsheet.addCell(label);
				label = new Label(3,22,sjbbAll.getZbf20(),format9);
				wsheet.addCell(label);
				label = new Label(0,23,"�����쵼�ɲ��漰���˾�������",format7);
				wsheet.addCell(label);
				label = new Label(1,23,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,23,"21",format8);
				wsheet.addCell(label);
				label = new Label(3,23,sjbbAll.getZbf21(),format9);
				wsheet.addCell(label);
				label = new Label(0,24,"        ���У�����",format7);
				wsheet.addCell(label);
				label = new Label(1,24,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,24,"22",format8);
				wsheet.addCell(label);
				label = new Label(3,24,sjbbAll.getZbf22(),format9);
				wsheet.addCell(label);
				label = new Label(0,25,"              �漰���",format7);
				wsheet.addCell(label);
				label = new Label(1,25,"��Ԫ",format8);
				wsheet.addCell(label);
				label = new Label(2,25,"23",format8);
				wsheet.addCell(label);
				label = new Label(3,25,sjbbAll.getZbf23(),format9);
				wsheet.addCell(label);
				label = new Label(0,26,"�ߡ���Ա������",format7);
				wsheet.addCell(label);
				label = new Label(1,26,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,26,"24",format8);
				wsheet.addCell(label);
				label = new Label(3,26,sjbbAll.getZbf24(),format9);
				wsheet.addCell(label);
				label = new Label(0,27,"        ���У���ְ������",format7);
				wsheet.addCell(label);
				label = new Label(1,27,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,27,"25",format8);
				wsheet.addCell(label);
				label = new Label(3,27,sjbbAll.getZbf25(),format9);
				wsheet.addCell(label);
				label = new Label(0,28,"              ��������",format7);
				wsheet.addCell(label);
				label = new Label(1,28,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,28,"26",format8);
				wsheet.addCell(label);
				label = new Label(3,28,sjbbAll.getZbf26(),format9);
				wsheet.addCell(label);
				label = new Label(0,29,"              ����˾������",format7);
				wsheet.addCell(label);
				label = new Label(1,29,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,29,"27",format8);
				wsheet.addCell(label);
				label = new Label(3,29,sjbbAll.getZbf27(),format9);
				wsheet.addCell(label);
				label = new Label(0,30,"              ���ͼͼ������",format7);
				wsheet.addCell(label);
				label = new Label(1,30,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,30,"28",format8);
				wsheet.addCell(label);
				label = new Label(3,30,sjbbAll.getZbf28(),format9);
				wsheet.addCell(label);
				label = new Label(0,31,"�ˡ��´���ƾ���",format7);
				wsheet.addCell(label);
				label = new Label(1,31,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,31,"29",format8);
				wsheet.addCell(label);
				label = new Label(3,31,sjbbAll.getZbf29(),format9);
				wsheet.addCell(label);
				label = new Label(0,32,"�š���ʵ��ƾ���",format7);
				wsheet.addCell(label);
				label = new Label(1,32,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,32,"30",format8);
				wsheet.addCell(label);
				label = new Label(3,32,sjbbAll.getZbf30(),format9);
				wsheet.addCell(label);
				label = new Label(0,33,"ʮ�������ƽ���",format7);
				wsheet.addCell(label);
				label = new Label(1,33,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,33,"31",format8);
				wsheet.addCell(label);
				label = new Label(3,33,sjbbAll.getZbf31(),format9);
				wsheet.addCell(label);
				label = new Label(0,34,"ʮһ��������ƽ���",format7);
				wsheet.addCell(label);
				label = new Label(1,34,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,34,"32",format8);
				wsheet.addCell(label);
				label = new Label(3,34,sjbbAll.getZbf32(),format9);
				wsheet.addCell(label);
				label = new Label(0,35,"ʮ�����ٽ����ƹ����ƶ�",format7);
				wsheet.addCell(label);
				label = new Label(1,35,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,35,"33",format8);
				wsheet.addCell(label);
				label = new Label(3,35,sjbbAll.getZbf33(),format9);
				wsheet.addCell(label);

				label = new Label(0,36,"��ע��1.����ϵ�����������ר����й�ָ���ָ������������е����ݡ�",format10);
				wsheet.addCell(label);
				label = new Label(0,37,"      2.�����߼���ϵ��01��=02��+03��+04�У�05��=06��+07��+08�У�09�С�10��+11��+12�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,38,"                      13�С�14��+15��+16�У�17�С�18��+19��+20�У�21�С�22�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,39,"                      24�С�25��+26��+27��+28�С�	",format10);
				wsheet.addCell(label);
				label = new Label(0,40,"      3.����߼���ϵ�������1��05�Сݽ����4��05�У������1��16�Сݽ����4��09��",format10);
				wsheet.addCell(label);
				label = new Label(0,41,"                      �����1��17�Сݽ����4��13�У������1��18�Сݽ����4��17��",format10);
				wsheet.addCell(label);
				label = new Label(0,42,"                      �����1��20�Сݽ����4��33�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,43,"                      �����1��23�Сݽ����4��27��+�����4��28�С�",format10);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 3, 0);
				wsheet.mergeCells(0, 36, 3, 36);
				wsheet.mergeCells(0, 37, 3, 37);
				wsheet.mergeCells(0, 38, 3, 38);
				wsheet.mergeCells(0, 39, 3, 39);
				wsheet.mergeCells(0, 40, 3, 40);
				wsheet.mergeCells(0, 41, 3, 41);
				wsheet.mergeCells(0, 42, 3, 42);
				wsheet.mergeCells(0, 43, 3, 43);
				//�����п�
				wsheet.setColumnView(0, 45);
				wsheet.setColumnView(1, 10);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 45);
				//�����п�
				wsheet.setRowView(0, 600, false);
				
				
				//�½�һ�ű���������ƻ�����Ա����
				wsheet = wwb.createSheet("SJ5 ��ƻ�����Ա����(SJB5)",6);
				//���ñ�ͷ
				label = new Label(0,0,"��ƻ�����Ա����",format6);
				wsheet.addCell(label);
				label = new Label(0,1,"ָ������",format8);
				wsheet.addCell(label);
				label = new Label(2,1,"���㵥λ",format8);
				wsheet.addCell(label);
				label = new Label(3,1,"����",format8);
				wsheet.addCell(label);
				label = new Label(4,1,"��ֵ",format8);
				wsheet.addCell(label);
				label = new Label(0,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(2,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,2,"��",format8);
				wsheet.addCell(label);
				label = new Label(4,2,"1",format8);
				wsheet.addCell(label);
				label = new Label(0,3,"һ���ѽ�����",format7);
				wsheet.addCell(label);
				label = new Label(1,3,"��    ��",format7);
				wsheet.addCell(label);
				label = new Label(2,3,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,3,"1",format8);
				wsheet.addCell(label);
				label = new Label(4,3,sjbbAll.getZbv1(),format9);
				wsheet.addCell(label);
				label = new Label(0,4,"",format7);
				wsheet.addCell(label);
				label = new Label(1,4,"��    ��",format7);
				wsheet.addCell(label);
				label = new Label(2,4,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,4,"2",format8);
				wsheet.addCell(label);
				label = new Label(4,4,sjbbAll.getZbv2(),format9);
				wsheet.addCell(label);
				label = new Label(0,5,"",format7);
				wsheet.addCell(label);
				label = new Label(1,5,"��    ��",format7);
				wsheet.addCell(label);
				label = new Label(2,5,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,5,"3",format8);
				wsheet.addCell(label);
				label = new Label(4,5,sjbbAll.getZbv3(),format9);
				wsheet.addCell(label);
				label = new Label(0,6,"",format7);
				wsheet.addCell(label);
				label = new Label(1,6,"�Ƽ�����",format7);
				wsheet.addCell(label);
				label = new Label(2,6,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,6,"4",format8);
				wsheet.addCell(label);
				label = new Label(4,6,sjbbAll.getZbv4(),format9);
				wsheet.addCell(label);
				label = new Label(0,7,"������Ա����",format7);
				wsheet.addCell(label);
				label = new Label(1,7,"",format7);
				wsheet.addCell(label);
				label = new Label(2,7,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,7,"5",format8);
				wsheet.addCell(label);
				label = new Label(4,7,sjbbAll.getZbv5(),format9);
				wsheet.addCell(label);
				label = new Label(0,8,"�����䱸��Ա",format7);
				wsheet.addCell(label);
				label = new Label(1,8,"",format7);
				wsheet.addCell(label);
				label = new Label(2,8,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,8,"6",format8);
				wsheet.addCell(label);
				label = new Label(4,8,sjbbAll.getZbv6(),format9);
				wsheet.addCell(label);
				label = new Label(0,9,"��һ�����������ʷ�",format7);
				wsheet.addCell(label);
				label = new Label(1,9,"ר    ְ",format7);
				wsheet.addCell(label);
				label = new Label(2,9,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,9,"7",format8);
				wsheet.addCell(label);
				label = new Label(4,9,sjbbAll.getZbv7(),format9);
				wsheet.addCell(label);
				label = new Label(0,10,"",format7);
				wsheet.addCell(label);
				label = new Label(1,10,"��    ְ",format7);
				wsheet.addCell(label);
				label = new Label(2,10,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,10,"8",format8);
				wsheet.addCell(label);
				label = new Label(4,10,sjbbAll.getZbv8(),format9);
				wsheet.addCell(label);
				label = new Label(0,11,"��������ѧ����",format7);
				wsheet.addCell(label);
				label = new Label(1,11,"��ר������",format7);
				wsheet.addCell(label);
				label = new Label(2,11,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,11,"9",format8);
				wsheet.addCell(label);
				label = new Label(4,11,sjbbAll.getZbv9(),format9);
				wsheet.addCell(label);
				label = new Label(0,12,"",format7);
				wsheet.addCell(label);
				label = new Label(1,12,"��ר������",format7);
				wsheet.addCell(label);
				label = new Label(2,12,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,12,"10",format8);
				wsheet.addCell(label);
				label = new Label(4,12,sjbbAll.getZbv10(),format9);
				wsheet.addCell(label);
				label = new Label(0,13,"��������ְ�Ʒ�",format7);
				wsheet.addCell(label);
				label = new Label(1,13,"��     ��",format7);
				wsheet.addCell(label);
				label = new Label(2,13,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,13,"11",format8);
				wsheet.addCell(label);
				label = new Label(4,13,sjbbAll.getZbv11(),format9);
				wsheet.addCell(label);
				label = new Label(0,14,"",format7);
				wsheet.addCell(label);
				label = new Label(1,14,"��     ��",format7);
				wsheet.addCell(label);
				label = new Label(2,14,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,14,"12",format8);
				wsheet.addCell(label);
				label = new Label(4,14,sjbbAll.getZbv12(),format9);
				wsheet.addCell(label);
				label = new Label(0,15,"",format7);
				wsheet.addCell(label);
				label = new Label(1,15,"��������",format7);
				wsheet.addCell(label);
				label = new Label(2,15,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,15,"13",format8);
				wsheet.addCell(label);
				label = new Label(4,15,sjbbAll.getZbv13(),format9);
				wsheet.addCell(label);
				label = new Label(0,16,"���ģ��������",format7);
				wsheet.addCell(label);
				label = new Label(1,16,"45������",format7);
				wsheet.addCell(label);
				label = new Label(2,16,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,16,"14",format8);
				wsheet.addCell(label);
				label = new Label(4,16,sjbbAll.getZbv14(),format9);
				wsheet.addCell(label);
				label = new Label(0,17,"",format7);
				wsheet.addCell(label);
				label = new Label(1,17,"30��44��",format7);
				wsheet.addCell(label);
				label = new Label(2,17,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,17,"15",format8);
				wsheet.addCell(label);
				label = new Label(4,17,sjbbAll.getZbv15(),format9);
				wsheet.addCell(label);
				label = new Label(0,18,"",format7);
				wsheet.addCell(label);
				label = new Label(1,18,"29������",format7);
				wsheet.addCell(label);
				label = new Label(2,18,"��",format8);
				wsheet.addCell(label);
				label = new Label(3,18,"16",format8);
				wsheet.addCell(label);
				label = new Label(4,18,sjbbAll.getZbv16(),format9);
				wsheet.addCell(label);
				label = new Label(0,19,"�ġ���ѵ��Ա",format7);
				wsheet.addCell(label);
				label = new Label(1,19,"��     ��",format7);
				wsheet.addCell(label);
				label = new Label(2,19,"�˴�",format8);
				wsheet.addCell(label);
				label = new Label(3,19,"17",format8);
				wsheet.addCell(label);
				label = new Label(4,19,sjbbAll.getZbv17(),format9);
				wsheet.addCell(label);
				label = new Label(0,20,"",format7);
				wsheet.addCell(label);
				label = new Label(1,20,"�԰���ѵ",format7);
				wsheet.addCell(label);
				label = new Label(2,20,"�˴�",format8);
				wsheet.addCell(label);
				label = new Label(3,20,"18",format8);
				wsheet.addCell(label);
				label = new Label(4,20,sjbbAll.getZbv18(),format9);
				wsheet.addCell(label);
				label = new Label(0,21,"",format7);
				wsheet.addCell(label);
				label = new Label(1,21,"�μӲ�����ѵ",format7);
				wsheet.addCell(label);
				label = new Label(2,21,"�˴�",format8);
				wsheet.addCell(label);
				label = new Label(3,21,"19",format8);
				wsheet.addCell(label);
				label = new Label(4,21,sjbbAll.getZbv19(),format9);
				wsheet.addCell(label);
				label = new Label(0,22,"",format7);
				wsheet.addCell(label);
				label = new Label(1,22,"�μ���ϵͳ����λ��ѵ",format7);
				wsheet.addCell(label);
				label = new Label(2,22,"�˴�",format8);
				wsheet.addCell(label);
				label = new Label(3,22,"20",format8);
				wsheet.addCell(label);
				label = new Label(4,22,sjbbAll.getZbv20(),format9);
				wsheet.addCell(label);

				label = new Label(0,23,"��ע��1.����ϵ��ƻ�����Ա���ר����������ָ���ͳ�Ʒ�Χ����������ͨ���ܲ��źͽ�ͨ����ҵ��λ��",format10);
				wsheet.addCell(label);
				label = new Label(0,24,"      2.�����߼���ϵ��01��=02��+03��+04�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,25,"                      06��=07��+08��=09��+10��=11��+12��+13��=14��+15��+16�У�",format10);
				wsheet.addCell(label);
				label = new Label(0,26,"                      17��=18��+19��+20�С�",format10);
				wsheet.addCell(label);
				label = new Label(0,27,"      3.����߼���ϵ�������1��03�Сݽ����5��06��",format10);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 4, 0);
				wsheet.mergeCells(0, 23, 4, 23);
				wsheet.mergeCells(0, 24, 4, 24);
				wsheet.mergeCells(0, 25, 4, 25);
				wsheet.mergeCells(0, 26, 4, 26);
				wsheet.mergeCells(0, 27, 4, 27);
				wsheet.mergeCells(0, 1, 1, 1);
				wsheet.mergeCells(0, 2, 1, 2);		

				//�����п�
				wsheet.setColumnView(0, 25);
				wsheet.setColumnView(1, 25);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 10);
				wsheet.setColumnView(4, 45);
				//�����п�
				wsheet.setRowView(0, 600, false);
		        
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("������Ϣ��(Excel��ʽ)ʱ����");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*��ȡExcel�ļ�������   
	 * @param file  ����ȡ���ļ�   
	 * @return   
	 */ 
	public void readSjbbAllExcel(HttpServletRequest request,
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
			//���������޸�
			String SjbbAll_id = request.getParameter("SjbbAll_id");
			String sj= request.getParameter("sj");
			String dwfzr= request.getParameter("dwfzr");
			String tjfzr= request.getParameter("tjfzr");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//����Workbook��������������    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}  
			SjbbAll sjbbAll = new SjbbAll();
			sjbbAll.setBt("������ͳ�Ʊ���");
			sjbbAll.setSj(DateFormat(sj));
			sjbbAll.setDwfzr(dwfzr);
			sjbbAll.setTjfzr(tjfzr);
			if(!"".equals(SjbbAll_id)&&SjbbAll_id!=null){
				sjbbAll.setId(Integer.parseInt(SjbbAll_id));
			}
			//�����Workbook����֮�󣬾Ϳ���ͨ�����õ�Sheet��������������    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				String[] sheetnames = wb.getSheetNames();
				for(int s=0; s<sheetnames.length; s++){
					if(sheetnames[s].indexOf("������ͳ��")!=-1){
						int r = sheet[s].getRows();//����
						int c = sheet[s].getColumns();//����
						if(r<27||c<4){
							result = "�����ļ��Ƿ���ȷ";
						}else{
							//�ҵ�ָ���
							int num=0;
							for(int i=0; i<sheet[s].getRows(); i++){
								if("��".equals(sheet[s].getCell(0,i).getContents())){
									num=i;
									break;
								}
							}
							sjbbAll.setZb1(sheet[s].getCell(3,num+1).getContents());
							sjbbAll.setZb2(sheet[s].getCell(3,num+2).getContents());
							sjbbAll.setZb3(sheet[s].getCell(3,num+3).getContents());
							sjbbAll.setZb4(sheet[s].getCell(3,num+4).getContents());
							sjbbAll.setZb5(sheet[s].getCell(3,num+5).getContents());
							sjbbAll.setZb6(sheet[s].getCell(3,num+6).getContents());
							sjbbAll.setZb7(sheet[s].getCell(3,num+7).getContents());
							sjbbAll.setZb8(sheet[s].getCell(3,num+8).getContents());
							sjbbAll.setZb9(sheet[s].getCell(3,num+9).getContents());
							sjbbAll.setZb10(sheet[s].getCell(3,num+10).getContents());
							sjbbAll.setZb11(sheet[s].getCell(3,num+11).getContents());
							sjbbAll.setZb12(sheet[s].getCell(3,num+12).getContents());
							sjbbAll.setZb13(sheet[s].getCell(3,num+13).getContents());
							sjbbAll.setZb14(sheet[s].getCell(3,num+14).getContents());
							sjbbAll.setZb15(sheet[s].getCell(3,num+15).getContents());
							sjbbAll.setZb16(sheet[s].getCell(3,num+16).getContents());
							sjbbAll.setZb17(sheet[s].getCell(3,num+17).getContents());
							sjbbAll.setZb18(sheet[s].getCell(3,num+18).getContents());
							sjbbAll.setZb19(sheet[s].getCell(3,num+19).getContents());
							sjbbAll.setZb20(sheet[s].getCell(3,num+20).getContents());
							sjbbAll.setZb21(sheet[s].getCell(3,num+21).getContents());
							sjbbAll.setZb22(sheet[s].getCell(3,num+22).getContents());
							sjbbAll.setZb23(sheet[s].getCell(3,num+23).getContents());
							sjbbAll.setZb24(sheet[s].getCell(3,num+24).getContents());
						}
					}else if(sheetnames[s].indexOf("������֧���")!=-1){
						int r = sheet[s].getRows();//����
						int c = sheet[s].getColumns();//����
						if(r<21||c<4){
							result = "�����ļ��Ƿ���ȷ";
						}else{
							//�ҵ�ָ���
							int num=0;
							for(int i=0; i<sheet[s].getRows(); i++){
								if("��".equals(sheet[s].getCell(0,i).getContents())){
									num=i;
									break;
								}
							}
							sjbbAll.setZbs1(sheet[s].getCell(3,num+1).getContents());
							sjbbAll.setZbs2(sheet[s].getCell(3,num+2).getContents());
							sjbbAll.setZbs3(sheet[s].getCell(3,num+3).getContents());
							sjbbAll.setZbs4(sheet[s].getCell(3,num+4).getContents());
							sjbbAll.setZbs5(sheet[s].getCell(3,num+5).getContents());
							sjbbAll.setZbs6(sheet[s].getCell(3,num+6).getContents());
							sjbbAll.setZbs7(sheet[s].getCell(3,num+7).getContents());
							sjbbAll.setZbs8(sheet[s].getCell(3,num+8).getContents());
							sjbbAll.setZbs9(sheet[s].getCell(3,num+9).getContents());
							sjbbAll.setZbs10(sheet[s].getCell(3,num+10).getContents());
							sjbbAll.setZbs11(sheet[s].getCell(3,num+11).getContents());
							sjbbAll.setZbs12(sheet[s].getCell(3,num+12).getContents());
							sjbbAll.setZbs13(sheet[s].getCell(3,num+13).getContents());
							sjbbAll.setZbs14(sheet[s].getCell(3,num+14).getContents());
							sjbbAll.setZbs15(sheet[s].getCell(3,num+15).getContents());
							sjbbAll.setZbs16(sheet[s].getCell(3,num+16).getContents());
							sjbbAll.setZbs17(sheet[s].getCell(3,num+17).getContents());
							sjbbAll.setZbs18(sheet[s].getCell(3,num+18).getContents());
						}
					}else if(sheetnames[s].indexOf("�����������")!=-1){
						int r = sheet[s].getRows();//����
						int c = sheet[s].getColumns();//����
						if(r<28||c<4){
							result = "�����ļ��Ƿ���ȷ";
						}else{
							//�ҵ�ָ���
							int num=0;
							for(int i=0; i<sheet[s].getRows(); i++){
								if("��".equals(sheet[s].getCell(0,i).getContents())){
									num=i;
									break;
								}
							}
							sjbbAll.setZbt1(sheet[s].getCell(3,num+1).getContents());
							sjbbAll.setZbt2(sheet[s].getCell(3,num+2).getContents());
							sjbbAll.setZbt3(sheet[s].getCell(3,num+3).getContents());
							sjbbAll.setZbt4(sheet[s].getCell(3,num+4).getContents());
							sjbbAll.setZbt5(sheet[s].getCell(3,num+5).getContents());
							sjbbAll.setZbt6(sheet[s].getCell(3,num+6).getContents());
							sjbbAll.setZbt7(sheet[s].getCell(3,num+7).getContents());
							sjbbAll.setZbt8(sheet[s].getCell(3,num+8).getContents());
							sjbbAll.setZbt9(sheet[s].getCell(3,num+9).getContents());
							sjbbAll.setZbt10(sheet[s].getCell(3,num+10).getContents());
							sjbbAll.setZbt11(sheet[s].getCell(3,num+11).getContents());
							sjbbAll.setZbt12(sheet[s].getCell(3,num+12).getContents());
							sjbbAll.setZbt13(sheet[s].getCell(3,num+13).getContents());
							sjbbAll.setZbt14(sheet[s].getCell(3,num+14).getContents());
							sjbbAll.setZbt15(sheet[s].getCell(3,num+15).getContents());
							sjbbAll.setZbt16(sheet[s].getCell(3,num+16).getContents());
							sjbbAll.setZbt17(sheet[s].getCell(3,num+17).getContents());
							sjbbAll.setZbt18(sheet[s].getCell(3,num+18).getContents());
							sjbbAll.setZbt19(sheet[s].getCell(3,num+19).getContents());
							sjbbAll.setZbt20(sheet[s].getCell(3,num+20).getContents());
							sjbbAll.setZbt21(sheet[s].getCell(3,num+21).getContents());
							sjbbAll.setZbt22(sheet[s].getCell(3,num+22).getContents());
							sjbbAll.setZbt23(sheet[s].getCell(3,num+23).getContents());
							sjbbAll.setZbt24(sheet[s].getCell(3,num+24).getContents());
							sjbbAll.setZbt25(sheet[s].getCell(3,num+25).getContents());
						}
					}else if(sheetnames[s].indexOf("�����������")!=-1){
						int r = sheet[s].getRows();//����
						int c = sheet[s].getColumns();//����
						if(r<36||c<4){
							result = "�����ļ��Ƿ���ȷ";
						}else{
							//�ҵ�ָ���
							int num=0;
							for(int i=0; i<sheet[s].getRows(); i++){
								if("��".equals(sheet[s].getCell(0,i).getContents())){
									num=i;
									break;
								}
							}
							sjbbAll.setZbf1(sheet[s].getCell(3,num+1).getContents());
							sjbbAll.setZbf2(sheet[s].getCell(3,num+2).getContents());
							sjbbAll.setZbf3(sheet[s].getCell(3,num+3).getContents());
							sjbbAll.setZbf4(sheet[s].getCell(3,num+4).getContents());
							sjbbAll.setZbf5(sheet[s].getCell(3,num+5).getContents());
							sjbbAll.setZbf6(sheet[s].getCell(3,num+6).getContents());
							sjbbAll.setZbf7(sheet[s].getCell(3,num+7).getContents());
							sjbbAll.setZbf8(sheet[s].getCell(3,num+8).getContents());
							sjbbAll.setZbf9(sheet[s].getCell(3,num+9).getContents());
							sjbbAll.setZbf10(sheet[s].getCell(3,num+10).getContents());
							sjbbAll.setZbf11(sheet[s].getCell(3,num+11).getContents());
							sjbbAll.setZbf12(sheet[s].getCell(3,num+12).getContents());
							sjbbAll.setZbf13(sheet[s].getCell(3,num+13).getContents());
							sjbbAll.setZbf14(sheet[s].getCell(3,num+14).getContents());
							sjbbAll.setZbf15(sheet[s].getCell(3,num+15).getContents());
							sjbbAll.setZbf16(sheet[s].getCell(3,num+16).getContents());
							sjbbAll.setZbf17(sheet[s].getCell(3,num+17).getContents());
							sjbbAll.setZbf18(sheet[s].getCell(3,num+18).getContents());
							sjbbAll.setZbf19(sheet[s].getCell(3,num+19).getContents());
							sjbbAll.setZbf20(sheet[s].getCell(3,num+20).getContents());
							sjbbAll.setZbf21(sheet[s].getCell(3,num+21).getContents());
							sjbbAll.setZbf22(sheet[s].getCell(3,num+22).getContents());
							sjbbAll.setZbf23(sheet[s].getCell(3,num+23).getContents());
							sjbbAll.setZbf24(sheet[s].getCell(3,num+24).getContents());
							sjbbAll.setZbf25(sheet[s].getCell(3,num+25).getContents());
							sjbbAll.setZbf26(sheet[s].getCell(3,num+26).getContents());
							sjbbAll.setZbf27(sheet[s].getCell(3,num+27).getContents());
							sjbbAll.setZbf28(sheet[s].getCell(3,num+28).getContents());
							sjbbAll.setZbf29(sheet[s].getCell(3,num+29).getContents());
							sjbbAll.setZbf30(sheet[s].getCell(3,num+30).getContents());
							sjbbAll.setZbf31(sheet[s].getCell(3,num+31).getContents());
							sjbbAll.setZbf32(sheet[s].getCell(3,num+32).getContents());
							sjbbAll.setZbf33(sheet[s].getCell(3,num+33).getContents());
						}
					}else if(sheetnames[s].indexOf("��ƻ�����Ա")!=-1){
						int r = sheet[s].getRows();//����
						int c = sheet[s].getColumns();//����
						if(r<23||c<5){
							result = "�����ļ��Ƿ���ȷ";
						}else{
							//�ҵ�ָ���
							int num=0;
							for(int i=0; i<sheet[s].getRows(); i++){
								if("��".equals(sheet[s].getCell(0,i).getContents())){
									num=i;
									break;
								}
							}
							sjbbAll.setZbv1(sheet[s].getCell(4,num+1).getContents());
							sjbbAll.setZbv2(sheet[s].getCell(4,num+2).getContents());
							sjbbAll.setZbv3(sheet[s].getCell(4,num+3).getContents());
							sjbbAll.setZbv4(sheet[s].getCell(4,num+4).getContents());
							sjbbAll.setZbv5(sheet[s].getCell(4,num+5).getContents());
							sjbbAll.setZbv6(sheet[s].getCell(4,num+6).getContents());
							sjbbAll.setZbv7(sheet[s].getCell(4,num+7).getContents());
							sjbbAll.setZbv8(sheet[s].getCell(4,num+8).getContents());
							sjbbAll.setZbv9(sheet[s].getCell(4,num+9).getContents());
							sjbbAll.setZbv10(sheet[s].getCell(4,num+10).getContents());
							sjbbAll.setZbv11(sheet[s].getCell(4,num+11).getContents());
							sjbbAll.setZbv12(sheet[s].getCell(4,num+12).getContents());
							sjbbAll.setZbv13(sheet[s].getCell(4,num+13).getContents());
							sjbbAll.setZbv14(sheet[s].getCell(4,num+14).getContents());
							sjbbAll.setZbv15(sheet[s].getCell(4,num+15).getContents());
							sjbbAll.setZbv16(sheet[s].getCell(4,num+16).getContents());
							sjbbAll.setZbv17(sheet[s].getCell(4,num+17).getContents());
							sjbbAll.setZbv18(sheet[s].getCell(4,num+18).getContents());
							sjbbAll.setZbv19(sheet[s].getCell(4,num+19).getContents());
							sjbbAll.setZbv20(sheet[s].getCell(4,num+20).getContents());
						}
					}
				}
				//���ر���Դ���ͷ��ڴ�    
				wb.close();
			}
			//ɾ����ʱ�ļ�
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("sjbbAll", sjbbAll);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbAllEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ��ͨ���䲿����ƶ���ͳ�Ʊ����б�
	 * 
	 */
	protected void getJtyssjdytj(HttpServletRequest request,
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
			//��ѯ�б�
			ArrayList<SjbbJtyssjdytj> SjbbJtyssjdytjList = new ArrayList<SjbbJtyssjdytj>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//ͨ�������ѯ
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt='2' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbSjbbDao.querySjbbJtyssjdytjListByBtCount(srbt);
			SjbbJtyssjdytjList = bbsbSjbbDao.querySjbbJtyssjdytjListByBt(srbt, begin, pageSize);
			request.setAttribute("SjbbJtyssjdytjList", SjbbJtyssjdytjList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbJtyssjdytjList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void JtyssjdytjEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String id = request.getParameter("id");
			String result = request.getParameter("result");
			SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
			ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList = new ArrayList<SjbbJtyssjdytjZxm>();
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			if(!"".equals(id)&&id!=null){
				sjbbJtyssjdytj = bbsbSjbbDao.querySjbbJtyssjdytjByID(Integer.parseInt(id));
				//�����б�
				SjbbJtyssjdytjZxmList =  bbsbSjbbDao.querySjbbJtyssjdytjZxmByID(Integer.parseInt(id));
			}
			//Ԥ������
			else{
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm1 = new SjbbJtyssjdytjZxm();
				sjbbJtyssjdytjZxm1.setLb("������λ");
				sjbbJtyssjdytjZxm1.setMc("��ͨ�н�ͨ�����");
				sjbbJtyssjdytjZxm1.setFl("1");
				sjbbJtyssjdytjZxm1.setDwxz("");
				sjbbJtyssjdytjZxm1.setSjnr("");
				sjbbJtyssjdytjZxm1.setSjfs("");
				sjbbJtyssjdytjZxm1.setXzfs("");
				sjbbJtyssjdytjZxm1.setSjsx("");
				sjbbJtyssjdytjZxm1.setSsdw("");
				SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm1);
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm2 = new SjbbJtyssjdytjZxm();
				sjbbJtyssjdytjZxm2.setLb("������λ");
				sjbbJtyssjdytjZxm2.setFl("2");
				sjbbJtyssjdytjZxm2.setMc("");
				sjbbJtyssjdytjZxm2.setDwxz("");
				sjbbJtyssjdytjZxm2.setSjnr("");
				sjbbJtyssjdytjZxm2.setSjfs("");
				sjbbJtyssjdytjZxm2.setXzfs("");
				sjbbJtyssjdytjZxm2.setSjsx("");
				sjbbJtyssjdytjZxm2.setSsdw("");
				SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm2);
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm3 = new SjbbJtyssjdytjZxm();
				sjbbJtyssjdytjZxm3.setLb("����������ϵ��λ");
				sjbbJtyssjdytjZxm3.setFl("3");
				sjbbJtyssjdytjZxm3.setMc("");
				sjbbJtyssjdytjZxm3.setDwxz("");
				sjbbJtyssjdytjZxm3.setSjnr("");
				sjbbJtyssjdytjZxm3.setSjfs("");
				sjbbJtyssjdytjZxm3.setXzfs("");
				sjbbJtyssjdytjZxm3.setSjsx("");
				sjbbJtyssjdytjZxm3.setSsdw("");
				SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm3);
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm4 = new SjbbJtyssjdytjZxm();
				sjbbJtyssjdytjZxm4.setLb("������Ŀ");
				sjbbJtyssjdytjZxm4.setFl("4");
				sjbbJtyssjdytjZxm4.setMc("");
				sjbbJtyssjdytjZxm4.setDwxz("");
				sjbbJtyssjdytjZxm4.setSjnr("");
				sjbbJtyssjdytjZxm4.setSjfs("");
				sjbbJtyssjdytjZxm4.setXzfs("");
				sjbbJtyssjdytjZxm4.setSjsx("");
				sjbbJtyssjdytjZxm4.setSsdw("");
				SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm4);
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm5 = new SjbbJtyssjdytjZxm();
				sjbbJtyssjdytjZxm5.setLb("ר���ʽ�");
				sjbbJtyssjdytjZxm5.setFl("5");
				sjbbJtyssjdytjZxm5.setMc("");
				sjbbJtyssjdytjZxm5.setDwxz("");
				sjbbJtyssjdytjZxm5.setSjnr("");
				sjbbJtyssjdytjZxm5.setSjfs("");
				sjbbJtyssjdytjZxm5.setXzfs("");
				sjbbJtyssjdytjZxm5.setSjsx("");
				sjbbJtyssjdytjZxm5.setSsdw("");
				SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm5);
			}
			request.setAttribute("sjbbJtyssjdytj", sjbbJtyssjdytj);
			request.setAttribute("SjbbJtyssjdytjZxmList", SjbbJtyssjdytjZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbJtyssjdytjEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void JtyssjdytjSave(HttpServletRequest request,
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
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//���������޸�
			String SjbbJtyssjdytj_id = request.getParameter("SjbbJtyssjdytj_id");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:�ύ2������ݸ���
			SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
			sjbbJtyssjdytj.setCzr(UserInfo.getName());
			sjbbJtyssjdytj.setCzrID(UserInfo.getUsername());
			sjbbJtyssjdytj.setCzrdw(UserInfo.getCompany());
			sjbbJtyssjdytj.setCzrphone(czrphone);
			sjbbJtyssjdytj.setCzsj(data1);
			sjbbJtyssjdytj.setTjzt(tjzt);
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			//�������޸�����
			if(!"".equals(SjbbJtyssjdytj_id)&&SjbbJtyssjdytj_id!=null){
				sjbbJtyssjdytj.setId(Integer.parseInt(SjbbJtyssjdytj_id));
				bbsbSjbbDao.updateSjbbJtyssjdytj(sjbbJtyssjdytj);
				bbsbSjbbDao.deleteSjbbJtyssjdytjZxmByID(Integer.parseInt(SjbbJtyssjdytj_id));
			}else{
				SjbbJtyssjdytj_id = bbsbSjbbDao.insertSjbbJtyssjdytj(sjbbJtyssjdytj)+"";
			}
			//���������
			String lb[]=request.getParameterValues("lb"); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
			String mc[]=request.getParameterValues("mc");
			String dwxz[]=request.getParameterValues("dwxz");
			String sjnr[]=request.getParameterValues("sjnr");
			String sjfs[]=request.getParameterValues("sjfs");
			String xzfs[]=request.getParameterValues("xzfs");
			String sjsx[]=request.getParameterValues("sjsx");
			String ssdw[]=request.getParameterValues("ssdw");
			String fl[]=request.getParameterValues("fl");
			ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList = new ArrayList<SjbbJtyssjdytjZxm>();
			if(lb!=null){
				for(int i=0; i<lb.length; i++){
					SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = new SjbbJtyssjdytjZxm();
					sjbbJtyssjdytjZxm.setXmid(Integer.parseInt(SjbbJtyssjdytj_id));
					sjbbJtyssjdytjZxm.setLb(lb[i]);
					sjbbJtyssjdytjZxm.setMc(mc[i]);
					sjbbJtyssjdytjZxm.setDwxz(dwxz[i]);
					sjbbJtyssjdytjZxm.setSjnr(sjnr[i]);
					sjbbJtyssjdytjZxm.setSjfs(sjfs[i]);
					sjbbJtyssjdytjZxm.setXzfs(xzfs[i]);
					sjbbJtyssjdytjZxm.setSjsx(sjsx[i]);
					sjbbJtyssjdytjZxm.setSsdw(ssdw[i]);
					sjbbJtyssjdytjZxm.setFl(fl[i]);
					SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm);
				}
				bbsbSjbbDao.insertSjbbJtyssjdytjZxm(SjbbJtyssjdytjZxmList);
			}
			
//			request.setAttribute("result", result);request.getRequestDispatcher("BbsbSjbbServlet?action=JtyssjdytjEdit&id="+SjbbJtyssjdytj_id).forward(request,
//					response);
			request.getRequestDispatcher("BbsbSjbbServlet?action=getJtyssjdytj&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void JtyssjdytjShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
		ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList = new ArrayList<SjbbJtyssjdytjZxm>();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			sjbbJtyssjdytj = bbsbSjbbDao.querySjbbJtyssjdytjByID(ID);
			SjbbJtyssjdytjZxmList =  bbsbSjbbDao.querySjbbJtyssjdytjZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("sjbbJtyssjdytj", sjbbJtyssjdytj);
		request.setAttribute("SjbbJtyssjdytjZxmList", SjbbJtyssjdytjZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/AuditedStatements/SjbbJtyssjdytjShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void JtyssjdytjDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		bbsbSjbbDao.deleteSjbbJtyssjdytjById(Integer.parseInt(id));
		//ɾ������Ŀ��¼
		bbsbSjbbDao.deleteSjbbJtyssjdytjZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbSjbbServlet?action=getJtyssjdytj&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getSjbbJtyssjdytjExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String SjbbJtyssjdytj_id = request.getParameter("SjbbJtyssjdytj_id");
		SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
		ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList = new ArrayList<SjbbJtyssjdytjZxm>();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(SjbbJtyssjdytj_id)&&SjbbJtyssjdytj_id!=null){
			int ID = Integer.parseInt(SjbbJtyssjdytj_id);
			sjbbJtyssjdytj = bbsbSjbbDao.querySjbbJtyssjdytjByID(ID);
			SjbbJtyssjdytjZxmList =  bbsbSjbbDao.querySjbbJtyssjdytjZxmByID(ID);
			int s = SjbbJtyssjdytjZxmList.size();
			//����xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.�趨��response��������ԣ�
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.ȡ��response��OutputStreamʵ�����������ʵ����һ��WritableWorkbook����
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//�½�һ�ű�
				WritableSheet wsheet = wwb.createSheet("��ͨ���䲿����ƶ���ͳ�Ʊ�",1);
				// ��������Ϊ����,18����,�Ӵ�
				WritableFont font1 = new WritableFont(WritableFont.createFont("����"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//ˮƽ����
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//��ֱ����
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("����"), 10);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("����"), 10);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setWrap(true);//�Զ�����
				format4.setAlignment(jxl.format.Alignment.RIGHT);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("����"), 10);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setWrap(true);
				format5.setAlignment(jxl.format.Alignment.CENTRE);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("����"), 10);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setWrap(true);
				format6.setAlignment(jxl.format.Alignment.LEFT);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				//���ñ�ͷ
				Label label = new Label(0,0,"��ͨ���䲿����ƶ���ͳ�Ʊ� ",format1);
				wsheet.addCell(label);
				label = new Label(0,2,"���λ��"+sjbbJtyssjdytj.getCzrdw(),format2);
				wsheet.addCell(label);


				label = new Label(0,3,"��ƶ�������",format5);
				wsheet.addCell(label);
				label = new Label(2,3,"��λ����\n(��������ѡ��)",format5);
				wsheet.addCell(label);
				label = new Label(3,3,"2011��ȱ�����λ��������ƶ���չ�ڲ�������",format5);
				wsheet.addCell(label);
				label = new Label(6,3,"2011�����ƶ��������ƻ���������",format5);
				wsheet.addCell(label);
				
				label = new Label(0,4,"���",format5);
				wsheet.addCell(label);
				label = new Label(1,4,"����",format5);
				wsheet.addCell(label);
				label = new Label(3,4,"�������",format5);
				wsheet.addCell(label);
				label = new Label(4,4,"��Ʒ�ʽ\n(��������ѡ��)",format5);
				wsheet.addCell(label);
				label = new Label(5,4,"�н����ѡ��ʽ\n(��������ѡ��)",format5);
				wsheet.addCell(label);
				label = new Label(6,4,"��ƻ����������",format5);
				wsheet.addCell(label);
				label = new Label(7,4,"���ʵʩ��λ\n(��������ѡ��)",format5);
				wsheet.addCell(label);

				label = new Label(0,5,"��1��",format5);
				wsheet.addCell(label);
				label = new Label(1,5,"��2��",format5);
				wsheet.addCell(label);
				label = new Label(2,5,"��3��",format5);
				wsheet.addCell(label);
				label = new Label(3,5,"��4��",format5);
				wsheet.addCell(label);
				label = new Label(4,5,"��5��",format5);
				wsheet.addCell(label);
				label = new Label(5,5,"��6��",format5);
				wsheet.addCell(label);
				label = new Label(6,5,"��7��",format5);
				wsheet.addCell(label);
				label = new Label(7,5,"��8��",format5);
				wsheet.addCell(label);
				
				for(int i=0; i<s; i++){
					label = new Label(0,i+6,SjbbJtyssjdytjZxmList.get(i).getLb(),format5);
					wsheet.addCell(label);
					label = new Label(1,i+6,SjbbJtyssjdytjZxmList.get(i).getMc(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+6,SjbbJtyssjdytjZxmList.get(i).getDwxz(),format5);
					ArrayList list1 = new ArrayList(); 
					list1.add("����"); 
					list1.add("��ҵ");
					list1.add("��ҵ");
					WritableCellFeatures ws1 = new WritableCellFeatures();
					ws1.setDataValidationList(list1);
					label.setCellFeatures(ws1);
					wsheet.addCell(label);
					
					label = new Label(3,i+6,SjbbJtyssjdytjZxmList.get(i).getSjnr(),format5);
					wsheet.addCell(label);
					label = new Label(4,i+6,SjbbJtyssjdytjZxmList.get(i).getSjfs(),format5);
					ArrayList list2 = new ArrayList(); 
					list2.add("����");
					list2.add("ί���н����");
					WritableCellFeatures ws2 = new WritableCellFeatures();
					ws2.setDataValidationList(list2);
					label.setCellFeatures(ws2);
					wsheet.addCell(label);
					
					label = new Label(5,i+6,SjbbJtyssjdytjZxmList.get(i).getXzfs(),format5);
					ArrayList list3 = new ArrayList(); 
					list3.add("�����б�");
					list3.add("������̸��");
					list3.add("ָ��");
					list3.add("�����ȡ");
					WritableCellFeatures ws3 = new WritableCellFeatures();
					ws3.setDataValidationList(list3);
					label.setCellFeatures(ws3);
					wsheet.addCell(label);
					label = new Label(6,i+6,SjbbJtyssjdytjZxmList.get(i).getSjsx(),format5);
					wsheet.addCell(label);
					
					label = new Label(7,i+6,SjbbJtyssjdytjZxmList.get(i).getSsdw(),format5);
					ArrayList list4 = new ArrayList();
					list4.add("�����"); 
					list4.add("ʡ�����");
					list4.add("����ƾ�");
					list4.add("����ƾ�");
					WritableCellFeatures ws4 = new WritableCellFeatures();
					ws4.setDataValidationList(list4);
					label.setCellFeatures(ws4);
					wsheet.addCell(label);
				}
				
				label = new Label(0,s+6,"����ˣ�"+sjbbJtyssjdytj.getCzr(),format2);
				wsheet.addCell(label);
				label = new Label(3,s+6,"��ϵ�绰��"+sjbbJtyssjdytj.getCzrphone(),format2);
				wsheet.addCell(label);
				label = new Label(6,s+6,"������ڣ�"+sjbbJtyssjdytj.getCzsj().toString().substring(0, 10),format2);
				wsheet.addCell(label);
				label = new Label(0,s+7,"˵����",format2);
				wsheet.addCell(label);
				label = new Label(0,s+8,"1����������Ŀ�����б�����λ���мලְ��Ľ�����Ŀ��",format2);
				wsheet.addCell(label);
				label = new Label(0,s+9,"2��ר���ʽ�ġ���ƶ������ơ�����д������λ����ƻ��ؿ�չר���ʽ���������漰��λ���������δ��ר���ʽ������ƣ��������С�",format2);
				wsheet.addCell(label);
				label = new Label(0,s+10,"3���ڣ�3���е�λ����ѡ��������������ҵ������ҵ����",format2);
				wsheet.addCell(label);
				label = new Label(0,s+11,"4���ڣ�5����ѡ�����󡱻�ί���н������������ί���н���������ڵڣ�6������ѡ�񡰹����бꡱ�򡰾�����̸�С���ָ�����������ȡ����",format2);
				wsheet.addCell(label);
				label = new Label(0,s+12,"5���ڣ�8����ѡ������𡱻�ʡ�������������ƾ֡�������ƾ֡�",format2);
				wsheet.addCell(label);

				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(0, 3, 1, 3);
				wsheet.mergeCells(3, 3, 5, 3);
				wsheet.mergeCells(6, 3, 7, 3);
				
				wsheet.mergeCells(2, 3, 2, 4);
				
				//�����п�
				wsheet.setColumnView(0, 20);
				wsheet.setColumnView(1, 30);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 25);
				wsheet.setColumnView(4, 20);
				wsheet.setColumnView(5, 20);
				wsheet.setColumnView(6, 30);
				wsheet.setColumnView(7, 15);
				//�����п�
				wsheet.setRowView(0, 600, false);
				wsheet.setRowView(2, 400, false);
				wsheet.setRowView(3, 500, false);
				wsheet.setRowView(4, 500, false);
				for(int k=5; k<s+12; k++){
					wsheet.setRowView(k, 400, false);
				}
				
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("������Ϣ��(Excel��ʽ)ʱ����");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*��ȡExcel�ļ�������   
	 * @param file  ����ȡ���ļ�   
	 * @return   
	 */ 
	public void readJtyssjdytjExcel(HttpServletRequest request,
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
			//���������޸�
			String SjbbJtyssjdytj_id = request.getParameter("SjbbJtyssjdytj_id");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//����Workbook��������������    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}  
			SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
			ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList = new ArrayList<SjbbJtyssjdytjZxm>();
			if(!"".equals(SjbbJtyssjdytj_id)&&SjbbJtyssjdytj_id!=null){
				sjbbJtyssjdytj.setId(Integer.parseInt(SjbbJtyssjdytj_id));
			}
			//�����Workbook����֮�󣬾Ϳ���ͨ�����õ�Sheet��������������    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//����
				int c = sheet[0].getColumns();//����
				if(r<7||c<8){
					result = "�����ļ��Ƿ���ȷ";
				}else{
					for(int i=6; i<r-7; i++){
						SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = new SjbbJtyssjdytjZxm();
						sjbbJtyssjdytjZxm.setLb(sheet[0].getCell(0,i).getContents());
						sjbbJtyssjdytjZxm.setMc(sheet[0].getCell(1,i).getContents());
						sjbbJtyssjdytjZxm.setDwxz(sheet[0].getCell(2,i).getContents());
						sjbbJtyssjdytjZxm.setSjnr(sheet[0].getCell(3,i).getContents());
						sjbbJtyssjdytjZxm.setSjfs(sheet[0].getCell(4,i).getContents());
						sjbbJtyssjdytjZxm.setXzfs(sheet[0].getCell(5,i).getContents());
						sjbbJtyssjdytjZxm.setSjsx(sheet[0].getCell(6,i).getContents());
						sjbbJtyssjdytjZxm.setSsdw(sheet[0].getCell(7,i).getContents());
						//����
						SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm);
					}
				}
				//���ر���Դ���ͷ��ڴ�    
				wb.close();
			}
			//ɾ����ʱ�ļ�
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("sjbbJtyssjdytj", sjbbJtyssjdytj);
			request.setAttribute("SjbbJtyssjdytjZxmList", SjbbJtyssjdytjZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbJtyssjdytjEdit.jsp").forward(request,
					response);
		}
	}
	
	/*
	 * �м����أ���������ҵ��λ����ʵ�շ����߼����Բ�����б�
	 * 
	 */
	protected void getLssfzczcb(HttpServletRequest request,
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
			//��ѯ�б�
			ArrayList<SjbbLssfzc> SjbbLssfzcList = new ArrayList<SjbbLssfzc>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxjd = request.getParameter("cxjd");
			String cxcompany = request.getParameter("cxcompany");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//ͨ�������ѯ
				srbt="";
				cxyear = "";
				cxjd = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbSjbbDao.querySjbbLssfzcListByBtCount(srbt);
			SjbbLssfzcList = bbsbSjbbDao.querySjbbLssfzcListByBt(srbt, begin, pageSize);
			request.setAttribute("SjbbLssfzcList", SjbbLssfzcList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxjd", cxjd);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbLssfzcList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void lssfzczcbEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			//���������޸�
			String id = request.getParameter("id");
			String result = request.getParameter("result");
			SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
			ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			if(!"".equals(id)&&id!=null){
				sjbbLssfzc = bbsbSjbbDao.querySjbbLssfzcByID(Integer.parseInt(id));
				sjbbLssfzcZxmList = bbsbSjbbDao.querySjbbLssfzcZxmByID(Integer.parseInt(id));
			}
			request.setAttribute("sjbbLssfzc", sjbbLssfzc);
			request.setAttribute("sjbbLssfzcZxmList", sjbbLssfzcZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbLssfzcEdit.jsp").forward(request,
					response);
		}
	}
	

	
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void lssfzczcbSave(HttpServletRequest request,
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
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			java.sql.Date  data2=new java.sql.Date(date.getTime());
			//���������޸�
			String SjbbLssfzc_id = request.getParameter("SjbbLssfzc_id");
			String year= request.getParameter("year");
			String jd= request.getParameter("jd");
//			String sbsj = request.getParameter("sbsj");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:�ύ2������ݸ���3������
			//�ӱ�
			String zb1[]= request.getParameterValues("zb1");
			String zb2[]= request.getParameterValues("zb2");
			String zb3[]= request.getParameterValues("zb3");
			String zb4[]= request.getParameterValues("zb4");
			String zb5[]= request.getParameterValues("zb5");
			String zb6[]= request.getParameterValues("zb6");
			String zb7[]= request.getParameterValues("zb7");
			String zb8[]= request.getParameterValues("zb8");
			String zb9[]= request.getParameterValues("zb9");
			String zb10[]= request.getParameterValues("zb10");
			String zb11[]= request.getParameterValues("zb11");
			String zb12[]= request.getParameterValues("zb12");
			String zb13[]= request.getParameterValues("zb13");
			String zb14[]= request.getParameterValues("zb14");
			
			SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
			sjbbLssfzc.setYear(Integer.parseInt(year));
			sjbbLssfzc.setJd(Integer.parseInt(jd));
			sjbbLssfzc.setCzr(UserInfo.getName());
			sjbbLssfzc.setCzrID(UserInfo.getUsername());
			sjbbLssfzc.setCzrdw(UserInfo.getCompany());
			sjbbLssfzc.setCzrphone(czrphone);
			sjbbLssfzc.setCzsj(data1);
			sjbbLssfzc.setSbsj(data2);
			sjbbLssfzc.setTjzt(tjzt);
			int zbid = 0;
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			//�������޸�����
			if(!"".equals(SjbbLssfzc_id)&&SjbbLssfzc_id!=null){
				zbid = Integer.parseInt(SjbbLssfzc_id);
				sjbbLssfzc.setId(zbid);
				bbsbSjbbDao.updateSjbbLssfzc(sjbbLssfzc);
			}else{
				zbid = bbsbSjbbDao.insertSjbbLssfzc(sjbbLssfzc);
			}
			//ɾ��ԭ������Ŀ��¼������������
			if (zbid!=0){
				bbsbSjbbDao.deleteSjbbLssfzcZxmByID(zbid);
			}
			SjbbLssfzcZxm sjbbLssfzcZxm = new SjbbLssfzcZxm();
			if(zb1!=null){
				for(int i=0; i<zb1.length; i++){
					sjbbLssfzcZxm.setXmid(zbid);
					sjbbLssfzcZxm.setZb1(zb1[i]);
					sjbbLssfzcZxm.setZb2(zb2[i]);
					sjbbLssfzcZxm.setZb3(zb3[i]);
					sjbbLssfzcZxm.setZb4(zb4[i]);
					sjbbLssfzcZxm.setZb5(zb5[i]);
					sjbbLssfzcZxm.setZb6(zb6[i]);
					sjbbLssfzcZxm.setZb7(zb7[i]);
					sjbbLssfzcZxm.setZb8(zb8[i]);
					sjbbLssfzcZxm.setZb9(zb9[i]);
					sjbbLssfzcZxm.setZb10(zb10[i]);
					sjbbLssfzcZxm.setZb11(zb11[i]);
					sjbbLssfzcZxm.setZb12(zb12[i]);
					sjbbLssfzcZxm.setZb13(zb13[i]);
					sjbbLssfzcZxm.setZb14(zb14[i]);
					bbsbSjbbDao.insertSjbbLssfzcZxm(sjbbLssfzcZxm);
				}
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbSjbbServlet?action=getLssfzczcb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	
	/*
	 *  ���벵��
	 * 
	 */
	protected void lssfzczcbReturn(HttpServletRequest request,
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
			String id = request.getParameter("id");
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			//������Ա���û���
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			//�������޸�����
			if(!"".equals(id)&&id!=null){
				bbsbSjbbDao.updateSjbbLssfzcRet(id);
				String dxnr = UserInfo.getName()+"������һ����ʵ�շ����߼����Բ�����أ��뼰ʱ�鿴";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//���Ͷ���
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				//��ȡ��ǰʱ��
				java.util.Date  date=new java.util.Date();
				java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("δ����");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbSjbbServlet?action=getLssfzczcb&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	
	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void lssfzczcbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
		ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(id)&&id!=null){
			sjbbLssfzc = bbsbSjbbDao.querySjbbLssfzcByID(Integer.parseInt(id));
			sjbbLssfzcZxmList = bbsbSjbbDao.querySjbbLssfzcZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("sjbbLssfzc", sjbbLssfzc);
		request.setAttribute("sjbbLssfzcZxmList", sjbbLssfzcZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/AuditedStatements/SjbbLssfzcShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void lssfzczcbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		bbsbSjbbDao.deleteSjbbLssfzcById(Integer.parseInt(id));
		bbsbSjbbDao.deleteSjbbLssfzcZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbSjbbServlet?action=getLssfzczcb&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getSjbbLssfzczcbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("SjbbLssfzc_id");
		SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
		ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(id)&&id!=null){
			sjbbLssfzc = bbsbSjbbDao.querySjbbLssfzcByID(Integer.parseInt(id));
			sjbbLssfzcZxmList = bbsbSjbbDao.querySjbbLssfzcZxmByID(Integer.parseInt(id));
			//����xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.�趨��response��������ԣ�
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.ȡ��response��OutputStreamʵ�����������ʵ����һ��WritableWorkbook����
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				// ��������Ϊ����
				WritableFont font1 = new WritableFont(WritableFont.createFont("����"), 18);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//ˮƽ����
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//��ֱ����
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				
				WritableFont font5 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				//�½�һ�ű��������������
				WritableSheet wsheet = wwb.createSheet("��ʵ�շ������Բ��",0);
				Label label;
				if(sjbbLssfzc.getJd()==0){
					label = new Label(0,0,"�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��"+sjbbLssfzc.getYear()+"�꣩",format1);
				}else{
					label = new Label(0,0,"�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��"+sjbbLssfzc.getYear()+"��"+sjbbLssfzc.getJd()+"���ȣ�",format1);
				}
				wsheet.addCell(label);
				label = new Label(0,1,"���͵�λ��"+sjbbLssfzc.getCzrdw(),format2);
				wsheet.addCell(label);
				label = new Label(10,1,"����ʱ�䣺"+sjbbLssfzc.getSbsj(),format3);
				wsheet.addCell(label);
				
				label = new Label(0,2,"�շѵ�λ",format4);
				wsheet.addCell(label);
				label = new Label(1,2,"ʵ���շ����",format4);
				wsheet.addCell(label);
				label = new Label(6,2,"�շѼ���������ʵ���",format4);
				wsheet.addCell(label);
				label = new Label(10,2,"��������",format4);
				wsheet.addCell(label);
				label = new Label(13,2,"��ע",format4);
				wsheet.addCell(label);
				
				label = new Label(1,3,"�շ���Ŀ",format4);
				wsheet.addCell(label);
				label = new Label(2,3,"�շѱ�׼",format4);
				wsheet.addCell(label);
				label = new Label(3,3,"�շѶ���",format4);
				wsheet.addCell(label);
				label = new Label(4,3,"�շ�����",format4);
				wsheet.addCell(label);
				label = new Label(5,3,"�շѽ���Ԫ��",format4);
				wsheet.addCell(label);
				label = new Label(6,3,"�������",format4);
				wsheet.addCell(label);
				label = new Label(7,3,"�Żݷ�ʽ",format4);
				wsheet.addCell(label);
				label = new Label(8,3,"��������",format4);
				wsheet.addCell(label);
				label = new Label(9,3,"�������Ԫ��",format4);
				wsheet.addCell(label);
				label = new Label(10,3,"����δִ�����������շѹ涨",format4);
				wsheet.addCell(label);
				label = new Label(11,3,"�Ƿ�����޷��������շ�",format4);
				wsheet.addCell(label);
				label = new Label(12,3,"���޴�շ���Ϊ",format4);
				wsheet.addCell(label);
				
				for(int i=0; i<sjbbLssfzcZxmList.size(); i++){
					label = new Label(0,i+4,sjbbLssfzcZxmList.get(i).getZb1(),format5);
					wsheet.addCell(label);
					label = new Label(1,i+4,sjbbLssfzcZxmList.get(i).getZb2(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+4,sjbbLssfzcZxmList.get(i).getZb3(),format5);
					wsheet.addCell(label);
					label = new Label(3,i+4,sjbbLssfzcZxmList.get(i).getZb4(),format5);
					wsheet.addCell(label);
					label = new Label(4,i+4,sjbbLssfzcZxmList.get(i).getZb5(),format5);
					wsheet.addCell(label);
					label = new Label(5,i+4,sjbbLssfzcZxmList.get(i).getZb6(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+4,sjbbLssfzcZxmList.get(i).getZb7(),format5);
					wsheet.addCell(label);
					label = new Label(7,i+4,sjbbLssfzcZxmList.get(i).getZb8(),format5);
					wsheet.addCell(label);
					label = new Label(8,i+4,sjbbLssfzcZxmList.get(i).getZb9(),format5);
					wsheet.addCell(label);
					label = new Label(9,i+4,sjbbLssfzcZxmList.get(i).getZb10(),format5);
					wsheet.addCell(label);
					label = new Label(10,i+4,sjbbLssfzcZxmList.get(i).getZb11(),format5);
					wsheet.addCell(label);
					label = new Label(11,i+4,sjbbLssfzcZxmList.get(i).getZb12(),format5);
					wsheet.addCell(label);
					label = new Label(12,i+4,sjbbLssfzcZxmList.get(i).getZb13(),format5);
					wsheet.addCell(label);
					label = new Label(13,i+4,sjbbLssfzcZxmList.get(i).getZb14(),format5);
					wsheet.addCell(label);
				}
				label = new Label(0,sjbbLssfzcZxmList.size()+4,"˵����1����������������������λ���ݣ���ȷ��д������ÿ�����¸��µ�10��ǰ������۾��շѹ�����7��10�ձ�1-6�·����ݣ�����۾��շѴ����䣺ntwjjsfc@163.com����ϵ�绰��85099592,85216544��",format2);
				wsheet.addCell(label);
				label = new Label(0,sjbbLssfzcZxmList.size()+5,"2�����շ�ʵ���㱨���ơ�",format2);
				wsheet.addCell(label);
				label = new Label(0,sjbbLssfzcZxmList.size()+6,"����ˣ�"+sjbbLssfzc.getCzr()+"      ��ϵ�绰��"+sjbbLssfzc.getCzrphone(),format2);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 13, 0);
				wsheet.mergeCells(10, 1, 12, 1);
				wsheet.mergeCells(0, 2, 0, 3);
				wsheet.mergeCells(1, 2, 5, 2);
				wsheet.mergeCells(6, 2, 9, 2);
				wsheet.mergeCells(10, 2, 12, 2);
				wsheet.mergeCells(13, 2, 13, 3);
				wsheet.mergeCells(0, sjbbLssfzcZxmList.size()+4, 13, sjbbLssfzcZxmList.size()+4);
				wsheet.mergeCells(0, sjbbLssfzcZxmList.size()+5, 13, sjbbLssfzcZxmList.size()+5);
				wsheet.mergeCells(0, sjbbLssfzcZxmList.size()+6, 13, sjbbLssfzcZxmList.size()+6);
				//�����п�
				wsheet.setColumnView(0, 15);
				wsheet.setColumnView(1, 30);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 10);
				wsheet.setColumnView(4, 40);
				wsheet.setColumnView(5, 18);
				wsheet.setColumnView(6, 10);
				wsheet.setColumnView(7, 10);
				wsheet.setColumnView(8, 10);
				wsheet.setColumnView(9, 18);
				wsheet.setColumnView(10, 20);
				wsheet.setColumnView(11, 20);
				wsheet.setColumnView(12, 20);
				wsheet.setColumnView(13, 10);
				//�����п�
				wsheet.setRowView(0, 800, false);
				wsheet.setRowView(1, 800, false);
				wsheet.setRowView(2, 800, false);
				wsheet.setRowView(3, 800, false);
				for(int k=0; k<sjbbLssfzcZxmList.size()+3; k++){
					wsheet.setRowView(k+4, 800, false);
				}
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("������Ϣ��(Excel��ʽ)ʱ����");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*��ȡExcel�ļ�������   
	 * @param file  ����ȡ���ļ�   
	 * @return   
	 */ 
	public void readLssfzczcbExcel(HttpServletRequest request,
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
			//���������޸�
			String SjbbLssfzc_id = request.getParameter("SjbbLssfzc_id");
			String year = request.getParameter("year");
			String jd = request.getParameter("jd");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//����Workbook��������������    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}  
			SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
			ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
			sjbbLssfzc.setYear(Integer.parseInt(year));
			sjbbLssfzc.setJd(Integer.parseInt(jd));
			if(!"".equals(SjbbLssfzc_id)&&SjbbLssfzc_id!=null){
				sjbbLssfzc.setId(Integer.parseInt(SjbbLssfzc_id));
			}
			//�����Workbook����֮�󣬾Ϳ���ͨ�����õ�Sheet��������������    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//����
				int c = sheet[0].getColumns();//����
				if(r<5||c<14){
					result = "�����ļ��Ƿ���ȷ";
				}else{
					for(int i=4;i<r-3; i++){
						SjbbLssfzcZxm sjbbLssfzcZxm = new SjbbLssfzcZxm();
						sjbbLssfzcZxm.setZb1(sheet[0].getCell(0,i).getContents());
						sjbbLssfzcZxm.setZb2(sheet[0].getCell(1,i).getContents());
						sjbbLssfzcZxm.setZb3(sheet[0].getCell(2,i).getContents());
						sjbbLssfzcZxm.setZb4(sheet[0].getCell(3,i).getContents());
						sjbbLssfzcZxm.setZb5(sheet[0].getCell(4,i).getContents());
						sjbbLssfzcZxm.setZb6(sheet[0].getCell(5,i).getContents());
						sjbbLssfzcZxm.setZb7(sheet[0].getCell(6,i).getContents());
						sjbbLssfzcZxm.setZb8(sheet[0].getCell(7,i).getContents());
						sjbbLssfzcZxm.setZb9(sheet[0].getCell(8,i).getContents());
						sjbbLssfzcZxm.setZb10(sheet[0].getCell(9,i).getContents());
						sjbbLssfzcZxm.setZb11(sheet[0].getCell(10,i).getContents());
						sjbbLssfzcZxm.setZb12(sheet[0].getCell(11,i).getContents());
						sjbbLssfzcZxm.setZb13(sheet[0].getCell(12,i).getContents());
						sjbbLssfzcZxm.setZb14(sheet[0].getCell(13,i).getContents());
						sjbbLssfzcZxmList.add(sjbbLssfzcZxm);
					}
				}
				//���ر���Դ���ͷ��ڴ�    
				wb.close();
			}
			//ɾ����ʱ�ļ�
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("sjbbLssfzc", sjbbLssfzc);
			request.setAttribute("sjbbLssfzcZxmList", sjbbLssfzcZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbLssfzcEdit.jsp").forward(request,
					response);
		}
	}

	/*
	 * �м����أ���������ҵ��λ����ʵ�շ����߼����Բ�����б�
	 * 
	 */
	protected void getLssfzczcbHzb(HttpServletRequest request,
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
			//��ѯ�б�
			ArrayList<SjbbLssfzcHzb> SjbbLssfzcHzbList = new ArrayList<SjbbLssfzcHzb>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxjd = request.getParameter("cxjd");
			//��Ϊ1����Ӳ˵�����
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//ͨ�������ѯ
				srbt="";
				cxyear = "";
				cxjd = "";
			}
			countAll = bbsbSjbbDao.querySjbbLssfzcListByBtCount(srbt);
			SjbbLssfzcHzbList = bbsbSjbbDao.querySjbbLssfzcHzbListByBt(srbt, begin, pageSize);
			request.setAttribute("SjbbLssfzcHzbList", SjbbLssfzcHzbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxjd", cxjd);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/AuditedStatements/SjbbLssfzcHzbList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  ��ʵ�շ����߼����Բ����ܱ�����������
	 * 
	 */
	protected void LssfzczcbHzbSave(HttpServletRequest request,
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
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//���������ݻ��߸��¾�����
			String SjbbLssfzcHzb_id = request.getParameter("SjbbLssfzcHzb_id");
			String year = request.getParameter("year");
			String jd = request.getParameter("jd");
			BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
			//����
			SjbbLssfzcHzb sjbbLssfzcHzb = new SjbbLssfzcHzb();
			sjbbLssfzcHzb.setYear(Integer.parseInt(year));
			sjbbLssfzcHzb.setJd(Integer.parseInt(jd));
			sjbbLssfzcHzb.setCzr(UserInfo.getName());
			sjbbLssfzcHzb.setCzrID(UserInfo.getUsername());
			sjbbLssfzcHzb.setCzrdw(UserInfo.getCompany());
			sjbbLssfzcHzb.setCzrphone(UserInfo.getWorkphone());
			sjbbLssfzcHzb.setCzsj(data1);
			int zbid = 0;
			//�������޸�����
			if(!"".equals(SjbbLssfzcHzb_id)&&SjbbLssfzcHzb_id!=null){
				zbid = Integer.parseInt(SjbbLssfzcHzb_id);
				sjbbLssfzcHzb.setId(zbid);
				bbsbSjbbDao.updateSjbbLssfzcHzb(sjbbLssfzcHzb);
			}else{
				zbid = bbsbSjbbDao.insertSjbbLssfzcHzb(sjbbLssfzcHzb);
			}

			if(!"".equals(year)&&year!=null&&!"".equals(jd)&&jd!=null){
				//ɾ��ԭ������Ŀ��¼������������
				if (zbid!=0){
					bbsbSjbbDao.deleteSjbbLssfzcZxmHzbByID(zbid);
				}
				//����ʱ���ѯ���з�������������ID
				String IDStr = "";
				if(!"0".equals(jd)){
					//������
					IDStr = bbsbSjbbDao.querySjbbLssfzcBySj(year,jd);
				}else{
					//�����
					IDStr = bbsbSjbbDao.querySjbbLssfzcByYear(year);
				}
				//���ӱ���������IDƥ������ݲ��
				ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
				if(!"".equals(IDStr)){
					sjbbLssfzcZxmList = bbsbSjbbDao.querySjbbLssfzcZxmByXmidList(IDStr);
					bbsbSjbbDao.insertSjbbLssfzcZxmHzb(zbid,sjbbLssfzcZxmList);
				}
			}else{
				result = "���ڴ�������ʧ��";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbSjbbServlet?action=getLssfzczcbHzb&flag=1").forward(request,
					response);
			
		}
	}
	/*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void LssfzczcbHzbShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		SjbbLssfzcHzb sjbbLssfzcHzb = new SjbbLssfzcHzb();
		ArrayList<SjbbLssfzcZxmHzb> sjbbLssfzcZxmHzbList = new ArrayList<SjbbLssfzcZxmHzb>();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(id)&&id!=null){
			sjbbLssfzcHzb = bbsbSjbbDao.querySjbbLssfzcHzbByID(Integer.parseInt(id));
			sjbbLssfzcZxmHzbList = bbsbSjbbDao.querySjbbLssfzcZxmHzbByID(Integer.parseInt(id));
		}
		request.setAttribute("sjbbLssfzcHzb", sjbbLssfzcHzb);
		request.setAttribute("sjbbLssfzcZxmHzbList", sjbbLssfzcZxmHzbList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/AuditedStatements/SjbbLssfzcHzbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void LssfzczcbHzbDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		bbsbSjbbDao.deleteSjbbLssfzcHzbById(Integer.parseInt(id));
		bbsbSjbbDao.deleteSjbbLssfzcZxmHzbByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbSjbbServlet?action=getLssfzczcbHzb&flag=1");
		rd.forward(request, response);
		return;
	}
	
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getSjbbLssfzczcbHzbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("SjbbLssfzcHzb_id");
		SjbbLssfzcHzb sjbbLssfzcHzb = new SjbbLssfzcHzb();
		ArrayList<SjbbLssfzcZxmHzb> sjbbLssfzcZxmHzbList = new ArrayList<SjbbLssfzcZxmHzb>();
		BbsbSjbbDao bbsbSjbbDao = new BbsbSjbbDao();
		if(!"".equals(id)&&id!=null){
			sjbbLssfzcHzb = bbsbSjbbDao.querySjbbLssfzcHzbByID(Integer.parseInt(id));
			sjbbLssfzcZxmHzbList = bbsbSjbbDao.querySjbbLssfzcZxmHzbByID(Integer.parseInt(id));
			//����xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.�趨��response��������ԣ�
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.ȡ��response��OutputStreamʵ�����������ʵ����һ��WritableWorkbook����
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				// ��������Ϊ����
				WritableFont font1 = new WritableFont(WritableFont.createFont("����"), 18);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//ˮƽ����
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//��ֱ����
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setAlignment(jxl.format.Alignment.CENTRE);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				
				WritableFont font5 = new WritableFont(WritableFont.createFont("����"), 11);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 

				//�½�һ�ű��������������
				WritableSheet wsheet = wwb.createSheet("��ʵ�շ������Բ��",0);
				Label label;
				if(sjbbLssfzcHzb.getJd()==0){
					label = new Label(0,0,"�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��"+sjbbLssfzcHzb.getYear()+"�꣩",format1);
				}else{
					label = new Label(0,0,"�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��"+sjbbLssfzcHzb.getYear()+"��"+sjbbLssfzcHzb.getJd()+"���ȣ�",format1);
				}
				wsheet.addCell(label);
				label = new Label(0,1,"���͵�λ����ͨ�н�ͨ�����",format2);
				wsheet.addCell(label);
				label = new Label(10,1,"����ʱ�䣺"+sjbbLssfzcHzb.getCzsj().toString().substring(0,10),format3);
				wsheet.addCell(label);
				
				label = new Label(0,2,"�շѵ�λ",format4);
				wsheet.addCell(label);
				label = new Label(1,2,"ʵ���շ����",format4);
				wsheet.addCell(label);
				label = new Label(6,2,"�շѼ���������ʵ���",format4);
				wsheet.addCell(label);
				label = new Label(10,2,"��������",format4);
				wsheet.addCell(label);
				label = new Label(13,2,"��ע",format4);
				wsheet.addCell(label);
				
				label = new Label(1,3,"�շ���Ŀ",format4);
				wsheet.addCell(label);
				label = new Label(2,3,"�շѱ�׼",format4);
				wsheet.addCell(label);
				label = new Label(3,3,"�շѶ���",format4);
				wsheet.addCell(label);
				label = new Label(4,3,"�շ�����",format4);
				wsheet.addCell(label);
				label = new Label(5,3,"�շѽ���Ԫ��",format4);
				wsheet.addCell(label);
				label = new Label(6,3,"�������",format4);
				wsheet.addCell(label);
				label = new Label(7,3,"�Żݷ�ʽ",format4);
				wsheet.addCell(label);
				label = new Label(8,3,"��������",format4);
				wsheet.addCell(label);
				label = new Label(9,3,"�������Ԫ��",format4);
				wsheet.addCell(label);
				label = new Label(10,3,"����δִ�����������շѹ涨",format4);
				wsheet.addCell(label);
				label = new Label(11,3,"�Ƿ�����޷��������շ�",format4);
				wsheet.addCell(label);
				label = new Label(12,3,"���޴�շ���Ϊ",format4);
				wsheet.addCell(label);
				
				for(int i=0; i<sjbbLssfzcZxmHzbList.size(); i++){
					label = new Label(0,i+4,sjbbLssfzcZxmHzbList.get(i).getZb1(),format5);
					wsheet.addCell(label);
					label = new Label(1,i+4,sjbbLssfzcZxmHzbList.get(i).getZb2(),format5);
					wsheet.addCell(label);
					label = new Label(2,i+4,sjbbLssfzcZxmHzbList.get(i).getZb3(),format5);
					wsheet.addCell(label);
					label = new Label(3,i+4,sjbbLssfzcZxmHzbList.get(i).getZb4(),format5);
					wsheet.addCell(label);
					label = new Label(4,i+4,sjbbLssfzcZxmHzbList.get(i).getZb5(),format5);
					wsheet.addCell(label);
					label = new Label(5,i+4,sjbbLssfzcZxmHzbList.get(i).getZb6(),format5);
					wsheet.addCell(label);
					label = new Label(6,i+4,sjbbLssfzcZxmHzbList.get(i).getZb7(),format5);
					wsheet.addCell(label);
					label = new Label(7,i+4,sjbbLssfzcZxmHzbList.get(i).getZb8(),format5);
					wsheet.addCell(label);
					label = new Label(8,i+4,sjbbLssfzcZxmHzbList.get(i).getZb9(),format5);
					wsheet.addCell(label);
					label = new Label(9,i+4,sjbbLssfzcZxmHzbList.get(i).getZb10(),format5);
					wsheet.addCell(label);
					label = new Label(10,i+4,sjbbLssfzcZxmHzbList.get(i).getZb11(),format5);
					wsheet.addCell(label);
					label = new Label(11,i+4,sjbbLssfzcZxmHzbList.get(i).getZb12(),format5);
					wsheet.addCell(label);
					label = new Label(12,i+4,sjbbLssfzcZxmHzbList.get(i).getZb13(),format5);
					wsheet.addCell(label);
					label = new Label(13,i+4,sjbbLssfzcZxmHzbList.get(i).getZb14(),format5);
					wsheet.addCell(label);
				}
				label = new Label(0,sjbbLssfzcZxmHzbList.size()+4,"˵����1����������������������λ���ݣ���ȷ��д������ÿ�����¸��µ�10��ǰ������۾��շѹ�����7��10�ձ�1-6�·����ݣ�����۾��շѴ����䣺ntwjjsfc@163.com����ϵ�绰��85099592,85216544��",format2);
				wsheet.addCell(label);
				label = new Label(0,sjbbLssfzcZxmHzbList.size()+5,"2�����շ�ʵ���㱨���ơ�",format2);
				wsheet.addCell(label);
				label = new Label(0,sjbbLssfzcZxmHzbList.size()+6,"����ˣ�"+sjbbLssfzcHzb.getCzr()+"      ��ϵ�绰��"+sjbbLssfzcHzb.getCzrphone(),format2);
				wsheet.addCell(label);
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, 13, 0);
				wsheet.mergeCells(10, 1, 12, 1);
				wsheet.mergeCells(0, 2, 0, 3);
				wsheet.mergeCells(1, 2, 5, 2);
				wsheet.mergeCells(6, 2, 9, 2);
				wsheet.mergeCells(10, 2, 12, 2);
				wsheet.mergeCells(13, 2, 13, 3);
				wsheet.mergeCells(0, sjbbLssfzcZxmHzbList.size()+4, 13, sjbbLssfzcZxmHzbList.size()+4);
				wsheet.mergeCells(0, sjbbLssfzcZxmHzbList.size()+5, 13, sjbbLssfzcZxmHzbList.size()+5);
				wsheet.mergeCells(0, sjbbLssfzcZxmHzbList.size()+6, 13, sjbbLssfzcZxmHzbList.size()+6);
				//�����п�
				wsheet.setColumnView(0, 15);
				wsheet.setColumnView(1, 30);
				wsheet.setColumnView(2, 10);
				wsheet.setColumnView(3, 10);
				wsheet.setColumnView(4, 40);
				wsheet.setColumnView(5, 18);
				wsheet.setColumnView(6, 10);
				wsheet.setColumnView(7, 10);
				wsheet.setColumnView(8, 10);
				wsheet.setColumnView(9, 18);
				wsheet.setColumnView(10, 20);
				wsheet.setColumnView(11, 20);
				wsheet.setColumnView(12, 20);
				wsheet.setColumnView(13, 10);
				//�����п�
				wsheet.setRowView(0, 800, false);
				wsheet.setRowView(1, 800, false);
				wsheet.setRowView(2, 800, false);
				wsheet.setRowView(3, 800, false);
				for(int k=0; k<sjbbLssfzcZxmHzbList.size()+3; k++){
					wsheet.setRowView(k+4, 800, false);
				}
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("������Ϣ��(Excel��ʽ)ʱ����");
	        	e.printStackTrace();
	        }
	        return;
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

	private String CountNumber(String str1, String str2){
		String strRes = str1;
		if(!"".equals(str1)&&str1!=null){
			if(!"".equals(str2)&&str2!=null){
				strRes =  Float.parseFloat(str1)+Float.parseFloat(str2)+"";
				if(strRes.indexOf(".") > 0){
					strRes = strRes.replaceAll("0+?$", "");//ȥ�������0
					strRes = strRes.replaceAll("[.]$", "");//�����һλ��.��ȥ��  
				}
			}
			return strRes;
		}else{
			return str2;
		}
	}
}
