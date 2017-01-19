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

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.test.RedirectUtil;


public class SendDataMDO {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String lqurl="http://203.86.5.80:31688/data/toall466.aspx?";
	public static void main(String[] args) {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="select id,fm,price, mobile,result,paycode from reqcontent_db   where ctime>CURDATE() and fm='WWLHMD' and result='0' and userdata='0'";
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/wfsdk?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			int index = 0;
			List<String> paraList = new ArrayList<>();
			while(rs.next()){

				String id = rs.getString(1);
				String fm = rs.getString(2);
				String price = rs.getString(3);
				String mobile = rs.getString(4);
				String result = rs.getString(5);
				String paycode = rs.getString(6);

				String can = "linkid=" + id + "&result="
						+ Integer.valueOf(result) + "&price=" + Integer.valueOf(price)+"&mobile="+mobile;
				paraList.add(can);
					index++;
			}
			for (String string : paraList) {
				HttpClient client = HttpClients.createDefault();
				HttpGet get = new HttpGet(lqurl +string);
				client.execute(get);
				System.out.println(string);
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
