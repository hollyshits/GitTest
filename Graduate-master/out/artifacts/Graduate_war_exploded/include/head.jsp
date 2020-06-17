<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/4/27
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="top" class="top">
    <div class="wrap">
        <p class="call">010-114/116114电话预约</p>
        <p class="welcome">
            <c:choose>
                <c:when test="${user.uname!=null}"><a href="index2.jsp">欢迎${aid}</a>
                    <a href="login2.jsp" >注销</a></c:when>
                <c:otherwise>欢迎来到H医院预约挂号统一平台&nbsp;请&nbsp;
                    <a href="/login2.jsp">登录</a>
                    <a href="/register.jsp">注册</a>
                </c:otherwise>
            </c:choose>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="/help.jsp">帮助中心</a>
        </p>
    </div>
</div>