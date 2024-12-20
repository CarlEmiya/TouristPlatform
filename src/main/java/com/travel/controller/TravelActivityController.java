package com.travel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.*;
import com.travel.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/travelActivity")
public class TravelActivityController {

    @Autowired
    private TravelActivityServiceImpl travelActivityService;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private ActivityRegistrationServiceImpl activityRegistrationService;
    @Autowired
    FileController fileController = new FileController();
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ReportServiceImpl reportService;

    private Long generateUniqueAid() {
        Long aid;
        do {
            aid = (long) (Math.random() * 1000000000);  // 随机生成一个rid
        } while (travelActivityService.isAidExist(aid));  // 检查rid是否存在
        return aid;
    }

    @GetMapping("/list")
    public String listActivities(
            @RequestParam(defaultValue = "1") int pageNo,  // 当前页
            @RequestParam(defaultValue = "5") int pageSize,  // 每页条数
            @RequestParam(required = false) String keyword,  // 关键字筛选
            @RequestParam(required = false) Integer status,  // 活动状态筛选（如进行中、结束）
            @RequestParam(required = false) Integer timeInterval,  // 时间筛选（如最近一个月、三个月等）
            @RequestParam(required = false) String aLabel,  // 标签筛选
            @RequestParam(required = false) Long uidForMyActivities,  // 我发布的活动
            @RequestParam(required = false) Long MyAttendedActivities,  // 我参与的活动
            @RequestParam(required = false) Long publisherId,
            @RequestParam(required = false) Long participantId,
            Model model) {

        // 设置分页
        PageHelper.startPage(pageNo, pageSize);

        // 创建查询条件
        TravelActivityExample activityExample = new TravelActivityExample();
        TravelActivityExample.Criteria criteria = activityExample.createCriteria();

        // 根据关键字筛选
        if (keyword != null && !keyword.trim().isEmpty()) {
            criteria.andTitleLike("%" + keyword + "%");
        }

        if(aLabel != null && !aLabel.trim().isEmpty()) {
            criteria.andLabelLike("%" + aLabel + "%");
        }

        if(participantId!= null) {
            ActivityRegistrationExample registrationExample = new ActivityRegistrationExample();
            registrationExample.createCriteria().andUidEqualTo(participantId);
            List<ActivityRegistration> registrations = activityRegistrationService.selectByExample(registrationExample);
            List<Long> aids = registrations.stream()
                    .map(ActivityRegistration::getAid)  // 假设 getAid() 方法获取活动 ID
                    .collect(Collectors.toList());
// 根据这些活动 IDs 获取活动列表
            if (aids != null && !aids.isEmpty()) {
                criteria.andAidIn(aids);
            }
        }

        if(publisherId!= null) {
            criteria.andUidEqualTo(publisherId);
        }

        // 根据活动状态筛选
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }

        // 根据时间间隔筛选（按最近一段时间内发布的活动）
        if (timeInterval != null) {
            long currentTime = System.currentTimeMillis();
            long timeIntervalInMillis = 0;

            switch (timeInterval) {
                case 1: // 最近一个月
                    timeIntervalInMillis = 30L * 24 * 60 * 60 * 1000;
                    break;
                case 3: // 最近三个月
                    timeIntervalInMillis = 90L * 24 * 60 * 60 * 1000;
                    break;
                case 6: // 最近半年
                    timeIntervalInMillis = 180L * 24 * 60 * 60 * 1000;
                    break;
                case 12: // 最近一年
                    timeIntervalInMillis = 365L * 24 * 60 * 60 * 1000;
                    break;
            }

            long timeLimit = currentTime - timeIntervalInMillis;
            criteria.andStartGreaterThan(new java.util.Date(timeLimit));
        }

//        如果uid != null) {
        if (uidForMyActivities != null) {
            criteria.andUidEqualTo(uidForMyActivities);
        }

