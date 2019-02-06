package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.CommentsVO;
import vo.MusicVO;


public class MusicDAO {
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void insert( MusicVO vo ) {
		sqlSession.insert("music.music_insert", vo);
	}
	public List<MusicVO> select() {
		List<MusicVO> list = null;
		list = sqlSession.selectList("music.music_list");
		return list;
	}
	public List<MusicVO> select(String search) {
		List<MusicVO> list = null;
		list = sqlSession.selectList("music.music_search",search);
		return list;
	}
	public MusicVO selectOne(int idx) {
		MusicVO vo = null;
		vo = sqlSession.selectOne("music_one", idx );
		return vo;
	}
	public void update(int idx) {
		sqlSession.update("music.music_update", idx);
	}
	public void deleteList(int idx) {
		sqlSession.delete("music.music_delete",idx);
	}
	
	public List<MusicVO> select(int memberidx) {
	      List<MusicVO> list = null;
	      list = sqlSession.selectList("music.mymusic_list", memberidx);
	      return list;
	}
	
	//페이징
	public int selectCount() {
		int count = sqlSession.selectOne("music.music_count");
		return count;
	}
	public List<MusicVO> select(HashMap<String, String> map) {
		List<MusicVO> list = null;
		list = sqlSession.selectList("music.music_list_page", map);
		return list;
	}
	
	public int myselectCount(int idx) {
		int count = sqlSession.selectOne("music.mymusic_count",idx);
		return count;
	}
	
	public List<MusicVO> myselect(HashMap<String, Integer> map) {
		List<MusicVO> list = null;
		list = sqlSession.selectList("music.mymusic_list_page", map);
		return list;
	}
	
	

	
	
	
	// 잘못된방법
	public int CommentsCount(int idx) {
		int count =sqlSession.selectOne("music.comments_count",idx);
		return count;
	}
	// 잘못된방법
	public List<CommentsVO> select_c(HashMap<String, Integer> map) {
		List<CommentsVO> list = null;
		list = sqlSession.selectList("music.comments_list_page", map);
		return list;
	}
	
	
}
