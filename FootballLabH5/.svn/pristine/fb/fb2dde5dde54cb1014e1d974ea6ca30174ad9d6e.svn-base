function SetHtmlEPByServerCode(service_code, obj, index) {
    var result = "";
    switch (service_code) {
        case "JCECY"://竞彩二串一
        case "JCRQECY":// 竞彩让球二串一
            result = BindECY(obj, index, service_code);
            break;
        case "JCSPF":// 竞彩胜平负
        case "JCRQSPF":// 竞彩让球胜平负
        case "DC":// 单场
            result = BindSPF(obj, index, service_code);
            break;
        case "YP":// 亚盘
            result = BindYP(obj, index, service_code);
            break;
    }
    return result;
}

// 绑定二串一
function BindECY(obj, index, service_code) {
    var html = "";
    var time = GetDateFByTime("hh", obj.time) + ":" + GetDateFByTime("mm", obj.time)

    html += "<div class=\"panel panel-default\">";
    html += "<div class=\"panel-heading\">";
    html += "<h4 class=\"panel-title\">";

    var IsDeFen = true;
    for (var i = 0; i < obj.bettingGameResultTwoOnOneList.length; i++) {
        var h1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_score1;
        var s1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_score1;
        if (h1 == undefined || s1 == undefined) {
            IsDeFen = false;
            //if (sessionStorage.zj_temp == undefined) {
            //    sessionStorage.setItem("zj_temp", obj.num3);
            //    sessionStorage.setItem("yy_temp", obj.num4);
            //} else {
            //    sessionStorage.setItem("zj_temp", (parseInt(sessionStorage.zj_temp) + obj.num3));
            //    sessionStorage.setItem("yy_temp", (parseInt(sessionStorage.yy_temp) + obj.num4));
            //}
            //continue;
            break;
        }
        var h2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_score2;
        var s2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_score2;
        if (h2 == undefined || s2 == undefined) {
            IsDeFen = false;
            //if (sessionStorage.zj_temp == undefined) {
            //    sessionStorage.setItem("zj_temp", obj.num3);
            //    sessionStorage.setItem("yy_temp", obj.num4);
            //} else {
            //    sessionStorage.setItem("zj_temp", (parseInt(sessionStorage.zj_temp) + obj.num3));
            //    sessionStorage.setItem("yy_temp", (parseInt(sessionStorage.yy_temp) + obj.num4));
            //}
            //continue;
            break;
        }
    }

    var n3 = parseInt(obj.num3) + "";
    var n3_t = parseInt(obj.num3);

    if (n3_t < 0) {
        n3_t = "<i style='color:#ddd;font-size:1.2rem;margin-left:0px;'>" + n3_t + "</i>";
    } else {
        n3_t = "<i style='color:#edc33e;font-size:1.2rem;'>" + n3_t + "</i>";
    }
    //if (n3.length == 1) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 2) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 3) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 4) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 5) {
    //    n3 = "&nbsp;&nbsp;" + n3_t;
    //}
    n3 = SetNbsp4(n3,n3_t);



    if (new Date().getHours() < 17 && GetDateDiffDay(getQueryString("gameDate"), GetDataYMD()).day == 0) {
        html += "<a data-toggle=\"collapse\" data-parent=\"#accordion\"  onclick=\"rMs()\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\">预盈:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultTwoOnOneList.length + "个）</a>";
        //if (index == 0) {
            $("#iZj").text("预奖:");
            $("#isYuYing").val("false");
        //}
            if(typeof(followType) != undefined && followType == 1){
            	$("#followOrder").show();
            }
    }
    else if (!IsDeFen) {
        html += "<a data-toggle=\"collapse\" data-parent=\"#accordion\"  onclick=\"rMs()\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\">预盈:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultTwoOnOneList.length + "个）</a>";
        //if (index == 0) {
            $("#iZj").text("预奖:");
            $("#isYuYing").val("false");
        //}
            if(typeof(followType) != undefined && followType == 1){
            	$("#followOrder").show();
            }
    }
    else {
        html += "<a data-toggle=\"collapse\" data-parent=\"#accordion\"  onclick=\"rMs()\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\">盈利:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultTwoOnOneList.length + "个）</a>";
    	$("#iZj").text("中奖: ");
    	
//    	if(typeof(followType) != undefined && followType == 1){
//    		$("#expertsList").show();
//    	}
    }
    

    html += "</h4>";
    html += "</div>";
    html += "<div id=\"collapse" + index + "\" class=\"panel-collapse collapse";
    if (index == 0) {
        html += " in";
        /*if (new Date().getHours() < 17 && GetDateDiffDay(getQueryString("gameDate"), GetDataYMD()).day == 0) {
            $("#lblZTandZJ").text("方案策略17:00发布，请耐心等待！");
            if (getQueryString("clickType") == 1) {
                $("#lblZTandZJ").text("");
            }
        }*/
    }
    html += "\">";
    html += "<div class=\"panel-body panel-body1\">";
    html += "<table class=\"table row tableTib rowTable\">";
    var n = 1;
    var no = "";
    var zjArr = new Array();
    for (var i = 0; i < obj.bettingGameResultTwoOnOneList.length; i++) {

        var releasetype1 = 1;
        var releasetype2 = 1;
        
        releasetype1 = GetReleaseType(obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.game_date_time1);
        releasetype2 = GetReleaseType(obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.game_date_time2);

        if (releasetype1 == 2 || releasetype1 == 3 || location.href.indexOf("myPlan_next_detail") > 0 && getQueryString("clickType") != "2") {
            html += "<tr style=\"border: 1px solid #999\">";
            html += "<th colspan=\"2\" class=\"col-md-2 mcTit\">";
            html += "<div class=\"leftBoxN\">";
            html += "<div class=\"pull-left borderR1\">";

            var game_serial_number1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.game_serial_number1;
            if (game_serial_number1 == undefined) {
                game_serial_number1 = "";
            }
            //var let_the1 = "";
            //if (game_serial_number1.length > 0 && obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count1 != undefined && service_code == "JCRQECY") {
            //    let_the1 = "<br>让球:&nbsp;" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count1;
            //}

            html += "<span>" + game_serial_number1 + "</span><br />";
            html += "<span class=\"colorhui\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.league_name1 + "</span><br />";
            html += "<span>" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_name1.substring(0, 4) + "</span><br />";

            var i_isDeFen = false;
            if (releasetype1 == 2) {
                if (service_code == "JCRQECY") {
                    var let_the_count1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count1;
                    if (let_the_count1 < 0) {
                        let_the_count1 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                    } else {
                        let_the_count1 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                    }
                    html += let_the_count1 + "<span class=\"colorH1\">VS</span><br />";
                } else {
                    html += "<span class=\"colorH1\">VS</span><br />";
                }
                i_isDeFen = true;
            }
            else {
                var h1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_score1;
                var s1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_score1;
                if (h1 == undefined || s1 == undefined) {
                    if (service_code == "JCRQECY") {
                        var let_the_count1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count1;
                        if (let_the_count1 < 0) {
                            let_the_count1 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                        } else {
                            let_the_count1 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                        }
                        html += let_the_count1 + "<span class=\"colorH1\">VS</span><br />";
                    } else {
                        html += "<span class=\"colorH1\">VS</span><br />";
                    }
                    i_isDeFen = true;
                }
                else {
                    var rn1 = "";
                    if (service_code == "JCRQECY") {
                        var let_the_count1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count1;
                        if (let_the_count1 < 0) {
                            let_the_count1 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                        } else {
                            let_the_count1 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                        }
                        rn1 = let_the_count1;
                    }
                    html += "<span class=\"colorH1\">" + rn1 + h1 + ":" + s1 + "</span><br />";
                }
            }
            html += "<span>" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_name1.substring(0, 4) + "</span>";
            html += "</div>";
            html += "<div class=\"pull-right\">";

            var game_serial_number2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.game_serial_number2;
            if (game_serial_number2 == undefined) {
                game_serial_number2 = "";
            }
            //var let_the2 = "";
            //if (game_serial_number2.length > 0 && obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count2 != undefined && service_code == "JCRQECY") {
            //    let_the2 = "<br>让球:&nbsp;" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count2;
            //}

            html += "<span>" + game_serial_number2 + "</span><br />";
            html += "<span class=\"colorhui\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.league_name2 + "</span><br />";
            html += "<span>" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_name2.substring(0, 4) + "</span><br />";
            if (releasetype2 == 2) {
                if (service_code == "JCRQECY") {
                    var let_the_count2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count2;
                    if (let_the_count2 < 0) {
                        let_the_count2 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                    } else {
                        let_the_count2 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                    }
                    html += let_the_count2 + "<span class=\"colorH1\">VS</span><br />";
                }
                else {
                    html += "<span class=\"colorH1\">VS</span><br />";
                }
                i_isDeFen = true;
            }
            else {
                var h2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_score2;
                var s2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_score2;
                if (h2 == undefined || s2 == undefined) {
                    if (service_code == "JCRQECY") {
                        var let_the_count2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count2;
                        if (let_the_count2 < 0) {
                            let_the_count2 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                        } else {
                            let_the_count2 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                        }
                        html += let_the_count2 + "<span class=\"colorH1\">VS</span><br />";
                    }
                    else {
                        html += "<span class=\"colorH1\">VS</span><br />";
                    }
                    i_isDeFen = true;
                }
                else {
                    var rn2 = "";
                    if (service_code == "JCRQECY") {
                        var let_the_count2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count2;
                        if (let_the_count2 < 0) {
                            let_the_count2 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                        } else {
                            let_the_count2 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                        }
                        rn2 = let_the_count2;
                    }
                    html += "<span class=\"colorH1\">" + rn2 + h2 + ":" + s2 + "</span><br />";
                }
            }
            html += "<span>" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_name2.substring(0, 4) + "</span>";
            html += "</div>";
            html += "</div>";
            html += "</th>";
            html += "<th class=\"col-md-9 mcCont\" width=\"50%\" valign=\"top\">";
            html += "<ul>";

            var baSum = 0;
            var html_t = "";
            var result1 = "";
            var result2 = "";
            var odds1 = "";
            var odds2 = "";
            var yj = 0;
            var libg = "";
            zjArr.length = 0;
            for (var j = 0; j < obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList.length; j++) {
                baSum += obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].betting_amount;

                result1 = GetStrategyName(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].strategy_one);
                result2 = GetStrategyName(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].strategy_two);

                //if (service_code == "JCRQECY") {
                //    result1 = "让" + result1 + ",";
                //    result2 = "让" + result2;
                //}
                if (service_code == "JCRQECY") {
                    result1 = "<b style=\"color:#666;font-size:1.2rem;font-weight:bold;\">让</b>" + result1;
                }

                odds1 = GetOdds(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].strategy_one, 1, obj.bettingGameResultTwoOnOneList[i].companyOddsTwoOnOne);
                odds2 = GetOdds(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].strategy_two, 2, obj.bettingGameResultTwoOnOneList[i].companyOddsTwoOnOne);

                zjArr.push(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].betting_amount * odds1 * odds2);

                if (obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].isWin == "1") {
                    yj = obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].betting_amount * odds1 * odds2;
                    libg = " class=\"colorH\"";
                }
                else
                    libg = "";

                var amount = "";
                amount = SetNbsp4(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].betting_amount);
                html_t += "<li " + libg + "><span class=\"pull-right\">投入:" + amount + "</span>" + result1 + result2 + "(" + (odds2 * odds1).toFixed(2) + ")</li>";
            }

            // 如果没有出成绩，获取中奖金额最大值
            if (yj == 0 && releasetype2 == 2 || i_isDeFen) {
                yj = Math.max.apply(null, zjArr)
            }
            var i_df_text = "中";
            if (i_isDeFen) {
                i_df_text = "预";
            }
            var yj_f_c = "";
            if (yj == 0) {
                yj_f_c = "color:#666;";
            }
            html += "<li style=\"border-bottom: 1px solid #ccc;\"><span class=\"pull-right\"><i style=\"font-size:1.2rem;" + yj_f_c + "\">" + i_df_text + "奖:" + SetNbsp4(parseInt(yj)) + "</i></span><i>投入:" + baSum + "</i></li>";
            html += html_t;
            html += "</ul>";
            html += "</th>";
            html += "</tr>";
        } else {
            if (i == 0)
                html += "<style>.rightMore{display:none;}</style>";
            if (i > 0) {
                html += "<table class=\"table row tableTib rowTable\">";
            }
            html += "<tr>";

            var game_serial_number1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.game_serial_number1;
            if (game_serial_number1 == undefined) {
                game_serial_number1 = "";
            }

            html += "<th class=\"col-md-1\">" + game_serial_number1 + "</th>";
            var game_serial_number2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.game_serial_number2;
            if (game_serial_number2 == undefined) {
                game_serial_number2 = "";
            }
            html += "<th class=\"col-md-1\">" + game_serial_number2 + "</th>";
            html += "<th class=\"col-md-2\">&nbsp;</th>";
            html += "<th class=\"col-md-8\" width=\"48%\">初步预测</th>";
            html += "</tr>";
            html += "<tr>";
            html += "<td class=\"col-md-1\" style=\"color: #666;\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.league_name1 + "</td>";
            html += "<td class=\"col-md-1\" style=\"color: #666;\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.league_name2 + "</td>";
            html += "<td class=\"col-md-2\">&nbsp;</td>";
            html += "<td class=\"col-md-8  col-md-8left\" rowspan=\"4\">";
            html += "<table class=\"table shengpng\">";
            var c1 = "", c4 = "", c7 = "";
            var c2 = "", c5 = "", c8 = "";
            var c3 = "", c6 = "", c9 = "";
            for (var j = 0; j < obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList.length; j++) {
                
                var result1 = GetStrategyName(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].strategy_one);
                var result2 = GetStrategyName(obj.bettingGameResultTwoOnOneList[i].bettingStrategyForTwoOnOneList[j].strategy_two);
                if (result1 == "胜" && result2 == "胜")
                    c1 = "class=\"colorRed\"";
                else if (result1 == "胜" && result2 == "平")
                    c2 = "class=\"colorRed\"";
                else if (result1 == "胜" && result2 == "负")
                    c3 = "class=\"colorRed\"";
                else if (result1 == "平" && result2 == "胜")
                    c4 = "class=\"colorRed\"";
                else if (result1 == "平" && result2 == "平")
                    c5 = "class=\"colorRed\"";
                else if (result1 == "平" && result2 == "负")
                    c6 = "class=\"colorRed\"";
                else if (result1 == "负" && result2 == "胜")
                    c7 = "class=\"colorRed\"";
                else if (result1 == "负" && result2 == "平")
                    c8 = "class=\"colorRed\"";
                else if (result1 == "负" && result2 == "负")
                    c9 = "class=\"colorRed\"";
            }
            var r_t = "";
            if (service_code == "JCRQECY") {
                r_t = "<b style=\"color:#666;font-size:1.2rem;font-weight:bold;\">让</b>";
            }

            html += "<tr>";
            html += "<td " + c1 + " style=\"padding:4px;\">" + r_t + "胜-胜</td>";
            html += "<td " + c2 + " style=\"padding:4px;\">" + r_t + "胜-平</td>";
            html += "<td " + c3 + " style=\"padding:4px;\">" + r_t + "胜-负</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td " + c4 + " style=\"padding:4px;\">" + r_t + "平-胜</td>";
            html += "<td " + c5 + " style=\"padding:4px;\">" + r_t + "平-平</td>";
            html += "<td " + c6 + " style=\"padding:4px;\">" + r_t + "平-负</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td " + c7 + " style=\"padding:4px;\">" + r_t + "负-胜</td>";
            html += "<td " + c8 + " style=\"padding:4px;\">" + r_t + "负-平</td>";
            html += "<td " + c9 + " style=\"padding:4px;\">" + r_t + "负-负</td>";
            html += "</tr>";
            html += "</table>";
            html += "</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td class=\"col-md-1\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_name1.substring(0, 4) + "</td>";
            html += "<td class=\"col-md-1\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.home_name2.substring(0, 4) + "</td>";
            html += "<td class=\"col-md-2\">&nbsp;</td>";
            html += "</tr>";
            html += "<tr>";
            var r_t1 = "";
            var r_t2 = "";
            if (service_code == "JCRQECY") {
                var let_the_count1 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count1;
                if (let_the_count1 < 0) {
                    let_the_count1 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                } else {
                    let_the_count1 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count1 + ")</span>";
                }
                var let_the_count2 = obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.let_the_count2;
                if (let_the_count2 < 0) {
                    let_the_count2 = "<span style='color:green;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                } else {
                    let_the_count2 = "<span style='color:red;font-size:1.2rem;'>(" + let_the_count2 + ")</span>";
                }
                r_t1 = let_the_count1;
                r_t2 = let_the_count2;
            }
            html += "<td class=\"col-md-1\">" + r_t1 + "<b>vs</b></td>";
            html += "<td class=\"col-md-1\">" + r_t2 + "<b>vs</b></td>";
            html += "<td class=\"col-md-2\">&nbsp;</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td class=\"col-md-1\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_name1.substring(0, 4) + "</td>";
            html += "<td class=\"col-md-1\">" + obj.bettingGameResultTwoOnOneList[i].bettingGameTwoOnOne.away_name2.substring(0, 4) + "</td>";
            html += "<td class=\"col-md-2\">&nbsp;</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td class=\"col-md-1\">&nbsp;</td>";
            html += "<td class=\"col-md-1\">&nbsp;</td>";
            html += "<td class=\"col-md-2\">&nbsp;</td>";
            html += "<td class=\"col-md-8\">&nbsp;</td>";
            html += "</tr>";

            if (i < obj.bettingGameResultTwoOnOneList.length - 1)
                html += "</table>";
        }
    }
    html += "</table>";
    html += "</div>";
    html += "</div>";
    html += "</div>";

    return html;
}

