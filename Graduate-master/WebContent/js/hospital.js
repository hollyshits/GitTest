$(function () {

	//	0. 给出所有的筛选条件
	var arrConditionArea = AjaxRemoteGetData.getDistinctArea().slice(1);
	var arrConditionLevel = AjaxRemoteGetData.getDistinctLevel().slice(1);
	var arrConditionType = AjaxRemoteGetData.getDistinctType().slice(1);
	

	$('.filter .group > .condition').remove();
	/*$.each(arrConditionArea,function(i,item){
		var c = $('<a class="condition" href="#">'+item+'</a>');
		$('.filter .group').eq(2).append(c);
	})*/
	$.each(arrConditionLevel,function(i,item){
		var c = $('<a class="condition" href="#">'+item+'</a>');
		$('.filter .group').eq(1).append(c);
	})
	$.each(arrConditionType,function(i,item){
		var c = $('<a class="condition" href="#">'+item+'</a>');
		$('.filter .group').eq(0).append(c);
	})


	//	1. 筛选条件事件监听 & 动态获取新的数据 AjaxRemoteGetData.getHospitalArrByFilter
	var DataHospital = [];

	$('.filter')
		.on('initEvent',function(){

			var filter = $(this);
			filter.find('.condition').on('click',function(){

				var condition = $(this);
				var group = condition.parents('.group').eq(0);
				group.find('.condition').removeClass('condition_focus')
				condition.addClass('condition_focus');
				
				filter.triggerHandler('reloadData');
				return false;
			})

			filter.triggerHandler('reloadData');
		})
		.on('reloadData',function(){

			var type = $('.filter .group').eq(0).find('.condition_focus').text();
			var level = $('.filter .group').eq(1).find('.condition_focus').text();
			var area = $('.filter .group').eq(2).find('.condition_focus').text();

			DataHospital = AjaxRemoteGetData.getHospitalArrByFilter(type,level,area).slice(1);
			$('.pagination').triggerHandler('initPageNumber');
		})
	
	//	2. 分页设置事件监听 & 处理数据渲染
	var pageSize = 1; //	每页显示3条数据
	var pageCount = 0 ; // 总共n页
	var currentPage =0 ; // 当前显示第n页，0=1页
	$('.pagination')
		.on('initPageNumber',function(){

			pageCount = Math.ceil( DataHospital.length / pageSize );
			currentPage = 0;

			//	设置有几个数字页
			var page_wrap  = $('.page_wrap',this);
			page_wrap.empty();

			for(i=0;i<pageCount;i++){
				var page = '<a href="#1" class="item item_page">'+ (i+1) +'</a>';
				page_wrap.append(page);
			}

			$('.item_count',this).text('共计'+ pageCount +'页')

			$('.datalist').triggerHandler('render');

		})
		.on('initEventAndDelegate',function(){
			//	处理按钮事件、页码切换、页码提交切换
			var pagination = $(this);
			var page_wrap = $('.page_wrap',pagination);

			//	首页、尾页
			$('.item_first',pagination).on('click',function(){
				currentPage = 0;
				$('.datalist').triggerHandler('render');
				return false;
			});
			$('.item_last',pagination).on('click',function(){
				currentPage = pageCount-1;
				$('.datalist').triggerHandler('render');
				return false;
			});
			//	上页、下页
			$('.item_prev',pagination).on('click',function(){
				if(currentPage>0){
					currentPage = currentPage-1;
					$('.datalist').triggerHandler('render');
				}
				return false;
			});
			$('.item_next',pagination).on('click',function(){
				if(currentPage < pageCount-1 ){
					currentPage = currentPage+1;
					$('.datalist').triggerHandler('render');
				}
				return false;
			});
			//	页码
			page_wrap.delegate('.item_page','click',function(){
					currentPage = $(this).index();
					$('.datalist').triggerHandler('render');
					return false;
			})
			//	到第n页
			$('.input_submit',pagination).on('click',function(){
				var goPage = $('.input_page',pagination).val() - 1;
				if( goPage >= 0 && goPage < pageCount ){
					currentPage = goPage;
					$('.datalist').triggerHandler('render');
				}
			})

		})
		.triggerHandler('initEventAndDelegate')


	//	3. 数据列表呈现数据
	var template = $('#template_datalist_ite').html();

	$('.datalist')

		.on('render',function(){


			//	渲染下当前页码的高亮
			$('.pagination .page_wrap').find('.item_page')
					.removeClass('item_page_focus')
					.eq(currentPage).addClass('item_page_focus');


			var datalist =$(this);
			datalist.empty();
			var arrData = DataHospital.slice( currentPage*pageSize , (currentPage+1)*pageSize );

			//	循环所有数据，列表数据。
			for(i=0,j=arrData.length;i<j;i++){

				var d = arrData[i];
				var objectData = {
						'area':d[0],'level':d[1],'type':d[2],
						'name':d[3],'address':d[4],'phone':d[5],
						'imgUrl':d[6],'time':d[7]
				}

				var html = template;
				for( k in objectData ){
					var v = objectData[k];
					html = html.replace('{'+k+'}',v);
				}

				datalist.append(html);


			}


		})


	$('.filter').triggerHandler('initEvent');
})