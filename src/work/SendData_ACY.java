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

import com.test.RedirectUtil;

public class SendData_ACY {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT vcode,orderid,mobile,price,id FROM `reqcontent_db` where create_time>'2016-12-10' and   bieming <>'LM' and userdata='' and result='0' and  fm='ACYSGRZ' ";
		try {

			String url = "jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password = "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int index = 0;
			while (rs.next()) {

				String vcode = rs.getString(1);
				String orderid = rs.getString(2);
				String mobile = rs.getString(3);
				Integer price = rs.getInt(4);
				String id = rs.getString(5);

				String content="resultCode="+vcode+"&cpPara="+orderid
						+"&phone="+mobile+"&fee="+(price*100)+"&timeStamp="+id;
				System.out.println(content);
				RedirectUtil.redirect2("ACYSGRZ", orderid.substring(0,9), id, price.toString(), "0", content, orderid);
				index++;

			}
			System.out.println(index);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{


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
}
