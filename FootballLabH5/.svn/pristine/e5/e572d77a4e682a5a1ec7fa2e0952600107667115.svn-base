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
	<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
	
	</head> 

<body>
		
		
<div class="container-fluid" id="main-container">

		

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="campaign/pc/list?isM1=${pdm.isM1 }&isM2=${pdm.isM2 }" method="post" name="userForm" id="userForm">
			<table border='0'>
				<tr>
				
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="title" value="${pd.title }" placeholder="这里输入标题" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="publisher" value="${pd.publisher }" placeholder="这里输入发布人" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td><input class="span10 date-picker" name="lastStart" value="${pd.lastStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100px;" placeholder="到期日期从"/></td>
					<td><input class="span10 date-picker" name="lastEnd" value="${pd.lastEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100px;" placeholder="到期日期至"/></td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="status" data-placeholder="状态" style="vertical-align:top;width: 79px;">
						<option value=""></option>
						<option value="1" <c:if test="${pd.status == '1' }">selected</c:if> >已发布</option>
						<option value="0" <c:if test="${pd.status == '0' }">selected</c:if> >未发布</option>
						</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="City_NAME" data-placeholder="城市" style="vertical-align:top;width: 79px;">
						<option value="" ></option>
						<option value="北京">北京</option><option value="上海">上海</option><option value="广州">广州</option><option value="深圳">深圳</option><option value="成都">成都</option><option value="重庆">重庆</option><option value="福州">福州</option><option value="厦门">厦门</option><option value="杭州">杭州</option><option value="武汉">武汉</option><option value="阿坝">阿坝</option><option value="阿克苏">阿克苏</option><option value="阿拉善">阿拉善</option><option value="阿里">阿里</option><option value="阿勒泰">阿勒泰</option><option value="安康">安康</option><option value="安庆">安庆</option><option value="鞍山">鞍山</option><option value="安顺">安顺</option><option value="安阳">安阳</option><option value="白城">白城</option><option value="百色">百色</option><option value="白山">白山</option><option value="白银">白银</option><option value="保定">保定</option><option value="宝鸡">宝鸡</option><option value="保山">保山</option><option value="包头">包头</option><option value="巴彦淖尔">巴彦淖尔</option><option value="巴音郭楞">巴音郭楞</option><option value="巴中">巴中</option><option value="北海">北海</option><option value="北京">北京</option><option value="蚌埠">蚌埠</option><option value="本溪">本溪</option><option value="毕节">毕节</option><option value="滨州">滨州</option><option value="博尔塔拉">博尔塔拉</option><option value="亳州">亳州</option><option value="沧州">沧州</option><option value="长春">长春</option><option value="常德">常德</option><option value="昌都">昌都</option><option value="昌吉">昌吉</option><option value="长沙">长沙</option><option value="长治">长治</option><option value="常州">常州</option><option value="朝阳">朝阳</option><option value="潮州">潮州</option><option value="承德">承德</option><option value="成都">成都</option><option value="郴州">郴州</option><option value="赤峰">赤峰</option><option value="池州">池州</option><option value="重庆">重庆</option><option value="崇左">崇左</option><option value="楚雄">楚雄</option><option value="滁州">滁州</option><option value="大理">大理</option><option value="大连">大连</option><option value="丹东">丹东</option><option value="儋州">儋州</option><option value="大庆">大庆</option><option value="大同">大同</option><option value="大兴安岭">大兴安岭</option><option value="达州">达州</option><option value="德宏">德宏</option><option value="德阳">德阳</option><option value="德州">德州</option><option value="定西">定西</option><option value="迪庆">迪庆</option><option value="东方">东方</option><option value="东莞">东莞</option><option value="东营">东营</option><option value="鄂尔多斯">鄂尔多斯</option><option value="恩施">恩施</option><option value="鄂州">鄂州</option><option value="防城港">防城港</option><option value="佛山">佛山</option><option value="抚顺">抚顺</option><option value="阜新">阜新</option><option value="阜阳">阜阳</option><option value="福州">福州</option><option value="抚州">抚州</option><option value="甘南">甘南</option><option value="赣州">赣州</option><option value="甘孜">甘孜</option><option value="广安">广安</option><option value="广元">广元</option><option value="广州">广州</option><option value="贵港">贵港</option><option value="桂林">桂林</option><option value="贵阳">贵阳</option><option value="果洛">果洛</option><option value="固原">固原</option><option value="哈尔滨">哈尔滨</option><option value="海北">海北</option><option value="海东">海东</option><option value="海口">海口</option><option value="海南">海南</option><option value="海西">海西</option><option value="哈密">哈密</option><option value="邯郸">邯郸</option><option value="杭州">杭州</option><option value="汉中">汉中</option><option value="鹤壁">鹤壁</option><option value="河池">河池</option><option value="合肥">合肥</option><option value="鹤岗">鹤岗</option><option value="黑河">黑河</option><option value="衡水">衡水</option><option value="衡阳">衡阳</option><option value="河源">河源</option><option value="菏泽">菏泽</option><option value="贺州">贺州</option><option value="红河">红河</option><option value="和田">和田</option><option value="淮安">淮安</option><option value="淮北">淮北</option><option value="怀化">怀化</option><option value="淮南">淮南</option><option value="黄冈">黄冈</option><option value="黄南">黄南</option><option value="黄山">黄山</option><option value="黄石">黄石</option><option value="呼和浩特">呼和浩特</option><option value="惠州">惠州</option><option value="葫芦岛">葫芦岛</option><option value="呼伦贝尔">呼伦贝尔</option><option value="湖州">湖州</option><option value="佳木斯">佳木斯</option><option value="吉安">吉安</option><option value="江门">江门</option><option value="焦作">焦作</option><option value="嘉兴">嘉兴</option><option value="嘉峪关">嘉峪关</option><option value="揭阳">揭阳</option><option value="吉林">吉林</option><option value="济南">济南</option><option value="金昌">金昌</option><option value="晋城">晋城</option><option value="景德镇">景德镇</option><option value="荆门">荆门</option><option value="荆州">荆州</option><option value="金华">金华</option><option value="济宁">济宁</option><option value="晋中">晋中</option><option value="锦州">锦州</option><option value="九江">九江</option><option value="酒泉">酒泉</option><option value="鸡西">鸡西</option><option value="济源">济源</option><option value="开封">开封</option><option value="克拉玛依">克拉玛依</option><option value="喀什">喀什</option><option value="昆明">昆明</option><option value="来宾">来宾</option><option value="莱芜">莱芜</option><option value="廊坊">廊坊</option><option value="兰州">兰州</option><option value="拉萨">拉萨</option><option value="乐山">乐山</option><option value="凉山">凉山</option><option value="连云港">连云港</option><option value="聊城">聊城</option><option value="辽阳">辽阳</option><option value="辽源">辽源</option><option value="丽江">丽江</option><option value="临沧">临沧</option><option value="临汾">临汾</option><option value="临夏">临夏</option><option value="临沂">临沂</option><option value="林芝">林芝</option><option value="丽水">丽水</option><option value="六安">六安</option><option value="六盘水">六盘水</option><option value="柳州">柳州</option><option value="陇南">陇南</option><option value="龙岩">龙岩</option><option value="娄底">娄底</option><option value="漯河">漯河</option><option value="洛阳">洛阳</option><option value="泸州">泸州</option><option value="吕梁">吕梁</option><option value="马鞍山">马鞍山</option><option value="茂名">茂名</option><option value="眉山">眉山</option><option value="梅州">梅州</option><option value="绵阳">绵阳</option><option value="牡丹江">牡丹江</option><option value="南昌">南昌</option><option value="南充">南充</option><option value="南京">南京</option><option value="南宁">南宁</option><option value="南平">南平</option><option value="南通">南通</option><option value="南阳">南阳</option><option value="那曲">那曲</option><option value="内江">内江</option><option value="宁波">宁波</option><option value="宁德">宁德</option><option value="怒江">怒江</option><option value="盘锦">盘锦</option><option value="攀枝花">攀枝花</option><option value="平顶山">平顶山</option><option value="平凉">平凉</option><option value="萍乡">萍乡</option><option value="普洱">普洱</option><option value="莆田">莆田</option><option value="濮阳">濮阳</option><option value="黔东南">黔东南</option><option value="潜江">潜江</option><option value="黔南">黔南</option><option value="黔西南">黔西南</option><option value="青岛">青岛</option><option value="庆阳">庆阳</option><option value="清远">清远</option><option value="秦皇岛">秦皇岛</option><option value="钦州">钦州</option><option value="琼海">琼海</option><option value="齐齐哈尔">齐齐哈尔</option><option value="七台河">七台河</option><option value="泉州">泉州</option><option value="曲靖">曲靖</option><option value="衢州">衢州</option><option value="日照">日照</option><option value="三门峡">三门峡</option><option value="三明">三明</option><option value="三亚">三亚</option><option value="上海">上海</option><option value="商洛">商洛</option><option value="商丘">商丘</option><option value="上饶">上饶</option><option value="山南">山南</option><option value="汕头">汕头</option><option value="汕尾">汕尾</option><option value="韶关">韶关</option><option value="绍兴">绍兴</option><option value="邵阳">邵阳</option><option value="神农架林区">神农架林区</option><option value="沈阳">沈阳</option><option value="深圳">深圳</option><option value="石河子">石河子</option><option value="石家庄">石家庄</option><option value="十堰">十堰</option><option value="石嘴山">石嘴山</option><option value="双鸭山">双鸭山</option><option value="朔州">朔州</option><option value="四平">四平</option><option value="松原">松原</option><option value="绥化">绥化</option><option value="遂宁">遂宁</option><option value="随州">随州</option><option value="宿迁">宿迁</option><option value="苏州">苏州</option><option value="宿州">宿州</option><option value="塔城">塔城</option><option value="泰安">泰安</option><option value="台北">台北</option><option value="太原">太原</option><option value="台州">台州</option><option value="泰州">泰州</option><option value="唐山">唐山</option><option value="天津">天津</option><option value="天门">天门</option><option value="天水">天水</option><option value="铁岭">铁岭</option><option value="铜川">铜川</option><option value="通化">通化</option><option value="通辽">通辽</option><option value="铜陵">铜陵</option><option value="铜仁">铜仁</option><option value="吐鲁番">吐鲁番</option><option value="万宁">万宁</option><option value="潍坊">潍坊</option><option value="威海">威海</option><option value="渭南">渭南</option><option value="文昌">文昌</option><option value="文山">文山</option><option value="温州">温州</option><option value="乌海">乌海</option><option value="武汉">武汉</option><option value="芜湖">芜湖</option><option value="乌兰察布">乌兰察布</option><option value="乌鲁木齐">乌鲁木齐</option><option value="武威">武威</option><option value="无锡">无锡</option><option value="五指山">五指山</option><option value="吴忠">吴忠</option><option value="梧州">梧州</option><option value="厦门">厦门</option><option value="西安">西安</option><option value="湘潭">湘潭</option><option value="湘西">湘西</option><option value="襄阳">襄阳</option><option value="咸宁">咸宁</option><option value="仙桃">仙桃</option><option value="咸阳">咸阳</option><option value="孝感">孝感</option><option value="日喀则">日喀则</option><option value="锡林郭勒">锡林郭勒</option><option value="兴安">兴安</option><option value="邢台">邢台</option><option value="西宁">西宁</option><option value="新乡">新乡</option><option value="信阳">信阳</option><option value="新余">新余</option><option value="忻州">忻州</option><option value="西双版纳">西双版纳</option><option value="宣城">宣城</option><option value="许昌">许昌</option><option value="徐州">徐州</option><option value="雅安">雅安</option><option value="延安">延安</option><option value="延边">延边</option><option value="盐城">盐城</option><option value="阳江">阳江</option><option value="阳泉">阳泉</option><option value="扬州">扬州</option><option value="烟台">烟台</option><option value="宜宾">宜宾</option><option value="宜昌">宜昌</option><option value="宜春">宜春</option><option value="伊春">伊春</option><option value="伊犁">伊犁</option><option value="银川">银川</option><option value="营口">营口</option><option value="鹰潭">鹰潭</option><option value="益阳">益阳</option><option value="永州">永州</option><option value="岳阳">岳阳</option><option value="玉林">玉林</option><option value="榆林">榆林</option><option value="运城">运城</option><option value="云浮">云浮</option><option value="玉树">玉树</option><option value="玉溪">玉溪</option><option value="枣庄">枣庄</option><option value="张家界">张家界</option><option value="张家口">张家口</option><option value="张掖">张掖</option><option value="漳州">漳州</option><option value="湛江">湛江</option><option value="肇庆">肇庆</option><option value="昭通">昭通</option><option value="郑州">郑州</option><option value="镇江">镇江</option><option value="中山">中山</option><option value="中卫">中卫</option><option value="周口">周口</option><option value="舟山">舟山</option><option value="珠海">珠海</option><option value="驻马店">驻马店</option><option value="株洲">株洲</option><option value="淄博">淄博</option><option value="自贡">自贡</option><option value="资阳">资阳</option><option value="遵义">遵义</option>
					</select>
					</td>
										
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"><i id="nav-search-icon" class="icon-search"></i></button></td>
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
						<th>标题</th>
						<th>城市</th>
						<th>发布人</th>
						<th><i class="icon-time hidden-phone"></i>开始时间</th>
						<th><i class="icon-time hidden-phone"></i>结束时间</th>
						<th>状态</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha==1}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
									
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.id}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td title="${var.title}">${fn:substring(var.title ,0,60)} ...</td>	
								<td>${var.City_NAME }</td>			
								<td>${var.publisher }</td>
								<td>${var.starttime }</td>
								<td>${var.endtime }</td>
								<td>
									<c:if test="${var.status == '0' }"><span class="label label-important arrowed-in">未发布</span></c:if>
									<c:if test="${var.status == '1' }"><span class="label label-success arrowed">已发布</span></c:if>
								</td>
								<td style="width: 60px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<c:if test="${QX.del == 1 }">
										<a class='btn btn-mini btn-info' title="编辑" onclick="edit('${var.id }');"><i class='icon-edit'></i></a>
										</c:if>
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
				<c:if test="${QX.add == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-small btn-success" onclick="add();">新增</a></td>
				</c:if>
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
			$("#Form").submit();
		}
		
		//新增
		function add(){
			top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>campaign/pc/goAdd';
			 diag.Width = 800;
			 diag.Height = 650;
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
		
		//修改
		function edit(id){
			top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>campaign/pc/goEdit?id='+id;
			 diag.Width = 800;
			 diag.Height = 650;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(id){
			bootbox.confirm("确定要删除该记录?", function(result) {
				if(result) {
					var url = "<%=basePath%>campaign/pc/delete?id="+id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data=="success"){
							nextPage(${page.currentPage});
						}
					});
				}
			});
		}
		
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//单选框
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
							if(msg == '确定要发布选中的数据吗?'){
								$.ajax({
									type: "POST",
									url: '<%=basePath%>campaign/pc/pubAll?tm='+new Date().getTime(),
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
			
		
		</script>
		
	</body>
</html>

