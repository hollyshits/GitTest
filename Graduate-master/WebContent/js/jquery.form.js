;(function($){
	var defaults = {
		form:{name:'inForm'},
		text:{name:'inText',ratio:false,hover:'hover',focus:'focus',disabled:'disabled',readonly:'readonly'},
		radio:{name:'inRadio',hover:'hover',focus:'focus',checked:'checked',disabled:'disabled'},
		checkbox:{name:'inCheckbox',hover:'hover',focus:'focus',checked:'checked',disabled:'disabled'},
		select:{name:'inSelect',ratio:false,focus:'focus',size:10,open:'open',dropbox:'dropbox',listbox:'listbox',selected:'selected',disabled:'disabled',hover:'hover',multiple:'multiple',searchbox:'searchbox'},
		button:{name:'inButton',ratio:false,hover:'hover',focus:'focus',disabled:'disabled'},
		textarea:{name:'inTextarea',ratio:false,hover:'hover',focus:'focus',disabled:'disabled',readonly:'readonly'}
	};
	var supportPlaceholder = 'placeholder' in document.createElement('input');
	var GetBox = function(element, params){
		if(element.prop('id')) {
			var id = element.attr('id');
		} else {
			var id = 0;
			while($('#'+params.name+id).length>0){
				id++;
			}
			var id = params.name + id;
		}
		var form = $(element.get(0).form);
		var disabled = element.is(':disabled') ? ' ' + params.disabled : '';
		var wrap = $('<div class="inElement '+params.name + '"></div>').css({'position':'relative','z-index':'1'});
		var label = element.next();
		if(!label.is('label') || (label.is('label') && label.find('input'))) {
			label = element.prev();
			if(!label.is('label') || (label.is('label') && label.find('input'))) {
				label = element.parent();
				if(!label.is('label')) {
					if(id) {
						label = form.find('label[for="'+id+'"]');
						if(label.length<1){
							label = element.wrap('<label></label>').parent('label');
						}
					} else {
						label = element.wrap('<label></label>').parent('label');
					}
				}
			}
		}
		element.attr('id',id);
		if(params.ratio) {
			var ratio = Math.ceil(element.width()/label.parent().width()*100);
			wrap.css('width', (ratio>99?100:ratio) +'%');
			element.css('width','100%');
		}
		if(!label.prop('for')){ label.attr('for', id); }
		return {lab:label.css('position','relative').wrap(wrap),div:label.parent()};
	};
	var GetWrap = function(element){
		element = $(element);
		var wrap = element.next();
		if(!wrap.is('label') || (wrap.is('label') && wrap.find('input'))) {
			wrap = element.prev();
			if(!wrap.is('label') || (wrap.is('label') && wrap.find('input'))) {
				wrap = element.parent();
				if(!wrap.is('label')) {
					if(id) {
						wrap = form.find('label[for="'+id+'"]');
						if(wrap.length<1){
							wrap = element;
						}
					} else {
						wrap = element;
					}
				}
			}
		}
		return wrap[0];
	}
	$.fn.inText = $.fn.inTextarea = function(params){
		return this.each(function(){
			if($(this).hasClass('hide')) {
				return false;
			} else {
				$(this).data('ele', GetWrap(this).outerHTML);
				var ele = this, obj = $(ele).addClass('hide');
				var opt = $.extend({},params,obj.data());
				var lib = GetBox(obj, opt);
				lib.div.data('opt', params);
				if(!supportPlaceholder && obj.prop('placeholder')) {
					var tip = $('<div></div>');
					tip.text(obj.attr('placeholder'));
					tip.css({
						'position':'absolute',
						'top':lib.lab.get(0).clientTop+ele.clientTop,
						'left':lib.lab.get(0).clientLeft+ele.clientLeft,
						'color':'#AAAAAA','font-size':obj.css('font-size'),
						'line-height':obj.css('line-height'),
						'padding':obj.css('padding'),
						'margin':obj.css('margin')
					});
					if(ele.value !== '') { tip.hide(); }
					obj.on('input propertychange',function() {
						if (ele.value !== '') {
							tip.hide();
						} else {
							tip.show();
						}
					});
					obj.after(tip);
				}
				$(obj,lib.div,lib.lab)
				.focus(function(){lib.lab.addClass(opt.focus);lib.div.css('z-index',2);})
				.blur(function(){lib.lab.removeClass(opt.focus);lib.div.css('z-index',1);})
				.mouseover(function(){lib.lab.addClass(opt.hover);})
				.mouseout(function(){lib.lab.removeClass(opt.hover);});
				if(ele.disabled){ lib.lab.addClass(opt.disabled); }
				if(ele.readOnly){ lib.lab.addClass(opt.readonly); }
			}
		});
	};
	$.fn.inRadio = $.fn.inCheckbox = function(params){
		return this.each(function(){
			if($(this).hasClass('hide')) {
				return false;
			} else {
				$(this).data('ele', GetWrap(this).outerHTML);
				var ele = this, obj = $(ele).addClass('hide').css({'position':'absolute','left':'-999%','opacity':'0'});
				var opt = $.extend({},params,obj.data());
				var lib = GetBox(obj, opt);
				lib.div.data('opt', params);
				obj.change(function(){
					if(ele.checked) {
						lib.div.addClass(opt.checked);
						lib.lab.addClass(opt.checked);
					} else {
						lib.div.removeClass(opt.checked);
						lib.lab.removeClass(opt.checked);
					}
				});
				obj.click(function(){
					if(obj.is(':disabled')) { return false; }
					obj.trigger('change');
					if(obj.is('input:radio')){
						$('input[name="' + ele.name + '"]',ele.form).not(obj).each(function(){
							if($(this).is('input:radio')){
								$(this).trigger('change');
							}
						});
					}
				});
				lib.lab.hover(
					function(){
						lib.div.addClass(opt.hover);
						$(this).addClass(opt.hover);
					},
					function(){
						lib.div.removeClass(opt.hover);
						$(this).removeClass(opt.hover);
					}
				);
				obj.focus(function(){
					lib.lab.addClass(opt.focus);
					lib.div.addClass(opt.focus);
					lib.div.css('z-index',2);
				});
				obj.blur(function(){
					lib.lab.removeClass(opt.focus);
					lib.div.removeClass(opt.focus);
					lib.div.css('z-index',1);
				});
				if(ele.checked){ lib.lab.addClass(opt.checked);}
				if(ele.disabled){ lib.lab.addClass(opt.disabled);}
			}
		});
	};
	$.fn.inSelect = function(params){
		return this.each(function(){
			if($(this).hasClass('hide')) {
				return false;
			} else {
				$(this).data('ele', GetWrap(this).outerHTML);
				var ele = this, obj = $(ele).addClass('hide');
				var opt = $.extend({},params,obj.data());
				var lib = GetBox(obj, opt);
				var select_seds = obj.find('option:selected');
				var select_opts = obj.find('option');
				var select_data = [], select_vals = [];
				var select_list = $('<div></div>').addClass(opt.listbox+(ele.multiple?' '+opt.multiple:'')).css('overflow-y','auto');
				var select_multiple = '# of % selected';
				lib.div.data('opt', params);
				obj.css({'position':'absolute','left':'-999%','opacity':'0','z-index':'-1'});
				opt.size = obj.prop('size') ? obj.prop('size') : opt.size;
				select_data.push("<dl>");
				$('*', obj).each(function() {
					switch(this.tagName.toLowerCase()){
						case 'option':
							if($(this).parent('optgroup').length==0){
								select_data.push('<dd class="option'+(this.disabled?' '+opt.disabled:'')+(this.selected?' '+opt.selected:'')+'"><a href="javascript:void(0)" tabIndex="-1" style="outline:0;"><span'+(this.selected?' class="'+opt.selected+'"':'')+'>'+this.text+'</span></a></dd>');
							}
							break;
						case 'optgroup':
							var isdisabled = this.disabled;
							select_data.push('<dd class="optgroup'+(isdisabled?' '+opt.disabled:'')+'"><dl><dt><a href="javascript:void(0)" tabIndex="-1" style="outline:0;"><span'+(this.selected?' class="'+opt.selected+'"':'')+'>'+this.label+'</span></a></dt>');
							$(this).find('option').each(function() {
								select_data.push('<dd class="option'+(this.disabled || isdisabled?' '+opt.disabled:'')+(this.selected?' '+opt.selected:'')+'"><a href="javascript:void(0)" tabIndex="-1" style="outline:0;"><span'+(this.selected?' class="'+opt.selected+'"':'')+'>'+this.text+'</span></a></dd>');
							});
							select_data.push('</dl></dd>');
							break;
					}
				});
				select_data.push("</dl>");
				select_list.html(select_data.join(''));
				if(select_seds.length !== select_opts.length){
					select_mtp = select_multiple.replace('#', select_seds.length).replace('%', select_opts.length);
				} else {
					select_mtp = 'All selected';
				}
				if(ele.multiple){
					select_seds.each(function(){
						select_vals.push((select_vals==''?'':', ')+$(this).text());
					});
				} else {
					select_vals.push(select_seds.text());
				}
				var select_tit = $('<span>'+(select_seds.length<5?select_vals.join(''):multiplestr)+'</span><em></em>');
				var select_box = $('<div></div>').addClass(opt.dropbox).append(select_list);
				lib.lab.append(select_tit);
				lib.div.append(select_box);
				var option_height = select_list.css({'height':(obj.find('*').length>opt.size?select_list.find('dd.option').outerHeight()*opt.size:'')}).find('dd.option').outerHeight();
				var option_list = select_list.find('dd.option');
				var optgroup_list = select_list.find('dd.optgroup');
				select_box.hide();
				select_box.css({'position':'absolute','width':'100%','top':lib.lab.outerHeight()});
				optgroup_list.each(function(){
					$(this).mousedown(function(){
						return false;
					});
				});
				option_list.each(function(index){
					$(this).mousedown(function(e){
						if(ele.options[index].disabled || $(this).hasClass(opt.disabled)){ return false; }
						if(ele.multiple){
							if(ele.options[index].selected) {
								ele.options[index].selected = false;
								$(this).addClass(opt.selected).find('span').addClass(opt.selected);
							} else {
								ele.options[index].selected = true;
								$(this).removeClass(opt.selected).find('span').removeClass(opt.selected);
							}
						} else {
							$(option_list).removeClass(opt.selected).find('span').removeClass(opt.selected);
							$(option_list).eq(index).addClass(opt.selected).find('span').addClass(opt.selected);
							ele.selectedIndex = index;
							lib.lab.find('em').removeClass(opt.open);
							select_box.slideUp();
						}
						obj.change().focus();
						return false;
					});
				});
				obj.bind('keydown', function(e){
					if(!ele.disabled){
						if(e.altKey && e.keyCode==40) {
							lib.lab.find('em').addClass(opt.open);
							select_box.slideDown();
							return false;
						}
						if(e.altKey&&e.keyCode==38) {
							lib.lab.find('em').removeClass(opt.open);
							select_box.slideUp();
							return false;
						}
						if(e.keyCode==13 || e.keyCode==32) {
							if(select_box.is(':hidden')) {
								lib.lab.find('em').addClass(opt.open);
								select_box.slideDown();
							} else {
								lib.lab.find('em').removeClass(opt.open);
								select_box.slideUp();
							}
							return false;
						}
						if(e.keyCode==27) {
							lib.lab.find('em').removeClass(opt.open);
							select_box.slideUp();
							obj.focus();
							return false;
						}
					}
				});
				lib.lab.mousedown(function(){
					if(!ele.disabled){
						if(select_box.is(':hidden')) {
							lib.lab.find('em').addClass(opt.open);
							select_box.slideDown();
						} else {
							lib.lab.find('em').removeClass(opt.open);
							select_box.slideUp();
						}
					}
					return false;
				});
				obj.focus(function(){
					lib.div.css('z-index',3);
				});
				obj.blur(function(e){
					if(!ele.disabled){
						if(lib.div.find($(':focus')).length>0 || lib.div[0]==$(':focus')[0]) { } else {
							lib.div.css('z-index',2);
							lib.lab.find('em').removeClass(opt.open);
							select_box.slideUp(function(){lib.div.css('z-index',1)});
						}
					}
				});
				obj.change(function(){
					if(!ele.disabled){
						var str = [],index;
						$(option_list).removeClass(opt.selected).find('span').removeClass(opt.selected);
						obj.find('option:selected').each(function(){
							index = $(select_opts).index(this);
							$(option_list).eq(index).addClass(opt.selected).find('span').addClass(opt.selected);
							if(ele.multiple){
								str.push((str==''?'':', ')+$(this).text());
								if(str.length !== select_opts.length){
									select_mtp = select_multiple.replace('#', str.length).replace('%', select_opts.length);
								} else {
									select_mtp = 'All selected';
								}
							} else {
								str.push($(this).text());
							}
						});
						lib.lab.find('span').text(str.length<5?str.join(''):select_mtp);
						select_list.scrollTop(select_list.find('.option').eq(index).offset().top-select_list.offset().top+select_list.scrollTop()-select_list.outerHeight()+select_list.find('.option').eq(index).outerHeight());
					}
				});
				if(!ele.disabled){
					lib.div.keyup(function(e){obj.focus();});
					obj.focus(function(){lib.lab.addClass(opt.focus);})
					.blur(function(){lib.lab.removeClass(opt.focus);});
					$(lib.div,lib.lab)
					.mouseover(function(){lib.lab.addClass(opt.hover);})
					.mouseout(function(){lib.lab.removeClass(opt.hover);});
				}
				ele.disabled && lib.lab.addClass(opt.disabled);
			}
		});
	};
	$.fn.inReset = function(params) {
		return this.each(function(){
			if($(this).data('inform') == 1){
				var opt = $(this).data('opt');
				if(opt.length<1 || $(this).hasClass(opt.form.name)) {
					var options = $.extend({},opt,params);
					$(this).removeClass(opt.form.name);
					$('input:text, input:password', this).inDestroy();
					$('input:checkbox', this).inDestroy();
					$('textarea', this).inDestroy();
					$('input:radio', this).inDestroy();
					$('select', this).inDestroy();
					$(this).inform(options);
				}
			}
		});
	}
	$.fn.inRedraw = function() {
		return this.each(function() {
			if($(this).hasClass('hide') || $(this).find('.hide').length > 0) {
				if($(this).hasClass('hide')){
					var ele = $($(this).data('ele'));
					var wrap = $(this).parents('div.inElement');
					var parent = wrap.parent();
				} else if($(this).find('.hide').length > 0){
					var ele = $($(this).find('.hide').data('ele'));
					if($(this).parents('.inElement').length > 0){
						var wrap = $(this).parents('.inElement');
					} else {
						var wrap = $(this);
					}
					var parent = wrap.parent();
				}
				var opt = wrap.data('opt');
				var parent = wrap.parent();
				wrap.remove();
				parent.append(ele);
				ele = ele.is('label') ? ele.find(':visible').first() : ele;
				if(ele.is('input:text, input:password')){
					ele.inText(opt);
				} else if(ele.is('input:checkbox')){
					ele.inCheckbox(opt);
				} else if(ele.is('textarea')){
					ele.inTextarea(opt);
				} else if(ele.is('input:radio')){
					ele.inRadio(opt);
				} else if(ele.is('select')){
					ele.inSelect(opt);
				}
			}
		})
	}
	$.fn.inDestroy = function() {
		return this.each(function() {
			if($(this).data('inform') == 1) {
				var opt = $(this).data('opt');
				if(opt !== undefined){
					if(opt.length<1 || $(this).hasClass(opt.form.name)) {
						$(this).removeClass(opt.form.name);
						$('input:text, input:password', this).inDestroy();
						$('input:checkbox', this).inDestroy();
						$('textarea', this).inDestroy();
						$('input:radio', this).inDestroy();
						$('select', this).inDestroy();
					}
				}
			} else if($(this).hasClass('hide')){
				var ele = $($(this).data('ele'));
				var wrap = $(this).parents('div.inElement');
				wrap.after(ele).remove();
			} else if($(this).find('.hide').length > 0){
				var ele = $($(this).find('.hide').data('ele'));
				if($(this).parents('.inElement').length > 0){
					var wrap = $(this).parents('.inElement');
				} else {
					var wrap = $(this);
				}
				wrap.after(ele).remove();
			}
		});
	}
	$.fn.inform = function(params) {
		var options = $.extend({},defaults,params);
		return this.each(function() {
			var self = $(this);
			self.data({'opt':options, 'inform':1});
			if(self.hasClass(options.form.name)) {
				return true;
			} else {
				$('input:text, input:password', this).inText(options.text);
				$('input:checkbox', this).inCheckbox(options.checkbox);
				$('textarea', this).inTextarea(options.textarea);
				$('input:radio', this).inRadio(options.radio);
				$('select', this).inSelect(options.select);
				self.addClass(options.form.name);
			}
		});
	}
})(jQuery);