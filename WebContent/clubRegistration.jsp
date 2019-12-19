<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubRegistration</title>

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
				<li><a href="ToTop">戻る</a></li>
				<!-- トップ(viewerTop.jsp)に遷移 -->
			</ul>
		</div>
	</nav>

	<p>
		<br />
	</p>

	<div class="row" style="margin-top: 50px;">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						新規サークルアカウント登録<br />
					</h6>
					<p>
						<br />
					</p>

					<form action="FromClubRegistration" method="get" name="formConfirm">
						<p style="margin-top: 20px;">
							<label>サークル名<input type="text" name="name" size="20"
								maxlength="50"></label>
						</p>
						<p>
							<label>メールアドレス<input type="email" name="mail" size="50"
								maxlength="256"></label>
						</p>
						<p>
							<label>ID<input type="text" name="id" size="20"
								maxlength="10"></label>
						</p>
						<p>
							<label>パスワード<input type="password" name="password" size="20"
								maxlength="16"></label>
						</p>

						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm"> <a
									class="waves-effect waves-light btn"
									href="javascript:formConfirm.submit()">確認画面</a>
							</p>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>



</body>
</html>