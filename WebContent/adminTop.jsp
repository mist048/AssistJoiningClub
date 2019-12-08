<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminTop</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>

<body>

<form action="FromTop" method="get">

<nav class="teal">
		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a href="logout.jsp">ログアウト</a></li>

			</ul>
		</div>
	</nav>

<p>管理者ページ</p>
<p><input type="hidden" name="option" value="tagDisplay">
	<a href="FromAdminTop">タグ一覧</a></p>
<p><input type="hidden" name="option" value="clubDisplay">
	<a href="FromAdminTop">サークルアカウント一覧</a></p>
<p><input type="hidden" name="option" value="userDisplay">
	<a href="FromAdminTop">一般ユーザ一覧</a></p>
<p><input type="hidden" name="option" value="">
	<a href="FromAdminTop">公式サークルアカウント作成</a></p>

</form>

</body>
</html>