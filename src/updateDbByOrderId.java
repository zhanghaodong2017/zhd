

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

import org.apache.commons.lang.math.RandomUtils;

import sun.util.logging.resources.logging;

import com.htest.HttpUtil;
import com.htest.Logs;
import com.monitor.ReqcontentDb;
import com.test.RedirectUtil;


public class updateDbByOrderId {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Map<String, String> dbMap = getdbList();
		System.out.println(dbMap.size());
	}

	private static ReqcontentDb getMsgByOrderId(String orderid) {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="SELECT id,orderid FROM reqcontent_db where create_time >CURDATE() and result='0' and bieming='LM' and fm='zmcallback' ";
		Map<String, String> dbMap = new HashMap<>();
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				String id = rs.getString(1);
//				String orderid = rs.getString(2);
				dbMap.put(id,orderid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stop(stmt, rs, con);
		}
		return null;
	}

	private static Map<String, String> getdbList() {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="SELECT id,orderid FROM reqcontent_db where create_time >CURDATE() and result='0' and bieming='LM' and fm='zmcallback' ";
		Map<String, String> dbMap = new HashMap<>();
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				String id = rs.getString(1);
				String orderid = rs.getString(2);
				dbMap.put(id,orderid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stop(stmt, rs, con);
		}
		return dbMap;
	}
	private static void stop(Statement stmt, ResultSet rs, Connection con) {
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
