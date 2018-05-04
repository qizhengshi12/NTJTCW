package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.safety.dao.BbdzDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.Bbdz;
import com.safety.entity.Bbdzmb;
import com.safety.entity.BbdzmbHb;
import com.safety.entity.BbdzmbJs;
import com.safety.entity.CglibBean;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;

public class BbdzServlet  extends HttpServlet{
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
		if("getBbdzmb".equals(action)){//������ģ���б�
			getBbdzmb(request,response);
		}else if("editBbdzmb".equals(action)){//������ģ�塪���������޸�
			editBbdzmb(request,response);
		}else if("deleteBbdzmb".equals(action)){//������ģ�塪��ɾ��
			deleteBbdzmb(request,response);
		}else if("saveBbdzmb".equals(action)){//������ģ�塪������
			saveBbdzmb(request,response);
		}else if("showBbdzmb".equals(action)){//������ģ�塪���鿴
			showBbdzmb(request,response);
		}else if("readBbdzmbExcel".equals(action)){//������ģ�塪������EXCEL
			readBbdzmbExcel(request,response);
		}else if("getBbdzmbExcel".equals(action)){//������ģ�塪������EXCEL
			getBbdzmbExcel(request,response);
		}else if("getBbdz".equals(action)){//����ģ�����½������б�
			getBbdz(request,response);
		}else if("editBbdz".equals(action)){//����ģ���½��������������޸�
			editBbdz(request,response);
		}else if("saveBbdz".equals(action)){//����ģ���½�����������
			saveBbdz(request,response);
		}else if("deleteBbdz".equals(action)){//����ģ���½�������ɾ��
			deleteBbdz(request,response);
		}else if("showBbdz".equals(action)){//����ģ���½��������鿴
			showBbdz(request,response);
		}else if("readBbdzExcel".equals(action)){//����ģ���½�����������EXCEL
			readBbdzExcel(request,response);
		}else if("getBbdzExcel".equals(action)){//����ģ���½�����������EXCEL
			getBbdzExcel(request,response);
		}else if("returnBbdz".equals(action)){//����ģ���½�����������
			returnBbdz(request,response);
		}
	}

	
	
	/*
	 * ������ģ���б�
	 * 
	 */
	protected void getBbdzmb(HttpServletRequest request,
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
			
		}else if(UserPer.getBbdz().indexOf("1")==-1){//������Ȩ��
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//�б�
			ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbdzDao bbdzDao = new BbdzDao();
			String srbt = request.getParameter("srbt");
			String cxbt = request.getParameter("cxbt");
			String cxlx = request.getParameter("cxlx");
//			String cxssrq1 = request.getParameter("cxssrq1");
//			String cxssrq2 = request.getParameter("cxssrq2");
			//���Ӳ˵�����
			if("".equals(srbt)||srbt==null){
				//ͨ�������ѯ
				cxbt = "";
//				cxssrq1 = "";
//				cxssrq2 = "";
				srbt= " where lx ='"+cxlx+"'";
			}
//			else{
//				srbt= srbt + " and fszt='1' ";
//			}
			BbdzmbList = bbdzDao.queryBbdzmbByBt(srbt, begin, pageSize);
			countAll = bbdzDao.queryBbdzmbByBtCount(srbt);
			request.setAttribute("BbdzmbList", BbdzmbList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxbt", cxbt);
			request.setAttribute("cxlx", cxlx);
//			request.setAttribute("cxssrq1", cxssrq1);
//			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/CustomReports/getBbdzmb.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ������ģ�塪���������޸�
	 * 
	 */
	protected void editBbdzmb(HttpServletRequest request,
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
			String dzls = request.getParameter("dzls");//��������
			String lx = request.getParameter("lx");
			String result = request.getParameter("result");
			BbdzDao bbdzDao = new BbdzDao();
			
			Bbdzmb bbdzmb = new Bbdzmb();
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
			if(!"".equals(id)&&id!=null){
				bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(id));
				//�ϲ����б�
				BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(id));
				//�������б�
				BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(id));
				//����Ŀ�б�
				BbdzmbDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(id),Integer.parseInt(dzls));
			}else{
				//��ѯ�Ƿ�����Ӧ�������б�
				boolean res1 = bbdzDao.queryBbdzmbLs(Integer.parseInt(dzls));
				if(!res1){
					//������Ӧ�������б�ģ�壩
					bbdzDao.createBbdzmbByDzls(Integer.parseInt(dzls));
				}
				//��ѯ�Ƿ�����Ӧ�������б�
				boolean res2 = bbdzDao.queryBbdzmbLsByLx(Integer.parseInt(dzls),lx);
				if(!res2){
					//������Ӧ���������б����ݣ������´��½���ʱ���ظ�����
					bbdzDao.insertBbdzmbLs(Integer.parseInt(dzls),lx);
					//������Ӧ�������б��洢��
					bbdzDao.createBbdzByDzls(Integer.parseInt(dzls),lx);
				}
			}
			request.setAttribute("bbdzmb", bbdzmb);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzmbDTList", BbdzmbDTList);
			request.setAttribute("dzls", dzls);
			request.setAttribute("lx", lx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzmbEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void deleteBbdzmb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String dzls = request.getParameter("dzls");
		String lx = request.getParameter("lx");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			BbdzDao bbdzDao = new BbdzDao();
			if (!"".equals(id)&&id!=null){
				//ɾ��ģ���
				bbdzDao.deleteBbdzmbByID(Integer.parseInt(id));
				//ɾ��ģ�������ϸ����
				bbdzDao.deleteBbdzmbDTByIDList(Integer.parseInt(id),Integer.parseInt(dzls));
				//ɾ���ϲ����
				bbdzDao.deleteBbdzmbHbByIDList(Integer.parseInt(id));
				//ɾ���������
				bbdzDao.deleteBbdzmbJsByIDList(Integer.parseInt(id));
			}
			RequestDispatcher rd = request.getRequestDispatcher("BbdzServlet?action=getBbdzmb&cxlx="+lx);
			rd.forward(request, response);
			return;
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void saveBbdzmb(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String lx = request.getParameter("lx");
			String dzls = request.getParameter("dzls");
			Bbdzmb bbdzmb = new Bbdzmb();
			bbdzmb.setBt(bt);
			bbdzmb.setLx(lx);
			int num = Integer.parseInt(dzls);
			bbdzmb.setDzls(num);
			bbdzmb.setCzr(UserInfo.getName());
			bbdzmb.setCzrID(UserInfo.getUsername());
			bbdzmb.setCzrdw(UserInfo.getCompany());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			bbdzmb.setCzsj(data1);
			BbdzDao bbdzDao = new BbdzDao();
			int zbid = 0;
			//�������޸�����
			if(!"".equals(id)&&id!=null){
				zbid = Integer.parseInt(id);
				bbdzmb.setId(zbid);
				bbdzDao.updateBbdzmb(bbdzmb);
			}else{
				zbid = bbdzDao.insertBbdzmb(bbdzmb);
			}
			//ɾ��ԭ������Ŀ��¼������������
			if (!"".equals(id)&&id!=null){
				bbdzDao.deleteBbdzmbDTByIDList(zbid,num);
			}
			HashMap propertyMap = new HashMap();
			// �������Ա����       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=num; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
//			CglibBean bean = new CglibBean(propertyMap);
			//��ֵ
			String zb1[]=request.getParameterValues("zb1"); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
			if(zb1!=null){
				for(int j=0; j<zb1.length; j++){
					CglibBean bean = new CglibBean(propertyMap);
					bean.setValue("zb1", zb1[j]);
					bean.setValue("zbid", zbid);
					BbdzmbDTList.add(bean);
				}
				for(int i=2; i<=num; i++){
					String zb[]=request.getParameterValues("zb"+i); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
					for(int j=0; j<zb.length; j++){
						BbdzmbDTList.get(j).setValue("zb"+i, zb[j]);
					}
				}
				bbdzDao.insertBbdzmbDT(BbdzmbDTList,num);
			}
			//�洢���кϲ�������
			String column1[]=request.getParameterValues("column1");//��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������ 
			String row1[]=request.getParameterValues("row1");
			String column2[]=request.getParameterValues("column2");
			String row2[]=request.getParameterValues("row2");
			//ɾ��ԭ�����ݣ�����������
			if (!"".equals(id)&&id!=null){
				bbdzDao.deleteBbdzmbHbByIDList(zbid);
			}
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			if(column1!=null){
				for(int i=0; i<column1.length; i++){
					BbdzmbHb bbdzmbHb = new BbdzmbHb();
					bbdzmbHb.setZbid(zbid);
					if("".equals(column1[i]))column1[i]="-1";
					bbdzmbHb.setColumn1(Integer.parseInt(column1[i]));
					if("".equals(row1[i]))row1[i]="-1";
					bbdzmbHb.setRow1(Integer.parseInt(row1[i]));
					if("".equals(column2[i]))column2[i]="-1";
					bbdzmbHb.setColumn2(Integer.parseInt(column2[i]));
					if("".equals(row2[i]))row2[i]="-1";
					bbdzmbHb.setRow2(Integer.parseInt(row2[i]));
					BbdzmbHbList.add(bbdzmbHb);
				}
				bbdzDao.insertBbdzmbHb(BbdzmbHbList);
			}

			//�洢���м���������
			String columnjs1[]=request.getParameterValues("columnjs1");//��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������ 
			String rowjs1[]=request.getParameterValues("rowjs1");
			String columnjs2[]=request.getParameterValues("columnjs2");
			String rowjs2[]=request.getParameterValues("rowjs2");
			String columnjs3[]=request.getParameterValues("columnjs3");
			String rowjs3[]=request.getParameterValues("rowjs3");
			String jslx[]=request.getParameterValues("jslx");
			String jsfh[]=request.getParameterValues("jsfh");
			//ɾ��ԭ�����ݣ�����������
			if (!"".equals(id)&&id!=null){
				bbdzDao.deleteBbdzmbJsByIDList(zbid);
			}
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			if(columnjs1!=null){
				for(int i=0; i<columnjs1.length; i++){
					BbdzmbJs bbdzmbJs = new BbdzmbJs();
					bbdzmbJs.setZbid(zbid);
					if("".equals(columnjs1[i]))columnjs1[i]="-1";
					bbdzmbJs.setColumn1(Integer.parseInt(columnjs1[i]));
					if("".equals(rowjs1[i]))rowjs1[i]="-1";
					bbdzmbJs.setRow1(Integer.parseInt(rowjs1[i]));
					if("".equals(columnjs2[i]))columnjs2[i]="-1";
					bbdzmbJs.setColumn2(Integer.parseInt(columnjs2[i]));
					if("".equals(rowjs2[i]))rowjs2[i]="-1";
					bbdzmbJs.setRow2(Integer.parseInt(rowjs2[i]));
					if("".equals(columnjs3[i]))columnjs3[i]="-1";
					bbdzmbJs.setColumn3(Integer.parseInt(columnjs3[i]));
					if("".equals(rowjs3[i]))rowjs3[i]="-1";
					bbdzmbJs.setRow3(Integer.parseInt(rowjs3[i]));
					bbdzmbJs.setLx(jslx[i]);
					bbdzmbJs.setFh(jsfh[i]);
					BbdzmbJsList.add(bbdzmbJs);
				}
				bbdzDao.insertBbdzmbJs(BbdzmbJsList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbdzServlet?action=getBbdzmb&cxlx="+lx).forward(request,
					response);
			
		}
	}
	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void showBbdzmb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String dzls = request.getParameter("dzls");
		String result = request.getParameter("result");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
		Bbdzmb bbdzmb = new Bbdzmb();
		if(!"".equals(id)&&id!=null){
			bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(id));
			//�ϲ����б�
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(id));
			//�������б�
			BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(id));
			//����Ŀ�б�
			BbdzmbDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(id),Integer.parseInt(dzls));
		}
		//�����ϲ����
		int r1 = 0;
		int c1 = 0;
		int r2 = 0;
		int c2 = 0;
		for(int i=0; i<BbdzmbHbList.size(); i++){
			r1 = BbdzmbHbList.get(i).getRow1();
			c1 = BbdzmbHbList.get(i).getColumn1();
			r2 = BbdzmbHbList.get(i).getRow2();
			c2 = BbdzmbHbList.get(i).getColumn2();
			//�ϲ���
			if(c1==c2){
				BbdzmbHbList.get(i).setHl("r");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
			}
			//�ϲ���
			else if(r1==r2){
				BbdzmbHbList.get(i).setHl("c");
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
			//�ϲ�����
			else{
				BbdzmbHbList.get(i).setHl("rc");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
		}
		request.setAttribute("bbdzmb", bbdzmb);
		request.setAttribute("BbdzmbHbList", BbdzmbHbList);
		request.setAttribute("BbdzmbJsList", BbdzmbJsList);
		request.setAttribute("BbdzmbDTList", BbdzmbDTList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/CustomReports/bbdzmbShow.jsp").forward(request,
				response);
		
		return;
	}
	/*��ȡExcel�ļ�������   
	 * @param file  ����ȡ���ļ�   
	 * @return   
	 */ 

	public void readBbdzmbExcel(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String lx = request.getParameter("lx");
			String dzls = request.getParameter("dzls");
			Bbdzmb bbdzmb = new Bbdzmb();
			bbdzmb.setBt(bt);
			bbdzmb.setLx(lx);
			int lc = Integer.parseInt(dzls);
			bbdzmb.setDzls(lc);
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
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=lc; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
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
					if(!"".equals(id)&&id!=null){
						bbdzmb.setId(Integer.parseInt(id));
					}
					Range[] ranges = sheet[StNm].getMergedCells(); 
				    for(Range space:ranges){
				    	BbdzmbHb bbdzmbHb = new BbdzmbHb();
				    	bbdzmbHb.setRow1(space.getTopLeft().getRow()+1);
				    	bbdzmbHb.setColumn1(space.getTopLeft().getColumn()+1);
				    	bbdzmbHb.setRow2(space.getBottomRight().getRow()+1);
				    	bbdzmbHb.setColumn2(space.getBottomRight().getColumn()+1);
				    	BbdzmbHbList.add(bbdzmbHb);
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
						BbdzmbDTList.add(bean);
					}
				}
				//���ر���Դ���ͷ��ڴ�    
				wb.close();
			}
			//ɾ����ʱ�ļ�
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("bbdzmb", bbdzmb);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzmbDTList", BbdzmbDTList);
			request.setAttribute("dzls", dzls);
			request.setAttribute("lx", lx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzmbEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getBbdzmbExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String exc_id = request.getParameter("exc_id");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
		Bbdzmb bbdzmb = new Bbdzmb();
		if(!"".equals(exc_id)&&exc_id!=null){
			bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(exc_id));
			//�ϲ����б�
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(exc_id));
			//�������б�
			BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(exc_id));
			//����Ŀ�б�
			BbdzmbDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(exc_id),bbdzmb.getDzls());
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
				WritableSheet wsheet = wwb.createSheet(bbdzmb.getBt(),1);
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

				Label label = new Label(0,0,bbdzmb.getBt(),format1);
				wsheet.addCell(label);
				int num = bbdzmb.getDzls();
				for(int i=0; i<BbdzmbDTList.size(); i++){
					for(int j=1; j<=num; j++){
						label = new Label(j-1,i+1,BbdzmbDTList.get(i).getValue("zb"+j)+"",format2);
						wsheet.addCell(label);
					}
				}
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, num-1, 0);
				for(int k=0; k<BbdzmbHbList.size(); k++){
					//��Ϊ���ڱ��⣬������Ҫ��1
					//����Ϊ���ݿ�洢��������������1��ʼ������������������������0��ʼ����������������������1
					//�����������������䣬������1
					wsheet.mergeCells(BbdzmbHbList.get(k).getColumn1()-1, BbdzmbHbList.get(k).getRow1(), BbdzmbHbList.get(k).getColumn2()-1, BbdzmbHbList.get(k).getRow2());
				}
				//�����п�
				for(int n=0; n<num; n++){
					wsheet.setColumnView(n, 20);
				}
				//�����п�
				wsheet.setRowView(0, 680, false);
				for(int k=0; k<BbdzmbHbList.size(); k++){
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

	/*
	 * ����ģ�����½������б�
	 * 
	 */
	protected void getBbdz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//�½����ݵ�����
		String lx = request.getParameter("lx");
		//ģ��ID
		String zbid = request.getParameter("zbid");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		//Ȩ��
		String qx = "";
		if("cwbb".equals(lx)){
			qx = UserPer.getCwbb();
		}else if("sjbb".equals(lx)){
			qx = UserPer.getSjbb();
		}else if("tjbb".equals(lx)){
			qx = UserPer.getTjbb();
		}
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(qx.indexOf("1")==-1){//������Ȩ��
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			//�б�
			ArrayList<Bbdz> BbdzList = new ArrayList<Bbdz>();
			//��ҳ;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbdzDao bbdzDao = new BbdzDao();
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
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) and zbid = "+zbid;
				cxtj = "1";
			}
			countAll = bbdzDao.queryBbdzByBtCount(srbt,lx);
			BbdzList = bbdzDao.queryBbdzByBt(srbt, begin, pageSize,lx);
			request.setAttribute("BbdzList", BbdzList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("zbid", zbid);
			request.setAttribute("lx", lx);
			request.setAttribute("cxtj", cxtj);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/CustomReports/getBbdz.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ����ģ���½��������������޸�
	 * 
	 */
	protected void editBbdz(HttpServletRequest request,
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
			String id = request.getParameter("id");//����ID
			String zbid = request.getParameter("zbid");//ģ��ID
			String lx = request.getParameter("lx");
			String result = request.getParameter("result");
			BbdzDao bbdzDao = new BbdzDao();
			
			Bbdz bbdz = new Bbdz();
			//�����������Ԥ���ȥ
			Bbdzmb bbdzmb = new Bbdzmb();
			bbdzmb = bbdzDao.queryBbdzmbById(Integer.parseInt(zbid));
			bbdz.setBt(bbdzmb.getBt());
			bbdz.setBbls(bbdzmb.getDzls());
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
			if(!"".equals(zbid)&&zbid!=null){
				//�ϲ����б�
				BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
				//�������б�
				BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(zbid));
				//����Ŀ�б�
				BbdzDTList =  bbdzDao.queryBbdzmbDTByIDList(Integer.parseInt(zbid),bbdz.getBbls());
			}
			if(!"".equals(id)&&id!=null){
				bbdz = bbdzDao.queryBbdzById(Integer.parseInt(id),lx);
				//����Ŀ�б�
				BbdzDTList =  bbdzDao.queryBbdzDTByIDList(Integer.parseInt(id),bbdz.getBbls(),lx);
			}
			request.setAttribute("bbdz", bbdz);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzDTList", BbdzDTList);
			request.setAttribute("bbls", bbdz.getBbls());//ǰ̨��ȡ��ʱ�򣬱�����int��
			request.setAttribute("zbid", zbid);
			request.setAttribute("lx", lx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ���뱣��ҳ��
	 * 
	 */
	protected void saveBbdz(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String lx = request.getParameter("lx");
			String zbid = request.getParameter("zbid");
			String bbls = request.getParameter("bbls");
			String sj = request.getParameter("sj");
			String tjzt = request.getParameter("tjzt");
			Bbdz bbdz = new Bbdz();
			bbdz.setBt(bt);
			bbdz.setZbid(Integer.parseInt(zbid));
			bbdz.setSj(DateFormat(sj));
			bbdz.setTjzt(tjzt);
			int num = Integer.parseInt(bbls);
			bbdz.setBbls(num);
			bbdz.setCzr(UserInfo.getName());
			bbdz.setCzrID(UserInfo.getUsername());
			bbdz.setCzrdw(UserInfo.getCompany());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			bbdz.setCzsj(data1);
			BbdzDao bbdzDao = new BbdzDao();
			int ID = 0;
			//�������޸�����
			if(!"".equals(id)&&id!=null){
				ID = Integer.parseInt(id);
				bbdz.setId(ID);
				bbdzDao.updateBbdz(bbdz,lx);
			}else{
				ID = bbdzDao.insertBbdz(bbdz,lx);
			}
			//ɾ��ԭ������Ŀ��¼������������
			bbdzDao.deleteBbdzDTByIDList(ID,num,lx);
			HashMap propertyMap = new HashMap();
			// �������Ա����       
			try {
				propertyMap.put("id", Class.forName("java.lang.Integer"));
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=num; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
//			CglibBean bean = new CglibBean(propertyMap);
			//��ֵ
			String zb1[]=request.getParameterValues("zb1"); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
			if(zb1!=null){
				for(int j=0; j<zb1.length; j++){
					CglibBean bean = new CglibBean(propertyMap);
					bean.setValue("zb1", zb1[j]);
					bean.setValue("zbid", ID);
					BbdzDTList.add(bean);
				}
				for(int i=2; i<=num; i++){
					String zb[]=request.getParameterValues("zb"+i); //��getParameterValues�ķ���������ȡ����ֵȡ��langtype[]������      
					for(int j=0; j<zb.length; j++){
						BbdzDTList.get(j).setValue("zb"+i, zb[j]);
					}
				}
				bbdzDao.insertBbdzDT(BbdzDTList,num,lx);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/*
	 *  ɾ��
	 * 
	 */
	protected void deleteBbdz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		String zbid = request.getParameter("zbid");
		String lx = request.getParameter("lx");
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			BbdzDao bbdzDao = new BbdzDao();
			if (!"".equals(id)&&id!=null){
				//ɾ������
				bbdzDao.deleteBbdzByID(Integer.parseInt(id),lx);
				//ɾ���ӱ�
				bbdzDao.deleteBbdzDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls),lx);
			}
			RequestDispatcher rd = request.getRequestDispatcher("BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+UserInfo.getCompany());
			rd.forward(request, response);
			return;
		}
	}
	 /*
	 *  ����鿴ҳ��
	 * 
	 */
	protected void showBbdz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String id = request.getParameter("id");
		String bbls = request.getParameter("bbls");
		String zbid = request.getParameter("zbid");
		String lx = request.getParameter("lx");
		String result = request.getParameter("result");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		Bbdz bbdz = new Bbdz();
		if(!"".equals(id)&&id!=null){
			bbdz = bbdzDao.queryBbdzById(Integer.parseInt(id),lx);
			//�ϲ����б�
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
			//����Ŀ�б�
			BbdzDTList =  bbdzDao.queryBbdzDTByIDList(Integer.parseInt(id),Integer.parseInt(bbls),lx);
		}
		//�����ϲ����
		int r1 = 0;
		int c1 = 0;
		int r2 = 0;
		int c2 = 0;
		for(int i=0; i<BbdzmbHbList.size(); i++){
			r1 = BbdzmbHbList.get(i).getRow1();
			c1 = BbdzmbHbList.get(i).getColumn1();
			r2 = BbdzmbHbList.get(i).getRow2();
			c2 = BbdzmbHbList.get(i).getColumn2();
			//�ϲ���
			if(c1==c2){
				BbdzmbHbList.get(i).setHl("r");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
			}
			//�ϲ���
			else if(r1==r2){
				BbdzmbHbList.get(i).setHl("c");
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
			//�ϲ�����
			else{
				BbdzmbHbList.get(i).setHl("rc");
				BbdzmbHbList.get(i).setHs(r2-r1+1);
				BbdzmbHbList.get(i).setLs(c2-c1+1);
			}
		}
		request.setAttribute("lx", lx);
		request.setAttribute("bbdz", bbdz);
		request.setAttribute("BbdzmbHbList", BbdzmbHbList);
		request.setAttribute("BbdzDTList", BbdzDTList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/CustomReports/bbdzShow.jsp").forward(request,
				response);
		
		return;
	}
	/*��ȡExcel�ļ�������   
	 * @param file  ����ȡ���ļ�   
	 * @return   
	 */ 

	public void readBbdzExcel(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String bt = request.getParameter("bt");
			String sj = request.getParameter("sj");
			String lx = request.getParameter("lx");
			String bbls = request.getParameter("bbls");
			String zbid = request.getParameter("zbid");
			Bbdz bbdz = new Bbdz();
			bbdz.setBt(bt);
			bbdz.setZbid(Integer.parseInt(zbid));
			bbdz.setSj(DateFormat(sj));
			int lc = Integer.parseInt(bbls);
			bbdz.setBbls(lc);
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
				propertyMap.put("zbid", Class.forName("java.lang.Integer"));
				for(int i=1; i<=lc; i++){
					propertyMap.put("zb"+i, Class.forName("java.lang.String"));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
			ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
			ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
			if(!"".equals(zbid)&&zbid!=null){
				BbdzDao bbdzDao = new BbdzDao();
				//�ϲ����б�
				BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
				//�������б�
				BbdzmbJsList = bbdzDao.queryBbdzmbJsList(Integer.parseInt(zbid));
			}
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
					if(!"".equals(id)&&id!=null){
						bbdz.setId(Integer.parseInt(id));
					}
					//��һ�б���Ĭ�ϲ�����
					for(int i=1;i<r; i++){
						CglibBean bean = new CglibBean(propertyMap);
						for(int j=1; j<=lc; j++){
							bean.setValue("zb"+j, sheet[StNm].getCell(j-1,i).getContents());
						}
						BbdzDTList.add(bean);
					}
				}
				//���ر���Դ���ͷ��ڴ�    
				wb.close();
			}
			//ɾ����ʱ�ļ�
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("bbdz", bbdz);
			request.setAttribute("BbdzmbHbList", BbdzmbHbList);
			request.setAttribute("BbdzmbJsList", BbdzmbJsList);
			request.setAttribute("BbdzDTList", BbdzDTList);
			request.setAttribute("bbls", lc);//ǰ̨��ȡ��ʱ�򣬱�����int��
			request.setAttribute("lx", lx);
			request.setAttribute("zbid", zbid);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/CustomReports/bbdzEdit.jsp").forward(request,
					response);
		}
	}
	/**
	* ������Ϣ��XLS
	*
	*/
	public void getBbdzExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ
		String exc_id = request.getParameter("exc_id");
		String lx = request.getParameter("lx");
		String zbid = request.getParameter("zbid");
		BbdzDao bbdzDao = new BbdzDao();
		ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		Bbdz bbdz = new Bbdz();
		if(!"".equals(exc_id)&&exc_id!=null){
			bbdz = bbdzDao.queryBbdzById(Integer.parseInt(exc_id),lx);
			//�ϲ����б�
			BbdzmbHbList = bbdzDao.queryBbdzmbHbList(Integer.parseInt(zbid));
			//����Ŀ�б�
			BbdzDTList =  bbdzDao.queryBbdzDTByIDList(Integer.parseInt(exc_id),bbdz.getBbls(),lx);
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
				WritableSheet wsheet = wwb.createSheet(bbdz.getBt(),1);
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

				Label label = new Label(0,0,bbdz.getSj()+"  "+bbdz.getBt(),format1);
				wsheet.addCell(label);
				int num = bbdz.getBbls();
				for(int i=0; i<BbdzDTList.size(); i++){
					for(int j=1; j<=num; j++){
						label = new Label(j-1,i+1,BbdzDTList.get(i).getValue("zb"+j)+"",format2);
						wsheet.addCell(label);
					}
				}
				//�ϲ���Ԫ��
				wsheet.mergeCells(0, 0, num-1, 0);
				for(int k=0; k<BbdzmbHbList.size(); k++){
					//��Ϊ���ڱ��⣬������Ҫ��1
					//����Ϊ���ݿ�洢��������������1��ʼ������������������������0��ʼ����������������������1
					//�����������������䣬������1
					wsheet.mergeCells(BbdzmbHbList.get(k).getColumn1()-1, BbdzmbHbList.get(k).getRow1(), BbdzmbHbList.get(k).getColumn2()-1, BbdzmbHbList.get(k).getRow2());
				}
				//�����п�
				for(int n=0; n<num; n++){
					wsheet.setColumnView(n, 20);
				}
				//�����п�
				wsheet.setRowView(0, 680, false);
				for(int k=0; k<BbdzmbHbList.size(); k++){
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
	/*
	 *  ���벵��
	 * 
	 */
	protected void returnBbdz(HttpServletRequest request,
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
			String id = request.getParameter("id");
			String lx = request.getParameter("lx");
			String zbid = request.getParameter("zbid");
			BbdzDao bbdzDao = new BbdzDao();
			//������Ա���û���
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbdzDao.updateBbdzRet(id,lx);
				String dxnr = UserInfo.getName()+"������һ���ʲ���ծ�����أ��뼰ʱ�鿴";
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
			RequestDispatcher rd = request.getRequestDispatcher("BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+UserInfo.getCompany());
			rd.forward(request, response);
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
}
