var pwd = document.getElementById("pwd-button");
var pwdmod = document.getElementById("pwd-modal");
var pwdclose = document.getElementById("pwd-close");

pwd.onclick = function() {
  pwdmod.style.display = "block";
};

pwdclose.onclick = function() {
  pwdmod.style.display = "none";
};

window.onclick = function(event) {
  if (event.target == pwdmod) {
    pwdmod.style.display = "none";
  }
};
