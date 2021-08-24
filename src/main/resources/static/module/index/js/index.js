$(function () {
    $('#datetimepicker').datetimepicker({
        inline: true,
    });

    // $.ajax({
    //     "url": url + '/user/toIndex',
    //     "type": "POST",
    //     "contentType": "application/json",
    //     "beforeSend": function (request) {
    //         request.setRequestHeader("Authorization", window.localStorage.token);
    //     },
    //     "success": function (result) {
    //         if(result["code"] == 400){
    //             alert(data.msg)
    //             window.location.href = url + "/login/login.html";
    //         }else{
    //             var data = result["data"];
    //
    //         }
    //
    //     },
    //     "error": function () {
    //         alert("服务器繁忙，请稍后重试");
    //         window.location.href = url + "/login/login.html";
    //     }
    // });
});

$(document).ready(function () {
    $(".booking-calender .fa.fa-clock-o").removeClass(this);
    $(".booking-calender .fa.fa-clock-o").addClass('fa-clock');
});