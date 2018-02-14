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
        
        <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/resources/images/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/resources/images/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon/favicon-16x16.png">
        <link rel="manifest" href="${pageContext.request.contextPath}/resources/images/favicon/site.webmanifest">
        <link rel="mask-icon" href="${pageContext.request.contextPath}/resources/images/favicon/safari-pinned-tab.svg" color="#5bbad5">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon/favicon.ico">
        <meta name="msapplication-TileColor" content="#2d89ef">
        <meta name="msapplication-config" content="${pageContext.request.contextPath}/resources/images/favicon/browserconfig.xml">
        <meta name="theme-color" content="#ffffff">
        
        <title>User page | Online PC shop</title>
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
                <div class="columns">
                    <div class="column">
                        <div class="panel">
                            <a class="panel-block" href="${pageContext.request.contextPath}/user?username=${user}&page=1">
                                User data
                            </a>
                            <a class="panel-block" href="${pageContext.request.contextPath}/user?username=${user}&page=2">
                                Orders
                            </a>
                        </div>
                    </div>
                    <div class="column is-four-fifths">
                        <div class="box">
                            <c:if test="${param.page == 1}">
                                <form:form action="${pageContext.request.contextPath}/user-update" method="post" modelAttribute="userDetails">
                                    <div class="field">
                                        <h1 class="title">Review &amp; edit your data</h1>
                                    </div>
                                    <form:hidden path="id"/>
                                    <form:hidden path="user.id"/>
                                    <form:hidden path="user.username"/>
                                    <form:hidden path="user.password"/>
                                    <form:hidden path="user.enabled"/>
                                    <div class="field">
                                        <label class="label">Name</label>
                                        <p class="control">
                                            <form:input path="name" cssClass="input"/>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Surname</label>
                                        <p class="control">
                                            <form:input path="surname" cssClass="input"/>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Email</label>
                                        <p class="control has-icons-left">
                                            <form:input path="email" cssClass="input"/>
                                            <span class="icon is-small is-left">
                                                <i class="fas fa-envelope"></i>
                                            </span>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Phone</label>
                                        <p class="control has-icons-left">
                                            <form:input path="phone" cssClass="input"/>
                                            <span class="icon is-small is-left">
                                                <i class="fas fa-phone"></i>
                                            </span>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Country</label>
                                        <p class="control has-icons-left">
                                            <form:input path="country" cssClass="input"/>
                                            <span class="icon is-small is-left">
                                                <i class="fas fa-globe"></i>
                                            </span>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">City</label>
                                        <p class="control">
                                            <form:input path="city" cssClass="input"/>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Street</label>
                                        <p class="control has-icons-left">
                                            <form:input path="street" cssClass="input"/>
                                            <span class="icon is-small is-left">
                                                <i class="fas fa-road"></i>
                                            </span>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">House number</label>
                                        <p class="control">
                                            <form:input path="houseNumber" cssClass="input"/>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Flat number</label>
                                        <p class="control">
                                            <form:input path="flatNumber" cssClass="input"/>
                                        </p>
                                    </div>
                                    <div class="field">
                                        <div class="level">
                                            <div class="level-item">
                                                <p class="control">
                                                    <input type="submit" class="button is-info" value="Update">
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </c:if>
                            <c:if test="${param.page == 2}">
                                <c:forEach  var="item" items="${orders}">
                                    <div class="level">
                                        <div class="level-left">
                                            <div class="level-item">
                                                <p>Order id: ${item.key.id}</p>
                                            </div>
                                        </div>
                                        <div class="level-item">
                                            <p>Order value: ${item.value}&euro;</p>
                                        </div>
                                        <div class="level-right">
                                            <div class="level-item">
                                                ${item.key.date}
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
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