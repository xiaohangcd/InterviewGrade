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

/**
 * Servlet implementation class deleteAllotClass
 */
@WebServlet("/project/Menu/deleteAllotClass")
public class deleteAllotClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAllotClass() {
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
		String testName = request.getParameter("test");
		String roomName = request.getParameter("room");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		//删除为某门考试这个时间段分配的考场，返回success或unsuccess
		java.sql.Timestamp date1 = java.sql.Timestamp.valueOf(beginTime);
		java.sql.Timestamp date2 = java.sql.Timestamp.valueOf(endTime);
		//取出返回字符串类型考试，时间段，考场名称
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		//java.sql.Statement state2;
		//ResultSet rs2 = null;
		String t_number = null;
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
			out.print("unsuccess!");
		}
		String r_number = null;
		String sql2 = "select r_number from rooms where r_name = '"+roomName+"'";
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
			out.print("unsuccess!");
		}
		String sql = "delete from arranged where t_number = '"+t_number+"' and r_number = '"+r_number+"' and start_time = '"+date1+"' and off_time = '"+date2+"'";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.write("unsuccess!");
		}
		out.write("success!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