// 绑定胜平负
function BindSPF(obj, index, service_code) {
    var html = "";
    var time = GetDateFByTime("hh", obj.time) + ":" + GetDateFByTime("mm", obj.time);
    html += "<div class=\"panel panel-default\">";
    html += "<div class=\"panel-heading\">";
    html += "<h4 class=\"panel-title\">";
    html += "<a data-toggle=\"collapse\"  onclick=\"rMs()\" data-parent=\"#accordion\"";
    html += "href=\"#collapse" + index + "\"><span></span>";

    // 验证是否是预盈
    var isBiFen = true;
    for (var n = 0; n < obj.bettingGameResultList.length; n++) {
        var home_score = obj.bettingGameResultList[n].bettingGame.home_score;
        var away_score = obj.bettingGameResultList[n].bettingGame.away_score;
        if (home_score == undefined || away_score == undefined) {
            isBiFen = false;
            //if (sessionStorage.zj_temp == undefined) {
            //    sessionStorage.setItem("zj_temp", obj.num3);
            //    sessionStorage.setItem("yy_temp", obj.num4);
            //} else {
            //    sessionStorage.setItem("zj_temp", (parseInt(sessionStorage.zj_temp) + obj.num3));
            //    sessionStorage.setItem("yy_temp", (parseInt(sessionStorage.yy_temp) + obj.num4));
            //}
            break;
        }
    }

    var n3 = parseInt(obj.num3) + "";
    var n3_t = parseInt(obj.num3);
    if (n3_t < 0) {
        n3_t = "<i style='color:#ddd;font-size:1.2rem;margin-left:0px;'>" + n3_t + "</i>";
    } else {
        n3_t = "<i style='color:#edc33e;font-size:1.2rem;'>" + n3_t + "</i>";
    }
    //if (n3.length == 1) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 2) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 3) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 4) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 5) {
    //    n3 = "&nbsp;&nbsp;" + n3_t;
    //}
    n3 = SetNbsp4(n3,n3_t);


    if (new Date().getHours() < 17 && GetDateDiffDay(getQueryString("gameDate"), GetDataYMD()).day == 0 && getQueryString("service_code") != "DC") {
        html += "<i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">预盈:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultList.length + "个）</a>";
        if (index == 0) {
            $("#iZj").text("预奖:");
            $("#isYuYing").val("false");
            
            if(typeof(followType) != undefined && followType == 1){
            	$("#followOrder").show();
            }
        }
    }
    //有比分，显示盈利
    else if (obj.bettingGameResultList[0] != undefined && obj.bettingGameResultList[0].bettingGame.home_score!= undefined && isBiFen) {
       html += "<i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">盈利:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultList.length + "个）</a>";
       $("#iZj").text("中奖: ");
       
//       if(typeof(followType) != undefined && followType == 1){
//   		$("#expertsList").show();
//       }
    }
    else if (obj.bettingGameResultList[0] != undefined && GetReleaseType(obj.bettingGameResultList[0].bettingGame.game_date_time) != 3) {
        html += "<i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">预盈:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultList.length + "个）</a>";
         $("#iZj").text("预奖: ");
         $("#isYuYing").val("false");
         
         if(typeof(followType) != undefined && followType == 1){
         	$("#followOrder").show();
         }
//      if (index == 0) {
//          $("#iZj").text("预奖:");
//      }
    }
    else if (!isBiFen) {
        html += "<i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">预盈:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultList.length + "个）</a>";
		 $("#iZj").text("预奖: ");
		 $("#isYuYing").val("false");
		 
		 if(typeof(followType) != undefined && followType == 1){
         	$("#followOrder").show();
         }
//      if (index == 0) {
//          $("#iZj").text("预奖:");
//      }
    }
    else {
        html += "<i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">盈利:<em>" + n3 + "</em></i>" + time + "（" + obj.bettingGameResultList.length + "个）</a>";
    	$("#iZj").text("中奖: ");
    	
//    	if(typeof(followType) != undefined && followType == 1){
//    		$("#expertsList").show();
//    	}
    }
    html += "</h4>";
    html += "</div>";
    html += "<div id=\"collapse" + index + "\" class=\"panel-collapse collapse";
    var d_n_diff = GetDateDiffDay(obj.time, GetDateYMDHMS());
    if (index == 0) {
        html += " in";
        if (service_code == "JCRQSPF" || service_code == "JCSPF") {
            if (new Date().getHours() < 17 && d_n_diff.day <= 0 && d_n_diff.hours <= 0 && d_n_diff.minutes <= 0) {
                $("#lblZTandZJ").text("方案策略17:00发布，请耐心等待！");
                if (getQueryString("clickType") == 1) {
                    $("#lblZTandZJ").text("");
                }
            }
        }
    }
    if (service_code == "DC") {
    	//只要有没比分的，就显示方案策略赛前半小时发布，请耐心等待
    	if(obj.bettingGameResultList[0].bettingGame.home_score== undefined){
    	    //$("#lblZTandZJ").text("方案策略赛前半小时发布，请耐心等待！");
    	    if (getQueryString("clickType") == 1) {
    	        $("#lblZTandZJ").text("");
    	    }
    	}
        //if (d_n_diff.day <= 0 && d_n_diff.hours <= 0 && d_n_diff.minutes <= 30) {
        //    $("#lblZTandZJ").text("方案策略赛前半小时发布，请耐心等待！");
        //}
    }

    html += "\">";
    html += "<div class=\"panel-body panel-body1\">";
    html += "<table class=\"table row tableTib rowTable\">";

    var strategy = "";
    var zjArr = new Array();
    for (var i = 0; i < obj.bettingGameResultList.length; i++) {

        var releasetype = 1;
        releasetype = GetReleaseType(obj.bettingGameResultList[i].bettingGame.game_date_time);
        
        //如果有比分，本场比赛已经完赛
        if(obj.bettingGameResultList[i].bettingGame.home_score!=undefined){
        	releasetype=3;
        }
        //比赛未开始，显示pk
        if(releasetype==1 &&  service_code == "DC"){
        	 $("#aPKbtn").show();
        }
        if (releasetype == 2 || releasetype == 3 || location.href.indexOf("myPlan_next_detail") > 0 && getQueryString("clickType") != "2") {
            html += "<tr style=\"border-bottom: 1px solid #a9aeb9;\">";
            html += "<th colspan=\"2\" class=\"col-md-2 mcTit\">";
            html += "<div style=\"border-right: 1px solid #ccc; height: 60px; padding-top: 7px;\">";

            var game_serial_number = obj.bettingGameResultList[i].bettingGame.game_serial_number;
            if (game_serial_number == undefined) {
                game_serial_number = "";
            }
            var let_the = "";
            if (game_serial_number.length > 0 && obj.bettingGameResultList[i].bettingGame.let_the_count != undefined && service_code == "JCRQSPF") {
                var let_the_count = obj.bettingGameResultList[i].bettingGame.let_the_count;
                if (let_the_count < 0) {
                    let_the_count = "<span style='color:green;font-size:1.2rem;'>" + let_the_count + "</span>";
                } else {
                    let_the_count = "<span style='color:red;font-size:1.2rem;'>" + let_the_count + "</span>";
                }
                let_the = "&nbsp;让球:&nbsp;" + let_the_count;
            }
            if (service_code == "DC") {
                game_serial_number = "";
            }
            html += "<span style=\"display: -webkit-box;\">" + game_serial_number + "&nbsp;" + obj.bettingGameResultList[i].bettingGame.league_name + let_the + "</span>";
            html += "<p class=\"rightText\">" + obj.bettingGameResultList[i].bettingGame.home_name.substring(0, 4) + "<i>&nbsp;";

            var i_IsBenFen = false;
            if (releasetype == 2) {
                html += "VS";
                i_IsBenFen = true;
            } else {
                var home_score = obj.bettingGameResultList[i].bettingGame.home_score;
                var away_score = obj.bettingGameResultList[i].bettingGame.away_score;
                if (home_score == undefined || away_score == undefined) {
                    html += "VS";
                    i_IsBenFen = true;
                } else {
                    html += home_score + ":" + away_score;
                }
            }
            html += "&nbsp;</i>" + obj.bettingGameResultList[i].bettingGame.away_name.substring(0, 4) + "</p>";
            html += "</div>";
            html += "</th>";
            html += "<th class=\"col-md-9 mcCont\" width=\"50%\" valign=\"top\">";
            html += "<ul>";

            var html_t = "";
            var odds, zj = 0, tz = 0;
            var libg = "";
            zjArr.length = 0;
            for (var j = 0; j < obj.bettingGameResultList[i].bettingStrategyForSingleList.length; j++) {
                tz += obj.bettingGameResultList[i].bettingStrategyForSingleList[j].betting_amount;
                odds = GetOdds(obj.bettingGameResultList[i].bettingStrategyForSingleList[j].strategy, 0, obj.bettingGameResultList[i].companyOdds);

                // 计算中奖，算法，成绩出来的，算猜中的，成绩没出来，算最高的
                zjArr.push(obj.bettingGameResultList[i].bettingStrategyForSingleList[j].betting_amount * odds);
                // 当前状态，未发布、已发布、已完赛
                strategy = GetStrategyName(obj.bettingGameResultList[i].bettingStrategyForSingleList[j].strategy);
                //if (service_code == "JCRQSPF") {
                //    strategy = "让" + strategy;
                //}
                if (service_code == "JCRQSPF") {
                    strategy = "<b style=\"color:#666;font-size:1.2rem;font-weight:bold;\">让</b>" + strategy;
                }

                // 验证是否中奖
                if (obj.bettingGameResultList[i].bettingStrategyForSingleList[j].isWin == "1") {
                    zj = obj.bettingGameResultList[i].bettingStrategyForSingleList[j].betting_amount * odds;
                    libg = " class=\"colorH\"";
                }
                else
                    libg = "";

                html_t += "<li " + libg + "><span class=\"pull-right\">投入:" + SetNbsp4(obj.bettingGameResultList[i].bettingStrategyForSingleList[j].betting_amount) + "</span>" + strategy + "(" + odds.toFixed(2) + ")</li>";
            }

            // 如果没有出成绩，获取中奖金额最大值
            if (zj == 0 && releasetype == 2 || i_IsBenFen) {
                zj = Math.max.apply(null, zjArr)
            }

            var zj_text = "中";
            if (i_IsBenFen) {
                zj_text = "预";
            }
            var yj_f_c = "";
            if (zj == 0) {
                yj_f_c = "color:#666;";
            }
            html += "<li style=\"border-bottom: 1px solid #ccc;\"><span class=\"pull-right\"><i style=\"font-size:1.2rem;" + yj_f_c + "\">" + zj_text + "奖:" + SetNbsp4(parseInt(zj)) + "</i></span><i>投入:" + parseInt(tz) + "</i></li>";
            html += html_t;
            if (obj.bettingGameResultList[i].bettingStrategyForSingleList.length <= 1) {
                html += "<li><span class=\"pull-right\">&nbsp;</li>";
            }

            html += "</ul>";
            html += "</th>";	
            html += "</tr>";
        } else {
            html += "<style>#rightMore" + index + "{display:none;}</style>";

            html += "<tr style=\"border:1px solid #999\">";
            html += "<th colspan=\"2\" class=\"col-md-2 mcTit\">";

            var game_serial_number = obj.bettingGameResultList[i].bettingGame.game_serial_number;
            if (game_serial_number == undefined) {
                game_serial_number = "";
            }
            var let_the = "";
            if (game_serial_number.length > 0 && obj.bettingGameResultList[i].bettingGame.let_the_count != undefined && service_code == "JCRQSPF") {
                var let_the_count = obj.bettingGameResultList[i].bettingGame.let_the_count;
                if (let_the_count < 0) {
                    let_the_count = "<span style='color:green;font-size:1.2rem;'>" + let_the_count + "</span>";
                } else {
                    let_the_count = "<span style='color:red;font-size:1.2rem;'>" + let_the_count + "</span>";
                }
                let_the = "&nbsp;让球:&nbsp;" + let_the_count;
            }
            if (service_code == "DC") {
                game_serial_number = "";
            }
            html += "<span class=\"rightText\" style=\"display: -webkit-box;\">" + game_serial_number + "&nbsp;" + obj.bettingGameResultList[i].bettingGame.league_name + let_the + "</span>";

            //html += "<span class=\"rightText\">" + obj.bettingGameResultList[i].bettingGame.league_name + "</span>";
            html += "<p  class=\"rightText\" sytle='border-bottom: 1px solid #000;'>" + obj.bettingGameResultList[i].bettingGame.home_name.substring(0, 4) + "<i>&nbsp;VS&nbsp;</i>" + obj.bettingGameResultList[i].bettingGame.away_name.substring(0, 4) + "</p>";
            html += "</th>";
            html += "<th class=\"col-md-9 mcTit\" width=\"30%\" valign=\"top\">";
            html += "<span class=\"leftText\" style='text-align:center;'>初步预测</span>";
            html += "<p class=\"colorH leftText\" style='text-align:center;'>";
            for (var j = 0; j < obj.bettingGameResultList[i].bettingStrategyForSingleList.length; j++) {
                if (j > 0) {
                    html += "、";
                }
                html += GetStrategyName(obj.bettingGameResultList[i].bettingStrategyForSingleList[j].strategy);
            }
            html += "</p>";
            html += "</th>";
            html += "</tr>";
        }
    }
    html += "</table>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    return html;
}

