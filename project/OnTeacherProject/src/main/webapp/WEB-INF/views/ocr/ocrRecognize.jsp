<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>OCR Recognize</title>
</head>
<body>
	<h3> 이미지 파일 인식결과 화면 </h3>
  	<form action="/ocr/savefile" method="POST">  		
 
  		<textarea rows="10" cols="100" name="txtSave" >${text}</textarea><br/>
  		<label>파일명:</label>
  		<input type="text" name="filename">
  		<input type="submit" value="저장"> </br> 
  	</form>
 	<br/>  
</html>
