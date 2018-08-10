(function ($) {
	$.stringSearch = function (obj, opt) {
			/************************************************************************************************
				원하는 값을 찾고 tab 키를 눌렀을때 주소 창으로 이동하는것 방지
			*************************************************************************************************/
			$(obj).bind( "keydown", function( event ) {
				if ( event.keyCode === $.ui.keyCode.TAB && $( this ).autocomplete( "instance" ).menu.active ) {
					event.preventDefault();
				} 
			});
			
			/************************************************************************************************
				값을 optSplitWord 로 자르기
			*************************************************************************************************/
			function split( val ) {
				return val.split(optSplitWord);
			}

			var optUrl			= opt.dataUrl;					//검색할 url (json 리턴)
			var optdataMethod		= opt.dataMethod;				//쿼리 페이지로 넘어가는 값
			var optSplitWord		= opt.splitWord;				
			var optmentWord			= opt.mentWord;					// 실시간 검색을 사용하기 위한 첨자
			//var optShowPhoto		= opt.showPhoto;				// 사진 사용여부
			var optDelay			= opt.delay;					// 검색 딜레이
			var optMultiSearch		= opt.multiSearch;				// 멀티서치 사용여부
			var optMinLength		= opt.minLength + 1;				// 최소 검색 글자( @ 를 제외 시키기 위해 최소 글자에서 1 을 더한다.)
			//var optTypingRestrict	= opt.typingRestrict;					// 타이핑 제한 시작은 무조건 @ 시작하기. 임의로 적기 불가능 

			console.log("셋팅 값 "+optUrl);
			console.log("셋팅 값 "+optdataMethod);
			console.log("셋팅 값 "+optSplitWord);
			//console.log("셋팅 값 "+optShowPhoto);
			console.log("셋팅 값 "+optDelay);
			console.log("셋팅 값 "+optMultiSearch);
			console.log("셋팅 값 "+optMinLength);
			//console.log("셋팅 값 "+optTypingRestrict);
			
			

			//jQuery autocomplete 로드
			$(obj).autocomplete({
				delay: optDelay,
				
				source: function ( request, response ) {
					
					
					var searchWord = '';										// 검색 글자 담아두기
					var searchAva = false;										// 검색할것인지 여부
					var atCheck = request.term.split(optSplitWord);				// 내용을 " " 으로 split
					var atCheckLength = atCheck.length;							// split 된 배열의 총길이
					
					
					/************************************************************************************************
						멀티 서치 확인
					*************************************************************************************************/
					if (atCheckLength == 1) {										// 배열의 길이가 1개 즉 단일값
						
						if (atCheck[0].charAt(0) == optmentWord) {					// 현재 값의 0 번째 인덱스가 optmentWord 와 같을때
							searchAva = true;									
							searchWord = atCheck[0].replace(optmentWord, "");		// atCheck[0] 내용에서 optmentWord 를 제외하고 
						}

					} else {
						var lastWord = atCheck[(atCheckLength-1)];					// 한개 이상의 배열에서 마지막에 입력한 단어
						if (lastWord.charAt(0) == optmentWord) {
							searchAva = true;
							searchWord = lastWord.replace(optmentWord, "");
						}
					}

					
					/************************************************************************************************
							타이핑 제한이 true 일경우 첫글자는 무조건 optmentWord 로 시작해야 한다.
					*************************************************************************************************/
					/*
					if (optTypingRestrict) {
						if (! optMultiSearch)
						{
						
							if (atCheck[0].charAt(0) != optmentWord) {
								alert("검색 단어의 시작은 '" + optmentWord + "'로 시작해야 합니다.");
								$(obj).val("");
								return;
							}
						}
					}
					*/


					/************************************************************************************************
						searchAva 값이 true 라고 해도 검색하고자 하는 단어수가 optMinLength 보다 짧으면 false 로 바꾼다
					*************************************************************************************************/
					if ((searchAva == true) && (searchWord.length < optMinLength)) { 
						searchAva = false;
					}


					/************************************************************************************************
						여기 까지 searchAva 값이 true 이면 ajax 통신을 할것이고. 최종적인 검색 값은 searchWord 이다.
					*************************************************************************************************/
					// ajax 통신 시작
					if (searchAva) {
						// ajax 통신 시작
						$.ajax ({
							url: optUrl,
							dataType: "json",
							contentType: "application/json; charset=utf-8",
								data: {
									word: searchWord,
									method: optdataMethod
								},
								success: function( json ) {
									response( json );
								},
								error: function() {
									alert('현재 실시간 검색을 이용할 수 없습니다. ');
								}
						});
					}
					// ajax 통신 끝
				},
				
				focus: function() {
					return false;
				},
				
				select: function (event, ui) {
					event.preventDefault();
					
					if (optMultiSearch) {
						/* 멀티 검색 사용시 */
						var terms = split(this.value);
						console.log(this.value);
						terms.pop();
						terms.push(ui.item.label );
						terms.push( "" );
						this.value = terms.join( optSplitWord );
					} else {
						/* 단일 검색 사용시 */
						$(obj).val(ui.item.label );
					}
					return false;
				}
			});

	 };

} (jQuery));