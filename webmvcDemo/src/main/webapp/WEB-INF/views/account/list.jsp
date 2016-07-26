<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head> 
<title>添加</title>
</head>
<div id="content">
	<c:if test="${!empty list }">
		<table class="table table-condensed table-striped ">
		    <tr>
		    	<th>ID</th>
		    	<th>项目编号</th>
		    	<th>姓名</th>
		    	<th>余额</th>
		    	<th>创建时间</th>
		    </tr>
			<c:forEach items="${list }" var="account">
			   <tr>
			   	<td>${account.id }</td>
			   	<td>${account.proId }</td>
			   	<td>${account.name }</td>
			   	<td>${account.balance }</td>
			   	<td>${account.createTime }</td>
			   </tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty list }">
		<h1>没有记录</h1>
	</c:if>
	<!-- end content -->
</div>