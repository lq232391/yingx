<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        //初始化表单属性
        $("#vTable").jqGrid({
            url: "${path}/video/queryByPage",
            editurl:"${path}/video/edit",
            datatype: "json",
            rowNum: 3,  //每页展示是条数
            rowList: [3, 5, 10, 20, 30],
            pager: '#vPager',
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            viewrecords: true,
            colNames: ['ID', '标题', '描述', '视频', '上传时间', '用户', '类别', '分组',"状态"],
            colModel: [
                {name: 'id', width: 55},
                {name: 'title', editable: true, width: 90},
                {name: 'brief', editable: true, width: 100},
                {
                    name: 'videoPath', editable: true, width: 100, align: "center", edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<video id='media' src='"+cellvalue+"' controls width='100%' heigt='100%' poster='"+rowObject.coverPath+"'/>";
                    }
                },
                {name: 'uploadTime', width: 80, align: "right"},
                {name: 'userId', width: 80, align: "right"},
                {name: 'categoryId', width: 80, align: "center"},
                {name: 'groupId', width: 150, sortable: false},
                {name:"status",width:100,editable:true,
                    formatter:function(value,options,row){
                        if(value==0){
                            return "<button class='btn btn-info' onclick='updateStatus(\""+value+"\",\""+row.id+"\")'>不正常</button>"
                        }else {                        //row
                            return "<button class='btn btn-success' onclick='updateStatus(\""+value+"\",\""+row.id+"\")'>正常</button>"
                        }
                    }}
            ]
        });

        //处理曾删改查操作
        $("#vTable").jqGrid('navGrid', '#vPager',
            {edit: true, add: true, del: true, edittext: "修改", addtext: "添加", deltext: "删除"},
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#photo").attr("disabled", true);
                }
            },
            {
                closeAfterAdd: true, //关闭添加的对话框
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        fileElementId: "photo",
                        url: "${path}/video/ipload",
                        type: 'post',
                        data: {id: data.responseText},
                        success: function () {
                            $("#vTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },
            {
                closeAfterDel: true,
            }
        );
    });
</script>

<%--初始化一个面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading" align="center">
        <h2>视频管理</h2>
    </div>

    <%--选项卡--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">视频信息</a></li>
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