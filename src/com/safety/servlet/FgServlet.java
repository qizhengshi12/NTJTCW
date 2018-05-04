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



import com.safety.dao.ContentFgDao;
import com.safety.dao.MenuDao;
import com.safety.entity.ContentFg;
import com.safety.entity.ContentToTxt;
import com.safety.entity.ContentZzxx;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;





public class FgServlet extends HttpServlet{
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
		if("getFg".equals(action)){
			getFg(request,response);
		}else if("query".equals(action)){
			queryFg(request,response);
		}else if("delete".equals(action)){
			deleteFg(request,response);
		}else if("insertMenu".equals(action)){
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){
			deleteMenu(request,response);
		}else if("downLoad".equals(action)){
			downLoad(request,response);
		}else if("saveFg".equals(action)){
			saveFg(request,response);
		}else if("showFg".equals(action)){
			showFg(request,response);
		}else if("editFg".equals(action)){
			editFg(request,response);
		}
	}
	
	/*
	 *  ˢ�²˵���
	 * 
	 */
	protected void getFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNodecjfg().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Fg");
			ArrayList<ContentFg> FgList = new ArrayList<ContentFg>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("FgList", FgList);
			request.getRequestDispatcher("/jsp/LawInformation/fg.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ��ѯ����
	 * 
	 */
	protected void queryFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���
		String resultList = request.getParameter("resultList");
		//ͨ���˵����ID�Ų�ѯ
		String MenuId = request.getParameter("MenuId");
		//ͨ�������ѯ
		String srbt = request.getParameter("srbt");
		/*���ò�ѯ����*/
		ContentFgDao contentFgDao = new ContentFgDao();
		//�洢��ѯ���
		ArrayList<ContentFg> FgList = new ArrayList<ContentFg>();

		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getContentcjfg().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('�޲�ѯȨ�ޣ�');</script>");
		}else{

			//��ҳ;
			String Fgag = "0";//������ڱ�־�������жϲ�ѯ��ʽ
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
				//��ȡ���ɷ���ID���б�
				MenuDao menuDao =new MenuDao();
				String idList = menuDao.QueryMenuById("node_Fg", MenuId);
				if(!"".equals(idList)&&idList!=null){
					countAll = contentFgDao.queryFgByIdListCount(idList);
					FgList = contentFgDao.queryFgByIdList(idList, begin, pageSize);
				}
			}
			//ͨ���������ݲ�ѯ
			else if(srbt!=null){
				countAll = contentFgDao.queryFgByBtCount(srbt);
				FgList = contentFgDao.queryFgByBt(srbt, begin, pageSize);
				Fgag = "1";
			}
			
			MenuDao menuDao = new MenuDao();
			String str="";
			str = menuDao.QueryAllMenuInfo("node_Fg");
			request.setAttribute("menuList", str);
			
			request.setAttribute("MenuId", MenuId);//ǰ̨ҳ�������ID
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("Fgag", Fgag);
			request.setAttribute("FgList", FgList);
			request.setAttribute("resultList", resultList);
			request.getRequestDispatcher("/jsp/LawInformation/fgList.jsp").forward(request,
					response);
		}
	}

	/*
	 *  ɾ������
	 * 
	 */
	protected void deleteFg(HttpServletRequest request,
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
			
			//ɾ�����ɷ���������
			if(ContentFgDao.DeleteFgById(Integer.parseInt(ContendId)) > 0){
				//���²˵����洢��
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_Fg", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//���ش�����
				resultList = "��Ϣɾ���ɹ�!";
			} 
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=query&MenuId="+fatherid+"&resultList="+resultList);
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
			menuDao.InsertMenu("node_Fg", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
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
			menuDao.UpdateMenuNameById("node_Fg", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),"");
			
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
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
		String idList = menuDao.QueryMenuById("node_Fg", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_Fg", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_Fg", Integer.parseInt(id));
				result = "ɾ���ɹ�";
			}else{
				result = "�޷�ɾ����������ɾ���Ӳ˵����ݣ�";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
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
        	RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=query&MenuId="+fatherid+"&resultList="+"�ļ������ڣ��޷����أ�");
    		rd.forward(request, response);
    		return ;
        }
	}
	/*
	 *  �������ɷ����¼�����������޸ģ�
	 * 
	 */
	protected void saveFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "����ʧ��!";
		//���������޸�
		String editFg_id = request.getParameter("editFg_id");
		
		//��ȡ����
		String bt = request.getParameter("bt");
		String wh = request.getParameter("wh");
		String ssrq = request.getParameter("ssrq");
		String fbdw = request.getParameter("fbdw");
		String FgTxt = request.getParameter("FgTxt");
		String FileUrl = request.getParameter("FileUrl");
		String fatherid = request.getParameter("fatherid");
		// List
		ContentFg Fg = new ContentFg();
		Fg.setBt(bt);
		Fg.setWh(wh);
		Fg.setSsrq(DateFormat(ssrq));
		Fg.setFbdw(fbdw);
		Fg.setFatherid(fatherid);
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
			Fg.setCzr(UserInfo.getName());
			Fg.setCzrID(UserInfo.getUsername());
			Fg.setCzsj(data1);
			ContentFgDao contentFgDao = new ContentFgDao();
			if("".equals(editFg_id)||editFg_id==null){
				//����
				if(!"".equals(FgTxt)&&FgTxt!=null){
					// �����ļ���д��
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(FgTxt, "LawInformation/fg", context);
					Fg.setFileUrl(FileUrl);
				}else{
					Fg.setFileUrl("");
				}
				//�洢����
				Fg.setLx("����");
				int InsertID = contentFgDao.insertFg(Fg);
				MenuDao menuDao = new MenuDao();
				menuDao.InsertMenuContextById("node_Fg", fatherid, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
				result = "����ɹ�!";
			}else{
				//�޸�
				if(!"".equals(FileUrl)&&FileUrl!=null){
					// ���ļ���д��
					ServletContext context = getServletContext();
					ContentToTxt.UpdateTxt(FgTxt, FileUrl, context);
					Fg.setFileUrl(FileUrl);
				}else if(!"".equals(FgTxt)&&FgTxt!=null){
					// �����ļ���д��
					ServletContext context = getServletContext();
					FileUrl = ContentToTxt.WriteTxt(FgTxt, "LawInformation/fg", context);
					Fg.setFileUrl(FileUrl);
				}else{
					Fg.setFileUrl("");
				}
				Fg.setId(Integer.parseInt(editFg_id));
				boolean effectCount = contentFgDao.updateFg(Fg);
				if(effectCount){
					result = "����ɹ�!";
				}
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("FgServlet?action=getFg&result="+result);
			rd.forward(request, response);
		}
	}
	

	/*
	 *  �鿴ĳһ�����ɷ�������
	 * 
	 */
	protected void showFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//ͨ���˵����ID�Ų�ѯ
		String id = request.getParameter("id");
		//�洢��ѯ���
		ContentFg Fg = new ContentFg();
		/*���ò�ѯ����*/
		ContentFgDao contentFgDao = new ContentFgDao();
		//ͨ��id��ѯ
		if(!"".equals(id)&&id!=null){
			Fg = contentFgDao.queryFgByID(Integer.parseInt(id));
			if(!"".equals(Fg.getFileUrl())&&Fg.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String FgTxt = ContentToTxt.ReadTxt(Fg.getFileUrl(), context);
				Fg.setFgTxt(FgTxt);
			}else{
				Fg.setFgTxt("");
			}
		}
		
		request.setAttribute("Fg", Fg);
		request.getRequestDispatcher("/jsp/LawInformation/fgShow.jsp").forward(request,
				response);
	}

	/*
	 *  �༭ĳһ�����ɷ�������
	 * 
	 */
	protected void editFg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//ͨ���˵����ID�Ų�ѯ
		String id = request.getParameter("id");
		//�洢��ѯ���
		ContentFg Fg = new ContentFg();
		/*���ò�ѯ����*/
		ContentFgDao contentFgDao = new ContentFgDao();
		//ͨ��id��ѯ
		if(!"".equals(id)&&id!=null){
			Fg = contentFgDao.queryFgByID(Integer.parseInt(id));
			if(!"".equals(Fg.getFileUrl())&&Fg.getFileUrl()!=null){
				ServletContext context = getServletContext();
				String FgTxt = ContentToTxt.ReadTxt(Fg.getFileUrl(), context);
				Fg.setFgTxt(FgTxt);
			}else{
				Fg.setFgTxt("");
			}
		}
		
		request.setAttribute("Fg", Fg);
		request.getRequestDispatcher("/jsp/LawInformation/fgEdit.jsp").forward(request,
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
