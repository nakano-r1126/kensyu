<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang = "ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript">

<script>
//ログインボタン処理
function fnc_submit(form){
	//ユーザーＩＤ 入力チェック
	if(form1.login_id.value == ""){
		alert('LoginIDを入力してください。');
		return;
	}
	//パスワード入力チェック
	if(form1.login_pass.value == ""){
		alert('Passwordを入力してください。');
		return;
	}
	form.submit();
}
window.onload = function(){
const image_back = document.getElementById("image_back");
const images = ["css/傘.jpg", "upload/チューリングラブ.jpg", "upload/ディスコミュ星人.jpg"];

const imageNo = Math.floor( Math.random() * images.length)
image_back.src = images[imageNo];
}
	</script>
<link rel = "stylesheet" href = "css/Music_Login.css">
<title>ログイン画面</title>
</head>
<body>
	<div class = "div1">
		<img src = "" id = "image_back">
		<div class = "div2">
			<h1>ログイン</h1>
			<form name = "form1" action = "<%=request.getContextPath()%>/login_check" method = "post">
			<p>LoginID</p>
				<p><input type = "text" name = "login_id" size = "10" maxlength = "10" /></p>
			<p>Password</p>
				<p><input type = "password" name = "login_pass" size = "10" maxlength = "10" /></p>
				<p><input type = "checkbox" name = "checkbox"/>パスワードを保存</p>
				<p><input type = "button" class = "loginBtn" type = "button" value = "Login" onClick = "fnc_submit(this.form)"/></p>
		</form>
		</div>
	</div>
</body>
</html>
