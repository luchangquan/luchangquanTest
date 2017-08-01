<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>公正查询-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
   <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<!-- 引入头部 -->
<input type="hidden" id="navID" value="3">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <div class="main-left">
        <ul class="navLF">
            <li><a href="${baseUrl}/notaryQuery/notaryList">全部公证</a></li>
            <li><a href="${baseUrl}/notaryQuery/notaryList?states=5">已出证</a></li>
            <li><a href="${baseUrl}/notaryQuery/notaryList?states=1">未完成</a></li>
        </ul>
    </div>
    <div class="main-right">
        <div class="notaryList">
            <h2>公证申请列表</h2>
            <table>
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

		               <c:choose>
						<c:when test="${not empty listNotaryQueryData}">
							 <c:forEach items="${listNotaryQueryData}" var="item">
				 	                <tr>
					                    <td>${item.applyName}</td>
					                    <td>${item.itemExplain }</td>
					                    <td>${item.count}</td>
					                    <td>${item.notaryName}</td>
					                    <td>${item.states}</td>
					                    <td>${item.currentTime}</td>
		           				</tr>
							 </c:forEach>
						</c:when>
				  </c:choose>
               
                </tbody>
            </table>

        </div>
        <div class="call-content-bt">
        	<!-- 分页所需要的值 -->
        	<input  type="hidden" value="${totalPages}" id="totalPages"/>
        	<input  type="hidden" value="${index}" id="index"/>
        	<input  type="hidden" value="${states}" id="states"/>
        	
        
            <div class="page"></div>
        </div>
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
	
	//根据状态来控制 菜单样式
	
		var states=$("#states").val();
		console.log(states);
		if(states=="null"||states==""){
			$(".navLF").find("li").eq(0).attr("class","currentlt");
		}else if(states=="1"){
			$(".navLF").find("li").eq(2).attr("class","currentlt");
		}else if(states=="5"){
			$(".navLF").find("li").eq(1).attr("class","currentlt");
		}
	
	
	//分页
	var totalPages=parseInt($("#totalPages").val());
	
	
	var index=parseInt($("#index").val());
	
	
	if(totalPages==0){
		$(".page").hide();
	}else{
		setPage($(".page")[0],totalPages,index);
	}
	
})
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
          
         	var states=$("#states").val();
  	 		 
	 		 	window.location="${baseUrl}/notaryQuery/notaryList?states="+states+"&index="+inx;
            return false;
        }
        for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
            oAlink[i].onclick = function() {
                inx = parseInt(this.innerHTML);
                setPage(container, count, inx);
             	var states=$("#states").val();
      	 		 
   	 		 	window.location="${baseUrl}/notaryQuery/notaryList?states="+states+"&index="+inx;
                return false;
            }
        }
        oAlink[oAlink.length - 1].onclick = function() { //点击下一页
            if (inx == count) {
                return false;
            }
             inx++;
             setPage(container, count, inx);
          
             	var states=$("#states").val();
   	 		 
   	 		 	window.location="${baseUrl}/notaryQuery/notaryList?states="+states+"&index="+inx;
   	 		 
   	 		
            return false;
        }
    } ();
}
</script>
</body>
</html>