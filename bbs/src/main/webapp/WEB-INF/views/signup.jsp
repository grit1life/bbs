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

           <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">회원가입</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form id="signup-form" role="form" action="signup" method="post">
                <div class="card-body">
                  <div class="form-group">
                    <label for="signup-id">아이디</label>
                    <input type="text" class="form-control" name="mId" id="signup-id" placeholder="">
                  </div>
                  <div class="form-group">
                    <label for="signup-pw">비밀번호</label>
                    <input type="password" class="form-control" name="mPw" id="signup-pw"  placeholder="">
                  </div>
                  <div class="form-group">
                    <label for="signup-email">이메일</label>
                    <input type="email" class="form-control" name="mEmail" id="signup-email"  placeholder="">
                  </div>
                  <div class="form-group">
                    <label for="signup-name">이름</label>
                    <input type="text" class="form-control" name="mName" id="signup-name"  placeholder="">
                  </div>
                  <div class="form-group">
                    <label for="signup-nickname">닉네임</label>
                    <input type="text" class="form-control" name="mNickname" id="signup-nickname"  placeholder="">
                  </div>
                </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <button id="signup-btn" type="button" class="btn btn-primary">회원가입</button>
                </div>
              </form>
            </div>
            

      </div>
    </section><!-- End Services Section -->
 
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>

  <script type="text/javascript">
	$('#signup-btn').click(function(){
		const signupForm = $('#signup-form').serialize();
		$.ajax({
			url : "ajaxSignup",
			method : 'POST',
			data: signupForm,
		}).done(function(data){
			if(data){
				console.log(data)
				if(data==1){
					Swal.fire({
				    	  icon: 'error',
				    	  text: '이미 id를 가진 사람이 있습니다'
				    	})
				}else{
					Swal.fire({
				    	  icon: 'error',
				    	  text: '이미 닉네임을 가진 사람이 있습니다'
				    	})
				}
			}else{
				location.href = "boardList"
			}
		}).fail(function(){
			console.log("실패")
		})
	})
  </script>


</body>

</html>