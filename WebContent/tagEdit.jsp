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

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- materialize CDN -->

</head>

<body bgcolor=#f9f9f9>

	<nav class="teal">
		<div class="nav-wrapper">
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

						<h5>タグ編集画面</h5>
						<hr>


					<ul class="collection">

						<%
							String[] deleteTagIds = (String[]) request.getAttribute("deleteTagIds");
							String[][] tags = (String[][]) request.getAttribute("allTags");
							for (int i = 0; i < tags.length; i++) {
						%>



						<li class="collection-item">
							<form action="FromTagEdit" method="post" name="form<%=i%>">


								<span class="title"><%=tags[i][Constant.NAME]%></span> <input
									type="hidden" name="option" value="delete"> <input
									type="hidden" name="deleteTagId"
									value=<%=tags[i][Constant.ID]%>>
								<%

								boolean isDeleted = false;
									if (deleteTagIds != null) {
											for (String tagId : deleteTagIds) {
												if (tags[i][Constant.ID].equals(tagId)) {
													isDeleted = true;
												}
								%>
								<input type="hidden" name="deleteTagIds[]" value=<%=tagId%>>
								<%
									}
										}
								%>
								<a href="javascript:form<%=i%>.submit()"
									class="secondary-content">
										<% if(isDeleted){  %>
										<i class="waves-effect waves-teal btn-flat">✓</i>
										<% }else{ %>

									<i class="waves-effect waves-teal btn-flat">□</i>
									<%} %>
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
							<li class="disabled"><form action="FromTagEdit"
									method="post" name="formBefore">
									<input type="hidden" name="option" value="tagEdit"> <input
										type="hidden" name="firstIndex"
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
								<form action="FromTagEdit" method="post" name="formTo<%=i%>">
									<input type="hidden" name="option" value="tagEdit"> <input
										type="hidden" name="firstIndex"
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
							<li class="waves-effect"><form action="FromTagEdit"
									method="post" name="formNext">
									<input type="hidden" name="option" value="tagEdit"> <input
										type="hidden" name="firstIndex"
										value=<%=firstIndex + Constant.MAX_OF_DISPLAYS%>><a
										href="javascript:formNext.submit()"><i
										class="material-icons">chevron_right</i></a>
								</form></li>
							<%
								}
							%>
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
								href="javascript:formSave.submit()">削除</a>
						</form>

					</div>

				</div>
			</div>
		</div>
	</div>

	<footer class="page-footer teal" style="margin-top: 50px;">
		<div class="footer-copyright">
			<div class="container">
				© 2019 クロノスの時計 <a class="grey-text text-lighten-4 right"
					href="https://mlab.im.dendai.ac.jp/~hirota/2019_WSP/">サーバプログラミング演習</a>
			</div>
		</div>
	</footer>

</body>
</html>