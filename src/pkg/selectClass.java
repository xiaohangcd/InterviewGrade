package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectClass
 */
@WebServlet("/project/Menu/selectClass")
public class selectClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String Cid = request.getParameter("Cid");
		//System.out.println(Sname);
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		String sql = "select * from rooms where r_number like '%"+Cid+"%'";
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				// Í¨¹ý×Ö¶Î¼ìË÷
				jsonobj.put("r_number", rs.getString("r_number").trim());
				//System.out.println(rs.getString("s_number"));
				jsonobj.put("r_name", rs.getString("r_name").trim());
				jsonarray.add(jsonobj);
			}
			System.out.print(jsonarray.toString());
			out.print(jsonarray);
			
			rs.close();
			state.close();
		}catch (Exception e) {
			out.print("get data error!");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
