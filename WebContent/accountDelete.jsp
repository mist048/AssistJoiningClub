<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>accountDelete</title>

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
						String deletedUser = (String) request.getAttribute("deletedUser");
						String id = (String) request.getAttribute("deletedId");
						String name = (String) request.getAttribute("name");

						request.setAttribute("deletedUser", deletedUser);
						request.setAttribute("deletedId", id);
					%>

					<h6>
						<%=name%><!-- name -->
					</h6>
					<!--
					<h6 class="blue-grey-text">
						<%=session.getAttribute("id")%>
					</h6>
					-->

					<br />


					<h5>
						アカウントが削除されます<br />
					</h5>

					<hr>
					<p class="blue-grey-text">
						アカウントの削除プロセスを開始します。<br /> 本システムに登録された情報は全て削除されます。
					</p>



					<!--
						<h6>アカウント削除</h6>

					<p>
						<br />
					</p>



					<table>
						<tr>
							<td>ユーザ名</td>
							<td><%=name%></td>
						</tr>
					</table>

					<br />

					-->


					<p>
						<br />
					</p>


					<div class="center-align">
						<form action="accountDeleteConfirm.jsp" method="post"
							name="formDelete">
							<input type="hidden" name="deletedUser" value=<%=deletedUser%>>
							<input type="hidden" name="deletedId" value=<%=id%>>
							<p>
								<a class="waves-effect waves-light btn"
									href="javascript:formDelete.submit()">削除</a>
							</p>
						</form>
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