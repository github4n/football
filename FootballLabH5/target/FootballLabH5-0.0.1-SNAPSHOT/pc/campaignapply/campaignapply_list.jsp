<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<form action="campaignapply/pc/list" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="CAMPAIGN_TITLE" value="${pd.CAMPAIGN_TITLE}" placeholder="这里输入活动名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="MEMBER_NAME" value="${pd.MEMBER_NAME}" placeholder="这里输入姓名" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<%-- <td><input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期"/></td>
					<td><input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期"/></td> --%>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="STATUS" data-placeholder="状态" style="vertical-align:top;width: 79px;">
						<option value=""></option>
						<option value="1" <c:if test="${pd.STATUS == '1' }">selected</c:if> >已受理</option>
						<option value="0" <c:if test="${pd.STATUS == '0' }">selected</c:if> >未受理</option>
						</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="City_NAME" data-placeholder="城市" style="vertical-align:top;width: 79px;">
						<option value="" ></option>
	<option value="北京"<c:if test="${pd.City_NAME == '北京' }">selected</c:if> >北京</option>
<option value="天津"<c:if test="${pd.City_NAME == '天津' }">selected</c:if> >天津</option>
<option value="上海"<c:if test="${pd.City_NAME == '上海' }">selected</c:if> >上海</option>
<option value="重庆"<c:if test="${pd.City_NAME == '重庆' }">selected</c:if> >重庆</option>
<option value="石家庄"<c:if test="${pd.City_NAME == '石家庄' }">selected</c:if> >石家庄</option>
<option value="唐山"<c:if test="${pd.City_NAME == '唐山' }">selected</c:if> >唐山</option>
<option value="秦皇岛"<c:if test="${pd.City_NAME == '秦皇岛' }">selected</c:if> >秦皇岛</option>
<option value="邯郸"<c:if test="${pd.City_NAME == '邯郸' }">selected</c:if> >邯郸</option>
<option value="邢台"<c:if test="${pd.City_NAME == '邢台' }">selected</c:if> >邢台</option>
<option value="保定"<c:if test="${pd.City_NAME == '保定' }">selected</c:if> >保定</option>
<option value="张家口"<c:if test="${pd.City_NAME == '张家口' }">selected</c:if> >张家口</option>
<option value="承德"<c:if test="${pd.City_NAME == '承德' }">selected</c:if> >承德</option>
<option value="沧州"<c:if test="${pd.City_NAME == '沧州' }">selected</c:if> >沧州</option>
<option value="廊坊"<c:if test="${pd.City_NAME == '廊坊' }">selected</c:if> >廊坊</option>
<option value="衡水"<c:if test="${pd.City_NAME == '衡水' }">selected</c:if> >衡水</option>
<option value="太原"<c:if test="${pd.City_NAME == '太原' }">selected</c:if> >太原</option>
<option value="大同"<c:if test="${pd.City_NAME == '大同' }">selected</c:if> >大同</option>
<option value="阳泉"<c:if test="${pd.City_NAME == '阳泉' }">selected</c:if> >阳泉</option>
<option value="长治"<c:if test="${pd.City_NAME == '长治' }">selected</c:if> >长治</option>
<option value="晋城"<c:if test="${pd.City_NAME == '晋城' }">selected</c:if> >晋城</option>
<option value="朔州"<c:if test="${pd.City_NAME == '朔州' }">selected</c:if> >朔州</option>
<option value="晋中"<c:if test="${pd.City_NAME == '晋中' }">selected</c:if> >晋中</option>
<option value="运城"<c:if test="${pd.City_NAME == '运城' }">selected</c:if> >运城</option>
<option value="忻州"<c:if test="${pd.City_NAME == '忻州' }">selected</c:if> >忻州</option>
<option value="临汾"<c:if test="${pd.City_NAME == '临汾' }">selected</c:if> >临汾</option>
<option value="吕梁"<c:if test="${pd.City_NAME == '吕梁' }">selected</c:if> >吕梁</option>
<option value="呼和浩特"<c:if test="${pd.City_NAME == '呼和浩特' }">selected</c:if> >呼和浩特</option>
<option value="包头"<c:if test="${pd.City_NAME == '包头' }">selected</c:if> >包头</option>
<option value="乌海"<c:if test="${pd.City_NAME == '乌海' }">selected</c:if> >乌海</option>
<option value="赤峰"<c:if test="${pd.City_NAME == '赤峰' }">selected</c:if> >赤峰</option>
<option value="通辽"<c:if test="${pd.City_NAME == '通辽' }">selected</c:if> >通辽</option>
<option value="鄂尔多斯"<c:if test="${pd.City_NAME == '鄂尔多斯' }">selected</c:if> >鄂尔多斯</option>
<option value="呼伦贝尔"<c:if test="${pd.City_NAME == '呼伦贝尔' }">selected</c:if> >呼伦贝尔</option>
<option value="巴彦淖尔"<c:if test="${pd.City_NAME == '巴彦淖尔' }">selected</c:if> >巴彦淖尔</option>
<option value="乌兰察布"<c:if test="${pd.City_NAME == '乌兰察布' }">selected</c:if> >乌兰察布</option>
<option value="兴安"<c:if test="${pd.City_NAME == '兴安' }">selected</c:if> >兴安</option>
<option value="锡林郭勒"<c:if test="${pd.City_NAME == '锡林郭勒' }">selected</c:if> >锡林郭勒</option>
<option value="阿拉善"<c:if test="${pd.City_NAME == '阿拉善' }">selected</c:if> >阿拉善</option>
<option value="沈阳"<c:if test="${pd.City_NAME == '沈阳' }">selected</c:if> >沈阳</option>
<option value="大连"<c:if test="${pd.City_NAME == '大连' }">selected</c:if> >大连</option>
<option value="鞍山"<c:if test="${pd.City_NAME == '鞍山' }">selected</c:if> >鞍山</option>
<option value="抚顺"<c:if test="${pd.City_NAME == '抚顺' }">selected</c:if> >抚顺</option>
<option value="本溪"<c:if test="${pd.City_NAME == '本溪' }">selected</c:if> >本溪</option>
<option value="丹东"<c:if test="${pd.City_NAME == '丹东' }">selected</c:if> >丹东</option>
<option value="锦州"<c:if test="${pd.City_NAME == '锦州' }">selected</c:if> >锦州</option>
<option value="营口"<c:if test="${pd.City_NAME == '营口' }">selected</c:if> >营口</option>
<option value="阜新"<c:if test="${pd.City_NAME == '阜新' }">selected</c:if> >阜新</option>
<option value="辽阳"<c:if test="${pd.City_NAME == '辽阳' }">selected</c:if> >辽阳</option>
<option value="盘锦"<c:if test="${pd.City_NAME == '盘锦' }">selected</c:if> >盘锦</option>
<option value="铁岭"<c:if test="${pd.City_NAME == '铁岭' }">selected</c:if> >铁岭</option>
<option value="朝阳"<c:if test="${pd.City_NAME == '朝阳' }">selected</c:if> >朝阳</option>
<option value="葫芦岛"<c:if test="${pd.City_NAME == '葫芦岛' }">selected</c:if> >葫芦岛</option>
<option value="长春"<c:if test="${pd.City_NAME == '长春' }">selected</c:if> >长春</option>
<option value="吉林"<c:if test="${pd.City_NAME == '吉林' }">selected</c:if> >吉林</option>
<option value="四平"<c:if test="${pd.City_NAME == '四平' }">selected</c:if> >四平</option>
<option value="辽源"<c:if test="${pd.City_NAME == '辽源' }">selected</c:if> >辽源</option>
<option value="通化"<c:if test="${pd.City_NAME == '通化' }">selected</c:if> >通化</option>
<option value="白山"<c:if test="${pd.City_NAME == '白山' }">selected</c:if> >白山</option>
<option value="松原"<c:if test="${pd.City_NAME == '松原' }">selected</c:if> >松原</option>
<option value="白城"<c:if test="${pd.City_NAME == '白城' }">selected</c:if> >白城</option>
<option value="延边"<c:if test="${pd.City_NAME == '延边' }">selected</c:if> >延边</option>
<option value="哈尔滨"<c:if test="${pd.City_NAME == '哈尔滨' }">selected</c:if> >哈尔滨</option>
<option value="齐齐哈尔"<c:if test="${pd.City_NAME == '齐齐哈尔' }">selected</c:if> >齐齐哈尔</option>
<option value="鸡西"<c:if test="${pd.City_NAME == '鸡西' }">selected</c:if> >鸡西</option>
<option value="鹤岗"<c:if test="${pd.City_NAME == '鹤岗' }">selected</c:if> >鹤岗</option>
<option value="双鸭山"<c:if test="${pd.City_NAME == '双鸭山' }">selected</c:if> >双鸭山</option>
<option value="大庆"<c:if test="${pd.City_NAME == '大庆' }">selected</c:if> >大庆</option>
<option value="伊春"<c:if test="${pd.City_NAME == '伊春' }">selected</c:if> >伊春</option>
<option value="佳木斯"<c:if test="${pd.City_NAME == '佳木斯' }">selected</c:if> >佳木斯</option>
<option value="七台河"<c:if test="${pd.City_NAME == '七台河' }">selected</c:if> >七台河</option>
<option value="牡丹江"<c:if test="${pd.City_NAME == '牡丹江' }">selected</c:if> >牡丹江</option>
<option value="黑河"<c:if test="${pd.City_NAME == '黑河' }">selected</c:if> >黑河</option>
<option value="绥化"<c:if test="${pd.City_NAME == '绥化' }">selected</c:if> >绥化</option>
<option value="大兴安岭"<c:if test="${pd.City_NAME == '大兴安岭' }">selected</c:if> >大兴安岭</option>
<option value="南京"<c:if test="${pd.City_NAME == '南京' }">selected</c:if> >南京</option>
<option value="无锡"<c:if test="${pd.City_NAME == '无锡' }">selected</c:if> >无锡</option>
<option value="徐州"<c:if test="${pd.City_NAME == '徐州' }">selected</c:if> >徐州</option>
<option value="常州"<c:if test="${pd.City_NAME == '常州' }">selected</c:if> >常州</option>
<option value="苏州"<c:if test="${pd.City_NAME == '苏州' }">selected</c:if> >苏州</option>
<option value="南通"<c:if test="${pd.City_NAME == '南通' }">selected</c:if> >南通</option>
<option value="连云港"<c:if test="${pd.City_NAME == '连云港' }">selected</c:if> >连云港</option>
<option value="淮安"<c:if test="${pd.City_NAME == '淮安' }">selected</c:if> >淮安</option>
<option value="盐城"<c:if test="${pd.City_NAME == '盐城' }">selected</c:if> >盐城</option>
<option value="扬州"<c:if test="${pd.City_NAME == '扬州' }">selected</c:if> >扬州</option>
<option value="镇江"<c:if test="${pd.City_NAME == '镇江' }">selected</c:if> >镇江</option>
<option value="泰州"<c:if test="${pd.City_NAME == '泰州' }">selected</c:if> >泰州</option>
<option value="宿迁"<c:if test="${pd.City_NAME == '宿迁' }">selected</c:if> >宿迁</option>
<option value="杭州"<c:if test="${pd.City_NAME == '杭州' }">selected</c:if> >杭州</option>
<option value="宁波"<c:if test="${pd.City_NAME == '宁波' }">selected</c:if> >宁波</option>
<option value="温州"<c:if test="${pd.City_NAME == '温州' }">selected</c:if> >温州</option>
<option value="嘉兴"<c:if test="${pd.City_NAME == '嘉兴' }">selected</c:if> >嘉兴</option>
<option value="湖州"<c:if test="${pd.City_NAME == '湖州' }">selected</c:if> >湖州</option>
<option value="绍兴"<c:if test="${pd.City_NAME == '绍兴' }">selected</c:if> >绍兴</option>
<option value="金华"<c:if test="${pd.City_NAME == '金华' }">selected</c:if> >金华</option>
<option value="衢州"<c:if test="${pd.City_NAME == '衢州' }">selected</c:if> >衢州</option>
<option value="舟山"<c:if test="${pd.City_NAME == '舟山' }">selected</c:if> >舟山</option>
<option value="台州"<c:if test="${pd.City_NAME == '台州' }">selected</c:if> >台州</option>
<option value="丽水"<c:if test="${pd.City_NAME == '丽水' }">selected</c:if> >丽水</option>
<option value="合肥"<c:if test="${pd.City_NAME == '合肥' }">selected</c:if> >合肥</option>
<option value="芜湖"<c:if test="${pd.City_NAME == '芜湖' }">selected</c:if> >芜湖</option>
<option value="蚌埠"<c:if test="${pd.City_NAME == '蚌埠' }">selected</c:if> >蚌埠</option>
<option value="淮南"<c:if test="${pd.City_NAME == '淮南' }">selected</c:if> >淮南</option>
<option value="马鞍山"<c:if test="${pd.City_NAME == '马鞍山' }">selected</c:if> >马鞍山</option>
<option value="淮北"<c:if test="${pd.City_NAME == '淮北' }">selected</c:if> >淮北</option>
<option value="铜陵"<c:if test="${pd.City_NAME == '铜陵' }">selected</c:if> >铜陵</option>
<option value="安庆"<c:if test="${pd.City_NAME == '安庆' }">selected</c:if> >安庆</option>
<option value="黄山"<c:if test="${pd.City_NAME == '黄山' }">selected</c:if> >黄山</option>
<option value="滁州"<c:if test="${pd.City_NAME == '滁州' }">selected</c:if> >滁州</option>
<option value="阜阳"<c:if test="${pd.City_NAME == '阜阳' }">selected</c:if> >阜阳</option>
<option value="宿州"<c:if test="${pd.City_NAME == '宿州' }">selected</c:if> >宿州</option>
<option value="六安"<c:if test="${pd.City_NAME == '六安' }">selected</c:if> >六安</option>
<option value="亳州"<c:if test="${pd.City_NAME == '亳州' }">selected</c:if> >亳州</option>
<option value="池州"<c:if test="${pd.City_NAME == '池州' }">selected</c:if> >池州</option>
<option value="宣城"<c:if test="${pd.City_NAME == '宣城' }">selected</c:if> >宣城</option>
<option value="福州"<c:if test="${pd.City_NAME == '福州' }">selected</c:if> >福州</option>
<option value="厦门"<c:if test="${pd.City_NAME == '厦门' }">selected</c:if> >厦门</option>
<option value="莆田"<c:if test="${pd.City_NAME == '莆田' }">selected</c:if> >莆田</option>
<option value="三明"<c:if test="${pd.City_NAME == '三明' }">selected</c:if> >三明</option>
<option value="泉州"<c:if test="${pd.City_NAME == '泉州' }">selected</c:if> >泉州</option>
<option value="漳州"<c:if test="${pd.City_NAME == '漳州' }">selected</c:if> >漳州</option>
<option value="南平"<c:if test="${pd.City_NAME == '南平' }">selected</c:if> >南平</option>
<option value="龙岩"<c:if test="${pd.City_NAME == '龙岩' }">selected</c:if> >龙岩</option>
<option value="宁德"<c:if test="${pd.City_NAME == '宁德' }">selected</c:if> >宁德</option>
<option value="南昌"<c:if test="${pd.City_NAME == '南昌' }">selected</c:if> >南昌</option>
<option value="景德镇"<c:if test="${pd.City_NAME == '景德镇' }">selected</c:if> >景德镇</option>
<option value="萍乡"<c:if test="${pd.City_NAME == '萍乡' }">selected</c:if> >萍乡</option>
<option value="九江"<c:if test="${pd.City_NAME == '九江' }">selected</c:if> >九江</option>
<option value="新余"<c:if test="${pd.City_NAME == '新余' }">selected</c:if> >新余</option>
<option value="鹰潭"<c:if test="${pd.City_NAME == '鹰潭' }">selected</c:if> >鹰潭</option>
<option value="赣州"<c:if test="${pd.City_NAME == '赣州' }">selected</c:if> >赣州</option>
<option value="吉安"<c:if test="${pd.City_NAME == '吉安' }">selected</c:if> >吉安</option>
<option value="宜春"<c:if test="${pd.City_NAME == '宜春' }">selected</c:if> >宜春</option>
<option value="抚州"<c:if test="${pd.City_NAME == '抚州' }">selected</c:if> >抚州</option>
<option value="上饶"<c:if test="${pd.City_NAME == '上饶' }">selected</c:if> >上饶</option>
<option value="济南"<c:if test="${pd.City_NAME == '济南' }">selected</c:if> >济南</option>
<option value="青岛"<c:if test="${pd.City_NAME == '青岛' }">selected</c:if> >青岛</option>
<option value="淄博"<c:if test="${pd.City_NAME == '淄博' }">selected</c:if> >淄博</option>
<option value="枣庄"<c:if test="${pd.City_NAME == '枣庄' }">selected</c:if> >枣庄</option>
<option value="东营"<c:if test="${pd.City_NAME == '东营' }">selected</c:if> >东营</option>
<option value="烟台"<c:if test="${pd.City_NAME == '烟台' }">selected</c:if> >烟台</option>
<option value="潍坊"<c:if test="${pd.City_NAME == '潍坊' }">selected</c:if> >潍坊</option>
<option value="济宁"<c:if test="${pd.City_NAME == '济宁' }">selected</c:if> >济宁</option>
<option value="泰安"<c:if test="${pd.City_NAME == '泰安' }">selected</c:if> >泰安</option>
<option value="威海"<c:if test="${pd.City_NAME == '威海' }">selected</c:if> >威海</option>
<option value="日照"<c:if test="${pd.City_NAME == '日照' }">selected</c:if> >日照</option>
<option value="莱芜"<c:if test="${pd.City_NAME == '莱芜' }">selected</c:if> >莱芜</option>
<option value="临沂"<c:if test="${pd.City_NAME == '临沂' }">selected</c:if> >临沂</option>
<option value="德州"<c:if test="${pd.City_NAME == '德州' }">selected</c:if> >德州</option>
<option value="聊城"<c:if test="${pd.City_NAME == '聊城' }">selected</c:if> >聊城</option>
<option value="滨州"<c:if test="${pd.City_NAME == '滨州' }">selected</c:if> >滨州</option>
<option value="菏泽"<c:if test="${pd.City_NAME == '菏泽' }">selected</c:if> >菏泽</option>
<option value="郑州"<c:if test="${pd.City_NAME == '郑州' }">selected</c:if> >郑州</option>
<option value="开封"<c:if test="${pd.City_NAME == '开封' }">selected</c:if> >开封</option>
<option value="洛阳"<c:if test="${pd.City_NAME == '洛阳' }">selected</c:if> >洛阳</option>
<option value="平顶山"<c:if test="${pd.City_NAME == '平顶山' }">selected</c:if> >平顶山</option>
<option value="安阳"<c:if test="${pd.City_NAME == '安阳' }">selected</c:if> >安阳</option>
<option value="鹤壁"<c:if test="${pd.City_NAME == '鹤壁' }">selected</c:if> >鹤壁</option>
<option value="新乡"<c:if test="${pd.City_NAME == '新乡' }">selected</c:if> >新乡</option>
<option value="焦作"<c:if test="${pd.City_NAME == '焦作' }">selected</c:if> >焦作</option>
<option value="濮阳"<c:if test="${pd.City_NAME == '濮阳' }">selected</c:if> >濮阳</option>
<option value="许昌"<c:if test="${pd.City_NAME == '许昌' }">selected</c:if> >许昌</option>
<option value="漯河"<c:if test="${pd.City_NAME == '漯河' }">selected</c:if> >漯河</option>
<option value="三门峡"<c:if test="${pd.City_NAME == '三门峡' }">selected</c:if> >三门峡</option>
<option value="南阳"<c:if test="${pd.City_NAME == '南阳' }">selected</c:if> >南阳</option>
<option value="商丘"<c:if test="${pd.City_NAME == '商丘' }">selected</c:if> >商丘</option>
<option value="信阳"<c:if test="${pd.City_NAME == '信阳' }">selected</c:if> >信阳</option>
<option value="周口"<c:if test="${pd.City_NAME == '周口' }">selected</c:if> >周口</option>
<option value="驻马店"<c:if test="${pd.City_NAME == '驻马店' }">selected</c:if> >驻马店</option>
<option value="济源"<c:if test="${pd.City_NAME == '济源' }">selected</c:if> >济源</option>
<option value="武汉"<c:if test="${pd.City_NAME == '武汉' }">selected</c:if> >武汉</option>
<option value="黄石"<c:if test="${pd.City_NAME == '黄石' }">selected</c:if> >黄石</option>
<option value="十堰"<c:if test="${pd.City_NAME == '十堰' }">selected</c:if> >十堰</option>
<option value="宜昌"<c:if test="${pd.City_NAME == '宜昌' }">selected</c:if> >宜昌</option>
<option value="襄阳"<c:if test="${pd.City_NAME == '襄阳' }">selected</c:if> >襄阳</option>
<option value="鄂州"<c:if test="${pd.City_NAME == '鄂州' }">selected</c:if> >鄂州</option>
<option value="荆门"<c:if test="${pd.City_NAME == '荆门' }">selected</c:if> >荆门</option>
<option value="孝感"<c:if test="${pd.City_NAME == '孝感' }">selected</c:if> >孝感</option>
<option value="荆州"<c:if test="${pd.City_NAME == '荆州' }">selected</c:if> >荆州</option>
<option value="黄冈"<c:if test="${pd.City_NAME == '黄冈' }">selected</c:if> >黄冈</option>
<option value="咸宁"<c:if test="${pd.City_NAME == '咸宁' }">selected</c:if> >咸宁</option>
<option value="随州"<c:if test="${pd.City_NAME == '随州' }">selected</c:if> >随州</option>
<option value="恩施"<c:if test="${pd.City_NAME == '恩施' }">selected</c:if> >恩施</option>
<option value="仙桃"<c:if test="${pd.City_NAME == '仙桃' }">selected</c:if> >仙桃</option>
<option value="潜江"<c:if test="${pd.City_NAME == '潜江' }">selected</c:if> >潜江</option>
<option value="天门"<c:if test="${pd.City_NAME == '天门' }">selected</c:if> >天门</option>
<option value="神农架林区"<c:if test="${pd.City_NAME == '神农架林区' }">selected</c:if> >神农架林区</option>
<option value="长沙"<c:if test="${pd.City_NAME == '长沙' }">selected</c:if> >长沙</option>
<option value="株洲"<c:if test="${pd.City_NAME == '株洲' }">selected</c:if> >株洲</option>
<option value="湘潭"<c:if test="${pd.City_NAME == '湘潭' }">selected</c:if> >湘潭</option>
<option value="衡阳"<c:if test="${pd.City_NAME == '衡阳' }">selected</c:if> >衡阳</option>
<option value="邵阳"<c:if test="${pd.City_NAME == '邵阳' }">selected</c:if> >邵阳</option>
<option value="岳阳"<c:if test="${pd.City_NAME == '岳阳' }">selected</c:if> >岳阳</option>
<option value="常德"<c:if test="${pd.City_NAME == '常德' }">selected</c:if> >常德</option>
<option value="张家界"<c:if test="${pd.City_NAME == '张家界' }">selected</c:if> >张家界</option>
<option value="益阳"<c:if test="${pd.City_NAME == '益阳' }">selected</c:if> >益阳</option>
<option value="郴州"<c:if test="${pd.City_NAME == '郴州' }">selected</c:if> >郴州</option>
<option value="永州"<c:if test="${pd.City_NAME == '永州' }">selected</c:if> >永州</option>
<option value="怀化"<c:if test="${pd.City_NAME == '怀化' }">selected</c:if> >怀化</option>
<option value="娄底"<c:if test="${pd.City_NAME == '娄底' }">selected</c:if> >娄底</option>
<option value="湘西"<c:if test="${pd.City_NAME == '湘西' }">selected</c:if> >湘西</option>
<option value="广州"<c:if test="${pd.City_NAME == '广州' }">selected</c:if> >广州</option>
<option value="韶关"<c:if test="${pd.City_NAME == '韶关' }">selected</c:if> >韶关</option>
<option value="深圳"<c:if test="${pd.City_NAME == '深圳' }">selected</c:if> >深圳</option>
<option value="珠海"<c:if test="${pd.City_NAME == '珠海' }">selected</c:if> >珠海</option>
<option value="汕头"<c:if test="${pd.City_NAME == '汕头' }">selected</c:if> >汕头</option>
<option value="佛山"<c:if test="${pd.City_NAME == '佛山' }">selected</c:if> >佛山</option>
<option value="江门"<c:if test="${pd.City_NAME == '江门' }">selected</c:if> >江门</option>
<option value="湛江"<c:if test="${pd.City_NAME == '湛江' }">selected</c:if> >湛江</option>
<option value="茂名"<c:if test="${pd.City_NAME == '茂名' }">selected</c:if> >茂名</option>
<option value="肇庆"<c:if test="${pd.City_NAME == '肇庆' }">selected</c:if> >肇庆</option>
<option value="惠州"<c:if test="${pd.City_NAME == '惠州' }">selected</c:if> >惠州</option>
<option value="梅州"<c:if test="${pd.City_NAME == '梅州' }">selected</c:if> >梅州</option>
<option value="汕尾"<c:if test="${pd.City_NAME == '汕尾' }">selected</c:if> >汕尾</option>
<option value="河源"<c:if test="${pd.City_NAME == '河源' }">selected</c:if> >河源</option>
<option value="阳江"<c:if test="${pd.City_NAME == '阳江' }">selected</c:if> >阳江</option>
<option value="清远"<c:if test="${pd.City_NAME == '清远' }">selected</c:if> >清远</option>
<option value="东莞"<c:if test="${pd.City_NAME == '东莞' }">selected</c:if> >东莞</option>
<option value="中山"<c:if test="${pd.City_NAME == '中山' }">selected</c:if> >中山</option>
<option value="潮州"<c:if test="${pd.City_NAME == '潮州' }">selected</c:if> >潮州</option>
<option value="揭阳"<c:if test="${pd.City_NAME == '揭阳' }">selected</c:if> >揭阳</option>
<option value="云浮"<c:if test="${pd.City_NAME == '云浮' }">selected</c:if> >云浮</option>
<option value="南宁"<c:if test="${pd.City_NAME == '南宁' }">selected</c:if> >南宁</option>
<option value="柳州"<c:if test="${pd.City_NAME == '柳州' }">selected</c:if> >柳州</option>
<option value="桂林"<c:if test="${pd.City_NAME == '桂林' }">selected</c:if> >桂林</option>
<option value="梧州"<c:if test="${pd.City_NAME == '梧州' }">selected</c:if> >梧州</option>
<option value="北海"<c:if test="${pd.City_NAME == '北海' }">selected</c:if> >北海</option>
<option value="防城港"<c:if test="${pd.City_NAME == '防城港' }">selected</c:if> >防城港</option>
<option value="钦州"<c:if test="${pd.City_NAME == '钦州' }">selected</c:if> >钦州</option>
<option value="贵港"<c:if test="${pd.City_NAME == '贵港' }">selected</c:if> >贵港</option>
<option value="玉林"<c:if test="${pd.City_NAME == '玉林' }">selected</c:if> >玉林</option>
<option value="百色"<c:if test="${pd.City_NAME == '百色' }">selected</c:if> >百色</option>
<option value="贺州"<c:if test="${pd.City_NAME == '贺州' }">selected</c:if> >贺州</option>
<option value="河池"<c:if test="${pd.City_NAME == '河池' }">selected</c:if> >河池</option>
<option value="来宾"<c:if test="${pd.City_NAME == '来宾' }">selected</c:if> >来宾</option>
<option value="崇左"<c:if test="${pd.City_NAME == '崇左' }">selected</c:if> >崇左</option>
<option value="海口"<c:if test="${pd.City_NAME == '海口' }">selected</c:if> >海口</option>
<option value="三亚"<c:if test="${pd.City_NAME == '三亚' }">selected</c:if> >三亚</option>
<option value="三沙"<c:if test="${pd.City_NAME == '三沙' }">selected</c:if> >三沙</option>
<option value="五指山"<c:if test="${pd.City_NAME == '五指山' }">selected</c:if> >五指山</option>
<option value="琼海"<c:if test="${pd.City_NAME == '琼海' }">selected</c:if> >琼海</option>
<option value="儋州"<c:if test="${pd.City_NAME == '儋州' }">selected</c:if> >儋州</option>
<option value="文昌"<c:if test="${pd.City_NAME == '文昌' }">selected</c:if> >文昌</option>
<option value="万宁"<c:if test="${pd.City_NAME == '万宁' }">selected</c:if> >万宁</option>
<option value="东方"<c:if test="${pd.City_NAME == '东方' }">selected</c:if> >东方</option>
<option value="定安"<c:if test="${pd.City_NAME == '定安' }">selected</c:if> >定安</option>
<option value="屯昌"<c:if test="${pd.City_NAME == '屯昌' }">selected</c:if> >屯昌</option>
<option value="澄迈"<c:if test="${pd.City_NAME == '澄迈' }">selected</c:if> >澄迈</option>
<option value="临高"<c:if test="${pd.City_NAME == '临高' }">selected</c:if> >临高</option>
<option value="白沙"<c:if test="${pd.City_NAME == '白沙' }">selected</c:if> >白沙</option>
<option value="昌江"<c:if test="${pd.City_NAME == '昌江' }">selected</c:if> >昌江</option>
<option value="乐东"<c:if test="${pd.City_NAME == '乐东' }">selected</c:if> >乐东</option>
<option value="陵水"<c:if test="${pd.City_NAME == '陵水' }">selected</c:if> >陵水</option>
<option value="保亭"<c:if test="${pd.City_NAME == '保亭' }">selected</c:if> >保亭</option>
<option value="琼中"<c:if test="${pd.City_NAME == '琼中' }">selected</c:if> >琼中</option>
<option value="成都"<c:if test="${pd.City_NAME == '成都' }">selected</c:if> >成都</option>
<option value="自贡"<c:if test="${pd.City_NAME == '自贡' }">selected</c:if> >自贡</option>
<option value="攀枝花"<c:if test="${pd.City_NAME == '攀枝花' }">selected</c:if> >攀枝花</option>
<option value="泸州"<c:if test="${pd.City_NAME == '泸州' }">selected</c:if> >泸州</option>
<option value="德阳"<c:if test="${pd.City_NAME == '德阳' }">selected</c:if> >德阳</option>
<option value="绵阳"<c:if test="${pd.City_NAME == '绵阳' }">selected</c:if> >绵阳</option>
<option value="广元"<c:if test="${pd.City_NAME == '广元' }">selected</c:if> >广元</option>
<option value="遂宁"<c:if test="${pd.City_NAME == '遂宁' }">selected</c:if> >遂宁</option>
<option value="内江"<c:if test="${pd.City_NAME == '内江' }">selected</c:if> >内江</option>
<option value="乐山"<c:if test="${pd.City_NAME == '乐山' }">selected</c:if> >乐山</option>
<option value="南充"<c:if test="${pd.City_NAME == '南充' }">selected</c:if> >南充</option>
<option value="眉山"<c:if test="${pd.City_NAME == '眉山' }">selected</c:if> >眉山</option>
<option value="宜宾"<c:if test="${pd.City_NAME == '宜宾' }">selected</c:if> >宜宾</option>
<option value="广安"<c:if test="${pd.City_NAME == '广安' }">selected</c:if> >广安</option>
<option value="达州"<c:if test="${pd.City_NAME == '达州' }">selected</c:if> >达州</option>
<option value="雅安"<c:if test="${pd.City_NAME == '雅安' }">selected</c:if> >雅安</option>
<option value="巴中"<c:if test="${pd.City_NAME == '巴中' }">selected</c:if> >巴中</option>
<option value="资阳"<c:if test="${pd.City_NAME == '资阳' }">selected</c:if> >资阳</option>
<option value="阿坝"<c:if test="${pd.City_NAME == '阿坝' }">selected</c:if> >阿坝</option>
<option value="甘孜"<c:if test="${pd.City_NAME == '甘孜' }">selected</c:if> >甘孜</option>
<option value="凉山"<c:if test="${pd.City_NAME == '凉山' }">selected</c:if> >凉山</option>
<option value="贵阳"<c:if test="${pd.City_NAME == '贵阳' }">selected</c:if> >贵阳</option>
<option value="六盘水"<c:if test="${pd.City_NAME == '六盘水' }">selected</c:if> >六盘水</option>
<option value="遵义"<c:if test="${pd.City_NAME == '遵义' }">selected</c:if> >遵义</option>
<option value="安顺"<c:if test="${pd.City_NAME == '安顺' }">selected</c:if> >安顺</option>
<option value="毕节"<c:if test="${pd.City_NAME == '毕节' }">selected</c:if> >毕节</option>
<option value="铜仁"<c:if test="${pd.City_NAME == '铜仁' }">selected</c:if> >铜仁</option>
<option value="黔西南"<c:if test="${pd.City_NAME == '黔西南' }">selected</c:if> >黔西南</option>
<option value="黔东南"<c:if test="${pd.City_NAME == '黔东南' }">selected</c:if> >黔东南</option>
<option value="黔南"<c:if test="${pd.City_NAME == '黔南' }">selected</c:if> >黔南</option>
<option value="昆明"<c:if test="${pd.City_NAME == '昆明' }">selected</c:if> >昆明</option>
<option value="曲靖"<c:if test="${pd.City_NAME == '曲靖' }">selected</c:if> >曲靖</option>
<option value="玉溪"<c:if test="${pd.City_NAME == '玉溪' }">selected</c:if> >玉溪</option>
<option value="昭通"<c:if test="${pd.City_NAME == '昭通' }">selected</c:if> >昭通</option>
<option value="丽江"<c:if test="${pd.City_NAME == '丽江' }">selected</c:if> >丽江</option>
<option value="普洱"<c:if test="${pd.City_NAME == '普洱' }">selected</c:if> >普洱</option>
<option value="临沧"<c:if test="${pd.City_NAME == '临沧' }">selected</c:if> >临沧</option>
<option value="楚雄"<c:if test="${pd.City_NAME == '楚雄' }">selected</c:if> >楚雄</option>
<option value="红河"<c:if test="${pd.City_NAME == '红河' }">selected</c:if> >红河</option>
<option value="文山"<c:if test="${pd.City_NAME == '文山' }">selected</c:if> >文山</option>
<option value="西双版纳"<c:if test="${pd.City_NAME == '西双版纳' }">selected</c:if> >西双版纳</option>
<option value="大理"<c:if test="${pd.City_NAME == '大理' }">selected</c:if> >大理</option>
<option value="德宏"<c:if test="${pd.City_NAME == '德宏' }">selected</c:if> >德宏</option>
<option value="怒江"<c:if test="${pd.City_NAME == '怒江' }">selected</c:if> >怒江</option>
<option value="迪庆"<c:if test="${pd.City_NAME == '迪庆' }">selected</c:if> >迪庆</option>
<option value="保山"<c:if test="${pd.City_NAME == '保山' }">selected</c:if> >保山</option>
<option value="拉萨"<c:if test="${pd.City_NAME == '拉萨' }">selected</c:if> >拉萨</option>
<option value="昌都"<c:if test="${pd.City_NAME == '昌都' }">selected</c:if> >昌都</option>
<option value="山南"<c:if test="${pd.City_NAME == '山南' }">selected</c:if> >山南</option>
<option value="日喀则"<c:if test="${pd.City_NAME == '日喀则' }">selected</c:if> >日喀则</option>
<option value="那曲"<c:if test="${pd.City_NAME == '那曲' }">selected</c:if> >那曲</option>
<option value="阿里"<c:if test="${pd.City_NAME == '阿里' }">selected</c:if> >阿里</option>
<option value="林芝"<c:if test="${pd.City_NAME == '林芝' }">selected</c:if> >林芝</option>
<option value="西安"<c:if test="${pd.City_NAME == '西安' }">selected</c:if> >西安</option>
<option value="铜川"<c:if test="${pd.City_NAME == '铜川' }">selected</c:if> >铜川</option>
<option value="宝鸡"<c:if test="${pd.City_NAME == '宝鸡' }">selected</c:if> >宝鸡</option>
<option value="咸阳"<c:if test="${pd.City_NAME == '咸阳' }">selected</c:if> >咸阳</option>
<option value="渭南"<c:if test="${pd.City_NAME == '渭南' }">selected</c:if> >渭南</option>
<option value="延安"<c:if test="${pd.City_NAME == '延安' }">selected</c:if> >延安</option>
<option value="汉中"<c:if test="${pd.City_NAME == '汉中' }">selected</c:if> >汉中</option>
<option value="榆林"<c:if test="${pd.City_NAME == '榆林' }">selected</c:if> >榆林</option>
<option value="安康"<c:if test="${pd.City_NAME == '安康' }">selected</c:if> >安康</option>
<option value="商洛"<c:if test="${pd.City_NAME == '商洛' }">selected</c:if> >商洛</option>
<option value="兰州"<c:if test="${pd.City_NAME == '兰州' }">selected</c:if> >兰州</option>
<option value="嘉峪关"<c:if test="${pd.City_NAME == '嘉峪关' }">selected</c:if> >嘉峪关</option>
<option value="金昌"<c:if test="${pd.City_NAME == '金昌' }">selected</c:if> >金昌</option>
<option value="白银"<c:if test="${pd.City_NAME == '白银' }">selected</c:if> >白银</option>
<option value="天水"<c:if test="${pd.City_NAME == '天水' }">selected</c:if> >天水</option>
<option value="武威"<c:if test="${pd.City_NAME == '武威' }">selected</c:if> >武威</option>
<option value="张掖"<c:if test="${pd.City_NAME == '张掖' }">selected</c:if> >张掖</option>
<option value="平凉"<c:if test="${pd.City_NAME == '平凉' }">selected</c:if> >平凉</option>
<option value="酒泉"<c:if test="${pd.City_NAME == '酒泉' }">selected</c:if> >酒泉</option>
<option value="庆阳"<c:if test="${pd.City_NAME == '庆阳' }">selected</c:if> >庆阳</option>
<option value="定西"<c:if test="${pd.City_NAME == '定西' }">selected</c:if> >定西</option>
<option value="陇南"<c:if test="${pd.City_NAME == '陇南' }">selected</c:if> >陇南</option>
<option value="临夏"<c:if test="${pd.City_NAME == '临夏' }">selected</c:if> >临夏</option>
<option value="甘南"<c:if test="${pd.City_NAME == '甘南' }">selected</c:if> >甘南</option>
<option value="西宁"<c:if test="${pd.City_NAME == '西宁' }">selected</c:if> >西宁</option>
<option value="海东"<c:if test="${pd.City_NAME == '海东' }">selected</c:if> >海东</option>
<option value="海北"<c:if test="${pd.City_NAME == '海北' }">selected</c:if> >海北</option>
<option value="黄南"<c:if test="${pd.City_NAME == '黄南' }">selected</c:if> >黄南</option>
<option value="海南"<c:if test="${pd.City_NAME == '海南' }">selected</c:if> >海南</option>
<option value="果洛"<c:if test="${pd.City_NAME == '果洛' }">selected</c:if> >果洛</option>
<option value="玉树"<c:if test="${pd.City_NAME == '玉树' }">selected</c:if> >玉树</option>
<option value="海西"<c:if test="${pd.City_NAME == '海西' }">selected</c:if> >海西</option>
<option value="银川"<c:if test="${pd.City_NAME == '银川' }">selected</c:if> >银川</option>
<option value="石嘴山"<c:if test="${pd.City_NAME == '石嘴山' }">selected</c:if> >石嘴山</option>
<option value="吴忠"<c:if test="${pd.City_NAME == '吴忠' }">selected</c:if> >吴忠</option>
<option value="固原"<c:if test="${pd.City_NAME == '固原' }">selected</c:if> >固原</option>
<option value="中卫"<c:if test="${pd.City_NAME == '中卫' }">selected</c:if> >中卫</option>
<option value="铁门关"<c:if test="${pd.City_NAME == '铁门关' }">selected</c:if> >铁门关</option>
<option value="北屯"<c:if test="${pd.City_NAME == '北屯' }">selected</c:if> >北屯</option>
<option value="乌鲁木齐"<c:if test="${pd.City_NAME == '乌鲁木齐' }">selected</c:if> >乌鲁木齐</option>
<option value="克拉玛依"<c:if test="${pd.City_NAME == '克拉玛依' }">selected</c:if> >克拉玛依</option>
<option value="吐鲁番"<c:if test="${pd.City_NAME == '吐鲁番' }">selected</c:if> >吐鲁番</option>
<option value="哈密"<c:if test="${pd.City_NAME == '哈密' }">selected</c:if> >哈密</option>
<option value="昌吉"<c:if test="${pd.City_NAME == '昌吉' }">selected</c:if> >昌吉</option>
<option value="博尔塔拉"<c:if test="${pd.City_NAME == '博尔塔拉' }">selected</c:if> >博尔塔拉</option>
<option value="巴音郭楞"<c:if test="${pd.City_NAME == '巴音郭楞' }">selected</c:if> >巴音郭楞</option>
<option value="阿克苏"<c:if test="${pd.City_NAME == '阿克苏' }">selected</c:if> >阿克苏</option>
<option value="克孜勒苏"<c:if test="${pd.City_NAME == '克孜勒苏' }">selected</c:if> >克孜勒苏</option>
<option value="喀什"<c:if test="${pd.City_NAME == '喀什' }">selected</c:if> >喀什</option>
<option value="和田"<c:if test="${pd.City_NAME == '和田' }">selected</c:if> >和田</option>
<option value="伊犁"<c:if test="${pd.City_NAME == '伊犁' }">selected</c:if> >伊犁</option>
<option value="塔城"<c:if test="${pd.City_NAME == '塔城' }">selected</c:if> >塔城</option>
<option value="阿勒泰"<c:if test="${pd.City_NAME == '阿勒泰' }">selected</c:if> >阿勒泰</option>
<option value="石河子"<c:if test="${pd.City_NAME == '石河子' }">selected</c:if> >石河子</option>
<option value="阿拉尔"<c:if test="${pd.City_NAME == '阿拉尔' }">selected</c:if> >阿拉尔</option>
<option value="图木舒克"<c:if test="${pd.City_NAME == '图木舒克' }">selected</c:if> >图木舒克</option>
<option value="五家渠"<c:if test="${pd.City_NAME == '五家渠' }">selected</c:if> >五家渠</option>
<option value="台北"<c:if test="${pd.City_NAME == '台北' }">selected</c:if> >台北</option>
<option value="高雄"<c:if test="${pd.City_NAME == '高雄' }">selected</c:if> >高雄</option>
<option value="基隆"<c:if test="${pd.City_NAME == '基隆' }">selected</c:if> >基隆</option>
<option value="台中"<c:if test="${pd.City_NAME == '台中' }">selected</c:if> >台中</option>
<option value="台南"<c:if test="${pd.City_NAME == '台南' }">selected</c:if> >台南</option>
<option value="新竹"<c:if test="${pd.City_NAME == '新竹' }">selected</c:if> >新竹</option>
<option value="嘉义"<c:if test="${pd.City_NAME == '嘉义' }">selected</c:if> >嘉义</option>
<option value="新北"<c:if test="${pd.City_NAME == '新北' }">selected</c:if> >新北</option>
<option value="宜兰"<c:if test="${pd.City_NAME == '宜兰' }">selected</c:if> >宜兰</option>
<option value="桃园"<c:if test="${pd.City_NAME == '桃园' }">selected</c:if> >桃园</option>
<option value="新竹"<c:if test="${pd.City_NAME == '新竹' }">selected</c:if> >新竹</option>
<option value="苗栗"<c:if test="${pd.City_NAME == '苗栗' }">selected</c:if> >苗栗</option>
<option value="彰化"<c:if test="${pd.City_NAME == '彰化' }">selected</c:if> >彰化</option>
<option value="南投"<c:if test="${pd.City_NAME == '南投' }">selected</c:if> >南投</option>
<option value="云林"<c:if test="${pd.City_NAME == '云林' }">selected</c:if> >云林</option>
<option value="嘉义"<c:if test="${pd.City_NAME == '嘉义' }">selected</c:if> >嘉义</option>
<option value="屏东"<c:if test="${pd.City_NAME == '屏东' }">selected</c:if> >屏东</option>
<option value="台东"<c:if test="${pd.City_NAME == '台东' }">selected</c:if> >台东</option>
<option value="花莲"<c:if test="${pd.City_NAME == '花莲' }">selected</c:if> >花莲</option>
<option value="澎湖"<c:if test="${pd.City_NAME == '澎湖' }">selected</c:if> >澎湖</option>
<option value="连江"<c:if test="${pd.City_NAME == '连江' }">selected</c:if> >连江</option>
<option value="金门"<c:if test="${pd.City_NAME == '金门' }">selected</c:if> >金门</option>
<option value="中西"<c:if test="${pd.City_NAME == '中西' }">selected</c:if> >中西</option>
<option value="葵青"<c:if test="${pd.City_NAME == '葵青' }">selected</c:if> >葵青</option>
<option value="元朗"<c:if test="${pd.City_NAME == '元朗' }">selected</c:if> >元朗</option>
<option value="屯门"<c:if test="${pd.City_NAME == '屯门' }">selected</c:if> >屯门</option>
<option value="荃湾"<c:if test="${pd.City_NAME == '荃湾' }">selected</c:if> >荃湾</option>
<option value="西贡"<c:if test="${pd.City_NAME == '西贡' }">selected</c:if> >西贡</option>
<option value="沙田"<c:if test="${pd.City_NAME == '沙田' }">selected</c:if> >沙田</option>
<option value="大埔"<c:if test="${pd.City_NAME == '大埔' }">selected</c:if> >大埔</option>
<option value="北区"<c:if test="${pd.City_NAME == '北区' }">selected</c:if> >北区</option>
<option value="观塘"<c:if test="${pd.City_NAME == '观塘' }">selected</c:if> >观塘</option>
<option value="黄大仙"<c:if test="${pd.City_NAME == '黄大仙' }">selected</c:if> >黄大仙</option>
<option value="深水埗"<c:if test="${pd.City_NAME == '深水埗' }">selected</c:if> >深水埗</option>
<option value="油尖旺"<c:if test="${pd.City_NAME == '油尖旺' }">selected</c:if> >油尖旺</option>
<option value="九龙城"<c:if test="${pd.City_NAME == '九龙城' }">selected</c:if> >九龙城</option>
<option value="南区"<c:if test="${pd.City_NAME == '南区' }">selected</c:if> >南区</option>
<option value="东区"<c:if test="${pd.City_NAME == '东区' }">selected</c:if> >东区</option>
<option value="湾仔"<c:if test="${pd.City_NAME == '湾仔' }">selected</c:if> >湾仔</option>
<option value="离岛"<c:if test="${pd.City_NAME == '离岛' }">selected</c:if> >离岛</option>
<option value="花地玛"<c:if test="${pd.City_NAME == '花地玛' }">selected</c:if> >花地玛</option>
<option value="圣安多"<c:if test="${pd.City_NAME == '圣安多' }">selected</c:if> >圣安多</option>
<option value="大堂区"<c:if test="${pd.City_NAME == '大堂区' }">selected</c:if> >大堂区</option>
<option value="望德"<c:if test="${pd.City_NAME == '望德' }">selected</c:if> >望德</option>
<option value="风顺"<c:if test="${pd.City_NAME == '风顺' }">selected</c:if> >风顺</option>
<option value="嘉模"<c:if test="${pd.City_NAME == '嘉模' }">selected</c:if> >嘉模</option>
<option value="圣方济各"<c:if test="${pd.City_NAME == '圣方济各' }">selected</c:if> >圣方济各</option>
<option value="路氹城"<c:if test="${pd.City_NAME == '路氹城' }">selected</c:if> >路氹城</option>
					</select>	
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					<c:if test="${QX.cha == 1 }">
<!-- 					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
 -->					</c:if>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th width="5%">序号</th>
						<!-- <th>活动ID</th> -->
						<th width="20%">活动标题</th>
						<th width="5%">城市</th>
						<!-- <th>会员ID</th> -->
						<th width="5%">姓名</th>
						<th width="5%">电话</th>
						<th width="17%">小区/楼盘名称</th>
						<th width="10%">房屋面积</th>
						<th width="10%">装修类型</th>
						<th width="10%">装修风格</th>
						<!-- <th>备注</th> -->
						<th width="20%">报名时间</th>
						<!-- <th width="5%">更新时间</th> -->
						<th width="5%">状态</th>
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
									<label><input type='checkbox' name='ids' value="${var.ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<%-- <td>${var.CAMPAIGN_ID}</td> --%>
										<td class='center' title="${var.CAMPAIGN_TITLE}">${fn:substring(var.CAMPAIGN_TITLE ,0,12)} ...</td>	
										<%-- <td>${var.MEMBER_ID}</td> --%>
										<td>${var.City_NAME}</td>
										<td>${var.MEMBER_NAME}</td>
										<td>${var.MEMBER_PHONE}</td>
										<td title="${var.MEMBER_VILLAGE_NAME}">${fn:substring(var.MEMBER_VILLAGE_NAME ,0,12)} ...</td>
										<td>${var.MEMBER_HOU_AREA}</td>
										<td>${var.MEMBER_HOU_TYPE}</td>
										<td>${var.MEMBER_HOU_STYLE}</td>
										<%-- <td>${var.MEMBER_REMARK}</td> --%>
										<td>${var.ADDTIME}</td>
										<%-- <td>${var.UPTIME}</td> --%>
										<td>
											<c:if test="${var.STATUS ==0 }"><span class="label label-important arrowed-in">未受理</span></c:if>
											<c:if test="${var.STATUS ==1}"><span class="label label-success arrowed">已受理</span></c:if>
										</td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<c:if test="${QX.edit == 1 }">
											<li><a style="cursor:pointer;" title="编辑" onclick="edit('${var.ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											</c:if>
											<c:if test="${QX.del == 1 }">
											<li><a style="cursor:pointer;" title="删除" onclick="del('${var.ID}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
											</c:if>
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
			 diag.URL = '<%=basePath%>campaignapply/pc/goAdd';
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
					var url = "<%=basePath%>campaignapply/pc/delete?ID="+Id+"&tm="+new Date().getTime();
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
			 diag.URL = '<%=basePath%>campaignapply/pc/goEdit?ID='+Id;
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
								url: '<%=basePath%>campaignapply/pc/deleteAll?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>campaignapply/pc/excel';
		}
		</script>
		
	</body>
</html>

