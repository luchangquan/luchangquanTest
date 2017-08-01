<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>指定日期统计-公证录音</title>
    <link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
	<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
    <link rel="stylesheet" href="${baseUrl}/resources/css/default_theme/defaultTheme.css">
    <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
    <script src="${baseUrl}/resources/script/echarts.min.js"></script>
    <script src="${baseUrl}/resources/script/jquery.fixedheadertable.min.js"></script>
    <script src="${baseUrl}/resources/script/date/laydate.js"></script>
</head>
<body>
<!-- 引入头部 -->
 <input type="hidden" id="navID" value="2">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <div class="main-left">
        <ul class="navLF">
            <li><a href="${baseUrl}/dataStatistics/countTimeStatistics">总时长统计</a></li>
            <li><a href="${baseUrl}/dataStatistics/cycleDateStatistics">周期内日统计</a></li>
            <li class="currentlt"><a href="${baseUrl}/dataStatistics/specifiedDateStatistics">指定日期统计</a></li>
        </ul>
    </div>
    <div class="main-right">
        <div id="statist-time"></div>
        <!--搜索-->
        <div class="date-tit">
            <div class="search">
                <label class="tel-box"><i>关键字：</i><input id="searchPhoneNoOrNickname" type="text" name="search-tel" value="${searchPhoneNoOrNickname}" placeholder="姓名"></label>
                <label class="start-da"><i>指定日期：</i><input type="text" class="laydate-icon" id="dayTi" value="${curTime }" readonly placeholder="选择日期"></label>
                                <span>时 间 段：</span>
                <select id='hour_start'>
                    				<option ${startHour == 0?"selected":""} value='0'>0点</option>
									<option ${startHour == 1?"selected":""} value='1'>1点</option>
									<option ${startHour == 2?"selected":""} value='2'>2点</option>
									<option ${startHour == 3?"selected":""} value='3'>3点</option>
									<option ${startHour == 4?"selected":""} value='4'>4点</option>
									<option ${startHour == 5?"selected":""} value='5'>5点</option>
									<option ${startHour == 6?"selected":""} value='6'>6点</option>
									<option ${startHour == 7?"selected":""} value='7'>7点</option>
									<option ${startHour == 8?"selected":""} value='8'>8点</option>
									<option ${startHour == 9?"selected":""} value='9'>9点</option>
									<option ${startHour == 10?"selected":""} value='10'>10点</option>
									<option ${startHour == 11?"selected":""} value='11'>11点</option>
									<option ${startHour == 12?"selected":""} value='12'>12点</option>
									<option ${startHour == 13?"selected":""} value='13'>13点</option>
									<option ${startHour == 14?"selected":""} value='14'>14点</option>
									<option ${startHour == 15?"selected":""} value='15'>15点</option>
									<option ${startHour == 16?"selected":""} value='16'>16点</option>
									<option ${startHour == 17?"selected":""} value='17'>17点</option>
									<option ${startHour == 18?"selected":""} value='18'>18点</option>
									<option ${startHour == 19?"selected":""} value='19'>19点</option>
									<option ${startHour == 20?"selected":""} value='20'>20点</option>
									<option ${startHour == 21?"selected":""} value='21'>21点</option>
									<option ${startHour == 22?"selected":""} value='22'>22点</option>
									<option ${startHour == 23?"selected":""} value='23'>23点</option>
                </select>
                至
                <select id="hour_end">
                   					<option ${endHour == 1?"selected":""} value='1'>1点</option>
									<option ${endHour == 2?"selected":""} value='2'>2点</option>
									<option ${endHour == 3?"selected":""} value='3'>3点</option>
									<option ${endHour == 4?"selected":""} value='4'>4点</option>
									<option ${endHour == 5?"selected":""} value='5'>5点</option>
									<option ${endHour == 6?"selected":""} value='6'>6点</option>
									<option ${endHour == 7?"selected":""} value='7'>7点</option>
									<option ${endHour == 8?"selected":""} value='8'>8点</option>
									<option ${endHour == 9?"selected":""} value='9'>9点</option>
									<option ${endHour == 10?"selected":""} value='10'>10点</option>
									<option ${endHour == 11?"selected":""} value='11'>11点</option>
									<option ${endHour == 12?"selected":""} value='12'>12点</option>
									<option ${endHour == 13?"selected":""} value='13'>13点</option>
									<option ${endHour == 14?"selected":""} value='14'>14点</option>
									<option ${endHour == 15?"selected":""} value='15'>15点</option>
									<option ${endHour == 16?"selected":""} value='16'>16点</option>
									<option ${endHour == 17?"selected":""} value='17'>17点</option>
									<option ${endHour == 18?"selected":""} value='18'>18点</option>
									<option ${endHour == 19?"selected":""} value='19'>19点</option>
									<option ${endHour == 20?"selected":""} value='20'>20点</option>
									<option ${endHour == 21?"selected":""} value='21'>21点</option>
									<option ${endHour == 22?"selected":""} value='22'>22点</option>
									<option ${endHour == 23?"selected":""} value='23'>23点</option>
									<option ${endHour == 24?"selected":""} value='24'>24点</option>
                    </select>
                
                <span class="search-bt">查询</span>
            </div>

        </div>
        <!--END 搜索-->
        <!--周期内日统计表单-->
        <div class="statistiDay-box">
            <div class="statistiDay">
                <table id="statistiDay">
                    <thead>
                         <c:choose>
						<c:when test="${not empty listTitles}">
							 <tr>
								 <c:forEach items="${listTitles}" var="item" varStatus="status">
									  <td>${item}</td>
							      </c:forEach>
							 </tr>
						</c:when>
					</c:choose>
                    </thead>
                    <tbody>
                     <c:choose>
						<c:when test="${not empty list}">
								 <c:forEach items="${list}" var="item" varStatus="status" >
		 							 <tr>	
		 							 	<c:forEach items="${item}" var="items" varStatus="statuss">
									 		 
									 		 <c:if test="${statuss.index eq 0 }">
									 		 	<td>${items.date}</td>
									 		 </c:if>
									 		 <c:if test="${statuss.index ne 0 }">
									 		 	<td> 全部：${items.callAllDuration} <br/>
					 							 	    呼出：${items.callIngDuration} <br/>
					 							 	    呼入：${items.callEndDuration} <br/>
		 							 		</td>	
									 		 </c:if>
							    		 </c:forEach>
		 							 </tr>	
							     </c:forEach>
							
						</c:when>
					</c:choose>
                    </tbody>
                </table>
            </div>
            <!--按钮-->
            <div class="call-content-bt">
                <span class="call-excel-bt">导出Excel</span>
            </div>
            <!--EDN 按钮-->
        </div>
        <!--周期内日统计表单-->
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>

