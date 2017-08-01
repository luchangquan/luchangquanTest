<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>证据管理</title>
    <!--共用部分-->
    <link href="${baseUrl}/resources/css/head.css?v=3" rel="stylesheet">
    <script type="text/javascript" src="${baseUrl}/resources/script/jquery.min.js"></script>
    <script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
    <link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
    <script src="${baseUrl}/resources/js/manage.js?v=4"></script>
    <script src="${baseUrl}/resources/js/html5.js?v=8"></script>
    
    <link rel="shortcut icon" href="${baseUrl}/resources/favicon.ico" type="image/x-icon">
    <!--单独部分-->
    <link href="${baseUrl}/resources/css/evidenceManag/evidpage.css?v=1" rel="stylesheet">
    <link href="${baseUrl}/resources/css/evidenceManag/audioplay.css" rel="stylesheet">
        <link href="${baseUrl}/resources/script/media/skin/functional.css" rel="stylesheet">

</head>
<body>
 <input value="${userContext.sourceNppCode}" id="sourceNppCode" type="hidden"/>
<input type="hidden" id="navOn" value="1">
<header class="headerBox">
</header>
<!--NAV-->
<nav class="navBox">
</nav>
<!--NAV-->
<!--主体-->
<section class="main">
    <div class="contentBox">
        <h1 class="BoxTitle evidIcon">证据管理 - <i class="typeOn">我的证据</i><!-- <span class="busiType">组/成员选择</span> --></h1>
        <!--搜索框-->
        <input value="${code}" id="code" type="hidden"/>
        <input value="${userContext.user.account}" id="account" type="hidden"/>
        <input type="hidden" id="category" value="${category}"/>
        <input type="hidden" id="searchType" value="${searchType}"/>
        <ul class="evidSearch borBacPadd">
            <li class="evidSearchType"><i>证据类型：</i><span class="" onclick="onSearch('all')">全部</span><span onclick="onSearch('VIDEO')">视频</span><span onclick="onSearch('FAX')">传真语音</span><span onclick="onSearch('APPVIDEO')">APP视频</span><span onclick="onSearch('APPVOICE')">APP录音</span><span onclick="onSearch('APPPICTURE')">APP图片</span></li>
            <li class="dateTime"><i>存证日期：</i><input type="text" value="${startTimeStr}" class="laydate-icon" id="start" placeholder="起始日期" readonly><span>至</span><input type="text" value="${endTimeStr}" class="laydate-icon" id="end" placeholder="结束日期" readonly></li>
            <li class="evidGj"><i>关 键 字 ：</i><article><p data-value="1">证据名称</p><b class="selectGj"><span data-value="1">证据名称</span><span data-value="2">存证人</span></b></article><input id="searchValue" type="text" value="${searchValue}" placeholder="请输入关键字"></li>
                 <li class="evidBt"><span class="evidBtOk">搜索</span></li><!--evidBtOk-->
        </ul>
        <!--END搜索框-->
        <!--类型-->
        <div class="evidListBox">
           <h2 class="evidType">证据列表</h2>
            <div class="evidList clear">
                 <c:choose>
					<c:when test="${not empty ListEnhancedMEvidence}">
					 	<c:forEach items="${ListEnhancedMEvidence}" var="item">
					 		 <c:if test="${item.category eq 'VIDEO'}">
					 		 	 <div class="evidListCon video" data-type="video"><!--data-type证据类型：video  record  image 等等-->
					 		 	   <div class="evidConImg"><img src="${item.coverUrl}"><input id="evidenceId" type="hidden" value="${item.id}"/></div>
					                    <div class="evidConText">
					                        <p class="evidConTitle">${item.name}</p>
					                        <p class="evidName">${item.enhancedUser.account}   <span title=""></span></p>
					                        <time class="evidConTime"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></time>
					                        <i class="addMore" title="更多"></i>
					                    </div>
					                </div>
					 		 </c:if>
					 		 <c:if test="${item.category eq 'FAX'}">
					 		 	 <div class="evidListCon telephone" data-type="telephone"><!--data-type证据类型：video  record  image 等等-->
					 		 	  <div class="evidConImg"><img src="${item.coverUrl}"><input id="evidenceId" type="hidden" value="${item.id}"/></div>
					                    <div class="evidConText">
					                        <p class="evidConTitle">${item.name}</p>
					                        <p class="evidName">${item.enhancedUser.account}   <span title=""></span></p>
					                        <time class="evidConTime"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></time>
					                        <i class="addMore" title="更多"></i>
					                    </div>
					                </div>
					 		 	 
					 		 </c:if>
					 		 <c:if test="${item.category eq 'APPVIDEO'}">
					 		 	 <div class="evidListCon video" data-type="video"><!--data-type证据类型：video  record  image 等等-->
					 		 	  <div class="evidConImg"><img src="${item.coverUrl}"><input id="evidenceId" type="hidden" value="${item.id}"/></div>
					                    <div class="evidConText">
					                        <p class="evidConTitle">${item.name}</p>
					                        <p class="evidName">${item.enhancedUser.account}   <span title="${item.enhancedItem.locationDesc}">${item.enhancedItem.locationDesc}</span></p>
					                        <time class="evidConTime"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></time>
					                        <i class="addMore" title="更多"></i>
					                    </div>
					                </div>
					 		 	 
					 		 </c:if>
					 		 <c:if test="${item.category eq 'APPPICTURE'}">
					 		 	 <div class="evidListCon image" data-type="image"><!--data-type证据类型：video  record  image 等等-->
					 		 	  <div class="evidConImg"><img src="${item.coverUrl}"><input id="evidenceId" type="hidden" value="${item.id}"/></div>
					                    <div class="evidConText">
					                        <p class="evidConTitle">${item.name}</p>
					                        <p class="evidName">${item.enhancedUser.account}   <span title="${item.enhancedItem.locationDesc}">${item.enhancedItem.locationDesc}</span></p>
					                        <time class="evidConTime"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></time>
					                        <i class="addMore" title="更多"></i>
					                    </div>
					                </div>
					 		 	 
					 		 </c:if>
					 		 <c:if test="${item.category eq 'APPVOICE'}">
					 		  	<div class="evidListCon record" data-type="record"><!--data-type证据类型：video  record  image 等等-->
					 		 	  <div class="evidConImg"><img src="${item.coverUrl}"><input id="evidenceId" type="hidden" value="${item.id}"/></div>
					                    <div class="evidConText">
					                        <p class="evidConTitle">${item.name}</p>
					                        <p class="evidName">${item.enhancedUser.account}   <span title="${item.enhancedItem.locationDesc}">${item.enhancedItem.locationDesc}</span></p>
					                        <time class="evidConTime"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></time>
					                        <i class="addMore" title="更多"></i>
					                    </div>
					                </div>
					 		  	
					 		 </c:if>
			                    
					 	</c:forEach>
					</c:when>
				</c:choose>

            </div>
        </div>
        <!--END类型-->
        <!--分页-->
        <input  type="hidden" value="${totalPages}" id="totalPages"/>
        <input  type="hidden" value="${index}" id="index"/>
        <div class="pageFt"><span class="pageTips">总共<i>${totalPages}</i>页 每页${size} 个</span> <span class="page"></span></div>
        <!--END分页-->
    </div>
    <footer class="footerM"></footer>
