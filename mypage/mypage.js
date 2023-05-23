function go_cart(){
	if (document.formm.quantity.value == "") {
	    alert("수량을 입력하여 주세요.");
	    document.formm.quantity.focus();
	}else{
		document.formm.action ="shop.do?command=cartInsert";
		document.formm.submit();
	}
}



function go_cart_delete(){
	
	// 자바스크립트에서 jsp 페이지내에 있는  동일한 name 의 두개이상의 입력란(input 태그)들은 하나의 이름의 배열로 인식합니다
	// document.formm.cseq[ ]
	
	// 그말은 곧 배열의 크기를 나태내는 length 속성이 존재한다는 뜻이며, 배열내의 요소 갯수를 저장하고 있습니다.
	// 만약 같은 name의  input 태그가 여러개가 아니고 한개만 존재하는 상태라면  length 값은 undefined 가 됩니다.
	 
	// 아래에서 몇개의 체크박스가 체크되었는지 검사를 할껀데, 
	// 이때 체크박스냐 한개냐 아니면 두개이상이냐를  length 값으로 구분해서 체크갯수를 count합니다
	var count = 0;
	if( document.formm.cseq.length == undefined){
		// 체크박스가 한개인 경우
		if(document.formm.cseq.checked==true)
			count++;
	}else{
		// 체크박스가 두개 이상인 경우 반복실행문을 이용하여 모든 체크박스를 하나씩 점검하여 체크된 갯수를 count 합니다
		for( var i=0; i < document.formm.cseq.length; i++){
			if(document.formm.cseq[i].checked==true)
				count++;
		}
	}
	
	if( count == 0){
		alert('삭제할 항목을 선택하세요');
	}else{
		document.formm.action = "shop.do?command=cartDelete";
	    document.formm.submit();
	    // jsp 파일에 있는 체크된 checkbox 들의 value들(cseq 값들)이 배열로 전송됩니다.
	}
}





function go_order_insert(){
	document.formm.action ="shop.do?command=orderInsert";
	document.formm.submit();
}






function go_order(){
	document.formm.action ="shop.do?command=orderInsertOne";
	document.formm.submit();

}







