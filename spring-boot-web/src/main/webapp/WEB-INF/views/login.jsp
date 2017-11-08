<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
        <script type="text/javascript">
            var $message;
            $(function () {
                $message = $('.message');
            });
            $(document).on('click', 'button[name="btn"]', function () {
                if ($('#email').val() === '') {
                    $message.html('email cannot be empty');
                } else if ($('#password').val() === '') {
                    $message.html('password cannot be empty');
                } else {
                    $message.empty();
                    $('form').submit();
                }
            });
        </script>
    </head>
    <body>
        <center>
            <h2>Login | Spring Boot Sample Web App</h2>
            <a href="/">Home</a>
            <a href="register">Register</a>

            <br>
            <hr>
            <br>

            <form:form action="login" method="post" commandName="loginForm">
                <table>
                    <tr>
                        <td>Email</td>
                        <td><form:input id="email" path="email" type="email" placeholder="enter your email" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><form:input id="password" path="password" type="password" placeholder="enter your password" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><form:button name="btn" type="button">Login</form:button></td>
                        </tr>
                    </table>
            </form:form>

            <div class="message">${message}</div>
        </center>
    </body>
</html>
