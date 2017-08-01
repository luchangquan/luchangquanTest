<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<%
String phoneNo = request.getParameter("phoneNo");//用request得到 
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>保全方式(黑白名单)-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
<script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
 <script src="${baseUrl}/resources/script/scriptAll.js"></script>
</head>
<body>
<input type="hidden" value="<%=phoneNo%>" id="phoneNo"/>
   <input type="hidden" id="navID" value="0">
    <input type="hidden" id="navLF" value="2">
<!--提示框开始-->
<div id="alertbox">
    <div class="alertbk"></div>
    <div class="alertcontent">
        <div class="alertcontit"><i>提示信息</i><span title="关闭" onclick="boclose()"></span></div>
        <div class="alertconbox">
            <input type="hidden" value="" id="contactboxid" />
            <p></p>
            <a onclick="contacok()">确定</a>　　<a onclick="boclose()">取消</a></div>
    </div>
</div>
<!--提示框结束-->
<!--名单新建开始-->
<div id="alertcon">
    <div class="alertbk"></div>
    <div class="alertcontent">
        <div class="alertcontit"><i>名单列表添加</i><span title="关闭" onclick="coclose()"></span></div>
        <div class="alertconbox">
            <ul class="conadd">
                <li><i>姓　　名：</i>
                    <input type="text" value="" id="contactname"  placeholder="输入姓名" maxlength="10"/>
                </li>
                <li><i>电话号码：</i>
                    <input type="tel" value="" id="contacttel" placeholder="输入手机/固定电话(如02188888888)" maxlength="13"/>
                </li>
                <li class="addtips"></li>
                <a onclick="conaddok()">确定</a>　　<a onclick="coclose()">取消</a>
            </ul>
            <ul class="conaddtip" style="display:none">
                <b>恭喜您，名单添加成功！</b><a onclick="conaddback()">继续添加</a>　　<a onclick="coclose()">关闭</a>
            </ul>
        </div>
    </div>
