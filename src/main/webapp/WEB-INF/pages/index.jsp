<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../../web/js/jquery-form.js"></script>
<link rel="stylesheet" type="text/css" media="screen" title="classic"
      href="<c:url value="../../web/extjs/resources/theme-classic/resources/theme-classic-all.css"/> "/>
<script src="<c:url value="../../web/extjs/ext-all.js"/> " type="text/javascript"></script>
<script src="<c:url value="../../web/extjs/ext-locale-zh_CN.js"/> " type="text/javascript"></script>
<%--
  Created by IntelliJ IDEA.
  User: Jade.xiao
  Date: 2018/1/16
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<div id="userdiv">
    <div>
        <input type="submit" value="查询"
               onclick="searchUser(document.getElementById('UserId').value,'UserTable','updateDiv','inputId','inputName','inputAge')"><input
            type="text"
            id="UserId"></br>
        <button onclick="insertShow('insertDiv')">添加用户</button>
        <button onclick="tabledeleteuser()">删除</button>
        <button onclick="tableupdateUser('updateDiv','inputId','inputName','inputAge')">修改</button>
        <button onclick="reload()">刷新</button>
        </br>
    </div>
    <div>
        <table id="UserTable" border="1" width="100%" cellpadding="1" cellspacing="1" bgcolor="#ACDDEC">
            <tr>
                <th>选择</th>
                <th>ID</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td width="5%"><input name="userCheckBox" type="checkbox"></td>
                    <td name="id">${user.id}</td>
                    <td name="name">${user.name}</td>
                    <td name="age">${user.age}</td>
                    <td>
                            <%--加type="button" 解决controller执行成功ajax却返回error的问题--%>
                        <button onclick="deleteById(${user.id})">删除</button>
                        <button onclick="updateShow('updateDiv','inputId','inputName','inputAge',${user.id},'${user.name}',${user.age})">
                            修改
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div id="insertDiv" style="display: none">
    <form action="/insert" id="insertUser" method="get">
        <label>输入ID：</label><input type="text" id="id" name="id"><br>
        <label>输入姓名：</label><input type="text" id="name" name="name"><br>
        <label>输入年龄：</label><input type="text" id="age" name="age"><br>
        <input type="submit" value="提交">
        <button onclick="return closeFormDiv('insertDiv')">取消</button>
    </form>
</div>
<div id="updateDiv" style="display: none">
    <form action="/update" id="updateUser" method="get">
        <label>ID :</label><input id="inputId" type="text" readonly name="id"/><br>
        <label>姓名:</label><input id="inputName" type="text" name="name"/><br>
        <label>年龄:</label><input id="inputAge" type="text" name="age"/><br>
        <input type="submit" value="提交">
        <button onclick="return closeFormDiv('updateDiv')">取消</button>
    </form>
</div>
</body>
</html>


