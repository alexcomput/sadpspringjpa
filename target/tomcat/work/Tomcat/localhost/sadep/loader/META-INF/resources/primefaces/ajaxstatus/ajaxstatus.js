PrimeFaces.widget.AjaxStatus=PrimeFaces.widget.BaseWidget.extend({init:function(a){this._super(a);this.bind()},bind:function(){var b=$(document),a=this;b.on("pfAjaxStart",function(){a.trigger("start")}).on("pfAjaxError",function(){a.trigger("error")}).on("pfAjaxSuccess",function(){a.trigger("success")}).on("pfAjaxComplete",function(){a.trigger("complete")});this.bindToStandard()},trigger:function(a){var b=this.cfg[a];if(b){b.call(document)}this.jq.children().hide().filter(this.jqId+"_"+a).show()},bindToStandard:function(){if(window.jsf&&window.jsf.ajax){var a=$(document);jsf.ajax.addOnEvent(function(b){if(b.status==="begin"){a.trigger("pfAjaxStart")}else{if(b.status==="complete"){a.trigger("pfAjaxSuccess")}else{if(b.status==="success"){a.trigger("pfAjaxComplete")}}}});jsf.ajax.addOnError(function(b){a.trigger("pfAjaxError")})}}});