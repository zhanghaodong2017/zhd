package com.zuidiao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FeeDbUtils {
	private static String url = "jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "fee";
	private static String password = "Lemi20132015";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<CNSParam> getTelByTime() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "select imsi,imei,tel,pro from registerapp  "
				+ "  where tel <> '' and carrier='T' and create_time>'2016-10-25' and create_time <'2016-10-26' ";
		System.out.println(sql);
		List<CNSParam> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String imsi = rs.getString(1);
				String imei = rs.getString(2);
				String sc = rs.getString(3);
				String pro = rs.getString(4);
				if (sc != null && sc.length() > 10 && imsi != null
						&& imei != null && imsi.length() == 15
						&& imei.length() == 14) {
					if (sc.length() > 11) {
						sc = sc.substring(sc.length() - 11);
					}
					if (!sc.contains("000") && !imsi.contains("0000000")
							&& !imei.contains("0000000")) {
						CNSParam param = new CNSParam();
						param.setImei(imei);
						param.setImsi(imsi);
						param.setTel(sc);
						list.add(param);
					}
				}
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
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

	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
