package com.travel.service.impl;

import com.travel.entity.TravelActivity;
import com.travel.entity.TravelActivityExample;
import com.travel.entity.TravelDiary;
import com.travel.entity.TravelDiaryExample;
import com.travel.mapper.ActivityRegistrationMapper;
import com.travel.mapper.TravelActivityMapper;
import com.travel.mapper.TravelDiaryMapper;
import com.travel.service.TravelActivityService;
import com.travel.service.TravelDiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelDiaryServiceImpl implements TravelDiaryService {

	@Autowired
	private TravelDiaryMapper diaryMapper;

	private static final Logger logger = LoggerFactory.getLogger(TravelDiaryServiceImpl.class);
    @Autowired
    private ActivityRegistrationMapper activityRegistrationMapper;


	public int banComment(Long reported) {
		try {
			TravelDiaryExample example = new TravelDiaryExample();
			example.createCriteria().andDidEqualTo(reported);
			TravelDiary diary = diaryMapper.selectByExample(example).get(0);
			if(diary != null) {

				TravelDiary newDiary = new TravelDiary();
				newDiary.setDid(reported);
				newDiary.setStatus(2);
				diaryMapper.updateByPrimaryKeyWithBLOBs(newDiary);
				return 1;
			}
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean totalDeleteDiary(Long reported) {
		try {
			TravelDiaryExample example = new TravelDiaryExample();
			example.createCriteria().andDidEqualTo(reported);
			diaryMapper.deleteByExample(example);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}