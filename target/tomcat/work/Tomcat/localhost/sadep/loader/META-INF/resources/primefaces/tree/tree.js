PrimeFaces.widget.BaseTree=PrimeFaces.widget.BaseWidget.extend({init:function(a){this._super(a);this.cfg.highlight=(this.cfg.highlight===false)?false:true;if(this.cfg.selectionMode){this.initSelection()}this.bindEvents();this.jq.data("widget",this)},initSelection:function(){this.selectionHolder=$(this.jqId+"_selection");var a=this.selectionHolder.val();this.selections=a===""?[]:a.split(",");if(this.isCheckboxSelection()){this.preselectCheckbox()}},expandNode:function(b){var c=this;if(this.cfg.dynamic){if(this.cfg.cache&&c.getNodeChildrenContainer(b).children().length>0){this.showNodeChildren(b);return}if(b.data("processing")){PrimeFaces.debug("Node is already being expanded, ignoring expand event.");return}b.data("processing",true);var a={source:this.id,process:this.id,update:this.id,formId:this.cfg.formId,params:[{name:this.id+"_expandNode",value:c.getRowKey(b)}],onsuccess:function(g,e,f){PrimeFaces.ajax.Response.handle(g,e,f,{widget:c,handle:function(h){var i=this.getNodeChildrenContainer(b);i.append(h);this.showNodeChildren(b);if(this.cfg.draggable){this.makeDraggable(i.find("span.ui-treenode-content"))}if(this.cfg.droppable){this.makeDropPoints(i.find("li.ui-tree-droppoint"));this.makeDropNodes(i.find("span.ui-treenode-droppable"))}}});return true},oncomplete:function(){b.removeData("processing")}};if(this.hasBehavior("expand")){var d=this.cfg.behaviors.expand;d.call(this,a)}else{PrimeFaces.ajax.Request.handle(a)}}else{this.showNodeChildren(b);this.fireExpandEvent(b)}},fireExpandEvent:function(b){if(this.cfg.behaviors){var c=this.cfg.behaviors.expand;if(c){var a={params:[{name:this.id+"_expandNode",value:this.getRowKey(b)}]};c.call(this,a)}}},fireCollapseEvent:function(c){if(this.cfg.behaviors){var a=this.cfg.behaviors.collapse;if(a){var b={params:[{name:this.id+"_collapseNode",value:this.getRowKey(c)}]};a.call(this,b)}}},getNodeChildrenContainer:function(a){throw"Unsupported Operation"},showNodeChildren:function(a){throw"Unsupported Operation"},writeSelections:function(){this.selectionHolder.val(this.selections.join(","))},fireNodeSelectEvent:function(c){if(this.isCheckboxSelection()&&this.cfg.dynamic){var e=this,a={source:this.id,process:this.id};a.params=[{name:this.id+"_instantSelection",value:this.getRowKey(c)}];a.oncomplete=function(k,f,g){if(g.descendantRowKeys&&g.descendantRowKeys!==""){var j=g.descendantRowKeys.split(",");for(var h=0;h<j.length;h++){e.addToSelection(j[h])}e.writeSelections()}};if(this.hasBehavior("select")){var d=this.cfg.behaviors.select;d.call(this,a)}else{PrimeFaces.ajax.AjaxRequest(a)}}else{if(this.hasBehavior("select")){var d=this.cfg.behaviors.select,b={params:[{name:this.id+"_instantSelection",value:this.getRowKey(c)}]};d.call(this,b)}}},fireNodeUnselectEvent:function(c){if(this.cfg.behaviors){var a=this.cfg.behaviors.unselect;if(a){var b={params:[{name:this.id+"_instantUnselection",value:this.getRowKey(c)}]};a.call(this,b)}}},fireContextMenuEvent:function(c){if(this.hasBehavior("contextMenu")){var b=this.cfg.behaviors.contextMenu,a={params:[{name:this.id+"_contextMenuNode",value:this.getRowKey(c)}]};b.call(this,a)}},getRowKey:function(a){return a.attr("data-rowkey")},isNodeSelected:function(a){return $.inArray(this.getRowKey(a),this.selections)!=-1},isSingleSelection:function(){return this.cfg.selectionMode=="single"},isMultipleSelection:function(){return this.cfg.selectionMode=="multiple"},isCheckboxSelection:function(){return this.cfg.selectionMode=="checkbox"},addToSelection:function(a){if(!PrimeFaces.inArray(this.selections,a)){this.selections.push(a)}},removeFromSelection:function(a){this.selections=$.grep(this.selections,function(b){return b!==a})},removeDescendantsFromSelection:function(c){var a=[];for(var b=0;b<this.selections.length;b++){if(this.selections[b].indexOf(c+"_")!==0){a.push(this.selections[b])}}this.selections=a},hasBehavior:function(a){if(this.cfg.behaviors){return this.cfg.behaviors[a]!=undefined}return false},nodeClick:function(f,a){PrimeFaces.clearSelection();if($(f.target).is(":not(.ui-tree-toggler)")){var e=a.parent(),b=a.hasClass("ui-tree-selectable");if(this.cfg.onNodeClick){this.cfg.onNodeClick.call(this,e,f)}if(b&&(this.cfg.selectionMode||this.cfg.draggable)){var d=this.isNodeSelected(e),g=f.metaKey||f.ctrlKey,c=f.shiftKey;if(this.isCheckboxSelection()){this.toggleCheckboxNode(e)}else{if(d&&g){this.unselectNode(e)}else{if(this.isSingleSelection()||(this.isMultipleSelection()&&!g)){this.unselectAllNodes()}this.selectNode(e);this.cursorNode=e}}}}},nodeRightClick:function(e,a){PrimeFaces.clearSelection();if($(e.target).is(":not(.ui-tree-toggler)")){var d=a.parent(),b=a.hasClass("ui-tree-selectable");if(b&&this.cfg.selectionMode){var c=this.isNodeSelected(d);if(!c){if(this.isCheckboxSelection()){this.toggleCheckboxNode(d)}else{this.unselectAllNodes();this.selectNode(d,true)}}this.fireContextMenuEvent(d)}}},bindEvents:function(){throw"Unsupported Operation"},selectNode:function(b,a){throw"Unsupported Operation"},unselectNode:function(b,a){throw"Unsupported Operation"},unselectAllNodes:function(){throw"Unsupported Operation"},preselectCheckbox:function(){throw"Unsupported Operation"},toggleCheckboxNode:function(a){throw"Unsupported Operation"},isEmpty:function(){throw"Unsupported Operation"},toggleCheckboxState:function(b,a){if(a){this.uncheck(b)}else{this.check(b)}},partialCheck:function(c){var a=c.find("> .ui-chkbox-box > .ui-chkbox-icon"),b=c.closest(".ui-treenode"),d=this.getRowKey(b);this.removeFromSelection(d);b.find("> .ui-treenode-content > .ui-treenode-label").removeClass("ui-state-highlight");a.removeClass("ui-icon ui-icon-check").addClass("ui-icon ui-icon-minus");b.removeClass("ui-treenode-selected ui-treenode-unselected").addClass("ui-treenode-hasselected").attr("aria-checked",false).attr("aria-selected",false)},check:function(d){var b=d.children(".ui-chkbox-box"),a=b.children(".ui-chkbox-icon"),c=d.closest(".ui-treenode"),e=this.getRowKey(c);b.removeClass("ui-state-hover");a.removeClass("ui-icon ui-icon-minus").addClass("ui-icon ui-icon-check");this.addToSelection(e);c.removeClass("ui-treenode-hasselected ui-treenode-unselected").addClass("ui-treenode-selected").attr("aria-checked",true).attr("aria-selected",true)},uncheck:function(d){var b=d.children(".ui-chkbox-box"),a=b.children(".ui-chkbox-icon"),c=d.closest(".ui-treenode"),e=this.getRowKey(c);b.removeClass("ui-state-hover");a.removeClass("ui-icon ui-icon-minus ui-icon-check");this.removeFromSelection(e);c.removeClass("ui-treenode-hasselected ui-treenode-selected").addClass("ui-treenode-unselected").attr("aria-checked",false).attr("aria-selected",false)}});PrimeFaces.widget.VerticalTree=PrimeFaces.widget.BaseTree.extend({init:function(a){this._super(a);this.container=this.jq.children(".ui-tree-container");this.cfg.rtl=this.jq.hasClass("ui-tree-rtl");this.cfg.collapsedIcon=this.cfg.rtl?"ui-icon-triangle-1-w":"ui-icon-triangle-1-e";if(this.cfg.draggable){this.initDraggable()}if(this.cfg.droppable){this.initDroppable()}},bindEvents:function(){var e=this,b=".ui-tree-toggler",a=".ui-tree-selectable .ui-treenode-label",c=".ui-treenode-content";this.jq.off("click.tree-toggle",b).on("click.tree-toggle",b,null,function(h){var f=$(this),g=f.closest("li");if(f.hasClass(e.cfg.collapsedIcon)){e.expandNode(g)}else{e.collapseNode(g)}});if(this.cfg.highlight&&this.cfg.selectionMode){this.jq.off("mouseout.tree mouseover.tree",a).on("mouseout.tree",a,null,function(){var f=$(this);f.removeClass("ui-state-hover");if(e.isCheckboxSelection()){f.siblings("div.ui-chkbox").children("div.ui-chkbox-box").removeClass("ui-state-hover")}}).on("mouseover.tree",a,null,function(){var f=$(this);$(this).addClass("ui-state-hover");if(e.isCheckboxSelection()){f.siblings("div.ui-chkbox").children("div.ui-chkbox-box").addClass("ui-state-hover")}})}if(this.isCheckboxSelection()){var d=".ui-chkbox-box";this.jq.off("mouseout.tree-checkbox mouseover.tree-checkbox click.tree-checkbox",d).on("mouseout.tree-checkbox",d,null,function(){$(this).removeClass("ui-state-hover").parent().siblings("span.ui-treenode-label").removeClass("ui-state-hover")}).on("mouseover.tree-checkbox",d,null,function(){$(this).addClass("ui-state-hover").parent().siblings("span.ui-treenode-label").addClass("ui-state-hover")})}this.jq.off("click.tree-content",c).on("click.tree-content",c,null,function(f){e.nodeClick(f,$(this))})},collapseNode:function(g){var b=this;g.attr("aria-expanded",true);var f=g.find("> .ui-treenode-content > .ui-tree-toggler"),d=g.data("nodetype"),c=f.next(),a=this.cfg.iconStates[d],e=g.children(".ui-treenode-children");f.addClass(b.cfg.collapsedIcon).removeClass("ui-icon-triangle-1-s");if(a){c.removeClass(a.expandedIcon).addClass(a.collapsedIcon)}if(this.cfg.animate){e.slideUp("fast",function(){b.postCollapse(g,e)})}else{e.hide();this.postCollapse(g,e)}},postCollapse:function(b,a){if(this.cfg.dynamic&&!this.cfg.cache){a.empty()}this.fireCollapseEvent(b)},getNodeChildrenContainer:function(a){return a.children(".ui-treenode-children")},showNodeChildren:function(e){e.attr("aria-expanded",true);var d=e.find("> .ui-treenode-content > .ui-tree-toggler"),c=e.data("nodetype"),b=d.next(),a=this.cfg.iconStates[c];d.addClass("ui-icon-triangle-1-s").removeClass(this.cfg.collapsedIcon);if(a){b.removeClass(a.collapsedIcon).addClass(a.expandedIcon)}if(this.cfg.animate){e.children(".ui-treenode-children").slideDown("fast")}else{e.children(".ui-treenode-children").show()}},unselectAllNodes:function(){this.selections=[];this.jq.find(".ui-treenode-label.ui-state-highlight").each(function(){$(this).removeClass("ui-state-highlight").closest(".ui-treenode").attr("aria-selected",false)})},selectNode:function(b,a){b.attr("aria-selected",true).find("> .ui-treenode-content > .ui-treenode-label").removeClass("ui-state-hover").addClass("ui-state-highlight");this.addToSelection(this.getRowKey(b));this.writeSelections();if(!a){this.fireNodeSelectEvent(b)}},unselectNode:function(b,a){var c=this.getRowKey(b);b.attr("aria-selected",false).find("> .ui-treenode-content > .ui-treenode-label").removeClass("ui-state-highlight ui-state-hover");this.removeFromSelection(c);this.writeSelections();if(!a){this.fireNodeUnselectEvent(b)}},toggleCheckboxNode:function(b){var d=this,c=b.find("> .ui-treenode-content > .ui-chkbox"),a=c.find("> .ui-chkbox-box > .ui-chkbox-icon").hasClass("ui-icon-check");this.toggleCheckboxState(c,a);if(this.cfg.propagateDown){b.children(".ui-treenode-children").find(".ui-chkbox").each(function(){d.toggleCheckboxState($(this),a)});if(this.cfg.dynamic){this.removeDescendantsFromSelection(b.data("rowkey"))}}if(this.cfg.propagateUp){b.parents("li.ui-treenode-parent").each(function(){var e=$(this),f=e.find("> .ui-treenode-content > .ui-chkbox"),g=e.find("> .ui-treenode-children > .ui-treenode");if(a){if(g.filter(".ui-treenode-unselected").length===g.length){d.uncheck(f)}else{d.partialCheck(f)}}else{if(g.filter(".ui-treenode-selected").length===g.length){d.check(f)}else{d.partialCheck(f)}}})}this.writeSelections();if(a){this.fireNodeUnselectEvent(b)}else{this.fireNodeSelectEvent(b)}},preselectCheckbox:function(){this.jq.find(".ui-chkbox-icon").not(".ui-icon-check").each(function(){var a=$(this),b=a.closest("li");if(b.children(".ui-treenode-children").find(".ui-chkbox-icon.ui-icon-check").length>0){b.addClass("ui-treenode-hasselected");a.addClass("ui-icon ui-icon-minus")}})},check:function(a){this._super(a);a.siblings("span.ui-treenode-label").addClass("ui-state-highlight").removeClass("ui-state-hover")},uncheck:function(a){this._super(a);a.siblings("span.ui-treenode-label").removeClass("ui-state-highlight")},initDraggable:function(){this.makeDraggable(this.jq.find("span.ui-treenode-content"))},initDroppable:function(){this.makeDropPoints(this.jq.find("li.ui-tree-droppoint"));this.makeDropNodes(this.jq.find("span.ui-treenode-droppable"));this.initDropScrollers()},makeDraggable:function(b){var c=this,a=this.cfg.dragdropScope||this.id;b.draggable({helper:function(){var d=$('<div class="ui-tree-draghelper ui-state-highlight"></div>');d.width(c.jq.width());d.height(20);return d},appendTo:document.body,zIndex:++PrimeFaces.zindex,revert:true,scope:a}).data({dragsourceid:this.jqId,dragmode:this.cfg.dragMode})},makeDropPoints:function(b){var c=this,a=this.cfg.dragdropScope||this.id;b.droppable({hoverClass:"ui-state-hover",accept:"span.ui-treenode-content",tolerance:"pointer",scope:a,drop:function(e,p){var f=$(p.draggable.data("dragsourceid")).data("widget"),n=p.draggable.data("dragmode"),m=c,r=$(this),q=r.closest("li.ui-treenode-parent"),s=c.getRowKey(q),i=p.draggable.closest("li.ui-treenode"),g=c.findTargetDragNode(i,n),k=c.getRowKey(g),l=i.children("span.ui-treenode-content"),o=l.children("span.ui-treenode-label"),d=g.next("li.ui-tree-droppoint"),t=g.parent().closest("li.ui-treenode-parent"),h=(f.id!==m.id);p.helper.remove();r.removeClass("ui-state-hover");var j=c.validateDropPoint(i,r);if(!j){return}g.hide().insertAfter(r);if(h){if(f.isCheckboxSelection()){f.unselectSubtree(g)}o.removeClass("ui-state-highlight");d.remove();c.updateDragDropBindings(g)}else{d.insertAfter(g)}if(t.length&&(t.find("> ul.ui-treenode-children > li.ui-treenode").length===0)){c.makeLeaf(t)}g.fadeIn();if(c.isCheckboxSelection()){c.syncDNDCheckboxes(f,t,q)}c.syncDragDrop();if(h){f.syncDragDrop()}c.fireDragDropEvent({dragNodeKey:k,dropNodeKey:s,dragSource:f.id,dndIndex:r.prevAll("li.ui-treenode").length,transfer:h})}})},makeDropNodes:function(b){var c=this,a=this.cfg.dragdropScope||this.id;b.droppable({accept:".ui-treenode-content",tolerance:"pointer",scope:a,over:function(d,e){$(this).children(".ui-treenode-label").addClass("ui-state-hover")},out:function(d,e){$(this).children(".ui-treenode-label").removeClass("ui-state-hover")},drop:function(p,l){var m=$(l.draggable.data("dragsourceid")).data("widget"),o=l.draggable.data("dragmode"),k=c,u=$(this),f=u.closest("li.ui-treenode"),h=c.getRowKey(f),s=l.draggable.closest("li.ui-treenode"),i=c.findTargetDragNode(s,o),n=c.getRowKey(i),j=s.children("span.ui-treenode-content"),e=j.children("span.ui-treenode-label"),d=i.next("li.ui-tree-droppoint"),q=i.parent().closest("li.ui-treenode-parent"),t=f.children(".ui-treenode-children"),g=(m.id!==k.id);l.helper.remove();u.children(".ui-treenode-label").removeClass("ui-state-hover");var r=c.validateDropNode(s,f,q);if(!r){return}if(t.children("li.ui-treenode").length===0){c.makeParent(f)}i.hide();t.append(i);if(q.length&&(q.find("> ul.ui-treenode-children > li.ui-treenode").length===0)){c.makeLeaf(q)}if(g){if(m.isCheckboxSelection()){m.unselectSubtree(i)}e.removeClass("ui-state-highlight");d.remove();c.updateDragDropBindings(i)}else{t.append(d)}i.fadeIn();if(c.isCheckboxSelection()){c.syncDNDCheckboxes(m,q,f)}c.syncDragDrop();if(g){m.syncDragDrop()}c.fireDragDropEvent({dragNodeKey:n,dropNodeKey:h,dragSource:m.id,dndIndex:i.prevAll("li.ui-treenode").length,transfer:g})}})},initDropScrollers:function(){var b=this,a=this.cfg.dragdropScope||this.id;this.jq.prepend('<div class="ui-tree-scroller ui-tree-scrollertop"></div>').append('<div class="ui-tree-scroller ui-tree-scrollerbottom"></div>');this.jq.children("div.ui-tree-scroller").droppable({accept:".ui-treenode-content",tolerance:"pointer",scope:a,over:function(){var c=$(this).hasClass("ui-tree-scrollertop")?-10:10;b.scrollInterval=setInterval(function(){b.scroll(c)},100)},out:function(){clearInterval(b.scrollInterval)}})},scroll:function(a){this.container.scrollTop(this.container.scrollTop()+a)},updateDragDropBindings:function(c){c.after('<li class="ui-tree-droppoint ui-droppable"></li>');this.makeDropPoints(c.next("li.ui-tree-droppoint"));var b=c.find("li.ui-tree-droppoint");b.droppable("destroy");this.makeDropPoints(b);var a=c.find("span.ui-treenode-content");a.droppable("destroy");this.makeDropNodes(a);a.draggable("destroy");if(this.cfg.draggable){this.makeDraggable(a)}},findTargetDragNode:function(b,c){var a=null;if(c==="self"){a=b}else{if(c==="parent"){a=b.parent().closest("li.ui-treenode")}else{if(c==="ancestor"){a=b.parent().parents("li.ui-treenode:last")}}}if(a.length===0){a=b}return a},findNodes:function(c){var a=[];for(var b=0;b<c.length;b++){a.push($(this.jqId+"\\:"+c[b]))}return a},updateRowKeys:function(){var a=this.jq.find("> ul.ui-tree-container > li.ui-treenode");this.updateChildrenRowKeys(a,null)},updateChildrenRowKeys:function(b,a){var c=this;b.each(function(f){var e=$(this),g=e.attr("data-rowkey"),d=(a===null)?f.toString():a+"_"+f;e.attr({id:c.id+":"+d,"data-rowkey":d});if(e.hasClass("ui-treenode-parent")){c.updateChildrenRowKeys(e.find("> ul.ui-treenode-children > li.ui-treenode"),d)}})},validateDropPoint:function(a,b){if(a.next().get(0)===b.get(0)||a.prev().get(0)===b.get(0)){return false}if(a.has(b.get(0)).length){return false}if(this.cfg.dropRestrict){if(this.cfg.dropRestrict==="sibling"&&a.parent().get(0)!==b.parent().get(0)){return false}}return true},validateDropNode:function(c,b,a){if(a.get(0)===b.get(0)){return false}if(c.has(b.get(0)).length){return false}if(this.cfg.dropRestrict){if(this.cfg.dropRestrict==="sibling"){return false}}return true},makeLeaf:function(a){a.removeClass("ui-treenode-parent").addClass("ui-treenode-leaf");a.find("> .ui-treenode-content > .ui-tree-toggler").addClass("ui-treenode-leaf-icon").removeClass("ui-tree-toggler ui-icon ui-icon-triangle-1-s");a.children(".ui-treenode-children").hide().children().remove()},makeParent:function(a){a.removeClass("ui-treenode-leaf").addClass("ui-treenode-parent");a.find("> span.ui-treenode-content > span.ui-treenode-leaf-icon").removeClass("ui-treenode-leaf-icon").addClass("ui-tree-toggler ui-icon ui-icon-triangle-1-e");a.children(".ui-treenode-children").append('<li class="ui-tree-droppoint ui-droppable"></li>');this.makeDropPoints(a.find("> ul.ui-treenode-children > li.ui-tree-droppoint"))},syncDragDrop:function(){var a=this;if(this.cfg.selectionMode){var b=this.findNodes(this.selections);this.updateRowKeys();this.selections=[];$.each(b,function(c,d){a.selections.push(d.attr("data-rowkey"))});this.writeSelections()}else{this.updateRowKeys()}},syncDNDCheckboxes:function(a,b,c){if(b.length){a.propagateDNDCheckbox(b)}if(c.length){this.propagateDNDCheckbox(c)}},unselectSubtree:function(a){var c=this,b=a.find("> .ui-treenode-content > .ui-chkbox");this.toggleCheckboxState(b,true);a.children(".ui-treenode-children").find(".ui-chkbox").each(function(){c.toggleCheckboxState($(this),true)})},propagateDNDCheckbox:function(c){var d=c.find("> .ui-treenode-content > .ui-chkbox"),a=c.find("> .ui-treenode-children > .ui-treenode");if(a.length){if(a.filter(".ui-treenode-unselected").length===a.length){this.uncheck(d)}else{if(a.filter(".ui-treenode-selected").length===a.length){this.check(d)}else{this.partialCheck(d)}}}var b=c.parent().closest(".ui-treenode-parent");if(b.length){this.propagateDNDCheckbox(b)}},fireDragDropEvent:function(c){var d=this,b={source:this.id,process:c.transfer?this.id+" "+c.dragSource:this.id};b.params=[{name:this.id+"_dragdrop",value:true},{name:this.id+"_dragNode",value:c.dragNodeKey},{name:this.id+"_dragSource",value:c.dragSource},{name:this.id+"_dropNode",value:c.dropNodeKey},{name:this.id+"_dndIndex",value:c.dndIndex}];if(this.hasBehavior("dragdrop")){var a=this.cfg.behaviors.dragdrop;a.call(this,b)}else{PrimeFaces.ajax.AjaxRequest(b)}},isEmpty:function(){return(this.container.children().length===0)}});PrimeFaces.widget.HorizontalTree=PrimeFaces.widget.BaseTree.extend({init:function(a){this._super(a);if(PrimeFaces.isIE()){this.drawConnectors()}},bindEvents:function(){var c=this,d=this.cfg.selectionMode,a=".ui-tree-toggler",b=".ui-treenode-content.ui-tree-selectable";this.jq.off("click.tree-toggle",a).on("click.tree-toggle",a,null,function(){var e=$(this),f=e.closest("td.ui-treenode");if(f.hasClass("ui-treenode-collapsed")){c.expandNode(f)}else{c.collapseNode(f)}});if(d&&this.cfg.highlight){this.jq.off("mouseout.tree mouseover.tree",b).on("mouseover.tree",b,null,function(){var e=$(this);if(!e.hasClass("ui-state-highlight")){e.addClass("ui-state-hover");if(c.isCheckboxSelection()){e.children("div.ui-chkbox").children("div.ui-chkbox-box").addClass("ui-state-hover")}}}).on("mouseout.tree",b,null,function(){var e=$(this);if(!e.hasClass("ui-state-highlight")){e.removeClass("ui-state-hover");if(c.isCheckboxSelection()){e.children("div.ui-chkbox").children("div.ui-chkbox-box").removeClass("ui-state-hover")}}})}this.jq.off("click.tree-content",b).on("click.tree-content",b,null,function(f){c.nodeClick(f,$(this))})},showNodeChildren:function(e){e.attr("aria-expanded",true);var c=e.next(),d=e.find("> .ui-treenode-content > .ui-tree-toggler"),b=e.data("nodetype"),a=this.cfg.iconStates[b];if(a){d.next().removeClass(a.collapsedIcon).addClass(a.expandedIcon)}d.addClass("ui-icon-minus").removeClass("ui-icon-plus");e.removeClass("ui-treenode-collapsed");c.show();if($.browser.msie){this.drawConnectors()}},collapseNode:function(e){var c=e.next(),d=e.find("> .ui-treenode-content > .ui-tree-toggler"),b=e.data("nodetype"),a=this.cfg.iconStates[b];if(a){d.next().addClass(a.collapsedIcon).removeClass(a.expandedIcon)}d.removeClass("ui-icon-minus").addClass("ui-icon-plus");e.addClass("ui-treenode-collapsed");c.hide();if(this.cfg.dynamic&&!this.cfg.cache){c.children(".ui-treenode-children").empty()}this.fireCollapseEvent(e);if($.browser.msie){this.drawConnectors()}},getNodeChildrenContainer:function(a){return a.next(".ui-treenode-children-container").children(".ui-treenode-children")},selectNode:function(b,a){b.removeClass("ui-treenode-unselected").addClass("ui-treenode-selected").children(".ui-treenode-content").removeClass("ui-state-hover").addClass("ui-state-highlight");this.addToSelection(this.getRowKey(b));this.writeSelections();if(!a){this.fireNodeSelectEvent(b)}},unselectNode:function(b,a){var c=this.getRowKey(b);b.removeClass("ui-treenode-selected").addClass("ui-treenode-unselected").children(".ui-treenode-content").removeClass("ui-state-highlight");this.removeFromSelection(c);this.writeSelections();if(!a){this.fireNodeUnselectEvent(b)}},unselectAllNodes:function(){this.selections=[];this.jq.find(".ui-treenode-content.ui-state-highlight").each(function(){$(this).removeClass("ui-state-highlight").closest(".ui-treenode").attr("aria-selected",false)})},preselectCheckbox:function(){var a=this;this.jq.find(".ui-chkbox-icon").not(".ui-icon-check").each(function(){var c=$(this),d=c.closest(".ui-treenode"),b=a.getNodeChildrenContainer(d);if(b.find(".ui-chkbox-icon.ui-icon-check").length>0){c.addClass("ui-icon ui-icon-minus")}})},toggleCheckboxNode:function(b){var d=this,c=b.find("> .ui-treenode-content > .ui-chkbox"),a=c.find("> .ui-chkbox-box > .ui-chkbox-icon").hasClass("ui-icon-check");this.toggleCheckboxState(c,a);if(this.cfg.propagateDown){b.next(".ui-treenode-children-container").find(".ui-chkbox").each(function(){d.toggleCheckboxState($(this),a)});if(this.cfg.dynamic){this.removeDescendantsFromSelection(b.data("rowkey"))}}if(this.cfg.propagateUp){b.parents("td.ui-treenode-children-container").each(function(){var f=$(this),e=f.prev(".ui-treenode-parent"),g=e.find("> .ui-treenode-content > .ui-chkbox"),h=f.find("> .ui-treenode-children > table > tbody > tr > td.ui-treenode");if(a){if(h.filter(".ui-treenode-unselected").length===h.length){d.uncheck(g)}else{d.partialCheck(g)}}else{if(h.filter(".ui-treenode-selected").length===h.length){d.check(g)}else{d.partialCheck(g)}}})}this.writeSelections();if(a){this.fireNodeUnselectEvent(b)}else{this.fireNodeSelectEvent(b)}},check:function(a){this._super(a);a.parent(".ui-treenode-content").addClass("ui-state-highlight").removeClass("ui-state-hover")},uncheck:function(a){this._super(a);a.parent(".ui-treenode-content").removeClass("ui-state-highlight")},drawConnectors:function(){this.jq.find("table.ui-treenode-connector-table").each(function(){var a=$(this);a.height(0).height(a.parent().height())})},isEmpty:function(){return this.jq.children("table").length===0}});