<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>favoriteClubDelete</title>

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

<body bgcolor=#f9f9f9>

	<nav class="teal">
			<div class="nav-wrapper">
				<a href="#" class="brand-logo center"></a>
				<ul class="left hide-on-med-and-down">
					<li><a href="favoriteClubDisplay.jsp">戻る</a></li>
				</ul>
			</div>
	</nav>

	<p>
	</p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						削除するサークルを選択<br/>
					</h6>
					<p>
						<br/>
					</p>

					<ul class="collection">

						<%
							String[][] clubs = (String[][]) request.getAttribute("clubs");
							for (int i = 0; i < clubs.length; i++) {
						%>
						<li class="collection-item avatar"><i
							class="material-icons circle">person</i>
							<form action="FromFavoriteClubDelete" method="post" name="form<%=i%>">
								<span class="title"><%=clubs[i][Constant.NAME]%></span>
								<p><%=clubs[i][Constant.DESCRIPTION]%></p>
								<input type="hidden" name="option" value="delete">
								<input type="hidden" name="clubId"
									value=<%=clubs[i][Constant.ID]%>> <a
									href="javascript:form<%=i%>.submit()" class="secondary-content">
									<i class="waves-effect waves-teal btn-flat">削除</i>
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


</body>
</html>