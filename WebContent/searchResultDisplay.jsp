<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchResultDisplay</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>

<body>

<form action="" method="get">

<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="clubMyPage">ユーザ</a></li>
			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						検索結果</br>
					</h6>
					<p>
						</br>
					</p>

					<p><label>入力キーワード: <%= session.getAttribute("result")%></label></p>

					<table>
					<thead><tr> <th>サークル名</th></tr></thead>

					<tbody><tr><td></td></tr></tbody>

					</table>


				</div>
			</div>
		</div>
	</div>


</form>

</body>
</html>