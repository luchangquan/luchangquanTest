/**
 * Created by RK007 on 2017/1/4.
 */
$(document).ready(function(){
    //导航栏高亮
    var navID=$("input[id=navID]").val();
    var $navli=$(".navTop");
    $navli.find("li:eq("+navID+")").addClass("current").siblings().removeClass("current");
    
    //左边菜单选中
    var navLF=$("input[id=navLF]").val();
    var $navLF=$(".navLF");
    $navLF.find("li:eq("+navLF+")").addClass("currentlt").siblings().removeClass("currentlt");
    
    
});