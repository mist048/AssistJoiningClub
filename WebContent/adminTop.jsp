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

<body bgcolor=#f9f9f9>

	<nav class="teal">
		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a href="logout.jsp">ログアウト</a></li>

			</ul>
		</div>
	</nav>

	<p>
	<br/>
	<p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
							<h5>
								管理者トップページ<br />
							</h5>
							<hr>

						<div class="center-align" style = "margin-top: 30px;">
							<form action="FromAdminTop" method="post" name="formTag">
								<p>
									<input type="hidden" name="option" value="tagDisplay">
									<a class="waves-effect waves-light btn"
										href="javascript:formTag.submit()">タグ一覧</a>
								</p>
							</form>

							<br />

							<form action="FromAdminTop" method="post" name="formClub">
								<p>
									<input type="hidden" name="option" value="clubDisplay">
									<a class="waves-effect waves-light btn"
										href="javascript:formClub.submit()">サークルアカウント一覧</a>
								</p>
							</form>

							<br />

							<form action="FromAdminTop" method="post" name="formUser">
								<p>
									<input type="hidden" name="option" value="userDisplay">
									<a class="waves-effect waves-light btn"
										href="javascript:formUser.submit()">一般ユーザ一覧</a>
								</p>
							</form>

							<br />

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="page-footer teal" style="width: 100%; position: absolute; bottom: 0;">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">お問い合わせ</h5>
					<ul>
						<li><form action="ToContactAdmin" method="post" name="formContact">
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