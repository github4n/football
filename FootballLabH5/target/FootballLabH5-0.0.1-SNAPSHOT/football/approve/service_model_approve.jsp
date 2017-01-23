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
					<td>专家:</td>
					<td>
						${serviceModelPd.experts_name}
					</td>
				</tr>
				<tr >
					<td>玩法：</td>
					<td>${serviceModelPd.service_name}</td>
				</tr>
				<tr>
					<td>模型/模型组名称：</td>
					<td>${serviceModelPd.models_name}</td>
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