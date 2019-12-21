<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>accountDelete</title>

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
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- materialize CDN -->



</head>
<body>


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

	<p>
		<br/>
	</p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						アカウント削除<br/>
					</h6>
					<p>
						<br/>
					</p>


					<p>
						<label>ID <%= request.getAttribute("id")%></label>
					</p>
					<p>
						<label>ユーザ <%= request.getAttribute("name")%></label>
					</p>


					<form action="FromAccountDelete" method="get" name="form0">
						<div class="center-align">
							<p>
								<input type="hidden" name="user_name" value="">
								<a class="waves-effect waves-light btn" href="javascript:form0.submit()">削除</a>
							</p>

						</div>
				 	</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>