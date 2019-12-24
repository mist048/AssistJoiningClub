<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchResultDisplay</title>

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
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToMyPage">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">

					<h6>検索結果</h6>

					</div>

					<p>
						<br/>
					</p>


					<ul class="collection">

						<%
							String[][] clubs = (String[][]) request.getAttribute("result");
							for (int i = 0; i < clubs.length; i++) {
								String intro = "";
								if (clubs[i][Constant.DISPLAY_INTRO] != null) {
									intro = clubs[i][Constant.DISPLAY_INTRO];
								}
								String icon = "person";
								if (clubs[i][Constant.DISPLAY_ICON] != null) {
									icon = "<img src=./images/" + clubs[i][Constant.DISPLAY_ICON] + " width=50px height=50px>";
								}
						%>
						<li class="collection-item avatar">
							<form action="FromSearchResultDisplay" method="post" name="form<%=i%>">
								<i class="material-icons circle"><%=icon%> </i> <span
									class="title"><%=clubs[i][Constant.DISPLAY_NAME]%></span>
								<p><%=intro%></p>
								<input type="hidden" name="option" value="clubInfoDisplay">
								<input type="hidden" name="clubId"
									value=<%=clubs[i][Constant.DISPLAY_ID]%>> <a
									href="javascript:form<%=i%>.submit()" class="secondary-content">
									<i class="waves-effect waves-teal btn-flat">詳細</i>
								</a>
							</form>
						</li>
						<%
							}
						%>


					</ul>


				</div>
			</div>
		</div>
	</div>


</body>
</html>