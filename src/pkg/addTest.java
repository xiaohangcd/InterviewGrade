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
 * Servlet implementation class addTest
 */
@WebServlet("/project/Menu/addTest")
public class addTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String testId = request.getParameter("testId");
		String testName = request.getParameter("testName");
		PrintWriter out = response.getWriter();
		SQL Conn = new SQL();
		java.sql.Statement state;
		//ResultSet rs;
		String sql = "insert into tests values('"+testId+"','"+testName+"')";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.print("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Menu/index.html';</script>");
		}
		//成功提示跳转
		out.print("<script language='JavaScript'>alert('insert success!');"
				+ "window.location.href='/InterviewGrade/project/Menu/index.html';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
