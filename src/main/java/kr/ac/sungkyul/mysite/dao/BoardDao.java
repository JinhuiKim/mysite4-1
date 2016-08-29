package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.mysite.vo.BoardVo;
@Repository
public class BoardDao {
	
	public BoardVo get(Long vno) {
		BoardVo vo = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			

			String sql = "select no, title, content, view_count, user_no, group_no, order_no, depth from board where no=? ";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, vno);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				Long no = rs.getLong(1);
				String title =rs.getString(2);
				String content =rs.getString(3);
				Long viewNo = rs.getLong(4);
				Long userNo = rs.getLong(5);
				Long groupNo = rs.getLong(6);
				Long orderNo = rs.getLong(7);
				Long depth = rs.getLong(8);
				
			vo= new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setViewNo(viewNo);
			vo.setUserNo(userNo);
			vo.setGroupNo(groupNo);
			vo.setOrderNo(orderNo);
			vo.setDepth(depth);
			
			
			
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
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public List<BoardVo> getAll(String kwd) {
		List<BoardVo> list = new ArrayList<BoardVo>();	
		BoardVo vo = null;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			stmt = connection.createStatement();
			 
			String sql ="select a.no, title, b.name, content, reg_date, view_count, b.no as user_no from board a, users b where a.user_no = b.no ";
			if(kwd!=null){
				sql += " and title like '%"+kwd+"%' and content like '%"+kwd+"%' ";
				
			}
				sql += " order by group_no DESC, order_no ASC ";
			
			
				rs = stmt.executeQuery(sql);
			
					
			while(rs.next()){
				Long no = rs.getLong(1);
				
				
				vo =new BoardVo();
				vo.setNo(no);
				list.add(vo);
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
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	
	public boolean viewcount(BoardVo vo) {
		Connection connection =null;
		PreparedStatement pstmt = null;
		int count = 0;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		connection = DriverManager.getConnection(url, "webdb", "webdb");
		
		
		
		String sql = "update BOARD set view_count =? where no=?";
			pstmt= connection.prepareStatement(sql);
			
		Long vcount = vo.getViewNo()+1;
		pstmt.setLong(1, vcount);
		pstmt.setLong(2, vo.getNo());
		
		count = pstmt.executeUpdate();
		
		
		}catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (pstmt != null) {

					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (count==1);
	
	}
	public List<BoardVo> getList(Long pageNo, String kwd) {
		List<BoardVo> list = new ArrayList<BoardVo>();	
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			//String sql ="select a.no, title, b.name, content, reg_date, view_count, b.no from board a, users b where a.user_no = b.no order by group_no DESC, order_no ASC";
			String sql = "select * from (select c.*, rownum as rn from ( select a.no, title, b.name, content, reg_date, view_count, b.no as user_no  from board a, users b where a.user_no = b.no ";
			
			
			
			if(kwd!=null){
			sql += "   and title like '%"+ kwd+ "%' and content like '%"+ kwd +"%' ";
			
			
			}
			sql += "  order by group_no DESC, order_no ASC ) c) where (?-1)*10 + 1 <= rn and rn <= ?*10";
				

			pstmt = connection.prepareStatement(sql);
		
				pstmt.setLong(1, pageNo);
				pstmt.setLong(2, pageNo);
				
			
		
			rs = pstmt.executeQuery();
					
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				String content = rs.getString(4);
				String regDate = rs.getString(5);
				Long viewNo = rs.getLong(6);
				Long userNo = rs.getLong(7);
				
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setViewNo(viewNo);
				vo.setUserNo(userNo);
				
				list.add(vo);
				
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {

					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	
	public boolean delete(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int count= 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			String sql ="delete from board where no =?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			
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
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (count==1);

	}
	public boolean write(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int count= 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			if(vo.getGroupNo()!= null){//답글일때
			//order_no
			String orderSql = "update board set order_no=order_no+1 where group_no = ? and order_no > ?";
			
			pstmt2 = connection.prepareStatement(orderSql);
			pstmt2.setLong(1, vo.getGroupNo());
			pstmt2.setLong(2, vo.getOrderNo());
			
			pstmt2.executeUpdate();
			
			}
			String sql ="insert into board values(seq_board.nextval, ?, ?, sysdate, 0, " ;
		
			if(vo.getGroupNo()!= null){//답글일때
				sql += " ?, ?, ?, ?)";
			

			}else{//답글아닐때
				sql += " nvl((select max(group_no) from board), 0)+1, 1, 1, ?)";
			}
			
			
			pstmt = connection.prepareStatement(sql);
		//no, title, content, reg_date, view_count, group_no, order_no, depth ,user_No

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			
			
			if(vo.getGroupNo()!= null){
				pstmt.setLong(3, vo.getGroupNo());
				pstmt.setLong(4, vo.getOrderNo()+1);
				pstmt.setLong(5, vo.getDepth()+1);
				pstmt.setLong(6, vo.getUserNo());//userno
				
			}
			else{
				pstmt.setLong(3, vo.getUserNo());//userno
					
			}
			
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
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (count==1);

	}
	
	public boolean  modify(BoardVo vo) {
		Connection connection =null;
		PreparedStatement pstmt = null;
		int count=0;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		connection = DriverManager.getConnection(url, "webdb", "webdb");
		
		
		
		String sql = "update BOARD set title =?, content=? where no=?";
		
		pstmt= connection.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getNo());
		
		count = pstmt.executeUpdate();
		
		
		}catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (pstmt != null) {

					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (count==1);
	
	}
}
