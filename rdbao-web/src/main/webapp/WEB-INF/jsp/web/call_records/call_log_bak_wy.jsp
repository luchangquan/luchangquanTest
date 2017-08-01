<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>通话记录-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/audioplay.css">
<script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
<script src="${baseUrl}/resources/script/audioplayer.js"></script>
<script src="${baseUrl}/resources/script/date/laydate.js"></script>
<script src="${baseUrl}/resources/script/media/audiojs/audio.min.js"></script>
</head>
<body>
<div class="downBox">下载<span class="downNumber">0</span></div>
<!--语音下载-->
<div class="callDown">
    <div class="downCon">
         <p>语音下载   *请在30分钟内提交下载申请，超时将清空此列表<span class="downConClose" title="关闭"></span></p>
        <div class="downList">
            <div class="down01">
                <table>
                    <thead >
                        <tr>
                            <td>呼叫类型</td>
                            <td>呼出号码</td>
                            <td>呼入号码</td>
                            <td>通话时长</td>
                            <td>存证公证处</td>
                            <td>操作</td>
                        </tr>
                     </thead>
                     <tbody id="downloadList">
                    </tbody>
                </table>
            </div>
            <div class="down02">
            </div>
        </div>
        <div class="downBut"><span class="downNext">批量下载</span><span class="downOk">确　定</span></div>
    </div>
</div>
<!--END语音下载-->
<!--申请公证-->
<div id="NotaryBox">
    <div class="NotaryCon">
        <p>申请公证<span class="NotaryClose" title="关闭"></span></p>
        <ul class="Notitle">
            <li class="ath">证据列表</li>
            <li>公证须知</li>
            <li>申请信息</li>
            <li>完成</li>
        </ul>
        <div class="NotaryLi">
            <div id="Notary01">
                <table>
                    <thead>
                        <tr>
                            <td>呼叫类型</td>
                            <td>呼出号码</td>
                            <td>呼入号码</td>
                            <td>通话时长</td>
                            <td>存证公证处</td>
                            <td>操作</td>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                    
                    </tbody>
                </table>
            </div>
            <div id="Notary02">
                <div class="orczxz">
                    <p>公证所需材料</p>
                    <ul>
                        <li>申请人办理需携带：</li>
                        <li>申请人的营业执照副本或正本复印件（盖公章）</li>
                        <li>申请人的组织机构代码证（盖公章）</li>
                        <li>法定代表人身份证明（盖公章）</li>
                        <li>法定代表人身份证复印件（盖公章）</li>
                    </ul>
                    <ul>
                        <li>委托办理还需携带：</li>
                        <li>授权委托书（盖公章）</li>
                        <li>受托人身份证复印件</li>
                        <li>著作权凭证</li>
                    </ul>
                </div>
                <div class="orczxz" style="clear:both; border-top: solid 1px #dedede;">
                    <p>出证必须由申请人或受托人到场</p>
                </div>
            </div>
            <div id="Notary03">
                <div class="oryysx">
                    <label><i>手机号码：</i><input id="phoneNo" type="text" value=""/><b>* 接收公证提示信息</b></label>
                    <label><i>邮　　箱：</i><input id="email" type="text" value=""/><b>* 接收公证提示信息</b></label>
                    <label><i>事项名称：</i><input id="matterName" type="text" value="" maxlength="30"/><b>* 公证事项命个标题吧</b></label>
                    <label><i>事项说明：</i><input id="matterDesc" type="text" value="" maxlength="30"/><b>* 简要说明公证事项</b></label>
                </div>
            </div>
            <div id="Notary04">
                <p>申请公证提交成功，查看当前公证状态请至<a href="${baseUrl}/notaryQuery/notaryList">公证查询</a>，谢谢！</p>
            </div>
        </div>
        <div class="NotaryBut"><span class="NoPrev">上一步</span><span class="NoNext">下一步</span><span class="NoOk">确　定</span></div>
    </div>
</div>
<!--END申请公证-->
<!--播放层-->
<div class="audio-play"></div>
<!--END播放层-->
<!--绑定邮箱-->
<section class="emailIdIn" style="display:none">
    <div class="emailBox">
        <p>绑定邮箱<span class="emailClose" title="关闭"></span></p>
        <div class="emailBoxInfo"></div>
    </div>
