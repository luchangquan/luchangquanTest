<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>公证查询</title>
    <!--共用部分-->
    <link href="${baseUrl}/resources/css/head.css?v=3" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
     <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">

    <!--单独部分-->
    <link href="${baseUrl}/resources/css/userCenter/user.css" rel="stylesheet">
    <link href="${baseUrl}/resources/css/evidenceManag/request.css" rel="stylesheet">




</head>
<body>
<input type="hidden" id="navOn" value="4">
<header class="headerBox">
</header>
<!--NAV-->
<nav class="navBox">
</nav>
<nav class="menuBox">
    <ul>
        <li class="twOn"><a href="#">全部公证</a><input value="" type="hidden"/></li>
        <li><a href="#">申请中</a><input value="APPLY" type="hidden"/></li>
        <li><a href="#">预约成功</a><input value="SUCCESS" type="hidden"/></li>
        <li><a href="#">预约失败</a><input value="FAIL" type="hidden"/></li>
        <li><a href="#">已出证</a><input value="OUTED" type="hidden"/></li>
    </ul>
</nav>
<!--NAV-->
<!--主体-->
 <input value="${userContext.user.account}" id="account" type="hidden"/>
  <input value="${userContext.sourceNppCode}" id="sourceNppCode" type="hidden"/>
  <input value="${code}" id="code" type="hidden"/>
  
<input value="${statuses}" type="hidden" id="statuses"/>
<section class="main">
    <div class="contentBox">
        <h1 class="BoxTitle ">公证申请 - <i class="typeOn">全部公证</i></h1>
        <div class="request">
             <div class="requestMinH">
                    	<c:choose>
                    		<c:when test="${not empty listEnhancedENotarizationReserve }">
                    		 <table class="requestBox">
              			        <thead>
			                        <tr>
			                            <td>申请人</td>
			                            <td>事项说明</td>
			                            <td>证据数量</td>
			                            <td>出证公证处</td>
			                            <td>当前状态</td>
			                            <td>申请时间</td>
			                        </tr>
             					 </thead>
             					  <tbody>
	                    			<c:forEach var="item" items="${listEnhancedENotarizationReserve}">
	                    				<tr>
				                            <td>${item.enhancedUser.account}</td>
				                            <td>${item.description}</td>
				                            <c:choose>
				                            	<c:when test="${not empty item.enhancedMEvidences}">
				                            		<c:set var="len" value="0"/>
						                            <c:forEach items="${item.enhancedMEvidences}" var = "enhancedMEvidences" varStatus="var">
						                            	<c:set var="len" value="${len+1}"/>		
						                            </c:forEach>
						                             <td>${len}</td>
				                            	</c:when>
				                            	<c:otherwise>
				                            		 <td>0</td>
				                            	</c:otherwise>
				                            </c:choose>
				                            <td>${item.enhancedDNpp.name}</td>
				                            <c:if test="${item.status eq 'APPLY' }">
				                           		 <td>申请中</td>				                            
				                            </c:if>
				                            <c:if test="${item.status eq 'SUCCESS' }">
				                           		 <td>预约成功</td>				                            
				                            </c:if>
				                            <c:if test="${item.status eq 'FAIL' }">
				                           		 <td>预约失败</td>				                            
				                            </c:if>
				                            <c:if test="${item.status eq 'CANCEL' }">
				                           		 <td>预约已取消</td>				                            
				                            </c:if>				
				                            
				                            <c:if test="${item.status eq 'OUTED' }">
				                           		 <td>已出证</td>				                            
				                            </c:if>
				                            <c:if test="${item.status eq 'REVOKE' }">
				                           		 <td>预约已撤销</td>				                            
				                            </c:if>
				                            <c:if test="${item.status eq 'OVERDUE' }">
				                           		 <td>预约已过期</td>				                            
				                            </c:if>
				                            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                       				 </tr>
	                    			  </c:forEach>
                    			     </tbody>
               					 </table>
                    		</c:when>
                    		<c:otherwise>
	                    		<!--未申请-->
	                			<c:if test="${statuses eq 'APPLY'}">
	                				<p class="requestTips">您没有提交公证申请，可在证据管理页面提交申请！</p>
	                			</c:if>
	                			<c:if test="${statuses eq 'OUTED'}">
	                				<p class="requestTips">您没有出证成功的数据！</p>
	                			</c:if>
	                			<c:if test="${statuses eq 'FAIL'}">
	                				<p class="requestTips">您没有申请失败的数据！</p>
	                			</c:if>
	                			<c:if test="${empty  statuses}">
	                				<p class="requestTips">您没有提交公证申请，可在证据管理页面提交申请！</p>
	                			</c:if>
	                		
	              			    <!--END未申请-->
                    		</c:otherwise>
                    	</c:choose>
                        
          
            </div>
            <!--分页-->
	        <input  type="hidden" value="${totalPages}" id="totalPages"/>
	        <input  type="hidden" value="${index}" id="index"/>
	        <div class="pageFt"><span class="pageTips">总共<i>${totalPages}</i>页 每页${size} 个</span> <span class="page"></span></div>
            <!--END分页-->
        </div>
    </div>
    <footer class="footerM"></footer>
