<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userMyPage</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>

<body>

<form action="FromUserMyPage" method="get">

<div class="center-align">
		<a class="waves-effect waves-light btn"> トップ </a>
		<input type="hidden" name="option" value="top">
</div>


<p>アカウント情報</p>

<table>
<tr><td>ID</td><td><%= session.getAttribute("id")%></td></tr>
<tr><td>ユーザ名</td><td><%= session.getAttribute("name")%></td></tr>
<tr><td>メールアドレス</td><td><%= session.getAttribute("mail")%></td></tr>
</table>

<div class="center-align">
		<a class="waves-effect waves-light btn"> お気に入りリスト </a>
		<input type="hidden" name="option" value="favoriteClubDisplay">
</div>

<div class="center-align">
		<a class="waves-effect waves-light btn"> 設定 </a>
		<input type="hidden" name="option" value="setting">
</div>

</form>

</body>
</html>