<script type="text/javascript">
    //    用于复选框选中后赋值ID进行操作
    var USERID = null;
    var USERNAME = null;
    var USERAGE = null;

    //    判断复选框选中操作对象，此处设置只能选择一个对象进行操作。
    function ck() {
        var countCheckBox = 0;
        var idtemp = null;
        var nametemp = null;
        var agetemp = null;
        var check = $("input[name='userCheckBox']:checked");//选中的复选框
        check.each(function () {
            countCheckBox++;
        });
        if (countCheckBox == 1) {
            check.each(function () {
                var row = $(this).parent("td").parent("tr");
                idtemp = row.find("[name='id']").html();//注意html()和val()
                nametemp = row.find("[name='name']").html();
                agetemp = row.find("[name='age']").html();
                USERID = idtemp;
                USERNAME = nametemp;
                USERAGE = agetemp;
            });
        } else {
            alert("请选中一个进行操作");
        }
    }

    //        删除操作
    function tabledeleteuser() {
//            check复选框
        ck();
//            进行删除 选中才删除
        if (USERID != null) {
            deleteById(USERID);
            USERID = null;
            USERNAME = null;
            USERAGE = null;
        }

    }

    //         修改操作
    function tableupdateUser(updatediv, inputid, inputname, inputage) {
//          check复选框
        ck();
//        进行更新 选中才更新
        if (USERID != null) {
            updateShow(updatediv, inputid, inputname, inputage, USERID, USERNAME, USERAGE);
            USERID = null;
            USERNAME = null;
            USERAGE = null;
        }

    }

    //    点击table某一行获取到其中的数据
        $(function () {
            var TaskType = '';
            $("#UserTable tr:gt(0)").click(function () {
//                第一次执行的时候冒泡时间并没有阻止成功第二次开始没有问题
//                TaskType = $(this).find("td").eq(1).html();
//                alert(TaskType)
//                TaskType = $(this).find("td").eq(2).html();
//                alert(TaskType)
//                TaskType = $(this).find("td").eq(3).html();
//                alert(TaskType)

//    //            获取当前点击行checkbox
//                if($(this).find(":checkbox").prop('checked')){
//                    $(this).find(":checkbox").prop("checked", false);
//                }else{
//                    $(this).find(":checkbox").prop("checked",true);
//                }
//    //            阻止冒泡事件
//                var check=$("input[name='userCheckBox']");//复选框
//                check.on('click',function (e) {  //check是子元素
//                    e.stopPropagation();
//                });


    //              这个是循环全部获取checkbox
    //            var tbodyObj = document.getElementById('UserTable');
    //            $(":checkbox").each(function(key,value){
    //                if($(value).prop('checked')){
    //                    alert($(value).prop('checked'));
    //                    $(value).prop('checked',false);
    //                    alert($(value).prop('checked'));
    //                }else{
    //                    alert($(value).prop('checked'));
    //                    $(value).prop('checked',true);
    //                }
    //            })
            })
        });


    //    删除用户
    function deleteById(Id) {
        $.ajax({
            url: "<c:url value="/delete"/>",
            async:false,
            data: {"userId": Id},
            datatype: "json",
            success: function (data) {
                alert(data.deleteflag);
            },
            error: function (data) {
                alert("system wrong");
            }
        });
        location.reload(true);
    }


    //    增加新用户
    $(document).ready(function () {
        $("#insertUser").ajaxForm(function (data) {
            alert(data.insertflag);
            if (data.insertflag == "success") {
                window.location.href = "/index";
            }
        });
    });

    //    修改用户信息
    $(document).ready(function () {
        $("#updateUser").ajaxForm(function (data) {
            alert(data.updateflag);
            if (data.updateflag == "success") {
                window.location.href = "/index";
            }
        });
    });

    //    根据用户id查询用户
    function searchUser(userId, tableId, updatediv, inputid, inputname, inputage) {
        $.ajax({
            url: "<c:url value="/id"/>",
            data: {"userId": userId},
            datatype: "json",
            beforeSend: function () {
                alert("before search");
            },
            success: function (data) {
                $("#" + tableId).empty();
                $("#" + tableId).append("<tr><th>选择</th><th>ID</th><th>姓名</th><th>年龄</th></tr>");
                $.each(data.users, function (index, items) {
                    $("#" + tableId).append("<tr><td width='5%'><input name='userCheckBox' type='checkbox'></td><td name='id'>" + items.id + "</td><td name='name'>" + items.name + "</td><td name='age'>" + items.age + "</td></tr>");
                });
                $("#"+tableId).append("<tr><td width='5%'><button onclick='reload()'>返回</button></td></tr>")
            },
            error: function () {
                alert("系统错误");
            }
        });
    }
    function reload(){
        location.reload(true);
    }

    //    增加用户的影藏div的显示
    function insertShow(divid) {
        var show = $("#" + divid).css('display');
        if (show == 'none') {
            $("#" + divid).css('display', '');
        } else {
            $("#" + divid).css('display', 'none');
        }
    }

    //  影藏div的方法
    function closediv(divid) {
        $("#" + divid).css('display', 'none');
    }

    //  隐藏div且不触发表单的提交
    function closeFormDiv(divid) {
        $("#" + divid).css('display', 'none');
        return false;
    }

    //    修改用户时出现修改提示
    function updateShow(divid, inputid, inputname, inputage, userId, userName, userAge) {
        var show = $("#" + divid).css('display');
        if (show == 'none') {
            $("#" + divid).css('display', 'block');
            $("#" + inputid).val(userId);
            $("#" + inputname).val(userName);
            $("#" + inputage).val(userAge);
        } else {
            if (userId == $("#" + inputid).val()) {
                $("#" + divid).css('display', 'none');
            } else {
                $("#" + inputid).val(userId);
                $("#" + inputname).val(userName);
                $("#" + inputage).val(userAge);
            }
        }
    }

    //     修改用户信息
    function updateUser(inputId, inputName, inputAge) {
        var userId = $("#" + inputId).val();
        var userName = $("#" + inputName).val();
        var userAge = $("#" + inputAge).val();
        $.ajax({
            url: "<c:url value="/update"/>",
            data: {"userId": userId, "userName": userName, "userAge": userAge},
            datatype: "json",
            success: function (data) {
                alert(data.flag);
            },
            error: function () {
                alert("系统错误!");
            }
        });
    }
</script>
