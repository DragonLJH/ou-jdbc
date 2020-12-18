package jg.ou.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import jg.ou.dao.achievementDao;
import jg.ou.dao.courseDao;

/**
 * Servlet implementation class AchievementController
 */
@WebServlet("/achievement")
public class AchievementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private courseDao courDao = courseDao.INSTANCE;

	private achievementDao achieveDao = achievementDao.INSTANCE;

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
		int course_id = 0;
		if (course_name != null) {
			Course course = new Course();
			course.setCourse_name(course_name);
			List<Course> courselist = courDao.queryInfoByData(course);
			course = courselist.get(0);
			course_id = course.getCourse_id();
		}

		Achievement achieve = new Achievement(Integer.parseInt(achievement_id), Integer.parseInt(stu_id), course_id,
				Double.parseDouble(result));
		List<Achievement> achievelsit = achieveDao.queryInfoByData(achieve);
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		String course_id = request.getParameter("course_id") == "" ? "0" : request.getParameter("course_id");
		int res = 0;
		int stu_id = (int) session.getAttribute("LoginId");
		int acourse_id = Integer.parseInt(course_id);
		double aresult = 0.0;
		List<Achievement> achieveList = new ArrayList<Achievement>();
		Achievement achievement = new Achievement();
		achievement.setStu_id(stu_id);
		achievement.setCourse_id(acourse_id);
		achievement.setResult(aresult);
		List<Achievement> list = achieveDao.queryInfoByData(achievement);
		if (list.size() == 0) {
			achieveList.add(achievement);
			res = achieveDao.createAchievement(achieveList);
		}
		PrintWriter writer = response.getWriter();
		writer.println(res);
	}

}
