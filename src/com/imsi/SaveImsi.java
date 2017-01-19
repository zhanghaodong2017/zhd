package com.imsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaveImsi {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void sve(List<ImsiInfo> infos){

		Connection con = null;
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			Statement stmt =con.createStatement();
			StringBuffer sb = new StringBuffer("INSERT into imsi_info (id,imsi) VALUES");
			boolean flag= false;
			for (ImsiInfo imsiInfo : infos) {
				if(flag){
					sb.append(",");
				}
				flag= true;
				sb.append("('"+imsiInfo.getId()+"','"+imsiInfo.getImsi()+"')");
			}
			System.out.println(sb.toString());
			stmt.execute(sb.toString());
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
