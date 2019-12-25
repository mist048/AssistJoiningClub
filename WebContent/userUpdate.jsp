<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userUpdate</title>

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
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>ユーザ更新</h6>
					</div>

					<p>
						<br/>
					</p>

					<%
						String name = (String) request.getAttribute("name");
						String mail = (String) request.getAttribute("mail");
						String password = (String) request.getAttribute("password");
					%>

					<form action="FromClubUpdate" method="get" name = formConfirm>
						<p>
							<label>ユーザ名<input type="text" name="name" value = "<%=name%>"
							size="20" maxlength="50"></label>
						</p>
						<p>
							<label>メールアドレス<input type="email" name="mail" value = "<%=mail%>"
							size="50" maxlength="256"></label>
						</p>
						<p>
							<label>パスワード<input type="password" name="pass" value = "<%=password%>"
							size="20" maxlength="16"></label>
						</p>


						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm">
								<a class="waves-effect waves-light btn" href = "javascript:formConfirm.submit()">確定</a>
							</p>

						</div>
					</form>

					<div class="center-align">
						<form action="FromClubUpdate" method="post" name=formDelete>
							<input type="hidden" name="option" value="delete"> <a
								class="waves-effect waves-teal btn-flat"
								href="javascript:formDelete.submit()"> <span
								class="red-text">アカウントを削除</span></a><br /> <br />
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