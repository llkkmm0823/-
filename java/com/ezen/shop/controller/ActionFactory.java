package com.ezen.shop.controller;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.controller.action.IndexAction;
import com.ezen.shop.controller.action.admin.AdminAction;
import com.ezen.shop.controller.action.admin.AdminLoginAction;
import com.ezen.shop.controller.action.admin.AdminLogoutAction;
import com.ezen.shop.controller.action.admin.AdminMemberListAction;
import com.ezen.shop.controller.action.admin.AdminOrderListAction;
import com.ezen.shop.controller.action.admin.AdminOrderSaveAction;
import com.ezen.shop.controller.action.admin.AdminProductDetailAction;
import com.ezen.shop.controller.action.admin.AdminProductListAction;
import com.ezen.shop.controller.action.admin.AdminProductUpdateAction;
import com.ezen.shop.controller.action.admin.AdminProductUpdateFormAction;
import com.ezen.shop.controller.action.admin.AdminProductWriteAction;
import com.ezen.shop.controller.action.admin.AdminProductWriteFormAction;
import com.ezen.shop.controller.action.admin.AdminQnaDetailAction;
import com.ezen.shop.controller.action.admin.AdminQnaListAction;
import com.ezen.shop.controller.action.admin.AdminQnaRepSaveAction;
import com.ezen.shop.controller.action.member.ContractAction;
import com.ezen.shop.controller.action.member.DeleteMemberAction;
import com.ezen.shop.controller.action.member.EditFormAction;
import com.ezen.shop.controller.action.member.FindZipNumAction;
import com.ezen.shop.controller.action.member.IdCheckFormAction;
import com.ezen.shop.controller.action.member.JoinAction;
import com.ezen.shop.controller.action.member.JoinFormAction;
import com.ezen.shop.controller.action.member.LoginAction;
import com.ezen.shop.controller.action.member.LoginFormAction;
import com.ezen.shop.controller.action.member.LogoutAction;
import com.ezen.shop.controller.action.member.MemberUpdateAction;
import com.ezen.shop.controller.action.mypage.CartDeleteAction;
import com.ezen.shop.controller.action.mypage.CartInsertAction;
import com.ezen.shop.controller.action.mypage.CartListAction;
import com.ezen.shop.controller.action.mypage.MypageAction;
import com.ezen.shop.controller.action.mypage.OrderAllAction;
import com.ezen.shop.controller.action.mypage.OrderDetailAction;
import com.ezen.shop.controller.action.mypage.OrderInsertAction;
import com.ezen.shop.controller.action.mypage.OrderInsertOneAction;
import com.ezen.shop.controller.action.mypage.OrderListAction;
import com.ezen.shop.controller.action.product.CategoryAction;
import com.ezen.shop.controller.action.product.ProductDetailAction;
import com.ezen.shop.controller.action.qna.QnaListAction;
import com.ezen.shop.controller.action.qna.QnaViewAction;
import com.ezen.shop.controller.action.qna.QnaWriteAction;
import com.ezen.shop.controller.action.qna.QnaWriteFormAction;

public class ActionFactory {
	
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();
		else if( command.equals("loginForm") ) ac = new LoginFormAction();
		else if( command.equals("login") ) ac = new LoginAction();
		else if( command.equals("logout") ) ac = new LogoutAction();
		else if( command.equals("contract") ) ac = new ContractAction();
		else if( command.equals("joinForm") ) ac = new JoinFormAction();
		else if( command.equals("idCheckForm") ) ac = new IdCheckFormAction();
		else if( command.equals("findZipNum") ) ac = new FindZipNumAction();
		else if( command.equals("join") ) ac = new JoinAction();
		else if( command.equals("editForm") ) ac = new EditFormAction();
		else if( command.equals("memberUpdate") ) ac = new MemberUpdateAction();
		// product
		else if( command.equals("category") ) ac = new CategoryAction();
		else if( command.equals("productDetail") ) ac = new ProductDetailAction();
		// mypage(cart)
		else if( command.equals("cartInsert") ) ac = new CartInsertAction();
		else if( command.equals("cartList") ) ac = new CartListAction();
		else if( command.equals("cartDelete") ) ac = new CartDeleteAction();
		// mypage(order)
		else if( command.equals("orderInsert") ) ac = new OrderInsertAction();
		else if( command.equals("orderList") ) ac = new OrderListAction(); 
		else if( command.equals("orderInsertOne") ) ac = new OrderInsertOneAction();
		else if( command.equals("mypage") ) ac = new MypageAction();
		else if( command.equals("orderAll") ) ac = new OrderAllAction();
		else if( command.equals("orderDetail") ) ac = new OrderDetailAction();	
		else if( command.equals("deleteMember") ) ac = new DeleteMemberAction();
		// qna
		else if( command.equals("qnaList") ) ac = new QnaListAction();
		else if( command.equals("qnaView") ) ac = new QnaViewAction();
		else if( command.equals("qnaWriteForm") ) ac = new QnaWriteFormAction();
		else if( command.equals("qnaWrite") ) ac = new QnaWriteAction();
		// admin
		else if( command.equals("admin") ) ac = new AdminAction();
		else if( command.equals("adminLogin") ) ac = new AdminLoginAction();
		else if( command.equals("adminProductList") ) ac = new AdminProductListAction();
		else if( command.equals("adminLogout") ) ac = new AdminLogoutAction();
		else if( command.equals("adminProductDetail") ) ac = new AdminProductDetailAction();
		else if( command.equals("adminProductWriteForm") ) ac = new AdminProductWriteFormAction();
		else if( command.equals("adminProductWrite") ) ac = new AdminProductWriteAction();
		else if( command.equals("adminProductUpdateForm") ) ac = new AdminProductUpdateFormAction();
		else if( command.equals("adminProductUpdate") ) ac = new AdminProductUpdateAction();
		else if( command.equals("adminOrderList") ) ac = new AdminOrderListAction();
		else if( command.equals("adminOrderSave") ) ac = new AdminOrderSaveAction();
		else if( command.equals("adminMemberList") ) ac = new AdminMemberListAction();
		else if( command.equals("adminQnaList") ) ac = new AdminQnaListAction();
		else if( command.equals("adminQnaDetail") ) ac = new AdminQnaDetailAction();
		else if( command.equals("adminQnaRepSave") ) ac = new AdminQnaRepSaveAction();
	
		
		
		return ac;
	}
}











