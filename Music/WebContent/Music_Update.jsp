<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang = "ja">
<head>
<meta charset = "UTF-8">
<script>
 function update(form){
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
<link rel = "stylesheet" href = "css/Music_Update.css">
<title>更新画面</title>
</head>
<body>
	<div class = "div1">
		<div class = "div2">
			<h1>更新画面</h1>
		<form name = "form1" action = "<%=request.getContextPath()%>/Music_Update" method = "post">
		<c:forEach var="dbdataLine" items="${musicdate}">
		<input type="file" name = "m_gazou" value = "upload/${dbdataLine.m_gazou}" ><br>
			<p>音楽コード</p>
				<input type = "text" value = "${dbdataLine.m_cd}" name = "m_cd" id = "cd" readonly>
			<p>CDタイトル</p>
				<input type = "text" value = "${dbdataLine.m_name}" name = "m_name" id = "music" required>
				<div class="error-message">※ 入力必須です</div>
			<p>歌手名</p>
				<input type = "text" value = "${dbdataLine.m_singer}" name = "m_singer" id = "singer" required>
				<div class="error-message">※ 入力必須です</div>
			<p>CDジャンル</p>
				<input type = "text" value = "${dbdataLine.m_genre}" name = "m_genre" id = "genre" required>
				<div class="error-message">※ 入力必須です</div>
			<p>CD価格</p>
				<input type = "text" value = "${dbdataLine.m_price}" name = "m_price" id = "price" required>
				<div class="error-message">※ 入力必須です</div><br><br>
				<input type = "button" onClick = "update(this.form)" value = "更新">
				<input type = "hidden" value = "${dbdataLine.date_create}" name = "date_create">
				</c:forEach>
		</form>
		</div>
		<input type="button" onclick="history.back()" value = "戻る">
	</div>
</body>
</html>