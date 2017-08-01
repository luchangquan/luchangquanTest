/**
 * Created by kingM on 2017/3/24.
 */
var userEmail="";
//获取用户信息
var url=baseUrl+"/userCenter/getUserInfo";
var associatePhoneNo="";
var account="";
var password="";
var isCellPhone="";
console.log(userType);
	$.ajax({
	url : url,
	type : "post",
	async : false,
	success : function(responseJson) {
		console.log(responseJson);
		userEmail=responseJson.baseEnhanced.email;
		associatePhoneNo=responseJson.baseEnhanced.associatePhoneNo;
		account=responseJson.baseEnhanced.account;
		password=responseJson.baseEnhanced.password;
		password=responseJson.baseEnhanced.password;
		isCellPhone=responseJson.isCellPhone;
		console.log(isCellPhone);
	},
	error : function(msg) {
		alert(msg);
	}
}); 
//绑定邮箱
var reEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;//邮箱正则表达式
var $emailInfo=$(".emailInfo");//主体
var emailData={'wbd':'<p>未绑定邮箱，请立即设置，保护账户安全找回用户名密码，语音记录批量下载。</p>','ybd':'<p>您当前绑定的邮箱账号为：<b class="emailDz">'+userEmail+'</b>，若已丢失或停用，请 <span class="emailXg">立即更换</span>。</p>','newEmail':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入新的邮箱地址"></li>','oldEmail':'<li><i>邮 箱 地 址：</i><input type="email" value="'+userEmail+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span onclick="sendVerificationMailCode(1)" id="time" class="">发送验证码</span></li>'};
var emailXg='<div class="emailXiBox"><ul class="emailXiList"></ul><p class="emailTips"></p><span class="emailBt">确定</span></div>';
var userType=$("#userType").val();
$(document).ready(function(){
    //验证邮箱
    $(document).on("keyup","input[name=emailId]",function(){
        if ($(this).val().match(reEmail)) {
        	
            $("#time").addClass("txnum");
        }else {
            $("#time").removeClass("txnum");
        }
    });
    //发送验证码
    $(document).on("click","#time.txnum",function(){
    });
    //焦点
    $(document).on("focus","input[name=emailNumber]",function(){
        $(".emailTips").html("");
    });
    //立即更换
    $(document).on("click",".emailXg",function(){
        $emailInfo.html(emailData.ybd+emailXg);
        clearTimeout(counttimeStop);
        $(".emailXiList").html(emailData.oldEmail+emailData.emailNumber).find("#time").addClass("txnum").attr('onclick','sendVerificationMailCode(2)');
    });
    if(userEmail!=null&&userEmail!=""){//已绑定邮箱
        $emailInfo.html(emailData.ybd);//加载默认显示	
    }else{//未绑定邮箱
        $emailInfo.html(emailData.wbd+emailXg);//默认显示
        $(".emailXiList").html(emailData.newEmail+emailData.emailNumber);
    }
    //验证手机
    $(document).on("keyup","input[name=mobileId]",function(){
        if ($(this).val().match(reMobile)) {
            $("#time1").addClass("txnum");
        }else {
            $("#time1").removeClass("txnum");
        }
    });


    //焦点
    $(document).on("focus","input[name=mobileNumber]",function(){
        $(".mobileTips").html("");
    });

});
//倒计时
var counttimeStop;
function counttime(time) {
    var _this = $("#time");
    if (time > 0) {
        time--;
        _this.html("发送成功（" + time + "秒)").removeClass("txnum");
        counttimeStop=   window.setTimeout("counttime(" + time+ ")", 1000);
        $("input[name=emailId]").attr("readonly",true);
        
    } else {
    	 $("input[name=emailId]").attr("readonly",false);
        _this.html("重新发送").addClass("txnum");
    }
}


//强度
//发送邮箱验证码

