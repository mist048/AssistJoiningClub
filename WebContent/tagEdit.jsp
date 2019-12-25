<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagEdit</title>

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
				<li><a href="tagDisplay.jsp">戻る</a></li>
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
					<div class="center-align">
						<h6>タグ編集画面</h6>
					</div>

					<ul class="collection">

						<%
							String[] deleteTagIds = (String[]) request.getAttribute("deleteTagIds");
							String[][] tags = (String[][]) request.getAttribute("allTags");
							for (int i = 0; i < tags.length; i++) {
						%>
						<li class="collection-item avatar">
							<form action="FromTagEdit" method="post" name="form<%=i%>">
								<span class="title"><%=tags[i][Constant.NAME]%></span> <input
									type="hidden" name="option" value="delete"> <input
									type="hidden" name="deleteTagId"
									value=<%=tags[i][Constant.ID]%>>
								<%
									if (deleteTagIds != null) {
											for (String tagId : deleteTagIds) {
								%>
								<input type="hidden" name="deleteTagIds[]" value=<%=tagId%>>
								<%
									}
										}
								%>
								<a href="javascript:form<%=i%>.submit()"
									class="secondary-content"> <i
									class="waves-effect waves-teal btn-flat">削除</i>
								</a>
							</form>
						</li>
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

					<div class="center-align">

						<form action="FromTagEdit" method="post" name="formSave"
							style="display: inline">
							<input type="hidden" name="option" value="save">
							<%
								if (deleteTagIds != null) {
									for (String tagId : deleteTagIds) {
							%>
							<input type="hidden" name="deleteTagIds[]" value=<%=tagId%>>
							<%
								}
								}
							%>
							<a class="waves-effect waves-light btn"
								href="javascript:formSave.submit()">保存</a>
						</form>

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