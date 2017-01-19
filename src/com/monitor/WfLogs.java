package com.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WfLogs {
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
		String sql="select content from wfsdk.`logs` where create_time>CURRENT_DATE()  and type='ZMKMYL' and channel='000785' and result='200'";
		try {
			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/wfsdk?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				String line = rs.getString(1);
				String out = line.substring(line.indexOf("&extparams=") + 11,line.indexOf("&ip"));
				logslist.add(out);
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
