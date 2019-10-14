/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
const btnElts = document.querySelectorAll(".myBtn");

// when the user clicks one of the button open the modal that correspond to the button
for (let i = 0; i < btnElts.length; i++) {
    btnElts[i].onclick = function () {
        // get id of the correspondant modal
        const modalId = "modal-" + btnElts[i].id
        // get the modal
        const modal = document.getElementById(modalId)
        modal.style.display = "block";
        // get the span that closes the modal
        const closerId = "close-" + btnElts[i].id
        const span = document.getElementById(closerId)
        // When the user clicks on <span> (x), close the modal
        span.onclick = function () {
            modal.style.display = "none";
        }

    }
}



// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
   const modals = document.querySelectorAll(".modal");
    for (let i = 0; i < modals.length; i++) {
        if (event.target == modals[i]) {
            modals[i].style.display = "none";
        }
    }
}

