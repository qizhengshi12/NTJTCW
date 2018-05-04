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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.safety.dao.ContentGlzdDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentGlzd;
import com.safety.entity.ContentToTxt;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;





public class GlzdServlet extends HttpServlet{
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
		if("getGlzd".equals(action)){
			getGlzd(request,response);
		}else if("resetMenu".equals(action)){//���ò˵�
			resetMenu(request,response);
		}else if("query".equals(action)){
			queryGlzd(request,response);
		}else if("delete".equals(action)){
			deleteGlzd(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("saveGlzd".equals(action)){
			saveGlzd(request,response);
		}else if("showGlzd".equals(action)){
			showGlzd(request,response);
		}else if("editGlzd".equals(action)){
			editGlzd(request,response);
		}
	}
	
	/*
	 *  ˢ�²˵���
	 * 
	 */
	protected void getGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNodeglzd().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Glzd");
			ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("GlzdList", GlzdList);
			request.getRequestDispatcher("/jsp/MngInformation/glzd.jsp").forward(request,
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
			menuDao.DeleteMenuName("node_glzd");
			menuDao.ResetZzxxMenu("node_glzd",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  ��ѯ����
	 * 
	 */
	protected void queryGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���
		String resultList = request.getParameter("resultList");
		//ͨ���˵����ID�Ų�ѯ
		String MenuId = request.getParameter("MenuId");
		//ͨ�������ѯ
		String srbt = request.getParameter("srbt");
		/*���ò�ѯ����*/
		ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
		//�洢��ѯ���
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
		}else if(UserPer.getContentglzd().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('�޲�ѯȨ�ޣ�');</script>");
		}else{

			//��ҳ;
			String flag = "0";//������ڱ�־�������жϲ�ѯ��ʽ
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			
			//ͨ���������ѯ
			if(!"".equals(MenuId)&&MenuId!=null){
				//��ȡ��λ�ƶ�ID���б�
				MenuDao menuDao =new MenuDao();
				//����Ȩ�޲�ѯ
				if(UserPer.getContentglzd().indexOf("5")!=-1){
					String idList = menuDao.QueryMenuById("node_Glzd", MenuId);
					if(!"".equals(idList)&&idList!=null){
						countAll = contentGlzdDao.queryGlzdByIdListCount(idList);
						GlzdList = contentGlzdDao.queryGlzdByIdList(idList, begin, pageSize);
					}
				}else{
					//��ѯ�ýڵ�ĵ�λ����
					int theID = Integer.parseInt(MenuId);
					int parentID = menuDao.QueryPidById("node_Glzd", theID);
					while(parentID!=0){
						theID = parentID;
						parentID = menuDao.QueryPidById("node_Glzd", parentID);
					}
					String company = menuDao.QueryNameById("node_Glzd", theID);
					//����λ��Ա��ѯ
					if(company.equals(UserInfo.getCompany())){
						String idList = menuDao.QueryMenuById("node_Glzd", MenuId);
						if(!"".equals(idList)&&idList!=null){
							countAll = contentGlzdDao.queryGlzdByIdListCount(idList);
							GlzdList = contentGlzdDao.queryGlzdByIdList(idList, begin, pageSize);
						}
					}else{//�ⵥλ��Ա��ѯ
						String idList = menuDao.QueryMenuById("node_Glzd", MenuId);
						if(!"".equals(idList)&&idList!=null){
							countAll = contentGlzdDao.queryGlzdByIdListCountGK(idList);
							GlzdList = contentGlzdDao.queryGlzdByIdListGK(idList, begin, pageSize);
						}
					}
				}
			}
			//ͨ���������ݲ�ѯ
			else if(srbt!=null){
				if(UserPer.getContentglzd().indexOf("5")!=-1){
					countAll = contentGlzdDao.queryGlzdByBtCount(srbt);
					GlzdList = contentGlzdDao.queryGlzdByBt(srbt, begin, pageSize);
					flag = "1";
				}else{
					countAll = contentGlzdDao.queryGlzdByBtCountGK(srbt,UserInfo.getCompany());
					GlzdList = contentGlzdDao.queryGlzdByBtGK(srbt, begin, pageSize,UserInfo.getCompany());
					flag = "1";
				}
			}
			
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Glzd");
			request.setAttribute("menuList", str);
			
			request.setAttribute("MenuId", MenuId);//ǰ̨ҳ�������ID
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("flag", flag);
			request.setAttribute("GlzdList", GlzdList);
			request.setAttribute("resultList", resultList);
			request.getRequestDispatcher("/jsp/MngInformation/glzdList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  ɾ������
	 * 
	 */
	protected void deleteGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		// TODO Auto-generated method stub
		String ContendId = request.getParameter("id");
		String fatherid = request.getParameter("fatherid");
		String resultList = "��Ϣɾ��ʧ��!";
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
			//ɾ�������ļ�
			String path =request.getParameter("path");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+path;
			File file = new File(FullFilePath);  
			// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			
			//ɾ����λ�ƶ�������
			if(ContentGlzdDao.DeleteGlzdById(Integer.parseInt(ContendId)) > 0){
				//���²˵����洢��
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_Glzd", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//���ش�����
				resultList = "��Ϣɾ���ɹ�!";
			} 
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=query&MenuId="+fatherid+"&resultList="+resultList);
			rd.forward(request, response);
		}
	}
	/*
	 *  �����˵�
	 * 
	 */
	protected void insertMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String ParentID = request.getParameter("ParentID");//��ȡ��ǰ�û�
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
			menuDao.InsertMenu("node_Glzd", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
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
			menuDao.UpdateMenuNameById("node_Glzd", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
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
		int count = 0;
		String idList = menuDao.QueryMenuById("node_Glzd", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_Glzd", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_Glzd", Integer.parseInt(id));
				result = "ɾ���ɹ�";
			}else{
				result = "�޷�ɾ����������ɾ���Ӳ˵����ݣ�";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
		rd.forward(request, response);
		return ;
	}
	/*
	 * �����ļ�
	 * 
	 */
	protected void downLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String URL = request.getParameter("URL");
		String fatherid = request.getParameter("fatherid");
		String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
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
        	RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=query&MenuId="+fatherid+"&resultList="+"�ļ������ڣ��޷����أ�");
    		rd.forward(request, response);
    		return ;
        }
	}

	/*
	 *  ������λ�ƶȼ�¼�����������޸ģ�
	 * 
	 */
	protected void saveGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "����ʧ��!";
		//���������޸�
		String editGlzd_id = request.getParameter("editGlzd_id");
		
		//��ȡ����
		String bt = request.getParameter("bt");
		String wh = request.getParameter("wh");
		String ssrq = request.getParameter("ssrq");
		String sfgk = request.getParameter("sfgk");
		String GlzdTxt = request.getParameter("GlzdTxt");
		String FileUrl = request.getParameter("FileUrl");
		String fatherid = request.getParameter("fatherid");
		// List
		ContentGlzd Glzd = new ContentGlzd();
		Glzd.setBt(bt);
		Glzd.setWh(wh);
		Glzd.setSsrq(DateFormat(ssrq));
		Glzd.setFatherid(fatherid);
		Glzd.setSfgk(sfgk);
		//��ȡ��ǰʱ��
		java.util.Date  date=new java.util.Date();
		java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			Glzd.setFbdw(UserInfo.getCompany());
			Glzd.setCzr(UserInfo.getName());
			Glzd.setCzrID(UserInfo.getUsername());
			Glzd.setCzsj(data1);
			ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
			if("".equals(editGlzd_id)||editGlzd_id==null){
				//����
				if(!"".equals(GlzdTxt)&&GlzdTxt!=null){
					// �����ļ���д��
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(GlzdTxt, "MngInformation/glzd", context);
					Glzd.setFileUrl(FileUrl);
				}else{
					Glzd.setFileUrl("");
				}
				int InsertID = contentGlzdDao.insertGlzd(Glzd);
				MenuDao menuDao = new MenuDao();
				menuDao.InsertMenuContextById("node_Glzd", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
				result = "����ɹ�!";
			}else{
				//�޸�
				if(!"".equals(FileUrl)&&FileUrl!=null){
					// ���ļ���д��
					ServletContext context = getServletContext();
					ContentToTxt.UpdateTxt(GlzdTxt, FileUrl, context);
					Glzd.setFileUrl(FileUrl);
				}else if(!"".equals(GlzdTxt)&&GlzdTxt!=null){
					// �����ļ���д��
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(GlzdTxt, "MngInformation/glzd", context);
					Glzd.setFileUrl(FileUrl);
				}else{
					Glzd.setFileUrl("");
				}
				Glzd.setId(Integer.parseInt(editGlzd_id));
				boolean effectCount = contentGlzdDao.updateGlzd(Glzd);
				if(effectCount){
					result = "����ɹ�!";
				}
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("GlzdServlet?action=getGlzd&result="+result);
			rd.forward(request, response);
		}
	}
	

	/*
	 *  �鿴ĳһ����λ�ƶ�����
	 * 
	 */
	protected void showGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//ͨ���˵����ID�Ų�ѯ
		String id = request.getParameter("id");
		//�洢��ѯ���
		ContentGlzd Glzd = new ContentGlzd();
		/*���ò�ѯ����*/
		ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
		//ͨ��id��ѯ
		if(!"".equals(id)&&id!=null){
			Glzd = contentGlzdDao.queryGlzdByID(Integer.parseInt(id));
			if(!"".equals(Glzd.getFileUrl())&&Glzd.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String GlzdTxt = ContentToTxt.ReadTxt(Glzd.getFileUrl(), context);
				Glzd.setGlzdTxt(GlzdTxt);
			}else{
				Glzd.setGlzdTxt("");
			}
		}
		
		request.setAttribute("Glzd", Glzd);
		request.getRequestDispatcher("/jsp/MngInformation/glzdShow.jsp").forward(request,
				response);
	}

	/*
	 *  �༭ĳһ����λ�ƶ�����
	 * 
	 */
	protected void editGlzd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//ͨ���˵����ID�Ų�ѯ
		String id = request.getParameter("id");
		//�洢��ѯ���
		ContentGlzd Glzd = new ContentGlzd();
		/*���ò�ѯ����*/
		ContentGlzdDao contentGlzdDao = new ContentGlzdDao();
		//ͨ��id��ѯ
		if(!"".equals(id)&&id!=null){
			Glzd = contentGlzdDao.queryGlzdByID(Integer.parseInt(id));
			if(!"".equals(Glzd.getFileUrl())&&Glzd.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String GlzdTxt = ContentToTxt.ReadTxt(Glzd.getFileUrl(), context);
				Glzd.setGlzdTxt(GlzdTxt);
			}else{
				Glzd.setGlzdTxt("");
			}
		}
		
		request.setAttribute("Glzd", Glzd);
		request.getRequestDispatcher("/jsp/MngInformation/glzdEdit.jsp").forward(request,
				response);
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
