<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoDisplay</title>

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
			<%
				if (session.getAttribute("user") != null) {
			%>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToMyPage">マイページ</a></li>
			</ul>
			<%
				}
			%>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>


	<%
		String name = (String) request.getAttribute("name");
		String mail = (String) request.getAttribute("mail");
		String recogn = (String) request.getAttribute("recogn");
		String link = "";
		if (request.getAttribute("link") != null) {
			link = (String) request.getAttribute("link");
		}
		String intro = "";
		if (request.getAttribute("intro") != null) {
			intro = (String) request.getAttribute("intro");
		}
		String member = "";
		if (request.getAttribute("member") != null) {
			member = (String) request.getAttribute("member");
		}
		String icon = "<div  style=\"float:left; background-color:white; width:20%; height:100px; padding-left:13%; margin-top:10px; padding-top:10px;\">"+
		"<i class=\"medium material-icons circle \" style=\"float: left;\">person</i></div>";
		if (request.getAttribute("icon") != null) {
			icon = "<div style=\"float: left; background-color: white; width: 20%; height: 100px; padding-left: 13%; margin-top: 10px; padding-top: 10px;\">"+
					"<img src=\"./images/"+(String)request.getAttribute("icon")+"\" width=50px height=50px style=\"border-radius: 50%;\"></div>";
		}
		String home = "HomeSample.jpg";
		if (request.getAttribute("home") != null) {
			home = (String) request.getAttribute("home");
		}
	%>

	<img src = "./images/<%=home %>" width = 100% height=150px>



	<div style="background-color: white;">

		<%=icon %>


		<div style="float:left; background-color:white; width:60%; height:100px; margin-top:10px; ">
			<h5>
				<%=name%>
			</h5>
			<p class="blue-grey-text">
			<%=recogn%>
			</p>

		</div>


		<div style="float: left; background-color:white; width:20% ; height:100px; margin-top:10px; padding-top:10px">


			<%
				if (session.getAttribute("user") != null && session.getAttribute("user").equals("general")) {
					String clubId = (String) request.getAttribute("clubId");
			%>
			<form action="FromClubInfoDisplay" method="post" name="formFavorite">
				<p>
					<input type="hidden" name="option" value="favorite"> <input
						type="hidden" name="clubId" value=<%=clubId%>>
					<%
						boolean isFavorite = (boolean) request.getAttribute("isFavorite");
							if (isFavorite) {
					%><a
						class="waves-effect waves-light btn-small grey lighten-5 grey-text"
						href="javascript:formFavorite.submit()">登録済み</a>
					<%
						} else {
					%>
					<a class="waves-effect waves-light btn-small "
						href="javascript:formFavorite.submit()">お気に入り登録</a>
					<%
						}
					%>
				</p>
			</form>
			<%
				}
			%>



		</div>

	</div>



	<p>
		<br />
	</p>

	<div class="row">
		<div class="col s12 m8 l7 offset-l1">
			<div class="card">
				<div class="card-content" style="margin-top: 15px;">



					<div id='tags'></div>

					<div style="padding-top: 20px;">
						<label>サークル説明文</label>
						<p><%=intro%></p>
					</div>



				</div>
			</div>
		</div>

		<div class="row">
			<div class="col s12 m4 l3">
				<div class="card">
					<div class="card-content" style="margin-top: 15px;">



							<div>
								<label>メールアドレス</label><br/>
								<p><%=mail%></p>
							</div>

							<div style = "margin-top:10px;">
								<label>リンク</label><br/>
								<a href= <%=link%>><%=link%></a>
							</div>

							<div style = "margin-top:10px;">
								<label>人数</label><br/>
								<p><%=member%>名</p>
							</div>




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
						<li><form action="ToContactAdmin" method="post"
								name="formContact">
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

	<script>

	<%String[][] tags = (String[][]) request.getAttribute("tags");%>
		var tagNames = [
	<%for (int i = 0; i < tags.length; i++) {
				if (i != 0) {
					out.print(",");
				}
				out.print("\"" + tags[i][Constant.NAME] + "\"");
			}%>
		];
		init();
		function init() {
			var tagList = []; //ここが配列になる
			for (var i = 0; i < tagNames.length; i++) {
				tagList.push('<div class="chip">' + tagNames[i] + '</div>'); //ここにpush()がくる
			}
			document.getElementById('tags').innerHTML = tagList.join(''); //innerHTMLへ入れる時にjoin()で文字列にする
		}
	</script>
</body>
</html>