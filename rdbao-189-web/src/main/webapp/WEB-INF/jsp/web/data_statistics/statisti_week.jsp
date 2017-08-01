<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>周期内日统计-公证录音</title>
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
            <li class="currentlt"><a href="${baseUrl}/dataStatistics/cycleDateStatistics">周期内日统计</a></li>
            <li><a href="${baseUrl}/dataStatistics/specifiedDateStatistics">指定日期统计</a></li>
        </ul>
    </div>
    <div class="main-right">
        <div id="statist-box"></div>
        <!--搜索-->
        <div class="date-tit">
                <input value="${startTimeStr}" type="hidden" id="startTimeStr"/>
        		<input value="${endTimeStr}" type="hidden" id="endTimeStr"/>
        		<input value="${daysNumber}" type="hidden" id="daysNumber"/>
            <div class="search">
                <label class="tel-box"><i>关键字：</i><input id="searchPhoneNoOrNickname" type="text" name="search-tel" value="${searchPhoneNoOrNickname }" placeholder="姓名"></label>
                <label class="start-da"><i>指定日期：</i><input type="text" value="${startTimeStr}" class="laydate-icon" id="start" readonly placeholder="开始日期"></label>至<label class="end-da"><input type="text" class="laydate-icon" id="end" value="${endTimeStr}" readonly placeholder="结束日期"></label><span class="search-bt">查询</span>
            </div>
            <p class="date-ra"><label><input onclick="onDays(30)" type="radio" name="date-r" value="30"><i>最近30日</i> </label><label><input onclick="onDays(7)" type="radio" name="date-r" value="7"><i>最近7日</i> </label></p>
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
            	  <!-- 分页所需要的值 -->
        		 <input  type="hidden" value="${totalPages}" id="totalPages"/>
        		 <input  type="hidden" value="${index}" id="index"/>
                <span class="call-excel-bt">导出Excel</span>
                <div class="page"></div>
            </div>
            <!--EDN 按钮-->
        </div>
        <!--周期内日统计表单-->
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script src="${baseUrl}/resources/script/statisticsday.js"></script>
<script>
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
		//分页
		var totalPages=parseInt($("#totalPages").val());
		
		
		var index=parseInt($("#index").val());
		
		
		if(totalPages==0){
			$(".page").hide();
		}else{
			setPage($(".page")[0],totalPages,index);
		}
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
       
        //window.location="${baseUrl}/dataStatistics/cycleDateStatistics?index="+pageindex;
        
        
        
        
        
        
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
              
	   	 		var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
	   	 		var start=$("#start").val();
	   	 		var end=$("#end").val();
   	 		 
	   	 		 if(start==""&&end==""){
	   	 		     var  day=$("input[name=date-r]:checked").val();
		   	 		 window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&daysNumber="+day+"&index="+inx;
	   	 		 }else{
	   	 		 	window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end+"&index="+inx;
	   	 		 }
                return false;
            }
            for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
                oAlink[i].onclick = function() {
                    inx = parseInt(this.innerHTML);
                    setPage(container, count, inx);
                    var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
                   
    	   	 		var start=$("#start").val();
    	   	 		var end=$("#end").val();
       	 		 
   	   	 		 if(start==""&&end==""){
   	   	 	  		 var  day=$("input[name=date-r]:checked").val();
		   	 		 window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&daysNumber="+day+"&index="+inx;
	   	 		 }else{
	   	 		 	window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end+"&index="+inx;
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
              
	   	 		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
	   	 		 var start=$("#start").val();
	   	 		 var end=$("#end").val();
	   	 		 if(start==""&&end==""){
	   	 		  	 var day=$("input[name=date-r]:checked").val();
		   	 		 window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&daysNumber="+day+"&index="+inx;
	   	 		 }else{
	   	 		 	window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end+"&index="+inx;
	   	 		 }
	   	 		
                return false;
            }
        } ();
    }
	//最近天数点击事件
	function onDays(day){		
		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();

 		
 		
		 window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&daysNumber="+day;
	}
	 
	//查询事件
 	$(".search-bt").click(function(){
 		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
 		 var start=$("#start").val();
 		 var end=$("#end").val();
 		
 		
 		 window.location="${baseUrl}/dataStatistics/cycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end;
 	});
	
	//导出事件
 	$(".call-excel-bt").click(function(){
 		 var searchPhoneNoOrNickname=$("#searchPhoneNoOrNickname").val();
 		 var start=$("#start").val();
 		 var end=$("#end").val();
 		
 		
 		 window.location="${baseUrl}/dataStatistics/downloadVoiceCycleDateStatistics?searchPhoneNoOrNickname="+searchPhoneNoOrNickname+"&startTime="+start+"&endTime="+end;
 	});
	

</script>

</body>
</html>