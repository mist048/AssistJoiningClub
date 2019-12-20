<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userDisplay</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>

<body>

	<nav class="teal">

		<div class="nav-wrapper">
			<form action="ToTop" method="post">
			<ul class="left hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
			</form>
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
					<h6>ユーザ一覧</h6>
				</div>

					<ul class="collection">

						<%
							String[][] users = (String[][]) request.getAttribute("users");
							for (int i = 0; i < users.length; i++) {
						%>

						<li class="collection-item avatar"><i
							class="material-icons circle">person</i>
							<form action="FromUserDisplay" method="get" name="form<%=i%>">
								<span class="title"><%=users[i][Constant.NAME]%></span>
								<input type="hidden" name="option" value="userInfoDisplayForAdmin">
								<input type="hidden" name="generalId"
									value=<%=users[i][Constant.ID]%>> <a
									href="javascript:form<%=i%>.submit()" class="secondary-content">
									<i class="waves-effect waves-teal btn-flat">詳細</i>
								</a>
							</form></li>

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