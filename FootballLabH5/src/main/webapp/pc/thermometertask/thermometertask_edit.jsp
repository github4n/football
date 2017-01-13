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
		if($("#MEMBER_ID").val()==""){
			$("#MEMBER_ID").tips({
				side:3,
	            msg:'请输入会员ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_ID").focus();
			return false;
		}
		if($("#MEMPHONENUM").val()==""){
			$("#MEMPHONENUM").tips({
				side:3,
	            msg:'请输入会员电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMPHONENUM").focus();
			return false;
		}
		if($("#TASK_DESC").val()==""){
			$("#TASK_DESC").tips({
				side:3,
	            msg:'请输入消息',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TASK_DESC").focus();
			return false;
		}
		if($("#MEMNAME").val()==""){
			$("#MEMNAME").tips({
				side:3,
	            msg:'请输入会员昵称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMNAME").focus();
			return false;
		}
		if($("#ADDTIME").val()==""){
			$("#ADDTIME").tips({
				side:3,
	            msg:'请输入添加时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ADDTIME").focus();
			return false;
		}
		if($("#TASK_STATUS").val()==""){
			$("#TASK_STATUS").tips({
				side:3,
	            msg:'请输入消息状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TASK_STATUS").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="thermometer/${msg}" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="number" name="MEMBER_ID" id="MEMBER_ID" value="${pd.MEMBER_ID}" maxlength="32" placeholder="这里输入会员ID" title="会员ID"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMPHONENUM" id="MEMPHONENUM" value="${pd.MEMPHONENUM}" maxlength="32" placeholder="这里输入会员电话" title="会员电话"/></td>
			</tr>
			<tr>
				<td><input type="text" name="TASK_DESC" id="TASK_DESC" value="${pd.TASK_DESC}" maxlength="32" placeholder="这里输入消息" title="消息"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMNAME" id="MEMNAME" value="${pd.MEMNAME}" maxlength="32" placeholder="这里输入会员昵称" title="会员昵称"/></td>
			</tr>
			<tr>
				<td><input class="span10 date-picker" name="ADDTIME" id="ADDTIME" value="${pd.ADDTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="添加时间" title="添加时间"/></td>
			</tr>
			<tr>
				<td><input type="number" name="TASK_STATUS" id="TASK_STATUS" value="${pd.TASK_STATUS}" maxlength="32" placeholder="这里输入消息状态" title="消息状态"/></td>
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