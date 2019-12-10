<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewerTop</title>

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
				<li><a href="login.jsp">ログイン</a></li>
				<li><a class="waves-effect waves-light btn">新規登録</a></li>
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
					<h5>サークル入部支援システム</h5>

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

					<div class="center-align">


						<ul class="pagination">
							<li class="disabled"><a href="#!"><i
									class="material-icons">chevron_left</i></a></li>
							<li class="active teal"><a href="#!">1</a></li>
							<li class="waves-effect"><a href="#!">2</a></li>
							<li class="waves-effect"><a href="#!">3</a></li>
							<li class="waves-effect"><a href="#!">4</a></li>
							<li class="waves-effect"><a href="#!">5</a></li>
							<li class="waves-effect"><a href="#!"><i
									class="material-icons">chevron_right</i></a></li>
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
						<div class="input-field col s12">
							<i class="material-icons prefix">search</i> <input type="text"
								id="autocomplete-input" class="autocomplete">

						</div>
						<form action="#">
							<p>
								<label> <input name="submit" type="radio" /> <span>フリーワード</span>
								</label>
							</p>
							<p>
								<label> <input name="submit" type="radio" /> <span>タグ</span>
								</label>
							</p>
						</form>

						<p>
							</br>
						</p>

						<div class="center-align">
							<a class="waves-effect waves-light btn">検索</a> <input
								type="hidden" name="option" value="search">
						</div>
					</div>
				</div>



				<div class="card">
					<div class="card-content">
						<h6 class="teal-text">新規登録</h6>
						<hr class="teal">
						<div class="center-align">
							<a class="waves-effect waves-light btn"
								href="clubRegistration.jsp">サークル新規登録</a>
							<p>
								</br>
							</p>
							<a class="waves-effect waves-light btn"
								href="userRegistration.jsp">ユーザ新規登録</a>
						</div>
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
							<li><a class="grey-text text-lighten-3" href="">お問い合わせ</a></li>
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