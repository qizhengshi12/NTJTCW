package com.safety.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MenuDao;
import com.safety.dao.PermissionsDao;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Menu;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;






public class ZzxxServlet extends HttpServlet{
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
		if("getCompany".equals(action)){//ˢ�������֯�ṹͼ
			getCompany(request,response);
		}else if("query".equals(action)){//��ѯ��Ա
			query(request,response);
		}else if("CXCompany".equals(action)){//��ѯ��λ�б�����������ѯ��
			CXCompany(request,response);
		}else if("insertMenu".equals(action)){//�����˵�
			insertMenu(request,response);
		}else if("updateMenu".equals(action)){//�޸Ĳ˵�
			updateMenu(request,response);
		}else if("deleteMenu".equals(action)){//ɾ���˵�
			deleteMenu(request,response);
		}else if("showPerson".equals(action)){//����鿴��Ա��ϸ��Ϣҳ��
			showPerson(request,response);
		}else if("insertPerson".equals(action)){//�����û�
			insertPerson(request,response);
		}else if("editPerson".equals(action)){//����༭ҳ��
			editPerson(request,response);
		}else if("updatePerson".equals(action)){//�����û���Ϣ
			updatePerson(request,response);
		}else if("deletePerson".equals(action)){//ɾ���û�
			deletePerson(request,response);
		}else if("sortNum".equals(action)){//����˳��
			sortNum(request,response);
		}else if("FindSameUser".equals(action)){//ע��ʱ��AJAXȷ���û���δ����
			FindSameUser(request,response);
		}else if("FindCompany".equals(action)){//��ѯ��λ�б����������༭��Ա��
			FindCompany(request,response);
		}else if("FindDepart1".equals(action)){//��ѯ������λ
			FindDepart1(request,response);
		}else if("FindDepart2".equals(action)){//��ѯ������λ
			FindDepart2(request,response);
		}else if("FindDepart3".equals(action)){//��ѯ�ļ���λ
			FindDepart3(request,response);
		}else if("getMyAccount".equals(action)){//�����ҵ��˻�
			getMyAccount(request,response);
		}else if("editMyAccount".equals(action)){//�����ҵ��˻������༭ҳ��
			editMyAccount(request,response);
		}else if("saveMyAccount".equals(action)){//�����ҵ��˻����� ����ҳ��
			saveMyAccount(request,response);
		}else if("editPassWord".equals(action)){//�����ҵ��˻����� �޸�����
			editPassWord(request,response);
		}else if("getThreeSelectFree".equals(action)){//����Աѡ��򣨲��̶���������
			getThreeSelectFree(request,response);
		}else if("getLeftSelect".equals(action)){//��ѯ��ѡ����
			getLeftSelect(request,response);
		}else if("fastChoose".equals(action)){//���ѡ����ϢԱ��
			fastChoose(request,response);
		}
	}
	
	/*
	 *  ˢ�²˵���
	 * 
	 */
	protected void getCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getNodezzxx().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "�޲�ѯȨ��");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			String result = request.getParameter("result");
			// List
			String str="";
			str = menuDao.QueryAllMenuInfo("node_zzxx");
			ArrayList<ContentZzxx> zzxxList = new ArrayList<ContentZzxx>();
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.setAttribute("zzxxList", zzxxList);
			request.getRequestDispatcher("/jsp/BasicInformation/zzxx.jsp").forward(request,
					response);
		}
	}
	/*
	 *  ��ѯ����
	 * 
	 */

	protected void query(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ͨ���˵����ID�Ų�ѯ
		String MenuId = request.getParameter("MenuId");
		//ͨ��������ѯ
		String srbt = request.getParameter("srbt");

		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
		}else if(UserPer.getContentzzxx().indexOf("1")==-1){
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
			/*���ò�ѯ����*/
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			//�洢��ѯ���
			ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
			//ͨ���������ѯ
			if(!"".equals(MenuId)&&MenuId!=null){
				//��ȡ�˵�ID���б�
				MenuDao menuDao =new MenuDao();
				String idList = menuDao.QueryMenuById("node_zzxx", MenuId);
				if(!"".equals(idList)&&idList!=null){
					countAll = contentZzxxDao.queryZzxxByIdListCount(idList);
					ZzxxList = contentZzxxDao.queryZzxxByIdList(idList, begin, pageSize);
				}
			}
			//ͨ���������ݲ�ѯ
			else {
				countAll = contentZzxxDao.queryZzxxByBtCount(srbt);
				ZzxxList = contentZzxxDao.queryZzxxByBt(srbt, begin, pageSize);
				flag = "1";
			}
			//��ȡ��ǰ�û�
	//		String username = (String) request.getSession().getAttribute("adminUsername");
			//��ȡ�û�Ȩ��
	//		PermissionsDao permissionsDao= new PermissionsDao();
	//		String permissions = permissionsDao.QueryPermissionsById("flfg", username);
	//		request.setAttribute("permissions", permissions);
			
	
			request.setAttribute("ZzxxList", ZzxxList);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.setAttribute("flag", flag);
			
			request.setAttribute("MenuId", MenuId);//ǰ̨ҳ�������ID
			request.setAttribute("srbt", srbt);//ǰ̨ҳ������Ĳ�ѯ����
			request.getRequestDispatcher("/jsp/BasicInformation/zzxxList.jsp").forward(request,
					response);
		}
	}

	/*
	 * AJAX
	 * ��ѯ��λ�б�����������ѯ��
	 */
	protected void CXCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany("0");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"CXCompanyName(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=��ѡ��=</option>");
		out.print("<option value=''>���е�λ</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
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
	/*
	 *  ������λ����
	 * 
	 */
	protected void insertMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String ParentID = request.getParameter("ParentID");
		String result= "�����ɹ�";
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
			menuDao.InsertMenu("node_zzxx", name, Integer.parseInt(ParentID),data1,UserInfo.getName(),UserInfo.getUsername(),description);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getCompany&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 * �޸Ĳ���
	 * 
	 */
	protected void updateMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
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
			menuDao.UpdateMenuNameById("node_zzxx", name, Integer.parseInt(id),data1,UserInfo.getName(),UserInfo.getUsername(),description);
			//�޸ĸ�����Ϣ����Ӧ�ĵ�λ��������
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			contentZzxxDao.updateZzxxCompany1(id, name);
			contentZzxxDao.updateZzxxCompany2(id, name);
			contentZzxxDao.updateZzxxCompany3(id, name);
			contentZzxxDao.updateZzxxCompany4(id, name);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getCompany&result="+result);
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
		String idList = menuDao.QueryMenuById("node_zzxx", id);
		if("".equals(idList)||idList==null){
			count = menuDao.QueryCountByFatherId("node_zzxx", id);
			if(count==0){
				menuDao.DeleteMenuNameById("node_zzxx", Integer.parseInt(id));
				result = "ɾ���ɹ�";
			}else{
				result = "�޷�ɾ����������ɾ���������ݣ�";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getCompany&result="+result);
		rd.forward(request, response);
		return ;
	}

	/*
	 *  ����鿴��Ա��ϸ��Ϣҳ��
	 * 
	 */
	protected void showPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ID
		String id = request.getParameter("id");
		
		/*���ò�ѯ����*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//�洢��ѯ���
		ContentZzxx zzxx = new ContentZzxx();
		zzxx = contentZzxxDao.queryZzxxByID(Integer.parseInt(id));
		
		request.setAttribute("zzxx", zzxx);
		request.getRequestDispatcher("/jsp/BasicInformation/showPerson.jsp").forward(request,
				response);

	}
	/*
	 *  ������Ա
	 * 
	 */
	protected void insertPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String education = request.getParameter("education");
		String birth = request.getParameter("birth");
		String pcard = request.getParameter("pcard");
		String school = request.getParameter("school");
		String worktime = request.getParameter("worktime");
		String workphone = request.getParameter("workphone");
		String joblevel = request.getParameter("joblevel");
		String phone = request.getParameter("phone");
		String company = request.getParameter("company");
		String companyID = request.getParameter("companyID");
		String depart1 = request.getParameter("depart1");
		String departID1 = request.getParameter("departID1");
		String depart2 = request.getParameter("depart2");
		String departID2 = request.getParameter("departID2");
		String depart3 = request.getParameter("depart3");
		String departID3 = request.getParameter("departID3");
		String job = request.getParameter("job");
		String jobdes = request.getParameter("jobdes");
		String roles = request.getParameter("roles");
		String username = request.getParameter("user");
		ContentZzxx Zzxx = new ContentZzxx();
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			Zzxx.setCzr(UserInfo.getName());
			Zzxx.setCzrID(UserInfo.getUsername());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Zzxx.setCzsj(data1);
			Zzxx.setName(name);
			Zzxx.setSex(sex);
			Zzxx.setEducation(education);
			Zzxx.setPcard(pcard);
			Zzxx.setSchool(school);
			Zzxx.setWorkphone(workphone);
			Zzxx.setJoblevel(joblevel);
			Zzxx.setBirth(DateFormat(birth));
			Zzxx.setWorktime(DateFormat(worktime));
			Zzxx.setPhone(phone);
			Zzxx.setJob(job);
			Zzxx.setJobdes(jobdes);
			Zzxx.setUsername(username);
			Zzxx.setPassword("111111");
			//��ƬĬ��·��
			if("��".equals(sex)){
				Zzxx.setFileURL("BasicInformation/zp1.jpg");
			}else{
				Zzxx.setFileURL("BasicInformation/zp2.jpg");
			}
			Zzxx.setRoles(roles);
			Zzxx.setCompany(company);
			Zzxx.setCompanyID(companyID);
			Zzxx.setDepart1(depart1);
			Zzxx.setDepartID1(departID1);
			Zzxx.setDepart2(depart2);
			Zzxx.setDepartID2(departID2);
			Zzxx.setDepart3(depart3);
			Zzxx.setDepartID3(departID3);
			String MenuID = "";
			if(!"".equals(departID3)&&departID3!=null){
				MenuID = departID3;
			}else if(!"".equals(departID2)&&departID2!=null){
				MenuID = departID2;
			}else{
				MenuID = departID1;
			}
			Zzxx.setFatherid(MenuID);
			//��ѯ���е������Ŀ
			MenuDao menuDao = new MenuDao();
			int sortnum = menuDao.QueryCountById("content_zzxx", MenuID);
			Zzxx.setSortnum(sortnum+1);
			//���������ݿ�
			ContentZzxxDao ContentZzxxDao = new ContentZzxxDao();
			int InsertID = ContentZzxxDao.insertZzxx(Zzxx);
			menuDao.InsertMenuContextById("node_zzxx", MenuID, InsertID,data1,UserInfo.getName(),UserInfo.getUsername());
			
			//����Ȩ��
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(roles);
			permissions.setUsername(username);
			permissions.setCzr(UserInfo.getName());
			permissions.setCzrID(UserInfo.getUsername());
			permissions.setCzsj(data1);
			permissionsDao.insertPermissions(permissions);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=showPerson&id="+InsertID);
			rd.forward(request, response);
		}
	}
	/*
	 *  ����༭ҳ��
	 * 
	 */
	protected void editPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ID
		String id = request.getParameter("id");
		
		/*���ò�ѯ����*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//�洢��ѯ���
		ContentZzxx zzxx = new ContentZzxx();
		zzxx = contentZzxxDao.queryZzxxByID(Integer.parseInt(id));
		
		request.setAttribute("zzxx", zzxx);
		request.getRequestDispatcher("/jsp/BasicInformation/editPerson.jsp").forward(request,
				response);

	}
	/*
	 *  ������Ա��Ϣ������Ա������ϢԱ��
	 * 
	 */
	protected void updatePerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String personid = request.getParameter("personid");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String education = request.getParameter("education");
		String birth = request.getParameter("birth");
		String pcard = request.getParameter("pcard");
		String school = request.getParameter("school");
		String worktime = request.getParameter("worktime");
		String workphone = request.getParameter("workphone");
		String joblevel = request.getParameter("joblevel");
		String phone = request.getParameter("phone");
		String company = request.getParameter("company");
		String companyID = request.getParameter("companyID");
		String depart1 = request.getParameter("depart1");
		String departID1 = request.getParameter("departID1");
		String depart2 = request.getParameter("depart2");
		String departID2 = request.getParameter("departID2");
		String depart3 = request.getParameter("depart3");
		String departID3 = request.getParameter("departID3");
		String job = request.getParameter("job");
		String jobdes = request.getParameter("jobdes");
		String roles = request.getParameter("roles");
		String username = request.getParameter("user");
		String fatherid = request.getParameter("fatherid");
		ContentZzxx Zzxx = new ContentZzxx();
		Zzxx.setId(Integer.parseInt(personid));
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			Zzxx.setCzr(UserInfo.getName());
			Zzxx.setCzrID(UserInfo.getUsername());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Zzxx.setCzsj(data1);
			Zzxx.setName(name);
			Zzxx.setSex(sex);
			Zzxx.setEducation(education);
			Zzxx.setPcard(pcard);
			Zzxx.setSchool(school);
			Zzxx.setWorkphone(workphone);
			Zzxx.setJoblevel(joblevel);
			Zzxx.setBirth(DateFormat(birth));
			Zzxx.setWorktime(DateFormat(worktime));
			Zzxx.setPhone(phone);
			Zzxx.setJob(job);
			Zzxx.setJobdes(jobdes);
			Zzxx.setUsername(username);
			Zzxx.setRoles(roles);
			Zzxx.setCompany(company);
			Zzxx.setCompanyID(companyID);
			Zzxx.setDepart1(depart1);
			Zzxx.setDepartID1(departID1);
			Zzxx.setDepart2(depart2);
			Zzxx.setDepartID2(departID2);
			Zzxx.setDepart3(depart3);
			Zzxx.setDepartID3(departID3);
			String MenuID = "";
			if(!"".equals(departID3)&&departID3!=null){
				MenuID = departID3;
			}else if(!"".equals(departID2)&&departID2!=null){
				MenuID = departID2;
			}else{
				MenuID = departID1;
			}
			Zzxx.setFatherid(MenuID);
			ContentZzxxDao ContentZzxxDao = new ContentZzxxDao();
			//���޸ĵ�λ�Ͳ���
			if(!fatherid.equals(MenuID)){
				//��ѯ���е������Ŀ
				MenuDao menuDao = new MenuDao();
				int sortnum = menuDao.QueryCountById("content_zzxx", MenuID);
				Zzxx.setSortnum(sortnum+1);
				//�������²���
				menuDao.InsertMenuContextById("node_zzxx", MenuID, Integer.parseInt(personid),data1,UserInfo.getName(),UserInfo.getUsername());
				//����ԭ����
				menuDao.UpdateMenuContextById("node_zzxx", fatherid, personid,data1,UserInfo.getName(),UserInfo.getUsername());
			}
			ContentZzxxDao.updateZzxx(Zzxx);
			
			//����Ȩ��
			Permissions permissions = new Permissions();
			PermissionsDao permissionsDao = new PermissionsDao();
			permissions = permissionsDao.queryPermissionsByUsername(roles);
			permissions.setUsername(username);
			permissions.setCzr(UserInfo.getName());
			permissions.setCzrID(UserInfo.getUsername());
			permissions.setCzsj(data1);
			permissionsDao.updatePermissions(permissions);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=showPerson&id="+personid);
			rd.forward(request, response);
		}
	}


	/*
	 *  ɾ������
	 * 
	 */
	protected void deletePerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡɾ�����ID�źͲ˵���ID
		// TODO Auto-generated method stub
		String ContendId = request.getParameter("id");
		String fatherid = request.getParameter("fatherid");
		String user = request.getParameter("user");
		
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
			//ɾ�����ɷ���������
			if(ContentZzxxDao.DeleteZzxxById(Integer.parseInt(ContendId)) > 0){
				//���²˵����洢��
				MenuDao menuDao =new MenuDao();
				menuDao.UpdateMenuContextById("node_zzxx", fatherid, ContendId,data1,UserInfo.getName(),UserInfo.getUsername());
				//ɾ��Ȩ��
				PermissionsDao permissionsDao = new PermissionsDao();
				permissionsDao.DeletePermissionsByUsername(user);
				//���ش�����
				request.setAttribute("result", "��Ϣɾ���ɹ�!");
			} else {
				request.setAttribute("result", "��Ϣɾ��ʧ��!");
			}
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=query&MenuId="+fatherid);
			rd.forward(request, response);
		}
	}
	
		/*
		 *  ����˳��
		 * 
		 */
		protected void sortNum(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//��ȡɾ�����ID�źͲ˵���ID
			// TODO Auto-generated method stub
			String sortList = request.getParameter("sortList");
			String idList = request.getParameter("idList");
			String fatherid = request.getParameter("fatherid");
			//��ȡ��ǰ�û�
			HttpSession session = request.getSession(); 
			ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");

			if(UserInfo==null||"".equals(UserInfo)){
				response.setContentType("text/html;charset=gb2312");
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
				
			}else{
				ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
				String[] sortStr = sortList.split(",");
				String[] idSrt = idList.split(",");
				for(int i=0; i<sortStr.length; i++){ 
					contentZzxxDao.updateZzxxSort(Integer.parseInt(idSrt[i]),Integer.parseInt(sortStr[i]));
				}
				RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=query&MenuId="+fatherid);
				rd.forward(request, response);
			}
		}
	/*
	 * AJAX
	 * �ж��û��Ƿ����
	 */
	protected void FindSameUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String jg = "true";
		boolean result =false;
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		
		result= contentZzxxDao.FindSameUser(user);
		if(result){
			jg = "�û����Ѵ���";
		}
		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		
		out.print(jg);
		  
		out.flush();  
		out.close();
		
	}

	/*
	 * AJAX
	 * ��ѯһ����λ�б�
	 */
	protected void FindCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseCompany(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=��ѡ��=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}

	/*
	 * AJAX
	 * ��ѯ������λ�б�
	 */
	protected void FindDepart1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseDepart1(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=��ѡ��=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}
	/*
	 * AJAX
	 * ��ѯ������λ�б�
	 */
	protected void FindDepart2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseDepart2(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=��ѡ��=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}
	/*
	 * AJAX
	 * ��ѯ�ļ���λ�б�
	 */
	protected void FindDepart3(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parent_id = request.getParameter("parent_id");
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		ArrayList<Menu> CompanyList = new ArrayList<Menu>();
		CompanyList = contentZzxxDao.FindCompany(parent_id);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		out.print("<select onchange=\"chooseDepart3(this.value)\" class=\"STYLE1\">");
		out.print("<option value=''>=��ѡ��=</option>");
		for(int i=0;i<CompanyList.size();i++)
		{	
			out.print("<option value='"+CompanyList.get(i).getId()+"&#&"+CompanyList.get(i).getName()+"'>"+CompanyList.get(i).getName()+"</option>");						 
		} 
		out.print("</select>");
		out.flush();  
		out.close();
		
	}
	/*
	 *  ��ѯ�˻�
	 * 
	 */
	protected void getMyAccount(HttpServletRequest request,
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
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = contentZzxxDao.queryZzxxByID(UserInfo.getId());
			
			request.setAttribute("Zzxx", Zzxx);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/BasicInformation/showMyAccount.jsp").forward(request,
					response);
		}
	}
	/*
	 *  �༭�˻�
	 * 
	 */
	protected void editMyAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			
			ContentZzxx Zzxx = new ContentZzxx();
			Zzxx = contentZzxxDao.queryZzxxByID(UserInfo.getId());
			
			request.setAttribute("Zzxx", Zzxx);
			request.getRequestDispatcher("/jsp/BasicInformation/editMyAccount.jsp").forward(request,
					response);
		}
	}

	/*
	 *  �����˻�
	 * 
	 */
	protected void saveMyAccount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ZHID = request.getParameter("ZHID");
		String birth = request.getParameter("birth");
		String pcard = request.getParameter("pcard");
		String education = request.getParameter("education");
		String school = request.getParameter("school");
		String worktime = request.getParameter("worktime");
		String phone = request.getParameter("phone");
		String workphone = request.getParameter("workphone");
		String job = request.getParameter("job");
		String jobdes = request.getParameter("jobdes");
		String FileURL = request.getParameter("FileURL");
		String TempURL = request.getParameter("TempURL");
		
		String result= "�޸ĳɹ�";
		ContentZzxx Zzxx = new ContentZzxx();
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");

		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			Zzxx.setCzr(UserInfo.getName());
			Zzxx.setCzrID(UserInfo.getUsername());
			//��ȡ��ǰʱ��
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			Zzxx.setCzsj(data1);
			Zzxx.setId(Integer.parseInt(ZHID));
			Zzxx.setEducation(education);
			Zzxx.setPcard(pcard);
			Zzxx.setSchool(school);
			Zzxx.setWorkphone(workphone);
			Zzxx.setBirth(DateFormat(birth));
			Zzxx.setWorktime(DateFormat(worktime));
			Zzxx.setPhone(phone);
			Zzxx.setJob(job);
			Zzxx.setJobdes(jobdes);
			String FullFilePath = request.getRealPath("/UserFile");
			if(!"".equals(TempURL)&&TempURL!=null){
				if(!"".equals(FileURL)&&FileURL!=null){
					delFile(FullFilePath+"/"+FileURL);
				}
				FileURL = TempURL.replaceAll("temp", "photo");
				copyFile(FullFilePath+"/"+TempURL, FullFilePath+"/"+FileURL);   
		        delFile(FullFilePath+"/"+TempURL);   
			}
			Zzxx.setFileURL(FileURL);
			ContentZzxxDao ContentZzxxDao = new ContentZzxxDao();
			ContentZzxxDao.updateMyAccount(Zzxx);
			
			RequestDispatcher rd = request.getRequestDispatcher("ZzxxServlet?action=getMyAccount&result="+result);
			rd.forward(request, response);
		}
	}

	public void copyFile(String oldPath, String newPath) {   
        try {   
            int bytesum = 0;   
            int byteread = 0;   
            File oldfile = new File(oldPath);   
            if (oldfile.exists()) { // �ļ�����ʱ   
                InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�   
                FileOutputStream fs = new FileOutputStream(newPath);   
                byte[] buffer = new byte[1024];   
                while ((byteread = inStream.read(buffer)) != -1) {   
                    bytesum += byteread; // �ֽ��� �ļ���С   
                    fs.write(buffer, 0, byteread);   
                }   
                inStream.close();   
            }   
        } catch (Exception e) {   
            System.out.println("���Ƶ����ļ��������� ");   
            e.printStackTrace();   
  
        }   
  
    }  
	 public void delFile(String filePathAndName) {   
         try {   
             String filePath = filePathAndName;   
             filePath = filePath.toString();   
             java.io.File myDelFile = new java.io.File(filePath);   
             myDelFile.delete();   
   
         } catch (Exception e) {   
             System.out.println("ɾ���ļ��������� ");   
             e.printStackTrace();   
   
         }   
     }   
	/*
	 *  �޸�����
	 */
	protected void editPassWord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String oldPS = request.getParameter("oldPS");
		String newPS = request.getParameter("newPS");
		String result= "";
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
			
		}else{
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			String PS = contentZzxxDao.queryPWByID(UserInfo.getId());
			if(PS.equals(oldPS)){
				contentZzxxDao.updatePWByID(newPS, UserInfo.getId());
				result= "�޸ĳɹ�";
				request.getRequestDispatcher("ZzxxServlet?action=getMyAccount&result="+result).forward(request,
						response);
			}else{
				result= "ԭ�������벻��ȷ";
				request.getRequestDispatcher("/jsp/BasicInformation/editPassWord.jsp?result="+result).forward(request,
						response);
			}
		}
	}
	/*
	 *  �½��˵��������������Ա�����̶���������
	 * 
	 */
	protected void getThreeSelectFree(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String backname = request.getParameter("name");
		String backid = request.getParameter("id");
		MenuDao menuDao = new MenuDao();
		// List
		String str="";
		str = menuDao.QueryAllMenuInfo("node_zzxx");
		request.setAttribute("menuList", str);
		request.setAttribute("backid", backid);
		request.setAttribute("backname", backname);
		request.getRequestDispatcher("/jsp/ThreeSelectFree.jsp").forward(request,
				response);
		return;
	}
	/*
	 * ��ѯ��ѡ����
	 * 
	 */

	protected void getLeftSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ͨ���˵����ID�Ų�ѯ
		String MenuId = request.getParameter("MenuId");
