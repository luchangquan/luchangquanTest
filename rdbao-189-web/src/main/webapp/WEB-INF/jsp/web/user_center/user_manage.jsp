<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>成员管理</title>
<link rel="stylesheet"
	href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet"
	href="${baseUrl}/resources/css/user_center/business.css">
<script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
<script type="text/javascript"
	src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
<script src="${baseUrl}/resources/script/scriptAll.js"></script>

</head>
<body>

	<!--移动分组-->
	<div id="NotaryBox">
		<div class="NotaryConM">
			<p>
				移动分组<span class="NotaryClose" title="关闭"></span>
			</p>
			<div class="NotaryLi move">
				移动至&nbsp;&nbsp;&nbsp; <select class="move" id="moveAOrganization">
				</select>
			</div>
			<div class="NotaryBut sure">
				<span class="Ok" id="surel">确 定</span>
			</div>
		</div>
	</div>
	<!--END移动分组-->

	<!--创建子部门-->
	<div id="NotaryBox2">
		<div class="NotaryConM">
			<p>
				新建子部门<span class="NotaryClose" title="关闭"></span>
			</p>
			<div class="NotaryLi move">
				部门名称&nbsp;&nbsp;&nbsp; <input id="newAorganization" type="text">
			</div>
			<div class="NotaryBut">
				<span class="Ok" style="display: block; margin: 0 auto;">新 建</span>
			</div>
		</div>
	</div>
	<!--END创建子部门-->

	<!--修改部门名-->
	<div id="NotaryBox3">
		<div class="NotaryConM">
			<p>
				修改部门名<span class="NotaryClose" title="关闭"></span>
			</p>
			<div class="NotaryLi move">
				部门名称&nbsp;&nbsp;&nbsp; <input type="text"
					id="updateAOrganizationName">
			</div>
			<div class="NotaryBut">
				<span class="Ok" style="display: block; margin: 0 auto;">修 改</span>
			</div>
		</div>
	</div>
	<!--END修改部门名-->

	<!--创建管理员-->
	<div id="NotaryBox4">
		<div class="NotaryConM">

			<p class="admin">
				创建管理员<span class="NotaryClose" title="关闭"></span>
			</p>
			<div class="NotaryLi guanliyuan">
				<table>
					<tr>
						<td>联系人姓名</td>
						<td><input type="text" id="name"></td>
					</tr>
					<tr>
						<td>联系人号码</td>
						<td><input type="text" id="phone"></td>
					</tr>
					<tr>
						<td>联系人邮箱</td>
						<td><input type="text" id="email"></td>
					</tr>
					<tr>
						<td>联系人身份证号</td>
						<td><input type="text" id="idCard"></td>
						<td><input type="hidden" id="organizationId"></td>
					</tr>
				</table>
			</div>
			<div class="NotaryBut">
				<span class="Ok"  style="display: block; margin: 0 auto;">创
					建</span>
			</div>
		</div>
	</div>
	<!--END修改部门名-->
	   <input type="hidden" id="navID" value="0">
    <input type="hidden" id="navLF" value="3">
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<section class="main">
		<!--  <div class="main-left"> -->
		<jsp:include page="../commons/left_menu.jsp"></jsp:include>
		<div class="main-right">
			<div class="businesBill">
				<h3 id="" class="h2"></h3>
				<input id="companyid" type="hidden" vale="">
				<!--搜索-->
				<div class="date-tit font12" style="border: none;">
					<div class="search">
						<label class="tel-box"><i>成员搜索：</i><input type="text"
							id="accountsearch" name="search-tel" value="" placeholder="账号"></label>
						<span class="search-bt">搜索</span>
					</div>
					<span class="mobiSearch"></span>
					<!--移动端搜索-->
					<p class="date-ra">
						<label><i class="creatnew">新建子部门</i></label><label><i
							class="modify">修改名字</i></label><label> <i class="administrator">创建管理员</i></label><label><i
							class="delete">删除该部门</i></label>
					</p>
				</div>
				<!--END 搜索-->
				<!--通话记录表单-->
				<div class="callLog-con-box manage-box">
					<!--通话记录头部-固定-->
					<div class="call-content-title">
						<table>
							<tr>
								<td class="checkboz"><label><input id="checkboz1"
										type="checkbox" name="all-call-ba"><i title="全选/反选"></i></label></td>
								<td class="call-name">姓名</td>
								<td class="call-account">账号</td>
								<td class="call-department">部门</td>
							</tr>
						</table>
					</div>
					<!--END 通话记录头部-固定-->
					<!--通话记录列表-->
					<div class="call-content-con">
						<table class="call-content">
							<c:choose>
								<c:when test="${not empty enhancedAOrganizations}">
									<c:forEach items="${enhancedAOrganizations}"
										var="enhancedAOrganization">
										<tr>
											<td class="checkboz"><label><input
													type="checkbox" name="call-ba"><i></i></label></td>
											<td class="call-name">${enhancedAOrganization.enhancedEUser189.name}</td>
											<td class="call-account">${enhancedAOrganization.enhancedEUser189.account}</td>
											<td class="call-department">${enhancedAOrganization.enhancedEUser189.account}</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
						</table>
					</div>
					<!--END 通话记录列表-->
					<!--按钮-->
					<div class="call-content-bt">
						<span class="call-move-bt">移动</span> <span class="call-del-bt">删除</span>
						<div class="page"></div>
					</div>
					<!--END 按钮-->
				</div>
				<!--END 通话记录表单-->
			</div>
		</div>
	</section>
	<script type="text/javascript">
	var isPhoneValidator =/^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
	var emailValidator=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
	var idCardValidator = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	//var nameValidator=/['"#$%&\^*》>,."< 《？，。@#￥%……’”：/；]/;
	$('#name').blur(function() { 
		var name=$("#name").val().length
		if(name.length>20 || name<2){
			swal("", "姓名在2—20位", "warning");
		}
	});
	$('#phone').blur(function() { 
		if(!isPhoneValidator.test($("#phone").val())){
			swal("", "请输入合法手机号", "warning");
		}
	});
	$('#email').blur(function() {
		if(!emailValidator.test($("#email").val())){
			swal("", "请输入合法email", "warning");
		}
	});
	$('#idCard').blur(function() {
		if(!idCardValidator.test($("#idCard").val())){
			swal("", "请输入合法身份证号", "warning");
		}
	});

	// 全选/反选
		$("input[name='all-call-ba']:checkbox").click(
				function() {
					$("input[name='call-ba']:checkbox").prop("checked",
					$(this).is(':checked'));
				});
		//移动分组
		$("#surel").on(
				"click",
				function() {
					var targetOrganizationId = $("#moveAOrganization").val();
					var parentOrganizationAndUserIds = [];
					$("input[name=call-ba]:checked").each(
									function() {
									parentOrganizationAndUserIds.push($(this).attr("id")+"_"+$(this).val());
									});		
					var url = "${baseUrl}/userCenter/saveMovie";
					$.ajax({
						url : url,
						data : {
							"parentOrganizationAndUserIds" : parentOrganizationAndUserIds,
							"targetOrganizationId" : targetOrganizationId
						},
						type : "POST",
						async : false,
						traditional : true, //可以是ajax 传递数组
						success : function(responseText) {
							if (responseText.respCode == "0000") {
								swal("", "移动成功", "success");
								$("input[name=call-ba]:checked").closest('tr').remove();
								 window.location.reload();
							} else {
								swal("", "移动失败", "error");
								//      					 window.location="${baseUrl}/callRecords/callLog";//刷新当前页面.
							}

						},
						error : function(msg) {
							alert(msg);
						}
					});
				});

		//删除成员
		$(document).on(	"click",
						".call-del-bt",
						function() {
						
							var callBaL = $("input[name=call-ba]:checked").length;//已选择的证据
							if (callBaL == 0) {
								swal("", "请勾选需要删除的成员", "warning")
							} else {
								swal({
											title : "",
											text : "你确定删除该成员？",
											type : "warning",
											showCancelButton : true
										},
										function(isConfirm) {
											if (isConfirm) {
												var parentOrganizationAndUserIds = [];
												var sing=false;
												$("input[name=call-ba]:checked").each(
																function() {
																var parentOrganizationid=$(this).attr("id");
																parentOrganizationAndUserIds.push($(this).attr("id")+"_"+$(this).val());
																if(parentOrganizationid=="parentAOrganizationIdEmpty"){
																	alert($(this).attr("class")+"不在组织中");
																	sing=true;
																	return;
																
																}
																});
												if(!sing){
												var url = "${baseUrl}/userCenter/deleteUser";
												$.ajax({	url : url,
															data : {
																"parentOrganizationAndUserIds" : parentOrganizationAndUserIds
															},
															type : "POST",
															async : false,
															traditional : true, //可以是ajax 传递数组
															success : function(responseText) {
																if (responseText.respCode == "0000") {
																	swal(
																			"删除成功!",
																			"你选中的记录已成功删除.",
																			"success");
																	$("input[name=call-ba]:checked").closest('tr').remove();

																} else {
																	swal("删除失败!",responseText.success,"error");
																	//             					 window.location="${baseUrl}/callRecords/callLog";//刷新当前页面.
																}

															},
															error : function(
																	msg) {
																alert(msg);
															}
														});
											}}
										});
							}
						});

		//修改管理员 
		$(function() {
			$(this).on("click", "#update", function() {
				var aorganizationAdminId = $("#organizationId").val();
				var aorganizationParentId = $("h3").attr("id");
				var name = $("#name").val();
				var phoneNo = $("#phone").val();
				var email = $("#email").val();
				var idCard = $("#idCard").val();
				
				var url = "${baseUrl}/userCenter/updateOrganizationAdmin";
				var data = {
					"aorganizationAdminId" : aorganizationAdminId,
					"aorganizationParentId" : aorganizationParentId,
					"name" : name,
					"phoneNo" : phoneNo,
					"email" : email,
					"idCard" : idCard
				};
				console.log("---修改管理员数据" + data);
				asynchronoupdateutils(url, data);
				$("#NotaryBox4").hide();
			});
		});

		$(function() {
			$(".h2").text("成员管理");
			$(this).on(		"click",
							".allGroup",
							function() {
								$(".h2").text($(this).text());
								var organizationId = $(this).attr("id");
								$(".h2").attr("id", organizationId);
								var url = "${baseUrl}/userCenter/getEnhancedUsersByOrganizationId";
								var data = {
									"organizationId" : organizationId
								};
								asynchronouselectusers(url, data);
								console.log("organizationId"
										+ data.organizationId);
								url = "${baseUrl}/userCenter/getOrganizationAdmin";
								getOrganizationAdmin(url, data);
							});
		});

		//搜索
		$(".search-bt").click(function() {
			var accountsearch = $("#accountsearch").val();
			if (accountsearch == "" || accountsearch == null) {
				swal("", "请填写账号 ", "warning");
			} else {
			
				$(".call-account").each(function(index, element) {
					var account = $(this).text();
					if ($.trim(accountsearch) == $.trim(account)) {
						$(element).parent().css("backgroundColor", "#2A8FD5");
					} else {
						$(element).parent().css("backgroundColor", "#FFFFFF");
					}
				}); 
				
				$(".call-account").each(function(index, element) {
					$("html,body").animate({ scrollTop:$(this).offset().top},500);
				return false;
				});
			 
			}
		});

		//ok
		$(function() {
			$(".creatnew,.modify,.administrator").click(
							function() {
								var aorganizationId = $("h3").attr("id");
								var attrs = $(this).attr("class");
								$(".Ok").unbind('click').click(
												function() {
													//新建子部门
													if ("creatnew" == attrs) {
														var newAorganizationName = $("#newAorganization").val();
														var url = "${baseUrl}/userCenter/addOrganization";
														var data = {
															"childrenAorganizationName" : newAorganizationName,
															"parentAorganizationId" : aorganizationId
														};
														asynchronoupdateutils(url, data);
														
													}
													//修改名字
													if ("modify" == attrs) {
														var name = $("#updateAOrganizationName").val();
														var url = "${baseUrl}/userCenter/saveEditName";
														var data = {
															"organizationId" : aorganizationId,
															"name" : name
														};
														asynchronoupdateutils(url, data);
													}
													//创建管理员
													if ("administrator" == attrs) {

														var name = $("#name").val();
														var phoneNo = $("#phone").val();
														var email = $("#email").val();
														var idCard = $("#idCard").val();
									
														var url = "${baseUrl}/userCenter/saveOrganizationAdmin";
														var data = {
															"aorganizationId" : aorganizationId,
															"name" : name,
															"phoneNo" : phoneNo,
															"email" : email,
															"idCard" : idCard
														};
													asynchronoupdateutils(url, data);

													}

												});
							});
		});

		$(".groupListBox").click(function() {
			$(".delete,.modify,.administrator").show();
		});

		//菜单更新
		$(function() {
			$("#toUserManage").click(function() {
				var companyName = $(this).text();
				$(".h2").text(companyName);
				$(".h2").attr("id", "");
				var url = "${baseUrl}/userCenter/getOrganizations";
				var data = {};
				asynchronousupdate(url, data);
				url = "${baseUrl}/userCenter/getAllEnhancedUsers";
				asynchronougetAllEnhancedUsers(url, data);
				$(".delete,.modify,.administrator").hide();
				$(".currentlt").removeClass("currentlt");
				$(this).parent().addClass("currentlt");
				return false;
			});
		});
		function asynchronousupdate(url, data) {
			$.ajax({
						url : url,
						type : "post",
						data : data,
						async : false,
						success : function(responseJson) {
							if (responseJson.respCode != "0000") {
								swal("", responseJson.respDesc, "warning");
							} else {
								if (responseJson.data == null) {
									return false;
								}
								var aOrganizations = responseJson.data;

								var str = "";
								var forTree = function(aOrganizations) {
									$(aOrganizations).each(
													function(i, item) {
														var organizationstr = "";
														if(!item.userId || item.userId == null){
														organizationstr = '<p class="allGroup" id="'+item.id+'"><i></i>'+item.name+'</p>';
														var childstr = childAOrganization(item.enhancedChildAOrganizations,organizationstr);
														}
														str += childstr;
													});
											return str;
								}
								var childAOrganization = function(childAOrganizations, organizationstr) {

									if (childAOrganizations != null) {
										organizationstr += '<div class="groupList">';
										for (var i = 0; i < childAOrganizations.length; i++) {
											if (childAOrganizations[i]["userId"]==null) {
												organizationstr += '<h2 class="allGroup" id="'+childAOrganizations[i]["id"]+'"><i></i>'+childAOrganizations[i]["name"]+'</h2>';
												
												if (childAOrganizations[i]["enhancedChildAOrganizations"] != null) {	
													organizationstr+='<ul class="groupNaList"><div class="groupList">';
													$(childAOrganizations[i]["enhancedChildAOrganizations"]).each(
														function(j, k) {
															if(k.userId == null){
																 organizationstr+='<h2 class="allGroup" id="'+k.id+'"><i></i>'+k.name+'</h2>';
															}
															});
													organizationstr+=' </div></ul>';
													/* 递归暂未使用  childAOrganization(childAOrganizations[i]["enhancedChildAOrganizations"],organizationstr);
												 */
												 }
											}
										}
										organizationstr += "</div>";
									}
									return organizationstr;
								}
								var aOrganization = forTree(aOrganizations);
								console.log(aOrganization);
								$(".groupListBox").html(aOrganization);
							}
						},
						error : function(msg) {
							//alert(msg);
						}
					});
		};
		//查找所有 
		function asynchronougetAllEnhancedUsers(url, data) {
			$
					.ajax({
						url : url,
						type : "post",
						data : data,
						async : true,
						success : function(responseJson) {
							if (responseJson.respCode != "0000") {
								//swal("", responseJson.respDesc);
							} else {
								var html = "";
								if (responseJson.data == null) {
									$(".call-content").html(html);
									return false;
								}
								var users = responseJson.data;
								console.log(users);

								$
										.each(
												users,
												function(i, item) {
													if (item.allName == null) {
														html += "<tr>";
														if(item.enhancedParentAOrganization == null){
															html += "<td class='checkboz'> <label><input type='checkbox' class='"+item.enhancedEUser189.phoneNo
																+ "' id='parentAOrganizationIdEmpty' value='"+item.enhancedEUser189.id+"' name='call-ba'><i></i></label></td>";
														}else{
															html += "<td class='checkboz'> <label><input type='checkbox' id='"+item.enhancedParentAOrganization.id+"' value='"+item.enhancedEUser189.id+"' name='call-ba'><i></i></label></td>";
														}
														
														html += "<td class='call-name'>"
																+ item.enhancedEUser189.name
																+ "</td>";
														html += "<td  class='call-account'> "
																+ item.enhancedEUser189.phoneNo
																+ "</td>";
														html += "<td  class='call-department'> "
																+ "</td>";
														html += "</tr>";
													} else {
														html += "<tr>";
														if(item.enhancedParentAOrganization == null){
															html += "<td class='checkboz'> <label><input type='checkbox' id='parentAOrganizationIdEmpty' value='"+item.enhancedEUser189.id+"' name='call-ba'><i></i></label></td>";
														}else{
															html += "<td class='checkboz'> <label><input type='checkbox' id='"+item.enhancedParentAOrganization.id+"' value='"+item.enhancedEUser189.id+"' name='call-ba'><i></i></label></td>";
														}
														html += "<td class='call-name'>"
																+ item.enhancedEUser189.name
																+ "</td>";
														html += "<td  class='call-account'> "
																+ item.enhancedEUser189.phoneNo
																+ "</td>";
														html += "<td  class='call-department'> "
																+ item.allName
																+ "</td>";
														html += "</tr>";
													}
												});
								$(".call-content").html(html);
							}
						},
						error : function(msg) {
							//alert(msg);
						}
					});
		};
		//组织ID查找用户  
		function asynchronouselectusers(url, data) {
			$
					.ajax({
						url : url,
						type : "post",
						data : data,
						async : true,
						success : function(responseJson) {
							var html = "";
							if (responseJson.respCode != "0000") {
								
								$(".call-content").html(html);
							} else {

								if (responseJson.data == null) {

									$(".call-content").html(html);
									return false;
								}
								var users = responseJson.data;
								$
										.each(
												users,
												function(i, item) {
													html += "<tr>";
													if(item.enhancedParentAOrganization == null){
														html += "<td class='checkboz'> <label><input type='checkbox' id='parentAOrganizationIdEmpty' value='"+item.enhancedEUser189.id+"' name='call-ba'><i></i></label></td>";
													}else{
														html += "<td class='checkboz'> <label><input type='checkbox' id='"+item.enhancedParentAOrganization.id+"' value='"+item.enhancedEUser189.id+"' name='call-ba'><i></i></label></td>";
													}
													html += "<td class='call-name'>"
															+ item.enhancedEUser189.name
															+ "</td>";
													html += "<td  class='call-account'> "
															+ item.enhancedEUser189.phoneNo
															+ "</td>";
													html += "<td  class='call-department'> "
															+ item.allName
															+ "</td>";

													html += "</tr>";

												});
								$(".call-content").html(html);
							}
						},
						error : function(msg) {
							alert(msg);
						}
					});
		};
		// 公用 
		function asynchronoupdateutils(url, data) {
			$.ajax({
				url : url,
				type : "post",
				data : data,
				async : true,
				success : function(responseJson) {
					if (responseJson.respCode == "0000") {
						swal("", "成功", "success");
						 window.location.reload();
					}
					
				},
				error : function(msg) {

					//	alert(msg);
				}
			});
		};
		// 获取管理员信息
		function getOrganizationAdmin(url, data) {
			$
					.ajax({
						url : url,
						type : "post",
						data : data,
						async : false,
						success : function(responseJson) {
							if (responseJson.respCode != "0000") {
								swal("", responseJson.respDesc);
							} else {
								var organizationAdmin = responseJson.data;
								if (organizationAdmin != null) {

									$("#name")
											.val(
													organizationAdmin.enhancedEUser189.name);
									$("#phone")
											.val(
													organizationAdmin.enhancedEUser189.phoneNo);
									$("#email")
											.val(
													organizationAdmin.enhancedEUser189.email);
									$("#idCard")
											.val(
													organizationAdmin.enhancedEUser189.credentialsNo);
									$("#organizationId").val(
											organizationAdmin.id);

									$(".admin").html("修改管理员<span class='NotaryClose' title='关闭'></span>");
									$(".administrator").text("修改管理员");
									$("#update").text("修改");
									console.log($("#NotaryBox4").children(".NotaryConM").children(".NotaryBut"));
									$("#NotaryBox4").children(".NotaryConM").children(".NotaryBut").html('<span class="" id="update" style="display: block; margin: 0 auto;width:30%;">修改</span>');
								} else {
									$(".admin").html("创建管理员<span class='NotaryClose' title='关闭'></span>");
									$("#update").text("创建");
									$(".administrator").text("创建管理员");
									$("#name").val("");
								$("#phone").val("");
								$("#email").val("");
								$("#idCard").val("");
								$("#organizationId").val("");
								$("#NotaryBox4").children(".NotaryConM").children(".NotaryBut").html('<span class="Ok"  style="display: block; margin: 0 auto;">创建</span>');
								}
							}
						},
						error : function(msg) {

							//	alert(msg);
						}
					});
		};
		//移动分组 -获取所以组列表
		function asynchronoMoveOrganization(url, data) {
			$
					.ajax({
						url : url,
						type : "post",
						data : data,
						async : true,
						success : function(responseJson) {
							if (responseJson.respCode == "0001") {
								swal("", responseJson.respDesc, "success");
							} else {
								var organizations = responseJson.data
								console.log("----------" + organizations);
								var str = "";
								$(organizations)
										.each(
												function(_inx, organization) {
													str += "<option value='"+organization.id+"'>"
															+ organization.allName
															+ "</option>";

												});
								$(".move").children().html(str);
							}

						},
						error : function(msg) {

							//	alert(msg);
						}
					});
		};
	</script>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
	<script src="${baseUrl}/resources/script/evidpage.js"></script>
</body>
</html>