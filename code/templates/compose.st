<div id="body"> 
    <div id="topBar">
        $topBar()$
    </div>
    <div id="mainFrame">
        <div id="leftBar" style="float:left">
            <div id="leftBar">
                $leftBar()$
            </div>
        </div>

        <div id="compose">
            <form action="/mail/proc_compose" method="POST" style="float:left" enctype="multipart/form-data" onsubmit="javascript:KindSubmit();">
                <table>
                    <tbody>
                    <tr>
                        <td>To:</td>
                        <td><input type="text" name="recipient" size="60" value="$to$"></td>
                    </tr>
                    <tr>
                        <td>Cc:</td>
                        <td><input type="text" name="cc" size="60" value="$cc$"></td>
                    </tr>
                    <tr>
                        <td>Bcc:</td>
                        <td><input type="text" name="bcc" size="60" value="$bcc$"></td>
                    </tr>
                    <tr>
                        <td>Subject:</td>
                        <td><input type="text" size="60" name="subject" value="$subject$"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="addressBook" value="address book"><input type="submit" name="saveDraft" value="save as draft"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <!--<td><textarea name="content" rows="20" cols="76"></textarea></td>-->
                        
                        <td>
    
                        <textarea name="content" id="content" cols="80" rows="20" >$content$</textarea>
                        <script type="text/javascript">
                          var editor = new KindEditor("editor");
                          editor.hiddenName = "content";
                          editor.editorWidth = "700px"; 
                          editor.editorHeight = "400px"; 
                          editor.editorType = "simple";
                          editor.show();
                          
                          function KindSubmit() {
                              editor.data();
                          }
                        </script>                     
                    </td>
                    </tr>
                    <tr>
                        $uploadFile: {
                            <tr>
                            <td></td>
                            <td><input type="checkbox" name="selUploadFile$i$" value="$it.name$|$it.id$">$it.name$</td>
                            </tr>
                        }$
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="button" name="addOneAttachment" id="addOneAttachment" value="Add" onclick="AddOne(this.form, this.parentNode)">
                            <input type="button" name="removeOneAttachment" id="removeOneAttachment" value="Remove" onclick="removeOne(this.form)">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="file" name="attachment" id="attachment1"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="check" value="check">    <input type="submit" name="send" value="send"></td>
                    </tr>
                    </tbody>
                </table>    
            </form>
            </div>
    </div>
</div>
