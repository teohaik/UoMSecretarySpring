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
		<c:forEach var="secretary" items="${secretaries}">
			<tr>
				<td>${secretary.name}</td>
				<td>${secretary.surname}</td>
				<td>${secretary.telephone}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"%>