<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%	String filename=(String)request.getAttribute("filename");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function fnc_submit(){
		var radioBtnElements = document.getElementsByName('radio');
		flag = false;
		for (var i = 0, len = radioBtnElements.length; i < len; i++){
    	if (radioBtnElements[i].checked) {
        	flag = true;
    	}
  		}
  		if(flag == true){
  		if(confirm("削除してもよろしいですか？")){
			var fm = document.getElementById("fm1");
  				fm.method = "post";
  				fm.action = "<%=request.getContextPath()%>/deletemusic";
  				fm.submit();
  				}
  				else{
  				}
  				}
	else{
		alert('選択してください');
	}
}
function update(form){

		var radioBtnElements = document.getElementsByName('radio');
		flag = false;
		for (var i = 0, len = radioBtnElements.length; i < len; i++){
    	if (radioBtnElements[i].checked) {
        	flag = true;
    	}
  		}
  		if(flag == true){
  		var fm = document.getElementById("fm1");
  		fm.method = "post";
  		fm.action = "<%=request.getContextPath()%>/Update";
  		fm.submit();
  		}
  		else{
  		alert('選択してください');
  		}
}
function search(form){
if(form.m_cd.value == "" && form.m_name.value == ""){
	form.method = "post";
	form.action = "<%=request.getContextPath()%>/Display";
	form.submit();
}
  else{
  form.submit();
  }
}
function reset(){
	var textForm = document.getElementById("m_cd");
  	textForm.value = '';
  	var textareaForm = document.getElementById("m_name");
  textareaForm.value = '';
}

</script>
<link rel="stylesheet" href="css/Music_Search.css">
<title>検索一覧画面</title>
</head>
<body>
	<p id="user_name" style="position: absolute; right: 0px; top: 0px"></p>
	<h1>検索一覧画面</h1>
	<form action = "<%=request.getContextPath()%>/MusicSearch" method = "post">
		<div class="box1">
			<p>コード検索</p>
			<input type="text" name="m_cd">
			<p>CDタイトル検索</p>
			<input type="text" name="m_name"><br>
			<p></p>
			<input type = "button" onclick= "search(this.form)" value = "検索"/>
			<input type = "button" onclick= "reset(this.form)" value = "リセット"/>
		</div>
		</form>
	<form id = "fm1">
	<div class="box2">
		<table class="tb_01">
			<tr>
				<th></th>
				<th>音楽コード</th>
				<th>画像</th>
				<th>CDタイトル</th>
				<th>CD歌手名</th>
				<th>CDジャンル</th>
				<th>CD価格</th>
				<th>作成日</th>
				<th>更新日</th>
			</tr>
			<tbody class = "list-table">
			<c:forEach var="dbdataLine" items="${musicdate}" varStatus="num">
				<tr>
					<td><label>
					<input type="radio" name="radio"value="${num.index}"></label></td>
					<td>${dbdataLine.m_cd}</td>
					<td><img src = "upload/${dbdataLine.m_gazou}"></td>
					<td>${dbdataLine.m_name}</td>
					<td>${dbdataLine.m_singer}</td>
					<td>${dbdataLine.m_genre}</td>
					<td>${dbdataLine.m_price}</td>
					<td>${dbdataLine.date_create}</td>
					<td>${dbdataLine.date_update}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="box3">
		<input type="button" onclick="location.href = 'Music_Entry.jsp'"value="登録">
		<input type="button"onclick="update(${dbdataLine.m_cd})" value="更新">
		<input type="button" onclick= "fnc_submit(this.form)" value="削除">
	</div><br>
	<div class = "box4">
	<ul class="example">
<li><a href = "<%=request.getContextPath()%>/MusicSearch1">前へ</a></li>
<li><a href = "<%=request.getContextPath()%>/MusicSearch1">1</a></li>
<li class = "this">2</li>
<li><a href="<%=request.getContextPath()%>/MusicSearch3">3</a></li>
<li><a href="<%=request.getContextPath()%>/MusicSearch3">次へ</a></li>
</ul>
</div>
	</form>
	</body>
	</html>