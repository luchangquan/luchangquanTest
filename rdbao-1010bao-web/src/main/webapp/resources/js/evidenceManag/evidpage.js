/*
w * Created by kingM on 2017/3/27.
 */
var code=$("#code").val();	
var sourceNppCode=$("#sourceNppCode").val();
$(document).ready(function(){
    //组/成员
    $(document).on("click",".groupClose",function(){
        $(this).parent().animate({right:"-255px"},500);
    });
    $(document).on("click",".busiType",function(){
        $(".groupBox").animate({right:"0"},500);
    });
    $(document).on("click",".groupList>h2>i,.allGroup>i",function(){
        $(this).parent().next().slideToggle();
    });



    //返回顶部
    $(window).bind('scroll',function(){
        if ($(document).scrollTop() >100) {
            $(".scrollTop").show(1);
        }else{
            $(".scrollTop").hide(1);
        }
    });

    $(".scrollTop").on("click",function(){
        $('body,html').animate({scrollTop:0},500);
    });




    //证据夹--申请公证

    //全选
    $("input[name=AllCheckBt]").on("click",function(){
        var inPt=$("input[name=checkBtList]");
        if($(this).is(":checked")==true){
            $.each(inPt,function(){
                if($(this).is(":checked")==false){
                    $(this).click();
                }
            });
        }else{
            $.each(inPt,function(){
                if($(this).is(":checked")==true){
                    $(this).click();
                }
            });
        }
    });









    //搜索类型
    $(".evidGj p").on("click",function(){
        var selectGj=$(".selectGj");
        if($(selectGj).is(":hidden")==true){
            $(selectGj).show();
        }else{
            $(selectGj).hide();
        }
    });
    //
    $(".selectGj span").on("click",function(){
        var dataVal=$(this).attr("data-value");
        var text=$(this).text();
        $(this).parent().prev("p").attr("data-value",dataVal).html(text);
        $(".selectGj").hide();
    });



    //证据列表隔6个清除marginRight值
    var iboxnumber = $(".evidList").find(".evidListCon");
    var iboxnumberLe=iboxnumber.length;
    for (var i = 0; i < iboxnumberLe; i++) {
        a = 6;
        b = i*a-1;
        if (b < iboxnumberLe) {
            //console.log(b);
            $(iboxnumber).eq(b).attr("style","margin-right:0");
        }
    }
    //图片Box默认宽度
    var liApplyLi=$(".evidListCon");
    var applyInfoWidth=$(liApplyLi).width();
    $.each(liApplyLi,function(){
        $(this).find(".evidConImg").attr("style","width:"+applyInfoWidth+"px");
    });

    //更多
    $(document).on("click",".addMore",function(){
        var e = e||window.event;
        var pageX = e.pageX;
        var pageY = e.pageY;
        $(".moreBox").css({left:pageX-100+"px",top:pageY-140+"px"}).show();
        $(this).parents(".evidListCon").addClass("moreOn").siblings().removeClass("moreOn");
        $(this).parents(".evidListBox").siblings().find(".moreOn").removeClass("moreOn");
    });
    $("body").not(".moreBox *,.addMore").on("click",function(){
        $(".moreBox").hide();
    });


    //证据详情关闭
    $(".evidInfoClose").on("click",function(){
        $(".evidInfoBox").hide();
        $(".evidInfoContent").html("");
        $("body").removeAttr("style");
    });
	//删除
    
    $(".evidRemove").click(function(){
        swal({
            title:"",
            text:"你确定删除该条证据？",
            type:"warning",
            showCancelButton: true,
            closeOnConfirm: false
        },function(isConfirm){
            if(isConfirm){
		    	var id=$(".moreOn").find("input").val();
		    	var url=baseUrl+"/evidenceManag/updateById";
		    	var data={"id":id};
		    	$.ajax({
					url : url,
					type : "post",
					data : data,
					async : false,
					traditional: true,  
					success : function(response) {
				    	if(response.respCode!="0000"){
						 	if(response.respDesc=="缺少用户上下文"){
						 		window.location.reload();
						 	}else{
						 		 swal("",response.respDesc);
						 	}	    		
				    	}else{
				    		
				    		
				    		window.location.reload();
				    		swal("","删除成功");
				    	}
					
					},
					error : function(msg) {
						alert(msg);
					}
				}); 
            }
        });
		
    });
    
    $(".evidDown").click(function(){
    	var id=$(".moreOn").find("input").val();
    	var url=baseUrl+"/evidenceManag/getEvidenceInfo";
    	var data={"id":id};
    	var fileUrl="";
    	var	enhancedMEvidenceJson="";
    	$.ajax({
			url : url,
			type : "post",
			data : data,
			async : false,
			traditional: true,  
			success : function(response) {
		    	if(response.respCode!="0000"){
				 	if(response.respDesc=="缺少用户上下文"){
				 		window.location.reload();
				 	}else{
				 		 swal("",response.respDesc);
				 	}	    		
		    		
		    		return false;
		    	}else{
		    	    enhancedMEvidenceJson=response.data;
		    	    var evidenceArrays= enhancedMEvidenceJson.enhancedMREvidenceFiles;
		    	    for (var i = 0; i < evidenceArrays.length; i++) {
	            		if(evidenceArrays[i]['fileType']=='VIDEO'){
	            			fileUrl=evidenceArrays[i]['fileUrl'];
	            		
	            		}
            		}
		    	}
			
			},
			error : function(msg) {
				alert(msg);
			}
		}); 
		if(fileUrl!=""){
			window.open(fileUrl);
		}else{
			fileUrl=enhancedMEvidenceJson.enhancedMREvidenceFiles[0]['fileUrl'];
			window.open(fileUrl);
		}
		
    });
    
    //下载图片zip包
    $(".evidInfoBt .evidImgZipDown").click(function(){
    	var evidenceId=$(".evidInfoBt .evidInfoIdInputStore").val();
    	var url=baseUrl+"/evidenceManag/getEvidenceImgZip?evidenceId="+evidenceId;
    	window.open(url);
    });



    //证据详情
    $(document).on("click",".evidConImg",function(){
    	var id=$(this).find("#evidenceId").val();
    	var data={"id":id};
    	//根据id查询证据信息
    	var enhancedMEvidence="";
    	var responseJson="";
    	var url=baseUrl+"/evidenceManag/getEvidenceInfo";
		$.ajax({
			url : url,
			type : "post",
			data : data,
			async : false,
			traditional: true,  
			success : function(response) {
				responseJson=response;
			},
			error : function(msg) {
				alert(msg);
			}
		}); 
    	if(responseJson.respCode!="0000"){
    		
		 	if(responseJson.respDesc=="缺少用户上下文"){
		 		window.location.reload();
		 	}else{
		 		 swal("",responseJson.respDesc);
		 	}	    		
    		
    		
    		return false;
    	}
    	enhancedMEvidence=responseJson.data;
    	
    	console.log(enhancedMEvidence);
    	$(".evidInfoBt .evidInfoIdInputStore").val(enhancedMEvidence.id);
    	
        var dataType=$(this).parent().attr("data-type");//当前证据类型
        console.log(dataType);
        var $evidInfoContent=$(".evidInfoContent");//证据内容区域
        var tihImg=$(this).find("img").attr("src");//当前视频缩略图；
        $("body").attr("style","overflow:hidden");
        $(this).parents(".evidListCon").addClass("moreOn").siblings().removeClass("moreOn");//标识当前证据
        $(this).parents(".evidListBox").siblings().find(".moreOn").removeClass("moreOn");
        var address="";
		if(enhancedMEvidence.category=='APPVIDEO'||enhancedMEvidence.category=='APPPICTURE'||enhancedMEvidence.category=='APPVOICE'){
			address=enhancedMEvidence.enhancedItem.locationDesc;
			if(address==null){
			 address="";	
			}
		}
        //数据
									
        var evidData={"evidTitle":enhancedMEvidence.name,"evidNumber":enhancedMEvidence.code,"evidName":enhancedMEvidence.enhancedUser.account,"evidSite":address,"evidNotary":enhancedMEvidence.enhancedDNpp.name,"evidTime":format(enhancedMEvidence.createTime)

        };

        //通用部分
        var evidContent='<p class="evidInfoTitle">'+evidData.evidTitle+'<span>证据编号：'+evidData.evidNumber+'</span></p><p class="evidInfoBz"><span>存证人：'+evidData.evidName+'</span><span>地点：'+evidData.evidSite+'</span></p><p class="notaryTime"><span>存证公证处：'+evidData.evidNotary+'</span><time>存证时间：'+evidData.evidTime+'</time></p>';
        $(".evidInfoText").html(evidContent);
	//
        //证据类型
        switch (dataType){
			
            //视频类型
            case "video":
            	var VIDEOUrl="";
            	var imgArray=new Array();
            	var count=0;
            	var evidenceArray= enhancedMEvidence.enhancedMREvidenceFiles;
            	var zipArray=new Array();
            	var zipArrayCount=0;
            	for (var i = 0; i < evidenceArray.length; i++) {
            		if(evidenceArray[i]['fileType']=='VIDEO'){
            			VIDEOUrl=evidenceArray[i]['fileUrl'];
            			console.log(VIDEOUrl);
            		}else{
            			if(evidenceArray[i]['fileType']=="IMG"){
            				imgArray[count]=evidenceArray[i]['fileUrl'];
            				count+=1;
            			}
            		}
            		if(evidenceArray[i]['fileType']!="VIDEO"&&evidenceArray[i]['fileType']!="IMG"){
            			var zipDownloadUrl=evidenceArray[i]['fileUrl'];
            			var zipName=evidenceArray[i]['name'];
            			var zipJson={"zipDownloadUrl":zipDownloadUrl,"zipName":zipName};
            			zipArray[zipArrayCount]=zipJson;
            			zipArrayCount+=1;

            		}
            		
            	}
                var videoUrl=[[encodeURI(VIDEOUrl),format(enhancedMEvidence.enhancedItem.beginTime),format(enhancedMEvidence.enhancedItem.endTime),enhancedMEvidence.enhancedItem.duration],imgArray];//文件地址
               //var videoBox='<div class="evidVideo"><div class="reTips"><span>取证开始：'+videoUrl[0][1]+'</span><span>取证结束：'+videoUrl[0][2]+'</span><span>时长：'+Switch(videoUrl[0][3])+'</span></div><a class="bigLeft" title="上一张"></a><a class="bigRight" title="下一张"></a><div id="mediaplayer" class="videoPlay"><div class="flowplayer" data-swf="'+baseUrl+'/resources/script/media/flowplayer.swf" data-ratio="0.4167"><video><source type="video/flv" src="'+videoUrl[0][0]+'"></video></div></div><div class="bigIm"><img src="" class="bigImgSrc"></div><div class="smallIm"></div></div>';
                var videoBox='<div class="evidVideo"><div class="reTips"><span>取证开始：'+videoUrl[0][1]+'</span><span>取证结束：'+videoUrl[0][2]+'</span><span>时长：'+Switch(videoUrl[0][3])+'</span></div><a class="bigLeft" title="上一张"></a><a class="bigRight" title="下一张"></a><div id="mediaplayer" class="videoPlay"><div class="flowplayer" data-swf="'+baseUrl+'/resources/script/media/flowplayer.swf" data-ratio="0.4167"><video><source type="video/flv" src="'+videoUrl[0][0]+'"></video></div></div><div class="bigImgCo"><div class="bigIm"><img src="" class="bigImgSrc"></div></div><div class="smallIm"></div></div>';

                if(zipArray.length>0){
	                for (var i = 0; i < zipArray.length; i++) {
	              	  videoBox+='<div class="attachment">其他附件：<a href="'+zipArray[i]['zipDownloadUrl']+'" title="点击下载附件">'+evidData.evidTitle+'</a></div>';
	                }                	
                }

                $.getScript(baseUrl+"/resources/script/media/flowplayer.min.js");
                $evidInfoContent.html(videoBox);
                 flowplayer($(".flowplayer"));
                $(".smallIm").append('<span><img src="'+tihImg+'"/></span>');//插入视频缩略图
                $(".smallIm span:first").addClass("smon");//缩略图第一张为选中状态
           

				
                
                //是否有视频截图
                var videoUrl0len=videoUrl[1].length;
                if (videoUrl0len>=1){//有视频截图载入视频截图
                    for(i=0;i<videoUrl0len;i++){
                        var spanimg='<span><img src="'+videoUrl[1][i]+'"/></span>';
                        $(".smallIm").append(spanimg);
                    }
                    $(".bigLeft").hide();
                    $(".evidInfoBt .evidImgZipDown").show();
                }else{
                    $(".bigLeft").hide().next().hide();
                }
                break;


            //录音类型
            case "record":
            	var fileUrl=enhancedMEvidence.enhancedMREvidenceFiles[0]['fileUrl'];
            	var audiosrc = [fileUrl,format(enhancedMEvidence.enhancedItem.beginTime),format(enhancedMEvidence.enhancedItem.endTime),enhancedMEvidence.enhancedItem.duration];//文件地址
                var recordBox='<div class="evidRecord"><div class="reTips"><span>录音开始：'+audiosrc[1]+'</span><span>录音结束：'+audiosrc[2]+'</span><span>录音时长：'+Switch(audiosrc[3])+'</span></div><div class="audioPlay"></div></div>';
                $evidInfoContent.html(recordBox);
                var $audioRec = $(".audioPlay");
                var audiobox = '<audio preload="auto" controls><source id="audioREC" src="' + audiosrc[0]+ '"/><source src="audio.ogg" /><source src="audio.wav" /></audio>';
                $audioRec.html(audiobox);
                audiojs.events.ready(function() {
                    audiojs.createAll();
                });
                break;


            //图片类型
            case "image":
                var imageBox='<div class="evidImage"></div>';
                $evidInfoContent.html(imageBox);
                var imUrl="hbbrrfz.jpg";//文件地址
                var imbox="<img src="+enhancedMEvidence.enhancedMREvidenceFiles[0]['fileUrl']+"> ";
                $(".evidImage").append(imbox);
                break;


            //电话类型
            case "telephone":
                var type="";
            	if(enhancedMEvidence.enhancedItem.callType=='CALLING'){
            		type="呼出";
            	}else{
            		type="呼入";	
            	}
                var telsrc = [enhancedMEvidence.enhancedMREvidenceFiles[0]['fileUrl'],type,enhancedMEvidence.enhancedItem.callingNo,enhancedMEvidence.enhancedItem.calledNo,format(enhancedMEvidence.enhancedItem.callTime),format(enhancedMEvidence.enhancedItem.callTime+(enhancedMEvidence.enhancedItem.duration*1000)),enhancedMEvidence.enhancedItem.duration];//文件地址
                var telBox='<div class="evidRecord"><div class="reTips"><span>呼叫类型：'+telsrc[1]+'</span><span>本机号码：'+telsrc[2]+'</span><span>对方号码：'+telsrc[3]+'</span><span>通话开始：'+telsrc[4]+'</span><span>通话结束：'+telsrc[5]+'</span><span>通话时长：'+Switch(telsrc[6])+'</span></div><div class="audioPlay"></div></div>';
                $evidInfoContent.html(telBox);
                var $audioRec = $(".audioPlay");
                var telPlaybox = '<audio preload="auto" controls><source id="audioREC" src="' + telsrc[0] + '"/><source src="audio.ogg" /><source src="audio.wav" /></audio>';
                $audioRec.html(telPlaybox);
                audiojs.events.ready(function() {
                    audiojs.createAll();
                });
                break;
        }


        //显示证据详情图层
        $(".evidInfoBox").show();
    });



    //视频截图预览
    $(document).on("click",".smallIm span",function(){
        $(this).addClass("smon").siblings().removeClass("smon");
        var $smon=$(".smon");
        var smonPrev=$smon.prevAll().length;
        var smonNext=$smon.nextAll().length;
        if(smonPrev==0){
        	 $(".videoPlay").removeAttr("style");
            $(".bigLeft").hide().next().show();
        }else{
            $(".videoPlay").attr("style","margin-left:-100%");
            var tihImg=$(this).find("img").attr("src");
            $(".bigImgSrc").attr("src",tihImg);
            $(".bigLeft").show();
            if(smonNext==0){
                $(".bigRight").hide();
            }
        }
    });
    //上一张
    $(document).on("click",".bigLeft",function(){
        $(".smon").prev().addClass("smon").siblings().removeClass("smon");
        var $smon=$(".smon");
        $(".bigImgSrc").attr("src",$smon.find("img").attr("src"));
        $(".bigRight").show();
        var smonPrev=$smon.prevAll().length;
        if(smonPrev==0){
             $(".videoPlay").removeAttr("style");
            $(this).hide();
        }
    });
    //下一张
    $(document).on("click",".bigRight",function(){
        $(".smon").next().addClass("smon").siblings().removeClass("smon");
        var $smon=$(".smon");
        var smonPrev=$smon.prevAll().length;
        var smonNext=$smon.nextAll().length;
        if(smonPrev>0){
        	$(".videoPlay").attr("style","margin-left:-100%");
            $(".bigImgSrc").attr("src",$smon.find("img").attr("src"));
            $(".bigLeft").show();
        }
        if(smonNext==0){
            $(this).hide();
        }
    });





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
            
        var startTime=$("#start").val();
		var endTime=$("#end").val();
		var searchType=$(".evidGj p").attr("data-value");
		var searchValue=$("#searchValue").val();
		var category=$("#category").val();
	
		
		window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?category="+category+"&startTime="+startTime+"&endTime="+endTime+"&searchValue="+searchValue+"&searchType="+searchType+"&index="+inx+"&code="+code+"&sourceNppCode="+sourceNppCode;

            return false;
        }
        for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
            oAlink[i].onclick = function () {
                inx = parseInt(this.innerHTML);
                setPage(container, count, inx);
                       var startTime=$("#start").val();
		var endTime=$("#end").val();
		var searchType=$(".evidGj p").attr("data-value");
		var searchValue=$("#searchValue").val();
		var category=$("#category").val();
	
		
		window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?category="+category+"&startTime="+startTime+"&endTime="+endTime+"&searchValue="+searchValue+"&searchType="+searchType+"&index="+inx+"&code="+code+"&sourceNppCode="+sourceNppCode;
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
		var endTime=$("#end").val();
		var searchType=$(".evidGj p").attr("data-value");
		var searchValue=$("#searchValue").val();
		var category=$("#category").val();
	
		
		window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?category="+category+"&startTime="+startTime+"&endTime="+endTime+"&searchValue="+searchValue+"&searchType="+searchType+"&index="+inx+"&code="+code+"&sourceNppCode="+sourceNppCode;
            return false;
        }
    }()
}
//日期范围限制
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: '2013-01-01',
    max: laydate.now(),
    isbtn: false,
    istoday: false,
    choose: function(datas){
        end.min = datas;
        end.start = datas ;
    }
};
var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: '2013-01-01',
    max: laydate.now(),
    isbtn: false,
    istoday: false,
    choose: function(datas){
        start.max = datas;
    }
};
laydate(start);
laydate(end);
function format(str) {  
	var fmt = "yyyy-MM-dd hh:mm:ss";  
    var usedDate = new Date(str);  
    var o = {  
        "M+": usedDate.getMonth() + 1, //月份   
        "d+": usedDate.getDate(), //日   
        "h+": usedDate.getHours(), //小时   
        "m+": usedDate.getMinutes(), //分   
        "s+": usedDate.getSeconds(), //秒   
        "q+": Math.floor((usedDate.getMonth() + 3) / 3), //季度   
        "S": usedDate.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (usedDate.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
}
$(function(){
	//分页
	var totalPages=parseInt($("#totalPages").val());
	var index=parseInt($("#index").val());
	if(totalPages==0){
		$(".page").hide();
	}else{
		setPage($(".page")[0],totalPages,index);
	}
	$(document).on("click",".evidBtOk",function(){
		
		var startTime=$("#start").val();
		var endTime=$("#end").val();
		var searchType=$(".evidGj p").attr("data-value");
		var searchValue=$("#searchValue").val();
		var category=$("#category").val();
	
		
		window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?category="+category+"&startTime="+startTime+"&endTime="+endTime+"&searchValue="+searchValue+"&searchType="+searchType+"&code="+code+"&sourceNppCode="+sourceNppCode;;
		
	});
	$(".evidSearchType span").click(function(){
		var eqindex=$(this).index()-1;
		$($(this).parent().find("span")).each(function(index){
			if(eqindex==index){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		});
	})
	//查询参数回显
	
	var searchType=$("#searchType").val();
	if(searchType=="2"){
		$(".evidGj p").attr("data-value",2).html("存证人");
	}else{
		$(".evidGj p").attr("data-value",1).html("证据名称");
	}
	var category=$("#category").val();

	$(".evidSearchType span").each(function(index){
		if(category=="VIDEO"){
			if(index==1){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		}else if(category=="FAX"){
			if(index==2){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		}else if(category=="APPVIDEO"){
			if(index==3){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		}else if(category=="APPVOICE"){
			if(index==4){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		}else if(category=="APPPICTURE"){
			if(index==5){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		}else{
			if(index==0){
				$(this).attr("class","no3");
			}else{
				$(this).removeClass("no3");
			}
		}
		
	})

});
  
function onSearch(category){
	var startTime=$("#start").val();
	var endTime=$("#end").val();
	var searchType=$(".evidGj p").attr("data-value");
	var searchValue=$("#searchValue").val();	
	
	if(category!='all'){
		window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?category="+category+"&startTime="+startTime+"&endTime="+endTime+"&searchValue="+searchValue+"&searchType="+searchType+"&code="+code+"&sourceNppCode="+sourceNppCode;
	}else{
		window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?startTime="+startTime+"&endTime="+endTime+"&searchValue="+searchValue+"&searchType="+searchType+"&code="+code+"&sourceNppCode="+sourceNppCode;
	}
}
$(document).ready(function(){
	//加载公证申请列表
	getReserveEvidencesInfo4User();

	
	
	//申请公证
	$(".evidNotaryBt").on("click",function(){
		
	    var lileng=$(".evidFiContent").find("li").length;
	    if(lileng!=0){
	        $("body").attr("style","overflow:hidden");
	        $(".evidNotary").show();
	    }else{
	        swal("","请将申请公证的证据添加至证据夹");
	    }
	
	});	
	
	//证据夹
	$(document).on("click",".evidFiBb",function(){
	    var $evidFixedbox=$(".evidFixedbox");
	    var evidLeft=$evidFixedbox.position().left;
	    var evidLeftG=Math.round(evidLeft);
	    var windWid=$(window).width();
	    if(evidLeftG==windWid){
	        $evidFixedbox.animate({right:"0"},500);
	    }else{
	        $evidFixedbox.animate({right:"-230px"},500);
	    }
		});
	// 添加 公证申请
	$(".addBtGz").click(function(){
    	var id=$(".moreOn").find("input").val();
    	var data={"id":id};
    	var url=baseUrl+"/evidenceManag/saveReserveEvidencesInfo4User";
    	var responseJson="";
		$.ajax({
			url : url,
			type : "post",
			data : data,
			async : false,
			traditional: true,  
			success : function(response) {
				responseJson=response;
		
			},
			error : function(msg) {
				alert(msg);
			}
		}); 
    	if(responseJson.respCode!="0000"){
   		 	
   		 	if(responseJson.respDesc=="缺少用户上下文"){
   		 		window.location.reload();
   		 	}else{
   		 		swal("",responseJson.respDesc);
   		 	}
   			return false;
   		}else{
   			
   			getReserveEvidencesInfo4User();
   			swal("","添加成功");
   		}
		
	});
});
function getReserveEvidencesInfo4User(){
	//加载公证申请列表

	var url=baseUrl+"/evidenceManag/getReserveEvidencesInfo4User";
	var responseJson="";
	$.ajax({
		url : url,
		type : "post",
		async : false,
		traditional: true,  
		success : function(response) {
			responseJson=response;
	
		},
		error : function(msg) {
			alert(msg);
		}
	}); 
	if(responseJson.respCode!="0000"){
		 
   		 	if(responseJson.respDesc=="缺少用户上下文"){
   		 		window.location.reload();
   		 	}else{
   		 		swal("",responseJson.respDesc);
   		 	}
		
			return false;
	}
	$(".evidFiBb").find("i").html(responseJson.data.length);
	$(".evidFiContent").html("");
	$("#tbodyList").html("");
	if(responseJson.data.length<=0){
		return false;
	}
	var html="<ul>";
	$.each( responseJson.data, function(i, item){ 
		html+="<li><label><input type='checkbox' name='checkBtList' checked value='"+item.id+"' /><i></i></label><span>"+item.name+"</span><p>"+item["enhancedUser"]["account"]+"</p></li>";
			
	});
	html+="</ul>";

	$(".evidFiContent").html(html);

	var html="";
	$.each( responseJson.data, function(i, item){ 
		var date=(format(item.createTime));
		html+="<tr>";
		html+="<td>"+item.name+"</td>";
		html+="<td>"+item.enhancedUser.account+"</td>";
	
		html+="<td>"+date+"</td>";
		html+="<td>"+item.enhancedDNpp.name+"</td>";
		html+="<td class='delete'>删除<input type='hidden' value='"+item.id+"'/></td>";
		html+="</tr>";
	});
	
	$("#tbodyList").html(html);
    //删除申请公证列表证据
    $(document).on("click",".delete",function(){
        $(this).parent("tr").addClass("dele");
        var id=$(this).find("input").val();
        swal({
            title:"",
            text:"你确定删除该条证据？",
            type:"warning",
            showCancelButton: true
        },function(isConfirm){
            if(isConfirm){
               
            	var  ids =[];
            	ids[0]=id;
                onDelete(ids);
            }else{
            	 
                $(".dele").removeClass("dele");
            }
        });
    });
	
}
function onDelete(id){
	var url=baseUrl+"/evidenceManag/deleteReserveEvidencesInfo4User";
	var data={"id":id};
	var responseJson="";
	$.ajax({
		url : url,
		data:data,
		type : "post",
		async : false,
		traditional: true,  
		success : function(response) {
			responseJson=response;
	
		},
		error : function(msg) {
			alert(msg);
		}
	}); 
	if(responseJson.respCode!="0000"){
   		 	if(responseJson.respDesc=="缺少用户上下文"){
   		 		window.location.reload();
   		 	}else{
   		 		swal("",responseJson.respDesc);
   		 	}
			return false;
	}else{
		 getReserveEvidencesInfo4User();
	}
	
}

//移除
$(".evidFiContentTitle span").on("click",function(){
	 var  ids = [];
    var inPt=$("input[name=checkBtList]:checked");
    $.each(inPt,function(){
    	ids.push($(this).val());
    });
    onDelete(ids);

});

//申请公证默认显示
var NoNext=$(".NoNext");
var NoPrev=$(".NoPrev");
//下一步
$(NoNext).on("click",function(){
    var lileng=$("#tbodyList").find("tr").length;
    if(lileng==0){
    	 swal("","请将申请公证的证据添加至证据夹");
    	 return false;
    }
	
    var NotaryLi=$(".NotaryLi>div:visible");
    var NotaryLiDiv=NotaryLi.index();

    if(NotaryLiDiv==2){
    	var reltdtel=/^(1(([358][0-9])|[4][579]|[7][0135678]))\d{8}$/;
    	var phoneNo=$("#phoneNo").val();
    	var email=$("#email").val();
    	var matterName=$("#matterName").val();
    	var matterDesc=$("#matterDesc").val();
    	if($.trim(phoneNo)==""){
    		
    		swal("提交失败!","手机号不能为空", "error");
    		return false;
    	}
    	if(!reltdtel.test(phoneNo)){
			swal("提交失败!","请正确的输入手机号码", "error");
			return false;
		}
    	if($.trim(email)==""){
    		
    		swal("提交失败!","邮箱不能为空", "error");
    		return false;
    	}
    	var reEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;//邮箱正则表达式
    	if(!email.match(reEmail)){
    		swal("提交失败!","请正确的输入邮箱账号", "error");
    		return false;
    	}
    	if($.trim(matterName)==""){
    		
    		swal("提交失败!","事项名称不能为空", "error");
    		return false;
    	}
    	if($.trim(matterDesc)==""){
    		
    		swal("提交失败!","事项说明不能为空", "error");
    		return false;
    	}
    	
    	

    	
		//提交公证申请
    	var  ids = [];
  
        var inPt=$("input[name=checkBtList]");
        $.each(inPt,function(){
        	ids.push($(this).val());
        });
    	var dataJson={"id":ids,"agentName":$("#agentName").val(),"notarySubject":$("#notarySubject").val(),"phoneNo":$("#phoneNo").val(),"email":$("#email").val(),"matterName":$("#matterName").val(),"matterDesc":$("#matterDesc").val()};
    	var url=baseUrl+"/evidenceManag/saveENotarizationReserves";
		$.ajax({
			url : url,
			data :dataJson ,
			type : "POST",
			async : false,
			traditional: true,  //可以是ajax 传递数组
			success : function(responseText) {
				 if(responseText.respCode!="0000"){
					
		   		 	if(responseText.respDesc=="缺少用户上下文"){
		   		 		window.location.reload();
		   		 	}else{
		   		 		 swal("提交失败!",responseText.respDesc, "error");
		   		 	}					 
					 
					 
					 
				 }else{
					    $(".Notitle").find("li").eq(NotaryLiDiv).next().addClass("ath").siblings().removeClass("ath");
  					 	 NoNext.prev().show();
			        	 NotaryLi.hide().next().show();
		                 NoNext.prev().hide();
		                 NoNext.hide().next().show();
		                 
		          		 getReserveEvidencesInfo4User();
				 }
				
			},
			error : function(msg) {
				alert(msg);
			}
		});
    	
    	
     
        
    }else{
    	$(".Notitle").find("li").eq(NotaryLiDiv).next().addClass("ath").siblings().removeClass("ath");
   	 	NoNext.prev().show();
        NotaryLi.hide().next().show();
    }
});
//上一步
$(NoPrev).on("click",function(){
    var NotaryLi=$(".NotaryLi>div:visible");
    var NotaryLiDiv=NotaryLi.index();
    $(".Notitle").find("li").eq(NotaryLiDiv).prev().addClass("ath").siblings().removeClass("ath");
    NotaryLi.hide().prev().show();
    if(NotaryLiDiv==1){
        NoPrev.hide();
    }else if(NotaryLiDiv==3){
        NoPrev.show().next().show().next().hide();
        
    }
});
//申请公证关闭
$(".notaryClose ,.NoOk").on("click",function(){
    $(".evidNotary").hide();
    $("#Notary01").show().siblings().hide();
    $(".Notitle").find("li:first").addClass("ath").siblings().removeClass("ath");
    $("body").removeAttr("style");
    $(".NoNext").show().siblings().hide();
    
});


function Switch(value) { 
	var theTime = parseInt(value);// 秒 
	var theTime1 = 0;// 分 
	var theTime2 = 0;// 小时 
	// alert(theTime); 
	if(theTime > 60) { 
	theTime1 = parseInt(theTime/60); 
	theTime = parseInt(theTime%60); 
	// alert(theTime1+"-"+theTime); 
	if(theTime1 > 60) { 
	theTime2 = parseInt(theTime1/60); 
	theTime1 = parseInt(theTime1%60); 
	} 
	} 
	var result = ""+parseInt(theTime)+"秒"; 
	if(theTime1 > 0) { 
	result = ""+parseInt(theTime1)+"分"+result; 
	} 
	if(theTime2 > 0) { 
	result = ""+parseInt(theTime2)+"小时"+result; 
	} 
	return result; 
} 


