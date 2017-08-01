<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>应用使用统计</title>
    <!--共用部分-->
    <link href="${baseUrl}/resources/css/head.css" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
    <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">
    <script src="${baseUrl}/resources/js/manage.js?v=4"></script>

    <!--单独部分-->
    <link href="${baseUrl}/resources/css/evidenceManag/request.css" rel="stylesheet">
	<script type="text/javascript">
		
		$(function(){
			var sourceNppCode=$("#sourceNppCode").val();
			var account=$("#account").val();//账号
			var data={"account":account};
			var type=0;
			//根据账号获取信息
			var url="${baseUrl}/userCenter/getUserInfo";
			$.ajax({
				url:url,
				data:data,
				type:"POST",
				async:false,
				success:function(result){
					if(result.respCode!="0000"){
						swal("",result.respDesc);
					}else{
						type=result.data.type;
						if(type==2){
							var url="${baseUrl}/getRemotePcUrl?nppCode="+sourceNppCode;
							$.ajax({
								url:url,
								data:data,
								type:"POST",
								async:false,
								success:function(results){
									console.log(results.data);
									if(results.respCode!="0000"){
										swal("",results.respDesc);
									}else {
										window.open(results.data.url);
										
										
									}
								},
								error:function(result){
									alert(result);
								}
							});
							
							
						}else{
							$(".contentBox").show();
						}
						
					}
				},
				error:function(result){
					alert(result);
				}
			});
		})
		
	</script>
</head>

<body>
<input type="hidden" id="navOn" value="2">
<header class="headerBox">
</header>
<!--NAV-->
<nav class="navBox">
</nav>
<!--NAV-->
<!--主体-->
 <input value="${userContext.sourceNppCode}" id="sourceNppCode" type="hidden"/>
<input value="${userContext.user.account}" id="account" type="hidden"/>
<section class="main">
    <div class="contentBox" style="display:none">
        <h1 class="BoxTitle ">自助取证机使用统计</h1>
        <div class="appCount borBacPadd">
            <div class="CountType">
                <h2>总数量：</h2>
                <p><span>${evidenceCount}<i>条</i></span></p>
            </div>
            <div class="CountTime">
                <h2>总时长：</h2>
                <p><span><i>${totalDuration}</i></span></p>
            </div>
            <div class="countSize">
                <h2>总容量：</h2>
                <p><span><i>${spaceAmount}</i></span></p>
            </div>
        </div>

    </div>
    <footer class="footerM"></footer>
</section>
</body>
</html>