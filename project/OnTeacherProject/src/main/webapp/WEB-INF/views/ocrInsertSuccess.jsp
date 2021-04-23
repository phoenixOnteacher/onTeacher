<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCR Test</title>
</head>
<body>
	<h3> 이미지 파일이 업로드 되었습니다.</h3>
  	<span>
  		<a href="./filedownload?filename=${file.originalFilename}"> ${file.originalFilename}</a>
  	</span>
 	<br/>  
</html>