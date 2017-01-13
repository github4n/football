$(function () {
    //sessionStorage.setItem("UserInfo", JSON.stringify({ phone_number: "13663917121", membe_point: 1000, focus_status: 1 ,member_id:"b962bad7d2894826a7801cefb5ffdc01",weixin:"、N    q"}));//13511112222

    if (sessionStorage.UserInfo == undefined) {
        var device = getQueryString("device");
        var code = getQueryString("code");
        if (code == undefined) {
            // 跳转到授权页面(需要授权)
            if (device == "browse") {
                GoToWeiXinAuthorizeUrl("snsapi_userinfo");
            }
            // 跳转到授权页面(静默授权)
            if (device == undefined || device == "" || device == null) {
                GoToWeiXinAuthorizeUrl("snsapi_base");
            }
        } else {
            var data = GetData(fl_url.oauth, { code: code });
            sessionStorage.setItem("UserInfo", data);
            //alert(data);
        }
    }
    // alert(sessionStorage.UserInfo);
});
$.include(['http://res.wx.qq.com/open/js/jweixin-1.0.0.js']);
// 跳转到微信授权页面,scope:snsapi_base（不弹出授权页面，只能获得openid）、snsapi_userinfo（弹出授权页面，可以获得用户信息）
function GoToWeiXinAuthorizeUrl(scope) {
    var data = GetData(fl_url.getWxId, {});
    data = JSON.parse(data);
    var redirect_uri = encodeURIComponent(window.location.href);
    location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + data.app_id + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=" + scope + "&state=weixin#wechat_redirect";
}

// 初始化微信JSAPI
function Init() {
    var data = GetData(fl_url.getShareInfo, { url: window.location.href });
    //console.log(data);
    data = JSON.parse(data);

    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: data.appId, // 必填，公众号的唯一标识
        timestamp: data.timestamp, // 必填，生成签名的时间戳
        nonceStr: data.nonceStr, // 必填，生成签名的随机串
        signature: data.signature,// 必填，签名，见附录1
        jsApiList: ["onMenuShareTimeline", "onMenuShareAppMessage", "chooseWXPay", "hideMenuItems"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。

        if (typeof (sharttitle) == "undefined" || sharttitle == undefined) {
            sharttitle = "";
        }
        if (typeof (shartcontent) == "undefined" || shartcontent == undefined || shartcontent == "") {
            shartcontent = "最直接的方案推荐";
        }
        var link = "";
        if (typeof (shartlink) == "undefined" || shartlink == undefined || shartlink == "") {
            link = location.href;
            if (link.indexOf("&device") < 0) {
                link = link + "&device=browse";
            }
        }
        else {
            link = shartlink;
            if (link.indexOf("&device") < 0) {
                link = link + "&device=browse";
            }
        }

        link = delQueStr(link,"code");

        wx.hideMenuItems({
            menuList: ["menuItem:share:qq", "menuItem:share:QZone", "menuItem:share:weiboApp", "menuItem:share:facebook"] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
        });

        // 分享到朋友圈
        wx.onMenuShareTimeline({
            title: sharttitle, // 分享标题
            link: link, // 分享链接
            imgUrl: 'http://www.01588.com/wechat/img/getheadimg.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
                GetData(fl_url.saveSharePoints, {
                    phoneNum: fl_user.Mobile()
                });
                location.reload();
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        // 发送给朋友
        wx.onMenuShareAppMessage({
            title: sharttitle, // 分享标题
            desc: shartcontent, // 分享描述
            link: link, // 分享链接
            imgUrl: 'http://www.01588.com/wechat/img/getheadimg.jpg', // 分享图标
            //type: '', // 分享类型,music、video或link，不填默认为link
            //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
                GetData(fl_url.saveSharePoints, {
                    phoneNum: fl_user.Mobile()
                });
                location.reload();
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        // 分享到QQ
        //wx.onMenuShareQQ({
        //    title: sharttitle, // 分享标题
        //    desc: shartcontent, // 分享描述
        //    link: link, // 分享链接
        //    imgUrl: 'http://www.01588.com/wechat/img/getheadimg.jpg', // 分享图标
        //    success: function () {
        //        // 用户确认分享后执行的回调函数
        //        GetData(fl_url.saveSharePoints, {
        //            phoneNum: fl_user.Mobile()
        //        });
        //        location.reload();
        //    },
        //    cancel: function () {
        //        // 用户取消分享后执行的回调函数
        //    }
        //});

        //// 分享到腾讯微博
        //wx.onMenuShareWeibo({
        //    title: sharttitle, // 分享标题
        //    desc: shartcontent, // 分享描述
        //    link: link, // 分享链接
        //    imgUrl: 'http://www.01588.com/wechat/img/getheadimg.jpg', // 分享图标
        //    success: function () {
        //        // 用户确认分享后执行的回调函数
        //        GetData(fl_url.saveSharePoints, {
        //            phoneNum: fl_user.Mobile()
        //        });
        //        location.reload();
        //    },
        //    cancel: function () {
        //        // 用户取消分享后执行的回调函数
        //    }
        //});

        //// 分享到QQ空间
        //wx.onMenuShareQZone({
        //    title: sharttitle, // 分享标题
        //    desc: shartcontent, // 分享描述
        //    link: link, // 分享链接
        //    imgUrl: 'http://www.01588.com/wechat/img/getheadimg.jpg', // 分享图标
        //    success: function () {
        //        // 用户确认分享后执行的回调函数
        //        GetData(fl_url.saveSharePoints, {
        //            phoneNum: fl_user.Mobile()
        //        });
        //        location.reload();
        //    },
        //    cancel: function () {
        //        // 用户取消分享后执行的回调函数
        //    }
        //});
    });

    wx.error(function (res) {

        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

    });
}



// 调起微信支付
function PayWeiXin(appId, timestamp, nonceStr, p, signType, paySign,tid) {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": appId,     //公众号名称，由商户传入     
            "timeStamp": timestamp,         //时间戳，自1970年以来的秒数     
            "nonceStr": nonceStr, //随机串     
            "package": p,
            "signType": signType,         //微信签名方式：     
            "paySign": paySign //微信签名 
        },
        function (res) {
            // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
            // 支付成功
            if (res.err_msg == "get_brand_wcpay_request:ok") {
                location.href = "myCustomized_detail.html?tid=" + tid + "&experts_code=" + getQueryString("experts_code");
            }
            // 用户取消
            if (res.err_msg == "get_brand_wcpay_request:cancel") {
            	duiduTransactionFail(tid);
                tishi_error("用户取消支付。");
            }
            // 支付失败
            if (res.err_msg == "get_brand_wcpay_request:fail") {
            	duiduTransactionFail(tid);
                tishi_error("支付失败。");
            }
        }
    );
}

/**
 * 将对赌支付订单置为失败
 * @param tid
 */
function duiduTransactionFail(tid){
	    
	   var param = {tid:tid};
	   
	   $.ajax({
	        type: "post",
	        url: fl_app.AjaxHome+"/webChat/duiduTransactionFail",
	        data: param,
	        async: true,
	        success: function (data) {
	        },
	    });
	}

