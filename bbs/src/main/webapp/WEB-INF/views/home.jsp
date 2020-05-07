<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="wi	dth=device-width, initial-scale=1.0" name="viewport">

  <title>bbs</title>
  <meta content="" name="descriptison">
  <meta content="" name="keywords">

  <c:import url="fragments/head.jsp"></c:import>

  <!-- =======================================================
  * Template Name: Moderna - v2.0.1
  * Template URL: https://bootstrapmade.com/free-bootstrap-template-corporate-moderna/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <c:import url="fragments/header.jsp"></c:import>
  <!-- End Header -->

  <!-- ======= Hero Section ======= -->

	


  <main id="main">

    <!-- ======= Services Section ======= -->
    <section class="services">
      <div class="container">
<!-- 
           <div class="card card-primary login">
              <div class="card-header">
                <h3 class="card-title">관리자 권한 로그인</h3>
              </div>
              /.card-header
              form start
              <form role="form" class="login-form" action="login" method="post">
                <div class="card-body">
                  <div class="form-group">
                    <label for="admin-login">ID</label>
                    <input type="text" class="form-control" id="admin-login" name="mId" value="admin001" placeholder=" ">
                  </div>
                  <div class="form-group">
                    <label for="admin-pw">Password</label>
                    <input type="password" class="form-control" id="admin-pw" name="mPw" value="pw001" placeholder="">
                  </div>
                   <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="admin-keep">
                    <label class="form-check-label" for="admin-keep">로그인유지</label>
                  </div>
                </div>
                /.card-body
                <div class="card-footer">
                  <button type="button" class="login-btn btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
             -->
			<div class="card card-primary login" style="width: 100%">
              <div class="card-header">
                <h3 class="card-title">로그인</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form role="form" class="login-form" action="login" method="post">
                <div class="card-body">
                  <div class="form-group">
                    <label for="login-id">ID</label>
                    <input type="text" class="form-control" id="login-id" name="mId" value="id001" placeholder=" ">
                  </div>
                  <div class="form-group">
                    <label for="login-pw">Password</label>
                    <input type="password" class="form-control" id="login-pw" name="mPw" value="pw001" placeholder="">
                  </div>
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="login-keep" name="loginKeep">
                    <label class="form-check-label" for="login-keep">로그인 유지</label>
                  </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                	<input type="hidden" name="captchaKey" value="${key}"/>
                	<img src="/captcha/${captchaImage}.jpg" style="height:150px;">
                	<input class="form-control" type="text" name="captchaValue" style="width: 50%"/>
                  	<button type="button" class="login-btn btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
            <div style="text-align: center">
	            <!-- <a href="#">계정찾기</a>
	            / -->
	            <a href="signup">회원가입</a>
            </div>

      </div>
    </section><!-- End Services Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
  <script type="text/javascript">
  	$('.login-btn').click(function(){
  	  const loginForm = $(this).parents('.login-form')
  	  const formData = loginForm.serialize();
	  $.ajax({
	  	   url : "login"
	  	 , method : "POST"
	  	 , data : formData
	  	 , dataType : "json"
	  }).done(function(data){
		  if(data){
			  console.log(data)
			  location.href="boardList"
		  }else{
			  alert("일치하지 않습니다")
		  }
	  }).fail(function(){
		  console.log(2)
	  })
  		
  	})
  </script>
  
</body>

</html>