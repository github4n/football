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
			<form action="member/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td style="vertical-align:top;"> 
						<select name = "fk_experts_id" id="fk_experts_id" class="chzn-select">
								<option value="">--全部--</option>
						  	<c:forEach items="${experts}" var="expert">
						  		<option value="${expert.EXPERTS_ID}" <c:if test="${pd.fk_experts_id == expert.EXPERTS_ID}">selected</c:if>>${expert.EXPERTS_NAME}</option>
						  	</c:forEach>
						</select>
					</td>
					<td><input class="span10 date-picker" name="focusStart" id="focusStart" value="${pd.focusStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100px;" placeholder="关注开始日期"/></td>
					<td><input class="span10 date-picker" name="focusEnd" id="focusEnd" value="${pd.focusEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100px;" placeholder="关注结束日期"/></td>
					<td><input class="span10 date-picker" name="registerStart" id="registerStart" value="${pd.registerStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100px;" placeholder="注册开始日期"/></td>
					<td><input class="span10 date-picker" name="registerEnd" id="registerEnd" value="${pd.registerEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100px;" placeholder="注册结束日期"/></td>
					</tr><tr>
								<td style="vertical-align:top;"> 
						<select name = "fk_qrcode_id" id="fk_qrcode_id" class="chzn-select">
							<option value="">--全部--</option>
						  	<c:forEach items="${qrList}" var="qr">
						  		<option value="${qr.qrcode_id}" <c:if test="${qr.qrcode_id == pd.fk_qrcode_id}">selected</c:if>>${qr.name}</option>
						  	</c:forEach>
						  	<option value="other" <c:if test="${'other' == pd.fk_qrcode_id}">selected</c:if>>其它</option>
						</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="focus_status" data-placeholder="关注状态" style="vertical-align:top;width: 79px;">
						<option value="">--全部--</option>
						<option value="1" <c:if test="${pd.focus_status == '1' }">selected</c:if> >已关注</option>
						<option value="0" <c:if test="${pd.focus_status == '0' }">selected</c:if> >未关注</option>
						</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="register_status" data-placeholder="注册状态" style="vertical-align:top;width: 79px;">
						<option value="">--全部--</option>
						<option value="1" <c:if test="${pd.register_status == '1' }">selected</c:if> >已注册</option>
						<option value="0" <c:if test="${pd.register_status == '0' }">selected</c:if> >未注册</option>
						</select>
					</td>
					
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="member_name" value="${pd.member_name}" placeholder="用户名" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td>
						<span class="input-icon">
<!-- 							<input autocomplete="off" id="nav-search-input" type="text" name="weixin" value="${pd.weixin}" placeholder="微信号" /> -->
								<input autocomplete="off" id="nav-search-input" type="text" name="phone_number" value="${pd.phone_number}" placeholder="手机号" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				 	<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if> 
				</tr>
			</table>
			<!-- 检索  -->
		
			<a class="btn " onclick="goChat()" title="查看图形" >查看图形</a>
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th>序号</th>
						<th>会员名称</th>
						<th>微信号</th>
						<th>手机号</th>
						<th>积分</th>
						<th>关注状态</th>
						<th>关注时间</th>
						<th>注册状态</th>
						<th>注册时间</th>
						<th>所属专家</th>
						<th>渠道来源</th>
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
										<td>
											<a style="cursor:pointer;" title="会员详情" onclick="detail('${var.MEMBER_ID}');">${var.MEMBER_NAME}</a>
										</td>
										<td>${var.WEIXIN}</td>
										<td>${var.PHONE_NUMBER}</td>
										<td>${var.MEMBE_POINT}</td>
										<td>
											<c:if test="${var.FOCUS_STATUS == '1' }">已关注</c:if>
											<c:if test="${var.FOCUS_STATUS == '0' }">未关注</c:if>
										</td>
										<td>
											<c:if test="${var.FOCUS_STATUS == '1' }">
											<fmt:formatDate value="${var.FOCUS_TIME}" pattern="yyyy-MM-dd HH:mm:ss" />
											</c:if>
										</td>
										<td>
											<c:if test="${var.REGISTER_STATUS == '1' }">已注册</c:if>
											<c:if test="${var.REGISTER_STATUS == '0' }">未注册</c:if>
										</td>
										<td><fmt:formatDate value="${var.REGISTER_TIME}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>
											${var.EXPERTS_NAME}
										</td>
										<td>
											${var.QR_NAME}  <c:if test="${empty var.QR_NAME}">其它</c:if>
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
				<td style="vertical-align:top;">
				</td>
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
			$("#Form").attr("action", "<%=basePath%>member/list");
			$("#Form").submit();
		}
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>member/goAdd';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>member/delete?MEMBER_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>member/goEdit?MEMBER_ID='+Id;
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
		
		//详情页面
		function detail(Id){
				var d='<%=basePath%>member/goDetail?MEMBER_ID='+Id;
				top.mainFrame.tabAddHandler("hyxq","会员详情",d);
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
								url: '<%=basePath%>member/deleteAll?tm='+new Date().getTime(),
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
			$("#Form").attr("action", "<%=basePath%>member/excel");
			$("#Form").submit();
		}
		
		function goChat(){
			top.mainFrame.tabAddHandler("member_chat","用户图形","member/goChat");
		}
		</script>
		
	</body>
</html>