        if (MyAttendedActivities != null) {
            List<TravelActivity> activities = activityRegistrationService.getActivitiesByUid(MyAttendedActivities);
            criteria.andAidIn(activities.stream().map(TravelActivity::getAid).collect(Collectors.toList()));
        }


        // 查询符合条件的活动
        List<TravelActivity> activities = travelActivityService.searchActivities(activityExample);
        if (!activities.isEmpty()) {
            // 获取文件（图片）信息
            List<Long> activityIds = activities.stream()
                    .map(TravelActivity::getAid)  // 获取活动ID
                    .collect(Collectors.toList());

            // 假设文件接口返回的文件是按活动ID关联的，返回的是 List
            List<com.travel.entity.File> fileList = fileService.getFilesByConnectIds(activityIds, "Activity");

            Map<Long, com.travel.entity.File> fileMap = fileList.stream()
                    .collect(Collectors.toMap(
                            com.travel.entity.File::getConnectid,  // key: 通过 connectid 作为键
                            file -> file,  // value: 文件对象
                            (existing, replacement) -> existing  // 如果有冲突，选择第一个文件（existing）
                    ));

// 将文件ID传递给活动列表中的每个活动（假设每个活动只有一个文件，且获取第一个文件）
            for (TravelActivity activity : activities) {
                // 获取活动ID并根据ID获取对应的文件
                Long activityId = activity.getAid();
                com.travel.entity.File firstFile = fileMap.get(activityId);

                if (firstFile != null) {
                    // 获取文件的绝对路径
                    String absoluteFilePath = firstFile.getPath();
                    // 将绝对路径替换为虚拟路径
                    // 确保将文件路径从本地路径映射到可以被访问的虚拟路径
                    String virtualPath = absoluteFilePath.replace("D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded", "\\uploaded");

                    // 设置活动的第一张图片路径为虚拟路径
                    activity.setFirstFilePath(virtualPath);
                } else {
                    // 如果没有找到文件，设置第一张图片路径为空
                    activity.setFirstFilePath(null);
                }
            }
        }


        // 使用PageInfo来获取分页信息
        PageInfo<TravelActivity> pageInfo = new PageInfo<>(activities);

