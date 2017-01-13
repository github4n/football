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
			<form action="approve/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>类型：
							<select name="submit_type" id="submit_type" class="chzn-select">
								<option value="">--全部--</option>
								<option value="0" <c:if test="${pd.submit_type == '0'}">selected</c:if>>优惠券申请</option>
								<option value="1" <c:if test="${pd.submit_type == '1'}">selected</c:if>>模型更换</option>
								<option value="2" <c:if test="${pd.submit_type == '2'}">selected</c:if>>增加专家</option>
								<option value="3" <c:if test="${pd.submit_type == '3'}">selected</c:if>>修改抽奖奖品</option>
							</select>
					</td>
				
					<td>所属专家：
						<select name = "expert_id" id="expert_id" class="chzn-select">
								<option value="">--全部--</option>
						  	<c:forEach items="${experts}" var="expert">
						  		<option value="${expert.EXPERTS_ID}" <c:if test="${pd.expert_id == expert.EXPERTS_ID}">selected</c:if>>${expert.EXPERTS_NAME}</option>
						  	</c:forEach>
						</select>
					</td>
					
					<td>审核状态：
							<select name="approve_stauts" id="approve_stauts" class="chzn-select">
								<option value="">--全部--</option>
								<option value="1" <c:if test="${pd.approve_stauts == '1'}">selected</c:if>>未审核</option>
								<option value="2" <c:if test="${pd.approve_stauts == '2'}">selected</c:if>>审核不通过</option>
								<option value="3" <c:if test="${pd.approve_stauts == '3'}">selected</c:if>>审核通过</option>
							</select>
					</td>
					
				</tr>
				<tr>
					<td>开始时间：<input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="开始日期"/></td>
					<td>结束时间：<input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td>
					<td>提交人：
						<input type="text" name="submit_user" id="submit_user" value="${pd.submit_user}" />
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
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
						<th>提交人</th>
						<th>类型</th>
						<th>提交时间</th>
						<th>审核时间</th>
						<th>所属专家</th>
						<th>审核状态</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${var.submit_user}</td>
								<td>
									<c:if test="${var.submit_type == '0'}">优惠券申请</c:if>
									<c:if test="${var.submit_type == '1'}">模型更换</c:if>
									<c:if test="${var.submit_type == '2'}">增加专家</c:if>
									<c:if test="${var.submit_type == '3'}">修改抽奖奖品</c:if>
								</td>
								<td><fmt:formatDate value="${var.submit_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${var.approve_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${var.experts_name}</td>
								<td>
									<c:if test="${var.approve_stauts == '1'}">未审核</c:if>
									<c:if test="${var.approve_stauts == '2'}">审核不通过</c:if>
									<c:if test="${var.approve_stauts == '3'}">审核通过</c:if>
								</td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<li><a style="cursor:pointer;" title="详情" onclick="detaile('${var.approve_id}');" class="tooltip-details" data-rel="tooltip" title="" data-placement="left"><span class="yellow"><i class="icon-search"></i></span></a></li>
										</ul>
										</div>
									</div>
								</td>
							</tr>
						
						</c:forEach>
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
			 diag.URL = '<%=basePath%>approve/goApprove?approve_id='+Id;
			 diag.Width = 650;
			 diag.Height = 600;
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
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>coupon/deleteAll?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>coupon/excel';
		}
		</script>
		
	</body>
</html>

