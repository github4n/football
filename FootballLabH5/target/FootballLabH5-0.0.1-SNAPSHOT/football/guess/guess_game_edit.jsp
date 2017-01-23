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
			url:basePath+'/guess/${type}',
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
	<form action="guess/save" name="Form" id="Form" method="post">
		<input type="hidden" id="id" name="id" value="${guessGame.id}"/>
		<div id="zhongxin" style="padding-left: 20px;padding-top: 20px">
		<table >
			<tr>
				<td>赛事</td>
				<td><input class="mustVal" type="text" name="leagueName" id="leagueName" value="${guessGame.leagueName }" maxlength="32" placeholder="赛事" title="赛事"/></td>
			</tr>
			<tr>
				<td>主队</td>
				<td><input class="mustVal" type="text" name="homeName" id="homeName" value="${guessGame.homeName }" maxlength="32" placeholder="主队" title="主队"/></td>
			</tr>
			<tr>
				<td>客队</td>
				<td><input class="mustVal" type="text" name="awayName" id="awayName" value="${guessGame.awayName }" maxlength="32" placeholder="客队" title="客队"/></td>
			</tr>
			<tr>
				<td>比分</td>
				<td>
					<input type="text" name="homeScore" id="homeScore" value="${guessGame.homeScore }" maxlength="2" style="width:30px" />
					&nbsp;比&nbsp;
					<input type="text" name="awayScore" id="awayScore" value="${guessGame.awayScore }" maxlength="2" style="width:30px" />
				</td>
			</tr>
			<tr>
				<td>胜平负</td>
				<td>
					<input class="mustVal" type="text" name="winOdds" id="winOdds" value="${guessGame.winOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
					<input class="mustVal" type="text" name="drawOdds" id="drawOdds" value="${guessGame.drawOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
					<input class="mustVal" type="text" name="loseOdds" id="loseOdds" value="${guessGame.loseOdds }" maxlength="5" style="width:40px" />
				</td>
			</tr>
			<tr>
				<td style="width:90px">让球胜平负</td>
				<td>
					
					<input class="mustVal" type="text" name="rqWinOdds" id="rqWinOdds" value="${guessGame.rqWinOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
					<input class="mustVal" type="text" name="rqDrawOdds" id="rqDrawOdds" value="${guessGame.rqDrawOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
					<input class="mustVal" type="text" name="rqLoseOdds" id="rqLoseOdds" value="${guessGame.rqLoseOdds }" maxlength="5" style="width:40px" />&nbsp;&nbsp;
					<input class="mustVal" type="text" name="letTheCount" id="letTheCount" value="${guessGame.letTheCount }" maxlength="5" style="width:40px" placeholder="让球" title="让球" />
				</td>
			</tr>
			<tr>
				<td>所属专家</td>
				<td>
					<select class="mustVal" name="expertsId" id="expertsId">
							<option value="">请选择</option>
							<c:forEach items="${experts}" var="expert">
								<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == guessGame.expertsId }">selected</c:if> >${expert.EXPERTS_NAME}</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>上线时间</td>
				<td>
					<input class="mustVal span10 dateTime-picker"  name="onlineTime"
						id="onlineTime" value="<fmt:formatDate  value='${guessGame.onlineTime}' type='both' pattern='yyyy/MM/dd HH:mm' />" type="text"
						data-date-format="yyyy/mm/dd HH:ii" readonly="readonly"
						placeholder="上线时间" title="上线时间" />
				</td>
			</tr>
			<tr>
				<td>比赛时间</td>
				<td>
					<input class="mustVal span10 dateTime-picker" name="gameDateTime"
						id="gameDateTime" value="<fmt:formatDate  value='${guessGame.gameDateTime}' type='both' pattern='yyyy/MM/dd HH:mm' />" type="text"
						data-date-format="yyyy/mm/dd HH:ii" readonly="readonly"
						placeholder="下线时间" title="下线时间" />
				</td>
			</tr>
			<tr>
				<td>是否不中返还</td>
				<td>
					<select class="mustVal" name="isReturn" id="isReturn">
							<option value="">请选择</option>
							<option value="0" <c:if test="${guessGame.isReturn=='false'}">selected</c:if>>不返还</option>
							<option value="1" <c:if test="${guessGame.isReturn=='true'}">selected</c:if>>返还</option>
					</select>
				</td>
			</tr>
			<tr style="height:50px">
				<td style="text-align: center;" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();">提交</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
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
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			$(".dateTime-picker").datetimepicker({format: 'yyyy/mm/dd hh:ii'}); 
			
		});
		
		</script>
</body>
</html>