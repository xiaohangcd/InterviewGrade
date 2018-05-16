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
 * Servlet implementation class addTestSubject
 */
@WebServlet("/project/Menu/addTestSubject")
public class addTestSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTestSubject() {
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
		String[] name = request.getParameterValues("subject");
		String[] grade = request.getParameterValues("grade");
		System.out.println(testName);
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs;
		int num = 0;
		String t_number = null;

		String sql = "select count(*) as num from t_subjects";
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
		}
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
					+ "window.location.href='/InterviewGrade/project/Menu/index2.html';</script>");
		}
		for (int i = 0; i < name.length; i++) {
			//System.out.print(name[i]);
			num++;
			String s_id = "" + num;
			String sql2 = "insert into t_subjects values('"+s_id+"','"+name[i]+"')";
			try{
				state = Conn.dbConn.createStatement();
				state.executeUpdate(sql2);
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
				out.print("<script language='JavaScript'>alert('unsuccess!');"
						+ "window.location.href='/InterviewGrade/project/Menu/index2.html';</script>");
			}
			//System.out.print(t_number);
			String sql3 = "insert into included values('"+t_number+"','"+s_id+"','"+grade[i]+"')";
			try{
				state = Conn.dbConn.createStatement();
				state.executeUpdate(sql3);
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
				out.print("<script language='JavaScript'>alert('unsuccess!');"
						+ "window.location.href='/InterviewGrade/project/Menu/index2.html';</script>");
			}
		}
		
		out.print("<script language='JavaScript'>alert('success!');"
				+ "window.location.href='/InterviewGrade/project/Menu/index2.html';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
