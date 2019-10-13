var req = document.getElementById("email-button");
var sha = document.getElementById("pwd-button");
var reqmod = document.getElementById("email-modal");
var shamod = document.getElementById("pwd-modal");
var reqclose = document.getElementById("email-close");
var shaclose = document.getElementById("pwd-close");

req.onclick = function() {
  reqmod.style.display = "block";
};

sha.onclick = function() {
  shamod.style.display = "block";
};

reqclose.onclick = function() {
  reqmod.style.display = "none";
};

shaclose.onclick = function() {
  shamod.style.display = "none";
};

window.onclick = function(event) {
  if (event.target == reqmod) {
    reqmod.style.display = "none";
  } else if (event.target == shamod) {
    shamod.style.display = "none";
  }
};