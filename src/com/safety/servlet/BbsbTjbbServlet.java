package com.safety.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

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
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import com.safety.dao.BbdzDao;
import com.safety.dao.BbsbTjbbDao;
import com.safety.dao.ContentZzxxDao;
import com.safety.dao.MenuDao;
import com.safety.dao.MessageDao;
import com.safety.dao.MessagePlatForm;
import com.safety.entity.Bbdzmb;
import com.safety.entity.ContentZzxx;
import com.safety.entity.MyMessage;
import com.safety.entity.PaginationTool;
import com.safety.entity.Permissions;
import com.safety.entity.TjbbYxzbHd;
import com.safety.entity.TjbbYxzbHdZxm;
import com.safety.entity.TjbbYxzbHs;
import com.safety.entity.TjbbYxzbHsZd;
import com.safety.entity.TjbbYxzbHsZdZxm;
import com.safety.entity.TjbbYxzbHsZdhz;
import com.safety.entity.TjbbYxzbHsZdhzZxm;
import com.safety.entity.TjbbYxzbHsZxm;
import com.safety.entity.TjbbYxzbQy;
import com.safety.entity.TjbbYxzbQyZxm;
import com.safety.entity.TjbbYxzbSix;
import com.safety.entity.TjbbYxzbSixZxm;

public class BbsbTjbbServlet  extends HttpServlet{
	/**
	 * 短信平台
	 */
	private static MessagePlatForm messagePlatForm= new MessagePlatForm();
	/**
	 * 请求Servlet方法
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
		if("getTjbb".equals(action)){//统计报表
			getTjbb(request,response);
		}else if("getTjbbTX".equals(action)){//可视化图形
			getTjbbTX(request,response);
		}else if("resetMenu".equals(action)){//重置菜单
			resetMenu(request,response);
		}else if("getTjbbYxzbHd".equals(action)){//主要运行指标统计表航道——列表1
			getTjbbYxzbHd(request,response);
		}else if("TjbbYxzbHdEdit".equals(action)){//主要运行指标统计表航道——新增或修改
			TjbbYxzbHdEdit(request,response);
		}else if("TjbbYxzbHdSave".equals(action)){//主要运行指标统计表航道——保存页面
			TjbbYxzbHdSave(request,response);
		}else if("TjbbYxzbHdShow".equals(action)){//主要运行指标统计表航道——查看
			TjbbYxzbHdShow(request,response);
		}else if("TjbbYxzbHdDelete".equals(action)){//主要运行指标统计表航道——删除
			TjbbYxzbHdDelete(request,response);
		}else if("getTjbbYxzbHdExcel".equals(action)){//主要运行指标统计表航道——导出EXCEL
			getTjbbYxzbHdExcel(request,response);
		}else if("readTjbbYxzbHdExcel".equals(action)){//主要运行指标统计表航道——解析EXCEL
			readTjbbYxzbHdExcel(request,response);
		}else if("TjbbYxzbHdReturn".equals(action)){//主要运行指标统计表航道——驳回
			TjbbYxzbHdReturn(request,response);
		}else if("getTjbbQxtHd".equals(action)){//可视化图形——曲线图——航道
			getTjbbQxtHd(request,response);
		}else if("getTjbbZztHd".equals(action)){//可视化图形——柱状图——航道
			getTjbbZztHd(request,response);
		}else if("getTjbbBztHd".equals(action)){//可视化图形——饼状图——航道
			getTjbbBztHd(request,response);
		}else if("getTjbbYxzbSix".equals(action)){//主要运行指标统计表六县——列表1
			getTjbbYxzbSix(request,response);
		}else if("TjbbYxzbSixEdit".equals(action)){//主要运行指标统计表六县——新增或修改
			TjbbYxzbSixEdit(request,response);
		}else if("TjbbYxzbSixSave".equals(action)){//主要运行指标统计表六县——保存页面
			TjbbYxzbSixSave(request,response);
		}else if("TjbbYxzbSixShow".equals(action)){//主要运行指标统计表六县——查看
			TjbbYxzbSixShow(request,response);
		}else if("TjbbYxzbSixDelete".equals(action)){//主要运行指标统计表六县——删除
			TjbbYxzbSixDelete(request,response);
		}else if("getTjbbYxzbSixExcel".equals(action)){//主要运行指标统计表六县——导出EXCEL
			getTjbbYxzbSixExcel(request,response);
		}else if("readTjbbYxzbSixExcel".equals(action)){//主要运行指标统计表六县——解析EXCEL
			readTjbbYxzbSixExcel(request,response);
		}else if("TjbbYxzbSixReturn".equals(action)){//主要运行指标统计表六县——驳回
			TjbbYxzbSixReturn(request,response);
		}else if("getTjbbQxtSix".equals(action)){//可视化图形——曲线图——六县
			getTjbbQxtSix(request,response);
		}else if("getTjbbZztSix".equals(action)){//可视化图形——柱状图——六县
			getTjbbZztSix(request,response);
		}else if("getTjbbBztSix".equals(action)){//可视化图形——饼状图——六县
			getTjbbBztSix(request,response);
		}else if("getTjbbYxzbQy".equals(action)){//客运出站运输量统计表（汽运集团）——列表1
			getTjbbYxzbQy(request,response);
		}else if("TjbbYxzbQyEdit".equals(action)){//客运出站运输量统计表（汽运集团）——新增或修改
			TjbbYxzbQyEdit(request,response);
		}else if("TjbbYxzbQySave".equals(action)){//客运出站运输量统计表（汽运集团）——保存页面
			TjbbYxzbQySave(request,response);
		}else if("TjbbYxzbQyShow".equals(action)){//客运出站运输量统计表（汽运集团）——查看
			TjbbYxzbQyShow(request,response);
		}else if("TjbbYxzbQyDelete".equals(action)){//客运出站运输量统计表（汽运集团）——删除
			TjbbYxzbQyDelete(request,response);
		}else if("getTjbbYxzbQyExcel".equals(action)){//客运出站运输量统计表（汽运集团）——导出EXCEL
			getTjbbYxzbQyExcel(request,response);
		}else if("readTjbbYxzbQyExcel".equals(action)){//客运出站运输量统计表（汽运集团）——解析EXCEL
			readTjbbYxzbQyExcel(request,response);
		}else if("TjbbYxzbQyReturn".equals(action)){//客运出站运输量统计表（汽运集团）——驳回
			TjbbYxzbQyReturn(request,response);
		}else if("getTjbbQxtQy".equals(action)){//可视化图形——曲线图——汽运集团
			getTjbbQxtQy(request,response);
		}else if("getTjbbZztQy".equals(action)){//可视化图形——柱状图——汽运集团
			getTjbbZztQy(request,response);
		}else if("getTjbbBztQy".equals(action)){//可视化图形——饼状图——汽运集团
			getTjbbBztQy(request,response);
		}else if("getTjbbYxzbHs".equals(action)){//海事签证月度同比表（海事局）——列表1
			getTjbbYxzbHs(request,response);
		}else if("TjbbYxzbHsEdit".equals(action)){//海事签证月度同比表（海事局）——新增或修改
			TjbbYxzbHsEdit(request,response);
		}else if("TjbbYxzbHsSave".equals(action)){//海事签证月度同比表（海事局）——保存页面
			TjbbYxzbHsSave(request,response);
		}else if("TjbbYxzbHsShow".equals(action)){//海事签证月度同比表（海事局）——查看
			TjbbYxzbHsShow(request,response);
		}else if("TjbbYxzbHsDelete".equals(action)){//海事签证月度同比表（海事局）——删除
			TjbbYxzbHsDelete(request,response);
		}else if("getTjbbYxzbHsExcel".equals(action)){//海事签证月度同比表（海事局）——导出EXCEL
			getTjbbYxzbHsExcel(request,response);
		}else if("readTjbbYxzbHsExcel".equals(action)){//海事签证月度同比表（海事局）——解析EXCEL
			readTjbbYxzbHsExcel(request,response);
		}else if("TjbbYxzbHsReturn".equals(action)){//海事签证月度同比表（海事局）——驳回
			TjbbYxzbHsReturn(request,response);
		}else if("getTjbbQxtHs".equals(action)){//可视化图形——曲线图——海事局
			getTjbbQxtHs(request,response);
		}else if("getTjbbZztHs".equals(action)){//可视化图形——柱状图——海事局
			getTjbbZztHs(request,response);
		}else if("getTjbbBztHs".equals(action)){//可视化图形——饼状图——海事局
			getTjbbBztHs(request,response);
		}else if("getTjbbYxzbHsZd".equals(action)){//海事签证（海事局站点）——列表1
			getTjbbYxzbHsZd(request,response);
		}else if("TjbbYxzbHsZdEdit".equals(action)){//海事签证（海事局站点）——新增或修改
			TjbbYxzbHsZdEdit(request,response);
		}else if("TjbbYxzbHsZdSave".equals(action)){//海事签证（海事局站点）——保存页面
			TjbbYxzbHsZdSave(request,response);
		}else if("TjbbYxzbHsZdSaveAll".equals(action)){//海事签证（海事局站点）——保存页面（多个站点）
			TjbbYxzbHsZdSaveAll(request,response);
		}else if("TjbbYxzbHsZdShow".equals(action)){//海事签证（海事局站点）——查看
			TjbbYxzbHsZdShow(request,response);
		}else if("TjbbYxzbHsZdDelete".equals(action)){//海事签证（海事局站点）——删除
			TjbbYxzbHsZdDelete(request,response);
		}else if("getTjbbYxzbHsZdExcel".equals(action)){//海事签证（海事局站点）——导出EXCEL
			getTjbbYxzbHsZdExcel(request,response);
		}else if("readTjbbYxzbHsZdExcel".equals(action)){//海事签证（海事局站点）——解析EXCEL
			readTjbbYxzbHsZdExcel(request,response);
		}else if("readTjbbYxzbHsZdExcelAll".equals(action)){//海事签证（海事局站点）——解析EXCEL（多个站点）
			readTjbbYxzbHsZdExcelAll(request,response);
		}else if("TjbbYxzbHsZdReturn".equals(action)){//海事签证（海事局站点）——驳回
			TjbbYxzbHsZdReturn(request,response);
		}else if("getTjbbQxtHsZd".equals(action)){//可视化图形——曲线图——海事局站点
			getTjbbQxtHsZd(request,response);
		}else if("getTjbbZztHsZd".equals(action)){//可视化图形——柱状图——海事局站点
			getTjbbZztHsZd(request,response);
		}else if("getTjbbBztHsZd".equals(action)){//可视化图形——饼状图——海事局站点
			getTjbbBztHsZd(request,response);
		}else if("getTjbbYxzbHsZdhz".equals(action)){//海事局站点汇总表——列表
			getTjbbYxzbHsZdhz(request,response);
		}else if("TjbbYxzbHsZdhzSave".equals(action)){//海事局站点汇总表——生成数据
			TjbbYxzbHsZdhzSave(request,response);
		}else if("TjbbYxzbHsZdhzShow".equals(action)){//海事局站点汇总表——查看
			TjbbYxzbHsZdhzShow(request,response);
		}else if("TjbbYxzbHsZdhzDelete".equals(action)){//海事局站点汇总表——删除
			TjbbYxzbHsZdhzDelete(request,response);
		}else if("getTjbbYxzbHsZdhzExcel".equals(action)){//海事局站点汇总表——导出EXCEL
			getTjbbYxzbHsZdhzExcel(request,response);
		}else if("getTjbbQxtHsZdhz".equals(action)){//可视化图形——曲线图——海事局站点汇总
			getTjbbQxtHsZdhz(request,response);
		}else if("getTjbbZztHsZdhz".equals(action)){//可视化图形——柱状图——海事局站点汇总
			getTjbbZztHsZdhz(request,response);
		}else if("getTjbbBztHsZdhz".equals(action)){//可视化图形——饼状图——海事局站点汇总
			getTjbbBztHsZdhz(request,response);
		}
	}
	/*
	 *  统计报表可视化图形
	 * 
	 */
	protected void getTjbbTX(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			request.getRequestDispatcher("/jsp/StatisticalReports/getTjbbTX.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——曲线图
	 * 
	 */
	protected void getTjbbQxtHd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList =xList+"'船舶过闸量（吨）',";
				}else if("2".equals(xAxis[i])){
					xList =xList+"'南通船闸',";
				}else if("3".equals(xAxis[i])){
					xList =xList+"'九圩港闸',";
				}else if("4".equals(xAxis[i])){
					xList =xList+"'焦港船闸',";
				}else if("5".equals(xAxis[i])){
					xList =xList+"'吕四船闸',";
				}else if("6".equals(xAxis[i])){
					xList =xList+"'海安船闸',";
				}else if("7".equals(xAxis[i])){
					xList =xList+"'内河电煤等重点物资过闸量（吨））',";
				}else if("8".equals(xAxis[i])){
					xList =xList+"'南通船闸',";
				}else if("9".equals(xAxis[i])){
					xList =xList+"'九圩港闸',";
				}else if("10".equals(xAxis[i])){
					xList =xList+"'焦港船闸',";
				}else if("11".equals(xAxis[i])){
					xList =xList+"'吕四船闸',";
				}else if("12".equals(xAxis[i])){
					xList =xList+"'海安船闸',";
				}else if("13".equals(xAxis[i])){
					xList =xList+"'内河钢结构船舶过闸量（吨）',";
				}else if("14".equals(xAxis[i])){
					xList =xList+"'九圩港闸',";
				}
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHdZxmList =  bbsbTjbbDao.queryTjbbYxzbHdZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("上月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("本月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("环比增减%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("本年累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年同期累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("上年同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbQxt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  可视化图形——柱状图
	 * 
	 */
	protected void getTjbbZztHd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList =xList+"'船舶过闸量（吨）',";
				}else if("2".equals(xAxis[i])){
					xList =xList+"'南通船闸',";
				}else if("3".equals(xAxis[i])){
					xList =xList+"'九圩港闸',";
				}else if("4".equals(xAxis[i])){
					xList =xList+"'焦港船闸',";
				}else if("5".equals(xAxis[i])){
					xList =xList+"'吕四船闸',";
				}else if("6".equals(xAxis[i])){
					xList =xList+"'海安船闸',";
				}else if("7".equals(xAxis[i])){
					xList =xList+"'内河电煤等重点物资过闸量（吨））',";
				}else if("8".equals(xAxis[i])){
					xList =xList+"'南通船闸',";
				}else if("9".equals(xAxis[i])){
					xList =xList+"'九圩港闸',";
				}else if("10".equals(xAxis[i])){
					xList =xList+"'焦港船闸',";
				}else if("11".equals(xAxis[i])){
					xList =xList+"'吕四船闸',";
				}else if("12".equals(xAxis[i])){
					xList =xList+"'海安船闸',";
				}else if("13".equals(xAxis[i])){
					xList =xList+"'内河钢结构船舶过闸量（吨）',";
				}else if("14".equals(xAxis[i])){
					xList =xList+"'九圩港闸',";
				}
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHdZxmList =  bbsbTjbbDao.queryTjbbYxzbHdZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("上月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("本月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("环比增减%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("本年累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年同期累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("上年同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbZzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——饼状图
	 * 
	 */
	protected void getTjbbBztHd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			ArrayList<String> xList = new ArrayList<String>();
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList.add("'船舶过闸量（吨）'");
				}else if("2".equals(xAxis[i])){
					xList.add("'南通船闸'");
				}else if("3".equals(xAxis[i])){
					xList.add("'九圩港闸'");
				}else if("4".equals(xAxis[i])){
					xList.add("'焦港船闸'");
				}else if("5".equals(xAxis[i])){
					xList.add("'吕四船闸'");
				}else if("6".equals(xAxis[i])){
					xList.add("'海安船闸'");
				}else if("7".equals(xAxis[i])){
					xList.add("'内河电煤等重点物资过闸量（吨））'");
				}else if("8".equals(xAxis[i])){
					xList.add("'南通船闸'");
				}else if("9".equals(xAxis[i])){
					xList.add("'九圩港闸'");
				}else if("10".equals(xAxis[i])){
					xList.add("'焦港船闸'");
				}else if("11".equals(xAxis[i])){
					xList.add("'吕四船闸'");
				}else if("12".equals(xAxis[i])){
					xList.add("'海安船闸'");
				}else if("13".equals(xAxis[i])){
					xList.add("'内河钢结构船舶过闸量（吨）'");
				}else if("14".equals(xAxis[i])){
					xList.add("'九圩港闸'");
				}
			}
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			String yname = "";
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHdZxmList =  bbsbTjbbDao.queryTjbbYxzbHdZxmByID(Integer.parseInt(id));
			}
			if(yAxis.length==1){
				if("1".equals(yAxis[0])){
					yname = "上月数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb1())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb1());
						}
					}
				}else if("2".equals(yAxis[0])){
					yname = "本月数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb2())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb2());
						}
					}
				}else if("3".equals(yAxis[0])){
					yname = "环比增减%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb3())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb3());
						}
					}
				}else if("4".equals(yAxis[0])){
					yname = "本年累计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb4())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb4());
						}
					}
				}else if("5".equals(yAxis[0])){
					yname = "上年同期累计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb5())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb5());
						}
					}
				}else if("6".equals(yAxis[0])){
					yname = "上年同比%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb6())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHdZxmList.get(xAxisInt.get(k)).getZb6());
						}
					}
				}
				
			}
			request.setAttribute("yname", yname);
			request.setAttribute("xList", xList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbBzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  统计报表
	 * 
	 */
	protected void getTjbb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		Permissions UserPer = (Permissions)session.getAttribute("UserPer");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else if(UserPer.getTjbb().indexOf("1")==-1){
			response.setContentType("text/html;charset=gb2312");
			request.setAttribute("result", "无查询权限");
			request.getRequestDispatcher("desk.jsp").forward(request,
					response);
			
		}else{
			MenuDao menuDao = new MenuDao();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			BbdzDao bbdzDao = new BbdzDao();
			String result = request.getParameter("result");
			String nameList = "";
			//局机关统计负责人查看是否上月报表未提交
			if(("信息员".equals(UserInfo.getRoles())||"统计负责人".equals(UserInfo.getRoles()))&&"局机关".equals(UserInfo.getCompany())){
				//查询当前时间
				Calendar cal=Calendar.getInstance();
				int nowYear = cal.get(Calendar.YEAR);
				int nowMonth = cal.get(Calendar.MONTH);
				//通过时间和单位查询上月是否提交
				//今年12月之前
				if(nowMonth!=0){
					String datebegin1 = nowYear+"-"+nowMonth+"-01";
					String dateend1 = nowYear+"-"+nowMonth+"-31";
					//六县+运管统计表
					String yydw = "";
					yydw = bbsbTjbbDao.queryTjbbYxzbSixByYMWH(datebegin1,dateend1);
					if(yydw.indexOf("海安县交通运输局")==-1){
						nameList +="海安县交通运输局";
					}
					if(yydw.indexOf("如皋县交通运输局")==-1){
						nameList +="如皋县交通运输局";
					}
					if(yydw.indexOf("如东县交通运输局")==-1){
						nameList +="如东县交通运输局";
					}
					if(yydw.indexOf("海门县交通运输局")==-1){
						nameList +="海门县交通运输局";
					}
					if(yydw.indexOf("启东县交通运输局")==-1){
						nameList +="启东县交通运输局";
					}
					if(yydw.indexOf("通州区交通运输局")==-1){
						nameList +="通州区交通运输局";
					}
					if(yydw.indexOf("运输管理处")==-1){
						nameList +="运输管理处";
					}
					//航道统计表
					boolean res = false;
					res = bbsbTjbbDao.queryTjbbYxzbHdByYMWH(datebegin1,dateend1,"航道管理处");
					if(!res){
						nameList +="航道管理处";
					}
					//汽运统计表
					res = bbsbTjbbDao.queryTjbbYxzbQyByYMWH(datebegin1,dateend1,"汽运实业集团有限公司");
					if(!res){
						nameList +="汽运实业集团有限公司";
					}
					//海事统计表
					res = bbsbTjbbDao.queryTjbbYxzbHsByYMWH(nowYear,nowMonth,"地方海事局");
					if(!res){
						nameList +="地方海事局";
					}
				}
				//上年12月
				else{
					String datebegin1 = nowYear-1+"-12-01";
					String dateend1 = nowYear-1+"-12-31";
					//六县+运管统计表
					String yydw = "";
					yydw = bbsbTjbbDao.queryTjbbYxzbSixByYMWH(datebegin1,dateend1);
					if(yydw.indexOf("海安县交通运输局")==-1){
						nameList +="海安县交通运输局";
					}
					if(yydw.indexOf("如皋县交通运输局")==-1){
						nameList +="如皋县交通运输局";
					}
					if(yydw.indexOf("如东县交通运输局")==-1){
						nameList +="如东县交通运输局";
					}
					if(yydw.indexOf("海门县交通运输局")==-1){
						nameList +="海门县交通运输局";
					}
					if(yydw.indexOf("启东县交通运输局")==-1){
						nameList +="启东县交通运输局";
					}
					if(yydw.indexOf("通州区交通运输局")==-1){
						nameList +="通州区交通运输局";
					}
					if(yydw.indexOf("运输管理处")==-1){
						nameList +="运输管理处";
					}
					//航道统计表
					boolean res = false;
					res = bbsbTjbbDao.queryTjbbYxzbHdByYMWH(datebegin1,dateend1,"航道管理处");
					if(!res){
						nameList +="航道管理处";
					}
					//汽运统计表
					res = bbsbTjbbDao.queryTjbbYxzbQyByYMWH(datebegin1,dateend1,"汽运实业集团有限公司");
					if(!res){
						nameList +="汽运实业集团有限公司";
					}
					//海事统计表
					res = bbsbTjbbDao.queryTjbbYxzbHsByYMWH(nowYear-1,12,"地方海事局");
					if(!res){
						nameList +="地方海事局";
					}
				}
			}
			
			
			// List
			String str="";
			str = menuDao.QueryAllMenuInfoName("node_tjbb",nameList);
			//查询自定义财务报表模板
			ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
			BbdzmbList = bbdzDao.queryBbdzmbByLx("tjbb");
			
			request.setAttribute("BbdzmbList", BbdzmbList);
			request.setAttribute("menuList", str);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/getTjbb.jsp").forward(request,
					response);
		}
	}

	/*
	 *  重置菜单
	 * 
	 */
	protected void resetMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			String result= "重置成功";

			MenuDao menuDao = new MenuDao();
			menuDao.DeleteMenuName("node_tjbb");
			menuDao.ResetZzxxMenu("node_tjbb",data1,UserInfo.getName(),UserInfo.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbb&result="+result);
			rd.forward(request, response);
		}
	}
	/*
	 *  主要运行指标统计表航道——列表
	 * 
	 */
	protected void getTjbbYxzbHd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//查询列表
			ArrayList<TjbbYxzbHd> TjbbYxzbHdList = new ArrayList<TjbbYxzbHd>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbTjbbDao.queryTjbbYxzbHdListByBtCount(srbt);
			TjbbYxzbHdList = bbsbTjbbDao.queryTjbbYxzbHdListByBt(srbt, begin, pageSize);
			request.setAttribute("TjbbYxzbHdList", TjbbYxzbHdList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHdList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void TjbbYxzbHdEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String id = request.getParameter("id");
			String xzrq = request.getParameter("xzrq");
			String result = request.getParameter("result");
			TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
			//存储本年上月累计数据
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmListHistory = new ArrayList<TjbbYxzbHdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增时，将上月内容带入
			int hs = 15;//行数（需取出的行数据量）
			Calendar cal=Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH);
			if(!"".equals(xzrq)&&xzrq!=null){
				nowYear = Integer.parseInt(xzrq.substring(0,4));
				Date sj= DateFormat(xzrq);
				nowMonth = sj.getMonth();
				tjbbYxzbHd.setSj(sj);
			}
			//通过时间和单位查询（1月份不用带入）
			if(nowMonth!=0){
				String datebegin1 = nowYear+"-"+nowMonth+"-01";
				String dateend1 = nowYear+"-"+nowMonth+"-31";
				int LastMonth = 0;
				LastMonth = bbsbTjbbDao.queryTjbbYxzbHdByYM(datebegin1,dateend1,UserInfo.getCompany());
				TjbbYxzbHdZxmListHistory =  bbsbTjbbDao.queryYxzbHdZxmByLastMonth1(LastMonth,hs);
				TjbbYxzbHdZxmList =  bbsbTjbbDao.queryYxzbHdZxmByLastMonth(TjbbYxzbHdZxmList,LastMonth,hs);
			}
			//通过时间和单位查询上年同期累计
			String datebegin2 = (nowYear-1)+"-"+(nowMonth+1)+"-01";
			String dateend2 = (nowYear-1)+"-"+(nowMonth+1)+"-31";
			int LastYear = 0;
			LastYear = bbsbTjbbDao.queryTjbbYxzbHdByYM(datebegin2,dateend2,UserInfo.getCompany());
			TjbbYxzbHdZxmList =  bbsbTjbbDao.queryYxzbHdZxmByLastYear(TjbbYxzbHdZxmList,LastYear,hs);
			if(!"".equals(id)&&id!=null){
				tjbbYxzbHd = bbsbTjbbDao.queryTjbbYxzbHdByID(Integer.parseInt(id));
				//数据列表
				TjbbYxzbHdZxmList =  bbsbTjbbDao.queryTjbbYxzbHdZxmByID(Integer.parseInt(id));
			}
			request.setAttribute("tjbbYxzbHd", tjbbYxzbHd);
			request.setAttribute("TjbbYxzbHdZxmList", TjbbYxzbHdZxmList);
			request.setAttribute("TjbbYxzbHdZxmListHistory", TjbbYxzbHdZxmListHistory);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHdEdit.jsp").forward(request,
					response);
		}
	}
	

	
	/*
	 *  进入保存页面
	 * 
	 */
	protected void TjbbYxzbHdSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String TjbbYxzbHd_id = request.getParameter("TjbbYxzbHd_id");
			String bt= request.getParameter("bt");
			String sj = request.getParameter("sj");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String yxfx= request.getParameter("yxfx");
