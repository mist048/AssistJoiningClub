<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>


<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->



</head>
<body bgcolor=#f9f9f9>

	<nav class="teal">


	</nav>
	<p>
		<br/>
	</p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<h5>
						システムにログイン<br/>
					</h5>
					<p>
						<br/>
					</p>


						<p>
							<label> <input name="user" value = "general" type="radio" /> <span>一般</span>
							</label>
						</p>

						<p>
							<label> <input name="user" value = "club" type="radio" /> <span>サークル</span>
							</label>
						</p>

						<p>
							<label> <input name="user" value = "admin" type="radio" /> <span>管理者</span>
							</label>
						</p>


					<p>
						<label>ID<input type="text" name="id" size="20"
							maxlength="10"></label>
					</p>
					<p>
						<label>パスワード<input type="password" name="password" size="20"
							maxlength="16"></label>
					</p>
					<p>
						<br/>
					</p>
					<div class="center-align">
					<form action="" method="post" name ="formLogin">
					<input type="hidden" name="option" value="login">
						<a href = "javascript:formLogin.submit()" class="waves-effect waves-light btn">ログイン </a>
					</form>
					</div>

				</div>
			</div>
		</div>
	</div>




</body>
</html>