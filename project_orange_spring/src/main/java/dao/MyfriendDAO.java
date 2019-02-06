package dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import vo.MyfriendVO;

public class MyfriendDAO {
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void insert(MyfriendVO vo) {
		sqlSession.insert("myfriend.myfriend_insert", vo);
	}
	public MyfriendVO selectOne(MyfriendVO vo) {
		MyfriendVO res_vo = null;
		res_vo = sqlSession.selectOne("myfriend.myfriend_one", vo);
		return res_vo;
	}
	public List<MyfriendVO> select(String myid) {
		List<MyfriendVO> list = null;
		list = sqlSession.selectList("myfriend.myfriend_list",myid);
		return list;
	}
	public void deleteList(MyfriendVO vo) {
		sqlSession.delete("myfriend.myfriend_delete",vo);
	}

	
	//페이징
	public int myselectCount(String id) {
		int count =sqlSession.selectOne("myfriend.myfriend_count",id);
		return count;
	}
	public List<MyfriendVO> myselect(HashMap<String, String> map) {
		List<MyfriendVO> list = null;
		list = sqlSession.selectList("myfriend.myfriend_list_page", map);
		return list;
	}
}
