<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>username</th>
				<th>name</th>
				<th>surname</th>
				<th>sex</th>
				<th>telephone</th>
				<th>address</th>
				<th>birthday</th>
				<th>enabled</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${usersDetails}">
				<tr>
					<td>${user.username}</td>
					<td>${user.name}</td>
					<td>${user.surname}</td>
					<td>${user.sex}</td>
					<td>${user.telephone}</td>
					<td>${user.address}</td>
					<td>${user.birthday}</td>
					<td>${user.user.enabled}</td>
					<td><a class="btn btn-warning"
						href="${pageContext.request.contextPath}/secretary/users/edit/${user.username}">edit</a></td>
					<td><a class="btn btn-danger"
						href="${pageContext.request.contextPath}/secretary/users/delete/${user.username}"
						onclick="return confirm('Are you sure?')">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a class="btn btn-primary"
		href="${pageContext.request.contextPath}/secretary/users/insert">create</a>
</div>
<%@ include file="../footer.jsp"%>