<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>OCR Insert</title>
<script src="${path}/resources/js/ocr_upload.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

<form method="post" action="ocrImageUpload" enctype="multipart/form-data">
	<h3>그림 파일 업로드</h3>
	<table>
		<tr>
			<td>
			<input multiple="multiple" type="file" name="file" id="bfile">
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="파일 인식" id="upload"></td>
		</tr>
		<tr><td></td></tr>
		<tr>
			<td><b>JPG 그림 파일만 전송 가능</b></td>
		</tr>
	</table>
</form>

</body>
</html>