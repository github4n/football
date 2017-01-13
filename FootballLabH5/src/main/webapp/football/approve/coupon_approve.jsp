<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<script type="text/javascript">
function save(approve_stauts) {
	$("#Form").attr("action","approve/approveData?approve_stauts="+approve_stauts);
	$("#Form").submit();
	$("#zhongxin").hide();
	$("#zhongxin2").show();
}
</script>
</head>
<body>
	<form action="approve/approveData" name="Form" id="Form" method="post">
		<input type="hidden" name="approve_id" id="approve_id"
			value="${pd.approve_id}" />
		<div id="zhongxin">
			<table>
				<tr>
					<td>优惠券种类:</td>
					<td>
					<c:if test="${'01' == couponPd.coupon_type }">方案卷-竞彩胜平负</c:if>
					<c:if test="${'02' == couponPd.coupon_type }">方案卷-竞彩让球胜平负</c:if>
					<c:if test="${'03' == couponPd.coupon_type }">方案卷-竞彩2串1</c:if>
					<c:if test="${'04' == couponPd.coupon_type }">方案卷-竞彩让球2串1</c:if>
					<c:if test="${'05' == couponPd.coupon_type }">方案卷-亚盘</c:if>
					<c:if test="${'06' == couponPd.coupon_type }">方案卷-单场</c:if>
					<c:if test="${'07' == couponPd.coupon_type }">方案卷-足彩14场</c:if>
					<c:if test="${'08' == couponPd.coupon_type }">方案卷-足彩R9</c:if>
					<c:if test="${'1' == couponPd.coupon_type }">通用卷</c:if>
					<c:if test="${'2' == couponPd.coupon_type }">折扣卷</c:if>
					<c:if test="${'3' == couponPd.coupon_type }">现金抵扣卷</c:if>
				</td>
				</tr>
				<c:if test="${'2' != couponPd.coupon_type && '3' != couponPd.coupon_type}">
				<tr id="validity_days_tr">
					<td>服务天数：</td>
					<td>${couponPd.validity_days}</td>
				</tr>
				</c:if>
				<c:if test="${'2' == couponPd.coupon_type }">
				<tr id="discount_tr" >
					<td>折扣：</td>
					<td>${couponPd.discount}</td>
				</tr>
				</c:if>
				
				<c:if test="${'3' == couponPd.coupon_type }">
				<tr id="money_tr" >
					<td>抵扣金额：</td>
					<td>${couponPd.money}</td>
				</tr>
				</c:if>
				<tr>
					<td>所属专家:</td>
					<td>${couponPd.experts_name}</td>
				</tr>

				<tr>
					<td>生成张数:</td>
					<td>${couponPd.num}</td>
				</tr>

				<tr>
					<td>上线时间:</td>
					<td>${couponPd.validity_time}</td>
				</tr>

				<tr>
					<td>失效天数:</td>
					<td>${couponPd.invalid_days}</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td>${couponPd.remark}</td>
				</tr>
				<tr>
					<td>提交人:</td>
					<td>${pd.submit_user}</td>
				</tr>
				<tr>
					<td>提交时间</td>
					<td>${pd.submit_time}</td>
				</tr>

				<c:if test="${'1'==pd.approve_stauts}">
				<tr>
					<td style="text-align: center;">
							<a class="btn btn-mini btn-primary" onclick="save(3);">通过</a>
						    <a class="btn btn-mini btn-danger" onclick="save(2);">不通过</a>
					</td>
				</tr>
				</c:if>
			
				<tr>
				<td> 
					<c:if test="${'2'==pd.approve_stauts}">审核结果：审核不通过</c:if>
					<c:if test="${'3'==pd.approve_stauts}">审核结果：审核通过</c:if>
				</td>
				<tr>
			</table>
		</div>

		<div id="zhongxin2" class="center" style="display: none">
			<br />
			<br />
			<br />
			<br />
			<br />
			<img src="static/images/jiazai.gif" /><br />
			<h4 class="lighter block green">提交中...</h4>
		</div>

	</form>


	<!-- 引入 -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript">
		$(top.hangge());
		$(function() {

			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

			//日期框
			$('.date-picker').datepicker();
			couponTypeChange();
		});
	</script>
</body>
</html>