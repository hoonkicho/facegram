<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FACEGRAM - ERROR</title>
	</head>
	
	<body>
		<div style="text-align: center;">
			<h1>
				<c:out value='${ msg }'/>
			</h1>
			<input style="width: 200px;
					     height: 30px;
					     background: none;
					     border: 1px solid lightgrey;
					     font-size: 15px;"
			type="button" value="이전" onclick="history.back()">
		</div>
	</body>
</html>