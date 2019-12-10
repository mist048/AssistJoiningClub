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

<form action="FromTop" method="get">

<nav class="teal">
		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a href="http://localhost:8080/AssistJoiningClub/logout.jsp">ログアウト</a></li>
			</ul>

			<ul class="left hide-on-med-and-down">

				<li><a class="waves-effect waves-teal btn-flat">
					<span class="white-text">＜サークル名＞</span>
					</a>
				</li>
			</ul>
		</div>

</nav>

<p>
	<br/>
</p>



	<div class="row">
		<div class="col s12 m12 l12">
					<div class="center-align">
						<div>
							<h5>
							サークル入部支援システム
							</h5>

						</div>
					</div>

				</div>
			</div>
<p>
	<br/>
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
						%>
						<li class="collection-item avatar"><i
							class="material-icons circle">person</i>
							<form action="FromTop" method="post" name="form<%=i%>">
								<span class="title"><%=clubs[i][Constant.NAME]%></span>
								<p><%=clubs[i][Constant.DESCRIPTION]%></p>
								<input type="hidden" name="option" value="clubInfoDisplay">
								<input type="hidden" name="clubId"
									value=<%=clubs[i][Constant.ID]%>> <a
									href="javascript:form<%=i%>.submit()" class="secondary-content">
									<i class="waves-effect waves-teal btn-flat">詳細</i>
								</a>
							</form></li>
						<%
							}
						%>

					</ul>


				</div>
			</div>
		</div>

		<div class="row">
			<div class="col s12 m4 l3">
				<div class="card">
					<div class="card-content">
					<h6 class="teal-text">検索</h6>
					<hr class="teal">
						<div class="input-field col s12">
							<i class="material-icons prefix">search</i>
							<input type="text" id="autocomplete-input" class="autocomplete">

						</div>
						<form action="#">
							<p>
								<label>
									<input name="submit" type="radio" />
									<span>フリーワード</span>
								</label>
							</p>
							<p>
								<label>
									<input name="submit" type="radio" />
									<span>タグ</span>
								</label>
							</p>
						</form>

						<p>
							<br/>
						</p>
						<div class = "center-align">
						<form action="FromTop" method="post" name ="formSerch">
							<input type="hidden" name="option" value="searchResultDisplay">
							<a class="waves-effect waves-light btn" href= "javascript:formSerch.submit()">検索</a>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>

	<p>
		<input type="submit" value="問い合わせ">
	</p>

</form>

</body>
</html>