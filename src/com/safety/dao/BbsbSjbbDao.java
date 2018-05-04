package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.SjbbJtyssjdytj;
import com.safety.entity.SjbbJtyssjdytjZxm;
import com.safety.entity.SjbbAll;
import com.safety.entity.SjbbLssfzc;
import com.safety.entity.SjbbLssfzcHzb;
import com.safety.entity.SjbbLssfzcZxm;
import com.safety.entity.SjbbLssfzcZxmHzb;


public class BbsbSjbbDao {
	
	/*
	 *根据条件
	 *查询审计统计表记录
	 */
	public  ArrayList<SjbbAll> querySjbbAllListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbAll> sjbbAllList = new ArrayList<SjbbAll>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from sjbb_all  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from sjbb_all "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbAll sjbbAll = new SjbbAll();
				sjbbAll.setId(rs.getInt("id"));
				sjbbAll.setBt(rs.getString("bt"));
				sjbbAll.setSj(rs.getDate("sj"));
				sjbbAll.setCzr(rs.getString("czr"));
				sjbbAll.setCzrdw(rs.getString("czrdw"));
				sjbbAll.setCzrID(rs.getString("czrID"));
				sjbbAll.setCzsj(rs.getTimestamp("czsj"));
				sjbbAll.setCzrphone(rs.getString("czrphone"));
				sjbbAll.setDwfzr(rs.getString("dwfzr"));
				sjbbAll.setTjfzr(rs.getString("tjfzr"));
				sjbbAll.setTjzt(rs.getString("tjzt"));
				sjbbAll.setZb1(rs.getString("zb1"));
				sjbbAll.setZb2(rs.getString("zb2"));
				sjbbAll.setZb3(rs.getString("zb3"));
				sjbbAll.setZb4(rs.getString("zb4"));
				sjbbAll.setZb5(rs.getString("zb5"));
				sjbbAll.setZb6(rs.getString("zb6"));
				sjbbAll.setZb7(rs.getString("zb7"));
				sjbbAll.setZb8(rs.getString("zb8"));
				sjbbAll.setZb9(rs.getString("zb9"));
				sjbbAll.setZb10(rs.getString("zb10"));
				sjbbAll.setZb11(rs.getString("zb11"));
				sjbbAll.setZb12(rs.getString("zb12"));
				sjbbAll.setZb13(rs.getString("zb13"));
				sjbbAll.setZb14(rs.getString("zb14"));
				sjbbAll.setZb15(rs.getString("zb15"));
				sjbbAll.setZb16(rs.getString("zb16"));
				sjbbAll.setZb17(rs.getString("zb17"));
				sjbbAll.setZb18(rs.getString("zb18"));
				sjbbAll.setZb19(rs.getString("zb19"));
				sjbbAll.setZb20(rs.getString("zb20"));
				sjbbAll.setZb21(rs.getString("zb21"));
				sjbbAll.setZb22(rs.getString("zb22"));
				sjbbAll.setZb23(rs.getString("zb23"));
				sjbbAll.setZb24(rs.getString("zb24"));
				sjbbAll.setZbs1(rs.getString("zbs1"));
				sjbbAll.setZbs2(rs.getString("zbs2"));
				sjbbAll.setZbs3(rs.getString("zbs3"));
				sjbbAll.setZbs4(rs.getString("zbs4"));
				sjbbAll.setZbs5(rs.getString("zbs5"));
				sjbbAll.setZbs6(rs.getString("zbs6"));
				sjbbAll.setZbs7(rs.getString("zbs7"));
				sjbbAll.setZbs8(rs.getString("zbs8"));
				sjbbAll.setZbs9(rs.getString("zbs9"));
				sjbbAll.setZbs10(rs.getString("zbs10"));
				sjbbAll.setZbs11(rs.getString("zbs11"));
				sjbbAll.setZbs12(rs.getString("zbs12"));
				sjbbAll.setZbs13(rs.getString("zbs13"));
				sjbbAll.setZbs14(rs.getString("zbs14"));
				sjbbAll.setZbs15(rs.getString("zbs15"));
				sjbbAll.setZbs16(rs.getString("zbs16"));
				sjbbAll.setZbs17(rs.getString("zbs17"));
				sjbbAll.setZbs18(rs.getString("zbs18"));
				sjbbAll.setZbt1(rs.getString("zbt1"));
				sjbbAll.setZbt2(rs.getString("zbt2"));
				sjbbAll.setZbt3(rs.getString("zbt3"));
				sjbbAll.setZbt4(rs.getString("zbt4"));
				sjbbAll.setZbt5(rs.getString("zbt5"));
				sjbbAll.setZbt6(rs.getString("zbt6"));
				sjbbAll.setZbt7(rs.getString("zbt7"));
				sjbbAll.setZbt8(rs.getString("zbt8"));
				sjbbAll.setZbt9(rs.getString("zbt9"));
				sjbbAll.setZbt10(rs.getString("zbt10"));
				sjbbAll.setZbt11(rs.getString("zbt11"));
				sjbbAll.setZbt12(rs.getString("zbt12"));
				sjbbAll.setZbt13(rs.getString("zbt13"));
				sjbbAll.setZbt14(rs.getString("zbt14"));
				sjbbAll.setZbt15(rs.getString("zbt15"));
				sjbbAll.setZbt16(rs.getString("zbt16"));
				sjbbAll.setZbt17(rs.getString("zbt17"));
				sjbbAll.setZbt18(rs.getString("zbt18"));
				sjbbAll.setZbt19(rs.getString("zbt19"));
				sjbbAll.setZbt20(rs.getString("zbt20"));
				sjbbAll.setZbt21(rs.getString("zbt21"));
				sjbbAll.setZbt22(rs.getString("zbt22"));
				sjbbAll.setZbt23(rs.getString("zbt23"));
				sjbbAll.setZbt24(rs.getString("zbt24"));
				sjbbAll.setZbt25(rs.getString("zbt25"));
				sjbbAll.setZbf1(rs.getString("zbf1"));
				sjbbAll.setZbf2(rs.getString("zbf2"));
				sjbbAll.setZbf3(rs.getString("zbf3"));
				sjbbAll.setZbf4(rs.getString("zbf4"));
				sjbbAll.setZbf5(rs.getString("zbf5"));
				sjbbAll.setZbf6(rs.getString("zbf6"));
				sjbbAll.setZbf7(rs.getString("zbf7"));
				sjbbAll.setZbf8(rs.getString("zbf8"));
				sjbbAll.setZbf9(rs.getString("zbf9"));
				sjbbAll.setZbf10(rs.getString("zbf10"));
				sjbbAll.setZbf11(rs.getString("zbf11"));
				sjbbAll.setZbf12(rs.getString("zbf12"));
				sjbbAll.setZbf13(rs.getString("zbf13"));
				sjbbAll.setZbf14(rs.getString("zbf14"));
				sjbbAll.setZbf15(rs.getString("zbf15"));
				sjbbAll.setZbf16(rs.getString("zbf16"));
				sjbbAll.setZbf17(rs.getString("zbf17"));
				sjbbAll.setZbf18(rs.getString("zbf18"));
				sjbbAll.setZbf19(rs.getString("zbf19"));
				sjbbAll.setZbf20(rs.getString("zbf20"));
				sjbbAll.setZbf21(rs.getString("zbf21"));
				sjbbAll.setZbf22(rs.getString("zbf22"));
				sjbbAll.setZbf23(rs.getString("zbf23"));
				sjbbAll.setZbf24(rs.getString("zbf24"));
				sjbbAll.setZbf25(rs.getString("zbf5"));
				sjbbAll.setZbf26(rs.getString("zbf6"));
				sjbbAll.setZbf27(rs.getString("zbf7"));
				sjbbAll.setZbf28(rs.getString("zbf8"));
				sjbbAll.setZbf29(rs.getString("zbf9"));
				sjbbAll.setZbf30(rs.getString("zbf10"));
				sjbbAll.setZbf31(rs.getString("zbf11"));
				sjbbAll.setZbf32(rs.getString("zbf12"));
				sjbbAll.setZbf33(rs.getString("zbf13"));
				sjbbAll.setZbv1(rs.getString("zbv1"));
				sjbbAll.setZbv2(rs.getString("zbv2"));
				sjbbAll.setZbv3(rs.getString("zbv3"));
				sjbbAll.setZbv4(rs.getString("zbv4"));
				sjbbAll.setZbv5(rs.getString("zbv5"));
				sjbbAll.setZbv6(rs.getString("zbv6"));
				sjbbAll.setZbv7(rs.getString("zbv7"));
				sjbbAll.setZbv8(rs.getString("zbv8"));
				sjbbAll.setZbv9(rs.getString("zbv9"));
				sjbbAll.setZbv10(rs.getString("zbv10"));
				sjbbAll.setZbv11(rs.getString("zbv11"));
				sjbbAll.setZbv12(rs.getString("zbv12"));
				sjbbAll.setZbv13(rs.getString("zbv13"));
				sjbbAll.setZbv14(rs.getString("zbv14"));
				sjbbAll.setZbv15(rs.getString("zbv15"));
				sjbbAll.setZbv16(rs.getString("zbv16"));
				sjbbAll.setZbv17(rs.getString("zbv17"));
				sjbbAll.setZbv18(rs.getString("zbv18"));
				sjbbAll.setZbv19(rs.getString("zbv19"));
				sjbbAll.setZbv20(rs.getString("zbv20"));
				sjbbAllList.add(sjbbAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbAllList;
	}
	/*
	 *根据条件
	 *查询审计统计表记录数
	 */
	public  int querySjbbAllListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from sjbb_all";
		}else{
			sqlString = "select count(*) from sjbb_all "+srbt;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		int count=0;
		try {
			count = db.getRecordCount(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return count;
	}
	/*
	 *根据条件
	 *查询审计统计表记录
	 */
	public  ArrayList<SjbbAll> querySjbbAllByIDList(String IDList) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbAll> sjbbAllList = new ArrayList<SjbbAll>();
		String sqlString = "select * from sjbb_all where id in ("+IDList+") order by id desc ";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbAll sjbbAll = new SjbbAll();
				sjbbAll.setId(rs.getInt("id"));
				sjbbAll.setBt(rs.getString("bt"));
				sjbbAll.setSj(rs.getDate("sj"));
				sjbbAll.setCzr(rs.getString("czr"));
				sjbbAll.setCzrdw(rs.getString("czrdw"));
				sjbbAll.setCzrID(rs.getString("czrID"));
				sjbbAll.setCzsj(rs.getTimestamp("czsj"));
				sjbbAll.setCzrphone(rs.getString("czrphone"));
				sjbbAll.setDwfzr(rs.getString("dwfzr"));
				sjbbAll.setTjfzr(rs.getString("tjfzr"));
				sjbbAll.setTjzt(rs.getString("tjzt"));
				sjbbAll.setZb1(rs.getString("zb1"));
				sjbbAll.setZb2(rs.getString("zb2"));
				sjbbAll.setZb3(rs.getString("zb3"));
				sjbbAll.setZb4(rs.getString("zb4"));
				sjbbAll.setZb5(rs.getString("zb5"));
				sjbbAll.setZb6(rs.getString("zb6"));
				sjbbAll.setZb7(rs.getString("zb7"));
				sjbbAll.setZb8(rs.getString("zb8"));
				sjbbAll.setZb9(rs.getString("zb9"));
				sjbbAll.setZb10(rs.getString("zb10"));
				sjbbAll.setZb11(rs.getString("zb11"));
				sjbbAll.setZb12(rs.getString("zb12"));
				sjbbAll.setZb13(rs.getString("zb13"));
				sjbbAll.setZb14(rs.getString("zb14"));
				sjbbAll.setZb15(rs.getString("zb15"));
				sjbbAll.setZb16(rs.getString("zb16"));
				sjbbAll.setZb17(rs.getString("zb17"));
				sjbbAll.setZb18(rs.getString("zb18"));
				sjbbAll.setZb19(rs.getString("zb19"));
				sjbbAll.setZb20(rs.getString("zb20"));
				sjbbAll.setZb21(rs.getString("zb21"));
				sjbbAll.setZb22(rs.getString("zb22"));
				sjbbAll.setZb23(rs.getString("zb23"));
				sjbbAll.setZb24(rs.getString("zb24"));
				sjbbAll.setZbs1(rs.getString("zbs1"));
				sjbbAll.setZbs2(rs.getString("zbs2"));
				sjbbAll.setZbs3(rs.getString("zbs3"));
				sjbbAll.setZbs4(rs.getString("zbs4"));
				sjbbAll.setZbs5(rs.getString("zbs5"));
				sjbbAll.setZbs6(rs.getString("zbs6"));
				sjbbAll.setZbs7(rs.getString("zbs7"));
				sjbbAll.setZbs8(rs.getString("zbs8"));
				sjbbAll.setZbs9(rs.getString("zbs9"));
				sjbbAll.setZbs10(rs.getString("zbs10"));
				sjbbAll.setZbs11(rs.getString("zbs11"));
				sjbbAll.setZbs12(rs.getString("zbs12"));
				sjbbAll.setZbs13(rs.getString("zbs13"));
				sjbbAll.setZbs14(rs.getString("zbs14"));
				sjbbAll.setZbs15(rs.getString("zbs15"));
				sjbbAll.setZbs16(rs.getString("zbs16"));
				sjbbAll.setZbs17(rs.getString("zbs17"));
				sjbbAll.setZbs18(rs.getString("zbs18"));
				sjbbAll.setZbt1(rs.getString("zbt1"));
				sjbbAll.setZbt2(rs.getString("zbt2"));
				sjbbAll.setZbt3(rs.getString("zbt3"));
				sjbbAll.setZbt4(rs.getString("zbt4"));
				sjbbAll.setZbt5(rs.getString("zbt5"));
				sjbbAll.setZbt6(rs.getString("zbt6"));
				sjbbAll.setZbt7(rs.getString("zbt7"));
				sjbbAll.setZbt8(rs.getString("zbt8"));
				sjbbAll.setZbt9(rs.getString("zbt9"));
				sjbbAll.setZbt10(rs.getString("zbt10"));
				sjbbAll.setZbt11(rs.getString("zbt11"));
				sjbbAll.setZbt12(rs.getString("zbt12"));
				sjbbAll.setZbt13(rs.getString("zbt13"));
				sjbbAll.setZbt14(rs.getString("zbt14"));
				sjbbAll.setZbt15(rs.getString("zbt15"));
				sjbbAll.setZbt16(rs.getString("zbt16"));
				sjbbAll.setZbt17(rs.getString("zbt17"));
				sjbbAll.setZbt18(rs.getString("zbt18"));
				sjbbAll.setZbt19(rs.getString("zbt19"));
				sjbbAll.setZbt20(rs.getString("zbt20"));
				sjbbAll.setZbt21(rs.getString("zbt21"));
				sjbbAll.setZbt22(rs.getString("zbt22"));
				sjbbAll.setZbt23(rs.getString("zbt23"));
				sjbbAll.setZbt24(rs.getString("zbt24"));
				sjbbAll.setZbt25(rs.getString("zbt25"));
				sjbbAll.setZbf1(rs.getString("zbf1"));
				sjbbAll.setZbf2(rs.getString("zbf2"));
				sjbbAll.setZbf3(rs.getString("zbf3"));
				sjbbAll.setZbf4(rs.getString("zbf4"));
				sjbbAll.setZbf5(rs.getString("zbf5"));
				sjbbAll.setZbf6(rs.getString("zbf6"));
				sjbbAll.setZbf7(rs.getString("zbf7"));
				sjbbAll.setZbf8(rs.getString("zbf8"));
				sjbbAll.setZbf9(rs.getString("zbf9"));
				sjbbAll.setZbf10(rs.getString("zbf10"));
				sjbbAll.setZbf11(rs.getString("zbf11"));
				sjbbAll.setZbf12(rs.getString("zbf12"));
				sjbbAll.setZbf13(rs.getString("zbf13"));
				sjbbAll.setZbf14(rs.getString("zbf14"));
				sjbbAll.setZbf15(rs.getString("zbf15"));
				sjbbAll.setZbf16(rs.getString("zbf16"));
				sjbbAll.setZbf17(rs.getString("zbf17"));
				sjbbAll.setZbf18(rs.getString("zbf18"));
				sjbbAll.setZbf19(rs.getString("zbf19"));
				sjbbAll.setZbf20(rs.getString("zbf20"));
				sjbbAll.setZbf21(rs.getString("zbf21"));
				sjbbAll.setZbf22(rs.getString("zbf22"));
				sjbbAll.setZbf23(rs.getString("zbf23"));
				sjbbAll.setZbf24(rs.getString("zbf24"));
				sjbbAll.setZbf25(rs.getString("zbf5"));
				sjbbAll.setZbf26(rs.getString("zbf6"));
				sjbbAll.setZbf27(rs.getString("zbf7"));
				sjbbAll.setZbf28(rs.getString("zbf8"));
				sjbbAll.setZbf29(rs.getString("zbf9"));
				sjbbAll.setZbf30(rs.getString("zbf10"));
				sjbbAll.setZbf31(rs.getString("zbf11"));
				sjbbAll.setZbf32(rs.getString("zbf12"));
				sjbbAll.setZbf33(rs.getString("zbf13"));
				sjbbAll.setZbv1(rs.getString("zbv1"));
				sjbbAll.setZbv2(rs.getString("zbv2"));
				sjbbAll.setZbv3(rs.getString("zbv3"));
				sjbbAll.setZbv4(rs.getString("zbv4"));
				sjbbAll.setZbv5(rs.getString("zbv5"));
				sjbbAll.setZbv6(rs.getString("zbv6"));
				sjbbAll.setZbv7(rs.getString("zbv7"));
				sjbbAll.setZbv8(rs.getString("zbv8"));
				sjbbAll.setZbv9(rs.getString("zbv9"));
				sjbbAll.setZbv10(rs.getString("zbv10"));
				sjbbAll.setZbv11(rs.getString("zbv11"));
				sjbbAll.setZbv12(rs.getString("zbv12"));
				sjbbAll.setZbv13(rs.getString("zbv13"));
				sjbbAll.setZbv14(rs.getString("zbv14"));
				sjbbAll.setZbv15(rs.getString("zbv15"));
				sjbbAll.setZbv16(rs.getString("zbv16"));
				sjbbAll.setZbv17(rs.getString("zbv17"));
				sjbbAll.setZbv18(rs.getString("zbv18"));
				sjbbAll.setZbv19(rs.getString("zbv19"));
				sjbbAll.setZbv20(rs.getString("zbv20"));
				sjbbAllList.add(sjbbAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbAllList;
	}
	
	/*
	 *根据ID
	 *查询
	 */
	public  SjbbAll querySjbbAllByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		SjbbAll sjbbAll = new SjbbAll();
		String sqlString = "select * from sjbb_all where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				sjbbAll.setId(rs.getInt("id"));
				sjbbAll.setBt(rs.getString("bt"));
				sjbbAll.setSj(rs.getDate("sj"));
				sjbbAll.setCzr(rs.getString("czr"));
				sjbbAll.setCzrdw(rs.getString("czrdw"));
				sjbbAll.setCzrID(rs.getString("czrID"));
				sjbbAll.setCzsj(rs.getTimestamp("czsj"));
				sjbbAll.setCzrphone(rs.getString("czrphone"));
				sjbbAll.setDwfzr(rs.getString("dwfzr"));
				sjbbAll.setTjfzr(rs.getString("tjfzr"));
				sjbbAll.setZb1(rs.getString("zb1"));
				sjbbAll.setZb2(rs.getString("zb2"));
				sjbbAll.setZb3(rs.getString("zb3"));
				sjbbAll.setZb4(rs.getString("zb4"));
				sjbbAll.setZb5(rs.getString("zb5"));
				sjbbAll.setZb6(rs.getString("zb6"));
				sjbbAll.setZb7(rs.getString("zb7"));
				sjbbAll.setZb8(rs.getString("zb8"));
				sjbbAll.setZb9(rs.getString("zb9"));
				sjbbAll.setZb10(rs.getString("zb10"));
				sjbbAll.setZb11(rs.getString("zb11"));
				sjbbAll.setZb12(rs.getString("zb12"));
				sjbbAll.setZb13(rs.getString("zb13"));
				sjbbAll.setZb14(rs.getString("zb14"));
				sjbbAll.setZb15(rs.getString("zb15"));
				sjbbAll.setZb16(rs.getString("zb16"));
				sjbbAll.setZb17(rs.getString("zb17"));
				sjbbAll.setZb18(rs.getString("zb18"));
				sjbbAll.setZb19(rs.getString("zb19"));
				sjbbAll.setZb20(rs.getString("zb20"));
				sjbbAll.setZb21(rs.getString("zb21"));
				sjbbAll.setZb22(rs.getString("zb22"));
				sjbbAll.setZb23(rs.getString("zb23"));
				sjbbAll.setZb24(rs.getString("zb24"));
				sjbbAll.setZbs1(rs.getString("zbs1"));
				sjbbAll.setZbs2(rs.getString("zbs2"));
				sjbbAll.setZbs3(rs.getString("zbs3"));
				sjbbAll.setZbs4(rs.getString("zbs4"));
				sjbbAll.setZbs5(rs.getString("zbs5"));
				sjbbAll.setZbs6(rs.getString("zbs6"));
				sjbbAll.setZbs7(rs.getString("zbs7"));
				sjbbAll.setZbs8(rs.getString("zbs8"));
				sjbbAll.setZbs9(rs.getString("zbs9"));
				sjbbAll.setZbs10(rs.getString("zbs10"));
				sjbbAll.setZbs11(rs.getString("zbs11"));
				sjbbAll.setZbs12(rs.getString("zbs12"));
				sjbbAll.setZbs13(rs.getString("zbs13"));
				sjbbAll.setZbs14(rs.getString("zbs14"));
				sjbbAll.setZbs15(rs.getString("zbs15"));
				sjbbAll.setZbs16(rs.getString("zbs16"));
				sjbbAll.setZbs17(rs.getString("zbs17"));
				sjbbAll.setZbs18(rs.getString("zbs18"));
				sjbbAll.setZbt1(rs.getString("zbt1"));
				sjbbAll.setZbt2(rs.getString("zbt2"));
				sjbbAll.setZbt3(rs.getString("zbt3"));
				sjbbAll.setZbt4(rs.getString("zbt4"));
				sjbbAll.setZbt5(rs.getString("zbt5"));
				sjbbAll.setZbt6(rs.getString("zbt6"));
				sjbbAll.setZbt7(rs.getString("zbt7"));
				sjbbAll.setZbt8(rs.getString("zbt8"));
				sjbbAll.setZbt9(rs.getString("zbt9"));
				sjbbAll.setZbt10(rs.getString("zbt10"));
				sjbbAll.setZbt11(rs.getString("zbt11"));
				sjbbAll.setZbt12(rs.getString("zbt12"));
				sjbbAll.setZbt13(rs.getString("zbt13"));
				sjbbAll.setZbt14(rs.getString("zbt14"));
				sjbbAll.setZbt15(rs.getString("zbt15"));
				sjbbAll.setZbt16(rs.getString("zbt16"));
				sjbbAll.setZbt17(rs.getString("zbt17"));
				sjbbAll.setZbt18(rs.getString("zbt18"));
				sjbbAll.setZbt19(rs.getString("zbt19"));
				sjbbAll.setZbt20(rs.getString("zbt20"));
				sjbbAll.setZbt21(rs.getString("zbt21"));
				sjbbAll.setZbt22(rs.getString("zbt22"));
				sjbbAll.setZbt23(rs.getString("zbt23"));
				sjbbAll.setZbt24(rs.getString("zbt24"));
				sjbbAll.setZbt25(rs.getString("zbt25"));
				sjbbAll.setZbf1(rs.getString("zbf1"));
				sjbbAll.setZbf2(rs.getString("zbf2"));
				sjbbAll.setZbf3(rs.getString("zbf3"));
				sjbbAll.setZbf4(rs.getString("zbf4"));
				sjbbAll.setZbf5(rs.getString("zbf5"));
				sjbbAll.setZbf6(rs.getString("zbf6"));
				sjbbAll.setZbf7(rs.getString("zbf7"));
				sjbbAll.setZbf8(rs.getString("zbf8"));
				sjbbAll.setZbf9(rs.getString("zbf9"));
				sjbbAll.setZbf10(rs.getString("zbf10"));
				sjbbAll.setZbf11(rs.getString("zbf11"));
				sjbbAll.setZbf12(rs.getString("zbf12"));
				sjbbAll.setZbf13(rs.getString("zbf13"));
				sjbbAll.setZbf14(rs.getString("zbf14"));
				sjbbAll.setZbf15(rs.getString("zbf15"));
				sjbbAll.setZbf16(rs.getString("zbf16"));
				sjbbAll.setZbf17(rs.getString("zbf17"));
				sjbbAll.setZbf18(rs.getString("zbf18"));
				sjbbAll.setZbf19(rs.getString("zbf19"));
				sjbbAll.setZbf20(rs.getString("zbf20"));
				sjbbAll.setZbf21(rs.getString("zbf21"));
				sjbbAll.setZbf22(rs.getString("zbf22"));
				sjbbAll.setZbf23(rs.getString("zbf23"));
				sjbbAll.setZbf24(rs.getString("zbf24"));
				sjbbAll.setZbf25(rs.getString("zbf25"));
				sjbbAll.setZbf26(rs.getString("zbf26"));
				sjbbAll.setZbf27(rs.getString("zbf27"));
				sjbbAll.setZbf28(rs.getString("zbf28"));
				sjbbAll.setZbf29(rs.getString("zbf29"));
				sjbbAll.setZbf30(rs.getString("zbf30"));
				sjbbAll.setZbf31(rs.getString("zbf31"));
				sjbbAll.setZbf32(rs.getString("zbf32"));
				sjbbAll.setZbf33(rs.getString("zbf33"));
				sjbbAll.setZbv1(rs.getString("zbv1"));
				sjbbAll.setZbv2(rs.getString("zbv2"));
				sjbbAll.setZbv3(rs.getString("zbv3"));
				sjbbAll.setZbv4(rs.getString("zbv4"));
				sjbbAll.setZbv5(rs.getString("zbv5"));
				sjbbAll.setZbv6(rs.getString("zbv6"));
				sjbbAll.setZbv7(rs.getString("zbv7"));
				sjbbAll.setZbv8(rs.getString("zbv8"));
				sjbbAll.setZbv9(rs.getString("zbv9"));
				sjbbAll.setZbv10(rs.getString("zbv10"));
				sjbbAll.setZbv11(rs.getString("zbv11"));
				sjbbAll.setZbv12(rs.getString("zbv12"));
				sjbbAll.setZbv13(rs.getString("zbv13"));
				sjbbAll.setZbv14(rs.getString("zbv14"));
				sjbbAll.setZbv15(rs.getString("zbv15"));
				sjbbAll.setZbv16(rs.getString("zbv16"));
				sjbbAll.setZbv17(rs.getString("zbv17"));
				sjbbAll.setZbv18(rs.getString("zbv18"));
				sjbbAll.setZbv19(rs.getString("zbv19"));
				sjbbAll.setZbv20(rs.getString("zbv20"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbAll;
	}
	
	/*
	 *
	 *新增审计统计表记录
	 */
	public boolean insertSjbbAll(SjbbAll sjbbAll) {
		String sqlString = "insert into sjbb_all (bt,sj,czr,czrdw,czrID,czsj,czrphone,dwfzr,tjfzr,zb1,zb2,zb3,zb4,zb5,zb6,zb7,zb8,zb9,zb10,zb11,zb12,zb13,zb14,zb15,zb16,zb17,zb18,zb19,zb20,zb21,zb22,zb23,zb24,zbs1,zbs2,zbs3,zbs4,zbs5,zbs6,zbs7,zbs8,zbs9,zbs10,zbs11,zbs12,zbs13,zbs14,zbs15,zbs16,zbs17,zbs18,zbt1,zbt2,zbt3,zbt4,zbt5,zbt6,zbt7,zbt8,zbt9,zbt10,zbt11,zbt12,zbt13,zbt14,zbt15,zbt16,zbt17,zbt18,zbt19,zbt20,zbt21,zbt22,zbt23,zbt24,zbt25,zbf1,zbf2,zbf3,zbf4,zbf5,zbf6,zbf7,zbf8,zbf9,zbf10,zbf11,zbf12,zbf13,zbf14,zbf15,zbf16,zbf17,zbf18,zbf19,zbf20,zbf21,zbf22,zbf23,zbf24,zbf25,zbf26,zbf27,zbf28,zbf29,zbf30,zbf31,zbf32,zbf33,zbv1,zbv2,zbv3,zbv4,zbv5,zbv6,zbv7,zbv8,zbv9,zbv10,zbv11,zbv12,zbv13,zbv14,zbv15,zbv16,zbv17,zbv18,zbv19,zbv20,tjzt) values('";
		sqlString += sjbbAll.getBt() + "','";
		sqlString += sjbbAll.getSj() + "','";
		sqlString += sjbbAll.getCzr() + "','";
		sqlString += sjbbAll.getCzrdw() + "','";
		sqlString += sjbbAll.getCzrID()+ "','";
		sqlString += sjbbAll.getCzsj()  + "','";
		sqlString += sjbbAll.getCzrphone()  + "','";
		sqlString += sjbbAll.getDwfzr()  + "','";
		sqlString += sjbbAll.getTjfzr() + "','";
		sqlString += sjbbAll.getZb1()  + "','";
		sqlString += sjbbAll.getZb2()  + "','";
		sqlString += sjbbAll.getZb3()  + "','";
		sqlString += sjbbAll.getZb4()  + "','";
		sqlString += sjbbAll.getZb5()  + "','";
		sqlString += sjbbAll.getZb6()  + "','";
		sqlString += sjbbAll.getZb7()  + "','";
		sqlString += sjbbAll.getZb8()  + "','";
		sqlString += sjbbAll.getZb9()  + "','";
		sqlString += sjbbAll.getZb10()  + "','";
		sqlString += sjbbAll.getZb11()  + "','";
		sqlString += sjbbAll.getZb12()  + "','";
		sqlString += sjbbAll.getZb13()  + "','";
		sqlString += sjbbAll.getZb14()  + "','";
		sqlString += sjbbAll.getZb15()  + "','";
		sqlString += sjbbAll.getZb16()  + "','";
		sqlString += sjbbAll.getZb17()  + "','";
		sqlString += sjbbAll.getZb18()  + "','";
		sqlString += sjbbAll.getZb19()  + "','";
		sqlString += sjbbAll.getZb20()  + "','";
		sqlString += sjbbAll.getZb21()  + "','";
		sqlString += sjbbAll.getZb22()  + "','";
		sqlString += sjbbAll.getZb23()  + "','";
		sqlString += sjbbAll.getZb24()  + "','";
		sqlString += sjbbAll.getZbs1()  + "','";
		sqlString += sjbbAll.getZbs2()  + "','";
		sqlString += sjbbAll.getZbs3()  + "','";
		sqlString += sjbbAll.getZbs4()  + "','";
		sqlString += sjbbAll.getZbs5()  + "','";
		sqlString += sjbbAll.getZbs6()  + "','";
		sqlString += sjbbAll.getZbs7()  + "','";
		sqlString += sjbbAll.getZbs8()  + "','";
		sqlString += sjbbAll.getZbs9()  + "','";
		sqlString += sjbbAll.getZbs10()  + "','";
		sqlString += sjbbAll.getZbs11()  + "','";
		sqlString += sjbbAll.getZbs12()  + "','";
		sqlString += sjbbAll.getZbs13()  + "','";
		sqlString += sjbbAll.getZbs14()  + "','";
		sqlString += sjbbAll.getZbs15()  + "','";
		sqlString += sjbbAll.getZbs16()  + "','";
		sqlString += sjbbAll.getZbs17()  + "','";
		sqlString += sjbbAll.getZbs18() + "','";
		sqlString += sjbbAll.getZbt1()  + "','";
		sqlString += sjbbAll.getZbt2()  + "','";
		sqlString += sjbbAll.getZbt3()  + "','";
		sqlString += sjbbAll.getZbt4()  + "','";
		sqlString += sjbbAll.getZbt5()  + "','";
		sqlString += sjbbAll.getZbt6()  + "','";
		sqlString += sjbbAll.getZbt7()  + "','";
		sqlString += sjbbAll.getZbt8()  + "','";
		sqlString += sjbbAll.getZbt9()  + "','";
		sqlString += sjbbAll.getZbt10()  + "','";
		sqlString += sjbbAll.getZbt11()  + "','";
		sqlString += sjbbAll.getZbt12()  + "','";
		sqlString += sjbbAll.getZbt13()  + "','";
		sqlString += sjbbAll.getZbt14()  + "','";
		sqlString += sjbbAll.getZbt15()  + "','";
		sqlString += sjbbAll.getZbt16()  + "','";
		sqlString += sjbbAll.getZbt17()  + "','";
		sqlString += sjbbAll.getZbt18()  + "','";
		sqlString += sjbbAll.getZbt19()  + "','";
		sqlString += sjbbAll.getZbt20()  + "','";
		sqlString += sjbbAll.getZbt21()  + "','";
		sqlString += sjbbAll.getZbt22()  + "','";
		sqlString += sjbbAll.getZbt23()  + "','";
		sqlString += sjbbAll.getZbt24()  + "','";
		sqlString += sjbbAll.getZbt25() + "','";
		sqlString += sjbbAll.getZbf1()  + "','";
		sqlString += sjbbAll.getZbf2()  + "','";
		sqlString += sjbbAll.getZbf3()  + "','";
		sqlString += sjbbAll.getZbf4()  + "','";
		sqlString += sjbbAll.getZbf5()  + "','";
		sqlString += sjbbAll.getZbf6()  + "','";
		sqlString += sjbbAll.getZbf7()  + "','";
		sqlString += sjbbAll.getZbf8()  + "','";
		sqlString += sjbbAll.getZbf9()  + "','";
		sqlString += sjbbAll.getZbf10()  + "','";
		sqlString += sjbbAll.getZbf11()  + "','";
		sqlString += sjbbAll.getZbf12()  + "','";
		sqlString += sjbbAll.getZbf13()  + "','";
		sqlString += sjbbAll.getZbf14()  + "','";
		sqlString += sjbbAll.getZbf15()  + "','";
		sqlString += sjbbAll.getZbf16()  + "','";
		sqlString += sjbbAll.getZbf17()  + "','";
		sqlString += sjbbAll.getZbf18()  + "','";
		sqlString += sjbbAll.getZbf19()  + "','";
		sqlString += sjbbAll.getZbf20()  + "','";
		sqlString += sjbbAll.getZbf21()  + "','";
		sqlString += sjbbAll.getZbf22()  + "','";
		sqlString += sjbbAll.getZbf23()  + "','";
		sqlString += sjbbAll.getZbf24()  + "','";
		sqlString += sjbbAll.getZbf25()  + "','";
		sqlString += sjbbAll.getZbf26()  + "','";
		sqlString += sjbbAll.getZbf27()  + "','";
		sqlString += sjbbAll.getZbf28()  + "','";
		sqlString += sjbbAll.getZbf29()  + "','";
		sqlString += sjbbAll.getZbf30()  + "','";
		sqlString += sjbbAll.getZbf31()  + "','";
		sqlString += sjbbAll.getZbf32()  + "','";
		sqlString += sjbbAll.getZbf33()  + "','";
		sqlString += sjbbAll.getZbv1()  + "','";
		sqlString += sjbbAll.getZbv2()  + "','";
		sqlString += sjbbAll.getZbv3()  + "','";
		sqlString += sjbbAll.getZbv4()  + "','";
		sqlString += sjbbAll.getZbv5()  + "','";
		sqlString += sjbbAll.getZbv6()  + "','";
		sqlString += sjbbAll.getZbv7()  + "','";
		sqlString += sjbbAll.getZbv8()  + "','";
		sqlString += sjbbAll.getZbv9()  + "','";
		sqlString += sjbbAll.getZbv10()  + "','";
		sqlString += sjbbAll.getZbv11()  + "','";
		sqlString += sjbbAll.getZbv12()  + "','";
		sqlString += sjbbAll.getZbv13()  + "','";
		sqlString += sjbbAll.getZbv14()  + "','";
		sqlString += sjbbAll.getZbv15()  + "','";
		sqlString += sjbbAll.getZbv16()  + "','";
		sqlString += sjbbAll.getZbv17()  + "','";
		sqlString += sjbbAll.getZbv18()  + "','";
		sqlString += sjbbAll.getZbv19()  + "','";
		sqlString += sjbbAll.getZbv20()  + "','";
		sqlString += sjbbAll.getTjzt() + "')";
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *修改审计统计表记录
	 */
	public boolean updateSjbbAllRet(String id) {
		String sqlString = "update sjbb_all set tjzt='3' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}		
	/*
	 *根据ID
	 *修改审计统计表记录
	 */
	public boolean updateSjbbAll(SjbbAll sjbbAll) {
		String sqlString = "update sjbb_all set sj='"; 
		sqlString += sjbbAll.getSj() + "',bt='";
		sqlString += sjbbAll.getBt() + "',czr='";
		sqlString += sjbbAll.getCzr() + "',czrdw='";
		sqlString += sjbbAll.getCzrdw() + "',czrID='";
		sqlString += sjbbAll.getCzrID() + "',czsj='";
		sqlString += sjbbAll.getCzsj() + "',czrdw='";
		sqlString += sjbbAll.getCzrdw() + "',czrphone='";
		sqlString += sjbbAll.getCzrphone() + "',dwfzr='";
		sqlString += sjbbAll.getDwfzr() + "',tjfzr='";
		sqlString += sjbbAll.getTjfzr() + "',tjzt='";
		sqlString += sjbbAll.getTjzt() + "',zb1='";
		sqlString += sjbbAll.getZb1() + "',zb2='";
		sqlString += sjbbAll.getZb2() + "',zb3='";
		sqlString += sjbbAll.getZb3() + "',zb4='";
		sqlString += sjbbAll.getZb4() + "',zb5='";
		sqlString += sjbbAll.getZb5() + "',zb6='";
		sqlString += sjbbAll.getZb6() + "',zb7='";
		sqlString += sjbbAll.getZb7() + "',zb8='";
		sqlString += sjbbAll.getZb8() + "',zb9='";
		sqlString += sjbbAll.getZb9() + "',zb10='";
		sqlString += sjbbAll.getZb10() + "',zb11='";
		sqlString += sjbbAll.getZb11() + "',zb12='";
		sqlString += sjbbAll.getZb12() + "',zb13='";
		sqlString += sjbbAll.getZb13() + "',zb14='";
		sqlString += sjbbAll.getZb14() + "',zb15='";
		sqlString += sjbbAll.getZb15() + "',zb16='";
		sqlString += sjbbAll.getZb16() + "',zb17='";
		sqlString += sjbbAll.getZb17() + "',zb18='";
		sqlString += sjbbAll.getZb18() + "',zb19='";
		sqlString += sjbbAll.getZb19() + "',zb20='";
		sqlString += sjbbAll.getZb20() + "',zb21='";
		sqlString += sjbbAll.getZb21() + "',zb22='";
		sqlString += sjbbAll.getZb22() + "',zb23='";
		sqlString += sjbbAll.getZb23() + "',zb24='";
		sqlString += sjbbAll.getZb24() + "',zbs1='";
		sqlString += sjbbAll.getZbs1() + "',zbs2='";
		sqlString += sjbbAll.getZbs2() + "',zbs3='";
		sqlString += sjbbAll.getZbs3() + "',zbs4='";
		sqlString += sjbbAll.getZbs4() + "',zbs5='";
		sqlString += sjbbAll.getZbs5() + "',zbs6='";
		sqlString += sjbbAll.getZbs6() + "',zbs7='";
		sqlString += sjbbAll.getZbs7() + "',zbs8='";
		sqlString += sjbbAll.getZbs8() + "',zbs9='";
		sqlString += sjbbAll.getZbs9() + "',zbs10='";
		sqlString += sjbbAll.getZbs10() + "',zbs11='";
		sqlString += sjbbAll.getZbs11() + "',zbs12='";
		sqlString += sjbbAll.getZbs12() + "',zbs13='";
		sqlString += sjbbAll.getZbs13() + "',zbs14='";
		sqlString += sjbbAll.getZbs14() + "',zbs15='";
		sqlString += sjbbAll.getZbs15() + "',zbs16='";
		sqlString += sjbbAll.getZbs16() + "',zbs17='";
		sqlString += sjbbAll.getZbs17() + "',zbs18='";
		sqlString += sjbbAll.getZbs18() + "',zbt1='";
		sqlString += sjbbAll.getZbt1() + "',zbt2='";
		sqlString += sjbbAll.getZbt2() + "',zbt3='";
		sqlString += sjbbAll.getZbt3() + "',zbt4='";
		sqlString += sjbbAll.getZbt4() + "',zbt5='";
		sqlString += sjbbAll.getZbt5() + "',zbt6='";
		sqlString += sjbbAll.getZbt6() + "',zbt7='";
		sqlString += sjbbAll.getZbt7() + "',zbt8='";
		sqlString += sjbbAll.getZbt8() + "',zbt9='";
		sqlString += sjbbAll.getZbt9() + "',zbt10='";
		sqlString += sjbbAll.getZbt10() + "',zbt11='";
		sqlString += sjbbAll.getZbt11() + "',zbt12='";
		sqlString += sjbbAll.getZbt12() + "',zbt13='";
		sqlString += sjbbAll.getZbt13() + "',zbt14='";
		sqlString += sjbbAll.getZbt14() + "',zbt15='";
		sqlString += sjbbAll.getZbt15() + "',zbt16='";
		sqlString += sjbbAll.getZbt16() + "',zbt17='";
		sqlString += sjbbAll.getZbt17() + "',zbt18='";
		sqlString += sjbbAll.getZbt18() + "',zbt19='";
		sqlString += sjbbAll.getZbt19() + "',zbt20='";
		sqlString += sjbbAll.getZbt20() + "',zbt21='";
		sqlString += sjbbAll.getZbt21() + "',zbt22='";
		sqlString += sjbbAll.getZbt22() + "',zbt23='";
		sqlString += sjbbAll.getZbt23() + "',zbt24='";
		sqlString += sjbbAll.getZbt24()  + "',zbt25='";
		sqlString += sjbbAll.getZbt25() + "',zbf1='";
		sqlString += sjbbAll.getZbf1() + "',zbf2='";
		sqlString += sjbbAll.getZbf2() + "',zbf3='";
		sqlString += sjbbAll.getZbf3() + "',zbf4='";
		sqlString += sjbbAll.getZbf4() + "',zbf5='";
		sqlString += sjbbAll.getZbf5() + "',zbf6='";
		sqlString += sjbbAll.getZbf6() + "',zbf7='";
		sqlString += sjbbAll.getZbf7() + "',zbf8='";
		sqlString += sjbbAll.getZbf8() + "',zbf9='";
		sqlString += sjbbAll.getZbf9() + "',zbf10='";
		sqlString += sjbbAll.getZbf10() + "',zbf11='";
		sqlString += sjbbAll.getZbf11() + "',zbf12='";
		sqlString += sjbbAll.getZbf12() + "',zbf13='";
		sqlString += sjbbAll.getZbf13() + "',zbf14='";
		sqlString += sjbbAll.getZbf14() + "',zbf15='";
		sqlString += sjbbAll.getZbf15() + "',zbf16='";
		sqlString += sjbbAll.getZbf16() + "',zbf17='";
		sqlString += sjbbAll.getZbf17() + "',zbf18='";
		sqlString += sjbbAll.getZbf18() + "',zbf19='";
		sqlString += sjbbAll.getZbf19() + "',zbf20='";
		sqlString += sjbbAll.getZbf20() + "',zbf21='";
		sqlString += sjbbAll.getZbf21() + "',zbf22='";
		sqlString += sjbbAll.getZbf22() + "',zbf23='";
		sqlString += sjbbAll.getZbf23() + "',zbf24='";
		sqlString += sjbbAll.getZbf24() + "',zbf25='";
		sqlString += sjbbAll.getZbf25() + "',zbf26='";
		sqlString += sjbbAll.getZbf26() + "',zbf27='";
		sqlString += sjbbAll.getZbf27() + "',zbf28='";
		sqlString += sjbbAll.getZbf28() + "',zbf29='";
		sqlString += sjbbAll.getZbf29() + "',zbf30='";
		sqlString += sjbbAll.getZbf30() + "',zbf31='";
		sqlString += sjbbAll.getZbf31() + "',zbf32='";
		sqlString += sjbbAll.getZbf32() + "',zbf33='";
		sqlString += sjbbAll.getZbf33() + "',zbv1='";
		sqlString += sjbbAll.getZbv1() + "',zbv2='";
		sqlString += sjbbAll.getZbv2() + "',zbv3='";
		sqlString += sjbbAll.getZbv3() + "',zbv4='";
		sqlString += sjbbAll.getZbv4() + "',zbv5='";
		sqlString += sjbbAll.getZbv5() + "',zbv6='";
		sqlString += sjbbAll.getZbv6() + "',zbv7='";
		sqlString += sjbbAll.getZbv7() + "',zbv8='";
		sqlString += sjbbAll.getZbv8() + "',zbv9='";
		sqlString += sjbbAll.getZbv9() + "',zbv10='";
		sqlString += sjbbAll.getZbv10() + "',zbv11='";
		sqlString += sjbbAll.getZbv11() + "',zbv12='";
		sqlString += sjbbAll.getZbv12() + "',zbv13='";
		sqlString += sjbbAll.getZbv13() + "',zbv14='";
		sqlString += sjbbAll.getZbv14() + "',zbv15='";
		sqlString += sjbbAll.getZbv15() + "',zbv16='";
		sqlString += sjbbAll.getZbv16() + "',zbv17='";
		sqlString += sjbbAll.getZbv17() + "',zbv18='";
		sqlString += sjbbAll.getZbv18() + "',zbv19='";
		sqlString += sjbbAll.getZbv19() + "',zbv20='";
		sqlString += sjbbAll.getZbv20() + "' where id=" + sjbbAll.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}

	/*根据记录编号删除审计情况统计表记录*/
	public int deleteSjbbAllById(int id) {
		int result = 0;
		String sqlString = "delete from sjbb_all where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*
	 *根据条件
	 *查询交通运输部门审计对象统计表记录
	 */
	public  ArrayList<SjbbJtyssjdytj> querySjbbJtyssjdytjListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbJtyssjdytj> SjbbJtyssjdytjList = new ArrayList<SjbbJtyssjdytj>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from sjbb_jtyssjdytj  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from sjbb_jtyssjdytj "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
				sjbbJtyssjdytj.setId(rs.getInt("id"));
				sjbbJtyssjdytj.setCzr(rs.getString("czr"));
				sjbbJtyssjdytj.setCzrdw(rs.getString("czrdw"));
				sjbbJtyssjdytj.setCzrID(rs.getString("czrID"));
				sjbbJtyssjdytj.setCzsj(rs.getTimestamp("czsj"));
				sjbbJtyssjdytj.setCzrphone(rs.getString("czrphone"));
				sjbbJtyssjdytj.setTjzt(rs.getString("tjzt"));
				SjbbJtyssjdytjList.add(sjbbJtyssjdytj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return SjbbJtyssjdytjList;
	}
	/*
	 *根据条件
	 *查询交通运输部门审计对象统计表记录数
	 */
	public  int querySjbbJtyssjdytjListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from sjbb_jtyssjdytj";
		}else{
			sqlString = "select count(*) from sjbb_jtyssjdytj "+srbt;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		int count=0;
		try {
			count = db.getRecordCount(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return count;
	}
	/*
	 *根据ID
	 *查询
	 */
	public  SjbbJtyssjdytj querySjbbJtyssjdytjByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		SjbbJtyssjdytj sjbbJtyssjdytj = new SjbbJtyssjdytj();
		String sqlString = "select * from sjbb_jtyssjdytj where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				sjbbJtyssjdytj.setId(rs.getInt("id"));
				sjbbJtyssjdytj.setCzr(rs.getString("czr"));
				sjbbJtyssjdytj.setCzrdw(rs.getString("czrdw"));
				sjbbJtyssjdytj.setCzrID(rs.getString("czrID"));
				sjbbJtyssjdytj.setCzsj(rs.getTimestamp("czsj"));
				sjbbJtyssjdytj.setCzrphone(rs.getString("czrphone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbJtyssjdytj;
	}
	
	/*
	 *根据条件
	 *查询交通运输部门审计对象统计表数据
	 */
	public  ArrayList<SjbbJtyssjdytjZxm> querySjbbJtyssjdytjZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList = new ArrayList<SjbbJtyssjdytjZxm>();
		String sqlString = "select * from sjbb_jtyssjdytj_zxm  where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = new SjbbJtyssjdytjZxm();
				sjbbJtyssjdytjZxm.setId(rs.getInt("id"));
				sjbbJtyssjdytjZxm.setXmid(rs.getInt("xmid"));
				sjbbJtyssjdytjZxm.setLb(rs.getString("lb"));
				sjbbJtyssjdytjZxm.setMc(rs.getString("mc"));
				sjbbJtyssjdytjZxm.setDwxz(rs.getString("dwxz"));
				sjbbJtyssjdytjZxm.setSjnr(rs.getString("sjnr"));
				sjbbJtyssjdytjZxm.setSjfs(rs.getString("sjfs"));
				sjbbJtyssjdytjZxm.setXzfs(rs.getString("xzfs"));
				sjbbJtyssjdytjZxm.setSjsx(rs.getString("sjsx"));
				sjbbJtyssjdytjZxm.setSsdw(rs.getString("ssdw"));
				sjbbJtyssjdytjZxm.setFl(rs.getString("fl"));
				SjbbJtyssjdytjZxmList.add(sjbbJtyssjdytjZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return SjbbJtyssjdytjZxmList;
	}
	
	/*
	 *
	 *新增交通运输部门审计对象统计表记录
	 */
	public int insertSjbbJtyssjdytj(SjbbJtyssjdytj SjbbJtyssjdytj) {
		String sqlString = "insert into sjbb_jtyssjdytj (czrphone,czr,czrdw,czrID,czsj,tjzt) values('";
		sqlString += SjbbJtyssjdytj.getCzrphone() + "','";
		sqlString += SjbbJtyssjdytj.getCzr() + "','";
		sqlString += SjbbJtyssjdytj.getCzrdw() + "','";
		sqlString += SjbbJtyssjdytj.getCzrID() + "','";
		sqlString += SjbbJtyssjdytj.getCzsj() + "','";
		sqlString += SjbbJtyssjdytj.getTjzt() + "')";
		int ret_id=0;
		try {
			Connection conn = ConnectionPoolUtils.GetPoolInstance().getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlString,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				ret_id = rs.getInt(1);
				}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret_id;
	}
	/*
	 *
	 *新增交通运输部门审计对象统计表数据记录
	 */
	public boolean insertSjbbJtyssjdytjZxm(ArrayList<SjbbJtyssjdytjZxm> SjbbJtyssjdytjZxmList) {
		int len = SjbbJtyssjdytjZxmList.size()-1;
		String sqlString = "insert into sjbb_jtyssjdytj_zxm (xmid,lb,mc,dwxz,sjnr,sjfs,xzfs,sjsx,ssdw,fl) values(";
		for(int i=0; i<len; i++){
			sqlString += SjbbJtyssjdytjZxmList.get(i).getXmid() + ",'";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getLb() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getMc() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getDwxz() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getSjnr() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getSjfs() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getXzfs() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getSjsx() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getSsdw() + "','";
			sqlString += SjbbJtyssjdytjZxmList.get(i).getFl() + "'),(";
		}
		sqlString += SjbbJtyssjdytjZxmList.get(len).getXmid() + ",'";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getLb() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getMc() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getDwxz() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getSjnr() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getSjfs() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getXzfs() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getSjsx() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getSsdw() + "','";
		sqlString += SjbbJtyssjdytjZxmList.get(len).getFl() + "')";
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	
	/*
	 *根据ID
	 *修改交通运输部门审计对象统计表记录
	 */
	public boolean updateSjbbJtyssjdytj(SjbbJtyssjdytj SjbbJtyssjdytj) {
		String sqlString = "update sjbb_jtyssjdytj set czrphone='";
		sqlString += SjbbJtyssjdytj.getCzrphone() + "',czr='";
		sqlString += SjbbJtyssjdytj.getCzr() + "',czrdw='";
		sqlString += SjbbJtyssjdytj.getCzrdw() + "',czrID='";
		sqlString += SjbbJtyssjdytj.getCzrID() + "',czsj='";
		sqlString += SjbbJtyssjdytj.getCzsj() + "',tjzt='";
		sqlString += SjbbJtyssjdytj.getTjzt() + "' where id=" + SjbbJtyssjdytj.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}

	/*根据记录编号删除交通运输部门审计对象统计表记录*/
	public int deleteSjbbJtyssjdytjById(int id) {
		int result = 0;
		String sqlString = "delete from sjbb_jtyssjdytj where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除交通运输部门审计对象统计表数据*/
	public int deleteSjbbJtyssjdytjZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from sjbb_jtyssjdytj_zxm where xmid ="+xmid;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 *根据条件
	 *查询落实收费政策季度自查表记录
	 */
	public  ArrayList<SjbbLssfzc> querySjbbLssfzcListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbLssfzc> sjbbLssfzcList = new ArrayList<SjbbLssfzc>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from sjbb_lssfzc  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from sjbb_lssfzc "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
				sjbbLssfzc.setId(rs.getInt("id"));
				sjbbLssfzc.setYear(rs.getInt("year"));
				sjbbLssfzc.setJd(rs.getInt("jd"));
				sjbbLssfzc.setSbsj(rs.getDate("sbsj"));
				sjbbLssfzc.setCzr(rs.getString("czr"));
				sjbbLssfzc.setCzrdw(rs.getString("czrdw"));
				sjbbLssfzc.setCzrID(rs.getString("czrID"));
				sjbbLssfzc.setCzsj(rs.getTimestamp("czsj"));
				sjbbLssfzc.setCzrphone(rs.getString("czrphone"));
				sjbbLssfzc.setTjzt(rs.getString("tjzt"));
				sjbbLssfzcList.add(sjbbLssfzc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzcList;
	}
	/*
	 *根据条件
	 *查询落实收费政策季度自查表记录数
	 */
	public  int querySjbbLssfzcListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from sjbb_lssfzc";
		}else{
			sqlString = "select count(*) from sjbb_lssfzc "+srbt;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		int count=0;
		try {
			count = db.getRecordCount(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return count;
	}
	/*
	 *根据ID
	 *查询落实收费政策季度自查表
	 */
	public  SjbbLssfzc querySjbbLssfzcByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		SjbbLssfzc sjbbLssfzc = new SjbbLssfzc();
		String sqlString = "select * from sjbb_lssfzc where id = "+id ;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				sjbbLssfzc.setId(rs.getInt("id"));
				sjbbLssfzc.setYear(rs.getInt("year"));
				sjbbLssfzc.setJd(rs.getInt("jd"));
				sjbbLssfzc.setSbsj(rs.getDate("sbsj"));
				sjbbLssfzc.setCzr(rs.getString("czr"));
				sjbbLssfzc.setCzrdw(rs.getString("czrdw"));
				sjbbLssfzc.setCzrID(rs.getString("czrID"));
				sjbbLssfzc.setCzsj(rs.getTimestamp("czsj"));
				sjbbLssfzc.setCzrphone(rs.getString("czrphone"));
				sjbbLssfzc.setTjzt(rs.getString("tjzt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzc;
	}
	
	/*
	 *根据ID
	 *查询落实收费政策季度自查表子表内容
	 */
	public  ArrayList<SjbbLssfzcZxm> querySjbbLssfzcZxmByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
		String sqlString = "select * from sjbb_lssfzc_zxm where xmid = "+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbLssfzcZxm sjbbLssfzcZxm = new SjbbLssfzcZxm();
				sjbbLssfzcZxm.setId(rs.getInt("id"));
				sjbbLssfzcZxm.setXmid(rs.getInt("xmid"));
				sjbbLssfzcZxm.setZb1(rs.getString("zb1"));
				sjbbLssfzcZxm.setZb2(rs.getString("zb2"));
				sjbbLssfzcZxm.setZb3(rs.getString("zb3"));
				sjbbLssfzcZxm.setZb4(rs.getString("zb4"));
				sjbbLssfzcZxm.setZb5(rs.getString("zb5"));
				sjbbLssfzcZxm.setZb6(rs.getString("zb6"));
				sjbbLssfzcZxm.setZb7(rs.getString("zb7"));
				sjbbLssfzcZxm.setZb8(rs.getString("zb8"));
				sjbbLssfzcZxm.setZb9(rs.getString("zb9"));
				sjbbLssfzcZxm.setZb10(rs.getString("zb10"));
				sjbbLssfzcZxm.setZb11(rs.getString("zb11"));
				sjbbLssfzcZxm.setZb12(rs.getString("zb12"));
				sjbbLssfzcZxm.setZb13(rs.getString("zb13"));
				sjbbLssfzcZxm.setZb14(rs.getString("zb14"));
				sjbbLssfzcZxmList.add(sjbbLssfzcZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzcZxmList;
	}
	
	/*
	 *
	 *新增落实收费政策季度自查表记录
	 */
	public int insertSjbbLssfzc(SjbbLssfzc sjbbLssfzc) {
		String sqlString = "insert into sjbb_lssfzc (year,jd,sbsj,czr,czrdw,czrID,czsj,czrphone,tjzt) values(";
		sqlString += sjbbLssfzc.getYear() + ",";
		sqlString += sjbbLssfzc.getJd() + ",'";
		sqlString += sjbbLssfzc.getSbsj() + "','";
		sqlString += sjbbLssfzc.getCzr() + "','";
		sqlString += sjbbLssfzc.getCzrdw() + "','";
		sqlString += sjbbLssfzc.getCzrID()+ "','";
		sqlString += sjbbLssfzc.getCzsj()  + "','";
		sqlString += sjbbLssfzc.getCzrphone()  + "','";
		sqlString += sjbbLssfzc.getTjzt() + "')";
		int ret_id=0;
		try {
			Connection conn = ConnectionPoolUtils.GetPoolInstance().getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlString,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				ret_id = rs.getInt(1);
				}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret_id;
	}
			
	/*
	 *根据ID
	 *修改落实收费政策季度自查表记录
	 */
	public boolean updateSjbbLssfzc(SjbbLssfzc sjbbLssfzc) {
		String sqlString = "update sjbb_lssfzc set sbsj='"; 
		sqlString += sjbbLssfzc.getSbsj() + "',year=";
		sqlString += sjbbLssfzc.getYear() + ",jd=";
		sqlString += sjbbLssfzc.getJd() + ",czr='";
		sqlString += sjbbLssfzc.getCzr() + "',czrdw='";
		sqlString += sjbbLssfzc.getCzrdw() + "',czrID='";
		sqlString += sjbbLssfzc.getCzrID() + "',czsj='";
		sqlString += sjbbLssfzc.getCzsj() + "',czrdw='";
		sqlString += sjbbLssfzc.getCzrdw() + "',czrphone='";
		sqlString += sjbbLssfzc.getCzrphone() + "',tjzt='";
		sqlString += sjbbLssfzc.getTjzt()+ "' where id=" + sjbbLssfzc.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *修改落实收费政策季度自查表记录
	 */
	public boolean updateSjbbLssfzcRet(String id) {
		String sqlString = "update sjbb_lssfzc set tjzt='3' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*根据记录编号删除落实收费政策季度自查表记录*/
	public int deleteSjbbLssfzcById(int id) {
		int result = 0;
		String sqlString = "delete from sjbb_lssfzc where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 *
	 *新增落实收费政策季度自查表子表
	 */
	public boolean insertSjbbLssfzcZxm(SjbbLssfzcZxm sjbbLssfzcZxm) {
		String sqlString = "insert into sjbb_lssfzc_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6,zb7,zb8,zb9,zb10,zb11,zb12,zb13,zb14) values(";
		sqlString += sjbbLssfzcZxm.getXmid() + ",'";
		sqlString += sjbbLssfzcZxm.getZb1()  + "','";
		sqlString += sjbbLssfzcZxm.getZb2()  + "','";
		sqlString += sjbbLssfzcZxm.getZb3()  + "','";
		sqlString += sjbbLssfzcZxm.getZb4()  + "','";
		sqlString += sjbbLssfzcZxm.getZb5()  + "','";
		sqlString += sjbbLssfzcZxm.getZb6()  + "','";
		sqlString += sjbbLssfzcZxm.getZb7()  + "','";
		sqlString += sjbbLssfzcZxm.getZb8()  + "','";
		sqlString += sjbbLssfzcZxm.getZb9()  + "','";
		sqlString += sjbbLssfzcZxm.getZb10()  + "','";
		sqlString += sjbbLssfzcZxm.getZb11()  + "','";
		sqlString += sjbbLssfzcZxm.getZb12()  + "','";
		sqlString += sjbbLssfzcZxm.getZb13()  + "','";
		sqlString += sjbbLssfzcZxm.getZb14()  + "')";
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*根据记录编号删除落实收费政策季度自查表子表*/
	public int deleteSjbbLssfzcZxmByID(int id) {
		int result = 0;
		String sqlString = "delete from sjbb_lssfzc_zxm where xmid ="+id+"";
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 *根据条件
	 *查询落实收费政策季度汇总表记录
	 */
	public  ArrayList<SjbbLssfzcHzb> querySjbbLssfzcHzbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbLssfzcHzb> sjbbLssfzcList = new ArrayList<SjbbLssfzcHzb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from sjbb_lssfzc_hzb  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from sjbb_lssfzc_hzb "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbLssfzcHzb sjbbLssfzc = new SjbbLssfzcHzb();
				sjbbLssfzc.setId(rs.getInt("id"));
				sjbbLssfzc.setYear(rs.getInt("year"));
				sjbbLssfzc.setJd(rs.getInt("jd"));
				sjbbLssfzc.setCzr(rs.getString("czr"));
				sjbbLssfzc.setCzrdw(rs.getString("czrdw"));
				sjbbLssfzc.setCzrID(rs.getString("czrID"));
				sjbbLssfzc.setCzsj(rs.getTimestamp("czsj"));
				sjbbLssfzc.setCzrphone(rs.getString("czrphone"));
				sjbbLssfzcList.add(sjbbLssfzc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzcList;
	}
	/*
	 *根据条件
	 *查询落实收费政策季度汇总表记录数
	 */
	public  int querySjbbLssfzcHzbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from sjbb_lssfzc_hzb";
		}else{
			sqlString = "select count(*) from sjbb_lssfzc_hzb "+srbt;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		int count=0;
		try {
			count = db.getRecordCount(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return count;
	}
	/*
	 *根据ID
	 *查询落实收费政策季度汇总表
	 */
	public  SjbbLssfzcHzb querySjbbLssfzcHzbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		SjbbLssfzcHzb sjbbLssfzcHzb = new SjbbLssfzcHzb();
		String sqlString = "select * from sjbb_lssfzc_hzb where id = "+id ;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				sjbbLssfzcHzb.setId(rs.getInt("id"));
				sjbbLssfzcHzb.setYear(rs.getInt("year"));
				sjbbLssfzcHzb.setJd(rs.getInt("jd"));
				sjbbLssfzcHzb.setCzr(rs.getString("czr"));
				sjbbLssfzcHzb.setCzrdw(rs.getString("czrdw"));
				sjbbLssfzcHzb.setCzrID(rs.getString("czrID"));
				sjbbLssfzcHzb.setCzsj(rs.getTimestamp("czsj"));
				sjbbLssfzcHzb.setCzrphone(rs.getString("czrphone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzcHzb;
	}
	
	/*
	 *根据ID
	 *查询落实收费政策季度汇总表子表内容
	 */
	public  ArrayList<SjbbLssfzcZxmHzb> querySjbbLssfzcZxmHzbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbLssfzcZxmHzb> sjbbLssfzcZxmHzbList = new ArrayList<SjbbLssfzcZxmHzb>();
		String sqlString = "select * from sjbb_lssfzc_zxm_hzb where xmid = "+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbLssfzcZxmHzb sjbbLssfzcZxmHzb = new SjbbLssfzcZxmHzb();
				sjbbLssfzcZxmHzb.setId(rs.getInt("id"));
				sjbbLssfzcZxmHzb.setXmid(rs.getInt("xmid"));
				sjbbLssfzcZxmHzb.setZb1(rs.getString("zb1"));
				sjbbLssfzcZxmHzb.setZb2(rs.getString("zb2"));
				sjbbLssfzcZxmHzb.setZb3(rs.getString("zb3"));
				sjbbLssfzcZxmHzb.setZb4(rs.getString("zb4"));
				sjbbLssfzcZxmHzb.setZb5(rs.getString("zb5"));
				sjbbLssfzcZxmHzb.setZb6(rs.getString("zb6"));
				sjbbLssfzcZxmHzb.setZb7(rs.getString("zb7"));
				sjbbLssfzcZxmHzb.setZb8(rs.getString("zb8"));
				sjbbLssfzcZxmHzb.setZb9(rs.getString("zb9"));
				sjbbLssfzcZxmHzb.setZb10(rs.getString("zb10"));
				sjbbLssfzcZxmHzb.setZb11(rs.getString("zb11"));
				sjbbLssfzcZxmHzb.setZb12(rs.getString("zb12"));
				sjbbLssfzcZxmHzb.setZb13(rs.getString("zb13"));
				sjbbLssfzcZxmHzb.setZb14(rs.getString("zb14"));
				sjbbLssfzcZxmHzbList.add(sjbbLssfzcZxmHzb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzcZxmHzbList;
	}
	/*
	 *根据年份季度
	 *查询落实收费政策季度自查表记录ID
	 */
	public  String querySjbbLssfzcBySj(String year ,String jd) {
		 /* 保存符合条件的某页记录的集合链表 */
		String IDStr = "";
		String sqlString = "select id from sjbb_lssfzc where year = "+year +" and jd = "+jd+" and tjzt='1' order by id desc";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				if("".equals(IDStr)){
					IDStr = rs.getInt("id")+"";
				}else{
					IDStr = IDStr+","+rs.getInt("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return IDStr;
	}

	/*
	 *根据年份
	 *查询落实收费政策季度自查表记录ID
	 */
	public  String querySjbbLssfzcByYear(String year) {
		 /* 保存符合条件的某页记录的集合链表 */
		String IDStr = "";
		String sqlString = "select id from sjbb_lssfzc where year = "+year +" and tjzt='1' order by id desc";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				if("".equals(IDStr)){
					IDStr = rs.getInt("id")+"";
				}else{
					IDStr = IDStr+","+rs.getInt("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return IDStr;
	}
	/*
	 *根据ID
	 *查询落实收费政策季度汇总表子表内容
	 */
	public  ArrayList<SjbbLssfzcZxm> querySjbbLssfzcZxmByXmidList(String idList) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SjbbLssfzcZxm> sjbbLssfzcZxmList = new ArrayList<SjbbLssfzcZxm>();
		String sqlString = "select * from sjbb_lssfzc_zxm where xmid in ("+idList+")";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SjbbLssfzcZxm sjbbLssfzcZxm = new SjbbLssfzcZxm();
				sjbbLssfzcZxm.setId(rs.getInt("id"));
				sjbbLssfzcZxm.setXmid(rs.getInt("xmid"));
				sjbbLssfzcZxm.setZb1(rs.getString("zb1"));
				sjbbLssfzcZxm.setZb2(rs.getString("zb2"));
				sjbbLssfzcZxm.setZb3(rs.getString("zb3"));
				sjbbLssfzcZxm.setZb4(rs.getString("zb4"));
				sjbbLssfzcZxm.setZb5(rs.getString("zb5"));
				sjbbLssfzcZxm.setZb6(rs.getString("zb6"));
				sjbbLssfzcZxm.setZb7(rs.getString("zb7"));
				sjbbLssfzcZxm.setZb8(rs.getString("zb8"));
				sjbbLssfzcZxm.setZb9(rs.getString("zb9"));
				sjbbLssfzcZxm.setZb10(rs.getString("zb10"));
				sjbbLssfzcZxm.setZb11(rs.getString("zb11"));
				sjbbLssfzcZxm.setZb12(rs.getString("zb12"));
				sjbbLssfzcZxm.setZb13(rs.getString("zb13"));
				sjbbLssfzcZxm.setZb14(rs.getString("zb14"));
				sjbbLssfzcZxmList.add(sjbbLssfzcZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sjbbLssfzcZxmList;
	}

	/*
	 *
	 *新增落实收费政策季度汇总表记录
	 */
	public int insertSjbbLssfzcHzb(SjbbLssfzcHzb sjbbLssfzcHzb) {
		String sqlString = "insert into sjbb_lssfzc_hzb (year,jd,czr,czrdw,czrID,czsj,czrphone) values(";
		sqlString += sjbbLssfzcHzb.getYear() + ",";
		sqlString += sjbbLssfzcHzb.getJd() + ",'";
		sqlString += sjbbLssfzcHzb.getCzr() + "','";
		sqlString += sjbbLssfzcHzb.getCzrdw() + "','";
		sqlString += sjbbLssfzcHzb.getCzrID()+ "','";
		sqlString += sjbbLssfzcHzb.getCzsj()  + "','";
		sqlString += sjbbLssfzcHzb.getCzrphone()  + "')";
		int ret_id=0;
		try {
			Connection conn = ConnectionPoolUtils.GetPoolInstance().getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlString,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				ret_id = rs.getInt(1);
				}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret_id;
	}
			
	/*
	 *根据ID
	 *修改落实收费政策季度汇总表记录
	 */
	public boolean updateSjbbLssfzcHzb(SjbbLssfzcHzb sjbbLssfzcHzb) {
		String sqlString = "update sjbb_lssfzc_hzb set year=";
		sqlString += sjbbLssfzcHzb.getYear() + ",jd=";
		sqlString += sjbbLssfzcHzb.getJd() + ",czr='";
		sqlString += sjbbLssfzcHzb.getCzr() + "',czrdw='";
		sqlString += sjbbLssfzcHzb.getCzrdw() + "',czrID='";
		sqlString += sjbbLssfzcHzb.getCzrID() + "',czsj='";
		sqlString += sjbbLssfzcHzb.getCzsj() + "',czrdw='";
		sqlString += sjbbLssfzcHzb.getCzrdw() + "',czrphone='";
		sqlString += sjbbLssfzcHzb.getCzrphone() + "' where id=" + sjbbLssfzcHzb.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}

	/*根据记录编号删除落实收费政策季度汇总表记录*/
	public int deleteSjbbLssfzcHzbById(int id) {
		int result = 0;
		String sqlString = "delete from sjbb_lssfzc_hzb where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 *
	 *新增落实收费政策季度汇总表子表
	 */
	public boolean insertSjbbLssfzcZxmHzb(int id,ArrayList<SjbbLssfzcZxm>sjbbLssfzcZxmList) {
		int len = sjbbLssfzcZxmList.size()-1;
		String sqlString = "insert into sjbb_lssfzc_zxm_hzb (xmid,zb1,zb2,zb3,zb4,zb5,zb6,zb7,zb8,zb9,zb10,zb11,zb12,zb13,zb14) values(";
		for(int i=0; i<len; i++){
			sqlString += id + ",'";
			sqlString += sjbbLssfzcZxmList.get(i).getZb1() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb2() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb3() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb4() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb5() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb6() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb7() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb8() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb9() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb10() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb11() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb12() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb13() + "','";
			sqlString += sjbbLssfzcZxmList.get(i).getZb14() + "'),(";
		}
		sqlString += id + ",'";
		sqlString += sjbbLssfzcZxmList.get(len).getZb1() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb2() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb3() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb4() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb5() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb6() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb7() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb8() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb9() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb10() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb11() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb12() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb13() + "','";
		sqlString += sjbbLssfzcZxmList.get(len).getZb14() + "')";
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*根据记录编号删除落实收费政策季度汇总表子表*/
	public int deleteSjbbLssfzcZxmHzbByID(int id) {
		int result = 0;
		String sqlString = "delete from sjbb_lssfzc_zxm_hzb where xmid ="+id+"";
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
