<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/css/chosen.css" />

<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />

<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<script type="text/javascript">
	//保存
	function save() {
		var data = '';
		var obj = document.getElementsByName("id");
		for(i=0;i<obj.length;i++){
			if(9 == type){
				continue;
			}
			var id = obj[i].value;
			var type = $("#"+id+"type").val();
			var prize_name = $("#"+id+"prize_name").val();
			
			var numOrCouponId = null;
			if(0 == type){
				var prize = $("#"+id+"prize_name_select").val();
				numOrCouponId = prize.split(',')[0]
			}else if(1 == type){
				numOrCouponId =$("#"+id+"points_num").val();
			}
			
			var scale = $("#"+id+"scale").val();
			if(null == type || "" == type){
				alert("请选择奖品种类");
				return;
			}
			if(null == prize_name || "" == prize_name){
				alert("奖品不能为空");
				return;
			}
			if(null == scale || "" == scale){
				alert("请输入抽奖几率");
				return;
			}
			
			var item = type+":"+id+":"+prize_name+":"+numOrCouponId+":"+scale+","
			data = data+item;
		}
		$("#zhongxin").hide();
		$("#zhongxin2").show();
		var managerId = $("#managerId").val();
		$.ajax( {
			url : "<%=basePath%>activityM/editDetaile",
			type : "post",
			data : {data:data,managerId:managerId},
			success : function(data) {
				top.Dialog.close();
				
			},
			failure : function() {alert("无法连接服务器！");}
		});
	}
	
	function prizeNameChange(id){
		var prize = $("#"+id+"prize_name_select").val();
		$("#"+id+"number").html(prize.split(',')[1]);
		$("#"+id+"prize_name").val($("#"+id+"prize_name_select").find("option:selected").text());
	}
	
	function typeChange(id){
		var type = $("#"+id+"type").val();
		if(0==type){
			$("#"+id+"prize_name_select").show();
			$("#"+id+"points_num").hide();
			prizeNameChange(id);
		}else if(1==type){
			$("#"+id+"prize_name_select").hide();
			$("#"+id+"points_num").show();
			
			pointsChange(id);
		}else if(2==type){
			$("#"+id+"prize_name_select").hide();
			$("#"+id+"points_num").hide();
			
			$("#"+id+"prize_name").val("再来一次");
		}
	}
	
	function pointsChange(id){
		var points_num = $("#"+id+"points_num").val();
		if(null!=points_num && ""!=points_num){	
			$("#"+id+"prize_name").val($("#"+id+"points_num").val()+"积分");
		}else{
			$("#"+id+"prize_name").val("");
		}
	}
</script>
</head>
<body>
	<form action="activityM/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="managerId" id="managerId" value="${pd.id}" />
		<div id="zhongxin">
			<table id="table_report"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>奖品种类</th>
						<th>奖品名称</th>
						<th>剩余张数</th>
						<th>比例分配</th>
					</tr>
				</thead>

				<tbody>
					<!-- 开始循环 -->
					<c:forEach items="${varList}" var="var" varStatus="vs">
						<input type="hidden" name="id" id="${var.id}id" value="${var.id}" />
						<tr>
							<td class='center' style="width: 30px;">${vs.index+1}</td>
							<td>
								<select name="type" id="${var.id}type" onchange="typeChange('${var.id}')">
									<option value="0" <c:if test="${not empty var.fk_tb_coupon_detail_id}">selected</c:if>>优惠券</option>
									<option value="1" <c:if test="${empty var.fk_tb_coupon_detail_id && '再玩一次'!=var.prize_name}">selected</c:if>>积分</option>
									<option value="2" <c:if test="${'再玩一次'==var.prize_name}">selected</c:if>>再玩一次 </option>
								</select>
								
								<select name="prize_name" id="${var.id}prize_name_select" onchange="prizeNameChange('${var.id}')"  <c:if test="${empty var.fk_tb_coupon_detail_id}">style="display: none;"</c:if>>
									<c:forEach items="${jxList}" var="var1" varStatus="vs">
										<option value="${var1.id},${var1.num}" <c:if test="${var1.id == var.fk_tb_coupon_detail_id}">selected</c:if>>${var1.name}</option>
									</c:forEach>
								</select>
								
								<input type="number" name="points_num" id="${var.id}points_num"  <c:if test="${empty var.fk_tb_coupon_detail_id && '再玩一次'!=var.prize_name}"> value="${var.number}" </c:if> <c:if test="${not empty var.fk_tb_coupon_detail_id || '再玩一次'==var.prize_name}">style="display: none;"</c:if>   onkeyup="pointsChange('${var.id}')"/>
						
							</td>
							<td>
								<input type="text" name="prize_name" id="${var.id}prize_name" readonly="readonly" value="${var.prize_name}"/>
							</td>
							<td>
								<div id="${var.id}number"></div>
							</td>
							<td>
							<input type="number" name="scale" id="${var.id}scale"value="${var.scale}" maxlength="32"
								placeholder="这里输入比例" title="这里输入比例" /><span><b>%</b></span>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td style="text-align: center;" colspan="5">
							<a class="btn btn-mini btn-primary" onclick="save();">保存</a> 
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
							</td>
						</tr>
				</tbody>
			</table>
		</div>

		<div id="zhongxin2" class="center" style="display: none">
			<br /> <br /> <br /> <br /> <br /> <img
				src="static/images/jiazai.gif" /><br />
			<h4 class="lighter block green">提交中...</h4>
		</div>

	</form>


	<!-- 引入 -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript">
		$(top.hangge());
		$(function() {

			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

			//日期框
			$('.date-picker').datepicker();

			var obj = document.getElementsByName("id");
			for(i=0;i<obj.length;i++){
				var prize = $("#"+obj[i].value+"prize_name").val();
				$("#"+obj[i].value+"number").html(prize.split(',')[1])
			}
		});
	</script>
</body>
</html>