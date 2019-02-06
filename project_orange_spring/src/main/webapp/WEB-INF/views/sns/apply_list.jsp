<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script>
	function addFriend(friendname,friendid) { //수락버튼누르면 내친구목록에 추가
		var url = "myfriend_insert.korea";
		var param = "friendname=" + friendname + "&friendid="+friendid;
		sendRequest(url, param, resultFn, "POST");
	}
	function resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			if (data == 'yes') {
				alert("친구등록하였습니다.");
				if (confirm("친구목록으로 이동하시겠습니까?") == true) {
					location.href = "myfriend_list.korea";
				}
			} else {
				alert("이미 친구입니다.");
			}
		}
	}
	function deleteFriend(idx) {			//친구삭제	
		var url = "apply_delete.korea";
		var param = "idx="+idx ;
		sendRequest(url, param, resultDelFn, "POST");
	}
	function resultDelFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			location.href = "apply_list.korea";
		}
	}
</script>
<title>ApplyList</title>
</head>
<body>
	<jsp:include page="../music/index.jsp" />
	<div class="jumbotron text-center">
		<div class="container">
			<h3>Friend Request</h3>
			<br>
			<table class="table table-striped" align="center" width="600"
				style="border-collapse: collapse">
				<c:if test="${ empty list }">
					<!-- 나에게 친구신청한 유저리스트 -->
					<tr>
						<td align="center">Not Exist Friendlist.</td>
					</tr>
				</c:if>

				<c:forEach var="p" items="${list}">
					<tr>
						<td>Name</td>
						<td>${p.sendname }</td>
						<td align="right"><input type="button" class="btn btn-warning btn-sm" value="approve"
							onclick="addFriend('${p.sendname }','${p.sendid}');"> 
							<input type="button" class="btn btn-warning btn-sm" value="delete"onclick="deleteFriend('${p.idx}');" />
						</td>
					</tr>
				</c:forEach>

				<!-- 페이징 -->
				<tr>
					<td colspan="5" align="center">
						<input type="button" class="btn btn-warning btn-sm" value="처음"
						onclick="location.href='${url}?page=1&id=${id}'">
						<c:if test="${paging.currentPage > 1}">
							<input type="button" class="btn btn-warning btn-sm" value="이전"
								onclick="location.href='${url}?page=${paging.currentPage-1}&id=${id}'">
						</c:if>
						<c:if test="${paging.currentPage <= 1}">
							<input type="button" class="btn btn-warning btn-sm" value="이전" disabled="disabled">
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}" step="1">
							<c:if test="${paging.currentPage == i}">
								<input type="button" class="btn btn-warning btn-sm" value="${i}" disabled="disabled">
							</c:if>
							<c:if test="${paging.currentPage != i}">
								<input type="button" class="btn btn-warning btn-sm" value="${i}"
									onclick="location.href='${url}?page=${i}&id=${id}'">
							</c:if>
						</c:forEach>
						<c:if test="${paging.currentPage < paging.totalPage}">
							<input type="button" class="btn btn-warning btn-sm" value="다음" onclick="location.href='${url}?page=${paging.currentPage+1}&id=${id}'">
						</c:if>
						<c:if test="${paging.currentPage >= paging.totalPage}">
							<input type="button" class="btn btn-warning btn-sm" value="다음" disabled="disabled">
						</c:if>
						<input type="button" class="btn btn-warning btn-sm" value="끝"
							onclick="location.href='${url}?page=${paging.totalPage}&id=${id}'">
					</td>
				</tr>

			</table>
		</div>
	</div>
</body>
</html>