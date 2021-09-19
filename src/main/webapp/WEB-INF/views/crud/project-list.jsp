<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Projects Dashboard</title>

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
    <a href="https://www.javaguides.net" class="navbar-brand"> project
     dashboard</a>
   </div>

   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/list"
     class="nav-link">Projects</a></li>
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
   <h3 class="text-center">List of Projects</h3>
   <hr>
   <div class="container text-left">

    <a href="<%=request.getContextPath()%>/new"
     class="btn btn-success">Add Project</a>
   </div>
   <br>
   <table class="table table-bordered">
    <thead>
     <tr>
      <th>Name</th>
      <th>Department</th>
     </tr>
    </thead>
    <tbody>
     <!--   for (Project project: projects) {  -->
     <c:forEach var="project" items="${listProjects}">

      <tr>
       <td><c:out value="${project.name}" /></td>
       <td><c:out value="${project.department}" /></td>

       <td><a href="edit?id=<c:out value='${project.id}' />">Edit</a>
        &nbsp;&nbsp;&nbsp;&nbsp; <a
        href="delete?id=<c:out value='${project.id}' />">Delete</a>
         &nbsp;&nbsp;&nbsp;&nbsp; <a
                href="edit?id=<c:out value='${project.id}' />">Students</a></td>

       <!--  <td><button (click)="update(project.id)" class="btn btn-success">Update</button>
                 <button (click)="projectUsers(project.id)" class="btn btn-success">Students</button>
                 <button (click)="delete(project.id)" class="btn btn-warning">Delete</button></td> -->
      </tr>
     </c:forEach>
     <!-- } -->
    </tbody>

   </table>
  </div>
 </div>
</body>
</html>