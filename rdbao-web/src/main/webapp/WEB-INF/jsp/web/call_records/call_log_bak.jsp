<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>用户中心-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
   <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
<script src="${baseUrl}/resources/script/audioplayer.js"></script>
<script src="${baseUrl}/resources/script/date/laydate.js"></script>
</head>
<body>
<!--播放层-->
<div class="audio-play"></div>
<!--END播放层-->
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
        
        <p class="date-ra"><label><input onclick="onDays(90)" type="radio" name="date-r" value="90"  ><i>最近30日</i></label><label ><input onclick="onDays(7)"  type="radio" name="date-r" value="7"><i >最近7日</i> </label><label><input onclick="onDays(1)"  type="radio" name="date-r" value="1"><i >今天</i> </label></p>
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
                    <td class="th-time">通话时长</td>
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
				                    <td class="th-notary">${item.notaryName}</td>
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
        	
        	
            <span class="call-notary-bt">申请公证</span>
            <span class="call-down-bt">语音下载</span>
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
	//语音下载点击事件
	$(".call-down-bt").click(function(){
		
        var  day=$("input[name=call-ba]:checked").length;
        console.log(day);
        if(day<=0){
        	 swal({title:"",text:"请选择需要下载的列！"});
        	 return false;
        }
		window.location="${baseUrl}/callRecords/downloadRecord";
	});
	
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
    	 //查询数据回显

         $("#start").val($("#startTimeStr").val());
		 $("#end").val($("#endTimeStr").val());
    	 var daysNumber=parseInt($("#daysNumber").val());
    	 
   		 $("input[name='date-r']").each(function(i){
   		
   		 	if(daysNumber==$(this).val()){
   		 		$(this).attr("checked","checked");
   		 	}
   		 });
   	
		 
		 
    	
        $(document).on("click",".call-play span",function(){
            var audiosrc=$(this).find("input").val();//文件地址
            var audiobox='<audio preload="auto" controls><source id="audioREC" src="'+audiosrc+'"/></audio>';
            var $audioRec=$(".audio-play");
            $audioRec.html(audiobox);
            $('audio').audioPlayer();
            $(".audioplayer-playpause").click();
        });
		//分页
		var totalPages=parseInt($("#totalPages").val());
		
		
		var index=parseInt($("#index").val());
		
		
		if(totalPages==0){
			$(".page").hide();
		}else{
			setPage($(".page")[0],totalPages,index);
		}
	 		 
		//查询事件
	 	$(".search-bt").click(function(){
	 		 var searchPhoneNo=$("#searchPhoneNo").val();
	 		 var start=$("#start").val();
	 		 var end=$("#end").val();
	 		 window.location="${baseUrl}/callRecords/callLog?searchPhoneNo="+searchPhoneNo+"&startTime="+start+"&endTime="+end;
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
                					 window.location="${baseUrl}/callRecords/callLog";//刷新当前页面.
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
</script>
</body>
</html>