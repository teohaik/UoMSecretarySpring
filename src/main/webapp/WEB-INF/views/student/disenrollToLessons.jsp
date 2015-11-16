<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post"
		action="${pageContext.request.contextPath}/student/dis-enroll"
		modelAttribute="student">

		<form:checkboxes itemValue="id" itemLabel="name" delimiter="<br>"
			items="${lessons}" path="checkedLessons" />
		<br>
		<input value="Disenroll" class="btn btn-danger" type="submit">
	</form:form>
</div>
<%@ include file="../footer.jsp"%>