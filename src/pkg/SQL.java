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
	System.out.println("���������ɹ���");
}catch(Exception e){
	e.printStackTrace();
	System.out.println("��������ʧ�ܣ�");
}
try{
		dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		System.out.println("�������ݿ�ɹ���");
}catch(SQLException e)
{
	e.printStackTrace();
	System.out.print("SQL Server����ʧ�ܣ�");
}		
}
}