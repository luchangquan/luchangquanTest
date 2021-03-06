<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>重置密码-通话录音保全存证工具</title>
     <link href="${baseUrl}/resources/css/login/login.css" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
         <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="login">
    <div class="loginheader">
        <span class="bao1010logo"></span>
        <span class="pagetitle">弱密码-重置密码</span>
        <span class="bao1010tel">服务热线：400-821-9388</span>
    </div>
    <div class="recovery">
        <div class="recovery01">
            <p>输入帐号</p>
            <ul>
              
                <li><i>帐号</i><input type="text" data-class="regIn" name="userId" value=""><b></b></li>
                <li><i>验证码</i><input type="text" data-class="regIn" name="imgNumber" value=""><b></b></li>
                <li class="imgNum paddLf10"><span>请按下图输入验证码，看不清请 <a  class="sImg">刷新验证码</a></span><img id="imgCode" src="${baseUrl}/login/getValidateCode"></li>
            </ul>
        </div>
        <div class="recovery02">
            <p>重置方式</p>
            <ul>
                <li class="userId"><i>帐号</i><span id="userIdText"></span></li>
                <li class="reType paddLf10"><i>重置方式</i>
                    <label id="phoneNoLable"><input type="radio" id="phone"  name="reType" value="1"><i></i><span id="phoneText"></span></label>
                    <label id="targetEmailLable"><input type="radio" id="targetEmai" name="reType" value="2"><i></i><span id="emailText"></span></label>
                    <b class="adminType">若忘记手机或邮箱，请拨打客服电话：400-821-9388</b><!--管理员-->
                    <b class="userType">若忘记手机或邮箱，请联系贵公司的管理人员重置密码。</b><!--成员-->
                    <b class="userTis">很遗憾，您未设置任何密码保护措施，无法通过系统重置密码。<br/>请联系贵公司的管理人员重置密码。</b><!--成员没有绑定手机-->
                </li>
            </ul>
        </div>
        <div class="recovery03">
            <p>填写验证码</p>
            <div><i>手机或邮箱验证码</i><input type="text" data-class="regIn" name="numberE" value=""><b></b></div>
        </div>
        <div class="recovery04">
            <p>重置密码</p>
            <ul>
            	<ul class="reliTips">
                    <li>为保障您的帐号安全，请按照如下规则设定密码：</li>
                    <li>1、密码长度不少于八位字符</li>
                    <li>2、密码必须包含大写和小写字母、数字、特殊字符。</li>
                </ul>
                <li><i>新密码</i><input type="password" data-class="regIn" name="passNews" value=""><b></b></li>
                <li><i>再次输入</i><input type="password" data-class="regIn" name="passNewsD" value=""><b></b></li>
            </ul>
        </div>
        <div class="recovery05">
            <div>恭喜您，重置密码成功！返回<a href="javascript:;" onclick="reLogin();">重新登录</a></div>
        </div>
        <p class="recoverBt"><span class="nextBt">下一步</span><span class="backBt">返回</span><span class="buyOk nextBt">确定</span></p>
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
<script type="text/javascript">
	function reLogin(){
		window.location.href = "${baseUrl}/?sourceNppCode="+getQueryString("sourceNppCode") ;
	}
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
</script>
<script>
	var phoneNo=""; //全局变量 手机
	var recoveryin="";//账号类型
	var phoneNoHide="";
	var targetEmail="";//全局变量 邮箱账号
	var targetEmailHide="";
    $(document).ready(function(){
    
        //下一步
        $(".nextBt").on("click",function(){
            var vidDiv=$(".recovery div:visible");//当前显示
            var vidDivCla=vidDiv.attr("class");
            var vidDivClain=vidDiv.find("input[data-class=regIn]");
         
            var userIdin=$("input[name=userId]").val();//帐号
            var imgNumberin=$("input[name=imgNumber]").val();//图形验证码
            var numberEin=$("input[name=numberE]").val();//短信或邮箱验证码
            var passNewsin=$("input[name=passNews]").val();//新密码
            var passNewsDin=$("input[name=passNewsD]").val();//重复密码
            var inputreg=function(a){
                var bVal= a.val();
                var cTit= a.prev("i").text();
                if(bVal==""){
                    a.next("b").html(cTit+"不能为空");
                }
            };
            $.each(vidDivClain,function(){
                var _this=$(this);
                inputreg(_this);
            });
            switch(vidDivCla){
                case "recovery01":
               	 //校验图形验证码
                       if(userIdin!=""&&imgNumberin!=""){
                           console.log("查询帐号是否是用户咯");
                           //是用户
                           $("#userIdText").html("<b>"+userIdin+"</b>");
                           	 var url="${baseUrl}/login/checkValidateCode";
                           	 var data={"validateCode":imgNumberin};
                           	 $.ajax({
                           		url: url, 
                           		data:data,
                           		type:"POST",
                           		async:false,
                           		success: function(result){
                           			if(result.success==false){
                           				refreshalidateCode();
                           				$("input[name=imgNumber]").next("b").html("图形验证码错误！");
                           				$("input[name=userId]").next("b").html("");
                           			}else{
                           				
                           				//根据账号获取信息
                           				var url="${baseUrl}/userCenter/getUserInfo";
                           				var data={"account":userIdin};
                                      	 $.ajax({
                                        		url: url, 
                                        		data:data,
                                        		type:"POST",
                                        		async:false,
                                        		success: function(result){
                                        			console.log(result);
                                        			if(result.respCode!="0000"){
                                        				refreshalidateCode();
                                        				$("input[name=userId]").next("b").html(result.respDesc);
                                        				$("input[name=imgNumber]").next("b").html("");
                                        			}else{
                                        				phoneNo=result.data.phoneNo;
                                        				targetEmail=result.data.email;
                                        				recoveryin=result.data.type;
                                        			
                                        				if(phoneNo!=null&&phoneNo!=""){
                                        					phoneNoHide=phoneNo.substr(0, 3)+"****"+phoneNo.substr(7, 4);
                                        					//var ss = s.substr(12, 5); // 获取子字符串, 从下表12开始, 截取5个字符
                                        				}
														if(targetEmail!=null&&targetEmail!=""){
															
															
															var index= targetEmail.indexOf("@");
															var target=targetEmail.substring(index, targetEmail.length);
															 
															targetEmailHide=targetEmail.substr(0, 3)+"****"+target;
                                        				}
                                        				console.log(result);
                                        				
                                        				$("#phoneText").text("将验证方式发送至绑定手机："+phoneNoHide);
                                        				$("#emailText").text("将验证方式发送至绑定邮箱："+targetEmailHide);
                                        				
                                        				var reg=/^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
                                        				console.log("++++++++++"+phoneNo.match(reg));

                                                        if(recoveryin=="2"){//成员帐号
                                                            if(phoneNo==""&&targetEmail==""){
                                                                //未绑定
                                                                $(".userTis").show().prevAll("label").hide();
                                                                $(".backBt").show().prev().hide();
                                                                
                                                            }else{
                                                            	if(phoneNo==""||phoneNo==null){
                                                            		$("#phoneNoLable").hide();
                                                            		$("input[name=reType]:eq(1)").attr("checked",'checked'); 
                                                            	}
                                                            	if(targetEmail==""||targetEmail==null){
                                                            		$("#targetEmailLable").hide();
                                                            		$("input[name=reType]:eq(0)").attr("checked",'checked'); 
                                                            	}
                                                            	 //已绑定
                                                                $(".userType").show();
                                                               
                                                                
                                                				if(!phoneNo.match(reg)){
                                                					$("#phoneNoLable").hide();
                                                            		$("input[name=reType]:eq(1)").attr("checked",'checked'); 
                                                				}else{
                                                					 $("input[name=reType]:eq(0)").attr("checked",'checked'); 
                                                				}
                                                            }
                                                        
                                                        }else{//管理员帐号
                                                        	if(phoneNo==""||phoneNo==null){
                                                        		$("#phoneNoLable").hide();
                                                        		$("input[name=reType]:eq(1)").attr("checked",'checked'); 
                                                        	}
                                                        	if(targetEmail==""||targetEmail==null){
                                                        		$("#targetEmailLable").hide();
                                                        		$("input[name=reType]:eq(0)").attr("checked",'checked'); 
                                                        	}
															
                                                        	
                                                        	if(!phoneNo.match(reg)){
                                                        		$("#phoneNoLable").hide();
                                                        		$("input[name=reType]:eq(1)").attr("checked",'checked'); 
                                                        	}else{
                                                        		$("input[name=reType]:eq(0)").attr("checked",'checked'); 
                                                        	}
                                                            $(".adminType").show();
                                                            
                                                        
                                                        }
                                        				$(vidDiv).hide().next().show();
                                        			}
                                             	
                                              }});
                           			}
                                	
                                 }});
                       }
                    break;
                case "recovery02":
                		
				 			 var reType= $("input[name='reType']:checked").val();
                			 
                           	 if(reType==1){
                     			 //发送手机验证码
                    	         var url="${baseUrl}/login/sendVerificationCode";
                               	 var data={"account":userIdin,"userType":recoveryin,"smsTemplateCode":"SMS_42820123"};
                               	 $.ajax({
                               		url: url, 
                               		data:data,
                               		type:"POST",
                               		async:true,
                               		success: function(result){
                            			if(result.respCode!="0000"){
                            				alert(result.respDesc);
                            			}else{
                            				$(vidDiv).hide().next().show();
                            			}
                               		},error:function(result){
                               			alert(result.respDesc);
                               		}
                               	 }); 
                           		 
                           	 }else{
                     			 //邮箱验证码
                    	         var url="${baseUrl}/login/sendVerificationMailCode";
                               	 var data={"targetEmail":targetEmail,"userType":recoveryin,"smsTemplateCode":"retrieve_password"};
                               	 $.ajax({
                               		url: url, 
                               		data:data,
                               		type:"POST",
                               		async:true,
                               		success: function(result){
                            			if(result.respCode!="0000"){
                            				alert(result.respDesc);
                            			}else{
                            				$(vidDiv).hide().next().show();
                            			}
                               		},error:function(result){
                               			alert(result.respDesc);
                               		}
                               	 }); 
                           	 } 
                   
                    break;
                case "recovery03":
                        if(numberEin!=""){
 							 var reType= $("input[name='reType']:checked").val();
                           	 if(reType==1){
                        	
                       		 //手机校验验证码
               	       		 var url="${baseUrl}/login/checkVerificationCode";
               	        	 var data={"account":userIdin,"userType":recoveryin,"smsTemplateCode":"SMS_42820123","verificationCode":numberEin};
	                       	 $.ajax({
	                       		url: url, 
	                       		data:data,
	                       		type:"POST",
	                       		success: function(result){
	                       			console.log(result);
	                       			if(result.respCode!="0000"){
	                       				$("input[name=numberE]").next("b").html(result.respDesc);
	                             	}else{
	                             		 $(vidDiv).hide().next().show();
	                                     $(".buyOk").show().prevAll().hide();
	                             	}
	
	                       		}
	                       	 }); 
                           	 }else{
                           		 //邮箱校验验证码
                   	       		 var url="${baseUrl}/login/checkVerificationMailCode";
                   	        	 var data={"targetEmail":targetEmail,"userType":recoveryin,"smsTemplateCode":"retrieve_password","verificationCode":numberEin};
                   	        	 console.log(data);
    	                       	 $.ajax({
    	                       		url: url, 
    	                       		data:data,
    	                       		type:"POST",
    	                       		success: function(result){
    	                       			console.log(result);
    	                       			if(result.respCode!="0000"){
    	                       				$("input[name=numberE]").next("b").html(result.respDesc);
    	                             	}else{
    	                             		 $(vidDiv).hide().next().show();
    	                                     $(".buyOk").show().prevAll().hide();
    	                             	}
    	
    	                       		}
    	                       	 }); 
                           		 
                           		 
                           	 }
                       }
                    break;
                default:
                        if(passNewsin!=""&&passNewsDin!=""){
                            if(passNewsDin==passNewsin){
                        	    var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
                        	    var enoughRegex = new RegExp("(?=.{8,}).*", "g");
                        	    if (false == enoughRegex.test(passNewsin)) {
                        	    	$("input[name=passNews]").next("b").html("密码不少于8个字符");
                        	    	return false;
                        	    } 
	                      	     if (!strongRegex.test(passNewsin)) {
	                      	    	$("input[name=passNews]").next("b").html("密码必须包含大写和小写字母、数字、特殊字符");
	                      	        return false;
	                      	     }
                        	   
                            	
                          		 //邮箱校验验证码
                	       		 var url="${baseUrl}/login/updatePassword";
                	        	 var data={"account":userIdin,"password":passNewsDin,"userType":recoveryin};
	   	                       	 $.ajax({
	   	                       		url: url, 
	   	                       		data:data,
	   	                       		type:"POST",
	   	                       		success: function(result){
	   	                       			console.log(result);
	   	                       			if(result.respCode!="0000"){
	   	                       				$("input[name=passNewsD]").next("b").html(result.respDesc);
	   	                             	}else{
		   	                             	 $(vidDiv).hide().next().show();
		   	                                $(".buyOk").hide();
	   	                             	}
	   	
	   	                       		}
	   	                       	 }); 

                               
                            }else{
                                $("input[name=passNewsD]").next("b").html("再次输入密码不正确");
                            }
                        }
                    break;

            }
        });
        //返回
        $(".backBt").on("click",function(){
           $(this).hide().prev().show();
            $(".recovery div:visible").hide().prev().show();
        });
        //聚焦
        $("input[data-class=regIn]").on("focus",function(){
            $(this).next("b").html("");
        });
        //刷新验证码
        $(".sImg").on("click",function(){
        	refreshalidateCode();
        });
    })
            //刷新验证码
       function refreshalidateCode(e){
       	var id = Math.random();
       	$("#imgCode").attr("src","${baseUrl}/login/getValidateCode?id=" + id);
       }
</script>
</body>
</html>