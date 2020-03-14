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
                                                    <a class="dropdown-item" href="#" onclick="multiDelete()">批量删除</a>
                                                </div>
                                            </button>
                                        </div>
                                        <div class="btn-group">
                                            <a href="/auth/manager/list" type="button" class="btn btn-default" title="重新加载"><i class="fas fa-redo"></i></a>
                                            <button type="button" class="btn btn-default" title="打印">
                                                <i class="fas fa-print"></i></button>
                                            <button type="button" class="btn btn-default" title="下载">
                                                <i class="fas fa-download"></i></button>
                                        </div>
                                    </div>
                                    <div id="btnOpen" class="card-tools" style="display: ${managerSearcher.advanced?"none":"block"};">
                                        <div class="input-group" style="padding-top: 5px;">
                                            <form:input path="keyword" cssClass="form-control" placeholder="关键字：用户名" />
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-default" onclick="doSearch()">搜索
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
                                                <button type="button" class="btn btn-primary" onclick="doSearch()">搜 索
                                                </button>
                                            </div>
                                            <div class="btn-group">
                                                <a href="/auth/manager/list" type="button" class="btn btn-default">重
                                                    置</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body table-responsive p-0">
                                    <table id="dataTable" class="table table-hover text-nowrap">
                                        <thead>
                                        <tr>
                                            <th>
                                                <div class="icheck-primary d-inline">
                                                    <input type="checkbox" id="checkAll" name="checkAll" />
                                                    <label for="checkAll" />
                                                </div>
                                            </th>
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
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/layout_footer.jsp" />
</div>
<!-- ./wrapper -->
<jsp:include page="../includes/resources_body.jsp" />

<script>
$(function() {
    // 消息框显示
    if (${baseResult.status != null && baseResult.status == 200}) {
        Message.showSuccess('${baseResult.message}');
    }

    // 初始加载列表查询
    doSearch();
})

// 显示高级搜索
function showSearcher() {
    $('#advanced').val(true);
    $('#searcher').css('display', 'block');
    $('#btnOpen').css('display', 'none');
    $('#btnClose').css('display', 'block');
}

// 隐藏高级搜索
function hideSearcher() {
    $('#advanced').val(false);
    $('#searcher').css('display', 'none');
    $('#btnOpen').css('display', 'block');
    $('#btnClose').css('display', 'none');
}

function doSearch() {
    const columns = [
        {
            'data': function(row, type, val, meta) {
                return '<div class="icheck-primary d-inline">' +
                    '   <input type="checkbox" id="checkItem_' + row.userKey + '" name="checkItem" value="' + row.userKey + '" />' +
                    '   <label for="checkItem_' + row.userKey + '" />' +
                    '</div>'
            }
        },
        { 'data': 'id' },
        { 'data': 'userName' },
        { 'data': 'roles' },
        {
            'data': function(row, type, val, meta) {
                return row.superuser ? '是' : '否'
            }
        },
        {
            'data': function(row, type, val, meta) {
                switch (row.status) {
                    case 0:
                        return '<label class="text-muted">未激活</label>';
                    case 1:
                        return '<label class="text-success">已激活</label>';
                    case 2:
                        return '<label class="text-warning">锁定</label>';
                    case 3:
                        return '<label class="text-danger">被删除</label>';
                    default:
                        return '';
                }
                return '<div class="btn-group">' +
                    '   <a href="#" type="button" class="btn btn-default btn-sm"><i class="fas fa-eye"></i></a>' +
                    '   <a href="/auth/manager/edit/' + row.userKey + '" type="button" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></a>' +
                    '   <button type="button" class="btn btn-danger btn-sm" onclick="singleDelete(\'' + row.userKey + '\');"><i class="fas fa-trash"></i></button>' +
                    '</div>'
            }
        },
        { 'data': 'updated' },
        {
            'data': function(row, type, val, meta) {
                return '<div class="btn-group">' +
                    '   <a href="#" type="button" class="btn btn-default btn-sm"><i class="fas fa-eye"></i></a>' +
                    '   <a href="/auth/manager/edit/' + row.userKey + '" type="button" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></a>' +
                    '   <button type="button" class="btn btn-danger btn-sm" onclick="singleDelete(\'' + row.userKey + '\');"><i class="fas fa-trash"></i></button>' +
                    '</div>'
            }
        }
    ];

    let searchParams = {
        'advanced': $('#advanced').val(),
        'keyword': $('#keyword').val(),
        'userName': $('#userName').val(),
        'roles': $('#roles').val(),
        'status': $('#status').val(),
    };

    console.log(searchParams);

    // 加载 DataTable
    Table.loadDataTable('/auth/manager/page', columns, searchParams);
}

// 单个删除
function singleDelete(userKey) {
    ModalDialog.showConfirm('single-delete-confirm', '操作确认', '删除后数据不可恢复，您确认要操作吗？', singleDelete_callback, userKey);
}

function singleDelete_callback(userKey) {
    location.href = '/auth/manager/delete/' + userKey;
}

// 批量删除
function multiDelete() {
    let userKeys = Table.getCheckboxCheckedValues();
    if (userKeys.length == 0) {
        Message.showFail('请至少选择一条记录');
        return;
    }
    ModalDialog.showConfirm('multi-delete-confirm', '操作确认', '批量删除后数据不可恢复，您确认要操作吗？', multiDelete_callback, userKeys);
}

function multiDelete_callback(userKeys) {
    $.ajax({
        'url': '/auth/manager/multi-delete',
        'type': 'POST',
        'data': { 'userKeys': userKeys.toString() },
        'dataType': 'JSON',
        'success': function(ret) {
            if (ret.status === 200) {
                location.href = '/auth/manager/list';
                // Message.showSuccess(ret.message);
            } else {
                Message.showFail(ret.message);
            }
        }
    });
}
</script>
<script src="/static/assets/js/select2-utils.js"></script>
<script src="/static/assets/js/table-utils.js"></script>
<script src="/static/assets/js/message-utils.js"></script>
<script src="/static/assets/js/modal-dialog-utils.js"></script>
</body>
</html>
