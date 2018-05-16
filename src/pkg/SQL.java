package pkg;

import java.sql.*;

public class SQL {
	public static Connection dbConn;
	public  SQL()
 {
  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=mark";
  String userName="rjh";
  String userPwd="rjh";
 try
{
	Class.forName(driverName);
	System.out.println("加载驱动成功！");
}catch(Exception e){
	e.printStackTrace();
	System.out.println("加载驱动失败！");
}
try{
		dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		System.out.println("连接数据库成功！");
}catch(SQLException e)
{
	e.printStackTrace();
	System.out.print("SQL Server连接失败！");
}		
}
}