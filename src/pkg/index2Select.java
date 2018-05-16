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
 * Servlet implementation class index2Select
 */
@WebServlet("/project/Menu/index2Select")
public class index2Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index2Select() {
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
		//select所有考试用out传来
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		String sql = "select * from tests";
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				//jsonobj.put("t_number", rs.getString("t_number").trim());
				//System.out.println(rs.getString("s_number"));
				jsonobj.put("t_name", rs.getString("t_name").trim());
				jsonarray.add(jsonobj);
			}
			System.out.print(jsonarray.toString());
			out.print(jsonarray);
			System.out.print("888");
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
