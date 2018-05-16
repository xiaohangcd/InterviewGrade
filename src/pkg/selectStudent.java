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
 * Servlet implementation class selectStudent
 */
@WebServlet("/project/Node/selectStudent")
public class selectStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectStudent() {
        super();
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter(); //把json数据传递到前端，记着前端用ajax接收
		String Sname = request.getParameter("Sname");
		System.out.println(Sname);
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		String sql = "select * from students where s_name like '%"+Sname+"%'";
		//String sql = "select * from students";
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				jsonobj.put("s_number", rs.getString("s_number").trim());
				System.out.println(rs.getString("s_number"));
				jsonobj.put("s_name", rs.getString("s_name").trim());
				jsonobj.put("s_id", rs.getString("s_id").trim());
				jsonobj.put("s_telephone", rs.getString("s_telephone").trim());
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
		doGet(request, response);
	}

}
