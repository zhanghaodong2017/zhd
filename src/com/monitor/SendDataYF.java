package com.monitor;

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

public class SendDataYF {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String lqurl = "http://api.173ttl.com/v1/callback/lmmg?";

	public static void main(String[] args) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select  id,paycode,fm,channel,price,app_serial_no,result,mobile from reqcontent_db  "
				+ " where ctime>'2016-10-10' and fm='ACY' and paycode  like 'ZY%' and userdata='1' and result='0' ";
		try {

			String url = "jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/bsp?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password = "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int index = 0;
			while (rs.next()) {

				String id = rs.getString(1);
				String paycode = rs.getString(2);
				String fm = rs.getString(3);
				String channel = rs.getString(4);
				String price = rs.getString(5);
				String app_serial_no = rs.getString(6);
				String result = rs.getString(7);
				String mobile = rs.getString(8);

				String content="resultCode="+result+"&cpPara="+paycode
						+"&phone="+mobile+"&fee="+(Integer.valueOf(price)*100)+"&timeStamp="+System.currentTimeMillis();
				System.out.println(content);
				//RedirectUtil.redirect2("ACY", "ZY", id, price, result, content);
				index++;

			}
			System.out.println(index);
		} catch (Exception e) {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (null != stmt) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
