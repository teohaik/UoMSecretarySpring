<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post"
		action="${pageContext.request.contextPath}/secretary/professors/revoke/${professor.username}"
		modelAttribute="professor">

		<form:checkboxes itemValue="id" itemLabel="name" delimiter="<br>"
			items="${lessons}" path="checkedLessons" />
		<br>
		<input value="Revoke" type="submit" class="btn btn-warning"
			onclick="return confirm('Are you sure?')">
	</form:form>
</div>
<%@ include file="../footer.jsp"%>