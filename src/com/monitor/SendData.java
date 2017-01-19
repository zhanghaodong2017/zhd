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

import org.apache.commons.lang.math.RandomUtils;

import com.htest.HttpUtil;
import com.test.RedirectUtil;


public class SendData {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String lqurl="http://pay.name008.com/pay/lemi/sms?";
	public static void main(String[] args) {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="SELECT id,channel,price,result FROM reqcontent_db where create_time>'2016-10-25' and channel='6367582' ";
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			int index = 0;
			List<String> can = new ArrayList<>();
			while(rs.next()){
				String id = rs.getString(1);
				String channel = rs.getString(2);
				String price = rs.getString(3);
				String result = rs.getString(4);
				String urlReal = "linkid=" + id + "&channel=" + channel
						+ "&price=" + Integer.valueOf(price) + "&result=" + Integer.valueOf(result);
				can.add(urlReal);
				index++;
			}
			System.out.println(index);
			for (String param : can) {
				int ram = RandomUtils.nextInt(100);
				if(ram >20){
					String urlReal = lqurl+param;
					String back = HttpUtil.doGet(urlReal);
					System.out.println(back);
				}else{
					String urlReal = lqurl+param;
					System.out.println(urlReal);
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
	}
}
