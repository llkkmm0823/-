<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_image_menu.jsp" %>

<article>

<h2> 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
<form name="formm" method="post">
<table id="cartList">
	<tr>	<th>번호</th><th>제목</th><th>등록일</th><th>답변 여부</th></tr>
	<c:forEach items="${qnaList}"  var="qnaVO">
		<tr ><td> ${qnaVO.qseq}</td>    
    		<td><a href="shop.do?command=qnaView&qseq=${qnaVO.qseq}">${qnaVO.subject}</a></td>      
       		<td><fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
       		<td><c:choose>
				<c:when test="${qnaVO.rep==1}"> no </c:when>
				<c:when test="${qnaVO.rep==2}"> yes </c:when>
			</c:choose></td>    
   		</tr>
  	</c:forEach>
</table>

<div class="clear"></div>
<div id="paging" style="font-size:120%; font-weight:bold; margin-left:300px">
	<c:url var="action" value="shop.do?command=qnaList" />
	<c:if test="${paging.prev}">
		<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
	</c:if>
	<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
		<c:choose>
			<c:when test="${paging.page==index}">
				[${index}]
			</c:when>
			<c:otherwise>
				<a href="${action}&page=${index}">${index}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${paging.next}">
		<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
	</c:if>
</div>

<div  class="clear"></div><br>


<div id="buttons" style="float:right">
	<input type="button"  value="질문하기"  class="submit" 
		onClick="location.href='shop.do?command=qnaWriteForm'"> 
	<input type="button"    value="쇼핑 계속하기"  class="cancel" 	
		onclick="location.href='shop.do?command=index'">  
</div>
<div  class="clear"></div><br>
</form>


</article>

<%@ include file="../footer.jsp" %>