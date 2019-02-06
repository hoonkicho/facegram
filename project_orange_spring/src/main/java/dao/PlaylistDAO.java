package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import vo.PlaylistVO;

public class PlaylistDAO {
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public PlaylistVO selectOne(PlaylistVO vo) {
		PlaylistVO res_vo = null;
		res_vo = sqlSession.selectOne("playlist.playlist_one", vo);
		return res_vo;
	}
	public void insert(PlaylistVO vo) {
		sqlSession.insert("playlist.playlist_insert",vo);
	}
	public List<PlaylistVO> select(int memberidx) {
		List<PlaylistVO> list = null;
		list = sqlSession.selectList("playlist.playlist_list",memberidx);
		return list;
	}
	// 플레이리스트 삭제
	public void deleteList(int resultidx) {
		sqlSession.delete("playlist.playlist_delete",resultidx);
	}
	
	//페이징
	public int myselectCount(int idx) {
		int count =sqlSession.selectOne("playlist.myplaylist_count",idx);
		return count;
	}
	public List<PlaylistVO> myselect(HashMap<String, Integer> map) {
		List<PlaylistVO> list = null;
		list = sqlSession.selectList("playlist.myplaylist_list_page", map);
		return list;
	}
}
