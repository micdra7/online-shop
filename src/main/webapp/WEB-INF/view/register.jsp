<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl" class="has-navbar-fixed-top">
    <head>
        <meta charset="utf-8">
        <meta name="author" content="Michał Drabik">
        <meta name="description" content="Web app with an online shop functionality">
        <meta name="keywords" content="main page, online shop, web app, shop, 
                                       Michał Drabik, Michał, Drabik, Michal, Michal Drabik">
        <meta name='viewport'
            content='width=device-width, initial-scale=1.0, maximum-scale=1.0'>
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.2/css/bulma.min.css">
        <script defer src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/functions.js"></script>
    </head>
    <body>
        <nav class="navbar is-fixed-top is-info">
            <div class="navbar-brand">
                <a class="navbar-item" href="${pageContext.request.contextPath}/">
                    <img src="${pageContext.request.contextPath}/resources/images/logo/laptop.svg" alt="Logo" width="64" height="64">
                </a>
                <div class="navbar-item">
                    <a href="${pageContext.request.contextPath}/cart">
                        <span class="button is-info is-inverted">
                                <i class="fas fa-shopping-cart"></i>
                            ${sessionScope.cart.totalPrice}&euro;
                        </span>
                    </a>
                </div>
                <span class="navbar-burger burger" id="burger" onClick="setMenuVisibility();">
                    <span></span>
                    <span></span>
                    <span></span>
                </span>
            </div>
            <div class="navbar-menu" id="menu">
                <div class="navbar-start">
                    <a class="navbar-item" href="${pageContext.request.contextPath}/">
                        Home
                    </a>
                    <a class="navbar-item" href="${pageContext.request.contextPath}/categories">
                        Categories
                    </a>
                </div>
                <div class="navbar-end">
                    <security:authorize access="isAuthenticated()">
                        <c:set var="user">
                            <security:authentication property="principal.username"/>
                        </c:set>
                        <a class="navbar-item" href="${pageContext.request.contextPath}/user?username=${user}&page=1">
                            Welcome, ${user}
                        </a>
                        <div class="navbar-item">
                            <form:form action="${pageContext.request.contextPath}/logout" method="post">
                                <input type="submit" value="Log out" class="button is-info is-inverted">
                            </form:form>
                        </div>
                    </security:authorize>
                    <security:authorize access="isAnonymous()">
                        <a class="navbar-item" href="${pageContext.request.contextPath}/register-form">
                            Register
                        </a>
                        <div class="navbar-item">
                            <a class="button is-info is-inverted" href="${pageContext.request.contextPath}/log-in">
                                Log in
                            </a>
                        </div>
                    </security:authorize>
                </div>
            </div>
        </nav>
        <section class="section">
            <div class="box container">
                <form:form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="user" id="registrationForm">
                    <div class="columns">
                        <div class="column">
                            <c:choose>
                                <c:when test="${error.username != null}">
                                    <div class="notification is-danger has-text-centered">
                                        <p class="subtitle">${error.username}</p>
                                    </div>
                                </c:when>
                                <c:when test="${error.password != null}">
                                    <div class="notification is-danger has-text-centered">
                                        <p class="subtitle">${error.password}</p>
                                    </div>
                                </c:when>
                            </c:choose>
                            <div class="level">
                                <div class="level-item">
                                    <div class="columns">
                                        <div class="column">
                                            <h1 class="title is-spaced">Registration form</h1>
                                            <h4 class="subtitle">Fields marked with <sup>*</sup> are required</h4>
                                            <div class="box">
                                                <div class="field">
                                                    <label class="label">Username<sup>*</sup></label>
                                                    <p class="control has-icons-left">
                                                        <form:input path="username" cssClass="input" placeholder="Username"/>
                                                        <span class="icon is-small is-left">
                                                            <i class="fas fa-user"></i>
                                                        </span>
                                                        <form:errors path="username" cssClass="has-text-danger"/>
                                                    </p>
                                                </div>
                                                <div class="field">
                                                    <label class="label">Password<sup>*</sup></label>
                                                    <p class="control has-icons-left">
                                                        <form:password path="password" cssClass="input" placeholder="Password"/>
                                                        <span class="icon is-small is-left">
                                                            <i class="fas fa-lock"></i>
                                                        </span>
                                                        <form:errors path="password" cssClass="has-text-danger"/>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="box">
                                                <form:form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="userDetails">
                                                    <div class="field">
                                                        <label class="label">Name<sup>*</sup></label>
                                                        <p class="control">
                                                            <form:input path="name" cssClass="input" placeholder="John"/>
                                                            <form:errors path="name" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">Surname<sup>*</sup></label>
                                                        <p class="control">
                                                            <form:input path="surname" cssClass="input" placeholder="Doe"/>
                                                            <form:errors path="surname" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">Email<sup>*</sup></label>
                                                        <p class="control has-icons-left">
                                                            <form:input path="email" cssClass="input" placeholder="john.doe@domain.com"/>
                                                            <span class="icon is-small is-left">
                                                                <i class="fas fa-envelope"></i>
                                                            </span>
                                                            <form:errors path="email" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">Phone</label>
                                                        <p class="control has-icons-left">
                                                            <form:input path="phone" cssClass="input" placeholder="123456789"/>
                                                            <span class="icon is-small is-left">
                                                                <i class="fas fa-phone"></i>
                                                            </span>
                                                            <form:errors path="phone" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">Country<sup>*</sup></label>
                                                        <p class="control has-icons-left">
                                                            <form:input path="country" cssClass="input" placeholder="United Kingdom"/>
                                                            <span class="icon is-small is-left">
                                                                <i class="fas fa-globe"></i>
                                                            </span>
                                                            <form:errors path="country" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">City<sup>*</sup></label>
                                                        <p class="control">
                                                            <form:input path="city" cssClass="input" placeholder="London"/>
                                                            <form:errors path="city" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">Street<sup>*</sup></label>
                                                        <p class="control has-icons-left">
                                                            <form:input path="street" cssClass="input" placeholder="Bakers"/>
                                                            <span class="icon is-small is-left">
                                                                <i class="fas fa-road"></i>
                                                            </span>
                                                            <form:errors path="street" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">House number<sup>*</sup></label>
                                                        <p class="control">
                                                            <form:input path="houseNumber" cssClass="input" placeholder="10"/>
                                                            <form:errors path="houseNumber" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">Flat number</label>
                                                        <p class="control">
                                                            <form:input path="flatNumber" cssClass="input" placeholder="24"/>
                                                            <form:errors path="flatNumber" cssClass="has-text-danger"/>
                                                        </p>
                                                    </div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <div class="level">
                            <div class="level-item">
                                <p class="control">
                                    <input type="submit" class="button is-info" value="Register" form="registrationForm">
                                </p>
                            </div>
                        </div>
                    </div>
                </form:form>    
            </div>
        </section>
        <footer class="footer">
            <div class="container">
                <div class="content has-text-centered">
                    <p>
                        <strong>Online PC shop</strong> by <a class="link" href="https://github.com/micdra7" target="_blank">Michal Drabik</a>.
                    </p>
                    <p>
                        <a href="https://bulma.io">
                            <img src="https://bulma.io/images/made-with-bulma.png" alt="Made with Bulma" width="128" height="24">
                        </a>
                    </p>
                </div>
            </div>
        </footer>
    </body>
</html>