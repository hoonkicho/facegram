package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ApplyfriendDAO;
import util.Paging;
import vo.ApplyfriendVO;


@Controller
public class ApplyfriendController {
	ApplyfriendDAO applyfriend_dao;
	
	public ApplyfriendController(ApplyfriendDAO applyfriend_dao) {
		this.applyfriend_dao = applyfriend_dao;
	}
	public ApplyfriendController() {}
	
	//친구요청
	@RequestMapping("/sns/apply_insert.korea")
	@ResponseBody
	String apply_insert(ApplyfriendVO vo,HttpServletRequest request) {
		vo.setSendid(	(String)request.getSession().getAttribute("id")	);	
		vo.setSendname(	(String)request.getSession().getAttribute("name")	);
		
		ApplyfriendVO res_vo = applyfriend_dao.selectOne( vo );
		if(res_vo == null) {
			applyfriend_dao.insert( vo );
			return "yes";
		}
		else return "no";
	}
	
	//친구요청 리스트
	@RequestMapping("/sns/apply_list.korea")
	String apply_insert(HttpServletRequest request) {
		String page = request.getParameter("page"); 
		
		int currentPage = 1; 
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 10;
		int totalSize = applyfriend_dao.myselectCount((String)request.getSession().getAttribute("id"));
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNo", paging.getStartNo()+"");
		map.put("endNo", paging.getEndNo()+"");
		map.put("receiveid",  (String)request.getSession().getAttribute("id"));
		List<ApplyfriendVO> list =applyfriend_dao.myselect(map);
		request.setAttribute("paging", paging);
		
		request.setAttribute("id", (String)request.getSession().getAttribute("id"));
		request.setAttribute("url", "../sns/apply_list.korea");
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/sns/apply_list.jsp";
	}
	
	// 친구요청 삭제
	@RequestMapping("/sns/apply_delete.korea")
	@ResponseBody
	void apply_delete(String idx) {
		applyfriend_dao.deleteList(Integer.parseInt(idx));
	}
}
