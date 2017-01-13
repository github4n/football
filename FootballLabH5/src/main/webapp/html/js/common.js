﻿// JS ajax执行并返回结果
function GetData(url, param) {
    var result;
    $.ajax({
        type: "post",
        url: fl_app.AjaxHome+url,
        data: param,
        async: false,
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        //在请求之前调用的函数
        beforeSend: function () { },
        success: function (data) {
            //console.log(data);
            //console.log(param);
            result = data;
        },
        //调用执行后调用的函数
        complete: function (XMLHttpRequest, textStatus) {
        },
        //调用出错执行的函数
        error: function () {
            tishi_error("网络不给力，请稍后重试。");
            //请求出错处理
            console.log(url+"出错了");
        }
    });
    return result;
}
/**
 * 记录用户访问信息
 */
function addVisitInfo(){
   var urlArray = location.href.split("/");
   var pageName = urlArray[urlArray.length-1].split("?")[0];
    
   var param = {
		        memberId:fl_user.MemberId(),
		        memberName:fl_user.MemberName(),
		   	    expertsCode:getQueryString("experts_code"),
			   	serviceCode:getQueryString("service_code"),
			   	pageName:pageName
   			  };
   
   $.ajax({
        type: "post",
        url: fl_app.AjaxHome+fl_url.visitInfo,
        data: param,
        async: true,
        success: function (data) {
        },
    });
}

// 获取URL参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

// 计算时间差,天
function GetDateDiffDay(starttime, endtime) {
    starttime = new Date(starttime.replace(/-/g, "/").substring(0, 18));
    endtime = new Date(endtime.replace(/-/g, "/").substring(0, 18));

    var date3 = endtime.getTime() - starttime.getTime()  //时间差的毫秒数

    //计算出相差天数
    var days = Math.floor(date3 / (24 * 3600 * 1000))
    //计算出小时数
    var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
    var hours = Math.floor(leave1 / (3600 * 1000))
    //计算相差分钟数
    var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
    var minutes = Math.floor(leave2 / (60 * 1000))


    //计算相差秒数
    var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
    var seconds = Math.round(leave3 / 1000)


    return { day: days, hours: hours, minutes: minutes, seconds: seconds };
}

// 计算时间差,分钟
function GetDateDiffMinutes(starttime, endtime) {
    starttime = new Date(starttime.replace(/-/g, "/").substring(0, 18));
    endtime = new Date(endtime.replace(/-/g, "/").substring(0, 18));

    var date3 = endtime.getTime() - starttime.getTime();  //时间差的毫秒数

    var minutes = Math.floor(date3 / (60 * 1000));

    return minutes;
}

// 获取当前时间(yyyy-MM-dd)格式
function GetDataYMD() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    return year + "-" + month + "-"+day;
}
function GetDateYMDHMS() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var hh = myDate.getHours();
    var mm = myDate.getMinutes();
    var ss = myDate.getSeconds();
    return year + "-" + month + "-" + day + " "+hh+":"+mm+":"+ss;
}

// 获取当前时间-年份
function GetDataYear() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    return year;
}
// 获取当前时间-月份
function GetDataMonth() {
    var myDate = new Date();
    var month = myDate.getMonth() + 1;
    return month;
}

// 获取传递过来的时间
function GetDateFByTime(f, s) {
    s = s.replace(/-/g,"/").substring(0,18);
    var myDate = new Date(s);
    var result;
    switch (f) {
        case "yy":
            result = myDate.getFullYear();
            break;
        case "MM":
            result = myDate.getMonth() + 1;
            break;
        case "dd":
            result = myDate.getDate();
            break;
        case "hh":
            result = myDate.getHours();
            break;
        case "mm":
            result = myDate.getMinutes();
            break;
        case "ss":
            result = myDate.getSeconds();
            break;
        case "day":
            result = myDate.getDay();
            switch (result)
            {
                case 0:
                    result = "星期日";
                    break;
                case 1:
                    result = "星期一";
                    break;
                case 2:
                    result = "星期二";
                    break;
                case 3:
                    result = "星期三";
                    break;
                case 4:
                    result = "星期四";
                    break;
                case 5:
                    result = "星期五";
                    break;
                case 6:
                    result = "星期六";
                    break;
            }
            break;
    }
    if (!isNaN(result) && result < 10) {
        result = "0" + result;
    }
    return result;
}

// 动态加载文件
$.extend({
    includePath: '',
    include: function (file) {
        var files = typeof file == "string" ? [file] : file;
        var v = 5; //每次修改js或者css文件的时候记得修改这边的版本号
        v = '?v=' + v;
        for (var i = 0; i < files.length; i++) {
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + $.includePath + name + v + "'";
            if ($(tag + "[" + link + "]").length == 0) document.write("<" + tag + attr + link + "></" + tag + ">");
        }
    }
});

