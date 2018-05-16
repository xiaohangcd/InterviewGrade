package pkg;
//传过来一个考试名字删掉该考试下所有科目
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
 * Servlet implementation class deleteTestSubject
 */
@WebServlet("/project/Menu/deleteTestSubject")
public class deleteTestSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteTestSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("Tname");
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs;
		String t_number = null;
		String sql = "select t_number from tests where t_name = '"+name+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				t_number = rs.getString("t_number");
			}
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.write("unsuccess!");
		}
		String sql1 = "delete from included where t_number = '"+t_number+"'";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql1);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.write("unsuccess!");
		}
		out.write("success!");
		System.out.println(name);
		//out.write(name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
