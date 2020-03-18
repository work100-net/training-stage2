<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>查询列表 - 租户 | IoT-Admin</title>
    <jsp:include page="../includes/resources_head.jsp" />
</head>
<body class="hold-transition sidebar-mini ${cookie.sidebar_collapse.value=='true'?'sidebar-collapse':''}">
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
                            <li class="breadcrumb-item"><a href="#">租户</a></li>
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
                        <form:form action="/auth/tenant/search" method="post" modelAttribute="tenantSearcher">
                            <form:hidden path="advanced" />
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <div class="btn-group">
                                            <a href="/auth/tenant/add" type="button" class="btn btn-primary">新增</a>
                                            <button type="button" class="btn btn-default">更多...</button>
                                            <button type="button" class="btn btn-default dropdown-toggle dropdown-icon" data-toggle="dropdown">
                                                <span class="sr-only">Toggle Dropdown</span>
                                                <div class="dropdown-menu" role="menu">
                                                    <a class="dropdown-item" href="#" onclick="multiDelete()">批量删除</a>
                                                </div>
                                            </button>
                                        </div>
                                        <div class="btn-group">
                                            <a href="/auth/tenant/list" type="button" class="btn btn-default" title="重新加载"><i class="fas fa-redo"></i></a>
                                            <button type="button" class="btn btn-default" title="打印">
                                                <i class="fas fa-print"></i></button>
                                            <button type="button" class="btn btn-default" title="下载">
                                                <i class="fas fa-download"></i></button>
                                        </div>
                                    </div>
                                    <div id="btnOpen" class="card-tools" style="display: ${tenantSearcher.advanced?"none":"block"};">
                                        <div class="input-group" style="padding-top: 5px;">
                                            <form:input path="keyword" cssClass="form-control" placeholder="关键字：租户编码" />
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-default" onclick="doSearch()">
                                                    搜索<i class="fas fa-search"></i></button>
                                            </div>
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-default" title="展开更多" onclick="showSearcher()">
                                                    展开 <i class="fas fa-angle-double-down"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="btnClose" class="card-tools" style="display: ${tenantSearcher.advanced?"block":"none"};">
                                        <div class="input-group" style="padding-top: 5px;">
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-default" title="合拢更多" onclick="hideSearcher()">
                                                    合拢 <i class="fas fa-angle-double-up"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-header" id="searcher" style="display: ${tenantSearcher.advanced?"block":"none"};background-color: #f2f4f8;">
                                    <div class="row">
                                        <div class="col-md-9">
                                            <div class="input-group">
                                                <form:input path="tenantCode" cssClass="form-control" placeholder="租户编码" />
                                                <form:input path="tenantName" cssClass="form-control" placeholder="租户名称" />
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-primary" onclick="doSearch()">搜 索
                                                </button>
                                            </div>
                                            <div class="btn-group">
                                                <a href="/auth/tenant/list" type="button" class="btn btn-default">重
                                                    置</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <table id="dataTable" class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th width="30" align="center">
                                                <div class="icheck-primary d-inline">
                                                    <input type="checkbox" id="checkAll" name="checkAll" />
                                                    <label for="checkAll" />
                                                </div>
                                            </th>
                                            <th>ID</th>
                                            <th>租户编码</th>
                                            <th>租户名称</th>
                                            <th>创建时间</th>
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
                    '   <input type="checkbox" id="checkItem_' + row.tenantCode + '" name="checkItem" value="' + row.tenantCode + '" />' +
                    '   <label for="checkItem_' + row.tenantCode + '" />' +
                    '</div>'
            }
        },
        { 'data': 'id' },
        { 'data': 'tenantCode' },
        { 'data': 'tenantName' },
        {
            'data': function(row, type, val, meta) {
                if (row.created != null) {
                    return DateUtils.formatDate(new Date(row.created), "yyyy-MM-dd HH:mm:ss");
                } else {
                    return '';
                }
            }
        },
        {
            'data': function(row, type, val, meta) {
                return '<div class="btn-group">' +
                    '   <a href="#" type="button" class="btn btn-default btn-sm" onclick="showDetail(\'' + row.tenantCode + '\',\'' + row.tenantName + '\',\'' + row.tenantDesc + '\',' + row.created + ',' + row.updated + ');"><i class="fas fa-eye"></i></a>' +
                    '   <a href="/auth/tenant/edit/' + row.tenantCode + '" type="button" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></a>' +
                    '   <button type="button" class="btn btn-danger btn-sm" onclick="singleDelete(\'' + row.tenantCode + '\');"><i class="fas fa-trash"></i></button>' +
                    '</div>';
            }
        }
    ];

    let searchParams = {
        'advanced': $('#advanced').val(),
        'keyword': $('#keyword').val(),
        'tenantCode': $('#tenantCode').val(),
        'tenantName': $('#tenantName').val()
    };

    // 加载 DataTable
    Table.loadDataTable('/auth/tenant/page-search', columns, searchParams);
}

