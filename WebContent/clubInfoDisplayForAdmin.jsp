<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoDisplayForAdmin</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body>

<form action="FromClubInfoDisplayForAdmin" method="get">

<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>

<p>サークル情報</p>

<table>
<tr><td>ID</td><td><%= session.getAttribute("id")%></td></tr>
<tr><td>サークル名</td><td><%= session.getAttribute("name")%></td></tr>
<tr><td>メールアドレス</td><td><%= session.getAttribute("mail")%></td></tr>
<tr><td>公認</td><td><%= session.getAttribute("recogn")%></td></tr>
<tr><td>リンク</td><td><%= session.getAttribute("link")%></td></tr>
<tr><td>サークル説明文</td><td><%= session.getAttribute("intro")%></td></tr>
<tr><td>メンバー</td><td><%= session.getAttribute("member")%></td></tr>
<tr><td>アイコン</td><td><%= session.getAttribute("icon")%></td></tr>
<tr><td>ホーム画像</td><td><%= session.getAttribute("home")%></td></tr>
</table>

						<p>
							<input type="hidden" name="option" value="setting">
							<a class="waves-effect waves-light btn" href = "FromClubInfoDisplayForAdmin">設定</a>
						</p>

</form>

</body>
</html>