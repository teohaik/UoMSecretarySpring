<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post"
		action="${pageContext.request.contextPath}/secretary/professors/assign/${professor.username}"
		modelAttribute="professor">

		<form:checkboxes itemValue="id" itemLabel="name" delimiter="<br>"
			items="${lessons}" path="checkedLessons" />
		<br>
		<input class="btn btn-primary" value="Assign" type="submit">
	</form:form>
</div>
<%@ include file="../footer.jsp"%>