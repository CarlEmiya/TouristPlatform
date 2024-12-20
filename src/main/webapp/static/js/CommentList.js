// <script th:inline="javascript">

const connectId = $("#connectId").val();
const currentUserId = $("#LoginUid").val();
// 当前页码和每页记录数
const pageNo = $("#pageNo").val();
const pageSize = $("#pageSize").val();
const ConnectType = $("#ConnectType").val();
const connectType = $("#ConnectType").val();
const CommentMaxLength = 200; // 评论最大长度

function formatDate(dateString) {
    const date = new Date(dateString); // 将时间字符串转换为 Date 对象
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1并补零
    const day = String(date.getDate()).padStart(2, '0'); // 补零到两
    return `${year}-${month}-${day}`;
}

function formatfullComment(fullComment) {
    const fullComment1 = "" + fullComment + "\n";
    // debugger;
    // console.log("来自formatfullComment函数的fullComment：", fullComment1.length);
    return fullComment1;
}

function formatCity(city) {
    // 截取城市前两位
    return city.substring(0, 2);
}

function formatRate(rate) {
    // console.log("来自formatRate函数的评分：", rate);
    // 格式化评分1-10分
    if (rate >= 1 && rate <= 10) {
        //得到json，返回原样的text字符串
        return rate;
    }
}

// 删除单条评论
function deleteComment(cid) {
    // console.log("来自deleteComment函数的CID：", cid);
    if (confirm("确定要删除这条评论吗？")) {
        $.ajax({
            url: "/comment/deleteByCid",
            type: "DELETE",
            contentType: "application/json",  // 设置请求内容类型为 JSON
            data: JSON.stringify({cid: cid}),  // 将请求数据转换为 JSON 格式
            success: function (response) {
                if (response === 1) {  // 假设后端返回 1 表示成功
                    // alert("评论已删除！");
                    // 刷新页面
                    window.location.reload();
                } else {
                    alert("删除失败！");
                }
            },
            error: function (xhr, status, error) {
                alert("删除失败！请重试。");
                // console.error(error);  // 输出详细错误信息
            }
        });
    }
}


