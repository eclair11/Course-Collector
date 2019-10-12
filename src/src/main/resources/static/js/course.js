var req = document.getElementById("request-button");
var sha = document.getElementById("share-button");
var reqmod = document.getElementById("request-modal");
var shamod = document.getElementById("share-modal");
var reqclose = document.getElementById("request-close");
var shaclose = document.getElementById("share-close");

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

function redirect() {
  location.href = "http://localhost:8080/content";
}