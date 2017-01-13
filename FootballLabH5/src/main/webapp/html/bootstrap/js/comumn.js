$(function () {
    $('#container_col').highcharts({
        chart: {
            type: 'column',
             
             style:  
                        {  
                            fontFamily: 'Microsoft YaHei',  
                            fontSize: '12px',  
                            color: '#262626'  
                        }  
            
        },
        exporting: [{
            enabled:false
        }],
         colors: [

			'#046752',
			'#ff8105',
			
		
		],
	    credits:[{
	    	enabled:false,
	    }],
	    labels: {
			enabled: false
		},
		legend: {//图例方框所在的位置(不知道怎么表达)  
            layout: 'horizontal',  
            align: 'right', //水平方向位置

            verticalAlign: 'top',  
            x: -10,  
            y: -10,   
            borderWidth: 0  
        },  
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位 (个)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true,
            style: {  //提示框内容的样式
	            color: '#555',
	            padding: '10px',    //内边距 (这个会常用到)
	            fontSize: '9pt',            
	        }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
		labelFormatter: function() {
   /*
    *  格式化函数可用的变量：this， 可以用 console.log(this) 来查看包含的详细信息
    *  this 代表当前数据列对象，所以默认的实现是 return this.name 
    */
    return  this.name + '(Click to hide or show)';  

	}  ,


        series: [{
            name: '问题',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }, {
            name: '已修改',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

        }]
    });
    
    
    
    
    
    $('#container_col1').highcharts({
        chart: {
            type: 'column',
             
             style:  
                        {  
                            fontFamily: 'Microsoft YaHei',  
                            fontSize: '12px',  
                            color: '#262626'  
                        }  
            
        },
        exporting: [{
            enabled:false
        }],
         colors: [

			'#046752',
			'#ff8105',
			
		
		],
	    credits:[{
	    	enabled:false,
	    }],
	    labels: {
			enabled: false
		},
		legend: {//方框所在的位置(不知道怎么表达)  
            layout: 'horizontal',  
            align: 'right',  
            verticalAlign: 'top',  
            x: -10,  
            y: -10,   
            borderWidth: 0  
        },  
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园',
                '杭州鹿城花园'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位 (个)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true,
            style: {  //提示框内容的样式
	            color: '046752',
	            padding: '10px',    //内边距 (这个会常用到)
	            fontSize: '9pt',            
	        }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
		labelFormatter: function() {
   /*
    *  格式化函数可用的变量：this， 可以用 console.log(this) 来查看包含的详细信息
    *  this 代表当前数据列对象，所以默认的实现是 return this.name 
    */
    return  this.name + '(Click to hide or show)';  

	}  ,


        series: [{
            name: '问题',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }, {
            name: '已修改',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

        }]
    });
    
    
    
    
});						