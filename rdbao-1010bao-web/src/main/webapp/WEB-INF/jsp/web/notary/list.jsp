<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>涉外公证业务管理平台</title>
<meta name="copright" content="上海人科数据科技有限公司">
	<meta name="KEYWords" content="涉外公证，审核，公证处，业务">
		<meta name="Robots" content="all">
			<meta name="Author" content="MaggieXu">
				<meta name="viewport" content="width=device-width, initial-scale=1" />
				<link href="${baseUrl}/resources/css/notary/style.css?v=2"
					rel="stylesheet" type="text/css" />
				<link href="${baseUrl}/resources/css/notary/search_style.css?v=2"
					rel="stylesheet" type="text/css" />
				<link id="artDialogSkin"
					href="${baseUrl}/resources/css/notary/skins/twitter.css?v=2"
					rel="stylesheet" type="text/css" />

				<script type="text/javascript"
					src="${baseUrl}/resources/js/notary/tab.js?v=2"></script>
				<script src="${baseUrl}/resources/js/notary/main1.js?v=2"
					type="text/javascript"></script>
				<script src="${baseUrl}/resources/js/notary/modernizr.js?v=2"
					type="text/javascript"></script>
				<script src="${baseUrl}/resources/js/notary/artDialog.js?v=2"
					type="text/javascript"></script>
				<script src="${baseUrl}/resources/js/notary/iframeTools.js?v=2"
					type="text/javascript"></script>
</head>

