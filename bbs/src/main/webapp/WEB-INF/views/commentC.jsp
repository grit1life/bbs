<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">
<div id="comment-comment">
<c:if test="${board[0].commentNo!=null &&board[0].commentNo!=''}">
   <c:forEach var="i" begin="0" end="${fn:length(board)-1}">
      <div class="container">
        <div class="row">
          <!-- 댓글 들여쓰기 -->
          <c:forEach begin="1" end="${board[i].commentDepth-1}">
          	<div style="width:30px"></div>  
          </c:forEach>
	          <div class="board-comment-wrap">
		          <form class="writeCommentC" method="post">
						<div class="comment-writer">${board[i].CMNickname}</div>
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
</div>