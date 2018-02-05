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
                <security:authorize access="hasAnyRole('CUSTOMER','ADMIN')">
                    <div class="navbar-item">
                        <span class="button is-info is-inverted">
                            <a href="${pageContext.request.contextPath}/cart">
                                <i class="fas fa-shopping-cart"></i>
                            </a>
                            0.00&euro;
                        </span>
                    </div>
                </security:authorize>
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
                    <a class="navbar-item" href="${pageContext.request.contextPath}/register">
                        Register
                    </a>
                    <div class="navbar-item">
                        <a class="button is-info is-inverted" href="${pageContext.request.contextPath}/log-in">
                            Log in
                        </a>
                    </div>
                </div>
            </div>
        </nav>
        <section class="section">
            <div class="box container">
                <h1 class="title">Recently purchased</h1>
                <c:forEach var="item" items="${products}">
                    <div class="notification is-info">
                        <div class="media">
                            <div class="media-content">
                                <h4 class="subtitle">
                                    <a class="link" href="${pageContext.request.contextPath}/product?id=${item.id}">
                                        ${item.producer}&nbsp;${item.name}
                                    </a>
                                </h4>
                                <p>${item.description}</p>
                            </div>
                            <div class="media-right">
                                <p>${item.price}&euro;</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <footer class="footer">
            <div class="container">
                <div class="content has-text-centered">
                    <p>
                        <strong>Online PC shop</strong> by Michal Drabik.
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