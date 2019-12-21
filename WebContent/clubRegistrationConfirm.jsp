<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubRegistrationConfirm</title>

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
				<li><a href="clubRegistration.jsp">戻る</a></li>
			</ul>
		</div>
	</nav>


	<p></p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">
						<h6>登録情報</h6>
					</div>

					<%
						String id = (String) request.getAttribute("id");
						String name = (String) request.getAttribute("name");
						String password = (String) request.getAttribute("password");
						String mail = (String) request.getAttribute("mail");
					%>

					<table>
						<tr>
							<td>サークルID</td>
							<td><%=id%></td>
						</tr>
						<tr>
							<td>サークル名</td>
							<td><%=name%></td>
						</tr>
						<tr>
							<td>パスワード</td>
							<td><%=password%></td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><%=mail%></td>
						</tr>
					</table>

					<div class="center-align">
						<form action="FromClubRegistrationConfirm" method="post"
							name="formRegister">
							<p>
								<input type="hidden" name="option" value="register"> <input
									type="hidden" name="id" value=<%=id%>> <input
									type="hidden" name="name" value=<%=name%>> <input
									type="hidden" name="password" value=<%=password%>> <input
									type="hidden" name="mail" value=<%=mail%>> <a
									class="waves-effect waves-light btn"
									href="javascript:formRegister.submit()">登録</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>