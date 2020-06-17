<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/5/10
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    function a1() {
        console.log(111);
        $.ajax({
            url:"${pageContext.request.contextPath}/Djson",
            data:"",
            success:function (data) {
                console.log(data);
            }
        })
    }

</script>
<button id="1" onclick="a1()"></button>
</body>
</html>
