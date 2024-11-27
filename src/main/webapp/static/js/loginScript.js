$(document).ready(function () {
    initDatepicker();

    $('#signup').click(switchToSignupPage);
    $('#signin').click(switchToSigninPage);

    submitRegisterForm();
    submitLoginForm();

    validateInput();
});

function validateInput() {
    const registerButton = $("#registerButton");

    const validationRules = {
        uid: /^[a-zA-Z0-9_]{1,50}$/, // 用户ID：1-50字符，仅限字母、数字和下划线
        name: /^[\u4e00-\u9fa5a-zA-Z0-9_]{1,50}$/, // 用户名：1-50字符，允许中文
        password: /^.{6,100}$/, // 密码：6-100字符
        email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/, // 邮箱格式
        phone: /^\d{7,15}$/, // 手机号：7-15位数字
    };

    // 验证函数
    function validateField(fieldId) {
        const value = $(`#${fieldId}`).val();
        const isValid = validationRules[fieldId].test(value);
        const errorMsg = $(`#${fieldId}Error`);

        if (!isValid) {
            $(`#${fieldId}`).addClass("invalid").removeClass("valid");
            errorMsg.text(getErrorMessage(fieldId)).css("visibility", "visible");
        } else {
            $(`#${fieldId}`).addClass("valid").removeClass("invalid");
            errorMsg.css("visibility", "hidden");
        }

        return isValid;
    }

    // 错误提示信息
    function getErrorMessage(fieldId) {
        switch (fieldId) {
            case "uid": return "用户ID仅限字母、数字、下划线，且不超过50字符！";
            case "name": return "用户名仅限中文、字母、数字、下划线，且不超过50字符！";
            case "password": return "密码必须在6-100字符之间！";
            case "email": return "请输入有效的邮箱地址！";
            case "phone": return "手机号必须为7-15位数字！";
            default: return "输入格式不正确！";
        }
    }

    // 输入框失焦时验证
    $("input").on("blur", function () {
        const fieldId = $(this).attr("id");
        validateField(fieldId);
        toggleRegisterButton();
    });

    // 根据验证结果启用或禁用按钮
    function toggleRegisterButton() {
        const isFormValid = Object.keys(validationRules).every((fieldId) => validateField(fieldId));
        registerButton.prop("disabled",!isFormValid);
    }
}

function submitLoginForm() {
    $("#loginForm").submit(function (e) {
        e.preventDefault();
        const formData = {
            uid: $("#LoginUid").val(),
            password: $("#LoginPassword").val()
        };
        console.log("表单数据：", formData);
        $.ajax({
            type: "POST",
            url: "/user/login",
            data: JSON.stringify(formData),
            contentType: "application/json", // 设置请求头x
            success: function () {
                // alert("登录成功！即将跳转到用户信息页面。");
                window.location.href = "/userInfo";
            },
            error: function () {
                alert("登录失败，请重试！");
            }
        });
    });
}

function submitRegisterForm() {
    $("#registerForm").submit(function (e) {
        e.preventDefault();
        console.log("提交表单...");
        // 将表单数据转换为 JSON 对象
        const formData = {
            name: $("#name").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            phone: $("#phone").val()
        };
        console.log("表单数据：", formData);
        $.ajax({
            type: "POST",
            url: "/user/register",
            data: JSON.stringify(formData), // 转换为 JSON 字符串
            contentType: "application/json", // 设置请求头x
            success: function () {
                // alert("注册成功！即将跳转到用户信息页面。");
                window.location.href = "/userInfo";
            },
            error: function () {
                alert(`注册失败：请检查输入！`);
            }
        });
    });
}

function switchToSignupPage() {
    $('.pinkbox').css('transform', 'translateX(80%)');
    $('.signin').addClass('nodisplay');
    $('.signup').removeClass('nodisplay');
}

function switchToSigninPage() {
    $('.pinkbox').css('transform', 'translateX(0%)');
    $('.signup').addClass('nodisplay');
    $('.signin').removeClass('nodisplay');
}

function initDatepicker() {
    $("#birthDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true,
        yearRange: "1900:2024"
    });
}