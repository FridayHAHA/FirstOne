package com.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.entity.Student;
import com.student.service.IStudentService;
import com.student.service.impl.StudentServiceImpl;


public class QuerryAllStudentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询全部
		request.setCharacterEncoding("utf-8");
			
		IStudentService service = new StudentServiceImpl();
		List<Student> students = service.queryAllStudents();
		System.out.println(students);
		request.setAttribute("students", students);
		//因为request域中有数据，因此需要通过请求转发的方式跳转（重定向会丢失request域中的数据）
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
