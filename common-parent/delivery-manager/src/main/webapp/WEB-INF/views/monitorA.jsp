<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>CHENBRO | Barcode</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/barcode.ico" type="image/x-icon">
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootTree/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/v4-shims.min.css">
    <!-- Toastr -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/toastr/toastr.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    <style type="text/css">
        .progress-bar.progress-bar-green{background-color:#00ff00;}
        .progress-bar.progress-bar-aqua{background-color: #00ca6d;}
        #times{width: 220px;height: 20px;border: 3px solid greenyellow;color: #ffffff}
    </style>
</head>
<body style="width: 100%">
<div class="wrapper" style="width: 100%">
    <!-- Content Wrapper. Contains page content -->
    <div>
        <!-- Main content -->
        <section class="content" style="width: 100%">
            <%--      <button class="btn btn-navbar" data-toggle="modal" data-target="#modal-lg"><i class="fas fa-file"></i>添加</button>--%>
            <!-- Default box -->
                <div class="card">
                    <div class="card card-info">
                        <div class="card-header">
                            <h6 class="card-title">生产看板-组装A线</h6>
                            <h6  style="float:right;"><span id="times" ></span></h6>
                        </div>
                    </div>
                    <div class="card-body p-0" >
                        <div class="box-body">
                            <div class="table-responsive small">
                                <table class="table no-margin">
                                    <thead>
                                    <tr class="small">
                                        <th>工单</th>
                                        <th>出货料号</th>
                                        <th>工单数量</th>
                                        <th>线别</th>
                                        <th>今日实际生产数量</th>
                                        <th>累计生产数量</th>
                                        <th>工单达成率</th>
                                        <th>状态</th>
                                        <th>生产进度</th>
                                    </tr>
                                    </thead>
                                    <tbody id="showDetails">
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <hr style="background-color: red;height:2px"/>
                    <div class="card-body p-0" >
                        <div class="box-header with-border">
                            <h6 class="box-title" style="float:right"><span style="color: orangered">最近7天未结工单</span></h6>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="table-responsive small">
                                <table class="table no-margin">
                                    <thead>
                                    <tr class="small">
                                        <th>工单</th>
                                        <th>出货料号</th>
                                        <th>工单数量</th>
                                        <th>线别</th>
                                        <th>累计生产数量</th>
                                        <th>工单达成率</th>
                                        <th>生产进度</th>
                                        <th>生产时间</th>
                                    </tr>
                                    </thead>
                                    <tbody id="showUncompleted">
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.box-body -->
                    </div>
                </div>
                <!-- /.card -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


    <!-- Control Sidebar  foot jsp include -->
    <!-- /.control-sidebar -->
    <!-- Main Footer -->

</div>
<!-- ./wrapper -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/bootTree/js/jquery-2.1.0.min.js"></script>--%>
<!-- SweetAlert2 -->
<script src="${pageContext.request.contextPath}/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="${pageContext.request.contextPath}/plugins/toastr/toastr.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    $(function () {
        setInterval("getCurrentTimes()",1000);
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000
        });
        setInterval(function () {
            updateMonitors();
            updateUncompleted();
        },1000 * 5);
        function updateMonitors(){
            $.ajax({
                url: '${pageContext.request.contextPath}/box/queryMonitor.do',
                type: 'GET',
                data:{"prodLineId":"PL001"},
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        var monitorInfos = data.data;
                        //拼接字符串innerHtml
                        var innerElement = '';
                        $('#showDetails').html('') ;
                        for (let monitorInfo of monitorInfos) {
                            innerElement += '<tr><td>' + monitorInfo.workNo + '</td><td>' + monitorInfo.delMatno + '</td><td><span class="label label-success">' + monitorInfo.woQuantity + '</span></td><td>' + monitorInfo.prodlineDesc + '</td><td>' + monitorInfo.totayTotalQty + '</td><td>' + monitorInfo.totalQty + '</td>';
                            var prodRate = Math.floor(monitorInfo.totalQty/monitorInfo.woQuantity *10000/100.00);
                            var status = monitorInfo.totalQty - monitorInfo.woQuantity >= 0 ? "<td><span class='badge badge-success'>已完成</span>":"<td><span class='badge badge-info'>生产中</span>";
                            innerElement += '<td><span class="badge badge-info">' + prodRate+ '%</span></td>';
                            innerElement += status;
                            innerElement += '<td><div class="progress-group"> <span class="progress-text">' + monitorInfo.delMatno + '</span><span class="progress-number" style="float: right"><b>' + monitorInfo.totalQty + '</b>/' + monitorInfo.woQuantity + '</span><div class="progress sm"><div class="progress-bar progress-bar-aqua" style="width:' + prodRate+ '%"></div></div></div></td></tr>';
                        }
                        $("#showDetails").html(innerElement);
                    } else {
                        Toast.fire({
                            type: 'warning',
                            title: '查询电子看板信息异常,' + data.message
                        })
                    }
                },
                error: function () {
                    Toast.fire({
                        type: 'error',
                        title: '查询电子看板信息Error'
                    })
                }

            })
        }

        function updateUncompleted(){
            $.ajax({
                url: '${pageContext.request.contextPath}/box/queryMonitorUncompleted.do',
                type: 'GET',
                data:{"prodLineId":"PL001"},
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        var monitorInfos = data.data;
                        //拼接字符串innerHtml
                        var innerElement = '';
                        $('#showUncompleted').html('') ;
                        for (let monitorInfo of monitorInfos) {
                            innerElement += '<tr><td>' + monitorInfo.workNo + '</td><td>' + monitorInfo.delMatno + '</td><td><span class="label label-success">' + monitorInfo.woQuantity + '</span></td><td>' + monitorInfo.prodlineDesc + '</td><td>' + monitorInfo.totalQty + '</td>';
                            var prodRate = Math.floor(monitorInfo.totalQty/monitorInfo.woQuantity *10000/100.00);
                            innerElement += '<td><span class="badge badge-info">' + prodRate+ '%</span></td>';
                            innerElement += '<td><div class="progress-group"> <span class="progress-text">' + monitorInfo.delMatno + '</span><span class="progress-number" style="float: right"><b>' + monitorInfo.totalQty + '</b>/' + monitorInfo.woQuantity + '</span><div class="progress sm"><div class="progress-bar progress-bar-aqua" style="width:' + prodRate+ '%"></div></div></div></td>';
                            innerElement += '<td>'+ monitorInfo.createTime + '</td></tr>'
                        }
                        $("#showUncompleted").html(innerElement);
                    } else {
                        Toast.fire({
                            type: 'warning',
                            title: '查询电子看板信息异常,' + data.message
                        })
                    }
                },
                error: function () {
                    Toast.fire({
                        type: 'error',
                        title: '查询电子看板信息Error'
                    })
                }

            })
        }
    });

    function getCurrentTimes(){
        // 1.获取当前时间
        var date = new Date();
        // 2.格式化本地时间格式
        var date1 = date.toLocaleString();
        // 3.获取times <span>
        $("#times").html(date1);
    }


</script>
</body>
</html>
