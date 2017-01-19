package com.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;

import com.test.RedirectUtil;


public class SendData_3 {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="select id,fm,price,channel,mobile,result,vcode,orderid from reqcontent_db where create_time >CURDATE() and fm='TYZKLTWYD' and bieming='BA'";

		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			int index = 0;
			while(rs.next()){
				String id = rs.getString(1);
				String fm = rs.getString(2);
				Integer price = rs.getInt(3);
				String channel = rs.getString(4);
				String mobile = rs.getString(5);
				String result = rs.getString(6);
				String vcode = rs.getString(7);
				String orderid = rs.getString(8);

				Map<String,String> params = new HashMap<String,String>();
				params.put("linkid", id);
				params.put("result", "0");
				params.put("price", price.toString());
				params.put("mobile", mobile);
				params.put("extparams", channel);
				//paycode.substring(0,2)
				RedirectUtil.redirect(fm,"BA", params);
				System.out.println(params);
				index++;
			}
			System.out.println(index);
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
