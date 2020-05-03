<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <li><a href="writeBoard">글쓰기</a></li>
          </ol>
        </div>

      </div>
    </section><!-- End Our Portfolio Section -->

    <!-- ======= Portfolio Section ======= -->
    <section class="portfolio">

		<c:import url="boardListPage.jsp"></c:import>

    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  
  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
	
  <script type="text/javascript">
  
	  $(document).on('click', '.page-click',  function(){
		  $.ajax({
		  		  url: "boardList"
		  		, method: "POST"
		  		, data : {page : $(this).data('set')}
		  		, dataType : "html"
		  }).done(function(data){
		  		
		  		$('#board-container').remove();
		  		
				$('.portfolio').append(data);
				
				
		  }).fail(function(){
		  		console.log(2)
		  })
	   })
  </script>
</body>

</html>