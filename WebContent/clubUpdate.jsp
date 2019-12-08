<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubUpdate</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- materialize CDN -->

</head>
<body bgcolor=#f9f9f9>

<form action="FromClubUpdate" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="clubMyPage">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
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
						サークルアカウント更新</br>
					</h6>
					<p>
						</br>
					</p>

					<p>
						<label>サークル名<input type="text" name="name" value = "<%= session.getAttribute("name")%>"
						size="20" maxlength="50"></label>
					</p>
					<p>
						<label>メールアドレス<input type="email" name="mail" value = "<%= session.getAttribute("mail")%>"
						size="50" maxlength="256"></label>
					</p>
					<p>
						<label>パスワード<input type="password" name="pass" value = "<%= session.getAttribute("password")%>"
						size="20" maxlength="16"></label>
					</p>
					<div class="center-align">

						<input type="hidden" name="option" value="delete">
						<a class="waves-effect waves-teal btn-flat" href = "FromClubUpdate"> <span
							class="red-text">アカウントを削除</span></a></br>
						</br>


						<p>
							<input type="hidden" name="option" value="confirm">
							<a class="waves-effect waves-light btn" href = "FromClubUpdate">確定</a>
						</p>

					</div>

				</div>
			</div>
		</div>
	</div>



</form>

</body>
</html>