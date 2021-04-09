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
            <h1 class="m-0 text-dark">员工</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">用户管理</a></li>
              <li class="breadcrumb-item active">全部用户</li>
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
            <h4 class="modal-title">员工新增</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="addUserForm">
              <div class="card-body">
                <div class="form-group row">
                <label for="username" class="col-sm-2 col-form-label">用户名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="username" id="username" placeholder="username">
                </div>
              </div>
                <div class="form-group row">
                  <label for="workNumber" class="col-sm-2 col-form-label">工号</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="workNumber" id="workNumber" placeholder="C001">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="email" class="col-sm-2 col-form-label">Email</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" placeholder="Email">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="mobile" class="col-sm-2 col-form-label">电话</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="mobile" id="mobile" placeholder="18693515693">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="mobile" class="col-sm-2 col-form-label">启用状态</label>
                  <div class="col-sm-10">
                    <input type="checkbox" name="enableState" checked data-bootstrap-switch data-off-color="danger" value="1" data-on-color="success">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="mobile" class="col-sm-2 col-form-label">性别</label>
                  <div class="col-sm-10">
                    <div class="icheck-primary d-inline">
                      <input type="radio" id="radioPrimary1" name="gender" value="1" checked>
                      <label for="radioPrimary1">
                        男
                      </label>
                    </div>
                    <div class="icheck-primary d-inline">
                      <input type="radio" id="radioPrimary2" name="gender" value="0">
                      <label for="radioPrimary2">
                        女
                      </label>
                    </div>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="departmentName" class="col-sm-2 col-form-label">部门</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="departmentName" id="departmentName" readonly placeholder="部门选择" onclick="$('#treeview').show()">
                    <input type="text" name="departmentId" id="departmentId" hidden>
                    <div id="treeview" style="display: none"></div>

                  </div>
                </div>
              </div>

            </form>

          </div>
          <div class="modal-footer justify-content-between">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" id="addBtn" class="btn btn-primary">添加</button>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="card">
          <div class="card-header">
            <button class="btn btn-navbar" data-toggle="modal" data-target="#modal-lg"><i class="fas fa-file"></i>添加</button>
          <div class="card-tools" >
            <!-- SEARCH FORM -->
            <form>
              <div class="input-group input-group-sm">
              <input class="form-control form-control-sm" type="search" placeholder="Search" aria-label="Search">
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
              <th style="width: 20%">
                工号
              </th>
              <th style="width: 30%">
                用户名
              </th>
              <th>
                电话
              </th>
              <th style="width: 8%" class="text-center">
                启用状态
              </th>
              <th style="width: 8%" class="text-center">
                部门
              </th>
              <th style="width: 20%">
              </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="user">
              <tr>
                <td>
                  #
                </td>
                <td>
                  <a>
                      ${user.workNumber}
                  </a>
                  <br/>
                  <small>
                      ${user.createDate}
                  </small>
                </td>
                <td>
                    ${user.username}
                </td>
                <td class="project_progress">
                    ${user.mobile}
                </td>
                <td class="project-state">
                  <c:if test="${user.enableState == '1'}">
                    <span class="badge badge-success">已启用</span>
                  </c:if>
                  <c:if test="${user.enableState == '0'}">
                    <span class="badge badge-warning">未启用</span>
                  </c:if>
                </td>
                <td>
                    ${user.departmentName}
                </td>
                <td class="project-actions text-right">
                  <a class="btn btn-primary btn-sm" href="#">
                    <i class="fas fa-folder">
                    </i>
                    查看
                  </a>
                  <a class="btn btn-info btn-sm" href="#">
                    <i class="fas fa-pencil-alt">
                    </i>
                    编辑
                  </a>
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
          <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=1&size=${pageInfo.pageSize}">首页</a></li>
          <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
          <c:choose>
            <c:when test="${pageInfo.pages < 6}">
              <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum" >
                <c:if test="${pageNum == pageInfo.pageNum}">
                  <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                </c:if>
                <c:if test="${pageNum != pageInfo.pageNum}">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                </c:if>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <c:forEach begin="${pageInfo.pageNum -3 < 1 ? 1 : pageInfo.pageNum -3 }" end="${pageInfo.pageNum + 3 > pageInfo.pages ? pageInfo.pages : pageInfo.pageNum + 3}" var="pageNum" >
                <c:if test="${pageNum == pageInfo.pageNum}">
                  <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                </c:if>
                <c:if test="${pageNum != pageInfo.pageNum}">
                  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                </c:if>
              </c:forEach>
            </c:otherwise>
          </c:choose>
          <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
          <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/query.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}">尾页</a></li>
        </ul>
      </div>
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
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
<!-- SweetAlert2 -->
<script src="${pageContext.request.contextPath}/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="${pageContext.request.contextPath}/plugins/toastr/toastr.min.js"></script>
<script>
  var jsonTree;
  $(function() {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
    $.ajax({
      type : "post",
      url : "${pageContext.request.contextPath}/department/deparmentTree.do",
      success : function(data, status) {
        if (status == "success") {
          jsonTree = data;
        }
      },
      error : function() {
        toastr.error('Error');
      },
    });
    $('#addBtn').click(function () {
      console.log("进入了addBtn方法")
      $.ajax({
        url:'${pageContext.request.contextPath}/user/add.do',
        data:$('#addUserForm').serialize(),           //序列化表单数据，格式为name=value
        type:'POST',
        dataType:'json',
        success:function (data) {
          console.log(data);
          if(data.success){
            // Toast.fire({
            //   type: 'warning',
            //   title: '保存成功'
            // });
            window.location.href = '${pageContext.request.contextPath}/user/query.do';
          }else{
            Toast.fire({
              type: 'warning',
              title: '保存失败,' + data.message
            })
          }
        },
        error:function () {
          Toast.fire({
            type: 'warning',
            title: '保存Error'
          })
        }

      })
    })
    $('#departmentName').click(function(){
      var options = {
        bootstrap2 : false,
        showTags : true,
        levels : 5,
        showCheckbox : true,
        color: "#428bca",
        expandIcon: "glyphicon glyphicon-stop",
        collapseIcon: "glyphicon glyphicon-unchecked",
        nodeIcon: "glyphicon glyphicon-user",
        data : jsonTree,
        onNodeSelected : function(event, data) {
          $("#departmentName").val(data.text);
          $("#departmentId").val(data.id);
          console.log($("#departmentId").val());
          $("#treeview").hide();
        }
      };
      $('#treeview').treeview(options);
    })
  });




</script>
</body>

</html>
