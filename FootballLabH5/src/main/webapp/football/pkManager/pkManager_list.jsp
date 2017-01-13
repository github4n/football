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
			<form action="pkManager/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>所属专家：
						<select name = "expert_id" id="expert_id" class="chzn-select">
								<option value="">--全部--</option>
						  	<c:forEach items="${experts}" var="expert">
						  		<option value="${expert.EXPERTS_ID}" <c:if test="${pd.expert_id == expert.EXPERTS_ID}">selected</c:if>>${expert.EXPERTS_NAME}</option>
						  	</c:forEach>
						</select>
					</td>
					<td > 
						玩法
					 	<select class="chzn-select" name="wf" data-placeholder="玩法" style="vertical-align:top;width: 120px;">
						<option value="">全部</option>
						<option value="JCSPF" <c:if test="${pd.wf == 'JCSPF' }">selected</c:if> >竞彩胜平负</option>
						<option value="JCRQSPF" <c:if test="${pd.wf == 'JCRQSPF' }">selected</c:if> >竞彩让球胜平负</option>
						<option value="DC" <c:if test="${pd.wf == 'DC' }">selected</c:if> >单场</option>
						</select>
					</td>
					<td>用户名：	
							<input autocomplete="off"  type="text" name="member_name" id="member_name" value="${pd.member_name}"  />
					</td>
				</tr>
				<tr>
					<tr>
					<td>开始时间：<input class="span10 date-picker" readonly="readonly" name="create_timeStart" id="create_timeStart" value="${pd.create_timeStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="开始日期"/></td>
					<td>结束时间：<input class="span10 date-picker" readonly="readonly" name="create_timeEnd" id="create_timeEnd" value="${pd.create_timeEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td>
			<%-- 		<td>我的盈利排序： <select class="chzn-select" name="ylorder" data-placeholder="我的盈利排序" style="vertical-align:top;width: 120px;">
						<option value="">不排序</option>
						<option value="0" <c:if test="${pd.ylorder == '0' }">selected</c:if> >从高到底</option>
						<option value="1" <c:if test="${pd.ylorder == '1' }">selected</c:if> >从低到高</option>
						</select></td>
					 --%>
					<td>
					<button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button>
					<a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a>
					</td>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>电话</th>
						<th>所属专家</th>
						<th>玩法</th>
						<th>PK开始时间</th>
				        <th>我的盈利</th>
				        <th>我的盈利率</th>
				        <th>我的场数</th>
						<th>专家盈利</th>
						<th>专家盈利率</th>
						<th>专家场数</th>
						<th class="center">操作</th>
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
										<td>${var.service_name}</td>
										<td><fmt:formatDate value="${var.add_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>
											<fmt:formatNumber value="${var.myNum3}" type="currency" pattern="0.00"/>
										</td>
										<td>
											<c:if test="${0 == var.myNum4}">
												0
											</c:if>
											<c:if test="${0 != var.myNum4}">
											<fmt:formatNumber value="${var.myNum3 / var.myNum4 * 100}" type="currency" pattern="0.00"/>
											</c:if>
											%
										</td>
										<td> ${var.myNum2}</td>
										<td>
											<fmt:formatNumber value="${var.num3}" type="currency" pattern="0.00"/>
										</td>
										<td>
											<c:if test="${0 == var.num4}">
												0
											</c:if>
											<c:if test="${0 != var.num4}">
											<fmt:formatNumber value="${var.num3 / var.num4 * 100}" type="currency" pattern="0.00"/>
											</c:if>
											%
										</td>
										<td> ${var.num2}</td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<li><a style="cursor:pointer;" title="详情" onclick="detaile('${var.member_id}','${var.service_id}');" class="tooltip-details" data-rel="tooltip" title="" data-placement="left"><span class="yellow"><i class="icon-search"></i></span></a></li>
										</ul>
										</div>
									</div>
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
			$("#Form").attr("action", "<%=basePath%>pkManager/list");
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
		  
		
		function detaile(member_id,service_id){
			var create_timeStart = $("#create_timeStart").val();
			var create_timeEnd = $("#create_timeEnd").val();
			top.mainFrame.tabAddHandler("pk_detaile","PK详情","pkManager/pkList?member_id="+member_id+"&service_id="+service_id+"&create_timeStart="+create_timeStart+"&create_timeEnd="+create_timeEnd);
		}
		
		//导出excel
		function toExcel(){
			$("#Form").attr("action", "<%=basePath%>pkManager/excel");
			$("#Form").submit();
		}
		</script>
		
	</body>
</html>

