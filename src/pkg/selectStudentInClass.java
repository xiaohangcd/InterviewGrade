package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectStudentInClass
 */
@WebServlet("/project/Menu/selectStudentInClass")
public class selectStudentInClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectStudentInClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String testName = request.getParameter("Test"); 
		String Time = request.getParameter("Time");
		String Class = request.getParameter("Class");
		//��Ҫ���ؿ�����ţ��������������ƣ�ʱ��Σ�����
		System.out.print(testName);
		System.out.print(Time);
		System.out.print(Class);
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		java.sql.Statement state2;
		ResultSet rs2 = null;
		String t_number = null;
		String r_number = null;
		String sql1 = "select t_number from tests where t_name = '"+testName+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql1);
			while(rs.next()){
				t_number = rs.getString("t_number").trim();
			}
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		String sql2 = "select r_number from rooms where r_name = '"+Class+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql2);
			while(rs.next()){
				r_number = rs.getString("r_number").trim();
			}
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		String sql = "select * from joins where t_number = '"+t_number+"' and r_number = '"+r_number+"' and stest_time = '"+Time+"'";
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				// ͨ���ֶμ���
				String s_name = null;
				String sq3 = "select s_name from students where s_number = '"+rs.getString("s_number").trim()+"'";
				try{
					state2 = Conn.dbConn.createStatement();
					rs2 = state2.executeQuery(sq3);
					while(rs2.next()){
						s_name = rs2.getString("s_name").trim();
					}
					rs2.close();
					state2.close();
				}catch (Exception e) {
					out.print("get data error!");
					e.printStackTrace();
				}
				jsonobj.put("s_number", rs.getString("s_number").trim());
				jsonobj.put("s_name", s_name);
				jsonobj.put("t_name", testName.trim());
				jsonobj.put("time", Time.trim());
				jsonobj.put("r_name", Class.trim());
				jsonarray.add(jsonobj);
			}
			System.out.print(jsonarray.toString());
			out.print(jsonarray);
			rs.close();
			state.close();
		}catch (Exception e) {
			out.print("get data error!");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
