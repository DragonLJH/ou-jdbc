package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jg.ou.commom.Dept;
import jg.ou.helper.jdbcHelper;

public class deptDao {
	jdbcHelper jdbc = jdbcHelper.INSTANCE;

	public final static deptDao INSTANCE = new deptDao();
	
	public List<Dept> queryAllDept() {
		List<Dept> list = new ArrayList<Dept>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT dept_id, dept_name FROM dept";
			// 执行查询
			stmt = conn.prepareStatement(sql);
			//新增、更新、删除 >>> executeUpdate(); 查询 >>> executeQuery();
			ResultSet rs = stmt.executeQuery();
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int dept_id = rs.getInt("dept_id");
				String dept_name = rs.getString("dept_name");
				Dept adept = new Dept();
				adept.setDept_id(dept_id);
				adept.setDept_name(dept_name);
				list.add(adept);
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
	

	public List<Dept> queryInfoByData(Dept dept) {
		List<Dept> list = new ArrayList<Dept>();
		Connection conn = null;
		PreparedStatement stmt = null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		int flag = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT dept_id, dept_name FROM dept";
			// 判断传来的student对象里包含哪些数据
			if (dept.getDept_id() != 0) {
				// 判断sql语句是否存在where
				if (sql.indexOf("where") != -1) {
					sql = sql + " and dept_id = ?";
				} else {
					sql = sql + " where dept_id = ?";
				}
				flag = flag + 1;
				map.put(flag, dept.getDept_id());
			}
			if (dept.getDept_name() != null) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and dept_name = ?";
				} else {
					sql = sql + " where dept_name = ?";
				}
				flag = flag + 1;
				map.put(flag, dept.getDept_name());
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
				int dept_id = rs.getInt("dept_id");
				String dept_name = rs.getString("dept_name");
				Dept adept = new Dept();
				adept.setDept_id(dept_id);
				adept.setDept_name(dept_name);
				list.add(adept);
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
	
	public int createDept(Dept dept) {
		int res = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			String sql = "INSERT INTO dept VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dept.getDept_id());
			stmt.setString(2, dept.getDept_name());
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

	public int deleteDept(int deptId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			String sql = "delete from dept where dept_id = ?";
			System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, deptId);
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
