<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: Jade.xiao
  Date: 2018/1/18
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<%--<div>--%>
    <%--<form action="/Insert" method="get">--%>
    <%--<label>输入ID：</label><input type="text" id="id" name="id"><br>--%>
    <%--<label>输入姓名：</label><input type="text" id="name" name="name"><br>--%>
    <%--<label>输入年龄：</label><input type="text" id="age" name="age"><br>--%>
    <%--<input type="submit" value="提交"><button onclick="CloseWindow()">取消</button>--%>
    <%--</form>--%>
<%--</div>--%>
<div>
    <form action="" method="get">
        <label>输入ID：</label><input type="text" id="id" name="id"><br>
        <label>输入姓名：</label><input type="text" id="name" name="name"><br>
        <label>输入年龄：</label><input type="text" id="age" name="age"><br>
        <button onclick="AddUser(${user})">提交</button>
    </form>
</div>
</body>
</html>


<script type="text/javascript">




    function CloseWindow() {
        window.close();
    }
</script>
