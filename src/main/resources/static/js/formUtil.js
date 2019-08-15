/** **************JSON序列化表单*********************** */
;(function($, window, document, undefined) {

	
	
	/**
	 * 返回结果{a:x,b:x,c:x,d:{1,2,3}}
	 * 
	 * @type {{JsonSerialize: Function}}
	 */
	var serializeObject = function(ele) {
		this.element = ele;
		
	};
	serializeObject.prototype = {
		JsonSerialize : function() {
			var o = {};
			var a = this.element.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}

			});
			return o;
		}

	};
	$.fn.formSerialize = function() {
		var serializeObj = new serializeObject(this);
		return o = serializeObj.JsonSerialize();
	};
	
	/** *************将json数据快速绑定form表单或label中********************* */
	var JsonForm=function(ele){
		this.element=ele;
	};
	JsonForm.prototype={
		jsonFormByInp : function(json,type){
			this.element.each(function () {
		        var input, name;
		        if (json == null) { this.reset(); return; }
		        for (var i = 0; i < this.length; i++) {
		            input = this.elements[i];
		           
		            name = (input.type == "checkbox") ? input.name.replace(/(.+)\[\]$/, "$1") : input.name;
		            if (type != "") {
		                name = this.id.substring(0, (this.id).indexOf(type));
		            }
		            if (json[name] == undefined) continue;
		            if(input.nodeName=="SELECT"){
						$.each(input,function(i,d){
							if (String(json[name]).indexOf(d.value) > -1) {
								d.selected = true;
							}
						})
						//input.selected=true;
					}
		            switch (input.type) {
		            case "checkbox":
	                    if (json[name] === "") {
	                        input.checked = false;
	                    } else {
	                        if (json[name].indexOf(input.value) > -1) {
	                            input.checked = true;
	                        } else {
	                            input.checked = false;
	                        }
	                    }
	                    break;
	                case "radio":
	                    if (json[name] === "") {
	                        input.checked = false;
	                    } else if (input.value===String(json[name])) {
	                        input.checked = true;
	                    }
	                    break;

		                case "button": break;
		                default: input.value = json[name];
		            }
		            form.render();
		        }
		    });			
		}	,
		jsonFormBySpan : function(json,type){
		this.element.each(function (i,d) {
	        var span, name;
	           name=$(d).attr("name");
	  
	           if(null!=json[name]){
	        	   $(d).text( json[name])
	           }
	           
	    });			
	}	
	};
	$.fn.jsonFormByInp=function(json,type){
		var jsonF=new JsonForm(this);
		jsonF.jsonFormByInp(json,type);
		
	};
	
	$.fn.jsonFormBySpan=function(json,type){
		var jsonF=new JsonForm(this);
		jsonF.jsonFormBySpan(json,type);
		
	};
	
	

})(jQuery, window, document);