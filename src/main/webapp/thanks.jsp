<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thank You</title>

    <link rel="stylesheet"
          href="css/style.css">
</head>

<body>

<h1>Thanks for joining our email list!</h1>

<p>Your information has been successfully submitted.</p>

<p>
    <strong>First Name:</strong>
    <%= request.getAttribute("firstName") %>
</p>

<p>
    <strong>Last Name:</strong>
    <%= request.getAttribute("lastName") %>
</p>

<p>
    <strong>Email:</strong>
    <%= request.getAttribute("email") %>
</p>

<p>
    An email has been sent to your email address.
</p>

<form action="emailList" method="post">
    <button type="submit" name="action" value="return">
        Return
    </button>
</form>

</body>
</html>