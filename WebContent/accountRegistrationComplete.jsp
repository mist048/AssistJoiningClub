<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accountRegistrationComplete.</title>

<!-- materealize CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- materialize CDN -->

</head>
<body bgcolor=#f9f9f9>

<form action="FromAccountRegistrationComplete" method="get">

	<nav class="teal">

		<div class="nav-wrapper">
			<a href="#" class="brand-logo center"></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="ToTop">戻る</a></li>
				<!-- トップ(viewerTop.jsp)に遷移 -->
			</ul>
			<ul class="right hide-on-med-and-down">
					<li><input type="hidden" name="option" value="top">
						<a href="javascript:form0.submit()">トップ</a></li>
			</ul>
		</div>
	</nav>

	<p>
		<br />
	</p>

	<div class="row" style="margin-top: 50px;">
		<div class="col s12 m8 l6 offset-m2 offset-l3">
			<div class="card">
				<div class="card-content">

					<h6>
						アカウントを登録しました。<br/>
					</h6>
					<p>
						<br />
					</p>

					<form action="FromClubRegistration" method="get" name="form0">

						<div class="center-align">
							<p>
								<input type="hidden" name="option" value="confirm"> <a
									class="waves-effect waves-light btn"
									href="javascript:form0.submit()">了解</a>
							</p>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>


</form>

</body>
</html>