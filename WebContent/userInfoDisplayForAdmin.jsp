<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userInfoDisplayForAdmin</title>

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
				<li><a href="userDisplay.jsp">戻る</a></li>
			</ul>
		</div>
	</nav>


	<p></p>


	<div class="row">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">
					<div class="center-align">

						<%
							String id = (String) request.getAttribute("id");
							String name = (String) request.getAttribute("name");
							String mail = (String) request.getAttribute("mail");
						%>

						<div class="center-align">
							<h6>ユーザ情報</h6>
						</div>

						<p>
							<br />
						</p>

						<table>
							<tr>
								<td>ユーザ名</td>
								<td><%=name%></td>
							</tr>
							<tr>
								<td>メールアドレス</td>
								<td><%=mail%></td>
							</tr>
						</table>

					</div>

					<br />

					<div class="center-align">
						<form action="FromUserInfoDisplayForAdmin" method="post"
							name="formSetting">
							<p>
								<input type="hidden" name="generalId" value=<%=id%>> <input
									type="hidden" name="option" value="setting"> <a
									class="waves-effect waves-light btn"
									href="javascript:formSetting.submit()">設定</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>