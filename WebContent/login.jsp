<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.PageDataManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>


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
				<li><a href="ToTop">戻る</a></li>
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
					<h5>
						システムにログイン<br />
					</h5>

					<%
						String error = "";
						String user = request.getParameter("user");
						Boolean result = (Boolean) session.getAttribute("login");
						if (result != null && !result) {
							error = "ユーザIDかパスワードが間違っています";
						}
					%>
					<p class="red-text"><%=error%></p>
					<p>
						<br />
					</p>

					<form action="FromLogin" method="post" name="formLogin">
						<p>
							<label> <input name="user" value="general" type="radio"
								checked="checked" /> <span>一般</span>
							</label>
						</p>

						<p>
							<label> <input name="user" value="club" type="radio" />
								<span>サークル</span>
							</label>
						</p>

						<p>
							<label> <input name="user" value="admin" type="radio" />
								<span>管理者</span>
							</label>
						</p>


						<p>
							<label>ID<input type="text" name="id" size="20"
								maxlength="10"></label>
						</p>
						<p>
							<label>パスワード<input type="password" name="password"
								size="20" maxlength="16"></label>
						</p>
						<p>
							<br />
						</p>
						<div class="center-align">
							<input type="hidden" name="option" value="login"> <a
								href="javascript:formLogin.submit()"
								class="waves-effect waves-light btn">ログイン </a>
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