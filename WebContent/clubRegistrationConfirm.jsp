<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubRegistrationConfirm</title>

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


	<p>
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>登録情報</h6>
					</div>

						<table>
							<tr>
								<td>サークル名</td>
								<td><%=request.getAttribute("name")%></td>
							</tr>
							<tr>
								<td>メールアドレス</td>
								<td><%=request.getAttribute("mail")%></td>
							</tr>
							<tr>
								<td>パスワード</td>
								<td><%=request.getAttribute("password")%></td>
							</tr>
						</table>

					<div class="center-align">
					<form action="FromClubRegistrationConfirm" method="post" name ="formRegister">
						<p>
							<input type="hidden" name="user_name" value="register"> <a
								class="waves-effect waves-light btn"
								href= "javascript:formRegister.submit()">登録</a>
						</p>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>