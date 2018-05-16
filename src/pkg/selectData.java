package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectData
 */
@WebServlet("/project/DataAnalysis/selectData")
public class selectData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectData() {
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
		String Sid = request.getParameter("Sid");
		String Tid = request.getParameter("Tid");
		String testid = request.getParameter("testName");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String lowGrade = request.getParameter("lowGrade");
		String highGrade = request.getParameter("highGrade");
		SQL Conn = new SQL();
		java.sql.Statement state;
		ResultSet rs = null;
		String sql = "select distinct s_number,e_number,t_number,z_grade from grades where 1 = 1";
		String sql1,sql2,sql3,sql4;
		if(!Sid.equals("#")){
			sql1 = sql + "and s_number = '"+Sid+"'";
		}else{
			sql1 = sql;
		}
		if(!Tid.equals("#")){
			sql2 = sql1 + "and e_number = '"+Tid+"'";
		}else{
			sql2 = sql1;
		}
		if(!testid.equals("#")){
			sql3 = sql2 + "and t_number = '"+testid+"'";
		}else{
			sql3 = sql2;
		}
		if(!lowGrade.equals("#")){
			BigDecimal lg = new BigDecimal(lowGrade);
			BigDecimal hg = new BigDecimal(highGrade);
			sql4 = sql3 + "and z_grade >= "+lg+" and z_grade <= "+hg;
		}else{
			sql4 = sql3;
		}
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		try{
			state = Conn.dbConn.createStatement();
			rs = state.executeQuery(sql4);
			while(rs.next()){
				// Í¨¹ı×Ö¶Î¼ìË÷
				jsonobj.put("s_number", rs.getString("s_number").trim());
				System.out.println(rs.getString("s_number"));
				jsonobj.put("t_number", rs.getString("t_number").trim());
				jsonobj.put("e_number", rs.getString("e_number").trim());
				jsonobj.put("z_grade", rs.getString("z_grade").trim());
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
