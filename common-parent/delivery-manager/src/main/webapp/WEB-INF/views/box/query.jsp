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
            <h1 class="m-0 text-dark">成品条码</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">条码</a></li>
              <li class="breadcrumb-item active">装箱单进度</li>
            </ol>
          </div><!-- /.col -->

        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- /.modal -->

    <div class="modal fade" id="modal-lg">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">装箱单详细信息</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="addUserForm">
              <div class="card-body">
                <div class="form-group row">
                  <label for="boxNo" class="col-sm-1 col-form-label">箱号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="boxNo" id="boxNo" readonly>
                  </div>
                  <label for="cartonQty" class="col-sm-2 col-form-label">装箱数量</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="cartonQty" id="cartonQty" readonly>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="workNo" class="col-sm-1 col-form-label">工单号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="workNo" id="workNo" readonly>
                  </div>
                  <label for="cusPo" class="col-sm-2 col-form-label">客戶PO</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="cusPo" id="cusPo" readonly>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="cusName" class="col-sm-1 col-form-label">客戶名稱</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="cusName" id="cusName" readonly>
                  </div>
                  <label for="delMatno" class="col-sm-2 col-form-label">出貨料號</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="delMatno" id="delMatno" readonly>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="soOrder" class="col-sm-1 col-form-label">销货单号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="soOrder" id="soOrder" readonly>
                  </div>
                  <label for="vehicleNo" class="col-sm-2 col-form-label">车牌号</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="vehicleNo" id="vehicleNo" readonly>
                  </div>
                </div>
                <div class="form-group row">
                  <table class="table table-striped projects">
                    <thead>
                    <tr>
                      <th style="width: 10%">
                        CT号码
                      </th>
                      <th style="width: 15%">
                        编码规则号
                      </th>
                      <th style="width: 12%">
                        创建者
                      </th>
                      <th style="width: 12%">
                        修改者
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
      <div class="container-fluid">
        <!-- Info boxes -->
        <div class="row">
          <div class="col-12 col-sm-6 col-md-3">
            <div class="info-box">
              <span class="info-box-icon bg-info elevation-1"><i class="fas fa-cog"></i></span>

              <div class="info-box-content">
                <span class="info-box-text">装箱数量</span>
                <span class="info-box-number">
                ${pickedQty}
                </span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
          </div>
          <!-- /.col -->
          <div class="col-12 col-sm-6 col-md-3">
            <div class="info-box mb-3">
              <span class="info-box-icon bg-danger elevation-1"><i class="fas fa-thumbs-up"></i></span>

              <div class="info-box-content">
                <span class="info-box-text">装栈板数量</span>
                <span class="info-box-number">${packedQty}</span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
          </div>
          <!-- /.col -->

          <!-- fix for small devices only -->
          <div class="clearfix hidden-md-up"></div>

          <div class="col-12 col-sm-6 col-md-3">
            <div class="info-box mb-3">
              <span class="info-box-icon bg-success elevation-1"><i class="fas fa-shopping-cart"></i></span>

              <div class="info-box-content">
                <span class="info-box-text">入库数量</span>
                <span class="info-box-number">${receivedQty}</span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
          </div>
          <!-- /.col -->
          <div class="col-12 col-sm-6 col-md-3">
            <div class="info-box mb-3">
              <span class="info-box-icon bg-warning elevation-1"><i class="fas fa-users"></i></span>

              <div class="info-box-content">
                <span class="info-box-text">出库数量</span>
                <span class="info-box-number">${shippedQty}</span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->

        <!-- /.row -->

        <!-- Main row -->
        <div class="row">
          <!-- Left col -->
          <div class="col-md-8">
            <!-- MAP & BOX PANE -->
            <!-- /.card -->
            <div class="row">
              <div class="col-md-6">
                <!-- DIRECT CHAT -->
                <!--/.direct-chat -->
              </div>
              <!-- /.col -->

              <div class="col-md-6">
                <!-- USERS LIST -->
                <!--/.card -->
              </div>
              <!-- /.col -->
            </div>
            <!-- /.row -->

            <!-- TABLE: LATEST ORDERS -->
            <div class="card">
              <div class="card-header border-transparent">
                <div class="card-title">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <a class="btn btn-navbar" href="${pageContext.request.contextPath}/box/query.do">
                      <i class="fa fa-refresh"></i> 刷新
                    </a>
                  </div>
                </div>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove">
                    <i class="fas fa-times"></i>
                  </button>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body p-0">
                <div class="table-responsive">
                  <table class="table m-0">
                    <thead>
                    <tr>
                      <th>装箱单号</th>
                      <th>工单号</th>
                      <th>状态</th>
                      <th>包装类型</th>
                      <th>数量</th>
                      <th>创建时间</th>
                      <th>更新时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="box">
                      <tr>
                      <td><a href="javascript:void(0)" onclick="queryDetail('${box.uuid}')">${box.cartonNo}</a></td>
                      <td>${box.workNo}</td>
                      <c:if test="${box.cartonStatus == 0}">
                      <td><span class="badge badge-info">已装箱</span></td>
                      </c:if>
                      <c:if test="${box.cartonStatus == 1}">
                        <td><span class="badge badge-primary">已装栈板</span></td>
                      </c:if>
                      <c:if test="${box.cartonStatus == 2}">
                        <td><span class="badge badge-secondary">已入库</span></td>
                      </c:if>
                      <c:if test="${box.cartonStatus == 3}">
                        <td><span class="badge badge-success">已出货</span></td>
                      </c:if>
                        <c:if test="${box.packType == 0}">
                          <td><span class="badge badge-info">整机</span></td>
                        </c:if>
                        <c:if test="${box.packType == 1}">
                          <td><span class="badge badge-success">单出件</span></td>
                        </c:if>
                      <td>
                        <div class="sparkbar" data-color="#00a65a" data-height="20">${box.cartonQty}</div>
                      </td>
                        <td>
                          <small>${box.createDate}</small>
                        </td>
                        <td>
                          <small>${box.updateDate}</small>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
                <!-- /.table-responsive -->
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=1&size=${pageInfo.pageSize}">首页</a></li>
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
                  <c:choose>
                    <c:when test="${pageInfo.pages < 6}">
                      <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum" >
                        <c:if test="${pageNum == pageInfo.pageNum}">
                          <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                        </c:if>
                        <c:if test="${pageNum != pageInfo.pageNum}">
                          <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                        </c:if>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <c:forEach begin="${pageInfo.pageNum -3 < 1 ? 1 : pageInfo.pageNum -3 }" end="${pageInfo.pageNum + 3 > pageInfo.pages ? pageInfo.pages : pageInfo.pageNum + 3}" var="pageNum" >
                        <c:if test="${pageNum == pageInfo.pageNum}">
                          <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                        </c:if>
                        <c:if test="${pageNum != pageInfo.pageNum}">
                          <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                        </c:if>
                      </c:forEach>
                    </c:otherwise>
                  </c:choose>
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/box/query.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}">尾页</a></li>
                </ul>
              </div>
              <!-- /.card-footer -->
              <!-- /.card-footer -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->

          <div class="col-md-4">
            <!-- Info Boxes Style 2 -->
            <div class="info-box mb-3 bg-warning">
              <span class="info-box-icon"><i class="fas fa-tag"></i></span>

              <div class="info-box-content">
                <span class="info-box-text">Inventory</span>
                <span class="info-box-number"></span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
            <div class="info-box mb-3 bg-success">
              <span class="info-box-icon"><i class="far fa-heart"></i></span>

              <div class="info-box-content">
                <span class="info-box-text"></span>
                <span class="info-box-number"></span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
            <div class="info-box mb-3 bg-danger">
              <span class="info-box-icon"><i class="fas fa-cloud-download-alt"></i></span>

              <div class="info-box-content">
                <span class="info-box-text"></span>
                <span class="info-box-number"></span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
            <div class="info-box mb-3 bg-info">
              <span class="info-box-icon"><i class="far fa-comment"></i></span>

              <div class="info-box-content">
                <span class="info-box-text"></span>
                <span class="info-box-number"></span>
              </div>
              <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->

            <!-- /.card -->

            <!-- PRODUCT LIST -->
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!--/. container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


  <!-- Control Sidebar  foot jsp include -->
  <!-- /.control-sidebar -->
  <!-- Main Footer -->
  <%@include file="../footer.jsp"%>
