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
	
			<form action="guess/list" method="post" name="Form" id="Form">
				<table>
					<tr>
						<td>所属专家：
							<select name="expertsId" id="expertsId">
								<option value="">请选择</option>
								<c:forEach items="${experts}" var="expert">
									<option value="${expert.EXPERTS_ID}" <c:if test="${expert.EXPERTS_ID == pd.expertsId }">selected</c:if> >${expert.EXPERTS_NAME}</option>
								</c:forEach>
							</select>
						</td>
						<td>派奖状态：
							<select name="status" id="status">
								<option value="">请选择</option>
								<option value="0" <c:if test="${pd.status==false}">selected</c:if>>未派奖</option>
								<option value="1" <c:if test="${pd.status==true}">selected</c:if>>已派奖</option>
							</select>
						</td>
						<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"><i id="nav-search-icon" class="icon-search"></i></button></td>
					</tr>
				</table>
			
				<table id="table_report" class="table table-striped table-bordered table-hover" >
					
					<thead>
						<tr>
							<th class='center'>上线时间</th>
							<th class='center'>赛事</th>
							<th class='center'>主队</th>
							<th class='center'>客队</th>
							<th class='center'>比分</th>
							<th class='center'>参与人数</th>
							<th class='center'>积分盈亏</th>
							<th class='center'>所属专家</th>
							<th class="center">操作</th>
						</tr>
					</thead>
											
					<tbody>
						
					<!-- 开始循环 -->	
					<c:choose>
						<c:when test="${not empty guessGameList}">
							<c:if test="${QX.cha == 1 }">
							<c:forEach items="${guessGameList}" var="var" varStatus="vs">
								<tr>
									<td class='center' ><fmt:formatDate  value="${var.onlineTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td class='center' >${var.leagueName}</td>
									<td class='center' >${var.homeName}</td>
									<td class='center' >${var.awayName}</td>
									<c:choose>
										<c:when test="${var.homeScore!=null && var.awayScore!=null}">
											<td class='center' >${var.homeScore}:${var.awayScore}</td>
										</c:when>
										<c:otherwise>
											<td class='center' ></td>
										</c:otherwise>
									</c:choose>
									<td class='center' ><a style="text-decoration:none;cursor: pointer;" href="guess/memberGuessList?guessGameId=${var.id}">${var.memberCount}</a></td>
									<td class='center' ><a style="text-decoration:none;cursor: pointer;" href="guess/memberGuessList?guessGameId=${var.id}">${-var.profitAmount}</a></td>
									<td class='center' >${var.expertsName}</td>
									<td style="width: 60px;" class="center">
										<c:choose>
											<c:when test="${var.status}">
												<a style="text-decoration:none;cursor: pointer;" href="guess/memberGuessList?guessGameId=${var.id}">已派奖</a>
											</c:when>
											<c:otherwise>
												<c:if test="${QX.edit == 1 }">
													<a href="javascript:edit('${var.id}')" style="text-decoration:none;cursor: pointer;">修改</a>
													<a style="text-decoration:none;cursor: pointer;" href="javascript:paijiang('${var.id }')">派奖</a>
												</c:if>
											</c:otherwise>
										</c:choose>
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
						</td>
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