</section>
<script type="text/javascript">
var sourceNppCode=$("#sourceNppCode").val();
var statuses=$("#statuses").val();
var code=$("#code").val();
$(function(){
	//分页
	var totalPages=parseInt($("#totalPages").val());
	var index=parseInt($("#index").val());
	if(totalPages==0){
		$(".pageFt").hide();
	}else{
		setPage($(".page")[0],totalPages,index);
	}
	
	//查询条件数据会写
	
	if(statuses=="APPLY"){
		$(".typeOn").html("申请中");
	}else if(statuses=="OUTED"){
		$(".typeOn").html("已出证");
	}else if(statuses=="FAIL"){
		$(".typeOn").html("预约失败");
	}else if(statuses=="SUCCESS"){
		$(".typeOn").html("预约成功");
	}else {
		$(".typeOn").html("全部公证");
	}
	
	
	
	$(".menuBox li").each(function(i){
		if(statuses==$(this).find("input").val()){
			
			$(this).attr("class","twOn");	
		}else{
			$(this).removeClass("twOn");
		}
		
		
	})	;
	//根据状态查询
	var sourceNppCode=$("#sourceNppCode").val();

	$(".menuBox li").click(function(){
		var index=$(this).index();
		$(".menuBox li").each(function(i){
			if(index==i){
				var statuses=$(this).find("input").val();
				$(this).attr("class","twOn");
				window.location=baseUrl+"/evidenceManag/toMakeList?statuses="+statuses+"&sourceNppCode="+sourceNppCode+"&code="+code;
			}else{
				$(this).removeClass("twOn");
			}
			
		})
		
	});
})
//分页
function setPage(container, count, pageindex) {
    var container = container;//a标签
    var count = count;//总页数
    var pageindex = pageindex;//当前页
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
    var pageClick = function () {
        var oAlink = container.getElementsByTagName("a");
        var inx = pageindex; //初始的页码
        oAlink[0].onclick = function () { //点击上一页
            if (inx == 1) {
                return false;
            }
            inx--;
            setPage(container, count, inx);

	
		
        	window.location=baseUrl+"/evidenceManag/toMakeList?index="+inx+"&statuses="+statuses+"&sourceNppCode="+sourceNppCode+"&code="+code;

            return false;
        }
        for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
            oAlink[i].onclick = function () {
                inx = parseInt(this.innerHTML);
                setPage(container, count, inx);

	
		
		window.location=baseUrl+"/evidenceManag/toMakeList?index="+inx+"&statuses="+statuses+"&sourceNppCode="+sourceNppCode+"&code="+code;
                return false;
            }
        }
        oAlink[oAlink.length - 1].onclick = function () { //点击下一页
            if (inx == count) {
                return false;
            }
            inx++;
            setPage(container, count, inx);
                    var startTime=$("#start").val();

		
		window.location=baseUrl+"/evidenceManag/toMakeList?index="+inx+"&statuses="+statuses+"&sourceNppCode="+sourceNppCode+"&code="+code;
            return false;
        }
    }()
}
</script>
<script src="${baseUrl}/resources/js/manage.js?v=4"></script>
</body>
</html>