package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addTeacher
 */
@WebServlet("/project/Role/addTeacher")
public class addTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTeacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Tid = request.getParameter("Tid");
		String Tpass = request.getParameter("Tpass");
		String Tname = request.getParameter("Tname");
		String Tidcard = request.getParameter("Tidcard");
		String Tphone = request.getParameter("Tphone");
		//成功提示跳转
		PrintWriter out = response.getWriter();
		SQL Conn = new SQL();
		java.sql.Statement state;
		//ResultSet rs;
		String sql = "insert into examiners values('"+Tid+"','"+Tname+"','"+Tphone+"','"+Tidcard+"','"+Tpass+"')";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Role/index.html';</script>");
		}
		out.print("<script language='JavaScript'>alert('success!');"
				+ "window.location.href='/InterviewGrade/project/Role/index.html';</script>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
