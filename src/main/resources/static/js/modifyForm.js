function saveThumbnail() {

    let picForm = $("form")[0];
    let formData = new FormData(picForm);
    $.ajax({
        type: "PATCH",
        url: "/gamebook/thumbnail",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            console.log(result.filePath);
            if (!result) {
                alert(".jpg 또는 .png 확장자의 이미지 파일을 입력하세요");
            } else {
                $("#thumbnailPic").attr("src", "/images/" + result.filePath);
            }
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}

function saveTitle() {
    let form = $("form")[0];
    let formData = new FormData(form);
    $.ajax({
        type: "PATCH",
        url: "/gamebook/title",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            $("#titleText").text(result.title);
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}

function changePublicStatus() {
    $.ajax({
        type: "PATCH",
        url: "/gamebook/public",
        data: $("#gbNumForm").serialize(),
    })
        .done(function (result) {
            document.location.reload();

        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}
