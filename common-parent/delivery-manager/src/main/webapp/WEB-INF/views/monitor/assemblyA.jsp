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
    <!-- Theme style -->
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">--%>
    <style type="text/css">
        .progress-bar.progress-bar-green{background-color:#00ff00;}
        .progress-bar.progress-bar-aqua{background-color: #00ca6d;}
        #times{width: 220px;height: 20px;border: 3px solid greenyellow;color: #8e44ad}
    </style>
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../navBar.jsp"/>
    <!-- /.navbar -->
    <!-- Main Sidebar Container -->
    <jsp:include page="../aside.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0 text-dark">电子看板</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">电子看板</a></li>
                            <li class="breadcrumb-item active">组装A线</li>
                        </ol>
                    </div><!-- /.col -->

                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->


        <!-- Main content -->
        <section class="content">
            <%--      <button class="btn btn-navbar" data-toggle="modal" data-target="#modal-lg"><i class="fas fa-file"></i>添加</button>--%>
            <!-- Default box -->
            <form class="form-horizontal" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/user/update.do" method="post">
                <div class="card">
                    <div class="card card-info">
                        <div class="card-header">
                            <h3 class="card-title">组装A线看板</h3>
                        </div>
                    </div>
                    <div class="card-body p-0">
                        <div class="box-header with-border">
                            <h3 class="box-title" style="float:right"><span id="times"></span></h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="table-responsive">
                                <table class="table no-margin">
                                    <thead>
                                    <tr>
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
                    <div class="card-footer">
                    </div>
                </div>
                <!-- /.card -->
            </form>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


    <!-- Control Sidebar  foot jsp include -->
    <!-- /.control-sidebar -->
    <!-- Main Footer -->
    <%@include file="../footer.jsp" %>
</div>
<!-- ./wrapper -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/bootTree/js/jquery-2.1.0.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/bootTree/js/bootstrap-treeview.js"></script>
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
        setInterval("updateMonitors()",1000 * 5);
        $('#shippingBtn').click(function () {
            var boxNo = $('#boxNo').val();
            var vehicleNo = $('#vehicleNo').val();
            if (boxNo == null || boxNo == "" || vehicleNo == null || vehicleNo == "") {
                Toast.fire({
                    type: 'warning',
                    title: '条码/车牌号不能为空'
                })
            } else {
                $.ajax({
                    url: '${pageContext.request.contextPath}/box/shipping.do',
                    data: {'cartonNo': boxNo, 'vehicleNo': vehicleNo},           //序列化表单数据，格式为name=value
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            Toast.fire({
                                type: 'success',
                                title: '出货成功'
                            });
                            $('#boxNo').val("");
                            $('#boxNo').focus();
                        } else {
                            Toast.fire({
                                type: 'warning',
                                title: '出货失败,' + data.message
                            })
                            $('#boxNo').val("");
                            $('#boxNo').focus();
                        }
                    },
                    error: function () {
                        Toast.fire({
                            type: 'error',
                            title: '出货Error'
                        })
                    }

                })
            }
        })
    });

    function getCurrentTimes(){
        // 1.获取当前时间
        var date = new Date();
        // 2.格式化本地时间格式
        var date1 = date.toLocaleString();
        // 3.获取times <span>
        $("#times").html(date1);
    }

    function updateMonitors(){
        $.ajax({
            url: '${pageContext.request.contextPath}/box/queryMonitor.do',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var monitorInfos = data.data;
                    //拼接字符串innerHtml
                    var innerElement = '';
                    $('#showDetails').html('') ;
                    for (let monitorInfo of monitorInfos) {
                        innerElement += '<tr><td>' + monitorInfo.workNo + '</td><td>' + monitorInfo.delMatno + '</td><td><span class="label label-success">' + monitorInfo.woQuantity + '</span></td><td>' + monitorInfo.prodlineDesc + '</td><td>'+ monitorInfo.totayTotalQty + '</td><td>' + monitorInfo.totalQty + '</td>';
                        var prodRate = Math.round(monitorInfo.totalQty/monitorInfo.woQuantity *10000/100.00);
                        var status = monitorInfo.totalQty >= monitorInfo.woQuantity ? "<td><span class='badge badge-info'>已完成</span>":"<td><span class='badge badge-info'>生产中</span>";
                        innerElement += '<td><span class="badge badge-info">' + prodRate+ '%</span></td>';
                        innerElement += status;
                        innerElement += '<td><div class="progress-group"> <span class="progress-text">' + monitorInfo.delMatno + '</span><span class="progress-number" style="float: right"><b>' + monitorInfo.totalQty + '</b>/' + monitorInfo.woQuantity + '</span><div class="progress sm"><div class="progress-bar progress-bar-aqua" style="width:' + prodRate+ '%"></div></div></div></td></tr>';
                    }
                    console.log(innerElement);
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
</script>
</body>
</html>
