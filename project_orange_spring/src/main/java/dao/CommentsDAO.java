package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.CommentsVO;



public class CommentsDAO {
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void insert(CommentsVO cvo) {
		sqlSession.insert("comments.comments_insert", cvo);
	}
	public List<CommentsVO> select() {
		List<CommentsVO> list = null;
		list = sqlSession.selectList("comments.comments_list");
		return list;
	}
	public List<CommentsVO> select(int idx) {
		List<CommentsVO> list = null;
		list = sqlSession.selectList("comments.comments_list_idx",idx);
		return list;
	}
	public void update(int idx) {
		sqlSession.update("comments.comments_update", idx);
	}
	public void deleteList(int idx) {
		sqlSession.delete("comments.comments_delete",idx);
	}
	public int CommentsCount(int idx) {
		int count =sqlSession.selectOne("comments.comments_count",idx);
		return count;
	}
	public List<CommentsVO> select(HashMap<String, Integer> map) {
		List<CommentsVO> list = null;
		list = sqlSession.selectList("comments.comments_list_page", map);
		return list;
	}
}
