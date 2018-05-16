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
 * Servlet implementation class editStudent
 */
@WebServlet("/project/Node/editStudent")
public class editStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String Sid = request.getParameter("Sid");
		System.out.println(Sid);
		String Sname = request.getParameter("Sname");
		String Sidcard = request.getParameter("Sidcard");
		String Stelephone = request.getParameter("Stelephone");
		String Sbrith = request.getParameter("Sbrith");
		String Spoint = request.getParameter("Spoint");
		String Saward = request.getParameter("Saward");
		PrintWriter out = response.getWriter();
		java.sql.Date date = java.sql.Date.valueOf(Sbrith);
		SQL Conn = new SQL();
		java.sql.Statement state;
		//ResultSet rs;
		String sql = "update students set s_name='"+Sname+"',s_id='"+Sidcard+"',s_telephone='"+Stelephone+"',speciality='"+Spoint+"',award='"+Saward+"',birth='"+date+"' where s_number='"+Sid+"'";
		try{
			state = Conn.dbConn.createStatement();
			state.executeUpdate(sql);
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
			out.write("<script language='JavaScript'>alert('unsuccess!');"
					+ "window.location.href='/InterviewGrade/project/Node/index.html';</script>");

		}
		//成功提示跳转
		out.write("<script language='JavaScript'>alert('update success!');"
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