<body>

	<div id="fade" class="black_overlay"></div>

	<div class="cd-cover-layer"></div>
	<!-- cover main content when search form is open -->

	<div id="bigbox">
		<div id="HR_nav">
			<div class="logo">
				<img src="${baseUrl}/resources/images/notary/logo.png" />
				<div class="login">
					<div class="login_name" onclick="loginTk();">${userContext.user.account}</div>
					<a class="login_quit" href="${baseUrl}/logout/logout">退出</a>
				</div>
			</div>
		</div>

		<div id="HR_content">
			<div id="content_left">
				<div id="user_name">
					<div class="photo"></div>
					<div class="name" id="user">
					${nppList.name}
					</div>
				</div>
				<input value="${statuses}" type="hidden" id="statuses" /> <input
					value="${userContext.user.account}" id="account" type="hidden" /> <input
					value="${userContext.sourceNppCode}" id="sourceNppCode"
					type="hidden" /> <input value="${nppList.code}" id="code" type="hidden" />
				<div id="fixed" class="menuBox" style="min-height: 350px">
					<ul id="secondary_menu" style="margin: 20px auto 10px">
						<li class="selected"><a href="#" style="padding: 1px 15px;">全部公证</a><input
							value="" type="hidden" /></li>
						<li><a href="#" style="padding: 1px 15px;">申请中</a><input
							value="APPLY" type="hidden" /></li>
						<li><a href="#" style="padding: 1px 15px;">预约成功</a><input
							value="SUCCESS" type="hidden" /></li>
						<li><a href="#" style="padding: 1px 15px;">预约失败</a><input
							value="FAIL" type="hidden" /></li>
						<li><a href="#" style="padding: 1px 15px;">已出证</a><input
							value="OUTED" type="hidden" /></li>
					</ul>
				</div>
			</div>

			<div id="content_right">
				<div id="HR_details">
					<header class="cd-main-header animate-search"> <nav
						class="cd-main-nav-wrapper"> <a href="#search"
						class="cd-search-trigger cd-text-replace">Search</a> </nav> <!-- .cd-main-nav-wrapper -->

					<a href="#0" class="cd-nav-trigger cd-text-replace">Menu<span></span></a>
					</header>

					<div id="search" class="cd-main-search">
						<div class="cd-search-suggestions">
							<div class="searchBox">
								<form method="get" id="form_search"
									action="${baseUrl}/notary/nr/list">
									<h5>
										<span>订单搜索</span>
									</h5>
									<table>
										<tbody>
											<tr>
												<th>申请人姓名：</th>
												<td><input class="common_input common_input_bg"
													name="agentName" style="width: 160px;" type="text"></td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</div>

						<a href="#0" class="close cd-text-replace">Close Form</a>
					</div>

					<div class="notaryCall listBox">
						<h5>
							<span>订单列表</span>
						</h5>
						<c:choose>
							<c:when test="${not empty listEnhancedENotarizationReserve }">
								<table class="zebra">
									<thead>
										<tr>
											<th><input type="checkbox" name="controlAll"
												id="controlAll" /></th>
											<th>委托代理人姓名</th>
											<th>公证主体</th>
											<th>手机号</th>
											<th>申请时间</th>
											<th>证据数量</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item"
											items="${listEnhancedENotarizationReserve}">
											<tr>
												<td><input type="checkbox" class="checkbox" value="" /></td>
												<td>${item.agentName}</td>
												<td>${item.notarySubject}</td>
												<td>${item.phoneNo}</td>
												<td><fmt:formatDate value="${item.createTime}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<c:choose>
													<c:when test="${not empty item.enhancedMEvidences}">
														<c:set var="len" value="0" />
														<c:forEach items="${item.enhancedMEvidences}"
															var="enhancedMEvidences" varStatus="var">
															<c:set var="len" value="${len+1}" />
														</c:forEach>
														<td>${len}</td>
													</c:when>
													<c:otherwise>
														<td>0</td>
													</c:otherwise>
												</c:choose>
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
												<td><input type="hidden" value="${item.id}" /> <span
													class="soAllMore">查看详情</span></td>
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

						<input type="hidden" value="${totalPages}" id="totalPages" /> <input
							type="hidden" value="${index}" id="index" /> <input type="hidden"
							value="${nppList.code}" id="nppCode" />
					</div>
					<div class="changepage">
						<div class="pagebar">
							<form class="pageinner" id="pageBarForm_" method="get" action=""
								onsubmit="return pageJump_();">
								<div class="pageleft">
									<span>共${totalPages}页,每页10条</span>
								</div>

								<div class="page">
									<div class="pagelink">
										<span id="pageNumber"></span>
									</div>
									<div class="pagelink" style="padding-left: 7px;">
										到第&nbsp;&nbsp;<input id="gotoPage" size="3"
											class="pageno txt_pageno common_input common_input_bg"
											value="" type="text">&nbsp;&nbsp;页<input value="确定"
											class="button" type="button">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var sourceNppCode = $("#sourceNppCode").val();
		var statuses = $("#statuses").val();
		var code = $("#code").val();

		//分页
		var totalPages = parseInt($("#totalPages").val());
		var index = parseInt($("#index").val());
		if (totalPages == 0) {
			$(".pagebar").hide();
		} else {
			setPage($("#pageNumber")[0], totalPages, index);
		}

		//弹出页面插件，就是打开notaryEvidenceList界面
		$(function() {
			$(document).on(
					"click",
					".soAllMore",
					function() {
						$this = $(this);
						var flag = 0;
						var id = $this.prev("input").val();
						;

						var nppCode = $("#nppCode").val();
						var doheig = $(document).height();
						art.dialog.open(baseUrl
								+ '/notary/nr/notaryEvidenceList?id=' + id
								+ "&nppCode=" + nppCode, {
							title : '申请详情',
							width : 1200,
							height : doheig - 100,
							drag : false,
							resize : false,
							lock : true
						});
					})
		});

		$(".button").click(
				function() {
					var inx = $("#gotoPage").val();
					window.location = baseUrl + "/notary/nr/list?index=" + inx
							+ "&statuses=" + statuses + "&sourceNppCode="
							+ sourceNppCode + "&code=" + code;
				});

		//根据预约状态查询
		$("#fixed li").click(
				function() {
					var index = $(this).index();
					var nppCode = $("#nppCode").val();
					$("#fixed li").each(
							function(i) {

								if (index == i) {
									var statuses = $(this).find("input").val();
									$(this).attr("class", "selected");
									window.location = baseUrl
											+ "/notary/nr/list?statuses="
											+ statuses+"&sourceNppCode="+sourceNppCode;
								} else {
									$(this).removeClass("selected");
								}

							})

				});

		//去获取点击到的statuses，并加上一个class，去进行选中操作
		$("#fixed li").each(function(i) {
			if (statuses == $(this).find("input").val()) {
				$(this).attr("class", "selected");
			} else {
				$(this).removeClass("selected");
			}
		});

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
					a[a.length] = "<span class='selected' href=\"#\" >" + i
							+ "</span>";
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
					a[a.length] = "<span style='font-weight:bold'>......</span>"+"<a href=\"#\">" + count + "</a>";
				} else if (pageindex >= count - 3) {
					a[a.length] = "<a href=\"#\">1</a>"+"<span style='font-weight:bold'>......</span>";
					for (var i = count - 4; i <= count; i++) {
						setPageList();
					}
				} else { //当前页在中间部分
					a[a.length] = "<a href=\"#\">1</a>"+"<span style='font-weight:bold'>......</span>";
					for (var i = pageindex - 2; i <= pageindex + 2; i++) {
						setPageList();
					}
					a[a.length] = "<span style='font-weight:bold'>......</span>"+"<a href=\"#\">" + count + "</a>";
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

					window.location = baseUrl + "/notary/nr/list?index=" + inx
							+ "&statuses=" + statuses + "&sourceNppCode="
							+ sourceNppCode + "&code=" + code;

					return false;
				}
				for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
					oAlink[i].onclick = function() {
						inx = parseInt(this.innerHTML);
						setPage(container, count, inx);

						window.location = baseUrl + "//notary/nr/list?index="
								+ inx + "&statuses=" + statuses
								+ "&sourceNppCode=" + sourceNppCode + "&code="
								+ code;
						return false;
					}
				}
				oAlink[oAlink.length - 1].onclick = function() { //点击下一页
					if (inx == count) {
						return false;
					}
					inx++;
					setPage(container, count, inx);
					var startTime = $("#start").val();

					window.location = baseUrl + "//notary/nr/list?index=" + inx
							+ "&statuses=" + statuses + "&sourceNppCode="
							+ sourceNppCode + "&code=" + code;
					return false;
				}
			}()
		}
	</script>
</body>
</html>