// 绑定亚盘
function BindYP(obj, index, service_code) {
    var html = "";
    var time = GetDateFByTime("hh", obj.time) + ":" + GetDateFByTime("mm", obj.time);
    html += "<div class=\"panel panel-default\">";
    html += "<div class=\"panel-heading\">";
    html += "<h4 class=\"panel-title\">";

    var isFenShu = false;
    for (var i = 0; i < obj.bettingGameResultAsiansList.length; i++) {
        var home_score = obj.bettingGameResultAsiansList[i].bettingGame.home_score;
        var away_score = obj.bettingGameResultAsiansList[i].bettingGame.away_score;
        if (home_score == undefined || away_score == undefined) {
            isFenShu = true;
            //if (sessionStorage.zj_temp == undefined) {
            //    sessionStorage.setItem("zj_temp", obj.num3);
            //    sessionStorage.setItem("yy_temp", obj.num4);
            //} else {
            //    sessionStorage.setItem("zj_temp", (parseInt(sessionStorage.zj_temp) + obj.num3));
            //    sessionStorage.setItem("yy_temp", (parseInt(sessionStorage.yy_temp) + obj.num4));
            //}
            break;
        }
    }

    var n3 = parseInt(obj.num3) + "";
    var n3_t = parseInt(obj.num3);
    if (n3_t < 0) {
        n3_t = "<i style='color:#ddd;font-size:1.2rem;margin-left:0px;'>" + n3_t + "</i>";
    } else {
        n3_t = "<i style='color:#edc33e;font-size:1.2rem;'>" + n3_t + "</i>";
    }
    //if (n3.length == 1) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 2) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 3) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 4) {
    //    n3 = "&nbsp;&nbsp;&nbsp;&nbsp;" + n3_t;
    //} else if (n3.length == 5) {
    //    n3 = "&nbsp;&nbsp;" + n3_t;
    //}
    n3 = SetNbsp4(n3,n3_t);


    if (obj.bettingGameResultAsiansList[0] != undefined && GetReleaseType(obj.bettingGameResultAsiansList[0].bettingGame.game_date_time) != 3) {
        html += "<a data-toggle=\"collapse\"  onclick=\"rMs()\" data-parent=\"#accordion\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">预盈:<em>" + n3 + "</em></i>";
        /*if (index == 0) {*/
            $("#iZj").text("预奖:");
            $("#isYuYing").val("false");
        /*}*/
            
            if(typeof(followType) != undefined && followType == 1){
             	$("#followOrder").show();
             }
    }
    //有比分，显示盈利
    else if (obj.bettingGameResultAsiansList[0] != undefined && obj.bettingGameResultAsiansList[0].bettingGame.home_score != undefined && !isFenShu) {
        html += "<a data-toggle=\"collapse\"  onclick=\"rMs()\" data-parent=\"#accordion\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">盈利:<em>" + n3 + "</em></i>";
        $("#iZj").text("中奖:");
        
//        if(typeof(followType) != undefined && followType == 1){
//    		$("#expertsList").show();
//    	}
    }
    else if (isFenShu) {
        html += "<a data-toggle=\"collapse\"  onclick=\"rMs()\" data-parent=\"#accordion\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">预盈:<em>" + n3 + "</em></i>";
        /*if (index == 0) {*/
            $("#iZj").text("预奖:");
            $("#isYuYing").val("false");
        /*}*/
            
            if(typeof(followType) != undefined && followType == 1){
             	$("#followOrder").show();
             }
    }
    else {
        html += "<a data-toggle=\"collapse\"  onclick=\"rMs()\" data-parent=\"#accordion\" href=\"#collapse" + index + "\"><span></span><i class=\"pull-right rightMore\" id=\"rightMore" + index + "\">盈利:<em>" + n3 + "</em></i>";
    }
    html += time + "（" + obj.bettingGameResultAsiansList.length + "个）</a>";
    html += "</h4>";
    html += "</div>";
    html += "<div id=\"collapse" + index + "\" class=\"panel-collapse collapse";

    var d_n_diff = GetDateDiffDay(obj.time, GetDateYMDHMS());
    if (index == 0) {
        html += " in";
    }
    if (d_n_diff.day <= 0 && d_n_diff.hours <= 0 && d_n_diff.minutes <= 30) {
        //$("#lblZTandZJ").text("方案策略赛前半小时发布，请耐心等待！");
        if (getQueryString("clickType") == 1) {
            $("#lblZTandZJ").text("");
        }
    }

    html += "\">";
    html += "<div class=\"panel-body panel-body12 panel-body1\">";
    html += "<table class=\"table row tableTib rowTable\">";

    var zjArr = new Array();
    for (var i = 0; i < obj.bettingGameResultAsiansList.length; i++) {

        var releasetype = 1;
        releasetype = GetReleaseType(obj.bettingGameResultAsiansList[i].bettingGame.game_date_time);
        
        //如果有比分，本场比赛已经完赛
        if(obj.bettingGameResultAsiansList[i].bettingGame.home_score!=undefined){
        	releasetype=3;
        }
        if (releasetype == 2 || releasetype == 3 || location.href.indexOf("myPlan_next_detail") > 0 && getQueryString("clickType") != "2") {

            html += "<tr style=\"border-bottom: 1px solid #999;\">";
            html += "<th colspan=\"2\" class=\"col-md-2 mcTit\">";
            html += "<div style=\"border-right:1px solid #ccc;height: 77px;\">";
            html += "<span class=\"rightText\">" + obj.bettingGameResultAsiansList[i].bettingGame.league_name + "</span>";
            html += "<p class=\"rightText\">" + obj.bettingGameResultAsiansList[i].bettingGame.home_name.substring(0, 4);

            var i_isFenShu = false;
            if (releasetype == 2) {
                html += "<i>&nbsp;VS&nbsp;</i>";
                i_isFenShu = true;
            }
            else {
                var home_score = obj.bettingGameResultAsiansList[i].bettingGame.home_score;
                var away_score = obj.bettingGameResultAsiansList[i].bettingGame.away_score;
                if (home_score == undefined || away_score == undefined) {
                    html += "<i>&nbsp;VS&nbsp;</i>";
                    i_isFenShu = true;
                }
                else {
                    html += "<i>&nbsp;" + home_score + ":" + away_score + "&nbsp;</i>";
                }
            }
            html += obj.bettingGameResultAsiansList[i].bettingGame.away_name.substring(0, 4) + "</p>";
            html += "</div>";
            html += "</th>";
            html += "<th class=\"col-md-9 mcCont\" width=\"50%\" valign=\"top\">";
            html += "<ul>";


            var html_t = "";
            var zj = 0;
            var tz = 0;
            zjArr.length = 0;
            if (obj.bettingGameResultAsiansList[i].bettingStrategyForAsianList.length > 0) {
                // 获取投入
                tz = obj.bettingGameResultAsiansList[i].bettingStrategyForAsianList[0].betting_amount;

                var strategy_xx = obj.bettingGameResultAsiansList[i].bettingStrategyForAsianList[0].strategy;
                var name_xx = "";
                if (strategy_xx == 3) {
                    name_xx = obj.bettingGameResultAsiansList[i].bettingGame.home_name.substring(0, 4) + "(" + obj.bettingGameResultAsiansList[i].companyOddsAsian.win_odds.toFixed(2) + ")";

                } else {
                    name_xx = obj.bettingGameResultAsiansList[i].bettingGame.away_name.substring(0, 4) + "(" + obj.bettingGameResultAsiansList[i].companyOddsAsian.lose_odds.toFixed(2) + ")";
                }
                // 中奖
                //zjArr.push(obj.bettingGameResultAsiansList[i].num3 + obj.bettingGameResultAsiansList[i].num4);
                zj = parseInt(obj.bettingGameResultAsiansList[i].num3 + obj.bettingGameResultAsiansList[i].num4);
                //html_t += "<li>盘口:" + GetStrategyName(obj.bettingGameResultAsiansList[i].bettingStrategyForAsianList[0].strategy) + "</li>";
                html_t += "<li>盘口:" + obj.bettingGameResultAsiansList[i].companyOddsAsian.handicap_name + "</li>";
                html_t += "<li>推荐:" + name_xx + "</li>";
            }

            //// 如果没有出成绩，获取中奖金额最大值
            //if (zj == 0 && releasetype == 2) {
            //    zj = Math.max.apply(null, zjArr)
            //}

            var zhongjiang_text = "中";
            if (i_isFenShu) {
                zhongjiang_text = "预";
            }
            var yj_f_c = "";
            if (zj == 0) {
                yj_f_c = "color:#666;";
            }
            html += "<li style=\"border-bottom: 1px solid #ccc;\"><span class=\"pull-right\"><i style=\"font-size:1.2rem;" + yj_f_c + "\">" + zhongjiang_text + "奖:" + parseInt(zj) + "</i></span><i>投入:" + tz + "</i></li>";
            html += html_t;

            html += "</ul>";
            html += "</th>";
            html += "</tr>";
        } else {
            html += "<style>#rightMore" + index + "{display:none;}</style>";
            html += "<tr style=\"border:1px solid #999\">";
            html += "<th colspan=\"2\" class=\"col-md-2 mcTit\">";
            html += "<span class=\"rightText\">" + obj.bettingGameResultAsiansList[i].bettingGame.league_name + "</span>";
            html += "<p  class=\"rightText\">" + obj.bettingGameResultAsiansList[i].bettingGame.home_name.substring(0, 4) + "<i>&nbsp;" + obj.bettingGameResultAsiansList[i].companyOddsAsian.handicap_name + "&nbsp;</i>" + obj.bettingGameResultAsiansList[i].bettingGame.away_name.substring(0, 4) + "</p>";
            html += "</th>";
            html += "<th class=\"col-md-9 mcTit\" width=\"40%\" valign=\"top\">";
            html += "<span class=\"leftText\">初步预测</span>";

            var strategy_xx = obj.bettingGameResultAsiansList[i].bettingStrategyForAsianList[0].strategy;
            var name_xx = "";
            if (strategy_xx == 3) {
                name_xx = obj.bettingGameResultAsiansList[i].bettingGame.home_name.substring(0, 4);

            } else {
                name_xx = obj.bettingGameResultAsiansList[i].bettingGame.away_name.substring(0, 4);
            }

            html += "<p class=\"colorH\" style=\"text-align: right;\">" + name_xx + "</p>";
            html += "</th>";
            html += "</tr>";
        }
    }

    html += "</table>";
    html += "</div>";
    html += "</div>";
    html += "</div>";

    return html;
}

