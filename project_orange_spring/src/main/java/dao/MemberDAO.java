package dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import vo.MemberVO;


public class MemberDAO {
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public List<MemberVO> selectList() {
		List<MemberVO> list = null;
		list = sqlSession.selectList("join.member_list");
		return list;
	}

	public void insert(MemberVO vo) {
		sqlSession.insert("join.member_insert", vo);
	}
	
	public List<MemberVO> selectList(String id) {
		List<MemberVO> list = null;
		list = sqlSession.selectList("join.member_list_id", id);
		return list;
	}

	public MemberVO selectOne(MemberVO vo) {
		MemberVO res_vo = null;
		res_vo = sqlSession.selectOne("join.member_list_login",vo);
		return res_vo;
	}

	public void deleteList(int idx) {
		sqlSession.delete("join.member_delete",idx);
	}
	
	
	//페이징
	public int selectCount() {
		int count =sqlSession.selectOne("join.member_count");
		return count;
	}
	public List<MemberVO> select(HashMap<String, Integer> map) {
		List<MemberVO> list = null;
		list = sqlSession.selectList("join.member_list_page", map);
		return list;
	}
}