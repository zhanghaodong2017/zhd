package com.htest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Logs {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static List<String> getLogsList(){
		List<String> logslist = new ArrayList<>();
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="select content from logs where create_time>'2016-09-27' and create_time<'2016-09-28' order by create_time desc ";
		try {
			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/bsp?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				String content = rs.getString(1);
				if(content.startsWith("useragent")){
					logslist.add(content);
				}

			}
		} catch (Exception e) {

			if(rs !=null){
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(null != stmt){
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return logslist;
	}
}