// 编辑评论函数
// 编辑评论函数
function editComment(cid) {
    const commentContentElement = document.getElementById(`commentContent-${cid}`);
    const rateElement = document.getElementById(`commentRate-${cid}`);
    const filesElement = document.getElementById(`commentFiles-${cid}`);
    // 如果没有找到文件容器元素，不做文件处理
    if (!filesElement) {
        console.error(`无法找到评论文件容器元素filesElement，CID: ${cid}`);
    }

    // 获取评论内容和评分
    const commentContent = commentContentElement.textContent;
    const rateText = rateElement.textContent.trim();
    const rate = rateText.match(/\d+/) ? rateText.match(/\d+/)[0] : 1;

    // 填充评论内容和评分
    document.getElementById('editCommentContent').value = commentContent;
    document.getElementById('editCommentRate').value = rate;

    // 处理文件列表
    const files = filesElement ? filesElement.innerHTML : '';
    console.log("来自editComment函数的files(改前)：", files);
    const fileListDiv = document.getElementById('fileList');
    fileListDiv.innerHTML = '';  // 清空之前的文件列表

    // 存储临时删除的文件
    let deletedFiles = [];

    if (files && files.trim()) {
        console.log("来自editComment函数的files.trim()：", files);
        const fileItems = files.split('<div class="file-item">');
        fileItems.forEach(fileItemHTML => {
            if (fileItemHTML.trim() !== `<p type="hidden">没文件</p>`) {
                const tempDiv = document.createElement('div');
                tempDiv.innerHTML = fileItemHTML.trim();

                const fileItems = tempDiv.querySelectorAll('.file-item');
                fileItems.forEach(fileItem => {
                    // 创建删除按钮
                    const deleteBtn = document.createElement('button');
                    deleteBtn.textContent = '×';
                    deleteBtn.classList.add('delete-file-btn');
                    deleteBtn.addEventListener('click', () => {
                        removeFile(fileItem, deletedFiles);  // 删除文件的回调
                    });

                    // 给 file-item 添加定位样式
                    fileItem.style.position = 'relative';
                    fileItem.appendChild(deleteBtn);

                    fileListDiv.appendChild(fileItem);
                });
            }
        });
    }

    // 弹出模态框
    $('#editCommentModal').modal('show');

    // 保存编辑后的内容并删除文件
    document.getElementById('saveEdit').onclick = function () {
        const updatedContent = document.getElementById('editCommentContent').value.trim();
        const updatedRate = document.getElementById('editCommentRate').value.trim();

        console.log(updatedContent);
        console.log(updatedRate);

        // 创建包含评论ID、内容、评分、文件的请求数据
        const formData = new FormData();
        formData.append("cid", cid);  // 评论ID
        formData.append("content", updatedContent);  // 评论内容
        formData.append("rate", updatedRate);  // 评分

        // 处理文件
        const fileListDiv = document.getElementById('fileList');
        const filesToSend = [];

        // 获取所有文件项的路径并添加到 filesToSend 数组中
        fileListDiv.querySelectorAll('.file-item').forEach(fileItem => {
            const imgTag = fileItem.querySelector('img');
            if (imgTag) {
                const filePath = imgTag.src;  // 获取文件路径
                filesToSend.push(filePath);   // 将文件路径添加到数组
            }
        });

        console.log("来自editComment函数的files(改后)：", files);


        // 添加已删除的文件到 formData
        deletedFiles.forEach(deletedFile => {
            formData.append("deletedFiles", JSON.stringify(deletedFile));  // 将删除的文件信息附加到 formData
        });
        console.log("来自editComment函数-----------files：", files);
        console.log("来自editComment函数-----------deletedFiles：", deletedFiles);
        for (let pair of formData.entries()) {
            console.log(pair[0]+ ': ' + pair[1]);
        }

        // 获取文件输入元素
        const fileInput = document.getElementById('editCommentFiles');

// 获取选中的文件并添加到 formData
        for (let i = 0; i < fileInput.files.length; i++) {
            formData.append("files", fileInput.files[i]);
        }

        // 发送 POST 请求更新评论
        fetch(`/comment/update`, {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(result => {
                if (result === 1) {
                    // 如果有文件被删除，发送请求删除服务器上的文件
                    if (deletedFiles.length > 0) {
                        deletedFiles.forEach(file => {
                            const deleteFormData = new FormData();
                            deleteFormData.append("fid", file.fid);  // 文件ID
                            deleteFormData.append("filePath", file.filePath);  // 文件路径

                            // 发送 POST 请求删除文件
                            fetch('/files/deleteFile', {
                                method: 'POST',
                                body: deleteFormData
                            })
                                .then(deleteResponse => deleteResponse.json())
                                .then(deleteResult => {
                                    if (deleteResult == 1) {
                                        console.log(`文件 ${file.filePath} 已删除`);
                                    } else {
                                        alert('文件删除失败，请重试！');
                                    }
                                })
                        });
                    }

                    // 更新成功，刷新页面
                    window.location.reload();  // 刷新页面
                    $('#editCommentModal').modal('hide');  // 关闭模态框
                } else {
                    alert('评论更新失败，请重试！');
                }
            })
            .catch(error => {
                alert('更新评论发生错误，请重试！');
            });
    };
}



// 删除文件的回调函数：点击❌按钮时，仅从显示列表中移除文件
function removeFile(fileItem, deletedFiles) {
    // 查找 <img> 标签并获取其 src 属性（即文件路径）
    const imgTag = fileItem.querySelector('img');  // 查找 <img> 标签
    if (imgTag) {
        const filePath = imgTag.src.substring(imgTag.src.lastIndexOf('/') + 1);  // 获取 img 的 src 属性值（文件路径）,最后一个/后 .jpg 等后缀名前面的字符串
        console.log("来自removeFile函数的filePath：", filePath);

        // 从 filePath 中提取 fid，即最后一个/后 .jpg 等后缀名前面的字符串
        const fid = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
        console.log("来自removeFile函数的fid：", fid);

        // 将删除的文件添加到临时的删除文件列表
        deletedFiles.push({ fid: fid, filePath: filePath });

        // 从页面中删除文件项（只进行临时删除）
        fileItem.remove();
    } else {
        console.error('未找到 img 标签，删除操作失败');
    }
}







function ReportComment(connectId) {
    // console.log("来自ReportComment函数的CID：", connectId);
    reportedId = connectId;
}

function updatePagination(pageInfo) {
    // console.log("更新分页导航：", pageInfo);
    const pagination = $("#pagination ul.pagination");
    pagination.empty();

    const {
        pageNum,
        pages,
        hasPreviousPage,
        hasNextPage,
        navigateFirstPage,
        navigateLastPage,
        navigatepageNums
    } = pageInfo;

    // // console.log("分页导航数据：", pageInfo);

    // 添加 "首页" 按钮
    if (pageNum > 1) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="loadComments(currentUserId, 1, ConnectType)">首页</a></li>`);
    } else {
        pagination.append(`<li class="page-item disabled"><span class="page-link">首页</span></li>`);
    }

    // 添加 "上一页" 按钮
    if (hasPreviousPage) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="loadComments(currentUserId, ${pageNum - 1},  ConnectType)">上一页</a></li>`);
    } else {
        pagination.append(`<li class="page-item disabled"><span class="page-link">上一页</span></li>`);
    }

    // 动态生成页码按钮，当前页前后最多显示3个页码
    const maxPagesToShow = 3;
    const startPage = Math.max(1, pageNum - maxPagesToShow);
    const endPage = Math.min(pages, pageNum + maxPagesToShow);

    for (let i = startPage; i <= endPage; i++) {
        const activeClass = i === pageNum ? 'active' : '';
        pagination.append(`<li class="page-item ${activeClass}"><a class="page-link" href="#" onclick="loadComments(currentUserId, ${i}, ConnectType)">${i}</a></li>`);
    }

    // 添加 "下一页" 按钮
    if (hasNextPage) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="loadComments(currentUserId, ${pageNum + 1}, ConnectType)">下一页</a></li>`);
    } else {
        pagination.append(`<li class="page-item disabled"><span class="page-link">下一页</span></li>`);
    }

    // 添加 "尾页" 按钮
    if (pageNum < pages) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="loadComments(currentUserId, ${pages}, ConnectType)">尾页</a></li>`);
    } else {
        pagination.append(`<li class="page-item disabled"><span class="page-link">尾页</span></li>`);
    }
}


