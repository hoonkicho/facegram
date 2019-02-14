package com.fg.controller;


import org.springframework.stereotype.Controller;
import com.fg.dao.FriendsDAO;


@Controller
public class FriendsController {
	FriendsDAO friends_dao;
	
	final static String VIEW_PATH = "/WEB-INF/views/"; // 접두 경로
	
	// 페이지에 따라 중간 경로들을 다르게 지정
	final static String PATH_MAIN = "main/";
	final static String PATH_FEED = "feed/";
	final static String PATH_MEMBER = "member/";
	
	public FriendsController() {}
	
	public FriendsController( FriendsDAO friends_dao ) {
		this.friends_dao = friends_dao;
	}
	
}
