<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增账户 - 租户账户 | IoT-Admin</title>
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
                        <h1 class="m-0 text-dark">新增账户</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">租户账户</a></li>
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
                            <!-- form start -->
                            <form:form action="/auth/tenant-user/add" id="form" method="post" modelAttribute="authTenantUser">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="tenantCode">租户</label>
                                                <form:select path="tenantCode" cssClass="form-control select2" style="width: 100%;">
                                                    <option value="">请选择租户</option>
                                                    <c:forEach items="${authTenants}" var="authTenant">
                                                        <option value="${authTenant.tenantCode}" ${tenantCode == authTenant.tenantCode ? "selected" : ""}>
                                                                ${authTenant.tenantCode} - ${authTenant.tenantName}
                                                        </option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                            <div class="form-group">
                                                <label for="userName">用户名</label>
                                                <form:input path="userName" cssClass="form-control" placeholder="请输入用户名" />
                                            </div>
                                            <div class="form-group">
                                                <label for="password">密码</label>
                                                <form:password path="password" cssClass="form-control" placeholder="请输入密码" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="roles">角色</label>
                                                <form:select path="roles" cssClass="select2" multiple="multiple" data-placeholder="请选择角色" style="width: 100%;">
                                                    <option value="admin" ${authTenantUser.roles.contains("admin")?"selected":""}>
                                                        admin
                                                    </option>
                                                    <option value="editor" ${authTenantUser.roles.contains("editor")?"selected":""}>
                                                        editor
                                                    </option>
                                                </form:select>
                                            </div>
                                            <div class="form-group">
                                                <label for="superuser">是否超级用户</label>
                                                <form:select path="superuser" cssClass="form-control select2" style="width: 100%;">
                                                    <option value="0" selected="selected">否</option>
                                                    <option value="1">是</option>
                                                </form:select>
                                            </div>
                                            <div class="form-group">
                                                <label for="status">状态</label>
                                                <form:select path="status" cssClass="form-control select2" style="width: 100%;">
                                                    <option value="0" selected="selected">未激活</option>
                                                    <option value="1">激活</option>
                                                    <option value="2">锁定</option>
                                                    <option value="3">删除</option>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-body -->

                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary">保存</button>
                                    <a href="/auth/tenant-user/list?tenantCode=${tenantCode}" type="button" class="btn btn-default">返回列表</a>
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
                required: true
            },
            userName: {
                required: true,
                minlength: 4,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            roles: {
                required: true,
                minlength: 1,
                maxlength: 3
            }
        },
        {
            tenantCode: {
                required: " 请选择租户"
            },
            userName: {
                required: " 请输入用户名",
                minlength: " 用户名不能小于4位",
                maxlength: " 用户名不能大于于20位"
            },
            password: {
                required: " 请输入密码",
                minlength: " 密码不能小于6位",
                maxlength: " 密码不能大于于20位"
            },
            roles: {
                required: " 请选择角色",
                minlength: " 至少选择1个角色",
                maxlength: " 至多选择3个角色"
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
