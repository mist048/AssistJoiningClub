<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminTop</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>

<body>

	<nav class="teal">
		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a href="logout.jsp">ログアウト</a></li>

			</ul>
		</div>
	</nav>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<div class="center-align">
							<h6>
								管理者ページ<br />
							</h6>
						</div>

						<div class="center-align">
							<form action="FromAdminTop" method="post" name="formTag">
								<p>
									<input type="hidden" name="option" value="tagDisplay">
									<a class="waves-effect waves-light btn"
										href="javascript:formTag.submit()">タグ一覧</a>
								</p>
							</form>

							<br />

							<form action="FromAdminTop" method="post" name="formClub">
								<p>
									<input type="hidden" name="option" value="clubDisplay">
									<a class="waves-effect waves-light btn"
										href="javascript:formClub.submit()">サークルアカウント一覧</a>
								</p>
							</form>

							<br />

							<form action="FromAdminTop" method="post" name="formUser">
								<p>
									<input type="hidden" name="option" value="userDisplay">
									<a class="waves-effect waves-light btn"
										href="javascript:formUser.submit()">一般ユーザ一覧</a>
								</p>
							</form>

							<br />

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>