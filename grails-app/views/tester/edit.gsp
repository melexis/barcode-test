

<%@ page import="com.melexis.barcode.Tester" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tester.label', default: 'Tester')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${testerInstance}">
            <div class="errors">
                <g:renderErrors bean="${testerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${testerInstance?.id}" />
                <g:hiddenField name="version" value="${testerInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="address"><g:message code="tester.address.label" default="Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testerInstance, field: 'address', 'errors')}">
                                    <g:textField name="address" value="${testerInstance?.address}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="tester.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testerInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${testerInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="site"><g:message code="tester.site.label" default="Site" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testerInstance, field: 'site', 'errors')}">
                                    <g:textField name="site" value="${testerInstance?.site}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="testCell"><g:message code="tester.testCell.label" default="Test Cell" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testerInstance, field: 'testCell', 'errors')}">
                                    <g:select name="testCell.id" from="${com.melexis.barcode.TestCell.list()}" optionKey="id" value="${testerInstance?.testCell?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="testerProperties"><g:message code="tester.testerProperties.label" default="Tester Properties" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testerInstance, field: 'testerProperties', 'errors')}">
                                    <g:select name="testerProperties" from="${com.melexis.barcode.TesterProperty.list()}" multiple="yes" optionKey="id" size="5" value="${testerInstance?.testerProperties*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