function toggleComment(cid) {
    // console.log("来自toggleComment函数的CID：", cid);
    const contentDiv = document.querySelector(`#commentContent-${cid}`);
    const moreButton = document.querySelector(`#moreButton-${cid}`);
    const commentDiv = document.querySelector(`#comment-${cid}`);
    // debugger;
    // 判断当前是否为短文本显示
    const isShort = moreButton.textContent.trim() == '展开';

    if (isShort) {
        // debugger;
        // debugger;
        moreButton.textContent = '收起';


        // 去掉限制，显示全部内容
        contentDiv.style.whiteSpace = '';
        contentDiv.style.overflow = '';
        contentDiv.style.textOverflow = '';
        // 去掉-webkit-line-clamp的限制
        contentDiv.style.webkitLineClamp = 'unset';
    } else {
        // debugger;
        // 收缩为前2行
        moreButton.textContent = '展开';

        // 恢复文本为只显示2行，其他内容隐藏
        contentDiv.style.whiteSpace = 'nowrap';
        contentDiv.style.overflow = 'hidden';
        contentDiv.style.textOverflow = 'ellipsis';
        contentDiv.style.webkitLineClamp = '2';
    }
}


// 点赞/取消点赞评论
const likeComment = function (cid) {
    // console.log("来自点赞：点赞评论：", cid);
    // console.log("来自点赞：当前用户ID：", currentUserId);

    // 获取按钮和点赞数的元素
    const likeButton = $(`#comment-${cid} .like-button`);
    const agreeCount = $(`#agreeCount-${cid}`);

    // 判断按钮是否已经是 active 状态
    const isLiked = likeButton.hasClass('active');

    if (isLiked) {
        // 取消点赞操作
        // // console.log("取消点赞");

        // 发送取消点赞请求
        $.ajax({
            url: "/comment/unlike", // 假设有取消点赞的接口
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                cid: Number(cid), // 强制转换为数字
                uid: Number(currentUserId) // 强制转换为数字
            }),
            success: function (response) {
                // 更新UI：按钮变灰，点赞数减1
                likeButton.removeClass('active'); // 移除 active 类
                agreeCount.text(parseInt(agreeCount.text()) - 1); // 点赞数减1
            },
            error: function (xhr, status, error) {
                // console.error("取消点赞失败：", xhr.responseText || error);
                alert("取消点赞失败，请重试！");
            }
        });
    } else {
        // 点赞操作
        // // console.log("点赞");
        // 发送点赞请求
        $.ajax({
            url: "/comment/like",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                cid: Number(cid), // 强制转换为数字
                uid: Number(currentUserId) // 强制转换为数字
            }),
            success: function (response) {
                // 更新UI：按钮变为活跃状态，点赞数加1
                likeButton.addClass('active'); // 添加 active 类
                agreeCount.text(parseInt(agreeCount.text()) + 1); // 点赞数加1
            },
            error: function (xhr, status, error) {
                // console.error("点赞失败：", xhr.responseText || error);
                alert("点赞失败，请重试！");
            }
        });
    }
};

