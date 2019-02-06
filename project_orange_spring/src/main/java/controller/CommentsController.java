package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.CommentsDAO;
import vo.CommentsVO;

@Controller
@SessionAttributes("name")
public class CommentsController {
	CommentsDAO comments_dao;
	
	public CommentsController(CommentsDAO comments_dao) {
		this.comments_dao = comments_dao;
	}
	public CommentsController() {}
	
	
	//댓글 추가
	@RequestMapping("/music/comments_insert.korea")
	String goodpoint_add(Model model,CommentsVO vo,String musicidx,HttpServletRequest request) {
		vo.setC_name((String)request.getSession().getAttribute("name"));
		vo.setMemberidx((Integer)request.getSession().getAttribute("idx"));
		vo.setC_goodpoint(0);
		comments_dao.insert(vo);
		return "redirect:/music/view.korea?idx="+vo.getMusicidx();
	}
	
	//댓글 삭제
	@RequestMapping(value="/music/comments_delete.korea",
			produces = "application/text; charset=utf-8")
	@ResponseBody
	String comments_delete(String idx, String name) {
		comments_dao.deleteList(Integer.parseInt(idx));
		return name;
	}
	
	//댓글 좋아요
	@RequestMapping(value="/music/comments_goodpoint_add.korea",
			produces = "application/text; charset=utf-8")
	@ResponseBody
	String comments_goodpoint_add(String idx, String name) {
		comments_dao.update(Integer.parseInt(idx));
		return name;
	}
	
	
}
