package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class SjbbAll {
	private int id;//审计报表——审计情况统计表
	private String bt;//标题
	private Date sj;//时间
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private String czrphone;//联系电话
	private String dwfzr;//单位负责人
	private String tjfzr;//统计负责人
	private String tjzt;//提交状态（1：提交2：保存草稿）
	//审计情况统计表
	private String zb1;//一、内部审计机构
	private String zb2;//专职机构
	private String zb3;//二、内部审计人员
	private String zb4;//专职人员
	private String zb5;//三、完成审计项目
	private String zb6;//财务收支审计
	private String zb7;//基本建设审计
	private String zb8;//经济责任审计
	private String zb9;//效益审计
	private String zb10;//内部控制评审
	private String zb11;//信息系统审计
	private String zb12;//专项审查
	private String zb13;//其他
	private String zb14;//四、审计总金额
	private String zb15;//五、查出问题金额
	private String zb16;//违规金额
	private String zb17;//损失浪费金额
	private String zb18;//管理不规范金额
	private String zb19;//六、促进增收节支金额
	private String zb20;//七、促进完善规章制度
	private String zb21;//八、提出建议意见被采纳
	private String zb22;//九、移送司法、纪检监察机关处理线索（案件）
	private String zb23;//十、移送司法、纪检监察机关处理人员
	private String zb24;//十一、实际给予行政处分
	//财务收支审计报表			
	private String zbs1;//一、审计单位
	private String zbs2;//二、审计总金额
	private String zbs3;//三、查出违规金额
	private String zbs4;//其中：违规改变资金用途 
	private String zbs5;//虚报冒领骗取资金
	private String zbs6;//账外资产
	private String zbs7;//未按规定征收、缴纳财政收入
	private String zbs8;//损益（收支）不实
	private String zbs9;//四、查出损失浪费金额
	private String zbs10;//五、查出管理不规范金额
	private String zbs11;//六、促进增收节支金额
	private String zbs12;//七、下达审计决定
	private String zbs13;//八、落实审计决定
	private String zbs14;//九、提出审计建议
	private String zbs15;//十、采纳审计建议
	private String zbs16;//十一、促进完善规章制度
	private String zbs17;//十二、百万元以上违纪单位
	private String zbs18;//十三、万元以上贪污贿赂案件
	//基本建设审计报表			
	private String zbt1;//一、审计项目
	private String zbt2;//其中：竣工决算审计
	private String zbt3;//跟踪审计
	private String zbt4;//其他专项审计
	private String zbt5;//二、审计总金额
	private String zbt6;//其中：竣工决算审计
	private String zbt7;//跟踪审计
	private String zbt8;//其他专项审计
	private String zbt9;//三、核减投资（结算）额
	private String zbt10;//四、查出违规金额
	private String zbt11;//其中：超规模、超标准建设
	private String zbt12;//挤占挪用建设资金
	private String zbt13;//建设资金不落实
	private String zbt14;//多列(少列）工程成本
	private String zbt15;//漏交税费
	private String zbt16;//侵害群众利益问题
	private String zbt17;//五、查出损失浪费金额
	private String zbt18;//六、查出管理不规范金额
	private String zbt19;//七、应归还原渠道资金
	private String zbt20;//其中：已归还资金
	private String zbt21;//八、促进完善规章制度
	private String zbt22;//九、下达审计决定条数
	private String zbt23;//十、落实审计决定条数
	private String zbt24;//十一、提出审计建议条数
	private String zbt25;//十二、采纳审计建议条数
	//经济责任审计报表			
	private String zbf1;//一、 审计经济责任人
	private String zbf2;//其中：行政单位
	private String zbf3;//事业单位
	private String zbf4;//企业
	private String zbf5;//二、审计单位
	private String zbf6;//其中：行政单位
	private String zbf7;//事业单位
	private String zbf8;//企业
	private String zbf9;//三、查出违规金额
	private String zbf10;//其中：直接责任
	private String zbf11;//主管责任
	private String zbf12;//领导责任
	private String zbf13;//四、查出损失浪费金额
	private String zbf14;//其中：直接责任
	private String zbf15;//主管责任
	private String zbf16;//领导责任
	private String zbf17;//五、查出管理不规范金额
	private String zbf18;//其中：直接责任
	private String zbf19;//主管责任
	private String zbf20;//领导责任
	private String zbf21;//六、领导干部涉及个人经济问题
	private String zbf22;//其中：人数
	private String zbf23;//涉及金额
	private String zbf24;//七、人员处理处分
	private String zbf25;//其中：撤职、降级
	private String zbf26;//其他处分
	private String zbf27;//移送司法机关
	private String zbf28;//移送纪检监察机关
	private String zbf29;//八、下达审计决定
	private String zbf30;//九、落实审计决定
	private String zbf31;//十、提出审计建议
	private String zbf32;//十一、采纳审计建议
	private String zbf33;//十二、促进完善规章制度
	//审计机构人员报表				
	private String zbv1;//一、已建机构	合    计
	private String zbv2;//处    级
	private String zbv3;//科    级
	private String zbv4;//科级以下
	private String zbv5;//二、人员编制
	private String zbv6;//三、配备人员
	private String zbv7;//（一）按工作性质分	专    职
	private String zbv8;//兼    职
	private String zbv9;//（二）按学历分	大专及以上
	private String zbv10;//中专及以下
	private String zbv11;//（三）按职称分	高    级
	private String zbv12;//中    级
	private String zbv13;//初级以下
	private String zbv14;//（四）按年龄分	45岁以上
	private String zbv15;//30至44岁
	private String zbv16;//29岁以下
	private String zbv17;//四、培训人员	合    计
	private String zbv18;//自办培训
	private String zbv19;//参加部办培训
	private String zbv20;//参加外系统、单位培训
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSj() {
		return sj;
	}
	public void setSj(Date sj) {
		this.sj = sj;
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
	public String getCzrdw() {
		return czrdw;
	}
	public void setCzrdw(String czrdw) {
		this.czrdw = czrdw;
	}
	public String getCzrphone() {
		return czrphone;
	}
	public void setCzrphone(String czrphone) {
		this.czrphone = czrphone;
	}
	public String getZb1() {
		return zb1;
	}
	public void setZb1(String zb1) {
		this.zb1 = zb1;
	}
	public String getZb2() {
		return zb2;
	}
	public void setZb2(String zb2) {
		this.zb2 = zb2;
	}
	public String getZb3() {
		return zb3;
	}
	public void setZb3(String zb3) {
		this.zb3 = zb3;
	}
	public String getZb4() {
		return zb4;
	}
	public void setZb4(String zb4) {
		this.zb4 = zb4;
	}
	public String getZb5() {
		return zb5;
	}
	public void setZb5(String zb5) {
		this.zb5 = zb5;
	}
	public String getZb6() {
		return zb6;
	}
	public void setZb6(String zb6) {
		this.zb6 = zb6;
	}
	public String getZb7() {
		return zb7;
	}
	public void setZb7(String zb7) {
		this.zb7 = zb7;
	}
	public String getZb8() {
		return zb8;
	}
	public void setZb8(String zb8) {
		this.zb8 = zb8;
	}
	public String getZb9() {
		return zb9;
	}
	public void setZb9(String zb9) {
		this.zb9 = zb9;
	}
	public String getZb10() {
		return zb10;
	}
	public void setZb10(String zb10) {
		this.zb10 = zb10;
	}
	public String getZb11() {
		return zb11;
	}
	public void setZb11(String zb11) {
		this.zb11 = zb11;
	}
	public String getZb12() {
		return zb12;
	}
	public void setZb12(String zb12) {
		this.zb12 = zb12;
	}
	public String getZb13() {
		return zb13;
	}
	public void setZb13(String zb13) {
		this.zb13 = zb13;
	}
	public String getZb14() {
		return zb14;
	}
	public void setZb14(String zb14) {
		this.zb14 = zb14;
	}
	public String getZb15() {
		return zb15;
	}
	public void setZb15(String zb15) {
		this.zb15 = zb15;
	}
	public String getZb16() {
		return zb16;
	}
	public void setZb16(String zb16) {
		this.zb16 = zb16;
	}
	public String getZb17() {
		return zb17;
	}
	public void setZb17(String zb17) {
		this.zb17 = zb17;
	}
	public String getZb18() {
		return zb18;
	}
	public void setZb18(String zb18) {
		this.zb18 = zb18;
	}
	public String getZb19() {
		return zb19;
	}
	public void setZb19(String zb19) {
		this.zb19 = zb19;
	}
	public String getZb20() {
		return zb20;
	}
	public void setZb20(String zb20) {
		this.zb20 = zb20;
	}
	public String getZb21() {
		return zb21;
	}
	public void setZb21(String zb21) {
		this.zb21 = zb21;
	}
	public String getZb22() {
		return zb22;
	}
	public void setZb22(String zb22) {
		this.zb22 = zb22;
	}
	public String getZb23() {
		return zb23;
	}
	public void setZb23(String zb23) {
		this.zb23 = zb23;
	}
	public String getZb24() {
		return zb24;
	}
	public void setZb24(String zb24) {
		this.zb24 = zb24;
	}
	public String getDwfzr() {
		return dwfzr;
	}
	public void setDwfzr(String dwfzr) {
		this.dwfzr = dwfzr;
	}
	public String getTjfzr() {
		return tjfzr;
	}
	public void setTjfzr(String tjfzr) {
		this.tjfzr = tjfzr;
	}
	public String getZbs1() {
		return zbs1;
	}
	public void setZbs1(String zbs1) {
		this.zbs1 = zbs1;
	}
	public String getZbs2() {
		return zbs2;
	}
	public void setZbs2(String zbs2) {
		this.zbs2 = zbs2;
	}
	public String getZbs3() {
		return zbs3;
	}
	public void setZbs3(String zbs3) {
		this.zbs3 = zbs3;
	}
	public String getZbs4() {
		return zbs4;
	}
	public void setZbs4(String zbs4) {
		this.zbs4 = zbs4;
	}
	public String getZbs5() {
		return zbs5;
	}
	public void setZbs5(String zbs5) {
		this.zbs5 = zbs5;
	}
	public String getZbs6() {
		return zbs6;
	}
	public void setZbs6(String zbs6) {
		this.zbs6 = zbs6;
	}
	public String getZbs7() {
		return zbs7;
	}
	public void setZbs7(String zbs7) {
		this.zbs7 = zbs7;
	}
	public String getZbs8() {
		return zbs8;
	}
	public void setZbs8(String zbs8) {
		this.zbs8 = zbs8;
	}
	public String getZbs9() {
		return zbs9;
	}
	public void setZbs9(String zbs9) {
		this.zbs9 = zbs9;
	}
	public String getZbs10() {
		return zbs10;
	}
	public void setZbs10(String zbs10) {
		this.zbs10 = zbs10;
	}
	public String getZbs11() {
		return zbs11;
	}
	public void setZbs11(String zbs11) {
		this.zbs11 = zbs11;
	}
	public String getZbs12() {
		return zbs12;
	}
	public void setZbs12(String zbs12) {
		this.zbs12 = zbs12;
	}
	public String getZbs13() {
		return zbs13;
	}
	public void setZbs13(String zbs13) {
		this.zbs13 = zbs13;
	}
	public String getZbs14() {
		return zbs14;
	}
	public void setZbs14(String zbs14) {
		this.zbs14 = zbs14;
	}
	public String getZbs15() {
		return zbs15;
	}
	public void setZbs15(String zbs15) {
		this.zbs15 = zbs15;
	}
	public String getZbs16() {
		return zbs16;
	}
	public void setZbs16(String zbs16) {
		this.zbs16 = zbs16;
	}
	public String getZbs17() {
		return zbs17;
	}
	public void setZbs17(String zbs17) {
		this.zbs17 = zbs17;
	}
	public String getZbs18() {
		return zbs18;
	}
	public void setZbs18(String zbs18) {
		this.zbs18 = zbs18;
	}
	public String getZbt1() {
		return zbt1;
	}
	public void setZbt1(String zbt1) {
		this.zbt1 = zbt1;
	}
	public String getZbt2() {
		return zbt2;
	}
	public void setZbt2(String zbt2) {
		this.zbt2 = zbt2;
	}
	public String getZbt3() {
		return zbt3;
	}
	public void setZbt3(String zbt3) {
		this.zbt3 = zbt3;
	}
	public String getZbt4() {
		return zbt4;
	}
	public void setZbt4(String zbt4) {
		this.zbt4 = zbt4;
	}
	public String getZbt5() {
		return zbt5;
	}
	public void setZbt5(String zbt5) {
		this.zbt5 = zbt5;
	}
	public String getZbt6() {
		return zbt6;
	}
	public void setZbt6(String zbt6) {
		this.zbt6 = zbt6;
	}
	public String getZbt7() {
		return zbt7;
	}
	public void setZbt7(String zbt7) {
		this.zbt7 = zbt7;
	}
	public String getZbt8() {
		return zbt8;
	}
	public void setZbt8(String zbt8) {
		this.zbt8 = zbt8;
	}
	public String getZbt9() {
		return zbt9;
	}
	public void setZbt9(String zbt9) {
		this.zbt9 = zbt9;
	}
	public String getZbt10() {
		return zbt10;
	}
	public void setZbt10(String zbt10) {
		this.zbt10 = zbt10;
	}
	public String getZbt11() {
		return zbt11;
	}
	public void setZbt11(String zbt11) {
		this.zbt11 = zbt11;
	}
	public String getZbt12() {
		return zbt12;
	}
	public void setZbt12(String zbt12) {
		this.zbt12 = zbt12;
	}
	public String getZbt13() {
		return zbt13;
	}
	public void setZbt13(String zbt13) {
		this.zbt13 = zbt13;
	}
	public String getZbt14() {
		return zbt14;
	}
	public void setZbt14(String zbt14) {
		this.zbt14 = zbt14;
	}
	public String getZbt15() {
		return zbt15;
	}
	public void setZbt15(String zbt15) {
		this.zbt15 = zbt15;
	}
	public String getZbt16() {
		return zbt16;
	}
	public void setZbt16(String zbt16) {
		this.zbt16 = zbt16;
	}
	public String getZbt17() {
		return zbt17;
	}
	public void setZbt17(String zbt17) {
		this.zbt17 = zbt17;
	}
	public String getZbt18() {
		return zbt18;
	}
	public void setZbt18(String zbt18) {
		this.zbt18 = zbt18;
	}
	public String getZbt19() {
		return zbt19;
	}
	public void setZbt19(String zbt19) {
		this.zbt19 = zbt19;
	}
	public String getZbt20() {
		return zbt20;
	}
	public void setZbt20(String zbt20) {
		this.zbt20 = zbt20;
	}
	public String getZbt21() {
		return zbt21;
	}
	public void setZbt21(String zbt21) {
		this.zbt21 = zbt21;
	}
	public String getZbt22() {
		return zbt22;
	}
	public void setZbt22(String zbt22) {
		this.zbt22 = zbt22;
	}
	public String getZbt23() {
		return zbt23;
	}
	public void setZbt23(String zbt23) {
		this.zbt23 = zbt23;
	}
	public String getZbt24() {
		return zbt24;
	}
	public void setZbt24(String zbt24) {
		this.zbt24 = zbt24;
	}
	public String getZbt25() {
		return zbt25;
	}
	public void setZbt25(String zbt25) {
		this.zbt25 = zbt25;
	}
	public String getZbf1() {
		return zbf1;
	}
	public void setZbf1(String zbf1) {
		this.zbf1 = zbf1;
	}
	public String getZbf2() {
		return zbf2;
	}
	public void setZbf2(String zbf2) {
		this.zbf2 = zbf2;
	}
	public String getZbf3() {
		return zbf3;
	}
	public void setZbf3(String zbf3) {
		this.zbf3 = zbf3;
	}
	public String getZbf4() {
		return zbf4;
	}
	public void setZbf4(String zbf4) {
		this.zbf4 = zbf4;
	}
	public String getZbf5() {
		return zbf5;
	}
	public void setZbf5(String zbf5) {
		this.zbf5 = zbf5;
	}
	public String getZbf6() {
		return zbf6;
	}
	public void setZbf6(String zbf6) {
		this.zbf6 = zbf6;
	}
	public String getZbf7() {
		return zbf7;
	}
	public void setZbf7(String zbf7) {
		this.zbf7 = zbf7;
	}
	public String getZbf8() {
		return zbf8;
	}
	public void setZbf8(String zbf8) {
		this.zbf8 = zbf8;
	}
	public String getZbf9() {
		return zbf9;
	}
	public void setZbf9(String zbf9) {
		this.zbf9 = zbf9;
	}
	public String getZbf10() {
		return zbf10;
	}
	public void setZbf10(String zbf10) {
		this.zbf10 = zbf10;
	}
	public String getZbf11() {
		return zbf11;
	}
	public void setZbf11(String zbf11) {
		this.zbf11 = zbf11;
	}
	public String getZbf12() {
		return zbf12;
	}
	public void setZbf12(String zbf12) {
		this.zbf12 = zbf12;
	}
	public String getZbf13() {
		return zbf13;
	}
	public void setZbf13(String zbf13) {
		this.zbf13 = zbf13;
	}
	public String getZbf14() {
		return zbf14;
	}
	public void setZbf14(String zbf14) {
		this.zbf14 = zbf14;
	}
	public String getZbf15() {
		return zbf15;
	}
	public void setZbf15(String zbf15) {
		this.zbf15 = zbf15;
	}
	public String getZbf16() {
		return zbf16;
	}
	public void setZbf16(String zbf16) {
		this.zbf16 = zbf16;
	}
	public String getZbf17() {
		return zbf17;
	}
	public void setZbf17(String zbf17) {
		this.zbf17 = zbf17;
	}
	public String getZbf18() {
		return zbf18;
	}
	public void setZbf18(String zbf18) {
		this.zbf18 = zbf18;
	}
	public String getZbf19() {
		return zbf19;
	}
	public void setZbf19(String zbf19) {
		this.zbf19 = zbf19;
	}
	public String getZbf20() {
		return zbf20;
	}
	public void setZbf20(String zbf20) {
		this.zbf20 = zbf20;
	}
	public String getZbf21() {
		return zbf21;
	}
	public void setZbf21(String zbf21) {
		this.zbf21 = zbf21;
	}
	public String getZbf22() {
		return zbf22;
	}
	public void setZbf22(String zbf22) {
		this.zbf22 = zbf22;
	}
	public String getZbf23() {
		return zbf23;
	}
	public void setZbf23(String zbf23) {
		this.zbf23 = zbf23;
	}
	public String getZbf24() {
		return zbf24;
	}
	public void setZbf24(String zbf24) {
		this.zbf24 = zbf24;
	}
	public String getZbf25() {
		return zbf25;
	}
	public void setZbf25(String zbf25) {
		this.zbf25 = zbf25;
	}
	public String getZbf26() {
		return zbf26;
	}
	public void setZbf26(String zbf26) {
		this.zbf26 = zbf26;
	}
	public String getZbf27() {
		return zbf27;
	}
	public void setZbf27(String zbf27) {
		this.zbf27 = zbf27;
	}
	public String getZbf28() {
		return zbf28;
	}
	public void setZbf28(String zbf28) {
		this.zbf28 = zbf28;
	}
	public String getZbf29() {
		return zbf29;
	}
	public void setZbf29(String zbf29) {
		this.zbf29 = zbf29;
	}
	public String getZbf30() {
		return zbf30;
	}
	public void setZbf30(String zbf30) {
		this.zbf30 = zbf30;
	}
	public String getZbf31() {
		return zbf31;
	}
	public void setZbf31(String zbf31) {
		this.zbf31 = zbf31;
	}
	public String getZbf32() {
		return zbf32;
	}
	public void setZbf32(String zbf32) {
		this.zbf32 = zbf32;
	}
	public String getZbf33() {
		return zbf33;
	}
	public void setZbf33(String zbf33) {
		this.zbf33 = zbf33;
	}
	public String getZbv1() {
		return zbv1;
	}
	public void setZbv1(String zbv1) {
		this.zbv1 = zbv1;
	}
	public String getZbv2() {
		return zbv2;
	}
	public void setZbv2(String zbv2) {
		this.zbv2 = zbv2;
	}
	public String getZbv3() {
		return zbv3;
	}
	public void setZbv3(String zbv3) {
		this.zbv3 = zbv3;
	}
	public String getZbv4() {
		return zbv4;
	}
	public void setZbv4(String zbv4) {
		this.zbv4 = zbv4;
	}
	public String getZbv5() {
		return zbv5;
	}
	public void setZbv5(String zbv5) {
		this.zbv5 = zbv5;
	}
	public String getZbv6() {
		return zbv6;
	}
	public void setZbv6(String zbv6) {
		this.zbv6 = zbv6;
	}
	public String getZbv7() {
		return zbv7;
	}
	public void setZbv7(String zbv7) {
		this.zbv7 = zbv7;
	}
	public String getZbv8() {
		return zbv8;
	}
	public void setZbv8(String zbv8) {
		this.zbv8 = zbv8;
	}
	public String getZbv9() {
		return zbv9;
	}
	public void setZbv9(String zbv9) {
		this.zbv9 = zbv9;
	}
	public String getZbv10() {
		return zbv10;
	}
	public void setZbv10(String zbv10) {
		this.zbv10 = zbv10;
	}
	public String getZbv11() {
		return zbv11;
	}
	public void setZbv11(String zbv11) {
		this.zbv11 = zbv11;
	}
	public String getZbv12() {
		return zbv12;
	}
	public void setZbv12(String zbv12) {
		this.zbv12 = zbv12;
	}
	public String getZbv13() {
		return zbv13;
	}
	public void setZbv13(String zbv13) {
		this.zbv13 = zbv13;
	}
	public String getZbv14() {
		return zbv14;
	}
	public void setZbv14(String zbv14) {
		this.zbv14 = zbv14;
	}
	public String getZbv15() {
		return zbv15;
	}
	public void setZbv15(String zbv15) {
		this.zbv15 = zbv15;
	}
	public String getZbv16() {
		return zbv16;
	}
	public void setZbv16(String zbv16) {
		this.zbv16 = zbv16;
	}
	public String getZbv17() {
		return zbv17;
	}
	public void setZbv17(String zbv17) {
		this.zbv17 = zbv17;
	}
	public String getZbv18() {
		return zbv18;
	}
	public void setZbv18(String zbv18) {
		this.zbv18 = zbv18;
	}
	public String getZbv19() {
		return zbv19;
	}
	public void setZbv19(String zbv19) {
		this.zbv19 = zbv19;
	}
	public String getZbv20() {
		return zbv20;
	}
	public void setZbv20(String zbv20) {
		this.zbv20 = zbv20;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	
}
