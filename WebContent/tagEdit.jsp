<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<body>

<form action="FromClubUpdate" method="get">

	<nav class="teal">
		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="tagDisplay.jsp">戻る</a></li>
			</ul>
		</div>
	</nav>

<p>タグ編集画面</p>

<table>
<tr>
<td><%= session.getAttribute("allTags")%></td>
<td><input type="submit" value="×"></td>
</tr>
</table>

</form>

</body>
</html>