</section>
<!--END主体-->



<!--组/成员选择
<section class="groupBox">
    <div class="groupClose" title="关闭"></div>
    <div class="groupCon">
        <div class="groupSearch">
            <input type="text" value="" placeholder="组名称、成员名称">
            <span class="groupSeBt"></span>
        </div>
        <div class="groupListBox">
            <p class="myGroup" title="我的证据"><i></i>我的证据</p>
            <p class="allGroup" title="全部成员全部成员全部成员全部成员"><i></i>全部成员全部成员全部成员全部成员</p>
            <div class="groupList">
                <h2 title="一级组"><i></i>一级组</h2>
                <ul class="groupNaList">
                    <div class="groupList">
                        <h2 title="二级组"><i></i>二级组</h2>
                        <ul class="groupNaList">
                            <li>成员名001</li>
                            <li>成员名002</li>
                            <li>成员名003</li>
                            <li>成员名004</li>
                            <li>成员名005</li>
                        </ul>
                    </div>
                    <div class="groupList">
                        <h2><i></i>二级组</h2>
                        <ul class="groupNaList">
                            <li>成员名001</li>
                            <li>成员名002</li>
                            <li>成员名003</li>
                            <li>成员名004</li>
                            <li>成员名005成员名005成员名005</li>
                        </ul>
                    </div>
                    <li>成员名01</li>
                    <li>成员名02</li>
                    <li>成员名02</li>
                    <li>成员名03</li>
                    <li>成员名04</li>
                    <li>成员名05</li>
                </ul>
                <h2><i></i>一级组</h2>
                <ul class="groupNaList">
                    <li>成员名01</li>
                    <li>成员名02</li>
                    <li>成员名02</li>
                    <li>成员名03</li>
                    <li>成员名04</li>
                    <li>成员名05</li>
                </ul>
            </div>
        </div>
    </div>