// 放大图片的函数
function zoomImage(event) {
    const image = event.target;
    // 切换放大效果
    image.classList.toggle("zoomed");
    // 如果已经放大，再次点击时恢复原尺寸
    if (!image.classList.contains("zoomed")) {
        setTimeout(() => {
            image.style.transition = "transform 0.3s ease-in-out"; // 保证恢复时也有过渡效果
        }, 300);
    } else {
        image.style.transition = "none";  // 放大时取消过渡
    }
}



// 回复评论
// 回复评论
const replyComment = function (cid, uid, name) {
    const replyContent = `@${name}：`;
    document.getElementById('ReleaseCommentContent').value = replyContent;  // 在评论框中显示回复内容

    // 使用动画滚动到发布评论框
    $('html, body').animate({scrollTop: $('#ReleaseCommentContent').offset().top - 100}, 500);
};



/*<![CDATA[*/
function loadComments(uid = connectId, pageNo = 1, connectType) {
    // console.log("来自loadComments：", uid, pageNo, connectType);

    const apiUrl = connectType === "User" ? "/comment/listByUid" : "/comment/list";
    const requestData = connectType === "User"
        ? {uid: currentUserId, pageNo, pageSize}
        : {connectId: uid, pageNo, pageSize, connectType};

    // console.log(`来自loadComments：走的是${connectType === "User" ? "User" : "else"}：`, uid, pageNo, connectType);

    $.ajax({
        url: "/comment/listByUid",
        type: "GET",
        data: requestData,
        success: function (response) {
            const cids = response.cids; // 从响应中获取 cids
            const pageInfo = response.pageInfo; // 获取分页信息
            renderComments(pageInfo, cids); // 传递分页信息和 cids
        },
        error: function (xhr, status, error) {
            // console.error("加载评论失败：", xhr.responseText || error);
            alert("加载评论失败，请重试！");
        }
    });
}

