var theme_color = $MB.getThemeColor(theme);

$(document).ready(function() {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    
    $("input[name='dateStart']").daterangepicker(
   		 {
   	            singleDatePicker: true,//设置为单个的datepicker，而不是有区间的datepicker 默认false
   	            showDropdowns: true,//当设置值为true的时候，允许年份和月份通过下拉框的形式选择 默认false
   	            autoUpdateInput: false,//1.当设置为false的时候,不给与默认值(当前时间)2.选择时间时,失去鼠标焦点,不会给与默认值 默认true
   	            timePicker24Hour : true,//设置小时为24小时制 默认false
   	            timePicker : false,//可选中时分 默认false
   	    		locale: {
   	    			format: "YYYY-MM-DD",
   	    			separator: " - ",
   	    			daysOfWeek: ["日","一","二","三","四","五","六"],
   	    			monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
   	    		}
   	    			
   	     }
    ).on('cancel.daterangepicker', function(ev, picker) {
        $("#dateStart").val("请选择日期");
    }).on('apply.daterangepicker', function(ev, picker) {
    	$("#dateStart").val(picker.startDate.format('YYYY-MM-DD'));
    });
    //设置初始日期
    $("#dateStart").val(get3MonthBefor());
    
    $("input[name='dateEnd']").daterangepicker(
      		 {
      	            singleDatePicker: true,//设置为单个的datepicker，而不是有区间的datepicker 默认false
      	            showDropdowns: true,//当设置值为true的时候，允许年份和月份通过下拉框的形式选择 默认false
      	            autoUpdateInput: false,//1.当设置为false的时候,不给与默认值(当前时间)2.选择时间时,失去鼠标焦点,不会给与默认值 默认true
      	            timePicker24Hour : true,//设置小时为24小时制 默认false
      	            timePicker : false,//可选中时分 默认false
      	    		locale: {
      	    			format: "YYYY-MM-DD",
      	    			separator: " - ",
      	    			daysOfWeek: ["日","一","二","三","四","五","六"],
      	    			monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
      	    		}
      	    			
      	     }
       ).on('cancel.daterangepicker', function(ev, picker) {
           $("#dateEnd").val("请选择日期");
       }).on('apply.daterangepicker', function(ev, picker) {
       	$("#dateEnd").val(picker.startDate.format('YYYY-MM-DD'));
       });
    
    //设置初始结束日期
    $("#dateEnd").val(getToDay());
    
    //展示图表 融资余额
    var startTime = $("#dateStart").val();
	var endTime = $("#dateEnd").val();
    var financePara = {type:"1", startTime:startTime, endTime:endTime};
    
    financePara.chartName = "融资余额走势";
    financePara.yTitleName = "融资余额(亿元)";
    financePara.seriesName = "融资余额";
    financePara.tooltip = "(亿元)";
    showChart($("#container"),financePara);
    
    //展示图表 居民消费价格指数
    var cpiPara = {type:"2", startTime:"", endTime:""}
    cpiPara.chartName = "CPI走势";
    cpiPara.yTitleName = "同比增长(%)";
    cpiPara.seriesName = "居民消费价格指数";
    cpiPara.tooltip = "(%)";
    showChart($("#container_cpi"),cpiPara);
    
    //展示图表 M2
    var m2Para = {type:"3", startTime:"", endTime:""}
    m2Para.chartName = "M2走势";
    m2Para.yTitleName = "同比增长(%)";
    m2Para.seriesName = "货币供应量";
    m2Para.tooltip = "(%)";
    showChart($("#container_m2"),m2Para);
    
    //展示图表 社融
    var socialHarmonyPara = {type:"4", startTime:"", endTime:""}
    socialHarmonyPara.chartName = "社融走势";
    socialHarmonyPara.yTitleName = "社融金额(亿元)";
    socialHarmonyPara.seriesName = "社融规模";
    socialHarmonyPara.tooltip = "(亿元)";
    showChart($("#container_social_harmony"),socialHarmonyPara);
    
    //展示图表 GDP季度
    var gdpQuarterPara = {type:"5", startTime:"", endTime:""}
    gdpQuarterPara.chartName = "GDP季度走势";
    gdpQuarterPara.yTitleName = "GDP季度增速(%)";
    gdpQuarterPara.seriesName = "GDP季度增速";
    gdpQuarterPara.tooltip = "(%)";
    showChart($("#container_gdp_quarter"),gdpQuarterPara);
    
    //展示图表 乘用车同比增长率
    var carSamePara = {type:"6", startTime:"", endTime:""}
    carSamePara.chartName = "乘用车同比走势";
    carSamePara.yTitleName = "乘用车同比增速(%)";
    carSamePara.seriesName = "乘用车增速";
    carSamePara.tooltip = "(%)";
    showChart($("#container_car_same"),carSamePara);
    
    //展示图表 乘用车环比增长率
    var carLastPara = {type:"7", startTime:"", endTime:""}
    carLastPara.chartName = "乘用车环比走势";
    carLastPara.yTitleName = "乘用车环比增速(%)";
    carLastPara.seriesName = "乘用车增速";
    carLastPara.tooltip = "(%)";
    showChart($("#container_car_last"),carLastPara);
    
    //展示图表 沪深交易量
    var hushenPara = {type:"8", startTime:startTime, endTime:endTime}
    hushenPara.chartName = "沪深交易量走势";
    hushenPara.yTitleName = "沪深交易量(亿元)";
    hushenPara.seriesName = "沪深交易量";
    hushenPara.tooltip = "(亿元)";
    showChart($("#container_hushen"),hushenPara);
    
    //展示图表 沪股通资金流向
    var huHkPara = {type:"9", startTime:startTime, endTime:endTime}
    huHkPara.chartName = "沪股通资金走势";
    huHkPara.yTitleName = "沪股通资金流入(亿元)";
    huHkPara.seriesName = "沪股通资金流入";
    huHkPara.tooltip = "(亿元)";
    showChart($("#container_hu_hk"),huHkPara);
    
    //展示图表 深股通资金流向
    var shenHkPara = {type:"10", startTime:startTime, endTime:endTime}
    shenHkPara.chartName = "深股通资金走势";
    shenHkPara.yTitleName = "深股通资金流入(亿元)";
    shenHkPara.seriesName = "深股通资金流入";
    shenHkPara.tooltip = "(亿元)";
    showChart($("#container_shen_hk"),shenHkPara);
    
    //展示图表 北向资金流向
    var northPara = {type:"11", startTime:startTime, endTime:endTime}
    northPara.chartName = "北向资金走势";
    northPara.yTitleName = "北向资金流入(亿元)";
    northPara.seriesName = "北向资金流入";
    northPara.tooltip = "(亿元)";
    showChart($("#container_north"),northPara);
});

