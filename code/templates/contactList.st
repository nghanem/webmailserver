<table>
<thead>
Type Description:  Black List: 1 			White List: 2			Normal List: 3
<tr>
	<th></th>
	<th>Name</th>
	<th>Email Address</th>
	<th>Type</th>
</tr>
</thead> 
<tbody> 

$contact:{
	<tr>
		<td><input type="checkbox" name="selContact$i$" value="$it.contactId$"></td>
		<td>$it.contactName$</td>
		<td>$it.contactEmail$</td>
		<td>$it.contactType$</td>
	</tr>
}$
</tbody>
</table>
