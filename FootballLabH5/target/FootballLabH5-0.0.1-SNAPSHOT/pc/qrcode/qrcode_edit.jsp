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
			if($("#ID").val()==""){
			$("#ID").tips({
				side:3,
	            msg:'请输入主键',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ID").focus();
			return false;
		}
		if($("#SCANRESULT").val()==""){
			$("#SCANRESULT").tips({
				side:3,
	            msg:'请输入扫描结果',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SCANRESULT").focus();
			return false;
		}
		if($("#SCANTIME").val()==""){
			$("#SCANTIME").tips({
				side:3,
	            msg:'请输入扫描时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SCANTIME").focus();
			return false;
		}
		if($("#MEMBERID").val()==""){
			$("#MEMBERID").tips({
				side:3,
	            msg:'请输入扫描人',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBERID").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="qrcode/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="QRCODE_ID" id="QRCODE_ID" value="${pd.QRCODE_ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="number" name="ID" id="ID" value="${pd.ID}" maxlength="32" placeholder="这里输入主键" title="主键"/></td>
			</tr>
			<tr>
				<td><input type="text" name="SCANRESULT" id="SCANRESULT" value="${pd.SCANRESULT}" maxlength="32" placeholder="这里输入扫描结果" title="扫描结果"/></td>
			</tr>
			<tr>
				<td><input class="span10 date-picker" name="SCANTIME" id="SCANTIME" value="${pd.SCANTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="扫描时间" title="扫描时间"/></td>
			</tr>
			<tr>
				<td><input type="number" name="MEMBERID" id="MEMBERID" value="${pd.MEMBERID}" maxlength="32" placeholder="这里输入扫描人" title="扫描人"/></td>
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