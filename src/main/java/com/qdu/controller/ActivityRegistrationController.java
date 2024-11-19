package com.qdu.controller;

import com.qdu.entity.Comment;
import com.qdu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Comments")
public class ActivityRegistrationController {

    @Autowired
    private  CommentService CommentService;

	//1. 根据班级查询学生列表：
    @GetMapping("/findComments")
	public String getCommentListByClass(@RequestParam("batchName") String batchName, Model model) {
        List<Comment> CommentList = CommentService.getCommentListByBatchName(batchName);
        model.addAttribute("CommentList", CommentList);
        return "Comment_list";
    }
	//2. 点击Comment_list.jsp页面上的“+添加新学生”超链接，跳转到add_Comment.jsp页面
	@GetMapping("/to_add_Comment")
	public String toAddCommentPage() {
        return "add_Comment";
    }
	//3. 点击add_Comment.jsp页面上的“添加按钮”，提交表单数据，调用CommentService的addComment()方法添加新学生，添加后跳转到首页重新查询
	@PostMapping("/add_Comment")
    public String addComment(Comment Comment) {
        CommentService.addComment(Comment);
        return "index";
    }
	//4. 在Comment_list.jsp页面点击“返回首页”超链接可以返回index.jsp页面
    @ResponseBody
	public String toIndexPage() {
        return "index";
    }
	//5. 在Comment_list.jsp页面点击某个学生后面的“编辑”超链接可以跳转到edit_Comment.jsp页面编辑该学生的信息，
    //   要求跳转到该页面后，在页面的文本框中显示学生信息方便修改，而不是显示空白
	@GetMapping("/to_edit_Comment/{id}")
	public String editComment(@PathVariable String id, Model model) {
        Comment Comment = CommentService.getCommentById(id);
        model.addAttribute("Comment", Comment);
        return "edit_Comment";
    }
	//6. 在edit_Comment.jsp页面点击“修改”按钮，提交学生信息，调用CommentService的updateComment()方法更新学生信息，并跳转到首页重新查询
    @PostMapping("/edit_Comment")
    public String updateComment(Comment Comment) {
        CommentService.updateComment(Comment);
        return "index";
    }
	//7. 在Comment_list.jsp页面点击某个学生后面的“删除”超链接实现删除当前学生，调用CommentService的deleteComment()方法删除学生，并且页面上删除对应行

    @ResponseBody
    @PostMapping("/delete")
    public int deleteComment(String srollno) {
        return 1;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test aaaaa";
    }
}
