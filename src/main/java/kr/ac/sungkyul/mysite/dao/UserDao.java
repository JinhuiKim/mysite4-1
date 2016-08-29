package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.mysite.vo.UserVo;
@Repository
public class UserDao {
	
	public void update(UserVo vo) {//회원정보수정
		Connection connection = null;
		PreparedStatement pstmt = null;
	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
	
						
			Long no = vo.getNo();
			String name = vo.getName();
			String password =vo.getPassword();				
			String gender =vo.getGender();
			
			boolean isPasswordEmpty = "".equals(password);
			
			String sql = null;
			if(isPasswordEmpty == true){
				sql = "update users set name= ?, gender=? where no =?";
			}else{
				sql = "update users set name= ?, password=?, gender=? where no =?";
			}
			pstmt =connection.prepareStatement(sql);
			if(isPasswordEmpty == true){
				pstmt.setString(1, name);
				pstmt.setString(2, gender);
				pstmt.setLong(3, no);
			}
			else{
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.setString(3, gender);
				pstmt.setLong(4, no);
			}
			pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public UserVo get(Long userNo) {
		UserVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			

			String sql = "select no, name, gender from users where no=? ";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, userNo);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
			vo= new UserVo();
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setGender(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
	public UserVo get(String gemail) { //이메일 체크 다오
		UserVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			

			String sql = "select no, name, email from users where email=? ";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, gemail);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
			vo= new UserVo();
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setGender(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}if (pstmt != null) {
					pstmt.close();
				}if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}	
		}
		return vo;
	}
	
	
	public UserVo get(String email, String password) {//로그인 확인 
		UserVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			

			String sql = "select no, name from users where email=? and password=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
		
			vo= new UserVo();
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}

	public boolean insert(UserVo vo) {//회원가입

		PreparedStatement pstmt = null;
		Connection connection = null;
		int count = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "insert into Users values(seq_Users.nextval, ?, ?, ?, ?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getPassword());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {

					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (count == 1);

	}

	public boolean delete(UserVo vo) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		int count = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "delete from User where no =? and password = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {

					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (count == 1);

	}
}
