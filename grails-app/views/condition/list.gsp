
<%@ page import="com.melexis.barcode.Condition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'condition.label', default: 'Condition')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'condition.id.label', default: 'Id')}" />
                        
                            <th><g:message code="condition.lot.label" default="Lot" /></th>
                        
                            <g:sortableColumn property="name" title="${message(code: 'condition.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${conditionInstanceList}" status="i" var="conditionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${conditionInstance.id}">${fieldValue(bean: conditionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: conditionInstance, field: "lot")}</td>
                        
                            <td>${fieldValue(bean: conditionInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${conditionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
