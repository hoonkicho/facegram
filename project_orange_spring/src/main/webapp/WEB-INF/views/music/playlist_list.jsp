<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Playlist</title>

<!---------------- 					리스트 전체재생용 ------------------------------------------------------->
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

<!------------------------------------------------------------------------------------------>
<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script>
	function del(idx) { //플레이리스트 삭제
		if (confirm("Delete?") == false) {
			return;
		}
		var url = "playlist_delete.korea";
		var param = "idx="+idx;
		sendRequest(url, param, resultFn, "POST");
	}
	function resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			location.href = "playlist_list.korea";
		}
	}
	//리스트 전체반복재생
	$( function() {

	var my_audio = document.getElementById('my_audio');
	my_audio.volume = 0.5;
	var list_num = 1;
	var play_num = 1;
	var list = new Array();
	var type = new Array();
	
	$("#my_list source").each(function (i) {
	 list_num = i + 1;
	 list[list_num] = $(this).attr("src");
	 type[list_num] = $(this).attr("type");
	 $('#my_list').remove();
	});
	document.getElementById('sos').src = list[1];
	document.getElementById('sos').type = type[1];
	my_audio.play();

	my_audio.addEventListener('ended', function(){
	 var zz = play_num + 1;
	 if(zz > list_num){ zz = 1; }
	 document.getElementById('sos').src = list[zz];
	 document.getElementById('sos').type = type[zz];
	 my_audio.load();
	 my_audio.play();
	 play_num = zz;
	});
	});

</script>
</head>
<body>
	<jsp:include page="index.jsp" />
	<div class="jumbotron text-center">
		<div class="container">
			<h3>PlayList</h3>
			<br>
			<table class="table table-striped" align="center" width="600"
				style="border-collapse: collapse">

				<c:if test="${ empty list }">
					<tr>
						<td align="center" colspan="2">Not Exist Playlist.</td>
					</tr>
				</c:if>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td width="10%">${vo.idx }</td>
						<td>${vo.m_musicfile}<br>
						<audio src="${pageContext.request.contextPath }/resources/musicfile/${ vo.m_musicfile }" controls></audio></td>
						<c:set var="result" value="${vo.memberidx}" />
						<c:if test="${ result eq sessionScope.idx}">
							<!-- 내 플레이리스트만 삭제버튼 -->
							<td><input type="button" class="btn btn-warning btn-sm" value="delete" onclick="del( '${vo.idx}' );"></td>
						</c:if>
					</tr>
				</c:forEach>

				<!-- 전체재생  -->
				<tr>
					<td>ALL PlayList</td>
					<td colspan="2">
						<audio id='my_audio' controls autoplay>
							<source id='sos' src='' type=''>
						</audio>
						<div id="my_list">
							<c:forEach var="vo" items="${list }">
								<source src="${pageContext.request.contextPath }/resources/musicfile/${ vo.m_musicfile }" type="audio/mp3"></source>
							</c:forEach>
						</div>
					</td>
				</tr>
				<!-- 페이징 -->
				<tr>
					<td colspan="5" align="center">
						<input type="button" class="btn btn-warning btn-sm" value="처음"
							onclick="location.href='${url}?page=1&idx=${idx}'">
						<c:if test="${paging.currentPage > 1}">
							<input type="button" class="btn btn-warning btn-sm" value="이전"
								onclick="location.href='${url}?page=${paging.currentPage-1}&idx=${idx}'">
						</c:if>
						<c:if test="${paging.currentPage <= 1}">
							<input type="button" class="btn btn-warning btn-sm" value="이전" disabled="disabled">
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}" step="1">
							<c:if test="${paging.currentPage == i}">
								<input type="button" class="btn btn-warning btn-sm" value="${i}" disabled="disabled">
							</c:if>

							<c:if test="${paging.currentPage != i}">
								<input type="button" class="btn btn-warning btn-sm" value="${i}" onclick="location.href='${url}?page=${i}&idx=${idx}'">
							</c:if>

						</c:forEach>
						<c:if test="${paging.currentPage < paging.totalPage}">
							<input type="button" class="btn btn-warning btn-sm" value="다음"
								onclick="location.href='${url}?page=${paging.currentPage+1}&idx=${idx}'">
						</c:if>
						<c:if test="${paging.currentPage >= paging.totalPage}">
							<input type="button" class="btn btn-warning btn-sm" value="다음" disabled="disabled">
						</c:if> 
						<input type="button" class="btn btn-warning btn-sm" value="끝"
							onclick="location.href='${url}?page=${paging.totalPage}&idx=${idx}'">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>