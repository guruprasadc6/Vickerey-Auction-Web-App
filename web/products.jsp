<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page language="java" import="java.util.*" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<style>
    table, th, td {
        border: 1px solid black;
    }

    th, td {
        padding: 15px;
    }
    th, td {
        text-align: center;
    }
</style>


<table class="table table-striped" style="width:100%">
    <tr>
        <td><b>Product Id</td>
        <td><b>Product name</td>
        <td><b>Product Description</td>
        <td><b>Product Category</td>
        <td><b>Product Baseprice</td>
        <td><b>Product Duration</td>
    </tr>
    <%Iterator itr,itr2;%>
    <% List data=(List)request.getAttribute("data");
        List data2=(List)request.getAttribute("data2");
        itr2=data2.iterator();
        for (itr=data.iterator();itr.hasNext(); )
        {
    %>
    <tr>
        <td><%=itr.next()%></td>
        <td><%=itr.next()%></td>
        <td><%=itr.next()%></td>
        <td><%=itr.next()%></td>
        <td><%=itr2.next()%></td>
        <td><%=itr2.next()%></td>
    </tr>
    <%}%>
</table>

</body>
</html>
