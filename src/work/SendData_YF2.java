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

import org.apache.commons.lang.math.RandomUtils;

import com.monitor.ReqcontentDb;
import com.test.RedirectUtil;


public class SendData_YF2 {
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
		String sql="SELECT id,price,orderid,mobile,channel FROM `reqcontent_db` where create_time>'2016-12-10' and   bieming <>'LM' and userdata='' and result='0' and fm='YF' and bieming ='BC'  ";

		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				String id = rs.getString(1);
				Integer price = rs.getInt(2);
				String orderid = rs.getString(3);
				String mobile = rs.getString(4);
				String channel = rs.getString(5);

				Map<String,String> params = new HashMap<String,String>();
				params.put("linkid", id);
				params.put("result", "0");
				params.put("price", price.toString());
				params.put("mobile", "");
				params.put("extparams", channel);
				params.put("orderid", orderid);
				//paycode.substring(0,2)
				RedirectUtil.redirect("YF","BC", params);
				System.out.println(params);
			}


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
