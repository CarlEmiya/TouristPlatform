$(document).ready(function () {
    // 初始化日期选择器
    $("#birthDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true,
        yearRange: "1900:2024"
    });

    // 切换到注册页面
    $('#signup').click(function () {
        $('.pinkbox').css('transform', 'translateX(80%)');
        $('.signin').addClass('nodisplay');
        $('.signup').removeClass('nodisplay');
    });

    // 切换到登录页面
    $('#signin').click(function () {
        $('.pinkbox').css('transform', 'translateX(0%)');
        $('.signup').addClass('nodisplay');
        $('.signin').removeClass('nodisplay');
    });

    // AJAX 提交注册表单
    $("#registerForm").submit(function (e) {
        e.preventDefault();
        const formData = $(this).serialize();
        $.ajax({
            type: "POST",
            url: "/register",
            data: formData,
            success: function (response) {
                alert("注册成功！请登录。");
                $('#signin').click();
            },
            error: function () {
                alert("注册失败，请检查输入！");
            }
        });
    });

    // AJAX 提交登录表单
    $("#loginForm").submit(function (e) {
        e.preventDefault();
        const formData = $(this).serialize();
        $.ajax({
            type: "POST",
            url: "/login",
            data: formData,
            success: function (response) {
                alert("登录成功！");
                window.location.href = "/home";
            },
            error: function () {
                alert("登录失败，请重试！");
            }
        });
    });
});
