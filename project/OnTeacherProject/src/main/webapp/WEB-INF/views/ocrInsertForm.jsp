<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OCR Test</title>
</head>
<body>

<form method="post" action="ocrImageUpload" enctype="multipart/form-data">
	<h3>그림 파일 업로드</h3>
	<table>
		<tr>
			<td>
			<input multiple="multiple" type="file" name="file">
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="파일 업로드"></td>
		</tr>
		<tr><td></td></tr>
		<tr>
			<td><b>JPG 그림 파일만 전송 가능</b></td>
		</tr>
	</table>
</form>

</body>
</html>