<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>index</title>
		
		<!-- css_index 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="/resources/css/css_index.css?var=<%=System.currentTimeMillis()%>">
		
		<!-- 웹폰트 링크 -->
		<link href="http://fonts.googleapis.com/earlyaccess/notosanskr.css" rel="stylesheet" type="text/css">
		
		<!-- ajax js 파일 -->
		<script src="/js/httpRequest.js"></script>
		
		<script>
		// 검색어 자동완성 (AJAX)
		function search ( my_id, user_id ) {
			var url   = "/feed/search";
			var param = "my_id=" + my_id + "&user_id=" + user_id; // 내 아이디 + 검색할 회원 아이디
			
			sendRequest( url, param, search_result, "POST" );
		}
		
		
		// 검색어 자동완성 CALLBACK
		function search_result() {
			console.log( xhr.readyState + " / " + xhr.status );
			
			if ( xhr.readyState == 4 && xhr.status == 200 ) {
				var resultStr = ""; // 검색결과를 종합해서 보여줄 문자열 선언
				
				var data = xhr.responseText;
				var json = JSON.parse( data );
				
				if ( json.length != 0 && json != null ) {
					for ( var i=0; i < json.length; i++ ) {
						
						var img = json[i].profile_image;			// 찾은 회원 프로필사진
						var id  = json[i].member_id;				// 찾은 회원 아이디
						
						console.log("이미지명 : " + img);
						
						resultStr += "<div>" + 
									 "<img style='border-radius: 50%; height:35px; width: 35px; margin-right: 10px;'" + 
									 "src='/resources/profileimages/" + img + "'>" + 
									 "<a style='color:darkslategray; vertical-align: super;' href = /feed/friend?friend_id=" + id +
									 "> " + id + "</a>" + 
									 "</div><br>";
					}
				} 
				else {
					resultStr = "<span style='color:red'>검색결과가 없습니다.<span>";
				}
				
				document.getElementById("result").innerHTML = resultStr;
			} // if() 끝
		} // search_result() 끝
		</script>
	</head>
	
	<body>
		<article>
			<header id="main">
		
			<div id="log">
				<a href="/feed/mypageview"><strong>${ sessionScope.memberVO.member_id }</strong></a> 님 안녕하세요 |
					
					<c:choose>
						<%-- 친구요청온게 없으면 --%>
						<c:when test="${ sessionScope.applyCount eq 0 }">
							<a href="/friendapply/applylist" style="width:50px; position:relative;">
							친구요청 | 
							</a>
						</c:when>

						<%-- 친구요청온게 1개라도 있으면 (1개 이상이면) --%>
						<c:otherwise>
							<a href="/friendapply/applylist" style="width:50px; position:relative;">
							친구요청<span class="nav-counter"> ${ applyCount }</span> | 
							</a>
						</c:otherwise>
					</c:choose>
					
					<%-- 메세지 알림 --%> 
					<c:choose>
						<%-- 채팅창 열기 (새 메세지가 없을 때)--%>
						<c:when test="${ checkcount eq 0 }">
							<a href=
							"javascript:window.open('/message/msglist?my_id=${ sessionScope.memberVO.member_id }
															&profile_image=${ sessionScope.profileVO.profile_image }','?',
															'width=500,height=650,left=500,top=100');"
															class="button" style="width: 50px; position: relative;">
							메세지
							</a>
						</c:when>
						
						<%-- 채팅창 열기 (새 메세지가 있을 때)--%>
						<c:otherwise>
							<a href=
							"javascript:window.open('/message/msglist?my_id=${ sessionScope.memberVO.member_id }
															&profile_image=${ sessionScope.profileVO.profile_image }','?',
															'width=500,height=650,left=600,top=100');"
															class="button" style="width: 50px; position: relative;">
							메세지<span class="nav-counter">${ checkcount }</span>
							</a>
						</c:otherwise>
					</c:choose>
					
				 | <a style="cursor:pointer;" onclick="if( confirm('${ sessionScope.memberVO.member_id }님 로그아웃 하시겠습니까?') ) { location.href='/member/logout' }">로그아웃</a>
			</div>
			
			<div>
				<a id="facegram" href="/feed/mainview">FACEGRAM</a>
			</div>

		</header>
						
		<div id="friends">
			<strong style="font-size: 23px; color: cadetblue;">친구목록</strong>
			<ul>
				<c:forEach var="fr" items="${ friendsList }">
					<li>
						<%-- 사진 --%>
						<img src="/resources/profileimages/${ fr.profile_image }">
						
						<%-- 아이디 --%>
						<a href="/feed/friend?friend_id=${ fr.member_id }"> ${ fr.member_id }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="search">
			<input type="text" placeholder="회원검색" onkeyup="search( '${ sessionScope.memberVO.member_id }', this.value )">
			<!-- 검색결과 뿌려줄 div -->
			<span id="result"></span>
		</div>
		
		</article> 
	</body>
</html>