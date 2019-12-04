<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userRegistrationConfirm</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body>

<form action="FromUserRegistrationConfirm" method="get">

<p><input type="submit" value="戻る"></p>
<p>登録情報</p>

<table>
<tr><td>ユーザ名</td><td><%= session.getAttribute("name")%></td></tr>
<tr><td>メールアドレス</td><td><%= session.getAttribute("mail")%></td></tr>
<tr><td>ID</td><td><%= session.getAttribute("id")%></td></tr>
<tr><td>パスワード</td><td><%= session.getAttribute("password")%></td></tr>
</table>

<div class="center-align">
		<a href = "FromUserRegistration" class="waves-effect waves-light btn"> 登録 </a>
</div>

</form>

</body>
</html>