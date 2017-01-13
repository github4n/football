<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="<%=basePath%>/wechat/7mPage/css/page.css" />
		</head>
	<script type="text/javascript" src="<%=basePath%>/wechat/7mPage/js/jquery-1.10.2.min.js" ></script>
	<script type="text/javascript" src="<%=basePath%>/wechat/7mPage/js/page.js" ></script>
	<body>
	<!--弹出层div-->
	<div class="cd-popup">
   		<div class="cd-popup-container">
        	<p class="cb-title">100%盈利</p>
            <img src="<%=basePath%>/wechat/7mPage/img/code.png" />
            <p class="cb-des">扫一扫免费查看推荐</p>
    	</div>
	</div>
	<!--弹出层div-->	
	<div class="bg_banner"></div><!--背景-->
	<div class="allBox">
		<div class="header">
			<div class="h_left">
				<img src="<%=basePath%>/wechat/7mPage/img/logo.png" />
			</div>
			<div class="h_right">
				<img src="<%=basePath%>/wechat/7mPage/img/logodes.png" class="hr_img" />
				<div style="display:inline-block;height: 34px;width:286px;">
					<div class="imgshow">
					<span>100%盈利</span>
				</div>
				</div>
			</div>
		</div>
		<!--头部-->
		<div class="banner">
			<div class="b_left">
				<p>数据挖掘&nbsp;&nbsp;建立模型</p>
				<p>赛事预测&nbsp;&nbsp;实现盈利</p>
				<div class="b_des">微信公众号：dqll310</div>
			</div>
			<div class="b_right">
				<img src="<%=basePath%>/wechat/7mPage/img/phone.png" />
			</div>
		</div>
		<!--banner-->
		<div class="container">
			<div class="ab">
				<div class="a">单场推荐
					<div class="aa"></div>
				</div>
				<div class="b">不盈利 双倍返!</div>
			</div><!--csstitle-->
			<div class="con_list">
				<table class="list_table">
					<thead>
						<tr>
							<th>开赛时间</th>
							<th>赛事</th>
							<th>主队</th>
							<th>比分</th>
							<th>客队</th>
							<th>推荐方案</th>
							<th>投入</th>
							<th>中奖</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${singleList}" var="item">
							<tr>
								<td>${item.gameDate }</td>
								<td >${item.leagueName }</td>
								<td >${item.homeName }</td>
								<c:choose>
									<c:when test="${item.status==1}">
										<td>—&nbsp;:&nbsp;—</td>
										<td>${item.awayName }</td>
										<td>
											<div class="btn_see"><a href="#">免费查看</a></div>
										</td>
										<td class="t_colorY">微信公众号:dqll310</td>
										<td class="t_colorY">微信公众号:dqll310</td>
									</c:when>
									<c:otherwise>
										<td>${item.scoreInfo }</td>
										<td >${item.awayName }</td>
										<td class="t_color">${item.scheme }</td>
										<td >${item.inputAmount }</td>
										<td class="t_color">${item.profitAmount }</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--结束-->
		<div class="container">
			<div class="ab">
				<div class="a">亚盘推荐
					<div class="aa"></div>
				</div>
				<div class="b">不盈利 双倍返!</div>
			</div><!--csstitle-->
			<div class="con_list">
				<table class="list_table_2">
					<thead>
						<tr>
							<th>开赛时间</th>
							<th>赛事</th>
							<th>主队</th>
							<th>盘口</th>
							<th>客队</th>
							<th>比分</th>
							<th>推荐方案</th>
							<th>投入</th>
							<th>中奖</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${asianList}" var="item">
							<tr>
								<td>${item.gameDate }</td>
								<td >${item.leagueName }</td>
								<td >${item.homeName }</td>
								<td>${item.handicap }</td>
								<td >${item.awayName }</td>
								<c:choose>
									<c:when test="${item.status==1}">
										<td>—&nbsp;:&nbsp;—</td>
										<td>
											<div class="btn_see"><a href="#">免费查看</a></div>
										</td>
										<td class="t_colorY">微信公众号:dqll310</td>
										<td class="t_colorY">微信公众号:dqll310</td>
									</c:when>
									<c:otherwise>
										<td>${item.scoreInfo }</td>
										<td class="t_color">${item.scheme }</td>
										<td>${item.inputAmount }</td>
										<td class="t_color">${item.profitAmount }</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
						<!-- <tr>
							<td>10/20 02:45</td>
							<td>巴甲</td>
							<td>曼联</td>
							<td>半一盘</td>
							<td>利物浦</td>
							<td>-:-</td>
							<td>平 3.00</td>
							<td class="t_colorY">1000</td>
							<td>1000</td>
						</tr>
						<tr>
							<td>10/20 02:45</td>
							<td>巴甲</td>
							<td>曼联</td>
							<td>半一盘</td>
							<td>利物浦</td>
							<td>-:-</td>
							<td>平 3.00</td>
							<td class="t_colorY">微信公众号:dqll310</td>
							<td>1000</td>
						</tr> -->
					</tbody>
				</table>
			</div>
		</div>
		<!--结束-->
	</div>
	</body>
</html>

