/**
 * Created by RK007 on 2016/12/19.
 */
$(document).ready(function(){

	 var url=baseUrl+"/dataStatistics/getTotalVoiceSpecifiedDateQuantityStatistics";
	 ajaxCustom(url,(null),"POST",true);
	// console.log(results);
	
/*    $('#statistiDay').fixedHeaderTable({
        altClass: 'odd',
        footer: false,
        fixedColumns: 1
    });*/
   
    //时间段选择
    var hour_start = $('#hour_start');
    var hour_end = $('#hour_end');
    $(hour_start).on('change',function(){
        var  hour_end_val= hour_end.val();
        if(''!=hour_start.val()){
            hour_end.empty();
            hour_end.append($('<option value="">请选择</option>'));
            for(var i=parseInt(hour_start.val())+1;i<25;i++ ){
                hour_end.append($('<option value='+i+'>'+i+'点</option>'));
            }
        }
        hour_end.val(hour_end_val);
    });
    $(hour_end).on('change',function(){
        var  hour_start_val = hour_start.val();
        if(''!=hour_end.val()){
            hour_start.empty();
            hour_start.append($('<option value="">请选择</option>'));
            for(var i=0;i < parseInt(hour_end.val());i++ ){
                hour_start.append($('<option value='+i+'>'+i+'点</option>'));
            }
        }
        hour_start.val(hour_start_val);
    });
});





//定义一个有返回值的ajax函数供调用
function ajaxCustom(url, data, method, async) {
	var results = null;
	$.ajax({
		url : url,
		data : data,
		type : method,
		async : async,
		success : function(responseText) {
				results =responseText;
				//指定日期统计
				 var statistTm=echarts.init(document.getElementById("statist-time"));
				 var statistTime= {
				     title:{
				         text:'昨日呼入/呼出电话数量',
				         x:"center"
				     },
				     color:['#003b95','#36a5ec'],
				     tooltip : {
				         trigger: 'axis'
				     },
				     legend: {
				         orient: 'vertical',
				         left: '30',
				         data: ['呼入数量（个）','呼出数量（个）']
				     },

				     grid: {
				         left: '3%',
				         right: '4%',
				         bottom: '3%',
				         containLabel: true
				     },
				     xAxis : [
				         {
				             type : 'category',
				             data : ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'],//时间
				             axisTick: {
				                 alignWithLabel: true
				             }
				         }
				     ],
				     yAxis : [
				         {
				             type : 'value'
				         }
				     ],
				     series : [
				         {
				             name:'呼入数量（个）',
				             type:'line',
				             data:results.dataCallEd.total,
				             label:{
				                 normal: {
				                     show: true,
				                     position: 'top',
				                     formatter:'{c}'
				                 }
				             }
				         },
				         {
				             name:'呼出数量（个）',
				             type:'line',
				             data:results.dataCallIng.total,
				             label:{
				                 normal: {
				                     show: true,
				                     position: 'top',
				                     formatter:'{c}'
				                 }
				             }
				         }
				     ]
				 };
				 statistTm.setOption(statistTime);
		},
		error : function(msg) {
//			alert(msg);
		}
	});
	return results;
}