function sendVerificationMailCode(type){
	var classVal=$("#time").attr("class");
	if(classVal==""){
		return false;
	}
	
	if(type==1){
		//发送邮箱验证码
	  	 var url=baseUrl+"/login/sendVerificationMailCode";
	  	 var email=$("input[name=emailId]").val();
	  	 var smsTemplateCode="bind_email_code";
	  	 var userType=$("#userType").val();
		 
		 var data={"targetEmail":email,"userType":userType,"smsTemplateCode":smsTemplateCode};
		 $.ajax({
			url: url, 
			data:data,
			type:"POST",
			async:true,
			success: function(result){
				if(result.respCode!="0000"){
				  swal("",result.respDesc);
				}else{
					 counttime(40);
				}
			},error:function(result){
				alert(result.respDesc);
			}
		 });
	}else if(type==2){
		//发送邮箱验证码
	  	 var url=baseUrl+"/login/sendVerificationMailCode";
	  	 var userType=$("#userType").val();
		 var data={"targetEmail":userEmail,"userType":userType,"smsTemplateCode":"modify_bind_email_code"};
		 $.ajax({
			url: url, 
			data:data,
			type:"POST",
			async:true,
			success: function(result){
				if(result.respCode!="0000"){
				  swal("",result.respDesc);
				}else{
					 counttime(40);
				}
			},error:function(result){
				alert(result.respDesc);
			}
		 });

	}else if(type==3){
		//发送邮箱验证码
	  	 var url=baseUrl+"/login/sendVerificationMailCode";
	  	 var userType=$("#userType").val();
		 var email=$("input[name=emailId]").val();
		 var data={"targetEmail":email,"userType":userType,"smsTemplateCode":"bind_email_code"};
		 $.ajax({
			url: url, 
			data:data,
			type:"POST",
			async:true,
			success: function(result){
				if(result.respCode!="0000"){
				  swal("",result.respDesc);
				}else{
					 counttime(40);
				}
			},error:function(result){
				alert(result.respDesc);
			}
		 });
	}
	
}


