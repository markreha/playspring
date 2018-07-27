	<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
	<h2>User Information</h2>
	<table>
		<tr>
			<th><label>First Name</label></th>
			<th><label>Last Name</label></th>
			<th><label>Gender</label></th>
		</tr>
		<tr>
		    <td><label>${user.firstName}</label></td>
		    <td><label>${user.lastName}</label></td>
		    <td>
		    	<c:choose>
    				<c:when test="${user.gender == 0}">
		    			<label>Female</label>
    				</c:when>    
    				<c:when test="${user.gender == 1}">
		    			<label>Male</label>
    				</c:when>    
    				<c:otherwise>
		    			<label>Unknown</label>
    				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
		    <td colspan="2">
		    	<a href="add">Add a User</a>
		    </td>
		</tr>
	</table>  
