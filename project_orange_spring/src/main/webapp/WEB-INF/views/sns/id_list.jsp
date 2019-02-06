<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SearchId</title>
<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script>
		function friend( receiveid, checkidx ) { 
			if(checkidx == 0){	//비로그인
				location.href ="../login/check_login.korea";
				return;
			}
			var url = "apply_insert.korea";
			var param = "receiveid="+receiveid;
			sendRequest(url, param, resultFn, "POST");
		}
		function resultFn() {
			if( xhr.readyState == 4 && xhr.status == 200 ) {
				var data = xhr.responseText;
				if( data == 'yes' ) {
					alert("친구요청완료");
				}
				else {
					alert("이미 친구요청하셨습니다.");
				}
			}
		}
		
		function show_msg( id,checkidx ) {
			if(checkidx == 0){	//비로그인
				location.href = "../login/check_login.korea";
				return;
			}
			var url = "msg_insert_form.korea?receivedId="+id;
			window.open(url, "메세지보내기", "width=500, height=300, left=400, top=50");
		}
		
		
	</script>
</head>
<body>
	<jsp:include page="../music/index.jsp" />
	<div class="jumbotron text-center">
		<div class="container">
			<h3>ID Search</h3>
			<br>
			<table class="table table-striped" align="center" width="600"
				style="border-collapse: collapse">
				<c:if test="${ empty list }">
					<tr>
						<td align="center">Not Exist Id.</td>
						<!-- 아이디로 친구요청하기, 쪽지보내기 -->
					</tr>
				</c:if>
				<c:forEach var="s" items="${list}">
					<tr>
						<td>ID</td>
						<td><a href="../chating.jsp">${s.id}</a></td>
						<td align="right"><c:set var="result" value="${s.id}" /> <c:if
								test="${ result ne sessionScope.id }">
								<!-- 내자신에게 쪽지보내기버튼 친구요청버튼 없애기-->
								<input type="button" class="btn btn-warning btn-sm" value="sendMessage"
									onclick="show_msg('${s.id}','${ sessionScope.idx}');" />
								<input type="button" class="btn btn-warning btn-sm" value="Friend Request"
									onclick="friend('${s.id }','${sessionScope.idx }');" />
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>