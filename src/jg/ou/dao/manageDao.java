package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jg.ou.helper.jdbcHelper;

public class manageDao {
	jdbcHelper jdbc = jdbcHelper.INSTANCE;
	public final static manageDao INSTANCE = new manageDao();

	public String queryManageByName(int manage_admin) {
		String res = "";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT manage_name FROM manage where manage_admin = ?";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, manage_admin);
			// 新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				res = rs.getString("manage_name");
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return res;
	}
}
