PrimeFaces.widget.Slider=PrimeFaces.widget.BaseWidget.extend({init:function(b){this._super(b);this.cfg.displayTemplate=this.cfg.displayTemplate||(this.cfg.range?"{min} - {max}":"{value}");if(this.cfg.range){var a=this.cfg.input.split(",");this.input=$(PrimeFaces.escapeClientId(a[0])+","+PrimeFaces.escapeClientId(a[1]))}else{this.input=$(PrimeFaces.escapeClientId(this.cfg.input))}if(this.cfg.display){this.output=$(PrimeFaces.escapeClientId(this.cfg.display))}this.jq.slider(this.cfg);this.bindEvents()},bindEvents:function(){var a=this;this.jq.bind("slide",function(b,c){a.onSlide(b,c)});if(this.cfg.onSlideStart){this.jq.bind("slidestart",function(b,c){a.cfg.onSlideStart.call(this,b,c)})}this.jq.bind("slidestop",function(b,c){a.onSlideEnd(b,c)});this.input.keypress(function(c){var b=(c.which)?c.which:c.keyCode;if(b>31&&(b<48||b>57)){return false}else{return true}});this.input.keyup(function(){a.setValue(a.input.val())})},onSlide:function(a,b){if(this.cfg.onSlide){this.cfg.onSlide.call(this,a,b)}if(this.cfg.range){this.input.eq(0).val(b.values[0]);this.input.eq(1).val(b.values[1]);if(this.output){this.output.html(this.cfg.displayTemplate.replace("{min}",b.values[0]).replace("{max}",b.values[1]))}}else{this.input.val(b.value);if(this.output){this.output.html(this.cfg.displayTemplate.replace("{value}",b.value))}}},onSlideEnd:function(c,d){if(this.cfg.onSlideEnd){this.cfg.onSlideEnd.call(this,c,d)}if(this.cfg.behaviors){var a=this.cfg.behaviors.slideEnd;if(a){var b={params:[{name:this.id+"_slideValue",value:d.value}]};a.call(this,b)}}},getValue:function(){return this.jq.slider("value")},setValue:function(a){this.jq.slider("value",a)},getValues:function(){return this.jq.slider("values")},setValues:function(a){this.jq.slider("values",a)},enable:function(){this.jq.slider("enable")},disable:function(){this.jq.slider("disable")}});