<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<td>Assign</td>
				<td>Revoke</td>
				<td>Professor's Lessons</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="professor" items="${professors}">
				<tr>
					<td><a class="btn btn-info"
						href="${pageContext.request.contextPath}/secretary/professors/assign/${professor.username}">Username: ${professor.username}</a></td>
					<td><a class="btn btn-danger"
						href="${pageContext.request.contextPath}/secretary/professors/revoke/${professor.username}">Username: ${professor.username}</a></td>
					<td><a class="btn btn-default"
						href="${pageContext.request.contextPath}/secretary/professors/professorsLessons/${professor.username}">${professor.username}'s
							Lessons</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="../footer.jsp"%>