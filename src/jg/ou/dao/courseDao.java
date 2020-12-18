package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jg.ou.commom.Course;
import jg.ou.helper.jdbcHelper;

public class courseDao {

	jdbcHelper jdbc = jdbcHelper.INSTANCE;
	
	public final static courseDao INSTANCE = new courseDao();
	
	
	public List<Course> queryAllCourse() {
		List<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT course_id, course_name , course_dept , course_teacher FROM course";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			//新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int course_id = rs.getInt("course_id");
				String course_name = rs.getString("course_name");
				String course_dept = rs.getString("course_dept");
				int course_teacher = rs.getInt("course_teacher");
				Course newCourse = new Course(course_id, course_name, course_dept, course_teacher);
				list.add(newCourse);
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
	
	
	public List<Course> queryInfoByData(Course course) {
		List<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		Map<Object,Object> map = new HashMap<Object,Object>();
		int flag = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT course_id, course_name , course_dept , course_teacher FROM course";
			// 判断传来的student对象里包含哪些数据
			if (course.getCourse_id() != 0) {
				// 判断sql语句是否存在where
				if (sql.indexOf("where") != -1) {
					sql = sql + " and course_id = ?" ;
				} else {
					sql = sql + " where course_id = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag,course.getCourse_id());
			}
			if (course.getCourse_name() != null) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and course_name = ?" ;
				} else {
					sql = sql + " where course_name = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag,course.getCourse_name());
			}
			if (course.getCourse_dept() != null) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and course_dept = ?" ;
				} else {
					sql = sql + " where course_dept = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, course.getCourse_dept());
			}
			if (course.getCourse_teacher() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and course_teacher = ?" ;
				} else {
					sql = sql + " where course_teacher = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, course.getCourse_teacher());
			}
			// 执行查询
			stmt = conn.prepareStatement(sql);
			for(int i = 1; i < map.size()+1;i++) {
				System.out.println(map.get(i));
				if(map.get(i) instanceof Integer) {
					stmt.setInt(i, (Integer) map.get(i));
				}else if(map.get(i) instanceof String) {
					stmt.setString(i, (String) map.get(i));
				}
			}
			//新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int course_id = rs.getInt("course_id");
				String course_name = rs.getString("course_name");
				String course_dept = rs.getString("course_dept");
				int course_teacher = rs.getInt("course_teacher");
				Course newCourse = new Course(course_id, course_name, course_dept, course_teacher);
				list.add(newCourse);
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
	
}
