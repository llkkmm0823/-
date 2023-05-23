<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_image_menu.jsp" %>

<article>
<h2>${title}</h2>
<form>

	<table id="cartList">
		<tr><th>주문일자</th><th>주문번호</th><th>상품명</th><th>결제 금액</th><th>주문 상세</th>
			<th>처리상태</th></tr>
		
		<c:forEach items="${orderList}"  var="orderVO">
			<tr>
				<td><fmt:formatDate value="${orderVO.indate}" type="date"/></td>
	        	<td>${orderVO.oseq} </td><td>${orderVO.pname} </td>
	        	<td><fmt:formatNumber value="${orderVO.price2}" type="currency"/></td>
	        	<td><a href="shop.do?command=orderDetail&oseq=${orderVO.oseq}">주문상세</a></td>
				<td>
					<c:if test="${orderVO.result=='1'}">미처리</c:if>
					<c:if test="${orderVO.result=='2'}">완료</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>

</form>
</article>

<%@ include file="../footer.jsp" %>