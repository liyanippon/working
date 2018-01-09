$.fn
		.extend({
			locateTo : function(target, position, adjust) {
				if (!adjust || typeof adjust != "object") {
					adjust = {};
				}

				if (!adjust.x) {
					adjust.x = 0;
				}

				if (!adjust.y) {
					adjust.y = 0;
				}

				if (!position || typeof position != "object") {
					position = {};
				}

				var obj = null;

				if (typeof target == "string") {
					obj = $("#" + target);
				} else {
					obj = target;
				}

				if (!obj) {
					return;
				}

				var src = $(this);

				if (position.y == "bottom") {
					src.css("top", obj.offset().top + obj.outerHeight() - 0
							+ adjust.y);
				} else {
					src.css("top", obj.offset().top - 0 + adjust.y);
				}

				if (position.x == "left") {
					src.css("left", obj.offset().left - 0 + adjust.x);
				} else {
					src.css("left", obj.offset().left + obj.outerWidth() - 0
							+ adjust.x);
				}
			}
		});

$.extend({
	showError : function(obj, msg, args) {

		if (!obj) {
			return;
		}

		if (args) {
			if (typeof args == "string") {
				args = [ args ];
			}

			for ( var i = 0; i < args.length; i++) {
				msg = msg.replace("{" + i + "}", args[i]);
			}
		}

		obj.addClass("error_field");

		var tip = $("#__validator_error_tip");
		if (!tip || tip.size() == 0) {

			tip = $("<div id='__validator_error_tip'>");
			tip.appendTo("body");
			tip.css({
				color : "red",
				position : "absolute"
			});
		}

		tip.html(msg);
		tip.show();
		tip.attr("attachObjectId", obj.attr("id"));
		tip.locateTo(obj, {
			x : "right",
			y : "top"
		}, {
			x : 5,
			y : 2
		});

		$("#" + obj.attr("id") + "_notice").hide();

	},
	clearError : function(objs) {
		/*
		 * $(objs).each(function () { this.removeClass("error_field"); $("#" +
		 * this.attr("id") + "_notice").show(); });
		 */

		var tip = $("#__validator_error_tip");

		var objId = tip.attr("attachObjectId");

		var obj = $("#" + objId);
		obj.removeClass("error_field");
		$("#" + obj.attr("id") + "_notice").show();

		tip.hide();
		
		$(".action_error").hide();

	}
});

function checkDate(strInputDate) {
	if (strInputDate == "")
		return false;
	// strInputDate = strInputDate.replace(/-/g, "/");
	if (!/^\d{4}-\d{2}-\d{2}$/.test(strInputDate))
		return false;
	var d = new Date(strInputDate);
	if (isNaN(d))
		return false;
	var arr = strInputDate.split("-");
	return ((parseInt(arr[0], 10) == d.getFullYear())
			&& (parseInt(arr[1], 10) == (d.getMonth() + 1)) && (parseInt(
			arr[2], 10) == d.getDate()));
}

var RegUtil = {
		email : function (str) {
			return /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(str);
		},
		
		phone : function (str) {
			return /^[0-9-]+$/.test(str);
		},
		
		price : function (str) {
			return /^[0-9-]+(\.[0-9][0-9]?)?$/.test(str);
		},
		
		mobile : function (str) {
			return /^1\d{10}$/.test(str);			
		},
		
		url : function (str) {
			 return /^(https|http)?:\/\/(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,5})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)$/i.test(str);
		}
};

$.extend({
	escapeHTML : function(txt) {
		var div = document.createElement('div');
		var text = document.createTextNode(txt);
		div.appendChild(text);
		return div.innerHTML;
	},
	unescapeHTML : function (txt) {
		var div = document.createElement('div');
		txt = txt.replace(/<\/?[^>]+>/g, '');
		div.innerHTML = txt;
		
		return div.childNodes[0].nodeValue;
		
	}
});

function ScrollLayer(content, axis, direction) {
	ScrollLayer[content] = setInterval(function() {
		var obj = $("#" + content);
		var top = parseInt(obj.css("top"));
		var left = parseInt(obj.css("left"));
		var height = obj.height();
		var width = obj.width();
		if (axis == 'y') {
			if (direction == 'up') {
				if (top >= 0) {
					return ;
				}
				obj.css("top", parseInt(obj.css("top")) - 0 + 5);
			} else {
				if (top <= -height + obj.parent().height()) {
					return ;
				}
				obj.css("top", top - 5);
			}
		} else {
			if (direction == 'left') {
				if (left >= 0) {
					return ;
				}
				obj.css("left", parseInt(obj.css("left")) - 0 + 5);
			} else {
				if (left <= -width + obj.parent().width()) {
					return ;
				}
				obj.css("left", left - 5);
			}
		}
	}, 100);
}

