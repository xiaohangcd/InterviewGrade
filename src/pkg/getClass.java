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
 * Servlet implementation class getClass
 */
@WebServlet("/project/Menu/getClass")
public class getClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getClass() {
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
		String test =request.getParameter("test");
		String time = request.getParameter("time");
		//通过考试及时间找教室
		String[] s = null;
		s = time.split(";");
		System.out.println(s[0]);
		java.sql.Timestamp date1 = java.sql.Timestamp.valueOf(s[0]);
		java.sql.Timestamp date2 = java.sql.Timestamp.valueOf(s[1]);
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		java.sql.Statement state2;
		ResultSet rs2 = null;
		String t_number = null;
		String sql1 = "select t_number from tests where t_name = '"+test+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql1);
			while(rs.next()){
				t_number = rs.getString("t_number");
			}
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		String sql = "select r_number from arranged where t_number = '"+t_number+"' and start_time = '"+date1+"' and off_time = '"+date2+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				String r_name = null;
				String sql2 = "select r_name from rooms where r_number = '"+rs.getString("r_number").trim()+"'";
				try{
					state2 = Conn.dbConn.createStatement();
					rs2 = state2.executeQuery(sql2);
					while(rs2.next()){
						r_name = rs2.getString("r_name").trim(); 
					}
					rs2.close();
					state2.close();
				}catch (Exception e) {
					out.print("get data error!");
					e.printStackTrace();
				}
				jsonobj.put("r_name", r_name);
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
