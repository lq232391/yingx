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
        $("#userId").jqGrid({
            styleUI: "Bootstrap",//使用bootstrap主题
            url:"${path}/user/queryAll",
            edit:"",
            datatype: "json",//定义服务端返回数据格式
            rowNum:3,//每页显示记录数
            rowList:[2,4,20,30],//每页显示条数
            pager: '#userPage',//让表格展示分页工具栏
            //sortname: 'id',//使用那个字段进行排序
            autowidth:true,//自适应父容器
            height:"auto",
            viewrecords: true,  //显示数据库中总记录数
            colNames:['id','名字', '头像', '手机号','密码','注册时间','状态'],
            colModel:[
                {name:'id', width:55,editable:true},
                {name:'nickName',width:90,editable:true},
                {name:'picImg', width:100,editable:true,
                    formatter:function (value,options,rowObject) {
                        return"<img src='${path}/"+value+"'width='100px'height='100px'>";
                    }
                },
                {name:'phone', width:80, align:"right",editable:true},
                {name:'score', width:80,align:"right",editable:true},//密码
                {name:'createDate', width:150, editable:true},
                {name:"status",width:80,editable:true,
                    formatter:function(value,options,row){
                        if(value==0){
                            return "<button class='btn btn-success' onclick='updateStatus(\""+value+"\",\""+row.id+"\")'>冻结</button>"
                        }else {                        //row
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+value+"\",\""+row.id+"\")'>解冻</button>"
                        }
                    }
                }
            ]
        });
        $("#userId").jqGrid('navGrid','#userPage',{edit:false,add:false,del:false});

    });
    function updateStatus(status,id) {
        $.post("${path}/user/updateStatus",{"status":status=status==1?0:1,"id":id});
        $("#userId").trigger("reloadGrid")//重新加载当前表格 也会向服务器发送新的请求
    }
</script>
<script>
    $(function () {
        //给按钮添加一个单击事件
        $("aliyun").click(function () {
            var phone = $("#phone").val();
            //发送ajax的请求
            $.post("${pageContext.request.contextPath}/user/code",{phone:phone},function(res){
                if (res.status=="200"){
                    alert(res.message);
                }else{
                    alert(res.message);
                }
            },"JSON")
        });
        $("#PoiExport").click(function () {
            $.get("${path}/user/PoiExport",function (data) {
                if(data.message=='success'){
                    alert("导出成功")
                }else{
                    alert("导出失败")
                }
            })
        });

        <%--"${pageContext.request.contextPath}/user/import", data--%>
        $("#PoiImport").click(function () {
            $.get("${path}/user/PoiImport",function (data) {
                if(data.message=='success'){
                    alert("导入成功")
                }else{
                    alert("导入失败")
                }
            })
        });
    });
</script>
<body>

<%--设置面板--%>
<div class="panel panel-info">
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>


    <div>
        <div class="pull-left">

            <div>
                <button class="btn btn-success" id="PoiExport">导出用户信息</button>
                <button class="btn btn-info" id="PoiImport" >导入用户信息</button>
            </div>
        </div>
        <div class="pull-right col-sm-5">
            <form>
                <div class="col-md-2 col-md-offset-6" style="padding: 0px;">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号..." required
                           minlength="11">
                </div>
                <div class="col-md-3 pull-right" style="padding: 0px;">
                    <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                </div>
            </form>
        </div>
    </div>
    <br>
    <div style="margin-top: 20px;">
        <%--表单--%>
        <table id="userId"/>
        <%--分页工具栏--%>
        <div id="userPage"/>
    </div>
</div>
</body>
</html>