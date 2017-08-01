<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>应用中心</title>
    <!--共用部分-->
    <link href="${baseUrl}/resources/css/head.css" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
    <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">
    <script src="${baseUrl}/resources/js/manage.js?v=5"></script>

    <!--单独部分-->
    <link href="${baseUrl}/resources/css/applicationCenter/apply.css" rel="stylesheet">




</head>
<body>
<input type="hidden" id="navOn" value="3">
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
    <div class="contentBox">
        <h1 class="BoxTitle ">应用中心</h1>
        <div class="applyInfoBox">
            <ul class="applyInfo">
                <li><p><img src="${baseUrl}/resources/images/apply/rdp1.png"></p><b class="applyTextA"><span>自助取证机</span><a id="applyTextA1" href="#">立即进入</a></b></li>
                <li><p><img src="${baseUrl}/resources/images/apply/rdp2.png"></p><b class="applyTextA"><span>手机取证</span><a href="#" class="noTb">敬请期待</a></b></li>
                <li><p><img src="${baseUrl}/resources/images/apply/rdp3.png"></p><b class="applyTextA"><span>网页抓取</span><a href="#" class="noTb">敬请期待</a></b></li>
                <li><p><img src="${baseUrl}/resources/images/apply/rdp4.png"></p><b class="applyTextA"><span>其他工具</span><a href="#" class="noTb">敬请期待</a></b></li>
            </ul>
        </div>
        <footer class="footerM"></footer>
    </div>
</section>

<script src="${baseUrl}/resources/js/applicationCenter/apply.js"></script>
<script type="text/javascript">
		$(function(){
			$("#applyTextA1").on("click",function(){
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
								window.location=baseUrl+"/applicationCenter/toAppCount?sourceNppCode="+sourceNppCode;
							}
							
						}
					},
					error:function(result){
						alert(result);
					}
				});
			})
		})

</script>

</body>
</html>