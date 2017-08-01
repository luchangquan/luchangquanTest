/**
 * Created by RK007 on 2017/4/12.
 */
$(document).ready(function(){
    var liApplyLi=$(".applyInfo li");
    var applyInfoWidth=$(liApplyLi).width();
    $.each(liApplyLi,function(){
        $(this).find("p").attr("style","width:"+applyInfoWidth+"px");
    })
});