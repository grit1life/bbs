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
          <div class="board col-lg-6 d-flex flex-column justify-content-center p-5e">
			<div class=" ">${board[0].boardWriter}</div>
			<div class="board-date">${board[0].boardDate}</div>
			<div class="board-date">${board[0].boardModiDate}</div>
		  </div>
        </div>
      </div>
      <div class="container">
        <div class="row" >
          <div class="board" style="width: 100%">
			<div class="board-title" style="display:inline-block;">${board[0].boardTitle}</div>
			<div class="bookmark" style="display:inline-block; float: right">
				<svg class="bi bi-bookmark" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
				  <path fill-rule="evenodd" d="M8 12l5 3V3a2 2 0 00-2-2H5a2 2 0 00-2 2v12l5-3zm-4 1.234l4-2.4 4 2.4V3a1 1 0 00-1-1H5a1 1 0 00-1 1v10.234z" clip-rule="evenodd"/>
				</svg>
			</div>
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
      <c:if test="${board[0].commentNo!=null &&board[0].commentNo!=''}">
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
					            <div class="comment-comment" style="width: 500px">
					            	<textarea class="summernote " name="commentContent"></textarea>
					           		<button type="button" class="regist-comment-btn btn btn-success">등록</button> 
					            </div>
				          </form>
			          </div>
		        </div>
		      </div>
	      </c:forEach>
      </c:if>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  
  <!-- ======= Footer ======= -->
  <c:import url="fragments/footer.jsp"></c:import>
  
  <!-- script Files -->
  <c:import url="fragments/script.jsp"></c:import>
   <script>
     commentClick();
     
     $('.bookmark').click(function(){
    	 $('.bi-bookmark').attr('fill', 'red');
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
	     }).done(function(data){
	    	 const toDate = new Date();
			 let toYear = toDate.getFullYear();
			 let toMonth = toDate.getMonth()+1;
			 let toDay = toDate.getDate();
			 let toHour = toDate.getHours();
			 let toMinutes = toDate.getMinutes();
			 let toSeconds = toDate.getSeconds()
			 toYear = toYear<10 ? '0'+toYear : toYear;
			 toMonth = toMonth<10 ? '0'+toMonth : toMonth;
			 toDay = toDay<10 ? '0'+toDay : toDay;
			 toHour = toHour<10 ? '0'+toHour : toHour;
			 toMinutes = toMinutes<10 ? '0'+toMinutes : toMinutes;
			 toSeconds = toSeconds<10 ? '0'+toSeconds : toSeconds;
	    	 const date = toYear+'-'+toMonth+'-'+toDay+' '+toHour+':'+toMinutes
			 
	    	 let html = '<div class="container">'
			 html += '<div class="row">'

			 const cDepth = commentForm.children('[name="commentDepth"]').val()
			 const cBoardNo = commentForm.children('[name="boardNo"]').val()
			 const cSort = commentForm.find('[name="commentSort"]').val()
			 const cContent = commentForm.find('.summernote').val()
			 for(let i=0; i<cDepth; i++){
				 html +='<div style="width:30px"></div>'
			 }
			 
			 html +=    '<div class="board">'
			 html += 	   '<form class="writeCommentC" method="post">'
			 html += 			'<div class="comment-writer">'+'홍길동'+'</div>'
			 html += 			'<div class="comment-date">'+date+'</div>'
			 html += 		    '<div class="comment-content">'+cContent+'</div>'
			 html += 		    '<button type="button" class="comment-btn btn btn-success">답글</button> '
			 html += 		    '<input type="hidden" name="boardNo" value="'+cBoardNo+'"/>'
			 html +=      		'<input type="hidden" name="commentSort" value="'+cSort+'"/>' 
			 html +=       		'<input type="hidden" name="commentDepth" value="'+(Number(cDepth)+1)+'"/>' 
			 html += 		    '<div class="comment-comment" style="width: 500px">'
			 html += 	        	'<textarea class="summernote " name="commentContent"></textarea>'
			 html += 	        	'<button type="button" class="regist-comment-btn btn btn-success">등록</button>' 
			 html += 		  	'</div>'
			 html += 	  '</form>'
			 html += 	'</div>'
			 html +=  '</div>'
			 html += '</div>'
	    		 
	    	 commentForm.parents('.container').after(html);
			 
			 const commentArea = commentForm.children('.comment-comment')
    		 commentArea.css('display', 'none');
			 
			 commentClick();
			 
    	 }).fail(function(){
    		 console.log("fail")
    	 })
     })
     
     function commentClick(){
    	 $('.summernote').summernote({
    	       placeholder: 'Hello Bootstrap 4',
    	       tabsize: 2,
    	       height: 200,
    	       lang: 'ko-KR'
    	     });
     }
     
   </script>
</body>

</html>