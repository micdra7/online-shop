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