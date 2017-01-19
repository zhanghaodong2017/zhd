package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WfsdkDbUtils {
	private static String url = "jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/wfsdk?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "fqy";
	private static String password = "fqy1234";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getOrderidsList() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT order_id FROM wfsdk.`logs` where create_time>CURDATE() and type='SARDO' and result is null and create_time<'2016-11-09 13:05:00' and order_id !='FJAivaefrd1Ee'";
		List<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String order_id = rs.getString(1);
				list.add(order_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}

	public static Map<String, String> getLogsByorderid(List<String> orderids) {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		StringBuffer sql = new StringBuffer(
				"select order_id,content from `logs` where create_time>CURDATE() and result='200' and order_id in( ");
		for (int i = 0; i < orderids.size(); i++) {
			if (i > 0) {
				sql.append(",");
			}

			sql.append("'" + orderids.get(i).trim() + "'");
		}
		sql.append(")");
		System.out.println(sql.toString());
		Map<String, String> map = new HashMap<String, String>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String order_id = rs.getString(1);
				String content = rs.getString(2);
				map.put(order_id, getExpByContent(content));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stop(con, stmt, rs);
		}
		return map;
	}
	public static String getExpByContent(String content){
		String extparams="";
		String[] keyvalues =content.split("&");
		for (String kv : keyvalues) {
			if(kv.contains("extparams")){
				extparams =kv.split("=")[1];
			}
		}
		return extparams;

	}
	public static void stop(Connection con, Statement stmt, ResultSet rs) {

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
