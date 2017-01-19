package work;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.monitor.ReqcontentDb;

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

	public static Map<String, Integer> getSumPriceByFm() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT fm,sum(price) price FROM reqcontent_db where create_time>CURRENT_DATE() and bieming='LM' and result='0' GROUP BY fm";
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String fm = rs.getString(1);
				BigDecimal sumPrice = rs.getBigDecimal(2);
				if (sumPrice != null && fm != null) {
					map.put(fm, sumPrice.intValue());
				}
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return map;
	}

	public static void updateLqByid(String id, String orderid, String channel) {
		Statement stmt = null;
		Connection con = null;
		String sql = "update reqcontent_db set channel='" + channel
				+ "',orderid='" + orderid
				+ "' where create_time>CURDATE() and id='" + id + "'";
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {

		} finally {
			stop(con, stmt, null);
		}
	}

	public static List<String> getLqdtaByFm() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT id FROM reqcontent_db where channel is  null and fm='SARDO'   and create_time>'2016-11-09 12:28:33'";
		List<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				list.add(id);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}

	public static Map<String,AppInfo> getAppList() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select app_id,url,bili from app_info";
		Map<String,AppInfo> map = new HashMap<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String appid = rs.getString(1);
				String url = rs.getString(2);
				Integer bili = rs.getInt(3);
				AppInfo appInfo = new AppInfo();
				appInfo.setAppId(appid);
				appInfo.setUrl(url);
				appInfo.setBili(bili);
				map.put(appid, appInfo);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return map;
	}




	public static List<String> getReqcontentDb(String fm) {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id from reqcontent_db where create_time >CURDATE() and fm='"+fm+"'  and result='0' and bieming ='LM' and appid ='' ";
		List<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				list.add(id);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}
	public static List<ReqcontentDb> getReqDB12(String fm) {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id,appid,channel from reqcontent_db where create_time >CURDATE() and fm='"+fm+"'  and result='0' and bieming ='LM'  ";
		List<ReqcontentDb> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				String appid = rs.getString(2);
				String channel = rs.getString(3);

				ReqcontentDb db = new ReqcontentDb();
				db.setAppid(appid);
				db.setId(id);
				db.setChannel(channel);
				list.add(db);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}


	public static List<AfterPayLog> getAfterPayLog(String fm) {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select appid,channelid from  after_pay_log  where create_time >CURDATE() and fm='MQYDBY' and error_code = '2Y' ORDER BY create_time desc";
		System.out.println(sql);
		List<AfterPayLog> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String channelid = rs.getString(2);
				String appid = rs.getString(1);
				AfterPayLog payLog = new AfterPayLog();
				payLog.setChannelid(channelid);
				payLog.setAppid(appid);
				list.add(payLog);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}




	public static List<String> getBackByappid() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id from reqcontent_db where create_time >CURDATE()   and bieming='LM'   and result='0'  and fm='MQYDBY'";
		List<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				list.add(id);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}

	public static void updateReqByappid(List<String> ids) {

		Statement stmt = null;
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();

			for (String id : ids) {
				String sql = "update  reqcontent_db set userdata='2' where create_time>CURDATE() and id='"
						+ id + "' ";
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
		} catch (Exception e) {

		} finally {
			stop(con, stmt, null);
		}
	}


	public static void updateReqByDB(List<ReqcontentDb> dbs) {

		Statement stmt = null;
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();

			for (ReqcontentDb db : dbs) {
				String sql = "update  reqcontent_db set appid='"+db.getAppid()+"',channel='"+db.getChannel()+"' where create_time>CURDATE() and id='"+ db.getId() + "' ";
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
		} catch (Exception e) {

		} finally {
			stop(con, stmt, null);
		}
	}

	public static List<String> getSpNameList() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT name FROM sp_info  ";
		List<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String name = rs.getString(1);
				list.add(name);
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

	public static void main(String[] args) {
		System.out.println(getSumPriceByFm().size());
	}

	public static List<String> getYpdistro() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT  channel FROM  `reqcontent_db` where create_time>CURDATE()   and appid='000870' GROUP BY channel";
		List<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String channel = rs.getString(1);
				list.add(channel);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}

	public static TwoTuple<Map<String,List<String>>,Map<String, Integer>> getAppIsNull() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id,fm,price from reqcontent_db where create_time >'2016-12-18 7:00:00' and bieming='LM' and result='0' and appid='' and userdata='' and fm<>'YF' ";
		Map<String,List<String>> map = new HashMap<>();
		Map<String, Integer> mapPrice = new HashMap<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				String fm = rs.getString(2);
				Integer price = rs.getInt(3);
				mapPrice.put(id, price);
				List<String> list = map.get(fm);
				if(list == null){
					list = new ArrayList<>();
				}
				list.add(id);
				map.put(fm, list);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return new TwoTuple<Map<String,List<String>>, Map<String,Integer>>(map, mapPrice);
	}

	public static Map<String, Integer> getAppIsYunPan() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id,price from reqcontent_db where create_time >'2016-12-17 16:36:00' and bieming='LM' and result='0' and appid='' and userdata=''";
		Map<String, Integer> mapPrice = new HashMap<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				Integer price = rs.getInt(2);
				mapPrice.put(id, price);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return mapPrice;
	}
	public static Map<String,List<TwoTuple<String, String>>> getSendSuc() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select fm,appid,channelid from after_pay_log where create_time >CURDATE() and error_code like '%Y' ORDER BY create_time desc";
		Map<String,List<TwoTuple<String, String>>> map = new HashMap<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String fm = rs.getString(1);
				String appid = rs.getString(2);
				String channelid= rs.getString(3);
				List<TwoTuple<String, String>> list = map.get(fm);
				if(list == null){
					list = new ArrayList<>();
				}
				list.add(new TwoTuple<String, String>(appid, channelid));
				map.put(fm, list);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return map;
	}

	public static List<String> getSendYunpanChaennlSuc() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select channelid from after_pay_log where create_time >CURDATE() and error_code like '%Y' and appid='000870'";
		 List<String>  list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String channelid= rs.getString(1);
				list.add(channelid);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}

	public static List<ReqcontentDb> getReqDB09() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id,appid,channel,price from reqcontent_db where create_time >CURDATE() and bieming='LM' and result='0' and appid<>'' and userdata='3'  ";
		List<ReqcontentDb> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				String appid = rs.getString(2);
				String channel = rs.getString(3);
				Integer price = rs.getInt(4);

				ReqcontentDb db = new ReqcontentDb();
				db.setAppid(appid);
				db.setId(id);
				db.setChannel(channel);
				db.setPrice(price);
				list.add(db);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}
	public static List<ReqcontentDb> getReqDB10() {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "select id,appid,channel,price from reqcontent_db where create_time >CURDATE() and bieming='LM' and result='0' and userdata='2'  ";
		List<ReqcontentDb> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				String id = rs.getString(1);
				String appid = rs.getString(2);
				String channel = rs.getString(3);
				Integer price = rs.getInt(4);

				ReqcontentDb db = new ReqcontentDb();
				db.setAppid(appid);
				db.setId(id);
				db.setChannel(channel);
				db.setPrice(price);
				list.add(db);
			}
		} catch (Exception e) {

		} finally {
			stop(con, stmt, rs);
		}
		return list;
	}
}
