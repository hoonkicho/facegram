package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MemberDAO;
import util.Paging;
import vo.MemberVO;

@Controller
public class MemberController {
	MemberDAO member_dao;
	public MemberController(MemberDAO member_dao){
		this.member_dao = member_dao;
	}
	public MemberController() {}
	
	//회원가입 폼
	@RequestMapping("/member/member_insert_form.korea")
	String joinfrom() {
		return "/WEB-INF/views/member/join_insert_form.jsp";
	}
	//회원가입
	@RequestMapping("/member/insert.korea")
	String joininsert(Model model, String name, String id, String pw
			,int age, String phone) {
		MemberVO vo = new MemberVO(0, name, id, pw, age, phone, 2); 
		member_dao.insert(vo);
		
		return"/WEB-INF/views/member/joininsert.jsp";
	}
	//id중복체크
	@RequestMapping("/member/check_id.korea")
	@ResponseBody()
	String id_check(String id) {
		List<MemberVO> list = null;
		list = member_dao.selectList(id);
		if( list.size() == 0) {
			return "yes";
		}
		else {
			return "no";
		}
	}
	
	//로그인화면
	@RequestMapping("/login/login_form.korea")
	String login_form() {
		return"/WEB-INF/views/login/login_form.jsp";
	}
	//로그인 정보 일치
	@RequestMapping("/login/login.korea")
	@ResponseBody()
	String login_check(String id,String pw,HttpServletRequest request ) {
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		MemberVO res_vo = member_dao.selectOne(vo);
		if( res_vo == null) {
			return "no";
		}
		else {
			// 정상이라면 '세션'에 데이터 저장
			HttpSession session = request.getSession();
			
			session.setAttribute("res_vo", res_vo);
			session.setAttribute("name", res_vo.getName()); 
			session.setAttribute("idx", res_vo.getIdx()); 
			session.setAttribute("authority", res_vo.getAuthority());
			session.setAttribute("id",res_vo.getId());
			return "yes";
		}
	}
	//로그인 성공 후 메인으로 이동
	@RequestMapping("/login/clear.korea")
	String main_list() {
		return "../music/list.korea";
	}
	//로그아웃시
	@RequestMapping("/login/logout.korea")
	String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("idx");
		request.getSession().removeAttribute("authority");
		request.getSession().removeAttribute("id");
		return "../music/list.korea";
	}
	
	//로그인 여부
	@RequestMapping("/login/check_login.korea")
	String login_check() {
		return "/WEB-INF/views/login/check_login.jsp";
	}
	
	//아이디 찾기
	@RequestMapping("/sns/id_list.korea")
	String id_list(String idsearch,HttpServletRequest request) {
		List<MemberVO> list = member_dao.selectList(idsearch);
		request.setAttribute("list", list);
		return "/WEB-INF/views/sns/id_list.jsp";
	}
	
	//회원 리스트
	@RequestMapping("/member/member_list.korea")
	String member_list(HttpServletRequest request) {
		String page = request.getParameter("page"); 
		int currentPage = 1; 
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 10;
		int totalSize = member_dao.selectCount();
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		
		List<MemberVO> list = member_dao.select(map);
		request.setAttribute("paging", paging);
		
		request.setAttribute("url", "../member/member_list.korea");
		request.setAttribute("list", list);
		return "/WEB-INF/views/member/joinview.jsp";
	}
	
	//회원 삭제
	@RequestMapping("/member/member_delete.korea")
	@ResponseBody()
	void member_delete(String idx) {
		member_dao.deleteList(Integer.parseInt(idx));
	}
}
