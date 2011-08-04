<html>
  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     <meta name="layout" content="main" />
     <title>Confirm</title>
  </head>
  <body>
    <div class="body">
      <div class="dialog">
        <table>
          <tr>
            <td>Lotname</td>
            <td><%=testsession.lot%></td>
          </tr>
          <tr>
            <td>Condition</td>
            <td><%=testsession.condition%></td>
          </tr>
          <tr>
            <td>Testcell</td>
            <td><%=testsession.testcell%></td>
          </tr>
        </table>
      </div>

      <div class="buttons">
        <g:link action="execute" id="confirm">Confirm</g:link>
        <g:link action="cancel" id="cancel" onclick="return confirm('Are you sure?');">Cancel</g:link>
      </div>
    </div>
  </body>
</html>
