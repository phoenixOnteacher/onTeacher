<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCR Test</title>
</head>
<body>
	<h3> 이미지 파일을 텍스트로 변환한 결과는 아래 파일을 이용하세요.</h3>
  	<span>
  		<a href="./ocrRecognize?filename=${file}"> ${file}</a>
  	</span>
 	<br/>  
</html>