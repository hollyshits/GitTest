<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>添加医生</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/transition.js"></script>
<script src="js/collapse.js"></script>
<script src="js/ie-emulation-modes-warning.js"></script>
<script type="text/javascript" src="js/dropdown.js"></script>
</head>
    
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span> 修改个人信息</strong>
		</div>
		<div class="body-content">
		
		
			<form method="post" class="form-x" >
				<div class="form-group">
					<div class="label">
						<label>患者姓名：</label>
					</div>
					<div class="field">
						<input type="text" name="name" value="${aid}"  style="width: 25%; float: left;" class="input" />
							<div class="tipss"><font color="red">*必填项</font></div>
					</div>
					
				</div>
				<div class="form-group">
					<div class="label">
						<label>密码：</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="password" value="${pwd}"  style="width: 25%; float: left" type="text"  />
						<div class="tipss"><font color="red">*必填项</font></div>
					</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>个人描述</label>
						</div>
						<div class="field">
							<textarea class="input" name="Info" value="" style="width: 50%; float: left" >${info }</textarea>
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>电话号码：</label>
						</div>
						<div class="field">
							<input type="text" class="input" name="tel" value="${tel}"  style="width: 25%; float: left" type="text"  />
							<div class="tipss"><font color="red">*必填项</font></div>
						</div>
					</div>
					<div class="field" align="center">
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
					 <script type="text/javascript">
    function update(bid) {
    	con=confirm("是否更改?"); 
    	if(con==true){
    		location.href = "/updateUserServlet?bid="+bid;
    	}
    }
    </script>
				</div>
			</form>
		</div>
	
</body>
</html>