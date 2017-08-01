<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>测试页面--上海电信放音配置</title>
</head>
<body>
	<form action="../../openSpe/voice/Update" method="post">
		<span style="color: red;">
			号码：
			<input type="text" name="phoneNo" value="13636621498" />
		</span>
		<br /> 电信提供的服务ID：
		<input type="text" name="serviceId" value="y13500000000000000001" />
		固网：y13500000000000000001代表开主叫，y13500000000000000002代表开被叫 &nbsp;&nbsp; C网：cwyzb1代表开主叫，cwyzb2代表开被叫<br /> 来源网络：
		<input type="text" name="fromNet" value="cw" />
		cw:C网 gw:固网<br /> 开通语音提醒：
		<input type="text" name="openVoiceRemind" value="3" />
		1开通主叫提醒 2开通被叫提醒 3开通主被叫提醒 4关闭主叫提醒 5关闭被叫提醒 6关闭主被叫提醒 <br />
		<input type="submit" value="提  交">
	</form>
</body>
</html>