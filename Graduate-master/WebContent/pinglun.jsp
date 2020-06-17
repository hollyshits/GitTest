<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/5/3
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Comment System</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/index2.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/angular.js"></script>
    <script src="js/comment.js"></script>
</head>
<body ng-controller="mainController">

<!--这个报错功能不是很智能，只能显示在整个页面的最顶端-->
<div class="alert alert-warning hintDiv" id="hintDiv">
    <strong>评论不能为空</strong>
</div>

<div  class="container login"  ng-if="!isShowLogin">
    <form>
        <p>登录窗口</p>
        <p ng-if="isShowError"> 用户名或密码错误</p>
        <p ng-if="registerFlag">注册成功</p>
        用户名：<input type="text" ng-model="user.username"><br><br>
        密码：<input type="text" ng-model="user.password"><br><br>
        <button ng-click="login(user.username,user.password)">登录</button>
        <button ng-click="nologin()">匿名</button>
        <button ng-click="showRegisterBox()">注册</button>
    </form>
    <form ng-if="isShowRegister">
        <p>注册</p>
        <p ng-if="registerError"> 格式错误</p>
        用户名：<input type="text" ng-model="registerUsername"><br><br>
        密码：<input type="text" ng-model="registerPassowrd"><br><br>
        <button ng-click="myregister(registerUsername,registerPassowrd)">注册</button>
    </form>
</div>
<div class="container" ng-if="isShowLogin">
    <button ng-click="logout()">登出</button>
</div>


<div class="container" style="width:500px;" ng-if="isShowLogin">
    <div class="saying">
        <div class="meta-top">
            <!--avatar-->
            <a href="javascript:void(0);">
                <img class="avatar" ng-src="{{saying.avatar}}">
            </a>
            <!--nickname-->
            <p class="author">
                <a class="name" href="javascript:void(0);">{{saying.author}}</a>
                <a>{{saying.title}}</a>
            </p>
            <!--create time-->
            <span class="publish-time">{{saying.createTime}}</span>
        </div>
        <!--content-->
        <p class="content">{{saying.sayingContent}}</p>
    </div>

    <div class="comment-footer clearfix">
        <a class="like pull-left" href="javascript:void(0);" ng-click="like(saying.id)">
            <span ng-if="isShowLike" class="glyphicon glyphicon-heart"></span>
            <span ng-if="!isShowLike" class="glyphicon glyphicon-heart-empty"></span>
            <span>喜欢({{saying.likes.length}})</span>
        </a>
        <a class="like" href="javascript:void(0);">
            <span class="glyphicon glyphicon-share-alt"></span>
            <span>分享</span>
        </a>
    </div>

    <div class="commment-head">
        <span class="pull-left" style="margin-top:0px;">
          {{saying.flcs.length}}条评论
        </span>
        <a class="like" href="javascript:void(0);" ng-click="showComment()">
            <span class="glyphicon glyphicon-pencil"></span>
            <span>添加新评论</span>
        </a>
    </div>

    <!--comment text-->
    <form id="comment" ng-if="isShowComment" novalidate>
        <div class="comment-text">
            <textarea autofocus placeholder="写下你的评论…" maxlength="2000" ng-model="cmt.fstlvlcmt"></textarea>
            <div style="text-align:right;">
                <button class="btn btn-sm btn-default" style="float:left;" ng-click="showComment()">取消</button>
                <button class="btn btn-sm btn-info" ng-click="firstComment(saying.id)">提交</button>
            </div>
        </div>
    </form>

    <div class="comment-list">
        <!--first level comment-->
        <div class="first-level" ng-repeat="comment in saying.flcs">
            <!--值得注意的是comment，可以把这个变量看成是for循环里头的临时变量，这个变量的生存周期应该是整个div范围的-->
            <div class="meta-top">
                <a href="javascript:void(0);">
                    <img class="avatar" ng-src="{{comment.avatar}}">
                </a>
                <p class="author">
                    <a class="name" href="javascript:void(0);">{{comment.commenter}}</a>
                </p>
                <div class="comment-footer clearfix">
                    <span class="publish-time pull-left" style="margin-top:0px;">{{comment.commentTime}}</span>
                    <!--这里的删除要保证当前用户只能删除自己的评论-->
                    <a ng-if="comment.commenter == user.username" class="like delete" href="javascript:void(0);"
                       ng-click="deletefslcmt(saying.id, comment.id)">
                        <span>删除</span>
                    </a>
                    <a class="like delete-reply" href="javascript:void(0);"
                       ng-click="showSecondComment(comment, comment.commenter)">
                        <!--这个链接的触发事件做的事是：-->
                        <!--把comment的isShowChildComment设为true-->
                        <!--把comment的scndlvlcmt写上一些内容-->
                        <!--当这事件完成，就会触发另外的事件-->
                        <!--也就是那个表单：用来回复一级评论消息的文本框-->
                        <span>回复</span>
                    </a>
                </div>
            </div>
            <p class="content">{{comment.commentContent}}</p>

            <!--second level comment-->
            <div class="second-level" ng-repeat="childComment in comment.slcs">
                <p class="reply">
                    <a href="javascript:void(0);" class="blue-link">{{childComment.replier}}</a>:
                    <a href="javascript:void(0);" class="blue-link">@{{childComment.toCommenter}}</a>
                    <span>{{childComment.replyContent}}</span>
                </p>
                <div class="comment-footer clearfix">
              <span class="publish-time pull-left" style="margin-top:0px;">
                {{childComment.replyTime}}
              </span>
                    <a ng-if="childComment.replier == user.username" class="like delete" href="javascript:void(0);"
                       ng-click="deletescndcmt(saying.id, comment.id, childComment.id)">
                        <span>删除</span>
                    </a>
                    <a class="like delete-reply" href="javascript:void(0);"
                       ng-click="showSecondComment(comment, childComment.replier)">
                        <span>回复</span>
                    </a>
                </div>
            </div>

            <form id="childComment" ng-if="comment.isShowChildComment" novalidate>
                <div class="comment-text">
                    <textarea autofocus maxlength="2000" ng-model="comment.scndlvlcmt"></textarea>
                    <div style="text-align:right;">
                        <button class="btn btn-sm btn-default" style="float:left;"
                                ng-click="hideSecondComment(comment)">取消
                        </button>
                        <button class="btn btn-sm btn-info" ng-click="reply(saying.id, comment)">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="commentModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Delete
                </h4>
            </div>
            <div class="modal-body">
                确定删除评论?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" ng-click="confirm()">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div><!-- /.modal -->

<div class="modal fade" id="slcModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Delete
                </h4>
            </div>
            <div class="modal-body">
                确定删除评论?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" ng-click="confirmSlc()">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div><!-- /.modal -->
</body>
</html>
