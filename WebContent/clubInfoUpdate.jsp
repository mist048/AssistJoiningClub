<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clubInfoUpdate</title>
</head>
<body>
<p><input type="submit" value="戻る"></p>
<p>サークル情報編集</p>

<p>サークルについての情報を入力<br>
<textarea name="info" maxlength="2000" cols="100" rows="20"></textarea>
</p>

<p><label>タグ</label></p>

<p>
<input type="text" name="tag" size="20" maxlength="15" placeholder="タグを追加">
<input type="submit" value="追加">
</p>

<p><input type="submit" value="確定"></p>
</body>
</html>