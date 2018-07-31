"use strict"

// 자주 사용할 함수를 라이브러리화 시킨다.
let bit = function(value) {
    var el = [];
    if (value instanceof HTMLElement) {
        el[0] = value;
    } else if (value.startsWith('<')) {
    	el[0] = document.createElement(
                value.substr(1, value.length - 2));
    } else {
        var list = document.querySelectorAll(value);
        // selector 로 찾은 태그들은 빈배열로 옮긴다
        for(var i=0;i<list.length;i++){
        	el[i]= list[i];
        	
        }
    }
    
    
   // if(el.length == 0) return null;  // 못찾아도 빈배열을 추가하라
    // 개발자가 쓰기 좋은 함수를 추가해서 리턴하자!
    
    el.html = function(value) {
        if (arguments.length == 0) {
            return el[0].innerHTML;
        }
        for(var e of el)
        	e.innerHTML = value;
        return el;
    };
    
    el.append = function(child) {
    	for(var e of el)
    		e.appendChild(child);
        return el;
    };
    
    el.appendTo = function(parent) {
    	for(var e of el)
    		parent[parent.length - 1].appendChild(e);
        return el;
    };
    
    el.attr = function(name, value) {
        if (arguments.length < 2) {
            return el[0].getAttribute(name); // 배열에 0번째만 리턴 한다.
        }
        for(var e of el)
        	e.setAttribute(name, value);
        return el;
    };
    
    el.removeAttr = function(name) {
    	for(var e of el)
    		e.removeAttribute(name);
    	return el;
    };
    el.on = function(name, p2,p3){
    	var selector =null;
    	var handler =null;
    	
    	if(arguments.length ==2) handler =p2;
    	
    	if(arguments.length ==3){
    		selector= p2;
    		handler = p3;
    	}
    	
    	for(var e of el){
    		if(!selector){
    			// selector 가 지정 되어 있징 않으면,
    			// 해당 태그에 대해 이벤트가 발생했을때 핸들러를 호출한다.
    			e.addEventListener(name, handler);
    		} else{
    			// selector 가 지정되어 있으면,
    			// 핸들러를 호출하기 전에 selector 에 해당하는 것인지 검사하는
    			// 함수가 먼저 호출되게 한다.
    			e.addEventListener(name,function(event){
    				//console.log('임의로 만든 핸들러를 호출한다.')
    				// 현재 태그의 자신 태그중에 selector 조건에 해당되는
    				// 자식 태그들을 찾는다.
    				var selectorTargets = e.querySelectorAll(selector);
    					//클릭하는 시점에 찾는 
    				
    				// 그 자식 태그들 중에 이이벤트가 발생된 태그를 찾는다.
    				for(var target of selectorTargets){
    					// 만약 이벤트가 발생된 태그와 일치하는 자식 태그가 있다면
    					// 그때서야 핸들러를 호출해준다/
    					// 속도가 느리다.
    					if(event.target ==target){
    						handler(event);
    						break;
    					}
    				}
    			});
    		}
    		
    	}
    	return el;
    };
    
    el.click = function(handler){
    	return el.on('click', handler);
    };
    
    /////view 부분
    el.css =function(name, value){
    	if(arguments.length ==1){
    		return el[0].style[name];
    	}
    	for(var e of el){
    		e.style[name] =value;
    	}
    	return el;
    }; //el.css 종료
    
    //el.value
    el.val =function(value){
    	if(arguments.length ==0){
    		return el[0].value;
    	}
    	for(var e of el){
    		e.value =value;
    	}
    	return el;
    }; // el.val 종료
    
    return el;
};

bit.parseQuery = function(url) {
    var paramMap = {};

    var qs = url.split('?');
    if (qs.length > 1) {
        var params = qs[1].split('&');
        for (var param of params) {
            var kv = param.split('=')
            paramMap[kv[0]] = kv[1];
        }
    }
    return paramMap;
};

bit.ajax = function(url, settings) {
    if (settings == undefined) 
        settings = {};
    
    if (settings.dataType == undefined)
        settings.dataType = 'text';
    
    if (settings.method == undefined) 
        settings.method = 'GET';
    
    var xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (xhr.readyState < 4) return;
        if (xhr.status !== 200) {
            if (settings.error)
                error();
            return;
        }
        let data = xhr.responseText;
        if (settings.dataType == 'json') {
            data = JSON.parse(xhr.responseText);
        }

        if (settings.success) {
            settings.success(data);
        }

        // done()에 의해 등록된 함수가 있다면 그 함수를 호출한다.
        if (done) {  
            done(data);
        }
    };
    
    // settings에 서버로 보낼 data가 있다면 url에 포함해야 한다.
    //query string 으로 만든다. 
    var qs = '';
    if (settings.data) {
        for (var propName in settings.data) {
            qs += `&${propName}=${settings.data[propName]}`;
        }
    }
    
    if(settings.method == 'GET'){
    	if (url.indexOf('?') == -1)
    		url += '?';
    	url += qs;
    	console.log(url);
    	xhr.open(settings.method, url, true);
    	xhr.send();
    }else{
    	xhr.open(settings.method, url, true);
    	xhr.setRequestHeader('Content-Type'
	    		,'application/x-www-form-urlencoded');
    	xhr.send(qs);
    }
    
    // XMLHttpRequest 객체를 리턴하기 전에 함수를 추가한다.
    let done;
    xhr.done = function(func) {
        done = func;
    };
    
    return xhr;
};

