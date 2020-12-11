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

import jg.ou.commom.Student;
import jg.ou.dao.studentDao;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private studentDao stuDao = studentDao.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
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
		String stu_id = request.getParameter("stu_id") == "" ? "0" : request.getParameter("stu_id");
		String stu_name = request.getParameter("stu_name") == "" ? null : request.getParameter("stu_name");
		String stu_sex = request.getParameter("stu_sex") == null ? "0" : request.getParameter("stu_sex");
		String stu_age = request.getParameter("stu_age") == "" ? "0" : request.getParameter("stu_age");
		String stu_major = request.getParameter("stu_major") == "" ? null : request.getParameter("stu_major");
		Student student = new Student(Integer.parseInt(stu_id), Integer.parseInt(stu_sex), Integer.parseInt(stu_age),
				stu_name, stu_major);
		List<Student> stuList = stuDao.queryInfoByData(student);
		request.setAttribute("stuList", stuList);
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
		String stu_id = request.getParameter("stu_id");
		String stu_name = request.getParameter("stu_name");
		String stu_sex = request.getParameter("stu_sex");
		String stu_age = request.getParameter("stu_age");
		String stu_major = request.getParameter("stu_major");
		Student student = new Student(Integer.parseInt(stu_id), Integer.parseInt(stu_sex), Integer.parseInt(stu_age),
				stu_name, stu_major);
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(student);
		int res = stuDao.createStudent(stuList);
		PrintWriter writer = response.getWriter();
		writer.println(res);
	}

}