</section>
<!--END绑定邮箱-->
<!-- 引入头部 -->
 <input type="hidden" id="navID" value="1">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <!--搜索-->
    <div class="date-tit font12">
        <div class="search">
            <label class="tel-box"><i>电话号码：</i><input id="searchPhoneNo" type="text" name="search-tel" value="${searchPhoneNo}" placeholder="呼入/呼出电话号码"></label>
            <label class="start-da"><i>指定日期：</i><input type="text" class="laydate-icon " id="start" value="" readonly placeholder="开始日期"></label>至<label class="end-da"><input type="text" class="laydate-icon " id="end" value="" readonly placeholder="结束日期"></label><span class="search-bt">查询</span>
        </div>
        <input value="${startTimeStr}" type="hidden" id="startTimeStr"/>
        <input value="${endTimeStr}" type="hidden" id="endTimeStr"/>
        <input value="${daysNumber}" type="hidden" id="daysNumber"/>
        <span class="mobiSearch"></span><!--移动端搜索-->
        <p class="date-ra"><label><input onclick="onDays(30)" type="radio" name="date-r" value="30"  ><i>最近30日</i></label><label ><input onclick="onDays(7)"  type="radio" name="date-r" value="7"><i >最近7日</i> </label><label><input onclick="onDays(1)"  type="radio" name="date-r" value="1"><i >今天</i> </label></p>
    </div>
    <!--END 搜索-->
    <!--通话记录表单-->
    <div class="callLog-con-box">
        <!--通话记录头部-固定-->
        <div class="call-content-title">
            <table>
                <tr>
                    <td class="checkboz"><label><input type="checkbox" name="all-call-ba" id="checkboxAll"><i title="全选/反选"></i></label></td>
                    <td class="call-play">在线播放</td>
                    <td class="call-type">呼叫类型</td>
                    <td class="hj-no">呼出号码</td>
                    <td class="hc-no">呼入号码</td>
                    <td class="ly-ks">录音开始</td>
                    <td class="ly-js">录音结束</td>
                    <td class="th-time">通话时长(秒)</td>
                    <td class="th-notary">存证公证处</td>
                </tr>
            </table>
        </div>
        <!--END 通话记录头部-固定-->
        <!--通话记录列表-->
        <div class="call-content-con">
            <table class="call-content">
                
                <c:choose>
					<c:when test="${not empty listCallLogData}">
						 <c:forEach items="${listCallLogData}" var="item">
						 		<tr>
				                    <td class="checkboz"><label><input value="${item.id}" type="checkbox" name="call-ba" id="all"><i></i></label></td>
				                    <td class="call-play"><span title="在线播放"><input value="${item.fileUrl}" type="hidden" /></span></td>
				                    <td class="call-type">${item.callType}</td>
				                    <td class="hj-no">${item.callOutPhone}</td>
				                    <td class="hc-no">${item.callIncomingPhone}</td>
				                    <td class="ly-ks"> <fmt:formatDate value="${item.recordIngTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				                    <td class="ly-js"> <fmt:formatDate value="${item.recordEndTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				                    <td class="th-time">${item.conversationTime} </td>
				                    <td class="th-notary"><a href="${item.evidRecordViewUrl }" target="_blank">${item.notaryName}</a></td>
               				  </tr>
						 </c:forEach>
					</c:when>
			</c:choose>
            </table>
        </div>
        <!--END 通话记录列表-->
        <!--按钮-->
        <div class="call-content-bt">
        	<!-- 分页所需要的值 -->
        	<input  type="hidden" value="${totalPages}" id="totalPages"/>
        	<input  type="hidden" value="${index}" id="index"/>
        	
        	<span class="call-down-bt">添加到下载列表</span>
            <span class="call-notary-bt">申请公证</span>
            
            <span class="call-excel-bt">导出Excel</span>
            <span class="call-del-bt">删除</span>
            
            <div class="page"></div>
        </div>
        <!--END 按钮-->
    </div>
    <!--END 通话记录表单-->
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script>
var userEmail="";
	//获取用户信息判断用户是否已经绑定邮箱
	
	var url="${baseUrl}/userCenter/getUserInfo";
		$.ajax({
		url : url,
		type : "post",
		async : false,
		 traditional: true,  
		success : function(responseJson) {
			userEmail=responseJson.baseEnhanced.email;
			
			
			$("input[name=emailId]").val(userEmail);
		},
		error : function(msg) {
			alert(msg);
		}
	}); 

//定义一个有返回值的ajax函数供调用
function ajaxCustom(url, data, method, async) {
	var result = null;
	$.ajax({
		url : url,
		data : data,
		type : method,
		async : async,
		success : function(responseText) {
				result =responseText;
		},
		error : function(msg) {
			alert(msg);
		}
	});
	return result;
}

	//全选和全不选
	$("input[name='all-call-ba']:checkbox").click(function(){
	
		$("input[name='call-ba']:checkbox").prop("checked",$(this).is(':checked'));
	});
	//最近天数点击事件
	function onDays(day){		
		 var searchPhoneNo=$("#searchPhoneNo").val();
 		 window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&daysNumber="+day;
	}

    $(document).ready(function(){
    	downloadList();
    	$('.downConClose').trigger("click");
        $(document).on("click",".call-play span",function(){
            var audiosrc=$(this).find("input").val();//文件地址
            var audiobox='<audio preload="auto" controls><source id="audioREC" src="'+audiosrc+'"/><source src="audio.ogg" /><source src="audio.wav" /></audio>';
            var $audioRec=$(".audio-play");
            $audioRec.html(audiobox);
            
            audiojs.events.ready(function() {
                audiojs.createAll();
            });
            $(".audioplayer-playpause").click();
            $(this).addClass("play-on").parents("tr").siblings().find(".call-play span").removeClass("play-on");
        });
    	
    	
    	
    	 //查询数据回显
         $("#start").val($("#startTimeStr").val());
		 $("#end").val($("#endTimeStr").val());
    	 var daysNumber=parseInt($("#daysNumber").val());
    	 
   		 $("input[name='date-r']").each(function(i){
   		
   		 	if(daysNumber==$(this).val()){
   		 		$(this).attr("checked","checked");
   		 	}
   		 });
   	

		 
    	

		//分页
		var totalPages=parseInt($("#totalPages").val());
		
		
		var index=parseInt($("#index").val());
		
		
		if(totalPages==0){
			$(".page").hide();
		}else{
			setPage($(".page")[0],totalPages,index);
		}
	       //申请公证默认显示
        var NoNext=$(".NoNext");
        var NoPrev=$(".NoPrev");
        //下一步
        $(NoNext).on("click",function(){
            var NotaryLi=$(".NotaryLi>div:visible");
            var NotaryLiDiv=NotaryLi.index();
            
           
           
            if(NotaryLiDiv==2){
            	
            			//提交公证申请
                    	var  arr = [];
                    	$("#tbody").find("input[name=call-ba]").each(function(){
                    		 arr.push($(this).val());
                    	});
                    	
                    	var dataJson={"id":arr,"phoneNo":$("#phoneNo").val(),"email":$("#email").val(),"matterName":$("#matterName").val(),"matterDesc":$("#matterDesc").val()};
                    	var url="${baseUrl}/callRecords/applyNotary";
                		$.ajax({
                			url : url,
                			data :dataJson ,
                			type : "POST",
                			async : false,
                			traditional: true,  //可以是ajax 传递数组
                			success : function(responseText) {
                				 if(responseText.success!=true){
                					 swal("提交失败!",responseText.success, "error");
                					 
                				 }else{
                					 $(".Notitle").find("li").eq(NotaryLiDiv).next().addClass("ath").siblings().removeClass("ath");
                					 NoNext.prev().show();
                			         NotaryLi.hide().next().show();
              		                 NoNext.prev().hide();
              		                 NoNext.hide().next().show();
                				 }
                				
                			},
                			error : function(msg) {
                				alert(msg);
                			}
                		});
            	
            	

            }else{
            	$(".Notitle").find("li").eq(NotaryLiDiv).next().addClass("ath").siblings().removeClass("ath");
            	 NoNext.prev().show();
		         NotaryLi.hide().next().show();
            }
        });
        //上一步
        $(NoPrev).on("click",function(){
            var NotaryLi=$(".NotaryLi>div:visible");
            var NotaryLiDiv=NotaryLi.index();
            $(".Notitle").find("li").eq(NotaryLiDiv).prev().addClass("ath").siblings().removeClass("ath");
            NotaryLi.hide().prev().show();
            
            if(NotaryLiDiv==1){
                NoPrev.hide();
            }else if(NotaryLiDiv==3){
                NoPrev.show().next().show().next().hide();
            }
        });
        //申请公证关闭
        $(".NotaryClose ,.NoOk").on("click",function(){
            $("#NotaryBox").hide();
        });
        //申请公证
        $(".call-notary-bt").on("click",function(){
            var callBaL=$("input[name=call-ba]:checked").length;//已选择的证据
            if(callBaL==0){
                swal("","请勾选需要申请公证的录音","warning");
            }else{
            	//遍历
            	var html="";
            	$("input[name=call-ba]:checked").each(function(){
                    	var $tr =$(this).parent().parent().parent();
                    	html+="<tr>";
                    	html+="<td style='display:none'>"+$($tr).find("td").eq(0).html()+"</td>";
                    	html+="<td>"+$($tr).find("td").eq(2).html()+"</td>";
                    	html+="<td>"+$($tr).find("td").eq(3).html()+"</td>";
                    	html+="<td>"+$($tr).find("td").eq(4).html()+"</td>";
                    	html+="<td>"+$($tr).find("td").eq(7).html()+"</td>";
                    	html+="<td>"+$($tr).find("td").eq(8).html()+"</td>";
                    	html+="<td class='delete'>删除</td>";
                    	html+="</tr>";
                });
            	$("#tbody").html(html);
                $("#NotaryBox").show();
            }
        });
        //删除申请公证列表证据
        $(document).on("click",".delete",function(){
            $(this).parent("tr").addClass("dele");
            swal({
                title:"",
                text:"你确定删除该条证据？",
                type:"warning",
                showCancelButton: true
            },function(isConfirm){
                if(isConfirm){
                    $(".dele").remove();
                }else{
                    $(".dele").removeClass("dele");
                }
            })
        });
        //移动端
        $(".mobiSearch").on("click",function(){
            $(".search").slideToggle();
        });
        //查询
        $(document).on("click", ".search-bt", function () {
 
            
    
	 		 var searchPhoneNo=$("#searchPhoneNo").val();
	 		 var start=$("#start").val();
	 		 var end=$("#end").val();
	 		
            if (searchPhoneNo != "") {
            	
                swal({title: "", text: "正在查询，请稍后"});
                window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end;
            } else if (start != "" || end != "") {
            	window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end;
                swal({title: "", text: "正在查询，请稍后"});
            } else {
                swal({title: "", text: "请填写查询号码或选择指定日期"});
            }
            
        });
	 		 

        //删除
        $(document).on("click",".call-del-bt",function(){
            var  day=$("input[name=call-ba]:checked").length;
            console.log(day);
            if(day<=0){
            	 swal({title:"",text:"请选择需要删除的列！"});
            	 return false;
            }
             swal({
                        title: "你确定删除?",
                        text: "删除后该条记录不可恢复!",
                        type: "warning",
                        showCancelButton: true,
                        closeOnConfirm: false
                    },
                    function(){
                    	var  arr = [];
                    	$("input[name=call-ba]:checked").each(function(){
                    		 arr.push($(this).val());
                    	});
                    	var url="${baseUrl}/callRecords/updateById";
                		$.ajax({
                			url : url,
                			data : {"id":arr},
                			type : "POST",
                			async : false,
                			traditional: true,  //可以是ajax 传递数组
                			success : function(responseText) {
                				console.log(responseText.success);
                				 if(responseText.success!=true){
                					 swal("删除失败!",responseText.success, "error");
                				 }else{
                					 swal("删除成功!", "你选中的记录已成功删除.", "success");
                					 $("input[name=call-ba]:checked").closest('tr').remove();
//                 					 window.location="${baseUrl}/callRecords/callLog";//刷新当前页面.
                				 }
                				
                			},
                			error : function(msg) {
                				alert(msg);
                			}
                		});
                    	
                       
                    }); 
        });

	 	
       
    });

    //日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        min: '2014-04-01',
        max: laydate.now(),
        istoday: false,
        choose: function(datas){
            end.min = datas;
            end.start = datas ;
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: '2014-01-01',
        max: laydate.now(),
        istoday: false,
        choose: function(datas){
            start.max = datas;
        }
    };
    laydate(start);
    laydate(end);
   
    function setPage(container, count, pageindex) {
    	
        var container = container;//a标签
        var count = count;//总页数
        var pageindex = pageindex;//当前页
       
        //window.location="${baseUrl}/callRecords/callLog?index="+pageindex;
        
        
        
        
        
        
        var a = [];
        if (pageindex == 1) {
            a[a.length] = "<a href=\"#\" class=\"prev unclick\">上一页</a>";
        } else {
            a[a.length] = "<a href=\"#\" class=\"prev\">上一页</a>";
        }
        function setPageList() {
            if (pageindex == i) {
                a[a.length] = "<a href=\"#\" class=\"onPage\">" + i + "</a>";
            } else {
                a[a.length] = "<a href=\"#\">" + i + "</a>";
            }
        }
        //总页数小于10
        if (count <= 10) {
            for (var i = 1; i <= count; i++) {
                setPageList();
            }
        }
        //总页数大于10页
        else {
            if (pageindex <= 4) {
                for (var i = 1; i <= 5; i++) {
                    setPageList();
                }
                a[a.length] = "...<a href=\"#\">" + count + "</a>";
            } else if (pageindex >= count - 3) {
                a[a.length] = "<a href=\"#\">1</a>...";
                for (var i = count - 4; i <= count; i++) {
                    setPageList();
                }
            }
            else { //当前页在中间部分
                a[a.length] = "<a href=\"#\">1</a>...";
                for (var i = pageindex - 2; i <= pageindex + 2; i++) {
                    setPageList();
                }
                a[a.length] = "...<a href=\"#\">" + count + "</a>";
            }
        }
        if (pageindex == count) {
            a[a.length] = "<a href=\"#\" class=\"next unclick\">下一页</a>";
        } else {
            a[a.length] = "<a href=\"#\" class=\"next\">下一页</a>";
        }
        container.innerHTML = a.join("");
        //事件点击
        var pageClick = function() {
            var oAlink = container.getElementsByTagName("a");
            var inx = pageindex; //初始的页码
            oAlink[0].onclick = function() { //点击上一页
                if (inx == 1) {
                    return false;
                }
                inx--;
                setPage(container, count, inx);
              
	   	 		var searchPhoneNo=$("#searchPhoneNo").val();
	   	 		var start=$("#start").val();
	   	 		var end=$("#end").val();
   	 		 
	   	 		 if(start==""&&end==""){
	   	 		     var  day=$("input[name=date-r]:checked").val();
		   	 		 window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&daysNumber="+day+"&index="+inx;
	   	 		 }else{
	   	 		 	window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end+"&index="+inx;
	   	 		 }
                return false;
            }
            for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
                oAlink[i].onclick = function() {
                    inx = parseInt(this.innerHTML);
                    setPage(container, count, inx);
                    var searchPhoneNo=$("#searchPhoneNo").val();
    	   	 		var start=$("#start").val();
    	   	 		var end=$("#end").val();
       	 		 
   	   	 		 if(start==""&&end==""){
   	   	 	 		 var  day=$("input[name=date-r]:checked").val();
   	   	 	 		
		   	 		 window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&daysNumber="+day+"&index="+inx;
	   	 		 }else{
	   	 		 	window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end+"&index="+inx;
	   	 		 }
                    return false;
                }
            }
            oAlink[oAlink.length - 1].onclick = function() { //点击下一页
                if (inx == count) {
                    return false;
                }
                 inx++;
                 setPage(container, count, inx);
              
	   	 		 var searchPhoneNo=$("#searchPhoneNo").val();
	   	 		 var start=$("#start").val();
	   	 		 var end=$("#end").val();
	   	 		 if(start==""&&end==""){
	   	 		  	var  day=$("input[name=date-r]:checked").val();
	   	 		  	
		   	 		 window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&daysNumber="+day+"&index="+inx;
	   	 		 }else{
	   	 		 	window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end+"&index="+inx;
	   	 		 }
	   	 		
                return false;
            }
        } ();
    }
    //导出事件
    $(".call-excel-bt").click(function(){
    		var index=parseInt($("#index").val());
	 		 var searchPhoneNo=$("#searchPhoneNo").val();
   	 		 var start=$("#start").val();
   	 		 var end=$("#end").val();
   	 		 if(start==""&&end==""){
   	 		     var  day=$("input[name=date-r]:checked").val();
	   	 		 window.location="${baseUrl}/callRecords/getExportExcel?searchPhoneNo="+searchPhoneNo+"&daysNumber="+day;
   	 		 }else{
   	 		 	window.location="${baseUrl}/callRecords/getExportExcel?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end+"&index="+index;
   	 		 }
    });
	//-------------邮箱操作逻辑--------------------
    //发送验证码
    $(document).on("click", ".txnum", function () {

    	
    	
    	
        
    });
	
	
	
    //验证邮箱
    var remobile = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    $(document).on("keyup","input[name=emailId]",function () {
        if ($(this).val().match(remobile)) {
            $("#time").addClass("txnum");
        }else {
            $("#time").removeClass("txnum");
        }
    });
    //关闭
    $(document).on("click",".emailClose",function(){
        $(".emailIdIn").hide();
    });
    //立即绑定
    $(document).on("click",".letEmail",function(){
        var $emailNumber=$(this).parents(".emailBoxInfo").find("input[name=emailNumber]").val();
        if($emailNumber==""){
            swal("","邮件验证码不能为空，请正确填写邮件验证码");
        }else{
      		 //邮箱校验验证码
	       		 var url="${baseUrl}/login/checkVerificationMailCode";
	        	 
	        	 var email=$("input[name=emailId]").val();
	        	 var userType=$("#userType").val();
	         	 var data={"targetEmail":email,"userType":userType,"smsTemplateCode":"bind_email_code","verificationCode":$emailNumber};
          	 $.ajax({
          		url: url, 
          		data:data,
          		type:"POST",
          		success: function(result){
          			console.log(result);
          			if(result.respCode!="0000"){
          				swal("","验证码错误");
                	}else{
                		//绑定邮箱
                		 var url="${baseUrl}/userCenter/updateEmail4User";
                     	 $.ajax({
                       		url: url, 
                       		data:{'bindEmail':email},
                       		type:"POST",
                       		success: function(result){
                       			
                       			if(result.errorMsg!=true){
                                    swal({title:"",text:result.errorMsg,type:"error"});
                                       
                                    
                             	}else{
                             		//clearTimeout(counttimeStop);
                                    swal({title:"",text:"邮箱绑定成功",type:"success"},function(){
                                        $(".emailIdIn").hide();
                                        $(".emailIdIn").find("#time").html("发送验证码").attr('onclick','sendVerificationMailCode(2)');
                                        userEmail=email;
                                        //$(".downBox").show();
                                    })	;
                             	}
                             	

                       		}
                       	 });
                       
                	}

          		}
          	 }); 
		
    
        }
    });
    //倒计时
    var counttimeStop;
     
    function counttime(time) {
        var _this = $("#time");
        if (time > 0) {
            time--;
            _this.html("发送成功（" + time + "秒)").removeClass("txnum");
            counttimeStop=     window.setTimeout("counttime(" + time + ")", 1000);
            $("input[name=emailId]").attr("readonly",true);
        } else {
        	$("input[name=emailId]").attr("readonly",false);
            _this.html("重新发送").addClass("txnum");
        }
    }
