<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/web/commons/global.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>月度数据-公证录音</title>
<link rel="stylesheet" href="${baseUrl}/resources/css/style_manage/style-manage.css">
<link rel="stylesheet" href="${baseUrl}/resources/css/user_center/business.css">
   <script type="text/javascript" src="${baseUrl}/resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<!--提示框开始-->
<div id="alertbox">
    <div class="alertbk"></div>
    <div class="alertcontent">
        <div class="alertcontit"><i>提示信息</i><span title="关闭" onclick="boclose()"></span></div>
        <div class="alertconbox">
            <input type="hidden" value="" id="contactboxid" />
            <p></p>
            <a onclick="contacok()">确定</a>　　<a onclick="boclose()">取消</a></div>
    </div>
</div>
<!--提示框结束-->
<!--名单新建开始-->
<div id="alertcon">
    <div class="alertbk"></div>
    <div class="alertcontent">
        <div class="alertcontit"><i>名单列表添加</i><span title="关闭" onclick="coclose()"></span></div>
        <div class="alertconbox">
            <ul class="conadd">
                <li><i>姓　　名：</i>
                    <input type="text" value="" id="contactname"  placeholder="输入姓名" maxlength="10"/>
                </li>
                <li><i>电话号码：</i>
                    <input type="tel" value="" id="contacttel" placeholder="输入手机/固定电话号码" maxlength="12"/>
                </li>
                <li class="addtips"></li>
                <a onclick="conaddok()">确定</a>　　<a onclick="coclose()">取消</a>
            </ul>
            <ul class="conaddtip" style="display:none">
                <b>恭喜您，名单添加成功！</b><a onclick="conaddback()">继续添加</a>　　<a onclick="coclose()">关闭</a>
            </ul>
        </div>
    </div>
</div>
<!--名单新建结束-->
<!-- 引入头部 -->
   <input type="hidden" id="navID" value="0">
<jsp:include page="../commons/header.jsp"></jsp:include>
<section class="main">
    <!-- 引入菜单-->
	<jsp:include page="../commons/left_menu.jsp"></jsp:include>

    <div class="main-right">
        <div class="telephone-box">
            <div class="tele-news">
                <p class="tele-titlec"><b>保全模式设置</b></p>
                <ul class="contactbox">
                    <li id="all" class="conl">
                        <input type="radio" name="contype" checked="checked" value="0" />
                        <i></i><b>全部保全</b>
                        <p>所有通话均保全</p>
                    </li>
                    <li id="black">
                        <input type="radio" name="contype" value="1" />
                        <i></i><b>指定保全：除黑名单</b>
                        <p>除黑名单号码的通话不保全<br />
                            其余所有通话均保全</p>
                    </li>
                    <li id="white">
                        <input type="radio" name="contype" value="2" />
                        <i></i><b>指定保全：仅白名单</b>
                        <p>除白名单号码的通话保全<br />
                            其余所有通话均不保全</p>
                    </li>
                </ul>
            </div>
            <div class="contactlist">
                <p class="tele-title"><span class="back_ms" title="返回"></span><b></b><i class="addle" title="点击添加加名单">添加</i><i class="textbox">
                    <input type="text" value="" placeholder="关键字" />
                    <i class="seach" title="搜索"></i></i><i class="allcont" title="全部联系人">全部</i></p>
                <div class="contact-tit"><span>姓名</span><span>电话号码</span><span>操作</span></div>
                <ul class="contact">
                    <!--<p class="contact-tip"><i></i>为空，请<i class="addle" title="点击添加名单">添加</i></p>--><!--为空时显示这一句-->
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                    <li>
                        <input type="text" value="金明" readonly="readonly" maxlength="10" />
                        <input type="text" value="15921356355" maxlength="13" readonly="readonly"/>
                        <span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>
                </ul>
            </div>
        </div>
    </div>
