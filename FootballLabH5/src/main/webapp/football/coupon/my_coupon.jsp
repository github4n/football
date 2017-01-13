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
			<form action="coupon/myCoupon" method="post" name="Form" id="Form">
			<input type="hidden" name="member_id" id="member_id"
			value="${pd.member_id}" />
			<table>
				<tr>
					<td>使用状态：
							<select name="if_has_used" id="if_has_used" class="chzn-select">
								<option value="">--全部--</option>
								<option value="0" <c:if test="${pd.if_has_used == '0'}">selected</c:if> >未使用</option>
								<option value="1" <c:if test="${pd.if_has_used == '1'}">selected</c:if>>已使用</option>
								<%-- <option value="2" <c:if test="${pd.if_has_used == '2'}">selected</c:if>>已过期</option> --%>
							</select>
					</td>
					<td>领取方式：
							<select name="type" id="type" class="chzn-select">
								<option value="">--全部--</option>
								<option value="1" <c:if test="${pd.type == '1'}">selected</c:if> >抽奖</option>
								<option value="0" <c:if test="${pd.type == '0'}">selected</c:if> >分享</option>
							</select>
					</td>
				</tr>
				<tr>
					<td>开始时间：<input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="开始日期"/></td>
					<td>结束时间：<input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td>
					<td>礼券序列号：	
							<input type="text" name="no" id="no" value="${pd.no}"  />
					</td>
				
					<td style="vertical-align:top;">
						<button class="btn btn-mini btn-light" onclick="search();"  title="检索">
						<i id="nav-search-icon" class="icon-search"></i>
						</button>
						
						<a href="coupon/list" style="text-decoration: underline;size: 22px">返回</a>
						
					</td>
				<%-- 	<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if> --%>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>礼券序列号</th>
						<th>类型</th>
						<th>专家</th>
						<th>领取时间</th>
						<th>过期时间</th>
						<th>使用状态</th>
						<th>使用时间</th>
						<th>领取方式</th>
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
										<td>${var.no}</td>
										<td>
										<c:if test="${var.coupon_type == '01'}">方案卷-竞彩胜平负</c:if>
										<c:if test="${var.coupon_type == '02'}">方案卷-竞彩让球胜平负</c:if>
										<c:if test="${var.coupon_type == '03'}">方案卷-竞彩2串1</c:if>
										<c:if test="${var.coupon_type == '04'}">方案卷-竞彩让球2串1</c:if>
										<c:if test="${var.coupon_type == '05'}">方案卷-亚盘</c:if>
										<c:if test="${var.coupon_type == '06'}">方案卷-单场</c:if>
										<c:if test="${var.coupon_type == '07'}">方案卷-足彩14场</c:if>
										<c:if test="${var.coupon_type == '08'}">方案卷-足彩R9</c:if>
										<c:if test="${var.coupon_type == '1'}">通用卷</c:if>
										<c:if test="${var.coupon_type == '2'}">折扣卷</c:if>
										<c:if test="${var.coupon_type == '3'}">现金抵扣卷</c:if>
										</td>
										<td>${var.experts_name}</td>
										<td>${var.obtain_time}</td>
										<td>${var.invalid_time}</td>
										<td>
											<c:if test="${var.if_has_used == 0}">未使用</c:if>
											<c:if test="${var.if_has_used == 1}">已使用</c:if>
											<c:if test="${var.if_has_used == 2}">已过期</c:if>
										</td>
										<td>${var.use_time}</td>
										<td>											
											<c:if test="${var.type == 0}">分享</c:if>
											<c:if test="${var.type == 1}">抽奖</c:if>
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
				
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>	
				
				</tbody>
			</table>
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
		
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>coupon/excel';
		}
		</script>
		
	</body>
</html>