function showDetail(tenantCode, tenantName, tenantDesc, created, updated) {
    let html = '';
    html = html + '<div class="row" style="padding: 4px;">';
    html = html + '  <div class="col-md-3" style="font-weight: bold;">编码</div>';
    html = html + '  <div class="col-md-9">' + tenantCode + '</div>';
    html = html + '</div>';
    html = html + '<div class="row" style="padding: 4px;">';
    html = html + '  <div class="col-md-3" style="font-weight: bold;">名称</div>';
    html = html + '  <div class="col-md-9">' + tenantName + '</div>';
    html = html + '</div>';
    html = html + '<div class="row" style="padding: 4px;">';
    html = html + '  <div class="col-md-3" style="font-weight: bold;">备注</div>';
    html = html + '  <div class="col-md-9">' + tenantDesc + '</div>';
    html = html + '</div>';
    html = html + '<div class="row" style="padding: 4px;">';
    html = html + '  <div class="col-md-3" style="font-weight: bold;">创建时间</div>';
    html = html + '  <div class="col-md-9">' + DateUtils.formatDate(new Date(created), "yyyy-MM-dd HH:mm:ss") + '</div>';
    html = html + '</div>';
    html = html + '<div class="row" style="padding: 4px;">';
    html = html + '  <div class="col-md-3" style="font-weight: bold;">更新时间</div>';
    html = html + '  <div class="col-md-9">' + DateUtils.formatDate(new Date(updated), "yyyy-MM-dd HH:mm:ss") + '</div>';
    html = html + '</div>';
    ModalDialog.showAlert('show-detail', '查看租户', html);
}

// 单个删除
function singleDelete(tenantCode) {
    ModalDialog.showConfirm('single-delete-confirm', '操作确认', '删除后数据不可恢复，您确认要操作吗？', singleDelete_callback, tenantCode);
}

function singleDelete_callback(tenantCode) {
    location.href = '/auth/tenant/delete/' + tenantCode;
}

// 批量删除
function multiDelete() {
    let tenantCodes = Table.getCheckboxCheckedValues();
    if (tenantCodes.length == 0) {
        Message.showFail('请至少选择一条记录');
        return;
    }
    ModalDialog.showConfirm('multi-delete-confirm', '操作确认', '批量删除后数据不可恢复，您确认要操作吗？', multiDelete_callback, tenantCodes);
}

function multiDelete_callback(tenantCodes) {
    $.ajax({
        'url': '/auth/tenant/multi-delete',
        'type': 'POST',
        'data': { 'tenantCodes': tenantCodes.toString() },
        'dataType': 'JSON',
        'success': function(ret) {
            if (ret.status === 200) {
                location.href = '/auth/tenant/list';
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
<script src="/static/assets/js/date-utils.js"></script>
</body>
</html>
