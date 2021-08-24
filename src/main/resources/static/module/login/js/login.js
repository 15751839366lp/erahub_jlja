$(function () {
    var url = 'http://127.0.0.1:8888';
    $(".submitBtn").click(function () {
        $.ajax({
            "url": url + '/user/login',
            "data": JSON.stringify({
                "username": $('input[name="username"]').val(),
                "password": $('input[name="password"]').val(),
            }),
            "dataType": "json",
            "type": "POST",
            "contentType": "application/json",
            "success": function (result) {
                if (result["code"] == 400) {
                    alert(result["msg"])
                } else {
                    var data = result["data"];
                    window.localStorage.token = data["token"];
                    $("#toIndexForm").attr("action",url + "/user/toIndex")
                    $('input[name="token"]').val(window.localStorage.token)
                    $("#toIndexForm").submit();
                }

            },
            "error": function () {
                alert("服务器繁忙，请稍后重试");
            }

        });

    });

})
