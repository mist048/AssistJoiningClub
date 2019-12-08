<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userInfoDisplayForAdmin</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body>

<form action="FromUserInfoDisplayForAdmin" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><input type="hidden" name="option" value="top">
				<a href="FromUserInfoDisplayForAdmin">トップ</a></li>
			</ul>
		</div>
	</nav>

<p>ユーザ情報</p>

<table>
<tr><td>ユーザ名</td><td><%= session.getAttribute("name")%></td></tr>
<tr><td>メールアドレス</td><td><%= session.getAttribute("mail")%></td></tr>
<tr><td>ID</td><td><%= session.getAttribute("id")%></td></tr>
<tr><td>パスワード</td><td><%= session.getAttribute("password")%></td></tr>
</table>

						<p>
							<input type="hidden" name="option" value="setting">
							<a class="waves-effect waves-light btn" href = "FromUserInfoDisplayForAdmin">設定</a>
						</p>


</form>

</body>
</html>