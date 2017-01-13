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
			if($("#experts_code").val()==""){
			$("#experts_code").tips({
				side:3,
	            msg:'请输入编号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#experts_code").focus();
			return false;
		}
		if($("#experts_name").val()==""){
			$("#experts_name").tips({
				side:3,
	            msg:'请输入专家名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#experts_name").focus();
			return false;
		}
		if($("#app_id").val()==""){
			$("#app_id").tips({
				side:3,
	            msg:'请输入应用id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#app_id").focus();
			return false;
		}
		if($("#app_secret").val()==""){
			$("#app_secret").tips({
				side:3,
	            msg:'请输入应用密钥',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#app_secret").focus();
			return false;
		}
		if($("#wx_num").val()==""){
			$("#wx_num").tips({
				side:3,
	            msg:'请输入微信号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#wx_num").focus();
			return false;
		}
		if($("#partner_id").val()==""){
			$("#partner_id").tips({
				side:3,
	            msg:'微信支付身份id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#partner_id").focus();
			return false;
		}
		if($("#partner_key").val()==""){
			$("#partner_key").tips({
				side:3,
	            msg:'微信支付身份key',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#partner_key").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="experts/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="experts_id" id="experts_id" value="${pd.experts_id}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td>专家编码</td>
				<td><input type="text" name="experts_code" id="experts_code" value="${pd.experts_code}" maxlength="32" placeholder="这里输入专家编码" title="编码"/></td>
			</tr>
			<tr>
				<td>专家名称</td>
				<td><input type="text" name="experts_name" id="experts_name" value="${pd.experts_name}" maxlength="32" placeholder="这里输入专家名称" title="专家名称"/></td>
			</tr>
			<tr>
				<td colspan="2">微信参数</td>
			</tr>
			<tr>
				<td >应用id(app_id)</td>
				<td ><input type="text" name="app_id" id="app_id" value="${pd.app_id}" maxlength="32" placeholder="这里输入应用id" title="应用id"/></td>
			</tr>
			<tr>
				<td >应用密钥(app_secret)</td>
				<td ><input type="text" name="app_secret" id="app_secret" value="${pd.app_secret}" maxlength="32" placeholder="这里输入应用密钥" title="应用密钥"/></td>
			</tr>
			<tr>
				<td >微信号(wx_num)</td>
				<td ><input type="text" name="wx_num" id="wx_num" value="${pd.wx_num}" maxlength="32" placeholder="这里输入微信号" title="微信号"/></td>
			</tr>
			
			<tr>
				<td colspan="2">微信支付参数</td>
			</tr>
			<tr>
				<td >身份id(partner_id)</td>
				<td ><input type="text" name="partner_id" id="partner_id" value="${pd.partner_id}" maxlength="32" placeholder="这里输入身份id" title="身份id"/></td>
			</tr>
			<tr>
				<td >身份key(partner_key)</td>
				<td ><input type="text" name="partner_key" id="partner_key" value="${pd.partner_key}" maxlength="32" placeholder="这里输入身份key" title="身份key"/></td>
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