// 判断文件是否是图片
function isImage(filePath) {
    const imageExtensions = ['.jpg', '.jpeg', '.png', '.gif', '.bmp', '.svg'];
    const fileExtension = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();
    return imageExtensions.includes(fileExtension);
}


// 渲染评论列表的文件列表
function renderCommentFiles(cids) {
    $.ajax({
        url: "/comment/getFiles",
        type: "GET",
        data: { cids: cids.join(",") },
        success: function (fileMap) {
            console.log("来自renderCommentFiles的fileMap：", fileMap);
            console.log("来自renderCommentFiles的cids：", cids);

            // 对文件进行分组，按照 connectid 分组
            let groupedFiles = {};

            // 遍历 fileMap，按 connectid 分组文件
            Object.values(fileMap).forEach(file => {
                const connectid = file.connectid;  // 假设文件有 connectid 属性
                if (!groupedFiles[connectid]) {
                    groupedFiles[connectid] = [];
                }
                groupedFiles[connectid].push(file);  // 将文件添加到对应的分组
            });

            // 对每个评论ID进行处理，渲染对应的文件
            cids.forEach(cid => {
                let files = groupedFiles[cid] || [];  // 根据评论的 cid 获取文件
                const commentTextContainer = $(`#comment-${cid} .comment-text`); // 获取 comment-text

                // 无论是否有文件，都创建一个文件容器
                const fileContainer = $(`<div id="commentFiles-${cid}" class="comment-files" style="margin-top: 10px; display: flex; flex-wrap: wrap;"></div>`);

                console.log("来自renderCommentFiles的文件列表：", files);
                if (files.length > 0) {
                    files.forEach(file => {
                        const filePath = file.path.replace('D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded\\', '/uploaded/'); // 替换为虚拟路径
                        console.log("来自renderCommentFiles的单个文件：", file.path);
                        const fileItem = `
                            <div class="file-item" style="margin-right: 10px; margin-bottom: 10px;">
                                ${isImage(filePath) ?
                            `<img src="${filePath}" alt="${file.fid}" class="file-image" />` :
                            `<a href="${filePath}" target="_blank">${file.fid}</a>`
                        }
                            </div>
                        `;
                        fileContainer.append(fileItem);
                    });
                }

                // 如果没有文件，依然创建空的容器以确保容器存在
                if (files.length === 0) {
                    //隐藏文件容器fileContainer

                    const noFileMessage = `<p type="hidden">没文件</p>`;
                    fileContainer.append(noFileMessage);
                    fileContainer.hide();
                }

                // 无论文件是否存在，都将文件容器添加到 comment-text
                commentTextContainer.append(fileContainer);
            });
        },
        error: function (xhr, status, error) {
            console.log("获取评论文件失败，请重试！");
        }
    });
}








function renderComments(response,cids) {
    // console.log("来自renderComments：", response);
    const commentList = $("#commentList");
    const noComment = $("#noComment");
    console.log("来自renderComments：", response);
    commentList.empty();
    const comments = response.list;
    if (comments.length === 0) {
        noComment.show();
    } else {
        noComment.hide();
        const userIds = comments.map(comment => comment.uid);
        // 获取用户信息并渲染评论
        $.ajax({
            url: "/comment/getUsers",
            type: "GET",
            data: {uids: userIds.join(",")},
            success: function (userMap) {
                comments.forEach(comment => renderCommentItem(comment, userMap));
                renderCommentFiles(cids);  // 获取并渲染评论文件列表
                updatePagination(response);
                // console.log(response);
            },
            error: function (xhr, status, error) {
                // console.error("来自renderComments：获取用户信息失败：", xhr.responseText || error);
                alert("来自renderComments：获取用户信息失败，请重试！");
            }
        });
    }
}

