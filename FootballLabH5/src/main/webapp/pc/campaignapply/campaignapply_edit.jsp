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
			if($("#CAMPAIGN_ID").val()==""){
			$("#CAMPAIGN_ID").tips({
				side:3,
	            msg:'请输入活动ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CAMPAIGN_ID").focus();
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
		if($("#MEMBER_NAME").val()==""){
			$("#MEMBER_NAME").tips({
				side:3,
	            msg:'请输入会员姓名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_NAME").focus();
			return false;
		}
		if($("#MEMBER_PHONE").val()==""){
			$("#MEMBER_PHONE").tips({
				side:3,
	            msg:'请输入会员电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_PHONE").focus();
			return false;
		}
		if($("#MEMBER_VILLAGE_NAME").val()==""){
			$("#MEMBER_VILLAGE_NAME").tips({
				side:3,
	            msg:'请输入小区/楼盘名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_VILLAGE_NAME").focus();
			return false;
		}
		if($("#MEMBER_HOU_AREA").val()==""){
			$("#MEMBER_HOU_AREA").tips({
				side:3,
	            msg:'请输入房屋面积',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_HOU_AREA").focus();
			return false;
		}
		if($("#MEMBER_HOU_TYPE").val()==""){
			$("#MEMBER_HOU_TYPE").tips({
				side:3,
	            msg:'请输入装修类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_HOU_TYPE").focus();
			return false;
		}
		if($("#MEMBER_HOU_STYLE").val()==""){
			$("#MEMBER_HOU_STYLE").tips({
				side:3,
	            msg:'请输入装修风格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_HOU_STYLE").focus();
			return false;
		}
		if($("#MEMBER_REMARK").val()==""){
			$("#MEMBER_REMARK").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_REMARK").focus();
			return false;
		}
		if($("#STATUS").val()==""){
			$("#STATUS").tips({
				side:3,
	            msg:'请输入状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATUS").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="campaignapply/pc/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="number" name="CAMPAIGN_ID" id="CAMPAIGN_ID" value="${pd.CAMPAIGN_ID}" maxlength="32" placeholder="这里输入活动ID" title="活动ID"/></td>
			</tr>
			<tr>
				<td><input type="text" name="CAMPAIGN_TITLE" id="CAMPAIGN_TITLE" value="${pd.CAMPAIGN_TITLE}" maxlength="32" placeholder="这里输入活动标题" title="活动标题"/></td>
			</tr>
			<tr>
				<td><input type="number" name="MEMBER_ID" id="MEMBER_ID" value="${pd.MEMBER_ID}" maxlength="32" placeholder="这里输入会员ID" title="会员ID"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_NAME" id="MEMBER_NAME" value="${pd.MEMBER_NAME}" maxlength="32" placeholder="这里输入会员姓名" title="会员姓名"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_PHONE" id="MEMBER_PHONE" value="${pd.MEMBER_PHONE}" maxlength="32" placeholder="这里输入会员电话" title="会员电话"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_VILLAGE_NAME" id="MEMBER_VILLAGE_NAME" value="${pd.MEMBER_VILLAGE_NAME}" maxlength="32" placeholder="这里输入小区/楼盘名称" title="小区/楼盘名称"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_HOU_AREA" id="MEMBER_HOU_AREA" value="${pd.MEMBER_HOU_AREA}" maxlength="32" placeholder="这里输入房屋面积" title="房屋面积"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_HOU_TYPE" id="MEMBER_HOU_TYPE" value="${pd.MEMBER_HOU_TYPE}" maxlength="32" placeholder="这里输入装修类型" title="装修类型"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_HOU_STYLE" id="MEMBER_HOU_STYLE" value="${pd.MEMBER_HOU_STYLE}" maxlength="32" placeholder="这里输入装修风格" title="装修风格"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_REMARK" id="MEMBER_REMARK" value="${pd.MEMBER_REMARK}" maxlength="32" placeholder="这里输入备注" title="备注"/></td>
			</tr>
			<tr>
				<td>
					<select name="STATUS" title="状态">
					<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >未受理</option>
					<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >已受理</option>
					</select>
				</td>
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