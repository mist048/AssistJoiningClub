<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userUpdate</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>

<body>

<div class="center-align">
		<a href = "ToTop" class="waves-effect waves-light btn"> トップ </a>
</div>

<p>ユーザ更新</p>

<form action="FromUserUpdate" method="get">

<p><label>ユーザ名<input type="text" name="name" size="20" maxlength="50"></label></p>
<p><label>メールアドレス<input type="email" name="email" size="50" maxlength="256"></label></p>
<p><label>パスワード<input type="password" name="pass" size="20" maxlength="16"></label></p>


<div class="center-align">
		<a href = "FromUserUpdate" class="waves-effect waves-light btn"> 確定 </a>
		<input type="hidden" name="name">
		<input type="hidden" name="password">
		<input type="hidden" name="mail">
		<input type="hidden" name="option" value="confirm">
</div>

</form>

</body>
</html>