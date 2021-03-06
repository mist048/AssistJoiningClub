<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userRegistration</title>

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
<body bgcolor=#f9f9f9>

	<nav class="teal">

		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>

	</nav>


	<div class="row" style="margin-top: 50px;">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>新規一般ユーザ登録</h6>
					</div>

					<%
						String error = "";
						Integer result = (Integer) request.getAttribute("error");
						if (result != null) {
							if (result == Constant.CONTAINS_BLANK) {
								error = "空欄が含まれています";
							} else if (result == Constant.CONTAINS_EX_CHAR) {
								error = "特殊文字が含まれています";
							} else if (result == Constant.DUPLICATE) {
								error = "IDかメールアドレスが重複しています";
							} else if (result == Constant.EXCEED_NUM_OF_CHAR) {
								error = "定義された文字数を超えています";
							}
						}
					%>
					<p class="red-text"><%=error%></p>
					<p>
						<br />
					</p>

					<form action="FromUserRegistration" method="post"
						name="formConfirm">
						<p style="margin-top: 20px;">
							<label>ID<input type="text" name="id" size="20"
								maxlength="10"></label>
						</p>
						<p>
							<label>ユーザ名<input type="text" name="name" size="20"
								maxlength="50"></label>
						</p>
						<p>
							<label>パスワード<input type="password" name="password"
								size="20" maxlength="16"></label>
						</p>
						<p>
							<label>メールアドレス<input type="email" name="mail" size="50"
								maxlength="256"></label>
						</p>

						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm"> <a
									class="waves-effect waves-light btn"
									href="javascript:formConfirm.submit()">確認画面</a>
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<footer class="page-footer teal" style="margin-top: 50px;">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">お問い合わせ</h5>
					<ul>
						<li><form action="ToContactAdmin" method="post"
								name="formContact">
								<input type="hidden" name="option" value="contactAdmin">
								<a class="grey-text text-lighten-3"
									href="javascript:formContact.submit()">お問い合わせ</a>
							</form></li>
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