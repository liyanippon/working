(function($){
	jQuery.fn.accordionTree = function(options, param){
		var _this = $(this); 
		_this.accordion(options, param);
    	$.ajax({
    		url: options.url,
    		method: "post",
    		success : function(list) {
    			for(var i=0;i<list.length;i++) {
    				var menu = list[i];
    				var subMenu = "";
    				if (menu.subMenu) {
	    				for(var j=0; j < menu.subMenu.length; j++) {
	    					subMenu = subMenu + "<div class='subMenu' style='cursor: pointer' onclick=showContent(this,'" + menu.subMenu[j].url + "')><span>&nbsp;&nbsp;&nbsp;&nbsp;" + menu.subMenu[j].menuName + "</span></div>";
	    				}
    				}
    				_this.accordion('add', {
    		    		title: menu.menuName,
    		    		content: subMenu,
    		    		selected: false
    		    	});
    			}
    				
			}
    	});
    	
	};
	
	
})(jQuery);

function showContent(obj, url) {
	$(".menuSelected").removeClass("menuSelected");
	$(obj).addClass("menuSelected");
	$('#workspace').panel('refresh', url);
}

