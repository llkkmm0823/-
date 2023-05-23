package com.ezen.shop.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.CartDao;

public class CartDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] cseqArr = request.getParameterValues("cseq");
		CartDao cdao = CartDao.getInstance();
		
		for( String cseq : cseqArr) 
			cdao.deleteCart( Integer.parseInt(cseq) );
		
		response.sendRedirect("shop.do?command=cartList"); 

	}

}