function query() {
	var startTime = $("#dateStart").val();
	var endTime = $("#dateEnd").val();
    var financePara = {type:"1", startTime:startTime, endTime:endTime};
    
    financePara.chartName = "融资余额走势";
    financePara.yTitleName = "金额(亿元)";
    financePara.seriesName = "融资余额";
    financePara.tooltip = "(亿元)";
    showChart($("#container"),financePara);
    
    var hushenPara = {type:"8", startTime:startTime, endTime:endTime}
    hushenPara.chartName = "沪深交易量走势";
    hushenPara.yTitleName = "沪深交易量(亿元)";
    hushenPara.seriesName = "沪深交易量";
    hushenPara.tooltip = "(亿元)";
    showChart($("#container_hushen"),hushenPara);
    
    var huHkPara = {type:"9", startTime:startTime, endTime:endTime}
    huHkPara.chartName = "沪股通资金走势";
    huHkPara.yTitleName = "沪股通资金流入(亿元)";
    huHkPara.seriesName = "沪股通资金流入";
    huHkPara.tooltip = "(亿元)";
    showChart($("#container_hu_hk"),huHkPara);
    
    var shenHkPara = {type:"10", startTime:startTime, endTime:endTime}
    shenHkPara.chartName = "深股通资金走势";
    shenHkPara.yTitleName = "深股通资金流入(亿元)";
    shenHkPara.seriesName = "深股通资金流入";
    shenHkPara.tooltip = "(亿元)";
    showChart($("#container_shen_hk"),shenHkPara);
    
    var northPara = {type:"11", startTime:startTime, endTime:endTime}
    northPara.chartName = "北向资金走势";
    northPara.yTitleName = "北向资金流入(亿元)";
    northPara.seriesName = "北向资金流入";
    northPara.tooltip = "(亿元)";
    showChart($("#container_north"),northPara);
}

function showChart(obj,para){
	
	$.getJSON(ctx + "finance/financeInfo?sort=asc&type="+para.type+"&startTime="+para.startTime+"&endTime="+para.endTime, function(data) {
        var categories = data.categories;
        var seriesData = data.seriesData;
        
        obj.highcharts({
            chart: {
                type: "spline",
                animation: Highcharts.svg,
                marginRight: 0,
                events: {
                    load: function() {
                    }
                }
            },
            title: {
                text: para.chartName,
                style: {
                    "font-size": "1.2rem"
                }
            },
            xAxis: {
                type: "linear",
                categories: categories
            },
            yAxis: {
                title: {
                    text: para.yTitleName
                },
                plotLines: [ {
                    value: 0,
                    width: 1,
                    color: "#808080"
                } ]
            },
            plotOptions: {
                spline: {
                    color: theme_color
                }
            },
            credits: {
                enabled: false
            },
            tooltip: {
                formatter: function() {
                    return "<b>" + this.series.name + 
                    "</b><br/>" + this.x +
                    "</b><br/>" + Highcharts.numberFormat(this.y, 2) + para.tooltip;
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [ {
                name: para.seriesName,
                data: seriesData
            } ]
        });
        
    });
}

function get3MonthBefor(){
    var resultDate,year,month,date;
    var currDate = new Date();
    year = currDate.getFullYear();
    month = currDate.getMonth()+1;
    date = currDate.getDate();
    
    switch(month)
    {
      case 1:
      case 2:
      case 3:
        month += 9;
        year--;
        break;
      default:
        month -= 3;
        break;
    }
    month = (month < 10) ? ('0' + month) : month;
    resultDate = year + '-'+month+'-'+date;
  return resultDate;
}

function getToDay(){
    var resultDate,year,month,date;
    var currDate = new Date();
    
    year = currDate.getFullYear();
    month = currDate.getMonth()+1;
    date = currDate.getDate();
    month = (month < 10) ? ('0' + month) : month;
    resultDate = year + '-'+month+'-'+date;
    
  return resultDate;
}