//			String shsj= request.getParameter("shsj");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
			tjbbYxzbHd.setBt(bt);
			tjbbYxzbHd.setCzr(UserInfo.getName());
			tjbbYxzbHd.setCzrID(UserInfo.getUsername());
			tjbbYxzbHd.setCzrdw(UserInfo.getCompany());
			tjbbYxzbHd.setCzrphone(czrphone);
			tjbbYxzbHd.setCzsj(data1);
			tjbbYxzbHd.setSj(DateFormat(sj));
			tjbbYxzbHd.setShr(shr);
			tjbbYxzbHd.setShrID(shrID);
			tjbbYxzbHd.setYxfx(yxfx);
//			tjbbYxzbHd.setShsj(DateFormat(shsj));
			tjbbYxzbHd.setShyj("未审批");
			tjbbYxzbHd.setTjzt(tjzt);
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增或修改主表
			if(!"".equals(TjbbYxzbHd_id)&&TjbbYxzbHd_id!=null){
				tjbbYxzbHd.setId(Integer.parseInt(TjbbYxzbHd_id));
				bbsbTjbbDao.updateTjbbYxzbHd(tjbbYxzbHd);
				bbsbTjbbDao.deleteTjbbYxzbHdZxmByID(Integer.parseInt(TjbbYxzbHd_id));
			}else{
				TjbbYxzbHd_id = bbsbTjbbDao.insertTjbbYxzbHd(tjbbYxzbHd)+"";
			}

			//子表数据
			String zb1[]= request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String zb2[]= request.getParameterValues("zb2");
			String zb3[]= request.getParameterValues("zb3");
			String zb4[]= request.getParameterValues("zb4");
			String zb5[]= request.getParameterValues("zb5");
			String zb6[]= request.getParameterValues("zb6");
			String zb7[]= request.getParameterValues("zb7");
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
			if(zb1!=null){
				for(int i=0; i<zb1.length; i++){
					TjbbYxzbHdZxm tjbbYxzbHdZxm = new TjbbYxzbHdZxm();
					tjbbYxzbHdZxm.setXmid(Integer.parseInt(TjbbYxzbHd_id));
					tjbbYxzbHdZxm.setZb1(zb1[i]);
					tjbbYxzbHdZxm.setZb2(zb2[i]);
					tjbbYxzbHdZxm.setZb3(zb3[i]);
					tjbbYxzbHdZxm.setZb4(zb4[i]);
					tjbbYxzbHdZxm.setZb5(zb5[i]);
					tjbbYxzbHdZxm.setZb6(zb6[i]);
					tjbbYxzbHdZxm.setZb7(zb7[i]);
					TjbbYxzbHdZxmList.add(tjbbYxzbHdZxm);
				}
				bbsbTjbbDao.insertTjbbYxzbHdZxm(TjbbYxzbHdZxmList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHd&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void TjbbYxzbHdShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
		ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			tjbbYxzbHd = bbsbTjbbDao.queryTjbbYxzbHdByID(ID);
			TjbbYxzbHdZxmList =  bbsbTjbbDao.queryTjbbYxzbHdZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("tjbbYxzbHd", tjbbYxzbHd);
		request.setAttribute("TjbbYxzbHdZxmList", TjbbYxzbHdZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHdShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void TjbbYxzbHdDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		bbsbTjbbDao.deleteTjbbYxzbHdById(Integer.parseInt(id));
		//删除子项目记录
		bbsbTjbbDao.deleteTjbbYxzbHdZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHd&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getTjbbYxzbHdExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String TjbbYxzbHd_id = request.getParameter("TjbbYxzbHd_id");
		TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
		ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(TjbbYxzbHd_id)&&TjbbYxzbHd_id!=null){
			int ID = Integer.parseInt(TjbbYxzbHd_id);
			tjbbYxzbHd = bbsbTjbbDao.queryTjbbYxzbHdByID(ID);
			TjbbYxzbHdZxmList =  bbsbTjbbDao.queryTjbbYxzbHdZxmByID(ID);
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("主要运行指标统计表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,tjbbYxzbHd.getSj().toString().substring(0,4)+"年"+tjbbYxzbHd.getSj().toString().substring(5,7)+"月"+tjbbYxzbHd.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"填报单位："+tjbbYxzbHd.getCzrdw(),format2);
				wsheet.addCell(label);

				label = new Label(0,2,"序号",format6);
				wsheet.addCell(label);
				label = new Label(1,2,"指标名称",format6);
				wsheet.addCell(label);
				label = new Label(4,2,"上月数",format6);
				wsheet.addCell(label);
				label = new Label(5,2,"本月数",format6);
				wsheet.addCell(label);
				label = new Label(6,2,"环比增减%",format6);
				wsheet.addCell(label);
				label = new Label(7,2,"本年累计",format6);
				wsheet.addCell(label);
				label = new Label(8,2,"上年同期累计",format6);
				wsheet.addCell(label);
				label = new Label(9,2,"上年同比%",format6);
				wsheet.addCell(label);
				label = new Label(10,2,"备注",format6);
				wsheet.addCell(label);
				
				label = new Label(0,3,"1",format6);
				wsheet.addCell(label);
				label = new Label(1,3,"水路",format6);
				wsheet.addCell(label);
				label = new Label(2,3,"船舶过闸量（吨）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,4,"2",format6);
				wsheet.addCell(label);
				label = new Label(2,4,"其中",format6);
				wsheet.addCell(label);
				label = new Label(3,4,"南通船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,5,"3",format6);
				wsheet.addCell(label);
				label = new Label(3,5,"九圩港闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,6,"4",format6);
				wsheet.addCell(label);
				label = new Label(3,6,"焦港船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,7,"5",format6);
				wsheet.addCell(label);
				label = new Label(3,7,"吕四船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,8,"6",format6);
				wsheet.addCell(label);
				label = new Label(3,8,"海安船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,9,"7",format6);
				wsheet.addCell(label);
				label = new Label(2,9,"内河电煤等重点物资过闸量（吨）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,10,"8",format6);
				wsheet.addCell(label);
				label = new Label(2,10,"其中",format6);
				wsheet.addCell(label);
				label = new Label(3,10,"南通船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,11,"9",format6);
				wsheet.addCell(label);
				label = new Label(3,11,"九圩港闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,12,"10",format6);
				wsheet.addCell(label);
				label = new Label(3,12,"焦港船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,13,"11",format6);
				wsheet.addCell(label);
				label = new Label(3,13,"吕四船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,14,"12",format6);
				wsheet.addCell(label);
				label = new Label(3,14,"海安船闸",format5);
				wsheet.addCell(label);
				
				label = new Label(0,15,"13",format6);
				wsheet.addCell(label);
				label = new Label(2,15,"内河钢结构船舶过闸量（吨）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,16,"14",format6);
				wsheet.addCell(label);
				label = new Label(2,16,"九圩港闸",format6);
				wsheet.addCell(label);
				
				label = new Label(0,17,"15",format6);
				wsheet.addCell(label);
				label = new Label(2,17,"",format6);
				wsheet.addCell(label);

				for(int i=0; i<TjbbYxzbHdZxmList.size(); i++){
					label = new Label(4,i+3,TjbbYxzbHdZxmList.get(i).getZb1(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,TjbbYxzbHdZxmList.get(i).getZb2(),format6);
					wsheet.addCell(label);
					label = new Label(6,i+3,TjbbYxzbHdZxmList.get(i).getZb3(),format6);
					wsheet.addCell(label);
					label = new Label(7,i+3,TjbbYxzbHdZxmList.get(i).getZb4(),format6);
					wsheet.addCell(label);
					label = new Label(8,i+3,TjbbYxzbHdZxmList.get(i).getZb5(),format6);
					wsheet.addCell(label);
					label = new Label(9,i+3,TjbbYxzbHdZxmList.get(i).getZb6(),format6);
					wsheet.addCell(label);
					label = new Label(10,i+3,TjbbYxzbHdZxmList.get(i).getZb7(),format6);
					wsheet.addCell(label);
				}
				label = new Label(0,18,"运行分析说明：",format2);
				wsheet.addCell(label);
				label = new Label(2,18,tjbbYxzbHd.getYxfx(),format2);
				wsheet.addCell(label);
				
				label = new Label(0,19,"审核人：",format2);
				wsheet.addCell(label);
				label = new Label(1,19,tjbbYxzbHd.getShr(),format2);
				wsheet.addCell(label);
				label = new Label(3,19,"填表人：",format3);
				wsheet.addCell(label);
				label = new Label(4,19,tjbbYxzbHd.getCzr(),format2);
				wsheet.addCell(label);
				label = new Label(6,19,"电话：",format3);
				wsheet.addCell(label);
				label = new Label(7,19,tjbbYxzbHd.getCzrphone(),format2);
				wsheet.addCell(label);
				label = new Label(9,19,"上报日期：",format3);
				wsheet.addCell(label);
				label = new Label(10,19,tjbbYxzbHd.getCzsj().toString().substring(0,10),format2);
				wsheet.addCell(label);
				
				//合并单元格             （列、行、列、行）
				wsheet.mergeCells(0, 0, 10, 0);
				wsheet.mergeCells(0, 1, 3, 1);
				wsheet.mergeCells(1, 2, 3, 2);
				wsheet.mergeCells(1, 3, 1, 17);
				wsheet.mergeCells(2, 3, 3, 3);
				wsheet.mergeCells(2, 4, 2, 8);
				wsheet.mergeCells(2, 9, 3, 9);
				wsheet.mergeCells(2, 10, 2, 14);
				wsheet.mergeCells(2, 15, 3, 15);
				wsheet.mergeCells(2, 16, 3, 16);
				wsheet.mergeCells(2, 17, 3, 17);
				wsheet.mergeCells(0, 18, 1, 18);
				wsheet.mergeCells(2, 18, 10, 18);
				wsheet.mergeCells(1, 19, 2, 19);
				//设置列宽
				wsheet.setColumnView(0, 12);
				wsheet.setColumnView(1, 12);
				wsheet.setColumnView(2, 12);
				wsheet.setColumnView(3, 25);
				wsheet.setColumnView(4, 12);
				wsheet.setColumnView(5, 12);
				wsheet.setColumnView(6, 12);
				wsheet.setColumnView(7, 12);
				wsheet.setColumnView(8, 15);
				wsheet.setColumnView(9, 12);
				wsheet.setColumnView(10, 15);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<TjbbYxzbHdZxmList.size()+5; k++){
					wsheet.setRowView(k, 400, false);
				}
				
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readTjbbYxzbHdExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String TjbbYxzbHd_id = request.getParameter("TjbbYxzbHd_id");
			String sj= request.getParameter("sj");
			String bt= request.getParameter("bt");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String czrphone= request.getParameter("czrphone");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}
			//新增时，将上月内容带入
			int hs = 15;//行数（需取出的行数据量）
			Calendar cal=Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH);
			//手动输入
			if(!"".equals(sj)&&sj!=null){
				nowYear = Integer.parseInt(sj.substring(0,4));
				Date sjd= DateFormat(sj);
				nowMonth = sjd.getMonth();
			}
			//存储本年上月累计数据
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmListHistory = new ArrayList<TjbbYxzbHdZxm>();
			//通过时间和单位查询（1月份不用带入）
			if(nowMonth!=0){
				String datebegin1 = nowYear+"-"+nowMonth+"-01";
				String dateend1 = nowYear+"-"+nowMonth+"-31";
				int LastMonth = 0;
				BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
				LastMonth = bbsbTjbbDao.queryTjbbYxzbHdByYM(datebegin1,dateend1,UserInfo.getCompany());
				TjbbYxzbHdZxmListHistory =  bbsbTjbbDao.queryYxzbHdZxmByLastMonth1(LastMonth,hs);
			}  
			TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
			ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
			tjbbYxzbHd.setBt(bt);
			tjbbYxzbHd.setSj(DateFormat(sj));
			tjbbYxzbHd.setShr(shr);
			tjbbYxzbHd.setShrID(shrID);
			tjbbYxzbHd.setCzrphone(czrphone);
			if(!"".equals(TjbbYxzbHd_id)&&TjbbYxzbHd_id!=null){
				tjbbYxzbHd.setId(Integer.parseInt(TjbbYxzbHd_id));
			}
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<19||c<11){
					result = "请检查文件是否正确";
				}else{
					//找到指标甲
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("上月数".equals(sheet[0].getCell(4,i).getContents())){
							num=i;
							break;
						}
					}
					for(int j=1; j<16; j++){
						TjbbYxzbHdZxm tjbbYxzbHdZxm = new TjbbYxzbHdZxm();
						tjbbYxzbHdZxm.setZb1(sheet[0].getCell(4,num+j).getContents());
						tjbbYxzbHdZxm.setZb2(sheet[0].getCell(5,num+j).getContents());
						tjbbYxzbHdZxm.setZb3(sheet[0].getCell(6,num+j).getContents());
						tjbbYxzbHdZxm.setZb4(sheet[0].getCell(7,num+j).getContents());
						tjbbYxzbHdZxm.setZb5(sheet[0].getCell(8,num+j).getContents());
						tjbbYxzbHdZxm.setZb6(sheet[0].getCell(9,num+j).getContents());
						tjbbYxzbHdZxm.setZb7(sheet[0].getCell(10,num+j).getContents());
						TjbbYxzbHdZxmList.add(tjbbYxzbHdZxm);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("tjbbYxzbHd", tjbbYxzbHd);
			request.setAttribute("TjbbYxzbHdZxmList", TjbbYxzbHdZxmList);
			request.setAttribute("TjbbYxzbHdZxmListHistory", TjbbYxzbHdZxmListHistory);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHdEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入驳回
	 * 
	 */
	protected void TjbbYxzbHdReturn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String id = request.getParameter("id");
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbTjbbDao.updateTjbbYxzbHdRet(id);
				String dxnr = UserInfo.getName()+"：您有一份主要运行指标统计表（航道处）被驳回，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHd&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}

	/*
	 *  主要运行指标统计表六县——列表
	 * 
	 */
	protected void getTjbbYxzbSix(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//查询列表
			ArrayList<TjbbYxzbSix> TjbbYxzbSixList = new ArrayList<TjbbYxzbSix>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbTjbbDao.queryTjbbYxzbSixListByBtCount(srbt);
			TjbbYxzbSixList = bbsbTjbbDao.queryTjbbYxzbSixListByBt(srbt, begin, pageSize);
			request.setAttribute("TjbbYxzbSixList", TjbbYxzbSixList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbSixList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void TjbbYxzbSixEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String id = request.getParameter("id");
			String xzrq = request.getParameter("xzrq");
			String result = request.getParameter("result");
			TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
			//存储本年上月累计数据
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmListHistory = new ArrayList<TjbbYxzbSixZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增时，将上月内容带入
			int hs = 12;//行数（需取出的行数据量）
			Calendar cal=Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH);
			//手动输入
			if(!"".equals(xzrq)&&xzrq!=null){
				nowYear = Integer.parseInt(xzrq.substring(0,4));
				Date sj= DateFormat(xzrq);
				nowMonth = sj.getMonth();
				tjbbYxzbSix.setSj(sj);
			}
			//通过时间和单位查询（1月份不用带入）
			if(nowMonth!=0){
				String datebegin1 = nowYear+"-"+nowMonth+"-01";
				String dateend1 = nowYear+"-"+nowMonth+"-31";
				int LastMonth = 0;
				LastMonth = bbsbTjbbDao.queryTjbbYxzbSixByYM(datebegin1,dateend1,UserInfo.getCompany());
				TjbbYxzbSixZxmListHistory =  bbsbTjbbDao.queryYxzbSixZxmByLastMonth1(LastMonth,hs);
				TjbbYxzbSixZxmList =  bbsbTjbbDao.queryYxzbSixZxmByLastMonth(TjbbYxzbSixZxmList,LastMonth,hs);
			}
			//通过时间和单位查询上年同期累计（2016年3月16日后，不再统计上年同期累计——顾彬）
			String datebegin2 = (nowYear-1)+"-"+(nowMonth+1)+"-01";
			String dateend2 = (nowYear-1)+"-"+(nowMonth+1)+"-31";
			int LastYear = 0;
			LastYear = bbsbTjbbDao.queryTjbbYxzbSixByYM(datebegin2,dateend2,UserInfo.getCompany());
//			TjbbYxzbSixZxmList =  bbsbTjbbDao.queryYxzbSixZxmByLastYear(TjbbYxzbSixZxmList,LastYear,hs);
		
			if(!"".equals(id)&&id!=null){
				tjbbYxzbSix = bbsbTjbbDao.queryTjbbYxzbSixByID(Integer.parseInt(id));
				//数据列表
				TjbbYxzbSixZxmList =  bbsbTjbbDao.queryTjbbYxzbSixZxmByID(Integer.parseInt(id));
			}
				
			request.setAttribute("tjbbYxzbSix", tjbbYxzbSix);
			request.setAttribute("TjbbYxzbSixZxmList", TjbbYxzbSixZxmList);
			request.setAttribute("TjbbYxzbSixZxmListHistory", TjbbYxzbSixZxmListHistory);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbSixEdit.jsp").forward(request,
					response);
		}
	}
	

	
	/*
	 *  进入保存页面
	 * 
	 */
	protected void TjbbYxzbSixSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String TjbbYxzbSix_id = request.getParameter("TjbbYxzbSix_id");
			String bt= request.getParameter("bt");
			String sj = request.getParameter("sj");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String yxfx= request.getParameter("yxfx");
//			String shsj= request.getParameter("shsj");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
			tjbbYxzbSix.setBt(bt);
			tjbbYxzbSix.setCzr(UserInfo.getName());
			tjbbYxzbSix.setCzrID(UserInfo.getUsername());
			tjbbYxzbSix.setCzrdw(UserInfo.getCompany());
			tjbbYxzbSix.setCzrphone(czrphone);
			tjbbYxzbSix.setCzsj(data1);
			tjbbYxzbSix.setSj(DateFormat(sj));
			tjbbYxzbSix.setShr(shr);
			tjbbYxzbSix.setShrID(shrID);
			tjbbYxzbSix.setYxfx(yxfx);
//			tjbbYxzbSix.setShsj(DateFormat(shsj));
			tjbbYxzbSix.setShyj("未审批");
			tjbbYxzbSix.setTjzt(tjzt);
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增或修改主表
			if(!"".equals(TjbbYxzbSix_id)&&TjbbYxzbSix_id!=null){
				tjbbYxzbSix.setId(Integer.parseInt(TjbbYxzbSix_id));
				bbsbTjbbDao.updateTjbbYxzbSix(tjbbYxzbSix);
				bbsbTjbbDao.deleteTjbbYxzbSixZxmByID(Integer.parseInt(TjbbYxzbSix_id));
			}else{
				TjbbYxzbSix_id = bbsbTjbbDao.insertTjbbYxzbSix(tjbbYxzbSix)+"";
			}

			//子表数据
			String zb1[]= request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String zb2[]= request.getParameterValues("zb2");
			String zb3[]= request.getParameterValues("zb3");
			String zb4[]= request.getParameterValues("zb4");
			String zb5[]= request.getParameterValues("zb5");
			String zb6[]= request.getParameterValues("zb6");
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
			if(zb1!=null){
				for(int i=0; i<zb1.length; i++){
					TjbbYxzbSixZxm tjbbYxzbSixZxm = new TjbbYxzbSixZxm();
					tjbbYxzbSixZxm.setXmid(Integer.parseInt(TjbbYxzbSix_id));
					tjbbYxzbSixZxm.setZb1(zb1[i]);
					tjbbYxzbSixZxm.setZb2(zb2[i]);
					tjbbYxzbSixZxm.setZb3(zb3[i]);
					tjbbYxzbSixZxm.setZb4(zb4[i]);
					tjbbYxzbSixZxm.setZb5(zb5[i]);
					tjbbYxzbSixZxm.setZb6(zb6[i]);
					TjbbYxzbSixZxmList.add(tjbbYxzbSixZxm);
				}
				bbsbTjbbDao.insertTjbbYxzbSixZxm(TjbbYxzbSixZxmList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbSix&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void TjbbYxzbSixShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
		ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			tjbbYxzbSix = bbsbTjbbDao.queryTjbbYxzbSixByID(ID);
			TjbbYxzbSixZxmList =  bbsbTjbbDao.queryTjbbYxzbSixZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("tjbbYxzbSix", tjbbYxzbSix);
		request.setAttribute("TjbbYxzbSixZxmList", TjbbYxzbSixZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbSixShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void TjbbYxzbSixDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		bbsbTjbbDao.deleteTjbbYxzbSixById(Integer.parseInt(id));
		//删除子项目记录
		bbsbTjbbDao.deleteTjbbYxzbSixZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbSix&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getTjbbYxzbSixExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String TjbbYxzbSix_id = request.getParameter("TjbbYxzbSix_id");
		TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
		ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(TjbbYxzbSix_id)&&TjbbYxzbSix_id!=null){
			int ID = Integer.parseInt(TjbbYxzbSix_id);
			tjbbYxzbSix = bbsbTjbbDao.queryTjbbYxzbSixByID(ID);
			TjbbYxzbSixZxmList =  bbsbTjbbDao.queryTjbbYxzbSixZxmByID(ID);
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("主要运行指标统计表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,tjbbYxzbSix.getSj().toString().substring(0,4)+"年"+tjbbYxzbSix.getSj().toString().substring(5,7)+"月"+tjbbYxzbSix.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"填报单位："+tjbbYxzbSix.getCzrdw(),format2);
				wsheet.addCell(label);

				label = new Label(0,2,"序号",format6);
				wsheet.addCell(label);
				label = new Label(1,2,"指标名称",format6);
				wsheet.addCell(label);
				label = new Label(3,2,"上月数",format6);
				wsheet.addCell(label);
				label = new Label(4,2,"本月数",format6);
				wsheet.addCell(label);
				label = new Label(5,2,"环比增减%",format6);
				wsheet.addCell(label);
				label = new Label(6,2,"本年累计",format6);
				wsheet.addCell(label);
				label = new Label(7,2,"上年同期累计",format6);
				wsheet.addCell(label);
				label = new Label(8,2,"上年同期同比+-%",format6);
				wsheet.addCell(label);
				label = new Label(9,2,"运行分析（+-10%以上说明）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,3,"1",format6);
				wsheet.addCell(label);
				label = new Label(1,3,"公路",format6);
				wsheet.addCell(label);
				label = new Label(2,3,"客运量（万人）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,4,"2",format6);
				wsheet.addCell(label);
				label = new Label(2,4,"客运周转量（万人公里）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,5,"3",format6);
				wsheet.addCell(label);
				label = new Label(2,5,"货运量（万吨）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,6,"4",format6);
				wsheet.addCell(label);
				label = new Label(2,6,"货运周转量（万吨公里）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,7,"5",format6);
				wsheet.addCell(label);
				label = new Label(1,7,"水路",format6);
				wsheet.addCell(label);
				label = new Label(2,7,"货运量（万吨）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,8,"6",format6);
				wsheet.addCell(label);
				label = new Label(2,8,"货运周转量（万吨公里）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,9,"7",format6);
				wsheet.addCell(label);
				label = new Label(1,9,"公路营运工具",format6);
				wsheet.addCell(label);
				label = new Label(2,9,"营运客车辆数",format6);
				wsheet.addCell(label);
				
				label = new Label(0,10,"8",format6);
				wsheet.addCell(label);
				label = new Label(2,10,"营运客车客位数",format6);
				wsheet.addCell(label);
				
				label = new Label(0,11,"9",format6);
				wsheet.addCell(label);
				label = new Label(2,11,"营运货车辆数",format6);
				wsheet.addCell(label);
				
				label = new Label(0,12,"10",format6);
				wsheet.addCell(label);
				label = new Label(2,12,"营运货车吨位数",format6);
				wsheet.addCell(label);
				
				label = new Label(0,13,"11",format6);
				wsheet.addCell(label);
				label = new Label(1,13,"水路营运工具",format6);
				wsheet.addCell(label);
				label = new Label(2,13,"船舶艘数",format6);
				wsheet.addCell(label);
				
				label = new Label(0,14,"12",format6);
				wsheet.addCell(label);
				label = new Label(2,14,"载重吨位",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<TjbbYxzbSixZxmList.size(); i++){
					label = new Label(3,i+3,TjbbYxzbSixZxmList.get(i).getZb1(),format6);
					wsheet.addCell(label);
					label = new Label(4,i+3,TjbbYxzbSixZxmList.get(i).getZb2(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,TjbbYxzbSixZxmList.get(i).getZb3(),format6);
					wsheet.addCell(label);
					label = new Label(6,i+3,TjbbYxzbSixZxmList.get(i).getZb4(),format6);
					wsheet.addCell(label);
					label = new Label(7,i+3,TjbbYxzbSixZxmList.get(i).getZb5(),format6);
					wsheet.addCell(label);
					label = new Label(8,i+3,TjbbYxzbSixZxmList.get(i).getZb6(),format6);
					wsheet.addCell(label);
				}

				label = new Label(9,3,tjbbYxzbSix.getYxfx(),format6);
				wsheet.addCell(label);
				label = new Label(0,15,"审核人：",format2);
				wsheet.addCell(label);
				label = new Label(2,15,tjbbYxzbSix.getShr(),format2);
				wsheet.addCell(label);
				label = new Label(3,15,"填表人：",format3);
				wsheet.addCell(label);
				label = new Label(4,15,tjbbYxzbSix.getCzr(),format2);
				wsheet.addCell(label);
				label = new Label(6,15,"电话：",format3);
				wsheet.addCell(label);
				label = new Label(7,15,tjbbYxzbSix.getCzrphone(),format2);
				wsheet.addCell(label);
				label = new Label(8,15,"上报日期：",format3);
				wsheet.addCell(label);
				label = new Label(9,15,tjbbYxzbSix.getCzsj().toString().substring(0,10),format2);
				wsheet.addCell(label);
				
				//合并单元格             （列、行、列、行）
				wsheet.mergeCells(0, 0, 9, 0);
				wsheet.mergeCells(0, 1, 3, 1);
				wsheet.mergeCells(1, 2, 2, 2);
				wsheet.mergeCells(1, 3, 1, 6);
				wsheet.mergeCells(1, 7, 1, 8);
				wsheet.mergeCells(1, 9, 1, 12);
				wsheet.mergeCells(9, 3, 9, 14);
				wsheet.mergeCells(1, 13, 1, 14);
				wsheet.mergeCells(0, 15, 1, 15);
				wsheet.mergeCells(4, 15, 5, 15);
				//设置列宽
				wsheet.setColumnView(0, 12);
				wsheet.setColumnView(1, 12);
				wsheet.setColumnView(2, 35);
				wsheet.setColumnView(3, 12);
				wsheet.setColumnView(4, 12);
				wsheet.setColumnView(5, 12);
				wsheet.setColumnView(6, 12);
				wsheet.setColumnView(7, 15);
				wsheet.setColumnView(8, 15);
				wsheet.setColumnView(9, 15);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<TjbbYxzbSixZxmList.size()+4; k++){
					wsheet.setRowView(k, 400, false);
				}
				
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readTjbbYxzbSixExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String TjbbYxzbSix_id = request.getParameter("TjbbYxzbSix_id");
			String sj= request.getParameter("sj");
			String bt= request.getParameter("bt");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String czrphone= request.getParameter("czrphone");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}
			//新增时，将上月内容带入
			int hs = 12;//行数（需取出的行数据量）
			Calendar cal=Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH);
			//手动输入
			if(!"".equals(sj)&&sj!=null){
				nowYear = Integer.parseInt(sj.substring(0,4));
				Date sjd= DateFormat(sj);
				nowMonth = sjd.getMonth();
			}
			//存储本年上月累计数据
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmListHistory = new ArrayList<TjbbYxzbSixZxm>();
			//通过时间和单位查询（1月份不用带入）
			if(nowMonth!=0){
				String datebegin1 = nowYear+"-"+nowMonth+"-01";
				String dateend1 = nowYear+"-"+nowMonth+"-31";
				int LastMonth = 0;
				BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
				LastMonth = bbsbTjbbDao.queryTjbbYxzbSixByYM(datebegin1,dateend1,UserInfo.getCompany());
				TjbbYxzbSixZxmListHistory =  bbsbTjbbDao.queryYxzbSixZxmByLastMonth1(LastMonth,hs);
			}
			TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
			tjbbYxzbSix.setBt(bt);
			tjbbYxzbSix.setSj(DateFormat(sj));
			tjbbYxzbSix.setShr(shr);
			tjbbYxzbSix.setShrID(shrID);
			tjbbYxzbSix.setCzrphone(czrphone);
			if(!"".equals(TjbbYxzbSix_id)&&TjbbYxzbSix_id!=null){
				tjbbYxzbSix.setId(Integer.parseInt(TjbbYxzbSix_id));
			}
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<15||c<10){
					result = "请检查文件是否正确";
				}else{
					//找到指标甲
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("上月数".equals(sheet[0].getCell(3,i).getContents())){
							num=i;
							break;
						}
					}
					//运行分析说明
					tjbbYxzbSix.setYxfx(sheet[0].getCell(9,num+1).getContents());
					for(int j=1; j<13; j++){
						TjbbYxzbSixZxm tjbbYxzbSixZxm = new TjbbYxzbSixZxm();
						tjbbYxzbSixZxm.setZb1(sheet[0].getCell(3,num+j).getContents());
						tjbbYxzbSixZxm.setZb2(sheet[0].getCell(4,num+j).getContents());
						tjbbYxzbSixZxm.setZb3(sheet[0].getCell(5,num+j).getContents());
						tjbbYxzbSixZxm.setZb4(sheet[0].getCell(6,num+j).getContents());
						tjbbYxzbSixZxm.setZb5(sheet[0].getCell(7,num+j).getContents());
						tjbbYxzbSixZxm.setZb6(sheet[0].getCell(8,num+j).getContents());
						TjbbYxzbSixZxmList.add(tjbbYxzbSixZxm);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("tjbbYxzbSix", tjbbYxzbSix);
			request.setAttribute("TjbbYxzbSixZxmList", TjbbYxzbSixZxmList);
			request.setAttribute("TjbbYxzbSixZxmListHistory", TjbbYxzbSixZxmListHistory);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbSixEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入驳回
	 * 
	 */
	protected void TjbbYxzbSixReturn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String id = request.getParameter("id");
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbTjbbDao.updateTjbbYxzbSixRet(id);
				String dxnr = UserInfo.getName()+"：您有一份主要运行指标统计表（六县市）被驳回，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbSix&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/*
	 *  可视化图形——曲线图
	 * 
	 */
	protected void getTjbbQxtSix(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList =xList+"'客运量（万人）',";
				}else if("2".equals(xAxis[i])){
					xList =xList+"'客运周转量（万人公里）',";
				}else if("3".equals(xAxis[i])){
					xList =xList+"'货运量（万吨）',";
				}else if("4".equals(xAxis[i])){
					xList =xList+"'货运周转量（万吨公里）',";
				}else if("5".equals(xAxis[i])){
					xList =xList+"'货运量（万吨）',";
				}else if("6".equals(xAxis[i])){
					xList =xList+"'货运周转量（万吨公里）',";
				}else if("7".equals(xAxis[i])){
					xList =xList+"'营运客车辆数',";
				}else if("8".equals(xAxis[i])){
					xList =xList+"'营运客车客位数',";
				}else if("9".equals(xAxis[i])){
					xList =xList+"'营运货车辆数',";
				}else if("10".equals(xAxis[i])){
					xList =xList+"'营运货车吨位数',";
				}else if("11".equals(xAxis[i])){
					xList =xList+"'船舶艘数',";
				}else if("12".equals(xAxis[i])){
					xList =xList+"'载重吨位',";
				}
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbSixZxmList =  bbsbTjbbDao.queryTjbbYxzbSixZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("上月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("本月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("环比增减%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("本年累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年同期累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("上年同期同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbQxt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  可视化图形——柱状图
	 * 
	 */
	protected void getTjbbZztSix(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList =xList+"'客运量（万人）',";
				}else if("2".equals(xAxis[i])){
					xList =xList+"'客运周转量（万人公里）',";
				}else if("3".equals(xAxis[i])){
					xList =xList+"'货运量（万吨）',";
				}else if("4".equals(xAxis[i])){
					xList =xList+"'货运周转量（万吨公里）',";
				}else if("5".equals(xAxis[i])){
					xList =xList+"'货运量（万吨）',";
				}else if("6".equals(xAxis[i])){
					xList =xList+"'货运周转量（万吨公里）',";
				}else if("7".equals(xAxis[i])){
					xList =xList+"'营运客车辆数',";
				}else if("8".equals(xAxis[i])){
					xList =xList+"'营运客车客位数',";
				}else if("9".equals(xAxis[i])){
					xList =xList+"'营运货车辆数',";
				}else if("10".equals(xAxis[i])){
					xList =xList+"'营运货车吨位数',";
				}else if("11".equals(xAxis[i])){
					xList =xList+"'船舶艘数',";
				}else if("12".equals(xAxis[i])){
					xList =xList+"'载重吨位',";
				}
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbSixZxmList =  bbsbTjbbDao.queryTjbbYxzbSixZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("上月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("本月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("环比增减%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("本年累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年同期累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("上年同期同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbZzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——饼状图
	 * 
	 */
	protected void getTjbbBztSix(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			ArrayList<String> xList = new ArrayList<String>();
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList.add("'客运量（万人）'");
				}else if("2".equals(xAxis[i])){
					xList.add("'客运周转量（万人公里）'");
				}else if("3".equals(xAxis[i])){
					xList.add("'货运量（万吨）'");
				}else if("4".equals(xAxis[i])){
					xList.add("'货运周转量（万吨公里）'");
				}else if("5".equals(xAxis[i])){
					xList.add("'货运量（万吨）'");
				}else if("6".equals(xAxis[i])){
					xList.add("'货运周转量（万吨公里）'");
				}else if("7".equals(xAxis[i])){
					xList.add("'营运客车辆数'");
				}else if("8".equals(xAxis[i])){
					xList.add("'营运客车客位数'");
				}else if("9".equals(xAxis[i])){
					xList.add("'营运货车辆数'");
				}else if("10".equals(xAxis[i])){
					xList.add("'营运货车吨位数'");
				}else if("11".equals(xAxis[i])){
					xList.add("'船舶艘数'");
				}else if("12".equals(xAxis[i])){
					xList.add("'载重吨位'");
				}
			}
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			String yname = "";
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbSixZxmList =  bbsbTjbbDao.queryTjbbYxzbSixZxmByID(Integer.parseInt(id));
			}
			if(yAxis.length==1){
				if("1".equals(yAxis[0])){
					yname = "上月数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb1())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb1());
						}
					}
				}else if("2".equals(yAxis[0])){
					yname = "本月数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb2())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb2());
						}
					}
				}else if("3".equals(yAxis[0])){
					yname = "环比增减%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb3())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb3());
						}
					}
				}else if("4".equals(yAxis[0])){
					yname = "本年累计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb4())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb4());
						}
					}
				}else if("5".equals(yAxis[0])){
					yname = "上年同期累计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb5())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb5());
						}
					}
				}else if("6".equals(yAxis[0])){
					yname = "上年同期同比%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb6())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbSixZxmList.get(xAxisInt.get(k)).getZb6());
						}
					}
				}
				
			}
			request.setAttribute("yname", yname);
			request.setAttribute("xList", xList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbBzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  客运出站运输量统计表（汽运集团）——列表
	 * 
	 */
	protected void getTjbbYxzbQy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//查询列表
			ArrayList<TjbbYxzbQy> TjbbYxzbQyList = new ArrayList<TjbbYxzbQy>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxssrq1 = request.getParameter("cxssrq1");
			String cxssrq2 = request.getParameter("cxssrq2");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxssrq1 = "";
				cxssrq2 = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbTjbbDao.queryTjbbYxzbQyListByBtCount(srbt);
			TjbbYxzbQyList = bbsbTjbbDao.queryTjbbYxzbQyListByBt(srbt, begin, pageSize);
			request.setAttribute("TjbbYxzbQyList", TjbbYxzbQyList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxssrq1", cxssrq1);
			request.setAttribute("cxssrq2", cxssrq2);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbQyList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void TjbbYxzbQyEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String id = request.getParameter("id");
			String xzrq = request.getParameter("xzrq");
			String result = request.getParameter("result");
			TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//存储本年上月累计数据
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmListHistory = new ArrayList<TjbbYxzbQyZxm>();
			//新增时，将上月内容带入
			int hs = 18;//行数（需取出的行数据量）
			Calendar cal=Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH);
			//通过时间和单位查询（1月份不用带入）
			if(!"".equals(xzrq)&&xzrq!=null){
				nowYear = Integer.parseInt(xzrq.substring(0,4));
				Date sj= DateFormat(xzrq);
				nowMonth = sj.getMonth();
				tjbbYxzbQy.setSj(sj);
			}
			if(nowMonth!=0){
				String datebegin1 = nowYear+"-"+nowMonth+"-01";
				String dateend1 = nowYear+"-"+nowMonth+"-31";
				int LastMonth = 0;
				LastMonth = bbsbTjbbDao.queryTjbbYxzbQyByYM(datebegin1,dateend1,UserInfo.getCompany());
				TjbbYxzbQyZxmListHistory =  bbsbTjbbDao.queryYxzbQyZxmByLastMonth1(LastMonth,hs);
				TjbbYxzbQyZxmList =  bbsbTjbbDao.queryYxzbQyZxmByLastMonth(TjbbYxzbQyZxmList,LastMonth,hs);
			}
			//通过时间和单位查询上年同期累计
			String datebegin2 = (nowYear-1)+"-"+(nowMonth+1)+"-01";
			String dateend2 = (nowYear-1)+"-"+(nowMonth+1)+"-31";
			int LastYear = 0;
			LastYear = bbsbTjbbDao.queryTjbbYxzbQyByYM(datebegin2,dateend2,UserInfo.getCompany());
			TjbbYxzbQyZxmList =  bbsbTjbbDao.queryYxzbQyZxmByLastYear(TjbbYxzbQyZxmList,LastYear,hs);
			if(!"".equals(id)&&id!=null){
				tjbbYxzbQy = bbsbTjbbDao.queryTjbbYxzbQyByID(Integer.parseInt(id));
				//数据列表
				TjbbYxzbQyZxmList =  bbsbTjbbDao.queryTjbbYxzbQyZxmByID(Integer.parseInt(id));
			}
			request.setAttribute("tjbbYxzbQy", tjbbYxzbQy);
			request.setAttribute("TjbbYxzbQyZxmList", TjbbYxzbQyZxmList);
			request.setAttribute("TjbbYxzbQyZxmListHistory", TjbbYxzbQyZxmListHistory);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbQyEdit.jsp").forward(request,
					response);
		}
	}
	

	
	/*
	 *  进入保存页面
	 * 
	 */
	protected void TjbbYxzbQySave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String TjbbYxzbQy_id = request.getParameter("TjbbYxzbQy_id");
			String bt= request.getParameter("bt");
			String sj = request.getParameter("sj");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String yxfx= request.getParameter("yxfx");
//			String shsj= request.getParameter("shsj");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
			tjbbYxzbQy.setBt(bt);
			tjbbYxzbQy.setCzr(UserInfo.getName());
			tjbbYxzbQy.setCzrID(UserInfo.getUsername());
			tjbbYxzbQy.setCzrdw(UserInfo.getCompany());
			tjbbYxzbQy.setCzrphone(czrphone);
			tjbbYxzbQy.setCzsj(data1);
			tjbbYxzbQy.setSj(DateFormat(sj));
			tjbbYxzbQy.setShr(shr);
			tjbbYxzbQy.setShrID(shrID);
			tjbbYxzbQy.setYxfx(yxfx);
//			tjbbYxzbQy.setShsj(DateFormat(shsj));
			tjbbYxzbQy.setShyj("未审批");
			tjbbYxzbQy.setTjzt(tjzt);
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增或修改主表
			if(!"".equals(TjbbYxzbQy_id)&&TjbbYxzbQy_id!=null){
				tjbbYxzbQy.setId(Integer.parseInt(TjbbYxzbQy_id));
				bbsbTjbbDao.updateTjbbYxzbQy(tjbbYxzbQy);
				bbsbTjbbDao.deleteTjbbYxzbQyZxmByID(Integer.parseInt(TjbbYxzbQy_id));
			}else{
				TjbbYxzbQy_id = bbsbTjbbDao.insertTjbbYxzbQy(tjbbYxzbQy)+"";
			}

			//子表数据
			String zb1[]= request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String zb2[]= request.getParameterValues("zb2");
			String zb3[]= request.getParameterValues("zb3");
			String zb4[]= request.getParameterValues("zb4");
			String zb5[]= request.getParameterValues("zb5");
			String zb6[]= request.getParameterValues("zb6");
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
			if(zb1!=null){
				for(int i=0; i<zb1.length; i++){
					TjbbYxzbQyZxm tjbbYxzbQyZxm = new TjbbYxzbQyZxm();
					tjbbYxzbQyZxm.setXmid(Integer.parseInt(TjbbYxzbQy_id));
					tjbbYxzbQyZxm.setZb1(zb1[i]);
					tjbbYxzbQyZxm.setZb2(zb2[i]);
					tjbbYxzbQyZxm.setZb3(zb3[i]);
					tjbbYxzbQyZxm.setZb4(zb4[i]);
					tjbbYxzbQyZxm.setZb5(zb5[i]);
					tjbbYxzbQyZxm.setZb6(zb6[i]);
					TjbbYxzbQyZxmList.add(tjbbYxzbQyZxm);
				}
				bbsbTjbbDao.insertTjbbYxzbQyZxm(TjbbYxzbQyZxmList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbQy&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void TjbbYxzbQyShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
		ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			tjbbYxzbQy = bbsbTjbbDao.queryTjbbYxzbQyByID(ID);
			TjbbYxzbQyZxmList =  bbsbTjbbDao.queryTjbbYxzbQyZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("tjbbYxzbQy", tjbbYxzbQy);
		request.setAttribute("TjbbYxzbQyZxmList", TjbbYxzbQyZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbQyShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void TjbbYxzbQyDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		bbsbTjbbDao.deleteTjbbYxzbQyById(Integer.parseInt(id));
		//删除子项目记录
		bbsbTjbbDao.deleteTjbbYxzbQyZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbQy&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getTjbbYxzbQyExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String TjbbYxzbQy_id = request.getParameter("TjbbYxzbQy_id");
		TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
		ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(TjbbYxzbQy_id)&&TjbbYxzbQy_id!=null){
			int ID = Integer.parseInt(TjbbYxzbQy_id);
			tjbbYxzbQy = bbsbTjbbDao.queryTjbbYxzbQyByID(ID);
			TjbbYxzbQyZxmList =  bbsbTjbbDao.queryTjbbYxzbQyZxmByID(ID);
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("客运出站运输量统计表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,tjbbYxzbQy.getSj().toString().substring(0,4)+"年"+tjbbYxzbQy.getSj().toString().substring(5,7)+"月"+tjbbYxzbQy.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"填报单位："+tjbbYxzbQy.getCzrdw(),format2);
				wsheet.addCell(label);

				label = new Label(0,2,"车站名称（含下属站）",format6);
				wsheet.addCell(label);
				label = new Label(1,2,"指标名称",format6);
				wsheet.addCell(label);
				label = new Label(2,2,"上月数（1）",format6);
				wsheet.addCell(label);
				label = new Label(3,2,"本月数（2）",format6);
				wsheet.addCell(label);
				label = new Label(4,2,"环比增减%（3）",format6);
				wsheet.addCell(label);
				label = new Label(5,2,"本年自年初累计数（4）",format6);
				wsheet.addCell(label);
				label = new Label(6,2,"上年自年初累计数（5）",format6);
				wsheet.addCell(label);
				label = new Label(7,2,"与上年累计数同比%（6）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,3,"南通汽车站",format6);
				wsheet.addCell(label);
				label = new Label(1,3,"客运量（万人）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,4,"客运东站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,5,"启东汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,6,"如皋汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,7,"海安汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,8,"海门汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,9,"如东汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,10,"通州汽车站",format6);
				wsheet.addCell(label);

				label = new Label(0,11,"",format6);
				wsheet.addCell(label);
				label = new Label(1,11,"合计",format6);
				wsheet.addCell(label);
				
				label = new Label(0,12,"南通汽车站",format6);
				wsheet.addCell(label);
				label = new Label(1,12,"周转量（万人公里）",format6);
				wsheet.addCell(label);
				
				label = new Label(0,13,"客运东站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,14,"启东汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,15,"如皋汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,16,"海安汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,17,"海门汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,18,"如东汽车站",format6);
				wsheet.addCell(label);
				
				label = new Label(0,19,"通州汽车站",format6);
				wsheet.addCell(label);

				label = new Label(0,20,"",format6);
				wsheet.addCell(label);
				label = new Label(1,20,"合计",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<TjbbYxzbQyZxmList.size(); i++){
					label = new Label(2,i+3,TjbbYxzbQyZxmList.get(i).getZb1(),format6);
					wsheet.addCell(label);
					label = new Label(3,i+3,TjbbYxzbQyZxmList.get(i).getZb2(),format6);
					wsheet.addCell(label);
					label = new Label(4,i+3,TjbbYxzbQyZxmList.get(i).getZb3(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,TjbbYxzbQyZxmList.get(i).getZb4(),format6);
					wsheet.addCell(label);
					label = new Label(6,i+3,TjbbYxzbQyZxmList.get(i).getZb5(),format6);
					wsheet.addCell(label);
					label = new Label(7,i+3,TjbbYxzbQyZxmList.get(i).getZb6(),format6);
					wsheet.addCell(label);
				}

				label = new Label(0,21,"运行分析:"+tjbbYxzbQy.getYxfx(),format2);
				wsheet.addCell(label);
				label = new Label(0,22,"审核人：",format3);
				wsheet.addCell(label);
				label = new Label(1,22,tjbbYxzbQy.getShr(),format2);
				wsheet.addCell(label);
				label = new Label(2,22,"填表人：",format3);
				wsheet.addCell(label);
				label = new Label(3,22,tjbbYxzbQy.getCzr(),format2);
				wsheet.addCell(label);
				label = new Label(4,22,"电话：",format3);
				wsheet.addCell(label);
				label = new Label(5,22,tjbbYxzbQy.getCzrphone(),format2);
				wsheet.addCell(label);
				label = new Label(6,22,"上报日期：",format3);
				wsheet.addCell(label);
				label = new Label(7,22,tjbbYxzbQy.getCzsj().toString().substring(0,10),format2);
				wsheet.addCell(label);
				
				//合并单元格             （列、行、列、行）
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(0, 1, 3, 1);
				wsheet.mergeCells(1, 3, 1, 10);
				wsheet.mergeCells(1, 12, 1, 19);
				wsheet.mergeCells(0, 21, 7, 21);
				//设置列宽
				wsheet.setColumnView(0, 18);
				wsheet.setColumnView(1, 15);
				wsheet.setColumnView(2, 15);
				wsheet.setColumnView(3, 15);
				wsheet.setColumnView(4, 15);
				wsheet.setColumnView(5, 20);
				wsheet.setColumnView(6, 20);
				wsheet.setColumnView(7, 20);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<TjbbYxzbQyZxmList.size()+4; k++){
					wsheet.setRowView(k, 400, false);
				}
				
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readTjbbYxzbQyExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String TjbbYxzbQy_id = request.getParameter("TjbbYxzbQy_id");
			String sj= request.getParameter("sj");
			String bt= request.getParameter("bt");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String czrphone= request.getParameter("czrphone");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//存储本年上月累计数据
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmListHistory = new ArrayList<TjbbYxzbQyZxm>();
			//新增时，将上月内容带入
			int hs = 18;//行数（需取出的行数据量）
			Calendar cal=Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH);
			//通过时间和单位查询（1月份不用带入）
			if(!"".equals(sj)&&sj!=null){
				nowYear = Integer.parseInt(sj.substring(0,4));
				Date sjd= DateFormat(sj);
				nowMonth = sjd.getMonth();
			}
			if(nowMonth!=0){
				String datebegin1 = nowYear+"-"+nowMonth+"-01";
				String dateend1 = nowYear+"-"+nowMonth+"-31";
				int LastMonth = 0;
				LastMonth = bbsbTjbbDao.queryTjbbYxzbQyByYM(datebegin1,dateend1,UserInfo.getCompany());
				TjbbYxzbQyZxmListHistory =  bbsbTjbbDao.queryYxzbQyZxmByLastMonth1(LastMonth,hs);
			}  
			TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
			tjbbYxzbQy.setBt(bt);
			tjbbYxzbQy.setSj(DateFormat(sj));
			tjbbYxzbQy.setShr(shr);
			tjbbYxzbQy.setShrID(shrID);
			tjbbYxzbQy.setCzrphone(czrphone);
			if(!"".equals(TjbbYxzbQy_id)&&TjbbYxzbQy_id!=null){
				tjbbYxzbQy.setId(Integer.parseInt(TjbbYxzbQy_id));
			}
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<19||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标甲
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("指标名称".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					for(int j=1; j<19; j++){
						TjbbYxzbQyZxm tjbbYxzbQyZxm = new TjbbYxzbQyZxm();
						tjbbYxzbQyZxm.setZb1(sheet[0].getCell(2,num+j).getContents());
						tjbbYxzbQyZxm.setZb2(sheet[0].getCell(3,num+j).getContents());
						tjbbYxzbQyZxm.setZb3(sheet[0].getCell(4,num+j).getContents());
						tjbbYxzbQyZxm.setZb4(sheet[0].getCell(5,num+j).getContents());
						tjbbYxzbQyZxm.setZb5(sheet[0].getCell(6,num+j).getContents());
						tjbbYxzbQyZxm.setZb6(sheet[0].getCell(7,num+j).getContents());
						TjbbYxzbQyZxmList.add(tjbbYxzbQyZxm);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("tjbbYxzbQy", tjbbYxzbQy);
			request.setAttribute("TjbbYxzbQyZxmList", TjbbYxzbQyZxmList);
			request.setAttribute("TjbbYxzbQyZxmListHistory", TjbbYxzbQyZxmListHistory);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbQyEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入驳回
	 * 
	 */
	protected void TjbbYxzbQyReturn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String id = request.getParameter("id");
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbTjbbDao.updateTjbbYxzbQyRet(id);
				String dxnr = UserInfo.getName()+"：您有一份客运出站运输量统计表（汽运集团）被驳回，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbQy&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/*
	 *  可视化图形——曲线图
	 * 
	 */
	protected void getTjbbQxtQy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList =xList+"'南通汽车站',";
				}else if("2".equals(xAxis[i])){
					xList =xList+"'客运东站',";
				}else if("3".equals(xAxis[i])){
					xList =xList+"'启东汽车站',";
				}else if("4".equals(xAxis[i])){
					xList =xList+"'如皋汽车站',";
				}else if("5".equals(xAxis[i])){
					xList =xList+"'海安汽车站',";
				}else if("6".equals(xAxis[i])){
					xList =xList+"'海门汽车站',";
				}else if("7".equals(xAxis[i])){
					xList =xList+"'如东汽车站',";
				}else if("8".equals(xAxis[i])){
					xList =xList+"'通州汽车站',";
				}else if("9".equals(xAxis[i])){
					xList =xList+"'合计',";
				}else if("10".equals(xAxis[i])){
					xList =xList+"'南通汽车站',";
				}else if("11".equals(xAxis[i])){
					xList =xList+"'客运东站',";
				}else if("12".equals(xAxis[i])){
					xList =xList+"'启东汽车站',";
				}else if("13".equals(xAxis[i])){
					xList =xList+"'如皋汽车站',";
				}else if("14".equals(xAxis[i])){
					xList =xList+"'海安汽车站',";
				}else if("15".equals(xAxis[i])){
					xList =xList+"'海门汽车站',";
				}else if("16".equals(xAxis[i])){
					xList =xList+"'如东汽车站',";
				}else if("17".equals(xAxis[i])){
					xList =xList+"'通州汽车站',";
				}else if("18".equals(xAxis[i])){
					xList =xList+"'合计',";
				}
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbQyZxmList =  bbsbTjbbDao.queryTjbbYxzbQyZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("上月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("本月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("环比增减%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("本年自年初累计数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年自年初累计数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("与上年累计数同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbQxt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  可视化图形——柱状图
	 * 
	 */
	protected void getTjbbZztQy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList =xList+"'南通汽车站',";
				}else if("2".equals(xAxis[i])){
					xList =xList+"'客运东站',";
				}else if("3".equals(xAxis[i])){
					xList =xList+"'启东汽车站',";
				}else if("4".equals(xAxis[i])){
					xList =xList+"'如皋汽车站',";
				}else if("5".equals(xAxis[i])){
					xList =xList+"'海安汽车站',";
				}else if("6".equals(xAxis[i])){
					xList =xList+"'海门汽车站',";
				}else if("7".equals(xAxis[i])){
					xList =xList+"'如东汽车站',";
				}else if("8".equals(xAxis[i])){
					xList =xList+"'通州汽车站',";
				}else if("9".equals(xAxis[i])){
					xList =xList+"'合计',";
				}else if("10".equals(xAxis[i])){
					xList =xList+"'南通汽车站',";
				}else if("11".equals(xAxis[i])){
					xList =xList+"'客运东站',";
				}else if("12".equals(xAxis[i])){
					xList =xList+"'启东汽车站',";
				}else if("13".equals(xAxis[i])){
					xList =xList+"'如皋汽车站',";
				}else if("14".equals(xAxis[i])){
					xList =xList+"'海安汽车站',";
				}else if("15".equals(xAxis[i])){
					xList =xList+"'海门汽车站',";
				}else if("16".equals(xAxis[i])){
					xList =xList+"'如东汽车站',";
				}else if("17".equals(xAxis[i])){
					xList =xList+"'通州汽车站',";
				}else if("18".equals(xAxis[i])){
					xList =xList+"'合计',";
				}
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbQyZxmList =  bbsbTjbbDao.queryTjbbYxzbQyZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("上月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("本月数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("环比增减%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("本年自年初累计数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年自年初累计数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("与上年累计数同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbZzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——饼状图
	 * 
	 */
	protected void getTjbbBztQy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			ArrayList<String> xList = new ArrayList<String>();
			for(int i=0; i<xAxis.length; i++){
				if("1".equals(xAxis[i])){
					xList.add("'南通汽车站'");
				}else if("2".equals(xAxis[i])){
					xList.add("'客运东站'");
				}else if("3".equals(xAxis[i])){
					xList.add("'启东汽车站'");
				}else if("4".equals(xAxis[i])){
					xList.add("'如皋汽车站'");
				}else if("5".equals(xAxis[i])){
					xList.add("'海安汽车站'");
				}else if("6".equals(xAxis[i])){
					xList.add("'海门汽车站'");
				}else if("7".equals(xAxis[i])){
					xList.add("'如东汽车站'");
				}else if("8".equals(xAxis[i])){
					xList.add("'通州汽车站'");
				}else if("9".equals(xAxis[i])){
					xList.add("'合计'");
				}else if("10".equals(xAxis[i])){
					xList.add("'南通汽车站'");
				}else if("11".equals(xAxis[i])){
					xList.add("'客运东站'");
				}else if("12".equals(xAxis[i])){
					xList.add("'启东汽车站'");
				}else if("13".equals(xAxis[i])){
					xList.add("'如皋汽车站'");
				}else if("14".equals(xAxis[i])){
					xList.add("'海安汽车站'");
				}else if("15".equals(xAxis[i])){
					xList.add("'海门汽车站'");
				}else if("16".equals(xAxis[i])){
					xList.add("'如东汽车站'");
				}else if("17".equals(xAxis[i])){
					xList.add("'通州汽车站'");
				}else if("18".equals(xAxis[i])){
					xList.add("'合计'");
				}
			}
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			String yname = "";
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbQyZxmList =  bbsbTjbbDao.queryTjbbYxzbQyZxmByID(Integer.parseInt(id));
			}
			if(yAxis.length==1){
				if("1".equals(yAxis[0])){
					yname = "上月数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb1())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb1());
						}
					}
				}else if("2".equals(yAxis[0])){
					yname = "本月数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb2())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb2());
						}
					}
				}else if("3".equals(yAxis[0])){
					yname = "环比增减%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb3())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb3());
						}
					}
				}else if("4".equals(yAxis[0])){
					yname = "本年自年初累计数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb4())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb4());
						}
					}
				}else if("5".equals(yAxis[0])){
					yname = "上年自年初累计数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb5())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb5());
						}
					}
				}else if("6".equals(yAxis[0])){
					yname = "与上年累计数同比%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb6())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbQyZxmList.get(xAxisInt.get(k)).getZb6());
						}
					}
				}
				
			}
			request.setAttribute("yname", yname);
			request.setAttribute("xList", xList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbBzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  海事签证月度同比表（海事局）——列表
	 * 
	 */
	protected void getTjbbYxzbHs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//查询列表
			ArrayList<TjbbYxzbHs> TjbbYxzbHsList = new ArrayList<TjbbYxzbHs>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbTjbbDao.queryTjbbYxzbHsListByBtCount(srbt);
			TjbbYxzbHsList = bbsbTjbbDao.queryTjbbYxzbHsListByBt(srbt, begin, pageSize);
			request.setAttribute("TjbbYxzbHsList", TjbbYxzbHsList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void TjbbYxzbHsEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String id = request.getParameter("id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String result = request.getParameter("result");
			TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
			ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				tjbbYxzbHs = bbsbTjbbDao.queryTjbbYxzbHsByID(Integer.parseInt(id));
				//数据列表
				TjbbYxzbHsZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZxmByID(Integer.parseInt(id));
			}else{
				//新增时，将上月内容带入
				int hs = 12;//行数（需取出的行数据量）
				int nowYear = Integer.parseInt(year);
				int nowMonth = Integer.parseInt(month);
				tjbbYxzbHs.setYear(nowYear);
				tjbbYxzbHs.setMonth(nowMonth);
				//从汇总表中获取：海事签证站点个数、进港签证总量（吨）、出港签证总量（吨）、年初进、出港累计
				TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
				tjbbYxzbHsZdhz = bbsbTjbbDao.queryTjbbYxzbHsZdhzByYM(nowYear,nowMonth,UserInfo.getCompany());
				TjbbYxzbHsZxmList =  bbsbTjbbDao.queryYxzbHsZxmByHZ(TjbbYxzbHsZxmList,tjbbYxzbHsZdhz,hs);

				//通过时间和单位查询上年同期累计
//				String datebegin2 = (nowYear-1)+"-"+(nowMonth+1)+"-01";
//				String dateend2 = (nowYear-1)+"-"+(nowMonth+1)+"-31";
				int LastYear = 0;
				LastYear = bbsbTjbbDao.queryTjbbYxzbHsByYM(nowYear-1,nowMonth,UserInfo.getCompany());
				TjbbYxzbHsZxmList =  bbsbTjbbDao.queryYxzbHsZxmByLastYear(TjbbYxzbHsZxmList,LastYear,hs);
			}
			request.setAttribute("tjbbYxzbHs", tjbbYxzbHs);
			request.setAttribute("TjbbYxzbHsZxmList", TjbbYxzbHsZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsEdit.jsp").forward(request,
					response);
		}
	}
	

	
	/*
	 *  进入保存页面
	 * 
	 */
	protected void TjbbYxzbHsSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String TjbbYxzbHs_id = request.getParameter("TjbbYxzbHs_id");
			String bt= request.getParameter("bt");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String yxfx= request.getParameter("yxfx");
//			String shsj= request.getParameter("shsj");
			String czrphone = request.getParameter("czrphone");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
			tjbbYxzbHs.setBt(bt);
			tjbbYxzbHs.setCzr(UserInfo.getName());
			tjbbYxzbHs.setCzrID(UserInfo.getUsername());
			tjbbYxzbHs.setCzrdw(UserInfo.getCompany());
			tjbbYxzbHs.setCzrphone(czrphone);
			tjbbYxzbHs.setCzsj(data1);
			tjbbYxzbHs.setYear(Integer.parseInt(year));
			tjbbYxzbHs.setMonth(Integer.parseInt(month));
			tjbbYxzbHs.setShr(shr);
			tjbbYxzbHs.setShrID(shrID);
			tjbbYxzbHs.setYxfx(yxfx);
//			tjbbYxzbHs.setShsj(DateFormat(shsj));
			tjbbYxzbHs.setShyj("未审批");
			tjbbYxzbHs.setTjzt(tjzt);
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增或修改主表
			if(!"".equals(TjbbYxzbHs_id)&&TjbbYxzbHs_id!=null){
				tjbbYxzbHs.setId(Integer.parseInt(TjbbYxzbHs_id));
				bbsbTjbbDao.updateTjbbYxzbHs(tjbbYxzbHs);
				bbsbTjbbDao.deleteTjbbYxzbHsZxmByID(Integer.parseInt(TjbbYxzbHs_id));
			}else{
				TjbbYxzbHs_id = bbsbTjbbDao.insertTjbbYxzbHs(tjbbYxzbHs)+"";
			}

			//子表数据
			String zb1[]= request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String zb2[]= request.getParameterValues("zb2");
			String zb3[]= request.getParameterValues("zb3");
			String zb4[]= request.getParameterValues("zb4");
			String zb5[]= request.getParameterValues("zb5");
			String zb6[]= request.getParameterValues("zb6");
			ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
			if(zb1!=null){
				for(int i=0; i<zb1.length; i++){
					TjbbYxzbHsZxm tjbbYxzbHsZxm = new TjbbYxzbHsZxm();
					tjbbYxzbHsZxm.setXmid(Integer.parseInt(TjbbYxzbHs_id));
					tjbbYxzbHsZxm.setZb1(zb1[i]);
					tjbbYxzbHsZxm.setZb2(zb2[i]);
					tjbbYxzbHsZxm.setZb3(zb3[i]);
					tjbbYxzbHsZxm.setZb4(zb4[i]);
					tjbbYxzbHsZxm.setZb5(zb5[i]);
					tjbbYxzbHsZxm.setZb6(zb6[i]);
					TjbbYxzbHsZxmList.add(tjbbYxzbHsZxm);
				}
				bbsbTjbbDao.insertTjbbYxzbHsZxm(TjbbYxzbHsZxmList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHs&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void TjbbYxzbHsShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
		ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			tjbbYxzbHs = bbsbTjbbDao.queryTjbbYxzbHsByID(ID);
			TjbbYxzbHsZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("tjbbYxzbHs", tjbbYxzbHs);
		request.setAttribute("TjbbYxzbHsZxmList", TjbbYxzbHsZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void TjbbYxzbHsDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		bbsbTjbbDao.deleteTjbbYxzbHsById(Integer.parseInt(id));
		//删除子项目记录
		bbsbTjbbDao.deleteTjbbYxzbHsZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHs&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getTjbbYxzbHsExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String TjbbYxzbHs_id = request.getParameter("TjbbYxzbHs_id");
		TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
		ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(TjbbYxzbHs_id)&&TjbbYxzbHs_id!=null){
			int ID = Integer.parseInt(TjbbYxzbHs_id);
			tjbbYxzbHs = bbsbTjbbDao.queryTjbbYxzbHsByID(ID);
			TjbbYxzbHsZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZxmByID(ID);
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("客运出站运输量统计表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	
				
				//设置表头
				Label label = new Label(0,0,tjbbYxzbHs.getYear()+"年"+tjbbYxzbHs.getMonth()+"月"+tjbbYxzbHs.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"填报单位："+tjbbYxzbHs.getCzrdw(),format2);
				wsheet.addCell(label);

				label = new Label(0,2,"区划名称",format6);
				wsheet.addCell(label);
				label = new Label(1,2,"月份",format6);
				wsheet.addCell(label);
				label = new Label(2,2,"海事签证站点个数",format6);
				wsheet.addCell(label);
				label = new Label(3,2,"进港签证总量（吨）",format6);
				wsheet.addCell(label);
				label = new Label(4,2,"出港签证总量（吨）",format6);
				wsheet.addCell(label);
				label = new Label(5,2,"年初进、出港累计",format6);
				wsheet.addCell(label);
				label = new Label(6,2,"上年同期进、出港累计",format6);
				wsheet.addCell(label);
				label = new Label(7,2,"同比%",format6);
				wsheet.addCell(label);
				
				label = new Label(0,3,"南通市",format6);
				wsheet.addCell(label);
				label = new Label(1,3,"1",format6);
				wsheet.addCell(label);
				
				label = new Label(1,4,"2",format6);
				wsheet.addCell(label);
				
				label = new Label(1,5,"3",format6);
				wsheet.addCell(label);
				
				label = new Label(1,6,"4",format6);
				wsheet.addCell(label);
				
				label = new Label(1,7,"5",format6);
				wsheet.addCell(label);
				
				label = new Label(1,8,"6",format6);
				wsheet.addCell(label);
				
				label = new Label(1,9,"7",format6);
				wsheet.addCell(label);
				
				label = new Label(1,10,"8",format6);
				wsheet.addCell(label);

				label = new Label(1,11,"9",format6);
				wsheet.addCell(label);
				
				label = new Label(1,12,"10",format6);
				wsheet.addCell(label);
				
				label = new Label(1,13,"11",format6);
				wsheet.addCell(label);
				
				label = new Label(1,14,"12",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<TjbbYxzbHsZxmList.size(); i++){
					label = new Label(2,i+3,TjbbYxzbHsZxmList.get(i).getZb1(),format6);
					wsheet.addCell(label);
					label = new Label(3,i+3,TjbbYxzbHsZxmList.get(i).getZb2(),format6);
					wsheet.addCell(label);
					label = new Label(4,i+3,TjbbYxzbHsZxmList.get(i).getZb3(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,TjbbYxzbHsZxmList.get(i).getZb4(),format6);
					wsheet.addCell(label);
					label = new Label(6,i+3,TjbbYxzbHsZxmList.get(i).getZb5(),format6);
					wsheet.addCell(label);
					label = new Label(7,i+3,TjbbYxzbHsZxmList.get(i).getZb6(),format6);
					wsheet.addCell(label);
				}

				label = new Label(0,15,"运行分析:"+tjbbYxzbHs.getYxfx(),format2);
				wsheet.addCell(label);
				label = new Label(0,16,"审核人：",format3);
				wsheet.addCell(label);
				label = new Label(1,16,tjbbYxzbHs.getShr(),format2);
				wsheet.addCell(label);
				label = new Label(2,16,"填表人：",format3);
				wsheet.addCell(label);
				label = new Label(3,16,tjbbYxzbHs.getCzr(),format2);
				wsheet.addCell(label);
				label = new Label(4,16,"电话：",format3);
				wsheet.addCell(label);
				label = new Label(5,16,tjbbYxzbHs.getCzrphone(),format2);
				wsheet.addCell(label);
				label = new Label(6,16,"上报日期：",format3);
				wsheet.addCell(label);
				label = new Label(7,16,tjbbYxzbHs.getCzsj().toString().substring(0,10),format2);
				wsheet.addCell(label);
				
				//合并单元格             （列、行、列、行）
				wsheet.mergeCells(0, 0, 7, 0);
				wsheet.mergeCells(0, 1, 3, 1);
				wsheet.mergeCells(0, 3, 0, 14);
				wsheet.mergeCells(0, 15, 7, 15);
				//设置列宽
				wsheet.setColumnView(0, 15);
				wsheet.setColumnView(1, 15);
				wsheet.setColumnView(2, 15);
				wsheet.setColumnView(3, 15);
				wsheet.setColumnView(4, 15);
				wsheet.setColumnView(5, 15);
				wsheet.setColumnView(6, 15);
				wsheet.setColumnView(7, 15);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<TjbbYxzbHsZxmList.size()+4; k++){
					wsheet.setRowView(k, 400, false);
				}
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readTjbbYxzbHsExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String TjbbYxzbHs_id = request.getParameter("TjbbYxzbHs_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String bt= request.getParameter("bt");
			String shr= request.getParameter("shr");
			String shrID= request.getParameter("shrID");
			String czrphone= request.getParameter("czrphone");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}  
			TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
			ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
			tjbbYxzbHs.setBt(bt);
			tjbbYxzbHs.setYear(Integer.parseInt(year));
			tjbbYxzbHs.setMonth(Integer.parseInt(month));
			tjbbYxzbHs.setShr(shr);
			tjbbYxzbHs.setShrID(shrID);
			tjbbYxzbHs.setCzrphone(czrphone);
			if(!"".equals(TjbbYxzbHs_id)&&TjbbYxzbHs_id!=null){
				tjbbYxzbHs.setId(Integer.parseInt(TjbbYxzbHs_id));
			}
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			if(sheet!=null&&sheet.length>0){
				int r = sheet[0].getRows();//行数
				int c = sheet[0].getColumns();//列数
				if(r<17||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标甲
					int num=0;
					for(int i=0; i<sheet[0].getRows(); i++){
						if("月份".equals(sheet[0].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					for(int j=1; j<13; j++){
						TjbbYxzbHsZxm tjbbYxzbHsZxm = new TjbbYxzbHsZxm();
						tjbbYxzbHsZxm.setZb1(sheet[0].getCell(2,num+j).getContents());
						tjbbYxzbHsZxm.setZb2(sheet[0].getCell(3,num+j).getContents());
						tjbbYxzbHsZxm.setZb3(sheet[0].getCell(4,num+j).getContents());
						tjbbYxzbHsZxm.setZb4(sheet[0].getCell(5,num+j).getContents());
						tjbbYxzbHsZxm.setZb5(sheet[0].getCell(6,num+j).getContents());
						tjbbYxzbHsZxm.setZb6(sheet[0].getCell(7,num+j).getContents());
						TjbbYxzbHsZxmList.add(tjbbYxzbHsZxm);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("tjbbYxzbHs", tjbbYxzbHs);
			request.setAttribute("TjbbYxzbHsZxmList", TjbbYxzbHsZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsEdit.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入驳回
	 * 
	 */
	protected void TjbbYxzbHsReturn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String id = request.getParameter("id");
			String company = request.getParameter("company");
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbTjbbDao.updateTjbbYxzbHsRet(id);
				String dxnr = UserInfo.getName()+"：您有一份海事签证月度同比表（海事局）被驳回，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHs&flag=1&menuname="+company).forward(request,
					response);
			
		}
	}
	/*
	 *  可视化图形——曲线图
	 * 
	 */
	protected void getTjbbQxtHs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				xList =xList+"'"+xAxis[i]+"',";
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("海事签证站点个数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("进港签证总量（吨）");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("出港签证总量（吨）");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("年初进、出港累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年同期进、出港累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbQxt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  可视化图形——柱状图
	 * 
	 */
	protected void getTjbbZztHs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				xList =xList+"'"+xAxis[i]+"',";
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("海事签证站点个数");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("进港签证总量（吨）");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("出港签证总量（吨）");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("年初进、出港累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("上年同期进、出港累计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("同比%");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbZzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——饼状图
	 * 
	 */
	protected void getTjbbBztHs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			ArrayList<String> xList = new ArrayList<String>();
			for(int i=0; i<xAxis.length; i++){
				xList.add("'"+xAxis[i]+"月'");
			}
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			String yname = "";
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZxmByID(Integer.parseInt(id));
			}
			if(yAxis.length==1){
				if("1".equals(yAxis[0])){
					yname = "海事签证站点个数";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb1())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb1());
						}
					}
				}else if("2".equals(yAxis[0])){
					yname = "进港签证总量（吨）";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb2())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb2());
						}
					}
				}else if("3".equals(yAxis[0])){
					yname = "出港签证总量（吨）";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb3())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb3());
						}
					}
				}else if("4".equals(yAxis[0])){
					yname = "年初进、出港累计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb4())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb4());
						}
					}
				}else if("5".equals(yAxis[0])){
					yname = "上年同期进、出港累计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb5())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb5());
						}
					}
				}else if("6".equals(yAxis[0])){
					yname = "同比%";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb6())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZxmList.get(xAxisInt.get(k)).getZb6());
						}
					}
				}
				
			}
			request.setAttribute("yname", yname);
			request.setAttribute("xList", xList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbBzt.jsp").forward(request,
					response);
		}
	}
	
	/*
	 *  海事签证月度同比表（海事局站点）——列表
	 * 
	 */
	protected void getTjbbYxzbHsZd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//查询列表
			ArrayList<TjbbYxzbHsZd> TjbbYxzbHsZdList = new ArrayList<TjbbYxzbHsZd>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			String srbt = request.getParameter("srbt");
			String cxcompany = request.getParameter("cxcompany");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
			String menuname = request.getParameter("menuname");
			String cxtj = request.getParameter("cxtj");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
				cxcompany = menuname;
				srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt<>'1' and  czrID = '"+UserInfo.getUsername()+"')) ";
				cxtj = "1";
			}
			countAll = bbsbTjbbDao.queryTjbbYxzbHsZdListByBtCount(srbt);
			TjbbYxzbHsZdList = bbsbTjbbDao.queryTjbbYxzbHsZdListByBt(srbt, begin, pageSize);
			request.setAttribute("TjbbYxzbHsZdList", TjbbYxzbHsZdList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			request.setAttribute("cxtj", cxtj);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入新增或修改页面
	 * 
	 */
	protected void TjbbYxzbHsZdEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//新增或者修改
			String id = request.getParameter("id");
			String zdmc = request.getParameter("zdmc");
			String result = request.getParameter("result");
			TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				tjbbYxzbHsZd = bbsbTjbbDao.queryTjbbYxzbHsZdByID(Integer.parseInt(id));
				//数据列表
				TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(Integer.parseInt(id));
			}else{
				//新增时，将上月内容带入
				Calendar cal=Calendar.getInstance();
				int nowYear = cal.get(Calendar.YEAR);
				int nowMonth = cal.get(Calendar.MONTH);
				int LastMonth = 0;
				//通过时间和单位查询（1月份不用带入）
				if(nowMonth!=0&&!"".equals(zdmc)){
					LastMonth = bbsbTjbbDao.queryTjbbYxzbHsZdByYM(nowYear,nowMonth,UserInfo.getCompany(),zdmc);
					TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryYxzbHsZdZxmByLastMonth(TjbbYxzbHsZdZxmList,LastMonth);
					tjbbYxzbHsZd.setZdmc(zdmc);
				}
				//通过时间和单位查询上年同期累计
//				String datebegin2 = (nowYear-1)+"-"+(nowMonth+1)+"-01";
//				String dateend2 = (nowYear-1)+"-"+(nowMonth+1)+"-31";
//				int LastYear = 0;
//				LastYear = bbsbTjbbDao.queryTjbbYxzbHsZdByYM(datebegin2,dateend2,UserInfo.getCompany());
//				TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryYxzbHsZdZxmByLastYear(TjbbYxzbHsZdZxmList,LastYear,hs);
			}
			request.setAttribute("tjbbYxzbHsZd", tjbbYxzbHsZd);
			request.setAttribute("TjbbYxzbHsZdZxmList", TjbbYxzbHsZdZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdEdit.jsp").forward(request,
					response);
		}
	}
	

	
	/*
	 *  进入保存页面
	 * 
	 */
	protected void TjbbYxzbHsZdSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String TjbbYxzbHsZd_id = request.getParameter("TjbbYxzbHsZd_id");
			String bt= request.getParameter("bt");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
