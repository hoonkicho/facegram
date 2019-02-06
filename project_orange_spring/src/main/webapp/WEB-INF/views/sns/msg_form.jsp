<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
<script>
	
		function send( f ) {
			var receivedId = f.receivedId.value.trim();
			var sendIdx = f.sendIdx.value.trim();
			var sendId = f.sendId.value.trim();
			var msg = f.msg.value.trim();
			if(msg==''){				//메세지 내용 없으면
				alert("Please Input Message!!");	
				return;
			}
			f.submit();
		}
	</script>
</head>
<body>
	<form action="msg_insert.korea" method="post">
		<table align="center" width="400" border="1"
			style="border-collapse: collapse;">
			<tr>
				<th>Receiver</th>
				<td><input type="text" name="receivedId" value="${receivedId}"
					readonly></td>
			</tr>
			<tr>
				<td><input type="hidden" name="sendIdx"
					value="${sessionScope.idx}"></td>
			</tr>
			<tr>
				<th>Sender</th>
				<td><input type="text" name="sendId" value="${sessionScope.id}"
					readonly></td>
			</tr>

			<tr>
				<td colspan="2"><textarea name="msg" rows="5" cols="40"></textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="button" value="send"
					onclick="send(this.form);"></td>
			</tr>
		</table>
	</form>

</body>
</html>