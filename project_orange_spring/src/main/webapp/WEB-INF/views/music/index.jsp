<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
<style>
div {
	text-align: center;
}

a {
	text-decoration: none;
}

a:hover {
	color: red;
} /* hover : 마우스 올려졌을 때*/
</style>

<script>
		function searchf(f){
			var search = f.search.value.trim();
			f.submit();
		}
		
		function idsearchf(f){   //idsearch버튼 클릭시 해당 아이디 보여주기 
	         var search = f.search.value.trim();
	      
	         f.action = "../sns/id_list.korea?idsearch="+search;
	         f.method = "POST";
	         f.submit();
	      }
	</script>

</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<form action="../music/search_list.korea">
			<ul class="nav navbar-nav navbar-left">
				<li class="active"><a href="../music/list.korea">Home</a></li>
				<c:choose>
					<c:when test="${sessionScope.name == null}">
						<li class="active">
						<li><a href="../login/login_form.korea">Login</a></li>
						<li><a href="../member/member_insert_form.korea">Join</a></li>

					</c:when>
					<c:otherwise>
						<c:if test="${ sessionScope.authority eq 1 }">
							<!-- 관리자 -->
							<li><a href="../member/member_list.korea">Member List</a></li>
						</c:if>


						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> ${sessionScope.name}님 정보<span
								class="caret"></span>
						</a>

							<ul class="dropdown-menu">
								<li><a href="../music/playlist_list.korea">My PlayList</a></li>
								<li><a href="../sns/apply_list.korea">Friend Request</a></li>
								<li><a href="../sns/msg_list.korea">My Message</a></li>
								<li><a href="../sns/myfriend_list.korea">My Friend</a></li>
								<li><a href="../music/my_list.korea">My Post</a></li>
							</ul>
						<li><a href="../login/logout.korea">Logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</form>

		<div id="navbar" class="navbar-collapse collapse">
			<form class="navbar-form navbar-center" role="search"
				action="../music/search_list.korea">
				<div class="col-xl">
					<input type="text" placeholder="Title, Content, Music, User"
						name="search" class="form-control" aria-label="search">
					<div class="btn-group">
						<button type="button" class="btn btn-default" value="search"
							onclick="searchf(this.form)">
							Music <span class="glyphicon glyphicon-search"></span>
						</button>
						<button type="button" class="btn btn-default" value="idsearch"
							onclick="idsearchf(this.form)">
							ID <span class="glyphicon glyphicon-search"></span>
						</button>
					</div>
				</div>
			</form>
		</div>
	</nav>
</body>
</html>