function StopScrollLayer(content) {
	clearInterval(ScrollLayer[content]);
}

$.fn.extend({
	mask:function (locked) {
		var wrapper = $("#mask_wrapper");
		var container = $("#mask_container");
		if (wrapper.size() == 0) {
			var css = {
				position : 'absolute',
				top : 0,
				left : 0,
				background : '#000000',
				zIndex : 10000,
				display : 'none',
				textAlign : 'center'
			};
			wrapper = $("<div></div>");
			wrapper.attr("id", "mask_wrapper");
			wrapper.css(css);
			wrapper.bgiframe();
			wrapper.appendTo($(document.body));
			container = $("<div></div>");
			container.attr("id", "mask_container");
			container.height($(document.body).height());
			container.width($(document.body).width());
			css.background = "transparent";
			css.zIndex = 10001;
			container.css(css);
			container.appendTo($(document.body));
		}
		if (locked) {
			$(document.body).css("overflow", "hidden");
			wrapper.attr("locked", true);
		}
		var wh = $(window).height();
		var bh = $(document.body).height();
		wrapper.height(wh > bh ? wh : bh);
		wrapper.width($(document.body).width());
		var t = 150;
		if (bh - $(this).height() < t) {
			t = (bh - $(this).height()) / 2;
		}
		var margin = 200;// $(document.body).scrollTop() - 0 + t;
		$(this).css({margin:margin + "px auto 0px auto"});
		container.children().hide();
		container.children().appendTo($(document.body));
		container.append($(this));

		container.fadeIn("fast");
		wrapper.fadeTo("fast", 0.05);
		$(this).show();
		var $this = $(this);
		$(window).bind("scroll", function () {
			var margin = $(document.body).scrollTop() + 200;
			$this.css({marginTop:margin + "px"});
		})
	},
	unmask: function (fast) {
		$(window).unbind("scroll");
		var wrapper = $("#mask_wrapper");
		if (wrapper.attr("locked")) {
			$(document.body).css("overflow", "auto");
			wrapper.removeAttr("locked");
		}
		var container = $("#mask_container");
		if (wrapper.size() > 0) {
			if (fast) {
				container.hide();
				wrapper.hide();
			} else {
				container.fadeOut("fast");
				wrapper.fadeOut("fast");
			}
		}
		$(this).hide();
		$(this).appendTo($(document.body));
	}
});



function provChange(obj, citySelect, defaultOption) {
	var $this = $(obj);
	var prov = $this.val();
	var $city = $("#" + citySelect);
	
	$city.empty();
	
	var def = "<option value=''>" + defaultOption + "</option>";
	var o = "<option value='999999'>全国</option>";
	var wait = "<option value=''>加载中...</option>";
	
	if (prov == '') {
		$city.append(def);
		$city.trigger("change");
	} else if (prov == '999999') {
		$city.append(o);
		$city.trigger("change");
	} else {
		$city.append(wait);
		$city.trigger("change");
		sendAsyncRequest('data/city', {prov:prov}, function (json) {
			$city.empty();
			$city.append(def);
			$(json.areas).each(function () {
				$city.append("<option value='" + this.areaId + "' geo='" + this.areaGeo + "'>" + this.areaName + "</option>");
			});
			$city.trigger("change");
			$city.trigger("cityLoaded");
		}, function () {
			$city.empty();
			$city.append("<option value=''>加载失败</option>");
			$city.trigger("change");
		});
	}
	
}

function showActionErrors(err) {

	if ($(".action_error").size() > 0) {
		$(".action_error").text(err);
		$(".action_error").show();
	} else {
		alert(err);
	}
}

function showActionMessages(msg) {
	$(".action_message").text(msg);
	$(".action_message").show();
	
	setTimeout(function () {
		$(".action_message").fadeOut();
	}, 2000);
}