</div>
<!--名单新建结束-->
<!-- 引入头部 -->
   <input type="hidden" id="navID" value="0">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <!-- 引入菜单-->
	<jsp:include page="../commons/left_menu.jsp"></jsp:include>

    <div class="main-right">
        <div class="telephone-box">
            <div class="tele-news">
                <h2>保全模式设置: <span id="phoneNoText"><%=phoneNo%></span></h2>
                <ul class="contactbox">
                    <li id="all" class="">
                        <input type="radio" name="contype" checked="checked" value="0" />
                        <i></i><b>全部保全</b>
                        <p>所有通话均保全</p>
                    </li>
                    <li id="black">
                        <input type="radio" name="contype" value="1" />
                        <i></i><b>指定保全：除黑名单</b>
                        <p>除黑名单号码的通话不保全<br />
                            其余所有通话均保全</p>
                    </li>
                    <li id="white">
                        <input type="radio" name="contype" value="2" />
                        <i></i><b>指定保全：仅白名单</b>
                        <p>除白名单号码的通话保全<br />
                            其余所有通话均不保全</p>
                    </li>
                </ul>
            </div>
            <div class="contactlist">
                <p class="tele-title"><span class="back_ms" title="返回"></span><b></b><i class="addle" title="点击添加加名单">添加</i><i class="textbox">
                    <input  type="text" value="" placeholder="手机号/电话码号" />
                    <i class="seach" title="搜索"></i></i><i class="allcont" title="全部联系人">全部</i></p>
                <div class="contact-tit"><span>姓名</span><span>电话号码</span><span>操作</span></div>
                <ul class="contact">
                    <!--<p class="contact-tip"><i></i>为空，请<i class="addle" title="点击添加名单">添加</i></p>--><!--为空时显示这一句-->
                  
                    
                </ul>
            </div>
        </div>
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script>
	//var reltdtel=/^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
	var isMob=/^(0\d{2,4})(\d{7,9})(\d{2,4})?$/;
	var isPhone =/^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
	var phoneNo=$("#phoneNo").val();;
	var accountType=""; //1. 黑名单 2.白名单
	var oldTargetPhoneNo="";
    $(document).ready(function() {
    	var type="";
		//加载用户信息判断是否管理员用户
	 	var url="${baseUrl}/userCenter/getUserInfo";
		$.ajax({
			url : url,
			type : "post",
			async : false,
			success : function(responseJson) {
				//公司账户
	      		type=responseJson.baseEnhanced.type;
				//phoneNo=responseJson.baseEnhanced.phoneNo;
	      		if(type!="MANAGER"){
	      			phoneNo=responseJson.baseEnhanced.phoneNo;	
	      		}
				
			},
			error : function(msg) {
				alert(msg);
			}
		});  
		
    	
		if(type=="MANAGER"&&phoneNo=="null"){
			$("#phoneNoText").hide();
			$(".contactbox").find("li").attr("class","");
			$(".contactlist").hide();
			return false;
		}else{
			//判断查询该账号的保全类型
		 	var url="${baseUrl}/blacklistAndWhitelist/queryBlackAndWhiteType";
		 	var data={"phoneNo":phoneNo};
			$.ajax({
				url : url,
				type : "post",
				data:data,
				async : false,
				success : function(responseJson) {
					
					if(responseJson.respCode!="0000"){
		      			  swal("",responseJson.respDesc);
		      		}else{
		      			$("#phoneNoText").html(phoneNo);
		      			accountType=responseJson.data;
		      			$(".contactbox").find("li").eq(responseJson.data).attr("class","conl");
		      			if(responseJson.data=="1"){
		      				var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
		      				var data={"phoneNo":phoneNo};
		      				asynchronousRefreshList(url,data);
		      			}else if(responseJson.data=="2"){
		      				var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
		      				var data={"phoneNo":phoneNo};
		      				asynchronousRefreshList(url,data);
		      				
		      			}
		      		}
				},
				error : function(msg) {
					alert(msg);
				}
			});  	
		}
        $.ajaxSetup( {
            cache : false
        });
        var tidd=$(".conl").attr("id");//当前模式
        var listtit=$(".tele-title b");
        var listtis=$(".contact-tip i:first");
        
        //黑白名单是否显示
        if(tidd=="all"){
            $(".contactlist").hide();
        }else if(tidd=="black"){
	
            $(listtit).html("").html("以下列表所有号码的语音通话不保全");
            $(listtis).html("").html("黑名单");
            $(".contactlist").show();
        }else{
    
            $(listtit).html("").html("以下列表所有号码的语音通话保全");
            $(listtis).html("").html("白名单");
            $(".contactlist").show();
        }
        //选择保全模式
        $(".contactbox>li").on("click",function(){
            var idd=$(this).attr("id");//点击对象
            var tidd=$(".conl").attr("id");//当前模式
            var tipinp=$("#contactboxid");//提示层隐藏域
            var tiptextb=$(".alertconbox p");//提示层文字
            var allti='<b>您确定将保全模式修改为“全部保全”吗？</b><i>点击确定后“所有通话均保全”</i>';
            var blacktip='<b>您确定将保全模式修改为“指定保全：除黑名单”吗？</b><i>点击确定后“除黑名单号码的通话不保全，其余所有通话均保全”请添加黑名单列表</i>';
            var whitetip='<b>您确定将保全模式修改为“指定保全：仅白名单”吗？</b><i>点击确定后“除白名单号码的通话保全，其余所有通话均不保全”<b>请立即添加白名单列表</b></i>';
            if(idd==tidd){
                if($(window).width()<720){
                    if(idd=="black"||idd=="white"){
                        $(".contactlist").animate({left:'0'});
                        $(".contactlist>.tele-title").animate({left:'0'});
                    }
                }
            }else if(idd=="all"){
                $(tipinp).val("").val(idd);
                $(tiptextb).html("").html(allti);
                $("#alertbox").show();
            }else if(idd=="black"){
            	
                $(tipinp).val("").val(idd);
                $(tiptextb).html("").html(blacktip);
                $("#alertbox").show();
            }else{
                $(tipinp).val("").val(idd);
                $(tiptextb).html("").html(whitetip);
                $("#alertbox").show();
            }
        });
        //添加
        $(".addle").on("click",function(){
            $("#alertcon").show();
        });
        //编辑
        $(document).on("click",".listtext",function(){
            $(this).parent().prevAll("input").removeAttr("readonly").parent().addClass("edit");
            $(this).prev().show().nextAll().hide();
           
        });
        //保存
        $(document).on("click",".listok",function(){
            $(this).parent().prevAll("input").attr("readonly","readonly").parent().removeClass("edit");
            $(this).hide().nextAll().show();
    		if(accountType==2){
    			var url="${baseUrl}/blacklistAndWhitelist/updateWhite";
    			var newTargetPhoneNo=$(this).parent().parent("li").find("#targetPhoneNo").val();
    			
    			if(isMob.test(newTargetPhoneNo)||isPhone.test(newTargetPhoneNo)){
    				var oldTargetPhoneNo=$(this).parent().parent("li").find("#oldTargetPhoneNo").val();
        			var newTargetUsername=$(this).parent().parent("li").find("#name").val();
        			var data={"phoneNo":phoneNo,"oldTargetPhoneNo":oldTargetPhoneNo,"newTargetPhoneNo":newTargetPhoneNo,"newTargetUsername":newTargetUsername};
        			asynchronousRefreshList(url,data);
        			
        			var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
        			var data={"phoneNo":phoneNo};
        			asynchronousRefreshList(url,data);
    			}else {
    				swal("请输入正确的电话或手机号");
    				
    				var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
        			var data={"phoneNo":phoneNo};
        			asynchronousRefreshList(url,data);
    				return false;
				}
    			
    		}else{
    			var url="${baseUrl}/blacklistAndWhitelist/updateBlack";
    			var newTargetPhoneNo=$(this).parent().parent("li").find("#targetPhoneNo").val();
    		if( isMob.test(newTargetPhoneNo)||isPhone.test(newTargetPhoneNo)){
    			var oldTargetPhoneNo=$(this).parent().parent("li").find("#oldTargetPhoneNo").val();
    			var newTargetUsername=$(this).parent().parent("li").find("#name").val();
    			var data={"phoneNo":phoneNo,"oldTargetPhoneNo":oldTargetPhoneNo,"newTargetPhoneNo":newTargetPhoneNo,"newTargetUsername":newTargetUsername};
    			asynchronousRefreshList(url,data);
    			
    			var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
    			var data={"phoneNo":phoneNo};
    			asynchronousRefreshList(url,data);
    			}else {
    				swal("请输入正确的电话或手机号");
    				var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
        			var data={"phoneNo":phoneNo};
        			asynchronousRefreshList(url,data);
    				return false;
				} 		
    		}
        });
        //删除
        $(document).on("click",".listdele",function(){
        	var targetPhoneNo=$(this).parent().parent("li").find("#targetPhoneNo").val();
            swal({
                title: "真的要删除么?",
                text: "删除后该条记录不可恢复!",
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: false
            },
            function(){
            	var url="";
            	if(accountType==2){
            		url="${baseUrl}/blacklistAndWhitelist/deleteWhite";
            	}else{
            		url="${baseUrl}/blacklistAndWhitelist/deleteBlack";
            	}
				var data={"phoneNo":phoneNo,"targetPhoneNo":targetPhoneNo};

        		$.ajax({
        			url : url,
        			data : data,
        			type : "POST",
        			async : false,
        			success : function(responseJson) {
        				 if(responseJson.respCode!="0000"){
        					 swal("删除失败!",responseJson.respDesc, "error");
        				 }else{
        					 swal("删除成功!");
       			        	if(accountType==2){
       			    			var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
       			    			var data={"phoneNo":phoneNo};
       			    			asynchronousRefreshList(url,data);
       			    		}else{
       			    			var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
       			    			var data={"phoneNo":phoneNo};
       			    			asynchronousRefreshList(url,data);
       			    		}
        				 }
        				
        			},
        			error : function(msg) {
        				alert(msg);
        			}
        		});
            	
               
            }); 
        });
        //返回
        $(".back_ms").on("click",function(){
            $(".contactlist").animate({left:'100%'});
            $(".contactlist>.tele-title").animate({left:'100%'})
        });
    });
    //确定保全模式
    function contacok(){
    	
        var cid=$("#contactboxid").val();//提示层隐藏域值
        var tili=$(".contactbox").find("li#"+cid);
        var listtit=$(".tele-title b");
        var listtis=$(".contact-tip i:first");
        $(tili).addClass("conl").siblings().removeClass("conl");
        $(tili).find("input[type=radio]").click();
        if(cid=="all"){
        	accountType=0;
            $(".contactlist").hide();
        }else if(cid=="black"){
        	accountType=1;
        	//查询黑名单
        	var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
    		var data={"phoneNo":phoneNo};
    		asynchronousRefreshList(url,data);
        	type=2;
            $(listtit).html("").html("以下列表所有号码的语音通话不保全");
            $(listtis).html("").html("黑名单");
            $(".contactlist").show();
            if($(window).width()<720){
                $(".contactlist").animate({left:'0'});
                $(".contactlist>.tele-title").animate({left:'0'})
            }
        }else{
        	accountType=2;
        	//查询白名单
        	var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
    		var data={"phoneNo":phoneNo};
    		asynchronousRefreshList(url,data);
        	
            $(listtit).html("").html("以下列表所有号码的语音通话保全");
            $(listtis).html("").html("白名单");
            $(".contactlist").show();
            if($(window).width()<720){
                $(".contactlist").animate({left:'0'});
                $(".contactlist>.tele-title").animate({left:'0'})
            }
        }
        
    	
    	
        $("#alertbox").hide("slow");
        
        //修改保全模式
		//判断查询该账号的保全类型
	 	var url="${baseUrl}/blacklistAndWhitelist/updateBlackAndWhiteType";
	 	var data={"type":accountType,"phoneNo":phoneNo};
		$.ajax({
			url : url,
			type : "post",
			data:data,
			async : false,
			success : function(responseJson) {
				if(responseJson.respCode!="0000"){
	      			  swal("",responseJson.respDesc);
	      		}else{
	      			
	      		}
			},
			error : function(msg) {
				alert(msg);
			}
		});  

        
    }
    //添加名单
    function conaddok(){
       
        var contactname=$("#contactname").val();
        var contacttel=$("#contacttel").val();
        var lids='<li><input id="name" type="text" value="'+contactname+'"  maxlength="10" readonly="readonly" /><input id="targetPhoneNo" type="text" value="'+contacttel+'"  maxlength="13" readonly="readonly"/><span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>';
        if(isMob.test(contacttel)||isPhone.test(contacttel)){
        	var data={"phoneNo":phoneNo,"targetPhoneNo":contacttel,"targetUsername":contactname};
        	var url="";
        	
			if(accountType==2){
				url="${baseUrl}/blacklistAndWhitelist/addWhite";
			}else{
				url="${baseUrl}/blacklistAndWhitelist/addBlack";
			}
    		$.ajax({
    			url : url,
    			type : "post",
    			data:data,
    			async : false,
    			success : function(responseJson) {
	    			if(responseJson.respCode!="0000"){
	  	      			  swal("",responseJson.respDesc);
	  	      		}else{
		  	            $(".addtips").html("");
		  	            $(".contact").prepend(lids);
		  	            $("#contactname").val("");
		  	            $("#contacttel").val("");
		  	            $(".conadd").hide().next().show();
	  	      		}    			
    			},
    			error : function(msg) {
    				alert(msg);
    			}
    		});
            
            
        }else{$(".addtips").html("请正确的输入电话或手机号码")}
    }
    //关闭添加名单
    function coclose(){
        $("#alertcon").hide("slow");
        $("#contactname").val("");
        $("#contacttel").val("");
        $(".conaddtip").hide().prev().show();
        
    }
    //添加成功，继续添加
    function conaddback(){
        $(".conaddtip").hide().prev().show();
				
		if(accountType==2){
			var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
			var data={"phoneNo":phoneNo};
			asynchronousRefreshList(url,data);
		}else{
			var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
			var data={"phoneNo":phoneNo};
			asynchronousRefreshList(url,data);
		}
    }
    function boclose(){
        $("#alertbox").hide("slow");
    }
    //搜索
    $(".seach").click(function(){
    	var targetPhoneNo=$(this).parent().find("input").val();
    	if(accountType==2){
			var url="${baseUrl}/blacklistAndWhitelist/queryWhite";
			var data={"phoneNo":phoneNo,"targetPhoneNo":targetPhoneNo};
			asynchronousRefreshList(url,data);
		}else{
			var url="${baseUrl}/blacklistAndWhitelist/queryBlack";
			var data={"phoneNo":phoneNo,"targetPhoneNo":targetPhoneNo};
			asynchronousRefreshList(url,data);
		}
    	
    })
    
    
    function asynchronousRefreshList(url,data){
		$.ajax({
			url : url,
			type : "post",
			data:data,
			async : false,
			success : function(responseJson) {
				if(responseJson.respCode!="0000"){
	      			  swal("",responseJson.respDesc);
	      		}else{
	      			console.log(responseJson);
	      			$(".contact").html("");
	      			if(responseJson.data==null){
	      				return false;
	      			}
	      			var list=responseJson.data;
	      	    	var html="";
	      	    	for(row in list){
	      	    		html+="<li>";
	      	    		html+="<input id='name' type='text' value='"+list[row]['targetUsername']+"' readonly='readonly' maxlength='10' />";
	      	    		html+="<input id='oldTargetPhoneNo' type='hidden' value='"+list[row]['targetPhoneNo']+"' maxlength='13' readonly='readonly'/>";
	      	    		html+="<input id='targetPhoneNo' type='text' value='"+list[row]['targetPhoneNo']+"' maxlength='13' readonly='readonly'/>";
	      	    		html+="<span><i class='listok'>保存</i><i class='listtext'>编辑</i><i class='listdele'>删除</i></span>";
	      	    		html+="</li>";
	      	    	}
	      	    	$(".contact").html(html);
	      		}
			},
			error : function(msg) {
				alert(msg);
			}
		});
    }
</script>
</body>
</html>