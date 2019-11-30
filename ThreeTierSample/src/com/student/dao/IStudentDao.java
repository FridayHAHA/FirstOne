package com.student.dao;


import java.util.List;

import com.student.entity.Student;

public interface IStudentDao {
	public boolean addStudent(Student student) ;
	//根据学号删除学生
	public boolean deleteStudentBySno(int sno) ;
	
	//public int getTotalCount();//查询总数据数
	
	//public List<Student> queryStudentsByPage();
	
	//根据学号修改学生信息
	public boolean updateStudentBySno(int sno,Student student) ;

	public boolean isExist(int sno) ;
	
	//查询全部学生
	public List<Student> queryAllStudents() ;

	public Student queryStudentBySno(int sno) ;
}