</div>
<!-- ./wrapper -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/bootTree/js/jquery-2.1.0.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/bootTree/js/bootstrap-treeview.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- SweetAlert2 -->
<script src="${pageContext.request.contextPath}/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="${pageContext.request.contextPath}/plugins/toastr/toastr.min.js"></script>
<script>
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000
  });
  var jsonTree;
  $(function () {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
    $.ajax({
      type: "post",
      url: "${pageContext.request.contextPath}/department/deparmentTree.do",
      success: function (data, status) {
        if (status == "success") {
          jsonTree = data;
        }
      },
      error: function () {
        toastr.error('Error');
      },
    });
    $('#addBtn').click(function () {
      console.log("进入了addBtn方法")
      $.ajax({
        url: '${pageContext.request.contextPath}/role/add.do',
        data: $('#addUserForm').serialize(),           //序列化表单数据，格式为name=value
        type: 'POST',
        dataType: 'json',
        success: function (data) {
          console.log(data);
          if (data.success) {
            window.location.href = '${pageContext.request.contextPath}/role/query.do';
          } else {
            Toast.fire({
              type: 'warning',
              title: '保存失败,' + data.message
            })
          }
        },
        error: function () {
          Toast.fire({
            type: 'warning',
            title: '保存Error'
          })
        }

      })
    })

  });

  function edit(uuid) {
    $.ajax({
      url: '${pageContext.request.contextPath}/role/find.do',
      data: {'uuid': uuid},           //序列化表单数据，格式为name=value
      type: 'GET',
      dataType: 'json',
      success: function (data) {
        if (data.success) {

          console.log("success");
        } else {
          Toast.fire({
            type: 'warning',
            title: '保存失败,' + data.message
          })
        }
      },
      error: function () {
        Toast.fire({
          type: 'warning',
          title: '保存Error'
        })
      }

    })
  }


  function queryDetail(uuid) {
    $.ajax({
      url: '${pageContext.request.contextPath}/box/queryDetail.do',
      data: {'uuid': uuid},           //序列化表单数据，格式为name=value
      type: 'GET',
      dataType: 'json',
      success: function (data) {
        if (data.success) {
          var boxInfo = data.data;
          $('#boxNo').val(boxInfo.cartonNo);
          $('#cartonQty').val(boxInfo.cartonQty);
          $('#workNo').val(boxInfo.workNo);
          $('#cusPo').val(boxInfo.cusPo);
          $('#cusName').val(boxInfo.cusName);
          $('#delMatno').val(boxInfo.delMatno);
          $('#soOrder').val(boxInfo.soOrder);
          $('#vehicleNo').val(boxInfo.vehicleNo);
          //拼接字符串innerHtml
          var innerElement = '';
          $('#detailBody').html('') ;
          for (let ctcode of boxInfo.ctcodes) {
            innerElement += '<tr><td>' + ctcode.ctNumber + '</td><td>' + ctcode.ruleNo + '</td><td>'  + ctcode.createBy+ '<br/><small>' + ctcode.createDate+ '</small></td><td>' + ctcode.updateBy+ '<br/><small>' + ctcode.updateDate+ '</small></td></tr>'
        }
          $('#detailBody').html(innerElement);
          $('#modal-lg').modal();

        } else {
          Toast.fire({
            type: 'warning',
            title: '保存失败,' + data.message
          })
        }
      },
      error: function () {
        Toast.fire({
          type: 'warning',
          title: '操作Error'
        })
      }

    })
  }


</script>
</body>
</html>
