package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.MessageVO;

public class MessageDAO {
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void insert(MessageVO vo) {
		sqlSession.insert("message.message_insert", vo);
	}
	public MessageVO selectOne(MessageVO vo) {
		MessageVO res_vo = null;
		res_vo = sqlSession.selectOne("message.message_one", vo);
		return res_vo;
	}
	public List<MessageVO> select(String receivedId) {
		List<MessageVO> list = null;
		list = sqlSession.selectList("message.message_list",receivedId);
		return list;
	}
	public int deleteList(int idx) {
		int res = 0;
		res = sqlSession.delete("message.message_delete",idx);
		return res;
	}
	//페이징
	public int myselectCount(String id) {
		int count =sqlSession.selectOne("message.message_count",id);
		return count;
	}
	public List<MessageVO> myselect(HashMap<String, String> map) {
		List<MessageVO> list = null;
		list = sqlSession.selectList("message.message_list_page", map);
		return list;
	}
}
