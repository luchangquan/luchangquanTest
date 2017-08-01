var sourceNppCode="";
$(document).ready(function(){
    $(".headerBox").load(baseUrl+"/resources/jsp/manage.jsp #header",function(){
    	 var account=$("#account").val();
    	 
    	 $("#user").text(account);
    	 sourceNppCode =$("#sourceNppCode").val();
    	 $(".signOut").on("click",function(){
			var url=baseUrl+"/logout/logout";
			window.location=url;
		}); 
    	$(".notary-logo").css("background-image","url("+baseUrl+"/resources/images/logo/"+sourceNppCode+".png?v=1)");
    	var nppUrl = '#';
    	var footerContent = "";
    	if(sourceNppCode == 'ZJLA'){
    		nppUrl = "http://www.zjlagzc.com";
    		footerContent = "<p>临安市公证处版权所有 Copyright © 2012-2017 Linan Notary Public Office All rights reserved, 浙ICP备17011889号</p>";
    	}else if(sourceNppCode == 'HZDF'){
    		
    		nppUrl = "http://www.chinanotary.cn";
    		footerContent = "<p>CopyRight 2007-2008 浙江省杭州市东方公证处 （原浙江省公证处） All Right Reserved 地址：杭州市体育场路538号金祝大厦, 浙ICP备08005717号</p>";
    	}else if(sourceNppCode == 'SHMH'){
    		nppUrl = "http://www.mhnotary.com";
    	}else if(sourceNppCode == 'JSSZ'){
    		nppUrl = "http://www.szsgzc.com";
    	}
    	$(".notary-logo").attr("href",nppUrl);
    	$(".footer").html(footerContent);
    
    });
 		  
    
    //导航栏高亮
    $(".navBox").load(baseUrl+"/resources/jsp/manage.jsp #navLi",function(){
     var navID=$("input[id=navOn]").val();
     var $navli=$(".nav-box");
     $navli.find("li:eq("+navID+")").addClass("navOn").siblings().removeClass("navOn");
        //用户中心
        $(".imgO").on("click",function(){
            window.location=baseUrl+"/userCenter/toBasicInfo?sourceNppCode="+sourceNppCode;
        });
        //我的实时保
        $(".imgI").on("click",function(){
           window.location=baseUrl+"/userCenter/userInfo?sourceNppCode="+sourceNppCode;
        });
        
        $(".imgT").on("click",function(){
            window.location=baseUrl+"/evidenceManag/toEvidenceManagIndex?sourceNppCode="+sourceNppCode;
        });
        //公证申请
        $(".imgTH").on("click",function(){
            window.location=baseUrl+"/evidenceManag/toMakeList?sourceNppCode="+sourceNppCode;
        });
        //应用中心
        $(".imgA").on("click",function(){
            window.location=baseUrl+"/applicationCenter/toApply?sourceNppCode="+sourceNppCode;
        })
     });
    $(".footerM").load(baseUrl+"/resources/jsp/manage.jsp #footer",function(){
	    sourceNppCode =$("#sourceNppCode").val();
	   	var footerContent = "";
	   	if(sourceNppCode == 'ZJLA'){
	   		footerContent = "<p>临安市公证处版权所有 Copyright © 2012-2017 Linan Notary Public Office All rights reserved, 浙ICP备17011889号&nbsp</p>";
	   	}else if(sourceNppCode == 'HZDF'){
	   		footerContent = "<p>CopyRight 2007-2008 浙江省杭州市东方公证处 （原浙江省公证处） All Right Reserved 地址：杭州市体育场路538号金祝大厦, 浙ICP备08005717号&nbsp</p>";
	   	}else if(sourceNppCode == 'SHMH'){
	   	}else if(sourceNppCode == 'JSSZ'){
	   	}
        var cnzz='<span id="cnzz_stat_icon_1259761345"><a href="https://www.cnzz.com/stat/website.php?web_id=1259761345" target="_blank" title="站长统计"><img border="0" hspace="0" vspace="0" src="'+baseUrl+'/resources/images/pic1.gif"></a></span><script src="https://s11.cnzz.com/z_stat.php?id=1259761345&web_id=1259761345" language="JavaScript"></script>';
        $(".footer").append(footerContent);
        $(".footer p").append(cnzz);
//        $(".footerM").append(footerContent);
//        $(".footerM p").append(cnzz);
    })

});
