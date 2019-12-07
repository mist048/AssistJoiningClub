<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubRegistrationConfirm</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body>

<form action="FromClubRegistrationConfirm" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="clubRegistration">戻る</a></li>
			</ul>
		</div>
	</nav>

<p>登録情報</p>

<table>
<tr><td>サークル名</td><td><%= session.getAttribute("name")%></td></tr>
<tr><td>メールアドレス</td><td><%= session.getAttribute("mail")%></td></tr>
<tr><td>ID</td><td><%= session.getAttribute("id")%></td></tr>
<tr><td>パスワード</td><td><%= session.getAttribute("password")%></td></tr>
</table>

					<div class="center-align">
						<p>
							<input type="hidden" name="user_name" value="">
							<a class="waves-effect waves-light btn" href="FromClubRegistrationConfirm">登録</a>
						</p>

					</div>

</form>

</body>
</html>