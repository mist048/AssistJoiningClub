<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>contactAdmin</title>

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
				<li><a href="ToMyPage">マイページ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
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

					<h5>
						システムに関する問い合わせ<br />
					</h5>
					<hr>
					<p class = "blue-grey-text">
					システムに関する導入やログイン、パスワードの変更など、
					システムの管理者にご気軽にご相談ください。下記メールアドレスにお問い合わせいただければ、
					できるだけ早急にご連絡いたします。
					</p>

					<%
						String adminMail = (String) request.getAttribute("adminMail");

					%>
					<div style="margin-top: 10px;">
						<label style="margin-top:">管理者メールアドレス</label>

						<font size = 4>
						<p>
							<%=adminMail%>
						</p>
						</font>
					</div>


					<div style="margin-top: 20px;">

					<font size = 1>
					<p class = "blue-grey-text">
					※ご連絡の際はお手数ですが件名に「入部支援システム」とお書きください。<br/>
					</p>
					</font>
					</div>



				</div>
			</div>
		</div>
	</div>

	<footer class="page-footer teal footer-copyright" style="width: 100%; position: absolute; bottom: 0;">
		<div class="footer-copyright">
			<div class="container">
				© 2019 クロノスの時計 <a class="grey-text text-lighten-4 right"
					href="https://mlab.im.dendai.ac.jp/~hirota/2019_WSP/">サーバプログラミング演習</a>
			</div>
		</div>
	</footer>


</body>
</html>