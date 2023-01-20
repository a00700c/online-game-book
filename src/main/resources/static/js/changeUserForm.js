function updatePassword() {
    $.ajax({
        type: "PUT",
        url: "/member/change-password",
        data: $("#passwordForm").serialize()
    })
        .done(function (result) {
            if (result == null) {

            } else {
                $("#changePassword").text(result.password);
            }
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}
