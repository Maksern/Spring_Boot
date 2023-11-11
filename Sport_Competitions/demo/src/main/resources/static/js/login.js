function showLogin() {
    document.getElementById("login-form").style.display = "grid";
    document.getElementById("signup-form").style.display = "none";
}

function showSignup() {
    document.getElementById("login-form").style.display = "none";
    document.getElementById("signup-form").style.display = "grid";
}