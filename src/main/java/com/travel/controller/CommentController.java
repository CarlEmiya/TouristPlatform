package com.travel.controller;

import com.travel.entity.Comment;
import com.travel.entity.User;
import com.travel.mapper.UserMapper;
import com.travel.service.impl.CommentServiceImpl;
import com.travel.service.impl.FileServiceImpl;
import com.travel.service.impl.ReportServiceImpl;
import com.travel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

//@Controller
@RestController
//@ResponseBody
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private ReportServiceImpl reportService;

    private  Random random = new Random();


    // 发布评论
    @PostMapping("/add")
    public ResponseEntity<Integer> addComment(
            @RequestParam("connectId") Long connectid,
            @RequestParam("uid") Long uid,
            @RequestParam("content") String content,
            @RequestParam("type") String type,
            @RequestParam(value = "rate")  Double rate ,
            @RequestParam(value = "files", required = false) MultipartFile[] files) {  // 使用 required = false

        // 创建评论对象
        Comment comment = new Comment();
        Long cid = generateUniqueRid(); // 调用方法确保唯一性
        comment.setCid(cid);
        comment.setConnectid(connectid);
        comment.setUid(uid);
        comment.setContent(content);
        comment.setCreated(new Date());  // 根据需要解析日期
        comment.setType(type);
        comment.setStatus(12); // 设置为正常状态
        comment.setAgree(0);
        if(rate>10)
        {
            rate = 10.0;
        }else if(rate<1)
        {
            rate = 1.0;
        }
        comment.setRate(rate);

        // 处理文件上传
        List<String> filePaths = new ArrayList<>();
        if (files != null && files.length > 0) {  // 如果文件不为空，才进行上传处理
            try {
                // 如果有文件，则进行文件上传
                filePaths = fileService.uploadFiles(files, cid, type);  // 假设上传文件的方法
            } catch (Exception e) {
                return new ResponseEntity<>(-2, HttpStatus.INTERNAL_SERVER_ERROR);  // 文件上传失败
            }
        }

        // 保存评论
        int result = commentService.addComment(comment);  // 调用评论服务保存评论
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    // 根据关联对象ID、用户ID和关联对象类型获取评论列表
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getCommentsByAssociatedIdAndUidandAssociatedType(
            @RequestParam Long connectid,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "comment") String type,
            Model model,
            HttpSession session)
    {
        try {
            // 启用分页
            // 启用分页
            PageHelper.startPage(pageNo, pageSize);
            List<Comment> comments = commentService.getCommentsByConnectid(connectid,type);

            //从comments中获取所有cid
            List<Long> cids = comments.stream().map(Comment::getCid).collect(Collectors.toList());
            // 包装分页信息
            PageInfo<Comment> pageInfo = new PageInfo<>(comments);
            model.addAttribute("connectId", connectid);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("connectType", type);
            System.out.println("cids: " + cids);
            Map<String, Object> response = new HashMap<>();
            response.put("pageInfo", pageInfo);
            response.put("cids", cids);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据用户ID获取评论列表，支持分页
     * @param uid
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
    // 根据用户ID获取评论列表，支持分页
    @GetMapping("/listByUid")
    public ResponseEntity<Map<String, Object>> getCommentsByUid(
            @RequestParam Long uid,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            HttpSession session,
            Model model) {

        // 启用分页
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> comments = commentService.getCommentsByUid(uid);

        String connectType = "User";
        //从comments中获取所有cid
        List<Long> cids = comments.stream().map(Comment::getCid).collect(Collectors.toList());
        // 包装分页信息
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        model.addAttribute("connectId", uid);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("connectType", connectType);
//        System.out.println("cids: " + cids);
        Map<String, Object> response = new HashMap<>();
        response.put("pageInfo", pageInfo);
        response.put("cids", cids);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



//根据comment的uid获取用户信息
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserByUid(
            @RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = (Long) requestData.get("uid");
        User user = commentService.getUserByUid(uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // 获取所有评论后批量获取用户信息
    @GetMapping("/getUsers")
    public ResponseEntity<Map<Long, User>> getUsersByUids(@RequestParam List<Long> uids) {
        List<User> users = userServiceImpl.getUsersByUids(uids);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getUid, user -> user));
        return new ResponseEntity<>(userMap, HttpStatus.OK);
    }



    // 根据评论ID删除评论
    @DeleteMapping("/deleteByCid")
    public ResponseEntity<Integer> deleteCommentById(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = Long.valueOf(requestData.get("cid").toString());  // 确保获取的数据是 Long 类型
        int result = commentService.deleteCommentById(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据用户ID删除该用户的所有评论
    @DeleteMapping("/deleteAllByUid")
    public ResponseEntity<Map<String, String>> deleteAllCommentByUid(@RequestParam Long uid, HttpSession session) {
        boolean success = commentService.deleteAllCommentByUid(uid);
        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("message", "success");
        } else {
            response.put("message", "failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 对评论进行点赞
    @PostMapping("/like")
    public ResponseEntity<?> likeComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
//        System.out.println("接收到的参数：" + requestData);
        Long cid = Long.valueOf(requestData.get("cid").toString()); // 转换为 Long 类型
        Long uid = Long.valueOf(requestData.get("uid").toString()); // 转换为 Long 类型
        int result = commentService.likeComment(cid, uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 对评论进行点赞
    @PostMapping("/unlike")
    public ResponseEntity<?> unLikeComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
//        System.out.println("接收到的参数：" + requestData);
        Long cid = Long.valueOf(requestData.get("cid").toString()); // 转换为 Long 类型
        Long uid = Long.valueOf(requestData.get("uid").toString()); // 转换为 Long 类型
        int result = commentService.unLikeComment(cid, uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 获取某条评论的点赞数
    @GetMapping("/agreeCount")
    public ResponseEntity<Integer> getCommentAgreeCount(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = (Long) requestData.get("cid");
        int result = commentService.getCommentAgreeCount(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    private Long generateUniqueRid() {
        Long rid;
        do {
            rid = (long) (Math.random() * 1000000000);  // 随机生成一个rid
        } while (reportService.isRidExist(rid));  // 检查rid是否存在
        return rid;
    }

    private Long generateUniqueFid() {
        Random random = new Random();
        Long fid = null;
        File file;
        String path;

        do {
            fid = random.nextLong();  // 生成一个随机fid
            path = "D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\upload\\" + fid;
            file = new File(path);
        } while (file.exists());  // 如果文件已存在，重新生成fid

        return fid;
    }



    @PostMapping("/report")
    public ResponseEntity<Integer> reportComment(@RequestParam("category") String category,
                                                 @RequestParam("description") String reason,
                                                 @RequestParam("reportedType") String reportedType,
                                                 @RequestParam("reporter") Long reporter,
                                                 @RequestParam("reportedId") Long reportedId,
                                                 @RequestParam("files") MultipartFile[] files,
                                                 HttpSession session) {

        // 随机生成rid，如果已存在，重新生成
        Long rid = generateUniqueRid(); // 调用方法确保唯一性

        // 处理文件上传
        List<String> filePaths;
        try {
            filePaths = fileService.uploadFiles(files, rid,"Report");  // 调用文件上传方法
        } catch (RuntimeException e) {
            return new ResponseEntity<>(-2, HttpStatus.INTERNAL_SERVER_ERROR);  // 文件上传失败
        }

        // 将文件路径添加到举报理由中
        String finalReason = reason;

        // 处理举报信息（存储reason等）
        int result = reportService.insertReport(rid, reporter, reportedId, category, reportedType, finalReason);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<Integer> editComment(@RequestParam("cid") Long cid,
                                               @RequestParam("content") String content,
                                               @RequestParam("rate") Double rate,
                                               @RequestParam(value="files",required = false) MultipartFile[] files,
                                               HttpSession session) {

        System.out.println("files: " + files);
        boolean success = (files == null);

        // 更新评论内容
        Comment comment = new Comment();
        comment.setCid(cid);
        comment.setContent(content);
        comment.setRate(rate);  // 更新评分

        // 如果有文件上传，处理文件
        if (files != null) {
            // 处理文件上传逻辑，将文件保存到指定目录或数据库
            // 处理文件上传
            List<String> filePaths;
            try {
                filePaths = fileService.uploadFiles(files, cid,"Comment");  // 调用文件上传方法
            } catch (RuntimeException e) {
                return new ResponseEntity<>(-2, HttpStatus.INTERNAL_SERVER_ERROR);  // 文件上传失败
            }
        }
        // 更新评论
        int result = commentService.updateComment(comment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}