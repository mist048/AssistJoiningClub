<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubDisplay</title>

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
<body bgcolor=#f9f9f9>
	<!--JavaScript at end of body for optimized loading-->
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<nav class="teal">

		<div class="nav-wrapper">

			<a  class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">管理者</a></li>
			</ul>


			<form action="ToTop" method="post">
			<ul class="right hide-on-med-and-down">
				<li><a href="">トップ</a></li>
			</ul>
			</form>
		</div>
	</nav>



	<p>
		</br>
	</p>

<form action="FromClubDisplay" method="post">
	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
				<h6>サークル一覧</h6>

					<ul class="collection">

						<li class="collection-item avatar">
							<i class="material-icons circle">person</i>
							<span class="title">サークル名</span>
							<p>
								説明文(最初20文字)
							</p>
							<a href="#!" class="secondary-content">
								<i class="waves-effect waves-teal btn-flat">詳細</i>
							</a>
						</li>

						<li class="collection-item avatar">
							<i class="material-icons circle green">person</i>
							<span class="title">サークル名</span>
							<p>
								説明文(最初20文字)
							</p>
							<a href="#!" class="secondary-content">
								<i class="waves-effect waves-teal btn-flat">詳細</i>
							</a>
						</li>

						<li class="collection-item avatar">
							<i class="material-icons circle red">person</i>
							<span class="title">サークル名</span>
							<p>
								説明文(最初20文字)
							</p>
							<a href="#!" class="secondary-content">
								<i class="waves-effect waves-teal btn-flat">詳細</i>

							</a>
						</li>
					</ul>


				</div>
			</div>
		</div>
	</div>
</form>

</body>
</html>