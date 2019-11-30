package com.student.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.dao.IStudentDao;
import com.student.entity.Student;
import com.student.util.DBUtil;


public class StudentDaoImpl implements IStudentDao {
	
	private final String URL = "jdbc:mysql://localhost:3306/jdbctest?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private final String USERNAME = "root";
	private final String PASSWORD = "123456";
	
	
	public boolean addStudent(Student student) {
		/*
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			String sql = "insert into student(sno,sname,sage,saddress) values(?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, student.getSno());
			pstmt.setString(2, student.getSname());
			pstmt.setInt(3, student.getSage());
			pstmt.setString(4, student.getSaddress());
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
			if(pstmt != null) pstmt.close();
			if(connection != null) connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		*/
		String sql = "insert into student(sno,sname,sage,saddress) values(?,?,?,?)";
		Object[] params = {student.getSno(),student.getSname(),student.getSage(),student.getSaddress()};
		return DBUtil.executeUpdate(sql, params);
		
	}
	
	
	//根据学号删除学生
	public boolean deleteStudentBySno(int sno) {
		/*
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			String sql = "delete from student where sno = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
			if(pstmt != null) pstmt.close();
			if(connection != null) connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		*/
		String sql = "delete from student where sno = ?";
		Object[] params = {sno};
		return DBUtil.executeUpdate(sql, params);
		
	} 
	
	
	
	
	//根据学号修改学生信息
	public boolean updateStudentBySno(int sno,Student student) {
		String sql = "update student set sname = ?,sage = ?,saddress = ? where sno = ?";
		Object[] params = {student.getSname(),student.getSage(),student.getSaddress(),student.getSno()}; 
		return DBUtil.executeUpdate(sql, params);
		/*
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			String sql = "update student set sname = ?,sage = ?,saddress = ? where sno = ?";
			pstmt = connection.prepareStatement(sql);
			//修改后的内容
			pstmt.setString(1, student.getSname());
			pstmt.setInt(2, student.getSage());
			pstmt.setString(3, student.getSaddress());
			//修改的那个学生ID
			pstmt.setInt(4, sno);
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
			if(pstmt != null) pstmt.close();
			if(connection != null) connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		*/
		
		
	}
	
	
	
	
	
	public boolean isExist(int sno) {
		
		return queryStudentBySno(sno) == null ? false : true;
	
	}
	
	
	
	
	//查询全部学生
	public List<Student> queryAllStudents() {
		
		List<Student> students = new ArrayList<>();
		Student student = null;
		//Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			//String sql = "select * from student";
			//pstmt = connection.prepareStatement(sql);
			String sql = "select * from student";
			rs = DBUtil.executeQuerry(sql, null);
		
			
			
			while(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
				students.add(student);
			}
			
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(DBUtil.connection != null) DBUtil.connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
	
	
	//按学号查询学生
	public Student queryStudentBySno(int sno) {
		
		Student student = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			String sql = "select * from student where sno = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
			}
			
			return student;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(connection != null) connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	
	}

/*
	@Override
	public int getTotalCount() {
		String sql = "select count(1) from student";
		return DBUtil.getTotalCount(sql);
	}
	
	*/
	
	
	
}
