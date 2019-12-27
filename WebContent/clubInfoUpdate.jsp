<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoUpdate</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

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

	<p></p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						サークル情報編集<br />
					</h6>
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

						String[] addTagNames = (String[]) request.getAttribute("addTagNames");
					%>

					<form action="FromClubInfoUpdate" method="post" name=formConfirm>
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
							<label>紹介文<textarea name="intro" maxlength="2000"
									cols="100" rows="20">
							<%=intro%></textarea></label>
						</p>
						<p>
							<label>メンバー<input type="text" name="member"
								value="<%=member%>" size="20" maxlength="16"></label>
						</p>

						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm">
								<%
									if (addTagNames != null) {
										for (String tagName : addTagNames) {
								%>
								<input type="hidden" name="addTagNames[]" value=<%=tagName%>>
								<%
									}
									}
								%>
								<a class="waves-effect waves-light btn"
									href="javascript:formConfirm.submit()">確定</a>
							</p>
						</div>
					</form>

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

					<br />

					<div class="center-align">
						<form action="FromClubUpdate" method="post" name=formDelete>
							<input type="hidden" name="option" value="delete"> <a
								class="waves-effect waves-teal btn-flat"
								href="javascript:formDelete.submit()"> <span
								class="red-text">アカウントを削除</span></a><br /> <br />
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						タグ編集<br />
					</h6>
					<p>
						<br />
					</p>

					<%
						String[][] tags = (String[][]) request.getAttribute("tags");
						if (tags != null) {
							// 表示
						}
						if (addTagNames != null) {
							// 表示
						}
					%>

					<form action="FromClubInfoUpdate" method="post" name="formAdd">
						<input type="hidden" name="option" value="add">
						<p>
							<label>タグ名<input type="text" name="addTagName" size="20"
								maxlength="50"></label>
						</p>

						<br />

						<div class="center-align">
							<p>
								<%
									if (addTagNames != null) {
										for (String tagName : addTagNames) {
								%>
								<input type="hidden" name="addTagNames[]" value=<%=tagName%>>
								<%
									}
									}
								%>
								<a class="waves-effect waves-light btn"
									href="javascript:formAdd.submit()">追加</a>
							</p>
						</div>
					</form>
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
</body>
</html>