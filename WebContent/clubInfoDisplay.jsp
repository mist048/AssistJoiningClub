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
				<li><a href="ToMyPage">マイページ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>

		<p>
			<br />
		</p>

		<div class="row">
			<div class="col s12 m8 l6 offset-m2 offset-l3">
				<div class="card">
					<div class="card-content">

						<%
							String name = (String) request.getAttribute("name");
							String mail = (String) request.getAttribute("mail");
							String recogn = (String) request.getAttribute("recogn");
							String link = "";
							if (request.getAttribute("link") != null) {
								link = (String) request.getAttribute("link");
							}
							String intro = "";
							if (request.getAttribute("intro") != null) {
								intro = (String) request.getAttribute("intro");
							}
							String member = "";
							if (request.getAttribute("member") != null) {
								member = (String) request.getAttribute("member");
							}
							String icon = "";
							if (request.getAttribute("icon") != null) {
								icon = (String) request.getAttribute("icon");
							}
							String home = "";
							if (request.getAttribute("home") != null) {
								home = (String) request.getAttribute("home");
							}
						%>

						<h6>
							<%=name%><br />
						</h6>
						<p>
							<br />
						</p>


						<table>
							<tr>
								<td>メールアドレス</td>
								<td><%=mail%></td>
							</tr>
							<tr>
								<td>公認</td>
								<td><%=recogn%></td>
							</tr>
							<tr>
								<td>リンク</td>
								<td><%=link%></td>
							</tr>
							<tr>
								<td>サークル説明文</td>
								<td><%=intro%></td>
							</tr>
							<tr>
								<td>メンバー</td>
								<td><%=member%></td>
							</tr>
							<tr>
								<td>アイコン</td>
								<td><%=icon%></td>
							</tr>
							<tr>
								<td>ホーム画像</td>
								<td><%=home%></td>
							</tr>
						</table>

						<div class="center-align">
							<form action="FromClubInfoDisplay" method="get" name ="formFavorite">
								<p>
									<input type="hidden" name="option" value="favorite"> <a
										class="waves-effect waves-light btn"
										href="javascript:formFavorite.submit()">お気に入り</a>
								</p>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form>

	<footer class="page-footer teal">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">お問い合わせ</h5>
					<ul>
						<li><a class="grey-text text-lighten-3"
								href="contactAdmin.jsp">お問い合わせ</a></li>
						</ul>
				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
				© 2019 クロノスの時計 <a class="grey-text text-lighten-4 right"
					href="https://mlab.im.dendai.ac.jp/~hirota/2019_WSP/">サーバプログラミング演習</a>
			</div>
		</div>
	</footer>
</body>
</html>