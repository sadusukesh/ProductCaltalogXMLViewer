<%@ page import="java.util.List" %>
<%@ page import="com.example.Model.Product" %>
<%
List<Product> products =
        (List<Product>) request.getAttribute("products");
%>



<!DOCTYPE html>
<html>

<head>

<title>Product Catalog</title>

</head>

<body>

<h2>Product Catalog</h2>

<form action="ProductServlet" method="get">

<select name="category">

<option value="All">All</option>

<option value="Electronics">Electronics</option>

<option value="Furniture">Furniture</option>

<option value="Books">Books</option>

<option value="Sports">Sports</option>

</select>

<input type="submit" value="Filter">

</form>

<br>

<table border="1">

<tr>

<th>ID</th>

<th>Name</th>

<th>Category</th>

<th>Price</th>

<th>Stock</th>

</tr>

<%

if(products != null){

for(Product product : products){



%>

<tr>

<td><%= product.getId() %></td>

<td><%= product.getName() %></td>

<td><%= product.getCategory() %></td>

<td><%= product.getPrice() %></td>

<td><%= product.getStock() %></td>

</tr>

<%

}



}

%>

</table>

</body>

</html>