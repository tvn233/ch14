<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Join our email list</title>

    <link rel="stylesheet"
          href="css/style.css">
</head>

<body>

<h1>Join our email list</h1>

<p>
    To join our email list, enter your name and email address below.
</p>

<%
    String message =
            (String) request.getAttribute("message");

    if (message != null) {
%>

<p class="error">
    <%= message %>
</p>

<%
    }
%>

<form action="emailList" method="post">

    <div class="form-row">
        <label for="email">Email:</label>
        <input type="email"
               id="email"
               name="email"
               required>
    </div>

    <div class="form-row">
        <label for="firstName">First Name:</label>
        <input type="text"
               id="firstName"
               name="firstName"
               required>
    </div>

    <div class="form-row">
        <label for="lastName">Last Name:</label>
        <input type="text"
               id="lastName"
               name="lastName"
               required>
    </div>

    <button type="submit">
        Join Now
    </button>

</form>

</body>
</html>