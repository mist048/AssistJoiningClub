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

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToTop">トップ</a></li>
			</ul>
		</div>
	</nav>

	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<%
						String id = (String) request.getAttribute("id");
						String password = (String) request.getAttribute("password");
						String name = (String) request.getAttribute("name");
						String mail = (String) request.getAttribute("mail");
						String recogn = (String) request.getAttribute("recogn");
					%>

					<div class="center-align">
						<h6><%=name%></h6>
					</div>

					<p>
						<br />
					</p>


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
					</table>


					<br />

					<div class="center-align">

						<form action="FromClubInfoDisplayForAdmin" method="post"
							name="formSetting">
							<input type="hidden" name="clubId" value=<%=id%>> <input
								type="hidden" name="password" value=<%=password%>> <input
								type="hidden" name="name" value=<%=name%>> <input
								type="hidden" name="mail" value=<%=mail%>> <input
								type="hidden" name="recogn" value=<%=recogn%>> <input
								type="hidden" name="option" value="setting"> <a
								class="waves-effect waves-light btn"
								href="javascript:formSetting.submit()">設定</a>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>