<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>
    <div class="main-left">
        <ul class="navLF">
            <li class="currentlt"><a  href="${baseUrl}/userCenter/toAccount">账户概况</a><input value="" type="hidden"/></li>
            <li><a href="${baseUrl}/userCenter/toSecuritysettings">安全设置</a><input value="" type="hidden"/></li> 
           <%--  <li><a href="${baseUrl}/userCenter/toBlackAndWhiteList">黑白名单</a><input value="" type="hidden"/></li> --%>
            <!-- <li><a>成员管理</a> <input value="" type="hidden"/>	</li> -->
        </ul>
    </div>
 
</body>