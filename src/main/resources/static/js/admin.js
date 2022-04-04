    
    /**
     * Gère la vue de la page admin
     * @author Solofo R.
     */
    vm = new Vue({
        el: '#main',
        
        data: () => ({
            classes: [],
            options: [],
        }),

        created: function () {
            this.loadClasses()
            console.log("entrer dans created")
            console.log(this.classes)
          },

        methods: {
            loadClasses: async function () {
                const url = `/api/classes`
                const response = await fetch(url)
                console.log(response);
                const myJson = await response.json()
                this.classes = myJson
                console.log(this.classes)
            },
            
            loadOpions: async function () {
                const url = `/api/options`
                const response = await fetch(url)
                const myJson = await response.json()
                this.options = myJson
            },

            handleModal: function (event) {
                // get id of the correspondant modal
                const modalId = 'modal-' + event.currentTarget.id;
                // get the modal
                const modal = document.getElementById(modalId);
                modal.style.display = "block";
                // get the span that closes the modal
                const closerId = "close-" + event.currentTarget.id;
                const span = document.getElementById(closerId);
                // When the user clicks on <span> (x), close the modal
                span.onclick = function() {
                    modal.style.display = "none";
                };
            },

            toggleContent: function (event) {
                const btnId = event.currentTarget.id;
                const btn = event.currentTarget;
                // get the element to toggle identified by 'content-${btnId}'
                const eltToToggle = document.querySelector("#content-" + btnId);
                // if the element is hidden
                if (eltToToggle.style.display !== "block") {
                    // show the element 
                    eltToToggle.style.display = "block";
                    // hide the first icon inside the button and show the last one
                    btn.firstElementChild.style.display = "none";
                    btn.lastElementChild.style.display = "inline";
                }
                else {
                    // show the element 
                    eltToToggle.style.display = "none";
                    // show the first icon inside the button and hide the last one
                    btn.firstElementChild.style.display = "inline";
                    btn.lastElementChild.style.display = "none";
                }
            }

        }
    })


// setInterval(function(){
//     vm.chargerAvions();
//     vm.chargerMoteurs();
//     vm.chargerMessages();
// }, 700);

