package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertAllotClass
 */
@WebServlet("/project/Menu/insertAllotClass")
public class insertAllotClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertAllotClass() {
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
		String beginTime = request.getParameter("testTime");
		String endTime = request.getParameter("testTime2");
		String[] className = request.getParameterValues("class_select");
		System.out.println(testName);
		java.sql.Timestamp date1 = java.sql.Timestamp.valueOf(beginTime);
		java.sql.Timestamp date2 = java.sql.Timestamp.valueOf(endTime);
		//java.sql.Date date1 = java.sql.Date.valueOf(beginTime);
		//java.sql.Date date2 = java.sql.Date.valueOf(endTime);
		//System.out.println(date1);
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs;
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
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Menu/indexAdd2.html';</script>");
		}
		for (int i = 0; i < className.length; i++) {
			//System.out.println(className[i]);
			String r_number = null;
			String sql2 = "select r_number from rooms where r_name = '"+className[i]+"'";
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
				out.print("<script language='JavaScript'>alert('unsuccess!');"
						+ "window.location.href='/InterviewGrade/project/Menu/indexAdd2.html';</script>");
			}
			//System.out.print(t_number);
			System.out.println(t_number);
			String sql3 = "insert into arranged values('"+t_number+"','"+r_number+"','"+date1+"','"+date2+"')";
			try{
				state = Conn.dbConn.createStatement();
				state.executeUpdate(sql3);
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
				out.print("<script language='JavaScript'>alert('unsuccess!');"
						+ "window.location.href='/InterviewGrade/project/Menu/indexAdd2.html';</script>");
			}
		}
		out.print("<script language='JavaScript'>alert('success!');"
				+ "window.location.href='/InterviewGrade/project/Menu/indexAdd2.html';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
