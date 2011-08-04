

<%@ page import="com.melexis.barcode.Lot" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'lot.label', default: 'Lot')}" />
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
            <g:hasErrors bean="${lotInstance}">
            <div class="errors">
                <g:renderErrors bean="${lotInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${lotInstance?.id}" />
                <g:hiddenField name="version" value="${lotInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="conditions"><g:message code="lot.conditions.label" default="Conditions" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: lotInstance, field: 'conditions', 'errors')}">
                                    
<ul>
<g:each in="${lotInstance?.conditions?}" var="c">
    <li><g:link controller="condition" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="condition" action="create" params="['lot.id': lotInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'condition.label', default: 'Condition')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="device"><g:message code="lot.device.label" default="Device" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: lotInstance, field: 'device', 'errors')}">
                                    <g:textField name="device" value="${lotInstance?.device}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="lot.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: lotInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${lotInstance?.name}" />
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
