<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/4/27
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>预约界面</title>

    <link type="text/css" href="css/style.css" rel="stylesheet" />

    <script type="text/javascript" src="js/jquery.js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script language="javascript">
        $(function(){
            function test(func){
                var start = new Date().getTime();
                func();
                var end = new Date().getTime();
                return (end - start)+"ms";
            }
            $('.form').inform();
        });
    </script>

</head>
<body>

<div class="form">
    <form action="/addappointment" method="get">
        <div class="item" style="display: none;">
            <span>id：</span>
            <label><input type="text" name="userid" value=${user.id} class="width" placeholder="" /></label>
        </div>
        <div class="item">
            <span>用户名：</span>
            <label><input type="text" name="username" value=${user.uname} class="width" placeholder="" /></label>
        </div>
        <div class="item">
            <span>电话号码：</span>
            <label><input type="text" name="tel" value=${user.tel} placeholder="/></label>
        </div>
        <div class="item">
            <span>性　别：</span>
            <label><input type="radio" checked name="gender" value="1">男</label>&nbsp;
            <label><input type="radio" name="gender" value="2">女</label>&nbsp;
            <%--<label><input type="radio" name="gender" value="0" disabled />人妖</label>--%>
        </div>

        <div class="item">
            <span>可预约时间段：</span>
            <label>
                <select name="pvid" data-ratio="true">
                    <c:forEach items="${pvs}" var="pv">
                        <option value=${pv.pid}>${pv.timeDiv}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="item">
            <span>备　注：</span>
            <label><textarea name="message" class="width" placeholder="病情信息"></textarea></label>
        </div>
        <div class="item">
            <span></span>
            <label><button type="submit">提交</button></label>
            <label><button type="reset">重置</button></label>
        </div>
    </form>
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
</body>
</html>
