 $(function() {
	var d = 7;//需要显示的预约天数
	//获取几天后（前）的日期以及周几
	function fun_date(aa) {
		var date1 = new Date(),
			time1 = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDate(); //time1表示当前时间
			var date2 = new Date(date1);
			date2.setDate(date1.getDate() + aa);
			console.log("${doctor.dNumber}");
			var time2 = (date2.getMonth() + 1) + "/" + date2.getDate();
			var w = "";
			switch(date2.getDay()) {
				case 0:
					w = "周日"
					break;
				case 1:
					w = "周一"
					break;
				case 2:
					w = "周二"
					break;
				case 3:
					w = "周三"
					break;
				case 4:
					w = "周四"
					break;
				case 5:
					w = "周五"
					break;
				case 6:
					w = "周六"
					break;
				default:
					w = ""
			}
			var dd = {};
			dd.day = time2;
			dd.week = w
			return dd;
	}
	var doctor_order = '<td rowspan="4" class="doctor_order"><table border="0" id="tab2"><tr>';
	for(var i = 0; i < d; i++) {
		if(i == 0) {
			doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">今天</div></td>'
		} else if(i == 1) {
			doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">明天</div></td>'
		} else if(i == 2) {
			doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">后hou天</div></td>'
		} else {
			doctor_order += '<td><div class="time">' + fun_date(i).day + '</div><div class="week">' + fun_date(i).week + '</div></td>'
		}
	}
	doctor_order+='</tr>';
	for (var j = 0;j<3;j++) {
		doctor_order+='<tr>';
		for (var k = 0;k<d;k++) {
			doctor_order+='<td><button style=\"width: 60px; height:30px\" herf=\"javascript:void(0);\" data-reveal-id=\"myModal\" onclick=\"modifyTableContent()\"></button></td>'
		}
		doctor_order+='</tr>'
	}
	doctor_order+='</table></td>';
	$(".arrow_left").after(doctor_order);
	$(".doctor_order>table").width(d*140);
	//选项卡
	$(".tab_hd div").click(function() {
		var _this = $(this);
		var i = _this.index();
		_this.addClass("active");
		_this.siblings().removeClass("active");
		$(".doctor_pane").eq(i).addClass("active");
		$(".doctor_pane").eq(i).siblings().removeClass("active");
	})
	//点击左右箭头
	//动画执行完为true
	var type = true;
	function change(c){
		var left = parseInt($(".doctor_order table").css("left"));
		console.log("位移是"+left);
		left = left + (-c*140);
		if(left >0 ){
			console.log("左边没有了")
			$(".arrow_left div img").attr("src","img/left.png");
			$(".arrow_left").removeClass("left_active");
		}else if(left< (d-7)*-140){
			console.log("右边没有了")
			$(".arrow_right div img").attr("src","img/right.png");
			$(".arrow_right").removeClass("right_active");
		}else{
			console.log("aaaaaaaaaaaaaaa")
			$(".arrow_left div img").attr("src","img/left_active.png");
			$(".arrow_right div img").attr("src","img/right_active.png");
			$(".arrow_left").addClass("left_active");
			$(".arrow_right").addClass("right_active");
			$(".doctor_order table").animate({left:left+"px"},1000);
			type = true;
			console.log(type)
		}	
	}

	var time = 0;	
	$(".arrow_left").click(function(){
		if (time == 0) {
			time = 1; //设定间隔时间（秒）			
			//启动计时器，倒计时time秒后自动关闭计时器。
			var index = setInterval(function(){
				time--;
				if (time == 0) {
					clearInterval(index);
				}
			}, 1000);
			change(-1);
		}else{
			// alert('目前按钮事件不允许被触发');
		}

		
	})
	
	$(".arrow_right").click(function(){
		if (time == 0) {
			time = 1; //设定间隔时间（秒）
			//启动计时器，倒计时time秒后自动关闭计时器。
			var index = setInterval(function(){
				time--;
				if (time == 0) {
					clearInterval(index);
				}
			}, 1000);
			change(1);
		}else{
			// alert('目前按钮事件不允许被触发');
		}
		
	})
})