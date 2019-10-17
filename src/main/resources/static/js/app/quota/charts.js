var theme_color = $MB.getThemeColor(theme);

$(document).ready(function() {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    //初始化
    query();
});

function showChart(obj,para){
	var url = ctx + para.url;
	
	$.getJSON(url, function(data) {
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

function query() {
	
	var $chartsTableForm = $(".charts-table-form");
	var corpName = $chartsTableForm.find("input[name='corpName']").val();
	var quotaType = $chartsTableForm.find("select[name='quotaType']").val();
	var url = "quota/getSingleChartData?sort=asc&corpName="+corpName
			+"&quotaType="+quotaType;
	
	//营业收入
    var bussIn = {corpName:corpName, quotaType:quotaType}
    bussIn.chartName = "营业收入走势";
    bussIn.yTitleName = "营业收入(亿元)";
    bussIn.seriesName = "营业收入";
    bussIn.tooltip = "(亿元)";
    bussIn.url = url+"&quotaName=0001";
    showChart($("#container_buss_in"),bussIn);
    
    //营业收入增长率
    var bussInRate = {corpName:corpName, quotaType:quotaType}
    bussInRate.chartName = "营业收入增长率走势";
    bussInRate.yTitleName = "营业收入增长率(%)";
    bussInRate.seriesName = "营业收入增长率";
    bussInRate.tooltip = "(%)";
    bussInRate.url = url+"&quotaName=0019";
    showChart($("#container_buss_in_rate"),bussInRate);
    
    //毛利率
    var marginRate = {corpName:corpName, quotaType:quotaType}
    marginRate.chartName = "毛利率走势";
    marginRate.yTitleName = "毛利率(%)";
    marginRate.seriesName = "毛利率";
    marginRate.tooltip = "(%)";
    marginRate.url = url+"&quotaName=0002";
    showChart($("#container_margin_rate"),marginRate);
    
    //净利润率
    var netProfitsRate = {corpName:corpName, quotaType:quotaType}
    netProfitsRate.chartName = "净利润走势";
    netProfitsRate.yTitleName = "净利润率(%)";
    netProfitsRate.seriesName = "净利润率";
    netProfitsRate.tooltip = "(%)";
    netProfitsRate.url = url+"&quotaName=0003";
    showChart($("#container_net_profits_rate"),netProfitsRate);
    
    //经营性净利润率
    var bussNetProfitsRate = {corpName:corpName, quotaType:quotaType}
    bussNetProfitsRate.chartName = "经营性净利润走势";
    bussNetProfitsRate.yTitleName = "经营性净利润率(%)";
    bussNetProfitsRate.seriesName = "经营性净利润率";
    bussNetProfitsRate.tooltip = "(%)";
    bussNetProfitsRate.url = url+"&quotaName=0004";
    showChart($("#container_buss_net_profits_rate"),bussNetProfitsRate);
    
    //经营性净利润增长率
    var bussNetProfitsIncRate = {corpName:corpName, quotaType:quotaType}
    bussNetProfitsIncRate.chartName = "经营性净利润增长走势";
    bussNetProfitsIncRate.yTitleName = "经营性净利润增长率(%)";
    bussNetProfitsIncRate.seriesName = "经营性净利润增长率";
    bussNetProfitsIncRate.tooltip = "(%)";
    bussNetProfitsIncRate.url = url+"&quotaName=0005";
    showChart($("#container_buss_net_profits_inc_rate"),bussNetProfitsIncRate);
}




