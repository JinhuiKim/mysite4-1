package kr.ac.sungkyul.mysite.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public void update(UserVo vo) {// 회원정보수정

		sqlSession.update("user.update", vo);
		/*
		 * catch (SQLException e) { //e.printStackTrace(); throw new
		 * RuntimeException(); }
		 */
	}

	public UserVo get(Long userNo) { //세션넘버를 받아서 정보를 다가져올때
		UserVo vo = sqlSession.selectOne("user.getByNo", userNo);
		return vo;
	}

	public UserVo get(String email) { // 이메일 체크 다오
		UserVo vo = sqlSession.selectOne("user.getByEmail", email);
		//List<UserVo> list = sqlSession.selectList("user.getByEmail", email);
		return vo;
	}

	public UserVo get(String email, String password) {// 로그인 확인

		UserVo userVo = new UserVo();
		userVo.setEmail(email);
		userVo.setPassword(password);

		//만약에 파라미터로 넘겨야 할 매핑 클래스가 없는경우
		/*Map<String, Object>map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		UserVo vo = sqlSession.selectOne("user.getByEmailAndPassword", map); q받아올때는 map.get();
		*/
		
		UserVo vo = sqlSession.selectOne("user.getByEmailAndPassword", userVo);
		return vo;

	}

	public void insert(UserVo vo) {// 회원가입
		sqlSession.insert("user.insert",vo);

	}

	public boolean delete(UserVo vo) {
		return sqlSession.delete("user.delete",vo) !=0;
}
}