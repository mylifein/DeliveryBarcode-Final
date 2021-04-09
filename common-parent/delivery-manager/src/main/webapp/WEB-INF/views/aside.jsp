<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>aa
<!DOCTYPE html>
<html>
<head>
</head>
<html>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<aside class="main-sidebar sidebar-dark-green elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
<%--        <img src="${pageContext.request.contextPath}/images/chenbro.jpg" alt="CHENBRO Logo" class="brand-image img-circle elevation-3"--%>
<%--             style="opacity: .8">--%>
        <span class="brand-text font-weight-light">CHENBRO</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
<%--            <img src="${pageContext.request.contextPath}/images/kervin.jpg" class="img-circle elevation-2" alt="User Image">--%>
    <img src="<shiro:principal type="com.chenbro.deliverybarcode.model.HubUser" property="staffPhoto"/>" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block" id="userName"><shiro:principal type="com.chenbro.deliverybarcode.model.HubUser" property="username"/></a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/user/main.do" class="nav-link">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            首页
                        </p>
                    </a>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-barcode"></i>
                        <p>
                            条码
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/pallet/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>栈板条码进度</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>装箱单进度</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/ct/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>CT码进度</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/queryInspur.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>浪潮条码进度</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/queryWoBatch.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>工单批次管理</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/distributeBatch.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>工单批次分配</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/checkOrder/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>质检工单信息</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-user-edit"></i>
                        <p>
                            用戶管理
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/user/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>帐号管理</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/role/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>角色管理</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/perm/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>权限管理</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/sysLog/query.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>日志查询</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-search"></i>
                        <p>
                            扫码
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/store.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>仓库入库</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/outstore.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>仓库出库</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/qualityCheck.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>品保质检</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/destroyPallet.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>栈板作废</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/box/destroyBox.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>装箱作废</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-cube"></i>
                        <p>
                            电子看板
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/monitor/assembly.do?mark=A" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>组装A线</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/monitor/assembly.do?mark=B" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>组装B线</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/monitor/assembly.do?mark=C" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>组装C线</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-print"></i>
                        <p>
                            打印程序管理
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item has-treeview">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>
                                    打印模板管理
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/print/query.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>模板文件管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/print/queryCite.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>模板引用管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/field/query.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>模板参数管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/field/queryCite.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>模板参数引用管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/print/queryField.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>打印字段管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/print/queryCapacity.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>容量管理</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/print/queryCapacityCite.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>容量引用管理</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item has-treeview">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>
                                    编码规则管理
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/rule/queryCodeRule.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>编码规则查询</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/rule/queryRuleCite.do" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>编码规则引用查询</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>


                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-file-excel"></i>
                        <p>
                            报表
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/report/delivery.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>装箱单条码报表</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/report/pallet.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>栈板条码报表</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/report/check.do" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>工单首件质检报表</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/monitor/assembly.do?mark=B" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>半成品条码报表</p>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>

</body>
</html>
</html>