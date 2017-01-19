package com.imsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

public class GetImsi {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static List<ImsiInfo> getImsi(String carrier,int pro){
		String sql ="SELECT imsi,pro,carrier FROM `registerapp` where imsi !='' and imsi!='000000000000000'"
				+ " and carrier='"+carrier+"' and pro='"+pro+"' LIMIT 1,1000 ";
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		List<ImsiInfo> imsiInfos = new ArrayList<>();
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				ImsiInfo info = new ImsiInfo();
				info.setImsi(rs.getString(1));
				info.setPro(rs.getString(2));
				info.setCarrier(rs.getString(3));
				imsiInfos.add(info);
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

		return imsiInfos;

	}
	public static List<ImsiInfo> getImsiBsp(String carrier,int pro,int size){
		String sql ="SELECT imsi,pro,carrier FROM `registerapp` where createdate>'2016-07-30' and  createdate<'2016-08-20' and imsi!='null'   "
				+ " and carrier='"+carrier+"' and pro='"+pro+"' LIMIT 1,"+size;
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		List<ImsiInfo> imsiInfos = new ArrayList<>();
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/bsp?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				ImsiInfo info = new ImsiInfo();
				info.setImsi(rs.getString(1));
				info.setPro(rs.getString(2));
				info.setCarrier(rs.getString(3));
				imsiInfos.add(info);
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

		return imsiInfos;

	}
}
