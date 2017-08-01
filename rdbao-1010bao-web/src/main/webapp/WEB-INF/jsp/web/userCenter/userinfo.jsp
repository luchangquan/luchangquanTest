<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>基本信息</title>
    <!--共用部分-->
    <link href="${baseUrl}/resources/css/head.css" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
    
        <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">

    <!--单独部分-->
    <link href="${baseUrl}/resources/css/userCenter/user.css" rel="stylesheet">




</head>
<body>
<input type="hidden" id="navOn" value="0">
<header class="headerBox">
</header>
<!--NAV-->
<nav class="navBox">
</nav>
<nav class="menuBox">
    <ul>
        <li class="twOn"><a href="#">基本信息</a></li>
        <li><a href="#">企业信息</a></li>
        <li><a href="#">成员管理</a></li>
        <li><a href="#">数据统计</a></li>
        <li><a href="#">安全设置</a></li>
    </ul>
</nav>
<!--NAV-->
<!--主体-->
<section class="main">
    <div class="contentBox">
        <h1 class="BoxTitle ">用户中心 - <i class="typeOn">基本信息</i></h1>
        <div class="userInfoBox borBacPadd">
            11111
        </div>

    </div>
</section>
</body>
<script src="${baseUrl}/resources/js/manage.js?v=9"></script>
</html>