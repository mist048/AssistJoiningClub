<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>clubUpdate</title>
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
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- materialize CDN -->
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

</head>
<body bgcolor=#f9f9f9>

	<nav class="teal">
		<div class="nav-wrapper">
			<%
				if (!session.getAttribute("user").equals("admin")) {
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


	<p>
		<br />
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h5>
						サークルアカウント更新<br />
					</h5>
					<hr>

					<%
						String error = "";
						Integer result = (Integer) request.getAttribute("error");
						if (result != null) {
							if (result == Constant.CONTAINS_BLANK) {
								error = "空欄が含まれています";
							} else if (result == Constant.CONTAINS_EX_CHAR) {
								error = "特殊文字が含まれています";
							} else if (result == Constant.DUPLICATE) {
								error = "メールアドレスが重複しています";
							} else if (result == Constant.EXCEED_NUM_OF_CHAR) {
								error = "定義された文字数を超えています";
							}
						}
					%>
					<p class="red-text"><%=error%></p>
					<p>
						<br />
					</p>

					<%
						String id = (String) request.getAttribute("id");
						String name = (String) request.getAttribute("name");
						String mail = (String) request.getAttribute("mail");
						String recogn = (String) request.getAttribute("recogn");
					%>
					<form action="FromClubUpdate" method="post" name=formConfirm>
						<p>
							<label>サークル名<input type="text" name="name"
								value="<%=name%>" size="20" maxlength="50"></label>
						</p>
						<p>
							<label>メールアドレス<input type="email" name="mail"
								value="<%=mail%>" size="50" maxlength="256"></label>
						</p>
						<p>
							<label>パスワード<input type="password" name="password"
								value="" size="20" maxlength="16"></label>
						</p>


						<%
							if (session.getAttribute("user").equals("admin")) { // 管理者のとき
								String recognChecked = "";
								String nonRecognChecked = "";
								if (recogn.equals("公認")) {
									recognChecked = "checked=\"checked\"";
								} else {
									nonRecognChecked = "checked=\"checked\"";
								}
						%>
						<p>
							<label> <input type="radio" name="recogn" value="非公認"
								<%=nonRecognChecked%> /> <span>非公認</span>
							</label>
						</p>
						<p>
							<label> <input type="radio" name="recogn" value="公認"
								<%=recognChecked%> /> <span>公認</span>
							</label>
						</p>
						<%
							}
						%>









						<div class="center-align">

							<p>
								<br /> <input type="hidden" name="option" value="confirm">
								<input type="hidden" name="id" value=<%=id%>> <a
									class="waves-effect waves-light btn"
									href="javascript:formConfirm.submit()">確定</a>
							</p>
						</div>
					</form>

					<div class="center-align">
						<form action="FromClubUpdate" method="post" name=formDelete>
							<input type="hidden" name="option" value="delete"> <input
								type="hidden" name="id" value=<%=id%>> <a
								class="waves-effect waves-teal btn-flat"
								href="javascript:formDelete.submit()" style="margin-top: 10px;">
								<span class="red-text">アカウントを削除</span>
							</a>
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
		document.addEventListener('DOMContentLoaded', function() {
			var elems = document.querySelectorAll('.dropdown-trigger');
			var instances = M.Dropdown.init(elems);
		});
	</script>

</body>
</html>