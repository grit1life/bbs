<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">

      <div id="board-container" class="container">
		<table class="table table-hover">
		
			<c:forEach items="${list}" var="list">
				<tr>
					<td><a href="board?no=${list.boardNo}">${list.boardTitle}</a></td>
					<td><span style="font-size: 7px">조회</span> ${list.boardHits}</td>
					<td><span style="font-size: 7px">댓글</span> ${list.commentCount}</td>
					<td>${list.MNickname}</td>
				</tr>
				
			</c:forEach>
		</table>
        
        <nav aria-label="...">
		  <ul class="pagination">
		  	<c:if test="${currentPage==1}">
			    <li class="page-item disabled">
			      <span class="page-link">Previous</span>
			    </li>
		    </c:if>
		    <c:if test="${currentPage!=1}">
		    	<li class="page-item">
			      <a href="#">
			      	<span class="page-link page-click" data-set="${currentPage-1}">Previous</span>
			      </a>
			    </li>
		    </c:if>
		    <c:forEach var="i" begin="${startPage}" end="${endPage}">
		    	<c:if test="${i!=currentPage}">
		    		<li class="page-item"><a class="page-link page-click" href="#" data-set="${i}">${i}</a></li>
		    	</c:if>
		    	<c:if test="${i==currentPage}">
					<li class="page-item active">
				      <span class="page-link">
				        ${currentPage}
				        <span class="sr-only">(current)</span>
				      </span>
				    </li>
		    	</c:if>
		    </c:forEach>
		    <c:if test="${currentPage!=lastPage}">
			    <li class="page-item">
			      <a class="page-link page-click" href="#" data-set="${currentPage+1}">Next</a>
			    </li>
	        </c:if>
		    <c:if test="${currentPage==lastPage}">
			    <li class="page-item disabled">
			      <a class="page-link" href="#">Next</a>
			    </li>
	        </c:if>
		  </ul>
		</nav>
	
      </div>