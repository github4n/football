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
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#TRANSACTION_NUMBER").val()==""){
			$("#TRANSACTION_NUMBER").tips({
				side:3,
	            msg:'请输入订单号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TRANSACTION_NUMBER").focus();
			return false;
		}
		if($("#TRANSACTION_AMOUNT").val()==""){
			$("#TRANSACTION_AMOUNT").tips({
				side:3,
	            msg:'请输入订单金额',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TRANSACTION_AMOUNT").focus();
			return false;
		}
		if($("#SERVICE_START_TIME").val()==""){
			$("#SERVICE_START_TIME").tips({
				side:3,
	            msg:'请输入服务开始时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SERVICE_START_TIME").focus();
			return false;
		}
		if($("#SERVICE_END_TIME").val()==""){
			$("#SERVICE_END_TIME").tips({
				side:3,
	            msg:'请输入服务结束时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SERVICE_END_TIME").focus();
			return false;
		}
		if($("#TRANSACTION_STATUS").val()==""){
			$("#TRANSACTION_STATUS").tips({
				side:3,
	            msg:'请输入订单状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TRANSACTION_STATUS").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="transaction/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="TRANSACTION_ID" id="TRANSACTION_ID" value="${pd.TRANSACTION_ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="text" name="TRANSACTION_NUMBER" id="TRANSACTION_NUMBER" value="${pd.TRANSACTION_NUMBER}" maxlength="32" placeholder="这里输入订单号" title="订单号"/></td>
			</tr>
			<tr>
				<td><input type="text" name="TRANSACTION_AMOUNT" id="TRANSACTION_AMOUNT" value="${pd.TRANSACTION_AMOUNT}" maxlength="32" placeholder="这里输入订单金额" title="订单金额"/></td>
			</tr>
			<tr>
				<td><input class="span10 date-picker" name="SERVICE_START_TIME" id="SERVICE_START_TIME" value="${pd.SERVICE_START_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="服务开始时间" title="服务开始时间"/></td>
			</tr>
			<tr>
				<td><input class="span10 date-picker" name="SERVICE_END_TIME" id="SERVICE_END_TIME" value="${pd.SERVICE_END_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="服务结束时间" title="服务结束时间"/></td>
			</tr>
			<tr>
				<td><input type="number" name="TRANSACTION_STATUS" id="TRANSACTION_STATUS" value="${pd.TRANSACTION_STATUS}" maxlength="32" placeholder="这里输入订单状态" title="订单状态"/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
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
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>