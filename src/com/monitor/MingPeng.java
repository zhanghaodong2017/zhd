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


public class MingPeng {
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
		String sql="select distro from sp_app where userid='220'";
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/partnersdk?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			List<String> distroes = new ArrayList<>();
			List<String> newdistroes = new ArrayList<>();
			while(rs.next()){
				String distro = rs.getString(1);
				distroes.add(distro);
			}
			for (String distro : distroes) {
				if(distro.startsWith("0")){
					while(distro.startsWith("0")){
						distro = distro.substring(1);
					}

					if(!distroes.contains(distro)){
						newdistroes.add(distro);
					}
				}

			}
			for (String distro : newdistroes) {
				System.out.println(distro);
//				String insert="INSERT into  sp_app(gamename,gameid,userid,distro,distroname,type) VALUES('TV','013','220','"+distro+"','明鹏光易','SP')";
//				boolean isInert = stmt.execute(insert);
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
