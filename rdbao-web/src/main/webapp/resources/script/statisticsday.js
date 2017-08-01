/**
 * Created by RK007 on 2016/12/19.
 */
var dataArray=new Array();
$(document).ready(function(){
	 //加载图标数据
	
	 var url=baseUrl+"/dataStatistics/getTotalVoiceCycleDateQuantityStatisticsVo";
	 var result= ajaxCustom(url,(null),"POST",false);
	 console.log(result);
	 dataArray=result.listDate;
	 var voiceCycleDateQuantityStatisticsVoIng=result.voiceCycleDateQuantityStatisticsVoIng.total;
	 var voiceCycleDateQuantityStatisticsVoEd=result.voiceCycleDateQuantityStatisticsVoEd.total;
     var statistiDayTdL=$("#statistiDay thead").find("td").length;
    if(statistiDayTdL>6){
        $('#statistiDay').fixedHeaderTable({
            altClass: 'odd',
            footer: false,
            fixedColumns: 1
        });
    }
  //周期内日统计
    var statist=echarts.init(document.getElementById("statist-box"));
    var statistDay= {
    		
        title:{
            text:'最近15天呼入/呼出电话数量',
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
                data :dataArray,//日期
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
                data:voiceCycleDateQuantityStatisticsVoEd,
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
                data:voiceCycleDateQuantityStatisticsVoIng,
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
    statist.setOption(statistDay);
});


// 定义一个有返回值的ajax函数供调用
function ajaxCustom(url, data, method, async) {
	var result = null;
	$.ajax({
		url : url,
		data : data,
		type : method,
		async : async,
		success : function(responseText) {
				result =responseText;
		},
		error : function(msg) {
			alert(msg);
		}
	});
	return result;
}