//			String shr= request.getParameter("shr");
//			String shrID= request.getParameter("shrID");
			String zdmc= request.getParameter("zdmc");
//			String shsj= request.getParameter("shsj");
			String lxr = request.getParameter("lxr");
			String lxrdh = request.getParameter("lxrdh");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			
			TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
			tjbbYxzbHsZd.setBt(bt);
			tjbbYxzbHsZd.setCzr(UserInfo.getName());
			tjbbYxzbHsZd.setCzrID(UserInfo.getUsername());
			tjbbYxzbHsZd.setCzrdw(UserInfo.getCompany());
			tjbbYxzbHsZd.setLxr(lxr);
			tjbbYxzbHsZd.setLxrdh(lxrdh);
			tjbbYxzbHsZd.setCzsj(data1);
			tjbbYxzbHsZd.setYear(Integer.parseInt(year));
			tjbbYxzbHsZd.setMonth(Integer.parseInt(month));
//			tjbbYxzbHsZd.setShr(shr);
//			tjbbYxzbHsZd.setShrID(shrID);
			tjbbYxzbHsZd.setZdmc(zdmc);
//			tjbbYxzbHsZd.setShsj(DateFormat(shsj));
//			tjbbYxzbHsZd.setShyj("未审批");
			tjbbYxzbHsZd.setTjzt(tjzt);
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//新增或修改主表
			if(!"".equals(TjbbYxzbHsZd_id)&&TjbbYxzbHsZd_id!=null){
				tjbbYxzbHsZd.setId(Integer.parseInt(TjbbYxzbHsZd_id));
				bbsbTjbbDao.updateTjbbYxzbHsZd(tjbbYxzbHsZd);
				bbsbTjbbDao.deleteTjbbYxzbHsZdZxmByID(Integer.parseInt(TjbbYxzbHsZd_id));
			}else{
				TjbbYxzbHsZd_id = bbsbTjbbDao.insertTjbbYxzbHsZd(tjbbYxzbHsZd)+"";
			}

			//子表数据
			String zb1[]= request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
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
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
			if(zb1!=null){
				for(int i=0; i<zb1.length; i++){
					TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm();
					tjbbYxzbHsZdZxm.setXmid(Integer.parseInt(TjbbYxzbHsZd_id));
					tjbbYxzbHsZdZxm.setZb1(zb1[i]);
					tjbbYxzbHsZdZxm.setZb2(zb2[i]);
					tjbbYxzbHsZdZxm.setZb3(zb3[i]);
					tjbbYxzbHsZdZxm.setZb4(zb4[i]);
					tjbbYxzbHsZdZxm.setZb5(zb5[i]);
					tjbbYxzbHsZdZxm.setZb6(zb6[i]);
					tjbbYxzbHsZdZxm.setZb7(zb7[i]);
					tjbbYxzbHsZdZxm.setZb8(zb8[i]);
					tjbbYxzbHsZdZxm.setZb9(zb9[i]);
					tjbbYxzbHsZdZxm.setZb10(zb10[i]);
					tjbbYxzbHsZdZxm.setZb11(zb11[i]);
					TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
				}
				bbsbTjbbDao.insertTjbbYxzbHsZdZxm(TjbbYxzbHsZdZxmList);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHsZd&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
			
		}
	}
	/*
	 *  进入保存页面（多个站点）
	 * 
	 */
	protected void TjbbYxzbHsZdSaveAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String tjzt = request.getParameter("tjzt");//1:提交2：保存草稿箱
			//主表数据
			String bt[]= request.getParameterValues("bt");
			String zdmc[]= request.getParameterValues("zdmc");
			String lxr[] = request.getParameterValues("lxr");
			String lxrdh[] = request.getParameterValues("lxrdh");
			//子表数据
			String zb1[]= request.getParameterValues("zb1"); //用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
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
			if(bt!=null){
				for(int i=0; i<bt.length; i++){
					TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
					tjbbYxzbHsZd.setBt(bt[i]);
					tjbbYxzbHsZd.setCzr(UserInfo.getName());
					tjbbYxzbHsZd.setCzrID(UserInfo.getUsername());
					tjbbYxzbHsZd.setCzrdw(UserInfo.getCompany());
					tjbbYxzbHsZd.setLxr(lxr[i]);
					tjbbYxzbHsZd.setLxrdh(lxrdh[i]);
					tjbbYxzbHsZd.setCzsj(data1);
					tjbbYxzbHsZd.setYear(Integer.parseInt(year));
					tjbbYxzbHsZd.setMonth(Integer.parseInt(month));
					tjbbYxzbHsZd.setZdmc(zdmc[i]);
					tjbbYxzbHsZd.setTjzt(tjzt);
					BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
					//新增多个表格
					int id = bbsbTjbbDao.insertTjbbYxzbHsZd(tjbbYxzbHsZd);
					
					ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
					for(int j=0; j<12; j++){
						TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm();
						tjbbYxzbHsZdZxm.setXmid(id);
						tjbbYxzbHsZdZxm.setZb1(zb1[i*12+j]);
						tjbbYxzbHsZdZxm.setZb2(zb2[i*12+j]);
						tjbbYxzbHsZdZxm.setZb3(zb3[i*12+j]);
						tjbbYxzbHsZdZxm.setZb4(zb4[i*12+j]);
						tjbbYxzbHsZdZxm.setZb5(zb5[i*12+j]);
						tjbbYxzbHsZdZxm.setZb6(zb6[i*12+j]);
						tjbbYxzbHsZdZxm.setZb7(zb7[i*12+j]);
						tjbbYxzbHsZdZxm.setZb8(zb8[i*12+j]);
						tjbbYxzbHsZdZxm.setZb9(zb9[i*12+j]);
						tjbbYxzbHsZdZxm.setZb10(zb10[i*12+j]);
						tjbbYxzbHsZdZxm.setZb11(zb11[i*12+j]);
						TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
					}
					bbsbTjbbDao.insertTjbbYxzbHsZdZxm(TjbbYxzbHsZdZxmList);
				}
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHsZd&flag=1&menuname="+UserInfo.getCompany()).forward(request,
					response);
		}
	}
	 /*
	 *  进入查看页面
	 * 
	 */
	protected void TjbbYxzbHsZdShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
		ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			tjbbYxzbHsZd = bbsbTjbbDao.queryTjbbYxzbHsZdByID(ID);
			TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("tjbbYxzbHsZd", tjbbYxzbHsZd);
		request.setAttribute("TjbbYxzbHsZdZxmList", TjbbYxzbHsZdZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdShow.jsp").forward(request,
				response);
		
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void TjbbYxzbHsZdDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		bbsbTjbbDao.deleteTjbbYxzbHsZdById(Integer.parseInt(id));
		//删除子项目记录
		bbsbTjbbDao.deleteTjbbYxzbHsZdZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHsZd&flag=1&menuname="+UserInfo.getCompany());
		rd.forward(request, response);
		return;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getTjbbYxzbHsZdExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String TjbbYxzbHsZd_id = request.getParameter("TjbbYxzbHsZd_id");
		TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
		ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(TjbbYxzbHsZd_id)&&TjbbYxzbHsZd_id!=null){
			int ID = Integer.parseInt(TjbbYxzbHsZd_id);
			tjbbYxzbHsZd = bbsbTjbbDao.queryTjbbYxzbHsZdByID(ID);
			TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(ID);
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("客运出站运输量统计表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				format6.setWrap(true);//换行
				
				//设置表头
				Label label = new Label(0,0,tjbbYxzbHsZd.getYear()+"年"+tjbbYxzbHsZd.getMonth()+"月"+tjbbYxzbHsZd.getBt(),format1);
				wsheet.addCell(label);
				label = new Label(0,1,"填报单位："+tjbbYxzbHsZd.getCzrdw(),format2);
				wsheet.addCell(label);

				label = new Label(0,2,"站点名称",format6);
				wsheet.addCell(label);
				label = new Label(1,2,"月份",format6);
				wsheet.addCell(label);
				label = new Label(2,2,"进港签证总量（吨）",format6);
				wsheet.addCell(label);
				label = new Label(7,2,"出港签证总量（吨）",format6);
				wsheet.addCell(label);
				label = new Label(12,2,"海事签证涉及港口范围",format6);
				wsheet.addCell(label);
				
				label = new Label(2,3,"总计",format6);
				wsheet.addCell(label);
				label = new Label(3,3,"1000吨(含)以下船舶",format6);
				wsheet.addCell(label);
				label = new Label(4,3,"1000—5000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(5,3,"5000—10000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(6,3,"10000吨以上船舶",format6);
				wsheet.addCell(label);
				label = new Label(7,3,"总计",format6);
				wsheet.addCell(label);
				label = new Label(8,3,"1000吨(含)以下船舶",format6);
				wsheet.addCell(label);
				label = new Label(9,3,"1000—5000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(10,3,"5000—10000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(11,3,"10000吨以上船舶",format6);
				wsheet.addCell(label);
				
				
				label = new Label(0,4,tjbbYxzbHsZd.getZdmc(),format6);
				wsheet.addCell(label);
				label = new Label(1,4,"1",format6);
				wsheet.addCell(label);
				
				label = new Label(1,5,"2",format6);
				wsheet.addCell(label);
				
				label = new Label(1,6,"3",format6);
				wsheet.addCell(label);
				
				label = new Label(1,7,"4",format6);
				wsheet.addCell(label);
				
				label = new Label(1,8,"5",format6);
				wsheet.addCell(label);
				
				label = new Label(1,9,"6",format6);
				wsheet.addCell(label);
				
				label = new Label(1,10,"7",format6);
				wsheet.addCell(label);
				
				label = new Label(1,11,"8",format6);
				wsheet.addCell(label);

				label = new Label(1,12,"9",format6);
				wsheet.addCell(label);
				
				label = new Label(1,13,"10",format6);
				wsheet.addCell(label);
				
				label = new Label(1,14,"11",format6);
				wsheet.addCell(label);
				
				label = new Label(1,15,"12",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<TjbbYxzbHsZdZxmList.size(); i++){
					label = new Label(2,i+4,TjbbYxzbHsZdZxmList.get(i).getZb1(),format6);
					wsheet.addCell(label);
					label = new Label(3,i+4,TjbbYxzbHsZdZxmList.get(i).getZb2(),format6);
					wsheet.addCell(label);
					label = new Label(4,i+4,TjbbYxzbHsZdZxmList.get(i).getZb3(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+4,TjbbYxzbHsZdZxmList.get(i).getZb4(),format6);
					wsheet.addCell(label);
					label = new Label(6,i+4,TjbbYxzbHsZdZxmList.get(i).getZb5(),format6);
					wsheet.addCell(label);
					label = new Label(7,i+4,TjbbYxzbHsZdZxmList.get(i).getZb6(),format6);
					wsheet.addCell(label);
					label = new Label(8,i+4,TjbbYxzbHsZdZxmList.get(i).getZb7(),format6);
					wsheet.addCell(label);
					label = new Label(9,i+4,TjbbYxzbHsZdZxmList.get(i).getZb8(),format6);
					wsheet.addCell(label);
					label = new Label(10,i+4,TjbbYxzbHsZdZxmList.get(i).getZb9(),format6);
					wsheet.addCell(label);
					label = new Label(11,i+4,TjbbYxzbHsZdZxmList.get(i).getZb10(),format6);
					wsheet.addCell(label);
					label = new Label(12,i+4,TjbbYxzbHsZdZxmList.get(i).getZb11(),format6);
					wsheet.addCell(label);
				}
				label = new Label(0,16,"注1：各市请在12月2日前上报2013年1至10月市县级月度运输量统计推算补充数据，11-12月数据可延迟在1月5日之前上报。",format2);
				wsheet.addCell(label);
				label = new Label(0,17,"注2：填报范围为全市地方海事签证情况，填写2014年各月情况。",format2);
				wsheet.addCell(label);
				label = new Label(0,18,"注3：签证船舶载重总量分吨位情况，按总吨吨位分类填写。",format2);
				wsheet.addCell(label);
				label = new Label(0,19,"联系人：",format3);
				wsheet.addCell(label);
				label = new Label(1,19,tjbbYxzbHsZd.getLxr(),format2);
				wsheet.addCell(label);
				label = new Label(2,19,"联系电话：",format3);
				wsheet.addCell(label);
				label = new Label(3,19,tjbbYxzbHsZd.getLxrdh(),format2);
				wsheet.addCell(label);
				label = new Label(4,19,"上报日期：",format3);
				wsheet.addCell(label);
				label = new Label(5,19,tjbbYxzbHsZd.getCzsj().toString().substring(0,10),format2);
				wsheet.addCell(label);
				
				//合并单元格             （列、行、列、行）
				wsheet.mergeCells(0, 0, 12, 0);
				wsheet.mergeCells(0, 2, 0, 3);
				wsheet.mergeCells(1, 2, 1, 3);
				wsheet.mergeCells(2, 2, 6, 2);
				wsheet.mergeCells(7, 2, 11, 2);
				wsheet.mergeCells(12, 2, 12, 3);
				wsheet.mergeCells(0, 4, 0, 15);
				//设置列宽
				wsheet.setColumnView(0, 15);
				wsheet.setColumnView(1, 15);
				wsheet.setColumnView(2, 15);
				wsheet.setColumnView(3, 15);
				wsheet.setColumnView(4, 15);
				wsheet.setColumnView(5, 15);
				wsheet.setColumnView(6, 15);
				wsheet.setColumnView(7, 15);
				wsheet.setColumnView(8, 15);
				wsheet.setColumnView(9, 15);
				wsheet.setColumnView(10, 15);
				wsheet.setColumnView(11, 15);
				wsheet.setColumnView(12, 15);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				wsheet.setRowView(1, 400, false);
				for(int k=0; k<TjbbYxzbHsZdZxmList.size()+4; k++){
					wsheet.setRowView(k, 400, false);
				}
				wsheet.setRowView(3, 800, false);
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readTjbbYxzbHsZdExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//导入EXCEL位置
			String sheetNum = request.getParameter("sheetNum");
			//新增或者修改
			String TjbbYxzbHsZd_id = request.getParameter("TjbbYxzbHsZd_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String zdmc= request.getParameter("zdmc");
			String bt= request.getParameter("bt");
			String lxr= request.getParameter("lxr");
			String lxrdh= request.getParameter("lxrdh");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			}  
			TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
			tjbbYxzbHsZd.setBt(bt);
			tjbbYxzbHsZd.setYear(Integer.parseInt(year));
			tjbbYxzbHsZd.setMonth(Integer.parseInt(month));
			tjbbYxzbHsZd.setZdmc(zdmc);
			tjbbYxzbHsZd.setLxr(lxr);
			tjbbYxzbHsZd.setLxrdh(lxrdh);
			if(!"".equals(TjbbYxzbHsZd_id)&&TjbbYxzbHsZd_id!=null){
				tjbbYxzbHsZd.setId(Integer.parseInt(TjbbYxzbHsZd_id));
			}
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			int StNm = 0;
			if(!"".equals(sheetNum)&&sheetNum!=null){
				StNm = Integer.parseInt(sheetNum)-1;
			}
			if(sheet!=null&&sheet.length>0){
				int r = sheet[StNm].getRows();//行数
				int c = sheet[StNm].getColumns();//列数
				if(r<17||c<8){
					result = "请检查文件是否正确";
				}else{
					//找到指标甲
					int num=0;
					for(int i=0; i<sheet[StNm].getRows(); i++){
						if("1".equals(sheet[StNm].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					tjbbYxzbHsZd.setZdmc(sheet[StNm].getCell(0,9).getContents());
					tjbbYxzbHsZd.setLxr(sheet[StNm].getCell(1,4).getContents());
					tjbbYxzbHsZd.setLxrdh(sheet[StNm].getCell(3,4).getContents());
					for(int j=0; j<12; j++){
						TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm();
						tjbbYxzbHsZdZxm.setZb1(sheet[StNm].getCell(2,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb2(sheet[StNm].getCell(3,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb3(sheet[StNm].getCell(4,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb4(sheet[StNm].getCell(5,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb5(sheet[StNm].getCell(6,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb6(sheet[StNm].getCell(7,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb7(sheet[StNm].getCell(8,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb8(sheet[StNm].getCell(9,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb9(sheet[StNm].getCell(10,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb10(sheet[StNm].getCell(11,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb11(sheet[StNm].getCell(12,num+j).getContents());
						TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
					}
				}
				//最后关闭资源，释放内存    
				wb.close();
			}
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("tjbbYxzbHsZd", tjbbYxzbHsZd);
			request.setAttribute("TjbbYxzbHsZdZxmList", TjbbYxzbHsZdZxmList);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdEdit.jsp").forward(request,
					response);
		}
	}

	/*读取Excel文件的内容   
	 * @param file  待读取的文件   
	 * @return   
	 */ 
	public void readTjbbYxzbHsZdExcelAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//导入EXCEL位置
			String sheetNum1 = request.getParameter("sheetNum1");
			String sheetNum2 = request.getParameter("sheetNum2");
			//新增或者修改
//			String TjbbYxzbHsZd_id = request.getParameter("TjbbYxzbHsZd_id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
//			String zdmc= request.getParameter("zdmc");
			String bt= request.getParameter("bt");
//			String lxr= request.getParameter("lxr");
//			String lxrdh= request.getParameter("lxrdh");
			String URL = request.getParameter("URL");
			String FullFilePath = request.getRealPath("/UserFile") + "/"+URL;
			File file = new File(FullFilePath);  
			Workbook wb = null;    
			try {    
				//构造Workbook（工作薄）对象    
				wb=Workbook.getWorkbook(file);    
			} catch (BiffException e) {    
				e.printStackTrace();    
			} catch (IOException e) {    
				e.printStackTrace();    
			} 
			//获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了    
			Sheet[] sheet = wb.getSheets();
			int StNm1 = 0;
			int StNm2 = 0;
			if(!"".equals(sheetNum1)&&sheetNum1!=null){
				StNm1 = Integer.parseInt(sheetNum1)-1;
			} 
			if(!"".equals(sheetNum2)&&sheetNum2!=null){
				StNm2 = Integer.parseInt(sheetNum2)-1;
			}
			int all=0;
			for(int k=StNm1;k<=StNm2&&k<sheet.length;k++){
				TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
				ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
				tjbbYxzbHsZd.setBt(bt);
				tjbbYxzbHsZd.setYear(Integer.parseInt(year));
				tjbbYxzbHsZd.setMonth(Integer.parseInt(month));
				if(sheet!=null&&sheet.length>0){
					//找到指标甲
					int num=0;
					for(int i=0; i<sheet[k].getRows(); i++){
						if("1".equals(sheet[k].getCell(1,i).getContents())){
							num=i;
							break;
						}
					}
					tjbbYxzbHsZd.setZdmc(sheet[k].getCell(0,9).getContents());
					tjbbYxzbHsZd.setLxr(sheet[k].getCell(1,4).getContents());
					tjbbYxzbHsZd.setLxrdh(sheet[k].getCell(3,4).getContents());
					for(int j=0; j<12; j++){
						TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm();
						tjbbYxzbHsZdZxm.setZb1(sheet[k].getCell(2,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb2(sheet[k].getCell(3,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb3(sheet[k].getCell(4,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb4(sheet[k].getCell(5,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb5(sheet[k].getCell(6,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb6(sheet[k].getCell(7,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb7(sheet[k].getCell(8,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb8(sheet[k].getCell(9,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb9(sheet[k].getCell(10,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb10(sheet[k].getCell(11,num+j).getContents());
						tjbbYxzbHsZdZxm.setZb11(sheet[k].getCell(12,num+j).getContents());
						TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
					}
				}
				all =all+1;
				request.setAttribute("tjbbYxzbHsZd"+all, tjbbYxzbHsZd);
				request.setAttribute("TjbbYxzbHsZdZxmList"+all, TjbbYxzbHsZdZxmList);
			}
			//最后关闭资源，释放内存    
			wb.close();
			//删除临时文件
			if (file.isFile() && file.exists()) {  
				file.delete();  
			}
			request.setAttribute("all", all);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdEditAll.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入驳回
	 * 
	 */
	protected void TjbbYxzbHsZdReturn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//新增或者修改
			String id = request.getParameter("id");
			String company = request.getParameter("company");
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			//驳回人员的用户名
			String bhry = request.getParameter("bhry");
			ContentZzxx Zzxx = new ContentZzxx();
			ContentZzxxDao contentZzxxDao = new ContentZzxxDao();
			Zzxx = contentZzxxDao.queryZzxxByUserName(bhry);
			if(!"".equals(id)&&id!=null){
				bbsbTjbbDao.updateTjbbYxzbHsZdRet(id);
				String dxnr = UserInfo.getName()+"：您有一份海事站点签证数据被驳回，请及时查看";
				if(Zzxx.getPhone()!=null&&!"".equals(Zzxx.getPhone())){
					//发送短信
					messagePlatForm.submitShortMessage(Zzxx.getPhone(),dxnr);
				}
				MyMessage myMessage = new MyMessage();
				myMessage.setDxnr(dxnr);
				myMessage.setJsrID(Zzxx.getUsername());
				myMessage.setFsr(UserInfo.getName());
				myMessage.setJssj(data1);
				myMessage.setCybz("未查阅");
				MessageDao messageDao = new MessageDao();
				messageDao.insertMyMessageOne(myMessage);
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHsZd&flag=1&menuname="+company).forward(request,
					response);
			
		}
	}
	/*
	 *  可视化图形——曲线图
	 * 
	 */
	protected void getTjbbQxtHsZd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				xList =xList+"'"+xAxis[i]+"',";
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("7".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb7())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb7()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("8".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb8())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb8()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("9".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb9())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb9()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("10".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb10())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb10()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbQxt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  可视化图形——柱状图
	 * 
	 */
	protected void getTjbbZztHsZd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				xList =xList+"'"+xAxis[i]+"',";
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("7".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb7())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb7()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("8".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb8())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb8()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("9".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb9())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb9()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("10".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb10())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb10()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbZzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——饼状图
	 * 
	 */
	protected void getTjbbBztHsZd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			ArrayList<String> xList = new ArrayList<String>();
			for(int i=0; i<xAxis.length; i++){
				xList.add("'"+xAxis[i]+"月'");
			}
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			String yname = "";
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZdZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(Integer.parseInt(id));
			}
			if(yAxis.length==1){
				if("1".equals(yAxis[0])){
					yname = "总计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb1())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb1());
						}
					}
				}else if("2".equals(yAxis[0])){
					yname = "1000吨(含)以下船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb2())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb2());
						}
					}
				}else if("3".equals(yAxis[0])){
					yname = "1000—5000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb3())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb3());
						}
					}
				}else if("4".equals(yAxis[0])){
					yname = "5000—10000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb4())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb4());
						}
					}
				}else if("5".equals(yAxis[0])){
					yname = "10000吨以上船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb5())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb5());
						}
					}
				}else if("6".equals(yAxis[0])){
					yname = "总计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb6())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb6());
						}
					}
				}else if("7".equals(yAxis[0])){
					yname = "1000吨(含)以下船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb7())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb7());
						}
					}
				}else if("8".equals(yAxis[0])){
					yname = "1000—5000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb8())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb8());
						}
					}
				}else if("9".equals(yAxis[0])){
					yname = "5000—10000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb9())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb9());
						}
					}
				}else if("10".equals(yAxis[0])){
					yname = "10000吨以上船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb10())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdZxmList.get(xAxisInt.get(k)).getZb10());
						}
					}
				}
				
			}
			request.setAttribute("yname", yname);
			request.setAttribute("xList", xList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbBzt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  海事局站点汇总表——列表
	 * 
	 */
	protected void getTjbbYxzbHsZdhz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//查询列表
			ArrayList<TjbbYxzbHsZdhz> TjbbYxzbHsZdhzList = new ArrayList<TjbbYxzbHsZdhz>();
			//分页;
			String page_no = request.getParameter("page_no");
			int pageNo = 1;
			if(!"".equals(page_no)&&page_no!=null){
				pageNo = Integer.parseInt(page_no);
			}
			int pageSize = 10;
			int countAll = 0;
			int begin = (pageNo - 1) * pageSize;
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			String srbt = request.getParameter("srbt");
			String cxyear = request.getParameter("cxyear");
			String cxmonth = request.getParameter("cxmonth");
//			String cxcompany = request.getParameter("cxcompany");
			//若为1：则从菜单进入
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				//通过标题查询
				srbt="";
				cxyear = "";
				cxmonth = "";
//				cxcompany = "";
			}
			countAll = bbsbTjbbDao.queryTjbbYxzbHsZdhzListByBtCount(srbt);
			TjbbYxzbHsZdhzList = bbsbTjbbDao.queryTjbbYxzbHsZdhzListByBt(srbt, begin, pageSize);
			request.setAttribute("TjbbYxzbHsZdhzList", TjbbYxzbHsZdhzList);
			request.setAttribute("srbt", srbt);
			request.setAttribute("cxyear", cxyear);
			request.setAttribute("cxmonth", cxmonth);
//			request.setAttribute("cxcompany", cxcompany);
			request.setAttribute("result", result);
			PaginationTool pt = new PaginationTool(pageNo, countAll, pageSize);
			request.setAttribute("pt", pt);
			request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdhzList.jsp").forward(request,
					response);
		}
	}
	/*
	 *  进入保存页面
	 * 
	 */
	protected void TjbbYxzbHsZdhzSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = request.getParameter("result");
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//获取当前时间
			java.util.Date  date=new java.util.Date();
			java.sql.Timestamp  data1=new java.sql.Timestamp(date.getTime());
			//生成新数据或者更新旧数据
			String TjbbYxzbHsZdhz_id = request.getParameter("TjbbYxzbHsZdhz_id");
			String yearStr = request.getParameter("year");
			String monthStr = request.getParameter("month");
			TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(yearStr)&&yearStr!=null&&!"".equals(monthStr)&&monthStr!=null){
				int year = Integer.parseInt(yearStr);
				int month = Integer.parseInt(monthStr);
				tjbbYxzbHsZdhz.setCzr(UserInfo.getName());
				tjbbYxzbHsZdhz.setCzrID(UserInfo.getUsername());
				tjbbYxzbHsZdhz.setCzrdw(UserInfo.getCompany());
				tjbbYxzbHsZdhz.setCzsj(data1);
				tjbbYxzbHsZdhz.setYear(year);
				tjbbYxzbHsZdhz.setMonth(month);
				//根据时间查询符合条件的主表ID
				ArrayList<Integer>idList = new ArrayList<Integer>();
				idList = bbsbTjbbDao.queryHsZdHzIdListByYM(UserInfo.getCompany(),year, month);
				tjbbYxzbHsZdhz.setZdgs(idList.size());
				//汇总计算
				ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList1 = new ArrayList<TjbbYxzbHsZdZxm>();
				//需汇总文件不为空
				if(idList.size()!=0){
					TjbbYxzbHsZdZxmList1 =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(idList.get(0));
					ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList2 = new ArrayList<TjbbYxzbHsZdZxm>();
					for(int i=1; i<idList.size(); i++){
						TjbbYxzbHsZdZxmList2 =  bbsbTjbbDao.queryTjbbYxzbHsZdZxmByID(idList.get(i));
						//将两个站点内容进行相加（只累加到所需汇总的月份）
						TjbbYxzbHsZdZxmList1 = AddTwoList(TjbbYxzbHsZdZxmList1,TjbbYxzbHsZdZxmList2,month);
					}
				}else{
					for(int k=0; k<12; k++){
						TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm(); 
						tjbbYxzbHsZdZxm.setZb1("0");
						tjbbYxzbHsZdZxm.setZb2("0");
						tjbbYxzbHsZdZxm.setZb3("0");
						tjbbYxzbHsZdZxm.setZb4("0");
						tjbbYxzbHsZdZxm.setZb5("0");
						tjbbYxzbHsZdZxm.setZb6("0");
						tjbbYxzbHsZdZxm.setZb7("0");
						tjbbYxzbHsZdZxm.setZb8("0");
						tjbbYxzbHsZdZxm.setZb9("0");
						tjbbYxzbHsZdZxm.setZb10("0");
						tjbbYxzbHsZdZxm.setZb11("0");
						TjbbYxzbHsZdZxmList1.add(tjbbYxzbHsZdZxm);
					}
				}
				//新增或修改主表
				int xmid = 0;
				if(!"".equals(TjbbYxzbHsZdhz_id)&&TjbbYxzbHsZdhz_id!=null){
					//更新
					tjbbYxzbHsZdhz.setId(Integer.parseInt(TjbbYxzbHsZdhz_id));
					bbsbTjbbDao.updateTjbbYxzbHsZdhz(tjbbYxzbHsZdhz);
					//删除原有记录
					bbsbTjbbDao.deleteTjbbYxzbHsZdhzZxmByID(Integer.parseInt(TjbbYxzbHsZdhz_id));
					xmid = Integer.parseInt(TjbbYxzbHsZdhz_id);
				}else{
					//新增最新记录
					xmid = bbsbTjbbDao.insertTjbbYxzbHsZdhz(tjbbYxzbHsZdhz);
				}
				for(int j=0; j<TjbbYxzbHsZdZxmList1.size(); j++){
					TjbbYxzbHsZdZxmList1.get(j).setXmid(xmid);
				}
				bbsbTjbbDao.insertTjbbYxzbHsZdhzZxm(TjbbYxzbHsZdZxmList1);
			}else{
				result = "日期错误，生成失败";
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHsZdhz&flag=1").forward(request,
					response);
			
		}
	}

	 /*
	 *  进入查看页面
	 * 
	 */
	protected void TjbbYxzbHsZdhzShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String id = request.getParameter("id");
		String result = request.getParameter("result");
		TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
		ArrayList<TjbbYxzbHsZdhzZxm> TjbbYxzbHsZdhzZxmList = new ArrayList<TjbbYxzbHsZdhzZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(id)&&id!=null){
			int ID = Integer.parseInt(id);
			tjbbYxzbHsZdhz = bbsbTjbbDao.queryTjbbYxzbHsZdhzByID(ID);
			TjbbYxzbHsZdhzZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdhzZxmByID(Integer.parseInt(id));
		}
		request.setAttribute("tjbbYxzbHsZdhz", tjbbYxzbHsZdhz);
		request.setAttribute("TjbbYxzbHsZdhzZxmList", TjbbYxzbHsZdhzZxmList);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jsp/StatisticalReports/TjbbYxzbHsZdhzShow.jsp").forward(request,
				response);
		return;
	}
	/*
	 *  删除
	 * 
	 */
	protected void TjbbYxzbHsZdhzDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取删除项的ID号和菜单项ID
		String id = request.getParameter("id");
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		bbsbTjbbDao.deleteTjbbYxzbHsZdhzById(Integer.parseInt(id));
		//删除子项目记录
		bbsbTjbbDao.deleteTjbbYxzbHsZdhzZxmByID(Integer.parseInt(id));
		RequestDispatcher rd = request.getRequestDispatcher("BbsbTjbbServlet?action=getTjbbYxzbHsZdhz&flag=1");
		rd.forward(request, response);
		return;
	}
	
	private ArrayList<TjbbYxzbHsZdZxm> AddTwoList(ArrayList<TjbbYxzbHsZdZxm> List1,ArrayList<TjbbYxzbHsZdZxm> List2,int num){
		BigDecimal nr1 = null;
		BigDecimal nr2 = null;
		for(int i=0;i<num;i++){
			if("".equals(List1.get(i).getZb1().trim()))List1.get(i).setZb1("0");
			if("".equals(List1.get(i).getZb2().trim()))List1.get(i).setZb2("0");
			if("".equals(List1.get(i).getZb3().trim()))List1.get(i).setZb3("0");
			if("".equals(List1.get(i).getZb4().trim()))List1.get(i).setZb4("0");
			if("".equals(List1.get(i).getZb5().trim()))List1.get(i).setZb5("0");
			if("".equals(List1.get(i).getZb6().trim()))List1.get(i).setZb6("0");
			if("".equals(List1.get(i).getZb7().trim()))List1.get(i).setZb7("0");
			if("".equals(List1.get(i).getZb8().trim()))List1.get(i).setZb8("0");
			if("".equals(List1.get(i).getZb9().trim()))List1.get(i).setZb9("0");
			if("".equals(List1.get(i).getZb10().trim()))List1.get(i).setZb10("0");
			if("".equals(List2.get(i).getZb1().trim()))List2.get(i).setZb1("0");
			if("".equals(List2.get(i).getZb2().trim()))List2.get(i).setZb2("0");
			if("".equals(List2.get(i).getZb3().trim()))List2.get(i).setZb3("0");
			if("".equals(List2.get(i).getZb4().trim()))List2.get(i).setZb4("0");
			if("".equals(List2.get(i).getZb5().trim()))List2.get(i).setZb5("0");
			if("".equals(List2.get(i).getZb6().trim()))List2.get(i).setZb6("0");
			if("".equals(List2.get(i).getZb7().trim()))List2.get(i).setZb7("0");
			if("".equals(List2.get(i).getZb8().trim()))List2.get(i).setZb8("0");
			if("".equals(List2.get(i).getZb9().trim()))List2.get(i).setZb9("0");
			if("".equals(List2.get(i).getZb10().trim()))List2.get(i).setZb10("0");
//			System.out.print(List1.get(i).getZb1());
			nr1 = new BigDecimal(List1.get(i).getZb1().trim());
//			System.out.println("*"+nr1);
//			System.out.print(List2.get(i).getZb1()+"@");
			nr2 = new BigDecimal(List2.get(i).getZb1().trim());
//			System.out.println("**"+nr2);
			List1.get(i).setZb1(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb2().trim());
			nr2 = new BigDecimal(List2.get(i).getZb2().trim());
			List1.get(i).setZb2(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb3().trim());
			nr2 = new BigDecimal(List2.get(i).getZb3().trim());
			List1.get(i).setZb3(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb4().trim());
			nr2 = new BigDecimal(List2.get(i).getZb4().trim());
			List1.get(i).setZb4(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb5().trim());
			nr2 = new BigDecimal(List2.get(i).getZb5().trim());
			List1.get(i).setZb5(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb6().trim());
			nr2 = new BigDecimal(List2.get(i).getZb6().trim());
			List1.get(i).setZb6(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb7().trim());
			nr2 = new BigDecimal(List2.get(i).getZb7().trim());
			List1.get(i).setZb7(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb8().trim());
			nr2 = new BigDecimal(List2.get(i).getZb8().trim());
			List1.get(i).setZb8(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb9().trim());
			nr2 = new BigDecimal(List2.get(i).getZb9().trim());
			List1.get(i).setZb9(nr1.add(nr2).toString());
			nr1 = new BigDecimal(List1.get(i).getZb10().trim());
			nr2 = new BigDecimal(List2.get(i).getZb10().trim());
			List1.get(i).setZb10(nr1.add(nr2).toString());
		}
		return List1;
	}
	/**
	* 生成信息的XLS
	*
	*/
	public void getTjbbYxzbHsZdhzExcel(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//查询
		String TjbbYxzbHsZdhz_id = request.getParameter("TjbbYxzbHsZdhz_id");
		TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
		ArrayList<TjbbYxzbHsZdhzZxm> TjbbYxzbHsZdhzZxmList = new ArrayList<TjbbYxzbHsZdhzZxm>();
		BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
		if(!"".equals(TjbbYxzbHsZdhz_id)&&TjbbYxzbHsZdhz_id!=null){
			int ID = Integer.parseInt(TjbbYxzbHsZdhz_id);
			tjbbYxzbHsZdhz = bbsbTjbbDao.queryTjbbYxzbHsZdhzByID(ID);
			TjbbYxzbHsZdhzZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdhzZxmByID(ID);
			//生成xls
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			String filename =formatter.format(new java.util.Date()) + ".xls";
			//1.设定好response的相关属性：
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1"));
			try{
				//2.取到response的OutputStream实例，并用这个实例化一个WritableWorkbook对象
				OutputStream os = response.getOutputStream();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				
				//新建一张表
				WritableSheet wsheet = wwb.createSheet("海事站点签证数据汇总表",1);
				// 设置字体为宋体,18号字,加粗
				WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				//水平居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居下
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format2 = new WritableCellFormat(font2);
				format2.setAlignment(jxl.format.Alignment.LEFT);
				format2.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format3 = new WritableCellFormat(font3);
				format3.setAlignment(jxl.format.Alignment.RIGHT);
				format3.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				
				WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format4 = new WritableCellFormat(font4);
				format4.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);
				//设置边框
				format4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font5 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format5 = new WritableCellFormat(font5);
				format5.setAlignment(jxl.format.Alignment.LEFT);
				format5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				
				WritableFont font6 = new WritableFont(WritableFont.createFont("宋体"), 12);
				WritableCellFormat format6 = new WritableCellFormat(font6);
				format6.setAlignment(jxl.format.Alignment.CENTRE);
				format6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				format6.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
				format6.setWrap(true);//换行
				
				//设置表头
				Label label = new Label(0,0,tjbbYxzbHsZdhz.getYear()+"年"+tjbbYxzbHsZdhz.getMonth()+"月海事站点签证数据汇总表",format1);
				wsheet.addCell(label);

				label = new Label(0,1,"区划名称",format6);
				wsheet.addCell(label);
				label = new Label(1,1,"月份",format6);
				wsheet.addCell(label);
				label = new Label(2,1,"海事签证站点个数（个）",format6);
				wsheet.addCell(label);
				label = new Label(3,1,"进港签证总量（吨）",format6);
				wsheet.addCell(label);
				label = new Label(8,1,"出港签证总量（吨）",format6);
				wsheet.addCell(label);
				label = new Label(13,1,"海事签证涉及港口范围",format6);
				wsheet.addCell(label);
				
				label = new Label(3,2,"总计",format6);
				wsheet.addCell(label);
				label = new Label(4,2,"1000吨(含)以下船舶",format6);
				wsheet.addCell(label);
				label = new Label(5,2,"1000—5000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(6,2,"5000—10000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(7,2,"10000吨以上船舶",format6);
				wsheet.addCell(label);
				label = new Label(8,2,"总计",format6);
				wsheet.addCell(label);
				label = new Label(9,2,"1000吨(含)以下船舶",format6);
				wsheet.addCell(label);
				label = new Label(10,2,"1000—5000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(11,2,"5000—10000吨(含)船舶",format6);
				wsheet.addCell(label);
				label = new Label(12,2,"10000吨以上船舶",format6);
				wsheet.addCell(label);
				
				
				label = new Label(0,3,"南通市",format6);
				wsheet.addCell(label);
				
				for(int i=0; i<TjbbYxzbHsZdhzZxmList.size(); i++){
					label = new Label(1,i+3,(i+1)+"",format6);
					wsheet.addCell(label);
					label = new Label(2,i+3,tjbbYxzbHsZdhz.getZdgs()+"",format6);
					wsheet.addCell(label);
					label = new Label(3,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb1(),format6);
					wsheet.addCell(label);
					label = new Label(4,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb2(),format6);
					wsheet.addCell(label);
					label = new Label(5,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb3(),format6);
					wsheet.addCell(label);
					label = new Label(6,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb4(),format6);
					wsheet.addCell(label);
					label = new Label(7,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb5(),format6);
					wsheet.addCell(label);
					label = new Label(8,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb6(),format6);
					wsheet.addCell(label);
					label = new Label(9,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb7(),format6);
					wsheet.addCell(label);
					label = new Label(10,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb8(),format6);
					wsheet.addCell(label);
					label = new Label(11,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb9(),format6);
					wsheet.addCell(label);
					label = new Label(12,i+3,TjbbYxzbHsZdhzZxmList.get(i).getZb10(),format6);
					wsheet.addCell(label);
					label = new Label(13,i+3,"南通市",format6);
					wsheet.addCell(label);
				}
				label = new Label(0,15,"注1：各市请在12月2日前上报2013年1至10月市县级月度运输量统计推算补充数据，11-12月数据可延迟在1月5日之前上报。",format2);
				wsheet.addCell(label);
				label = new Label(0,16,"注2：填报范围为全市地方海事签证情况，填写2014年各月情况。",format2);
				wsheet.addCell(label);
				label = new Label(0,17,"注3：签证船舶载重总量分吨位情况，按总吨吨位分类填写。",format2);
				wsheet.addCell(label);
				
				//合并单元格             （列、行、列、行）
				wsheet.mergeCells(0, 0, 13, 0);
				wsheet.mergeCells(0, 1, 0, 2);
				wsheet.mergeCells(1, 1, 1, 2);
				wsheet.mergeCells(2, 1, 2, 2);
				wsheet.mergeCells(3, 1, 7, 1);
				wsheet.mergeCells(8, 1, 12, 1);
				wsheet.mergeCells(13, 1, 13, 2);
				wsheet.mergeCells(0, 3, 0, 14);
				//设置列宽
				wsheet.setColumnView(0, 15);
				wsheet.setColumnView(1, 15);
				wsheet.setColumnView(2, 15);
				wsheet.setColumnView(3, 15);
				wsheet.setColumnView(4, 15);
				wsheet.setColumnView(5, 15);
				wsheet.setColumnView(6, 15);
				wsheet.setColumnView(7, 15);
				wsheet.setColumnView(8, 15);
				wsheet.setColumnView(9, 15);
				wsheet.setColumnView(10, 15);
				wsheet.setColumnView(11, 15);
				wsheet.setColumnView(12, 15);
				wsheet.setColumnView(13, 15);
				//设置行宽
				wsheet.setRowView(0, 680, false);
				for(int k=1; k<TjbbYxzbHsZdhzZxmList.size()+4; k++){
					wsheet.setRowView(k, 400, false);
				}
				wsheet.setRowView(2, 800, false);
				
		        wwb.write();
		        wwb.close();
		        os.close();
		        response.flushBuffer();
	
	        }catch(Exception e){
	        	System.out.println("生成信息表(Excel格式)时出错：");
	        	e.printStackTrace();
	        }
	        return;
		}
	}
	/*
	 *  可视化图形——曲线图
	 * 
	 */
	protected void getTjbbQxtHsZdhz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				xList =xList+"'"+xAxis[i]+"',";
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZdhzZxm> TjbbYxzbHsZdhzZxmList = new ArrayList<TjbbYxzbHsZdhzZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZdhzZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdhzZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("7".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb7())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb7()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("8".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb8())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb8()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("9".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb9())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb9()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("10".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb10())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb10()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbQxt.jsp").forward(request,
					response);
		}
	}
	/*
	 *  可视化图形——柱状图
	 * 
	 */
	protected void getTjbbZztHsZdhz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			String xList = "";
			for(int i=0; i<xAxis.length; i++){
				xList =xList+"'"+xAxis[i]+"',";
			}
			//去掉最后一个逗号
			xList = xList.substring(0,xList.length()-1);
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			ArrayList<String> ynameList = new ArrayList<String>();
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZdhzZxm> TjbbYxzbHsZdhzZxmList = new ArrayList<TjbbYxzbHsZdhzZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZdhzZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdhzZxmByID(Integer.parseInt(id));
			}
			for(int j=0; j<yAxis.length; j++){
				if("1".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb1())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb1()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("2".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb2())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb2()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("3".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb3())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb3()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("4".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb4())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb4()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("5".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb5())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb5()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("6".equals(yAxis[j])){
					ynameList.add("总计");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb6())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb6()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("7".equals(yAxis[j])){
					ynameList.add("1000吨(含)以下船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb7())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb7()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("8".equals(yAxis[j])){
					ynameList.add("1000—5000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb8())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb8()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("9".equals(yAxis[j])){
					ynameList.add("5000—10000吨(含)船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb9())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb9()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}else if("10".equals(yAxis[j])){
					ynameList.add("10000吨以上船舶");
					String ydata =  "";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb10())){
							ydata =ydata+"0,";
						}else{
							ydata =ydata+TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb10()+",";
						}
					}
					ydata = ydata.substring(0,ydata.length()-1);
					ydataList.add(ydata);
				}
				
			}
			request.setAttribute("xList", xList);
			request.setAttribute("ynameList", ynameList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbZzt.jsp").forward(request,
					response);
		}
	}

	/*
	 *  可视化图形——饼状图
	 * 
	 */
	protected void getTjbbBztHsZdhz(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取当前用户
		HttpSession session = request.getSession(); 
		ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
		if(UserInfo==null||"".equals(UserInfo)){
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
			
		}else{
			//测试
			String id = request.getParameter("id");
			String xAxis[] = request.getParameterValues("xAxis");//用getParameterValues的方法，将核取到的值取到langtype[]阵列内      
			String yAxis[] = request.getParameterValues("yAxis");
			
			ArrayList<String> xList = new ArrayList<String>();
			for(int i=0; i<xAxis.length; i++){
				xList.add("'"+xAxis[i]+"月'");
			}
			//将xAxis中的内容转化为Integer类型
			ArrayList<Integer> xAxisInt = new ArrayList<Integer>();
			for(int i=0; i<xAxis.length; i++){
				xAxisInt.add(Integer.parseInt(xAxis[i])-1);
			}
			String yname = "";
			ArrayList<String> ydataList = new ArrayList<String>();
			ArrayList<TjbbYxzbHsZdhzZxm> TjbbYxzbHsZdhzZxmList = new ArrayList<TjbbYxzbHsZdhzZxm>();
			BbsbTjbbDao bbsbTjbbDao = new BbsbTjbbDao();
			if(!"".equals(id)&&id!=null){
				TjbbYxzbHsZdhzZxmList =  bbsbTjbbDao.queryTjbbYxzbHsZdhzZxmByID(Integer.parseInt(id));
			}
			if(yAxis.length==1){
				if("1".equals(yAxis[0])){
					yname = "总计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb1())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb1());
						}
					}
				}else if("2".equals(yAxis[0])){
					yname = "1000吨(含)以下船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb2())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb2());
						}
					}
				}else if("3".equals(yAxis[0])){
					yname = "1000—5000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb3())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb3());
						}
					}
				}else if("4".equals(yAxis[0])){
					yname = "5000—10000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb4())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb4());
						}
					}
				}else if("5".equals(yAxis[0])){
					yname = "10000吨以上船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb5())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb5());
						}
					}
				}else if("6".equals(yAxis[0])){
					yname = "总计";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb6())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb6());
						}
					}
				}else if("7".equals(yAxis[0])){
					yname = "1000吨(含)以下船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb7())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb7());
						}
					}
				}else if("8".equals(yAxis[0])){
					yname = "1000—5000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb8())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb8());
						}
					}
				}else if("9".equals(yAxis[0])){
					yname = "5000—10000吨(含)船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb9())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb9());
						}
					}
				}else if("10".equals(yAxis[0])){
					yname = "10000吨以上船舶";
					for(int k=0; k<xAxisInt.size(); k++){
						if("".equals(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb10())){
							ydataList.add("0");
						}else{
							ydataList.add(TjbbYxzbHsZdhzZxmList.get(xAxisInt.get(k)).getZb10());
						}
					}
				}
				
			}
			request.setAttribute("yname", yname);
			request.setAttribute("xList", xList);
			request.setAttribute("ydataList", ydataList);
			request.getRequestDispatcher("/jsp/StatisticalReports/tjbbBzt.jsp").forward(request,
					response);
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
