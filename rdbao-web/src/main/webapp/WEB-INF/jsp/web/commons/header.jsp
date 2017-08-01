<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title></title>
<script src="${baseUrl}/resources/script/scriptAll.js"></script>
<link rel="stylesheet" href="${baseUrl}/resources/css/sweetalert.css">
<script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
</head>
<body>
<header class="header">
    <a href="http://www.1010bao.com" class="t-logo" target="_blank"><img src="${baseUrl}/resources/images/a-logo.png"></a>
    <nav class="navTop">
        <ul id="navTop">
            <li><a href="${baseUrl}/userCenter/toAccount">用户中心</a></li>
            <li><a href="${baseUrl}/callRecords/callLog">通话记录</a></li>
            <li><a href="${baseUrl}/dataStatistics/countTimeStatistics">数据统计</a></li>
            <li><a href="${baseUrl}/notaryQuery/notaryList">公证查询</a></li>
        </ul>
    </nav>
    <p class="telRig"><i>${empty userContext.user.nickname ? userContext.user.phoneNo : userContext.user.nickname }</i><span class="login-exit">退出</span></p>
    <input type="hidden" value="${userContext.user.type}" id="userType">
</header>
<input type="hidden" value="${errorMsg}" id="errorMsg">
<script type="text/javascript">

	function getCookie(name){ 
		var strCookie=document.cookie; 
		var arrCookie=strCookie.split("; "); 
		for(var i=0;i<arrCookie.length;i++){ 
			var arr=arrCookie[i].split("="); 
			console.log(arr);
				if(arr[0]==name)
					return arr[1]; 
			} 
		return ""; 
	}  
	$(function(){
// 		var account=getCookie("account");
// 		$("#account").html(account);
		
		var errorMsg=$("#errorMsg").val();
		if(errorMsg!=null&&errorMsg!=""){
			alert(errorMsg);
		}
	});
	$(".login-exit").click(function(){
		var url="${baseUrl}/login/logout";
		//删除cookie
// 		document.cookie ="account=;expires=Fri, 02-Jan-1970 00:00:00 GMT";  
		window.location=url;
	});
</script>
</body>
