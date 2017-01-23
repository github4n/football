<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<link rel="stylesheet" href="static/css/bootstrap-datetimepicker.min.css" /><!-- 日期框 -->
		
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	var basePath = '<%=basePath%>';
	//保存
	function save(){
	
		var mustValFlag = true;
	
		$(".mustVal").each(function(){
		    if($(this).val()==""){
		    	$(this).tips({
					side:3,
		            msg:'必填项，请输入',
		            bg:'#AE81FF',
		            time:2
		        });
				
				$(this).focus();
				mustValFlag=false;
				return false;
		    }
		});
		if(!mustValFlag){
			return;
		}
		
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		var flag;
		$.ajax({
			url:basePath+'/otherLeague/${type}/${serviceCode}',
			type:'post',
			async:false,
			data:$("#Form").serializeArray(),
			success:function(data){
				flag = data.flag;
			}
		});
		
		if(flag){
			top.Dialog.close();
		}else{
			alert("保存失败！");
		}
		
	}
	
</script>
	</head>
<body>
	<form action="otherLeague/save" name="Form" id="Form" method="post">
		<input type="hidden" id="serviceCode" name="serviceCode" value="${serviceCode}"/>
		<input type="hidden" id="id" name="id" value="${data.id}"/>
		<div id="zhongxin" style="padding-left: 20px;padding-top: 20px">
			<c:choose>
				<c:when test="${serviceCode=='DC' }">
					<input type="hidden" id="gameId" name="gameInfo.gameId" value="${data.gameInfo.gameId}"/>
					<table id="DC">
						<tr>
							<td style="width:80px">所属专家</td>
							<td>
								<select class="mustVal" name="expertId" id="expertId" onchange="javascript:getServcieList(this.value)">
										<c:forEach items="${experts}" var="expert">
											<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == data.expertId }">selected</c:if> >${expert.EXPERTS_NAME}</option>
										</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>类型</td>
							<td>
								<select class="mustVal" name="serviceId" id="serviceId">
									<option value="" >请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>玩法</td>
							<td>
								<c:if test="${serviceCode=='DC'}">
									<input type="text"  value="单场" disabled/>
								</c:if>
								<c:if test="${serviceCode=='YP'}">
									<input type="text"  value="亚盘" disabled/>
								</c:if>
								<c:if test="${serviceCode=='JCECY'}">
									<input type="text"  value="二串一" disabled/>
								</c:if>
								
							</td>
						</tr>
						<tr>
							<td>编号</td>
							<td>
								<input class="" type="text" name="gameInfo.serialNumber" id="serialNumber" value="${data.gameInfo.serialNumber }" maxlength="32" placeholder="编号" title="编号"/>
							</td>
						</tr>
						<tr>
							<td>赛事</td>
							<td>
								<input class="mustVal" type="text" name="gameInfo.leagueName" id="leagueName" value="${data.gameInfo.leagueName }" maxlength="32" placeholder="赛事" title="赛事"/>
								<input type="hidden" name="gameInfo.leagueId" id="leagueId" value="99"/>
							</td>
						</tr>
						<tr>
							<td>主队</td>
							<td><input class="mustVal" type="text" name="gameInfo.homeName" id="homeName" value="${data.gameInfo.homeName }" maxlength="32" placeholder="主队" title="主队"/></td>
						</tr>
						<tr>
							<td>客队</td>
							<td><input class="mustVal" type="text" name="gameInfo.awayName" id="awayName" value="${data.gameInfo.awayName }" maxlength="32" placeholder="客队" title="客队"/></td>
						</tr>
						<tr>
							<td>比分</td>
							<td>
								<input type="text" name="gameInfo.homeScore" id="homeScore" value="${data.gameInfo.homeScore }" maxlength="2" style="width:30px" />
								&nbsp;比&nbsp;
								<input type="text" name="gameInfo.awayScore" id="awayScore" value="${data.gameInfo.awayScore }" maxlength="2" style="width:30px" />
							</td>
						</tr>
						<tr >
							<td>胜</td>
							<td>
								赔率&nbsp;&nbsp;<input class="mustVal" type="text" name="oddsInfo.winOdds" id="winOdds" value="${data.oddsInfo.winOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								投入&nbsp;&nbsp;<input class="" type="text" name="winAmount" id="winAmount" value="${data.winAmount }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>平</td>
							<td>
								赔率&nbsp;&nbsp;<input class="mustVal" type="text" name="oddsInfo.drawOdds" id="drawOdds" value="${data.oddsInfo.drawOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								投入&nbsp;&nbsp;<input class="" type="text" name="drawAmount" id="drawAmount" value="${data.drawAmount }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>负</td>
							<td>
								赔率&nbsp;&nbsp;<input class="mustVal" type="text" name="oddsInfo.loseOdds" id="loseOdds" value="${data.oddsInfo.loseOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								投入&nbsp;&nbsp;<input class="" type="text" name="loseAmount" id="loseAmount" value="${data.loseAmount }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>比赛时间</td>
							<td>
								<input class="mustVal span10 dateTime-picker" name="gameInfo.gameDateTime"
									id="gameDateTime" value="<fmt:formatDate  value='${data.gameInfo.gameDateTime}' type='both' pattern='yyyy/MM/dd HH:mm' />" type="text"
									data-date-format="yyyy/mm/dd HH:ii" readonly="readonly"
									placeholder="比赛时间" title="比赛时间" />
							</td>
						</tr>
						<tr style="height:50px">
							<td style="text-align: center;" colspan="2">
								<a class="btn btn-mini btn-primary" onclick="save();">提交</a>
								<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
							</td>
						</tr>
				</table>
				</c:when>
				<c:when test="${serviceCode=='YP' }">
					<input type="hidden" id="gameId" name="gameInfo.gameId" value="${data.gameInfo.gameId}"/>
					<table id="YP">
						<tr>
							<td style="width:80px">所属专家</td>
							<td>
								<select class="mustVal" name="expertId" id="expertId" onchange="javascript:getServcieList(this.value)">
										<c:forEach items="${experts}" var="expert">
											<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == data.expertId}">selected</c:if> >${expert.EXPERTS_NAME}</option>
										</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>类型</td>
							<td>
								<select class="mustVal" name="serviceId" id="serviceId">
									<option value="" >请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>玩法</td>
							<td>
								<c:if test="${serviceCode=='DC'}">
									<input type="text"  value="单场" disabled/>
								</c:if>
								<c:if test="${serviceCode=='YP'}">
									<input type="text"  value="亚盘" disabled/>
								</c:if>
								<c:if test="${serviceCode=='JCECY'}">
									<input type="text"  value="二串一" disabled/>
								</c:if>
								
							</td>
						</tr>
						<tr>
							<td>编号</td>
							<td>
								<input class="" type="text" name="gameInfo.serialNumber" id="serialNumber" value="${data.gameInfo.serialNumber }" maxlength="32" placeholder="编号" title="编号"/>
							</td>
						</tr>
						<tr>
							<td>赛事</td>
							<td>
								<input class="mustVal" type="text" name="gameInfo.leagueName" id="leagueName" value="${data.gameInfo.leagueName }" maxlength="32" placeholder="赛事" title="赛事"/>
								<input type="hidden" name="gameInfo.leagueId" id="leagueId" value="99"/>
							</td>
						</tr>
						<tr>
							<td>主队</td>
							<td><input class="mustVal" type="text" name="gameInfo.HomeName" id="HomeName" value="${data.gameInfo.homeName }" maxlength="32" placeholder="主队" title="主队"/></td>
						</tr>
						<tr>
							<td>客队</td>
							<td><input class="mustVal" type="text" name="gameInfo.awayName" id="awayName" value="${data.gameInfo.awayName }" maxlength="32" placeholder="客队" title="客队"/></td>
						</tr>
						<tr>
							<td>比分</td>
							<td>
								<input type="text" name="gameInfo.homeScore" id="homeScore" value="${data.gameInfo.homeScore }" maxlength="2" style="width:30px" />
								&nbsp;比&nbsp;
								<input type="text" name="gameInfo.awayScore" id="awayScore" value="${data.gameInfo.awayScore }" maxlength="2" style="width:30px" />
							</td>
						</tr>
						<tr >
							<td>盘口</td>
							<td>
								<select class="mustVal" name="oddsInfo.handicap" id="handicap">
									<option value="" >请选择</option>
									<c:forEach items="${handicapMap}" var="handicap">
										<option value="${handicap.key}" <c:if test="${handicap.key == data.oddsInfo.handicap}">selected</c:if>>${handicap.value}</option>
									</c:forEach>
								</select>
								
							</td>
						</tr>
						<tr>
							<td>主队赢</td>
							<td>
								水位&nbsp;&nbsp;<input class="mustVal" type="text" name="oddsInfo.winOdds" id="winOdds" value="${data.oddsInfo.winOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								投入&nbsp;&nbsp;<input class="" type="text" name="winAmount" id="winAmount" value="${data.winAmount }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>客队赢</td>
							<td>
								水位&nbsp;&nbsp;<input class="mustVal" type="text" name="oddsInfo.loseOdds" id="loseOdds" value="${data.oddsInfo.loseOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								投入&nbsp;&nbsp;<input class="" type="text" name="loseAmount" id="loseAmount" value="${data.loseAmount }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>比赛时间</td>
							<td>
								<input class="mustVal span10 dateTime-picker" name="gameInfo.gameDateTime"
									id="gameDateTime" value="<fmt:formatDate  value='${data.gameInfo.gameDateTime}' type='both' pattern='yyyy/MM/dd HH:mm' />" type="text"
									data-date-format="yyyy/mm/dd HH:ii" readonly="readonly"
									placeholder="比赛时间" title="比赛时间" />
							</td>
						</tr>
						<tr style="height:50px">
							<td style="text-align: center;" colspan="2">
								<a class="btn btn-mini btn-primary" onclick="save();">提交</a>
								<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
							</td>
						</tr>
					</table>
					
				</c:when>
				<c:when test="${serviceCode=='JCECY' }">
					<input type="hidden" id="gameIdOne" name="gameInfoOne.gameId" value="${data.gameInfoOne.gameId}"/>
					<input type="hidden" id="gameIdTwo" name="gameInfoTwo.gameId" value="${data.gameInfoTwo.gameId}"/>
					<input type="hidden" id="twoOnOneMatchId" name="twoOnOneMatchId" value="${data.twoOnOneMatchId}"/>
					<table id="JCECY">
						<tr>
							<td style="width:80px">所属专家</td>
							<td>
								<select class="mustVal" name="expertId" id="expertId" onchange="javascript:getServcieList(this.value)">
										<c:forEach items="${experts}" var="expert">
											<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == data.expertId}">selected</c:if> >${expert.EXPERTS_NAME}</option>
										</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>类型</td>
							<td>
								<select class="mustVal" name="serviceId" id="serviceId">
									<option value="" >请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>玩法</td>
							<td>
								<c:if test="${serviceCode=='DC'}">
									<input type="text"  value="单场" disabled/>
								</c:if>
								<c:if test="${serviceCode=='YP'}">
									<input type="text"  value="亚盘" disabled/>
								</c:if>
								<c:if test="${serviceCode=='JCECY'}">
									<input type="text"  value="二串一" disabled/>
								</c:if>
								
							</td>
						</tr>
						<tr>
							<td>赛事1</td>
							<td>
								<input class="mustVal" type="text" name="gameInfoOne.serialNumber" id="serialNumberOne" value="${data.gameInfoOne.serialNumber }" maxlength="32" placeholder="编号" title="编号" style="width:92px;"/>&nbsp;
								<input class="mustVal" type="text" name="gameInfoOne.leagueName" id="leagueNameOne" value="${data.gameInfoOne.leagueName }" maxlength="32" placeholder="赛事" title="赛事" style="width:92px;"/>
								<input type="hidden" name="gameInfoOne.leagueId" id="leagueIdOne" value="99"/>
							</td>
						</tr>
						<tr>
							<td>第一场</td>
							<td>
								<input class="mustVal" type="text" name="gameInfoOne.homeName" id="homeNameOne" value="${data.gameInfoOne.homeName }" maxlength="32" placeholder="主队" title="主队" style="width:150px;"/>
								VS
								<input class="mustVal" type="text" name="gameInfoOne.awayName" id="awayNameOne" value="${data.gameInfoOne.awayName }" maxlength="32" placeholder="客队" title="客队" style="width:150px;"/>
							</td>
						</tr>
						<tr>
							<td>赔率</td>
							<td>
								<input class="mustVal" type="text" name="oddsInfoOne.winOdds" id="winOddsOne" value="${data.oddsInfoOne.winOdds}" maxlength="5" style="width:40px" />&nbsp;&nbsp;
								<input class="mustVal" type="text" name="oddsInfoOne.drawOdds" id="drawOddsOne" value="${data.oddsInfoOne.drawOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
								<input class="mustVal" type="text" name="oddsInfoOne.loseOdds" id="loseOddsOne" value="${data.oddsInfoOne.loseOdds }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>比分</td>
							<td>
								<input type="text" name="gameInfoOne.homeScore" id="homeScoreOne" value="${data.gameInfoOne.homeScore }" maxlength="2" style="width:30px" />
								&nbsp;比&nbsp;
								<input type="text" name="gameInfoOne.awayScore" id="awayScoreOne" value="${data.gameInfoOne.awayScore }" maxlength="2" style="width:30px" />
							</td>
						</tr>
						<tr>
							<td>赛事2</td>
							<td>
								<input class="mustVal" type="text" name="gameInfoTwo.serialNumber" id="serialNumberTwo" value="${data.gameInfoTwo.serialNumber }" maxlength="32" placeholder="编号" title="编号" style="width:92px;" />&nbsp;
								<input class="mustVal" type="text" name="gameInfoTwo.leagueName" id="leagueNameTwo" value="${data.gameInfoTwo.leagueName }" maxlength="32" placeholder="赛事" title="赛事" style="width:92px;"/>
								<input type="hidden" name="gameInfoTwo.leagueId" id="leagueIdTwo" value="99"/>
							</td>
						</tr>
						<tr>
							<td>第二场</td>
							<td>
								<input class="mustVal" type="text" name="gameInfoTwo.homeName" id="homeNameTwo" value="${data.gameInfoTwo.homeName }" maxlength="32" placeholder="主队" title="主队" style="width:150px;"/>
								VS
								<input class="mustVal" type="text" name="gameInfoTwo.awayName" id="awayNameTwo" value="${data.gameInfoTwo.awayName }" maxlength="32" placeholder="客队" title="客队" style="width:150px;"/>
							</td>
						</tr>
						<tr>
							<td>赔率</td>
							<td>
								<input class="mustVal" type="text" name="oddsInfoTwo.winOdds" id="winOddsTwo" value="${data.oddsInfoTwo.winOdds}" maxlength="5" style="width:40px" />&nbsp;&nbsp;
								<input class="mustVal" type="text" name="oddsInfoTwo.drawOdds" id="drawOddsTwo" value="${data.oddsInfoTwo.drawOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
								<input class="mustVal" type="text" name="oddsInfoTwo.loseOdds" id="loseOddsTwo" value="${data.oddsInfoTwo.loseOdds }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>比分</td>
							<td>
								<input type="text" name="gameInfoTwo.homeScore" id="homeScoreTwo" value="${data.gameInfoTwo.homeScore }" maxlength="2" style="width:30px" />
								&nbsp;比&nbsp;
								<input type="text" name="gameInfoTwo.awayScore" id="awayScoreTwo" value="${data.gameInfoTwo.awayScore }" maxlength="2" style="width:30px" />
							</td>
						</tr>
						<tr >
							<td>胜胜</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="winWinAmount" id="winWinAmount" value="${data.winWinAmount }" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>胜平</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="winDrawAmount" id="winDrawAmount" value="${data.winDrawAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>胜负</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="winLoseAmount" id="winLoseAmount" value="${data.winLoseAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>平胜</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="drawWinAmount" id="drawWinAmount" value="${data.drawWinAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>平平</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="drawDrawAmount" id="drawDrawAmount" value="${data.drawDrawAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>平负</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="drawLoseAmount" id="drawLoseAmount" value="${data.drawLoseAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>负胜</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="loseWinAmount" id="loseWinAmount" value="${data.loseWinAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>负平</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="loseDrawAmount" id="loseDrawAmount" value="${data.loseDrawAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>负负</td>
							<td>
								投入&nbsp;&nbsp;<input class="" type="text" name="loseLoseAmount" id="loseLoseAmount" value="${data.loseLoseAmount}" maxlength="5" style="width:40px" />
							</td>
						</tr>
						<tr>
							<td>比赛时间（最先开赛场）</td>
							<td>
								<input class="mustVal span10 dateTime-picker" name="gameInfoOne.gameDateTime"
									id="gameDateTime" value="<fmt:formatDate  value='${data.gameInfoOne.gameDateTime}' type='both' pattern='yyyy/MM/dd HH:mm' />" type="text"
									data-date-format="yyyy/mm/dd HH:ii" readonly="readonly"
									placeholder="比赛时间" title="比赛时间" style="width:220px" />
							</td>
						</tr>
						<tr style="height:50px">
							<td style="text-align: center;" colspan="2">
								<a class="btn btn-mini btn-primary" onclick="save();">提交</a>
								<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
							</td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="static/js/datetimepicker/bootstrap-datetimepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="static/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script><!-- 日期框 -->

<script type="text/javascript">
	$(top.hangge());
	var serviceCode;
	$(function() {
	
		serviceCode = $("#serviceCode").val();
		getServcieList($("#expertId option:selected").val());
	
		//单选框
		$(".chzn-select").chosen(); 
		$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
		
		//日期框
		$('.date-picker').datepicker();
		$(".dateTime-picker").datetimepicker({format: 'yyyy/mm/dd hh:ii'}); 
		
	});
	
	function getServcieList(expertId){
		
		var serviceList = {};
		
		$.ajax({
	        type: "post",
	        url: '<%=basePath%>/otherLeague/getServiceList?expertId='+expertId+'&serviceCode='+serviceCode,
	        async: false,
	        datatype: "json",
	        success: function (data) {
	        	serviceList = data.list;
	        },
	    });
	    
	    var html = "";
	    html+="<option value='' >请选择</option>"
	    for(i in serviceList){
	    	if(serviceList[i].id=='${data.serviceId}'){
	    		html+="<option value='"+serviceList[i].id+"' selected>"+serviceList[i].service_name+"</option>";
	    	}else{
	    		html+="<option value='"+serviceList[i].id+"'>"+serviceList[i].service_name+"</option>";
	    	}
	    	
	    }
	    $("#serviceId").empty();
	    $("#serviceId").html(html);
	}

</script>
</body>
</html>