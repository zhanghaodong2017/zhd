package com.monitor;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateData {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Statement stmt =null;
		Connection con = null;
//		String sql="SELECT code,type FROM verify_code";
		StringBuffer insert_sql= new StringBuffer();
		insert_sql.append("insert verify_code (code,type) VALUES ");
		try {
			File file = new File("E:\\Wrork4\\day0628\\src\\com\\monitor", "verify20161110.txt");
			FileOutputStream out = new FileOutputStream(file, true);


			long abc = System.currentTimeMillis();
			for (int i = 1; i <= 4; i++) {
				for (int j = 0; j < 2000; j++) {
					abc = abc+10;
					String code = Long.toString(abc, 36);
					String type= i+"";
					insert_sql.append("('"+code+"',"+type+"),");

					out.write((code+","+type+"\r\n").getBytes());

				}
			}
			out.flush();
			out.close();


			String url="jdbc:mysql://rds92rpo26irqpw1ev1q.mysql.rds.aliyuncs.com:3306/tyread_db?useUnicode=true&characterEncoding=UTF-8";
			String user = "ty";
			String password= "ty1234";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			String sql = insert_sql.toString();
			stmt.execute(sql.substring(0,sql.length()-1));
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

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
