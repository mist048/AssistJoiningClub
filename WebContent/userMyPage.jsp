<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userMyPage</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>

<body bgcolor=#f9f9f9>

	<nav class="teal">

		<div class="nav-wrapper">
		<form action="FromClubMyPage" method="post" name ="form0">
			<a href="#" class="brand-logo center"></a>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</form>
		</div>
	</nav>

	<p>
		<br/>
	</p>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>
							アカウント情報<br/>
						</h6>
						<p>
							<br/>
						<table>
							<tr>
								<td>ID</td>
								<td><%= request.getAttribute("id")%></td>
							</tr>
							<tr>
								<td>ユーザ</td>
								<td><%= request.getAttribute("name")%></td>
							</tr>
							<tr>
								<td>メールアドレス</td>
								<td><%= request.getAttribute("mail")%></td>
							</tr>
						</table>


						<br/>


						<div class="center-align">

							<form action="FromUserMyPage" method="post" name ="formSetting" style="display: inline">
								<input type="hidden" name="option" value="setting">
								<a class="waves-effect waves-light btn"
									href="javascript:formSetting.submit()">設定</a>
							</form>

							<form action="FromUserMyPage" method="post" name ="formFavorite" style="display: inline">
								<input type="hidden" name="option" value="favoriteClubDisplay">
								<a class="waves-effect waves-light btn"
									href="javascript:formFavorite.submit()">お気に入り</a>
							</form>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>