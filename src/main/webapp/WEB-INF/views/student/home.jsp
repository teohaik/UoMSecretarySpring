<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="get" modelAttribute="student">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td>username</td>
					<td>${student.username}</td>
				</tr>
				<tr>
					<td>name</td>
					<td>${student.name}</td>
				</tr>
				<tr>
					<td>surname</td>
					<td>${student.surname}</td>
				</tr>
				<tr>
					<td>sex</td>
					<td>${student.sex}</td>
				</tr>
				<tr>
					<td>telephone</td>
					<td>${student.telephone}</td>
				</tr>
				<tr>
					<td>address</td>
					<td>${student.address}</td>
				</tr>
				<tr>
					<td>birthday</td>
					<td>${student.birthday}</td>
				</tr>
			</tbody>
		</table>
		
		<a class="btn btn-default" href="${pageContext.request.contextPath}/student/edit">Edit my Information</a>
		<a class="btn btn-default" href="${pageContext.request.contextPath}/student/myLessons">my Lessons</a>
		<a class="btn btn-success" href="${pageContext.request.contextPath}/student/enroll">Enroll to new Lessons</a>
		<a class="btn btn-danger" href="${pageContext.request.contextPath}/student/dis-enroll">Dis-enroll to Lessons</a>
</form:form>
</div>
<%@ include file="../footer.jsp"%>