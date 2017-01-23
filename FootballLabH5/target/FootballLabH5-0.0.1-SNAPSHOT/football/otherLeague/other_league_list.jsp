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
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
	</head>
<body>
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<form action="otherLeague/list" method="post" name="Form" id="Form">
				<table>
					<tr>
						<td>专家名称：
							<select name="expertId" id="expertId">
								<option value="">请选择</option>
								<c:forEach items="${experts}" var="expert">
									<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == pd.expertId }">selected</c:if> >${expert.EXPERTS_NAME}</option>
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;&nbsp;玩法：
							<select name="serviceCode" id="serviceCode">
								<option value="">请选择</option>
								<option value="DC"  <c:if test="${'DC' == pd.serviceCode }">selected</c:if>>单场</option>
				 				<option value="YP"  <c:if test="${'YP' == pd.serviceCode }">selected</c:if>>亚盘</option>
								<option value="JCECY"  <c:if test="${'JCECY' == pd.serviceCode }">selected</c:if>>竞彩二串一</option>
							</select>
						</td>
						<td>&nbsp;&nbsp;结算状态：
							<select name="status" id="status">
								<option value="">请选择</option>
								<option value="0" <c:if test="${pd.status=='0'}">selected</c:if>>未结算</option>
								<option value="1" <c:if test="${pd.status=='1'}">selected</c:if>>已结算</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							比赛时间：
							<input class="span10 date-picker" name="startDate" id="startDate" value="${pd.startDate}" type="text" data-date-format="yyyy-mm-dd"  style="width:92px;" placeholder="开始日期"/>
							&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;<input class="span10 date-picker" name="endDate" id="endDate" value="${pd.endDate}" type="text" data-date-format="yyyy-mm-dd"  style="width:92px;" placeholder="结束日期"/>
						</td>
						<td style="font-size:16px">
							&nbsp;&nbsp;总投：<i id="totalInputAmount"></i> &nbsp;&nbsp;中奖：<i id="totalWinAmount"></i>&nbsp;&nbsp;盈利：<i id="totalProfitAmount"></i>
						</td>
						<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"><i id="nav-search-icon" class="icon-search"></i></button></td>
					</tr>
				</table>
				
				<table style="width:100%;">
					<tr>
						<td style="vertical-align:top; padding-bottom:10px;padding-top:10px;">
							<c:if test="${QX.add == 1 }">
							<a class="btn btn-small btn-success" onclick="add('DC');">新增单场</a>
							<a class="btn btn-small btn-success" onclick="add('YP');">新增亚盘</a>
							<a class="btn btn-small btn-success" onclick="add('JCECY');">新增二串一</a>
							</c:if>
						</td>
					</tr>
				</table>
			
				<table id="table_report" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th class='center'>比赛时间</th>
							<th class='center'>玩法</th>
							<th class='center'>投入</th>
							<th class='center'>中奖</th>
							<th class='center'>盈利</th>
							<th class='center'>结算</th>
							<th class='center'>所属专家</th>
							<th class="center">操作</th>
						</tr>
					</thead>
											
					<tbody>
						
					<!-- 开始循环 -->	
					<c:choose>
						<c:when test="${not empty list}">
							<c:if test="${QX.cha == 1 }">
							<c:forEach items="${list}" var="var" varStatus="vs">
								<tr>
									<td class='center' ><fmt:formatDate  value="${var.gameDateTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td class='center' >
										<c:choose>
											<c:when test="${var.serviceCode=='DC'}">单场</c:when>
											<c:when test="${var.serviceCode=='YP'}">亚盘</c:when>
											<c:when test="${var.serviceCode=='JCECY'}">竞彩二串一</c:when>
										</c:choose>
									
									</td>
									<td class='center inputAmount'>
										<fmt:formatNumber value="${var.inputAmount}" type="number" pattern="0"/>
									</td>
									<td class='center winAmount' >
										<c:if test="${var.status}">
											<fmt:formatNumber value="${var.winAmount}" type="number" pattern="0"/>
										</c:if>
									</td>
									<td class='center profitAmount' >
										<c:if test="${var.status}">
											<fmt:formatNumber value="${var.profitAmount}" type="number" pattern="0"/>
										</c:if>
									</td>
									<c:choose>
										<c:when test="${var.status}">
											<td class='center' >已结算</td>
										</c:when>
										<c:otherwise>
											<td class='center' >未结算</td>
										</c:otherwise>
									</c:choose>
									<td class='center' >${var.expertName}</td>
									<td style="width: 60px;" class="center">
										<a href="javascript:edit('${var.serviceCode}','${var.id}')" style="text-decoration:none;cursor: pointer;">修改</a>
										<a style="text-decoration:none;cursor: pointer;" href="javascript:del('${var.id}')">删除</a>
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
			<div class="page-header position-relative">
				<table style="width:100%;">
					<tr>
						<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
 	<div id="jiazai" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		
	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
	<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
	<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
	<!-- 引入 -->
	
	<script type="text/javascript">
	
	$(top.hangge());
	var basePath = '<%=basePath%>';
	
	$(function(){
		//下拉框
		$(".chzn-select").chosen(); 
		$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
		//日期框
		$('.date-picker').datepicker();
		
		
		var totalInputAmount = 0;
		$(".inputAmount").each(function(){
			if($(this).html().trim()!=''){
				totalInputAmount+=parseInt($(this).html());
			}
		})
		$("#totalInputAmount").html(totalInputAmount);
		
		var totalWinAmount = 0;
		$(".winAmount").each(function(){
			if($(this).html().trim()!=''){
				totalWinAmount+=parseInt($(this).html());
			}
		})
		$("#totalWinAmount").html(totalWinAmount);
		
		var totalProfitAmount = 0;
		$(".profitAmount").each(function(){
			if($(this).html().trim()!=''){
				totalProfitAmount+=parseInt($(this).html());
			}
		})
		$("#totalProfitAmount").html(totalProfitAmount);
		
		
	})
	
	//检索
	function search(){
		top.jzts();
		$("#Form").submit();
	}
	
	//新增
	function add(serviceCode){
		 var widHeight = [400,580];
		 if(serviceCode=='JCECY'){
		 	widHeight = [600,800];
		 }
		 
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增";
		 diag.URL = basePath+'otherLeague/goAdd?serviceCode='+serviceCode;
		 diag.Width = widHeight[0];
		 diag.Height = widHeight[1];
		 diag.CancelEvent = function(){ //关闭事件
			 if('${page.currentPage}' == '0'){
				 top.jzts();
				 setTimeout("self.location=self.location",100);
			 }else{
				window.location.reload(true);
			 }
			diag.close();
		 };
		 diag.show();
	}
	
	
	//修改
	function edit(serviceCode,id){
	     var widHeight = [400,580];
		 if(serviceCode=='JCECY'){
		 	widHeight = [600,800];
		 }
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑";
		 diag.URL = basePath+'otherLeague/goEdit?id='+id+'&serviceCode='+serviceCode;
		 diag.Width = widHeight[0];
		 diag.Height = widHeight[1];
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 window.location.reload(true);
			}
			diag.close();
		 };
		 diag.show();
	}
	
	function del(id){
		bootbox.confirm("确定要删除么?", function(result) {
			if(result) {
				$("#jiazai").show();
				var ajaxResult;
				$.ajax({
					url:basePath+'otherLeague/delete?id='+id,
					type:'post',
					async:false,
					success:function(data){
						ajaxResult = data;
					}
				});
				$("#jiazai").hide();
				bootbox.alert(ajaxResult.msg,function(){window.location.reload(true);});
			}
		});
		
	}
	
	</script>
</body>
</html>

