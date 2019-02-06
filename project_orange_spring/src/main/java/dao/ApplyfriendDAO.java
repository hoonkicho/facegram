package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ApplyfriendVO;

public class ApplyfriendDAO {
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void insert(ApplyfriendVO vo) {
		sqlSession.insert("applyfriend.applyfriend_insert", vo);
	}
	
	public ApplyfriendVO selectOne(ApplyfriendVO vo) {
		ApplyfriendVO res_vo = null;
		res_vo = sqlSession.selectOne("applyfriend.applyfriend_one", vo);
		return res_vo;
	}
	
	public List<ApplyfriendVO> select(String receiveid) {
		List<ApplyfriendVO> list = null;
		list = sqlSession.selectList("applyfriend.applyfriend_list",receiveid);
		return list;
	}
	
	public void deleteList(int idx) {
		sqlSession.delete("applyfriend.applyfriend_delete",idx);
	}
	
	//페이징
	public int myselectCount(String id) {
		int count =sqlSession.selectOne("applyfriend.applyfriend_count",id);
		return count;
	}
	public List<ApplyfriendVO> myselect(HashMap<String, String> map) {
		List<ApplyfriendVO> list = null;
		list = sqlSession.selectList("applyfriend.applyfriend_list_page", map);
		return list;
	}
}
