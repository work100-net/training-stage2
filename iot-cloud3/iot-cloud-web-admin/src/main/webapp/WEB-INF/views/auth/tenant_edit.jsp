<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑租户 - 租户 | IoT-Admin</title>
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
                        <h1 class="m-0 text-dark">编辑租户</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">租户</a></li>
                            <li class="breadcrumb-item active">编辑租户</li>
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
                            <!-- form start -->
                            <form:form action="/auth/tenant/edit/${authTenant.tenantCode}" id="form" method="post" modelAttribute="authTenant">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="tenantCode">编码</label>
                                                <form:input path="tenantCode" cssClass="form-control" disabled="true" />
                                            </div>
                                            <div class="form-group">
                                                <label for="created">创建时间</label>
                                                <form:input path="created" cssClass="form-control" disabled="true" />
                                            </div>
                                            <div class="form-group">
                                                <label for="tenantDesc">备注</label>
                                                <form:textarea path="tenantDesc" cssClass="form-control" placeholder="请输入备注" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="tenantName">名称</label>
                                                <form:input path="tenantName" cssClass="form-control" />
                                            </div>
                                            <div class="form-group">
                                                <label for="updated">更新时间</label>
                                                <form:input path="updated" cssClass="form-control" disabled="true" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->

                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary">保存</button>
                                    <a href="/auth/tenant/list" type="button" class="btn btn-default">返回列表</a>
                                </div>
                            </form:form>
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
    if (${baseResult.status != null && baseResult.status != 200}) {
        Message.showFail('${baseResult.message}');
    }

    FormValidate.validate(
        'form',
        {
            tenantCode: {
                required: true,
                minlength: 6,
                maxlength: 10
            },
            tenantName: {
                required: true,
                minlength: 4,
                maxlength: 20
            }
        },
        {
            tenantCode: {
                required: " 请输入编码",
                minlength: " 用户名不能小于6位",
                maxlength: " 用户名不能大于于10位"
            },
            tenantName: {
                required: " 请输入名称",
                minlength: " 名称不能小于4位",
                maxlength: " 名称不能大于于20位"
            }
        }
    );
})
</script>
<script src="/static/assets/js/select2-utils.js"></script>
<script src="/static/assets/js/message-utils.js"></script>
<script src="/static/assets/js/form-validate-utils.js"></script>
</body>
</html>
