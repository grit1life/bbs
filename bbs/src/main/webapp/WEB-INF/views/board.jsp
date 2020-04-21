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
          <h2>Our Portfolio</h2>
          <ol>
            <li><a href="wirteBoard">Home</a></li>
            <li>Our Portfolio</li>
          </ol>
        </div>

      </div>
    </section><!-- End Our Portfolio Section -->

    <!-- ======= Portfolio Section ======= -->

	<section class="why-us section-bg" data-aos="fade-up" date-aos-delay="200">
      <div class="container">
        <div class="row" >
          <div class="board col-lg-6 d-flex flex-column justify-content-center p-5">
			<div class=" ">${board[0].boardWriter}</div>
			<div class="board-date">${board[0].boardDate}</div>
			<div class="board-date">${board[0].boardModiDate}</div>
		  </div>
        </div>
      </div>
      <div class="container">
        <div class="row" >
          <div class="board col-lg-6 d-flex flex-column justify-content-center p-5">
			<div class="board-title">${board[0].boardTitle}</div>
		  </div>
        </div>
      </div>
      <div class="container" style="margin-bottom: 20px">
        <div class="row" >
          <div class="board">
			
			<div class="board-content">
				${board[0].boardContent}
			</div>
          </div>
        </div>
      </div>
      <form action="writeComment" method="post">
	      <div class="container">
	        <div class="row" >
				<div style="padding: 20px">
		      		<button type="submit" class="comment-btn btn btn-primary">댓글 작성</button>
		      	</div>
	        </div>
	      </div>
      
	      <div class="container" style="margin-bottom: 20px">
	        <div class="row" >
	      		<textarea class="summernote" name="commentContent"></textarea>
	      
	      		<input type="hidden" name="boardNo" value="${board[0].boardNo}"/>
	      		<input type="hidden" name="commentDepth" value="1"/>
	        </div>
	      </div>
	  </form>
      
      <c:forEach var="i" begin="0" end="${fn:length(board)-1}">
	      <div class="container">
	        <div class="row">
	          <!-- 댓글 들여쓰기 -->
	          <c:forEach begin="1" end="${board[i].commentDepth-1}">
	          	<div style="width:30px"></div>  
	          </c:forEach>
		          <div class="board">
			          <form class="writeCommentC" method="post">
							<div class="comment-writer">${board[i].commentWriter}</div>
							<div class="comment-date">${board[i].commentDate}</div>
							<div class="comment-modi-date">${board[i].commentModiDate}</div>
				            <div class="comment-content">${board[i].commentContent}</div>
				            
				            <button type="button" class="comment-btn btn btn-success">답글</button> 
				            <input type="hidden" name="boardNo" value="${board[i].boardNo}"/>
		      				<input type="hidden" name="commentSort" value="${board[i].commentSort}"/> 
		      				<input type="hidden" name="commentDepth" value="${board[i].commentDepth+1}"/> 
				            <div class="comment-comment">
				            	<textarea class="summernote " name="commentContent"></textarea>
				           		<button type="button" class="regist-comment-btn btn btn-success">등록</button> 
				            </div>
			          </form>
		          </div>
	        </div>
	      </div>
      </c:forEach>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  
  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
   <script>
     $('.summernote').summernote({
       placeholder: 'Hello Bootstrap 4',
       tabsize: 2,
       height: 200,
       lang: 'ko-KR'
     });
     $('.comment-btn').click(function(){
    	 const commentArea = $(this).parents('form').children('.comment-comment')
    	 if(commentArea.css('display')=='none'){
    	 	commentArea.css('display', 'block');
    	 }else{
   		 	commentArea.css('display', 'none');
    	 }
     })
     $('.regist-comment-btn').click(function(){
    	 const commentForm = $(this).parents('.writeCommentC')
    	 const formData = new FormData(commentForm[0]);
    	 $.ajax({
	    	 url: "writeCommentC"
	    	 ,method: "POST"
	    	 ,contentType : false
	    	 ,processData : false
	    	 ,data: formData
	    	 ,dataType : "json"
	     }).done(function(data){
    		 console.log("success");
    	 }).fail(function(){
    		 console.log("fail")
    	 })
    	 
     })
     
   </script>
</body>

</html>