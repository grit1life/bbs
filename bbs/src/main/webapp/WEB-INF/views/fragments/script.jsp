<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

  <!-- Vendor JS Files -->
  <script src=<c:url value="/resources/assets/vendor/jquery/jquery.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/jquery.easing/jquery.easing.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/php-email-form/validate.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/venobox/venobox.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/waypoints/jquery.waypoints.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/counterup/counterup.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/owl.carousel/owl.carousel.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"/>></script>
  <script src=<c:url value="/resources/assets/vendor/aos/aos.js"/>></script>

  <!-- Template Main JS File -->
  <script src=<c:url value="/resources/assets/js/main.js"/>></script>
  
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote-bs4.min.js"></script>
  <script src=<c:url value="/resources/assets/vendor/summernote/summernote-ko-KR.min.js"/>></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> 
  
  <script type="text/javascript">
  	$('.logout-btn').click(function(){
  		location.href = "logout"
  	})
  </script>