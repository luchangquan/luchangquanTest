<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>我的实时保</title>
    <!--共用部分-->
    <link href="${baseUrl}/resources/css/head.css?v=1" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
    <script src="${baseUrl}/resources/js/manage.js?v=11"></script>
    <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">

    <!--单独部分-->
    <link href="${baseUrl}/resources/css/index/index.css?v=3" rel="stylesheet">

</head>
<body>
<input type="hidden" id="navOn" value="0">
<header class="headerBox">
</header>
<!--NAV-->
<nav class="navBox">
</nav> 
<!--NAV-->
<!--主体-->

<input value="${userContext.user.account}" id="account" type="hidden"/>
 <input value="${userContext.sourceNppCode}" id="sourceNppCode" type="hidden"/>
<section class="main">
    <div class="contentBox">
        <div class="homeBox">
      
            <!--用户中心头部-->
            <div class="userHead borBacPadd">
                <div class="userID">
                    <div class="userImg"><img src="${baseUrl}/resources/images/userImg.png"></div>
                    <p>${userContext.user.account}</p>
                    <article> <a href="${baseUrl}/userCenter/toBasicInfo">个人信息</a></article>
                </div>
                <div class="userMoney">
                    <h2>账户余额：</h2>
                    <p><span>0.0</span></p>
                    <article class="userMonBt"><!-- <a href="#">费用中心</a> --><!-- <a href="#">充值</a>  --></article>
                </div>
                <div class="evidNumber">
                    <h2>证据数量：</h2>
                    <p><span>${userEvidenceInfoVo.evidenceCount}</span></p>
                    <article class="evidNotaryT">正在进行：<a href="${baseUrl}/evidenceManag/toMakeList?sourceNppCode=${userContext.sourceNppCode}&code=${item.code}">${userEvidenceInfoVo.notaryApplyCount}</a>项公证申请</article>
                </div>
                <div class="appcenter">
                </div>
            </div>
            <!--END用户中心头部-->
            <!--公证处统计-->
            <c:choose>
            	<c:when test="${not empty userEvidenceInfoVo.listEnhancedDNpp}">
            		<c:forEach var="item" items="${userEvidenceInfoVo.listEnhancedDNpp}">
			            <div class="NotaryTj borBacPadd">
			                <h2>${item.name} <a href="${baseUrl}/evidenceManag/toEvidenceManagIndex?code=${item.code}&sourceNppCode=${userContext.sourceNppCode}">查看全部证据》</a></h2>
			                <ul>
			                    <li>
			                        <h2>证据数量：</h2>
			                        <article class="TjNumber"> 
			                            <span><i>视　频：</i>${item.evidenceVideoCount}</span>
			                            <span><i>传真语音：</i>${item.evidenceFaxCount}</span>
			                            <span><i>APP视频：</i>${item.evidenceAppvideoCount}</span>
			                            <span><i>APP录音：</i>${item.evidenceAppvoiceCount}</span>
			                            <span><i>APP图片：</i>${item.evidenceApppictureCount}</span>
			                            <span><i>总数量：</i>${item.evidenceFaxCount+item.evidenceVideoCount+item.evidenceApppictureCount+item.evidenceAppvoiceCount+item.evidenceAppvideoCount }</span>
			                        </article>
			                    </li>
			                    <li>
			                        <h2>存储空间：</h2>
			                        <article class="CchSize">
			                            <span><i>已　用：</i>${item.spaceAmount}</span>

			                        </article>
			                    </li>
			                    <li>
			                        <h2>正在进行：</h2>
			                        <article class="thNotary">
			                            <span><a href="${baseUrl}/evidenceManag/toMakeList?sourceNppCode=${userContext.sourceNppCode}&code=${item.code}">${item.notaryApplyCount}</a>项公证申请</span>
			                        </article>
			                    </li>
			                </ul>
			            </div>
            		</c:forEach>
            	</c:when>
            </c:choose>
            <!--END公证处统计-->
        </div>
    </div>
    <footer class="footerM"></footer>
</section>
<!--END 主体-->
<script type="text/javascript">

var stateObject = {};
var sourceNppCode=$("#sourceNppCode").val();
var newUrl = "${baseUrl}/userCenter/userInfo?sourceNppCode="+sourceNppCode;
history.pushState(stateObject,"",newUrl);
$(function(){
	$(".appcenter").on("click",function(){
		var sourceNppCode=$("#sourceNppCode").val();
		var account=$("#account").val();//账号
		var data={"account":account};
		var type=0;
		//根据账号获取信息
		var url="${baseUrl}/userCenter/getUserInfo";
		$.ajax({
			url:url,
			data:data,
			type:"POST",
			async:false,
			success:function(result){
				if(result.respCode!="0000"){
					swal("",result.respDesc);
				}else{
					type=result.data.type;
					if(type==2){
						var url="${baseUrl}/getRemotePcUrl?nppCode="+sourceNppCode;
						$.ajax({
							url:url,
							data:data,
							type:"POST",
							async:false,
							success:function(results){
								console.log(results.data);
								if(results.respCode!="0000"){
									swal("",results.respDesc);
								}else {
									window.open(results.data.url);
				
									
								}
							},
							error:function(result){
								alert(result);
							}
						});
						
						
					}else{
						window.location=baseUrl+"/applicationCenter/toAppCount?sourceNppCode="+sourceNppCode;
					}
					
				}
			},
			error:function(result){
				alert(result);
			}
		});
	})
})

</script>
</body>
</html>