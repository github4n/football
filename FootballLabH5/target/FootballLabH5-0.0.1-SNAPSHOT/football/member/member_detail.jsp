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
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<div class="container-fluid" id="main-container">



<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


 	<div class="span12">
		<div class="widget-box">
			<div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">
				<h4 class="lighter">会员详情</h4>
			</div>
			<div class="widget-body">
			 
			 
			 <div class="widget-main">
			 
			 
			 
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">&nbsp;&nbsp;微信号:</span></label>
						<label style="float:left;padding-left: 15px;margin-top: -5px;"><input type="text" name="WEIXIN" id="WEIXIN" value="${pd.WEIXIN}" maxlength="32" placeholder="微信" title="微信" disabled="disabled"/></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">&nbsp;&nbsp;手机号:</span></label>
						<label style="float:left;padding-left: 15px;margin-top: -5px;"><input type="text" name="PHONE_NUMBER" id="PHONE_NUMBER" value="${pd.PHONE_NUMBER}" maxlength="32" placeholder="手机号" title="手机号" disabled="disabled"/></label>
						<c:if test="${pd.PHONE_NUMBER!=null}">
						<label style="float:left;padding-left: 15px;"><a style="cursor:pointer;" title="发送短信" onclick="sendContent('${pd.MEMBER_ID}');"><span class="lbl">发送信息</span></a></label>
						</c:if>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">所属专家:</span></label>
						<label style="float:left;padding-left: 15px;margin-top: -5px;"><input type="text" name="EXPERTS_NAME" id="EXPERTS_NAME" value="${pd.EXPERTS_NAME}" maxlength="32" placeholder="所属专家" title="所属专家" disabled="disabled"/></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">关注时间:</span></label>
						<label style="float:left;padding-left: 15px;margin-top: -5px;"><input type="text" name="FOCUS_TIME" id="FOCUS_TIME" value="${pd.FOCUS_TIME}" maxlength="32" placeholder="关注时间" title="关注时间" disabled="disabled"/></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">注册时间:</span></label>
						<label style="float:left;padding-left: 15px;margin-top: -5px;"><input type="text" name="REGISTER_TIME" id="REGISTER_TIME" value="${pd.REGISTER_TIME}" maxlength="32" placeholder="注册时间" title="注册时间" disabled="disabled"/></label>
					</div>
	<%-- 			<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">账号状态:</span></label>
						<label style="float:left;padding-left: 15px;margin-top: -5px;"><input type="text" name="EXPERTS_NAME" id="EXPERTS_NAME" value="${pd.EXPERTS_NAME}" maxlength="32" placeholder="账号状态" title="账号状态" disabled="disabled"/></label>
					</div> --%>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">&nbsp;&nbsp;优惠券:</span></label>
						<label style="float:left;padding-left: 15px;"><a style="cursor:pointer;" title="优惠券" onclick="detailCoupon('${pd.MEMBER_ID}','${pd.MEMBER_NAME}');"><span class="lbl">详情</span></a></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">订单记录:</span></label>
						<label style="float:left;padding-left: 15px;"><a style="cursor:pointer;" title="订单详情" onclick="detailOrder('${pd.MEMBER_ID}','${pd.MEMBER_NAME}');"><span class="lbl">详情</span></a></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">交易记录:</span></label>
						<label style="float:left;padding-left: 15px;"><a style="cursor:pointer;" title="交易记录" onclick="detailTrade('${pd.MEMBER_ID}','${pd.MEMBER_NAME}');"><span class="lbl">详情</span></a></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">pk方案:</span></label>
						<label style="float:left;padding-left: 15px;"><a style="cursor:pointer;" title="积分详情" onclick="detailPk('${pd.MEMBER_ID}','${pd.MEMBER_NAME}');"><span class="lbl">详情</span></a></label>
					</div>
					<div class="step-content row-fluid position-relative">
						<label style="float:left;padding-left: 35px;"><span class="lbl">积分余额:</span></label>
						<label style="float:left;padding-left: 15px;"><a style="cursor:pointer;" title="积分详情" onclick="detailPoint('${pd.MEMBER_ID}','${pd.MEMBER_NAME}');"><span class="lbl">${pd.MEMBE_POINT}</span></a></label>
					</div>
				 
			 </div><!--/widget-main-->
			</div><!--/widget-body-->
		</div>
	</div>
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		
		//优惠券详情
		function detailCoupon(Id,memberName){
				var url='<%=basePath%>coupon/myCoupon?member_name='+memberName+'&member_id='+Id;
				top.mainFrame.tabAddHandler("yhqjj","优惠券记录",url);
		}
		
		//订单记录
		function detailOrder(Id,memberName){
				var url='<%=basePath%>transaction/list?member_name='+memberName+'&member_id='+Id;
				top.mainFrame.tabAddHandler("ddjl","订单记录",url);
		}
		//交易记录
		function detailTrade(Id,memberName){
				var url='<%=basePath%>trade/list?member_name='+memberName+'&member_id='+Id;
				top.mainFrame.tabAddHandler("jyjl","交易记录",url);
		}
		//PK方案
		function detailPk(Id,memberName){
				var url='<%=basePath%>pkManager/list?member_name='+memberName+'&member_id='+Id;
				top.mainFrame.tabAddHandler("pkjl","PK赛记录",url);
		}
		//积分记录
		function detailPoint(Id,memberName){
				var url='<%=basePath%>points/list?member_name='+memberName+'&member_id='+Id;
				top.mainFrame.tabAddHandler("jfxq","积分详情",url);
		}
		
		function sendContent(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送短信";
			 diag.URL = '<%=basePath%>member/toSendPage?member_id='+Id;
			 diag.Width = 450;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				alert("已发送");
				diag.close();
			 };
			 diag.show();
		}
		
		
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>