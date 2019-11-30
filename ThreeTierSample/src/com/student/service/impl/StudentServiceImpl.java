package com.student.service.impl;

import java.util.List;

import com.student.dao.IStudentDao;
import com.student.dao.impl.StudentDaoImpl;
import com.student.entity.Student;
import com.student.service.IStudentService;

//业务逻辑层：逻辑性的增删改查（增：查+增），对dao层进行组装
public class StudentServiceImpl implements IStudentService{
	IStudentDao studentDao = new StudentDaoImpl();
	
	//增加学生
	public boolean addStudent(Student student) {
		if( !studentDao.isExist(student.getSno())) {//不存在
			studentDao.addStudent(student);
			return true;
		}else {
			System.out.println("此人已存在");
			return false;
		}
	}
	
	
	//按学号删除学生
	public boolean deleteStudentBySno(int sno) {
		if(studentDao.isExist(sno)) {
			return studentDao.deleteStudentBySno(sno);
		}
		return false;
	}
	
	
	
	//修改学生信息
	public boolean updateStudentBySno(int sno,Student student) {
		if(studentDao.isExist(sno)) {
			return studentDao.updateStudentBySno(sno, student);
		}
		return false;
	}
	
	
	//根据学号查询学生
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	
	
	//查询全部学生
	public List<Student> queryAllStudents(){
		return studentDao.queryAllStudents();
	}
	
	
}