function dateCompare(d1, d2) {
	var arr1 = d1.split("-");
    var time1 = new Date(arr1[0], arr1[1], arr1[2]).getTime();

    var arr2 = d2.split("-");
    var time2 = new Date(arr2[0], arr2[1], arr2[2]).getTime();
    if (time1 == time2) {
    	return 0;
    }

    if (time1 > time2) {
        return 1;
    } else {
        return -1;
    }
}


function fixImage(obj, w, h){   
	var i=new Image();    

	i.src=obj.src;     
	if(i.width > 0 && i.height > 0) {         
		if(i.width / i.height >= w / h) {             
			if(i.width > w) {                 
				obj.width = w;                			
				obj.height = (i.height * w)/ i.width;             
			} else {                 
				obj.width = i.width;  
				obj.height = i.height;  
			}         			
		} else {            
			if(i.height > h){   
				obj.height = h;         
				obj.width = (i.width * h) / i.height;    
			} else {        
				obj.width = i.width;    
				obj.height = i.height;  
			}       
		}    

		if (obj.height < h) {
			obj.style.marginTop = (h - obj.height) / 2 + "px";
		}

		if (obj.width < w) {
			obj.style.marginLeft = (w - obj.width) / 2 + "px";
		}
	}
} 


(function( $ ){  var  $scrollTo = $.scrollTo = function( target, duration, settings ){  $(window).scrollTo( target, duration, settings ); }; $scrollTo.defaults = {  axis:'y',  duration:1 }; $scrollTo.window = function( scope ){  return $(window).scrollable(); }; $.fn.scrollable = function(){  return this.map(function(){   var win = this.parentWindow || this.defaultView,    elem = this.nodeName == '#document' ? win.frameElement || win : this,    doc = elem.contentDocument || (elem.contentWindow || elem).document,    isWin = elem.setInterval;   return elem.nodeName == 'IFRAME' || isWin && $.browser.safari ? doc.body    : isWin ? doc.documentElement    : this;  }); }; $.fn.scrollTo = function( target, duration, settings ){  if( typeof duration == 'object' ){   settings = duration;   duration = 0;  }  if( typeof settings == 'function' )   settings = { onAfter:settings };     settings = $.extend( {}, $scrollTo.defaults, settings );  duration = duration || settings.speed || settings.duration;  settings.queue = settings.queue && settings.axis.length > 1;    if( settings.queue )   duration /= 2;  settings.offset = both( settings.offset );  settings.over = both( settings.over );  return this.scrollable().each(function(){   var elem = this,    $elem = $(elem),    targ = target, toff, attr = {},    win = $elem.is('html,body');   switch( typeof targ ){    case 'number':    case 'string':     if( /^([+-]=)?\d+(px)?$/.test(targ) ){      targ = both( targ );      break;     }     targ = $(targ,this);    case 'object':     if( targ.is || targ.style )      toff = (targ = $(targ)).offset();   }   $.each( settings.axis.split(''), function( i, axis ){    var Pos = axis == 'x' ? 'Left' : 'Top',     pos = Pos.toLowerCase(),     key = 'scroll' + Pos,     old = elem[key],     Dim = axis == 'x' ? 'Width' : 'Height',     dim = Dim.toLowerCase();    if( toff ){     attr[key] = toff[pos] + ( win ? 0 : old - $elem.offset()[pos] );     if( settings.margin ){      attr[key] -= parseInt(targ.css('margin'+Pos)) || 0;      attr[key] -= parseInt(targ.css('border'+Pos+'Width')) || 0;     }          attr[key] += settings.offset[pos] || 0;          if( settings.over[pos] )      attr[key] += targ[dim]() * settings.over[pos];    }else     attr[key] = targ[pos];    if( /^\d+$/.test(attr[key]) )     attr[key] = attr[key] <= 0 ? 0 : Math.min( attr[key], max(Dim) );    if( !i && settings.queue ){     if( old != attr[key] )      animate( settings.onAfterFirst );     delete attr[key];    }   });      animate( settings.onAfter );      function animate( callback ){    $elem.animate( attr, duration, settings.easing, callback && function(){     callback.call(this, target, settings);    });   };   function max( Dim ){    var attr ='scroll'+Dim,     doc = elem.ownerDocument;        return win      ? Math.max( doc.documentElement[attr], doc.body[attr]  )      : elem[attr];   };  }).end(); }; function both( val ){  return typeof val == 'object' ? val : { top:val, left:val }; };})( jQuery );