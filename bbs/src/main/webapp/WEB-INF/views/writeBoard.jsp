<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

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
  
  <main id="main">

    <!-- ======= Our Portfolio Section ======= -->
    <section class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>게시판</h2>
          <ol>
            <li><a href="boardList.html">게시판 목록</a></li>
          </ol>
        </div>

      </div>
    </section><!-- End Our Portfolio Section -->

    <!-- ======= Portfolio Section ======= -->

	<section class="why-us section-bg" data-aos="fade-up" date-aos-delay="200">
      
      <form id="writeBoardForm" action="writeBoard" method="post">
	      <div class="container">
	        <div class="row" >
	          <div class="board boardTitle">
				<div class="input-group mb-3">
				  <input id="btitle" name="boardTitle" type="text" class="form-control" placeholder="제목을 입력해주세요" aria-label="Recipient's username" aria-describedby="basic-addon2">
				</div>
			  </div>
	        </div>
	      </div>
	      <div class="container" style="margin-bottom: 20px">
	        <div class="row" >
	          <div class="board">
				<div class="board-content">
					<textarea id="btext"class="summernote" name="boardContent"></textarea>
				</div>
	          </div>
	        </div>
	      </div>
	      <div class="container" style="margin-bottom: 20px">
	        <div class="row" >
				<button style="width:100%" class="submit-btn btn btn-success" type="button">등록</button>
	        </div>
	      </div>
	   </form>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  
  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
	<script>
     $('.summernote').summernote({
       placeholder: '내용을 입력해주세요',
       tabsize: 2,
       height: 500,
       lang: 'ko-KR'
     });
     
     $('.submit-btn').click(function(){
	 	if($('#btitle').val()==""){
	    	 Swal.fire({
		    	  icon: 'error',
		    	  text: '제목을 입력해주세요'
		    	})
	 	}else{
	 		$('#writeBoardForm').submit();
	 	}
     })
     
     </script>

</body>

</html>