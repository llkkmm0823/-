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

public class MypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "mypage/mypage.jsp";
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if(mvo==null) {
	    	url = "shop.do?command=loginForm";
	    }else {
	    	// 진행중인 주문 내역
	    	// 현재 로그인 한 사용자의 아직 배송안된 주문내역이 보여집니다
	    	// 예를 들어 한번에 2,3개의 상품씩  3회에 걸쳐서 주문한 상태라면.... 
	    	// 그리고 그 주문들이 하나도 배송이 안된 상태(배송전)라면...
	    	// 진행중인 주문 내역은 세줄이 표시됩니다(주문 건별 표시)
	    	
	    	// 표시내용은 주문의 대표상품 이름을 이용하여    슬리퍼 포함 3,   겨울용부츠 외 2 ...  등등  총 세줄이 표시됩니다
	    	// 그리고 각 행에는   상세보기 버튼이 있어서 클릭하면 그 주문에 속한 세개의 상품을 볼 수 있습니다   
	    	
	    	// mypage.jsp 에 최종 전달되서 화면에 보여질 리스트 생성
	    	ArrayList<OrderVO> finalList = new ArrayList<OrderVO>();
	    	// (  finalList.get(0).getName() -> "XXXX 외 2건" )  와 같이 list 를 구성할 예정입니다
	    	
	    	
	    	// 이를 위해서 가장 먼저 필요한 사항은  주문 번호들 입니다
	    	// order_view 에서 주문자 아이디로 검색하고, result 가 1인 주문들을 검색해서,  주문 번호(oseq)들을 조회합니다
	    	// 위의 예를 든 상태라면  주문번호들이 다음과 같습니다    
	    	// 22 22 22  24 24  26  27 27 27  <- 조회된 주문번호(oseq)들입니다
	    	
	    	// 그러나 정작 우리에게 필요한건   22  24  26  27  이므로, sql문에  distinct 키워드를 써서 조회해 옵니다
	    	// select distinct oseq from order_view where id=? and result='1'  
	    	
	    	OrderDao odao = OrderDao.getInstance();
	    	// 주문번호들의 리스트, 중복을 없앤 검색
	    	ArrayList<Integer> oseqList = odao.selectOseqOrderIng( mvo.getId() );
	    	
	    	// 주문번호들로 반복실행을 하면서....
	    	// 주문 번호별로  상품을 다시 조회해서  
	    	// 세개의 상품이 있다면  
	    	// 그들 가격의 총금액을계산하고, 첫번째 상품의 price2로 저장합니다.  
	    	// 첫번째 상품의 상품이름도,   대표상품 이름 외 2건 이라고 바꿔줍니다.
	    	// 그 대표상품을  최종화면에 표시될 리스트(finalList)에 따로 담습니다.
	    	
	    	// 요약하면
	    	// 22번 주문의  3개의 상품에서 첫번째 상품의   상품이름을  "상품이름 포함 2 건"  으로 변경
	    	// 금액은  3개의 가격을 합산한 금액을   22번상품의 price2 에 저장
	    	// 그 변경된 첫번째 상품을  finalList 에 저장
	    	
	    	for( Integer oseq : oseqList ) {
	    		// 주문 번호로 상품 검색
	    		ArrayList<OrderVO> orderListByOseq = odao.listOrderByOseq(oseq);
	    		
	    		// 원본 보호 차원에서 리턴된 리스트의 첫번째 상품을 별도에 변수에 저장
	    		OrderVO firstProduct = orderListByOseq.get(0); 
	    		
	    		// 총 결제금액 계산
	    		int totalPrice = 0;
	    		for( OrderVO ovo : orderListByOseq) 
	    			totalPrice += ovo.getPrice2() * ovo.getQuantity();
	   
	    		// 상품명과 가격필드 변경
	    		firstProduct.setPrice2(totalPrice);
	    		firstProduct.setPname( firstProduct.getPname() + " 포함 " + orderListByOseq.size() + " 건" );
	    		
	    		// 모든 변경을 마친  firstProduct 는 finalList  에 저장
	    		finalList.add( firstProduct );
	    	}
	    	request.setAttribute("orderList", finalList);
	    	request.setAttribute("title", "진행 중인 주문 내역");
	    }
	    request.getRequestDispatcher(url).forward(request, response);
	}
}
