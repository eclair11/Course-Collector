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

function check() {
  if (document.getElementById("date-picker").checked == "1") {
    document.getElementById("select-div").style.display = "block";
    document.getElementById("input-div").style.display = "none";
  } else if (document.getElementById("date-picker").checked == "0") {
    document.getElementById("select-div").style.display = "none";
    document.getElementById("input-div").style.display = "block";
  }
}

function insert() {
  id = document.getElementById("input-date");
  sd = document.getElementById("select-date");
  id.value = sd.options[sd.selectedIndex].value;
}


/**
 * Function that permit user to like or dislike a course
 * @author Solofo R.
 * @param boolean like - true if like false otherwise
 * @param number studentId
 * @param number courseId
 * @param number subjectId
 */
function like(like=true, studentId, courseId, subjectId) {
    const likeValue = (like) ? 1 : 0;
    window.location.href="/rateCourse?like=" + likeValue + "&studentId=" + studentId + "&courseId=" + courseId + "&subjectId=" + subjectId;
}

