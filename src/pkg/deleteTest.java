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
 * Servlet implementation class deleteTest
 */
@WebServlet("/project/Menu/deleteTest")
public class deleteTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String Tid = request.getParameter("Tid");
		SQL Conn = new SQL();
		java.sql.Statement state;
		String sql = "delete from tests where t_number = '"+Tid+"'";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.write("unsuccess!");
		}
		out.write("success!");
		System.out.println(Tid);
		out.write(Tid);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
