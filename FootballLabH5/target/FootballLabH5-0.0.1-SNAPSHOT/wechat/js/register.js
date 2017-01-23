var canRegister = true;

$(function () {
    $(".yzmB").click(function () {
        var mobile = $("#txtMobile").val();
        if ($.trim(mobile).length == 0) {
            tishi_error("请输入手机号码。");
            return;
        }
        if (!mobile.match(/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/)) {
            tishi_error("您输入的手机号码不正确。");
            return;
        }
        GetData(fl_url.sendCode, { phoneNum: mobile });
        timedecline();
    });

    $("#btnRegister").bind("click",function(){register()});
});


function register(){
	
	if(!canRegister){
		return;
	}
	canRegister = false;
    var mobile = $("#txtMobile").val();
    if ($.trim(mobile).length == 0) {
    	canRegister = true;
        tishi_error("请输入手机号码。");
        return;
    }
    if (!mobile.match(/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/)) {
    	canRegister = true;
        tishi_error("您输入的手机号码不正确。");
        return;
    }
    var code = $("#txtCode").val();
    if ($.trim(code).length == 0) {
    	canRegister = true;
        tishi_error("请输入验证码。");
        return;
    }
    
    var token = $.base64.btoa(mobile+code+fl_user.openid()+"&"+(new Date()).getTime(),true);
    var result = GetData(fl_url.login, { phoneNum: mobile, code: code, openId: fl_user.openid(),token:token });//
    

    if (result == "{'msg':'timeOut'}" || result == "{'msg':'errorCode'}") {
    	canRegister = true;
        tishi_error("您输入的验证码有问题。");
        return;
    }
    
    if (result == "{'msg':'repeatRegister'}") {
    	canRegister = true;
    	tishi_error("注册失败。");
    	return;
    }
    
    if(result==""){
    	location.reload();
    }
    
    result = JSON.parse(result);
    if (result.member_id == undefined) {
    	canRegister = true;
        tishi_error("注册失败。");
        return;
    }

    var userinfo = { phone_number: mobile, membe_point: 500, openid: fl_user.openid(), focus_status: fl_user.focus_status() };
    sessionStorage.setItem("UserInfo", JSON.stringify(userinfo));
    var url = location.href;

    //if (url.indexOf("loginOrRegister.html") > 0) {
        if (mobile == "15810566243" || mobile == "13811208406" || mobile == "13911778863" || mobile == "15110148566" || mobile == "13810997529" || mobile == "13487220539" || mobile == "15822504983" || mobile == "13718311498" || mobile == "13683181943" || mobile == "18201124845" || mobile == "15311426335" || mobile == "13311107194" || mobile == "13910125991" || mobile == "13910418937" || mobile == "13901324591" || mobile == "18618419634" || mobile == "13602186070" || mobile == "13488775585" || mobile == "18510168854") {
            alert_error("<lable style=\"color:#000000;font-size:16px;\">恭喜您</lable><br><div style=\"text-align:left;color:#000;line-height:24px;\">获得100M流量，我们将在24小时内充入您的手机里！</div>", "确认", function () {
                location.href = "index.html?experts_code=" + getQueryString("experts_code");
            });
        } else {
            alert_error("<lable style=\"color:#000000;font-size:16px;\">恭喜您</lable><br><div style=\"text-align:center;color:#000;line-height:24px;\">注册成功！</div>", "确认", function () {
                location.href = "index.html?experts_code=" + getQueryString("experts_code");
            });
        }
        return;
    //}

    if (url.indexOf("expertPlan.html") > 0 || url.indexOf("LuckDraw.html") > 0) {
        location.reload();
        return;
    }

    // 跳转到进入页面
    var url = sessionStorage.topurl;

    if (url.indexOf("experts_code") <= 0) {
        url = url + "&experts_code=" + getQueryString("experts_code");
    }

    location.href = url;

}

var s_time = 60;
var time;
function timedecline() {
    s_time = 60;
    $(".yzmB").attr({ "disabled": "disabled" });
    time = setInterval(function () {
        if (s_time <= 0) {
            $(".yzmB").removeAttr("disabled");
            $(".yzmB").text("获取验证码");
            clearInterval(time);
            return;
        }
        $(".yzmB").text("剩余" + s_time + "秒");
        s_time--;
    }, 1000);
}