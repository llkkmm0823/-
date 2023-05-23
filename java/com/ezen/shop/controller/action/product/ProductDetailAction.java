package com.ezen.shop.controller.action.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.ProductDao;
import com.ezen.shop.dto.ProductVO;

public class ProductDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pseq = Integer.parseInt( request.getParameter("pseq") );
		ProductDao pdao = ProductDao.getInstance();
		ProductVO pvo = pdao.getProduct( pseq );
		
		request.setAttribute("productVO", pvo);
		String url = "product/productDetail.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
		

	}

}
