$(document).ready(function () {
    var date = $("#dateSubmit").val();
    var id1 = $("#movieId").val();
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "http://localhost:8054/date/date-choose/" + id1 + "/" + date,
        success: function (showTimeList) {

            $('#tableShow').empty()
            for (let i in showTimeList) {
                var tableS = "#tableShow" + i;
                $('#tableShow').append("<tr class='tableShow" + i + "'></tr>")

                $(".tableShow" + i).append(
                    "<th>" + showTimeList[i].cinema + "</th>");
                for (let j in showTimeList[i].time) {
                    $(".tableShow" + i).append("<td>\
                            <a href='http://localhost:8054/buy-ticket/showPrice/" + showTimeList[i].id + "/" + showTimeList[i].time[j] + "'>\
                                <button id='clock' class='btn btn-outline-info' >" + showTimeList[i].time[j] + "</button>\
                            </a></td>\
                    ");


                }

            }
        },
        error: function (err) {
            console.log(err);
            alert(err);
        }
    });
    document.getElementById("dateSubmit").onchange = function () {

        date = $("#dateSubmit").val();
        $.ajax({
            type: 'POST',
            contentType: "application/json",
            url: "http://localhost:8054/date/date-choose/" + id1 + "/" + date,
            success: function (showTimeList) {

                $('#tableShow').empty()
                for (let i in showTimeList) {
                    $('#tableShow').append("<tr class='tableShow" + i + "'></tr> ")

                    $(".tableShow" + i).append(
                        "<th>" + showTimeList[i].cinema + "</th>");
                    for (let j in showTimeList[i].time) {
                        $(".tableShow" + i).append("<td>\
                            <a href='http://localhost:8054/buy-ticket/showPrice/" + showTimeList[i].id + "/" + showTimeList[i].time[j] + "'>\
                                <button id='clock' class='btn btn-outline-info' >" + showTimeList[i].time[j] + "</button>\
                            </a></td>\
                    ");


                    }

                }
            },
            error: function (err) {
                console.log(err);
                alert(err);
            }
        });


    }
});