function renderCommentItem(comment, userMap) {
    const user = userMap[comment.uid] || {name: "匿名用户", picture: "/static/img/default.png", city: ""};
    const formattedDate = formatDate(comment.created);
    const rate = formatRate(comment.rate);
    const likeClass = comment.agree > 0 ? 'like-button active' : 'like-button';
    const fullContent = formatfullComment(comment.content);

    const showMoreButton = fullContent.length > CommentMaxLength;

    const commentItem = `
    <div class="comment-item" id="comment-${comment.cid}" data-full-content="${fullContent}">
        <div class="comment-header" style="display: flex; align-items: center;">
            <img class="user-avatar" src="${user.picture}" alt="头像" />
            <span class="username">${user.name}</span>
        </div>
        <div class="comment-body" style="display: flex; align-items: center; gap: 20px; margin-top: 10px;">
            <div class="comment-text" style="flex-grow: 1;">
                <div id="commentContent-${comment.cid}" class="comment-content" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; -webkit-line-clamp: 2;">${comment.content}</div>
                <div>
                    ${showMoreButton ? `
                    <div>
                        <button class="btn btn-sm btn-link" id="moreButton-${comment.cid}" 
                                onclick="toggleComment(${comment.cid})">
                                展开
                        </button>
                    </div>` : ''}
                </div>
            </div>
        </div>
        <div class="comment-footer" style="display: flex; align-items: center; gap: 10px;">
            <span class="time">${formattedDate}</span>
            <p class="location">${user.city || ''}</p>
            <span class="star-rating" id="commentRate-${comment.cid}">${rate}分</span>
            <button class="like-button ${likeClass}" onclick="likeComment(${comment.cid})">点赞</button>
            <span id="agreeCount-${comment.cid}">${comment.agree}</span>
            <button class="btn btn-sm btn-info" onclick="replyComment(${comment.cid}, '${comment.uid}', '${user.name}')">回复</button>
            <button th:if="${currentUserId == comment.uid}" class="btn btn-sm btn-danger" onclick="deleteComment(${comment.cid})">删除</button>
            <button th:if="${currentUserId == comment.uid}" class="btn btn-sm btn-primary" onclick="editComment(${comment.cid})">编辑</button>
            <button class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#reportModal" onclick="ReportComment(${comment.cid})">举报</button>
        </div>
    </div>
    `;
    $("#commentList").append(commentItem);
}


