/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

var req = document.getElementById("request-button");

var sha = document.getElementById("share-button");

var reqmod = document.getElementById("request-modal");

var shamod = document.getElementById("share-modal")

var reqclose = document.getElementById("request-close");

var shaclose = document.getElementById("share-close");

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

req.onclick = function() {
  reqmod.style.display = "block";
}

sha.onclick = function() {
  shamod.style.display = "block";
}

reqclose.onclick = function() {
  reqmod.style.display = "none";
}

shaclose.onclick = function() {
  shamod.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
  else if (event.target == reqmod) {
    reqmod.style.display = "none";
  }
  else if (event.target == shamod) {
    shamod.style.display = "none";
  }
}
