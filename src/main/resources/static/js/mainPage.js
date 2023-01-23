function deleteComment() {
    $.ajax({
        type: "DELETE",
        url: "/comment/[[${comment.commentId}]]"
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

function inputComment() {
    $.ajax({
        type: "POST",
        url: "/comment",
        data: $("#commentForm").serialize(),
        dataType: "html",
    })
        .done(function (result) {
            $("#commentDiv").replaceWith(result);
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}
