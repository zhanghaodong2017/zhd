package com.http;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.lemi.utils.JoyUtils;
import com.test.RedirectUtil;


public class Dmoe3 {
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
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/bsp?useUnicode=true&characterEncoding=UTF-8";
			String user = "fqy";
			String password= "fqy1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			String sql="select id,phone_number from tbl_phone_number_area  where  provider is  null and provider_name not like '%电信%' and provider_name not like '%移动%' and provider_name not like '%联通%'";
			rs = stmt.executeQuery(sql);
			int index=0;
			Map<Integer, String> map = new HashMap<>();
			while(rs.next()){
				Integer id = rs.getInt(1);
				String  phone_number= rs.getString(2);
				map.put(id, phone_number);
			}
			System.out.println(map.size());
			Set<Integer> mapset = map.keySet();
			for (Integer id : mapset) {
				String 	phone_number = map.get(id);
				String mtu = getMUT(con, phone_number);
				String sqlupte="update tbl_phone_number_area set provider_name = '"+mtu
						+"' where provider is  null and id= '"+id+"'";
				Statement statement =con.createStatement();
				boolean is = statement.execute(sqlupte);
				System.out.println(is);
				System.out.println(index++);
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
	public static void  doUpdate(Statement statement ,String province_id,String provider_name) throws SQLException{
		String sql="update tbl_phone_number_area set province_id='"
				+province_id+"' , provider_name='"+provider_name
				+"' where provider_name like '%"+provider_name+"%'";
		boolean flag = statement.execute(sql);
		System.out.println(flag);
	}
	public static String  getMUT(Connection con ,String phone_number ) throws SQLException{
		Statement statement = con.createStatement();
		String querysql ="select mobile_type from rdms_mobile_area where mobile_number='"+phone_number+"'";
		ResultSet resultSet = statement.executeQuery(querysql);
		String reString="";
		if(resultSet.next()){
			reString = resultSet.getString(1);
		}
		resultSet.close();
		return reString;
	}
}
