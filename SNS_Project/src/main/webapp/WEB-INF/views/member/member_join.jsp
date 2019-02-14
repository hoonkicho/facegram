<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FACEGRAM - 회원가입</title>
		
		<!-- login 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="/resources/css/css_login.css?var=<%=System.currentTimeMillis()%>">
		
		<!-- 웹폰트 -->
		<link href="http://fonts.googleapis.com/earlyaccess/notosanskr.css" rel="stylesheet" type="text/css">
		
		<!-- 아이디 유효성검사할 js파일 (여기에 join() 있음) -->
		<script src="/js/joinCheck.js?var=<%=System.currentTimeMillis()%>"></script>
		
		<!-- AJAX -->
		<script src="/js/httpRequest.js"></script>
		
		<script>
		// 아이디 중복체크 AJAX
		function checkId( id ) {
			var url   = "/member/checkid";
			var param = "id=" + id;
			
			sendRequest( url, param, result_check, "POST" );
		}
	
		// 콜백함수
		function result_check() {
			console.log( xhr.readyState + " / " + xhr.status );
		
			if ( xhr.readyState == 4 && xhr.status == 200 ) {
				var data = xhr.responseText;
				var json = JSON.parse( data );
				
				document.getElementById("checkid").innerHTML = 
					"<p style=\"margin:0 auto; color:" + json.textColor + ";\">" + json.msg + "</p>";
			}
		}
		</script>
		
	</head>
	
	<body>
		<div id="main">
			<a class="facegram" href="/main">FACEGRAM</a>
		</div>
		
		<div id="login">
			<form action="/member/join" method="POST"> 
				<h3>FACEGRAM에 오신 것을 환영합니다.</h3>
				<p>모든 서비스는 회원가입 후 이용 가능합니다.</p>
				<dl>
					<dd>아이디</dd>
					<dd>
						<input style="border:none; background-color: #e2e2e2;" type="text"
						name="member_id" onblur="checkId(this.value)">
						<!-- 아이디 중복체크 결과 뿌려줄 영역 -->
						<div id="checkid"></div>
					</dd>
					
					<dd>비밀번호</dd>
					<dd>
						<input style="border:none; background-color: #e2e2e2;" type="password" name="member_pwd">
					</dd>
					
					<dd>비밀번호 확인</dd>
					<dd>
						<input style="border:none; background-color: #e2e2e2;" type="password" name="pwd_check">
					</dd>
					
					<dd>연락처 ('-' 빼고 입력)</dd>
					<dd>	
						<input style="border:none; background-color: #e2e2e2;" type="text" name="member_phonenumber">
					</dd>
					
					<br>	
					
					<dd>
						<input type="button" value="가입" onclick="join(this.form)">
						<input type="button" value="이전으로" onclick="javascript:history.go(-1)">
					</dd>
				</dl>
			</form>
		</div>
	</body>
</html>