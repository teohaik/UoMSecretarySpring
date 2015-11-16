<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post"
		action="${pageContext.request.contextPath}/student/enroll"
		modelAttribute="student">

		<form:checkboxes itemValue="id" itemLabel="name" delimiter="<br>"
			items="${lessons}" path="checkedLessons" />
		<br>
		<input value="Enroll" class="btn btn-success" type="submit">
	</form:form>
</div>
<%@ include file="../footer.jsp"%>