package com.ezen.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.CartDao;
import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;

public class OrderInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
		    url = "shop.do?command=loginForm";
		}else {
			
			// 주문자 아이디로 검색한 카트 목록(지금 주문 처리할)을 먼저 조회합니다.(cdao 생성 필요)
			CartDao cdao = CartDao.getInstance();
			ArrayList<CartVO> list = cdao.selectCart( mvo.getId() );
			
			// 조회한 list 와 주문자의 아이디를 갖고 OrderDao 에 가서 오더 와 오더 디테일에 데이터를 추가합니다.
			// 주문 추가 후  추가한 주문의 주문번호를 리턴받습니다
			OrderDao odao = OrderDao.getInstance();
			// int Oseq = odao.insertOrder( list , mvo.getId() );
			
			
			
			// 1. orders 테이블에 레코드 추가
			odao.insertOrders( mvo.getId() );
			
			// 2. 방금 orders 테이블에 추가한 oseq 조회
			int Oseq = odao.lookupMaxOseq();
			
			// 3. order_detail 테이블에 레코드 추가
			odao.insertOrder_Detail( list, Oseq );
			
			// 4. cart의 내용을 삭제합니다
			odao.deleteCart( list );
			
			
			// 방금 주문에 성공한 주문 번호를 갖고 오더 리스트로 이동하여 주문번호로 주문 내역을 다시 조회하고 jsp로 이동합니다
			url = "shop.do?command=orderList&oseq="+ Oseq;
		}
		response.sendRedirect(url);

	}

}
