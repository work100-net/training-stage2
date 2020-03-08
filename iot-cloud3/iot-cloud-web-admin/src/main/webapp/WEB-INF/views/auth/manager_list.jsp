<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>查询列表 - 后台账户 | IoT-Admin</title>
    <jsp:include page="../includes/resources_head.jsp" />
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/layout_header.jsp" />

    <jsp:include page="../includes/layout_left.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0 text-dark">查询列表</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">后台账户</a></li>
                            <li class="breadcrumb-item active">查询列表</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <form:form action="/auth/manager/search" method="post" modelAttribute="managerSearcher">
                            <form:hidden path="advanced" />
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <div class="btn-group">
                                            <a href="/auth/manager/add" type="button" class="btn btn-primary">新增</a>
                                            <button type="button" class="btn btn-default">更多...</button>
                                            <button type="button" class="btn btn-default dropdown-toggle dropdown-icon" data-toggle="dropdown">
                                                <span class="sr-only">Toggle Dropdown</span>
                                                <div class="dropdown-menu" role="menu">
                                                    <a class="dropdown-item" href="#">批量锁定</a>
                                                    <a class="dropdown-item" href="#">批量解锁</a>
                                                    <div class="dropdown-divider"></div>
                                                    <a class="dropdown-item" href="#">批量删除</a>
                                                </div>
                                            </button>
                                        </div>
                                        <div class="btn-group">
                                            <a href="/auth/manager/list" type="button" class="btn btn-default" title="重新加载"><i class="fas fa-redo"></i></a>
                                            <button type="button" class="btn btn-default" title="打印"><i class="fas fa-print"></i></button>
                                            <button type="button" class="btn btn-default" title="下载"><i class="fas fa-download"></i></button>
                                        </div>
                                    </div>
                                    <div id="btnOpen" class="card-tools" style="display: ${managerSearcher.advanced?"none":"block"};">
                                        <div class="input-group" style="padding-top: 5px;">
                                            <form:input path="keyword" cssClass="form-control" placeholder="关键字：用户名" />
                                            <div class="input-group-append">
                                                <button type="submit" class="btn btn-default">搜索
                                                    <i class="fas fa-search"></i></button>
                                            </div>
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-default" title="展开更多" onclick="showSearcher()">
                                                    展开 <i class="fas fa-angle-double-down"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="btnClose" class="card-tools" style="display: ${managerSearcher.advanced?"block":"none"};">
                                        <div class="input-group" style="padding-top: 5px;">
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-default" title="合拢更多" onclick="hideSearcher()">
                                                    合拢 <i class="fas fa-angle-double-up"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-header" id="searcher" style="display: ${managerSearcher.advanced?"block":"none"};background-color: #f2f4f8;">
                                    <div class="row">
                                        <div class="col-md-9">
                                            <div class="input-group">
                                                <form:input path="userName" cssClass="form-control" placeholder="用户名" />
                                                <form:select path="roles" class="form-control select2" style="width: 150px;">
                                                    <option value="" ${managerSearcher.roles == "" ? "selected" : ""}>
                                                        角色
                                                    </option>
                                                    <option value="admin" ${managerSearcher.roles == "admin" ? "selected" : ""}>
                                                        admin
                                                    </option>
                                                    <option value="editor" ${managerSearcher.roles == "editor" ? "selected" : ""}>
                                                        editor
                                                    </option>
                                                </form:select>
                                                <form:select path="status" class="form-control select2" style="width: 150px;">
                                                    <option value="-1" ${managerSearcher.status == -1 ? "selected" : ""}>
                                                        状态
                                                    </option>
                                                    <option value="0" ${managerSearcher.status == 0 ? "selected" : ""}>
                                                        未激活
                                                    </option>
                                                    <option value="1" ${managerSearcher.status == 1 ? "selected" : ""}>
                                                        激活
                                                    </option>
                                                    <option value="2" ${managerSearcher.status == 2 ? "selected" : ""}>
                                                        锁定
                                                    </option>
                                                    <option value="3" ${managerSearcher.status == 3 ? "selected" : ""}>
                                                        删除
                                                    </option>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="btn-group">
                                                <button type="submit" class="btn btn-primary">搜 索</button>
                                            </div>
                                            <div class="btn-group">
                                                <a href="/auth/manager/list" type="button" class="btn btn-default">重 置</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body table-responsive p-0">
                                    <table class="table table-hover text-nowrap">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>用户名</th>
                                            <th>角色</th>
                                            <th>超级用户</th>
                                            <th>状态</th>
                                            <th>更新时间</th>
                                            <th width="120px" align="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${authManagers}" var="authManager">
                                            <tr>
                                                <td>${authManager.id}</td>
                                                <td>${authManager.userName}</td>
                                                <td>${authManager.roles}</td>
                                                <td>${authManager.superuser?"是":"否"}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${authManager.status==0}">
                                                            未激活
                                                        </c:when>
                                                        <c:when test="${authManager.status==1}">
                                                            已激活
                                                        </c:when>
                                                        <c:when test="${authManager.status==2}">
                                                            锁定
                                                        </c:when>
                                                        <c:when test="${authManager.status==3}">
                                                            被删除
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${authManager.updated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a href="#" type="button" class="btn btn-default btn-sm"><i class="fas fa-eye"></i></a>
                                                        <a href="/auth/manager/edit/${authManager.userKey}" type="button" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></a>
                                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-operate-confirm" data-whatever="${authManager.userKey}">
                                                            <i class="fas fa-trash"></i></button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </form:form>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /.content -->

        <div class="modal fade" id="modal-operate-confirm">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">操作确认</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>操作后不可恢复，确定吗？</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary">确定</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/layout_footer.jsp" />
</div>
<!-- ./wrapper -->
<jsp:include page="../includes/resources_body.jsp" />

<script>
$(function() {
    //Initialize Select2 Elements
    $('.select2').select2();

    //Initialize Select2 Elements
    $('.select2bs4').select2({
        theme: 'bootstrap4'
    });

    if (${baseResult.status != null && baseResult.status == 200}) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true
        })

        Toast.fire({
            type: 'success',
            title: '${baseResult.message}'
        })
    }

    $('#modal-operate-confirm').on('show.bs.modal', function(event) {
        let trigger = $(event.relatedTarget)
        let userKey = trigger.data('whatever')
        let modal = $(this)
        let ok = modal.find('.modal-footer button')[1]
        $(ok).click(function(e) {
            location.href = '/auth/manager/delete/' + userKey
        })
    })
})

// 显示高级搜索
function showSearcher() {
    $("#advanced").val(true);
    $("#searcher").css('display', 'block');
    $("#btnOpen").css('display', 'none');
    $("#btnClose").css('display', 'block');
}

// 隐藏高级搜索
function hideSearcher() {
    $("#advanced").val(false);
    $("#searcher").css('display', 'none');
    $("#btnOpen").css('display', 'block');
    $("#btnClose").css('display', 'none');
}
</script>
</body>
</html>
