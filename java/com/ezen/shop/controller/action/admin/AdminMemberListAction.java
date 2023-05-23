package com.ezen.shop.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.AdminDao;
import com.ezen.shop.dto.AdminVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.util.Paging;

public class AdminMemberListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/member/memberList.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) {
			url = "shop.do?command=admin";
		}else {				
			
			if( request.getParameter("changeMenu") != null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			
			Paging paging = new Paging();
			paging.setDisplayPage(10);
			paging.setDisplayRow(10);
			
			if(request.getParameter("page")!=null) {
				paging.setPage( Integer.parseInt( request.getParameter("page") ) );
				session.setAttribute("page", Integer.parseInt( request.getParameter("page") ) );
			}else if(session.getAttribute("page") != null){
				paging.setPage( (Integer)session.getAttribute("page") );
			}else {
				paging.setPage(1);
			}			
			String key="";
			if ( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else  if( session.getAttribute("key") != null ) {
				key=(String)session.getAttribute("key");
			}else {
				key="";
				session.removeAttribute("key");
			}
			
			AdminDao adao = AdminDao.getInstance();
			int count = adao.getAllCount("member", "name", key);
			paging.setTotalCount(count);
			
			ArrayList<MemberVO> list = adao.adminMemberList( paging, key );
			request.setAttribute("memberList", list);
			request.setAttribute("paging", paging);
			request.setAttribute("key", key);
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
