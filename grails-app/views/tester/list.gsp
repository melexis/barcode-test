
<%@ page import="com.melexis.barcode.Tester" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tester.label', default: 'Tester')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'tester.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="address" title="${message(code: 'tester.address.label', default: 'Address')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'tester.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="site" title="${message(code: 'tester.site.label', default: 'Site')}" />
                        
                            <th><g:message code="tester.testCell.label" default="Test Cell" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${testerInstanceList}" status="i" var="testerInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${testerInstance.id}">${fieldValue(bean: testerInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: testerInstance, field: "address")}</td>
                        
                            <td>${fieldValue(bean: testerInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: testerInstance, field: "site")}</td>
                        
                            <td>${fieldValue(bean: testerInstance, field: "testCell")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${testerInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
