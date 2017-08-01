<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>用户中心-公证录音</title>
	<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
	<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
    <link rel="stylesheet" href="${baseUrl}/resources/css/user_center/safeInfo.css">
    <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
    <script src="${baseUrl}/resources/script/scriptAll.js"></script>
    <script src="${baseUrl}/resources/script/jquery.md5.js"></script>
    <script src="${baseUrl}/resources/script/date/laydate.js"></script>
</head>
<body>
<!-- 引入头部 -->
   <input type="hidden" id="navID" value="0">
    <input type="hidden" id="navLF" value="1">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <!-- 引入菜单-->
	<jsp:include page="../commons/left_menu.jsp"></jsp:include>
    <div class="main-right">
        <div class="safeInfo">
            <!--绑定邮箱-->
            <div class="safeEmail">
                <h2>绑定邮箱</h2>
                <div class="emailInfo">
                </div>
            </div>
            <!--END绑定邮箱-->
            <!--绑定手机-->
            <div id="safeMobile" class="safeMobile" style="display:none">
                <h2>绑定手机</h2>
                <div class="mobileInfo">
                </div>
            </div>
            <!--END绑定手机-->
            <!--修改密码-->
            <div class="safePassword" style="">
                <h2>修改密码</h2>
                <div class="passwordInfo">
                    <ul>
                        <li><i>原 密 码 ：</i><input type="password" value="" name="oldPass" placeholder="输入帐号原密码"><b></b></li>
                        <li><i>新 密 码 ：</i><input type="password" value="" name="newsPass" placeholder="不少于8个字符(允许大小字母、数字、字符)"><b id="passStrength" class=""></b><span></span></li>
                        <li><i>确认密码：</i><input type="password" value="" name="bdNewsPass" placeholder="再次输入帐号新密码"><b></b></li>
                    </ul>
                    <p class="passTips"></p>
                    <span class="passNiBt">确定</span>
                </div>
            </div>
            <!--END修改密码-->
        </div>
    </div>
