/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function handleModals(btnClassName, modalIdPrefix) {
    // Get the button that opens the modal
    const btnElts = document.querySelectorAll("." + btnClassName);

// when the user clicks one of the button open the modal that correspond to the button
    for (let i = 0; i < btnElts.length; i++) {
        btnElts[i].onclick = function () {
            // get id of the correspondant modal
            const modalId = modalIdPrefix + btnElts[i].id
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
}

/****
 * 
 *  MODAL TYPE CONTENUS
 *
 */

handleModals("myBtn", "modal-")


/**
 * 
 * MODAL TYPE DELETE
 * 
 */
handleModals("delBtn", "modal-")

/**
 * 
 * MODAL TYPE EDIT
 * 
 */
handleModals("editBtn", "modal-")



// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    const modals = document.querySelectorAll(".modal");
    for (let i = 0; i < modals.length; i++) {
        if (event.target == modals[i]) {
            modals[i].style.display = "none";
        }
    }
}

