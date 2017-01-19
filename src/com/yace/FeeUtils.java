package com.yace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.test.RedirectUtil;


public class FeeUtils {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static List<String> getRegist() {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="select * from registerapp where create_time>CURRENT_DATE() ";
		List<String>  list = new ArrayList<>();
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				String userId          = rs.getString(1);
				String appid           = rs.getString(2);
				String imei            = rs.getString(3);
				String iccid           = rs.getString(4);
				String imsi            = rs.getString(5);
				String distro          = rs.getString(6);
				String appver          = rs.getString(7);
				String useragent       = rs.getString(8);
				String model           = rs.getString(9);
				String mac             = rs.getString(10);
				String tel             = rs.getString(11);
				String carrier         = rs.getString(12);
				String androidversion  = rs.getString(14);
				String pro             = rs.getString(15);
				String ip              = rs.getString(16);
				String screen          = rs.getString(17);
				String telType         = rs.getString(18);
				String qxtType         = rs.getString(19);
				String lac             = rs.getString(20);
				String cid             = rs.getString(21);
				JSONObject json=new JSONObject();
				json.put("userId",OrderIdUtils.getOrderId(15));
				json.put("appid",appid);
				json.put("imei",imei);
				json.put("iccid",iccid);
				json.put("imsi",imsi);
				json.put("distro",distro);
				json.put("appver",appver);
				json.put("useragent",useragent);
				json.put("model",model);
				json.put("mac",mac);
				json.put("tel",tel);
				json.put("carrier",carrier);
				json.put("androidversion",androidversion);
				json.put("pro",pro);
				json.put("ip",ip);
				json.put("screen",screen);
				json.put("telType",telType);
				json.put("qxtType",qxtType);
				json.put("lac",lac);
				json.put("cid",cid);
				list.add(json.toString());
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
		return list;
	}
}