//邮箱修改的确定按钮
$(document).on("click",".emailBt",function(){
	if(userEmail!=null&&userEmail!=""){//已绑定邮箱
        	//确定
            //邮箱校验验证码
        	 var inputNumb=$("input[name=emailNumber]").val();
        	 var emailTips=$(".emailTips");
        	 if(userEmail==""){
        		 emailTips.html("邮箱账号不能为空");
        		 return false;
        	 }
        	 if(inputNumb==""){
        		 emailTips.html("验证码不能为空");
        		 return false;
        	 }
        	 var dataJson={"targetEmail":userEmail,"userType":userType,"smsTemplateCode":"modify_bind_email_code","verificationCode":inputNumb};
        	 var url=baseUrl+"/login/checkVerificationMailCode";
         	 $.ajax({
         		url: url, 
         		data:dataJson,
         		type:"POST",
         		success:function(result){
         			console.log(result);
         			if(result.respCode!="0000"){
         				
         				
         				emailTips.html("验证码错误！");   
         				
         			}else{
         				
         			    clearTimeout(counttimeStop);
         	           $(".emailXiList").html(emailData.newEmail+emailData.emailNumber);
         	           $("#time").html("发送验证码").attr('onclick','sendVerificationMailCode(3)');
         	           
                       $(".emailBt").attr("class","emailZcBt").html("提交修改");
         			}

         		},error:function(e){
         			alert(e);
         		}
         	 }); 
	}else{
        //确定
        	 var emailTips=$(".emailTips");
     		 //邮箱校验验证码
        	 var url=baseUrl+"/login/checkVerificationMailCode";
	         var inputNumb=$("input[name=emailNumber]").val();//验证码
	         var	 email=$("input[name=emailId]").val();
	         
        	 if(email==""){
        		 emailTips.html("邮箱账号不能为空");
        		 return false;
        	 }
        	 if(inputNumb==""){
        		 emailTips.html("验证码不能为空");
        		 return false;
        	 }
	         
         	 var data={"targetEmail":email,"userType":userType,"smsTemplateCode":"bind_email_code","verificationCode":inputNumb};
         	 $.ajax({
         		url: url, 
         		data:data,
         		type:"POST",
         		success: function(result){
         			if(result.respCode!="0000"){
         				emailTips.html("验证码错误！");   
         			}else{
         				 //绑定邮箱
         				 var url=baseUrl+"/userCenter/updateEmail4User";
                    	 $.ajax({
                      		url: url, 
                      		data:{'bindEmail':email},
                      		type:"POST",
                      		success: function(result){
                      			
                      			if(result.errorMsg!=true){
                                   emailTips.html(result.errorMsg);   
                                   
                            	}else{
                                    $(".emailXiBox").html("<h1>邮箱绑定成功</h1>").show(8000).delay(2000).hide(0).prev("p").remove();
                                    $(".emailInfo").prepend(emailData.ybd).find(".emailDz").html(email);
                                    userEmail=email;
                                    console.log(userEmail);
                                    emailData={'wbd':'<p>未绑定邮箱，请立即设置，保护账户安全找回用户名密码，语音记录批量下载。</p>','ybd':'<p>您绑定的邮箱账号为：<b class="emailDz">'+userEmail+'</b>，若已丢失或停用，请 <span class="emailXg">立即更换</span>。</p>','newEmail':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱地址"></li>','oldEmail':'<li><i>邮 箱 地 址：</i><input type="email" value="'+userEmail+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span onclick="sendVerificationMailCode(2)" id="time" class="">发送验证码</span></li>'};
                            	
                            	}
                      		}
                      	 });
                      
               	}

         		}
         	 }); 
        	
	}
	
}); 	
//提交修改
$(document).on("click",".emailZcBt",function(){
    var emailDz=$("input[name=emailId]").val();//邮箱
    //邮箱校验验证码
   	 var inputNumb=$("input[name=emailNumber]").val();
   	 var emailTips=$(".emailTips");
   	 var dataJson={"targetEmail":emailDz,"userType":userType,"smsTemplateCode":"bind_email_code","verificationCode":inputNumb};
   	 var url=baseUrl+"/login/checkVerificationMailCode";
    	 $.ajax({
    		url: url, 
    		data:dataJson,
    		type:"POST",
    		success:function(result){
    			console.log(result);
    			if(result.respCode!="0000"){
    				emailTips.html(result.respDesc);   
    			}else{
    				
    			  //修改邮箱	
     				 //绑定邮箱
 				 var url=baseUrl+"/userCenter/updateEmail4User";
            	 $.ajax({
              		url: url, 
              		data:{'bindEmail':emailDz},
              		type:"POST",
              		success: function(result){
              			
              			if(result.errorMsg!=true){
                           emailTips.html(result.errorMsg);   
                           
                    	}else{
                            $(".emailXiBox").html("<h1>邮箱绑定成功</h1>").show(8000).delay(2000).hide(0);
                            $(".emailDz").html(emailDz);
                            

                            userEmail=emailDz;
                            emailData={'wbd':'<p>未绑定邮箱，请立即设置，保护账户安全找回用户名密码，语音记录批量下载。</p>','ybd':'<p>您绑定的邮箱账号为：<b class="emailDz">'+userEmail+'</b>，若已丢失或停用，请 <span class="emailXg">立即更换</span>。</p>','newEmail':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱地址"></li>','oldEmail':'<li><i>邮 箱 地 址：</i><input type="email" value="'+userEmail+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span onclick="sendVerificationMailCode(2)" id="time" class="">发送验证码</span></li>'};
                    	}
              		}
              	 });
    				
    			}
    		},error:function(e){
    			alert(e);
    		}
    	 });
    
    
    
});

