<%@ include file="../header.jsp"%>

	<div class="jumbotron">
	<div class="container">
		<h1>Hello, ${user.name}</h1>
		<P>This is the Admin panel for the secretary</P>
	</div>
</div>

	<a class="btn btn-default" href="${pageContext.request.contextPath}/secretary/lessons">List lessons</a>
	<a class="btn btn-default" href="${pageContext.request.contextPath}/secretary/lessons/insert">Create lesson</a>
	<a class="btn btn-default" href="${pageContext.request.contextPath}/secretary/users">List users</a>
	<a class="btn btn-default" href="${pageContext.request.contextPath}/secretary/users/insert">Create user</a>
	<a class="btn btn-default" href="${pageContext.request.contextPath}/secretary/professors">Assign lessons to proffesor</a>

<%@ include file="../footer.jsp"%>