</section>
END组/成员选择-->

<!--证据夹-->
<section class="evidFixedbox">
    <div class="evidFixedLeft">
        <ul class="evidFixed">
            <li class="scrollTop"><span>返回顶部</span></li>
            <li class="evidFiBb"><b>公证申请</b><i></i><span>证据列表</span></li>
   <!--          <li class="evidFiDel"><span>回收站-已删证据</span></li> -->
           <!--  <li class="evidWechat"><span>微信公众号</span></li> -->
        </ul>
    </div>
    <div class="evidFiContentBox">
        <div class="evidFiContentTitle"><label><input type="checkbox" name="AllCheckBt" value="全部"/><i></i> 全部</label><span>移除</span></div>
        <div class="evidFiContent">
           
           
                
          
        </div>
        <div class="evidFiContentBt"><span class="evidNotaryBt">申请公证</span></div>
    </div>
</section>
<!--END证据夹-->
<!--更多-->
<section class="moreBox">
    <ul>
        <li class="addBtl">添加到</li>
        <li class="addBtGz">公证申请</li>
     <!--    <li class="addBtSc">证据收藏</li> -->
        <li class="evidDown">下载</li>
         <li class="evidRemove">删除</li> 
    </ul>
</section>
<!--END更多-->
<!--证据详情.在线播放-->
<section class="evidInfoBox">
    <div class="evidInfo">
        <div class="evidInfoClo"><h2>证据详情</h2><span class="evidInfoClose" title="关闭"></span></div>
        <div class="evidInfoConBox">
            <!--证据文字信息-->
            <div class="evidInfoText"></div>
            <!--操作-->
            <div class="evidInfoBt"><span class="addBtGz">添加到公证申请</span> <span class="evidDown">证据下载</span><span class="evidRemove">删除</span></div>
            <!--证据类型-->
            <div class="evidInfoContent"></div>
            <!--其他类型-->
            <div class="evidOther" style="display:none">
                <ul>
                    <li><i>类型：</i>值</li>
                    <li><i>类型：</i>值</li>
                    <li><i>类型：</i>值</li>
                    <li><i>类型：</i>值</li>
                    <li><i>类型：</i>值</li>
                </ul>
            </div>
        </div>
    </div>
</section>
<!--END证据详情.在线播放-->
<!--证据公证-->
<section class="evidNotary">
    <div class="notaryInfo">
        <div class="notaryInfoClo"><h2>申请公证</h2><span class="notaryClose" title="关闭"></span></div>
        <div class="notaryInfoConBox">
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
                                <td>证据名称</td>
                                <td>存证人</td>
                                <td>时间</td>
                                <td>存证公证处</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody id="tbodyList">
                  	
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
                   	    <label><i>代理人姓名：</i><input id="agentName" type="text" value="${lastENotarization.agentName}"/><b></b></label>
                   	    <label><i>公证主体&nbsp;&nbsp;&nbsp;：</i><input id="notarySubject" type="text" value="" /><b>* 填写公证信息主体</b></label>
                        <label><i>手机&nbsp;&nbsp;&nbsp;号码：</i><input id="phoneNo" type="text" value="${lastENotarization.phoneNo}"/><b>* 接收公证提示信息</b></label>
                        <label><i>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</i><input id="email" type="text" value="${lastENotarization.email}"/><b>* 接收公证提示信息</b></label>
                        <label><i>事项&nbsp;&nbsp;&nbsp;名称：</i><input id="matterName" type="text" value="" maxlength="30"/><b>* 公证事项命个标题吧</b></label>
                        <label><i>事项&nbsp;&nbsp;&nbsp;说明：</i><input id="matterDesc" type="text" value="" maxlength="200"/><b>* 简要说明公证事项</b></label>
                    </div>
                </div>
                <div id="Notary04">
                    <p>申请公证提交成功，查看当前公证状态请至<a href="${baseUrl}/evidenceManag/toMakeList">公证申请</a>，谢谢！</p>
                </div>
            </div>
            <div class="NotaryBut"><span class="NoPrev">上一步</span><span  class="NoNext">下一步</span><span class="NoOk">确　定</span></div>
        </div>
    </div>
</section>
<!--END证件公证-->


<script src="${baseUrl}/resources/script/date/laydate.js"></script>
<script src="${baseUrl}/resources/script/media/flowplayer.min.js"></script>
<script src="${baseUrl}/resources/script/media/audiojs/audio.min.js"></script>
<script src="${baseUrl}/resources/js/evidenceManag/evidpage.js?v=59"></script>
<script type="text/javascript">
</script>



</body>
</html>