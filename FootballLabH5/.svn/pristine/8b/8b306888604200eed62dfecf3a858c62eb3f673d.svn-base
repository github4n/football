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
			<form action="trade/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td style="vertical-align:top;"> 
						<select name = "expert_id" id="expert_id" class="chzn-select">
							<option value="">--全部--</option>
						  	<c:forEach items="${experts}" var="expert">
						  		<option value="${expert.EXPERTS_ID}" <c:if test="${pd.expert_id == expert.EXPERTS_ID}">selected</c:if> >${expert.EXPERTS_NAME}</option>
						  	</c:forEach>
						</select>
					</td>
					<td><input class="span10 date-picker" name="create_timeStart" id="create_timeStart" value="${pd.create_timeStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:100px;" placeholder="创建开始日期"/></td>
					<td><input class="span10 date-picker" name="create_timeEnd" id="create_timeEnd" value="${pd.create_timeEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:100px;" placeholder="创建结束日期"/></td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="statement" id="statement" data-placeholder="交易状态" style="vertical-align:top;width: 79px;">
						<option value="">全部</option>
						<option value="0" <c:if test="${pd.statement == '0' }">selected</c:if> >等待支付</option>
						<option value="1" <c:if test="${pd.statement == '1' }">selected</c:if> >支付失败</option>
						<option value="2" <c:if test="${pd.statement == '2' }">selected</c:if> >支付成功</option>
						</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="wf" data-placeholder="定制玩法" style="vertical-align:top;width: 120px;">
						<option value="">全部</option>
						<option value="JCSPF" <c:if test="${pd.wf == 'JCSPF' }">selected</c:if> >竞彩胜平负</option>
						<option value="JCRQSPF" <c:if test="${pd.wf == 'JCRQSPF' }">selected</c:if> >竞彩让球胜平负</option>
						<option value="JCECY" <c:if test="${pd.wf == 'JCECY' }">selected</c:if> >竞彩二串一</option>
						<option value="JCRQECY" <c:if test="${pd.wf == 'JCRQECY' }">selected</c:if> >竞彩让球二串一</option>
						<option value="YP" <c:if test="${pd.wf == 'YP' }">selected</c:if> >亚盘</option>
						<option value="DC" <c:if test="${pd.wf == 'DC' }">selected</c:if> >单场</option>
						</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="type" data-placeholder="订单类型" style="vertical-align:top;width: 120px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${pd.type == '1' }">selected</c:if> >方案定制</option>
						<option value="2" <c:if test="${pd.type == '2' }">selected</c:if> >对赌</option>
						<option value="3" <c:if test="${pd.type == '3' }">selected</c:if> >积分充值</option>
						<option value="4" <c:if test="${pd.type == '4' }">selected</c:if> >跟单</option>
						</select>
					</td>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="member_name" value="${pd.member_name}"  placeholder="用户名" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" value="${pd.phone_number}" name="phone_number" value="" placeholder="手机号" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>
			<!-- 检索  -->
		<%-- 	<table>
				<tr>
					<td>交易笔数 ：&nbsp;${totalCount }&nbsp;&nbsp;</td>
					<td>交易成功笔数 ：&nbsp;${successCount }&nbsp;&nbsp;</td>
					<td>支付成功金额 ：&nbsp;${totalAmountBySuccess }&nbsp;&nbsp;</td>
				</tr>
			</table> --%>
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				
				
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>手机号</th>
						<th>交易时间</th>
						<th>交易单号</th>
						<th>定制玩法</th>
						<th>所属专家</th>
						<th>交易状态</th>
						<th>支付金额（元）</th>
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
										<td><fmt:formatDate value="${var.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${var.wx_transaction_id}</td>
										<c:choose>
											<c:when test="${var.type == 1 && (var.play_type==2 || var.play_type==3)}">
												<td>
													${var.service_name}
													<c:if test="${var.service_code=='DC' }">
														/单场
													</c:if>
													<c:if test="${var.service_code=='YP' }">
														/亚盘
													</c:if>
													<c:if test="${var.service_code=='JCECY' }">
														/亚盘
													</c:if>
												</td>
											</c:when>
											<c:when test="${var.type==2}">
												<td>
													${var.service_name}
													<c:if test="${var.service_code=='DC' }">
														/单场
													</c:if>
													<c:if test="${var.service_code=='YP' }">
														/亚盘
													</c:if>
													/对赌
												</td>
											</c:when>
											<c:when test="${var.type==3}">
												<td>
													<!-- 积分充值 -->
													${var.remark }
												</td>
											</c:when>
											<c:when test="${var.type==4}">
												<td>
													${var.service_name}
													<c:if test="${var.service_code=='DC' }">
														/单场
													</c:if>
													<c:if test="${var.service_code=='YP' }">
														/亚盘
													</c:if>
													/跟单
												</td>
											</c:when>
											<c:otherwise>
												<td>${var.service_name}</td>
											</c:otherwise>
										</c:choose>
										<td>${var.experts_name}</td>
										<td>
											<c:if test="${var.statement==0}">等待支付</c:if>
											<c:if test="${var.statement==1}">支付失败</c:if>
											<c:if test="${var.statement==2}">支付成功</c:if>
										</td>
										<td>${var.money_amount}</td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<li><a style="cursor:pointer;" title="详情" onclick="detaile('${var.id}');" class="tooltip-details" data-rel="tooltip" title="" data-placement="left"><span class="yellow"><i class="icon-search"></i></span></a></li>
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
			 diag.Title ="详情";
			 diag.URL = '<%=basePath%>trade/goDetaile?transaction_detail_id='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
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

