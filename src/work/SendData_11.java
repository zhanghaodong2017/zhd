package work;

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


public class SendData_11 {
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
		String sql="select id,fm,price,channel, mobile,result,paycode from reqcontent_db "
				+ "where  fm='SARDO'   and create_time>'2016-11-09 12:31:00' and create_time<='2016-11-09 13:08:22'";

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
				String price = rs.getString(3);
				String channel = rs.getString(4);
				String mobile = rs.getString(5);
				String result = rs.getString(6);
				String paycode = rs.getString(7);
				Map<String,String> params = new HashMap<String,String>();
				params.put("linkid", id);
				params.put("result", "0");
				params.put("price", "10");
				params.put("mobile", "");
				params.put("extparams", channel);
				System.out.println(channel);
				RedirectUtil.redirect(fm, "FJA", params);
				System.out.println(params);
				index++;
			}
			System.out.println(index);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{


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
