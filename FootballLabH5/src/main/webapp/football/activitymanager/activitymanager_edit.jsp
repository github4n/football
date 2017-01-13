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
		if ($("#num").val() == "") {
			$("#num").tips({
				side : 3,
				msg : '请输入奖项数量',
				bg : '#AE81FF',
				time : 2
			});
			$("#num").focus();
			return false;
		}
		$("#expert_id").removeAttr("disabled");
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
</script>
</head>
<body>
	<form action="activityM/${msg }" name="Form" id="Form"
		method="post">
		<input type="hidden" name="id" id="id" value="${pd.id}" />
		<div id="zhongxin">
			<table>
				<tr>
					<td>活动项目:</td><%-- ${pd.prize_name} --%>
					<td><input type="text" name="prize_name" id="prize_name"  readonly="readonly"
						value="抽奖" maxlength="32" placeholder="这里输入活动项目"
						title="活动项目" /></td>
				</tr>

				<tr>
					<td>所属专家:</td>
					<td><select name="expert_id" id="expert_id"  <c:if test="${'edit' == msg}">disabled="disabled"</c:if>>
							<c:forEach items="${experts}" var="expert">
								<option value="${expert.EXPERTS_ID}"
									<c:if test="${expert.EXPERTS_ID == pd.fk_experts_id }">selected</c:if>>${expert.EXPERTS_NAME}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>奖项数量:</td><%-- ${pd.prize_name} --%>
					<td><input type="number" name="num" id="num" 
						value="${pd.num}" maxlength="32" placeholder="这里输入奖项数量"
						title="奖项数量" /></td>
				</tr>

				<tr>
					<td style="text-align: center;"><a
						class="btn btn-mini btn-primary" onclick="save();">保存</a> <a
						class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
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

		});
	</script>
</body>
</html>