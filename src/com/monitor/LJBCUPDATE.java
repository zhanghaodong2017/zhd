package com.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.test.RedirectUtil;


public class LJBCUPDATE {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static String lqurl="http://114.55.52.145:8588/synInfo/lmdyyInfo.Syn?";
	public static void main(String[] args) {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="select  id,paycode from reqcontent_db where ctime > '2016-08-20' and fm='LJBKCCC'";
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/bsp?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			int index = 0;
			Map<String, String> map =new HashMap<>();
			while(rs.next()){
				String id = rs.getString(1);
				String paycode = rs.getString(2);
				map.put(id, paycode.substring(0,26));
				index++;

			}
			System.out.println(index);
			Set<String> set = map.keySet();
			for (String id : set) {
				String paycode = map.get(id);
				String price ="10";
				switch (paycode) {
				case "00160427B06D01FA1851001096":
					price="20";
					break;
				case "00160427B06D01C8BB4d001096":
					price="15";
					break;
				case "00160427B06D012DBC48001096":
					price="10";
					break;
				case "00160427B06D0141D146001096":
					price="8";
					break;
				case "00160427B06D01483844001096":
					price="6";
					break;
				}
				String sql_update="update reqcontent_db set price ='"+price
						+"' where ctime>'2016-08-20' and id='"+id+"'";
				stmt.execute(sql_update);
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
	}
}
