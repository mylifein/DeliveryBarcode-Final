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
                        <h1 class="m-0 text-dark">??????</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">??????</a></li>
                            <li class="breadcrumb-item active">?????????????????????</li>
                        </ol>
                    </div><!-- /.col -->

                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->


        <!-- Main content -->
        <section class="content">
            <%--      <button class="btn btn-navbar" data-toggle="modal" data-target="#modal-lg"><i class="fas fa-file"></i>??????</button>--%>
            <!-- Default box -->
            <form class="form-horizontal" id="queryForm">
                <div class="card">
                    <div class="card card-info">
                        <div class="card card-header">
                            <h3 class="card-title">?????????????????????</h3>
                        </div>
                    </div>
                    <div class="card card-body">
                        <div class="form-group row">
                            <label for="dept" class="col-sm-1 col-form-label">????????????</label>
                            <div class="col-sm-2">
                                <select type="text" class="form-control" name="dept" id="dept" readonly>
                                </select>
                            </div>
                            <label for="prodLine" class="col-sm-1 col-form-label">??????</label>
                            <div class="col-sm-2">
                                <select type="text" class="form-control" name="prodLine" id="prodLine" readonly>
                                </select>
                            </div>
                            <label for="status" class="col-sm-1 col-form-label">???????????????</label>
                            <div class="col-sm-2">
                                <select type="text" class="form-control" name="status" id="status" readonly>
                                    <option value="0">?????????</option>
                                    <option value="1">????????????</option>
                                    <option value="2">?????????</option>
                                    <option value="3">?????????</option>
                                    <option value="">??????</option>
                                </select>
                            </div>
                            <label for="workNo" class="col-sm-1 col-form-label">??????</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="workNo" id="workNo">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="delMatno" class="col-sm-1 col-form-label">????????????</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="delMatno" id="delMatno">
                            </div>
                            <label for="cusMatno" class="col-sm-1 col-form-label">????????????</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="cusMatno" id="cusMatno">
                            </div>
                            <label for="soOrder" class="col-sm-1 col-form-label">????????????</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="soOrder" id="soOrder">
                            </div>
                            <label for="cusPO" class="col-sm-1 col-form-label">??????PO</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="cusPO" id="cusPO">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="createdStartTime" class="col-sm-1 col-form-label">??????????????????</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="createdStartTime"
                                       id="createdStartTime">
                            </div>
                            <label for="createdEndTime" class="col-sm-1 col-form-label">??????????????????</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="createdEndTime" id="createdEndTime">
                            </div>
                            <label for="updatedStartTime" class="col-sm-1 col-form-label">??????????????????</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="updatedStartTime"
                                       id="updatedStartTime">
                            </div>
                            <label for="updatedEndTime" class="col-sm-1 col-form-label">??????????????????</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name="updatedEndTime" id="updatedEndTime">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="packType" class="col-sm-1 col-form-label">????????????</label>
                            <div class="col-sm-2">
                                <select type="text" class="form-control" name="packType" id="packType" readonly>
                                    <option value="0">??????</option>
                                    <option value="1">?????????</option>
                                    <option value="">??????</option>
                                </select>
                            </div>
                        </div>
                        <div class="card-footer">
                            <button type="button" class="btn btn-info float-right" id="queryBtn">??????</button>
                        </div>
                    </div>
                    <div class="card card-body p-0">
                        <table id="displayTable" class="table table-striped projects">
                            <thead>
                            <th>????????????</th>
                            <th>????????????</th>
                            <th>??????ID</th>
                            <th>??????</th>
                            <th>????????????</th>
                            <th>????????????</th>
                            <th>????????????</th>
                            <th>?????????</th>
                            <th>????????????</th>
                            <th>????????????</th>
                            <th>??????PO</th>
                            <th>??????PO??????</th>
                            <th>????????????</th>
                            <th>?????????</th>
                            <th>????????????</th>
                            <th>????????????</th>
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
        $.ajax({
            url: '${pageContext.request.contextPath}/printDept/query.do',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    printDepts = data.data;
                    console.log(printDepts);
                    $('#dept').append('<option value="">?????????????????????</option>');
                    for (let dept of printDepts) {
                        $('#dept').append('<option value="' + dept.deptId + '">' + dept.deptName + '</option>')
                    }
                } else {
                    Toast.fire({
                        type: 'warning',
                        title: '?????????????????????,' + data.message
                    })
                }
            },
            error: function () {
                Toast.fire({
                    type: 'warning',
                    title: '???????????????????????????????????????'
                })
            }

        });
        //??????????????????
        $('#dept').change(function () {
            $('#prodLine').empty();
            if (printDepts != null) {

                for (let dept of printDepts) {
                    if (dept.deptId == $('#dept').val()) {
                        for (let prodLine of dept.produceLines) {
                            $('#prodLine').append('<option value="' + prodLine.prodLineId + '">' + prodLine.prodLineDesc + '</option>')
                        }
                    }
                }

            }
        });

        $('#queryBtn').click(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/report/queryDelivery.do',
                data: $('#queryForm').serialize(),           //?????????????????????????????????name=value
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
                            title: '???????????????,' + data.message
                        })
                    }
                },
                error: function () {
                    Toast.fire({
                        type: 'warning',
                        title: '??????????????????????????????????????????'
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
                    text: '??????excel',
                    exportOptions: {columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13,14,15]}
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
            "columns": [{data: 'cartonNo'}, {data: 'cartonQty'}, {data: 'prodlineId'}, {data: 'cartonStatus'}, {data: 'packType'}, {data: 'delMatno'}, {data: 'cusMatno'}, {data: 'workNo'}, {data: 'woQuantity'}, {data: 'cusName'}, {data: 'cusPo'}, {data: 'poQty'}, {data: 'soOrder'}, {data: 'vehicleNo'},{data: 'createDate'},{data: 'updateDate'}],
            "columnDefs": [
                {
                    "targets": [3], // ???????????????????????????0??????
                    "data": "cartonStatus", // ????????????
                    "render": function (data, type, full) { // ?????????????????????
                        var packType;
                        switch (data) {
                            case '0':
                                packType = "<span class='badge badge-info'>?????????</span>";
                                break;
                            case '1':
                                packType = "<span class='badge badge-primary'>????????????</span>";
                                break;
                            case '2':
                                packType = "<span class='badge badge-secondary'>?????????</span>";
                                break;
                            case '3':
                                packType = "<span class='badge badge-success'>?????????</span>";
                                break;
                        }
                        return packType;
                    }
                },
                {
                    "targets": [4], // ???????????????????????????0??????
                    "data": "packType", // ????????????
                    "render": function (data, type, full) { // ?????????????????????
                        var packType;
                        switch (data) {
                            case '0':
                                packType = "<span class='badge badge-success'>??????</span>";
                                break;
                            case '1':
                                packType = "<span class='badge badge-info'>??????</span>";
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
