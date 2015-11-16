<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
			<td>id</td>
			<td>name</td>
			<td>description</td>
			<td>ECTS</td>
			<td>Semester</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="lesson" items="${lessons}">
			<tr>
				<td>${lesson.id}</td>
				<td>${lesson.name}</td>
				<td>${lesson.description}</td>
				<td>${lesson.ects}</td>
				<td>${lesson.semester}</td>
				<td><a class="btn btn-warning"
					href="${pageContext.request.contextPath}/secretary/lessons/edit/${lesson.id}">edit</a></td>
				<td><a class="btn btn-danger"
					href="${pageContext.request.contextPath}/secretary/lessons/delete/${lesson.id}"
					onclick="return confirm('Are you sure?')">delete</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/secretary/lessons/insert">create</a>
</div>
<%@ include file="../footer.jsp"%>