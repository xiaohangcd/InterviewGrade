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
 * Servlet implementation class deleteTeacherInClass
 */
@WebServlet("/project/Menu/deleteTeacherInClass")
public class deleteTeacherInClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteTeacherInClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String e_number = request.getParameter("Tid");//考官编号
		String testName = request.getParameter("test");//考试名字
		String time = request.getParameter("time");//考试时间
		String room = request.getParameter("Class");//考场名称
		//删除之后用out返回success 或 unsuccess的字符串
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
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
		String sql2 = "select r_number from rooms where r_name = '"+room+"'";
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
		String sql3 = "delete from allocated where t_number = '"+t_number+"' and r_number = '"+r_number+"' and etest_time = '"+time+"' and e_number = '"+e_number+"'";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql3);
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