//-------------邮箱操作逻辑 end--------------------  

//--------------批量下载按钮---------------
    //批量下载按钮
    $(".downNext").on("click",function(){
        var $down01=$(".down01 tbody").find("tr").length;
        var $down02=$(".down02");
        var emailY='<p><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span  id="time" class="downLoadButton">发送验证码</span></p>';
        var emailYl='<div class="emailYl">语音证据批量下载将通过邮件方式发送至绑定邮箱：<b>'+userEmail+'</b></div><div class="emailInput"></div>';
        if($down01>0){
            $down02.html(emailYl);
            $(".emailInput").html(emailY).find("#time").addClass("txnum");
            $(".down01").hide().next().show();
            $(this).hide().next().show();
        }else{
            swal({title: "", text: "当前下载列表的为空，请重新选择"});
        }

    });
	
    
    //发送下载验证码
    $(document).on("click",".downLoadButton",function(){
    	var classVal=$("#time").attr("class");
    	if(classVal=="downLoadButton"){
    		return false;
    	}
   	 var url="${baseUrl}/login/sendVerificationMailCode";
   	 var userType=$("#userType").val();
    	 var data={"targetEmail":userEmail,"userType":userType,"smsTemplateCode":"download_file_code"};
    	 $.ajax({
    		url: url, 
    		data:data,
    		type:"POST",
    		async:true,
    		success: function(result){
    			console.log(result);
      			if(result.respCode!="0000"){
      			  swal("",result.respDesc);
      			}else{
      				counttime(40);//倒计时90秒
      			}
    		},error:function(result){
    			alert(result.respDesc);
    		}
    	 }); 

    });
    //确定下载
    $(document).on("click",".downOk",function(){
        var $emailNumber=$("input[name=emailNumber]").val();
        var $success='<div class="emailYl">语音证据批量下载申请成功,将通过邮件附件的方式发送至绑定邮箱，请注意查收。</div>';
        if($emailNumber==""){
            swal("","邮件验证码不能为空，请正确填写邮件验证码");
        }else{
            console.log("验证邮箱验证码咯");
            //校验下载验证码
	       		 var url="${baseUrl}/login/checkVerificationMailCode";
	        	 
	        	 var userType=$("#userType").val();
	         	 var data={"targetEmail":userEmail,"userType":userType,"smsTemplateCode":"download_file_code","verificationCode":$emailNumber};
          	 $.ajax({
          		url: url, 
          		data:data,
          		type:"POST",
          		success: function(result){
          			console.log(result);
          			if(result.respCode!="0000"){
          				swal("","验证码错误");
                	}else{
                		 //多条证据下载推送接口
                		 	 $(".down02").html($success);
		              			
		              			$("#downloadList").html("");
		              			$(".downOk").hide();
                		 var url="${baseUrl}/callRecords/sendCmd2Pnoes4DownloadEvidences4User";
       	 
		              	 $.ajax({
		              		url: url, 
		              		type:"POST",
		              		
		              		success: function(result){
		              		
		              		}
		              	 }); 
                		
                	}

          		}
          	 }); 

        }
    });
