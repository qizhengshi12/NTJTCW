package com.safety.entity;

import java.sql.Timestamp;
import java.sql.Date;

import javax.servlet.http.HttpSession;

public class ContentZzxx {
	private int id;
	private String name;//����
	private String sex;//�Ա�
	private String education;//ѧ��
	private Date birth;//��������
	private String pcard;//���֤
	private String school;//��ҵԺУ
	private Date worktime;//�μӹ���ʱ��
	private String phone;//��ϵ��ʽ
	private String workphone;//�칫�绰
	private String joblevel;//ְ�Ƽ���
	private String job;//��λ
	private String jobdes;//��λ˵��
	private String username;//�û���
	private String password;//����
	private String roles;//��ɫ
	private String company;//һ����λ
	private String companyID;//һ����λID
	private String depart1;//������λ
	private String departID1;//������λID
	private String depart2;//������λ
	private String departID2;//������λID
	private String depart3;//�ļ���λ
	private String departID3;//�ļ���λID
	private String fatherid;//���ڵ�ID
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
	private int sortnum;//�������
	private String FileURL;//��Ƭ·��
	

	private HttpSession userSession;  

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPcard() {
		return pcard;
	}
	public void setPcard(String pcard) {
		this.pcard = pcard;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getWorktime() {
		return worktime;
	}
	public void setWorktime(Date worktime) {
		this.worktime = worktime;
	}
	public String getWorkphone() {
		return workphone;
	}
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}
	public String getJoblevel() {
		return joblevel;
	}
	public void setJoblevel(String joblevel) {
		this.joblevel = joblevel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJobdes() {
		return jobdes;
	}
	public void setJobdes(String jobdes) {
		this.jobdes = jobdes;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getDepart1() {
		return depart1;
	}
	public void setDepart1(String depart1) {
		this.depart1 = depart1;
	}
	public String getDepartID1() {
		return departID1;
	}
	public void setDepartID1(String departID1) {
		this.departID1 = departID1;
	}
	public String getDepart2() {
		return depart2;
	}
	public void setDepart2(String depart2) {
		this.depart2 = depart2;
	}
	public String getDepartID2() {
		return departID2;
	}
	public void setDepartID2(String departID2) {
		this.departID2 = departID2;
	}
	public String getDepart3() {
		return depart3;
	}
	public void setDepart3(String depart3) {
		this.depart3 = depart3;
	}
	public String getDepartID3() {
		return departID3;
	}
	public void setDepartID3(String departID3) {
		this.departID3 = departID3;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public Timestamp getCzsj() {
		return czsj;
	}
	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}
	public String getCzrID() {
		return czrID;
	}
	public void setCzrID(String czrID) {
		this.czrID = czrID;
	}
	public int getSortnum() {
		return sortnum;
	}
	public void setSortnum(int sortnum) {
		this.sortnum = sortnum;
	}
	public HttpSession getUserSession() {
		return userSession;
	}
	public void setUserSession(HttpSession userSession) {
		this.userSession = userSession;
	}
	public String getFileURL() {
		return FileURL;
	}
	public void setFileURL(String fileURL) {
		FileURL = fileURL;
	}
	
}
