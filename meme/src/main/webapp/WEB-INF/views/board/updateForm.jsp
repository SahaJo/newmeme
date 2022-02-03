<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form>
		<input type="hidden" id="id" value="${board.id }"/>
		<div class="form-group">
			<!-- <label for="title">Title :</label>  -->
			<input value="${board.title }" type="title" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
		<!--   <label for="comment">Content:</label> -->
		  <textarea class="summernote form-control" rows="5" id="content">${board.content }</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">수정 완료</button>
	
</div>
 	
 	<script>
      $('.summernote').summernote({
      //  placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
      });
    </script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>








