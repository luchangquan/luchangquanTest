/*
 * Created by kingM on 2017/3/27.
 */
var isF=true;
$(document).ready(function(){
    //组/成员
    $(document).on("click",".groupClose",function(){
        $(this).parent().animate({right:"-255px"},500);
    });
    $(document).on("click",".busiType",function(){
        $(".groupBox").animate({right:"0"},500)
    });
    $(document).on("click",".groupList>h2>i,.allGroup>i",function(){
        $(this).parent().next().slideToggle();
    });



		
 //新建子部门
 	$(".creatnew").on("click",function(){
		$("#NotaryBox2").show();
	})
	
 //修改部门名
 	$(".modify").on("click",function(){
		$("#NotaryBox3").show();
	})
	
 //创建管理员
 	$(".administrator").on("click",function(){
		$("#NotaryBox4").show();
	})
			
 //移动分组关闭
 	$(document).on("click",".Ok",function(){
			$("#NotaryBox").hide();
			$("#NotaryBox2").hide();
			$("#NotaryBox3").hide();
			$("#NotaryBox4").hide();
	});
	$(document).on("click",".NotaryClose",function(){
			$("#NotaryBox").hide();
			$("#NotaryBox2").hide();
			$("#NotaryBox3").hide();
			$("#NotaryBox4").hide();
	});
	
	 //移动分组
	$(".call-move-bt").on("click",function(){
		var aorganizationId = $("h3").attr("id");
		var callBaL=$("input[name=call-ba]:checked").length;//已选择的证据
		if(callBaL==0){
			swal("","请勾选需要移动的成员","warning")
		}else{
			$("#NotaryBox").show();
			var url = baseUrl+"/userCenter/moveOrganization";
			var data = {};
			asynchronoMoveOrganization(url,data);
		}
	});	

		
//删除部门
        $(document).on("click",".delete",function(){
            
            swal({
                title:"",
                text:"你确定删除该部门？",
                type:"warning",
                showCancelButton: true
            },function(isConfirm){
                if(isConfirm){
                    $(".dele").remove();
                    var aorganizationId = $("h3").attr("id");
       				console.log(aorganizationId);
       				var url = baseUrl+"/userCenter/delteOrganization";
       				var data = {
       					"aorganizationId" : aorganizationId
       				};
       				asynchronoupdateutils(url, data);
       		
    /*				var companyName = $("#toUserManage").text();
    				$(".h2").text(companyName);
    				var url = baseUrl+"/userCenter/getOrganizations";
    				var data = {};
    				asynchronousupdate(url, data);
    				url =  baseUrl+"/userCenter/getAllEnhancedUsers";
    				asynchronougetAllEnhancedUsers(url, data);*/
    				$(".delete,.modify,.administrator").hide();
                }else{
                    $(".dele").removeClass("dele");
                }
            })
        });		
		
    //全选
    $("input[name=AllCheckBt]").on("click",function(){
        var inPt=$("input[name=checkBtList]");
        if($(this).is(":checked")==true){
            $.each(inPt,function(){
                if($(this).is(":checked")==false){
                    $(this).click();
                }
            })
        }else{
            $.each(inPt,function(){
                if($(this).is(":checked")==true){
                    $(this).click();
                }
            })
        }
    });




});
