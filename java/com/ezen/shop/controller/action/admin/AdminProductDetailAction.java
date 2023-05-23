package com.ezen.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.ProductDao;
import com.ezen.shop.dto.AdminVO;
import com.ezen.shop.dto.ProductVO;

public class AdminProductDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/product/productDetail.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) { 
			url = "shop.do?command=admin"; 
		} else {
			int pseq = Integer.parseInt( request.getParameter("pseq") );
			// 전달받은 상품번호로 상품을 검색하여 리턴 받습니다.
			ProductDao pdao = ProductDao.getInstance();
			ProductVO pvo = pdao.getProduct( pseq ); 
			
			// 화면에 표시될 kind 종류를 배열로 선언
			String [] kindList = { "0", "Heels", "Boots", "Sandals", "Snicakers", "Slipers", "On Sale" };
			
			// 현재 상품의 kind 값 추출
			int index = Integer.parseInt( pvo.getKind() ); 
			
			// index 번째의  kindList 값을 리퀘스트에 저장
			request.setAttribute("kind", kindList[index]);
			
			request.setAttribute("productVO", pvo);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}


}
