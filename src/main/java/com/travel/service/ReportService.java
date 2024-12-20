package com.travel.service;

import com.travel.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CommentService接口，定义学生相关的业务
 * 
 * @author Anna
 *
 */
@Service
public interface ReportService {

	int insertReport(Long rid, Long reporter, Long reportedId, String category, String finalReason, String reportedType);

	boolean isRidExist(Long rid);
}
