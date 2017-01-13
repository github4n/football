<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		table.dataintable {
		   border: 1px solid #888888;
		   border-collapse: collapse;
		   font-family: Arial,Helvetica,sans-serif;
		   margin-top: 10px;
		   width: 30%;
		   text-align:center;
		}
		table.dataintable th {
		   background-color: #CCCCCC;
		   border: 1px solid #888888;
		   padding: 5px 15px 5px 5px;
		   text-align: left;
		   vertical-align: baseline;
		   text-align:center;
		}
		table.dataintable td {
		   background-color: #EFEFEF;
		   border: 1px solid #AAAAAA;
		   padding: 5px 15px 5px 5px;
		   vertical-align: text-top;
		   text-align:center;
		}
	</style>
</head>


	<body>
		<div >
			<table  class="dataintable" style=" margin:0 auto;">
				<tr>
					<th>日期</th>
					<th>浏览数</th>
				</tr>
				<c:forEach items="${datas}" var="item">
					<tr>
						<td><fmt:formatDate value='${item.key}' pattern='yyyy年MM月dd日HH时' /> </td>
						<td>${item.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>

