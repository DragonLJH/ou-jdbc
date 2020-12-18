package jg.ou.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jg.ou.commom.Achievement;
import jg.ou.commom.Course;
import jg.ou.commom.Login;
import jg.ou.commom.Student;
import jg.ou.dao.achievementDao;
import jg.ou.dao.courseDao;
import jg.ou.dao.loginDao;
import jg.ou.dao.manageDao;
import jg.ou.dao.studentDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private studentDao stuDao = studentDao.INSTANCE;
	private loginDao login = loginDao.INSTANCE;
	private manageDao manage = manageDao.INSTANCE;
	private courseDao courDao = courseDao.INSTANCE;
	private achievementDao achieveDao = achievementDao.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		String stu_id = request.getParameter("stu_id");
		String pwd = request.getParameter("pwd");
		int id = Integer.parseInt(stu_id);
		String management = manage.queryManageByName(id);
		Login al = login.queryLoginByid(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("404.jsp");

		if (al.getPwd().equals(pwd)) {
			session.setAttribute("LoginId", id);
			if (management.equals("admin")) {
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				Student stu = new Student();
				stu.setStu_id(id);
				List<Student> list = stuDao.queryInfoByData(stu);
				Student aStudent = list.get(0);
				request.setAttribute("aStudent", aStudent);
				Achievement achievement = new Achievement();
				achievement.setStu_id(id);
				List<Achievement> achieveList = achieveDao.queryInfoByData(achievement);
				request.setAttribute("achieveList", achieveList);
				Course course = new Course();
				List<Course> courseList = courDao.queryInfoByData(course);
				request.setAttribute("courseList", courseList);
				dispatcher = request.getRequestDispatcher("student.jsp");
			}
		}

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
