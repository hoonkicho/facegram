package com.fg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
			
	@RequestMapping("/400")
	public String error400( Model model ) {
		
		model.addAttribute("msg", "잘못된 요청입니다. (400)");
		
		return "errorpage/errorpage";
	}
	
	@RequestMapping("/404")
	public String error404( Model model ) {
		
		model.addAttribute("msg", "요청하신 페이지는 존재하지 않습니다. (404)");
		
		return "errorpage/errorpage";
	}
	
	@RequestMapping("/403")
	public String error403( Model model ) {
		
		model.addAttribute("msg", "접근이 금지되었습니다. (403)");
		
		return "errorpage/errorpage";
	}
	
	@RequestMapping("/405")
	public String error405( Model model ) {
		
		model.addAttribute("msg", "요청된 메소드가 허용되지 않습니다. (405)");
		
		return "errorpage/errorpage";
	}
	
	@RequestMapping("/500")
	public String error500( Model model ) {
		
		model.addAttribute("msg", "서버에 오류가 발생하였습니다. (500)");
		
		return "errorpage/errorpage";
	}
	
	@RequestMapping("/503")
	public String error503( Model model ) {
		
		model.addAttribute("msg", "서비스를 사용할 수 없습니다. (503)");
		
		return "errorpage/errorpage";
	}
}
