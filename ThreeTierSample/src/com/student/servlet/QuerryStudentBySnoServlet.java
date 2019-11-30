package com.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.entity.Student;
import com.student.service.IStudentService;
import com.student.service.impl.StudentServiceImpl;


public class QuerryStudentBySnoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询
			request.setCharacterEncoding("utf-8");
				//System.out.println(request.getParameter("sno"));
			int no = Integer.parseInt(request.getParameter("sno"));
			IStudentService service = new StudentServiceImpl();
			Student student = service.queryStudentBySno(no);
			System.out.println(student);
			
			request.setAttribute("student", student);//将查询到的学生放入request域中
			//request域没有数据用重定向
			//有数据就用请求转发
			request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
