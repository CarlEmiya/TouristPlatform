// <script th:inline="javascript">

    const connectId = $("#connectId").val();
    const currentUserId = $("#LoginUid").val();
    // 当前页码和每页记录数
    const pageNo = $("#pageNo").val();
    const pageSize = $("#pageSize").val();
    const ConnectType = $("#ConnectType").val();
    const connectType = $("#ConnectType").val();
    const CommentMaxLength = 200; // 评论最大长度

    // console.log("来自CommentList.js的connectId：", connectId);
    // console.log("来自CommentList.js的currentUserId：", currentUserId);
    // console.log("来自CommentList.js的pageNo：", pageNo);
    // console.log("来自CommentList.js的pageSize：", pageSize);
    // console.log("来自CommentList.js的ConnectType：", ConnectType);
    // console.log("来自CommentList.js的connectType：", connectType);


    function formatDate(dateString) {
        const date = new Date(dateString); // 将时间字符串转换为 Date 对象
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1并补零
        const day = String(date.getDate()).padStart(2, '0'); // 补零到两
        return `${year}-${month}-${day}`;
    }

    function formatfullComment(fullComment) {
        const fullComment1 = ""+ fullComment +"\n";
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
    data: JSON.stringify({ cid: cid }),  // 将请求数据转换为 JSON 格式
    success: function(response) {
    if (response === 1) {  // 假设后端返回 1 表示成功
    // alert("评论已删除！");
    // 刷新页面
    window.location.reload();
} else {
    alert("删除失败！");
}
},
    error: function(xhr, status, error) {
    alert("删除失败！请重试。");
    // console.error(error);  // 输出详细错误信息
}
});
}
}


    function ReportComment(cid) {
    // console.log("来自ReportComment函数的CID：", cid);
    // 保存评论 ID
    currentCid = cid;
    // // console.log("举报评论ID:", currentCid); // 可用此行调试，确认 CID 已经传入

    // 初始化 WangEditor 编辑器
    const reportEditor = new wangEditor('#reportEditor');
    reportEditor.create();  // 创建编辑器
}



    // 编辑评论函数
    function editComment(cid) {
    // console.log("来自editComment函数的CID：", cid);
    // 获取当前评论内容
    const commentContent = document.getElementById(`commentContent-${cid}`).textContent;

    // 初始化 wangEditor
    const updateEditor = new wangEditor('#updateEditor');
    updateEditor.create();

    // 填充评论内容到编辑器
    updateEditor.setContent(commentContent);

    // 保存编辑后的内容
    document.getElementById('saveEdit').onclick = function() {
    // 获取编辑后的内容
    const updatedContent = updateEditor.getHtml(); // 获取HTML内容

    // 创建一个包含评论ID和内容的请求数据
    const data = JSON.stringify({
    cid: cid,
    content: updatedContent
});

    // 发送请求到后端
    fetch('/comment/update', {
    method: 'PUT',
    headers: {
    'Content-Type': 'application/json'
},
    body: data
})
    .then(response => response.json())
    .then(result => {
    if (result === 1) {
    // 更新成功后刷新评论列表或修改DOM
    alert('评论已更新');
    window.location.reload();
    $('#editCommentModal').modal('hide'); // 关闭模态框
} else {
    alert('更新失败');
}
})
    .catch(error => {
    alert('发生错误');
});
};
}

    function updatePagination(pageInfo) {
    // console.log("更新分页导航：", pageInfo);
    const pagination = $("#pagination ul.pagination");
    pagination.empty();

    const { pageNum, pages, hasPreviousPage, hasNextPage, navigateFirstPage, navigateLastPage, navigatepageNums } = pageInfo;

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

    // 判断当前是否为短文本显示
    const isShort = moreButton.textContent === '展开';
    if (isShort) {
    // debugger;
    // debugger;
    // 展开全文
    moreButton.textContent = '收起';


    // 去掉限制，显示全部内容
    contentDiv.style.whiteSpace = '';
    contentDiv.style.overflow = '';
    contentDiv.style.textOverflow = '';
    // 去掉-webkit-line-clamp的限制
    contentDiv.style.webkitLineClamp = 'unset';
} else {
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
    const likeComment = function(cid) {
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
    success: function(response) {
    // 更新UI：按钮变灰，点赞数减1
    likeButton.removeClass('active'); // 移除 active 类
    agreeCount.text(parseInt(agreeCount.text()) - 1); // 点赞数减1
},
    error: function(xhr, status, error) {
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
    success: function(response) {
    // 更新UI：按钮变为活跃状态，点赞数加1
    likeButton.addClass('active'); // 添加 active 类
    agreeCount.text(parseInt(agreeCount.text()) + 1); // 点赞数加1
},
    error: function(xhr, status, error) {
    // console.error("点赞失败：", xhr.responseText || error);
    alert("点赞失败，请重试！");
}
});
}
};


    // 回复评论
    const replyComment = function(cid, uid, name) {
    // console.log("来自回复：评论ID：", cid, "用户ID：", uid, "用户名：", name);
    // 编辑器内容中预填充回复文本
    const replyContent = `@${name}：`;
    editor.txt.html(replyContent);  // 在编辑器中显示回复内容

    // 确保 #commentContent 元素存在并且有有效的 offset()
    const commentContent = $(`#commentContent-${cid}`);
    if (commentContent.length > 0) {
    // 使用动画滚动到评论内容
    $('html, body').animate({ scrollTop: commentContent.offset().top }, 500);
} else {
    // console.error('找不到评论内容元素');
}
};





    /*<![CDATA[*/
    function loadComments(uid = connectId, pageNo = 1, connectType) {
    // console.log("来自loadComments：", uid, pageNo, connectType);

    const apiUrl = connectType === "User" ? "/comment/listByUid" : "/comment/list";
    const requestData = connectType === "User"
    ? { uid: currentUserId, pageNo, pageSize }
    : { connectId: uid, pageNo, pageSize, connectType };

    // console.log(`来自loadComments：走的是${connectType === "User" ? "User" : "else"}：`, uid, pageNo, connectType);

    $.ajax({
    url: "/comment/listByUid",
    type: "GET",
    data: requestData,
    success: function(response)
    {
        renderComments(response);
    },
    error: function(xhr, status, error) {
    // console.error("加载评论失败：", xhr.responseText || error);
    alert("加载评论失败，请重试！");
    }
});
}

    function renderComments(response) {
    // console.log("来自renderComments：", response);
    const commentList = $("#commentList");
    const noComment = $("#noComment");

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
    data: { uids: userIds.join(",") },
    success: function(userMap) {
        comments.forEach(comment => renderCommentItem(comment, userMap));
        updatePagination(response);
        // console.log(response);
    },
    error: function(xhr, status, error) {
        // console.error("来自renderComments：获取用户信息失败：", xhr.responseText || error);
        alert("来自renderComments：获取用户信息失败，请重试！");
    }
});
}
}

    function renderCommentItem(comment, userMap) {
    const user = userMap[comment.uid] || { name: "匿名用户", picture: "/static/img/default.png", city: "" };
    const formattedDate = formatDate(comment.created);
    const rate = formatRate(comment.rate);
    const likeClass = comment.agree > 0 ? 'like-button active' : 'like-button';
    const fullContent = formatfullComment(comment.content);
        const showMoreButton = fullContent.length > CommentMaxLength;
    // // console.log("来自renderCommentItem：", comment, user, formattedDate, rate, likeClass, fullContent);


    const commentItem = `
    <div class="comment-item" id="comment-${comment.cid}" data-full-content="${fullContent}">
        <div class="comment-header" style="display: flex; align-items: center;">
            <img class="user-avatar" src="${user.picture}" alt="头像" />
            <span class="username">${user.name}</span>
        </div>
        <div class="comment-body" style="display: flex; align-items: center; gap: 20px; margin-top: 10px;">
        
        
            <div class="comment-text" style="flex-grow: 1;">
                <div id="commentContent-${comment.cid}" class="comment-content" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; -webkit-line-clamp: 2;">
                    ${comment.content}
                </div>
                <div>
                    ${showMoreButton ? `
                    <div>
                        <button class="btn btn-sm btn-link" id="moreButton-${comment.cid}" 
                                onclick="toggleComment(${comment.cid})">
                            展开
                        </button>
                    </div>
                ` : ''}
                </div>
            </div>
            
            
            
        </div>
        <div class="comment-footer" style="display: flex; align-items: center; gap: 10px;">
            <span class="time">${formattedDate}</span>
            <p class="location">${user.city || ''}</p>
            <span class="star-rating">${rate}分</span>
            <button class="like-button ${likeClass}" onclick="likeComment(${comment.cid})">点赞</button>
            <span id="agreeCount-${comment.cid}">${comment.agree}</span>
            <button class="btn btn-sm btn-info" onclick="replyComment(${comment.cid}, '${comment.uid}', '${user.name}')">回复</button>
            <!-- 判断是否是当前用户 -->
            <button th:if="${currentUserId == comment.uid}" class="btn btn-sm btn-danger" onclick="deleteComment(${comment.cid})">删除</button>
            <button th:if="${currentUserId == comment.uid}" class="btn btn-sm btn-primary" onclick="editComment(${comment.cid})">编辑</button>
            <button class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#reportModal" onclick="ReportComment(${comment.cid})">举报</button>
        </div>
    </div>
    `;
    // console.log("来自renderCommentItem：", commentItem);
    $("#commentList").append(commentItem);
}

    $(document).ready(function() {

    // console.log("ready:表单数据：", currentUserId);
    let editor = new wangEditor('#editor-container');
    editor.create();

    // 发布评论
    $("#addCommentBtn").click(function() {
    const content = editor.txt.html();
    if (content.trim()) {
    $.ajax({
    url: "/comment/add",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify({
    uid: currentUserId,
    content: editor.getText(),
    created: new Date(),
    type: "Comment"
}),
    success: function(response) {
    alert("评论发布成功！");
    window.location.reload();  // 刷新页面
    editor.txt.clear();  // 清空编辑器内容
},
    error: function() {
    alert("发布评论失败，请重试！");
}
});
}
});




    $(document).ready(function() {
        // 清空指定人的评论
    $("#clearCommentsBtn").click(function() {
    const uid = $("#clearCommentsUid").val().trim();
    if (!uid) {
    alert("请输入有效的用户ID！");
    return;
}

    if (confirm(`确定要清空用户 ${uid} 的所有评论吗？`)) {
    $.ajax({
    url: `/comment/deleteAllByUid?uid=${uid}`, // 将 uid 放在 URL 参数中
    type: "DELETE",
    success: function(response) {
    if (response.message === "success") {
    alert(`用户 ${uid} 的所有评论已成功删除！`);
    window.location.reload(); // 重新加载评论
} else {
    alert("清空评论失败：" + response.message);
}
},
    error: function(xhr, status, error) {
    alert("操作失败，请稍后重试！");
    // console.error(error);
}
});
}
});

});

    // 提交举报
    document.getElementById('submitReport').onclick = function() {
    const category = document.getElementById('reportCategory').value; // 获取举报类别
    const reason = reportEditor.getText(); // 获取举报理由
    const data = JSON.stringify({
    reporter: $("#LoginUid").val(),
    cid: cid, // 评论ID
    category: category,
    reason: reason
});

    fetch('/comment/report', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: data
})
    .then(response => response.json())
    .then(result => {
    alert('举报成功');
    $('#reportModal').modal('hide'); // 关闭模态框
})
    .catch(error => {
    alert('举报失败');
});
};

    loadComments(connectId, 1, "User");
    // 初次加载评论列表
});
    /*]]>*/
// </script>