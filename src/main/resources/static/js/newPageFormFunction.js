function savePic() {

    let picForm = $("form")[0];
    let formData = new FormData(picForm);
    $.ajax({
        type: "POST",
        url: "/page/update-pic",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            console.log(result.filePath);
            if (!result) {
                alert(".jpg 또는 .png 확장자의 이미지 파일을 입력하세요");
            } else {
                $("#pagePic").attr("src", "/images/" + result.filePath);
            }
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}

function saveCon() {
    let form = $("form")[0];
    let formData = new FormData(form);
    $.ajax({
        type: "POST",
        url: "/page/update-con",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            $("#pageContent").text(result.content);
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}

function saveFirstChoice() {
    let form = $("#firstForm")[0];
    let formData = new FormData(form);
    $.ajax({
        type: "POST",
        url: "/page/update-first-choice",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            if (result.next == 0) {
                $("#firstChoiceNum").text("메인 페이지");
            } else {
                $("#firstChoiceNum").text(result.next);
            }
            $("#firstChoiceCon").text(result.content);
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}

function saveSecondChoice() {
    let form = $("#secondForm")[0];
    let formData = new FormData(form);
    $.ajax({
        type: "POST",
        url: "/page/update-second-choice",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            if (result.next == 0) {
                $("#secondChoiceNum").text("메인 페이지");
            } else {
                $("#secondChoiceNum").text(result.next);
            }
            $("#secondChoiceCon").text(result.content);
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}

function saveThirdChoice() {
    let form = $("#thirdForm")[0];
    let formData = new FormData(form);
    $.ajax({
        type: "POST",
        url: "/page/update-third-choice",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (result) {
            if (result.next == 0) {
                $("#thirdChoiceNum").text("메인 페이지");
            } else {
                $("#thirdChoiceNum").text(result.next);
            }
            $("#thirdChoiceCon").text(result.content);
        })
        .fail(function (jqXHR) {
            console.log(jqXHR);
        })
        .always(function () {
        })
}
