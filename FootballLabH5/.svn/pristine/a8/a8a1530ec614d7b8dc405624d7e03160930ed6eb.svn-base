$(function () {
	$('#container_instrument1').highcharts({
	
	    chart: {
	        type: 'gauge',
	        alignTicks: false,
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false,
	        margin:[1, 1, 1, 1],//整个图表的位置(上下左右的空隙)  
	    },
	
	    title: {
	        text: ''
	    },
	    
	    pane: {
	        startAngle: -150,
	        endAngle: 150
	    },	        
	
	    yAxis: [{
	        min: 0,
	        max: 100,
	        tickPosition: 'outside',
	        lineColor: '#046752',
	        lineWidth: 2,
	        minorTickPosition: 'outside',
	        tickColor: '#046752',
	        minorTickColor: '#046752',
	        tickLength: 5,
	        minorTickLength: 5,
	        labels: {
	            distance: 12,
	            rotation: 'auto'
	        },
	        offset: -18,
	        endOnTick: false
	    }],
	    
	    exporting: [{
            enabled:false
        }],
	    credits:[{
	    	enabled:false,
	    }],
	    series: [{
	    
	        name: '百分比',
	        data: [80],
	        dataLabels: {
	        	
	            formatter: function () {
	                var kmh = this.y,
	                    mph = Math.round(kmh * 0.621);
               return '<span style="color:#05997a;">'+ kmh + ' %</span>'    //	 +
//	                    '<span style="color:#933">' + mph + ' mph</span>';
	            },
	            
	            backgroundColor: {
	                linearGradient: {
	                    x1: 0,
	                    y1: 0,
	                    x2: 0,
	                    y2: 1
	                },
	                stops: [
	                    [0, '#DDD'],
	                    [1, '#FFF']
	                ]
	            }
	        },
	        tooltip: {
	        	
	            valueSuffix: ' %',
	            
	            
	        }
	    }]
	
	},
	// Add some life
	function(chart) {
	    setInterval(function() {
	        var point = chart.series[0].points[0],
	            newVal, inc = Math.round((Math.random() - 0.5) * 20);
	
	        newVal = point.y + inc;
	        if (newVal < 0 || newVal > 100) {
	            newVal = point.y - inc;
	        }
	
	        point.update(newVal);
	
	    }, 3000);
	
	});
	
	
	
	$('#container_instrument2').highcharts({
	
	    chart: {
	        type: 'gauge',
	        alignTicks: false,
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false,
	        margin:[2, 2, 2, 2],//整个图表的位置(上下左右的空隙)  
	    },
	
	    title: {
	        text: ''
	    },
	    
	    pane: {
	        startAngle: -150,
	        endAngle: 150
	    },	        
	
	    yAxis: [{
	        min: 0,
	        max: 100,
	        tickPosition: 'outside',
	        lineColor: '#046752',
	        lineWidth: 2,
	        minorTickPosition: 'outside',
	        tickColor: '#046752',
	        minorTickColor: '#046752',
	        tickLength: 5,
	        minorTickLength: 5,
	        labels: {
	            distance: 12,
	            rotation: 'auto'
	        },
	        offset: -18,
	        endOnTick: false
	    }],
	    
	    exporting: [{
            enabled:false
        }],
	    credits:[{
	    	enabled:false,
	    }],
	    series: [{
	    
	        name: '百分比',
	        data: [80],
	        dataLabels: {
	        	
	            formatter: function () {
	                var kmh = this.y,
	                    mph = Math.round(kmh * 0.621);
               return '<span style="color:#05997a;">'+ kmh + ' %</span>'    //	 +
//	                    '<span style="color:#933">' + mph + ' mph</span>';
	            },
	            backgroundColor: {
	                linearGradient: {
	                    x1: 0,
	                    y1: 0,
	                    x2: 0,
	                    y2: 1
	                },
	                stops: [
	                    [0, '#DDD'],
	                    [1, '#FFF']
	                ]
	            }
	        },
	        tooltip: {
	            valueSuffix: ' %'
	        }
	    }]
	
	},
	// Add some life
	function(chart) {
	    setInterval(function() {
	        var point = chart.series[0].points[0],
	            newVal, inc = Math.round((Math.random() - 0.5) * 20);
	
	        newVal = point.y + inc;
	        if (newVal < 0 || newVal > 100) {
	            newVal = point.y - inc;
	        }
	
	        point.update(newVal);
	
	    }, 3000);
	
	});
	
	
	
	$('#container_instrument3').highcharts({
	
	    chart: {
	        type: 'gauge',
	        alignTicks: false,
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false,
	        margin:[2, 2, 2, 2],//整个图表的位置(上下左右的空隙)  
	    },
	
	    title: {
	        text: ''
	    },
	    
	    pane: {
	        startAngle: -150,
	        endAngle: 150
	    },	        
	
	    yAxis: [{
	        min: 0,
	        max: 100,
	        tickPosition: 'outside',
	        lineColor: '#046752',
	        lineWidth: 2,
	        minorTickPosition: 'outside',
	        tickColor: '#046752',
	        minorTickColor: '#046752',
	        tickLength: 5,
	        minorTickLength: 5,
	        labels: {
	            distance: 12,
	            rotation: 'auto'
	        },
	        offset: -18,
	        endOnTick: false
	    }],
	    
	    exporting: [{
            enabled:false
        }],
	    credits:[{
	    	enabled:false,
	    }],
	    series: [{
	    
	        name: '百分比',
	        data: [80],
	        dataLabels: {
	        	
	            formatter: function () {
	                var kmh = this.y,
	                    mph = Math.round(kmh * 0.621);
               return '<span style="color:#05997a;">'+ kmh + ' %</span>'    //	 +
//	                    '<span style="color:#933">' + mph + ' mph</span>';
	            },
	            backgroundColor: {
	                linearGradient: {
	                    x1: 0,
	                    y1: 0,
	                    x2: 0,
	                    y2: 1
	                },
	                stops: [
	                    [0, '#DDD'],
	                    [1, '#FFF']
	                ]
	            }
	        },
	        tooltip: {
	            valueSuffix: ' %'
	        }
	    }]
	
	},
	// Add some life
	function(chart) {
	    setInterval(function() {
	        var point = chart.series[0].points[0],
	            newVal, inc = Math.round((Math.random() - 0.5) * 20);
	
	        newVal = point.y + inc;
	        if (newVal < 0 || newVal > 100) {
	            newVal = point.y - inc;
	        }
	
	        point.update(newVal);
	
	    }, 3000);
	
	});
});				