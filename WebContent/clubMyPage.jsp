<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubMyPage</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- materialize CDN -->

</head>
<body bgcolor=#f9f9f9>
<form action="FromClubMyPage" method="post">
	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>
	<p>
		</br>
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>
							マイサークル情報</br>
						</h6>
						<p>
							</br>
						<table>
							<tr>
								<td>サークル名</td>
								<td><%= session.getAttribute("name")%></td>
							</tr>
							<tr>
								<td>メールアドレス</td>
								<td><%= session.getAttribute("mail")%></td>
							</tr>
							<tr>
								<td>公認</td>
								<td><%= session.getAttribute("recogn")%></td>
							</tr>
							<tr>
								<td>サークル説明文</td>
								<td><%= session.getAttribute("intro")%></td>
							</tr>
						</table>


						</br>


						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="setting">
								<a class="waves-effect waves-light btn" href="FromClubMyPage">設定</a>
								<input type="hidden" name="option" value="edit">
								<a class="waves-effect waves-light btn" href="FromClubMyPage">編集</a>
							</p>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
