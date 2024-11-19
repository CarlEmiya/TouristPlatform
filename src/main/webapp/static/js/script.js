
var currentProductId;

function findAll() {

    $.ajax({
        url: 'product/findAll',
        type: "POST",
        success: function (list) {

            $("#dataTable tr.datarow").remove();

            $.each(list, function (index, p) {
                //console.log(p);
                var str = "<tr id=tr" + p.productId + " class=\"datarow\">" +
                        "<td>" + p.productId + "</td>" +
                        "<td>" + p.productName + "</td>" +
                        "<td>" + p.productPrice + "</td>" +
                        "<td>" + p.productUnit + "</td>" +
                        "<td>" + p.productStock + "</td>" +
                        "<td>" +
                        "<a href=\"javascript:popupEdit(" + p.productId + ");\">编辑</a>&nbsp;&nbsp;" +
                        "<a href=\"javascript:delete_product(" + p.productId + ");\">删除</a>" +
                        "</td></tr>";

                $("#dataTable").append(str);
            });
        },
        error: function (req, status, error) {
            alert("Ajax请求失败！" + error);
        }
    });
}

function popupEdit(pid) {

    currentProductId = pid;
    $.ajax({
        url: 'product/get_product_to_edit',
        type: 'POST',
        data: {proId: pid}, //发送一个请求参数，参数名为proId，参数值为传入的pid变量的值
        success: function (pro) {
            $("#inputId").val(pro.productId);
            $("#inputName").val(pro.productName);
            $("#inputPrice").val(pro.productPrice);
            $("#inputUnit").val(pro.productUnit);
            $("#inputStock").val(pro.productStock);
            $('#editModal').modal('show');
        },
        error: function (req, status, error) {
            alert("Ajax请求失败，错误：" + error);
        }
    });
}

function popupAdd() {
    $('#addModal').modal('show');
    $("#addForm")[0].reset();
}

//定义一个函数,根据编号删除产品
function delete_product(pid) {
    $.ajax({
        url: 'product/delete',
        type: 'POST',
        data: {proId: pid}, //发送一个请求参数，参数名为proId，参数值为传入的pid变量的值
        success: function () {
            //如果能执行到success，说明后台删除成功，这里同时将表格中对应的数据行删除
            $("#tr" + pid).remove(); // remove是删除当前元素和其内容
        },
        error: function (req, status, error) {
            alert("Ajax请求失败，错误：" + error);
        }
    });
}

$(document).ready(function () {

    $("#btnEdit").click(function () {
        $.ajax({
            url: 'product/edit_product',
            type: 'POST',
            data: $("#editForm").serialize(),
            success: function () {
                $("#tr" + currentProductId).children().eq(1).html($("#inputName").val());
                $("#tr" + currentProductId).children().eq(2).html($("#inputPrice").val());
                $("#tr" + currentProductId).children().eq(3).html($("#inputUnit").val());
                $("#tr" + currentProductId).children().eq(4).html($("#inputStock").val());
                $('#editModal').modal('hide');
            },
            error: function (req, status, error) {
                alert("Ajax请求失败，错误：" + error);
            }
        });
    });

    $("#btnAdd").click(function () {
        $.ajax({
            url: 'product/add_product',
            type: 'POST',
            data: $("#addForm").serialize(),
            success: function (proId) {
                var p = {
                    productId: proId,
                    productName: $("#proName").val(),
                    productPrice: $("#proPrice").val(),
                    productUnit: $("#proUnit").val(),
                    productStock: $("#proStock").val()
                }; 

                var str = "<tr id=tr" + p.productId + " class=\"datarow\">" +
                        "<td>" + p.productId + "</td>" +
                        "<td>" + p.productName + "</td>" +
                        "<td>" + p.productPrice + "</td>" +
                        "<td>" + p.productUnit + "</td>" +
                        "<td>" + p.productStock + "</td>" +
                        "<td>" +
                        "<a href=\"javascript:popupEdit(" + p.productId + ");\">编辑</a>&nbsp;&nbsp;" +
                        "<a href=\"javascript:delete_product(" + p.productId + ");\">删除</a>" +
                        "</td></tr>";

                $("#dataTable").append(str);

                $("#addModal").modal("hide");
            },
            error: function (req, status, error) {
                alert("Ajax请求失败，错误：" + error);
            }
        });
    });
});


// 校验输入内容并动态更新错误提示
$(document).ready(function () {
    const registerButton = $("#registerButton");

    const validationRules = {
        userId: /^[a-zA-Z0-9_]{1,50}$/, // 用户ID：1-50字符，仅限字母、数字和下划线
        userName: /^[\u4e00-\u9fa5a-zA-Z0-9_]{1,50}$/, // 用户名：1-50字符，允许中文
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
            case "userId": return "用户ID仅限字母、数字、下划线，且不超过50字符！";
            case "userName": return "用户名仅限中文、字母、数字、下划线，且不超过50字符！";
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
        registerButton.prop("disabled", !isFormValid);
    }
});

