<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>

<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>

<script>
			var b_idCheck = false;
			
			function send(f){
				var name = f.name.value.trim();
				var id = f.id.value.trim();
				var pw = f.pw.value.trim();
				var age = f.age.value.trim();
				var phone = f.phone.value.trim();
				if( !b_idCheck ) {
		               alert("아이디 중복입니다");
		               return;
		        }
				if(pw == ''){
					alert("비밀번호를 입력하세요!");
					f.pw.focus();
					return;
				}
				
				if(name == ''){
					alert("이름을 입력하세요!");
					f.name.focus();
					return;
				}
				var check = /^[0-9]*$/;
				if(age == '' || check.test(age)==false){
					alert("나이는 정수로 입력하세요!");
					f.age.focus();
					return;
				}
				f.action = "insert.korea";
				f.method = "POST";
				f.submit();
			}
			
			function id_check() {
				var id = document.getElementById("id").value.trim();
				if( id == '' ) {
					alert("아이디를 입력하세요");
					return;
				}
				var url = "check_id.korea";
				var param = "id="+id;
				sendRequest( url, param, result_check, "GET" );
			}
			function result_check() {
				if( xhr.readyState == 4 && xhr.status == 200 ) {
					var data = xhr.responseText;
					if( data == 'no' ){
						alert( "이미 사용중인 아이디 입니다." );
						return;
					}
					alert("사용 가능한 아이디 입니다." );
					b_idCheck = true;
				}
			}
		</script>
</head>
<body>
	<jsp:include page="../music/index.jsp" />
	<form>
		<div class="jumbotron text-center">

			<div class="container">
				<div class="raw">

					<h3>Create your Orange Account</h3>
					<br>
					<div class="form-group">
						<label for="NAME">Name <input type="text"
							class="form-control" name="name" placeholder="NAME" /></label>
					</div>
					<br>

					<div class="form-group">
						<label for="ID">ID <input type="text" class="form-control"
							id="id" name="id" placeholder="ID" /> <input type="button"
							value="confirm" class="btn btn-warning btn-sm"
							onclick="id_check();" />
						</label>
					</div>
					<br>

					<div class="form-group">
						<label for="PWD">Password <input type="password"
							class="form-control" name="pw" placeholder="PW"></label>
					</div>
					<br>

					<div class="form-group">
						<label for="AGE">Age <input type="text"
							class="form-control" name="age" placeholder="AGE"></label>
					</div>
					<br>

					<div class="form-group">
						<label for="form-group">Phone <input type="text"
							class="form-control" name="phone" placeholder="PHONE"></label>
					</div>
					<br>

					<div class="form-group">
						<input type="button" value="Join" class="btn btn-warning btn-sm"
							onclick="send(this.form);"> <input type="button"
							value="Cancel" class="btn btn-warning btn-sm"
							onclick="location.href='../music/list.korea';">
					</div>

				</div>
			</div>
		</div>
	</form>
</body>
</html>
