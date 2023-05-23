package com.ezen.shop.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.CartDao;
import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;

public class CartInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// cartvo 에 전달 파라미터(상품번호, 수량)를 넣고, cart  테이블에 레코드를 추가합니다.
		
		// cart 관련(mypage관련) 동작은 로그인 이후에 유효한 동작이므로, 현재 로그인 상태인지 먼저 점검합니다
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		String url="";
		if( mvo == null ) {
			url = "shop.do?command=loginForm";
		}else {
			CartVO cvo = new CartVO();
			cvo.setId( mvo.getId() );
			cvo.setPseq( Integer.parseInt( request.getParameter("pseq")));
			cvo.setQuantity( Integer.parseInt( request.getParameter("quantity")));
			
			CartDao cdao = CartDao.getInstance();
			cdao.insertCart( cvo );
			url = "shop.do?command=cartList";
		}
		
		response.sendRedirect(url);


	}

}
