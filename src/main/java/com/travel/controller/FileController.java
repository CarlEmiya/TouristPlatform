package com.travel.controller;

import com.travel.service.CommentService;
import com.travel.service.FileService;
import com.travel.service.ReportService;
import com.travel.service.impl.FileServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileServiceImpl fileService;
	@Autowired
	private CommentService commentService;
    @Autowired
    private ReportService reportService;

	String prePath = "D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded\\";
	String prePath2 = "D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\";

	@PostMapping("/report")
	public ResponseEntity<Integer> reportComment(@RequestParam("category") String category,
												 @RequestParam("description") String reason,
												 @RequestParam("reportedType") String reportedType,
												 @RequestParam("reporter") Long reporter,
												 @RequestParam("reportedId") Long reportedId,
												 @RequestParam("files") MultipartFile[] files,
												 HttpSession session)
	{

		//随机生成rid，如果已存在，重新生成
		Long rid = (long) (Math.random() * 1000000000);
		// 处理文件上传
		List<String> filePaths = new ArrayList<>();  // 用来存储文件路径
		List<com.travel.entity.File> fileList = new ArrayList<>();
		for (MultipartFile file : files) {
			Long fid = (long) (Math.random() * 1000000000);
			try {
				// 上传文件到指定目录
				String path = prePath + fid;
				file.transferTo(new File(prePath));

				// 保存文件信息到数据库
				com.travel.entity.File fileEntity = new com.travel.entity.File(fid,rid,path,"*");
				fileList.add(fileEntity);
				filePaths.add(path);  // 添加文件路径
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 存储文件信息
		if (!fileList.isEmpty()) {
			fileService.saveFiles(fileList);
		}

		// 将文件路径添加到举报理由中
		String finalReason = reason + "\n\n附加文件：" + String.join(", ", filePaths);

		// 处理举报信息（存储reason等）
		int result = reportService.insertReport(rid,reporter, reportedId, category, finalReason, reportedType);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 获取对应评论的文件列表
	@GetMapping("/getFiles")
	public ResponseEntity<Map<Long, com.travel.entity.File>> getFilesByConnectIds(@RequestParam List<Long> connectIds,@RequestParam String type) {
		List<com.travel.entity.File> files = fileService.getFilesByConnectIds(connectIds,type);
		Map<Long, com.travel.entity.File> FileMap = files.stream().collect(Collectors.toMap(com.travel.entity.File::getFid, file -> file));
		return new ResponseEntity<>(FileMap, HttpStatus.OK);
	}



	@PostMapping("/deleteFile")
	public ResponseEntity<Integer> deleteFile(@RequestParam("filePath") String filePath, @RequestParam("fid") Long fid) {
		// 逻辑处理，检查文件是否存在，删除文件并更新数据库记录
		String prePath = "D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded\\";
		File file = new File(prePath, filePath);  // 拼接文件路径

		if (file.exists()) {
			boolean deleted = file.delete();
			if (deleted) {
				fileService.deleteFileByFid(fid);  // 删除数据库记录
				return new ResponseEntity<>(1, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
		}
	}

	// 获取文件列表

	// 如果在一个地方要使用程序的ServletContext对象，可以直接依赖注入
	// 一个Web程序，有且仅有一个ServletContext对象，用于存储程序范围数据以及程序信息
	@Autowired
	private ServletContext ctx;

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download() throws IOException {

		// 获取要下载的文件的路径
		// 可以是c盘或d盘下任意一个路径，比如c:/download/aaa.txt
		// 如果想获取程序中某个文件的真实路径，可以调用getRealPath()方法获取
		String path = ctx.getRealPath("/files/transcript.xlsx");

		// 将文件内容读取出来，发送到客户端即可
		// 可以将内容读取到一个byte[]数组
		byte[] data = FileUtils.readFileToByteArray(new File(path));
//		byte[] data = new byte[1024];

		// 如果返回响应，需要指定一些报头信息（响应的一些额外信息），可以使用一个HttpHeaders对象封装报头信息
		HttpHeaders headers=new HttpHeaders();
		// 这个报头说明希望文件作为附件下载
		// 也可以加上fileName（可选）指定下载的文件的名称
		headers.add("content-disposition", "attchment;fileName=muban.xlsx");

		// 3.状态代码
		ResponseEntity<byte[]> resp = new ResponseEntity<>(data, headers, HttpStatus.OK);

		return resp; // 将ResponseEntity对象作为返回值即可，因为它包含了我们的响应信息
	}
}
