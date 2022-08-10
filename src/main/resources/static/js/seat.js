$(document).ready(function() {
    // var checkbox = document.getElementsByClassName("checkboxSelect");
    var checkbox1 = document.getElementsByClassName("mybtn");
     var priceVal=$(".price").text();
    $(".mybtn").click(function () {
     var ln = 0;
     for (var i = 0; i < checkbox1.length; i++) {
         if (checkbox1[i].checked ){
             ln++;
         }

     }

        console.log(ln);
        $(".price2").text($(".price2").text().replace($(".price2").text(),priceVal*ln));
        document.getElementById("price3").value = priceVal*ln;
 })

    
})