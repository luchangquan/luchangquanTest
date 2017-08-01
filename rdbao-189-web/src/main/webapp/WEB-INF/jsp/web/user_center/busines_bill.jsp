<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>月度数据-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
   <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<!-- 引入头部 -->
<input type="hidden" id="navID" value="0">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <!-- 引入菜单-->
	<jsp:include page="../commons/left_menu.jsp"></jsp:include>

    <div class="main-right">
        <div class="businesBill">
            <h2>电话语音保全月数据</h2>
            <table>
                <thead>
                    <tr>
                        <td>数据名称</td>
                        <td>发送时间</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <td>2016年11月数据</td>
                    <td>2016年12月1日</td>
                    <td><a href="businesBillCon.html">查看详情</a></td>
                </tr>
                <tr>
                    <td>2016年10月数据</td>
                    <td>2016年11月1日</td>
                    <td><a href="#">查看详情</a></td>
                </tr>
                <tr>
                    <td>2016年09月数据</td>
                    <td>2016年10月1日</td>
                    <td><a href="#">查看详情</a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>