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
			<a href="guess/list">返回竞猜列表</a>
			<div style="padding-top:8px;padding-bottom:10px">
				<div style="float: left;font-size: 16px;">
					参与人数：${sumInfo.totalCount }
				</div>
				<div style="padding-left:30px;float: left;font-size: 16px;">
					投入积分：${sumInfo.totalInput }
				</div>
				<div style="padding-left:30px;float: left;font-size: 16px;">
					中奖积分：${sumInfo.totalWin }
				</div>
				<div style="padding-left:30px;float: left;font-size: 16px;">
					亏盈积分：${-sumInfo.totalProfit }
				</div>
				 <br style="clear:both">          
			</div>
			<form action="guess/memberGuessList" method="post" name="Form" id="Form">
				<input type="hidden" name="guessGameId" id="guessGameId" value="${pd.guessGameId}"/>
				<table>
					<tr>
						<td>用户名：
							<input autocomplete="off" id="nav-search-input" type="text" name="memberName" value="${pd.memberName }" placeholder="用户名" />
						</td>
						<td>手机号：
							<input autocomplete="off" id="nav-search-input" type="text" name="phoneNumber" value="${pd.phoneNumber }" placeholder="手机号" />
						</td>
						<td>投注选项：
							<select name="strategy" id="strategy">
								<option value="">请选择</option>
								<option value="winAmount" <c:if test="${pd.strategy=='winAmount'}">selected</c:if>>胜</option>
								<option value="drawAmount" <c:if test="${pd.strategy=='drawAmount'}">selected</c:if>>平</option>
								<option value="loseAmount" <c:if test="${pd.strategy=='loseAmount'}">selected</c:if>>负</option>
								<option value="rqWinAmount" <c:if test="${pd.strategy=='rqWinAmount'}">selected</c:if>>让胜</option>
								<option value="rqDrawAmount" <c:if test="${pd.strategy=='rqDrawAmount'}">selected</c:if>>让平</option>
								<option value="rqLoseAmount" <c:if test="${pd.strategy=='rqLoseAmount'}">selected</c:if>>让负</option>
							</select>
						</td>
						<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"><i id="nav-search-icon" class="icon-search"></i></button></td>
					</tr>
				</table>
			
				<table id="table_report" class="table table-striped table-bordered table-hover" >
					
					<thead>
						<tr>
							<th class='center'>微信号</th>
							<th class='center'>手机号</th>
							<th class='center'>投注时间</th>
							<th class='center'>投注选项</th>
							<th class='center'>投入积分</th>
							<th class='center'>中奖积分</th>
							<th class='center'>亏盈积分</th>
						</tr>
					</thead>
											
					<tbody>
						
					<!-- 开始循环 -->	
					<c:choose>
						<c:when test="${not empty memberGuessList}">
							<c:if test="${QX.cha == 1 }">
							<c:forEach items="${memberGuessList}" var="var" varStatus="vs">
								<tr>
									<td class='center' >${var.memberName}</td>
									<td class='center' >${var.phoneNumber}</td>
									<td class='center' ><fmt:formatDate  value="${var.guessTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td class='center' >${var.guessStrategyStr}</td>
									<td class='center' >${var.inputAmount}</td>
									<td class='center' >${var.winAmount}</td>
									<td class='center' >${var.profitAmount}</td>
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
		<!-- 引入 -->
		
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		</script>
		
	</body>
</html>

