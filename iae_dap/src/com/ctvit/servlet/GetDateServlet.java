package com.ctvit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ctvit.action.dap.DailyUserCountData;
import com.ctvit.dbutils.C3P0Utils;
import com.ctvit.dbutils.ExecuteSql;

/**
 * Servlet implementation class GetDateServlet
 */
@WebServlet("/GetDateServlet")
public class GetDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		try {
			con = C3P0Utils.getConnection();
			ExecuteSql excuteSql = new ExecuteSql(con);
			String sql = "select * from userdailycount order by date asc";
			ResultSet rs = excuteSql.executeSql(sql);
			String date = "";
			String nowDate = "";
			List<String> list = new ArrayList<String>();
			if(rs.next()){
				date = rs.getString(2);
				nowDate = DailyUserCountData.nextDay(rs.getString(2));
				list.add(rs.getString(3));
			}while(rs.next()){
				while(!rs.getString(2).equals(nowDate)){
					
					nowDate = DailyUserCountData.nextDay(nowDate);
					list.add("0");
				}
				list.add(rs.getString(3));
				nowDate = DailyUserCountData.nextDay(nowDate);
			}

			System.out.println(list);
			System.out.println(date);
			response.setContentType("text;html;charset=utf-8");
			  ServletOutputStream out = response.getOutputStream();
			  JSONObject json = new JSONObject();
			  SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");
			  long time = format.parse(date).getTime();
			  json.put("data", list);
			  json.put("date", time);
			  out.print(json.toString());
			  out.flush();
			  out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
