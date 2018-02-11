function setMenuVisibility() {
    var burger = document.getElementById("burger");
    var menu = document.getElementById("menu");
    
    if (burger.classList.contains("is-active")) {
        burger.classList.remove("is-active");
        menu.classList.remove("is-active");
    } else {
        burger.classList.add("is-active");
        menu.classList.add("is-active");
    }
}

function updateQuantity(pageContext, index) {
    var id = document.getElementById("id" + index).value;
    var selectedQuantity = document.getElementById("selectedQuantity" + index).value;
    
    window.location.replace(pageContext + "/update-cart?id=" + id + "&selectedQuantity=" + selectedQuantity);
}

function deleteFromCart(pageContext, index) {
    var id = document.getElementById("id" + index).value;
    window.location.replace(pageContext + "/delete-from-cart?id=" + id);
}

function proceedWithPayment(pageContext) {
    window.location.replace(pageContext + "/pay");
}