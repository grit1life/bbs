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

  
	<jsp:include page="fragments/head.jsp"></jsp:include>

  <!-- =======================================================
  * Template Name: Moderna - v2.0.1
  * Template URL: https://bootstrapmade.com/free-bootstrap-template-corporate-moderna/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
	<jsp:include page="fragments/header.jsp"></jsp:include>
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
  					<jsp:include page="boardListPage.jsp"></jsp:include>
        		</section>
        	</div>
        	
          
        </div>
        <!-- /.row -->
            

      </div>
    </section><!-- End Services Section -->
 
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <jsp:include page="fragments/footer.jsp"></jsp:include>
  <!-- script Files -->
  <jsp:include page="fragments/script.jsp"></jsp:include>
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