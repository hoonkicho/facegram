package controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.PlaylistDAO;
import util.Paging;
import vo.PlaylistVO;



@Controller
public class PlaylistController {
	PlaylistDAO playlist_dao;
	
	public PlaylistController(PlaylistDAO playlist_dao) {
		this.playlist_dao = playlist_dao;
	}
	public PlaylistController() {}
	
	
	// 플레이리스트 담기
	@RequestMapping("/music/playlist_insert.korea")
	@ResponseBody
	String playlist_insert(String musicidx, String memberidx){
		PlaylistVO vo = new PlaylistVO();
		vo.setMusicidx( Integer.parseInt(musicidx));
		vo.setMemberidx(Integer.parseInt(memberidx));
		
		PlaylistVO res_vo = playlist_dao.selectOne(vo);
		
		String result = "no";
		if(res_vo == null ) {
			result = "yes"; 
			playlist_dao.insert(vo); 
		}
		return result;
	}
	
	// 플레이리스트 이동
	@RequestMapping("/music/playlist_list.korea")
	String playlist_list(HttpServletRequest request) {
		String page = request.getParameter("page"); 
		int currentPage = 1;
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 10;
		int totalSize = playlist_dao.myselectCount((Integer)request.getSession().getAttribute("idx"));
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		map.put("memberidx", (Integer)request.getSession().getAttribute("idx"));
		List<PlaylistVO> list = playlist_dao.myselect(map);
		request.setAttribute("paging", paging);
		
		request.setAttribute("url", "../music/playlist_list.korea");
		request.setAttribute("list", list);
		return"/WEB-INF/views/music/playlist_list.jsp";
	}
	
	// 플레이리스트 삭제
	@RequestMapping("/music/playlist_delete.korea")
	@ResponseBody
	String playlist_delete(String idx) {
		playlist_dao.deleteList(Integer.parseInt(idx));
		return"/music/playlist_list.korea";
	}
	
	// 다른유저의 플레이리스트
	@RequestMapping("/music/otherlist_list.korea")
	String otherlist_list(String idx,HttpServletRequest request) {
	
		String page = request.getParameter("page"); 
		int currentPage = 1; 
		if (page != null && page.equals("") == false) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 10;
		int totalSize = playlist_dao.myselectCount(Integer.parseInt(request.getParameter("idx")));
		Paging paging = new Paging(pageSize, totalSize, currentPage);

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		map.put("memberidx", Integer.parseInt(request.getParameter("idx")));
		List<PlaylistVO> list = playlist_dao.myselect(map);
		request.setAttribute("paging", paging);

		request.setAttribute("idx", Integer.parseInt(request.getParameter("idx")));
		request.setAttribute("url", "../music/otherlist_list.korea");
		request.setAttribute("list", list);
		return "/WEB-INF/views/music/playlist_list.jsp";
	}
	
}