//--------------批量下载按钮 end---------------
        //显示下载层
        $(".downBox").on("click",function(){
        	downloadList();
        });
        //关闭显示层
        
        $(".downConClose").on("click",function(){
            var $down01=$(".down01 tbody").find("tr").length;
            console.log($down01);
            $(".downNext").show().siblings().hide();
            if($down01==0){
                $(".downBox").hide();
            }else{
            	console.log("jinl");
                $(".downNumber").html($down01);
                $(".downBox").show();
            }
            $(".callDown").hide();
        });
   		//语音下载点击事件
 		$(".call-down-bt").click(function(){
 	        var emailNl='<div class="emailNl">您的帐号当前未绑定邮箱，不能进行批量下载，请立即绑定。</div>';
 	        var emailN='<p><i>邮 箱 帐 号：</i><input type="email" value="" name="emailId" placeholder="请输入邮箱帐号"></p>';
 	        var emailY='<p><i>邮件验证码：</i><input type="text" value="" name="emailNumber" placeholder="输入邮件验证码"><span onclick="sendVerificationMailCode(1)" id="time" class="">发送验证码</span></p>';
 	        var embdBT='<div class="embdBT"><span class="letEmail">立即绑定</span></div>';
 			
 	        var  items =$("input[name=call-ba]:checked");
 	        if(items.length<=0){
 	        	 swal({title:"",text:"请选择需要下载的列！"});
 	        	 return false;
 	        }
 	        //获取保存的证据下载信息
 	        var count=0;
 	        var url="${baseUrl}/callRecords/getDownloadInfo";
	   		$.ajax({
				url : url,
				type : "post",
				async : false,
				 traditional: true,  
				success : function(responseJson) {
					if(responseJson.errorMsg!=true){
						 swal({title:"",text:responseJson.errorMsg});
						 return false;
					}
					console.log(responseJson);
					count=responseJson.listCallLogData.length;
				},
				error : function(msg) {
					alert(msg);
				}
			});
 	        
 	        if(items.length==1){
 	        	if(count==0){
					//单条记录直接下载
					var fileUrls =  items.closest('tr').find('.call-play input');
		 	 	        $.each(fileUrls,function(_inx,fileUrl){
		 	 	        	window.open($(fileUrl).val(),$(fileUrl).val());
		 	 	     });
 	        	}else{
 	        		$(".downNumber").html(saveDownloadInfo());//写入数量
					$(".downBox").show();
 	        	}
 	        	
 	        }else{
				//获取用户信息判断用户是否已经绑定邮箱
				var url="${baseUrl}/userCenter/getUserInfo";
 	 	   		$.ajax({
	 				url : url,
	 				type : "post",
	 				async : false,
	 				 traditional: true,  
	 				success : function(responseJson) {
	 					userEmail=responseJson.baseEnhanced.email;
	 					$("input[name=emailId]").val(userEmail);
	 				},
	 				error : function(msg) {
	 					alert(msg);
	 				}
	 			}); 
				if(userEmail!=null&&$.trim(userEmail)!=""){
					
					$(".downNumber").html(saveDownloadInfo());//写入数量
					$(".downBox").show();
				}else{
					 swal({title:"",text:"您的帐号当前未绑定邮箱,请立即绑定！",type:"warning"},function(){
                        $(".emailBoxInfo").html(emailNl+emailN+emailY+embdBT).parents(".emailIdIn").show();
                    });
				}
 	        	
 	        }

 	        
 		});   
   		//保存证据
   		function saveDownloadInfo(){
   				var downloadEvidencesVoCount=0;
	        	//保存下载证据的id到后台
	         	var idArray=new Array();
	        	$("input[name=call-ba]:checked").each(function(index){
     			var $tr =$(this).parent().parent().parent();
     			idArray[index]= id=$($tr).find("td").eq(0).find("input").val();
                 	
	        	});
	        	var url="${baseUrl}/callRecords/saveDownloadInfo";
	 	   		$.ajax({
	 				url : url,
	 				data : {"id":idArray},
	 				type : "post",
	 				async : false,
	 				 traditional: true,  
	 				success : function(responseJson) {
	 					
	 						if(responseJson.errorMsg!=true){
	 							 swal({title:"",text:responseJson.errorMsg});
	 							 return false;
	 						}
	 						downloadEvidencesVoCount=responseJson.downloadEvidencesVo.count;
	 				},
	 				error : function(msg) {
	 					alert(msg);
	 					
	 				}
	 			}); 
   			return downloadEvidencesVoCount;
   		}
   		
 		//加载下载列表数据
 		function downloadList(){
 	    	var url="${baseUrl}/callRecords/getDownloadInfo";
 		   		$.ajax({
 					url : url,
 					type : "post",
 					async : false,
 					 traditional: true,  
 					success : function(responseJson) {
 						if(responseJson.errorMsg!=true){
 							 swal({title:"",text:responseJson.errorMsg});
 							 return false;
 						}
 						//遍历数据
 						var listCallLogData=responseJson.listCallLogData;
 						var html="";
 						$.each( listCallLogData, function(i, item){ 
 							html+="<tr>";
 							html+="<td>"+item.callType+"</td>";
 							html+="<td>"+item.callOutPhone+"</td>";
 	                        html+="<td>"+item.callIncomingPhone+"</td>";
 	                        html+="<td>"+item.conversationTime+"</td>";
 	                        html+="<td>"+item.notaryName+"</td>";
 	                        html+="<td class='deletes'>删除<input id='id' type='hidden' value='"+item.id+"'/></td>";
 	                        html+="</tr>"; 
 							
 						});
 						$("#downloadList").html(html);
 						$(".callDown").show().find(".down01").show().siblings().hide();
 			            $(this).hide();
 			            //删除证据下载信息
 			            $(document).on("click",".deletes",function(){
 			                $(this).parent("tr").addClass("dele");
 			                var idArray=new Array();
 	                    	console.log($(this).find("#id").val());
 	                    	idArray[0]=$(this).find("#id").val();
 			                swal({
 			                    title:"",
 			                    text:"你确定删除该条证据？",
 			                    type:"warning",
 			                    showCancelButton: true,
 			                    closeOnConfirm:false
 			                },function(isConfirm){
 			                    if(isConfirm){
 			         	        	var url="${baseUrl}/callRecords/deleteDownloadEvidencesInfo4User";
 			         	 	   		$.ajax({
 			        	 				url : url,
 			        	 				data : {"id":idArray},
 			        	 				type : "post",
 			        	 				async : false,
 			        	 				 traditional: true,  
 			        	 				success : function(responseJson) {
 			        	 					 
 			        	 						if(responseJson.errorMsg!=true){
 			        	 							 swal({title:"",text:responseJson.errorMsg,type:"error"});
 			        	 						}else{
 			        	 							 swal({title:"",text:"删除成功",type:"success"});
 			        	 							 $(".dele").remove();
 			        	 							 var count=$(".down01 tbody").find("tr").length;
 			        	 							  $(".downNumber").html(count);
 			        	 							 if(count==0){
 			        	 								$(".downBox").attr("display","none");
 			        	 								$(".downBox").hide();
 			        	 							 }else{
 			        	 								 
 			        	 							 }
 			        	 						}
 			        	 				},
 			        	 				error : function(msg) {
 			        	 					alert(msg);
 			        	 				}
 			        	 			}); 
 			                    }else{
 			                        $(".dele").removeClass("dele");
 			                    }
 			                });
 			            });
 							
 					},
 					error : function(msg) {
 						alert(msg);
 					}
 				});
 			
 			
 		}
//发送邮箱验证码
function sendVerificationMailCode(type){	
	var classVal=$("#time").attr("class");
	if(classVal==""){
		return false;
	}
	if(type==1){
	   	 //发送邮箱验证码
	   	 var url="${baseUrl}/login/sendVerificationMailCode";
	   	 var email=$("input[name=emailId]").val();
	   	 var userType=$("#userType").val();
   	 
     
    	 var data={"targetEmail":email,"userType":userType,"smsTemplateCode":"bind_email_code"};
    	 $.ajax({
    		url: url, 
    		data:data,
    		type:"POST",
    		async:true,
    		success: function(result){
    			console.log(result);
     			if(result.respCode!="0000"){
     			  swal("",result.respDesc);
     			}else{
     				counttime(40);//倒计时90秒
     			}
    		},error:function(result){
    			alert(result.respDesc);
    		}
    	 }); 
	}else{
	   	$('.downLoadButton').trigger("click");
    	
	}
	
} 
</script>
</body>
</html>