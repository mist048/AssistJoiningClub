<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- materialize CDN -->

</head>
<body bgcolor=#f9f9f9>

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="Totop">戻る</a></li>
			</ul>
		</div>

	</nav>



	<p>
		<br/>
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>
							ログアウトしますか？<br />
						</h6>

							<br/>

						<form action="FromLogout" method="get" name ="formLogoutYes" style="display: inline">
						<input type="hidden" name="option" value="yes">
						<a href = "javascript:formLogoutYes.submit()" class="waves-effect waves-light btn"> はい </a>
						</form>

						<form action="FromLogout" method="get" name ="formLogoutNo" style="display: inline">
						<input type="hidden" name="option" value="no">
						<a href = "javascript:formLogoutNo.submit()" class="waves-effect waves-light btn"> いいえ </a>
						</form>

					</div>

				</div>
			</div>
		</div>
	</div>

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