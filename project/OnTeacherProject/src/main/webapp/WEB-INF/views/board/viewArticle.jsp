<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- 
<c:set var="article"  value="${articleMap.article}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />

 --%>
<%
  request.setCharacterEncoding("UTF-8");
%>

<!-- >
글 목록보기 
http://localhost:8090/listArticle -->
<!-- >
글 쓰기
http://localhost:8090/articleForm -->
<!-- >
글 상세보기
http://localhost:8090/viewArticle?no=x
x값은 아무거나 넣어서 확인. listArticle에서 글 눌러서 확인도 가능
 -->


<head>
<meta charset="UTF-8">
<title>글보기</title>
<style>
#tr_file_upload {
	display: none;
}

#tr_btn_modify {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
  
 </script>
</head>
<body>
	<form name="frmArticle" method="post" action="addComment">
		<table border=0 align="center">
			<tr>
				<td width=150 align="center" bgcolor=#FF9933>글번호</td>
				<td><input type="text" value="${article.id }" disabled /> <input
					type="hidden" value="${article.id }" name="article_id" /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자</td>
				<td><input type="text" value="${article.user_id }" disabled />
					<input type="hidden" value="${article.user_id }" name="user_id" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제목</td>
				<td><input type=text value="${article.title }" name="title"
					id="title" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td><textarea rows="20" cols="60" name="content" id="i_content"	disabled />${article.content }</textarea></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">등록일자</td>
				<td><input type=text value="${article.created_at}"></td>
			</tr>
			<tr id="tr_btn_modify" align="center">
				<td colspan="2"><input type=button value="수정반영하기">
					<input type=button value="취소"></td>
			</tr>
			<tr id="tr_btn">
				<td colspan="2" align="center"><c:if
						test="${login.id == article.user_id }">
						<input type=button value="수정하기">
						<input type=button value="삭제하기">
					</c:if> <input type=button value="리스트로 돌아가기"> <a href="#">${article.filename}</a>

					<input type=button value="파일 보기"></td>
			</tr>
		</table>

		<table align="center" border="1" width="80%">
			<tr height="10" align="center" bgcolor="#FF9933">
				<td>댓글 번호</td>
				<td>내용</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
			<c:forEach var="comment" items="${commentList}">
				<tr>
					<td>${comment.id}</td>
					<td>${comment.content}</td>
					<td>${comment.user_id}</td>
					<td>${comment.created_at}</td>
				</tr>
			</c:forEach>
		</table>
		<table align="center" border="2">
			<tr>
				<td>
					<!-- 댓글 작성 영역 -->
				<td width="950" align="center" bgcolor="#FF9933"><br> <textarea
						name="content" rows="5" cols="80" id="comment"
						placeholder="댓글을 작성해주세요"></textarea> <br>
					<button type="submit" id="btnComment">댓글 작성</button></td>
			</tr>
		</table>
	</form>
</body>
</html>



