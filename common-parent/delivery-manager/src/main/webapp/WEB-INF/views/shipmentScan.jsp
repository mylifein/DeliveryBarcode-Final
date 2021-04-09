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
                            <h6 class="card-title">已出货统计</h6>
                            <h6  style="float:right;"><span id="times" ></span></h6>
                        </div>
                    </div>
                    <div class="card-body p-0" >
                        <div class="box-body">
                            <div class="table-responsive">
                                <table class="table no-margin small">
                                    <thead>
                                    <tr class="small">
                                        <th>部门</th>
                                        <th>线别</th>
                                        <th>出货料号</th>
                                        <th>箱数</th>
                                        <th>总数量</th>
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
        },1000 * 5);
        function updateMonitors(){
            $.ajax({
                url: '${pageContext.request.contextPath}/box/packingBoxes.do',
                type: 'GET',
                data:{},
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        var pallets = data.data;
                        //拼接字符串innerHtml
                        var innerElement = '';
                        $('#showDetails').html('') ;
                        for (let pallet of pallets) {
                            innerElement += '<tr><td>' + pallet.deptName + '</td><td>' + pallet.prodlineDesc + '</td><td><span class="label label-success">' + pallet.delMatno + '</span></td><td>' + pallet.boxesQty + '</td><td>' + pallet.totalQty + '</td>';
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
