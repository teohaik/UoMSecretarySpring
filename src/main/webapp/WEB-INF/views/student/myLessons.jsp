<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Semester</th>
				<th>ECTS</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lesson" items="${lessons}">
				<tr>
					<td>${lesson.lesson.name}</td>
					<td>${lesson.lesson.semester}</td>
					<td>${lesson.lesson.ects}</td>
					<td>${lesson.grade}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="../footer.jsp"%>