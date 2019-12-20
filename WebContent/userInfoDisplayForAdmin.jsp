<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userInfoDisplayForAdmin</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body>

<form action="FromUserInfoDisplayForAdmin" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><input type="hidden" name="option" value="top">
				<a href="FromUserInfoDisplayForAdmin">トップ</a></li>
			</ul>
		</div>
	</nav>


	<p>
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<div class="center-align">
							<h6>
								ユーザ情報<br/>
							</h6>
						</div>

						<p>
							<br/>
						</p>

						<table>
						<tr><td>ユーザ名</td><td><%= request.getAttribute("name")%></td></tr>
						<tr><td>メールアドレス</td><td><%= request.getAttribute("mail")%></td></tr>
						<tr><td>ID</td><td><%= request.getAttribute("id")%></td></tr>
						</table>
					</div>

					<br/>

					<div class="center-align">
					<form action="FromUserInfoDisplayForAdmin" method="post" name ="formSetting">
						<p>
							<input type="hidden" name="generalId" value="">
							<input type="hidden" name="option" value="setting">
							<a class="waves-effect waves-light btn" href = "javascript:formSetting.submit()">設定</a>
						</p>
					</form>
					</div>

				</div>
			</div>
		</div>
	</div>




</form>

</body>
</html>