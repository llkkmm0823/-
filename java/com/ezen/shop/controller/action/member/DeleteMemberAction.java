package com.ezen.shop.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.MemberVO;

public class DeleteMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		String url="";
		if( mvo == null ) {
			url = "shop.do?command=loginForm";
		}else {
			MemberDao mdao = MemberDao.getInstance();
			mdao.deleteMember( mvo.getId() );
		}
		request.getRequestDispatcher("member/login.jsp").forward(request, response);
	}

}
