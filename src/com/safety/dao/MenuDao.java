package com.safety.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;

import com.database.Utils.DB;

public class MenuDao {
	
	/*查询所有的节点信息（点击获得当前节点ID）*/
	public  String QueryAllMenuInfo(String DBName) {
		String sqlString = "select * from "+DBName;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				str+= "tree.nodes['"+rs.getInt("parent_id")+"_"+rs.getInt("id")+"'] = 'text:"+rs.getString("name")+"; method:queryMenuContext("+rs.getInt("id")+"); hint:"+rs.getString("description")+"';";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return str;
	}

	/*查询所有的节点信息（点击获得当前节点名称）*/
	public  String QueryAllMenuInfoName(String DBName) {
		String sqlString = "select * from "+DBName;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				str+= "tree.nodes['"+rs.getInt("parent_id")+"_"+rs.getInt("id")+"'] = 'text:"+rs.getString("name")+"; method:queryMenuContext(\""+rs.getString("name")+"\"); hint:"+rs.getString("description")+"';";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return str;
	}
	/*查询所有的节点信息（点击获得当前节点名称）
	 * 参数：数据库名称、 需要标记的节点名称
	 * */
	public  String QueryAllMenuInfoName(String DBName,String nameList) {
		String sqlString = "select * from "+DBName;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				if(nameList.indexOf(rs.getString("name"))!=-1){
					str+= "tree.nodes['"+rs.getInt("parent_id")+"_"+rs.getInt("id")+"'] = 'text:"+rs.getString("name")+"; method:queryMenuContext(\""+rs.getString("name")+"\"); hint:"+rs.getString("description")+"; icon:wh;';";
				}else{
					str+= "tree.nodes['"+rs.getInt("parent_id")+"_"+rs.getInt("id")+"'] = 'text:"+rs.getString("name")+"; method:queryMenuContext(\""+rs.getString("name")+"\"); hint:"+rs.getString("description")+"';";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return str;
	}
	/*
	 * 根据ID号查询子节点内容
	 */
	public static String QueryMenuById(String DBName,String MenuId) {
		String sqlString = "select son_id from "+DBName+" where id = "+MenuId;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				str = rs.getString("son_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return str;
	}

	/*
	 * 根据ID号查询节点名称
	 */
	public static String QueryNameById(String DBName,int MenuId) {
		String sqlString = "select name from "+DBName+" where id = "+MenuId;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				str = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return str;
	}
	/*
	 * 根据ID号查询父节点ID
	 */
	public static int QueryPidById(String DBName,int MenuId) {
		String sqlString = "select parent_id from "+DBName+" where id = "+MenuId;
		DB db = new DB();
		ResultSet rs; 
		int id=0;
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				id = rs.getInt("parent_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return id;
	}
	/*
	 * 根据父节点ID号查询子节点ID集合
	 */
	public static String QueryMenuByFatherId(String DBName,String MenuId) {
		String sqlString = "select id from "+DBName+" where parent_id = "+MenuId;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				if("".equals(str)){
					str = rs.getString("id");
				}else{
					str = str+","+rs.getString("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return str;
	}
	/*
	 * 根据ID号修改子项内容(新增指定信息)
	 */
	public static String InsertMenuContextById(String DBName, String MenuId, int InsId, Timestamp czsj, String czr, String czrID) {
		String sqlString = "select son_id from "+DBName+" where id = "+MenuId;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		String strNew="";
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				str = rs.getString("son_id");
				if(str!=null && !"".equals(str)){
					strNew = str + ","+InsId;
				}else{
					strNew = ""+InsId;
				}
				String sqlStringNew = "update "+DBName+" set son_id= '"+strNew+"', czsj='";
					sqlStringNew += czsj + "',czr='";
					sqlStringNew += czr +"',czrID='";
					sqlStringNew += czrID + "' where id = "+MenuId;
				db.executeUpdate(sqlStringNew);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return str;
	}
	/*
	 * 根据ID号修改子项内容(删除指定信息)
	 */
	public static String UpdateMenuContextById(String DBName, String MenuId, String DelId, Timestamp czsj, String czr, String czrID) {
		String sqlString = "select son_id from "+DBName+" where id = "+MenuId;
		DB db = new DB();
		ResultSet rs; 
		String str="";
		String strNew="";
		String strList[] =null;
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				str = rs.getString("son_id");
				strList = str.split(",");
				for(int i=0; i< strList.length; i++){
					if(!DelId.equals(strList[i])&&"".equals(strNew)){
						strNew = strList[i];
					}else if(!DelId.equals(strList[i])&&!"".equals(strNew)){
						strNew += ","+strList[i];
					}
				}
				String sqlStringNew = "update "+DBName+" set son_id= '"+strNew+"', czsj='";
					sqlStringNew += czsj + "',czr='";
					sqlStringNew += czr +"',czrID='";
					sqlStringNew += czrID + "' where id = "+MenuId;
				db.executeUpdate(sqlStringNew);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return str;
	}
	/*
	 * 新增菜单项
	 */
	public static void InsertMenu(String DBName,String name,int ParentID, Timestamp czsj, String czr, String czrID, String description) {
		String sqlString = "insert into "+DBName+" (name,parent_id,czsj,czr,czrID,description) value ('"+name+"',"+ParentID+",'"+czsj+"','"+czr+"','"+czrID+"','"+description+"')";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
	}

	/*
	 * 修改菜单项
	 */
	public static void UpdateMenuNameById(String DBName,String name,int id, Timestamp czsj, String czr, String czrID, String description) {
		String sqlString = "update "+DBName+" set name = '";
		sqlString += name+"', czsj='";
		sqlString += czsj + "',czr='";
		sqlString += czr +"',czrID='";
		sqlString += czrID+"',description='";
		sqlString += description + "' where id = "+id;
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
	}
	/*
	 * 删除菜单项
	 */
	public static void DeleteMenuName(String DBName) {
		String sqlString = "delete from "+DBName;
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
	}
	/*
	 * 删除菜单项
	 */
	public static void DeleteMenuNameById(String DBName,int id) {
		String sqlString = "delete from "+DBName+" where id = "+id;
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
	}
	/*
	 * 在菜单记录中，根据父节点ID查询存在的子项数目
	 */
	public static int QueryCountByFatherId(String DBName,String parent_id) {
		String sqlString = "select count(*) from "+DBName+" where parent_id = "+parent_id;
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
	 * 在菜单记录中，根据ID查询存在的子项数目
	 */
	public static int QueryCountById(String DBName,String id) {
		String sqlString = "select count(*) from "+DBName+" where fatherid = '"+id+"'";
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
	 * 初始化单位菜单架构
	 */
	public static void ResetZzxxMenu(String DBName, Timestamp czsj, String czr, String czrID) {
		String sqlString = "insert into "+DBName+" (name,parent_id,description,czr,czrID,czsj)"
		+"select name,parent_id,description,'"+ czr+"','"+czrID+"','"+czsj+"' from node_zzxx where parent_id=0";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
	}
}
