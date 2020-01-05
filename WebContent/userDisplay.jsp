<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userDisplay</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>

<body>

	<nav class="teal">

		<div class="nav-wrapper">
			<form action="ToTop" method="post">
				<ul class="left hide-on-med-and-down">
					<li><a href="ToTop">トップ</a></li>
				</ul>
			</form>
		</div>
	</nav>


	<p>
		<br />
	</p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>ユーザ一覧</h6>
					</div>

					<ul class="collection">

						<%
							String[][] users = (String[][]) request.getAttribute("users");
							for (int i = 0; i < users.length; i++) {
						%>

						<li class="collection-item avatar"><i
							class="material-icons circle">person</i>
							<form action="FromUserDisplay" method="get" name="form<%=i%>">
								<span class="title"><%=users[i][Constant.NAME]%></span> <input
									type="hidden" name="option" value="userInfoDisplayForAdmin">
								<input type="hidden" name="id"
									value=<%=users[i][Constant.ID]%>> <a
									href="javascript:form<%=i%>.submit()" class="secondary-content">
									<i class="waves-effect waves-teal btn-flat">詳細</i>
								</a>
							</form></li>

						<%
							}
						%>
					</ul>

					<div class="center-align">


						<ul class="pagination">
							<%
								int firstIndex = (int) request.getAttribute("firstIndex");
								if (firstIndex > 0) {
							%>
							<li class="disabled"><form action="FromUserDisplay"
									method="post" name="formBefore">
									<input type="hidden" name="option" value="userDisplay">
									<input type="hidden" name="firstIndex"
										value=<%=firstIndex - Constant.MAX_OF_DISPLAYS%>> <a
										href="javascript:formBefore.submit()"> <i
										class="material-icons">chevron_left</i></a>
								</form></li>
							<%
								}
							%>
							<%
								int numOfPages = (int) request.getAttribute("numOfPages");
								for (int i = 0; i < numOfPages; i++) {
									String state = "waves-effect";
									if (i * Constant.MAX_OF_DISPLAYS == firstIndex) {
										state = "active teal";
									}
							%>
							<li class="<%=state%>">
								<form action="FromUserDisplay" method="post" name="formTo<%=i%>">
									<input type="hidden" name="option" value="userDisplay">
									<input type="hidden" name="firstIndex"
										value="<%=i * Constant.MAX_OF_DISPLAYS%>"> <a
										href="javascript:formTo<%=i%>.submit()"><%=i + 1%></a>
								</form>
							</li>
							<%
								}
							%>
							<%
								Boolean next = (Boolean) request.getAttribute("next");
								if (next != null && next == true) {
							%>
							<li class="waves-effect"><form action="FromUserDisplay"
									method="post" name="formNext">
									<input type="hidden" name="option" value="userDisplay">
									<input type="hidden" name="firstIndex"
										value=<%=firstIndex + Constant.MAX_OF_DISPLAYS%>><a
										href="javascript:formNext.submit()"><i
										class="material-icons">chevron_right</i></a>
								</form></li>
							<%
								}
							%>
						</ul>
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