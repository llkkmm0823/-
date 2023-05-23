package com.ezen.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;

public class OrderDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전달된 주문번호 저장
		int oseq = Integer.parseInt( request.getParameter("oseq") );
		// 로그인 체크
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");    
		String url = "";
		if (loginUser == null) {
		  	url = "shop.do?command=loginForm";
		}else {
			url = "mypage/orderDetail.jsp";
			// 주문번호로 조회한 주문(상품) 리스트 리턴
			OrderDao odao = OrderDao.getInstance();
			ArrayList<OrderVO> orderList = odao.listOrderByOseq(oseq);			
			request.setAttribute("orderList", orderList);
			
			// 리턴받은 주문리스트의 첫번째를 이용하여 orderDetail 이란 이름으로 리퀘스트에 담습니다
			request.setAttribute("orderDetail", orderList.get(0) );
			// 주문자 정보 및 배송정보 표시용으로 별도 저장합니다.
	
			//주문 총금액을 계산하고 리퀘스트에 담습니다
			int totalPrice = 0;
	    	for( OrderVO ovo : orderList)
	    		totalPrice += ovo.getPrice2() * ovo.getQuantity();
	    	request.setAttribute("totalPrice" , totalPrice);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
