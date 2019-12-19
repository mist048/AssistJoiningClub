<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>contactInfoConfirm</title>

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
					<li><a href="contactAdmin.jsp">戻る</a></li>
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
						問い合わせフォーム確認<br/>
					</h6>
					<p>
						<br/>
					</p>

					<p>
						<label>件名 <%=request.getAttribute("")%></label>
					</p>
					<textarea name="info" maxlength="2000" cols="100" rows="20" readonly>
						<%=request.getAttribute("")%></textarea>


					<div class="center-align">
						<form action="FromClubUpdate" method="get" name = formDecision>
						<p>
							<input type="hidden" name="option" value="decision">
							<a class="waves-effect waves-light btn" href = "javascript:formDecision.submit()">確定</a>
						</p>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>


</body>
</html>