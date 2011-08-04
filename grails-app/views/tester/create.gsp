

<%@ page import="com.melexis.barcode.Tester" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tester.label', default: 'Tester')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${testerInstance}">
            <div class="errors">
                <g:renderErrors bean="${testerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
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
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
