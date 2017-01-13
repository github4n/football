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
	
//检索
function searchData(){
	top.jzts();
	$("#SearchForm").submit();
}
	
</script>
	</head>
<body>
		<div id="zhongxin">
		<form action="experts/goSelectModel" name="SearchForm" id="SearchForm" method="post">
		<input type="hidden" name="service_code" id="service_code" value="${pd.service_code}"/>
		<input type="hidden" name="service_id" id="service_id" value="${pd.service_id}"/>
		<input type="hidden" name="service_name" id="service_name" value="${pd.service_name}"/>
			<table class="table table-striped table-bordered table-hover">
				<tr>
				<td colspan="4">${pd.service_name} </td>
				<%--
				<td>开始时间：<input class="span10 date-picker" name="begin_date" id="begin_date" value="${pd.begin_date}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;"  placeholder="开始日期"/></td>
				<td>结束时间：<input class="span10 date-picker" name="end_date" id="end_date" value="${pd.end_date}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td> 
				--%>
				</tr>
				<tr>
					<td>模型名称（组）:	<input autocomplete="off"  type="text" name="name" id="name" value="${pd.name}"  /> </td>
					<td>创建人:	<input autocomplete="off"  type="text" name="member_name" id="member_name" value="${pd.member_name}"  /> </td>
					<td>
						<a class="btn btn-mini btn-primary" onclick="searchData();">查询</a>
					</td>
					<td>
						<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					</td>
				</tr>
			</table>
			</form>
			
			<form action="experts/${msg}" name="Form" id="Form" method="post">
			<input type="hidden" name="service_id" id="service_id" value="${pd.service_id}"/>
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>模型名称（组）</th>
						<th>创建人</th>
						<th>理论盈利率</th>
						<th>盈利率</th>
						<th class="center">选择模型</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.name}</td>
										<td>${var.member_name}</td>
										<td>${var.expected_profit_rate}</td>
										<td>${var.historical_profit_rate}</td>
										<td class='center' style="width: 30px;">
										<label><input type="radio" name='models_info' id='models_info' value="${var.id},${var.name},${var.models_type}" checked="checked"/><span class="lbl"></span></label>
										</td>
							</tr>
						
						</c:forEach>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			</form>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
	
	
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
		
		function save(){
			
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		</script>
</body>
</html>