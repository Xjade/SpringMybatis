<%--
  Created by IntelliJ IDEA.
  User: Jade.xiao
  Date: 2018/1/31
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<c:url value="../../web/extjs/ext-all.js"/> " type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" title="classic"
      href="<c:url value="../../web/extjs/resources/theme-classic/resources/theme-classic-all.css"/> "/>
<script src="<c:url value="../../web/extjs/ext-locale-zh_CN.js"/> " type="text/javascript"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>extjsuser</title>
</head>
<body>
</body>
</html>
<script type="text/javascript">
    //到数据库查询数据
    var doQueryUrl ="<c:url value='/extid'/>";
    //保存数据
    var doSaveUrl = "<c:url value='/extinsert'/>";
    //删除
    var doDeleteUrl ="<c:url value='/extdelete'/>";
    //编辑
    var doUpdateUrl ="<c:url value="/extupdate"/>";
    //表格数据处理
    var gridStore;
    //新增用户窗口装载表单
    var extAdd;
    //更新用户窗口装载表单
    var extUpdate;
    //表单
    var grid;


    Ext.onReady(function() {
        /*Ext.BLANK_IMAGE_URL默认已经加载s.gif*/
        Ext.tip.QuickTipManager.init();

        var storePageSize = 10;

        $("#edits").click(function(){
            alert("edit");
        });

        //新增用户数据的表单
        var addform = new Ext.form.Panel({
            autoScroll: true,  //与layout: "fit",结合使用，在window内部显示滚动条
            frame: false,
            border: false,
            bodyPadding: 10,
            defaults: {
                width: 350
            },
            items: [{
                xtype: "textfield",
                id: "insertUserId",
                fieldLabel: "用户ID<font color='red'>*</font>",
                allowBlank: false
            }, {
                xtype: "textfield",
                id: "insertUserName",
                fieldLabel: "用户姓名<font color='red'>*</font>",
                allowBlank: false,
            }, {
                xtype: "numberfield",
                id: "insertUserAge",
                fieldLabel: "用户年龄<font color='red'>*</font>",
                allowBlank: false
            }]
        });

        //更新用户数据的表单
        var updateform = new Ext.form.Panel({
            autoScroll: true,  //与layout: "fit",结合使用，在window内部显示滚动条
            frame: false,
            border: false,
            bodyPadding: 10,
            defaults: {
                width: 350
            },
            items: [{
                xtype: "textfield",
                id: "updateUserId",
                fieldLabel: "用户ID<font color='red'>*</font>",
                allowBlank: false,
                readOnly:true
            }, {
                xtype: "textfield",
                id: "updateUserName",
                fieldLabel: "用户姓名<font color='red'>*</font>",
                allowBlank: false,
            }, {
                xtype: "numberfield",
                id: "updateUserAge",
                fieldLabel: "用户年龄<font color='red'>*</font>",
                allowBlank: false
            }]
        });

        //装新增用户数据表单的窗口
        extAdd = new Ext.window.Window({
            title: "用户信息",
            width: 420,
            height: 200,
            closeAction: "hide",
            modal: true,  //遮罩 :就是让form表单以外不能编辑
            constrain: true,  //限制拖动范围
            resizable: true,  //可调整大小的; 可变尺寸的
            bodyPadding: 10,
            border:false,
            buttonAlign: "center",    //按钮显示位置
            layout: "fit",
            items: [addform],  //装表单进来
            listeners: {
                "close": function() {
                    //点击右上角的关闭按钮，就清空form
                    addform.getForm().reset();
                }
            },
            buttons: [{
                text: "保存",
                iconCls: "icon-save",
                handler: function() {
                    doSave();
                }
            }, {
                text: "取消",
                iconCls: "icon-cancel",
                handler: function() {
                    addform.getForm().reset();
                    extAdd.hide();  //隐藏窗口
                }
            }]
        });

        //装更新用户数据表单的窗口
        extUpdate = new Ext.window.Window({
            title: "用户信息",
            width: 420,
            height: 200,
            closeAction: "hide",
            modal: true,  //遮罩 :就是让form表单以外不能编辑
            constrain: true,  //限制拖动范围
            resizable: true,  //可调整大小的; 可变尺寸的
            bodyPadding: 10,
            border:false,
            buttonAlign: "center",    //按钮显示位置
            layout: "fit",
            items: [updateform],  //装表单进来
            listeners: {
                "close": function() {
                    //点击右上角的关闭按钮，就清空form
                    updateform.getForm().reset();
                }
            },
            buttons: [{
                text: "保存",
                iconCls: "icon-save",
                handler: function() {
                    doUpdate();
                }
            }, {
                text: "取消",
                iconCls: "icon-cancel",
                handler: function() {
                    updateform.getForm().reset();
                    extUpdate.hide();  //隐藏窗口
                }
            }]
        });


//        新增确认保存方法
        function doSave() {
            if(addform.getForm().isValid()) {
                Ext.Msg.confirm("提示", "是否保存？", function(btn) {
                    if(btn == "yes") {
                        //加载中
                        //  layer.load(2);

                        $.ajax({
                            type : "GET",
                            url : doSaveUrl,
                            datatype : "json",  //返回 JSON 数据
                            cache : false,  //不缓存
                            async : true,
                            timeout : 30000,
                            data : {
                                //传参
                                userId: Ext.getCmp("insertUserId").getValue(),
                                userName: Ext.getCmp("insertUserName").getValue(),
                                userAge: Ext.getCmp("insertUserAge").getValue()
                            },
                            success : function(data, textStatus) {
                                //关闭加载中
                                //  layer.closeAll('loading');
                                if(data.insertflag == "success") {
                                    addform.getForm().reset();
                                    extAdd.hide();
                                    alert("新增成功！");
                                    doQuery();
                                }else{
                                    alert("用户id已存在");
                                }
                                //  layer.msg(data.msg);
                            },
                            error : function(XMLHttpRequest, textStatus, errorThrown) {
                                Ext.Msg.alert("提示","很遗憾出现错误");
                                //layer.closeAll('loading');
                            }
                        });
                    }
                });
            }

        }

        //        更新确认保存方法
        function doUpdate() {
            if(updateform.getForm().isValid()) {
                Ext.Msg.confirm("提示", "是否提交修改？", function(btn) {
                    if(btn == "yes") {
                        //加载中
                        //  layer.load(2);

                        $.ajax({
                            type : "GET",
                            url : doUpdateUrl,
                            datatype : "json",  //返回 JSON 数据
                            cache : false,  //不缓存
                            async : true,
                            timeout : 30000,
                            data : {
                                //传参
                                userId: Ext.getCmp("updateUserId").getValue(),
                                userName: Ext.getCmp("updateUserName").getValue(),
                                userAge: Ext.getCmp("updateUserAge").getValue()
                            },
                            success : function(data, textStatus) {
                                //关闭加载中
                                //  layer.closeAll('loading');
                                if(data.updateflag == "success") {
                                    updateform.getForm().reset();
                                    extUpdate.hide();
                                    alert("更新成功！");
                                    doQuery();
                                }
                                //  layer.msg(data.msg);
                            },
                            error : function(XMLHttpRequest, textStatus, errorThrown) {
                                Ext.Msg.alert("提示","很遗憾出现错误");
                                //layer.closeAll('loading');
                            }
                        });
                    }
                });
            }

        }




//        查询全部数据
        gridStore = new Ext.data.Store({
            pageSize: storePageSize,  //设置分页大小
            fields: [
                {name:"userId",mapping:"id"},
                {name:"userName",mapping:"name"},
                {name:"userAge",mapping:"age"}
            ],
            proxy: {   //Proxy对象，用于访问数据对象。
                type: "ajax",
                url:doQueryUrl,
                data:{},
                extraParams:{},
                //data: json,
                reader: {
                //处理数据对象的DataReader会返回一个Ext.data.Record对象的数组。其中的id属性会是一个缓冲了的键。
                    type: "json",  //返回数据类型为json格式
                    root:  "users"  //数据
                }
            },
            autoload:true
        });

        //创建多选
        var selModel = Ext.create("Ext.selection.CheckboxModel");

//      整体中间显示的表单
        grid = new Ext.grid.Panel({
            region: "center",
//             selModel:Ext.create('Ext.selection.CheckboxModel',{mode:"SIMPLE"}),  复选框
            selModel:selModel,
            disableSelection:false,//值为TRUE，表示禁止选择行
            title: "用户信息列表",
            frame: true,
            border: false,
            columnLines: true,  //在列分隔处显示分隔符
            autoScroll: true,
            viewConfig: {
                forceFit: true  //列宽度自动充满空间,强制平均列宽度
            },
//            store：就是刚刚定义查询放入内容的store 自动填充
            store: gridStore,
            columns: [{
                xtype: "rownumberer"  //显示行号
            },{
                    header: "用户ID",
                    dataIndex: "userId",
                },
                {
                    header: "用户姓名",
                    dataIndex: "userName",
                    width: 200
                },
                {
                    header: "用户年龄",
                    dataIndex: "userAge",
                    width: 100
                }],
            loadMask: {   //loadMask:True表示为当grid加载过程中，会有一个Ext.LoadMask的遮罩效果。默认为false。
                msg: "正在加载数据，请稍候......"
            },
            dockedItems: [{   //在底部显示，分页
                xtype: "pagingtoolbar",
                store: gridStore,
                dock: "bottom", //extjs在容器中引入了dockedItems属性，需要停靠的组件应该放在这里。且停靠位置用dock属性指定。
                displayInfo: true
            }],
            tbar: [{
                text: "新增",
                iconCls: "icon-add",
                handler: function() {
                    extAdd.show();
                }
            },{
                text:"编辑",
                handler:function(){
                    edits();
                }
            },{
                text:"删除",
                handler:function(){
                    deleteUser();
                }
            },
                "-",//一条竖线，用于分隔
                "（提示：双击编辑!）"],
            listeners: {
                //双击事件
                /*视图：Ext.view.View
                 record：Ext.data.Model 属于item的记录  The record that belongs to the item
                 item：HTMLElement item的元素
                 index：Number item的索引
                 e：Ext.EventObject raw事件对象
                 eOpts：Object将options对象传递给Ext.util.Observable.addListener。*/
                "itemdblclick": function(grid, record, item, index, e) {
//                    这边的userId userName userAge 是Panel里面的columns的里面的dataindex
                    editRow(record.data["userId"], record.data["userName"], record.data["userAge"]);
                }
            }
        });

//        顶部查询部分
        var panel = new Ext.Panel({
            region: "north",  //显示在顶部
            layout: {
                type: "table",
                columns: 6
            },
            height: 60,
            width: "100%",
            bodyPadding: 10,  //内边距
            frame: true,
            defaults: {
                width: 120,  //对 下面items内容宽度的设置
                margin: "0 10 10 0"
            },
            items: [{
                id: "txtUserId",
                xtype: "textfield",
                emptyText: "用户ID"
            }, {
                xtype: "button",
                text: "查询",
                width: 60,
                iconCls: "icon-search",
                handler: function() {
                    var userIdTemp=Ext.getCmp("txtUserId").getValue();
                    doQueryById(userIdTemp);
                }
            }]
        });

        var viewport = new Ext.container.Viewport({
            layout: "border",
            items: [panel, grid]
        });
        doQuery();  //连接数据库才需要执行这一行
    });