// 普通提示错误框（不需要确认）*************************************
// ***  str  >>>>  提示语
// ***  top  >>>>  提示框位置距离顶部 默认50%
// ***  time >>>>  多长时间消失 默认3000
tishi_error_type = null;
function tishi_error(str, top, time) {
    time = time ? time : 3000;
    if ($("#lss_tishi_error").length != 0) {
        if ($("#lss_tishi_error").attr("stats") == 0) {
            $("#lss_tishi_error .con").html(str);
            tishi_error_size(top);
            $("#lss_tishi_error").fadeIn().attr("stats", 1);
        } else {
            clearInterval(tishi_error_type);
            $("#lss_tishi_error .con").html(str);
            tishi_error_size(top);
        }
        tishi_error_type = setTimeout(function () { $("#lss_tishi_error").fadeOut().attr("stats", 0); }, time);
    } else {
        var html = '<div id="lss_tishi_error" stats=0><div class="box"></div><div class="con">' + str + '</div></div>';
        $("body").append(html);
        tishi_error_size(top);
        $("#lss_tishi_error").fadeIn().attr("stats", 1);
        tishi_error_type = setTimeout(function () { $("#lss_tishi_error").fadeOut().attr("stats", 0); }, time);
    }
}

// 提示框位置*************************************
function tishi_error_size(top) {
    var W = window.innerWidth;
    var w = $("#lss_tishi_error").width();
    T = top ? top : "45%";
    $("#lss_tishi_error").css({ "left": (W - w) / 2 + "px", "top": T })
}

// Alert 错误提示框（需要确认的）*************************************
// *** con >>>>  错误内容
// *** str >>>>  确认按钮的文字
// *** fun >>>>  点击后执行的函数
function alert_error(con, str, fun) {
    str = str ? str : "知道了";
    if ($("#lss_alert_error").length == 0) {
        var html = '<div id="lss_alert_error"><div class="con"></div><a class="clone" href="javascript:;"></a></div>';
        if ($("#lss_black_bg").length == 0) html += '<div id="lss_black_bg"></div>';
        $("body").append(html);
    }
    var W = window.innerWidth;
    var H = window.innerHeight;
    var w = $("#lss_alert_error").width();
    var h = $("#lss_alert_error").height();
    $("#lss_alert_error>.con").html(con);
    $("#lss_alert_error>.clone").html(str);
    $("#lss_black_bg").fadeIn();
    $("#lss_alert_error").css({ "top": ((H - h) / 2-40) + "px", "left": (W - w) / 2 + "px" }).fadeIn();
    $(document).one('click', '#lss_alert_error>.clone', function (event) {
        $("#lss_black_bg").fadeOut();
        $("#lss_alert_error").fadeOut();
        if (fun) fun();
    });
}

// confirm 提示框*************************************
// *** con >>>>  提示内容
// *** fun >>>>  确认后执行的函数
// *** str >>>>  确认按钮文本
// *** str1>>>>  取消按钮文本
function confirm_error(con, fun, str, str1) {
    str = str ? str : "确认";
    str1 = str1 ? str1 : "取消";
    if ($("#lss_confirm_error").length == 0) {
        var html = '<div id="lss_confirm_error"><div class="con"></div><a class="confirm" href="javascript:;"></a><a class="cancel" href="javascript:;"></a></div>';
        if ($("#lss_black_bg").length == 0) html += '<div id="lss_black_bg"></div>';
        $("body").append(html);
    }
    var W = window.innerWidth;
    var H = window.innerHeight;
    var w = $("#lss_confirm_error").width();
    var h = $("#lss_confirm_error").height();
    $("#lss_confirm_error>.con").html(con);
    $("#lss_confirm_error>.confirm").html(str);
    $("#lss_confirm_error>.cancel").html(str1);
    $("#lss_black_bg").fadeIn();
    $("#lss_confirm_error").css({ "top": ((H - h) / 2 - 40) + "px", "left": (W - w) / 2 + "px" }).fadeIn();
    //取消
    $(document).one('click', '#lss_confirm_error>.cancel', function (event) {
        $("#lss_black_bg").fadeOut();
        $("#lss_confirm_error").fadeOut();
    });
    // 确定
    $(document).one('click', '#lss_confirm_error>.confirm', function (event) {
        $("#lss_black_bg").fadeOut();
        $("#lss_confirm_error").fadeOut();
        if (fun) fun();
    });
}

