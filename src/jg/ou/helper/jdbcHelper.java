package jg.ou.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcHelper {
	
	public static final jdbcHelper INSTANCE = new jdbcHelper(); // ����

	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://47.115.29.252:3306/ou?characterEncoding=utf8";
	private final String USER = "root";
	private final String PASS = "root";
	public jdbcHelper() {
		
	}

	public Connection helper() {
		Connection conn = null;
		// ע�� JDBC ����
		try {
			Class.forName(JDBC_DRIVER);

			// ������
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return conn;
	}
}
