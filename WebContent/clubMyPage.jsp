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
							マイサークル情報<br/>
						</h6>

						<p>
							<br/>
						</p>

					<%
						String name = (String) request.getAttribute("name");
						String mail = (String) request.getAttribute("mail");
						String recogn = (String) request.getAttribute("recogn");
						String intro = (String) request.getAttribute("intro");
					%>

						<table>
							<tr>
								<td>サークル名</td>
								<td><%=name%></td>
							</tr>
							<tr>
								<td>メールアドレス</td>
								<td><%=mail%></td>
							</tr>
							<tr>
								<td>公認</td>
								<td><%=recogn%></td>
							</tr>
							<tr>
								<td>サークル説明文</td>
								<td><%=intro%></td>
							</tr>
						</table>


						<br/>


						<div class="center-align">

							<form action="FromClubMyPage" method="post" name ="formSetting" style="display: inline">
								<input type="hidden" name="option" value="setting">
								<input type="hidden" name="name" value=<%=name%>>
								<input type="hidden" name="mail" value=<%=mail%>>
								<input type="hidden" name="recogn" value=<%=recogn%>>
								<input type="hidden" name="intro" value=<%=intro%>>
								<a class="waves-effect waves-light btn" href="javascript:formSetting.submit()">設定</a>
							</form>

							<form action="FromClubMyPage" method="post" name ="formEdit" style="display: inline">
								<input type="hidden" name="option" value="edit">
								<a class="waves-effect waves-light btn" href="javascript:formEdit.submit()">編集</a>
							</form>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>
