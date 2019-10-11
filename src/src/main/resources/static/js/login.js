
function surligne(champ, erreur)
{
   if(erreur)
      champ.style.backgroundColor = "#fbe";
   else
      champ.style.backgroundColor = "";
}



function verifStudentID(champ)
{
   if(champ.value.length < 2 || champ.value.length > 25)
   {
      surligne(champ, true);
      return false;
   }
   else
   {
      surligne(champ, false);
      return true;
   }
}


function verifForm(f)
{
   var verifID = verifStudentID(f.studentID);
   
   if(verifID)
      return true;
   else
   {
      alert("Veuillez remplir correctement tous les champs");
      return false;
   }
}