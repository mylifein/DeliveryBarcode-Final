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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootTree/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/v4-shims.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
    <!-- Buttons -->
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.1/css/buttons.dataTables.min.css">
    <!-- Toastr -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/toastr/toastr.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
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
                        <h1 class="m-0 text-dark">报表</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">报表</a></li>
                            <li class="breadcrumb-item active">工单首件质检报表</li>
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
            <form class="form-horizontal" id="queryForm">
                <div class="card">
                    <div class="card card-info">
                        <div class="card card-header">
                            <h3 class="card-title">工单首件质检报表</h3>
                        </div>
                    </div>
                    <div class="card card-body">
                        <div class="form-group row">
                            <label for="deptId" class="col-sm-1 col-form-label">生产部门Id</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="deptId" id="deptId">
                            </div>
                            <label for="deptId" class="col-sm-1 col-form-label">生产部门</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="deptCode" id="deptCode">
                            </div>
                            <label for="status" class="col-sm-1 col-form-label">质检状态</label>
                            <div class="col-sm-2">
                                <select type="text" class="form-control" name="status" id="status" readonly>
                                    <option value="">全部</option>
                                    <option value="0">未开始</option>
                                    <option value="1">质检中</option>
                                    <option value="2">质检ok</option>
                                    <option value="3">质检NG</option>
                                </select>
                            </div>
                            <label for="woNo" class="col-sm-1 col-form-label">工单：</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="woNo" id="woNo">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="delMatno" class="col-sm-1 col-form-label">出货料号</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="delMatno" id="delMatno">
                            </div>
                            <label for="modelNo" class="col-sm-1 col-form-label">模号</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="modelNo" id="modelNo">
                            </div>
                            <label for="deliverMan" class="col-sm-1 col-form-label">送检人</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="deliverMan" id="deliverMan">
                            </div>
                            <label for="checkTimes" class="col-sm-1 col-form-label">工单质检次数：</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="checkTimes" id="checkTimes">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="startCheckTime" class="col-sm-1 col-form-label">开始日期</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="startCheckTime"
                                       id="startCheckTime">
                            </div>
                            <label for="endCheckTime" class="col-sm-1 col-form-label">结束日期</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="endCheckTime" id="endCheckTime">
                            </div>
                        </div>
                        <div class="card-footer">
                            <button type="button" class="btn btn-info float-right" id="queryBtn">查询</button>
                        </div>
                    </div>
                    <div class="card card-body p-0">
                        <table id="displayTable" class="table table-striped projects">
                            <thead>
                            <th>日期</th>
                            <th>送检单位</th>
                            <th>工单号</th>
                            <th>质检编号</th>
                            <th>质检序号</th>
                            <th>工单数量</th>
                            <th>模号</th>
                            <th>料号</th>
                            <th>送件人</th>
                            <th>送检时间</th>
                            <th>检验开始时间</th>
                            <th>检验结束时间</th>
                            <th>检验总时(分钟)</th>
                            <th>检验人</th>
                            <th>判定</th>
                            <th>检验次数</th>
                            <th>不良问题描述</th>
                            </thead>
                        </table>
                    </div>
                    <!-- /.card-body -->
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
<!-- SweetAlert2 -->
<script src="${pageContext.request.contextPath}/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="${pageContext.request.contextPath}/plugins/toastr/toastr.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
<!-- Buttons -->
<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    var printDepts;
    var $displayTable = null;
    $(function () {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000
        });

        $('#queryBtn').click(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/report/queryCheck.do',
                data: $('#queryForm').serialize(),           //序列化表单数据，格式为name=value
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        if ($displayTable == null) {
                            $displayTable = initTable(data.data);
                        } else {
                            $('#displayTable').dataTable().fnDestroy();
                            $displayTable = initTable(data.data);

                        }

                    } else {
                        Toast.fire({
                            type: 'warning',
                            title: '查询失败：,' + data.message
                        })
                    }
                },
                error: function () {
                    Toast.fire({
                        type: 'warning',
                        title: '查询错误，请检查是否登录超时'
                    })
                }

            })
        })
    });

    function initTable(data) {
        return $('#displayTable').dataTable({
            "dom": 'Bfrtip',
            "buttons": [
                {
                    extend: 'excelHtml5',
                    text: '导出excel',
                    exportOptions: {columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13,14,15,16]}
                }
            ],
            "paging": true,
            "bLengthChange": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "info": true,
            "autoWidth": false,
            "data": data,
            "columns": [{data: 'createDate'}, {data: 'deptCode'}, {data: 'woNo'}, {data: 'qualityNo'},{data: 'checkNum'},{data: 'woQty'}, {data: 'modelNo'}, {data: 'delMatno'}, {data: 'deliverMan'}, {data: 'deiliveryTime'}, {data: 'startTime'}, {data: 'endTime'}, {data: 'duringTime'}, {data: 'checkMan'}, {data: 'status'}, {data: 'checkTimes'},{data: 'remark'}],
            "columnDefs": [
                {
                    "targets": [14], // 目标列位置，下标从0开始
                    "data": "status", // 数据列名
                    "render": function (data, type, full) { // 返回自定义内容
                        var packType;
                        switch (data) {
                            case '0':
                                packType = "<span class='badge badge-info'>未开始</span>";
                                break;
                            case '1':
                                packType = "<span class='badge badge-primary'>检验中</span>";
                                break;
                            case '2':
                                packType = "<span class='badge badge-secondary'>检验Ok</span>";
                                break;
                            case '3':
                                packType = "<span class='badge badge-success'>检验NG</span>";
                                break;
                        }
                        return packType;
                    }
                }]
        })
    }

</script>
</body>
</html>
