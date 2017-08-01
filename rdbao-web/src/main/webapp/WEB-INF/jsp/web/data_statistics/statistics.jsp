<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>数据统计-公证录音</title>
    <link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
	<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
   <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
    <script src="${baseUrl}/resources/script/date/laydate.js"></script>
</head>
<body>
<!-- 引入头部 -->
<input type="hidden" id="navID" value="2">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <div class="main-left">
        <ul class="navLF">
            <li class="currentlt"><a href="${baseUrl}/dataStatistics/countTimeStatistics">总时长统计</a></li>
            <li><a href="${baseUrl}/dataStatistics/cycleDateStatistics">周期内日统计</a></li>
            <li><a href="${baseUrl}/dataStatistics/specifiedDateStatistics">指定日期统计</a></li>
        </ul>
    </div>
    <div class="main-right">
        <!--搜索-->
        <div class="date-tit">
        	 <input value="${startTimeStr}" type="hidden" id="startTimeStr"/>
       	 	<input value="${endTimeStr}" type="hidden" id="endTimeStr"/>
       		 <input value="${daysNumber}" type="hidden" id="daysNumber"/>
            <div class="search">
                <label class="tel-box"><i>关键字：</i><input id="searchPhoneNoOrNickname" type="text" name="search-tel" value="${searchPhoneNoOrNickname}" placeholder="姓名"></label>
                <label class="start-da"><i>指定日期：</i><input type="text" value="${startTimeStr}" class="laydate-icon" id="start" readonly placeholder="开始日期"></label>至<label class="end-da"><input type="text" value="${endTimeStr}" class="laydate-icon" id="end" readonly placeholder="结束日期"></label><span class="search-bt">查询</span>
            </div>
            <p class="date-ra"><label><input onclick="onDays(30)" type="radio" name="date-r" value="30"><i>最近30日</i> </label><label><input onclick="onDays(7)" type="radio" name="date-r" value="7"><i>最近7日</i> </label></p>
        </div>
        <!--END 搜索-->
        <!--总时长统计表单-->
        <div class="statistic">
            <!--表单抬头-固定-->
            <div class="statis-title">
                <table>
                     <tr>
                        <td class="sta-name">姓名</td>
                        <td class="sta-time">总通话时长</td>
                        <td class="sta-time">呼入电话时长</td>
                        <td class="sta-num">呼入电话个数</td>
                        <td class="sta-time">呼入电话平均时长</td>
                        <td class="sta-time">呼出电话时长</td>
                        <td class="sta-num">呼出电话个数</td>
                        <td class="sta-time">呼出电话平均时长</td>
                        <td class="sta-dis">&nbsp;</td>
                      </tr>
                </table>
            </div>
            <!--END 表单抬头-固定-->
            <!--表单列表-->
            <div class="call-content-con">
                <table class="call-content">

                     <c:choose>
						<c:when test="${not empty listDataStatisticsData}">
							
							 <c:forEach items="${listDataStatisticsData}" var="item" varStatus="status">
									<tr>
				                        <td class="sta-name">${item.name }</td>
				                        <td class="sta-time">${item.countConversationTime }</td>
				                        <td class="sta-time">${item.callIncomingTime }</td>
				                        <td class="sta-num">${item.callIncomingCount }</td>
				                        <td class="sta-time">${item.callIncomingAverageTime }</td>
				                        <td class="sta-time">${item.callOutTime }</td>
				                        <td class="sta-num">${item.callOutCount }</td>
				                        <td class="sta-time">${item.callOutAverageTime }</td>
									</tr>
						      </c:forEach>
						 
						</c:when>
					</c:choose>
                </table>
            </div>
            <!--END 表单列表-->
            <!--按钮-->
            <div class="call-content-bt">
                <span class="call-excel-bt">导出Excel</span>
            </div>
            <!--EDN 按钮-->
        </div>
        <!--总时长统计表单-->
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script>

	//最近天数点击事件
	function onDays(day){		
		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
	
			
			
			 window.location="${baseUrl}/dataStatistics/countTimeStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&daysNumber="+day;
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
		 
		//查询事件
	 	$(".search-bt").click(function(){
	 		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
	 		 var start=$("#start").val();
	 		 var end=$("#end").val();
	 		
	 		
	 		 window.location="${baseUrl}/dataStatistics/countTimeStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end;
	 	});
		//导出事件
	 	
	 	$(".call-excel-bt").click(function(){
	 		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
	 		 var start=$("#start").val();
	 		 var end=$("#end").val();
	 		
	 		 window.location="${baseUrl}/dataStatistics/downloadVoiceTotalTimeStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end;
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
</script>
</body>
</html>