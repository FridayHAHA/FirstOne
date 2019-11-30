package com.student.service;

import java.util.List;

import com.student.entity.Student;

public interface IStudentService {
	public boolean addStudent(Student student) ;
	
	//按学号删除学生
	public boolean deleteStudentBySno(int sno) ;
	
	//修改学生信息
	public boolean updateStudentBySno(int sno,Student student) ;
	
	//根据学号查询学生
	public Student queryStudentBySno(int sno) ;

	//查询全部学生
	public List<Student> queryAllStudents();
	
}
