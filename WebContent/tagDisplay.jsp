<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagDisplay</title>

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
				<li><a href="ToTop">戻る</a></li>
				<!-- トップ(adminTop.jsp)に遷移 -->
			</ul>
		</div>
	</nav>

	<p>
		<br/>
	</p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>タグ一覧</h6>
					</div>

					<ul class="collection">

						<%
							String[][] tags = (String[][]) request.getAttribute("allTags");
							for (int i = 0; i < tags.length; i++) {
						%>
						<li class="collection-item avatar">
							<span class="title"><%=tags[i][Constant.DISPLAY_NAME]%></span>
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

							<form action="FromClubMyPage" method="post" name="formTagedit"
								style="display: inline">
								<input type="hidden" name="option" value="tagEdit"> <a
									class="waves-effect waves-light btn"
									href="javascript:formTagedit.submit()">編集</a>
							</form>

						</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>