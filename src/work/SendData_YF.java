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


public class SendData_YF {
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
		String sql="SELECT id,price,orderid,imei FROM `reqcontent_db` where create_time>'2016-12-10' and   bieming <>'LM' and userdata='' and result='0' and fm='YF' and bieming in('ZY','LP','HR','YQ','KL','NY')";

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


			for (ReqcontentDb reqcontentDb : dbs) {
				String reqsid = reqcontentDb.getPaycode();
				String price =String.valueOf(reqcontentDb.getPrice()*100);
				String content ="spnumber=1065842410&message=30000961028908&orderid="+reqcontentDb.getId()+"&mobile="+reqcontentDb.getImei()+"&tradeid=07E91F36E6&channelid=7100001&reqsid="+reqsid+"&actiontime=2016-12-12T10:18:17&price="+price;
				System.out.println(content);
				if (!reqsid.startsWith("LM")) {
					if (reqsid.startsWith("ZY") || reqsid.startsWith("LP")
							|| reqsid.startsWith("HR") || reqsid.startsWith("KL")
							|| reqsid.startsWith("YQ")) {

						RedirectUtil.redirect2("YF",
								"YF" + reqsid.substring(0, 2), reqcontentDb.getId(),
								reqcontentDb.getPrice() + "", "0", content,reqsid);
					} else if (reqsid.startsWith("NY")) {
						RedirectUtil.redirectPost("YF",
								"YF" + reqsid.substring(0, 2), reqcontentDb.getId(),
								reqcontentDb.getPrice() + "", "0", content);
					}

				}
			}
			System.out.println(dbs.size());

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
