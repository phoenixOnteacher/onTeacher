<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/ocrInsertForm.css" />
<script src="${path}/resources/js/ocr_upload.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div id="ocr_wrap">
	<div id="ocr_title_wrap">
		<div id="ocr_title">글자 인식하기</div>
	</div>
	<div class="alert alert-warning" role="alert">
		OCR 기능은 사진파일에 있는 글자를 인식해서 텍스트 형태로 추출해주는 기능입니다.<br> 사진으로 찍은 문제를
		출제하거나 과제를 제출하는 등 다양하게 활용할 수 있습니다.<br> <strong>*JPG 파일만
			가능합니다.</strong>
	</div>
	<form method="post" action="ImageUpload" enctype="multipart/form-data">
		<input type="file" name="file" id="bfile" class="form-control">
		<div id="ocr_btn">
			<button type="submit" id="upload" class="btn btn-primary"
				onclick="fileCheck( this.form.file )">글자 인식</button>
		</div>
	</form>
</div>