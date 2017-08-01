<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎登录实时保</title>
    <link href="${baseUrl}/resources/css/login/login.css" rel="stylesheet">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${baseUrl}/resources/script/jquerytabs.js"></script>
    <script>
		 function Set(accountCookie){  
			  
   			var Then = new Date() ; 
   			Then.setTime(Then.getTime() + 1*3600000 ); //小时  
   			document.cookie = "account"+"="+accountCookie+";expires="+ Then.toGMTString()+";path=/"; // path 设置可以是cookie跨页面之后不失效
   		 }   
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
        $(document).ready(function () {
            $("input[type=text]，input[type=password]").attr("autocomplete","off");
            $(".loginrg").rTabs({
                btnClass: '.loginnav',
                conClass: '.lgtabscon',
                bind: 'click',
                animation: 'fadein',
                speed: 2000,
                auto: false
            });
            $(document).on("click", ".txnum", function () {
                counttime(90);
            });
            var remobile = /^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
            $(".mobile>input").on("keyup", function () {
                if ($(this).val().match(remobile)) {
                    $("#time").addClass("txnum")
                } else {
                    $("#time").removeClass("txnum")
                }
            });
            
            $('.loginrg').find('input').bind('keydown', function(e) {
				var key = e.which;
				if (key == 13) {
					$('.loginbt').trigger('click');
				}
			});
            
            $(".loginbt").on("click", function () {
                var userid = $.trim($("input[name=userid]").val());
                var password = $.trim($("input[name=password]").val());
                var imgnumber = $.trim($("input[name=imgnumber]").val());
                var ADuser = $.trim($("input[name=admin-userid]").val());
                var ADpass = $.trim($("input[name=admin-password]").val());
                var ADimgnum=$.trim($("input[name=admin-imgnumber]").val());
                var tips = $(".logintips");
                if ($(".userpw").is(":visible") == true) {//普通登录
                	
                	if ($.trim(userid) == "") {
                        $(tips).html("").html("请输入用户名").show().delay(5000).hide(0);
                        return;
                    }  
	                if ($.trim(password) == "") {
	                        $(tips).html("").html("请输入登录密码").show().delay(5000).hide(0);
	                        return;
	                 }  
	                if ($.trim(imgnumber) == "") {
	                        $(tips).html("").html("请输入图形验证码").show().delay(5000).hide(0);
	                        return;
                    }
                    	
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
                            	var data={"account":userid,"passWord":password,"type":"2"};
                            	$.ajax({
                            		url: url, 
                            		data:data,
                            		type:"POST",
                            		success: function(result){
                            			
                                  	if(result.errorMsg!=null){
                                  		 $(tips).html("").html(result.errorMsg).show().delay(5000).hide(0);
                                  		refreshalidateCode();
                                  	}else{
//                                   		var accountCookie= result.userContext.user189.account;
                                  		//把用户名存进cookie
// 										Set(accountCookie);
//                                   		window.location="${baseUrl}/userCenter/toAccount";
                                  		if(result.weakPassword){
											window.location.href="${baseUrl}/login/resetPassword?tip=弱密码-";
										}else{
											window.location.reload();
										}
                                  	}
                                  }});
                          	}
                          }});
                } else {//管理登录
							
                        if (ADuser == "") {
                            $(tips).html("").html("请输入管理员帐号").show().delay(5000).hide(0);
                            return;
                        }
                		if (ADpass=="") {
                            $(tips).html("").html("请输入管理员帐号密码").show().delay(5000).hide(0);
                            return;
                        }  
                        if (ADimgnum==""){
                            $(tips).html("").html("请输入图形验证码").show().delay(5000).hide(0);
                            return;
                        }
                	
                    	 var url="${baseUrl}/login/checkValidateCode";
                    	 var data={"validateCode":ADimgnum};
                    	 $.ajax({
                    		url: url, 
                    		data:data,
                    		type:"POST",
                    		success: function(result){
                          	if(result.success==false){
                          		$(tips).html("").html("图形验证码有误").show().delay(5000).hide(0);
                          		refreshalidateCode();
                          	}else{
                            	var url="${baseUrl}/login/login";
                            	var data={"account":ADuser,"passWord":ADpass,"type":"1"};
                            	$.ajax({
                            		url: url, 
                            		data:data,
                            		type:"POST",
                            		success: function(result){
                            			
                                  	if(result.errorMsg!=null){
                                  		 $(tips).html("").html(result.errorMsg).show().delay(5000).hide(0);
                                  		refreshalidateCode();
                                  	}else{
//                                   		var accountCookie= result.userContext.user189.account;
//                                   		//把用户名存进cookie
// 										Set(accountCookie);
//                                   		window.location="${baseUrl}/userCenter/toAccount";
										if(result.weakPassword){
											window.location.href="${baseUrl}/login/resetPassword?tip=弱密码-";
										}else{
											window.location.reload();
										}
                                  	}
                                  }});
                          		 }
                          	}
                    	 });
                    	
                }
            });
            $(".loginuser>span").on("click", function () {
                $(".loginuserbox").hide();
            })
        });
        //刷新验证码
        function refreshalidateCode(e){
        	var id = Math.random();
        	$(".imgnumber").find('img').attr("src","${baseUrl}/login/getValidateCode?id=" + id);
        }
    </script>
</head>
<body>

<div class="login">
    <div class="loginheader">
        <span class="bao1010logo"></span>
        <span class="pagetitle">欢迎登录公证语音</span>
        <span class="bao1010tel">服务热线：400-821-9388</span>
    </div>
    <div class="loginbox">
        <div class="logincontent">
            <div class="loginlf"><a href="" target="_blank"></a></div>
            <div class="loginrg">
                <div class="loginnav"><span class="current">普通登录</span><span>管理员登录</span></div>
                <div class="logintabs">
                    <div class="lgtabscon">
                        <ul class="userpw" style="display:block;">
                            <li class="userid"><i></i><input type="text" value="" name="userid" maxlength="30" placeholder="请输入用户名"></li>
                            <li class="password"><i></i><input type="password" value="" name="password" maxlength="20" placeholder="请输入登录密码"></li>
                            <li class="imgnumber"><input type="text" value="" name="imgnumber" maxlength="4" placeholder="输入图形验证码"><img src="${baseUrl}/login/getValidateCode" onclick="refreshalidateCode(this)" title="点击刷新"></li>
                        </ul>
                        <ul class="telnum">
                            <li class="userid"><i></i><input type="text" value="" name="admin-userid" maxlength="30" placeholder="请输入管理员帐号"></li>
                            <li class="password"><i></i><input type="password" value="" name="admin-password" maxlength="20" placeholder="请输入管理员帐号密码"></li>
                            <li class="imgnumber"><input type="text" value="" name="admin-imgnumber" maxlength="4" placeholder="输入图形验证码"><img src="${baseUrl}/login/getValidateCode" onclick="refreshalidateCode(this)" title="点击刷新"></li>
                        </ul>
                    </div>
                </div>
                <div class="loginbtbox">
                    <p class="logintips"></p>
                    <span class="loginbt">登 录</span>
                    <p class="signup"><a href="${baseUrl}/login/toRecovery" class="lf">忘记密码？</a><a href="${baseUrl}/activate" class="rg">账户激活</a>
<!--                     <a href="" class="rg">没有公证语音帐号？立即注册</a> -->
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="loginfooter">
        <p>上海人科数据科技有限公司 Copyright © 2012-2015 RenosData Technology Co.,Ltd 沪ICP备13025873号-2</p>
    </div>
</div>
</body>
</html>