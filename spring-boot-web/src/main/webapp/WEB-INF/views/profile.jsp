<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    </head>
    <body>
        <center>
            <h1>Profile | Spring Boot Sample Web App</h1>

            <div class="links">
                <a href="/">Index</a>
                <a href="logout">Logout</a>
            </div>

            <br>
            <hr>
            <br>

            <div><h2>Welcome ${account.name}</h2></div>
            <div><h2>Email : ${account.email}</h2></div>
            <div><a href="/delete/${account.id}">Delete Account</a></div>
        </center>
    </body>
</html>
