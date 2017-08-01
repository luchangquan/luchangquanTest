<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>涉外公证业务管理平台</title>
<meta name="copright" content="上海人科数据科技有限公司">
<meta name="KEYWords" content="涉外公证，审核，公证处，业务">
<meta name="Robots" content="all">
<meta name="Author" content="MaggieXu">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="${baseUrl}/resources/script/sweetalert.min.js"></script>
<link href="${baseUrl}/resources/css/sweetalert.css" rel="stylesheet">
<link href="${baseUrl}/resources/css/notary/style.css?v=1"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${baseUrl}/resources/css/notary/details.css?v=1" />
<link rel="stylesheet" type="text/css"
	href="${baseUrl}/resources/css/notary/remind.css?v=1" />

<script src="${baseUrl}/resources/script/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script src="${baseUrl}/resources/js/notary/artDialog.js"
	type="text/javascript"></script>
<script src="${baseUrl}/resources/js/notary/iframeTools.js"
	type="text/javascript"></script>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	java.util.Date currentTime = new java.util.Date();//得到当前系统时间

	String str_date1 = formatter.format(currentTime); //将日期时间格式化
%>
<script>
	$(document)
			.ready(
					function() {

						/* 获取预约状态判断显示底部哪些按钮 */
						var status = $("#status").val();
						if (status == "SUCCESS") {
							var text = "<a href='javascript:void(0)' onclick='notaried()'><button type='button' class='button-s4'>已出证</button></a>"
									+ "<button type='button' class='button-s4 ' onclick='art.dialog.close()'>取消</button>";
							$(".form-but").empty();
							$(".form-but").html(text);
						} else if (status == "FAIL" || status == "OUTED") {
							var text = "<button type='button' class='button-s4 ' onclick='art.dialog.close()'>取消</button>";
							$(".form-but").empty();
							$(".form-but").html(text);
						}

						/* md5校验*/
						$('.mdJy')
								.click(
										function() {
											var $this = $(this);
											var id = $this.next("input").val();
											var nppCode = $("#nppCode").val();
											$
													.ajax({
														method : 'get',
														url : baseUrl
																+ "/notary/e/checkFileMd5?id="
																+ id
																+ "&nppCode="
																+ nppCode,
														success : function(data) {
															/* 判断是否返回成功，9999为失败，0000为成功*/
															if (data.respCode == "9999") {
																swal(
																		"",
																		data.respDesc);
															} else if (data.respCode == "0000") {
																if (data.data.pass == true) {
																	/*成功修改样式*/
																	$this
																			.html("MD5校验成功");
																	$this
																			.attr(
																					"class",
																					"zjBt");
																} else {
																	swal(
																			"",
																			data.respDesc);
																}
															}
														}
													})
										});

						/* 下载文件 */
						$(".url")
								.click(
										function() {
											var $this = $(this);
											if ($this.parent().parent().find(
													"span").html() == "MD5校验成功") {
												var evidenceId = $this.prev(
														"input").val();
												var fileId = $this
														.next("input").val();
												var nppCode = $("#nppCode")
														.val();
												window
														.open("${baseUrl}/notary/e/downloadFile?evidenceId="
																+ evidenceId
																+ "&id="
																+ fileId
																+ "&nppCode="
																+ nppCode);
											} else {
												swal("", "请进行MD5校验");
											}

										})

						$(".urlSpecial")
								.click(
										function() {
											var evidenceId = $this
													.prev("input").val();
											var fileId = $this.next("input")
													.val();
											var nppCode = $("#nppCode").val();
											window
													.open("${baseUrl}/notary/e/downloadFile?evidenceId="
															+ evidenceId
															+ "&id="
															+ fileId
															+ "&nppCode="
															+ nppCode);
										})

					});

	function pass() {
		document.getElementById("apper").style.display = "block";
		document.getElementById("fade").style.display = "block";
	}
	function none() {
		document.getElementById("fade").style.display = "none";
		document.getElementById('apper').style.display = 'none';
		document.getElementById('apper4').style.display = 'none';
		document.getElementById('apper5').style.display = 'none';
	}
	function save() {
		/* 审核通过按钮 */
		var id = $("#id").val();
		$.ajax({
			method : 'get',
			url : baseUrl + "/notary/nr/updateStatus?id=" + id
					+ "&statusString=2",
			success : function(data) {
				if (data.success != null) {
					/*成功则打开和关闭部分弹窗*/
					document.getElementById("apper").style.display = "none";
					document.getElementById('apper0').style.display = 'block';
				} else if (data.error != null) {
					swal("", data.error);
				}
			}

		})

	}
	function refuse() {
		document.getElementById("apper5").style.display = "block";
		document.getElementById("fade").style.display = "block";
	}
	function save2() {
		/* 拒绝通过的按钮 */
		var refuseReason = $("#refuseReason").val();
		var id = $("#id").val();
		$.ajax({
			type : 'POST',
			data : {
				'id' : id,
				'statusString' : 3,
				'reason' : refuseReason
			},
			dataType : "json",
			url : baseUrl + "/notary/nr/updateStatus",
			success : function(data) {
				if (data.success != null) {
					document.getElementById("apper4").style.display = "none";
					document.getElementById("apper5").style.display = "none";
					document.getElementById('apper0').style.display = 'block';
				} else if (data.error != null) {
					swal("", data.error);
				}
			}
		});
	}
	/* 已出证按钮 */
	function notaried() {
		var id = $("#id").val();
		$.ajax({
			method : 'get',
			url : baseUrl + "/notary/nr/updateStatus?id=" + id
					+ "&statusString=5",
			success : function(data) {
				if (data.success != null) {
					document.getElementById("apper").style.display = "none";
					document.getElementById('apper0').style.display = 'block';
				}
				if (data.error != null) {
					swal("", data.error);
				}
			}

		})
	}

	function save3() {
		document.getElementById('apper1').style.display = 'block';
	}
	function success() {
		document.getElementById("apper1").style.display = "none";
		document.getElementById("fade").style.display = "none";
	}
	function closeArtDiaLog() {
		var win = art.dialog.open.origin;
		win.location.reload();
	}
