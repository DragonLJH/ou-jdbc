package jg.ou.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jg.ou.commom.Achievement;
import jg.ou.commom.Course;
import jg.ou.dao.achievementDao;
import jg.ou.dao.courseDao;

/**
 * Servlet implementation class AchievementController
 */
@WebServlet("/achievement")
public class AchievementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private courseDao courDao;
	
	private achievementDao achieveDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AchievementController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String achievement_id = request.getParameter("achievement_id") == "" ? "0"
				: request.getParameter("achievement_id");
		String stu_id = request.getParameter("stu_id") == "" ? "0" : request.getParameter("stu_id");
		String course_name = request.getParameter("course_id") == "" ? null : request.getParameter("course_id");
		String result = request.getParameter("result") == "" ? "0" : request.getParameter("result");

		Course course = new Course();
		course.setCourse_name(course_name);
		List<Course> courselist = courDao.queryInfoByData(course);
		course = courselist.get(1);
		int course_id = course.getCourse_id();

		Achievement achieve = new Achievement(Integer.parseInt(achievement_id), Integer.parseInt(stu_id), course_id,
				Double.parseDouble(result));
		List<Achievement> achievelsit =  achieveDao.queryInfoByData(achieve);
		request.setAttribute("achievelsit", achievelsit);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
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
