<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <title>用户中心</title>
	    <!--共用部分-->
	    <link href="${baseUrl}/resources/css/head.css" rel="stylesheet">
	    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
	    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
	    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
	    <script src="${baseUrl}/resources/js/manage.js?v=4"></script>
	    <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">
	
	    <!--单独部分-->
	    <link href="${baseUrl}/resources/css/userCenter/user.css" rel="stylesheet">
	    <link href="${baseUrl}/resources/css/userCenter/info.css" rel="stylesheet">
	
	
	</head>
	<body>
	<input value="${userContext.user.account}" id="account" type="hidden"/>
	 <input value="${userContext.sourceNppCode}" id="sourceNppCode" type="hidden"/>
	<input type="hidden" id="navOn" value="5">
	<header class="headerBox">
	</header>
	<!--NAV-->
	<nav class="navBox">
	</nav>
	<nav class="menuBox">
	    <ul>
 
        	<li class="twOn"><a href="#">基本信息</a></li>
	       <!--  <li><a href="#">数据统计</a></li> -->
	<!--        <li><a href="#">企业信息</a></li>
	        <li><a href="#">成员管理</a></li>
	        <li><a href="#">安全设置</a></li>-->
	    </ul>
	</nav>
	<!--NAV-->
	<!--主体-->
	<section class="main">
	    <div class="contentBox">
	        <h1 class="BoxTitle ">用户中心 - <i class="typeOn">基本信息</i></h1>
	        <div class="ltdInfoBox borBacPadd" id="company">
	            <ul>
	                <div class="info-title">企业信息</div>
	                <li>
	                    <span>企业名称：</span><input id="companyName" type="text" readonly="readonly" value="人科数据科技有限公司" />
	                </li>
	                <li>
	                    <span>证件号码：</span><input id="licenseNo" type="text" readonly="readonly" value="营业执照号码" />
	                </li>
	                <li>
	                    <span>联系电话：</span><input id="contractPhoneNo" type="text" readonly="readonly" value="021-64336050" />
	                </li>
	                <li>
	                    <span>联系地址：</span><input id="address" type="text" readonly="readonly" value="上海市长宁区xxxdsa" />
	                </li>
	            </ul>
	        </div>
	        <div class="userInfoBox borBacPadd">
	            <ul>
	                <div class="info-title">账户信息</div>
	                <li>
	                    <span>姓　　名：</span><input id="name" type="text" readonly="readonly" value="" />
	                </li>
	                <li>
	                    <span>身份证号：</span><input id="credentials_no" type="text" readonly="readonly" maxlength="18" value="" />
	                </li>
	                <li>
	                    <span>手机号码：</span><input id="associate_phone_no" type="text" readonly="readonly" name="telephone" maxlength="11" value="" />
	                </li>
	                <li>
	                    <span>邮箱地址：</span><input id="email" type="text" readonly="readonly" name="email" maxlength="30" value="" />
	                    <i class="emailXG">修改</i>
	                </li>
	            </ul>
	        </div>
	        <div class="passInfo borBacPadd">
	            <ul id="passwordInfo">
	                <div class="info-title">修改密码</div>
	                <li>
	                    <ul class="passTips"><!--激活帐号专属2017 03 17-->
	                        <li>为保障您的帐号安全，请按照如下规则设定密码：</li>
	                        <li>1、密码长度不少于八位字符</li>
	                        <li>2、密码可包含大写和小写字母、数字、特殊字符。</li>
	                    </ul>
	                </li>
	                <li>
	                    <span>原 密 码 ：</span><input type="password" value="" name="passOld" placeholder="输入帐号原密码" /><b></b>
	                </li>
	                <li>
	                         <span>新 密 码 ：</span><input type="password" value="" name="passNews" placeholder="设置帐号新密码" /><i id="passStrength"></i><b></b>
	                </li>
	                <li>
	                    <span>确认密码：</span><input type="password" value="" name="passNewsQ" placeholder="再次输入新密码" /><b></b>
	                </li>
	            </ul>
	            <div class="passwordBtBox"><span class="passwordBt">确定修改</span></div>
	        </div>
	
	    </div>
	    <footer class="footerM"></footer>
	</section>
	
	
	<!--修改手机/邮箱-->
	<section class="infoTelMailbox" >
	    <div class="infoTelMail">
	        <div class="infoTelMailTitle"><h2></h2><span class="InfoTelMailClose" title="关闭"></span></div>
	        <div class="infoTelMailContent">
	
	        </div>
	    </div>
	</section>
	<!--END修改手机/邮箱-->
	<script src="${baseUrl}/resources/js/userCenter/infoPassword.js?v=16"></script>
	<script type="text/javascript">
	 	
	
	
	
		$(function(){
			
			
			
			
		    //修改邮箱
		    $(document).on("click",".emailXG",function(){
		        var email=$("input[name=email]").val();
		        $infoTelMailbox.show();
		        $infoTelMailTitleH2.html("修改邮箱地址");
		        $infoTelMailContent.html(emailXg);

		        if(email!=""){//已绑定
		            $(".emailXiList").html(emailData.emailOld+emailData.emailNumber);
		            $("#time").addClass("txnum");
		        }else{//未绑定
		            $(".emailXiList").html(emailData.emailNull+emailData.emailNew+emailData.emailNumber);
		        }
		    });
		    //验证邮箱
		    $(document).on("keyup","input[name=emailId]",function(){
		        if ($(this).val().match(reEmail)) {
		            $("#time").addClass("txnum");
		        }else {
		            $("#time").removeClass("txnum");
		        }
		    });
		    //焦点
		    $(document).on("focus","input[name=emailNumber]",function(){
		        $(".emailTips").html("");
		    });
		    //确定
		    $(document).on("click",".emailBt",function(){
		        var emailTips=$(".emailTips");
		        if(emailBox!="") {//已绑定
					 //校验邮箱验证码
					 var inputNumb=$("input[name=emailNumber]").val();
		        	 if(inputNumb==""){
		        		 emailTips.html("验证码不能为空");   
		        		 return false;
		        	 }
				   
				   	 var dataJson={"targetEmail":emailBox,"userType":type,"smsTemplateCode":"modify_bind_email_code","verificationCode":inputNumb};
				   	 console.log(type);
				   	 var url=baseUrl+"/userCenter/checkVerificationMailCode";
			    	 $.ajax({
			    		url: url, 
			    		data:dataJson,
			    		type:"POST",
			    		success:function(result){
			    			console.log(result);
			    			if(result.respCode!="0000"){
								var re =/[验证码已过期，请刷新后操作]/;
								if(re.test(result.respDesc)){
			    					emailTips.html("验证码已过期,请重新发送");   
			    				}else{
			    					emailTips.html(result.respDesc);   
			    				}
			    				
			    			}else{
		 		                $(".emailXiList").html(emailData.emailNew+emailData.emailNumber);
		 		               	$(".emailBt").attr("class","emailZcBt").html("提交修改");
			    			}
			    		},error:function(e){
			    			alert(e);
			    		}
			    	 });
		        
		        }else{//未绑定
					 //校验邮箱验证码
		        	 var emailDz=$("input[name=emailId]").val();//邮箱
				   	 var inputNumb=$("input[name=emailNumber]").val();
					 if(inputNumb==""){
		        		 emailTips.html("验证码不能为空");   
		        		 return false;
		        	 }
				   	 var dataJson={"targetEmail":emailDz,"userType":type,"smsTemplateCode":"bind_email_code","verificationCode":inputNumb};
				   	 console.log(type);
				   	 var url=baseUrl+"/userCenter/checkVerificationMailCode";
			    	 $.ajax({
			    		url: url, 
			    		data:dataJson,
			    		type:"POST",
			    		success:function(result){
			    			console.log(result);
			    			if(result.respCode!="0000"){
								var re =/[验证码已过期，请刷新后操作]/;
								if(re.test(result.respDesc)){
			    					emailTips.html("验证码已过期,请重新发送");   
			    				}else{
			    					emailTips.html(result.respDesc);   
			    				}   
			    			}else{
		         				 //绑定邮箱
		         				 var url=baseUrl+"/userCenter/updateEmail4User";
		                    	 $.ajax({
		                      		url: url, 
		                      		data:{'bindEmail':emailDz},
		                      		type:"POST",
		                      		success: function(result){
		                      			
		                      			if(result.respCode!="0000"){
		                      				emailTips.html(result.respDesc);   
		                            	}else{
		    		 		                $(".emailXiBox").html("<h1>恭喜你，邮箱绑定成功！</h1>");
		    		 		                $("input[name=email]").val(emailDz);
		    		 		                $infoTelMailbox.delay(3000).hide(0);
		    		 		                emailBox=emailDz;
		    		 		                emailData={'emailNull':'<p>未绑定邮箱，请立即设置，保护账户安全,方便找回帐号密码。</p>','emailNew':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱地址"></li>','emailOld':'<li><i>邮 箱 地 址：</i><input type="email" value="'+emailDz+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span  id="time" class="">发送验证码</span></li>'};
		                            	}
		                      		}
		                      	 });
			    				
			    				

			    			}
			    		},error:function(e){
			    			alert(e);
			    		}
			    	 });
		        }
		      //  clearTimeT();
		    });
			
		    //提交修改
		    $(document).on("click",".emailZcBt",function(){
		        var inputNumb=$("input[name=emailNumber]").val();//验证码
	        
		        var emailDz=$("input[name=emailId]").val();//邮箱
		        var emailTips=$(".emailTips");
			   	 if(inputNumb==""){
	        		 emailTips.html("验证码不能为空");   
	        		 return false;
	        	 }
			   	 var dataJson={"targetEmail":emailDz,"userType":type,"smsTemplateCode":"bind_email_code","verificationCode":inputNumb};
			   	 var url=baseUrl+"/userCenter/checkVerificationMailCode";
		    	 $.ajax({
		    		url: url, 
		    		data:dataJson,
		    		type:"POST",
		    		success:function(result){
		    			console.log(result);
		    			if(result.respCode!="0000"){
		    				
							var re =/[验证码已过期，请刷新后操作]/;
							if(re.test(result.respDesc)){
		    					emailTips.html("验证码已过期,请重新发送");   
		    				}else{
		    					emailTips.html(result.respDesc);   
		    				}
		    			}else{
	         				 //绑定邮箱
	         				 var url=baseUrl+"/userCenter/updateEmail4User";
	                    	 $.ajax({
	                      		url: url, 
	                      		data:{'bindEmail':emailDz},
	                      		type:"POST",
	                      		success: function(result){
	                      			
	                      			if(result.respCode!="0000"){
	                      				emailTips.html(result.respDesc);   
	                            	}else{
	    		 		                $(".emailXiBox").html("<h1>恭喜你，邮箱绑定成功！</h1>");
	    		 		                $("input[name=email]").val(emailDz);
	    		 		                $infoTelMailbox.delay(3000).hide(0);
	    		 		                emailBox=emailDz;
	    		 		                emailData={'emailNull':'<p>未绑定邮箱，请立即设置，保护账户安全,方便找回帐号密码。</p>','emailNew':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱地址"></li>','emailOld':'<li><i>邮 箱 地 址：</i><input type="email" value="'+emailDz+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span  id="time" class="">发送验证码</span></li>'};
	                            	}
	                      		}
	                      	 });
		    				
		    				

		    			}
		    		},error:function(e){
		    			alert(e);
		    		}
		    	 });
		        
		    });



		    //发送验证码
		    $(document).on("click","#time.txnum",function(){
		        var thisName=$(this).prevAll("input").attr("name");
		        
		        if(thisName=="telNumber"){//调发送手机验证码
		            console.log("这是要验证手机");
		        }else{//调发送邮箱验证码
		   	  	 	var emailDz="";//邮箱
		   	  	 	var smsTemplateCode="";
			        if(emailBox==""){
			        	emailDz=$("input[name=emailId]").val();//邮箱
			        	smsTemplateCode="bind_email_code";
			        	
			        }else{
			        	emailDz=emailBox;//邮箱
			        	smsTemplateCode="modify_bind_email_code";
			        }
			        if($(".emailZcBt").html()=="提交修改"){
			        	emailDz=$("input[name=emailId]").val();//邮箱
			        	smsTemplateCode="bind_email_code";
			        }
			    	 //发送邮箱验证码
			   	  	 var url=baseUrl+"/userCenter/sendVerificationMailCode";
			
			   		 var data={"targetEmail":emailDz,"userType":type,"smsTemplateCode":smsTemplateCode};
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
			   					counttime(40);
			   				}
			   			},error:function(result){
			   				alert(result.respDesc);
			   			}
			   		 });
		        }
		    });
			
			
			
		    //关闭
		    $(".InfoTelMailClose").on("click",function(){
		        $infoTelMailbox.hide();
		        $infoTelMailContent.html("");
		        clearTimeT();
		    });
			
		});
	


		//倒计时
		var X;
		function counttime(time) {
		    var _this = $("#time");
		    if (time > 0) {
		        time--;
		        _this.html("发送成功（" + time + "秒)").removeClass("txnum");
		        X=window.setTimeout("counttime(" + time+ ")", 1000);
		        $("input[name=emailId]").attr("readonly",true);
		    } else {
		    	 $("input[name=emailId]").attr("readonly",false);
		        _this.html("重新发送").addClass("txnum");
		    }
		}
		function clearTimeT(){
		    clearTimeout(X);
		}	
		
		

	</script>
	</body>
	</html>


