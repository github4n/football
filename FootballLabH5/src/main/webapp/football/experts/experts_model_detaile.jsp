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
		
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		
<script type="text/javascript">

</script>
	</head>
<body>
		<div id="zhongxin">
		<div>
			 ${pd.experts_name}  ${pd.service_name}  
			
			 <a href="javascript:setUse('${pd.service_id}','${pd.is_used}');" style="text-decoration: underline;" ><c:if test="${'0'==pd.is_used || pd.is_used == null}">开启玩法</c:if><c:if test="${'1'==pd.is_used}">关闭玩法</c:if></a>		
			 <%--  <a href="#" style="text-decoration: underline;"><c:if test="${'0'==pd.is_pk}">开启PK赛</c:if><c:if test="${'1'==pd.is_pk}">关闭PK赛</c:if></a>
	    	--%>
	    </div>
	    <div>
	    	当前使用模型（组）：${pd.models_name}	&nbsp;&nbsp;&nbsp;&nbsp;
	    	<c:if test="${empty approvePd}">
	    		<a  onclick="goSelect('${pd.service_code}','${pd.service_id}','${pd.service_name}')" style="text-decoration: underline;">更换</a>
			</c:if>
			<c:if test="${not empty approvePd}">
	    		审核中：${approvePd.models_name}
			</c:if>
	    </div>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<td>投注场数</td>
				<td>投注总额</td>
				<td>中奖总额</td>
				<td>盈利总额</td>
				<td>盈利率</td>
				<td>理论本金</td>
				<td>理论盈利率</td>
			</tr>
			<tr>
				<td>--</td>
				<td>--</td>
				<td>--</td>
				<td>--</td>
				<td>--</td>
				<td>--</td>
				<td>--</td>
			</tr>
		</table>
		</div>
	
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
		
		
		//选择界面
		function goSelect(service_code,service_id,service_name){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>experts/goSelectModel?service_code='+service_code+'&service_id='+service_id+'&service_name='+service_name;
			 diag.Width = 700;
			 diag.Height = 550;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 window.location.reload(true);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function setUse(Id,isUsed){
			bootbox.confirm("确定要开启玩法吗?", function(result) {
				if(result) {
					top.jzts();
					if(1 == isUsed){
						isUsed = 0;
					}else{
						isUsed = 1;
					}
					var url = "<%=basePath%>experts/setUse?service_id="+Id+"&isUsed="+isUsed+"&tm="+new Date().getTime();
					$.get(url,function(data){
						window.location.reload(true);
					});
				}
			});
		}
		
		</script>
</body>
</html>