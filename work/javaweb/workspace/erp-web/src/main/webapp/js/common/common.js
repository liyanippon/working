/**
 * 项目公共方法
 */

/**
 * 重写.ajax方法，调用异常时跳转到登录页面，处理session超时
 */
var ctx;
function sendAjaxRequest(url,data,successFunc,errorFunc,type){
	if(type == null || type == undefined || type == ""){
		type = "POST";
	}
	if(!ctx){
		ctx = "";
	}
	$.ajax({  
	    type: type,  
	    url: ctx + url,  
	    data: data,
	    success: function(data){  
	    	if(data == "true"){
	    		successFunc(data);
	    	} else {
	    		//session 超时
	    		if(!parent){
	    			//弹出窗口
	    			//alert("父窗口跳转");
	    			parent.location = ctx + "/login.do";
	    		}else{
	    			window.location = ctx + "/login.do";
	    		}
	    	}
	    },  
	    error: function(){
	    	if(errorFunc){
	    		errorFunc();
	    	}else{
	    		//window.location = ctx + "/login.do";
	    		showErrorMsg("连接超时，请重新登录！");
	    	}
	    }
	});
}

/**
 * 通用提示信息
 * @param msg
 */
function showInfoMsg(msg){
	showMsg("提示",msg,"info");
}

/**
 * 通用警告信息
 */
function showErrorMsg(msg){
	showMsg("警告",msg,"error");
}

/**
 * 自定义信息
 * 扩展messager.show(). 增加icon显示
 * @param title
 * @param msg
 */
function showMsg(title,msg,icon){
	var _msg;
	if(icon){
		_msg = '<div style="width:100%;">';
        _msg += '<div class="messager-icon messager-' + icon + '"></div>';
        _msg += '<div style="word-break : break-all;font-size:15px;">'+ msg +'</div>';
        _msg += '</div>';
	}else{
		_msg = msg;
	}
	$.messager.show({
		title:title,
		msg:_msg,
		timeout:3000,
		showType:'show'
	});  
}

/**
 * 模态窗口
 * @param title
 * @param width
 * @param height
 * @param url
 */
function showModalWindow(title,width,height,url){
	sendAjaxRequest("/checkSession.do",null,function(){
		layer.open({
			  type: 2,
			  title: title,
			  shadeClose: false,
			  shade: 0.1,
			  area: [width + 'px', height + 'px'],
			  shift:1,
			  content: ctx + url 
		}); 
	});
}

/**
 * 弹出窗口session超时
 * @param ctx
 */
function sessionOutForPopup(ctx){
	sendAjaxRequest(ctx + "/checkSession.do",null,function(){});
}