//    全部查询
    function doQuery() {
        gridStore.loadPage(1);
    }

//    根据id查询
    function doQueryById(userId){
//        为查询添加参数
        var new_params={"userId":userId};
        Ext.apply(gridStore.proxy.extraParams, new_params);
        gridStore.loadPage(1);
    }


    //设置值并显示在表单中
    function editRow(userId,userName,userAge) {
        Ext.getCmp("updateUserId").setValue(userId);
        Ext.getCmp("updateUserName").setValue(userName);
        Ext.getCmp("updateUserAge").setValue(userAge);
        extUpdate.show();
    }




    //编辑
    function  edits(){
        var record = grid.getSelectionModel().getSelection();
        if(record.length == 0 || record.length > 1){
            Ext.MessageBox.show({
                title:"提示",
                msg:"请选择一行进行编辑！"
            })
            return;
        }else{
            var userId = record[0].get("userId");
            var userName = record[0].get("userName");
            var userAge = record[0].get("userAge");
            editRow(userId,userName,userAge);
        }
    }


    //根据userId批量删除
    function deleteUser(){
        var record = grid.getSelectionModel().getSelection();
        if(record.length==0){
            Ext.MessageBox.show({
                title:"提示",
                msg:"请选择至少一行进行删除！"
            })
            return;
        }else{
            var ids = "";
            for(var i=0;i<record.length; i++){
                ids += record[i].get("userId");
                if(i<record.length-1){
                    ids = ids +","
                }
            }

            $.ajax({
                type : "GET",
                url : doDeleteUrl,
                dataType : "JSON", //返回JSON数据
                cache : false, //不缓存
                async : true,
                timeout : 30000,
                data : {ids:ids},
                success : function(data,textStatus){
                    if(data.deleteflag == "true"){
                        doQuery();
                    }else{
                        alert(data.deleteflag);
                        gridStore.loadPage(1);

                    }
                },
                error:function(){
                    alert("系统错误！！！！")
                }
            });
        }
    }

</script>