//		String pid = request.getParameter("pid");
		/*���ò�ѯ����*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//�洢��ѯ���
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		//��ѯ��ϢԱ
//		if("0".equals(pid)){
//			ZzxxList = contentZzxxDao.queryXxyByCompanyID(MenuId);
//			for(int i=0;i<ZzxxList.size();i++)
//			{	
//				out.print("<option value='"+ZzxxList.get(i).getUsername()+"'>"+"��ϢԱ"+ZzxxList.get(i).getName()+"</option>");						 
//			} 
//		}
		//��ȡ�˵�ID���б�
		MenuDao menuDao =new MenuDao();
		String idList = menuDao.QueryMenuById("node_zzxx", MenuId);
		if(!"".equals(idList)&&idList!=null){
			ZzxxList = contentZzxxDao.queryZzxxByIdListALL(idList);
			for(int i=0;i<ZzxxList.size();i++)
			{	
				out.print("<option value='"+ZzxxList.get(i).getUsername()+"'>"+ZzxxList.get(i).getName()+"</option>");						 
			} 
		}
		out.flush();  
		out.close();
	}
	/*
	 * ���ѡ����ϢԱ��
	 * 
	 */

	protected void fastChoose(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ͨ���˵����ID�Ų�ѯ
		String roles = request.getParameter("val");
		/*���ò�ѯ����*/
		ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
		//�洢��ѯ���
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter(); 
		//��ȡ�˵�ID���б�
		MenuDao menuDao =new MenuDao();
		if(!"".equals(roles)&&roles!=null){
			ZzxxList = contentZzxxDao.queryZzxxByRoles(roles);
			for(int i=0;i<ZzxxList.size();i++)
			{	
				out.print("<option value='"+ZzxxList.get(i).getUsername()+"'>"+ZzxxList.get(i).getName()+"</option>");						 
			} 
		}
		out.flush();  
		out.close();
	}
}
