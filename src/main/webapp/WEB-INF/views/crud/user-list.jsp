<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
</head>

</head>
<body>
 <header>
  <nav class="navbar navbar-expand-md navbar-dark"
   style="background-color: SlateBlue">
   <div>
    <a href="https://www.javaguides.net" class="navbar-brand"> Project
     Dashboard</a>
   </div>

   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/list"
     class="nav-link">Users</a></li>
   </ul>

   <ul class="navbar-nav navbar-collapse justify-content-end">
    <li><a href="<%=request.getContextPath()%>/logout"
     class="nav-link">Logout</a></li>
   </ul>
  </nav>
 </header>

 <div class="row">
  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

  <div class="container">
   <h3 class="text-center">List of Users</h3>
   <hr>
   <div class="container text-left">

    <a href="<%=request.getContextPath()%>/new"
     class="btn btn-success">Add User</a>
   </div>
   <br>
   <table class="table table-bordered">
    <thead>
     <tr>
      <th>Username</th>
      <th>Project ID</th>
      <th>Status</th>
     </tr>
    </thead>
    <tbody>
     <!--   for (User user: users) {  -->
     <c:forEach var="user" items="${listUsers}">

      <tr>
       <td><c:out value="${user.username}" /></td>
       <td><c:out value="${user.projectId}" /></td>
       <td><c:out value="${user.status}" /></td>

       <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
        &nbsp;&nbsp;&nbsp;&nbsp; <a
        href="delete?id=<c:out value='${user.id}' />">Delete</a></td>

       <!--  <td><button (click)="updateUser(user.id)" class="btn btn-success">Update</button>
                 <button (click)="deleteUser(user.id)" class="btn btn-warning">Delete</button></td> -->
      </tr>
     </c:forEach>
     <!-- } -->
    </tbody>

   </table>
  </div>
 </div>
</body>
</html>