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



	<nav class="teal">
		<div class="nav-wrapper">
			<form action="FromClubUpdate" method="get" name = form0>
				<a href="#" class="brand-logo center"></a>
				<ul class="left hide-on-med-and-down">
					<li><input type="hidden" name="option" value="myPage">
						<a href="javascript:form0.submit()">マイページ</a></li>
				</ul>
				<ul class="right hide-on-med-and-down">
					<li><input type="hidden" name="option" value="top">
						<a href="javascript:form0.submit()">トップ</a></li>
				</ul>
			</form>
		</div>
	</nav>


	<p>
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						サークルアカウント更新<br/>
					</h6>
					<p>
						<br/>
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
							class="red-text">アカウントを削除</span></a><br/>
						<br/>


						<form action="FromClubUpdate" method="get" name = form0>
						<p>

							<input type="hidden" name="option" value="confirm">
							<a class="waves-effect waves-light btn" href = "javascript:form0.submit()">確定</a>

						</p>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>


</body>
</html>