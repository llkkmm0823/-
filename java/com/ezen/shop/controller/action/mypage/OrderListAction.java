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

public class OrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/orderList.jsp";
		int oseq = Integer.parseInt(request.getParameter("oseq"));  // 조회할 주문번호 저장
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
		    url = "shop.do?command=loginForm";
		}else {
			OrderDao odao = OrderDao.getInstance();
			//  order_view 에서 주문번호(oseq)로 주문을 검색
			ArrayList<OrderVO> list = odao.listOrderByOseq( oseq );
			int totalPrice=0;
			for(OrderVO ovo : list)  // 조회된 주문의 총 결제금액 계산
				totalPrice+=ovo.getPrice2() * ovo.getQuantity();	
			
			request.setAttribute("orderList", list);
			request.setAttribute("totalPrice", totalPrice);	
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
