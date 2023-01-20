function updatePassword() {
    $.ajax({
        type: "PATCH",
        url: "/member/change-password",
        data: $("#passwordForm").serialize()
    })
        .done(function (result) {
            if (result.isSuccess) {
                $("#changePassword").text(result.password);
                $("#password").css('border-color', "");
                $("#passwordErrorMessage").text(null);
            } else {
                $("#password").css('border-color', "#bd2130");
                $("#passwordErrorMessage").text(result.errorMessage + "");
            }
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}
