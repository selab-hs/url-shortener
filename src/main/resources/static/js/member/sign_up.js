// 이메일 방식 입력
const emailCheck = /[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\w+\.)+\w+$/;

// 비밀번호 영문자+숫자+특수조합(8~16자리 입력) 정규식
const pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;

let email_boolean = false;
let email_duplication_boolean = false;
let password_boolean = false;
let password_check_boolean = false;

function email_check() {
    const email = document.getElementById("email_input");
    const email_error_text = document.getElementById("email_error_text");

    if (!emailCheck.test(email.value)) {
        email_error_text.style.display = 'block';
        email_boolean = false;
    } else {
        email_error_text.style.display = 'none';
        email_boolean = true;
    }
}

function email_duplication_check() {
    const email = $("#email_input");
    if (!email_boolean) {
        alert("유효하지 않은 이메일 형식입니다");
        return;
    }

    $.ajax({
        url: "/api/v1/members/email-check?email=" + email.val(),
        type: "GET",
        success: function () {
            alert("중복되지 않은 이메일입니다.");
            email_duplication_boolean = true;
            email.prop('readonly', true);
            email.css("background-color", "#E6E6E6");
            $(".join_email").css("background-color", "#E6E6E6");
        },
        error: function () {
            alert("중복된 이메일입니다. 다른 이메일을 입력해주세요.");
            email_duplication_boolean = false;
        }
    })
}

function pwd_check() {
    const pwd = document.getElementById("password_input");
    const pwd_error_text = document.getElementById("password_error_text");

    if (!pwdCheck.test(pwd.value)) {
        pwd_error_text.style.display = 'block';
        password_boolean = false;
    } else {
        pwd_error_text.style.display = 'none';
        password_boolean = true;
    }
}

function pwd_check2() {
    const pwd = document.getElementById("password_input");
    const pwd_check = document.getElementById("password_check_input");
    const pwd_check_error_text = document.getElementById("password_check_error_text");

    if (pwd.value !== pwd_check.value) {
        pwd_check_error_text.style.display = 'block';
        password_check_boolean = false;
    } else {
        pwd_check_error_text.style.display = 'none';
        password_check_boolean = true;
    }
}

const maxLength = (e) => {
    if (e.target.value.length > e.target.maxLength) {
        e.target.value = e.target.value.slice(0, e.target.maxLength);
    }
}

document.addEventListener("DOMContentLoaded", function () {
    if (window.localStorage.getItem("X-READYS-AUTH-TOKEN") != null) {
        alert('유저 정보가 이미 존재합니다.');
        location.href = "/member_home";
        return;
    }

    document.getElementById("email_input").addEventListener("blur", function () {
        email_check();
    });

    document.getElementById("password_input").addEventListener("blur", function () {
        pwd_check();
    });

    document.getElementById("password_check_input").addEventListener("blur", function () {
        pwd_check2();
    });
});

function sign_up_submit() {
    if (email_duplication_boolean && password_boolean && password_check_boolean) {
        let data = JSON.stringify({
            "email": $("#email_input").val(),
            "password": $("#password_input").val()
        });

        $.ajax({
            url: "/api/v1/members",
            data: data,
            contentType: 'application/json',
            type: "POST",
            success: function () {
                alert("회원 가입 성공 !");
                location.href = "/login";
            },
            error: function (response) {
                alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
            }
        })
    } else {
        alert("회원가입 정보가 올바르지 않습니다.");
        return false;
    }
}
