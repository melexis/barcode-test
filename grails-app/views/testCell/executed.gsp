<html>
  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     <meta name="layout" content="main" />
     <title>Executed</title>
  </head>
  <body>
    <div class="body">
      <div class="dialog">
        Executed command on testers:

        <ul id="testers">
            <g:each in="${testers}" var="tester">
              <li>${tester}</li>
            </g:each>
        </ul>
      </div>
    </div>
  </body>
</html>
