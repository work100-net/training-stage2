<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Navbar -->
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="/" class="nav-link">首页</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="/auth/manager/list" class="nav-link">后台账户</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="/auth/tenant/list" class="nav-link">租户</a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#">
                <i class="nav-icon fas fa-user-circle"></i>
                ${manager.userName}
                <i class="fas fa-angle-down right"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-md dropdown-menu-right">
                <a href="/my/profile" class="dropdown-item">
                    <i class="fas fa-address-card mr-2"></i>我的资料
                </a>
                <div class="dropdown-divider"></div>
                <a href="/my/change-password" class="dropdown-item">
                    <i class="fas fa-lock mr-2"></i>修改密码
                </a>
                <div class="dropdown-divider"></div>
                <a href="/logout" class="dropdown-item">
                    <i class="fas fa-sign-out-alt mr-2"></i>退出
                </a>
            </div>
        </li>
    </ul>
</nav>
<!-- /.navbar -->
