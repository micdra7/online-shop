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
                <section class="hero is-medium">
                    <div class="hero-head">
                        <header class="navbar">
                            <div class="navbar-brand">
                                <a class="navbar-item" href="${pageContext.request.contextPath}/subcategories?id=${subcategory.id}&page=1">
                                    ${subcategory.name}
                                </a>
                            </div>
                        </header>
                    </div>
                    <div class="hero-body">
                        <div class="columns is-mobile">
                            <div class="column is-two-thirds has-text-centered">
                                <h4 class="subtitle">
                                    <a class="link" href="${pageContext.request.contextPath}/product?id=${product.id}">
                                        ${product.producer}&nbsp;${product.name}
                                    </a>
                                </h4>
                                <p>${product.description}</p>
                            </div>
                            <div class="column has-text-centered">
                                <p>Price: ${product.price}&euro;</p>
                                <p>Quantity: ${product.quantity}</p>
                                <c:choose>
                                    <c:when test="${product.quantity > 0}">
                                        <a class="button is-info" href="${pageContext.request.contextPath}/add-to-cart?id=${item.id}">
                                            Add to cart
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="button is-info" disabled>
                                            Out of stock
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="hero-footer">
                        <p class="subtitle">Recent reviews for this product</p>
                        <c:forEach var="review" items="${reviews}">
                            <div class="columns">
                                <div class="column has-text-left-desktop has-text-center-touch">
                                    <p><strong>Rating: ${review.rating}/6</strong></p>
                                </div>
                                <div class="column has-text-left">
                                    <p>${review.content}</p>
                                </div>
                                <div class="column has-text-right-desktop has-text-center-touch">
                                    <p>${review.date}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
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