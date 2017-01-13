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
	//保存
	function save() {
		if ($("#coupon_name").val() == "") {
			$("#coupon_name").tips({
				side : 3,
				msg : '请输入礼券名称',
				bg : '#AE81FF',
				time : 2
			});
			$("#coupon_name").focus();
			return false;
		}
		if ($("#num").val() == "") {
			$("#num").tips({
				side : 3,
				msg : '请输入张数',
				bg : '#AE81FF',
				time : 2
			});
			$("#num").focus();
			return false;
		}
		var coupon_type_value = $("#coupon_type").val();
		if ((coupon_type_value == '0' || coupon_type_value == '1')
				&& $("#validity_days").val() == "") {
			$()
			$("#validity_days").tips({
				side : 3,
				msg : '请输入兑换服务天数',
				bg : '#AE81FF',
				time : 2
			});
			$("#validity_days").focus();
			return false;
		}
		if (coupon_type_value == '2' && $("#discount").val() == "") {
			$()
			$("#discount").tips({
				side : 3,
				msg : '请输入折扣',
				bg : '#AE81FF',
				time : 2
			});
			$("#validity_days").focus();
			return false;
		}
		if (coupon_type_value == '3' && $("#money").val() == "") {
			$()
			$("#discount").tips({
				side : 3,
				msg : '请输入抵用现金',
				bg : '#AE81FF',
				time : 2
			});
			$("#validity_days").focus();
			return false;
		}

		if ($("#validity_time").val() == "") {
			$("#validity_time").tips({
				side : 3,
				msg : '请输入生效时间',
				bg : '#AE81FF',
				time : 2
			});
			$("#validity_time").focus();
			return false;
		}

		if ($("#invalid_days").val() == "") {
			$("#invalid_days").tips({
				side : 3,
				msg : '请输入失效天数',
				bg : '#AE81FF',
				time : 2
			});
			$("#invalid_days").focus();
			return false;
		}

		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
</script>
</head>
<body>
	<form action="coupon/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="coupon_id" id="coupon_id"
			value="${pd.coupon_id}" />
		<div id="zhongxin">
			<table>
				<tr>
					<td>优惠券种类:</td>
					<td><select name="coupon_type" id="coupon_type"
						onchange="couponTypeChange()">
							<option value="01" <c:if test="${'01' == pd.coupon_type }">selected</c:if>>方案卷-竞彩胜平负</option>
							<option value="02" <c:if test="${'02' == pd.coupon_type }">selected</c:if>>方案卷-竞彩让球胜平负</option>
							<option value="03" <c:if test="${'03' == pd.coupon_type }">selected</c:if>>方案卷-竞彩2串1</option>
							<option value="04" <c:if test="${'04' == pd.coupon_type }">selected</c:if>>方案卷-竞彩让球2串1</option>
							<option value="05" <c:if test="${'05' == pd.coupon_type }">selected</c:if>>方案卷-亚盘</option>
							<option value="06" <c:if test="${'06' == pd.coupon_type }">selected</c:if>>方案卷-单场</option>
							<option value="07" <c:if test="${'07' == pd.coupon_type }">selected</c:if>>方案卷-足彩14场</option>
							<option value="08" <c:if test="${'08' == pd.coupon_type }">selected</c:if>>方案卷-足彩R9</option>
				 			<option value="1" <c:if test="${'1' == pd.coupon_type }">selected</c:if>>通用卷</option>
							<option value="2" <c:if test="${'2' == pd.coupon_type }">selected</c:if>>折扣卷</option>
							<option value="3" <c:if test="${'3' == pd.coupon_type }">selected</c:if>>现金抵扣卷</option>
							<option value="4" <c:if test="${'4' == pd.coupon_type }">selected</c:if>>单联赛通用卷</option>
					</select></td>
				</tr>
				<tr id="validity_days_tr">
					<td>服务天数：</td>
					<td><input type="number" name="validity_days" id="validity_days"
						value="${pd.validity_days}" maxlength="32"
						placeholder="这里输入兑换服务天数" title="兑换服务天数" /></td>
				</tr>
				<tr id="discount_tr" >
					<td>折扣：</td>
					<td><input type="number" name="discount" id="discount"
						value="${pd.discount}" maxlength="32" placeholder="这里输入折扣"
						title="折扣" /></td>
				</tr>
				<tr id="money_tr" >
					<td>抵扣金额：</td>
					<td><input type="number" name="money" id="money"
						value="${pd.money}" maxlength="32" placeholder="这里输入现金抵用金额"
						title="现金抵用金额" /></td>
				</tr>
				<tr>
					<td>礼券名称:</td>
					<td><input type="text" name="coupon_name" id="coupon_name"
						value="${pd.coupon_name}" maxlength="32" placeholder="这里输入礼券名称"
						title="礼券名称" /></td>
				</tr>
				<tr>
					<td>所属专家:</td>
					<td><select name="expert_id" id="expert_id">
							<c:forEach items="${experts}" var="expert">
								<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == pd.expert_id }">selected</c:if> >${expert.EXPERTS_NAME}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>生成张数:</td>
					<td><input type="number" name="num" id="num" value="${pd.num}"
						maxlength="32" placeholder="这里输入张数" title="张数" /></td>
				</tr>

				<tr>
					<td>上线时间:</td>
					<td><input class="span10 date-picker" name="validity_time"
						id="validity_time" value="${pd.validity_time}" type="text"
						data-date-format="yyyy-mm-dd" readonly="readonly"
						placeholder="生效时间" title="生效时间" /></td>
				</tr>

				<tr>
					<td>失效天数:</td>
					<td><input name="invalid_days" id="invalid_days"
						value="${pd.invalid_days}" type="text" placeholder="失效天数"
						title="失效天数" /></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input type="text" name="remark" id="remark"
						value="${pd.remark}" maxlength="32" placeholder="这里输入备注"
						title="备注" /></td>
				</tr>

				<tr>
					<td style="text-align: center;"><c:if
							test="${msg != 'detaile'}">
							<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
						</c:if> <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
					</td>
				</tr>

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

		function couponTypeChange() {
			var coupon_type_value = $("#coupon_type").val();
			if ('2' == coupon_type_value) {
				$("#validity_days_tr").hide();
				$("#discount_tr").show();
				$("#money_tr").hide();
			} else if ('3' == coupon_type_value) {
				$("#validity_days_tr").hide();
				$("#discount_tr").hide();
				$("#money_tr").show();
			}else{
				$("#validity_days_tr").show();
				$("#discount_tr").hide();
				$("#money_tr").hide();
			}
		}
	</script>
</body>
</html>