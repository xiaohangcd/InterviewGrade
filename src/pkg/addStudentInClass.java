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
 * Servlet implementation class studentInClass
 */
@WebServlet("/project/Menu/addStudentInClass")
public class addStudentInClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStudentInClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String testName = request.getParameter("testName"); 
		String Time = request.getParameter("testTime");
		String Class = request.getParameter("testClass");
		String[] studentName = request.getParameterValues("student_select");
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs;
		int num = 0;
		String t_number = null;
		String r_number = null;
		String sql1 = "select t_number from tests where t_name = '"+testName+"'";
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
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Menu/index4.html';</script>");
		}
		String sql2 = "select r_number from rooms where r_name = '"+Class+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql2);
			while(rs.next()){
				r_number = rs.getString("r_number");
			}
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Menu/index4.html';</script>");
		}
		String sql = "select count(*) as num from joins where t_number = '"+t_number+"' and r_number ='"+r_number+"' and stest_time = '"+Time+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				num = rs.getInt("num");
			}
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Menu/index4.html';</script>");
		}
		for(int i=0;i<studentName.length;i++)
		{
			num++;
			String s_number = null;
			String sql4 = "select s_number from students where s_name = '"+studentName[i]+"'";
			try{
				state = Conn.dbConn.createStatement();
				rs = state.executeQuery(sql4);
				while(rs.next()){
					s_number = rs.getString("s_number");
				}
				rs.close();
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
				out.print("<script language='JavaScript'>alert('unsuccess!');"
						+ "window.location.href='/InterviewGrade/project/Menu/index4.html';</script>");
			}
			String sql3 = "insert into joins values('"+s_number+"','"+t_number+"','"+r_number+"','"+Time+"',"+num+")";
			try{
				state = Conn.dbConn.createStatement();
				state.executeUpdate(sql3);
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
				out.print("<script language='JavaScript'>alert('unsuccess!');"
						+ "window.location.href='/InterviewGrade/project/Menu/index4.html';</script>");
			}
		}
		out.print("<script language='JavaScript'>alert('success!');"
				+ "window.location.href='/InterviewGrade/project/Menu/index4.html';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