function SetNbsp4(n3,n_3_t) {
    n3 += "";
    var n3_t_int = parseInt(n3);
    if (n_3_t == undefined) {
        n_3_t = n3;
    }

    if (n3.length == 1) {
        n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n_3_t;
        n_3_t = n3;
    } else if (n3.length == 2) {
        n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n_3_t;
        n_3_t = n3;
    } else if (n3.length == 3) {
        n3 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + n_3_t;
        n_3_t = n3;
    } else if (n3.length == 4) {
        n3 = "&nbsp;&nbsp;&nbsp;&nbsp;" + n_3_t;
        n_3_t = n3;
    } else if (n3.length == 5) {
        n3 = "&nbsp;&nbsp;" + n_3_t;
        n_3_t = n3;
    }
    if (n3_t_int < 0) {
        n3 = "&nbsp;" + n_3_t;
    }
    return n3;
}

// 获取胜负
function GetStrategyName(strategy) {
    switch (strategy) {
        case 0:
            strategy = "负";
            break;
        case 1:
            strategy = "平";
            break;
        case 3:
            strategy = "胜";
            break;
    }
    return strategy;
}

// 获取赔率
function GetOdds(strategy, c, obj) {
    var result = "";
    if (c == 0) {
        switch (strategy) {
            case 0:
                result = obj.lose_odds;
                break;
            case 1:
                result = obj.draw_odds;
                break;
            case 3:
                result = obj.win_odds;
                break;
        }
    } else {
        switch (strategy) {
            case 0:
                result = c == 1 ? obj.lose_odds1 : obj.lose_odds2;
                break;
            case 1:
                result = c == 1 ? obj.draw_odds1 : obj.draw_odds2;
                break;
            case 3:
                result = c == 1 ? obj.win_odds1 : obj.win_odds2;
                break;
        }
    }
    return result;
}

