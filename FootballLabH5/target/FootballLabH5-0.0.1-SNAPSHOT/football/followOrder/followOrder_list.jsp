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
			<form action="transaction/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td style="vertical-align:top;"> 
						<select name = "expert_id" id="expert_id" class="chzn-select">
							<option value="">--全部--</option>
						  	<c:forEach items="${experts}" var="expert">
						  		<option value="${expert.EXPERTS_ID}">${expert.EXPERTS_NAME}</option>
						  	</c:forEach>
						</select>
					</td>
					<td><input class="span10 date-picker" name="create_timeStart" id="create_timeStart" value="${pd.create_timeStart}" type="text" data-date-format="yyyy-mm-dd" style="width:100px;" placeholder="创建开始日期"/></td>
					<td><input class="span10 date-picker" name="create_timeEnd" id="create_timeEnd" value="${pd.create_timeEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:100px;" placeholder="创建结束日期"/></td>
				<%-- 	<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="transaction_status" data-placeholder="定制状态" style="vertical-align:top;width: 79px;">
						<option value=""></option>
						<option value="1" <c:if test="${pd.transaction_status == '1' }">selected</c:if> >成功</option>
						<option value="0" <c:if test="${pd.transaction_status == '0' }">selected</c:if> >失败</option>
						</select>
					</td> --%>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="wf" data-placeholder="跟单玩法" style="vertical-align:top;width: 120px;">
						<option value="">全部</option>
						<option value="JCSPF" <c:if test="${pd.wf == 'JCSPF' }">selected</c:if> >竞彩胜平负</option>
						<option value="JCRQSPF" <c:if test="${pd.wf == 'JCRQSPF' }">selected</c:if> >竞彩让球胜平负</option>
						<option value="JCECY" <c:if test="${pd.wf == 'JCECY' }">selected</c:if> >竞彩二串一</option>
						<option value="JCRQECY" <c:if test="${pd.wf == 'JCRQECY' }">selected</c:if> >竞彩让球二串一</option>
						<option value="YP" <c:if test="${pd.wf == 'YP' }">selected</c:if> >亚盘</option>
						<option value="DC" <c:if test="${pd.wf == 'DC' }">selected</c:if> >单场</option>
						</select>
					</td>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="member_name" value="${pd.member_name}"  placeholder="用户名" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
<!-- 					<td> -->
<!-- 						<span class="input-icon"> -->
<!-- 							<input autocomplete="off" id="nav-search-input" type="text" value="${pd.transaction_number}" name="transaction_number" value="" placeholder="订单号" /> -->
<!-- 							<i id="nav-search-icon" class="icon-search"></i> -->
<!-- 						</span> -->
<!-- 					</td> -->
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>
			<!-- 检索  -->
		
			<table>
				<tr>
					<th>跟单数量 ：&nbsp;${totalOrder }&nbsp;&nbsp;</th>
					<th>跟单积分 ：&nbsp;${inputPoints }&nbsp;&nbsp;</th>
					<th>中奖积分 ：&nbsp;${winPoints }&nbsp;&nbsp;</th>
					<th>盈亏 ：&nbsp;${profitPoints }&nbsp;&nbsp;</th>
				</tr>
			</table>
			
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th>微信号</th>
						<th>手机号</th>
						<th>跟单时间</th>
						<th>订单号</th>
						<th>跟单玩法</th>
						<th>跟单积分</th>
						<th>中奖积分</th>
						<th>盈亏</th>
						<th>所属专家</th>
						
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<%-- <td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.transaction_id}" /><span class="lbl"></span></label>
								</td> --%>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.weixin}</td>
										<td>${var.phone_number}</td>
										<td><fmt:formatDate value="${var.follow_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${var.id}</td>
										<c:choose>
											<c:when test="${var.play_type==2 || var.play_type==1}">
												<td>
													${var.service_name}
													<c:if test="${var.service_code=='DC' }">
														/单场/跟单
													</c:if>
													<c:if test="${var.service_code=='YP' }">
														/亚盘/跟单
													</c:if>
													<c:if test="${var.service_code=='JCECY' }">
														/2串1/跟单
													</c:if>
												</td>
											</c:when>
											<c:otherwise>
												<td>${var.service_name}/多联赛/跟单</td>
											</c:otherwise>
										</c:choose>
										<td>${var.input_amount }</td>
										<c:choose>
											<c:when test="${var.win_amount != null ||var.win_amount != undefined || var.win_amount != '' }">
												<td>${var.win_amount }</td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${var.profit_amount != null ||var.profit_amount != undefined || var.profit_amount != ''}">
												<td>${var.profit_amount }</td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>
										<td>${var.experts_name}</td>
								<%-- 		
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<li><a style="cursor:pointer;" title="详情" onclick="detaile('${var.transaction_id}');" class="tooltip-details" data-rel="tooltip" title="" data-placement="left"><span class="yellow"><i class="icon-search"></i></span></a></li>
										</ul>
										</div>
									</div>
								</td> --%>
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

		//详情
		function detaile(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>transaction/goDetaile?transaction_id='+Id;
			 diag.Width = 450;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
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

