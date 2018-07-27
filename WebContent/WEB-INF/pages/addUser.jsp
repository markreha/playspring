	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<h2>User Information</h2>
	<form:form method="POST" modelAttribute="user" action="adduser">
	   <table>
		    <tr>
		        <td><form:label path="firstName">First Name:</form:label></td>
		        <td><form:input path="firstName" /><form:errors path="firstName"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="lastName">Last Name:</form:label></td>
		        <td><form:input path="lastName" /><form:errors path="lastName"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="gender">Gender:</form:label></td>
				<td><form:radiobutton path="gender" value="0" label="Female" /> <form:radiobutton path="gender" value="1" label="Male" /></td>
            </tr>
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="Submit"/>
		        </td>
		    </tr>
		</table>  
	    <br/>
	    <form:errors path="*"/>
	</form:form>
