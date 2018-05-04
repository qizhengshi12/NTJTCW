package com.safety.entity;

import java.sql.Timestamp;
import java.sql.Date;

import javax.servlet.http.HttpSession;

public class ContentZzxx {
	private int id;
	private String name;//姓名
	private String sex;//性别
	private String education;//学历
	private Date birth;//出生日期
	private String pcard;//身份证
	private String school;//毕业院校
	private Date worktime;//参加工作时间
	private String phone;//联系方式
	private String workphone;//办公电话
	private String joblevel;//职称级别
	private String job;//岗位
	private String jobdes;//岗位说明
	private String username;//用户名
	private String password;//密码
	private String roles;//角色
	private String company;//一级单位
	private String companyID;//一级单位ID
	private String depart1;//二级单位
	private String departID1;//二级单位ID
	private String depart2;//三级单位
	private String departID2;//三级单位ID
	private String depart3;//四级单位
	private String departID3;//四级单位ID
	private String fatherid;//父节点ID
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private int sortnum;//排列序号
	private String FileURL;//照片路径
	

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
