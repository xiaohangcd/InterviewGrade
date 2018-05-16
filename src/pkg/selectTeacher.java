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
 * Servlet implementation class selectTeacher
 */
@WebServlet("/project/Role/selectTeacher")
public class selectTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectTeacher() {
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
		String Sname = request.getParameter("Tname");
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		String sql = "select * from examiners where e_name like '%"+Sname+"%'";
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				jsonobj.put("e_number", rs.getString("e_number").trim());
				//System.out.println(rs.getString("e_number"));
				jsonobj.put("e_name", rs.getString("e_name").trim());
				jsonobj.put("e_id", rs.getString("e_id").trim());
				jsonobj.put("e_telephone", rs.getString("e_telephone").trim());
				jsonobj.put("passwords", rs.getString("passwords").trim());
				//jsonobj.put("speciality", rs.getString("speciality"));
				//jsonobj.put("award", rs.getString("award"));
				//jsonobj.put("date", rs.getString("birth").trim());
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
		//out.write(buf);//buf=查询出的数据
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
