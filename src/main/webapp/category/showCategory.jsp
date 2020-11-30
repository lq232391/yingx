<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学app后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
</head>
<script>
    $(function(){
        pageInit();
    });

    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/category/queryAllcategory",
            datatype : "json",
            rowNum : 2,
            rowList : [ 2, 10, 20, 30 ],
            pager : '#catePage',
            sortname : 'id',
            viewrecords : true,//是否显示总记录数
            styleUI:"Bootstrap",//以bootstrap风格展示
            autowidth:true,//自适应父容器
            height:"auto",//居中
            editurl:"${path}/category/edit",
            colNames : [ 'id', '名称', '级别', '父类别id'],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cateName',index : 'invdate',width : 90,editable:true},
                {name : 'levels',index : 'name',width : 100},
                {name : 'parentId',index : 'amount',width : 80,align : "right"}
            ],
            subGrid : true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {edit : true,add : true,del : true},
            {closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,},  //修改
            {closeAfterAdd: true,
                reloadAfterSubmit: true}, //添加
            {closeAfterDelete: true,
                reloadAfterSubmit: true,
                afterSubmit:function (response) {
                    if (response.responseJSON.status==200){
                        alert(response.responseJSON.message);
                    }
                    return "true";
                }
            },
                //删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
            //删除
        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId){

        var subgridTableTd= subgridId + "Table";
        var pagerId= subgridId+"Page";


        $("#"+subgridId).html("" +
            "<table id='"+subgridTableTd+"' />" +
            "<div id='"+pagerId+"' />"
        );

        $("#" + subgridTableTd).jqGrid({
            url : "${path}/category/queryTwoCategory?id=" + rowId,
            datatype : "json",
            rowNum : 2,
            pager : "#"+pagerId,
            sortname : 'num',
            sortorder : "asc",
            styleUI:"Bootstrap",
            viewrecords : true,//是否显示总记录数
            autowidth:true,
            editurl:"${path}/category/edit",
            height:"auto",
            colNames : [ 'id', '名称', '级别', '父类级别'],
            colModel : [
                {name : "id",  index : "num",width : 80,key : true},
                {name : "cateName",index : "item",  width : 130,editable: true},
                {name : "levels",index : "qty",width : 70,align : "right"},
                {name : "parentId",editable: true,index : "unit",width : 70,align : "right",edittype:'select',
                    editoptions:{dataUrl:"${path}/category/queryAll"}
                    },
            ]
        });
        $("#" + subgridTableTd).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true},
            {closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,},  //修改
            {closeAfterAdd: true,
                reloadAfterSubmit: true}, //添加
            {closeAfterDelete: true,//删除
                reloadAfterSubmit: true,
            },//删除之后触发function,接受删除返回的提示信息，在页面作展示
        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />
        <span id="msgDiv"></span>
</div>

<%--
    删除要有提示信息

--%>
