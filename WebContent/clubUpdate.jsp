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
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">戻る</a></li>
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
						<label>サークル名<input type="text" name="name" size="20"
							maxlength="50"></label>
					</p>
					<p>
						<label>メールアドレス<input type="email" name="email" size="50"
							maxlength="256"></label>
					</p>
					<p>
						<label>ID<input type="text" name="id" size="20"
							maxlength="10"></label>
					</p>
					<p>
						<label>パスワード<input type="password" name="pass" size="20"
							maxlength="16"></label>
					</p>
					<div class="center-align">

						<a class="waves-effect waves-teal btn-flat"> <span
							class="red-text">アカウントを削除</span></a></br>
						</br>


						<p>
							<a class="waves-effect waves-light btn">確定</a>
						</p>

					</div>

				</div>
			</div>
		</div>
	</div>





</body>
</html>