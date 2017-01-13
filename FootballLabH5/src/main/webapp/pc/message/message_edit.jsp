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
		if($("#MESSAGE_ID").val()==""){
			$("#MESSAGE_ID").tips({
				side:3,
	            msg:'请输入消息ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_ID").focus();
			return false;
		}
		if($("#MESSAGE_DESC").val()==""){
			$("#MESSAGE_DESC").tips({
				side:3,
	            msg:'请输入消息描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_DESC").focus();
			return false;
		}
		if($("#MESSAGE_PUBLISH_TIME").val()==""){
			$("#MESSAGE_PUBLISH_TIME").tips({
				side:3,
	            msg:'请输入发布时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_PUBLISH_TIME").focus();
			return false;
		}
		if($("#MESSAGE_STATUS").val()==""){
			$("#MESSAGE_STATUS").tips({
				side:3,
	            msg:'请输入消息状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_STATUS").focus();
			return false;
		}
		if($("#MESSAGE_TITLE").val()==""){
			$("#MESSAGE_TITLE").tips({
				side:3,
	            msg:'请输入消息标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_TITLE").focus();
			return false;
		}
		if($("#MESSAGE_PUBLISHER_ID").val()==""){
			$("#MESSAGE_PUBLISHER_ID").tips({
				side:3,
	            msg:'请输入发布者ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_PUBLISHER_ID").focus();
			return false;
		}
		if($("#MESSAGE_PUBLISHER_NAME").val()==""){
			$("#MESSAGE_PUBLISHER_NAME").tips({
				side:3,
	            msg:'请输入发布者姓名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MESSAGE_PUBLISHER_NAME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="message/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="MESSAGE_ID" id="MESSAGE_ID" value="${pd.MESSAGE_ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="number" name="ID" id="ID" value="${pd.ID}" maxlength="32" placeholder="这里输入主键" title="主键"/></td>
			</tr>
			<tr>
				<td><input type="number" name="MEMBER_ID" id="MEMBER_ID" value="${pd.MEMBER_ID}" maxlength="32" placeholder="这里输入会员ID" title="会员ID"/></td>
			</tr>
			<tr>
				<td><input type="number" name="MESSAGE_ID" id="MESSAGE_ID" value="${pd.MESSAGE_ID}" maxlength="32" placeholder="这里输入消息ID" title="消息ID"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MESSAGE_DESC" id="MESSAGE_DESC" value="${pd.MESSAGE_DESC}" maxlength="32" placeholder="这里输入消息描述" title="消息描述"/></td>
			</tr>
			<tr>
				<td><input class="span10 date-picker" name="MESSAGE_PUBLISH_TIME" id="MESSAGE_PUBLISH_TIME" value="${pd.MESSAGE_PUBLISH_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="发布时间" title="发布时间"/></td>
			</tr>
			<tr>
				<td><input type="number" name="MESSAGE_STATUS" id="MESSAGE_STATUS" value="${pd.MESSAGE_STATUS}" maxlength="32" placeholder="这里输入消息状态" title="消息状态"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MESSAGE_TITLE" id="MESSAGE_TITLE" value="${pd.MESSAGE_TITLE}" maxlength="32" placeholder="这里输入消息标题" title="消息标题"/></td>
			</tr>
			<tr>
				<td><input type="number" name="MESSAGE_PUBLISHER_ID" id="MESSAGE_PUBLISHER_ID" value="${pd.MESSAGE_PUBLISHER_ID}" maxlength="32" placeholder="这里输入发布者ID" title="发布者ID"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MESSAGE_PUBLISHER_NAME" id="MESSAGE_PUBLISHER_NAME" value="${pd.MESSAGE_PUBLISHER_NAME}" maxlength="32" placeholder="这里输入发布者姓名" title="发布者姓名"/></td>
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