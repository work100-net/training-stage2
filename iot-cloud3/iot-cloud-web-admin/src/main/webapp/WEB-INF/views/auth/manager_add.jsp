<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <h1 class="m-0 text-dark">新增账户</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">后台账户</a></li>
                            <li class="breadcrumb-item active">新增账户</li>
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
                        <div class="card card-gray">
                            <!-- /.card-header -->
                            <!-- form start -->
                            <form action="/auth/manager/add" method="post">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="userName">用户名 <font color="red">*</font></label>
                                                <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名" value="${authManager.userName}">
                                            </div>
                                            <div class="form-group">
                                                <label for="password">密码 <font color="red">*</font></label>
                                                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                                            </div>
                                            <div class="form-group">
                                                <label for="status">状态 <font color="red">*</font></label>
                                                <select class="form-control select2" style="width: 100%;" id="status" name="status">
                                                    <option value="0" selected="selected">未激活</option>
                                                    <option value="1">激活</option>
                                                    <option value="2">锁定</option>
                                                    <option value="3">删除</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="roles">角色 <font color="red">*</font></label>
                                                <select class="select2" id="roles" name="roles" multiple="multiple" data-placeholder="请选择角色" style="width: 100%;">
                                                    <option value="admin" ${authManager.roles.contains("admin")?"selected":""}>admin</option>
                                                    <option value="editor" ${authManager.roles.contains("editor")?"selected":""}>editor</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="superuser">是否超级用户 <font color="red">*</font></label>
                                                <select class="form-control select2" id="superuser" name="superuser" style="width: 100%;">
                                                    <option value="0" selected="selected">否</option>
                                                    <option value="1">是</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->

                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary">保存</button>
                                    <a href="/auth/manager/list" type="button" class="btn btn-default">返回列表</a>
                                </div>
                            </form>
                        </div>
                        <!-- /.card -->
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
    //Initialize Select2 Elements
    $('.select2').select2();

    //Initialize Select2 Elements
    $('.select2bs4').select2({
        theme: 'bootstrap4'
    });

    if (${baseResult.status != null && baseResult.status != 200}) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })

        Toast.fire({
            type: 'error',
            title: '${baseResult.message}'
        })
    }
})
</script>
</body>
</html>
