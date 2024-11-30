$(document).ready(function () {
    // 编辑信息功能
    $("#editInfoButton").click(function () {
        // 切换所有输入框的编辑状态
        toggleInputEditable(true);

        // 修改按钮变为“保存修改”
        $(this).text("保存修改");
        $(this).off("click").click(function () {
            const formData = collectFormData();

            // 发送Ajax请求
            $.ajax({
                type: "POST",
                url: "/user/updateInfo",
                data: JSON.stringify(formData),
                contentType: "application/json",
                success: function () {
                    // alert("信息更新成功！");
                    location.reload(); // 重新加载页面
                },
                error: function () {
                    // alert("信息更新失败！");
                }
            });

            // 修改按钮恢复原样
            $(this).text("编辑信息");
            toggleInputEditable(false);
        });
    });

    // 修改密码功能
    $('#updatePasswordButton').click(function () {
        const oldPassword = $('#oldPassword').val();
        const newPassword = $('#newPassword').val();
        const confirmNewPassword = $('#confirmNewPassword').val();

        // 检查新密码和确认密码是否一致
        if (newPassword !== confirmNewPassword) {
            alert("新密码和确认新密码不一致，请重新输入！");
            return;
        }

        const formData = {
            oldPassword: oldPassword,
            newPassword: newPassword
        };

        $.ajax({
            type: "POST",
            url: "/user/updatePassword",
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function () {
                alert("密码更新成功！");
            },
            error: function () {
                alert("密码更新失败，请稍后再试！");
            }
        });
    });

    // 禁用账户功能
    $('#disableUser').click(function () {
        // 显示禁用账户确认对话框
        $('#disableConfirmationDialog').show();
    });

    $('#confirmDisableButton').click(function () {
        $.ajax({
            type: "POST",
            url: "/user/disable",
            success: function () {
                alert("账号已禁用！");
                $('#disableUser').text("恢复正常使用");
            },
            error: function () {
                alert("禁用账号失败，请稍后再试！");
            }
        });
        $('#disableConfirmationDialog').hide(); // 隐藏对话框
    });

    $('#cancelDisableButton').click(function () {
        $('#disableConfirmationDialog').hide(); // 隐藏对话框
    });

    // 删除账户功能
    $('#deleteUser').click(function () {
        showDeleteConfirmation().then(() => {
            const password = prompt("请输入密码以确认删除账户:");
            if (password) {
                $.ajax({
                    type: "POST",
                    url: "/user/delete",
                    data: JSON.stringify({ password: password }),
                    contentType: "application/json",
                    success: function () {
                        alert("账户已删除！");
                        window.location.href = "/home"; // 重定向到首页
                    },
                    error: function () {
                        alert("删除账户失败！");
                    }
                });
            } else {
                alert("密码不能为空！");
            }
        }).catch(() => {
            // alert("删除操作已取消");
        });
    });
});

// 工具函数：切换输入框的可编辑状态
function toggleInputEditable(isEditable) {
    $("input[type='text']").each(function () {
        if (isEditable) {
            $(this).removeClass("user-info-input").addClass("user-info-input-editable");
            $(this).prop("readonly", false);
        } else {
            $(this).removeClass("user-info-input-editable").addClass("user-info-input");
            $(this).prop("readonly", true);
        }
    });
}

// 工具函数：收集表单数据
function collectFormData() {
    return {
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
}

// 删除账户确认功能
function showDeleteConfirmation() {
    const confirmationPromises = [];
    for (let i = 0; i < 3; i++) {
        confirmationPromises.push(new Promise((resolve, reject) => {
            const dialog = $('<div>').text('确定要删除账号吗？').css('color', 'rgb(' + (255 - 50 * i) + ', 0, 0)');
            $('body').append(dialog);
            dialog.dialog({
                modal: true,
                buttons: {
                    "确认": function () {
                        $(this).dialog("close");
                        resolve();
                    },
                    "取消": function () {
                        $(this).dialog("close");
                        reject();
                    }
                }
            });
        }));
    }
    return Promise.all(confirmationPromises);
}