$(document).ready(function () {
    $(document).ready(function () {
        // 清空指定人的评论
        $("#clearCommentsBtn").click(function () {
            const uid = $("#clearCommentsUid").val().trim();
            if (!uid) {
                alert("请输入有效的用户ID！");
                return;
            }

            if (confirm(`确定要清空用户 ${uid} 的所有评论吗？`)) {
                $.ajax({
                    url: `/comment/deleteAllByUid?uid=${uid}`, // 将 uid 放在 URL 参数中
                    type: "DELETE",
                    success: function (response) {
                        if (response.message === "success") {
                            alert(`用户 ${uid} 的所有评论已成功删除！`);
                            window.location.reload(); // 重新加载评论
                        } else {
                            alert("清空评论失败：" + response.message);
                        }
                    },
                    error: function (xhr, status, error) {
                        alert("操作失败，请稍后重试！");
                        // console.error(error);
                    }
                });
            }
        });

    });


    let reportedId;
    // 发布评论
    document.getElementById('addCommentBtn').onclick = function () {
        const content = document.getElementById('ReleaseCommentContent').value.trim();
        const files = document.getElementById('ReleaseCommentFiles').files;
        const connectId = document.getElementById('ReleaseConnectId').value.trim();
        const connectType = document.getElementById('ReleaseConnectType').value.trim();
        const rate = document.getElementById('ReleaseCommentRate').value.trim().split('.')[0];     // 评分取整
        console.log("发布评论：", content, files, connectId, connectType);
        if (content) {
            const formData = new FormData();
            formData.append("connectId", connectId);  // 评论的目标ID
            formData.append("uid", currentUserId);  // 评论的用户ID
            formData.append("content", content);  // 评论内容
            formData.append("type", "Comment");  // 类型
            formData.append("rate", rate);  //   // 评分取整
            // 只有在有文件时才添加文件到 FormData
            if (files.length > 0) {
                for (let i = 0; i < files.length; i++) {
                    formData.append("files", files[i]);
                    console.log("添加文件：", files[i]);
                }
            }

            // 发送请求
            fetch("/comment/add", {
                method: "POST",
                body: formData
            })
                .then(response => response.json())
                .then(result => {
                    if (result === 1) {
                        alert("评论发布成功！");
                        window.location.reload();  // 刷新页面
                    } else {
                        alert("发布评论失败，请重试！");
                    }
                })
                .catch(error => {
                    alert("发布评论失败，请重试！");
                });
        } else {
            alert("评论内容不能为空！");
        }
    };



    // 提交举报
    document.getElementById('submitReport').onclick = function () {
        const categoryElement = document.getElementById('reportCategory');
        const descriptionElement = document.getElementById('reportReason');
        const filesElement = document.getElementById('reportFiles');
        const reportedTypeElement = document.getElementById('reportedType');
        const reportedIdElement = document.getElementById('reportedId');

        // 检查每个元素是否存在
        if (!categoryElement || !descriptionElement || !filesElement || !reportedTypeElement || !reportedIdElement) {
            console.error("一些必要的元素没有找到。请检查页面上的HTML元素ID是否正确。");
            return; // 如果任何元素未找到，终止操作
        }

        const category = categoryElement.value; // 获取举报类别
        const description = descriptionElement.value; // 获取举报理由
        const files = filesElement.files; // 获取上传的文件
        const reportedType = reportedTypeElement.value; // 获取被举报者举报类型
        const reporter = currentUserId; // 获取举报者ID
        const reportedId = reportedIdElement.value; // 获取被举报者ID

        // 检查是否至少选择了一个文件
        if (files.length === 0) {
            alert('请至少选择一个文件/一项证据 进行上传');
            return; // 如果没有文件，阻止后续代码执行
        }

        const formData = new FormData();
        // console.log("举报类别：", category, "举报理由：", description, "被举报者类型：", reportedType, "被举报者ID：", reportedId, "举报者ID：", currentUserId);

        // 添加举报类别和理由
        formData.append("category", category);
        formData.append("description", description);
        formData.append("reportedType", reportedType);
        formData.append("reporter", reporter);
        formData.append("reportedId", reportedId);

        // 添加文件
        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        // 发送请求
        fetch('/comment/report', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(result => {
                // console.log(result);
                if (result == 1) {
                    alert('举报成功');
                    $('#reportModal').modal('hide'); // 关闭模态框
                }else if(result== -2){
                    alert('举报失败,文件保存失败' + result.message);
                }else if(result == -1){
                    alert('举报失败-1：' + result.message);
                }else{
                    alert('举报失败else：' + result.message);
                }
            })
            .catch(error => {
                alert('举报失败');
            });
    };



    // //处理id为uploadBtn的点击事件
    // $("#uploadBtn").click(function(){
    //
    //     //如果是ajax提交包含上传文件的请求
    //     //需要用一个FormData对象封装表单数据
    //     var formData=new FormData($("#form1")[0]);
    //
    //     $.ajax({
    //         url:'files/upload2',
    //         type:'POST',
    //         data: formData, //提交的就是封装成FormData对象的表单数据
    //         processData: false, //不使用默认方式处理上传的数据，默认会转成字符串
    //         contentType: false, //不使用默认的内容类型作为发送的数据的类型
    //         success: function(img){ //如果处理请求成功，返回上传的图片的名称
    //             $("#img1").attr("src","uploaded/"+img);
    //         },
    //         error: function(req,status,error){
    //             alert("请求处理出错!!!"+error);
    //         }
    //     });
    // });


    loadComments(connectId, 1, "User");
    // 初次加载评论列表
});
/*]]>*/
// </script>