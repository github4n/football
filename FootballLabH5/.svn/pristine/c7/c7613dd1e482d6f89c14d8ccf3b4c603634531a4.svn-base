var turnplate = {
    restaraunts: [],			// 大转盘奖品名称
    colors: [],					// 大转盘奖品区块对应背景颜色
    ids: [],                    // 大转盘奖品对应id
    scales: [],                 // 大转盘奖品对应概率
    activity_type: [],          // 对应类别
    number: [],                 // 积分数量
    coupon_type: [],            // 优惠券类别
    outsideRadius: 192,			// 大转盘外圆的半径
    textRadius: 155,			// 大转盘奖品位置距离圆心的距离
    insideRadius: 68,			// 大转盘内圆的半径
    startAngle: 0,				// 开始角度
    bRotate: false				// false:停止;ture:旋转
};

$(document).ready(function () {
    var data = GetData(fl_url.activityData, {});
    data = JSON.parse(data);
    var prizeNameArray = new Array();
    var idArray = new Array();
    var scales = new Array();
    var activity_typearr = new Array();
    var number_arr = new Array();
    var coupon_type_arr = new Array();
    for (var i = 0; i < data.length; i++) {
        prizeNameArray.push(data[i].prize_name);
        idArray.push(data[i].id);
        scales.push(data[i].scale);
        activity_typearr.push(data[i].activity_type);
        coupon_type_arr.push(data[i].coupon_type);
        if (data[i].number == undefined) {
            number_arr.push(0);
        } else {
            number_arr.push(data[i].number);
        }
    }
    //动态添加大转盘的奖品与奖品区域背景颜色
    turnplate.restaraunts = prizeNameArray;
    turnplate.colors = ["#e04854", "#50b637", "#336fd4", "#d38d12", "#ae39e5", "#d38d12", "#e04854", "#50b637", "#336fd4", "#d38d12", "#50b637", "#336fd4", "#d38d12", "#50b637", "#336fd4", "#d38d12", "#50b637", "#336fd4", "#d38d12", "#50b637", "#336fd4", "#d38d12", "#50b637", "#336fd4", "#d38d12"];
    turnplate.ids = idArray;
    turnplate.scales = scales;
    turnplate.activity_type = activity_typearr;
    turnplate.number = number_arr;
    turnplate.coupon_type = coupon_type_arr;

    var rotateTimeOut = function () {
        $('#wheelcanvas').rotate({
            angle: 0,
            animateTo: 2160,
            duration: 8000,
            callback: function () {
                console.log('网络超时，请检查您的网络设置！');
            }
        });
    };

    //旋转转盘 item:奖品位置; txt：提示语;
    var rotateFn = function (item, txt,result) {
        var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length * 2));
        if (angles < 270) {
            angles = 270 - angles;
        } else {
            angles = 360 - angles + 270;
        }
        $('#wheelcanvas').stopRotate();
        $('#wheelcanvas').rotate({
            angle: 0,
            animateTo: angles + 1800,
            duration: 8000,
            callback: function () {
                // 再来一次
                if (turnplate.activity_type[item - 1] == 3) {
                	/*var jfNum = parseInt($(".colorH").text());
                    $(".colorH").text(jfNum + 100);
                    fl_user.SetIntegral(100);*/
                    tishi_error(txt);
                    turnplate.bRotate = !turnplate.bRotate;
                    return;
                }

                // 积分
                if (turnplate.activity_type[item - 1] == 2){
                    var jfNum = parseInt($(".colorH").text());
                    $(".colorH").text(parseInt(jfNum+ parseInt(turnplate.number[item - 1])));
                    fl_user.SetIntegral(turnplate.number[item - 1]);
                    tishi_error(txt);
                }
                // 方案
                if (turnplate.activity_type[item - 1] == 1) {
                    txt += "使用券一张";
                    if (fl_user.focus_status() != 1) {
                        confirm_error1("<lable style=\"color:#000000;font-size:16px;\">恭喜您！</lable><br>获取" + txt + "<br><span style='color:#FF8000;'>温馨提示：为保证奖品获得，请先+关注</span>"
                            , function () {
                                if (getQueryString("experts_code") == "jinghu") {
                                    location.href = "http://mp.weixin.qq.com/s?__biz=MzAxMjk1NjYwMA==&mid=2247483654&idx=1&sn=66ed8863ea21271f03880c7338e8bad4&scene=0#wechat_redirect";
                                } else {
                                    location.href = "http://mp.weixin.qq.com/s?__biz=MzIxODQ5MTY4Mw==&mid=2247483666&idx=1&sn=2c38aac6da365d49afc1f468d99bff1e&scene=0#wechat_redirect";
                                }
                        }, function () {
                            location.href = "index.html?experts_code=" + getQueryString("experts_code");
                        }, "查看战绩", "<span style='color:#FF8000;'>+关注</span>");
                    } else {
                        confirm_error1("<lable style=\"color:#000000;font-size:16px;\">恭喜您！</lable><br>获取" + txt, function () { }, function () {
                            if (result.mcid == undefined) {
                                location.href = "myCoupon.html?experts_code=" + getQueryString("experts_code");
                            } else {
                                var type = turnplate.coupon_type[item - 1];
                                if (type == "01" || type == "02" || type == "03" || type == "04" || type == "05" || type == "06" || type == "1" || type == "4") {
                                    //location.href = "useCoupon.html?mcid=" + result.mcid + "&type=" + type + "&experts_code=" + getQueryString("experts_code");
                                	location.href = "index.html?experts_code=" + getQueryString("experts_code");
                                } else {
                                    location.href = "index.html?experts_code=" + getQueryString("experts_code");
                                }
                            }
                        }, "立即使用", "继续抽奖");
                    }
                }
                turnplate.bRotate = !turnplate.bRotate;
                
            }
        });
    };

    $('.pointer').click(function () {
        if (turnplate.bRotate) return;

        if (fl_user.focus_status() == 1) {
            var jfNum = parseInt($(".colorH").text());
            if (jfNum - 100 < 0) {

                var memberInfo;
                if (fl_user.IsLogin()) {
                    memberInfo = GetData(fl_url.memberInfo, { phoneNum: fl_user.Mobile(), serviceId: getQueryString("serviceId") });
                    memberInfo = JSON.parse(memberInfo);
                }

                if (memberInfo.hasShare == 0) {
                    confirm_error1("<lable style=\"color:#000000;font-size:16px;\">对不起！</lable><br>您当前积分不足，分享后可获得100积分！", function () {
                        $("#pShart").show();
                    }, function () {
                        location.href = "index.html?experts_code=" + getQueryString("experts_code");
                    }, "查看专家方案", "立即分享");
                } else {
                    alert_error("<lable style=\"color:#000000;font-size:16px;\">对不起！</lable><br>您当前积分不足，明日再来！", function () {
                        location.href = "payment.html?experts_code=" + getQueryString("experts_code");
                    },function () {
                        location.href = "index.html?experts_code=" + getQueryString("experts_code");
                    }, "查看专家方案", "立即充值");
                }
                return;
            }
        }
        var jfNum = parseInt($(".colorH").text());
        $(".colorH").text(jfNum - 100);
        fl_user.SetIntegral(-100);

        turnplate.bRotate = !turnplate.bRotate;
        //获取随机数(奖品个数范围内)
        //var item = rnd(1, turnplate.restaraunts.length);
        
        // 请求接口记录数据
        var UserInfo = sessionStorage.UserInfo;
        UserInfo = JSON.parse(UserInfo);
        var result;
        if (UserInfo.phone_number != undefined) {
        	var token = $.base64.btoa(fl_user.Mobile()+"&"+(new Date()).getTime(),true);
        	var info = $.base64.btoa(token+"_"+fl_user.Mobile(),true);
    		result = GetData(fl_url.saveActivityData, {info: info,token:token});
        }
        
        result = JSON.parse(result);
        if(result!=undefined){
        	rotateFn(result.item, result.name,result);
        }
        
        // turnplate.scales
        //奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
        //rotateFn(item, turnplate.restaraunts[item - 1]);
        
    });
});

