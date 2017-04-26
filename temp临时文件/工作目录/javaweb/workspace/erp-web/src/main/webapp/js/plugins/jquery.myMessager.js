/**
 * 消息弹出插件
 */
(function($){
	jQuery.myMessager = {
		info : function(message) {
			$.messager.alert('info', message, 'info');
		},
		warn : function(message) {
			$.messager.alert('warning', message, 'warning');
		},
		error : function(message) {
			$.messager.alert('error', message, 'error');
		},
		question : function(message) {
			$.messager.alert('question', message, 'question');
		},
		confirm : function(title, message, callBack) {
			$.messager.confirm(title, message, callBack);
		},
		show : function(msg, options) {
			if(options) {
				$.messager.show({
					width: options.width,
					height: options.height,
					title: "",
					msg: msg,
					style: {
						left: '',
						right: 0,
						zIndex: 1000,
						top: '',
						bottom: -document.body.scrollTop - document.documentElement.scrollTop
					},
					timeout: 3000
				});
			} else {
				$.messager.show({
					title: "",
					msg: msg,
					style: {
						left: '',
						right: 0,
						zIndex: 1000,
						top: '',
						bottom: -document.body.scrollTop - document.documentElement.scrollTop
					},
					timeout: 3000
				});
			}

		},
		topLeft : function(options) {
			$.messager.show({
				showType: 'show',
				showSpeed: options.showSpeed,
				width: options.width,
				height: options.height,
				title: options.title,
				msg: options.msg,
				style: {
					right: '',
					left: 0,
					zIndex: 1000,
					top: document.body.scrollTop + document.documentElement.scrollTop,
					bottom: ''
				},
				timeout: options.timeout
			});

		},
		topRight : function(options) {
			$.messager.show({
				showType: 'show',
				showSpeed: options.showSpeed,
				width: options.width,
				height: options.height,
				title: options.title,
				msg: options.msg,
				style: {
					left: '',
					right: 0,
					zIndex: 1000,
					top: document.body.scrollTop + document.documentElement.scrollTop,
					bottom: ''
				},
				timeout: options.timeout
			});

		},
		bottomLeft : function(options) {
			$.messager.show({
				showType: 'show',
				showSpeed: options.showSpeed,
				width: options.width,
				height: options.height,
				title: options.title,
				msg: options.msg,
				style: {
					right: '',
					left: 0,
					zIndex: 1000,
					top: '',
					bottom: -document.body.scrollTop - document.documentElement.scrollTop
				},
				timeout: options.timeout
			});

		},
		bottomRight : function(options) {
			$.messager.show({
				showType: 'show',
				showSpeed: options.showSpeed,
				width: options.width,
				height: options.height,
				title: options.title,
				msg: options.msg,
				style: {
					left: '',
					right: 0,
					zIndex: 1000,
					top: '',
					bottom: -document.body.scrollTop - document.documentElement.scrollTop
				},
				timeout: options.timeout
			});

		}
    	
	};
	
})(jQuery);


