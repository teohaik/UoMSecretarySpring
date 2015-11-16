<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="get" modelAttribute="professor">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td>username</td>
					<td>${professor.username}</td>
				</tr>
				<tr>
					<td>name</td>
					<td>${professor.name}</td>
				</tr>
				<tr>
					<td>surname</td>
					<td>${professor.surname}</td>
				</tr>
				<tr>
					<td>sex</td>
					<td>${professor.sex}</td>
				</tr>
				<tr>
					<td>telephone</td>
					<td>${professor.telephone}</td>
				</tr>
				<tr>
					<td>address</td>
					<td>${professor.address}</td>
				</tr>
				<tr>
					<td>birthday</td>
					<td>${professor.birthday}</td>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/professor/edit">Edit my Information</a>
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/professor/myLessons" role="button">my Lessons</a>
	</form:form>
</div>
<%@ include file="../footer.jsp"%>