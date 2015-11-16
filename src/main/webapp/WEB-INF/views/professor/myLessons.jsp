<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>description</th>
				<th>ECTS</th>
				<th>Semester</th>
				<th>SET GRADES</th>
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
				<td><a class="btn btn-default"
					href="${pageContext.request.contextPath}/professor/setGrades/${lesson.id}"
					role="button">Set grades &raquo;</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="../footer.jsp"%>