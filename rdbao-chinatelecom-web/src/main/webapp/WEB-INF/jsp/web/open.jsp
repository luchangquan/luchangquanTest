<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>测试页面</title>
</head>
<body>
	<form action="../openSpe" method="post">
		<span style="color: red;">
			账户名：
			<input type="text" autofocus="autofocus" name="account" value="jgshun13636621498" />
		</span>
		<br />
		<span style="color: red;">
			号码：
			<input type="text" name="phoneNo" value="13636621498" />
		</span>
		<br />
		<span style="color: red;">
			虚拟号码：
			<input type="text" name="virtualNo" value="13636621498" />
		</span>
		<br />
		<span style="color: red;">
			用户名：
			<input type="text" name="name" value="姜国顺" />
		</span>
		<br /> 公司ID：
		<input type="text" name="companyId" />
		<br />
		<span style="color: red;">
			邮箱：
			<input type="text" name="email" value="13636621498@189.com" />
		</span>
		<br />
		<span style="color: red;">
			密码：
			<input type="text" name="password" value="11111111" />
		</span>
		<br /> 证件类型：
		<input type="text" name="credentialsType" value="99" />
		<br /> 证件号码：
		<input type="text" name="credentialsNo" />
		<br /> 证件保存地址：
		<input type="text" name="credentialsPath" />
		<br /> 用户类型：
		<input type="text" name="type" value="2" />
		1 公司管理员 2个人或员工 <br /> 性别：
		<input type="text" name="gender" value="0" />
		<br /> 开户来源：
		<input type="text" name="openSource" value="3" />
		3人科开到上海电信 4人科开到智恒 <br /> 昵称：
		<input type="text" name="nickname" value="昵称字段" />
		<br />
		<span style="color: red;">
			操作类型：
			<input type="text" name="optype" value="add" />
			add代表开户 delete代表销户
		</span>
		<br /> 费率：
		<input type="text" name="rate" value="38" />
		38元-2G 58元-5G 88元-10G <br />电信提供的产品ID：
		<input type="text" name="product" value="yzbzj-renke" />
		被叫产品ID“yzbbj-renke” 主叫产品ID“yzbzj-renke” <br /> 电信提供的服务ID：
		<input type="text" name="serviceId" value="y13500000000000000001" />
		固网：y13500000000000000001代表开主叫，y13500000000000000002代表开被叫 &nbsp;&nbsp; C网：cwyzb1代表开主叫，cwyzb2代表开被叫<br /> 来源网络：
		<input type="text" name="fromNet" value="cw" />
		cw:C网 gw:固网<br /> 开通语音提醒：
		<input type="text" name="openVoiceRemind" value="3" />
		1开通主叫提醒 2开通被叫提醒 3开通主被叫提醒 4关闭主叫提醒 5关闭被叫提醒 6关闭主被叫提醒 <br /> 开通语音服务类型：
		<input type="text" name="openVoiceType" value="3" />
		1开通主叫 2开通被叫 3开通主被叫 4关闭主叫 5关闭被叫 6关闭主被叫<br /> 应用受理方：
		<input type="text" name="voiceFrom" value="3" />
		1智恒 2人科 3上海电信spe<br /> 订购类型：
		<input type="text" name="experience" value="1" />
		0：正常订购 1：免费体验<br /> 公证处ID：
		<input type="text" name="pnoeId" value="37" />
		默认开到闵行spe <br /> 人科产品ID：
		<input type="text" name="appId" value="25" />
		默认开通分配给上海电信的appID <br />
		<input type="submit" value="提  交">
	</form>
</body>
</html>