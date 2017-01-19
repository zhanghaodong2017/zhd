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


public class SendData_ZYHDURL {
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
		String sql="SELECT id,price,orderid,imei FROM `reqcontent_db` where create_time>CURDATE() and fm='ZYHDURL'   and result='0' and userdata='0' and bieming ='ZY' ";

		List<ReqcontentDb> dbs = new ArrayList<>();
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
				ReqcontentDb db = new ReqcontentDb();
				db.setId(id);
				db.setImei(mobile);
				db.setPrice(price);
				db.setPaycode(orderid);
				dbs.add(db);
			}
			System.out.println(dbs.size());

			for (ReqcontentDb reqcontentDb : dbs) {
				String cpparam = reqcontentDb.getPaycode();
				String fee =String.valueOf(reqcontentDb.getPrice()*100);
				String content ="linkid="+reqcontentDb.getId()+"&mobile="+reqcontentDb.getImei()+"&hret=0&cpparam="+cpparam+"&fee="+fee;

				RedirectUtil.redirect2("ZYHDURL",
						"ZYHDURLZY", reqcontentDb.getId(),
						reqcontentDb.getPrice() + "", "0", content,cpparam);
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
