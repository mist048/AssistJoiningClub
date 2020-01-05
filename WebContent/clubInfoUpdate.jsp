<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoUpdate</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

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

	<p></p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h5>
						サークル情報編集<br />
					</h5>
					<hr>

					<%
						String error = "";
					    Boolean result = (Boolean)request.getAttribute("error");
						if (result != null && !result) {
							error = "もう一度入力してください";
						}
					%>
					<p class="red-text"><%=error%></p>
					<p>
						<br />
					</p>

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
					%>

					<form action="FromClubInfoUpdate" method="post" name=formConfirm>

						<div id='tags'></div>
						<div class="input-field col s12">
							<input type="text" id="addTagName"><a
								class="waves-effect waves-light btn" onclick="addChip()">タグ追加</a>
						</div>

						<p>
							<label>サークル名<input type="text" name="name"
								value="<%=name%>" size="20" maxlength="50"></label>
						</p>
						<p>
							<label>メールアドレス<input type="email" name="mail"
								value="<%=mail%>" size="50" maxlength="256"></label>
						</p>
						<p>
							<label>公認<input type="text" name="recgon"
								value="<%=recogn%>" size="20" maxlength="50"></label>
						</p>
						<p>
							<label>リンク<input type="text" name="link"
								value="<%=link%>" size="20" maxlength="256"></label>
						</p>
						<p>
							<label>紹介文<textarea name="intro" maxlength="2000" cols="100" rows="20"><%=intro%></textarea></label>
						</p>
						<p>
							<label>メンバー<input type="text" name="member"
								value="<%=member%>" size="20" maxlength="16"></label>
						</p>

						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm"> <a
									class="waves-effect waves-light btn"
									href="javascript:formConfirm.submit()">確定</a>
							</p>
						</div>
					</form>

					<br />

				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						画像編集<br />
					</h6>
					<p>
						<br />
					</p>

					<form action="FileHandleServlet" method="post"
						name=formImageConfirm enctype="multipart/form-data">
						<p>
							<label>アイコン<input type="file" name="icon"></label>
						</p>
						<br />
						<p>
							<label>ホーム画像<input type="file" name="home"></label>
						</p>
						<br />

						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm"> <a
									class="waves-effect waves-light btn"
									href="javascript:formImageConfirm.submit()">確定</a>
							</p>
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
				tagList
						.push('<div class="chip">'
								+ tagNames[i]
								+ '<i class="close material-icons" onclick="handleClick()" id="chip">close</i></div>'
								+ '<input type="hidden" name="addTagNames[]" value="'+tagNames[i]+'">'); //ここにpush()がくる
			}

			document.getElementById('tags').innerHTML = tagList.join(''); //innerHTMLへ入れる時にjoin()で文字列にする

			(function() {
				i
				function handleClick(event) {
					tagNames.splice(this.x, 1);
					init();
				}

				var closeIcons = document
						.getElementsByClassName('close material-icons');

				for (var i = 0, l = closeIcons.length; i < l; ++i) {
					closeIcons[i].addEventListener('click', {
						x : i,
						handleEvent : handleClick
					}, false);
				}
			}());
		}

		function addChip() {
			var addTagName = document.getElementById('addTagName').value;
			if (addTagName != "" && tagNames.indexOf(addTagName) == -1
					&& tagNames.length <
	<%=Constant.MAX_OF_HOLD_TAG%>
		) {
				tagNames.push(addTagName);
				init();
			}
		}
	</script>
</body>
</html>