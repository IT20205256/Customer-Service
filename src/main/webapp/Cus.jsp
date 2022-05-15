<%@page import="com.CustomerModel" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">

<h1>Customer Service Management</h1>
<form id="formCus" name="formCus" method="POST" action="Cus.jsp">

    Customer Name:
    <input id="Name" name="Name" type="text"
           class="form-control form-control-sm">
    <br>
    Customer Address:
    <input id="Address" name="Address" type="text"
           class="form-control form-control-sm">
           
    <br>
    Issue:
    <input id="Issue" name="Issue" type="text"
           class="form-control form-control-sm">
     
    <br>       
    Phone Number:
    <input id="TelNo" name="TelNo" type="text"
           class="form-control form-control-sm">
           
    <br>
    Status:
    <select id="Status" name="Status">
    <option value="--Not Selected--">--Not Selected--</option>
    <option value="Not repaired">Not repaired</option>
    <option value="Repaired">Repaired</option>
    </select>
    
    <br>
    <input id="btnSave" name="btnSave" type="button" value="Save"
           class="btn btn-primary">
    <input type="hidden" id="hidCusIDSave" name="hidCusIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divCusGrid">
     <%
        CustomerModel cusObj = new CustomerModel();
        out.print(cusObj.readCusService());
     %>
</div>

</div></div></div>
</body>
</html>