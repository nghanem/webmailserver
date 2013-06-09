$emailAccount.keys:{k|
  <tr>
    <td>Email Address:</td>
    <td>$emailAccount.(k)$</td><input type="hidden" name="email$k$" value=$emailAccount.(k)$>
  </tr>
  <tr>
    <td>Password: </td>
    <td><input type="password" name="emailPass$k$"></td>
  </tr>
  
  <tr>
    <td></td>
    <td>
    		<input type="submit" name="setEmail$k$" value="confirm">
    		<input type="submit" name="deleteEmail$k$" value="delete">
    		
    </td>
  </tr>
}$
