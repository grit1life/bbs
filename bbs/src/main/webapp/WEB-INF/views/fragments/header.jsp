<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<header id="header" class="fixed-top">
    <div class="container">

      <div class="logo float-left">
        <h1 class="text-light"><a href="http://louw0.cafe24.com/"><span>Main</span></a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
      </div>

      <nav class="nav-menu float-right d-none d-lg-block">
        <ul>
          <li class="active"><a href=".">Home</a></li>
           <c:if test="${sId!=null}">
	          <li><a href="myPage">myPage</a></li>
          </c:if>
          <li><a href="boardList">bbs</a></li>
          <li><a href="http://louw0.cafe24.com/index">TeamProject</a></li>
          <c:if test="${sId!=null}">
          	<button class="logout-btn btn btn-success" type="button">logout</button>
          </c:if>
        </ul>
      </nav><!-- .nav-menu -->

    </div>
</header>