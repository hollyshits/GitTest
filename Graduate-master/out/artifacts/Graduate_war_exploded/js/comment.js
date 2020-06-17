var app = angular.module('app', []);

app.controller('mainController', ['$scope', 'httpService', function($scope, httpService) {

	//模拟出一个已经登录的用户
	/*var mockusers = [{
		"id" : "1",
		"username" : "li",
		"avatar" : "images/li.jpg"
	}, {
		"id" : "2",
		"username" : "mario",
		"avatar" : "images/mario.jpg"
	}, {
		"id" : "3",
		"username" : "timelessmemory",
		"avatar" : "images/timelessmemory.jpg"
	}, {
		"id" : "4",
		"username" : "smile",
		"avatar" : "images/smile.jpg"
	},]

	var currentUserIndex = 2;

	window.localStorage.setItem("id", mockusers[currentUserIndex].id);
	window.localStorage.setItem("username", mockusers[currentUserIndex].username);
	window.localStorage.setItem("avatar", mockusers[currentUserIndex].avatar);*/

	$scope.isShowComment = false;

	$scope.isShowLogin = false;

	$scope.isShowError = false;

	$scope.isShowRegister = false;

	$scope.registerFlag = false;

	$scope.registerError = false;
	
	$scope.cmt = {
		fstlvlcmt : ""
	}
	//这些事全局变量
	var sayingID, flcId, slcId;

	//current user information
	$scope.user = {
		id : "",
		username : "",
		password : "",
		avatar : "images/li.jpg"
	}

	$scope.registerUsername;
	$scope.registerPassowrd;

    var id=1;
	httpService.get("http://localhost:8080/saying/get/comment/"+id, {}, function(data) {
		$scope.saying = data;
		//likes应该是一个字符串，数组内容是每一个给它点赞用户的id，以逗号分隔
		//判断这个数组是否为空，空则返回[]，否则返回把这个字符串返回成数组，以逗号分隔
		$scope.saying.likes = $scope.saying.likes.split(",")[0] == "" ? [] : $scope.saying.likes.split(",");
		//如果当前登录的用户已经给这个评论点赞了，则返回true，否则false
		$scope.isShowLike = $scope.saying.likes.contains($scope.user.id);
	}, function(error) {
		console.log(error)
	})

	$scope.like = function(sayingId) {
		if ($scope.saying.likes.contains($scope.user.id)) {
			$scope.saying.likes.splice($scope.saying.likes.indexOf($scope.user.id), 1);
		} else {
			$scope.saying.likes.push($scope.user.id)
		}

		var tmpLikes = $scope.saying.likes;
		tmpLikes = tmpLikes.join(",");

		var data = {
			id : sayingId,
			likes : tmpLikes
		};

		httpService.post("http://localhost:8080/saying/likes", data, function(data) {
			$scope.isShowLike = $scope.saying.likes.contains($scope.user.id);
		}, function(error) {
			console.log(error)
		})
	}

	$scope.showComment = function() {
		$scope.isShowComment = ! $scope.isShowComment;
	}

	$scope.login = function(username,password){
        var data = {
            username : username,
            password : password,
        }
        httpService.post("http://localhost:8080/user/login", data, function(data) {
            //获取到时间和id
			$scope.user.id = data.id;
			$scope.user.username = data.username;
             $scope.isShowLogin = data.status;
             $scope.isShowError = !data.status;

        }, function(error) {
            console.log(error)
        })
        //shakeModal();
    }

    $scope.myregister = function(username,password){
		var data = {
			username : username,
			password : password,
		}
		httpService.post("http://localhost:8080/user/register", data, function(data) {
			//获取到时间和id
			$scope.registerFlag = data;
			$scope.isShowRegister = !data;
			$scope.registerError = !data;

		}, function(error) {
			console.log(error)
		})
	}
    $scope.nologin = function(){
		$scope.user.username = "as";
		$scope.isShowLogin = true;
		$scope.isShowError = false;
	}
	$scope.logout = function(){
		$scope.user.username = "";
		$scope.user.id = "";
		$scope.user.password = "";
		$scope.isShowLogin = false;
	}
	$scope.showRegisterBox = function(){
		$scope.isShowRegister = !$scope.isShowRegister;

	}
	$scope.firstComment = function(sayingId) {

		if ($scope.cmt.fstlvlcmt == "") {
			$('#hintDiv').fadeIn(300);
			
			setTimeout(function() {
				$('#hintDiv').fadeOut(300);
			}, 800);

			return;
		}

		var data = {
			sayingId : sayingId,
			commenter : $scope.user.username,
			avatar : $scope.user.avatar,
			commentContent : $scope.cmt.fstlvlcmt,
			slcs : []
		}

		httpService.post("http://localhost:8080/comment/add/first", data, function(result) {
			//获取到时间和id
			data.id = result.id;
			data.commentTime = result.commentTime;
			//放入saying中的flcs容器中，并且清空
			$scope.saying.flcs.push(data);
			$scope.cmt.fstlvlcmt = "";
			$scope.isShowComment = false;
			console.log($scope.saying.flcs)
		}, function(error) {
			console.log(error)
		})
	}

	$scope.showSecondComment = function(comment, toWho) {
		if (!comment.isShowChildComment) {
			comment.scndlvlcmt = "@" + toWho + " ";
			comment.tmptoWho = toWho;
			comment.isShowChildComment = true;
		} else {
			if (toWho == comment.tmptoWho) {
				comment.isShowChildComment = false;
				comment.tmptoWho = "";
				comment.scndlvlcmt = "";
			} else {
				comment.scndlvlcmt = "@" + toWho + " ";
				comment.tmptoWho = toWho;
			}
		}
	}

	$scope.hideSecondComment = function(comment) {
		comment.isShowChildComment = false;
		comment.tmptoWho = "";
		comment.scndlvlcmt = "";
	}
	//修复插入二级评论失败的bug
	$scope.reply = function(sayingId, comment) {

		var scndlvlcmt = comment.scndlvlcmt;
		var str = "@" + comment.tmptoWho;
		
		if (scndlvlcmt == "" || $.trim(scndlvlcmt) == str) {
			$('#hintDiv').fadeIn(300);
			
			setTimeout(function() {
				$('#hintDiv').fadeOut(300);
			}, 800);

			return;
		}
		
		var cmt = "";
		//首先判断评论框里是否有固定的格式内容，即回复哪一个一级评论的用户,表示为@xxx
		//若存在，截取str后面的评论内容作为cmt
		//若不存在，全部都将作为cmt
		if (scndlvlcmt.indexOf(str) >= 0) {
			cmt = scndlvlcmt.substr(scndlvlcmt.indexOf(str) + str.length)
		} else {
			cmt = scndlvlcmt;
		}

		var data = {
			sayingId : sayingId,
			flcId : comment.id,
			replier : $scope.user.username,
			toCommenter : comment.tmptoWho,
			replyContent :  cmt
		}

		httpService.post("http://localhost:8080/comment/add/second", data, function(result) {
			comment.isShowChildComment = false;
			comment.scndlvlcmt = "";
			data.id = result.id;
			data.replyTime = result.replyTime;
			//这一步是为了把二级评论的消息放入到saing属性下的相应的位置
			//值得注意的是：这一步是一定可以成功的，不论是否成功的将评论写入数据库中
			angular.forEach($scope.saying.flcs, function(item) {
				if (item.id == comment.id) {
					item.slcs.push(data);
				}
			})
		}, function(error) {
			console.log(error)
		})
	}

	$scope.deletefslcmt = function(sayingId, firstlvlId) {
		$('#commentModal').modal("show");
		sayingID = sayingId;
		flcId = firstlvlId;
	}

	$scope.confirm = function() {
		$('#commentModal').modal("hide");

		var url = "http://localhost:8080/comment/delete/first/" + sayingID + "/" + flcId;
		
		httpService.get(url, {}, function(data) {
			//local delete comment
			//怎么理解本地删除评论：
			//js文件中，angular在第一次载入时调用httpservice服务请求到json存入到了$scope.saying中
			//之后如果不显式的请求，将不会更新saying属性中的内容
			//需要通过手动的方式在本地删除，而不是请求远程服务器
			angular.forEach($scope.saying.flcs, function(item, index) {
				if (item.id == flcId) {
					$scope.saying.flcs.splice(index, 1);
				}
			})
			sayingID = "";
			flcId = "";
		}, function(error) {
			console.log(error)
		})
	}

	$scope.deletescndcmt = function(sayingId, firstlvlId, secondlvlId) {
		$('#slcModal').modal("show");
		sayingID = sayingId;
		flcId = firstlvlId;
		slcId = secondlvlId;
	}

	$scope.confirmSlc = function() {
		$('#slcModal').modal("hide");

		var url = "http://localhost:8080/comment/delete/second/" + sayingID + "/" + slcId;
		
		httpService.get(url, {}, function(data) {
			//local delete second level comment
			angular.forEach($scope.saying.flcs, function(item) {
				if (item.id == flcId) {
					angular.forEach(item.slcs, function(slc, index) {
						if (slc.id == slcId) {
							item.slcs.splice(index, 1);
						}
					})
				}
			})
			sayingID = "";
			flcId = "";
			slcId = "";
		}, function(error) {
			console.log(error)
		})
	}

}]);

app.factory('httpService', ['$http', function($http) {
  return {
    get : function(url, params, successCallback, errorCallback) {
        $http({
            url : url + "?" + $.param(params),
            method : 'get',
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
            responseType : 'json'
        })
        .success(successCallback)
        .error(errorCallback);
    },
    post : function(url, params, successCallback, errorCallback) {
      $http({
            url : url,
            method : 'post',
            data : $.param(params),
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
            responseType : 'json'
      })
      .success(successCallback)
      .error(errorCallback);
    }
  }
}]);

Array.prototype.contains = function(obj) {
    var i = this.length;

    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
