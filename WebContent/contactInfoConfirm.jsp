<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>contactInfoConfirm</title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>

<body>
<p><input type="submit" value="戻る"></p>
<p>問い合わせフォーム</p>

<p><label>件名<input type="text" name="subject" size="50" maxlength="50" readonly> </label></p>
<textarea name="info" maxlength="2000" cols="100" rows="10" readonly>*** </textarea>

<p><input type="submit" value="決定"></p>
</body>
</html>