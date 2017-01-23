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
	
</script>
	</head>
<body>
		<div id="zhongxin">
		<table>
			<tr>
				<td>微信号：${pd.weixin}</td>
			</tr>
			<tr>
				<td>手机号：${pd.phone_number}</td>
			</tr>
			<tr>
				<td>所属专家：${pd.experts_name}</td>
			</tr>
			<tr>
				<td>定制玩法：${pd.service_name}</td>
			</tr>
			<tr>
				<td>定制时间：${pd.transaction_create_time}</td>
			</tr>
			<tr>
				<td>订单号：${pd.transaction_number}</td>
			</tr>
			<tr>
				<td>定制状态：
					<c:if test="${pd.transaction_status == '1' }">成功</c:if>
					<c:if test="${pd.transaction_status == '0' }">失败</c:if>
				</td>
			</tr>
			<tr>
				<td>产品定价：${pd.transaction_amount}</td>
			</tr>
			<tr>
				<td>实付金额：${pd.money_amount}</td>
			</tr>
			<tr>
				<td>交易方式：${pd.remark}</td>
			</tr>
			<tr>
				<td>优惠券使用：${pd.coupon_name}</td>
			</tr>
			<tr>
				<td>积分使用：${pd.consumption_points_number}</td>
			</tr>
			<tr>
				<td>积分获得：${pd.obtain_points_number}</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	
	
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