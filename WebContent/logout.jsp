<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>

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
				<li><a href="">戻る</a></li>
			</ul>
		</div>
	</nav>



	<p>
		</br>
	</p>

	<form action="FromLogout" method="get">
	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>
							ログアウトしますか？</br>
						</h6>
						<p>
							</br>
						</p>

						<p>

						<button class="waves-effect waves-light btn" type="submit" value="yes" name="option">
							はい </button>
						<button class="waves-effect waves-light btn" type="submit" value="no" name="option">
							いいえ </button>

						</p>
					</div>

				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>