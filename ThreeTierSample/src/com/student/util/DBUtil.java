package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.student.entity.Student;

//通用的数据库操作方法
public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	public static Connection connection = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	
//通用的增删改
	public static boolean executeUpdate(String sql,Object[] params) {
		//Connection connection = null;
		//PreparedStatement pstmt = null;
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			
			//String sql = "delete from student where sno = ?";
			//pstmt = connection.prepareStatement(sql);
			//pstmt.setInt(1, sno);
			//setXxx()方法的个数依赖于?的个数，而？的个数又与params的元素一致s
			pstmt = getConnection().prepareStatement(sql);
			if(params != null) {
				for(int i = 0;i < params.length;i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			
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
	} 
	
	
	
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection)
	{
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(connection!=null)connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	
	
	//查询总数
	public static int getTotalCount(String sql) {
		int count = -1;
		try {
		
			pstmt = createPreParedStatement(sql, null);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, connection);
		}
		return count;
	}
	
	
	
	public static PreparedStatement createPreParedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		  pstmt = getConnection() .prepareStatement( sql) ;
		  if(params!=null ) {
			  for(int i=0;i<params.length;i++) {
				  pstmt.setObject(i+1, params[i]);
			  }
		  }
		  return pstmt;
	}
	
	
	
	
	//单独获取连接
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	
	
	
	
	//通用的查：返回值是一个集合  (Student,List<Student>,null)
	public static ResultSet executeQuerry(String sql,Object[] params) {
		List<Student> students = new ArrayList<>();
		Student student = null;
		//Connection connection = null;
		//PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//getConnection();
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			//String sql = "select * from student";
			pstmt = getConnection().prepareStatement(sql);
			
		if(params != null) {
			for(int i = 0;i < params.length;i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
			
			rs = pstmt.executeQuery();
			
			return rs;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		/*finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(connection != null) connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
	}
	
	
}
