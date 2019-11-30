package com.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.entity.Student;
import com.student.service.IStudentService;
import com.student.service.impl.StudentServiceImpl;


public class UpdateStudentServlet extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//修改
		request.setCharacterEncoding("utf-8");
		//获取修改人的学号
		int no = Integer.parseInt(request.getParameter("sno"));
		
		//修改后的内容
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		
		//将修改后的内容封装到一个实体类中（javabean）
		Student student = new Student(name,age,address);
		
		//调用service层修改信息
		IStudentService service = new StudentServiceImpl();
		boolean result = service.updateStudentBySno(no, student);
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		if(result) {
			//response.getWriter().print("修改成功！");
			//修改完成过后，重新跳转到查询所有学生并显示
			response.sendRedirect("QuerryAllStudentServlet");
		}else {
			response.getWriter().print("修改失败！");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
