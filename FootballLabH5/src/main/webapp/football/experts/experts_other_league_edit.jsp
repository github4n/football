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
			if($("#service_name").val()==""){
			$("#service_name").tips({
				side:3,
	            msg:'请输入玩法名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#service_name").focus();
			return false;
		}
		if($("#service_code").val()==""){
			$("#service_code").tips({
				side:3,
	            msg:'请选择玩法类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#service_code").focus();
			return false;
		}
		
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="experts/${updateUrl}" name="Form" id="Form" method="post">
		<input type="hidden" name="service_id" id="service_id" value="${pd.service_id}"/>
		<input type="hidden" name="experts_id" id="experts_id" value="${pd.experts_id}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td>赛事名称</td>
				<td><input type="text" name="service_name" id="service_name" value="${pd.service_name}" maxlength="32" placeholder="玩法名称" title="玩法名称"/></td>
			</tr>
			<tr>
				<td>玩法类型</td>
				<td>
					<select name="service_code" id="service_code">
						<option value="JCSPF" <c:if test="${pd.service_code == 'JCSPF' }">selected</c:if> >竞彩胜平负</option>
						<option value="JCRQSPF" <c:if test="${pd.service_code == 'JCRQSPF' }">selected</c:if> >竞彩让球胜平负</option>
						<option value="JCECY" <c:if test="${pd.service_code == 'JCECY' }">selected</c:if> >竞彩二串一</option>
						<option value="DC" <c:if test="${pd.service_code == 'DC' }">selected</c:if> >单场</option>
						<option value="YP" <c:if test="${pd.service_code == 'YP' }">selected</c:if> >亚盘</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>是否开启</td>
				<td>
					<select name="is_used" id="is_used">
						<option value="0" <c:if test="${pd.is_used == '0' }">selected</c:if> >不开启</option>
						<option value="1" <c:if test="${pd.is_used == '1' }">selected</c:if> >开启</option>
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