</script>
</head>
<body>
	<input type="hidden" value="${enhancedENotarizationReserve.status}"
		id="status" />
	<input type="hidden" value="${enhancedENotarizationReserve.id}" id="id">
	<input type="hidden" value="${nppCode}" id="nppCode">
	<div id="fade" class="black_overlay"></div>

	<div id="apper">
		<p
			style="text-align: center; font-size: 16px; line-height: 40px; margin-top: 25px;">请确认事项材料无误后点击提交通过！</p>
		<div class="submit2">
			<div class="button-s">
				<a href="javascript:void(0)" onclick="none()"><input class="no"
					type="submit" value="取消" /></a> <a href="javascript:void(0)"
					onclick="save()"><input class="yes" type="submit" value="提交" /></a>
			</div>
		</div>
	</div>

	<div id="apper0">
		<p>提交成功！</p>
		<div class="submit3">
			<div class="button-s">
				<a onclick="closeArtDiaLog()"><input class="yes" type="submit"
					value="确定" /></a>
			</div>
		</div>
	</div>

	<div id="apper4">
		<div class="write3">
			<p>常用意见:</p>
			<ul>
				<li><input type="checkbox" /><label>&nbsp;&nbsp;&nbsp;&nbsp;身份证材料不正确</label></li>
				<li><input type="checkbox" /><label>&nbsp;&nbsp;&nbsp;&nbsp;户口簿材料不正确</label></li>
				<li><input type="checkbox" /><label>&nbsp;&nbsp;&nbsp;&nbsp;无犯罪记录证明材料不正确</label></li>
				<li><input type="checkbox" /><label>&nbsp;&nbsp;&nbsp;&nbsp;代理人身份证材料不正确</label></li>
				<li><input type="checkbox" /><label>&nbsp;&nbsp;&nbsp;&nbsp;签字确认材料不正确</label></li>
			</ul>
		</div>
		<div class="write3">
			<p>手输意见:</p>
			<textarea></textarea>
		</div>
		<div class="submit2">
			<div class="button-s">
				<a href="javascript:void(0)" onclick="none()"><input class="no"
					type="submit" value="取消" /></a> <a href="javascript:void(0)"
					onclick="saveb()"><input class="yes" type="submit" value="提交" /></a>
			</div>
		</div>
	</div>

	<div id="apper5">
		<div class="write3">
			<p>无法办理原因:</p>
			<textarea id="refuseReason"></textarea>
		</div>
		<div class="submit2">
			<div class="button-s">
				<a href="javascript:void(0)" onclick="none()"><input class="no"
					type="submit" value="取消" /></a> <a href="javascript:void(0)"
					onclick="save2()"><input class="yes" type="submit" value="提交" /></a>
			</div>
		</div>
	</div>

	<div class="formBox">
		<!--订单部分-->
		<div class="content">
			<div style="height: 40px; line-height: 40px; font-size: 21px;">
				<span>申请信息</span>
			</div>
			<table class="c2">
				<tbody>
					<tr>
						<th>委托代理人姓名：</th>
						<td>${enhancedENotarizationReserve.agentName}</td>
						<th>手机号码：</th>
						<td>${enhancedENotarizationReserve.phoneNo}</td>

					</tr>
					<tr>
					</tr>
					<!--证件类型-->
					<tr>
						<th>公证主体：</th>
						<td>${enhancedENotarizationReserve.notarySubject}</td>
					</tr>
				</tbody>
			</table>

		</div>
		<!--在线播放/下载-->
		<div class="content">
			<div style="height: 40px; line-height: 40px; font-size: 21px;">
				<span>证据列表</span>
			</div>

			<c:forEach var="item"
				items="${enhancedENotarizationReserve.enhancedMEvidences}">
				<c:if test="${item.category.code == 20}">
					<div class="audio-play">
						<h3>
							<i>证据编号：</i>${item.code}</h3>
						<div>
							证据来源：<span>本处APP</span>
						</div>
						<table class="c2 x1">
							<tr>
								<th>取证帐号：</th>
								<td><c:choose>
										<c:when test="${not empty item.enhancedUser.account}">
                        	${item.enhancedUser.account}
                        	</c:when>
										<c:otherwise>   
        					  ${enhancedENotarizationReserve.phoneNo}
 							 </c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<th>照片拍摄时间：</th>
								<td><fmt:formatDate value="${item.enhancedItem.takeTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<th>照片拍摄地点：</th>
								<td>${item.enhancedItem.locationDesc}</td>
							</tr>

							<tr>
								<c:forEach var="items" items="${item.enhancedMREvidenceFiles}">
									<th>文 件：</th>
									<td><input type="hidden" value="${item.id}"
										class="evidenceId"> <a href="#" class="url">照片.jpg</a>
										<input type="hidden" value="${items.id}" class="fileId">
									</td>

									<th>MD5值：</th>
									<td class="md5jy"><input type="text" value="${items.md5}"
										data-md5="" readonly>
									<td class="md5jy"><span class="mdJy">MD5立即校验</span> <input
										type="hidden" value="${items.id}" class="fileId"></td>
								</c:forEach>
							</tr>
						</table>
						<p class="signTime"><%=str_date1%></p>
					</div>
				</c:if>

				<c:if test="${item.category.code == 21}">
					<div class="audio-play">
						<h3>
							<i>证据编号：</i>${item.code}</h3>
						<div>
							证据来源：<span>本处APP</span>
						</div>
						<table class="c2 x1">
							<tr>
								<th>取证帐号：</th>
								<td><c:choose>
										<c:when test="${not empty item.enhancedUser.account}">
                        	${item.enhancedUser.account}
                        	</c:when>
										<c:otherwise>   
        					  ${enhancedENotarizationReserve.phoneNo}
 							 </c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<th>录音时长：</th>
								<td><fmt:formatNumber type="number"
										value="${item.enhancedItem.duration/3600}"
										maxFractionDigits="0" />时 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)/60}"
										maxFractionDigits="0" />分 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)%60}"
										maxFractionDigits="0" />秒</td>
								<th>录音地点：</th>
								<td>${item.enhancedItem.locationDesc}</td>
							</tr>
							<tr>
								<c:forEach var="items" items="${item.enhancedMREvidenceFiles}">
									<th>文 件：</th>
									<td><input type="hidden" value="${item.id}"
										class="evidenceId"> <a href="#" class="url">录音.mp3</a>
										<input type="hidden" value="${items.id}" class="fileId">
									</td>

									<th>MD5值：</th>
									<td class="md5jy"><input type="text" value="${items.md5}"
										data-md5="" readonly></td>
									<td class="md5jy"><span class="mdJy">MD5立即校验</span> <input
										type="hidden" value="${items.id}" class="fileId"></td>
								</c:forEach>
							</tr>
						</table>
						<p class="signTime"><%=str_date1%></p>
					</div>
				</c:if>

				<c:if test="${item.category.code == 22}">
					<div class="audio-play">
						<h3>
							<i>证据编号：</i>${item.code}</h3>
						<div>
							证据来源：<span>本处APP</span>
						</div>
						<table class="c2 x1">
							<tr>
								<th>取证帐号：</th>
								<td><c:choose>
										<c:when test="${not empty item.enhancedUser.account}">
                        	${item.enhancedUser.account}
                        	</c:when>
										<c:otherwise>   
        					  ${enhancedENotarizationReserve.phoneNo}
 							 </c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<th>录像拍摄时长：</th>
								<td><fmt:formatNumber type="number"
										value="${item.enhancedItem.duration/3600}"
										maxFractionDigits="0" />时 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)/60}"
										maxFractionDigits="0" />分 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)%60}"
										maxFractionDigits="0" />秒</td>
								<th>录像拍摄地点：</th>
								<td>${item.enhancedItem.locationDesc}</td>
							</tr>
							<tr>
								<c:forEach var="items" items="${item.enhancedMREvidenceFiles}">
									<th>文 件：</th>
									<td><input type="hidden" value="${item.id}"
										class="evidenceId"> <a href="122901886.flv"
										download="122901886" title="点击下载">录像.flv</a> <input
										type="hidden" value="${items.id}" class="fileId"></td>

									<th>MD5值：</th>
									<td class="md5jy"><input type="text" value="${items.md5}"
										data-md5="" readonly></td>

									<td class="md5jy"><span class="mdJy">MD5立即校验</span> <input
										type="hidden" value="${items.id}" class="fileId"></td>
								</c:forEach>
							</tr>

						</table>
						<p class="signTime"><%=str_date1%></p>
					</div>
				</c:if>

				<c:if test="${item.category.code == 5}">
					<div class="audio-play">
						<h3>
							<i>证据编号：</i>${item.code}</h3>
						<div>
							证据来源：<span>本处电话录音平台</span>
						</div>
						<table class="c2 x1">
							<tr>
								<th>取证号码：</th>
								<td><c:choose>
										<c:when test="${not empty item.enhancedUser.account}">
                        	${item.enhancedUser.account}
                        	</c:when>
										<c:otherwise>   
        					  ${enhancedENotarizationReserve.phoneNo}
 							 </c:otherwise>
									</c:choose></td>
								<th>显示号码：</th>
								<td>${item.enhancedItem.callingNo}</td>
							</tr>
							<tr>
								<th>对方号码：</th>
								<td>${item.enhancedItem.calledNo}</td>
								<th>呼叫类型：</th>
								<td>${item.enhancedItem.callType.desc}</td>

							</tr>

							<tr>
								<th>通话时长：</th>
								<td><fmt:formatNumber type="number"
										value="${item.enhancedItem.duration/3600}"
										maxFractionDigits="0" />时 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)/60}"
										maxFractionDigits="0" />分 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)%60}"
										maxFractionDigits="0" />秒</td>
							</tr>
							<tr>
								<c:forEach var="items" items="${item.enhancedMREvidenceFiles}">
									<th>文 件：</th>
									<td><input type="hidden" value="${item.id}"
										class="evidenceId"> <a href="#" class="url">录音.mp3</a>
										<input type="hidden" value="${items.id}" class="fileId">
									</td>

									<th>MD5值：</th>
									<td class="md5jy"><input type="text" value="${items.md5}"
										data-md5="" readonly></td>
									<td class="md5jy"><span class="mdJy">MD5立即校验</span><input
										type="hidden" value="${items.id}" class="fileId"></td>
								</c:forEach>
							</tr>
						</table>
						<p class="signTime"><%=str_date1%></p>
					</div>
				</c:if>

				<c:if test="${item.category.code == 4}">
					<div class="audio-play">
						<h3>
							<i>证据编号：</i>${item.code}</h3>
						<div>
							证据来源：<span>本处取证专用计算机</span>
						</div>
						<table class="c2 x1">
							<tr>
								<th>取证帐号：</th>
								<td><c:choose>
										<c:when test="${not empty item.enhancedUser.account}">
                        	${item.enhancedUser.account}
                        	</c:when>
										<c:otherwise>   
        					  ${enhancedENotarizationReserve.phoneNo}
 							 </c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<th>取证时长：</th>
								<td><fmt:formatNumber type="number"
										value="${item.enhancedItem.duration/3600}"
										maxFractionDigits="0" />时 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)/60}"
										maxFractionDigits="0" />分 <fmt:formatNumber type="number"
										value="${(item.enhancedItem.duration%3600)%60}"
										maxFractionDigits="0" />秒</td>
							</tr>

							<c:set var="flag" value="1" />
							<c:forEach var="itemFiles"
								items="${item.enhancedMREvidenceFiles}">
								<c:if test="${itemFiles.fileType.code==1}">
									<tr>
										<th>屏幕录像：</th>
										<td><input type="hidden" value="${item.id}"
											class="evidenceId"> <a href="#" class="url">录像.flv</a>
											<input type="hidden" value="${itemFiles.id}" class="fileId">
										</td>
										<th>MD5值：</th>
										<td class="md5jy"><input type="text"
											value="${itemFiles.md5}" data-md5="" readonly></td>
										<td class="md5jy"><span class="mdJy">MD5立即校验</span> <input
											type="hidden" value="${itemFiles.id}" class="fileId">
										</td>
									</tr>
								</c:if>

								<c:if test="${itemFiles.fileType.code==0}">
									<c:if test="${flag == 1}">
										<c:set var="flag" value="2" />
										<tr>
											<th>屏幕截图：</th>
											<td><input type="hidden" value="${item.id}"
												class="evidenceId"> <a href="#" class="urlSpecial">屏幕截图.zip</a>
												<input type="hidden" value="${itemFiles.id}" class="fileId">
											</td>
										</tr>
									</c:if>
								</c:if>


								<c:if test="${itemFiles.fileType.code==2}">

									<tr>
										<th>用户文件：</th>
										<td><input type="hidden" value="${item.id}"
											class="evidenceId"> <a href="#" class="url">文件.zip</a>
											<input type="hidden" value="${itemFiles.id}" class="fileId">
										</td>
										<th>MD5值：</th>
										<td class="md5jy"><input type="text"
											value="${itemFiles.md5}" data-md5="" readonly></td>
										<td class="md5jy"><span class="mdJy">MD5立即校验</span> <input
											type="hidden" value="${itemFiles.id}" class="fileId">
										</td>
									</tr>

								</c:if>
							</c:forEach>
						</table>
						<p class="signTime"><%=str_date1%></p>
					</div>

				</c:if>

			</c:forEach>

			<!--   <td class="md5jy">
                            <input type="text" value="JFYUH21HR838RHXV8HJHF7" data-md5="" readonly>
                            <span class="zjBt">MD5校验正确</span>
                        </td>
                        <span class="download"><a href="122901886.mp3" download="122901886" title="下载">下载</a></span> -->


		</div>

		<div class="formBox">
			<div class="content" style="margin-bottom: 100px;">
				<div style="height: 40px; line-height: 40px; font-size: 21px;">
					<span>操作日志</span>
				</div>
				<table class="c2">
					<tbody style="float: left; margin-left: 50px">
						<tr>
							<th>操作：</th>
							<td><span style="color: #004796">申请人首次提交</span></td>
						</tr>
						<tr class="show1 refuseRes">
							<th>时间：</th>
							<td><fmt:formatDate
									value="${enhancedENotarizationReserve.createTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="formBox">
			<div class="formBox">
				<form method="post" id="form0">
					<div class="form-but">
						<a href="javascript:void(0)" onclick="pass()"><button
								type="button" class="button-s4">审核通过</button></a> <a
							href="javascript:void(0)" onclick="refuse()"><button
								type="button" class="button-s4">拒绝办理</button></a>
						<button type="button" class="button-s4 "
							onclick="art.dialog.close()">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--  -->
</body>
</html>