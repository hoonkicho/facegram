<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up Done</title>
</head>
<body>
	<jsp:include page="../music/index.jsp" />
	<div class="jumbotron text-center">
		<form>
			<div class="container">
				<div class="row">

					<h3>Thank you! Sign up is Completed</h3>
					<br>
					<div class="form-group">
						<input type="button" class="btn btn-warning btn-sm" value="Login"
							onClick="location.href='../login/login_form.korea'"> <input
							type="button" class="btn btn-warning btn-sm" value="Main"
							onClick="location.href='../music/list.korea'">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>