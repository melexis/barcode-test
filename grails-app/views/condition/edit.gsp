

<%@ page import="com.melexis.barcode.Condition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'condition.label', default: 'Condition')}" />
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
            <g:hasErrors bean="${conditionInstance}">
            <div class="errors">
                <g:renderErrors bean="${conditionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${conditionInstance?.id}" />
                <g:hiddenField name="version" value="${conditionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="condProperties"><g:message code="condition.condProperties.label" default="Cond Properties" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: conditionInstance, field: 'condProperties', 'errors')}">
                                    <g:select name="condProperties" from="${com.melexis.barcode.CondProperty.list()}" multiple="yes" optionKey="id" size="5" value="${conditionInstance?.condProperties*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lot"><g:message code="condition.lot.label" default="Lot" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: conditionInstance, field: 'lot', 'errors')}">
                                    <g:select name="lot.id" from="${com.melexis.barcode.Lot.list()}" optionKey="id" value="${conditionInstance?.lot?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="condition.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: conditionInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${conditionInstance?.name}" />
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