//// 获取发布类型，1未发布，2已发布，3，已完赛
//function GetReleaseType(BeginTime) {
//  var result = 1;
//  var serverCode = getQueryString("service_code");
//  var date = GetDateYMDHMS();
//  var newDiffData = GetDateDiffDay(getQueryString("gameDate"), GetDataYMD());
//
//  // 如果时间相同，则判断是1还是2
//  if (newDiffData.day == 0) {
//      switch (serverCode) {
//          case "JCECY"://竞彩二串一
//          case "JCRQECY":// 竞彩让球二串一
//          case "JCSPF":// 竞彩胜平负
//          case "JCRQSPF":// 竞彩让球胜平负
//              var h = GetDateFByTime("hh", date);
//              if (h < 17) {
//                  result = 1;
//              } else if (h < 19) {
//                  result = 2;
//              } else {
//                  result = 3;
//              }
//              break;
//          case "DC":// 单场
//          case "YP":// 亚盘
//              var diff = GetDateDiffDay(date, BeginTime);
//              if (diff.hours > 0 || diff.minutes > 30) {
//                  result = 1;
//              } else {
//                  result = 2;
//              }
//              break;
//      }
//
//  }
//  else if (newDiffData.day < 0) {
//      result = 1;
//  }
//  else {
//      result = 3;
//      switch (serverCode) {
//         
//          case "DC":// 单场
//          case "YP":// 亚盘
//              var diff = GetDateDiffDay(date, BeginTime);
//          if (diff.day == 0 && diff.hours==0 && diff.minutes > 30 || diff.hours>0) {
//              result = 1;
//          } else if (diff.day == 0 && diff.hours == 0 && diff.minutes <= 30) {
//              result = 2;
//          }
//      }
//  }
//
//  return result;
//}

// 获取发布类型，1未发布，2已发布，3，已完赛
function GetReleaseType(BeginTime) {
    var serverCode = getQueryString("service_code");
    var gameDate = getQueryString("gameDate");
    var date=GetDateYMDHMS();
    
	var minutesJX="";//比赛进行时间
	var minutesFB="";//比赛发布时间
	if(serverCode=="DC" || serverCode=="YP"){
	    minutesFB =GetDateDiffMinutes(date,BeginTime);
		if(minutesFB>30){
			result = 2;
		}else if(minutesFB>=-120 && minutesFB<=30){
			result = 2;
		}else if(minutesFB<-120){
			result = 3
		}
	}else{
		/*var minutesFB =GetDateDiffMinutes(date,gameDate+" 17:00:00");
		if(minutesFB>0){
			result = 1;
		}else if(minutesFB<=0){
			result = 2;
		}*/
		result = 2;
		//if(serverCode!="JCECY" && serverCode!="JCRQECY"){
		var minutesJX =GetDateDiffMinutes(date,BeginTime);
		if(minutesJX<-120){
			result = 3
		}
		//}

	}
	
       
    return result;
}