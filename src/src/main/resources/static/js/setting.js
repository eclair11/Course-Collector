var email = document.getElementById("email-button");
var pwd = document.getElementById("pwd-button");
var remove = document.getElementById("remove-button");
var emailmod = document.getElementById("email-modal");
var pwdmod = document.getElementById("pwd-modal");
var removemod = document.getElementById("remove-modal");
var emailclose = document.getElementById("email-close");
var pwdclose = document.getElementById("pwd-close");
var removeclose = document.getElementById("remove-close");

email.onclick = function() {
  emailmod.style.display = "block";
};

pwd.onclick = function() {
  pwdmod.style.display = "block";
};

remove.onclick = function() {
  removemod.style.display = "block";
};

emailclose.onclick = function() {
  emailmod.style.display = "none";
};

pwdclose.onclick = function() {
  pwdmod.style.display = "none";
};

removeclose.onclick = function() {
  removemod.style.display = "none";
};

window.onclick = function(event) {
  if (event.target == emailmod) {
    emailmod.style.display = "none";
  } else if (event.target == pwdmod) {
    pwdmod.style.display = "none";
  } else if (event.target == removemod) {
    removemod.style.display = "none";
  }
};
