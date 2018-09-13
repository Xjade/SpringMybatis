<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../../web/js/jquery-form.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: Jade.xiao
  Date: 2018/7/16
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Image</title>
</head>
<body>
<div>
    <%--<input type="file" value="上传文件">--%>

    <input type="button" onclick="clickShowImage()" value="显示图片">
    <%--<img id="image" src="/public/testBase22one.jpg">--%>
</div>
<el-dialog  :visible.sync="false">
    aaaaa
</el-dialog>
</body>
</html>

<script type="text/javascript">

function clickShowImage() {
    $.ajax({
        <%--url: "<c:url value="/delete"/>",--%>
        url:"/showImage",
        async:true,
        data: {},
        datatype: "json",
        success: function (data) {
            console.log(data);
            $("#image").attr('src',"data:image/jpeg;base64,"+data);
        },
        error: function (data) {
            alert("system wrong");
        }
    });

}
</script>