bit.getJSON = function(url, p2, p3) {
    let data = {};
    let success = null;
    
    if (arguments.length > 1) {
        if (typeof p2 == "function") success = p2;
        else data = p2;
        
        if (typeof p3 == "function") success = p3;
    }
    
    return bit.ajax(url, {
        dataType: 'json',
        data: data,
        success: success
    });
}

bit.post = function(url, p2, p3, p4) {
    let data = {};
    let success = null;
    let dataType ='text'; // 기본적으로 text
    
    
    
    if (arguments.length == 2) {
    	if(typeof p3 =="function"){
    		data = p2;
    		success = p3;
    	}else if (typeof p2 == "function") {
        	success = p2;
        	 data = p3;
        }else{
        	data = p2;
        	dataType = p3;
        }
        
        
    }else if (arguments.length >2){
    	data =p2;
    	success = p3;
    	dataType =p4;
    }
    
    return bit.ajax(url, {
    	method:'POST',
        dataType: dataType,
        data: data,
        success: success
    });
}//post 종ㄽ


let $ = bit;












//"use strict"
//
////자주 사용할 함수를 라이브러리화 시킨다.
//let bit = function(value) {
//    var e;
//    if(value instanceof HTMLElement){
//    	e = value ;
//    }
//    else if (value.startsWith('<')) {
//        e = document.createElement(
//                value.substr(1, value.length - 2));
//    } else {
//        e = document.querySelector(value);
//    }
//    
//    // 개발자가 쓰기 좋은 함수를 추가해서 리턴하자!
//    e.html = function(value) {
//    	if (arguments.length ==0){
//    		return e.innerHTML;
//    	}
//        e.innerHTML = value;
//        return e;
//    };
//    
//    e.append = function(child) {
//        e.appendChild(child);
//        return e;
//    };
//    
//    e.appendTo = function(parent) {
//        parent.appendChild(e);
//        return e;
//    };
//    e.attr = function(name, value){
//    	if(arguments.length <2){
//    		return e.getAtrribute(name);  // 달라
//    	}
//    	e.setAttribute(name, value);
//    };
//    e.removeAttr = function(name, value){
//    	e.removeAttribute(name, value);
//    }
//    
//    return e;
//};
//
//bit.parseQuery = function(url) {
//    var paramMap = {};
//
//    var qs = url.split('?');
//    if (qs.length > 1) {
//        var params = qs[1].split('&');
//        for (var param of params) {
//            var kv = param.split('=')
//            paramMap[kv[0]] = kv[1];
//        }
//    }
//    return paramMap;
//};
//
//bit.ajax = function (url, settings){
//	var xhr = new XMLHttpRequest();
//	
//	if(settings == undefined)
//		settings ={};
//	if(settings.dataType == undefined)
//		settings.dataType ="text";
//	
//	if(settings.method == undefined)
//		settings.method ='GET',
//		
//		
//   
//    
//    xhr.onreadystatechange = function() {
//        if (xhr.readyState < 4) return;
//        if (xhr.status !== 200) {
//            if(settings.error)
//            	error();
//            return;
//        }
//        let = JSON.parse(xhr.responseText);
//        
//        if(settings.success){
//        	if(settings.dataType == 'json'){
//        		data = JSON.parse(xhr.responseText);
//        	}
//        	settings.success(data);
//        	
//        	// done() 의 의해 두번쨰 success 함수가 등록되어있다면
//        	if(done){
//        		done(data);
//        	}
//        }
//    };
//    
//    // settings 에 서버로 보낼 data 가 있다면 url 에 포함해야한다.
//    if(settings.data){
//    	var qs ='';
//    	for(var propName in settings.data){
//    		qs += `&${propName}=${settings.data[propName]}`;
//    	}
//    	
//    	if(url.indexOf('?')== -1)
//    		url +='?';
//    	url += qs;
//    }
//    console.log(url)
//    xhr.open(settings.method, url,true);
//    xhr.send();
//    
//    
//    //XMLHttpRequest 객체를 리턴하기 전에 함수를 리리턴
//    let done;
//    xhr.done =function(func){
//    	done = func;
//    }
//    
//    return xhr;
//	
//};
//
//bit.getJSON = function(url, p2, p3){
//	let data ={};
//	let success =null;
//	
//	if(arguments.length >1){}
//		if(typeof p2 == "function") success =p2;
//		else data = p2;
//		if(typeof p3 == "function") success =p3;
//	}
//
//	return bit.ajax(url,{
//		dataType:'json',
//		data : data,
//		success : success
//	});
//	
//}
//
//
//let $ = bit;
//
