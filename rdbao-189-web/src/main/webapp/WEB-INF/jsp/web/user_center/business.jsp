<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>用户中心-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css?t=20170706">
<script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
<script src="${baseUrl}/resources/script/echarts.min.js"></script>
</head>
<body>
<!-- 引入头部 -->
   <input type="hidden" id="navID" value="0">
   <input type="hidden" id="navLF" value="0">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <!-- 引入菜单-->
	<jsp:include page="../commons/left_menu.jsp"></jsp:include>
    <div class="main-right">
        <!--统计图表-->
        <div id="all-call-number"></div><div id="all-call-time"></div>
        <!--END统计图表-->
        <!--企业信息-->
        <div class="businessInfo-box">
            <!--企业名称-->
            <div class="businessName">
                <h2>我的账户信息</h2>
                <ul>
                    <li><i>名　　称：</i>${accountProfileData.accountName}</li>
                    <li><i>开通时间：</i>${accountProfileData.accountOpenTime}</li>
                    <li><i>账号状态：</i>${accountProfileData.accountStatus.desc}</li>
                    <li><i>开通号码：</i>${accountProfileData.openCount}个</li>
                    <li><i>姓　　名：</i><input type="text" value="${accountProfileData.accountUsername}" name="notaryName" disabled placeholder="真实姓名"> </li>
                    <li><i>手机号码：</i><input type="tel" value="${accountProfileData.accountPhoneNo}"  disabled name="notaryMobile" placeholder="手机号码"></li>
                    <li><i>身份证号：</i><input type="number" value="${accountProfileData.accountCredentialsNo}" disabled name="notaryuserId" placeholder="与姓名相匹配的证件号码"><a style="display:none" href="">完整信息</a></li>
                 </ul>
            </div>
            <!--企业名称-->
            <!--所有统计-->
            <div class="businessStat">
                <h2>累计使用情况</h2>
                <ul>
                    <li><i>总电话个数	：</i>${accountProfileData.countCalling+accountProfileData.countCalled}个</li>
                    <li><i>呼入电话个数：</i>${accountProfileData.countCalled}个<input id="countCalled" value="${accountProfileData.countCalled}" type="hidden"/></li>
                    <li><i>呼出电话个数：</i>${accountProfileData.countCalling}个<input id="countCalling" value="${accountProfileData.countCalling}" type="hidden"/></li>
                    <li><i>总通话时长：</i>${accountProfileData.countCallingTime+accountProfileData.countCalledTime}秒</li>
                    <li><i>呼入电话时长：</i>${accountProfileData.countCalledTime}秒<input id="countCalledTime" value="${accountProfileData.countCalledTime}" type="hidden"/></li>
                    <li><i>呼出电话时长：</i>${accountProfileData.countCallingTime}秒<input id="countCallingTime" value="${accountProfileData.countCallingTime}" type="hidden"/></li>
                </ul>
            </div>
            <!--所有统计-->
            <!--已开通号码-->
            <div class="businessNumber">
                <h2>已开通号码</h2>
                <table>
                    <thead>
                        <tr>
                            <td>序号/姓名</td>
                            <td>电话号码</td>
                            <td>开通时间</td>
                            <td>当前状态</td>
                            <td>序号/姓名</td>
                            <td>电话号码</td>
                            <td>开通时间</td>
                            <td>当前状态</td>
                        </tr>
                    </thead>
                     <tbody>
                    	<!-- 下面循环一次可以遍历2条数据 -->
                    	<c:choose>
								<c:when test="${not empty accountProfileData.openPhoneNoDatas}">
									 <c:forEach items="${accountProfileData.openPhoneNoDatas}" var="news_li" varStatus="i_li">
									 <c:if test="${i_li.index % 2 eq 0}">
									 			<tr>
									 			<c:forEach items="${accountProfileData.openPhoneNoDatas}" var="news_span" varStatus="i_span" begin="${i_li.index}" end="${i_li.index + 1}">
                            							<td>${i_span.index+1}/${news_span.name}</td>
							                            <td>${news_span.phoneNo} </td>
							                            <td><fmt:formatDate value="${news_span.openDate}" pattern="yyyy-MM-dd" /> </td>
							                            <td>${news_span.status.desc}<a href="${baseUrl}/userCenter/toBlackAndWhiteList?phoneNo=${news_span.phoneNo}"><i class="caSz" title="点击设置黑白名单"></i></a> </td>
                        						</c:forEach>
                        						</tr>
									 </c:if>
									 </c:forEach>
								</c:when>
						</c:choose>
                    </tbody>
                </table>

            </div>
            <!--已开通号码-->
        </div>
        <!--END企业信息-->
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script src="${baseUrl}/resources/script/all-call-data.js"></script>
</body>
</html>