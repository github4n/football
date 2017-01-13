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
			<form action="coupon/list" method="post" name="Form" id="Form">
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
					
					<td>礼券名称：	
							<input autocomplete="off"  type="text" name="coupon_name" id="coupon_name" value="${pd.coupon_name}"  />
					</td>
					<td>审核状态：
							<select name="approval_state" id="approval_state" class="chzn-select">
								<option value="">--全部--</option>
								<option value="1" <c:if test="${pd.approval_state == '1'}">selected</c:if>>未审批</option>
								<option value="2" <c:if test="${pd.approval_state == '2'}">selected</c:if>>不同意</option>
								<option value="3" <c:if test="${pd.approval_state == '3'}">selected</c:if>>同意</option>
								<option value="9" <c:if test="${pd.approval_state == '9'}">selected</c:if>>已下线</option>
							</select>
					</td>
					<td>编号：
						<input type="text" name="no" id="no" value="${pd.no}" />
					</td>
				</tr>
				<tr>
					<td>开始时间：<input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="开始日期"/></td>
					<td>结束时间：<input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd"  style="width:88px;" placeholder="结束日期"/></td>
					<!-- <td>运行状态：	<select name="" id="" class="chzn-select">
								<option value="">--全部--</option>
								<option value="0" >已上线</option>
								<option value="1">暂停</option>
								<option value="2">未上线</option>
								<option value="3">过期</option>
							</select>
					 --></td>
					<td>用途：	<select name="coupon_type" id="coupon_type" class="chzn-select">
									<option value="">--全部--</option>
									<option value="01" <c:if test="${'01' == pd.coupon_type }">selected</c:if>>方案卷-竞彩胜平负</option>
									<option value="02" <c:if test="${'02' == pd.coupon_type }">selected</c:if>>方案卷-竞彩让球胜平负</option>
									<option value="03" <c:if test="${'03' == pd.coupon_type }">selected</c:if>>方案卷-竞彩2串1</option>
									<option value="04" <c:if test="${'04' == pd.coupon_type }">selected</c:if>>方案卷-竞彩让球2串1</option>
									<option value="05" <c:if test="${'05' == pd.coupon_type }">selected</c:if>>方案卷-亚盘</option>
									<option value="06" <c:if test="${'06' == pd.coupon_type }">selected</c:if>>方案卷-单场</option>
									<option value="07" <c:if test="${'07' == pd.coupon_type }">selected</c:if>>方案卷-足彩14场</option>
									<option value="08" <c:if test="${'08' == pd.coupon_type }">selected</c:if>>方案卷-足彩R9</option>
						 			<option value="1" <c:if test="${'1' == pd.coupon_type }">selected</c:if>>通用卷</option>
									<option value="2" <c:if test="${'2' == pd.coupon_type }">selected</c:if>>折扣卷</option>
									<option value="3" <c:if test="${'3' == pd.coupon_type }">selected</c:if>>现金抵扣卷</option>
							</select>
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
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th>序号</th>
						<th>编号</th>
						<th>礼券名称</th>
						<th>所属专家</th>
						<th>创建时间</th>
						<th>张数</th>
						<th>审批状态</th>
						<!--<th>上线时间</th>-->
						<!-- <th>运行状态</th> -->
						<th>用途</th>
						<th>已领取</th>
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
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.coupon_id}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.no}</td>
										<td>${var.coupon_name}</td>
										<td>${var.experts_name}</td>
										<td><fmt:formatDate value="${var.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${var.num}</td>
										<td>
											<c:if test="${var.approval_state == '1'}">未审批</c:if>
											<c:if test="${var.approval_state == '2'}">不同意</c:if>
											<c:if test="${var.approval_state == '3'}">同意</c:if>
											<c:if test="${var.approval_state == '9'}">已下线</c:if>
										</td>
										<!--<td>${var.validity_time}</td>-->
										<!-- <td>--</td> -->
										<td>
										<c:if test="${var.coupon_type == '01'}">方案券-竞彩胜平负</c:if>
										<c:if test="${var.coupon_type == '02'}">方案券-竞彩让球胜平负</c:if>
										<c:if test="${var.coupon_type == '03'}">方案券-竞彩2串1</c:if>
										<c:if test="${var.coupon_type == '04'}">方案券-竞彩让球2串1</c:if>
										<c:if test="${var.coupon_type == '05'}">方案券-亚盘</c:if>
										<c:if test="${var.coupon_type == '06'}">方案券-单场</c:if>
										<c:if test="${var.coupon_type == '07'}">方案券-足彩14场</c:if>
										<c:if test="${var.coupon_type == '08'}">方案券-足彩R9</c:if>
										<c:if test="${var.coupon_type == '1'}">通用券</c:if>
										<c:if test="${var.coupon_type == '2'}">折扣券</c:if>
										<c:if test="${var.coupon_type == '3'}">现金抵扣券</c:if>
										<c:if test="${var.coupon_type == '4'}">单联赛通用券</c:if>
										</td>
										<td><a href="javascript:situationList('${var.id}')"  style="text-decoration: underline;" target="mainFrame">${var.coupon_count}</a></td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<c:if test="${QX.edit == 1 }">
											<li><a style="cursor:pointer;" title="编辑" onclick="edit('${var.coupon_id}','${var.approval_state}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											</c:if>
											<c:if test="${QX.del == 1 }">
											<li><a style="cursor:pointer;" title="删除" onclick="del('${var.coupon_id}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
											</c:if>
											<li><a style="cursor:pointer;" title="详情" onclick="detaile('${var.coupon_id}');" class="tooltip-details" data-rel="tooltip" title="" data-placement="left"><span class="yellow"><i class="icon-search"></i></span></a></li>
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
				<td style="vertical-align:top;">
					<c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
					<c:if test="${QX.del == 1 }">
					<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
					</c:if>
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
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>coupon/goAdd';
			 diag.Width = 450;
			 diag.Height = 500;
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
					var url = "<%=basePath%>coupon/delete?coupon_id="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id,approval_state){
	/* 		if("1" != approval_state){
				alert("只有未审批状态的优惠券才能修改");
				return;
			}	 */
		
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>coupon/goEdit?coupon_id='+Id;
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
		
		//详情
		function detaile(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="详情";
			 diag.URL = '<%=basePath%>coupon/goDetaile?coupon_id='+Id;
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
		
		//已领取详情
		function situationList(coupon_detail_id){
			top.mainFrame.tabAddHandler("coupon_lq_detaile","领取详情","coupon/situationList?coupon_detail_id="+coupon_detail_id);
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

