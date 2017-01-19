package druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

public class DatabaseOperator {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final int COUNT = 5;

	public DatabaseOperator() {
	}
	 public void insert() throws Exception {
	        String insertSql="insert into student(id,`name`) values(?,?)";
	        Connection conn = dataSource.getConnection();
	        System.out.println(insertSql.toString());
	        PreparedStatement stmt = conn.prepareStatement(insertSql.toString());
	        UUID id = UUID.randomUUID();
	        stmt.setString(1, id.toString());
	        stmt.setString(2, "zhangsan");
	        stmt.execute();
	        stmt.close();
	        conn.close();
	    }
	 public void createTable() throws SQLException {
	      String create_sql="create table student(id char(20),name varchar(20),primary key(id))";
	        Connection conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        System.out.println(create_sql);
	        stmt.execute(create_sql);
	        stmt.close();
	        conn.close();
	    }

}
