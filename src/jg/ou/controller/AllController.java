package jg.ou.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jg.ou.commom.Student;
import jg.ou.dao.achievementDao;
import jg.ou.dao.classroomDao;
import jg.ou.dao.courseDao;
import jg.ou.dao.deptDao;
import jg.ou.dao.studentDao;
import jg.ou.dao.teacherDao;

/**
 * Servlet implementation class AllController
 */
@WebServlet("/all")
public class AllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private studentDao stuDao = studentDao.INSTANCE;
	private teacherDao teaDao = teacherDao.INSTANCE;
	private classroomDao claDao = classroomDao.INSTANCE;
	private deptDao depDao = deptDao.INSTANCE;
	private courseDao courDao = courseDao.INSTANCE;
	private achievementDao achieveDao = achievementDao.INSTANCE;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		stuDao.queryAllStudent();
		teaDao.queryAllTeacher();
		claDao.queryAllClassroom();
		depDao.queryAllDept();
		courDao.queryAllCourse();
		achieveDao.queryAllAchievement();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
