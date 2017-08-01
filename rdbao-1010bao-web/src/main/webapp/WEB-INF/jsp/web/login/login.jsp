<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<%
String sourceNppCode = request.getParameter("sourceNppCode");//用request得到 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎登录实时保</title>
    <link href="${baseUrl}/resources/css/login/login.css?v=1" rel="stylesheet">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
<%--     <script type="text/javascript" src="${baseUrl}/resources/js/login/jquerytabs.js"></script> --%>
        <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">
    
    <!--[if lt IE 9]>
    <script src="${baseUrl}/resources/script/html5shiv.min.js" type="text/javascript"></script>
    <script src="${baseUrl}/resources/script/mediaqueries.js?ie8media" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
      	  	var url="${baseUrl}/resources/jsp/iealert.jsp";
        	
        	
            $(".iealert").load(url+" #ielt8-tips");

        })
    </script>
    <![ENDif]-->
    <script>
    $(document).ready(function () {
    	//根据sourceNppCode替换logo
    	var sourceNppCode=$("#sourceNppCode").val();
    	$(".notarylogo").css("background-image","url(${baseUrl}/resources/images/logo/"+sourceNppCode+".png?v=1)");
    	
        $('.loginrg').find('input').bind('keydown', function(e) {
			var key = e.which;
			if (key == 13) {
				$('.loginbt').trigger('click');
			}
		});
        $("input[type=text]，input[type=password]").attr("autocomplete","off");
/*         $(".loginrg").rTabs({
            btnClass: '.loginnav',
            conClass: '.lgtabscon',
            bind: 'click',
            animation: 'fadein',
            speed: 2000,
            auto: false
        }); */
        $(".loginbt").on("click", function () {
            var userid = $("input[name=userid]").val();
            var password = $("input[name=password]").val();
            var imgnumber = $("input[name=imgnumber]").val();
            var tips = $(".logintips");
                if (userid !== "" && password !== "" && imgnumber !== "") {
               	 //校验图形验证码
               	 var url="${baseUrl}/login/checkValidateCode";
               	 var data={"validateCode":imgnumber};
               	 $.ajax({
               		url: url, 
               		data:data,
               		type:"POST",
               		success: function(result){
                     	if(result.success==false){
                     		$(tips).html("").html("图形验证码有误").show().delay(5000).hide(0);
                     		refreshalidateCode();
                     	}else{
                       	//登陆
                       	var url="${baseUrl}/login/login";
                       	var data={"account":userid,"password":password,"sourceNppCode":$("#sourceNppCode").val()};
                       	$.ajax({
                       		url: url, 
                       		data:data,
                       		type:"POST",
                       		success: function(result){
                       		
                             	if(result.respCode!="0000"){
                             		$(tips).html("").html(result.respDesc).show().delay(5000).hide(0);
                             		refreshalidateCode();
                             	}else{
                             		if(result.data.weakPassword){
										window.location.href="${baseUrl}/login/resetPassword?tip=弱密码-&sourceNppCode="+getQueryString("sourceNppCode") ;
									}else{
										if(result.data.userType==1||result.data.userType==2){
											window.location.reload();
										}else if(result.data.userType==3||result.data.userType==4) {
											window.location.href="${baseUrl}/notary/nr/list?sourceNppCode="+getQueryString("sourceNppCode");
										}
									}
                             	}
                             }});
                     	}
                     }});
                } else {
                    if (userid == "") {
                        $(tips).html("").html("请输入用户名").show().delay(5000).hide(0);
                    } else if (password == "") {
                        $(tips).html("").html("请输入登录密码").show().delay(5000).hide(0);
                    } else {
                        $(tips).html("").html("请输入图形验证码").show().delay(5000).hide(0);
                    }
                }
        });
        $(".loginuser>span").on("click", function () {
            $(".loginuserbox").hide();
        });
    });
        //刷新验证码
        function refreshalidateCode(e){
        	var id = Math.random();
        	$(".imgnumber").find('img').attr("src","${baseUrl}/login/getValidateCode?id=" + id);
        }
        
    </script>
</head>
<body>
<div class="iealert"></div>
<div class="login">
    <div class="loginheader">
        <span class="notarylogo"></span>
        <span class="bao1010logo"></span>
        <span class="pagetitle">欢迎登录实时保电子证据保全平台</span>
        <span class="bao1010tel">服务热线：400-821-9388</span>
    </div>
    <div class="loginbox">
        <div class="logincontent">
            <div class="loginlf"><a href="" target="_blank"></a></div>
            <div class="loginrg">
                <div class="logintabs">
                    <div class="lgtabscon">
                    		<input id="sourceNppCode" value="<%=sourceNppCode%>" type="hidden">
                        <ul class="userpw" style="display:block;">
                            <li class="userid"><i></i><input type="text" value="" name="userid" maxlength="30" placeholder="请输入用户名"></li>
                            <li class="password"><i></i><input type="password" value="" name="password" maxlength="20" placeholder="请输入登录密码"></li>
                            <li class="imgnumber"><input type="text" value="" name="imgnumber" maxlength="4" placeholder="输入图形验证码"><img src="${baseUrl}/login/getValidateCode" onclick="refreshalidateCode(this)"  title="点击刷新"></li>
                        </ul>
                    </div>
                </div>
                <div class="loginbtbox">
                    <p class="logintips"></p>
                    <span class="loginbt">登 录</span>
                    <p class="signup">
                    <a href="javascript:;" onclick="toRecovery();" class="lf">忘记密码？</a><!--<a href="" class="rg">没有公证语音帐号？立即注册</a>--></p>
                </div>
                
            </div>
        </div>
    </div>
    <div class="loginfooter">
    	<c:choose>
    		<c:when test="${param.sourceNppCode == 'ZJLA' }">
    			<p>临安市公证处版权所有 Copyright © 2012-2017 Linan Notary Public Office All rights reserved, 浙ICP备17011889号</p>
    		</c:when>
    		<c:when test="${param.sourceNppCode == 'HZDF' }">
    			<p>CopyRight 2007-2008 浙江省杭州市东方公证处 （原浙江省公证处） All Right Reserved 地址：杭州市体育场路538号金祝大厦, 浙ICP备08005717号</p>
    		</c:when>
    	</c:choose>
    </div>
</div>
</body>
<script type="text/javascript">

	function toRecovery(){
		window.location.href = "${baseUrl}/login/toRecovery?sourceNppCode="+getQueryString("sourceNppCode") ;
	}
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
</script>
</html>