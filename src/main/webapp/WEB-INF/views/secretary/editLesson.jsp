<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post" modelAttribute="lesson"
		action="${pageContext.request.contextPath}/secretary/lessons/edit">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td>ID</td>
					<td><form:input path="id" readonly="true" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><form:textarea path="description" cols="20" rows="5" /></td>
				</tr>
				<tr>
					<td>ECTS</td>
					<td><form:input path="ects" /></td>
				</tr>
				<tr>
					<td>Semester</td>
					<td><form:input path="semester" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input class="btn btn-warning" type="submit" value="Edit Lesson" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
<%@ include file="../footer.jsp"%>