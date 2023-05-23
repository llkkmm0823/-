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

public class OrderAllAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/mypage.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if(mvo==null) {
	    	url = "shop.do?command=loginForm";
	    }else {
	    	ArrayList<OrderVO> finalList = new ArrayList<OrderVO>();	    	
	    	OrderDao odao = OrderDao.getInstance();
	    	ArrayList<Integer> oseqList = odao.selectOseqOrderAll( mvo.getId() );	    	
	    	for( Integer oseq : oseqList ) {
	    		ArrayList<OrderVO> orderListByOseq = odao.listOrderByOseq(oseq);
	    		OrderVO firstProduct = orderListByOseq.get(0); 
	    		int totalPrice = 0;
	    		for( OrderVO ovo : orderListByOseq) 
	    			totalPrice += ovo.getPrice2() * ovo.getQuantity();
	    		firstProduct.setPrice2(totalPrice);
	    		firstProduct.setPname( firstProduct.getPname() + " 포함 " + orderListByOseq.size() + " 건" );
	    		finalList.add( firstProduct );
	    	}
	    	request.setAttribute("orderList", finalList);
	    	request.setAttribute("title", "총 주문 내역");
	    	request.getRequestDispatcher(url).forward(request, response);
	    }

	}

}
