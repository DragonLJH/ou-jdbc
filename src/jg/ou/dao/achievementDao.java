package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jg.ou.commom.Achievement;
import jg.ou.helper.jdbcHelper;

public class achievementDao {

	jdbcHelper jdbc = jdbcHelper.INSTANCE;

	public final static achievementDao INSTANCE = new achievementDao();

	public List<Achievement> queryInfoByData(Achievement achievement) {
		List<Achievement> list = new ArrayList<Achievement>();
		Connection conn = null;
		PreparedStatement stmt = null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		int flag = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT achievement_id, stu_id , course_id , result  FROM student";
			// 判断传来的student对象里包含哪些数据
			if (achievement.getAchievement_id() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and achievement_id = ?";
				} else {
					sql = sql + " where achievement_id = ?";
				}
				flag = flag + 1;
				map.put(flag, achievement.getAchievement_id());
			}
			if (achievement.getStu_id() != 0) {
				// 判断sql语句是否存在where
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_id = ?";
				} else {
					sql = sql + " where stu_id = ?";
				}
				flag = flag + 1;
				map.put(flag, achievement.getStu_id());
			}

			if (achievement.getCourse_id() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and course_id = ?";
				} else {
					sql = sql + " where course_id = ?";
				}
				flag = flag + 1;
				map.put(flag, achievement.getCourse_id());
			}
			if (achievement.getResult() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_age = ?";
				} else {
					sql = sql + " where stu_age = ?";
				}
				flag = flag + 1;
				map.put(flag, achievement.getResult());
			}
			// 执行查询
			stmt = conn.prepareStatement(sql);
			for (int i = 1; i < map.size() + 1; i++) {
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
				int achievement_id = rs.getInt("achievement_id");
				int stu_id = rs.getInt("stu_id");
				int course_id = rs.getInt("course_id");
				double result = rs.getDouble("result");
				Achievement achieve = new Achievement(achievement_id, stu_id, course_id, result);
				list.add(achieve);
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

	public int createAchievement(List<Achievement> achieveList) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			for (Achievement achievement : achieveList) {
				String sql = "INSERT INTO achievement VALUES (?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, achievement.getStu_id());
				stmt.setInt(2, achievement.getCourse_id());
				stmt.setDouble(3, achievement.getResult());
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

}
