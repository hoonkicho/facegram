package controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dao.CommentsDAO;
import dao.MusicDAO;
import util.Paging;
import vo.CommentsVO;
import vo.MusicVO;


@Controller
public class MusicController {
	MusicDAO music_dao;
	CommentsDAO comments_dao;
	public MusicController(MusicDAO music_dao,CommentsDAO comments_dao) {
		this.music_dao = music_dao;
		this.comments_dao = comments_dao;
	}
	public MusicController() {}
	
	//메인
	@RequestMapping("/music/list.korea")
	public String musiclist(Model model,HttpServletRequest request) {
		String page = request.getParameter("page");
		int currentPage = 1; 
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 9;
		int totalSize = music_dao.selectCount();
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startNo", paging.getStartNo()+"");
		map.put("endNo", paging.getEndNo()+"");
	
		List<MusicVO> list = music_dao.select(map);
		
		model.addAttribute("list",list);
		model.addAttribute("paging", paging);
		model.addAttribute("url", "../music/list.korea");
		return"/WEB-INF/views/music/music_list.jsp";
	}
	
	//상세뷰
	@RequestMapping(value="/music/view.korea",
			produces = "application/text; charset=utf-8")
	public String musicview(Model model,String idx,String name,HttpServletRequest request) {

		MusicVO vo = music_dao.selectOne(Integer.parseInt( idx));
		vo.setName(name);
		model.addAttribute("vo",vo);
		
		String page = request.getParameter("page");
		int currentPage = 1;
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 5;
		int totalSize = comments_dao.CommentsCount(Integer.parseInt(request.getParameter("idx")) );
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		map.put("memberidx",   Integer.parseInt(idx) );
		List<CommentsVO> list = comments_dao.select(map);
		request.setAttribute("paging", paging);
		
		request.setAttribute("name",name);
		request.setAttribute("idx", Integer.parseInt(idx) );
		request.setAttribute("url", "../music/view.korea");
		request.setAttribute("list", list);
		return"/WEB-INF/views/music/music_content.jsp";
	}
	
	
	//게시물 작성
	@RequestMapping("/music/insert_form.korea")
	String form() {
		return"/WEB-INF/views/music/music_reg_form.jsp";
	}
	//음악게시물 삽입
	@RequestMapping("/music/insert.korea")
	String music_regi( MultipartHttpServletRequest request) {
		MultipartFile mf = request.getFile("m_musicfile");
		MultipartFile mf2 = request.getFile("m_image");
		String path = request.getSession().getServletContext().getRealPath("/resources/musicfile");
		System.out.println( "파일이 저장될 경로2 : " + path );
		String m_musicfile =  mf.getOriginalFilename();
		String m_image = mf2.getOriginalFilename();
		File uploadFile = new File( path + m_musicfile );
		File uploadFile2 = new File( path + m_image);
		
		try {
			mf.transferTo( uploadFile );
			mf2.transferTo(uploadFile2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MusicVO vo = new MusicVO();
		vo.setM_title(request.getParameter("m_title"));
		vo.setM_content(request.getParameter("m_content"));
		vo.setM_musicfile(m_musicfile);
		vo.setM_image(m_image);
		vo.setM_goodpoint(0); // 좋아요	
		
		if(request.getSession().getAttribute("idx")==null) {
			vo.setMemberidx(0);
			vo.setName("NO register");
		}
		else {
			vo.setMemberid((String)request.getSession().getAttribute("id")); //회원 ID
			vo.setMemberidx((Integer)request.getSession().getAttribute("idx")); //회원인덱스
			vo.setName((String)request.getSession().getAttribute("name")); //회원이름
		}
		music_dao.insert(vo);
		return"list.korea";
	}
	//게시물 좋아요
	@RequestMapping(value="/music/goodpoint_add.korea",
			produces = "application/text; charset=utf-8")
	@ResponseBody
	String goodpoint_add(String idx,String name) {
		music_dao.update(Integer.parseInt(idx));
		return name;
	}
	//게시물 리스트
	@RequestMapping("/music/my_list.korea")
	String my_list(HttpServletRequest request,String idx) {
		String page = request.getParameter("page"); 
		
		int currentPage = 1; 
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 9;
		int totalSize = music_dao.myselectCount((Integer)request.getSession().getAttribute("idx"));
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		map.put("memberidx",  (Integer)request.getSession().getAttribute("idx"));
		List<MusicVO> list = music_dao.myselect(map);
		request.setAttribute("paging", paging);
		
		
		request.setAttribute("url", "../music/my_list.korea");
		request.setAttribute("list", list);
		return "/WEB-INF/views/music/music_list.jsp";
	}
	
	//게시물 삭제
	@RequestMapping("/music/music_delete.korea")
	@ResponseBody
	void music_delete(String idx,String name) {
		music_dao.deleteList(Integer.parseInt(idx));
	}
	
	// 다른사람의 게시물
	@RequestMapping("/music/other_list.korea")
	String otherlist_list(HttpServletRequest request) {
		String page = request.getParameter("page"); 
		int currentPage = 1;
		if( page != null && page.equals("") == false ) {
			currentPage = Integer.parseInt(page);
		}
		int pageSize = 9;
		int totalSize = music_dao.myselectCount(Integer.parseInt(request.getParameter("idx")) );
		Paging paging = new Paging( pageSize, totalSize, currentPage );
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		map.put("memberidx",   Integer.parseInt(request.getParameter("idx")) );
		List<MusicVO> list = music_dao.myselect(map);
		
		request.setAttribute("paging", paging);
		request.setAttribute("idx", Integer.parseInt(request.getParameter("idx")) );
		request.setAttribute("url", "../music/other_list.korea");
		request.setAttribute("list", list);
		return"/WEB-INF/views/music/music_list.jsp";
	}
	
	// 음악탐색
	@RequestMapping("/music/search_list.korea")
	String search_list(String search,HttpServletRequest request){
		List<MusicVO> list = music_dao.select(search);
		request.setAttribute("list", list);
		return"/WEB-INF/views/music/music_list.jsp";
	}
}
