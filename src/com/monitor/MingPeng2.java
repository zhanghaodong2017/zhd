package com.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.test.RedirectUtil;


public class MingPeng2 {
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
		String sql="select id,distro from sp_app where userid='220'";
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/partnersdk?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			Map<String, String> map = new HashMap<>();
			while(rs.next()){
				String id = rs.getString(1);
				String distro = rs.getString(2);
				if(distro.endsWith(":") || distro.endsWith(";")){

					System.out.println(distro);
					String newdis = distro.substring(0,distro.length()-1);
					map.put(id, newdis);

				}
			}
			Set<String> set =map.keySet();
			for (String id : set) {
				String newdis = map.get(id);
				System.out.println(newdis);
//				String update="update sp_app set distro='"+newdis+"' where id="+id;
//				boolean isInert = stmt.execute(update);
//				System.out.println(isInert);
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
