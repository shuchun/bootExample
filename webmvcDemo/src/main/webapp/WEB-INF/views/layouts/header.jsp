<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="csr" value="${pageContext.request.servletPath}"/>

<header class="navbar header--navbar">
	<div class="navbar-inner">
		<div class="container-fluid">
			<div class="spring-logo--container">
				    <div class=" pull-left sm-table">
			          <div class="header-inner">
			            <div class="brand inline">
			              <img src="${ctx }/static/images/spring-logo.png" alt="logo" 
			              data-src="${ctx }/static/images/spring-logo.png" 
			              data-src-retina="${ctx }/static/images/spring-logo.png" width="78" height="22">
			            </div>
			        </div>
			      </div>
			</div>
			<ul class="nav pull-right">
				<li class="navbar-link "><a href="${ctx }/demo">Home</a></li>
				<li class="navbar-link "><a href="${ctx }/demo/add">Add</a></li>
				<li class="navbar-link "><a href="${ctx }/demo/list">List</a></li>
				<li class="dropdown ">
				    <a class="dropdown-toggle"
				       data-toggle="dropdown"
				       href="#">Actuator<b class="caret"></b>
				    </a>
				    <ul class="dropdown-menu">
				      <li class="navbar-link "><a href="${ctx }/health">Health</a></li>
				      <li class="navbar-link "><a href="${ctx }/trace">Trace</a></li>
				      <li class="navbar-link "><a href="${ctx }/info">Info</a></li>
				      <li class="navbar-link "><a href="${ctx }/env">Env</a></li>
				      <li class="navbar-link "><a href="${ctx }/metrics">Metrics</a></li>
				      <li class="navbar-link "><a href="${ctx }/dump">Dump</a></li>
				      <li class="navbar-link "><a href="${ctx }/mappings">Mappings</a></li>
				      <li class="navbar-link "><a href="${ctx }/autoconfig">Autoconfig</a></li>
				      <li class="navbar-link "><a href="${ctx }/configprops">Configprops</a></li>
				      <li class="navbar-link "><a href="${ctx }/beans">Beans</a></li>
				      <li class="navbar-link "><a href="${ctx }/shutdown">Shutdown</a></li>
				      <%-- <li class="navbar-link "><a href="${ctx }/dump">Dump</a></li> --%>
				    </ul>
				  </li>
			</ul>
		</div>
	</div>
</header>

