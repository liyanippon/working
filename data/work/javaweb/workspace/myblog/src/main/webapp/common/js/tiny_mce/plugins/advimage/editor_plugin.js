(function() {
	tinymce.create("tinymce.plugins.AdvancedImagePlugin", {
		init : function(a, b) {
			a.addCommand("mceAdvImage", function() {
				if (a.dom.getAttrib(a.selection.getNode(), "class", "").indexOf("mceItem") != -1) {
					return
				}
				a.windowManager.open({
					file : b + "/image.htm",
					width : 500,
					height : 390,
					inline : 1
				}, {
					plugin_url : b
				})
			});
			a.addButton("image", {
				title : "advimage.image_desc",
				cmd : "mceAdvImage"
			})
		},
		getInfo : function() {
			return {
				longname : "Advanced image",
				author : "Moxiecode Systems AB",
				authorurl : "http://tinymce.moxiecode.com",
				infourl : "http://wiki.moxiecode.com/index.php/TinyMCE:Plugins/advimage",
				version : tinymce.majorVersion + "." + tinymce.minorVersion
			}
		}
	});
	tinymce.PluginManager.add("advimage", tinymce.plugins.AdvancedImagePlugin)
})();