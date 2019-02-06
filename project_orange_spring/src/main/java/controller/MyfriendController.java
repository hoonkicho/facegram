package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MyfriendDAO;
import util.Paging;
import vo.MyfriendVO;



@Controller
public class MyfriendController {
	MyfriendDAO myfriend_dao;
	
	public MyfriendController(MyfriendDAO myfriend_dao) {
		this.myfriend_dao = myfriend_dao;
	}
	public MyfriendController() {}
	
	//친구추가
	@RequestMapping("/sns/myfriend_insert.korea")
	@ResponseBody
	String myfriend_insert(HttpServletRequest request,MyfriendVO vo) {
		vo.setMyid((String)request.getSession().getAttribute("id"));
		vo.setMyname( (String)request.getSession().getAttribute("name")	);
	
		MyfriendVO res_vo = myfriend_dao.selectOne( vo );
		if(res_vo==null) {
			myfriend_dao.insert( vo );	
			vo.setMyid( request.getParameter("friendid"));      //내 상대방에게도 내가친구라는걸 보이기위해 반대로 삽입
			vo.setMyname( request.getParameter("friendname"));
			vo.setFriendname((String)request.getSession().getAttribute("name"));
			vo.setFriendid( (String)request.getSession().getAttribute("id"));
			myfriend_dao.insert( vo );	
			return "yes";
		}
		else return"";
	}
	
	//친구리스트
	@RequestMapping("/sns/myfriend_list.korea")
	String myfriend_list(HttpServletRequest request) {
		String page = request.getParameter("page"); 
		
		int currentPage = 1; 
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 10;
		int totalSize = myfriend_dao.myselectCount((String)request.getSession().getAttribute("id"));
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNo", paging.getStartNo()+"");
		map.put("endNo", paging.getEndNo()+"");
		map.put("myid",  (String)request.getSession().getAttribute("id"));
		List<MyfriendVO> list = myfriend_dao.myselect(map);
		request.setAttribute("paging", paging);
		
		request.setAttribute("id", (String)request.getSession().getAttribute("id"));
		request.setAttribute("url", "../sns/myfriend_list.korea");
		request.setAttribute("f_list", list);
		
		return "/WEB-INF/views/sns/Myfriend_list.jsp";
	}
	
	//친구삭제
	@RequestMapping("/sns/myfriend_delete.korea")
	@ResponseBody
	void myfriend_delete(MyfriendVO vo) {
		myfriend_dao.deleteList(vo);
	}
}
