<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubRegistrationForAdmin</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body bgcolor=#f9f9f9>



	<nav class="teal">

		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop"></a></li>
			</ul>
		</div>
	</nav>

	<p>
		<br />
	</p>

	<div class="row" style="margin-top: 50px;">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						新規サークルアカウント登録<br />
					</h6>
					<p>
						<br />
					</p>

					<form action="FromClubRegistration" method="post" name="formConfirm">
						<p style="margin-top: 20px;">
							<label>サークル名<input type="text" name="name" size="20"
								maxlength="50"></label>
						</p>
						<p>
							<label>メールアドレス<input type="email" name="mail" size="50"
								maxlength="256"></label>
						</p>
						<p>
							<label>ID<input type="text" name="id" size="20"
								maxlength="10"></label>
						</p>
						<p>
							<label>パスワード<input type="password" name="password" size="20"
								maxlength="16"></label>
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