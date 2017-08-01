/**
 * Created by RK007 on 2016/12/16.
 */
var allCallNumber = echarts.init(document.getElementById("all-call-number"));
var allCallTime = echarts.init(document.getElementById("all-call-time"));
var countCallingTime = $("#countCallingTime").val();
var countCalledTime = $("#countCalledTime").val();

var countCalling = $("#countCalling").val();
var countCalled = $("#countCalled").val();
var callNunber = {
	title : {
		text : '呼入/呼出电话数量',
		subtext : '所有呼入呼出电话数量统计(个)',
		x : 'center'
	},
	tooltip : {
		trigger : 'item',
		formatter : '{a} <br/>{b} : {c} ({d}%)'
	},
	color : [ '#003b95', '#36a5ec', '#61a0a8', '#aad26f', '#ffd76e', '#749f83',
			'#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3' ],
	legend : {
		orient : 'vertical',
		left : 'left',
		data : [ '呼出', '呼入' ]
	},
	series : [ {
		name : '呼叫类型(个)',
		type : 'pie',
		radius : '70%',
		center : [ '50%', '62%' ],
		data : [ {
			name : '呼出',
			value : countCalling
		}, {
			name : '呼入',
			value : countCalled
		} ],
		itemStyle : {
			emphasis : {
				shadowBlur : 10,
				shadowOffsetX : 0,
				shadowColor : 'rgba(0, 0, 0, 0.5)'
			}
		},
		label : {
			normal : {
				show : true,
				position : 'inside',
				formatter : '{b}：{c}(个)'
			}
		}
	} ]
};
var calltime = {
	title : {
		text : '呼入/呼出时长',
		subtext : '所有呼入呼出电话时长统计(秒)',
		x : 'center'
	},
	tooltip : {
		trigger : 'item',
		formatter : '{a} <br/>{b} : {c} ({d}%)'
	},
	color : [ '#61a0a8', '#aad26f', '#003b95', '#36a5ec', '#ffd76e', '#749f83',
			'#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3' ],
	legend : {
		orient : 'vertical',
		left : 'left',
		data : [ '呼出', '呼入' ]
	},
	series : [ {
		name : '呼叫时长（秒）',
		type : 'pie',
		radius : '70%',
		center : [ '50%', '62%' ],
		data : [ {
			name : '呼出',
			value : countCallingTime
		}, {
			name : '呼入',
			value : countCalledTime
		} ],
		itemStyle : {
			emphasis : {
				shadowBlur : 10,
				shadowOffsetX : 0,
				shadowColor : 'rgba(0, 0, 0, 0.5)'
			}
		},
		label : {
			normal : {
				show : true,
				position : 'inside',
				formatter : '{b}：{c}(秒)'
			}
		}

	} ]

};
allCallNumber.setOption(callNunber);
allCallTime.setOption(calltime);
$(document).ready(function() {
});
