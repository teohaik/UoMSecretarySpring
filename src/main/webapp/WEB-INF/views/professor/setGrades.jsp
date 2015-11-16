<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post"
		modelAttribute="studentEnrolledToLessonWrapper"
		action="${pageContext.request.contextPath}/professor/setGrades">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Student's name</th>
					<th>Student's surname</th>
					<th>Grade</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach
					items="${studentEnrolledToLessonWrapper.studentsEnrolledToLessonList}"
					var="setl" varStatus="status">
					<tr>
						<td><input readonly="readonly"
							name="studentsEnrolledToLessonList[${status.index}].user.userDetails.name"
							value="${setl.user.userDetails.name}" /></td>
						<td><input readonly="readonly"
							name="studentsEnrolledToLessonList[${status.index}].user.userDetails.surname"
							value="${setl.user.userDetails.surname}" /></td>
						<td><input
							name="studentsEnrolledToLessonList[${status.index}].grade"
							value="${setl.grade}" /> <form:hidden
								path="studentsEnrolledToLessonList[${status.index}].user.username"
								value="${setl.user.username}" /> <form:hidden
								path="studentsEnrolledToLessonList[${status.index}].lesson.id"
								value="${setl.lesson.id}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
					<a class="btn btn-default"
			href="${pageContext.request.contextPath}/professor/myLessons"
			role="button">Back to my Lessons</a>
		<input type="submit" class="btn btn-info" value="Set grades">
	</form:form>
</div>
<%@ include file="../footer.jsp"%>