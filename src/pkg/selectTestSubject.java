package pkg;
//根据传入的考试名称返回考试科目及分数
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectTestSubject
 */
@WebServlet("/project/Menu/selectTestSubject")
public class selectTestSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectTestSubject() {
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
		String name = request.getParameter("Tname");
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs;
		java.sql.Statement state2;
		ResultSet rs2;
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
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		String sql1 = "select * from included where t_number = '"+t_number+"'";
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql1);
			while(rs.next()){
				// 通过字段检索
				String ts_name = null;
				String sql2 = "select ts_name from t_subjects where ts_number = '"+rs.getString("ts_number").trim()+"'";
				try{
					state2 = Conn.dbConn.createStatement();
					rs2 = state2.executeQuery(sql2);
					while(rs2.next()){
						ts_name = rs2.getString("ts_name").trim();
					}
					rs2.close();
					state2.close();
				}catch(SQLException e){
					e.printStackTrace();
					out.write("unsuccess!");
				}
				//jsonobj.put("t_name", name);
				//System.out.println(rs.getString("s_number"));
				jsonobj.put("ts_name", ts_name);
				jsonobj.put("weights", rs.getBigDecimal("weights").toString());
				System.out.print(rs.getBigDecimal("weights").toString());
				jsonarray.add(jsonobj);
			}
			System.out.print(jsonarray.toString());
			out.print(jsonarray);
			
			rs.close();
			state.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