</section>
<footer class="footer"><p>上海人科数据科技有限公司 Copyright © 2012-2016 RenosData Technology Co.,Ltd <a href="http://www.miitbeian.gov.cn/" target="_blank">沪ICP备13025873号-2</a> （建议使用IE9及以上浏览器）</p></footer>
<script src="${baseUrl}/resources/script/safeInfo.js?v=29"></script>
<script type="text/javascript">
	$(function(){
	    //立即更换
	    $(document).on("click",".mobileXg",function(){
	        $mobileInfo.html(mobileData.moOld+mobileXg);
	        clearTimeout(counttimeStop1);
	        $(".mobileXiList").html(mobileData.oldMobile+mobileData.mobileNumber).find("#time1").addClass("txnum").attr('onclick','sendVerificationCode(2)');
	    });
		
	});
	var reMobile = /^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
	var $mobileInfo=$(".mobileInfo");//主体
	var mobileData={'moNull':'<p>未绑定手机，请立即设置，保护账户安全找回帐号密码。</p>','moOld':'<p>您当前绑定的手机号为：<b class="mobileHM">'+associatePhoneNo+'</b>，若已丢失或停用，请 <span class="mobileXg">立即更换</span>。</p>','newMobile':'<li><i>手 机 号 码：</i><input type="email" value="" name="mobileId" placeholder="请输入您当前手机号码"></li>','oldMobile':'<li><i>手 机 号 码：</i><input type="email" value="'+associatePhoneNo+'" readonly></li>','mobileNumber':'<li><i>手机验证码：</i><input type="text" value="" name="mobileNumber" placeholder="输入短信验证码"><span onclick="sendVerificationCode(1)" id="time1" class="">发送验证码</span></li>'};
	var mobileXg='<div class="mobileXiBox"><ul class="mobileXiList"></ul><p class="mobileTips"></p><span class="mobileBt">确定</span> </div>';
	// 判断phoneNo 是不是手机号
	if(isCellPhone){
		$("#safeMobile").hide();
	}else{
		$("#safeMobile").show();
	    if(associatePhoneNo!=null&&associatePhoneNo!=""){//已绑定手机
	        $mobileInfo.html(mobileData.moOld);//默认显示
	
	    }else{//未绑定手机
	        $mobileInfo.html(mobileData.moNull+mobileXg);
	        $(".mobileXiList").html(mobileData.newMobile+mobileData.mobileNumber);
	
	
	    }
	}
	var counttimeStop1;
	function counttime1(time) {
		console.log(time);
	    var _this = $("#time1");
	    if (time > 0) {
	        time--;
	        _this.html("发送成功（" + time + "秒)").removeClass("txnum");
	        counttimeStop1= window.setTimeout("counttime1(" + time+ ")", 1000);
	        
	        $("input[name=mobileId]").attr("readonly",true);
	    } else {
	    	
	    	$("input[name=mobileId]").attr("readonly",false);
	        _this.html("重新发送").addClass("txnum");
	    }
	}

	//发送手机验证码

	function sendVerificationCode(type){
		var classVal=$("#time1").attr("class");
		if(classVal==""){
			return false;
		}
		if(type==1){
			//发送手机验证码
		  	 var url=baseUrl+"/userCenter/sendVerificationCodes";
			console.log(url);
		  	 var mobileId=$("input[name=mobileId]").val();
		  	 var smsTemplateCode="SMS_58245175";
		  	 var userType=$("#userType").val();
			 
			 var data={"associatePhoneNo":mobileId,"userType":userType,"smsTemplateCode":smsTemplateCode};
			 $.ajax({
				url: url, 
				data:data,
				type:"POST",
				async:true,
				success: function(result){
					if(result.respCode!="0000"){
						var re =/InvalidSendSms : Frequency limit reaches./;
						if(re.test(result.respDesc)){
							  swal("","发送频率极限达到。请稍后再发。");
						}else{
							swal("",result.respDesc);
						}
					}else{
						counttime1(40);
					}
				},error:function(result){
					alert(result.respDesc);
				}
			 });
		}else if(type==2){
			//发送手机验证码
		  	var url=baseUrl+"/userCenter/sendVerificationCodes";
		  	 var userType=$("#userType").val();
			 var data={"associatePhoneNo":associatePhoneNo,"userType":userType,"smsTemplateCode":"SMS_58465020"};
			 $.ajax({
				url: url, 
				data:data,
				type:"POST",
				async:true,
				success: function(result){
					if(result.respCode!="0000"){
						var re =/InvalidSendSms : Frequency limit reaches./;
						if(re.test(result.respDesc)){
							  swal("","发送频率极限达到。请稍后再发。");
						}else{
							swal("",result.respDesc);
						}
					}else{
						counttime1(40);
					}
				},error:function(result){
					alert(result.respDesc);
				}
			 });

		}else if(type==3){
			//发送手机验证码
		  	 var url=baseUrl+"/userCenter/sendVerificationCodes";
		  	 var userType=$("#userType").val();
		  	 var mobileId=$("input[name=mobileId]").val();
			 var data={"associatePhoneNo":mobileId,"userType":userType,"smsTemplateCode":"SMS_58245175"};
			 $.ajax({
				url: url, 
				data:data,
				type:"POST",
				async:true,
				success: function(result){
					if(result.respCode!="0000"){
						var re =/InvalidSendSms : Frequency limit reaches./;
						if(re.test(result.respDesc)){
							  swal("","发送频率极限达到。请稍后再发。");
						}else{
							swal("",result.respDesc);
						}
					}else{
						counttime1(40);
					}
				},error:function(result){
					alert(result.respDesc);
				}
			 });
		}
		
	}
    // 绑定手机校验短信验证的确定按钮
    $(document).on("click",".mobileBt",function(){
    	
        var inputNumb=$("input[name=mobileNumber]").val();//验证码
        var mobileDz=$("input[name=mobileId]").val();//手机号码
		 var mobileTips=$(".mobileTips");
		 if(mobileDz==""){
			 mobileTips.html("手机号不能为空");
			 return false;
		 }
		 if(inputNumb==""){
			 mobileTips.html("*手机验证码为空，请正确填写");
			 return false;
		 }
    	
    	if(associatePhoneNo!=null&&associatePhoneNo!=""){
     		 //手机校验验证码
	         var url="${baseUrl}/userCenter/checkVerificationCodes";
	         var data={"associatePhoneNo":associatePhoneNo,"userType":userType,"smsTemplateCode":"SMS_58465020","verificationCode":inputNumb};
          	 $.ajax({
         		url: url, 
         		data:data,
         		type:"POST",
         		success: function(result){
         			if(result.respCode!="0000"){
   						var re =/验证码已过期/;
   						if(re.test(result.respDesc)){
   							mobileTips.html("验证码已过期，请重新发送！");
   						}else{
   							mobileTips.html(result.respDesc);
   						}
               	}else{
               		clearTimeout(counttimeStop1);
               		$(".mobileXiList").html(mobileData.newMobile+mobileData.mobileNumber);
               		$("#time1").html("发送验证码").attr('onclick','sendVerificationCode(3)');
                    $(".mobileBt").attr("class","mobileZcBt").html("提交修改");
               		 
               		}
         		}
         	 });  

            
            
            
    	}else{
            var mobileTips=$(".mobileTips");
            var inputNumb=$("input[name=mobileNumber]").val();//验证码
            var mobileDz=$("input[name=mobileId]").val();//手机号码
      		 //手机校验验证码
	         var url="${baseUrl}/userCenter/checkVerificationCodes";
	         var data={"associatePhoneNo":mobileDz,"userType":userType,"smsTemplateCode":"SMS_58245175","verificationCode":inputNumb};
          	 $.ajax({
          		url: url, 
          		data:data,
          		type:"POST",
          		success: function(result){
          			if(result.respCode!="0000"){
    						var re =/验证码已过期/;
    						if(re.test(result.respDesc)){
    							 
    							mobileTips.html("验证码已过期，请重新发送！");
    						}else{
    							mobileTips.html(result.respDesc);
    						}
                	}else{
        				 //绑定手机号
	        			 var url=baseUrl+"/userCenter/updateAssociatePhoneNo4User";
	                   	 $.ajax({
	                     		url: url, 
	                     		data:{'bindAssociatePhoneNo':mobileDz},
	                     		type:"POST",
	                     		success: function(result){
	                     			console.log(result);
		                     		if(result.respCode!="0000"){
		                   				if(result.respDesc=="缺少用户上下文"){
		                   					window.location.reload();//刷新当前页面.
		                   				}
		                     			mobileTips.html(result.respDesc);   
		                           	}else{
		                           	      //绑定手机号
		                      		       $(".mobileXiBox").html("<h1>手机绑定成功</h1>").show(8000).delay(2000).hide(0).prev("p").remove();
		                                   $(".mobileInfo").prepend(mobileData.moOld).find(".mobileHM").html(mobileDz);
		                                   associatePhoneNo=mobileDz;
		                                   mobileData={'moNull':'<p>未绑定手机，请立即设置，保护账户安全找回帐号密码。</p>','moOld':'<p>您绑定的手机号为：<b class="mobileHM">'+associatePhoneNo+'</b>，若已丢失或停用，请 <span class="mobileXg">立即更换</span>。</p>','newMobile':'<li><i>手 机 号 码：</i><input type="email" value="" name="mobileId" placeholder="请输入手机号码"></li>','oldMobile':'<li><i>手 机 号 码：</i><input type="email" value="'+associatePhoneNo+'" readonly></li>','mobileNumber':'<li><i>手机验证码：</i><input type="text" value="" name="mobileNumber" placeholder="输入短信验证码"><span onclick="sendVerificationCode(2)" id="time1" class="">发送验证码</span></li>'};
		                           	}
	                     		}
	                     	 });
                		
                		 
                	}
          		}
          	 }); 
    	}

    });
    //提交修改
    $(document).on("click",".mobileZcBt",function(){
        
        var inputNumb=$("input[name=mobileNumber]").val();//验证码
        var mobileDz=$("input[name=mobileId]").val();//手机号码
        var mobileTips=$(".mobileTips");
        
        var url="${baseUrl}/userCenter/checkVerificationCodes";
        var data={"associatePhoneNo":mobileDz,"userType":userType,"smsTemplateCode":"SMS_58245175","verificationCode":inputNumb};
     	 $.ajax({
     		url: url, 
     		data:data,
     		type:"POST",
     		success: function(result){
     		if(result.respCode!="0000"){
						var re =/验证码已过期/;
						if(re.test(result.respDesc)){
							 
							mobileTips.html("验证码已过期，请重新发送！");
						}else{
							mobileTips.html(result.respDesc);
						}
           	}else{
       		 	 //绑定手机号
	       		 var url=baseUrl+"/userCenter/updateAssociatePhoneNo4User";
	             	 $.ajax({
	               		url: url, 
	               		data:{'bindAssociatePhoneNo':mobileDz},
	               		type:"POST",
	               		success: function(result){
	               			console.log(result);
	                   		if(result.respCode!="0000"){
	                   				if(result.respDesc=="缺少用户上下文"){
	                   					window.location.reload();//刷新当前页面.
	                   				}
	                   				mobileTips.html(result.respDesc);   
	                         	}else{
	                         	      //绑定手机号
	                        	      $(".mobileXiBox").html("<h1>手机绑定成功</h1>").show(8000).delay(2000).hide(0);
	                 				  $(".mobileHM").html(mobileDz);
	                                 associatePhoneNo=mobileDz;
	                                 mobileData={'moNull':'<p>未绑定手机，请立即设置，保护账户安全找回帐号密码。</p>','moOld':'<p>您绑定的手机号为：<b class="mobileHM">'+associatePhoneNo+'</b>，若已丢失或停用，请 <span class="mobileXg">立即更换</span>。</p>','newMobile':'<li><i>手 机 号 码：</i><input type="email" value="" name="mobileId" placeholder="请输入手机号码"></li>','oldMobile':'<li><i>手 机 号 码：</i><input type="email" value="'+associatePhoneNo+'" readonly></li>','mobileNumber':'<li><i>手机验证码：</i><input type="text" value="" name="mobileNumber" placeholder="输入短信验证码"><span onclick="sendVerificationCode(2)" id="time1" class="">发送验证码</span></li>'};
	                         	
	                         	}
	               		}
	               	 });

           		
           		 
           		}
     		}
     	 }); 
        
        
        
        

        
        
        

    });
    </script>

</body>
</html>