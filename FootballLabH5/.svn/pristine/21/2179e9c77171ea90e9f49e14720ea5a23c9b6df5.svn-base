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
			<a href="javascript:;" onclick="hideStrategy('0')"  id="myStra" style="text-decoration: underline;">我的方案</a>     
			 <a href="javascript:;" onclick="hideStrategy('1')"  id="expertStra" > 专家方案</a>
			
			<table class="table table-striped table-bordered " id="myStrategyTable">
			<tr>
			<td >
			
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th style="width: 20%">比赛时间</th>
						<th style="width: 15%">赛事</th>
						<th style="width: 15%">主队 VS 客队</th>
							<th style="width: 50%">
								<table style="width: 100%">
									<tr>
										<th style="width: 10%">推荐</th>
										<th style="width: 20%">竞彩</th>
										<th style="width: 35%">投注金额</th>
										<th style="width: 35%">奖金</th>
									</tr>
								</table>
							</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
										<td><fmt:formatDate value="${var.game_date_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${var.league_name}</td>
										<td>
											${var.home_name} <c:if test="${pkPd.type == 1}">(${var.let_the_count})</c:if> VS ${var.away_name} 
											<br></br>
											${var.home_score} <c:if test="${pkPd.type == 1}">(${var.let_the_count})</c:if> VS ${var.away_score} 
										</td>
										<td>
											<table style="width: 100%">
												<tr>
													<td style="width: 10%; <c:if test='${var.isWin==1}'>background: yellow;</c:if>">胜</td>
													<td style="width: 20%; <c:if test='${var.isWin==1}'>background: yellow;</c:if>">${var.companyOdds.win_odds}</td>
													<td style="width: 35%">
													<c:forEach items="${var.pdDetaileList}" var="detaile">
														<c:if test="${detaile.betting_strategy ==3 }">
															${detaile.betting_amount}
														</c:if>
													</c:forEach>
													</td>
													<td style="width: 35%">
													<c:forEach items="${var.pdDetaileList}" var="detaile">
														<c:if test="${detaile.betting_strategy ==3 }">
															<span style="width: 100%; <c:if test='${detaile.num3>0}'>color: red;</c:if>"><fmt:formatNumber value="${detaile.num3}" pattern="#0.00"/>  </span>
														</c:if>
													</c:forEach>
													</td>
												</tr>
												<tr>
													<td style="width: 10%; <c:if test='${var.isDraw==1}'>background: yellow;</c:if>">平</td>
													<td style="width: 20%; <c:if test='${var.isDraw==1}'>background: yellow;</c:if>">${var.companyOdds.draw_odds}</td>
													<td style="width: 35%">	
														<c:forEach items="${var.pdDetaileList}" var="detaile">
															<c:if test="${detaile.betting_strategy ==1 }">
																${detaile.betting_amount}
															</c:if>
														</c:forEach>
													</td>
													<td style="width: 35%">	
														<c:forEach items="${var.pdDetaileList}" var="detaile">
															<c:if test="${detaile.betting_strategy ==1 }">
																<span style="width: 100%; <c:if test='${detaile.num3>0}'>color: red;</c:if>"> <fmt:formatNumber value="${detaile.num3}" pattern="#0.00"/> </span>
															</c:if>
														</c:forEach>
													</td>	
												</tr>
												<tr>
													<td style="width: 10%; <c:if test='${var.isLose==1}'>background: yellow;</c:if>">负</td>
													<td style="width: 20%; <c:if test='${var.isLose==1}'>background: yellow;</c:if>">${var.companyOdds.lose_odds}</td>
													<td style="width: 35%">	
														<c:forEach items="${var.pdDetaileList}" var="detaile">
															<c:if test="${detaile.betting_strategy ==0 }">
																${detaile.betting_amount}
															</c:if>
														</c:forEach>
													</td>
													<td style="width: 35%">	
														<c:forEach items="${var.pdDetaileList}" var="detaile">
															<c:if test="${detaile.betting_strategy ==0 }">
																<span style="width: 100%; <c:if test='${detaile.num3>0}'>color: red;</c:if>"><fmt:formatNumber value="${detaile.num3}" pattern="#0.00"/>  </span>
															</c:if>
														</c:forEach>
													</td>		
												</tr>		
											</table>
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
			
			</td>
			
			<td>
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td>方案汇总</td>
					</tr>
					<tr>
						<td>投注总额:<fmt:formatNumber value="${number4}" pattern="#0.00"/></td>
					</tr>
					<tr>
						<td>奖金:<fmt:formatNumber value="${number3}" pattern="#0.00"/></td>
					</tr>
				</table>
			</td>
			</tr>
			</table>
			
			
			<!-- 专家方案 -->
			<table class="table table-striped table-bordered " id="expertStrategyTable" style="display: none;">
			<tr>
			<td >
			
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th style="width: 20%">比赛时间</th>
						<th style="width: 15%">赛事</th>
						<th style="width: 15%">主队 VS 客队</th>
							<th style="width: 50%">
								<table style="width: 100%">
									<tr>
										<th style="width: 10%">推荐</th>
										<th style="width: 20%">竞彩</th>
										<th style="width: 35%">投注金额</th>
										<th style="width: 35%">奖金</th>
									</tr>
								</table>
							</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty expertVarList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${expertVarList}" var="var" varStatus="vs">
							<tr>
										<td><fmt:formatDate value="${var.vo.game_date_time}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
										<td>${var.vo.league_name}</td>
										<td>
											${var.vo.home_name} <c:if test="${pkPd.vo.type == 1}">(${var.vo.let_the_count})</c:if> VS ${var.vo.away_name} 
											<br></br>
											${var.vo.home_score} <c:if test="${pkPd.vo.type == 1}">(${var.vo.let_the_count})</c:if> VS ${var.vo.away_score} 
										</td>
										<td>
											<table style="width: 100%">
												<tr>
													<td style="width: 10%; <c:if test='${var.isWin==1}'>background: yellow;</c:if>">胜</td>
													<td style="width: 20%; <c:if test='${var.isWin==1}'>background: yellow;</c:if>">${var.vo.win_odds}</td>
													<td style="width: 35%">
														<c:if test="${var.vo.strategy ==3 }">
															${var.vo.betting_amount}
														</c:if>
													</td>
													<td style="width: 35%">
														<c:if test="${var.vo.strategy ==3 }">
															<span style="width: 100%; <c:if test='${var.num3>0}'>color: red;</c:if>"><fmt:formatNumber value="${var.num3}" pattern="#0.00"/>  </span>
														</c:if>
													</td>
												</tr>
												<tr>
													<td style="width: 10%; <c:if test='${var.isDraw==1}'>background: yellow;</c:if>">平</td>
													<td style="width: 20%; <c:if test='${var.isDraw==1}'>background: yellow;</c:if>">${var.vo.draw_odds}</td>
													<td style="width: 35%">	
															<c:if test="${var.vo.strategy ==1 }">
																${var.vo.betting_amount}
															</c:if>
													</td>
													<td style="width: 35%">	
															<c:if test="${var.vo.strategy ==1 }">
																<span style="width: 100%; <c:if test='${var.num3>0}'>color: red;</c:if>"> <fmt:formatNumber value="${var.num3}" pattern="#0.00"/> </span>
															</c:if>
													</td>	
												</tr>
												<tr>
													<td style="width: 10%; <c:if test='${var.isLose==1}'>background: yellow;</c:if>">负</td>
													<td style="width: 20%; <c:if test='${var.isLose==1}'>background: yellow;</c:if>">${var.vo.lose_odds}</td>
													<td style="width: 35%">	
															<c:if test="${var.vo.strategy ==0 }">
																${var.vo.betting_amount}
															</c:if>
													</td>
													<td style="width: 35%">	
															<c:if test="${var.vo.strategy ==0 }">
																<span style="width: 100%; <c:if test='${var.num3>0}'>color: red;</c:if>"><fmt:formatNumber value="${var.num3}" pattern="#0.00"/>  </span>
															</c:if>
													</td>		
												</tr>		
											</table>
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
			
			</td>
			
			<td>
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td>方案汇总</td>
					</tr>
					<tr>
						<td>投注总额:<fmt:formatNumber value="${expertNumber4}" pattern="#0.00"/></td>
					</tr>
					<tr>
						<td>奖金:<fmt:formatNumber value="${expertNumber3}" pattern="#0.00"/></td>
					</tr>
				</table>
			</td>
			</tr>
			</table>
			
		</form>
	</div>
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
		
		function detaile(pk_id){
			top.mainFrame.tabAddHandler("pk_strategy_detaile","方案详情","coupon/list.do");
		}
		
		function hideStrategy(tag){
			if(tag == 0){
				$("#expertStrategyTable").hide();
				$("#myStrategyTable").show();
				
				$("#myStra").css("text-decoration","underline");
				$("#expertStra").css("text-decoration","none");
			}else{
				$("#expertStrategyTable").show();
				$("#myStrategyTable").hide();
				
				$("#myStra").css("text-decoration","none");
				$("#expertStra").css("text-decoration","underline");
			}
		}
		
		</script>
		
	</body>
</html>

