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
	<form action="${ctx }/demo/add" method="post" id="addForm" class="form-horizontal form-validation" >
	  <div class="control-group">
	    <label class="control-label" for="proId">项目编号：</label>
	    <div class="controls">
	      <input type="text" TABINDEX="1" placeholder="最多输入4个字符" maxLength="4"   name="proId" id="proId"
	 			class="required {required:true,rangelength:[1,4],messages: { rangelength: $.validator.format('最多可填{0}~{1}个字符')}}"  />
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="name">姓名：</label>
	    <div class="controls">
	      <input type="text" TABINDEX="2" maxlength="30" placeholder="最多输入10个字符"  name="name" id="name"
	      		class="required {required:true,rangelength:[1,10],messages: { rangelength: $.validator.format('最多可填{0}~{1}个字符')}}" />
	    </div>
	  </div>
	  <div class="control-group">
	    <div class="controls">
	      <input type="submit" class="btn btn-primary" value="添加"/>
	    </div>
	  </div>
	</form>
</div>