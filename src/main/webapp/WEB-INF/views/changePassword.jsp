<%@ include file="header.jsp"%>
<%@ include file="container.jsp"%>
<div class="table-responsive">
	<form:form method="post" modelAttribute="user"
		action="${pageContext.request.contextPath}/myAccount/changePassword">
		<table class="table table-striped">
			<tbody>
				<tr>
					<td>Username</td>
					<td><form:input path="username" readonly="true" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<form:hidden path="role" value="${role}" />
					<form:hidden path="enabled" value="${enabled}" />
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<input type="submit" class="btn btn-info" value="Change my Password"></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
<%@ include file="footer.jsp"%>