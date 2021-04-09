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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootTree/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/v4-shims.min.css">
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
                        <h1 class="m-0 text-dark">条码</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">条码</a></li>
                            <li class="breadcrumb-item active">工单批次管理</li>
                        </ol>
                    </div><!-- /.col -->

                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- /.modal -->

        <div class="modal fade" id="modal-lg"  style="word-break:break-all">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">栈板详细信息</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="addUserForm">
                            <div class="card-body">
                                <div class="form-group row">
                                    <label for="palletNo" class="col-sm-2 col-form-label">栈板编号</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="palletNo" id="palletNo" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="palletQty" class="col-sm-2 col-form-label">栈板包装数量</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="palletQty" id="palletQty" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <table class="table table-striped projects">
                                        <thead>
                                        <tr>
                                            <th style="width: 1%">
                                                #
                                            </th>
                                            <th style="width: 18%">
                                                装箱单号
                                            </th>
                                            <th style="width: 8%">
                                                数量
                                            </th>
                                            <th style="width: 20%">
                                                出货料号
                                            </th>
                                            <th style="width: 8%">
                                                工单号
                                            </th>
                                            <th style="width: 8%">
                                                车牌
                                            </th>
                                            <th style="width: 8%">
                                                状态
                                            </th>
                                            <th style="width: 10%">
                                                生产时间
                                            </th>
                                            <th style="width: 10%">
                                                更新时间
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody id="detailBody">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>

        <!-- /.modal -->



        <!-- Main content -->
        <section class="content">


            <!-- Default box -->
            <div class="card">
                <div class="card-header">
                    <button class="btn btn-navbar" data-toggle="modal" data-target="#modal-lg"><i
                            class="fas fa-file"></i>添加
                    </button>
                    <a class="btn btn-navbar" href="${pageContext.request.contextPath}/pallet/query.do"><i
                            class="fa fa-refresh"></i>刷新
                    </a>
                    <div class="card-tools">
                        <!-- SEARCH FORM -->
                        <form action="${pageContext.request.contextPath}/pallet/query.do">
                            <div class="input-group input-group-sm">
                                <input class="form-control form-control-sm" name="palletNo" type="search" placeholder="Search"
                                       aria-label="Search">
                                <div class="input-group-append">
                                    <button class="btn btn-navbar" type="submit">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped projects">
                        <thead>
                        <tr>
                            <th style="width: 1%">
                                #
                            </th>
                            <th style="width: 15%">
                                栈板号
                            </th>
                            <th style="width: 10%">
                                数量
                            </th>
                            <th style="width: 10%">
                                客户编号
                            </th>
                            <th style="width: 10%">
                                客户名称
                            </th>
                            <th style="width: 10%">
                                车牌号
                            </th>
                            <th style="width: 15%">
                                创建者
                            </th>
                            <th style="width: 15%">
                                修改者
                            </th>
                            <th style="width: 30%">
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="pallet">
                            <tr>
                                <td>
                                    #
                                </td>
                                <td>
                                    <a href="javascript:void(0)" onclick="queryDetail('${pallet.uuid}')">
                                            ${pallet.palletNo}
                                    </a>
                                </td>
                                <td>
                                        ${pallet.palletQty}
                                </td>
                                <td>
                                        ${pallet.cusNo}
                                </td>
                                <td>
                                        ${pallet.cusName}
                                </td>
                                <td>
                                        ${pallet.vehicleNo}
                                </td>
                                <td class="project_progress">
                                        ${pallet.createBy}
                                    <br/>
                                    <small>
                                            ${pallet.createDate}
                                    </small>
                                </td>
                                <td>
                                        ${pallet.updateBy}
                                    <br/>
                                    <small>
                                            ${pallet.updateDate}
                                    </small>
                                </td>
                                <td class="project-actions text-right">
                                    <button class="btn btn-info btn-sm"  onclick="edit('${woBatch.uuid}')">
                                        <i class="fas fa-pencil-alt">
                                        </i>
                                        编辑
                                    </button>
                                    <a class="btn btn-danger btn-sm" href="#">
                                        <i class="fas fa-trash">
                                        </i>
                                        删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
            <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/pallet/query.do?page=1&size=${pageInfo.pageSize}">首页</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/pallet/query.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a>
                    </li>
                    <c:choose>
                        <c:when test="${pageInfo.pages < 6}">
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <c:if test="${pageNum == pageInfo.pageNum}">
                                    <li class="page-item active"><a class="page-link"
                                                                    href="${pageContext.request.contextPath}/pallet/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageNum != pageInfo.pageNum}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/pallet/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="${pageInfo.pageNum -3 < 1 ? 1 : pageInfo.pageNum -3 }"
                                       end="${pageInfo.pageNum + 3 > pageInfo.pages ? pageInfo.pages : pageInfo.pageNum + 3}"
                                       var="pageNum">
                                <c:if test="${pageNum == pageInfo.pageNum}">
                                    <li class="page-item active"><a class="page-link"
                                                                    href="${pageContext.request.contextPath}/pallet/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageNum != pageInfo.pageNum}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/pallet/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/pallet/query.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/pallet/query.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}">尾页</a>
                    </li>
                </ul>
            </div>
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
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- SweetAlert2 -->
<script src="${pageContext.request.contextPath}/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="${pageContext.request.contextPath}/plugins/toastr/toastr.min.js"></script>
<!-- select treeview -->
<script src="${pageContext.request.contextPath}/bootTree/js/bootstrap-treeview.js"></script>
<!-- bs-custom-file-input -->
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<script>
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });

    $(function () {


    });


    function queryDetail(uuid) {
        $.ajax({
            url: '${pageContext.request.contextPath}/pallet/queryDetail.do',
            data: {'uuid': uuid},           //序列化表单数据，格式为name=value
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var pallet = data.data;
                    $('#palletNo').val(pallet.palletNo);
                    $('#palletQty').val(pallet.palletQty);
                    //拼接字符串innerHtml
                    var innerElement = '';
                    $('#detailBody').html('') ;
                    for (let box of pallet.boxes) {
                        innerElement += '<tr><td>#</td><td>' + box.cartonNo + '</td><td>' + box.cartonQty + '</td><td>' + box.delMatno + '</td><td>' + box.workNo + '</td><td>' + box.vehicleNo + '</td>';
                        if(box.cartonStatus == 0){
                            innerElement += '<td><span class="badge badge-info">已装箱</span></td>';
                        }else if(box.cartonStatus == 1){
                            innerElement += '<td><span class="badge badge-primary">已装栈板</span></td>';
                        }else if(box.cartonStatus == 2){
                            innerElement += '<td><span class="badge badge-success">已入库</span></td>';
                        }else{
                            innerElement += '<td><span class="badge badge-warning">已出货</span></td>';
                        }
                        innerElement += '<td>' + box.createBy+ '<br/><small>' + box.createDate+ '</small></td><td>' + box.updateBy+ '<br/><small>' + box.updateDate+ '</small></td></tr>';
                    }
                    $('#detailBody').html(innerElement);
                    $('#modal-lg').modal();

                } else {
                    Toast.fire({
                        type: 'warning',
                        title: '查询错误,' + data.message
                    })
                }
            },
            error: function () {
                Toast.fire({
                    type: 'warning',
                    title: '查询异常'
                })
            }

        })
    }



</script>
</body>
</html>
