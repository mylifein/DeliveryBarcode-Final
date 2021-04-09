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
            <h1 class="m-0 text-dark">扫码</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/user/query.do">扫码</a></li>
              <li class="breadcrumb-item active">品保质检</li>
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
      <div class="card">
        <div class="card card-info">
          <div class="card-header">
            <h3 class="card-title">品保质检</h3>
          </div>
        </div>
        <div class="card-body p-0">
          <table class="table table-striped projects">
            <tr>
              <td>
                <div class="form-group row">
                  <label for="barcode" class="col-sm-3 col-form-label">栈板条码</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="barcode" name="barcode">
                  </div>
                </div>
              </td>
              <td>
                <div class="form-group row">
                <div class="col-sm-5">
                  <input type="button" class="btn btn-success" id="passBtn" value="PASS">
                </div>
                  <div class="col-sm-5">
                    <input type="button" class="btn btn-danger" id="ngBtn" value="NG">
                  </div>
                </div>
              </td>
            </tr>
          </table>
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
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
  <%@include file="../footer.jsp"%>
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
  $(function() {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
    $('#ngBtn').click(function () {
      var barcode = $('#barcode').val();
      if(barcode == null || barcode == ""){
        Toast.fire({
          type: 'warning',
          title: '条码号不能为空'
        })
      }else{
      $.ajax({
        url:'${pageContext.request.contextPath}/pallet/ngBarcode.do',
        data:{'barcode':barcode},           //序列化表单数据，格式为name=value
        type:'POST',
        dataType:'json',
        success:function (data) {
          if(data.success){
            Toast.fire({
              type: 'success',
              title: 'NG判定ok'
            });
            $('#barcode').val("");
            $('#barcode').focus();
          }else{
            Toast.fire({
              type: 'warning',
              title: 'NG判定失败,' + data.message
            })
            $('#barcode').val("");
            $('#barcode').focus();
          }
        },
        error:function () {
          Toast.fire({
            type: 'error',
            title: 'NG判定Error，请检查是否登录超时'
          })
        }

      })
    }
    })
    $('#passBtn').click(function () {
      var barcode = $('#barcode').val();
      if(barcode == null || barcode == ""){
        Toast.fire({
          type: 'warning',
          title: '条码号不能为空'
        })
      }else{
        $.ajax({
          url:'${pageContext.request.contextPath}/pallet/passBarcode.do',
          data:{'barcode':barcode},           //序列化表单数据，格式为name=value
          type:'POST',
          dataType:'json',
          success:function (data) {
            if(data.success){
              Toast.fire({
                type: 'success',
                title: 'PASS判定ok'
              });
              $('#barcode').val("");
              $('#barcode').focus();
            }else{
              Toast.fire({
                type: 'warning',
                title: 'PASS判定失败,' + data.message
              })
              $('#barcode').val("");
              $('#barcode').focus();
            }
          },
          error:function () {
            Toast.fire({
              type: 'error',
              title: 'PASS判定Error，请检查是否登录超时'
            })
          }

        })
      }
    })
  });
</script>
</body>
</html>
