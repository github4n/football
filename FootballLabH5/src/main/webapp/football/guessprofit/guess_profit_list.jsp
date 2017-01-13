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
	
			<form action="guessProfit/list" method="post" name="Form" id="Form">
				<table>
					<tr>
						<td>公众号：
							<select name="expertsId" id="expertsId">
								<option value="">请选择</option>
								<c:forEach items="${experts}" var="expert">
									<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == pd.expertsId }">selected</c:if> >${expert.EXPERTS_NAME}</option>
								</c:forEach>
							</select>
						</td>
						<td>竞猜玩法：
							<select name="serviceCode" id="serviceCode">
								<option value="">请选择</option>
								<option value="DC" <c:if test="${pd.serviceCode=='DC'}">selected</c:if>>单场</option>
								<option value="YP" <c:if test="${pd.serviceCode=='YP'}">selected</c:if>>亚盘</option>
								<option value="JCECY" <c:if test="${pd.serviceCode=='JCECY'}">selected</c:if>>二串一</option>
							</select>
						</td>
						<td>用户手机号：
							<input autocomplete="off" id="nav-search-input" type="text" name="phoneNumber" value="${pd.phoneNumber }"  />
						</td>
						<td>专家名称：
							<input autocomplete="off" id="nav-search-input" type="text" name="serviceName" value="${pd.serviceName }"  />
						</td>
					</tr>
					<tr>
						<td>
							开始时间：
							<input class="span10 date-picker" name="startDate" id="startDate" value="${pd.startDate}" type="text" data-date-format="yyyy-mm-dd"  style="width:205px;" placeholder="开始日期"/>
						</td>
						<td>
							截止时间：
							<input class="span10 date-picker" name="endDate" id="endDate" value="${pd.endDate}" type="text" data-date-format="yyyy-mm-dd"  style="width:219px;" placeholder="截止日期"/>
						</td>
						<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"><i id="nav-search-icon" class="icon-search"></i></button></td>
					</tr>
				</table>
				
				<div style="padding-top:8px;padding-bottom:10px">
					<div style="float: left;font-size: 16px;">
						竞猜数量：${totalInfo.totalCount }
					</div>
					<div style="padding-left:30px;float: left;font-size: 16px;">
						投入积分：${totalInfo.totalInput }
					</div>
					<div style="padding-left:30px;float: left;font-size: 16px;">
						中奖积分：${totalInfo.totalWin==null?0:totalInfo.totalWin }
					</div>
					<div style="padding-left:30px;float: left;font-size: 16px;">
						平台盈亏：${-totalInfo.totalProfit }
					</div>
					 <br style="clear:both">          
				</div>
			
				<table id="table_report" class="table table-striped table-bordered table-hover" >
					
					<thead>
						<tr>
							<th class='center'>微信号</th>
							<th class='center'>手机号</th>
							<th class='center'>竞猜时间</th>
							<th class='center'>专家名称</th>
							<th class='center'>竞猜玩法</th>
							<th class='center'>竞猜选项</th>
							<th class='center'>投入积分</th>
							<th class='center'>中奖积分</th>
							<th class='center'>盈亏</th>
							<th class="center">公众号</th>
						</tr>
					</thead>
											
					<tbody>
						
					<!-- 开始循环 -->	
					<c:choose>
						<c:when test="${not empty guessProfitList}">
							<c:if test="${QX.cha == 1 }">
							<c:forEach items="${guessProfitList}" var="var" varStatus="vs">
								<tr>
									<td class='center' >${var.memberName}</td>
									<td class='center' >${var.phoneNumber}</td>
									<td class='center' ><fmt:formatDate  value="${var.guessTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td class='center' >${var.serviceName}</td>
									<c:choose>
										<c:when test="${var.serviceCode=='DC'}">
											<td class='center' >单场</td>
										</c:when>
										<c:when test="${var.serviceCode=='YP'}">
											<td class='center' >亚盘</td>
										</c:when>
										<c:when test="${var.serviceCode=='JCECY'}">
											<td class='center' >二串一</td>
										</c:when>
									</c:choose>
									<td class='center' >
										<c:choose>
											<c:when test="${var.isProfit}">盈利</c:when>
											<c:otherwise>亏损</c:otherwise>
										</c:choose>
									</td>
									<td class='center' >${var.inputAmount}</td>
									<td class='center' >${var.winAmount}</td>
									<td class='center' >${var.profitAmount}</td>
									<td class='center' >${var.expertName}</td>
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
 	<div id="jiazai" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
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
	<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
	<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
	<!-- 引入 -->
	
	<script type="text/javascript">
	
	$(top.hangge());
	var basePath = '<%=basePath%>';
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
		 diag.URL = '<%=basePath%>guess/goAdd';
		 diag.Width = 400;
		 diag.Height = 490;
		 diag.CancelEvent = function(){ //关闭事件
			 if('${page.currentPage}' == '0'){
				 top.jzts();
				 setTimeout("self.location=self.location",100);
			 }else{
				window.location.reload(true);
			 }
			diag.close();
		 };
		 diag.show();
	}
	
	
	//修改
	function edit(id){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑";
		 diag.URL = '<%=basePath%>guess/goEdit?id='+id;
		 diag.Width = 600;
		 diag.Height = 465;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 window.location.reload(true);
			}
			diag.close();
		 };
		 diag.show();
	}
	
	function paijiang(id){
		bootbox.confirm("确定要进行派奖?", function(result) {
			if(result) {
				$("#jiazai").show();
				var ajaxResult;
				$.ajax({
					url:basePath+'/guess/paijiang?guessGameId='+id,
					type:'post',
					async:false,
					success:function(data){
						ajaxResult = data;
					}
				});
				$("#jiazai").hide();
				bootbox.alert(ajaxResult.msg,function(){window.location.reload(true);});
			}
		});
		
	}
	
	</script>
</body>
</html>

