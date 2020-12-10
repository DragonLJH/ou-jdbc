package jg.ou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jg.ou.commom.Student;
import jg.ou.helper.jdbcHelper;

public class studentDao {

	jdbcHelper jdbc = jdbcHelper.INSTANCE;
	
	public final static studentDao INSTANCE = new studentDao(); // 单例

	/**
	 * 
	 * 根据传来的Student对象查询
	 * 返回一个学生集合
	 * 
	 * @param stu
	 * @return List<Student>
	 * 
	 */
	public List<Student> queryInfoByData(Student stu) {
		List<Student> list = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement stmt = null;
		Map<Object,Object> map = new HashMap<Object,Object>();
		int flag = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			String sql;
			sql = "SELECT stu_id, stu_name , stu_sex , stu_age , stu_major FROM student";
			// 判断传来的student对象里包含哪些数据
			if (stu.getStu_id() != 0) {
				// 判断sql语句是否存在where
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_id = ?" ;
				} else {
					sql = sql + " where stu_id = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, stu.getStu_id());
			}
			if (stu.getStu_name() != null) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_name = ?" ;
				} else {
					sql = sql + " where stu_name = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, stu.getStu_name());
			}
			if (stu.getStu_sex() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_sex = ?" ;
				} else {
					sql = sql + " where stu_sex = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, stu.getStu_sex());
			}
			if (stu.getStu_age() != 0) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_age = ?" ;
				} else {
					sql = sql + " where stu_age = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, stu.getStu_age());
			}
			if (stu.getStu_major() != null) {
				if (sql.indexOf("where") != -1) {
					sql = sql + " and stu_major = ?" ;
				} else {
					sql = sql + " where stu_major = ?" ;
				}
				flag = flag + 1 ;
				map.put(flag, stu.getStu_major());
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
				int stu_id = rs.getInt("stu_id");
				int stu_sex = rs.getInt("stu_sex");
				int stu_age = rs.getInt("stu_age");
				String stu_name = rs.getString("stu_name");
				String stu_major = rs.getString("stu_major");
				Student student = new Student(stu_id, stu_sex, stu_age, stu_name, stu_major);
				list.add(student);
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
	 * 根据传来的Student对象集合
	 * 新增学生
	 * 返回成功新增数
	 * 
	 * @param list
	 * @return res
	 * 
	 */
	public int createStudent(List<Student> list) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			for(Student stu : list) {
				String sql = "INSERT INTO student VALUES (?,?,?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, stu.getStu_id());
				stmt.setString(2, stu.getStu_name());
				stmt.setInt(3, stu.getStu_sex());
				stmt.setInt(4, stu.getStu_age());
				stmt.setString(5, stu.getStu_major());
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
	
	
	/**
	 * 
	 * 根据传来的学生集合
	 * 更新修改学生属性
	 * 返回更新成功数
	 * 
	 * @param list
	 * @return res
	 * 
	 * */
	public int updataStudent(List<Student> list) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Map<Object,Object> map = new HashMap<Object,Object>();
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			for(Student stu : list) {
				int flag = 0;
				String sql = "update student set";
				// 判断传来的student对象里包含哪些数据
				if (stu.getStu_name() != null) {
					if (flag == 0) {
						sql = sql + " stu_name = ?" ;
					} else {
						sql = sql + " ,stu_name = ?" ;
					}
					flag = flag + 1 ;
					map.put(flag, stu.getStu_name());
				}
				if (stu.getStu_sex() != 0) {
					if (flag == 0) {
						sql = sql + " stu_sex = ?" ;
					} else {
						sql = sql + " ,stu_sex = ?" ;
					}
					flag = flag + 1 ;
					map.put(flag, stu.getStu_sex());
				}
				if (stu.getStu_age() != 0) {
					if (flag == 0) {
						sql = sql + " stu_age = ?" ;
					} else {
						sql = sql + " ,stu_age = ?" ;
					}
					flag = flag + 1 ;
					map.put(flag, stu.getStu_age());
				}
				if (stu.getStu_major() != null) {
					if (flag == 0) {
						sql = sql + " stu_major = ?" ;
					} else {
						sql = sql + " ,stu_major = ?" ;
					}
					flag = flag + 1 ;
					map.put(flag, stu.getStu_major());
				}
				
				sql = sql + " where stu_id = ?";
				System.out.println(sql);
				stmt = conn.prepareStatement(sql);
				int i;
				for(i = 1; i < map.size()+1;i++) {
					if(map.get(i) instanceof Integer) {
						stmt.setInt(i, (Integer) map.get(i));
					}else if(map.get(i) instanceof String) {
						stmt.setString(i, (String) map.get(i));
					}
				}
				stmt.setInt(i,stu.getStu_id());
				System.out.println(stmt);
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
	
	/**
	 * 
	 * 根据传来的学生id集合删除
	 * 
	 * @param list
	 * @return res
	 * 
	 * */
	public int deleteStudent(List<Integer> list) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			// 打开链接
			conn = jdbc.helper();
			// 执行查询
			// .prepareStatement(sql);
			for(int stu_id : list) {
				String sql = "delete from student where stu_id = ?";
				System.out.println(sql);
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,stu_id);
				System.out.println(stmt);
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
