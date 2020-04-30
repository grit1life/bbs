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
            <li><a href="writdBoard.html">글쓰기</a></li>
          </ol>
        </div>

      </div>
    </section><!-- End Our Portfolio Section -->

    <!-- ======= Portfolio Section ======= -->

	<section class="why-us section-bg" data-aos="fade-up" date-aos-delay="200">
      <div class="container">
        <div class="row" >
          <div class="board">
			<div style="display:inline-block">${board[0].MNickname}</div>
				<c:if test="${board[0].MId==sId}">
					<button type="button" class="delete-btn btn btn-danger" style="display:inline-block; float:right">글삭제</button>
					<button type="button" class="modi-btn btn btn-success" style="display:inline-block; float:right">글수정</button>
				</c:if>
			<div class="board-date">${board[0].boardDate}</div>
			<div class="board-date">${board[0].boardModiDate}</div>
		  </div>
        </div>
      </div>
      <div class="container">
        <div class="row" >
          <div class="board" >
			<div class="board-title" style="display:inline-block;">${board[0].boardTitle}</div>
			<div class="bookmark" style="display:inline-block; float: right">
				<c:if test="${bookmark == 1}">
					<svg class="bi bi-bookmark" width="2em" height="2em" viewBox="0 0 16 16" fill="red" xmlns="http://www.w3.org/2000/svg">
					  <path fill-rule="evenodd" d="M8 12l5 3V3a2 2 0 00-2-2H5a2 2 0 00-2 2v12l5-3zm-4 1.234l4-2.4 4 2.4V3a1 1 0 00-1-1H5a1 1 0 00-1 1v10.234z" clip-rule="evenodd"/>
					</svg>
				</c:if>
				<c:if test="${bookmark == 0}">
					<svg class="bi bi-bookmark" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  <path fill-rule="evenodd" d="M8 12l5 3V3a2 2 0 00-2-2H5a2 2 0 00-2 2v12l5-3zm-4 1.234l4-2.4 4 2.4V3a1 1 0 00-1-1H5a1 1 0 00-1 1v10.234z" clip-rule="evenodd"/>
					</svg>
				</c:if>
			</div>
		  </div>
        </div>
      </div>
      <div class="container" style="margin-bottom: 20px">
        <div class="row" >
          <div id="board" class="board">
			
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
	  
	<div id="comment-container">
	      <c:import url="commentC.jsp"></c:import>
	</div>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  
  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
   <script>
   summerNoteClick();
     
     $(document).on('click', '.modi-btn', function(){
    	 if($('#modi-content').length){
    		 const boardNo = ${board[0].boardNo}
    		 const modiContent = $('#modi-content').val()
    		 $.ajax({
    			   url : "modiBoard"
    			 , method : "POST"
    			 , data : {boardNo : boardNo, boardContent : modiContent}
    		 	 , dataType : "json"
    		 }).done(function(){
        		 $('.board-content').remove();
        		 $('#board').append('<div class="board-content">'+modiContent+'</div>');
    		 }).fail(function(){
    			 console.log("실패")
    		 })
    	 }else{
	    	 $('.board-content').remove();
	    	 $('#board').append('<div class="board-content"><textarea id="modi-content" class="summernote">${board[0].boardContent}</textarea></div>')
	    	 summerNoteClick();
    	 }
     })
     $(document).on('click', '.delete-btn', function(){
    	 const boardNo = ${board[0].boardNo};
    	 location.href="deleteBoard?boardNo="+boardNo;
     })
     
   	 let boolBookmark = ${bookmark}
     $(document).on('click', '.bookmark', function(){
    	 const boardNo = ${board[0].boardNo}
    	 $.ajax({
    		  url: "changeBookmark"
    		, method: "POST"
    		, data : {boardNo : boardNo, boolBookmark: boolBookmark}
    		, dataType : "json"
    	 }).done(function(result){
    		 boolBookmark = result
    		 if(boolBookmark){
    			 $('.bi-bookmark').attr('fill', 'red')
    		 }else{
    			 $('.bi-bookmark').attr('fill', 'currentColor')
    		 }
    	 }).fail(function(){
    		 console.log('error')
    	 })
     })

	 $(document).on('click', '.comment-btn', function(){
    	 const commentArea = $(this).parents('form').children('.comment-comment')
    	 if(commentArea.css('display') =='none'){
    	 	commentArea.css('display', 'block');
    	 }else{
   		 	commentArea.css('display', 'none');
    	 }
     })
     
     $(document).on('click', '.regist-comment-btn', function(){
	    	
    	 const commentForm = $(this).parents('.writeCommentC')
    	
    	 const formData = commentForm.serialize();
    	 $.ajax({
	    	 url: "writeCommentC"
	    	 ,method: "POST"
	    	 ,data: formData
	    	 ,dataType : "html"
	     }).done(function(data){
	    	 $('#comment-comment').remove();
	    	 $('#comment-container').append(data)

	    	 //const commentArea = commentForm.children('.comment-comment')
    		 //commentArea.css('display', 'none');
			 
			 summerNoteClick();
			 
    	 }).fail(function(){
    		 console.log("fail")
    	 })
     })
     
     function summerNoteClick(){
    	 $('.summernote').summernote({
    	       placeholder: '내용을 작성하세요',
    	       tabsize: 2,
    	       height: 200,
    	       lang: 'ko-KR'
    	     });
     }
     
   </script>
</body>

</html>