</section>
<!-- 引入尾部 -->
<jsp:include page="../commons/footer.jsp"></jsp:include>
<script>
    $(document).ready(function() {
        $.ajaxSetup( {
            cache : false
        });
        var tidd=$(".conl").attr("id");//当前模式
        var listtit=$(".tele-title b");
        var listtis=$(".contact-tip i:first");
        //黑白名单是否显示
        if(tidd=="all"){
            $(".contactlist").hide();
        }else if(tidd=="black"){
            $(listtit).html("").html("以下列表所有号码的语音通话不保全");
            $(listtis).html("").html("黑名单");
            $(".contactlist").show();
        }else{
            $(listtit).html("").html("以下列表所有号码的语音通话保全");
            $(listtis).html("").html("白名单");
            $(".contactlist").show();
        }
        //选择保全模式
        $(".contactbox>li").on("click",function(){
            var idd=$(this).attr("id");//点击对象
            var tidd=$(".conl").attr("id");//当前模式
            var tipinp=$("#contactboxid");//提示层隐藏域
            var tiptextb=$(".alertconbox p");//提示层文字
            var allti='<b>您确定将保全模式修改为“全部保全”吗？</b><i>点击确定后“所有通话均保全”</i>';
            var blacktip='<b>您确定将保全模式修改为“指定保全：除黑名单”吗？</b><i>点击确定后“除黑名单号码的通话不保全，其余所有通话均保全”请添加黑名单列表</i>';
            var whitetip='<b>您确定将保全模式修改为“指定保全：仅白名单”吗？</b><i>点击确定后“除白名单号码的通话保全，其余所有通话均不保全”<b>请立即添加白名单列表</b></i>';
            if(idd==tidd){
                if($(window).width()<720){
                    if(idd=="black"||idd=="white"){
                        $(".contactlist").animate({left:'0'});
                        $(".contactlist>.tele-title").animate({left:'0'})
                    }
                }
            }else if(idd=="all"){
                console.log("全部");
                $(tipinp).val("").val(idd);
                $(tiptextb).html("").html(allti);
                $("#alertbox").show();
            }else if(idd=="black"){
                console.log("黑名单");
                $(tipinp).val("").val(idd);
                $(tiptextb).html("").html(blacktip);
                $("#alertbox").show();
            }else{
                console.log("白名单");
                $(tipinp).val("").val(idd);
                $(tiptextb).html("").html(whitetip);
                $("#alertbox").show();
            }
        });
        //添加
        $(".addle").on("click",function(){
            $("#alertcon").show();
        });
        //编辑
        $(document).on("click",".listtext",function(){
            $(this).parent().prevAll("input").removeAttr("readonly").parent().addClass("edit");
            $(this).prev().show().nextAll().hide();
        });
        //保存
        $(document).on("click",".listok",function(){
            $(this).parent().prevAll("input").attr("readonly","readonly").parent().removeClass("edit");
            $(this).hide().nextAll().show();
        });
        //删除
        $(document).on("click",".listdele",function(){
            var a = window.confirm('真的要删除么?');
            if(a==1){
                $(this).parents("li").remove();
            }
        });
        //返回
        $(".back_ms").on("click",function(){
            $(".contactlist").animate({left:'100%'});
            $(".contactlist>.tele-title").animate({left:'100%'})
        });
    });
    //确定保全模式
    function contacok(){
        var cid=$("#contactboxid").val();//提示层隐藏域值
        var tili=$(".contactbox").find("li#"+cid);
        var listtit=$(".tele-title b");
        var listtis=$(".contact-tip i:first");
        $(tili).addClass("conl").siblings().removeClass("conl");
        $(tili).find("input[type=radio]").click();
        if(cid=="all"){
            $(".contactlist").hide();
        }else if(cid=="black"){
            $(listtit).html("").html("以下列表所有号码的语音通话不保全");
            $(listtis).html("").html("黑名单");
            $(".contactlist").show();
            if($(window).width()<720){
                $(".contactlist").animate({left:'0'});
                $(".contactlist>.tele-title").animate({left:'0'})
            }
        }else{
            $(listtit).html("").html("以下列表所有号码的语音通话保全");
            $(listtis).html("").html("白名单");
            $(".contactlist").show();
            if($(window).width()<720){
                $(".contactlist").animate({left:'0'});
                $(".contactlist>.tele-title").animate({left:'0'})
            }
        }
        $("#alertbox").hide("slow");
    }
    //添加名单
    function conaddok(){
        var reltdtel=/^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
        var contactname=$("#contactname").val();
        var contacttel=$("#contacttel").val();
        var lids='<li><input type="text" value="'+contactname+'"  maxlength="10" readonly="readonly" /><input type="text" value="'+contacttel+'"  maxlength="13" readonly="readonly"/><span><i class="listok">保存</i><i class="listtext">编辑</i><i class="listdele">删除</i></span></li>';
        if(reltdtel.test(contacttel)){
            $(".addtips").html("");
            $(".contact").prepend(lids);
            $("#contactname").val("");
            $("#contacttel").val("");
            $(".conadd").hide().next().show();
        }else{$(".addtips").html("请正确的输入电话或手机号码")}
    }
    //关闭添加名单
    function coclose(){
        $("#alertcon").hide("slow");
        $("#contactname").val("");
        $("#contacttel").val("");
        $(".conaddtip").hide().prev().show();
    }
    //添加成功，继续添加
    function conaddback(){
        $(".conaddtip").hide().prev().show();
    }
    function boclose(){
        $("#alertbox").hide("slow");
    }
</script>
</body>
</html>