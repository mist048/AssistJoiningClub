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
						String id = (String) request.getAttribute("id");
						String name = (String) request.getAttribute("name");
					%>

					<h6>
						<%=session.getAttribute("name")%><!-- name -->
					</h6>
					<h6 class="blue-grey-text">
						<%=session.getAttribute("id")%><!-- ID -->
					</h6>

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
						<p>
							<input type="hidden" name="id" value=<%=id%>> <input
								type="hidden" name="name" value=<%=name%>> <a
								class="waves-effect waves-light btn"
								href="accountDeleteConfirm.jsp">削除</a>
						</p>
					</div>

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