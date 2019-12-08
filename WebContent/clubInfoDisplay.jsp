<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoDisplay</title>

<!-- materealize CDN -->

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- materialize CDN -->
</head>
<body>

<form action="FromClubInfoDisplay" method="get">
	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>

	<p>
		</br>
	</p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						<%= session.getAttribute("club")%></br>
					</h6>
					<p>
						</br>
					</p>


					<table>
<tr><td>ID</td><td><%= session.getAttribute("id")%></td></tr>
<tr><td>サークル名</td><td><%= session.getAttribute("name")%></td></tr>
<tr><td>メールアドレス</td><td><%= session.getAttribute("mail")%></td></tr>
<tr><td>公認</td><td><%= session.getAttribute("recogn")%></td></tr>
<tr><td>リンク</td><td><%= session.getAttribute("link")%></td></tr>
<tr><td>サークル説明文</td><td><%= session.getAttribute("intro")%></td></tr>
<tr><td>メンバー</td><td><%= session.getAttribute("member")%></td></tr>
<tr><td>アイコン</td><td><%= session.getAttribute("icon")%></td></tr>
<tr><td>ホーム画像</td><td><%= session.getAttribute("home")%></td></tr>
</table>


					</div>
				</div>
			</div>
		</div>
	</div>
</form>

</body>
</html>