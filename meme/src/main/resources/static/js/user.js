let index = {
	init:function(){
										/* on("click", ); = "첫번째 어떤 이벤트 ",  "무엇을할건지" */
		$("#btn-save").on("click", ()=>{	// function(){} ()=>{} 사용한 이유
			this.save();								// this를 바인딩 하기 위해서
		});	
		
		$("#btn-login").on("click", ()=>{	// function(){} ()=>{} 사용한 이유
			this.login();								// this를 바인딩 하기 위해서
		});		
		
	},
	
		save: function(){
		//	alert('user의 save함수 호출됨');
			let data ={
				username: $("#username").val(),	
				password: $("#password").val(),
				email: $("#email").val()	
			};
			console.log(data);
			
			 //$.ajax().done().fail();	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청;
			// Ajax 호출 시 default가 비동기 호출 
			// Ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해주지만 명시하는게 좋음
			$.ajax({
				// 회원가입 수행 요청 (100초 가정)
				type: "POST",		// restful
				url: "/api/user",  // class path
				// data를 JSON으로 받겠다. (JSON문자열)  
				data: JSON.stringify(data),	// http body 데이터  그래서 조건이 필요함
				contentType: "application/json; charset=utf-8",	// body 데이터가 어떤타입인지 (MIME)
				dataType:"json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 버퍼로(String,문자열)으로 옴
											// 생긴게 json이라면 (적는다면)==> javascript오브젝트로 변경해줌
			}).done(function(resp){
				alert("회원가입이 완료 되었습니다.");
				console.log(resp);
				location.href= "/";		// 기본 패스
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}, // seve
		
		login: function(){
		//	alert('user의 save함수 호출됨');
			let data ={
				username: $("#username").val(),	
				password: $("#password").val()
			};
			console.log(data);
			
			$.ajax({
				// login
				type: "POST",		// restful
				url: "/api/user/login",  // class path
				// data를 JSON으로 받겠다. (JSON문자열)  
				data: JSON.stringify(data),	// http body 데이터  그래서 조건이 필요함
				contentType: "application/json; charset=utf-8",	// body 데이터가 어떤타입인지 (MIME)
				dataType:"json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 버퍼로(String,문자열)으로 옴
											// 생긴게 json이라면 (적는다면)==> javascript오브젝트로 변경해줌
			}).done(function(resp){
				alert("로그인이 완료 되었습니다.");
				console.log(resp);
				location.href= "/";		// 기본 패스
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		} // login
}

index.init();



