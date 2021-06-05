<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "ja">
<head>
<meta charset = "UTF-8">
<script>
function fnc_submit(form){
  		var res = confirm("登録してもよろしいですか？");
    if( res == true ) {
    	if(document.form1.cd.value == ""){
    		alert("音楽コードを入力してください。");
    	}
    	else if(document.form1.cd.value.match(/[^0-9]+/)){
			alert("数字で入力してください");
		}else if(document.form1.music.value == ""){
    		alert("CDタイトルを入力してください。");
    	}else if(document.form1.singer.value == ""){
    		alert("CD歌手名を入力してください。");
    	}else if(document.form1.genre.value == ""){
    		alert("CDジャンルを入力してください。");
    	}else if(document.form1.price.value == ""){
    		alert("CD価格を入力してください。");
    	}
    	else if(document.form1.price.value.match(/[^0-9]+/)){
			alert("数字で入力してください");
		}
    	else{
    	form.submit();
    	}
}else{
    }
    }

</script>
<link rel = "stylesheet" href = "Music_Entry.css">
<title>登録画面</title>
</head>
<body>
	<div class = "div1">
		<div class = "div2">
			<h1>登録画面</h1>
		<form name = "form1" action = "<%=request.getContextPath()%>/Entry" method = "post" enctype="multipart/form-data">
			<p>音楽コード</p><br>
				<input type = "text" name = "m_cd" id = "cd" required>
				<div class="error-message">※ 入力必須です</div><br>
			<input type="file" name="m_gazou" required><br>
			<p>CDタイトル</p>
				<input type = "text" name = "m_music" id = "music"required>
				<div class="error-message">※ 入力必須です</div>
			<p>CD歌手名</p>
				<input type = "text" name = "m_singer" id = "singer"required>
				<div class="error-message">※ 入力必須です</div>
			<p>CDジャンル</p>
				<input type = "text" name = "m_genre" id = "genre" required>
				<div class="error-message">※ 入力必須です</div>
			<p>CD価格</p>
				<input type = "text" name = "m_price" id = "price"required>
				<div class="error-message">※ 入力必須です</div><br>
				<input type = "button" value = "登録" onClick = "fnc_submit(this.form)">
		</form>
		</div>
		<a class="btn btn-border">
		<input type = "button" onclick="history.back()" value = "戻る">
		</a>
	</div>
</body>
</html>
