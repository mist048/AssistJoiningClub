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

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="">戻る</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="">トップ</a></li>
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
					<h5>
						システムにログイン</br>
					</h5>
					<p>
						</br>
					</p>

					<!--

					<form action="#">
						<p>
							<label> <input name="group1" type="radio" /> <span>一般</span>
							</label>
						</p>

						<p>
							<label> <input name="group1" type="radio" /> <span>サークル</span>
							</label>
						</p>

						<p>
							<label> <input name="group1" type="radio" /> <span>管理者</span>
							</label>
						</p>
					</form>
					  -->

					<p>
						<select name="user">
							<option value="jeneral" label="一般ユーザ"></option>
							<option value="club" label="サークル"></option>
							<option value="admin" label="管理者"></option>
						</select>
					</p>
					<p>
						<label>ID<input type="text" name="id" size="20"
							maxlength="10"></label>
					</p>
					<p>
						<label>パスワード<input type="password" name="pass" size="20"
							maxlength="16"></label>
					</p>
					<p>
						</br>
					</p>
					<div class="center-align">
						<a class="waves-effect waves-light btn">ログイン</a>
					</div>

				</div>
			</div>
		</div>
	</div>



</body>
</html>