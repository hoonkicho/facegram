package controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MessageDAO;
import util.Paging;
import vo.MessageVO;



@Controller
public class MessageController {
	MessageDAO message_dao;
	
	public MessageController(MessageDAO message_dao) {
		this.message_dao = message_dao;
	}
	public MessageController() {}
	
	//메세지 작성
	@RequestMapping("/sns/msg_insert_form.korea")
	String msg_insert_form(HttpServletRequest request,String receivedId) {
		request.setAttribute("receivedId", receivedId);
		return"/WEB-INF/views/sns/msg_form.jsp";
	}
	
	//메세지 삽입
	@RequestMapping("/sns/msg_insert.korea")
	@ResponseBody
	String msg_insert(MessageVO vo,HttpServletResponse response) {
		message_dao.insert(vo);
		return"<script>window.close();</script>";
	}
	//메세지 리스트
	@RequestMapping("/sns/msg_list.korea")
	String msg_list(HttpServletRequest request) {
		String page = request.getParameter("page"); // ~.korea?page=2
		
		int currentPage = 1; // 현재 페이지 번호 (파라미터가 없을 것도 가정) 없으면 기본 1페이지
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 10;
		int totalSize = message_dao.myselectCount((String)request.getSession().getAttribute("id"));
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNo", paging.getStartNo()+"");
		map.put("endNo", paging.getEndNo()+"");
		map.put("receivedId",  (String)request.getSession().getAttribute("id"));
		List<MessageVO> list = message_dao.myselect(map);
		request.setAttribute("paging", paging);
		
		request.setAttribute("id", (String)request.getSession().getAttribute("id"));
		request.setAttribute("url", "../sns/msg_list.korea");
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/sns/msg_list.jsp";		
	}
	//메세지 삭제
	@RequestMapping("/sns/msg_delete.korea")
	@ResponseBody
	String msg_delete(String idx) {
		int res = message_dao.deleteList(Integer.parseInt(idx));
		if(res!=0) {
			return "yes";
		}
		else return"";
	}
}
