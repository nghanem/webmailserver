<div id="inboxMail">
  <table class="mailTable" id="mailTable" cellspacing="1" cellpadding="3" border="0" style="display:inline">
      
      <thead > 
      <tr>
          <th></th>
          <th><img src="../image/newmail.gif"></th>
          <th><img src="../image/Attachment.gif"></th>          
          
          <th><a href="./inbox?foldername=$currentfolder$&orderby=from">From </a></th>
          <th><a href="./inbox?foldername=$currentfolder$&orderby=subject">Subject </a></th>
          <th><a href="./inbox?foldername=$currentfolder$&orderby=date">Date </a></th>
          <th><a href="./inbox?foldername=$currentfolder$&orderby=size">Size </a></th>
         
      </tr>
      <tr><hr color="blue"></tr>
      </thead> 
      
      <tbody> 

      $mails:{
          <tr>
              <td><input type="checkbox" name="select$i$" value="$it.mailId$"></td>
              $if(it.hasUnread)$
                <td><img src="../image/newmail.gif"></td>
              $else$
                  <td></td>
              $endif$
              $if(it.hasAttachment)$
                  <td><img src="../image/Attachment.gif"></td>
              $else$
                  <td></td>
              $endif$
              <td><a href="./view?mid=$it.mailId$">$it.mailFrom$ $if(it.hasMoreThanOne)$($it.mailLinkLength$)$endif$</a></td>
              <td><a href="./view?mid=$it.mailId$">$it.mailSubject$</a></td>
              <td><a href="./view?mid=$it.mailId$">$it.mailDate$</a></td>
              <td><a href="./view?mid=$it.mailId$">$it.mailSize$</a></td>
          </tr>
      }$
      </tbody>
    </table>
</div>
