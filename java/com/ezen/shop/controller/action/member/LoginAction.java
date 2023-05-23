package com.ezen.shop.controller.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.MemberVO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(id);
		
		String url = "member/login.jsp";
		
		if( mvo == null)
			request.setAttribute("message", "없는 아이디입니다");
		else if( mvo.getUseyn().equals("N") )
			request.setAttribute("message", "회원가입후 탈퇴이력이 있습니다. 관리자에 문의하세요");
			// 필요에 따라 url 을 변경해서 별도의 페이지로 연결되어 재가입을 진행합니다.
		else if( mvo.getPwd() == null)
			request.setAttribute("message", "DB오류. 관리자에 문의하세요");
		else if( !mvo.getPwd().equals(pwd) )
			request.setAttribute("message", "비밀번호가 틀렸습니다");
		else if( mvo.getPwd().equals(pwd) ) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			url = "shop.do?command=index";
		}else
			request.setAttribute("message", "로그인 실패. 관리자에게 문의하세요");
		
		RequestDispatcher dp=request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
