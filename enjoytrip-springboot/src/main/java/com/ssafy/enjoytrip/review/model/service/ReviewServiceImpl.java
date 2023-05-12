package com.ssafy.enjoytrip.review.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;
import com.ssafy.enjoytrip.review.model.ReviewImageDTO;
import com.ssafy.enjoytrip.review.model.mapper.ReviewMapper;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service("ReviewServiceImpl")
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewMapper reviewMapper;
	
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}

	@Override
	public void createReview(ReviewDTO reviewDto) throws Exception {
		reviewMapper.createReview(reviewDto);
		
		List<ReviewImageDTO> reviewImageList = reviewDto.getReview_image();
		if(reviewImageList != null && !reviewImageList.isEmpty()) {
			for(ReviewImageDTO ri : reviewImageList) {
				ri.setReview_idx(reviewDto.getReview_idx());
				reviewMapper.createReviewImage(ri);
			}
		}
		
	}

	@Override
	public List<ReviewDTO> listReview(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		return reviewMapper.listReview(param);
	}

	@Override
	public void updateHit(int reviewIdx) throws Exception {
		reviewMapper.updateHit(reviewIdx);
		
	}

	@Override
	public void modifyReview(ReviewDTO reviewDto) throws Exception {
		reviewMapper.modifyReview(reviewDto);
		
	}

	@Override
	public void deleteReview(int reviewIdx) throws Exception {
		reviewMapper.deleteReviewComment(reviewIdx, 0);
		reviewMapper.deleteReviewImage(reviewIdx);
		reviewMapper.deleteReview(reviewIdx);
	}

	@Override
	public ReviewDTO getReview(int reviewIdx) throws Exception {
		return reviewMapper.getReview(reviewIdx);
	}

	@Override
	public void createReviewComment(ReviewCommentDTO reviewCommentDto) throws Exception {
		reviewMapper.createReviewComment(reviewCommentDto);
		
	}

	@Override
	public void deleteReviewComment(int reviewIdx, int review_comment_idx) throws Exception {
		reviewMapper.deleteReviewComment(reviewIdx, review_comment_idx);
		
	}

	@Override
	public List<ReviewCommentDTO> getReviewComment(int reviewIdx) {
		return reviewMapper.getReviewComment(reviewIdx);
	}

}