function rnd(n, m) {
    var random = Math.floor(Math.random() * (m - n + 1) + n);
    return random;

}


//页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
window.onload = function () {
    drawRouletteWheel();
};

function drawRouletteWheel() {
    var canvas = document.getElementById("wheelcanvas");
    if (canvas.getContext) {
        //根据奖品个数计算圆周角度
        var arc = Math.PI / (turnplate.restaraunts.length / 2);
        var ctx = canvas.getContext("2d");
        //在给定矩形内清空一个矩形
        ctx.clearRect(0, 0, 422, 422);
        //strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式  
        ctx.strokeStyle = "#FFBE04";
        //font 属性设置或返回画布上文本内容的当前字体属性
        ctx.font = '16px Microsoft YaHei';
        for (var i = 0; i < turnplate.restaraunts.length; i++) {
            var angle = turnplate.startAngle + i * arc;
            ctx.fillStyle = turnplate.colors[i];
            ctx.beginPath();
            //arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）    
            ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);
            ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
            ctx.stroke();
            ctx.fill();
            //锁画布(为了保存之前的画布状态)
            ctx.save();

            //----绘制奖品开始----
            ctx.fillStyle = "#fff";
            var text = turnplate.restaraunts[i];
            var line_height = 17;
            //translate方法重新映射画布上的 (0,0) 位置
            ctx.translate(211 + Math.cos(angle + arc / 2) * turnplate.textRadius, 211 + Math.sin(angle + arc / 2) * turnplate.textRadius);

            //rotate方法旋转当前的绘图
            ctx.rotate(angle + arc / 2 + Math.PI / 2);

            /** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/
            if (text.indexOf("M") > 0) {//流量包
                var texts = text.split("M");
                for (var j = 0; j < texts.length; j++) {
                    ctx.font = j == 0 ? 'bold 20px Microsoft YaHei' : '16px Microsoft YaHei';
                    if (j == 0) {
                        ctx.fillText(texts[j] + "M", -ctx.measureText(texts[j] + "M").width / 2, j * line_height);
                    } else {
                        ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
                    }
                }
            } else if (text.indexOf("M") == -1 && text.length > 6) {//奖品名称长度超过一定范围 
                text = text.substring(0, 6) + "||" + text.substring(6);
                var texts = text.split("||");
                for (var j = 0; j < texts.length; j++) {
                    ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
                }
            } else {
                //在画布上绘制填色的文本。文本的默认颜色是黑色
                //measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
                ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
            }

            //添加对应图标
            if (text.indexOf("闪币") > 0) {
                var img = document.getElementById("shan-img");
                img.onload = function () {
                    ctx.drawImage(img, -15, 10);
                };
                ctx.drawImage(img, -15, 10);
            } else if (text.indexOf("谢谢参与") >= 0) {
                var img = document.getElementById("sorry-img");
                img.onload = function () {
                    ctx.drawImage(img, -15, 10);
                };
                ctx.drawImage(img, -15, 10);
            }
            //把当前画布返回（调整）到上一个save()状态之前 
            ctx.restore();
            //----绘制奖品结束----
        }
    }
}