<script>

    laydate({
        elem: '#dayTi',
        min: "2014-04-01",
        max: laydate.now()
    });
    

    $(function(){
    	
    	//查询事件
     	$(".search-bt").click(function(){
     		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
     		 var dayTi=$("#dayTi").val();
     		 var start=$("#hour_start").val();
     		 var end=$("#hour_end").val();
// 	     		if(dayTi!=""){
// 	     			end=dayTi+" "+$("#hour_start").val();
// 	     			start=dayTi+" "+$("#hour_end").val();
// 	     		}
     		
     		 window.location="${baseUrl}/dataStatistics/specifiedDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&curTime="+dayTi+"&startHour="+start+"&endHour="+end;
     	});
    	//导出事件
     	$(".call-excel-bt").click(function(){
    		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
     		 var dayTi=$("#dayTi").val();
     		 var start=$("#hour_start").val();
     		 var end=$("#hour_end").val();
// 	     		if(dayTi!=""){
// 	     			end=dayTi+" "+$("#hour_start").val();
// 	     			start=dayTi+" "+$("#hour_end").val();
// 	     		}
     		
	     		 window.location="${baseUrl}/dataStatistics/downloadVoiceSpecifiedDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&curTime="+dayTi+"&startHour="+start+"&endHour="+end;
     	});
    })

</script>
<script src="${baseUrl}/resources/script/statisticstime.js"></script>
</body>
</html>