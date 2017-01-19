package com.html;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.RedirectUtil;

public class CodeCreate {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static final String TABLE_NAME="app_info";

	public String[] select = {"mobileSystem","status","sdkMode",""};
	public static void main(String[] args) throws Exception {
		String tableName=getJavaName(TABLE_NAME);
		String sql="select COLUMN_NAME,DATA_TYPE from information_schema.columns where table_name='"+TABLE_NAME+"'";
		String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
		String user = "fee";
		String password= "Lemi20132015";
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		List<String> list =new ArrayList<>();
		while(rs.next()){
			String column_name = rs.getString(1);
			list.add(getJavaName(column_name));
		}
		System.out.println("<form	action=\"${pageContext.request.contextPath }/.do\"	"
				+ "			method=\"POST\" id=\""+tableName+"Form\">");
		System.out.println("<div style=\"text-align: center;width: 100%\">");
		System.out.println("<table style=\"width: 800px\">");
		for (String column : list) {
			System.out.println("<tr>");
			System.out.println("<td width=\"30%\">"+column+"<span style=\"color:red\">*</span></td>");
			System.out.println("<td width=\"70%\">");
			System.out.println("<input class=\"form-control\" id=\""+column+"\" maxlength=\"19\" name=\""+tableName+"."+column+"\" value=\"${"+tableName+"."+column+"}\" />");
			System.out.println("</td>");
			System.out.println("</tr>");
		}
		System.out.println("</table>");
		System.out.println("</div>");
		System.out.println("</form>");
	}

	public static String getJavaName(String name){
		while(name.contains("_")){
			int _index = name.indexOf("_");
			String str =name.substring(_index+1, _index+2);
			name=name.replace("_"+str, str.toUpperCase());
		}

		return name;
	}
}
