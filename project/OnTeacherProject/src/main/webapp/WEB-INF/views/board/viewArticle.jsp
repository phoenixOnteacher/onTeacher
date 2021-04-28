<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%-- 
<c:set var="article"  value="${articleMap.article}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />

 --%>
<%
  request.setCharacterEncoding("UTF-8");
%> 


<!-- >
글 상세보기는 여기루 no=0,1,2 
http://localhost:8090/viewArticle?no=0 -->


<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
  
 </script>
</head>
<body>
  <form name="frmArticle" method="post"  action="${contextPath}"  enctype="multipart/form-data">
  <table  border=0  align="center">
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      글번호
   </td>
   <td >
    <input type="text"  value="${article.id }"  disabled />
    <input type="hidden" name="id" value="${article.id}"  />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자
   </td>
   <td >
    <input type=text value="${article.user_id }" name="user_id"  disabled />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${article.title }"  name="title"  id="title" disabled />
   </td>   
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="content"  id="i_content"  disabled />${article.content }</textarea>
   </td>  
  </tr>
   
 	 
  <tr>
	   <td width="150" align="center" bgcolor="#FF9933">
	      등록일자
	   </td>
	   <td>
	    <input type=text value="${article.created_at}" >
	   </td>   
  </tr>
  <tr   id="tr_btn_modify"  align="center"  >
	   <td colspan="2"   >
	       <input type=button value="수정반영하기"   >
           <input type=button value="취소"  >
	   </td>   
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
   
   
       <c:if test="${login.id == article.user_id }">
	      <input type=button value="수정하기" >
	      <input type=button value="삭제하기" >
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  >
	  
   </td>
  </tr>
  
  
 </table>
 <table>
 <tr>
    <td  align="center" >
      댓글
   </td>
      <td>
    <textarea rows="3" cols="60"  name="comment"  id="i_comment"  disabled />${comment.content }</textarea>
     <input type=button  value="댓글달기"  >
    
   </td>  
  </tr>
  
  

</table>

 </form>
</body>
</html>



