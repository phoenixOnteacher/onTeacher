<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  

<!-- >
글 목록보기 
http://localhost:8090/listArticle?page=1 -->


<!DOCTYPE html>
<html>
<head>
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>

<body>
<a  class="cls1"  href="javascript:fn_articleForm"><p class="cls2">글쓰기</p></a> 

<table align="center" border="1"  width="80%"  >
  <tr height="10" align="center"  bgcolor="lightgreen">
     <td >No</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
     <td >조회수</td>
    
  </tr>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="7">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach  var="article" items="${articlesList }" varStatus="articleNO" >
     <tr align="center">
     
     
  
	
	<td width="10%">${article.id }</td>
	
	   <td  width="10%">${article.user_id}</td> 
	   <td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	 <a href="./viewArticle?no=${article.id}">${article.title}  </a>



	  </td>
	  <td  width="10%">${article.created_at}</td> 
	  <td  width="5%">${article.hits}</td> 
	 
	 
	 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>

<!-- 페이징 그리기 -->
    <tr>
        <td height="30" align="center" valign="middle" colspan="50" style="border:1px #CCCCCC solid;">
<?
    $pageNum = ($_GET['page']) ? $_GET['page'] : 1;     //page : default - 1
    $list = ($_GET['list']) ? $_GET['list'] : 50; //page : default - 50
 
 
    $b_pageNum_list = 10; //블럭에 나타낼 페이지 번호 갯수
    $block = ceil($pageNum/$b_pageNum_list); //현재 리스트의 블럭 구하기
    
 
    $b_start_page = ( ($block - 1) * $b_pageNum_list ) + 1; //현재 블럭에서 시작페이지 번호
    $b_end_page = $b_start_page + $b_pageNum_list - 1; //현재 블럭에서 마지막 페이지 번호
 
    $total_page =  ceil($buyTotalCount/$list); //총 페이지 수
 
    if ($b_end_page > $total_page) 
        $b_end_page = $total_page;
    
 
    if($pageNum <= 1){?>
        <font size=2  color=red>처음</font>
        <?}else{?>
            <font size=2><a href="/yw/buypaging.php?year=<?=$year?>&month=<?=$month?>&day=<?=$day?>&page=&list=<?=$list?>">처음</a></font>
        <?}
 
 
 
    if($block <=1){?>
        <font> </font>
    <?}else{?>
        <font size=2><a href="/yw/buypaging.php?year=<?=$year?>&month=<?=$month?>&day=<?=$day?>&page=<?=$b_start_page-1?>&list=<?=$list?>">이전</a></font>
    <?}
 
        
 
 
 
    for($j = $b_start_page; $j <=$b_end_page; $j++)
    {
 
        if($pageNum == $j)
        {?>
            <font size=2 color=red><?=$j?></font>
        <?}
        else{?>
            <font size=2><a href="/yw/buypaging.php?year=<?=$year?>&month=<?=$month?>&day=<?=$day?>&page=<?=$j?>&list=<?=$list?>"><?=$j?></a></font>
            <?
          }                
 
    }
 
 
 
    $total_block = ceil($total_page/$b_pageNum_list);
 
    if($block >= $total_block){?>
    <font> </font>
    <?}else{?>    
        <font size=2><a href="/yw/buypaging.php?year=<?=$year?>&month=<?=$month?>&day=<?=$day?>&page=<?=$b_end_page+1?>&list=<?=$list?>">다음</a></font>
    <?}
 
 
 
    if($pageNum >= $total_page){?>
 
            <font size=2 color=red>마지막</font>
        
        <?}else{?>
            <font size=2><a href="/yw/buypaging.php?year=<?=$year?>&month=<?=$month?>&day=<?=$day?>&page=<?=$total_page?>&list=<?=$list?>">마지막</a></font>
 
        <?}
    ?>
    </td>
 
    </tr>

                                                    
                                                 
</body>
</html>





