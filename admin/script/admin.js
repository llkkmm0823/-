function workerCheck(){
	  if(document.frm.workId.value==""){
	      	alert("아이디를 입력하세요.");
	      	return false;
	  }else if(document.frm.workPwd.value==""){
	     	alert("비밀번호를 입력하세요.");
	      	return false;
	  }
	  return true;  
}

function go_detail( pseq ){
	
	document.frm.action = "shop.do?command=adminProductDetail&pseq=" + pseq;
	document.frm.submit();
}

function go_mov(){
	location.href = "shop.do?command=adminProductList";
}
	      	
function go_search( comm ){
		if( document.frm.key.value == "" ){
		alert("검색버튼 사용시에는 검색어 입력이 필수입니다");
	 	return;
	 }
	var url = "shop.do?command=" + comm + "&page=1";   // 검색어로 검색한 결과의 1페이지로 이동
	document.frm.action = url;
	document.frm.submit();
}

function go_total( comm ) {
	document.frm.key.value="";
	document.frm.action = "shop.do?command=" + comm + "&page=1";
	document.frm.submit();
}

function go_wrt(){
	document.frm.action = "shop.do?command=adminProductWriteForm";
	document.frm.submit();
}

function cal(){
	if (document.frm.price2.value =="" || document.frm.price1.value=="") return;
	document.frm.price3.value = document.frm.price2.value - document.frm.price1.value; 
}

function go_save(){
	var theForm = document.frm;
	if( theForm.kind.value==""){  // if( document.frm.kind.value=="" )
		alert('상품분류를 선택하세요');
		theForm.kind.focus();
	}else if (theForm.name.value == "") {
		alert('상품명을 입력하세요.'); 	
		theForm.name.focus();	
	} else if (theForm.price1.value == "") {
		alert('원가를 입력하세요.'); 		
		theForm.price1.focus();
	} else if (theForm.price2.value == "") {
		alert('판매가를 입력하세요.'); 		
		theForm.price2.focus();
	} else if (theForm.content.value == "") {
		alert('상품상세를 입력하세요.'); 		
		theForm.content.focus();
	} else if (theForm.image.value == "") {
		alert('상품이미지들 입력하세요.'); 	
		theForm.image.focus();	
	} else{
		theForm.action = "shop.do?command=adminProductWrite";
		theForm.submit();
	}
}






function go_mod(pseq){
	var url = "shop.do?command=adminProductUpdateForm&pseq=" + pseq;
	location.href=url;
	// document.frm.action = url;
	// document.frm.submit();
}








function go_mod_save(){
	if (document.frm.kind.value == '') {
		  alert('상품분류를 선택하세요'); 	  
			document.frm.kind.focus();
	 } else if (document.frm.name.value == '') {
		  alert('상품명을 입력하세요');	  
			document.frm.name.focus();
	 } else if (document.frm.price1.value == '') {
		  alert('원가를 입력하세요');	 
 		document.frm.price1.focus();
	 } else if (document.frm.price2.value == '') {
		  alert('판매가를 입력하세요');	  
			document.frm.price2.focus();
	 } else if (document.frm.content.value == '') {
		  alert('상품상세를 입력하세요');	  
			document.frm.content.focus();
	 }else{
		if( confirm('수정하시겠습니까?') ){
			 document.frm.action = "shop.do?command=adminProductUpdate";
			 document.frm.submit();
		}
	}
}




function go_order_save(){
	// 현재 화면에 보여지고 있는 주문들의 체크박스들의 체크된 상태가 몇개나 체크되어 있는지 갯수를 count
	var count = 0;
	if ( document.frm.result.length == undefined){
		if(document.frm.result.checked == true) count++;
	}else{ // 화면에 표시된 체크박스가 두 개 이상일 경우 
		for(var i=0; i<document.frm.result.length; i++)
			if( document.frm.result[i].checked==true)
				count++;
	}
	
	if (count ==0){
		alert("주문처리할 항목을 선택해주세요");
	}else{
		document.frm.action ="shop.do?command=adminOrderSave";
		document.frm.submit();
	}
	// 주문 처리하고 (주문의 result값을 '1' -> '2' 로 변경) orderList.jsp로 되돌아 간다. 	
}


function go_view( qseq ){
	location.href = " shop.do?command=adminQnaDetail&qseq=" + qseq;
}



function go_rep(qseq){
	document.frm.action="shop.do?command=adminQnaRepSave";
	document.frm.submit();
	// 답변 글 등록 & rep 필드를 2로 업데이트
}



