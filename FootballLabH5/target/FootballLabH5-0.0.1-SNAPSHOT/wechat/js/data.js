// 公有属性
var fl_app = {
		AjaxHome: "http://www.01588.com"//"http://118.244.237.231:8182",// Ajax域名
//		AjaxHome: "http://127.0.0.1:8080/FootballLabH5"
};
// 接口访问路径
var fl_url = {
    myCoupon: "/webChat/myCoupon?experts_code=" + getQueryString("experts_code"),// 我的优惠券
    myCouponDetaile: "/webChat/myCouponDetaile?experts_code=" + getQueryString("experts_code"),// 我的优惠券详情
    myPoints: "/webChat/myPoints?experts_code=" + getQueryString("experts_code"),// 我的积分
    myTrade: "/webChat/myTrade?experts_code=" + getQueryString("experts_code"),// 交易记录
    myTradeDetaile: "/webChat/myTradeDetaile?experts_code=" + getQueryString("experts_code"),// 交易记录详细
    myCustomized: "/webChat/myCustomized?experts_code=" + getQueryString("experts_code"),// 我的定制
    index: "/webChat/service?experts_code=" + getQueryString("experts_code"),// 首页//caishen
    indexNew: "/webChat/serviceByCode?experts_code=" + getQueryString("experts_code"),// 首页//caishen
    singleDayList: "/webChat/singleDayList?experts_code=" + getQueryString("experts_code"),// 盈利走势图
    singleTimeList: "/webChat/singleTimeList?experts_code=" + getQueryString("experts_code"),// 盈利走势图-详情
    activityData: "/webChat/activityData?experts_code=" + getQueryString("experts_code"),// 奖品数据
    sendCode: "/webChat/sendCode?experts_code=" + getQueryString("experts_code"),// 发送短信
    login: "/webChat/login?experts_code=" + getQueryString("experts_code"),// 登录
    getPkBettingGame: "/webChat/getPkBettingGame?experts_code=" + getQueryString("experts_code"),// PK赛选择场次
    savePk: "/webChat/savePk",// 保存pk数据
    myPlan: "/webChat/myPlan?experts_code=" + getQueryString("experts_code"),// 我的方案
    mySingleDayList: "/webChat/mySingleDayList?experts_code=" + getQueryString("experts_code"),// 我的方案-二级
    mySingleTimeList: "/webChat/mySingleTimeList?experts_code=" + getQueryString("experts_code"),// 我的方案-三级
    saveActivityData: "/webChat/saveActivityData?experts_code=" + getQueryString("experts_code"),// 保存抽奖信息
    memberInfo: "/webChat/memberInfo?experts_code=" + getQueryString("experts_code"),// 是否pk、是否定制、是否查看、是否分享  hasPK/hasTransaction/hasLook/hasShare
    saveViewRecord: "/webChat/saveViewRecord?experts_code=" + getQueryString("experts_code"),// 保存查看今日方案
    tradeDetaile: "/webChat/tradeDetaile?experts_code=" + getQueryString("experts_code"),// 订单详情
    hasPkBettingGame: "/webChat/hasPkBettingGame?experts_code=" + getQueryString("experts_code"),// 判断是否有pk赛数据
    indexLink: "/webChat/link?experts_code=" + getQueryString("experts_code"),// 首页获取轮播图
    visitInfo: "/webChat/memberVisitInfo",// 首页//caishen
    serviceInfo: "/webChat/getServiceInfo",
    getOnlineGuessGame:"/webChat/getOnlineGuessGame?experts_code=" + getQueryString("experts_code"),//竞猜比赛信息
    validateCanGuess:"/webChat/validateCanGuess?experts_code=" + getQueryString("experts_code"),//验证是否可以竞猜
    guessRanking:"/webChat/getGuessRanking?experts_code=" + getQueryString("experts_code"),//获取投注排行榜
    getGuessGame:"/webChat/getGuessGame?experts_code=" + getQueryString("experts_code"),//根据比赛id获取比赛信息
    memberGuessGame:"/webChat/guessGame?experts_code=" + getQueryString("experts_code"),//用户提交竞猜信息
    myGuess:"/webChat/getGuessList?experts_code=" + getQueryString("experts_code"),//用户竞猜列表
    memberGuessDetail:"/webChat/memberGuessDetail?experts_code=" + getQueryString("experts_code"),//用户竞猜详情
    exchange:"/webChat/exchange?experts_code=" + getQueryString("experts_code"),//积分兑换页面数据
    validateCanRegister:"/webChat/validateCanRegister?experts_code=" + getQueryString("experts_code"),//验证用户是否可注册
    getTodayGameInfo:"/webChat/getTodayGameInfo?experts_code=" + getQueryString("experts_code"),//获取当日比赛信息
    getExpertRanking:"/webChat/getExpertRanking?experts_code=" + getQueryString("experts_code"),//获取专家排行信息
    
    getGuessProfitList:"/webChat/getGuessProfitList?experts_code=" + getQueryString("experts_code"),//获取竞猜积分盈亏列表
    getGuessProfitInfoById:"/webChat/getGuessProfitInfoById?experts_code=" + getQueryString("experts_code"),//获取竞猜积分盈亏详情信息
    guessServcieProfit:"/webChat/guessServcieProfit?experts_code=" + getQueryString("experts_code"),//用户提交竞猜盈亏信息
    myGuessProfit:"/webChat/getMemberGuessProfitList?experts_code=" + getQueryString("experts_code"),//用户竞猜盈亏记录
    memberGuessProfitDetail:"/webChat/memberGuessProfitDetail?experts_code=" + getQueryString("experts_code"),//用户竞猜盈亏详情
    
    followPayConfig:"/webChat/followPayConfig?experts_code=" + getQueryString("experts_code"),//用户跟单
    myFollowOrder:"/webChat/myFollowOrder?experts_code=" + getQueryString("experts_code"),//跟单记录
    followPayPage:"/webChat/followPayPage?experts_code=" + getQueryString("experts_code"),//跟单支付页信息
    followOrderHall:"/webChat/followOrderHall?experts_code=" + getQueryString("experts_code"),//跟单大厅
    isFollowOrder:"/webChat/isFollowOrder?experts_code=" + getQueryString("experts_code"),//验证是否已经跟单
    
    saveSharePoints: "/webChat/saveSharePoints?experts_code=" + getQueryString("experts_code"),// 分享增加积分
    getWxId: "/webChat/getWxId?experts_code=" + getQueryString("experts_code"),// 获取appid等信息
    oauth: "/webChat/oauth?experts_code=" + getQueryString("experts_code"),// 获取用户信息
    getShareInfo: "/webChat/getShareInfo?experts_code=" + getQueryString("experts_code"),// 获取分享配置信息
    payConfig: "/webChat/payConfig?experts_code=" + getQueryString("experts_code"),// 支付
};
// 用户信息
var fl_user = {
    // 用户手机号码
    Mobile: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        if (userinfo.phone_number == undefined) {
            sessionStorage.setItem("topurl", window.location.href);
            location.href = "loginOrRegister.html?experts_code=" + getQueryString("experts_code");
        }

        return userinfo.phone_number;
    },
    MemberId: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        if (userinfo.member_id == undefined) {
        	return "";
        }

        return userinfo.member_id;
    },
    MemberName: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        if (userinfo.weixin == undefined) {
        	return "";
        }

        return userinfo.weixin;
    },
    // 是否登录
    IsLogin: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        if (userinfo.phone_number == undefined) {
            return false;
        }
        return true;
    },
    // 积分
    Integral: function () {
        //var userinfo = sessionStorage.UserInfo;
        //userinfo = JSON.parse(userinfo);
        //return userinfo.membe_point;

        var data_i = GetData(fl_url.myPoints,{
            phoneNum: fl_user.Mobile(),
            showCount: 1,
            currentPage: 0
        });
        try{
            data_i = JSON.parse(data_i);
            if (data_i.membe_point != undefined) {
                return data_i.membe_point;
            }
            return 0;
        }catch(e){
            return 0;
        }
    },
    // 增加、减少积分
    SetIntegral: function (i_num) {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        userinfo.membe_point = parseInt(userinfo.membe_point) + parseInt(i_num);
        sessionStorage.setItem("UserInfo", JSON.stringify(userinfo));
    },
    // 唯一ID
    openid: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        
        return userinfo.open_id;
    },
    // 是否关注
    focus_status: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        
        return userinfo.focus_status;
    },
    headimgurl: function () {
        var userinfo = sessionStorage.UserInfo;
        userinfo = JSON.parse(userinfo);
        if (typeof (userinfo.headimgurl) != "undefined" && userinfo.headimgurl != undefined) {
            return userinfo.headimgurl;
        } else {
            return "img/wode.png";
        }
    }
};