<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music Register Form</title>
<script>
		function checkdata(f) { //뮤직파일 등록
			var m_title = f.m_title.value.trim();
			var m_content = f.m_content.value.trim();
			var m_musicfile = f.m_musicfile.value.trim();
			var m_image = f.m_image.value.trim();
			if( m_title == '' || m_content == '' ) {	//제목 내용 파일업로드 안할경우
				alert("Please Input Title or Content!!");
				return;
			}
			if( m_musicfile == '' || m_image == '' ) {
				alert("Please Upload File!!");
				return;
			}
			f.submit();
		}
</script>

</head>
<body>
	<jsp:include page="index.jsp" />
	<div class="jumbotron text-center">
		<div class="container">
			<h3>Upload MusicPost</h3>
			<br>

			<form method="post" action="insert.korea"
				enctype="multipart/form-data">
				<table align="center" width="600" border="1"
					style="border-collapse: collapse">
					<tr>
						<th width="200">TITLE</th>
						<td><input type="text" name="m_title" placeholder="title"
							style="width: 500px; font-size: 13pt;"></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><textarea rows="5" cols="75"
								name="m_content" placeholder="content" style="font-size: 13pt;"></textarea></td>
					</tr>

					<tr>
						<th width="200">MUSIC</th>
						<td><input type="file" name="m_musicfile"></td>
					</tr>

					<tr>
						<th width="200">IMAGE</th>
						<td><input type="file" name="m_image"></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input
							class="btn btn-warning btn-sm" type="button" value="upload"
							onclick="checkdata(this.form);"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>