        // 将查询结果和分页信息传递到前端
        model.addAttribute("activities", activities);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("timeInterval", timeInterval);
        model.addAttribute("aLabel", aLabel);
        model.addAttribute("uidForMyActivities", uidForMyActivities);
        model.addAttribute("MyAttendedActivities", MyAttendedActivities);
        model.addAttribute("publisherId", publisherId);
        model.addAttribute("participantId", participantId);
        return "activity/list";  // 返回活动列表页面
    }











    @GetMapping("/listParticipants")
    public String listParticipants(
            @RequestParam(defaultValue = "1") int pageNo,  // 当前页
            @RequestParam(defaultValue = "5") int pageSize,  // 每页条数
            @RequestParam(required = false) Long aid,  // 关键字筛选
            Model model) {
            ActivityRegistrationExample registrationExample = new ActivityRegistrationExample();
            registrationExample.createCriteria().andAidEqualTo(aid);
            List<ActivityRegistration> registrations = activityRegistrationService.selectByExample(registrationExample);

        if (registrations.isEmpty()) {
            model.addAttribute("message", "暂无参与者");
            model.addAttribute("registrations", registrations);
            model.addAttribute("aid", aid);
            model.addAttribute("userMap", new HashMap<>());
            return "activity/listParticipants";  // 如果没有参与者，跳转到错误页面
        }
        else{
            List<Long> userIds = registrations.stream()
                    .map(ActivityRegistration::getUid)  // 假设 getAid() 方法获取活动 ID
                    .collect(Collectors.toList());

            List<User> users = userServiceImpl.getUsersByUids(userIds);
            // 使用PageInfo来获取分页信息
            PageInfo<ActivityRegistration> pageInfo = new PageInfo<>(registrations);
            Map<Long, User> userMap = users.stream()
                    .collect(Collectors.toMap(
                            User::getUid,  // key: 通过 uid 作为键
                            user -> user,  // value: 用户对象
                            (existing, replacement) -> existing  // 如果有冲突，选择第一个用户（existing）
                    ));

            // 将查询结果和分页信息传递到前端
            model.addAttribute("registrations", registrations);
            model.addAttribute("users", users);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("aid", aid);
            model.addAttribute("userMap", userMap);
            return "activity/listParticipants";  // 返回活动列表页面
        }
    }




    @PostMapping("/kickOutUser")
    @ResponseBody
    public Map<String, Object> kickOutUser(@RequestParam Long userId, @RequestParam Long aid) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 根据 userId 删除活动报名记录
            int result = activityRegistrationService.deleteByUidAndAid(aid, userId);
            if (result == 0) {
                response.put("message", "用户未报名该活动！");
                response.put("status", "error");
            } else {
                response.put("message", "踢出成功！");
                response.put("status", "success");
            }

        } catch (Exception e) {
            response.put("message", "踢出失败，请重试！");
            response.put("status", "error");
        }
        return response;
    }


















    @GetMapping("/detail")
    public String getActivityDetail(@RequestParam("aid") Long aid, Model model, HttpSession session) {
        // 根据活动 ID 获取活动详情
        TravelActivity activity = travelActivityService.getActivityByActivityId(aid);

        int status = activity.getStatus();
        //判断当前时间是否在活动开始之前
        Date startTime = activity.getStart();
        Date endTime = activity.getEnd();
        LocalDateTime nowTime = LocalDateTime.now();
        Date deadline = activity.getDeadline();
        //判断当前时间是否在活动开始之前
        boolean isBefore = nowTime.isBefore(startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        //判断当前时间是否在活动结束之后
        boolean isAfter = nowTime.isAfter(endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        //判断当前时间是否在活动截止之前且在活动开始之后,即为进行中
        boolean isBeforeDeadlineAfterStart = nowTime.isBefore(deadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) && !nowTime.isBefore(startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        //判断是否需要改状态:如果是2，3,7,16，需要不改状态
        boolean needChangeStatus = !(activity.getStatus() == 2 || activity.getStatus() == 3 || activity.getStatus() == 7);
        //判断是否人满了
        boolean isFull = activity.getCurrent() <= activity.getRequired();

        if(needChangeStatus) {
            if(isBefore){
                activity.setStatus(1);
            }else if(isAfter){
                activity.setStatus(16);
            }else if(isBeforeDeadlineAfterStart){
                activity.setStatus(6);
            }else if(isBefore){
                activity.setStatus(1);
            }else if(!isBefore && isFull){
                activity.setStatus(4);
            }
        }

        User currentUser = (User)session.getAttribute("currentUser");
        Long uid = currentUser.getUid();
        Boolean isRegister = activityRegistrationService.isRegister(aid,uid);

        try{
            int countLike = travelActivityService.getCountLike(aid,"activity");
            activity.setAgree(countLike);
            Boolean isLiked = travelActivityService.isLiked(aid,uid);
            model.addAttribute("countLike", countLike);
            model.addAttribute("isLiked", isLiked);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (activity != null) {

            //获取其文件列表
            List<com.travel.entity.File> files = fileService.getFilesByConnectId(aid,"Activity");
            // 假设文件接口返回的文件是按活动ID关联的，返回的是 List
            if (!files.isEmpty()) {
                for (com.travel.entity.File file : files) {
                    // 获取文件的绝对路径
                    String absoluteFilePath = file.getPath();
                    // 将绝对路径替换为虚拟路径
                    // 确保将文件路径从本地路径映射到可以被访问的虚拟路径
                    String virtualPath = absoluteFilePath.replace("D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded", "\\uploaded");
                    // 设置图片路径为虚拟路径
                    file.setPath(virtualPath);
                }
            }

            // 将标签列表转换为逗号分隔的字符串
            String tags = String.join(",", activity.getLabel());
            model.addAttribute("tags", tags);

            model.addAttribute("files", files);
            model.addAttribute("activity", activity);
            model.addAttribute("isRegister", isRegister);
//            model.addAttribute("oldLabels", activity.getLabel());
            return "activity/detail";  // 返回活动详情页面
        } else {
            model.addAttribute("message", "活动不存在或已被删除");
            return "error";  // 如果活动不存在，跳转到错误页面
        }
    }

    private Long generateUniqueRid() {
        Long rid;
        do {
            rid = (long) (Math.random() * 1000000000);  // 随机生成一个rid
        } while (reportService.isRidExist(rid));  // 检查rid是否存在
        return rid;
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
            filePaths = fileService.uploadFiles(files, rid,"Activity");  // 调用文件上传方法
        } catch (RuntimeException e) {
            return new ResponseEntity<>(-2, HttpStatus.INTERNAL_SERVER_ERROR);  // 文件上传失败
        }

        // 将文件路径添加到举报理由中
        String finalReason = reason;

        // 处理举报信息（存储reason等）
        int result = reportService.insertReport(rid, reporter, reportedId, category, reportedType, finalReason);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 活动参与
    @PostMapping("/join/{aid}")
    public ResponseEntity<String> joinActivity(@PathVariable Long aid, HttpSession session) {
        User currentUser = (User)session.getAttribute("currentUser");
        Long uid = currentUser.getUid();
        // 判断用户是否已报名
        if (activityRegistrationService.isRegister(aid, uid)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您已报名该活动");
        } else {
            // 如果没有报名，执行报名
            activityRegistrationService.registerForActivity(aid, uid);
            travelActivityService.updateActivityCurrent(aid);
            return ResponseEntity.ok("报名成功");
        }
    }

    // 取消活动报名
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelActivity(@RequestParam("aid") Long aid, HttpSession session) {
        User currentUser = (User)session.getAttribute("currentUser");
        Long uid = currentUser.getUid();
        // 判断用户是否已报名
        if (activityRegistrationService.isRegister(aid, uid)) {
            // 执行取消报名
            activityRegistrationService.cancelRegistration(aid, uid);
            travelActivityService.updateActivityCurrent(aid);  // 更新活动状态为“已结束”
            return ResponseEntity.ok("取消报名成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您没有报名该活动");
        }
    }

    // 取消活动点赞
    @PostMapping("/cancelLike")
    public ResponseEntity<String> cancelLikeActivity(@RequestParam("aid") Long aid,@RequestParam("uid") Long uid, HttpSession session) {
        // 判断用户是否已报名
        if (travelActivityService.isLiked(aid, uid)) {
            // 执行取消报名
            travelActivityService.cancelLikeActivity(aid, uid);
            return ResponseEntity.ok("取消点赞成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您没有报名该活动");
        }
    }

    // 活动点赞
    @PostMapping("/Like")
    public ResponseEntity<String> LikeActivity(@RequestParam("aid") Long aid,@RequestParam("uid") Long uid, HttpSession session) {
        // 判断用户是否已报名
        try {
            if (travelActivityService.isLiked(aid, uid)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您已点赞该活动");
            } else {
                travelActivityService.LikeActivity(aid, uid);
                return ResponseEntity.ok("点赞成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("点赞失败");
        }
    }


    // 访问发布活动页面
    @GetMapping("/publish")
    public String showPublishPage(Model model) {
        model.addAttribute("activity", new TravelActivity());  // 创建空活动对象
        return "activity/publish";  // 返回发布页面视图
    }

    @PostMapping("/publish")
    public ResponseEntity<?> addActivity(
            @RequestParam("uid") Long uid,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(value = "end", required = false)  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)LocalDateTime end,
            @RequestParam(value = "deadline", required = false)  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline,
            @RequestParam("location") String location,
            @RequestParam("min") int min,
            @RequestParam("cost") Double cost,
            @RequestParam("required") Integer required,
            @RequestParam("status") Integer status,
            @RequestParam("tags") String tags,
            @RequestParam(value = "activityImage", required = false) MultipartFile[] files,
            @RequestParam(value = "deletedFileIds", required = false) String deletedFileIdsJson,
            HttpSession session) {

        try {

            Long aid = generateUniqueAid();

            // 将cost转换成 BigDecimal类型
            BigDecimal costBigDecimal = new BigDecimal(cost);
//        // 获取并设置活动的其他信息
            TravelActivity activity = new TravelActivity();
            activity.setAid(aid);
            activity.setUid(uid);
            activity.setMin(min);
            activity.setLocation(location);
            activity.setCurrent(0);
            activity.setCreated(new Date());
            activity.setRate(10.0);
            activity.setCost(costBigDecimal);
            activity.setRequired(required);
            activity.setStatus(status);
            activity.setRequired(required);
            activity.setAgree(0);
            activity.setTitle(title);
            activity.setDescription(description);
            if (start != null) {
                activity.setStart(Date.from(start.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }
            if (end != null) {
                activity.setEnd(Date.from(end.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }
            if (deadline != null) {
                activity.setDeadline(Date.from(deadline.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }

            List<String> tagList = Arrays.asList(tags.split(","));
//            如果tag是空的，就跳过
            if (!tagList.isEmpty()) {
                activity.setLabel(tagList);
            }

//        // 处理删除的文件
            if (deletedFileIdsJson != null && !deletedFileIdsJson.isEmpty()) {
                // 解析删除的文件ID列表
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<Map<String, String>> deletedFiles = objectMapper.readValue(deletedFileIdsJson, List.class);
                    for (Map<String, String> fileInfo : deletedFiles) {
//                        trueFilePath只要文件路径filePath的最后一个uploaded\后面的内容即可
                        String trueFilePath="";
                        String filePath = fileInfo.get("filePath");
                        int index = filePath.lastIndexOf("uploaded\\");
                        if (index!= -1) {
                            trueFilePath = filePath.substring(index + "uploaded\\".length());
                            // 此时trueFilePath就是你想要的在"uploaded\"之后的那部分路径内容了
                        }
                        Long fileId = Long.parseLong(fileInfo.get("fileId"));
                        // 调用服务删除文件
                        fileController.deleteFile(trueFilePath, fileId);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            // 处理上传的文件
            if (files != null) {
                System.out.println(files);
                List<String> filePaths = fileService.uploadFiles(files, aid, "Activity"); // 调用文件上传方法
            }
            // 更新活动信息

            int result = travelActivityService.addActivity(activity);
            // 返回结果
            if (result > 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @PostMapping("/update")
    public ResponseEntity<?> updateActivity(
            @RequestParam("aid") Long aid,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(value = "end", required = false)  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)LocalDateTime end,
            @RequestParam(value = "deadline", required = false)  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadline,
            @RequestParam("location") String location,
            @RequestParam("cost") Double cost,
            @RequestParam("required") Integer required,
            @RequestParam("status") Integer status,
            @RequestParam("tags") String tags,
            @RequestParam(value = "activityImage", required = false) MultipartFile[] files,
            @RequestParam(value = "deletedFileIds", required = false) String deletedFileIdsJson,
            HttpSession session) {

        try {
            // 将cost转换成 BigDecimal类型
            BigDecimal costBigDecimal = new BigDecimal(cost);
//        // 获取并设置活动的其他信息
            TravelActivity activity = travelActivityService.getActivityByActivityId(aid);
            activity.setTitle(title);
            activity.setDescription(description);
            if (start != null) {
                activity.setStart(Date.from(start.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }
            if (end != null) {
                activity.setEnd(Date.from(end.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }
            if (deadline != null) {
                activity.setDeadline(Date.from(deadline.atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }
            activity.setLocation(location);
            activity.setCost(costBigDecimal);
            activity.setRequired(required);
            activity.setStatus(status);
            activity.setRequired(required);
            List<String> tagList = Arrays.asList(tags.split(","));
//            如果tag是空的，就跳过
            if (!tagList.isEmpty()) {
                activity.setLabel(tagList);
            }

//        // 处理删除的文件
            if (deletedFileIdsJson != null && !deletedFileIdsJson.isEmpty()) {
                // 解析删除的文件ID列表
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<Map<String, String>> deletedFiles = objectMapper.readValue(deletedFileIdsJson, List.class);
                    for (Map<String, String> fileInfo : deletedFiles) {
//                        trueFilePath只要文件路径filePath的最后一个uploaded\后面的内容即可
                        String trueFilePath="";
                        String filePath = fileInfo.get("filePath");
                        int index = filePath.lastIndexOf("uploaded\\");
                        if (index!= -1) {
                            trueFilePath = filePath.substring(index + "uploaded\\".length());
                            // 此时trueFilePath就是你想要的在"uploaded\"之后的那部分路径内容了
                        }
                        Long fileId = Long.parseLong(fileInfo.get("fileId"));
                        // 调用服务删除文件
                        fileController.deleteFile(trueFilePath, fileId);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            // 处理上传的文件
            if (files != null) {
                System.out.println(files);
                List<String> filePaths = fileService.uploadFiles(files, aid, "Activity"); // 调用文件上传方法
            }
            // 更新活动信息

            int result = travelActivityService.updateActivity(activity);
            // 返回结果
            if (result > 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // 更新指定活动的状态
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateActivityStatus(
            @RequestParam("aid") Long aid,
            @RequestParam("newStatus") int newStatus) {
        int result = travelActivityService.updateActivityStatus(aid, newStatus);
        if (result > 0) {
            return new ResponseEntity<>("状态更新成功！", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("状态更新失败，请重试。", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 标记活动为“预删除”，设置删除周期和删除时间
    @PostMapping("/TotalDelete")
    public ResponseEntity<String> deleteActivity(@RequestParam("aid") Long aid) {
        int result = travelActivityService.totalDeleteActivity(aid);
        if (result == 1) {
            return new ResponseEntity<>("活动删除成功！", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("活动删除失败，请重试。", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 撤销活动删除状态，恢复为正常状态
    @PostMapping("/cancelActivity")
    public ResponseEntity<String> cancelActivity(
            @RequestParam("aid") Long aid,
            @RequestParam("reason") String reason) {
        // 调用业务逻辑层来处理取消操作
        int result = travelActivityService.CancelActivity(aid,6,reason);

        // 根据结果返回不同的消息
        if (result == 1) {
            return new ResponseEntity<>("活动已成功取消！", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("活动取消失败，请重试。", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // 获取指定ID的活动详细信息
    @GetMapping("/getActivity")
    public ResponseEntity<TravelActivity> getActivity(
            @RequestParam("aid") Long aid) {
        TravelActivity activity = travelActivityService.getActivityByActivityId(aid);
        if (activity!= null) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 根据条件查询活动（与searchActivities功能相同）
    @PostMapping("/searchByCondition")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByCondition(
            @RequestBody TravelActivityExample activity) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByCondition(activity);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 根据时间间隔筛选活动（例如最近一个月或三个月）
    @GetMapping("/searchByTime")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByTime(
            @RequestParam("timeInterval") int timeInterval) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByTime(timeInterval);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 根据用户ID和时间间隔筛选活动
    @GetMapping("/searchByTimeAndUid")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByTimeAndUid(
            @RequestParam("uid") Long uid,
            @RequestParam("timeInterval") int timeInterval) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByTimeAndUid(uid, timeInterval);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 隐藏活动
    @PutMapping("/hide")
    public ResponseEntity<Integer> hideActivity(@RequestParam("aid") Long aid) {
        int result = travelActivityService.hideActivity(aid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}