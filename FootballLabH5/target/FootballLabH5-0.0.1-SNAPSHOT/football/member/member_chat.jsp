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
		
		<script type="text/javascript" src="static/js/chat/jQuery.js"></script>
		<script type="text/javascript" src="static/js/chat/jqplot.js"></script>
		
<script type="text/javascript">
	
	
</script>
	</head>
<body>
	<div class="container-fluid" id="main-container">



<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


 	<div class="span12">
		<div class="widget-box">
			<div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">
				<h4 class="lighter">用户图形</h4>
			</div>
			<div class="widget-body">
			 	<div class="widget-main">
			 
			 <form action="member/goChat" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>开始时间：<input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="开始日期"/></td>
					<td>结束时间：<input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td>
				
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				<%-- 	<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if> --%>
				</tr>
			</table>
			<!-- 检索  -->
			 注册总数：${registerSum}<br>
			关注总数：${focusSum} 
			 
			 <div id="chart1"></div>
			 
			 
			 <table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th>序号</th>
						<th>日期</th>
						<th>关注人数</th>
						<th>注册人数</th>
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
										<td>${var.date}</td>
										<td>${var.focusCount}</td>
										<td>${var.registerCount}</td>
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
				 
				 </div><!--/widget-main-->
			</div><!--/widget-body-->
		</div>
	</div>
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
	
	
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
		
			<script type="text/javascript">
	//var data = [[1,2,3,,5,6,7,8,9],[3,6,8,1,11,22,4,21,6]];
	var focus = '${focusYData}'.replace("[","").replace("]","").split(",");
	var register = '${registerYData}'.replace("[","").replace("]","").split(",");
	var data = [focus,register];
	var data_max = '${maxY}'; //Y轴最大刻度
	var line_title = ["关注","注册"]; //曲线名称
	var y_label = "用户数量"; //Y轴标题
	var x_label = "日期"; //X轴标题
	var x = '${xData}'.replace("[","").replace("]","").split(","); //定义X轴刻度值
	var title = "用户数量变化图"; //统计图标标题
	j.jqplot.diagram.base("chart1", data, line_title, title, x, x_label, y_label, data_max, 1);

</script>
</body>
</html>