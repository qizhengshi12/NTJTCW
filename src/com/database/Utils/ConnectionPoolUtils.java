package com.database.Utils;

/*连接池工具类，返回唯一的一个数据库连接池对象*/
public class ConnectionPoolUtils {
	private static ConnectionPool poolInstance = null;
	public static ConnectionPool GetPoolInstance(){
		if(poolInstance == null) {
			poolInstance = new ConnectionPool(
					//"com.microsoft.jdbc.sqlserver.SQLServerDriver",
					"com.mysql.jdbc.Driver",
					//"jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=SuperMarketInfo",
					"jdbc:mysql://127.0.0.1:3306/ntjtcw?user=root&password=admin&useUnicode=true&characterEncoding=gbk",
					//"jdbc:sqlserver://localhost;database=SuperMarketInfo;IntegratedSecurity=false;",
					"root", "admin");
			try {
				poolInstance.createPool();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return poolInstance;
	}
}