//------------------------------------修改密码逻辑处理------------------------------------------
$(function(){

	//确认密码
	$(document).on("click",".passNiBt",function(){
		

		 var oldPass= $("input[name=oldPass]").val();//输入的原密码
	
		
	     var thval=$("input[name=newsPass]").val();
	     var newsval=$("input[name=bdNewsPass]").val();
	      
	  
         var url=baseUrl+"/userCenter/updatePassword";
      	 var data={"account":account,"userType":userType,"password":thval,'oldPass':oldPass,'passwordNew':newsval};
      	 $.ajax({
      		url: url, 
      		data:data,
      		type:"POST",
      		async:true,
      		success: function(result){
      			console.log(result);
      			if(result.respCode!="0000"){
       				if(result.respDesc=="缺少用户上下文"){
       					window.location.reload();//刷新当前页面.
      				}
       				if(result.respCode=="9991"){
       					$("input[name=oldPass]").next("b").attr("class","red").html("*原密码不能为空");
       					return false;
       				}
       				if(result.respCode=="9992"){
       					$("input[name=oldPass]").next("b").attr("class","red").html("*原密码不正确");
       				}
       				
       				var $passstrength=$('#passStrength');
       				if(result.respCode=="9993"){
       					$passstrength.next().attr("class","").html("* 不少于8个字符");
       					return false;
       				}
       				if(result.respCode=="9994"){
       					$("input[name=bdNewsPass]").next().attr("class","red").html("* 确认密码不能为空");
       					return false;
       				}
       				if(result.respCode=="9995"){
       					$("input[name=bdNewsPass]").next().attr("class","red").html("* 两次输入的密码不一致");
       					return false;
       				}
       				if(result.respCode=="9996"){
       					$passstrength.attr("class","").next().html(result.respDesc);
       					return false;
       				}
       				
       				if(result.respCode!="9995"&&result.respCode!="9994"&&result.respCode!="9993"&&result.respCode!="9992"&&result.respCode!="9991"){
       					$(".passTips").html(result.respDesc);
       					
       				}
       				
      				
      			
      			}else{
      			 
      			       swal({title:"",text:"修改成功，请重新登陆",type:"success"},function(){
      			    	   var url=baseUrl+"/login/logout";
	      	 			   window.location=url;
                     })	;
      			}
      		},error:function(result){
      			alert(result.respDesc);
      		}
      	 });
       
	});
	
})


//聚焦
$(document).on("focus",".passwordInfo input",function(){
    $(this).next("b").html("");
});

//修改密码
//原密码失去焦点
$(document).on("blur","input[name=oldPass]",function(){
    var thval=$(this).val();

    if(thval==""){
    	 $(this).next("b").attr("class","red").html("*原密码不能为空");
    }else{
       // $(this).next("b").attr("class","green").html("&#10004");
    }
    
});
//新密码
$(document).on("keyup","input[name=newsPass]",function(e){
    var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
    var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
    var enoughRegex = new RegExp("(?=.{8,}).*", "g");
    var $passstrength=$('#passStrength');
    if (false == enoughRegex.test($(this).val())) {
        $passstrength.attr("class","").next().html("* 不少于8个字符");
    } else if (strongRegex.test($(this).val())) {
        $passstrength.attr('class','ok').next().html("强");
    } else if (mediumRegex.test($(this).val())) {
        $passstrength.attr('class','middle').next().html("中");
    } else {
        $passstrength.attr('class','weak').next().html("弱");
    }
    return true;
});

//确认密码
$(document).on("blur","input[name=bdNewsPass]",function(){
    var thval=$(this).val();
    var newsval=$("input[name=newsPass]").val();
  
    if(thval!=newsval){
        $(this).next("b").attr("class","red").html("* 两次输入的密码不一致");
    } else if(thval==newsval&&thval!=""&&newsval!=""){
    	
    	//$(this).next("b").attr("class","green").html("&#10004");
    }else{
    	$(this).next("b").html("");
    }
});


