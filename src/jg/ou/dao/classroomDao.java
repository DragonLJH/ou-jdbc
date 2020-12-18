package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jg.ou.commom.Classroom;
import jg.ou.helper.jdbcHelper;

public class classroomDao {
	jdbcHelper jdbc = jdbcHelper.INSTANCE;

	public final static classroomDao INSTANCE = new classroomDao();
	
	public List<Classroom> queryAllClassroom() {
		List<Classroom> list = new ArrayList<Classroom>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT classroom_id, course_id FROM classroom";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			//新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int classroom_id = rs.getInt("classroom_id");
				int course_id = rs.getInt("course_id");
				Classroom aclassroom = new Classroom();
				aclassroom.setClassroom_id(classroom_id);
				aclassroom.setCourse_id(course_id);
				list.add(aclassroom);
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
	

	public List<Classroom> queryInfoByData(Classroom classroom) {
		List<Classroom> list = new ArrayList<Classroom>();
		Connection conn = null;
		PreparedStatement stmt = null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		int flag = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT classroom_id, course_id FROM classroom";
			// 判断传来的student对象里包含哪些数据
			if (classroom.getClassroom_id() != 0) {
				// 判断sql语句是否存在where
				if (sql.indexOf("where") != -1) {
					sql = sql + " and classroom_id = ?";
				} else {
					sql = sql + " where classroom_id = ?";
				}
				flag = flag + 1;
				map.put(flag, classroom.getClassroom_id());
			}
			if (classroom.getCourse_id() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and course_id = ?";
				} else {
					sql = sql + " where course_id = ?";
				}
				flag = flag + 1;
				map.put(flag, classroom.getClassroom_id());
			}
			// 执行查询
			stmt = conn.prepareStatement(sql);
			for (int i = 1; i < map.size() + 1; i++) {
				System.out.println(map.get(i));
				if (map.get(i) instanceof Integer) {
					stmt.setInt(i, (Integer) map.get(i));
				} else if (map.get(i) instanceof String) {
					stmt.setString(i, (String) map.get(i));
				}
			}
			// 新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int classroom_id = rs.getInt("classroom_id");
				int course_id = rs.getInt("course_id");
				Classroom aclassroom = new Classroom();
				aclassroom.setClassroom_id(classroom_id);
				aclassroom.setCourse_id(course_id);
				list.add(aclassroom);
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

	public int createClassroom(Classroom classroom) {
		int res = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			String sql = "INSERT INTO classroom VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, classroom.getClassroom_id());
			stmt.setInt(2, classroom.getCourse_id());
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

	public int deleteClassroom(int classroomId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			String sql = "delete from classroom where classroom_id = ?";
			System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, classroomId);
			System.out.println(stmt);
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
