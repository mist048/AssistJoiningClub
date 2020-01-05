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
						String icon = "";
						if (request.getAttribute("icon") != null) {
							icon = (String) request.getAttribute("icon");
						}
						String home = "";
						if (request.getAttribute("home") != null) {
							home = (String) request.getAttribute("home");
						}
					%>


					<h5>
						<%=name%>
					</h5>
					<%
						String clubId = (String) request.getAttribute("clubId");
					%>
					<form action="FromClubInfoDisplay" method="post"
							name="formFavorite">
							<p>
								<input type="hidden" name="option" value="favorite"> <input
									type="hidden" name="clubId" value=<%=clubId%>> <a
									class="waves-effect waves-light btn-small"
									href="javascript:formFavorite.submit()">お気に入り登録</a>
									<a
									class="waves-effect waves-light btn-small grey lighten-5 grey-text"
									href="javascript:formFavorite.submit()">登録済み</a>
							</p>
						</form>
					<p>
						<br />
					</p>

					<div id='tags'></div>

					<div>

					</div>

					<table>

						<tr>
							<td>サークル説明文</td>
							<td><%=intro%></td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><%=mail%></td>
						</tr>
						<tr>
							<td>公認</td>
							<td><%=recogn%></td>
						</tr>
						<tr>
							<td>リンク</td>
							<td><%=link%></td>
						</tr>

						<tr>
							<td>メンバー</td>
							<td><%=member%></td>
						</tr>
						<tr>
							<td>アイコン</td>
							<td><%=icon%></td>
						</tr>
						<tr>
							<td>ホーム画像</td>
							<td><%=home%></td>
						</tr>
					</table>





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