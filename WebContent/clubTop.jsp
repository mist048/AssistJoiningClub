<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubTop</title>

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
				<li><a href="logout.jsp">ログアウト</a></li>
			</ul>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToMyPage">マイページ</a></li>
			</ul>
		</div>

	</nav>

	<p>
		<br />
	</p>



	<div class="row">
		<div class="col s12 m12 l12">
			<div class="center-align">
				<div>
					<h5>サークル入部支援システム</h5>

				</div>
			</div>

		</div>
	</div>
	<p>
		<br />
	</p>



	<div class="row">
		<div class="col s12 m8 l7 offset-l1">
			<div class="card">
				<div class="card-content">
					<h6 class="teal-text">サークル一覧</h6>
					<hr class="teal">

					<ul class="collection">

						<%
							String[][] clubs = (String[][]) request.getAttribute("clubs");
							for (int i = 0; i < clubs.length; i++) {
								String intro = "";
								if (clubs[i][Constant.DISPLAY_INTRO] != null) {
									intro = clubs[i][Constant.DISPLAY_INTRO];
								}
								String icon = "person";
								if (clubs[i][Constant.DISPLAY_ICON] != null) {
									icon = "<img src=./images/" + clubs[i][Constant.DISPLAY_ICON] + " width=50px height=50px>";
								}
						%>
						<li class="collection-item avatar">
							<form action="FromTop" method="post" name="form<%=i%>">
								<i class="material-icons circle"><%=icon%> </i> <span
									class="title"><%=clubs[i][Constant.DISPLAY_NAME]%></span>
								<p><%=intro%></p>
								<input type="hidden" name="option" value="clubInfoDisplay">
								<input type="hidden" name="clubId"
									value=<%=clubs[i][Constant.DISPLAY_ID]%>> <a
									href="javascript:form<%=i%>.submit()" class="secondary-content">
									<i class="waves-effect waves-teal btn-flat">詳細</i>
								</a>
							</form>
						</li>
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
							<li class="disabled"><form action="ToTop" method="post"
									name="formBefore">
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
								<form action="ToTop" method="post" name="formTo<%=i%>">
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
							<li class="waves-effect"><form action="ToTop" method="post"
									name="formNext">
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

		<div class="row">
			<div class="col s12 m4 l3">
				<div class="card">
					<div class="card-content">
						<h6 class="teal-text">検索</h6>
						<hr class="teal">
						<form action="FromTop" method="post" name="formSearch">
							<div class="input-field col s12">
								<i class="material-icons prefix">search</i> <input type="text"
									name="keyword" id="autocomplete-input" class="autocomplete">

							</div>

							<p>
								<label> <input type="radio" name="type" value="keyword"
									checked="checked" /> <span>フリーワード</span>
								</label>
							</p>
							<p>
								<label> <input type="radio" name="type" value="tag" />
									<span>タグ</span>
								</label>
							</p>

							<p>
								<br />
							</p>

							<div class="center-align">
								<input type="hidden" name="option" value="searchResultDisplay">
								<a class="waves-effect waves-light btn"
									href="javascript:formSearch.submit()">検索</a>
							</div>
						</form>
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
						<li><form action="FromTop" method="post" name="formContact">
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