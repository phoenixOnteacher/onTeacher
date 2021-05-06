<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/ocrRecognize.css" />
<div id="ocr_r_wrap">
	<div id="ocr_r_title_wrap">
		<div id="ocr_r_title">글자 인식 결과</div>
	</div>
	<form action="/ocr/savefile" method="POST">
		<textarea name="txtSave" class="form-control">${text}</textarea>
		<div id="savefileblock">
			<label for="filename">파일이름:</label> <input type="text"
				name="filename" class="form-control" id="filename">
			<button type="submit" class="btn btn-primary">텍스트파일로 저장</button>
		</div>
	</form>
</div>