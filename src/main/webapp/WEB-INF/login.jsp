<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Login</jsp:attribute>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/style/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="main">

        <div class="row pt-4">
            <div class="login-form col-6">
                <form action="/examples/actions/confirmation.php" method="post">
                    <h2>Log in</h2>
                <p class="hint-text">Give us your DATA! (Username = Email)</p>
                 <div class="form-group">
                       <input type="text" class="form-control" placeholder="Username" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" required="required">
                    </div>
                 <div class="form-row">
                        <div class="form-group col-md-6">
                            <label class="checkbox-inline">
                                <input type="checkbox" required="required"> Remember me
                            </label>
                        </div>
                        <div class="form-group col-md-6 text-right">
                            <a href="#" class="pull-right">Forgot Password?</a>
                        </div>
                    </div> 
                <div class="form-group">
                        <button type="submit" class="btn btn-success btn-lg btn-block">Log In</button>
                    </div>
                 <div class="text-center">You don't have an account? <a href="LoginServlet?type=register">Register</a></div>
                 </form>
            </div>
        </div>
        
    </jsp:attribute>
</template:base>