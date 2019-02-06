<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in</title>


<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script>
	function send(f) {
		var id = f.id.value.trim();
		var pw = f.pw.value.trim();

		if (id == '' || pw == '') {
			alert("Input ID or PW!!");
			return;
		}
		var url = "login.korea";
		var param = "id="+id+"&pw="+pw;
		sendRequest(url, param, resultFn, "POST");
	}

	function resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			if (data == 'no') {
				alert("로그인정보가 일치하지 않습니다.");
			} else {
				location.href = "clear.korea";
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="../music/index.jsp" />
	<div class="jumbotron text-center">
		<form>
			<div class="container">
				<div class="row">

					<h3>Sign in to Orange</h3>
					<br>
					<div class="form-group">
						<label for="ID">ID <input type="text" class="form-control"
							name="id" placeholder="Enter ID"></label> <br>
					</div>


					<div class="form-group">
						<label for="PWD">Password <input type="password"
							class="form-control" name="pw" placeholder="Enter passsword" /></label> <br>
					</div>

					<div>
						<div class="form-group">
							<button type="button" value="login"
								class="btn btn-warning btn-sm" onclick="send(this.form);">Sign
								in</button>

						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>


