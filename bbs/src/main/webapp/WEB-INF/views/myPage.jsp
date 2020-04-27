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
    <section class="blog" data-aos="fade-up" data-aos-easing="ease-in-out" data-aos-duration="500">
      <div class="container">

        <div class="row">
        	<div class="col-lg-8 entries">
        		<article class="entry">
        			<h3 class="profile-username text-center">${nickname} Bookmark</h3>
        		</article>
        		<section class="portfolio">
     				<c:import url="boardListPage.jsp"></c:import>
        		</section>
        	</div>
        	
          
        </div>
        <!-- /.row -->
            

      </div>
    </section><!-- End Services Section -->
 
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
  <script type="text/javascript">
	  $(document).on('click', '.page-click',  function(){
		  $.ajax({
		  		  url: "myPage"
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