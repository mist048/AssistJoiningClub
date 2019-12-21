<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>contactAdmin</title>

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
				<li><a href="clubMyPage">マイページ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>


	<p></p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						問い合わせフォーム<br />
					</h6>
					<p>
						<br />
					</p>

					<form action="FromContactAdmin" method="post" name=formDecision>
						<p>
							<label>件名<input type="text" name="subject" size="50"
								maxlength="50"></label>
						</p>
						<textarea name="info" maxlength="2000" cols="100" rows="20"></textarea>


						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="decision"> <a
									class="waves-effect waves-light btn"
									href="javascript:formDecision.submit()">確定</a>
							</p>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>


</body>
</html>