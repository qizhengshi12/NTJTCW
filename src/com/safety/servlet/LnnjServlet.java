package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.safety.dao.LnnjDao;
import com.safety.dao.MenuDao;
import com.safety.entity.CglibBean;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Njfb;
import com.safety.entity.NjfbConsolidations;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class LnnjServlet  extends HttpServlet{
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
		if("getNjfb".equals(action)){//������������б�
			getNjfb(request,response);
		}else if("query".equals(action)){
			queryLnnj(request,response);
		}else if("getNjgl".equals(action)){//������������б�
			getNjgl(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("lnnjEdit".equals(action)){//�������޸�ҳ�棨ͨ�ã�
			LnnjEdit(request,response);
		}else if("lnnjSave".equals(action)){///����ҳ�棨ͨ�ã�
			LnnjSave(request,response);
		}else if("lnnjShow".equals(action)){//�鿴��ͨ�ã�
			LnnjShow(request,response);
		}else if("lnnjDelete".equals(action)){//ɾ����ͨ�ã�
			LnnjDelete(request,response);
		}else if("getNjfbExcel".equals(action)){//����EXCEL��ͨ�ã�
			getNjfbExcel(request,response);
		}else if("readNjfbExcel".equals(action)){//����EXCEL��ͨ�ã�
			readNjfbExcel(request,response);
		}
	}
	/*
	 *  ˢ�²˵���
	 * 
	 */
	protected void getNjfb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNjfb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_njfb");
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfb.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  ��������б�
	 * 
	 */
	protected void queryLnnj(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���
		String result = request.getParameter("result");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNjfb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('�޲�ѯȨ�ޣ�');</script>");
			
		}else{
			//ͨ���˵����ID�Ų�ѯ
			String MenuId = request.getParameter("MenuId");
			//ͨ��������ѯ
			String srbt = request.getParameter("srbt");
			//��ѯ�б�
			ArrayList<Njfb> NjfbList = new ArrayList<Njfb>();
			//��ҳ;
//			String flag = "0";//������ڱ�־�������жϲ�ѯ��ʽ
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			/*���ò�ѯ����*/
			LnnjDao lnnjDao = new LnnjDao();
			//ͨ���������ѯ
			if(!"".equals(MenuId)&&MenuId!=null){
				//��ȡ�˵�ID���б�
//				MenuDao menuDao =new MenuDao();
//				String idList = menuDao.QueryMenuById("node_njfb", MenuId);
				if(!"".equals(MenuId)&&MenuId!=null){
					countAll = lnnjDao.queryNjfbByIdListCount(MenuId);
					NjfbList = lnnjDao.queryNjfbByIdList(MenuId, begin, pageSize);
				}
			}
			//ͨ���������ݲ�ѯ
			else {
				countAll = lnnjDao.queryNjfbByBtCount(srbt);
				NjfbList = lnnjDao.queryNjfbByBt(srbt, begin, pageSize);
//				flag = "1";
			}
			
			request.setAttribute("NjfbList", NjfbList);
//			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			request.setAttribute("MenuId", MenuId);//ǰ̨ҳ�������ID
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
//			request.setAttribute("flag", flag);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbList.jsp").forward(request,
					response);
		}
	}



	//TODO
	/*
	 *  �����������ҳ��
	 * 
	 */
	protected void getNjgl(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNjgl().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_njfb");
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njgl.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �����˵�
	 * 
	 */
	protected void insertMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String bbls = request.getParameter("bbls");
		String ParentID = request.getParameter("ParentID");//��ȡ���ڵ�ID
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
			String result= "�����ɹ�";
			MenuDao menuDao = new MenuDao();
			menuDao.InsertMenu("node_njfb", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),bbls);
			if(!"".equals(bbls)&&!"null".equals(bbls)&&bbls!=null){
				LnnjDao lnnjDao = new LnnjDao();
				//��ѯ�Ƿ�����Ӧ�������б�
				boolean res = lnnjDao.queryNjfbLs(Integer.parseInt(bbls));
				if(!res){
					//������Ӧ���������б����ݣ������´��½���ʱ���ظ�����
					lnnjDao.insertNjfbLs(Integer.parseInt(bbls));
					//������Ӧ�������б�
					lnnjDao.createNjfbByBbls(Integer.parseInt(bbls));
				}
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjgl&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 * �޸Ĳ˵�
	 * 
	 */
	protected void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String bbls = request.getParameter("bbls");
		String id = request.getParameter("id");
		String result= "�޸ĳɹ�";
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
			MenuDao menuDao = new MenuDao();
			menuDao.UpdateMenuNameById("node_njfb", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),bbls);
			
			RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjgl&result="+result);
			rd.forward(request, response);
		}
	}

	/*
	 * ɾ���˵�
	 * 
	 */
	protected void deleteMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String result = "�޷�ɾ����������ɾ�������ļ���";
		MenuDao menuDao = new MenuDao();
		LnnjDao lnnjDao = new LnnjDao();
		int count = 0;
		int countAll = lnnjDao.queryNjfbByIdListCount(id);
		if(countAll==0){
			count = menuDao.QueryCountByFatherId("node_njfb", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_njfb", Integer.parseInt(id));
				result = "ɾ���ɹ�";
			}else{
				result = "�޷�ɾ����������ɾ���Ӳ˵����ݣ�";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjgl&result="+result);
		rd.forward(request, response);
		return ;
	}
	/*
	 *  �����������޸�ҳ��
	 * 
	 */
	protected void LnnjEdit(HttpServletRequest request,
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
			String bbls = request.getParameter("bbls");//��������
			String fatherid = request.getParameter("fatherid");
			String result = request.getParameter("result");
			LnnjDao lnnjDao = new LnnjDao();
			
			ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
			Njfb njfb = new Njfb();
			ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
			if(!"".equals(id)&&id!=null){
				njfb = lnnjDao.queryNjfbById(Integer.parseInt(id));
				//�ϲ����б�
				njfbCLList = lnnjDao.queryNjfbCLById(Integer.parseInt(id));
				//����Ŀ�б�
				lnnjDTList =  lnnjDao.queryNjfbDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls));
			}
			request.setAttribute("njfb", njfb);
			request.setAttribute("lnnjDTList", lnnjDTList);
			request.setAttribute("njfbCLList", njfbCLList);
			request.setAttribute("bbls", bbls);
			request.setAttribute("fatherid", fatherid);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void LnnjSave(HttpServletRequest request,
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
			String njID = request.getParameter("njID");
			String bt = request.getParameter("bt");
			String year = request.getParameter("year");
			String fatherid = request.getParameter("fatherid");
			String bbls = request.getParameter("bbls");
			Njfb njfb = new Njfb();
			njfb.setBt(bt);
			njfb.setYear(Integer.parseInt(year));
			njfb.setFatherid(fatherid);
			int num = Integer.parseInt(bbls);
			njfb.setBbls(num);
			njfb.setCzr(UserInfo.getName());
			njfb.setCzrID(UserInfo.getUsername());
			njfb.setCzrdw(UserInfo.getCompany());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			njfb.setCzsj(data1);
			LnnjDao lnnjDao = new LnnjDao();
			int xmid = 0;
			//�������޸�����
			if(!"".equals(njID)&&njID!=null){
				xmid = Integer.parseInt(njID);
				njfb.setId(xmid);
				lnnjDao.updateNjfb(njfb);
			}else{
				xmid = lnnjDao.insertNjfb(njfb);
//				MenuDao menuDao = new MenuDao();
//				menuDao.InsertMenuContextById("node_njfb", fatherid, xmid,data1,UserInfo.getName(),UserInfo.getUsername());
			}
			//ɾ��ԭ������Ŀ��¼������������
			if (!"".equals(njID)&&njID!=null){
				lnnjDao.deleteNjfbDTByIDList(xmid,num);
			}
			HashMap propertyMap = new HashMap();
			// �������Ա����       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("xmid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=num; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
//			CglibBean bean = new CglibBean(propertyMap);
			//��ֵ
			String zb1[]=request.getParameterValues("zb1"); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
			if(zb1!=null){
				for(int j=0; j<zb1.length; j++){
					CglibBean bean = new CglibBean(propertyMap);
					bean.setValue("zb1", zb1[j]);
					bean.setValue("xmid", xmid);
					lnnjDTList.add(bean);
				}
				for(int i=2; i<=num; i++){
					String zb[]=request.getParameterValues("zb"+i); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
					for(int j=0; j<zb.length; j++){
						lnnjDTList.get(j).setValue("zb"+i, zb[j]);
					}
				}
	
				lnnjDao.insertNjfbDT(lnnjDTList,num);
			}
			//�洢���кϲ�������
			String column1[]=request.getParameterValues("column1");//��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������ 
			String row1[]=request.getParameterValues("row1");
			String column2[]=request.getParameterValues("column2");
			String row2[]=request.getParameterValues("row2");
			//ɾ��ԭ�����ݣ�����������
			if (!"".equals(njID)&&njID!=null){
				lnnjDao.deleteNjfbJTGKByIDList(xmid);
			}
			ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
			if(column1!=null){
				for(int i=0; i<column1.length; i++){
					NjfbConsolidations njfbCL = new NjfbConsolidations();
					njfbCL.setXmid(xmid);
					if("".equals(column1[i]))column1[i]="-1";
					njfbCL.setColumn1(Integer.parseInt(column1[i]));
					if("".equals(row1[i]))row1[i]="-1";
					njfbCL.setRow1(Integer.parseInt(row1[i]));
					if("".equals(column2[i]))column2[i]="-1";
					njfbCL.setColumn2(Integer.parseInt(column2[i]));
					if("".equals(row2[i]))row2[i]="-1";
					njfbCL.setRow2(Integer.parseInt(row2[i]));
					njfbCLList.add(njfbCL);
				}
				lnnjDao.insertNjfbJTGK(njfbCLList);
			}			
			request.setAttribute("result", result);
			request.getRequestDispatcher("LnnjServlet?action=getNjfb").forward(request,
					response);
			
		}
	}


	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void LnnjShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		String result = request.getParameter("result");
		LnnjDao lnnjDao = new LnnjDao();
		ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
		ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
		Njfb njfb = new Njfb();
		if(!"".equals(id)&&id!=null){
			njfb = lnnjDao.queryNjfbById(Integer.parseInt(id));
			//�ϲ����б�
			njfbCLList = lnnjDao.queryNjfbCLById(Integer.parseInt(id));
			//����Ŀ�б�
			lnnjDTList =  lnnjDao.queryNjfbDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls));
		}
		//�����ϲ����
		int r1 = 0;
		int c1 = 0;
		int r2 = 0;
		int c2 = 0;
		for(int i=0; i<njfbCLList.size(); i++){
			r1 = njfbCLList.get(i).getRow1();
			c1 = njfbCLList.get(i).getColumn1();
			r2 = njfbCLList.get(i).getRow2();
			c2 = njfbCLList.get(i).getColumn2();
			//�ϲ���
			if(c1==c2){
				njfbCLList.get(i).setHl("r");
				njfbCLList.get(i).setHs(r2-r1+1);
			}
			//�ϲ���
			else if(r1==r2){
				njfbCLList.get(i).setHl("c");
				njfbCLList.get(i).setLs(c2-c1+1);
			}
			//�ϲ�����
			else{
				njfbCLList.get(i).setHl("rc");
				njfbCLList.get(i).setHs(r2-r1+1);
				njfbCLList.get(i).setLs(c2-c1+1);
			}
		}
		request.setAttribute("njfb", njfb);
		request.setAttribute("lnnjDTList", lnnjDTList);
		request.setAttribute("njfbCLList", njfbCLList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void LnnjDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			LnnjDao lnnjDao = new LnnjDao();
			if (!"".equals(id)&&id!=null){
				lnnjDao.deleteNjfbDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls));
				lnnjDao.deleteNjfbByID(Integer.parseInt(id));
				lnnjDao.deleteNjfbJTGKByIDList(Integer.parseInt(id));
			}
			RequestDispatcher rd = request.getRequestDispatcher("LnnjServlet?action=getNjfb");
			rd.forward(request, response);
			return;
		}
	}
	/*��ȡExcel�ļ�������   
	 * @param file  ����ȡ���ļ�   
	 * @return   
	 */ 

	public void readNjfbExcel(HttpServletRequest request,
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
			//����EXCELλ��
			String sheetNum = request.getParameter("sheetNum");
			//���������޸�
			String njID = request.getParameter("njID");
			String bt = request.getParameter("bt");
			String year = request.getParameter("year");
			String fatherid = request.getParameter("fatherid");
			String bbls = request.getParameter("bbls");
			Njfb njfb = new Njfb();
			njfb.setBt(bt);
			njfb.setYear(Integer.parseInt(year));
			njfb.setFatherid(fatherid);
			int lc = Integer.parseInt(bbls);
			njfb.setBbls(lc);
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
			HashMap propertyMap = new HashMap();
			// �������Ա����       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("xmid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=lc; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
			ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
			//�����Workbook����֮�󣬾Ϳ���ͨ�����õ�Sheet��������������    
			Sheet[] sheet = wb.getSheets();
			int StNm = 0;
			if(!"".equals(sheetNum)&&sheetNum!=null){
				StNm = Integer.parseInt(sheetNum)-1;
			}
			if(sheet!=null&&sheet.length>0){
				int r = sheet[StNm].getRows();//�и�
				int c = sheet[StNm].getColumns();//�г�
				if(c<lc){
					result = "�����ļ��Ƿ���ȷ";
				}else{
					if(!"".equals(njID)&&njID!=null){
						njfb.setId(Integer.parseInt(njID));
					}
					Range[] ranges = sheet[StNm].getMergedCells(); 
				    for(Range space:ranges){
				    	NjfbConsolidations njfbCL = new NjfbConsolidations();
				    	njfbCL.setRow1(space.getTopLeft().getRow()+1);
				    	njfbCL.setColumn1(space.getTopLeft().getColumn()+1);
				    	njfbCL.setRow2(space.getBottomRight().getRow()+1);
				    	njfbCL.setColumn2(space.getBottomRight().getColumn()+1);
				    	njfbCLList.add(njfbCL);
				    } 
//				    Range[] ranges = sheet[StNm].getMergedCells(); 
//				    System.out.println("sheet" + StNm + "����" + ranges.length + "������"); 
//				    for(Range space:ranges){
//					    int i = space.getTopLeft().getRow();
//					    int j = space.getTopLeft().getColumn();
//						System.out.print(sheet[StNm].getCell(j,i).getContents()+"\t"); 
//					    System.out.print(space.getTopLeft().getRow()+1+"��,"); 
//					    System.out.print(space.getTopLeft().getColumn()+1+"��\t"); 
//					    System.out.print(space.getBottomRight().getRow()+1+"��,"); 
//					    System.out.print(space.getBottomRight().getColumn()+1+"��\n"); 
//				    } 
				    
					for(int i=0;i<r; i++){
						CglibBean bean = new CglibBean(propertyMap);
						for(int j=1; j<=lc; j++){
							bean.setValue("zb"+j, sheet[StNm].getCell(j-1,i).getContents());
						}
						lnnjDTList.add(bean);
					}
				}
				//���ر���Դ���ͷ��ڴ�    
				wb.close();
			}
			//ɾ����ʱ�ļ�
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("njfb", njfb);
			request.setAttribute("lnnjDTList", lnnjDTList);
			request.setAttribute("njfbCLList", njfbCLList);
			request.setAttribute("fatherid", fatherid);
			request.setAttribute("result", result);
			request.setAttribute("bbls", bbls);
			request.getRequestDispatcher("/jsp/AlmanacCalendar/njfbEdit.jsp").forward(request,
					response);
		}
	}
	
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getNjfbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String exc_id = request.getParameter("exc_id");
		LnnjDao lnnjDao = new LnnjDao();
		ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
		ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
		Njfb njfb = new Njfb();
		if(!"".equals(exc_id)&&exc_id!=null){
			njfb = lnnjDao.queryNjfbById(Integer.parseInt(exc_id));
			//�ϲ����б�
			njfbCLList = lnnjDao.queryNjfbCLById(Integer.parseInt(exc_id));
			//����Ŀ�б�
			lnnjDTList =  lnnjDao.queryNjfbDTByIDList(Integer.parseInt(exc_id),njfb.getBbls());
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
				WritableSheet wsheet = wwb.createSheet(njfb.getBt(),1);
				// ��������Ϊ����,18����,�Ӵ�
				WritableFont font1 = new WritableFont(WritableFont.createFont("����"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//ˮƽ����
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//��ֱ����
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("����"), 12, WritableFont.NO_BOLD);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.CENTRE);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				//���ñ߿�
				format2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
	
				
				//���ñ�ͷ

				Label label = new Label(0,0,njfb.getYear()+"��"+njfb.getBt(),format1);
				wsheet.addCell(label);
				int num = njfb.getBbls();
				for(int i=0; i<lnnjDTList.size(); i++){
					for(int j=1; j<=num; j++){
						label = new Label(j-1,i+1,lnnjDTList.get(i).getValue("zb"+j)+"",format2);
						wsheet.addCell(label);
					}
				}
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, num-1, 0);
				for(int k=0; k<njfbCLList.size(); k++){
					//��Ϊ���ڱ��⣬������Ҫ��1
					//����Ϊ���ݿ�洢��������������1��ʼ������������������������0��ʼ����������������������1
					//�����������������䣬������1
					wsheet.mergeCells(njfbCLList.get(k).getColumn1()-1, njfbCLList.get(k).getRow1(), njfbCLList.get(k).getColumn2()-1, njfbCLList.get(k).getRow2());
				}
				//�����п�
				for(int n=0; n<num; n++){
					wsheet.setColumnView(n, 20);
				}
				//�����п�
				wsheet.setRowView(0, 680, false);
				for(int k=0; k<lnnjDTList.size(); k++){
					wsheet.setRowView(k+1, 400, false);
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
	
}
