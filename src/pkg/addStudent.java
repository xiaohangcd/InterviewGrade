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
 * Servlet implementation class addStudent
 */
@WebServlet("/project/Node/addStudent")
public class addStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Sid = request.getParameter("Sid");
		String Sname = request.getParameter("Sname");
		System.out.println(Sname);
		String Sidcard = request.getParameter("Sidcard");
		String Stelephone = request.getParameter("Stelephone");
		String Sbrith = request.getParameter("Sbrith");
		String Spoint = request.getParameter("Spoint");
		String Saward = request.getParameter("Saward");
		java.sql.Date date = java.sql.Date.valueOf(Sbrith);
		PrintWriter out = response.getWriter();
		SQL Conn = new SQL();
		java.sql.Statement state;
		//ResultSet rs;
		String sql = "insert into students values('"+Sid+"','"+Sname+"','"+Sidcard+"','"+Stelephone+"','"+Spoint+"','"+Saward+"','"+date+"')";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Node/index.html';</script>");
		}
		//成功提示跳转
		out.print("<script language='JavaScript'>alert('insert success!');"
				+ "window.location.href='/InterviewGrade/project/Node/index.html';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
