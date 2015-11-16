<%@ include file="header.jsp"%>
<%@ include file="container.jsp"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Telephone</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="professor" items="${professors}">
				<tr>
					<td>${professor.name}</td>
					<td>${professor.surname}</td>
					<td>${professor.telephone}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"%>