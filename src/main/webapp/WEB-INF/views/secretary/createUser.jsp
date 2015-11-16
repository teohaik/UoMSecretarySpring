<%@ include file="../header.jsp"%>
<%@ include file="../container.jsp"%>
<div class="table-responsive">
	<form:form method="post" action="insert" modelAttribute="userDetails">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td>Username</td>
					<td><form:input path="user.username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="user.password" /></td>
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
					<td>Birthday (yyyy-MM-dd)</td>
					<td><form:input path="birthday" /></td>
				</tr>
				<tr>
					<td>Role</td>
					<td><form:select path="user.role">
							<form:option value="ROLE_SECRETARY">Secretary</form:option>
							<form:option value="ROLE_PROFESSOR">Professor</form:option>
							<form:option value="ROLE_STUDENT">Student</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Enabled</td>
					<td><form:select path="user.enabled">
							<form:option value="true">true</form:option>
							<form:option value="false">false</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input class="btn btn-primary" type="submit"
						value="Create User" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
<%@ include file="../footer.jsp"%>