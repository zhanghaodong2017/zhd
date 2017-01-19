

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

import com.htest.HttpUtil;
import com.test.RedirectUtil;


public class SendDataFee {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String lqurl="http://api.173ttl.com/v1/callback/lm/rdo2?";
	public static void main(String[] args) {
		Statement stmt =null;
		ResultSet rs = null;
		Connection con = null;
		String sql="select  id,channel,orderid from reqcontent_db where create_time >CURDATE() and  appid='000929' and userdata='' ";
		try {

			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/fee?useUnicode=true&characterEncoding=UTF-8";
			String user = "fee";
			String password= "Lemi20132015";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			int index = 0;
			Map<String, String> mapCan = new HashMap<>();
			while(rs.next()){
				String id = rs.getString(1);
				String channel = rs.getString(2);
				String orderid = rs.getString(3);
				String param =  "linkid=" +id + "&appid=000929&orderId=" +orderid
						+ "&result=0&extparams="
						+ channel + "&price=10&mobile=&vcode=";
				mapCan.put(id,param);
				index++;
			}

			Set<String> can =mapCan.keySet();
			for (String id : can) {
				String urlReal = lqurl+mapCan.get(id);
				String back = HttpUtil.doGet(urlReal);
				System.out.println(back);
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
