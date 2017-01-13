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
			if($("#MEMBER_NAME").val()==""){
			$("#MEMBER_NAME").tips({
				side:3,
	            msg:'请输入姓名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_NAME").focus();
			return false;
		}
		if($("#MEMBER_PHONE").val()==""){
			$("#MEMBER_PHONE").tips({
				side:3,
	            msg:'请输入电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_PHONE").focus();
			return false;
		}
		if($("#MEMBER_VILLAGE_NAME").val()==""){
			$("#MEMBER_VILLAGE_NAME").tips({
				side:3,
	            msg:'请输入小区楼盘名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_VILLAGE_NAME").focus();
			return false;
		}
		if($("#MEMBER_HOU_AREA").val()==""){
			$("#MEMBER_HOU_AREA").tips({
				side:3,
	            msg:'请输入房屋面积',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_HOU_AREA").focus();
			return false;
		}
		if($("#MEMBER_HOU_BUDGET").val()==""){
			$("#MEMBER_HOU_BUDGET").tips({
				side:3,
	            msg:'请输入装修预算',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_HOU_BUDGET").focus();
			return false;
		}
		if($("#MEMBER_APPOINTMENT_DATE").val()==""){
			$("#MEMBER_APPOINTMENT_DATE").tips({
				side:3,
	            msg:'请输入预约时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_APPOINTMENT_DATE").focus();
			return false;
		}
		if($("#MEMBER_APPOINTMENT_REMARK").val()==""){
			$("#MEMBER_APPOINTMENT_REMARK").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_APPOINTMENT_REMARK").focus();
			return false;
		}
		if($("#MEMBER_APPOINTMENT_STATUS").val()==""){
			$("#MEMBER_APPOINTMENT_STATUS").tips({
				side:3,
	            msg:'请输入预约状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMBER_APPOINTMENT_STATUS").focus();
			return false;
		}
		$("#City_NAME").val($("#City_ID").find("option:selected").text());
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="memberappointment/pc/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<input type="hidden" name="City_NAME" id="City_NAME"/>
		<div id="zhongxin">
		<table>
		<tr>
				<td>
					<select id="City_ID" name="City_ID" title="城市">
						<option value="1"<c:if test="${pd.City_NAME == '北京' }">selected</c:if> >北京</option>
						<option value="3"<c:if test="${pd.City_NAME == '上海' }">selected</c:if> >上海</option>
						<option value="321"<c:if test="${pd.City_NAME=='广州'}">selected</c:if> >广州</option>
						<option value="323"<c:if test="${pd.City_NAME == '深圳' }">selected</c:if> >深圳</option>
						<option value="375"<c:if test="${pd.City_NAME == '成都' }">selected</c:if> >成都</option>
						<option value="4"<c:if test="${pd.City_NAME == '重庆' }">selected</c:if> >重庆</option>
						<option value="235"<c:if test="${pd.City_NAME == '福州' }">selected</c:if> >福州</option>
						<option value="236"<c:if test="${pd.City_NAME == '厦门' }">selected</c:if> >厦门</option>
						<option value="208"<c:if test="${pd.City_NAME == '杭州' }">selected</c:if> >杭州</option>
						<option value="290"<c:if test="${pd.City_NAME == '武汉' }">selected</c:if> >武汉</option>
						<option value="393"<c:if test="${pd.City_NAME == '阿坝' }">selected</c:if> >阿坝</option>
						<option value="474"<c:if test="${pd.City_NAME == '阿克苏' }">selected</c:if> >阿克苏</option>
						<option value="158"<c:if test="${pd.City_NAME == '阿拉善' }">selected</c:if> >阿拉善</option>
						<option value="426"<c:if test="${pd.City_NAME == '阿里' }">selected</c:if> >阿里</option>
						<option value="480"<c:if test="${pd.City_NAME == '阿勒泰' }">selected</c:if> >阿勒泰</option>
						<option value="436"<c:if test="${pd.City_NAME == '安康' }">selected</c:if> >安康</option>
						<option value="226"<c:if test="${pd.City_NAME == '安庆' }">selected</c:if> >安庆</option>
						<option value="161"<c:if test="${pd.City_NAME == '鞍山' }">selected</c:if> >鞍山</option>
						<option value="399"<c:if test="${pd.City_NAME == '安顺' }">selected</c:if> >安顺</option>
						<option value="276"<c:if test="${pd.City_NAME == '安阳' }">selected</c:if> >安阳</option>
						<option value="180"<c:if test="${pd.City_NAME == '白城' }">selected</c:if> >白城</option>
						<option value="351"<c:if test="${pd.City_NAME == '百色' }">selected</c:if> >百色</option>
						<option value="178"<c:if test="${pd.City_NAME == '白山' }">selected</c:if> >白山</option>
						<option value="441"<c:if test="${pd.City_NAME == '白银' }">selected</c:if> >白银</option>
						<option value="130"<c:if test="${pd.City_NAME == '保定' }">selected</c:if> >保定</option>
						<option value="430"<c:if test="${pd.City_NAME == '宝鸡' }">selected</c:if> >宝鸡</option>
						<option value="420"<c:if test="${pd.City_NAME == '保山' }">selected</c:if> >保山</option>
						<option value="148"<c:if test="${pd.City_NAME == '包头' }">selected</c:if> >包头</option>
						<option value="154"<c:if test="${pd.City_NAME == '巴彦淖尔' }">selected</c:if> >巴彦淖尔</option>
						<option value="473"<c:if test="${pd.City_NAME == '巴音郭楞' }">selected</c:if> >巴音郭楞</option>
						<option value="391"<c:if test="${pd.City_NAME == '巴中' }">selected</c:if> >巴中</option>
						<option value="346"<c:if test="${pd.City_NAME == '北海' }">selected</c:if> >北海</option>
						<option value="1"<c:if test="${pd.City_NAME == '北京' }">selected</c:if> >北京</option>
						<option value="221"<c:if test="${pd.City_NAME == '蚌埠' }">selected</c:if> >蚌埠</option>
						<option value="163"<c:if test="${pd.City_NAME == '本溪' }">selected</c:if> >本溪</option>
						<option value="400"<c:if test="${pd.City_NAME == '毕节' }">selected</c:if> >毕节</option>
						<option value="270"<c:if test="${pd.City_NAME == '滨州' }">selected</c:if> >滨州</option>
						<option value="472"<c:if test="${pd.City_NAME == '博尔塔拉' }">selected</c:if> >博尔塔拉</option>
						<option value="232"<c:if test="${pd.City_NAME == '亳州' }">selected</c:if> >亳州</option>
						<option value="133"<c:if test="${pd.City_NAME == '沧州' }">selected</c:if> >沧州</option>
						<option value="173"<c:if test="${pd.City_NAME == '长春' }">selected</c:if> >长春</option>
						<option value="313"<c:if test="${pd.City_NAME == '常德' }">selected</c:if> >常德</option>
						<option value="422"<c:if test="${pd.City_NAME == '昌都' }">selected</c:if> >昌都</option>
						<option value="471"<c:if test="${pd.City_NAME == '昌吉' }">selected</c:if> >昌吉</option>
						<option value="307"<c:if test="${pd.City_NAME == '长沙' }">selected</c:if> >长沙</option>
						<option value="139"<c:if test="${pd.City_NAME == '长治' }">selected</c:if> >长治</option>
						<option value="198"<c:if test="${pd.City_NAME == '常州' }">selected</c:if> >常州</option>
						<option value="171"<c:if test="${pd.City_NAME == '朝阳' }">selected</c:if> >朝阳</option>
						<option value="339"<c:if test="${pd.City_NAME == '潮州' }">selected</c:if> >潮州</option>
						<option value="132"<c:if test="${pd.City_NAME == '承德' }">selected</c:if> >承德</option>
						<option value="375"<c:if test="${pd.City_NAME == '成都' }">selected</c:if> >成都</option>
						<option value="316"<c:if test="${pd.City_NAME == '郴州' }">selected</c:if> >郴州</option>
						<option value="150"<c:if test="${pd.City_NAME == '赤峰' }">selected</c:if> >赤峰</option>
						<option value="233"<c:if test="${pd.City_NAME == '池州' }">selected</c:if> >池州</option>
						<option value="4"<c:if test="${pd.City_NAME == '重庆' }">selected</c:if> >重庆</option>
						<option value="355"<c:if test="${pd.City_NAME == '崇左' }">selected</c:if> >崇左</option>
						<option value="412"<c:if test="${pd.City_NAME == '楚雄' }">selected</c:if> >楚雄</option>
						<option value="228"<c:if test="${pd.City_NAME == '滁州' }">selected</c:if> >滁州</option>
						<option value="416"<c:if test="${pd.City_NAME == '大理' }">selected</c:if> >大理</option>
						<option value="160"<c:if test="${pd.City_NAME == '大连' }">selected</c:if> >大连</option>
						<option value="164"<c:if test="${pd.City_NAME == '丹东' }">selected</c:if> >丹东</option>
						<option value="361"<c:if test="${pd.City_NAME == '儋州' }">selected</c:if> >儋州</option>
						<option value="187"<c:if test="${pd.City_NAME == '大庆' }">selected</c:if> >大庆</option>
						<option value="137"<c:if test="${pd.City_NAME == '大同' }">selected</c:if> >大同</option>
						<option value="194"<c:if test="${pd.City_NAME == '大兴安岭' }">selected</c:if> >大兴安岭</option>
						<option value="389"<c:if test="${pd.City_NAME == '达州' }">selected</c:if> >达州</option>
						<option value="417"<c:if test="${pd.City_NAME == '德宏' }">selected</c:if> >德宏</option>
						<option value="379"<c:if test="${pd.City_NAME == '德阳' }">selected</c:if> >德阳</option>
						<option value="268"<c:if test="${pd.City_NAME == '德州' }">selected</c:if> >德州</option>
						<option value="448"<c:if test="${pd.City_NAME == '定西' }">selected</c:if> >定西</option>
						<option value="419"<c:if test="${pd.City_NAME == '迪庆' }">selected</c:if> >迪庆</option>
						<option value="364"<c:if test="${pd.City_NAME == '东方' }">selected</c:if> >东方</option>
						<option value="337"<c:if test="${pd.City_NAME == '东莞' }">selected</c:if> >东莞</option>
						<option value="259"<c:if test="${pd.City_NAME == '东营' }">selected</c:if> >东营</option>
						<option value="152"<c:if test="${pd.City_NAME == '鄂尔多斯' }">selected</c:if> >鄂尔多斯</option>
						<option value="302"<c:if test="${pd.City_NAME == '恩施' }">selected</c:if> >恩施</option>
						<option value="295"<c:if test="${pd.City_NAME == '鄂州' }">selected</c:if> >鄂州</option>
						<option value="347"<c:if test="${pd.City_NAME == '防城港' }">selected</c:if> >防城港</option>
						<option value="326"<c:if test="${pd.City_NAME == '佛山' }">selected</c:if> >佛山</option>
						<option value="162"<c:if test="${pd.City_NAME == '抚顺' }">selected</c:if> >抚顺</option>
						<option value="167"<c:if test="${pd.City_NAME == '阜新' }">selected</c:if> >阜新</option>
						<option value="229"<c:if test="${pd.City_NAME == '阜阳' }">selected</c:if> >阜阳</option>
						<option value="235"<c:if test="${pd.City_NAME == '福州' }">selected</c:if> >福州</option>
						<option value="253"<c:if test="${pd.City_NAME == '抚州' }">selected</c:if> >抚州</option>
						<option value="451"<c:if test="${pd.City_NAME == '甘南' }">selected</c:if> >甘南</option>
						<option value="250"<c:if test="${pd.City_NAME == '赣州' }">selected</c:if> >赣州</option>
						<option value="394"<c:if test="${pd.City_NAME == '甘孜' }">selected</c:if> >甘孜</option>
						<option value="388"<c:if test="${pd.City_NAME == '广安' }">selected</c:if> >广安</option>
						<option value="381"<c:if test="${pd.City_NAME == '广元' }">selected</c:if> >广元</option>
						<option value="321"<c:if test="${pd.City_NAME == '广州' }">selected</c:if> >广州</option>
						<option value="349"<c:if test="${pd.City_NAME == '贵港' }">selected</c:if> >贵港</option>
						<option value="344"<c:if test="${pd.City_NAME == '桂林' }">selected</c:if> >桂林</option>
						<option value="396"<c:if test="${pd.City_NAME == '贵阳' }">selected</c:if> >贵阳</option>
						<option value="457"<c:if test="${pd.City_NAME == '果洛' }">selected</c:if> >果洛</option>
						<option value="463"<c:if test="${pd.City_NAME == '固原' }">selected</c:if> >固原</option>
						<option value="182"<c:if test="${pd.City_NAME == '哈尔滨' }">selected</c:if> >哈尔滨</option>
						<option value="454"<c:if test="${pd.City_NAME == '海北' }">selected</c:if> >海北</option>
						<option value="453"<c:if test="${pd.City_NAME == '海东' }">selected</c:if> >海东</option>
						<option value="356"<c:if test="${pd.City_NAME == '海口' }">selected</c:if> >海口</option>
						<option value="456"<c:if test="${pd.City_NAME == '海南' }">selected</c:if> >海南</option>
						<option value="459"<c:if test="${pd.City_NAME == '海西' }">selected</c:if> >海西</option>
						<option value="470"<c:if test="${pd.City_NAME == '哈密' }">selected</c:if> >哈密</option>
						<option value="128"<c:if test="${pd.City_NAME == '邯郸' }">selected</c:if> >邯郸</option>
						<option value="208"<c:if test="${pd.City_NAME == '杭州' }">selected</c:if> >杭州</option>
						<option value="434"<c:if test="${pd.City_NAME == '汉中' }">selected</c:if> >汉中</option>
						<option value="277"<c:if test="${pd.City_NAME == '鹤壁' }">selected</c:if> >鹤壁</option>
						<option value="353"<c:if test="${pd.City_NAME == '河池' }">selected</c:if> >河池</option>
						<option value="219"<c:if test="${pd.City_NAME == '合肥' }">selected</c:if> >合肥</option>
						<option value="185"<c:if test="${pd.City_NAME == '鹤岗' }">selected</c:if> >鹤岗</option>
						<option value="192"<c:if test="${pd.City_NAME == '黑河' }">selected</c:if> >黑河</option>
						<option value="135"<c:if test="${pd.City_NAME == '衡水' }">selected</c:if> >衡水</option>
						<option value="310"<c:if test="${pd.City_NAME == '衡阳' }">selected</c:if> >衡阳</option>
						<option value="334"<c:if test="${pd.City_NAME == '河源' }">selected</c:if> >河源</option>
						<option value="271"<c:if test="${pd.City_NAME == '菏泽' }">selected</c:if> >菏泽</option>
						<option value="352"<c:if test="${pd.City_NAME == '贺州' }">selected</c:if> >贺州</option>
						<option value="413"<c:if test="${pd.City_NAME == '红河' }">selected</c:if> >红河</option>
						<option value="477"<c:if test="${pd.City_NAME == '和田' }">selected</c:if> >和田</option>
						<option value="202"<c:if test="${pd.City_NAME == '淮安' }">selected</c:if> >淮安</option>
						<option value="224"<c:if test="${pd.City_NAME == '淮北' }">selected</c:if> >淮北</option>
						<option value="318"<c:if test="${pd.City_NAME == '怀化' }">selected</c:if> >怀化</option>
						<option value="222"<c:if test="${pd.City_NAME == '淮南' }">selected</c:if> >淮南</option>
						<option value="299"<c:if test="${pd.City_NAME == '黄冈' }">selected</c:if> >黄冈</option>
						<option value="455"<c:if test="${pd.City_NAME == '黄南' }">selected</c:if> >黄南</option>
						<option value="227"<c:if test="${pd.City_NAME == '黄山' }">selected</c:if> >黄山</option>
						<option value="291"<c:if test="${pd.City_NAME == '黄石' }">selected</c:if> >黄石</option>
						<option value="147"<c:if test="${pd.City_NAME == '呼和浩特' }">selected</c:if> >呼和浩特</option>
						<option value="331"<c:if test="${pd.City_NAME == '惠州' }">selected</c:if> >惠州</option>
						<option value="172"<c:if test="${pd.City_NAME == '葫芦岛' }">selected</c:if> >葫芦岛</option>
						<option value="153"<c:if test="${pd.City_NAME == '呼伦贝尔' }">selected</c:if> >呼伦贝尔</option>
						<option value="212"<c:if test="${pd.City_NAME == '湖州' }">selected</c:if> >湖州</option>
						<option value="189"<c:if test="${pd.City_NAME == '佳木斯' }">selected</c:if> >佳木斯</option>
						<option value="251"<c:if test="${pd.City_NAME == '吉安' }">selected</c:if> >吉安</option>
						<option value="327"<c:if test="${pd.City_NAME == '江门' }">selected</c:if> >江门</option>
						<option value="279"<c:if test="${pd.City_NAME == '焦作' }">selected</c:if> >焦作</option>
						<option value="211"<c:if test="${pd.City_NAME == '嘉兴' }">selected</c:if> >嘉兴</option>
						<option value="439"<c:if test="${pd.City_NAME == '嘉峪关' }">selected</c:if> >嘉峪关</option>
						<option value="340"<c:if test="${pd.City_NAME == '揭阳' }">selected</c:if> >揭阳</option>
						<option value="174"<c:if test="${pd.City_NAME == '吉林' }">selected</c:if> >吉林</option>
						<option value="255"<c:if test="${pd.City_NAME == '济南' }">selected</c:if> >济南</option>
						<option value="440"<c:if test="${pd.City_NAME == '金昌' }">selected</c:if> >金昌</option>
						<option value="140"<c:if test="${pd.City_NAME == '晋城' }">selected</c:if> >晋城</option>
						<option value="245"<c:if test="${pd.City_NAME == '景德镇' }">selected</c:if> >景德镇</option>
						<option value="296"<c:if test="${pd.City_NAME == '荆门' }">selected</c:if> >荆门</option>
						<option value="298"<c:if test="${pd.City_NAME == '荆州' }">selected</c:if> >荆州</option>
						<option value="214"<c:if test="${pd.City_NAME == '金华' }">selected</c:if> >金华</option>
						<option value="262"<c:if test="${pd.City_NAME == '济宁' }">selected</c:if> >济宁</option>
						<option value="142"<c:if test="${pd.City_NAME == '晋中' }">selected</c:if> >晋中</option>
						<option value="165"<c:if test="${pd.City_NAME == '锦州' }">selected</c:if> >锦州</option>
						<option value="247"<c:if test="${pd.City_NAME == '九江' }">selected</c:if> >九江</option>
						<option value="446"<c:if test="${pd.City_NAME == '酒泉' }">selected</c:if> >酒泉</option>
						<option value="184"<c:if test="${pd.City_NAME == '鸡西' }">selected</c:if> >鸡西</option>
						<option value="289"<c:if test="${pd.City_NAME == '济源' }">selected</c:if> >济源</option>
						<option value="273"<c:if test="${pd.City_NAME == '开封' }">selected</c:if> >开封</option>
						<option value="468"<c:if test="${pd.City_NAME == '克拉玛依' }">selected</c:if> >克拉玛依</option>
						<option value="476"<c:if test="${pd.City_NAME == '喀什' }">selected</c:if> >喀什</option>
						<option value="405"<c:if test="${pd.City_NAME == '昆明' }">selected</c:if> >昆明</option>
						<option value="354"<c:if test="${pd.City_NAME == '来宾' }">selected</c:if> >来宾</option>
						<option value="266"<c:if test="${pd.City_NAME == '莱芜' }">selected</c:if> >莱芜</option>
						<option value="134"<c:if test="${pd.City_NAME == '廊坊' }">selected</c:if> >廊坊</option>
						<option value="438"<c:if test="${pd.City_NAME == '兰州' }">selected</c:if> >兰州</option>
						<option value="421"<c:if test="${pd.City_NAME == '拉萨' }">selected</c:if> >拉萨</option>
						<option value="384"<c:if test="${pd.City_NAME == '乐山' }">selected</c:if> >乐山</option>
						<option value="395"<c:if test="${pd.City_NAME == '凉山' }">selected</c:if> >凉山</option>
						<option value="201"<c:if test="${pd.City_NAME == '连云港' }">selected</c:if> >连云港</option>
						<option value="269"<c:if test="${pd.City_NAME == '聊城' }">selected</c:if> >聊城</option>
						<option value="168"<c:if test="${pd.City_NAME == '辽阳' }">selected</c:if> >辽阳</option>
						<option value="176"<c:if test="${pd.City_NAME == '辽源' }">selected</c:if> >辽源</option>
						<option value="409"<c:if test="${pd.City_NAME == '丽江' }">selected</c:if> >丽江</option>
						<option value="411"<c:if test="${pd.City_NAME == '临沧' }">selected</c:if> >临沧</option>
						<option value="145"<c:if test="${pd.City_NAME == '临汾' }">selected</c:if> >临汾</option>
						<option value="450"<c:if test="${pd.City_NAME == '临夏' }">selected</c:if> >临夏</option>
						<option value="267"<c:if test="${pd.City_NAME == '临沂' }">selected</c:if> >临沂</option>
						<option value="427"<c:if test="${pd.City_NAME == '林芝' }">selected</c:if> >林芝</option>
						<option value="218"<c:if test="${pd.City_NAME == '丽水' }">selected</c:if> >丽水</option>
						<option value="231"<c:if test="${pd.City_NAME == '六安' }">selected</c:if> >六安</option>
						<option value="397"<c:if test="${pd.City_NAME == '六盘水' }">selected</c:if> >六盘水</option>
						<option value="343"<c:if test="${pd.City_NAME == '柳州' }">selected</c:if> >柳州</option>
						<option value="449"<c:if test="${pd.City_NAME == '陇南' }">selected</c:if> >陇南</option>
						<option value="242"<c:if test="${pd.City_NAME == '龙岩' }">selected</c:if> >龙岩</option>
						<option value="319"<c:if test="${pd.City_NAME == '娄底' }">selected</c:if> >娄底</option>
						<option value="282"<c:if test="${pd.City_NAME == '漯河' }">selected</c:if> >漯河</option>
						<option value="274"<c:if test="${pd.City_NAME == '洛阳' }">selected</c:if> >洛阳</option>
						<option value="378"<c:if test="${pd.City_NAME == '泸州' }">selected</c:if> >泸州</option>
						<option value="146"<c:if test="${pd.City_NAME == '吕梁' }">selected</c:if> >吕梁</option>
						<option value="223"<c:if test="${pd.City_NAME == '马鞍山' }">selected</c:if> >马鞍山</option>
						<option value="329"<c:if test="${pd.City_NAME == '茂名' }">selected</c:if> >茂名</option>
						<option value="386"<c:if test="${pd.City_NAME == '眉山' }">selected</c:if> >眉山</option>
						<option value="332"<c:if test="${pd.City_NAME == '梅州' }">selected</c:if> >梅州</option>
						<option value="380"<c:if test="${pd.City_NAME == '绵阳' }">selected</c:if> >绵阳</option>
						<option value="191"<c:if test="${pd.City_NAME == '牡丹江' }">selected</c:if> >牡丹江</option>
						<option value="244"<c:if test="${pd.City_NAME == '南昌' }">selected</c:if> >南昌</option>
						<option value="385"<c:if test="${pd.City_NAME == '南充' }">selected</c:if> >南充</option>
						<option value="195"<c:if test="${pd.City_NAME == '南京' }">selected</c:if> >南京</option>
						<option value="342"<c:if test="${pd.City_NAME == '南宁' }">selected</c:if> >南宁</option>
						<option value="241"<c:if test="${pd.City_NAME == '南平' }">selected</c:if> >南平</option>
						<option value="200"<c:if test="${pd.City_NAME == '南通' }">selected</c:if> >南通</option>
						<option value="284"<c:if test="${pd.City_NAME == '南阳' }">selected</c:if> >南阳</option>
						<option value="425"<c:if test="${pd.City_NAME == '那曲' }">selected</c:if> >那曲</option>
						<option value="383"<c:if test="${pd.City_NAME == '内江' }">selected</c:if> >内江</option>
						<option value="209"<c:if test="${pd.City_NAME == '宁波' }">selected</c:if> >宁波</option>
						<option value="243"<c:if test="${pd.City_NAME == '宁德' }">selected</c:if> >宁德</option>
						<option value="418"<c:if test="${pd.City_NAME == '怒江' }">selected</c:if> >怒江</option>
						<option value="169"<c:if test="${pd.City_NAME == '盘锦' }">selected</c:if> >盘锦</option>
						<option value="377"<c:if test="${pd.City_NAME == '攀枝花' }">selected</c:if> >攀枝花</option>
						<option value="275"<c:if test="${pd.City_NAME == '平顶山' }">selected</c:if> >平顶山</option>
						<option value="445"<c:if test="${pd.City_NAME == '平凉' }">selected</c:if> >平凉</option>
						<option value="246"<c:if test="${pd.City_NAME == '萍乡' }">selected</c:if> >萍乡</option>
						<option value="410"<c:if test="${pd.City_NAME == '普洱' }">selected</c:if> >普洱</option>
						<option value="237"<c:if test="${pd.City_NAME == '莆田' }">selected</c:if> >莆田</option>
						<option value="280"<c:if test="${pd.City_NAME == '濮阳' }">selected</c:if> >濮阳</option>
						<option value="403"<c:if test="${pd.City_NAME == '黔东南' }">selected</c:if> >黔东南</option>
						<option value="304"<c:if test="${pd.City_NAME == '潜江' }">selected</c:if> >潜江</option>
						<option value="404"<c:if test="${pd.City_NAME == '黔南' }">selected</c:if> >黔南</option>
						<option value="402"<c:if test="${pd.City_NAME == '黔西南' }">selected</c:if> >黔西南</option>
						<option value="256"<c:if test="${pd.City_NAME == '青岛' }">selected</c:if> >青岛</option>
						<option value="447"<c:if test="${pd.City_NAME == '庆阳' }">selected</c:if> >庆阳</option>
						<option value="336"<c:if test="${pd.City_NAME == '清远' }">selected</c:if> >清远</option>
						<option value="127"<c:if test="${pd.City_NAME == '秦皇岛' }">selected</c:if> >秦皇岛</option>
						<option value="348"<c:if test="${pd.City_NAME == '钦州' }">selected</c:if> >钦州</option>
						<option value="360"<c:if test="${pd.City_NAME == '琼海' }">selected</c:if> >琼海</option>
						<option value="183"<c:if test="${pd.City_NAME == '齐齐哈尔' }">selected</c:if> >齐齐哈尔</option>
						<option value="190"<c:if test="${pd.City_NAME == '七台河' }">selected</c:if> >七台河</option>
						<option value="239"<c:if test="${pd.City_NAME == '泉州' }">selected</c:if> >泉州</option>
						<option value="406"<c:if test="${pd.City_NAME == '曲靖' }">selected</c:if> >曲靖</option>
						<option value="215"<c:if test="${pd.City_NAME == '衢州' }">selected</c:if> >衢州</option>
						<option value="265"<c:if test="${pd.City_NAME == '日照' }">selected</c:if> >日照</option>
						<option value="283"<c:if test="${pd.City_NAME == '三门峡' }">selected</c:if> >三门峡</option>
						<option value="238"<c:if test="${pd.City_NAME == '三明' }">selected</c:if> >三明</option>
						<option value="357"<c:if test="${pd.City_NAME == '三亚' }">selected</c:if> >三亚</option>
						<option value="3"<c:if test="${pd.City_NAME == '上海' }">selected</c:if> >上海</option>
						<option value="437"<c:if test="${pd.City_NAME == '商洛' }">selected</c:if> >商洛</option>
						<option value="285"<c:if test="${pd.City_NAME == '商丘' }">selected</c:if> >商丘</option>
						<option value="254"<c:if test="${pd.City_NAME == '上饶' }">selected</c:if> >上饶</option>
						<option value="423"<c:if test="${pd.City_NAME == '山南' }">selected</c:if> >山南</option>
						<option value="325"<c:if test="${pd.City_NAME == '汕头' }">selected</c:if> >汕头</option>
						<option value="333"<c:if test="${pd.City_NAME == '汕尾' }">selected</c:if> >汕尾</option>
						<option value="322"<c:if test="${pd.City_NAME == '韶关' }">selected</c:if> >韶关</option>
						<option value="213"<c:if test="${pd.City_NAME == '绍兴' }">selected</c:if> >绍兴</option>
						<option value="311"<c:if test="${pd.City_NAME == '邵阳' }">selected</c:if> >邵阳</option>
						<option value="306"<c:if test="${pd.City_NAME == '神农架林区' }">selected</c:if> >神农架林区</option>
						<option value="159"<c:if test="${pd.City_NAME == '沈阳' }">selected</c:if> >沈阳</option>
						<option value="323"<c:if test="${pd.City_NAME == '深圳' }">selected</c:if> >深圳</option>
						<option value="481"<c:if test="${pd.City_NAME == '石河子' }">selected</c:if> >石河子</option>
						<option value="125"<c:if test="${pd.City_NAME == '石家庄' }">selected</c:if> >石家庄</option>
						<option value="292"<c:if test="${pd.City_NAME == '十堰' }">selected</c:if> >十堰</option>
						<option value="461"<c:if test="${pd.City_NAME == '石嘴山' }">selected</c:if> >石嘴山</option>
						<option value="186"<c:if test="${pd.City_NAME == '双鸭山' }">selected</c:if> >双鸭山</option>
						<option value="141"<c:if test="${pd.City_NAME == '朔州' }">selected</c:if> >朔州</option>
						<option value="175"<c:if test="${pd.City_NAME == '四平' }">selected</c:if> >四平</option>
						<option value="179"<c:if test="${pd.City_NAME == '松原' }">selected</c:if> >松原</option>
						<option value="193"<c:if test="${pd.City_NAME == '绥化' }">selected</c:if> >绥化</option>
						<option value="382"<c:if test="${pd.City_NAME == '遂宁' }">selected</c:if> >遂宁</option>
						<option value="301"<c:if test="${pd.City_NAME == '随州' }">selected</c:if> >随州</option>
						<option value="207"<c:if test="${pd.City_NAME == '宿迁' }">selected</c:if> >宿迁</option>
						<option value="199"<c:if test="${pd.City_NAME == '苏州' }">selected</c:if> >苏州</option>
						<option value="230"<c:if test="${pd.City_NAME == '宿州' }">selected</c:if> >宿州</option>
						<option value="479"<c:if test="${pd.City_NAME == '塔城' }">selected</c:if> >塔城</option>
						<option value="263"<c:if test="${pd.City_NAME == '泰安' }">selected</c:if> >泰安</option>
						<option value="485"<c:if test="${pd.City_NAME == '台北' }">selected</c:if> >台北</option>
						<option value="136"<c:if test="${pd.City_NAME == '太原' }">selected</c:if> >太原</option>
						<option value="217"<c:if test="${pd.City_NAME == '台州' }">selected</c:if> >台州</option>
						<option value="206"<c:if test="${pd.City_NAME == '泰州' }">selected</c:if> >泰州</option>
						<option value="126"<c:if test="${pd.City_NAME == '唐山' }">selected</c:if> >唐山</option>
						<option value="2"<c:if test="${pd.City_NAME == '天津' }">selected</c:if> >天津</option>
						<option value="305"<c:if test="${pd.City_NAME == '天门' }">selected</c:if> >天门</option>
						<option value="442"<c:if test="${pd.City_NAME == '天水' }">selected</c:if> >天水</option>
						<option value="170"<c:if test="${pd.City_NAME == '铁岭' }">selected</c:if> >铁岭</option>
						<option value="429"<c:if test="${pd.City_NAME == '铜川' }">selected</c:if> >铜川</option>
						<option value="177"<c:if test="${pd.City_NAME == '通化' }">selected</c:if> >通化</option>
						<option value="151"<c:if test="${pd.City_NAME == '通辽' }">selected</c:if> >通辽</option>
						<option value="225"<c:if test="${pd.City_NAME == '铜陵' }">selected</c:if> >铜陵</option>
						<option value="401"<c:if test="${pd.City_NAME == '铜仁' }">selected</c:if> >铜仁</option>
						<option value="469"<c:if test="${pd.City_NAME == '吐鲁番' }">selected</c:if> >吐鲁番</option>
						<option value="363"<c:if test="${pd.City_NAME == '万宁' }">selected</c:if> >万宁</option>
						<option value="261"<c:if test="${pd.City_NAME == '潍坊' }">selected</c:if> >潍坊</option>
						<option value="264"<c:if test="${pd.City_NAME == '威海' }">selected</c:if> >威海</option>
						<option value="432"<c:if test="${pd.City_NAME == '渭南' }">selected</c:if> >渭南</option>
						<option value="362"<c:if test="${pd.City_NAME == '文昌' }">selected</c:if> >文昌</option>
						<option value="414"<c:if test="${pd.City_NAME == '文山' }">selected</c:if> >文山</option>
						<option value="210"<c:if test="${pd.City_NAME == '温州' }">selected</c:if> >温州</option>
						<option value="149"<c:if test="${pd.City_NAME == '乌海' }">selected</c:if> >乌海</option>
						<option value="290"<c:if test="${pd.City_NAME == '武汉' }">selected</c:if> >武汉</option>
						<option value="220"<c:if test="${pd.City_NAME == '芜湖' }">selected</c:if> >芜湖</option>
						<option value="155"<c:if test="${pd.City_NAME == '乌兰察布' }">selected</c:if> >乌兰察布</option>
						<option value="467"<c:if test="${pd.City_NAME == '乌鲁木齐' }">selected</c:if> >乌鲁木齐</option>
						<option value="443"<c:if test="${pd.City_NAME == '武威' }">selected</c:if> >武威</option>
						<option value="196"<c:if test="${pd.City_NAME == '无锡' }">selected</c:if> >无锡</option>
						<option value="359"<c:if test="${pd.City_NAME == '五指山' }">selected</c:if> >五指山</option>
						<option value="462"<c:if test="${pd.City_NAME == '吴忠' }">selected</c:if> >吴忠</option>
						<option value="345"<c:if test="${pd.City_NAME == '梧州' }">selected</c:if> >梧州</option>
						<option value="236"<c:if test="${pd.City_NAME == '厦门' }">selected</c:if> >厦门</option>
						<option value="428"<c:if test="${pd.City_NAME == '西安' }">selected</c:if> >西安</option>
						<option value="309"<c:if test="${pd.City_NAME == '湘潭' }">selected</c:if> >湘潭</option>
						<option value="320"<c:if test="${pd.City_NAME == '湘西' }">selected</c:if> >湘西</option>
						<option value="294"<c:if test="${pd.City_NAME == '襄阳' }">selected</c:if> >襄阳</option>
						<option value="300"<c:if test="${pd.City_NAME == '咸宁' }">selected</c:if> >咸宁</option>
						<option value="303"<c:if test="${pd.City_NAME == '仙桃' }">selected</c:if> >仙桃</option>
						<option value="431"<c:if test="${pd.City_NAME == '咸阳' }">selected</c:if> >咸阳</option>
						<option value="297"<c:if test="${pd.City_NAME == '孝感' }">selected</c:if> >孝感</option>
						<option value="424"<c:if test="${pd.City_NAME == '日喀则' }">selected</c:if> >日喀则</option>
						<option value="157"<c:if test="${pd.City_NAME == '锡林郭勒' }">selected</c:if> >锡林郭勒</option>
						<option value="156"<c:if test="${pd.City_NAME == '兴安' }">selected</c:if> >兴安</option>
						<option value="129"<c:if test="${pd.City_NAME == '邢台' }">selected</c:if> >邢台</option>
						<option value="452"<c:if test="${pd.City_NAME == '西宁' }">selected</c:if> >西宁</option>
						<option value="278"<c:if test="${pd.City_NAME == '新乡' }">selected</c:if> >新乡</option>
						<option value="286"<c:if test="${pd.City_NAME == '信阳' }">selected</c:if> >信阳</option>
						<option value="248"<c:if test="${pd.City_NAME == '新余' }">selected</c:if> >新余</option>
						<option value="144"<c:if test="${pd.City_NAME == '忻州' }">selected</c:if> >忻州</option>
						<option value="415"<c:if test="${pd.City_NAME == '西双版纳' }">selected</c:if> >西双版纳</option>
						<option value="234"<c:if test="${pd.City_NAME == '宣城' }">selected</c:if> >宣城</option>
						<option value="281"<c:if test="${pd.City_NAME == '许昌' }">selected</c:if> >许昌</option>
						<option value="197"<c:if test="${pd.City_NAME == '徐州' }">selected</c:if> >徐州</option>
						<option value="390"<c:if test="${pd.City_NAME == '雅安' }">selected</c:if> >雅安</option>
						<option value="433"<c:if test="${pd.City_NAME == '延安' }">selected</c:if> >延安</option>
						<option value="181"<c:if test="${pd.City_NAME == '延边' }">selected</c:if> >延边</option>
						<option value="203"<c:if test="${pd.City_NAME == '盐城' }">selected</c:if> >盐城</option>
						<option value="335"<c:if test="${pd.City_NAME == '阳江' }">selected</c:if> >阳江</option>
						<option value="138"<c:if test="${pd.City_NAME == '阳泉' }">selected</c:if> >阳泉</option>
						<option value="204"<c:if test="${pd.City_NAME == '扬州' }">selected</c:if> >扬州</option>
						<option value="260"<c:if test="${pd.City_NAME == '烟台' }">selected</c:if> >烟台</option>
						<option value="387"<c:if test="${pd.City_NAME == '宜宾' }">selected</c:if> >宜宾</option>
						<option value="293"<c:if test="${pd.City_NAME == '宜昌' }">selected</c:if> >宜昌</option>
						<option value="252"<c:if test="${pd.City_NAME == '宜春' }">selected</c:if> >宜春</option>
						<option value="188"<c:if test="${pd.City_NAME == '伊春' }">selected</c:if> >伊春</option>
						<option value="478"<c:if test="${pd.City_NAME == '伊犁' }">selected</c:if> >伊犁</option>
						<option value="460"<c:if test="${pd.City_NAME == '银川' }">selected</c:if> >银川</option>
						<option value="166"<c:if test="${pd.City_NAME == '营口' }">selected</c:if> >营口</option>
						<option value="249"<c:if test="${pd.City_NAME == '鹰潭' }">selected</c:if> >鹰潭</option>
						<option value="315"<c:if test="${pd.City_NAME == '益阳' }">selected</c:if> >益阳</option>
						<option value="317"<c:if test="${pd.City_NAME == '永州' }">selected</c:if> >永州</option>
						<option value="312"<c:if test="${pd.City_NAME == '岳阳' }">selected</c:if> >岳阳</option>
						<option value="350"<c:if test="${pd.City_NAME == '玉林' }">selected</c:if> >玉林</option>
						<option value="435"<c:if test="${pd.City_NAME == '榆林' }">selected</c:if> >榆林</option>
						<option value="143"<c:if test="${pd.City_NAME == '运城' }">selected</c:if> >运城</option>
						<option value="341"<c:if test="${pd.City_NAME == '云浮' }">selected</c:if> >云浮</option>
						<option value="458"<c:if test="${pd.City_NAME == '玉树' }">selected</c:if> >玉树</option>
						<option value="407"<c:if test="${pd.City_NAME == '玉溪' }">selected</c:if> >玉溪</option>
						<option value="258"<c:if test="${pd.City_NAME == '枣庄' }">selected</c:if> >枣庄</option>
						<option value="314"<c:if test="${pd.City_NAME == '张家界' }">selected</c:if> >张家界</option>
						<option value="131"<c:if test="${pd.City_NAME == '张家口' }">selected</c:if> >张家口</option>
						<option value="444"<c:if test="${pd.City_NAME == '张掖' }">selected</c:if> >张掖</option>
						<option value="240"<c:if test="${pd.City_NAME == '漳州' }">selected</c:if> >漳州</option>
						<option value="328"<c:if test="${pd.City_NAME == '湛江' }">selected</c:if> >湛江</option>
						<option value="330"<c:if test="${pd.City_NAME == '肇庆' }">selected</c:if> >肇庆</option>
						<option value="408"<c:if test="${pd.City_NAME == '昭通' }">selected</c:if> >昭通</option>
						<option value="272"<c:if test="${pd.City_NAME == '郑州' }">selected</c:if> >郑州</option>
						<option value="205"<c:if test="${pd.City_NAME == '镇江' }">selected</c:if> >镇江</option>
						<option value="338"<c:if test="${pd.City_NAME == '中山' }">selected</c:if> >中山</option>
						<option value="464"<c:if test="${pd.City_NAME == '中卫' }">selected</c:if> >中卫</option>
						<option value="287"<c:if test="${pd.City_NAME == '周口' }">selected</c:if> >周口</option>
						<option value="216"<c:if test="${pd.City_NAME == '舟山' }">selected</c:if> >舟山</option>
						<option value="324"<c:if test="${pd.City_NAME == '珠海' }">selected</c:if> >珠海</option>
						<option value="288"<c:if test="${pd.City_NAME == '驻马店' }">selected</c:if> >驻马店</option>
						<option value="308"<c:if test="${pd.City_NAME == '株洲' }">selected</c:if> >株洲</option>
						<option value="257"<c:if test="${pd.City_NAME == '淄博' }">selected</c:if> >淄博</option>
						<option value="376"<c:if test="${pd.City_NAME == '自贡' }">selected</c:if> >自贡</option>
						<option value="392"<c:if test="${pd.City_NAME == '资阳' }">selected</c:if> >资阳</option>
						<option value="398"<c:if test="${pd.City_NAME == '遵义' }">selected</c:if> >遵义</option>
					</select>
				</td>			
			</tr>	
			<tr>
				<td><input type="text" name="MEMBER_NAME" id="MEMBER_NAME" value="${pd.MEMBER_NAME}" maxlength="32" placeholder="这里输入姓名" title="姓名"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_PHONE" id="MEMBER_PHONE" value="${pd.MEMBER_PHONE}" maxlength="32" placeholder="这里输入电话" title="电话"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_VILLAGE_NAME" id="MEMBER_VILLAGE_NAME" value="${pd.MEMBER_VILLAGE_NAME}" maxlength="32" placeholder="这里输入小区楼盘名称" title="小区楼盘名称"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_HOU_AREA" id="MEMBER_HOU_AREA" value="${pd.MEMBER_HOU_AREA}" maxlength="32" placeholder="这里输入房屋面积" title="房屋面积"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_HOU_BUDGET" id="MEMBER_HOU_BUDGET" value="${pd.MEMBER_HOU_BUDGET}" maxlength="32" placeholder="这里输入装修预算" title="装修预算"/></td>
			</tr>
			<tr>
				<td><input class="span10 date-picker" name="MEMBER_APPOINTMENT_DATE" id="MEMBER_APPOINTMENT_DATE" value="${pd.MEMBER_APPOINTMENT_DATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="预约时间" title="预约时间"/></td>
			</tr>
			<tr>
				<td><input type="text" name="MEMBER_APPOINTMENT_REMARK" id="MEMBER_APPOINTMENT_REMARK" value="${pd.MEMBER_APPOINTMENT_REMARK}" maxlength="32" placeholder="这里输入备注" title="备注"/></td>
			</tr>
			<tr>
				<td>
					<select name="MEMBER_APPOINTMENT_STATUS" title="状态">
					<option value="1" <c:if test="${pd.MEMBER_APPOINTMENT_STATUS == '1' }">selected</c:if> >未受理</option>
					<option value="2" <c:if test="${pd.MEMBER_APPOINTMENT_STATUS == '2' }">selected</c:if> >已受理</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
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