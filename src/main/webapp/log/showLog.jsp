<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        //初始化表单属性
        $("#vTable").jqGrid({
            url: "${path}/log/queryByPage",
            datatype: "json",
            rowNum: 5,  //每页展示是条数
            rowList: [5, 5, 10, 20, 30],
            pager: '#vPager',
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            viewrecords: true,
            colNames: ['ID', '名字', '时间', '操作', '状态'],
            colModel: [
                {name: 'id', width: 55},
                {name: 'name', editable: true, width: 90},
                {name: 'time', editable: true, width: 100},
                {name: 'options', editable: true, width: 100, align: "center"},
                {name:"status",width:100,editable:true,}
            ]
        });

        //处理曾删改查操作
        $("#vTable").jqGrid('navGrid', '#vPager',
            {edit: false, add: false, del: false, edittext: "修改", addtext: "添加", deltext: "删除"},
        );
    });
</script>

<%--初始化一个面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading" align="center">
        <h2>日志管理</h2>
    </div>

    <%--选项卡--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">日志详情</a></li>
    </div>

    <%--警告提示框--%>
    <div id="deleteMsg" class="alert alert-danger" style="height: 50px;width: 250px;display: none" align="center">
        <span id="showMsg"/>
    </div>

    <%--初始化表单--%>
    <table id="vTable"/>

    <%--工具栏--%>
    <div id="vPager"/>

</div>