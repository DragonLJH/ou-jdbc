package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jg.ou.commom.Login;
import jg.ou.helper.jdbcHelper;

public class loginDao {

	jdbcHelper jdbc = jdbcHelper.INSTANCE;

	public final static loginDao INSTANCE = new loginDao();

	public Login queryLoginByid(int stuid) {
		Login alogin = new Login();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT stu_id , stu_pwd FROM student where stu_id = ?";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, stuid);
			// 新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int stu_id = rs.getInt("stu_id");
				String stu_pwd = rs.getString("stu_pwd");
				alogin.setStu_id(stu_id);
				alogin.setPwd(stu_pwd);
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

		return alogin;
	}

	public int updatequeryLoginByid(Login login) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "UPDATE student SET stu_pwd = ? WHERE (stu_id = ?);";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, login.getPwd());
			stmt.setInt(2, login.getStu_id());
			// 新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			res = stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			try {
				conn.rollback();
				return res;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
