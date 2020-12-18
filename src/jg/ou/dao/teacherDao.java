package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jg.ou.commom.Teacher;
import jg.ou.helper.jdbcHelper;

public class teacherDao {

	jdbcHelper jdbc = jdbcHelper.INSTANCE;

	public final static teacherDao INSTANCE = new teacherDao();
	
	public List<Teacher> queryAllTeacher() {
		List<Teacher> list = new ArrayList<Teacher>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT teacher_id, teacher_name FROM teacher ";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			//新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int teacher_id = rs.getInt("teacher_id");
				String teacher_name = rs.getString("teacher_name");
				Teacher teac = new Teacher();
				teac.setTeacher_id(teacher_id);
				teac.setTeacher_name(teacher_name);
				list.add(teac);
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
		return list;
	}

	/**
	 * 
	 * 根据传来的teacher_id获取teacher对象
	 * 
	 * @param teacher_id
	 * @return teacher
	 * 
	 */
	public Teacher queryTeacherById(int teacher_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Teacher teacher = new Teacher();
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT teacher_id,teacher_name FROM teacher where teacher_id = ?";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, teacher_id);
			// 新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// 通过字段检索
				int teacherId = rs.getInt("teacher_id");
				String teacherName = rs.getString("teacher_name");
				teacher.setTeacher_id(teacherId);
				teacher.setTeacher_name(teacherName);
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
		return teacher;

	}

	/**
	 * 根据传来的list集合新增teache
	 * 
	 * @param list
	 * @return res
	 * 
	 */
	public int createTeacher(List<String> list) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			for (String teacher_name : list) {
				String sql = "INSERT INTO teacher VALUES (?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, teacher_name);
				int result = stmt.executeUpdate();
				res = res + result;
				conn.commit();
			}
			// 完成后关闭
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

	public int deleteTeacher(int teacher_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql = "delete from teacher where teacher_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, teacher_id);
			res = stmt.executeUpdate();
			conn.commit();
			// 完成后关闭
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