// confirm 提示框*************************************
// *** con >>>>  提示内容
// *** fun >>>>  确认后执行的函数
// *** str >>>>  确认按钮文本
// *** str1>>>>  取消按钮文本
function confirm_error1(con, fun, fun1, str, str1) {
    str = str ? str : "确认";
    str1 = str1 ? str1 : "取消";
    if ($("#lss_confirm_error").length == 0) {
        var html = '<div id="lss_confirm_error"><div class="con"></div><a class="confirm" href="javascript:;"></a><a class="cancel" href="javascript:;"></a></div>';
        if ($("#lss_black_bg").length == 0) html += '<div id="lss_black_bg"></div>';
        $("body").append(html);
    }
    var W = window.innerWidth;
    var H = window.innerHeight;
    var w = $("#lss_confirm_error").width();
    var h = $("#lss_confirm_error").height();
    $("#lss_confirm_error>.con").html(con);
    $("#lss_confirm_error>.confirm").html(str);
    $("#lss_confirm_error>.cancel").html(str1);
    $("#lss_black_bg").fadeIn();
    $("#lss_confirm_error").css({ "top": ((H - h) / 2 - 40) + "px", "left": (W - w) / 2 + "px" }).fadeIn();
    //取消
    $(document).one('click', '#lss_confirm_error>.cancel', function (event) {
        $("#lss_black_bg").fadeOut();
        $("#lss_confirm_error").fadeOut();
        if (fun) fun();
    });
    // 确定
    $(document).one('click', '#lss_confirm_error>.confirm', function (event) {
        $("#lss_black_bg").fadeOut();
        $("#lss_confirm_error").fadeOut();
        if (fun1) fun1();
    });
}

function rMs() {
    setTimeout(function () {
        myScroll.refresh();
    }, 500);
}

// 保留两位小数，补零
function returnFloat(value) {
    var value = Math.round(parseFloat(value) * 100) / 100;
    var xsd = value.toString().split(".");
    if (xsd.length == 1) {
        value = value.toString() + ".00";
        return value;
    }
    if (xsd.length > 1) {
        if (xsd[1].length < 2) {
            value = value.toString() + "0";
        }
        return value;
    }
}

// 昨天-1，今天0，明天1
function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期 
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;//获取当前月份的日期 
    var d = dd.getDate();

    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d = "0" + d;
    }

    return y + "-" + m + "-" + d;
    //return m + "-" + d;
}


function GetDateStrP(AddDayCount, p) {
    p =p.replace(/-/g, "/").substring(0, 18);
    var dd = new Date(p);
    dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期 
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;//获取当前月份的日期 
    var d = dd.getDate();

    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d = "0" + d;
    }

    return y + "-" + m + "-" + d;
}

function delQueStr(url, ref) //删除参数值
{
    var str = "";

    if (url.indexOf('?') != -1)
        str = url.substr(url.indexOf('?') + 1);
    else
        return url;
    var arr = "";
    var returnurl = "";
    var setparam = "";
    if (str.indexOf('&') != -1) {
        arr = str.split('&');
        for (i in arr) {
            if (arr[i].split('=')[0] != ref) {
                returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
            }
        }
        return url.substr(0, url.indexOf('?')) + "?" + returnurl.substr(0, returnurl.length - 1);
    }
    else {
        arr = str.split('=');
        if (arr[0] == ref)
            return url.substr(0, url.indexOf('?'));
        else
            return url;
    }
}

//倒计时

function Timer (dom,data){
	var timer=null;
	var now;
	var cc = document.querySelectorAll(dom);
	var leftTime;
	var leftsecond;
	var endDate;
	var day1;
	var hour;
	var minute;
	var second;
    function ShowCountDown(dom,data){
    	now = new Date();
		for (var i=0;i<data.timArr.length;i++){			
		    endDate = new Date(data.timArr[i].year,data.timArr[i].month-1,data.timArr[i].day,data.timArr[i].hour,data.timArr[i].minute);
		    leftTime = endDate.getTime() - now.getTime();
		    leftsecond = parseInt(leftTime / 1000);
		    day1=parseInt(leftsecond/(24*60*60*6)); 
		   // var day1 = Math.floor(leftsecond / (60 * 60 * 24));
		    hour = Math.floor((leftsecond - day1 * 24 * 60 * 60) / 3600);
		    minute = Math.floor((leftsecond - day1 * 24 * 60 * 60 - hour * 3600) / 60);
		    second = Math.floor(leftsecond - day1 * 24 * 60 * 60 - hour * 3600 - minute * 60);
		    if(hour>=0&&minute>=0){
		    	cc[i].innerHTML = "剩余时间 : "+hour + "小时" + minute + "分";  	
		    }else{
		    	cc[i].innerHTML = "已结束";
		    	cc[i].style.color="#999";
		    	
		    }
		}   	
    }
	ShowCountDown(dom,data)
	clearInterval(timer)
    var timer=setInterval(function(){  
    	ShowCountDown(dom,data)
    },data.intrval)    

}
