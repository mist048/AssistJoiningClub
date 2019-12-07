<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoUpdate</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- materialize CDN -->

</head>
<body bgcolor=#f9f9f9>

<form action="" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="clubMypage">ユーザ</a></li>
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
						サークル情報編集</br>
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
						<label>公認<input type="text" name="recgon" value = "<%= session.getAttribute("recgon")%>"
						size="20" maxlength="50"></label>
					</p>
					<p>
						<label>リンク<input type="text" name="link" value = "<%= session.getAttribute("link")%>"
						size="20" maxlength="256"></label>
					</p>
					<p>
						<label>紹介文<textarea name="intro" maxlength="2000" cols="100" rows="20">
						<%= session.getAttribute("intro")%></textarea></label>
					</p>
					<p>
						<label>メンバー<input type="text" name="member" value = "<%= session.getAttribute("member")%>"
						size="20" maxlength="16"></label>
					</p>
					<p>
						<label>アイコン<input type="text" name="icon" value = "<%= session.getAttribute("icon")%>"
						size="20" maxlength="16"></label>
					</p>
					<p>
						<label>ホーム画像<input type="text" name="home" value = "<%= session.getAttribute("home")%>"
						size="20" maxlength="16"></label>
					</p>

					<div class="center-align">

						<input type="hidden" name="option" value="delete">
						<a class="waves-effect waves-teal btn-flat" href = "FromClubInfoUpdate"> <span
							class="red-text">アカウントを削除</span></a></br>
						</br>


						<p>
							<input type="hidden" name="option" value="confirm">
							<a class="waves-effect waves-light btn" href = "FromClubInfoUpdate">確定</a>
						</p>

					</div>

				</div>
			</div>
		</div>
	</div>


<p><label>タグ</label></p>

<p>
<input type="text" name="tag" size="20" maxlength="15" placeholder="タグを追加">
<input type="submit" value="追加">
</p>

</form>

</body>
</html>