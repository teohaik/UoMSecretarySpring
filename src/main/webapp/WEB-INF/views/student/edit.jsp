<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post" modelAttribute="userDetails"
		action="${pageContext.request.contextPath}/student/edit">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td>Username</td>
					<td><form:input path="username" readonly="true" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Surname</td>
					<td><form:input path="surname" /></td>
				</tr>
				<tr>
					<td>Sex</td>
					<td><form:select path="sex">
							<form:option value="male">Male</form:option>
							<form:option value="female">Female</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Telephone</td>
					<td><form:input path="telephone" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td>Birthday</td>
					<td><form:input path="birthday" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" class="btn btn-info" value="Edit" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
<%@ include file="../footer.jsp"%>