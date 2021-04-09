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
                        <h1 class="m-0 text-dark">打印程序管理</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">打印程序管理</a></li>
                            <li class="breadcrumb-item active">编码规则管理</li>
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
                        <h4 class="modal-title">编码规则详细信息</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="addUserForm">
                            <div class="card-body">
                                <div class="form-group row">
                                    <label for="ruleNo" class="col-sm-2 col-form-label">規則编号</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="ruleNo" id="ruleNo" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="ruleDesc" class="col-sm-2 col-form-label">規則描述</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="ruleDesc" id="ruleDesc" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="ruleCreateBy" class="col-sm-2 col-form-label">创建者</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="ruleCreateBy" id="ruleCreateBy"
                                               readonly>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="ruleCreateTime"
                                               id="ruleCreateTime" readonly>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="ruleUpdateBy" class="col-sm-2 col-form-label">修改者</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="ruleUpdateBy" id="ruleUpdateBy"
                                               readonly>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="ruleUpdateTime"
                                               id="ruleUpdateTime" readonly>
                                    </div>
                                </div>
                                <!-- modal table show -->
                                <div class="form-group row">
                                    <table class="table table-striped projects">
                                        <thead>
                                        <tr>
                                            <th style="width: 10%">
                                                #
                                            </th>
                                            <th style="width: 20%">
                                                類型编号
                                            </th>
                                            <th style="width: 15%">
                                                類型描述
                                            </th>
                                            <th style="width: 15%">
                                                規則值
                                            </th>
                                            <th style="width: 15%">
                                                規則長度
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

                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>

        <!-- /.modal -->

        <div class="modal fade" id="modal-lg2">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">修改规则详细信息</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                            <div class="card-body">
                                <div class="card-body">
                                    <form class="form-horizontal" id="updateCodeRuleForm">
                                    <div class="form-group row">
                                        <label for="upRuleNo" class="col-sm-2 col-form-label">規則编号</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="uuid" id="upUuid" hidden>
                                            <input type="text" class="form-control" name="ruleNo" id="upRuleNo"
                                                   readonly>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="upRuleDesc" class="col-sm-2 col-form-label">規則描述</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="ruleDesc" id="upRuleDesc">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="upRuleCreateBy" class="col-sm-2 col-form-label">创建者</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" name="ruleCreateBy"
                                                   id="upRuleCreateBy" readonly>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="ruleCreateTime"
                                                   id="upRuleCreateTime" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="upRuleUpdateBy" class="col-sm-2 col-form-label">修改者</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" name="ruleUpdateBy"
                                                   id="upRuleUpdateBy" readonly>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="ruleUpdateTime"
                                                   id="upRuleUpdateTime" readonly>
                                        </div>
                                    </div>
                                    </form>
                                    <div class="form-group row">
                                        <div class="col-sm-6 right">
                                            <button class="btn btn-info btn-sm" id="addRuleContentBtn">
                                                <i class="fas fa-pencil-alt">
                                                </i>
                                                新增
                                            </button>
                                        </div>
                                    </div>
                                    <form id="ruleContentsForm">
                                    <!-- modal table show -->
                                    <div class="form-group row">
                                        <table class="table table-striped projects">
                                            <thead>
                                            <tr>
                                                <th style="width: 10%">
                                                    #
                                                </th>
                                                <th style="width: 20%">
                                                    規則類型
                                                </th>
                                                <th style="width: 15%">
                                                    規則值
                                                </th>
                                                <th style="width: 15%">
                                                    規則長度
                                                </th>
                                                <th style="width: 12%">
                                                    创建者
                                                </th>
                                                <th style="width: 12%">
                                                    修改者
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody id="updateDetailBody">
                                            </tbody>
                                        </table>
                                    </div>
                                    </form>
                                </div>
                            </div>



                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" id="updateBtn" class="btn btn-primary">修改</button>
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
                    <button class="btn btn-navbar" data-toggle="modal" data-target="#modal-lg"><i
                            class="fas fa-file"></i>添加
                    </button>
                    <a class="btn btn-navbar" href="${pageContext.request.contextPath}/rule/queryCodeRule.do"><i
                            class="fa fa-refresh"></i>刷新
                    </a>
                    <div class="card-tools">
                        <!-- SEARCH FORM -->
                        <form>
                            <div class="input-group input-group-sm">
                                <input class="form-control form-control-sm" type="search" placeholder="Search"
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
                            <th style="width: 10%">
                                编码规则编号
                            </th>
                            <th style="width: 15%">
                                编码规则描述
                            </th>
                            <th style="width: 15%">
                                创建者
                            </th>
                            <th style="width: 15%">
                                修改者
                            </th>
                            <th style="width: 15%">
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="codeRule">
                            <tr>
                                <td>
                                    #
                                </td>
                                <td>
                                    <a href="javascript:void(0)" onclick="queryDetail('${codeRule.uuid}')">
                                            ${codeRule.ruleNo}
                                    </a>
                                </td>
                                <td>
                                        ${codeRule.ruleDesc}
                                </td>
                                <td>
                                        ${codeRule.createBy}
                                    <br/>
                                    <small>
                                            ${codeRule.createDate}
                                    </small>
                                </td>
                                <td>
                                        ${codeRule.updateBy}
                                    <br/>
                                    <small>
                                            ${codeRule.updateDate}
                                    </small>
                                </td>
                                <td class="project-actions text-right">
                                    <button class="btn btn-info btn-sm" onclick="edit('${codeRule.uuid}')">
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
                                             href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=1&size=${pageInfo.pageSize}">首页</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a>
                    </li>
                    <c:choose>
                        <c:when test="${pageInfo.pages < 6}">
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <c:if test="${pageNum == pageInfo.pageNum}">
                                    <li class="page-item active"><a class="page-link"
                                                                    href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageNum != pageInfo.pageNum}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
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
                                                                    href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageNum != pageInfo.pageNum}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/rule/queryCodeRule.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}">尾页</a>
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
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });
    $(function () {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000
        });
        $('#updateBtn').click(function () {
            var ruleContents = getFormJson($("#ruleContentsForm"));
            var codeRule = getFormJson($("#updateCodeRuleForm"));
            var count = 0;
            if(!$.isEmptyObject(codeRule)){
                count = $.isArray(ruleContents["sequence"]) ? ruleContents["sequence"].length : 1;
            }
            var jsonArr = new Array();
            for(var i = 0; i < count ; i++){
                var jsonObj = {};
                for(var item in ruleContents) {
                    jsonObj[item] = ruleContents[item][i];
                }
                jsonArr.push(jsonObj);
            }
            codeRule["ruleContents"] = jsonArr;
            $.ajax({
                url: '${pageContext.request.contextPath}/rule/updateCodeRule.do',
                data: JSON.stringify(codeRule),           //序列化表单数据，格式为name=value
                type: 'POST',
                contentType : 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        Toast.fire({
                            type: 'success',
                            title: '更新成功'
                        });
                        $('#modal-lg2').modal('hide');
                    } else {
                        Toast.fire({
                            type: 'warning',
                            title: '更新失败,失败原因：' + data.message
                        })
                    }
                },
                error: function () {
                    Toast.fire({
                        type: 'warning',
                        title: '更新Error，请检查是否登录超时'
                    })
                }

            });
        });

        $('#addRuleContentBtn').click(function () {
            var rowElement = '<tr><td><input type="text" name="uuid" hidden value=""><input type="text" style="width: 30px" name="sequence" value=""></td><td><select name="typeNo" style="width: 150px"><option value="T001" >客戶PO或工單號</option><option value="T002" >年周</option><option value="T003" >客戶料號</option><option value="T004" >正式編號</option><option value="T005" >版本</option><option value="T006" >流水號</option><option value="T007" >自定義</option><option value="T008" >销售订单</option><option value="T009" >幾種號3到7位</option><option value="T010" >生產線別</option><option value="T011" >流水碼16進制</option><option value="T012" >校驗碼43</option><option value="T013" >十進制流水碼</option><option value="T014" >年月日進制表示</option><option value="T015" >浪潮年月日</option><option value="T016" >浪潮批次号</option><option value="T017" >自定義十進制批次</option><option value="T018" >H3C年月日</option><option value="T019" >工单十进制流水</option><option value="T020">工单数量</option><option value="T021" >单箱底座条码</option><option value="T022" >标准顺达流水码</option></select></td><td><input type="text" name="ruleContents.ruleValue" style="width: 100px" value=""></td><td><input type="text" name="ruleLength" style="width: 40px" value=""></td><td><br/><small></small></td><td><a href="#" onclick="removeRow(this)"><i class="fa fa-trash text-warning"></i> 删除</a></td></tr>'
            $("#updateDetailBody").append(rowElement) ;
        });

    });

    function edit(uuid) {
        $.ajax({
            url: '${pageContext.request.contextPath}/rule/queryCodeRuleDetail.do',
            data: {'uuid': uuid},           //序列化表单数据，格式为name=value
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var ruleInfo = data.data;
                    //  編碼規則 详细信息
                    $('#upRuleNo').val(ruleInfo.ruleNo);
                    $('#upRuleDesc').val(ruleInfo.ruleDesc);
                    $('#upRuleCreateBy').val(ruleInfo.createBy);
                    $('#upRuleCreateTime').val(ruleInfo.createDate);
                    $('#upRuleUpdateBy').val(ruleInfo.updateBy);
                    $('#upRuleUpdateTime').val(ruleInfo.updateDate);
                    $('#upUuid').val(ruleInfo.uuid);
                    // 編碼規則詳細信息

                    //拼接字符串innerHtml
                    var innerElement = '';
                    $('#updateDetailBody').html('');
                    for (var i = 0; i < ruleInfo.ruleContents.length; i++) {
                        var ruleContent = ruleInfo.ruleContents[i];
                        var ruleType = ruleContent.ruleType;
                        innerElement += '<tr><td><input type="text" name="uuid" hidden value="' +  ruleContent.uuid +'"><input type="text" style="width: 30px" name="sequence" value="' + ruleContent.sequence + '"></td><td><select name="typeNo" style="width: 150px"><option value="T001" ' + (ruleContent.typeNo == "T001" ? "selected=selected" : "") + '>客戶PO或工單號</option><option value="T002" ' + (ruleContent.typeNo == "T002" ? "selected=selected" : "") + '>年周</option><option value="T003" ' + (ruleContent.typeNo == "T003" ? "selected=selected" : "") + '>客戶料號</option><option value="T004" ' + (ruleContent.typeNo == "T004" ? "selected=selected" : "") + '>正式編號</option><option value="T005" ' + (ruleContent.typeNo == "T005" ? "selected=selected" : "") + '>版本</option><option value="T006" ' + (ruleContent.typeNo == "T006" ? "selected=selected" : "") + '>流水號</option><option value="T007" ' + (ruleContent.typeNo == "T007" ? "selected=selected" : "") + '>自定義</option><option value="T008" ' + (ruleContent.typeNo == "T008" ? "selected=selected" : "") + '>销售订单</option><option value="T009" ' + (ruleContent.typeNo == "T009" ? "selected=selected" : "") + '>幾種號3到7位</option><option value="T010" ' + (ruleContent.typeNo == "T010" ? "selected=selected" : "") + '>生產線別</option><option value="T011" ' + (ruleContent.typeNo == "T011" ? "selected=selected" : "") + '>流水碼16進制</option><option value="T012" ' + (ruleContent.typeNo == "T012" ? "selected=selected" : "") + '>校驗碼43</option><option value="T013" ' + (ruleContent.typeNo == "T013" ? "selected=selected" : "") + '>十進制流水碼</option><option value="T014" ' + (ruleContent.typeNo == "T014" ? "selected=selected" : "") + '>年月日進制表示</option><option value="T015" ' + (ruleContent.typeNo == "T015" ? "selected=selected" : "") + '>浪潮年月日</option><option value="T016" ' + (ruleContent.typeNo == "T016" ? "selected=selected" : "") + '>浪潮批次号</option><option value="T017" ' + (ruleContent.typeNo == "T017" ? "selected=selected" : "") + '>自定義十進制批次</option><option value="T018" ' + (ruleContent.typeNo == "T018" ? "selected=selected" : "") + '>H3C年月日</option><option value="T019" ' + (ruleContent.typeNo == "T019" ? "selected=selected" : "") + '>工单十进制流水</option><option value="T020" ' + (ruleContent.typeNo == "T020" ? "selected=selected" : "") + '>工单数量</option><option value="T021" ' + (ruleContent.typeNo == "T021" ? "selected=selected" : "") + '>单箱底座条码</option><option value="T022" ' + (ruleContent.typeNo == "T022" ? "selected=selected" : "") + '>标准顺达流水码</option></select></td><td><input type="text" name="ruleValue" style="width: 100px" value="' + ruleContent.ruleValue + '"></td><td><input type="text" name="ruleLength" style="width: 40px" value="' + ruleContent.ruleLength + '"></td><td>' + ruleContent.createBy + '<br/><small>' + ruleContent.createDate + '</small></td><td><a href="#" onclick="removeRow(this)"><i class="fa fa-trash text-warning"></i> 删除</a></td></tr>'
                    }
                    $('#updateDetailBody').html(innerElement);
                    $('#modal-lg2').modal();
                } else {
                    Toast.fire({
                        type: 'warning',
                        title: '信息查詢失敗' + data.message
                    })
                }
            },
            error: function () {
                Toast.fire({
                    type: 'warning',
                    title: '信息查詢失敗'
                })
            }

        })
    }

    function queryDetail(uuid) {
        $.ajax({
            url: '${pageContext.request.contextPath}/rule/queryCodeRuleDetail.do',
            data: {'uuid': uuid},           //序列化表单数据，格式为name=value
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var ruleInfo = data.data;
                    //  編碼規則 详细信息
                    $('#ruleNo').val(ruleInfo.ruleNo);
                    $('#ruleDesc').val(ruleInfo.ruleDesc);
                    $('#ruleCreateBy').val(ruleInfo.createBy);
                    $('#ruleCreateTime').val(ruleInfo.createDate);
                    $('#ruleUpdateBy').val(ruleInfo.updateBy);
                    $('#ruleUpdateTime').val(ruleInfo.updateDate);
                    // 編碼規則詳細信息

                    //拼接字符串innerHtml
                    var innerElement = '';
                    $('#detailBody').html('');
                    for (let ruleContent of ruleInfo.ruleContents) {
                        var ruleType = ruleContent.ruleType;
                        innerElement += '<tr><td>' + ruleContent.sequence + '</td><td>' + ruleContent.typeNo + '</td><td>' + ruleType.typeDesc + '</td><td>' + ruleContent.ruleValue + '</td><td>' + ruleContent.ruleLength + '</td><td>' + ruleContent.createBy + '<br/><small>' + ruleContent.createDate + '</small></td><td>' + ruleContent.updateBy + '<br/><small>' + ruleContent.updateDate + '</small></td></tr>'
                    }
                    $('#detailBody').html(innerElement);
                    $('#modal-lg').modal();

                } else {
                    Toast.fire({
                        type: 'warning',
                        title: '信息查詢失敗' + data.message
                    })
                }
            },
            error: function () {
                Toast.fire({
                    type: 'warning',
                    title: '信息查詢失敗'
                })
            }

        })
    }

    function removeRow(row) {
        $(row).parent().parent().remove();
    }
    function getFormJson(form) {
        var o = {};
        var a = $(form).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }

</script>
</body>
</html>
