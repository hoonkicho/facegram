<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberList</title>
<style>
.regi {
	witdh: 600px;
	align: center;
	text-align: center;
	margin: auto;
	margin-bottom: 6px;
}
</style>
<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script>
			function del(idx){
				if(confirm("정말 삭제하시겠습니까?") == false) {
					return;
				}
				var url = "member_delete.korea";
				var param = "idx=" + idx;
				sendRequest( url, param, resultFn, "POST" );
			}
			function resultFn() {
				if( xhr.readyState == 4 && xhr.status == 200 ){
					location.href = "member_list.korea";
				}
			}
		</script>
</head>
<body>
	<jsp:include page="../music/index.jsp" />
	<div class="jumbotron text-center">
		<div class="container">
			<h3>Member List</h3>
			<br>

			<table class="table table-striped">
				<thead class="thead-light">
					<tr>
						<th>Index:</th>
						<th>Name:</th>
						<th>ID:</th>
						<th>PW:</th>
						<th>Age:</th>
						<th>Phone:</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="vo" items="${ list }">
							<c:if test="${ vo.name ne '관리자'}">
								<!-- 관리자는 회원리스트에 비공개 -->
								<td>${ vo.idx }</td>
								<td>${ vo.name }</td>
								<td>${ vo.id }</td>
								<td>${ vo.pw }</td>
								<td>${ vo.age }</td>
								<td>${ vo.phone }</td>

								<td><input type="button" class="btn btn-warning btn-sm"
									value="delete" onclick="del('${vo.idx}');" /></td>
					</tr>
					</c:if>
					</c:forEach>
				</tbody>

				<!-- 페이징 -->
				<tfoot>
					<td colspan="7" align="center">
						<input type="button" class="btn btn-warning btn-sm" value="처음"
							onclick="location.href='${url}?page=1&idx=${idx}'">
						<c:if test="${paging.currentPage > 1}">
							<input type="button" class="btn btn-warning btn-sm" value="이전"
								onclick="location.href='${url}?page=${paging.currentPage-1}&idx=${idx}'">
						</c:if><c:if test="${paging.currentPage <= 1}">
							<input type="button" class="btn btn-warning btn-sm" value="이전"
								disabled="disabled">
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}" step="1">
							<c:if test="${paging.currentPage == i}">
								<input type="button" class="btn btn-warning btn-sm" value="${i}"
									disabled="disabled">
							</c:if>

							<c:if test="${paging.currentPage != i}">
								<input type="button" class="btn btn-warning btn-sm" value="${i}"
									onclick="location.href='${url}?page=${i}&idx=${idx}'">
							</c:if>

						</c:forEach>
						<c:if test="${paging.currentPage < paging.totalPage}">
							<input type="button" class="btn btn-warning btn-sm" value="다음"
								onclick="location.href='${url}?page=${paging.currentPage+1}&idx=${idx}'">
						</c:if> 
						<c:if test="${paging.currentPage >= paging.totalPage}">
							<input type="button" class="btn btn-warning btn-sm" value="다음"
								disabled="disabled">
						</c:if>
						<input type="button" class="btn btn-warning btn-sm" value="끝"
							onclick="location.href='${url}?page=${paging.totalPage}&idx=${idx}'">
					</td>

				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>
