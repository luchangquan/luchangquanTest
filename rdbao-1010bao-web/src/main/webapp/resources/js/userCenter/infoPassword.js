/**
 * Created by kingm on 2017/4/18.
 */
    var $infoTelMailbox=$(".infoTelMailbox");
    var $infoTelMailTitleH2=$(".infoTelMailTitle h2");
    var $infoTelMailContent=$(".infoTelMailContent");
        //邮箱
    var reEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;//邮箱正则表达式
    var email=$("input[name=email]").val();
    var emailXg='<div class="emailXiBox"><ul class="emailXiList"></ul><p class="emailTips"></p><span class="emailBt">确定</span></div>';
    var emailData={'emailNull':'<p>未绑定邮箱，请立即设置，保护账户安全,方便找回帐号密码。</p>','emailNew':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱地址"></li>','emailOld':'<li><i>邮 箱 地 址：</i><input type="email" value="'+email+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span  id="time" class="">发送验证码</span></li>'};

	var resultObject="";
	var enhancedECompany="";
	var $passstrength=$('#passStrength');
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var account=$("#account").val();//账号
	var type="";
	var data={"account":account};
	var emailBox="";
$(document).ready(function(){
			//根据账号获取信息
			var url=baseUrl+"/userCenter/getUserInfo";
			$.ajax({
				url:url,
				data:data,
				type:"POST",
				async:false,
				success:function(result){
					if(result.respCode!="0000"){
						alert(result.respDesc);
					}else{
						resultObject=result.data;
						type=result.data.type;
					}
				},
				error:function(result){
					alert(result);
				}
				
				
			});
			 if(resultObject==null&&resultObject==""){
				return false;
			} 
			
			
			enhancedECompany=resultObject.rnhancedEUser.enhancedECompany;
			
			//判读是否是公司账号
			if(enhancedECompany==null||enhancedECompany==""){
				$("#company").hide();
			}else{
				$("#companyName").val(enhancedECompany.name);
				$("#licenseNo").val(enhancedECompany.licenseNo);
				$("#contractPhoneNo").val(enhancedECompany.contractPhoneNo);
				$("#address").val(enhancedECompany.address);
			}
			
			$("#name").val(resultObject.rnhancedEUser.name);
			$("#credentials_no").val(resultObject.rnhancedEUser.credentialsNo);
			$("#associate_phone_no").val(resultObject.rnhancedEUser.phoneNo);
			$("#email").val(resultObject.rnhancedEUser.email);
			emailBox=$("#email").val();
			emailData={'emailNull':'<p>未绑定邮箱，请立即设置，保护账户安全,方便找回帐号密码。</p>','emailNew':'<li><i>邮 箱 地 址：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱地址"></li>','emailOld':'<li><i>邮 箱 地 址：</i><input type="email" value="'+resultObject.rnhancedEUser.email+'" readonly></li>','emailNumber':'<li><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span  id="time" class="">发送验证码</span></li>'};

    //手机
    var reTelephone = /^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;//手机号正则表达式
    var telephone=$("input[name=telephone]").val();
    var telephoneXg='<div class="telephoneXiBox"><ul class="telephoneXiList"></ul><p class="telephoneTips"></p><span class="telephoneBt">确定</span> </div>';
   var telephoneData={'telNull':'<p>未绑定手机，请立即设置，保护帐号安全,方便找回帐号密码。</p>','telNew':'<li><i>手 机 号 码：</i><input type="email" value="" name="telId" placeholder="请输入手机号码"></li>','telOld':'<li><i>手 机 号 码：</i><input type="tel" value="'+telephone+'" readonly></li>','telNumber':'<li><i>手机验证码：</i><input type="text" value="" name="telNumber" placeholder="输入短信验证码"><span id="time" class="">发送验证码</span></li>'};


    //修改手机
    $(document).on("click",".telXG",function(){
        var telephone=$("input[name=telephone]").val();
        $infoTelMailbox.show();
        $infoTelMailTitleH2.html("修改手机号码");
        $infoTelMailContent.html(telephoneXg);

        if(telephone!=""){//已绑定
            $(".telephoneXiList").html(telephoneData.telOld+telephoneData.telNumber);
            $("#time").addClass("txnum");
        }else{//未绑定
            $(".telephoneXiList").html(telephoneData.telNull+telephoneData.telNew+telephoneData.telNumber);
        }

    });
    //验证手机
    $(document).on("keyup","input[name=telId]",function(){
        if ($(this).val().match(reTelephone)) {
            $("#time").addClass("txnum")
        }else {
            $("#time").removeClass("txnum")
        }
    });
    //焦点
    $(document).on("focus","input[name=mobileNumber]",function(){
        $(".telephoneTips").html("");
    });
    //确定
    $(document).on("click",".telephoneBt",function(){
        var tele=$("input[name=telephone]");
        var telephone=tele.val();
        var number="123A";//验证码
        var inputNumb=$("input[name=telNumber]").val();
        var telTips=$(".telephoneTips");

        if(telephone!="") {//已绑定
            if(inputNumb==number){//验证码正确
                $(".telephoneXiList").html(telephoneData.telNew+telephoneData.telNumber);
                $(this).attr("class","telZcBt").html("提交修改");
            }else{
                telTips.html("*手机验证码错误，请正确填写")
            }
        }else{//未绑定
            var mobileDz=$("input[name=mobileId]").val();//手机号码
            if(inputNumb==number){//验证码正确
                $(".telephoneXiBox").html("<h1>恭喜你，手机号码绑定成功!</h1>");
                tele.val(mobileDz);
                $infoTelMailbox.delay(5000).hide(0);
            }else{
                telTips.html("*手机验证码错误，请正确填写")
            }

        }
        clearTimeT();
    });
    //提交修改
    $(document).on("click",".telZcBt",function(){
        var number="234A";//验证码
        var tele=$("input[name=telephone]");
        var inputNumb=$("input[name=telNumber]").val();//验证码
        var telDz=$("input[name=telId]").val();//手机号码
        var telTips=$(".telephoneTips");
        if(inputNumb==number){//验证码正确
            $(".telephoneXiBox").html("<h1>恭喜你，手机号码绑定成功!</h1>");
            tele.val(telDz);
            $infoTelMailbox.delay(5000).hide(0);
        }else{
            telTips.html("*手机验证码错误，请正确填写")
        }
    });








		//------------------------------------修改密码逻辑处理------------------------------------------
		$(function(){

			//确认密码
			$(document).on("click",".passwordBt",function(){
				

				 var oldPass= $("input[name=passOld]").val();//输入的原密码
			
				
			     var thval=$("input[name=passNews]").val();
			     var newsval=$("input[name=passNewsQ]").val();
			      
			  
		         var url=baseUrl+"/userCenter/updatePassword";
		      	 var data={"account":account,"userType":type,"password":thval,'oldPass':oldPass,'passwordNew':newsval};
		      	 console.log(strongRegex.test(thval));
          	     if (strongRegex.test(thval)) {
          	    	$passstrength.next().attr("class","").html("密码必须包含大写和小写字母、数字、特殊字符");
           	        return false;
           	     }
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
		       					$("input[name=passOld]").next("b").attr("class","red").html("*原密码不能为空");
		       					return false;
		       				}
		       				if(result.respCode=="9992"){
		       					$("input[name=passOld]").next("b").attr("class","red").html("*原密码不正确");
		       				}
		       				
		       				
		       				if(result.respCode=="9993"){
		       					$passstrength.next().attr("class","").html("* 不少于8个字符");
		       					return false;
		       				}
		       				if(result.respCode=="9994"){
		       					$("input[name=passNewsQ]").next().attr("class","red").html("* 确认密码不能为空");
		       					return false;
		       				}
		       				if(result.respCode=="9995"){
		       					$("input[name=passNewsQ]").next().attr("class","red").html("* 两次输入的密码不一致");
		       					return false;
		       				}
		       				if(result.respCode=="9996"){
		       					$passstrength.attr("class","").next().html(result.respDesc);
		       					return false;
		       				}
		       				
		       				if(result.respCode!="9995"&&result.respCode!="9994"&&result.respCode!="9993"&&result.respCode!="9992"&&result.respCode!="9991"){
		       					
		       					var str=new RegExp("密码在8-16个字符,须包含数字、大小写字母以及");
		       					
		       					console.log(str.test(result.respDesc));
		       					if(str.test(result.respDesc)){
		       						$passstrength.next().attr("class","").html("密码必须包含大写和小写字母、数字、特殊字符");
		       					}else{
		       						$passstrength.next().attr("class","").html(result.respDesc);
		       					}
		       				}
		      			}else{
		      			       swal({title:"",text:"修改成功，请重新登陆",type:"success"},function(){
		      			    	   var url=baseUrl+"/logout/logout";
			      	 			   window.location=url;
		                     })	;
		      			}
		      		},error:function(result){
		      			alert(result.respDesc);
		      		}
		      	 });
		       
			});
			
		})



    //新密码
    $(document).on("keyup","input[name=passNews]",function(){
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
    $(document).on("blur","input[name=passNewsQ]",function(){
        var thval=$(this).val();
        var newsval=$("input[name=passNews]").val();
        if(thval!=newsval){
            $(this).next("b").attr("style","").html("* 两次输入的密码不一致");
        }else{
            $(this).next("b").attr("style","color:green").html("&#10004");
        }
    });
    //聚焦
    $(document).on("focus",".passInfo input",function(){
        $(this).nextAll("b").html("");
    })
});
