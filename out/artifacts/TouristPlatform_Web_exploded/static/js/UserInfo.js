
$(document).ready(function () {
    // $.ajax({
    //     type: "GET",
    //     url: "/user/getUserInfo",
    //     success: function (currentUser) {
    //         if (!currentUser) {
    //             window.location.href = "/login";
    //             return;
    //         }
    //         // 将用户信息展示在页面上
    //         $('#uid').val(currentUser.uid);
    //         $('#name').val(currentUser.name);
    //         $('#gender').val(currentUser.gender);
    //         $('#birth').val(currentUser.birth);
    //         $('#idcard').val(currentUser.idcard);
    //         $('#email').val(currentUser.email);
    //         $('#phone').val(currentUser.phone);
    //         $('#city').val(currentUser.city);
    //         $('#occupation').val(currentUser.occupation);
    //         $('#question').val(currentUser.question);
    //         $('#level').val(currentUser.level);
    //         $('#created').val(currentUser.created);
    //         $('#deleted').val(currentUser.deleted);
    //         $('#period').val(currentUser.period);
    //         $('#status').val(currentUser.status);
    //         $('#identify').val(currentUser.identify);
    //         $('#answer').val(currentUser.answer);
    //         $('#picture').val(currentUser.picture);
    //         $('#description').val(currentUser.description);
    //
    //     },
    //     error: function () {
    //         alert("无法加载用户信息，请稍后重试！");
    //         window.location.href = "/login";
    //     }
    // });

    // 点击编辑信息按钮，使输入框可编辑
    $('#editInfoButton').click(function () {
        $('.user-info-input').removeClass('user-info-input').addClass('user-info-input-editable').attr('readonly', false);
    });

    // 点击保存按钮，将更新后的信息发送给后端
    $('#userInfoSection').on('submit', '#updateInfoForm', function (e) {
        e.preventDefault();
        const formData = {
            uid: $('#uid').val(),
            name: $('#name').val(),
            password: $('#password').val(),
            gender: $('#gender').val(),
            birth: $('#birth').val(),
            idcard: $('#idcard').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            city: $('#city').val(),
            occupation: $('#occupation').val(),
            question: $('#question').val(),
            level: $('#level').val(),
            created: $('#created').val(),
            deleted: $('#deleted').val(),
            period: $('#period').val(),
            status: $('#status').val(),
            identify: $('#identify').val(),
            answer: $('#answer').val(),
            picture: $('#picture').val(),
            description: $('#description').val()
        };
        $.ajax({
            type: "POST",
            url: "/user/updateInfo",
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function () {
                alert("信息更新成功！");
                // 更新成功后可根据需求重新获取用户信息并展示，这里暂不实现
            },
            error: function () {
                alert("信息更新失败，请重试！");
            }
        });
    });
});