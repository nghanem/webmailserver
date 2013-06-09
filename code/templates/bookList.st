<table>
<thead>
<tr>
	<th>To</th>
	<th>Cc</th>
	<th>Bcc</th>
	<th>Name</th>
	<th>Email</th>
</tr>
</thead> 
<tbody> 

$contact:{
	<tr>
		<td><input type="checkbox" name="to$i$" value="$it.contactEmail$"></td>
		<td><input type="checkbox" name="cc$i$" value="$it.contactEmail$"></td>
		<td><input type="checkbox" name="bcc$i$" value="$it.contactEmail$"></td>
		<td>$it.contactName$</td>
		<td>$it.contactEmail$</td>
	</tr>
}$
</tbody>
</table>
