<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<body>

<form action="" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToTop">戻る</a></li>
				<!-- トップ(viewerTop.jsp)に遷移 -->
			</ul>
		</div>
	</nav>

<p>タグ一覧</p>

<table>
<tr><td><%= session.getAttribute("allTags")%></td></tr>
</table>

					<div class="center-align">
						<p>
							<a class="waves-effect waves-light btn" href="FromTagDisplay">編集</a>
						</p>

					</div>

</form>

</body>
</html>