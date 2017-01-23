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
	
			<!-- 检索  -->
			<form action="points/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>所属专家：
						<select name = "expert_id" id="expert_id" class="chzn-select">
								<option value="">--全部--</option>
						  	<c:forEach items="${experts}" var="expert">
						  		<option value="${expert.EXPERTS_ID}" <c:if test="${pd.expert_id == expert.EXPERTS_ID}">selected</c:if> >${expert.EXPERTS_NAME}</option>
						  	</c:forEach>
						</select>
					</td>

					<td>交易类型：
							<select name="deal_type" id="deal_type" class="chzn-select">
								<option value="">--全部--</option>
								<option value="00" <c:if test="${pd.deal_type == '00'}">selected</c:if>>抽奖获得</option>
								<option value="01" <c:if test="${pd.deal_type == '01'}">selected</c:if>>分享获得</option>
								<option value="02" <c:if test="${pd.deal_type == '02'}">selected</c:if>>购买方案获得</option>
								<option value="03" <c:if test="${pd.deal_type == '03'}">selected</c:if>>注册获得</option>
								<option value="04" <c:if test="${pd.deal_type == '04'}">selected</c:if>>一场夺分奖金</option>
								<option value="05" <c:if test="${pd.deal_type == '05'}">selected</c:if>>不盈双倍返</option>
								<option value="06" <c:if test="${pd.deal_type == '06'}">selected</c:if>>积分充值</option>
								<option value="07" <c:if test="${pd.deal_type == '07'}">selected</c:if>>一场夺分排行榜奖励</option>
								<option value="08" <c:if test="${pd.deal_type == '08'}">selected</c:if>>一场夺分不中返还</option>
								<option value="09" <c:if test="${pd.deal_type == '09'}">selected</c:if>>订单失败返还</option>
								<option value="010" <c:if test="${pd.deal_type == '010'}">selected</c:if>>跟单中奖</option>
								<option value="011" <c:if test="${pd.deal_type == '011'}">selected</c:if>>盈亏中奖</option>
								<option value="10" <c:if test="${pd.deal_type == '10'}">selected</c:if>>抽奖消费</option>
								<option value="11" <c:if test="${pd.deal_type == '11'}">selected</c:if>>购买方案消费</option>
								<option value="12" <c:if test="${pd.deal_type == '12'}">selected</c:if>>一场夺金投注</option>
								<option value="13" <c:if test="${pd.deal_type == '13'}">selected</c:if>>积分兑换消费</option>
								<option value="14" <c:if test="${pd.deal_type == '14'}">selected</c:if>>跟单投入</option>
								<option value="15" <c:if test="${pd.deal_type == '15'}">selected</c:if>>盈亏投入</option>
							</select>
					</td>
					<td>用户名：
						<input type="text" name="member_name" id="member_name" value="${pd.member_name}" />
					</td>
					<td>手机号：
						<input type="text" name="phone_number" id="phone_number" value="${pd.phone_number}" />
					</td>
				</tr>
				<tr>
					<td>开始时间：<input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="开始日期"/></td>
					<td>结束时间：<input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td>
					<td>支出类型：	<select name="points_type" id="points_type" class="chzn-select">
								<option value="">--全部--</option>
								<option value="0" <c:if test="${pd.points_type == '0'}">selected</c:if>>获得</option>
								<option value="1" <c:if test="${pd.points_type == '1'}">selected</c:if>>消费</option>
							</select>
					</td>
				
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				<%-- 	<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if> --%>
				</tr>
			</table>
			<!-- 检索  -->
			
			<%-- <table>
				<tr>
					<td>交易数量 ：&nbsp;${totalCount }&nbsp;&nbsp;</td>
					<td>平台收入积分 ：&nbsp;${pointsDes}&nbsp;&nbsp;</td>
					<td>平台支出积分 ：&nbsp;${pointsIns}&nbsp;&nbsp;</td>
					<td>平台盈/亏 ：&nbsp;${profit}&nbsp;&nbsp;</td>
				</tr>
			</table> --%>
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>手机号</th>
						<th>所属专家</th>
						<th>时间</th>
						<th>交易类型</th>
						<th>积分流水</th>
						<th>积分余额</th>
						<th>备注</th>
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
										<td>${var.member_name}</td>
										<td>${var.phone_number}</td>
										<td>${var.experts_name}</td>
										<td><fmt:formatDate value="${var.consume_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>
											<c:if test="${var.points_type=='0'}">
												<c:if test="${var.type == '0'}">抽奖获得</c:if>
												<c:if test="${var.type == '1'}">分享获得</c:if>
												<c:if test="${var.type == '2'}">购买方案获得</c:if>
												<c:if test="${var.type == '3'}">注册获得</c:if>
												<c:if test="${var.type == '4'}">一场夺分奖金</c:if>
												<c:if test="${var.type == '5'}">不盈双倍返</c:if>
												<c:if test="${var.type == '6'}">积分充值</c:if>
												<c:if test="${var.type == '7'}">一场夺分排行榜奖励</c:if>
												<c:if test="${var.type == '8'}">一场夺分不中返还</c:if>
												<c:if test="${var.type == '9'}">订单失败返还</c:if>
												<c:if test="${var.type == '10'}">跟单中奖</c:if>
												<c:if test="${var.type == '11'}">盈亏中奖</c:if>
											</c:if>	
											<c:if test="${var.points_type=='1'}">
												<c:if test="${var.type == '0'}">抽奖消费</c:if>
												<c:if test="${var.type == '1'}">购买方案消费</c:if>
												<c:if test="${var.type == '2'}">一场夺分投注</c:if>
												<c:if test="${var.type == '3'}">积分兑换消费</c:if>
												<c:if test="${var.type == '4'}">跟单投入</c:if>
												<c:if test="${var.type == '5'}">盈亏投入</c:if>
											</c:if>											
										</td>
										<td>
											<c:if test="${var.points_type=='0'}">+</c:if>
											<c:if test="${var.points_type=='1'}">-</c:if>
											${var.points_number}
										</td>
										<td>${var.remain}</td>
										<td>${var.remarks}</td>
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
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		</script>
		
	</body>
</html>

