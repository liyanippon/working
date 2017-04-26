/**
 * 消息弹出插件
 */
(function($){
	$.myAjax = function(options){
		
		$.ajax({  
		    type: options.type,  
		    url: options.url,  
		    data: options.data,
		    async: options.async,
		    cache: options.cache,
		    type: options.type,
		    timeout: options.timeout,
		    success: function(result){  
		    	options.success(result);
		    },  
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
		    	var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		    	var redirectUrl = XMLHttpRequest.getResponseHeader("redirectUrl");
		    	if(sessionstatus == "timeout") {
		    		var top = getTopWinow(); //获取当前页面的顶层窗口对象
		    		top.location.href = redirectUrl;
		    	} else {
		    		options.error(XMLHttpRequest, textStatus, errorThrown);
		    	}
		    }
		});
	}
	
})(jQuery);



/** 
* 在页面中任何嵌套层次的窗口中获取顶层窗口 
* @return 当前页面的顶层窗口对象 
*/
function getTopWinow(){ 
	var p = window; 
	while(p != p.parent){ 
		p = p.parent; 
　　　} 
　　　return p; 
}

