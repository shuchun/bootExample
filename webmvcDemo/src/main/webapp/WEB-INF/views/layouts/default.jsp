<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %> --%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>Spring Boot示例:<!-- <sitemesh:title/> --><sitemesh:write property='title'/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx }/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx }/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx }/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx }/static/styles/default.css" type="text/css" rel="stylesheet" />
<style type="text/css">

.footer {
	text-align: center;
	padding: 30px 0;
	margin-top: 70px;
	border-top: 1px solid #E5E5E5;
	#background-color: whiteSmoke;
}
</style>
<script src="${ctx }/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx }/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx }/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx }/static/js/notices.js"></script>

<sitemesh:write property='head'/>
</head>

<body >
	<div class="container">
		<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:write property='body'/>
		</div>
		
	</div>
	<%@ include file="/WEB-INF/views/layouts/footer.jsp"%>
	<script src="${ctx }/static/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
	
</body>
</html>