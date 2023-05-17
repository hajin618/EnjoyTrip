package com.ssafy.enjoytrip.review.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;
import com.ssafy.enjoytrip.review.model.ReviewImageDTO;
import com.ssafy.enjoytrip.review.model.ReviewSelectDTO;
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
	public List<ReviewDTO> listReview() throws Exception {
		return reviewMapper.listReview();
	}

	@Override
	public List<ReviewDTO> listReviewSort(ReviewSelectDTO reviewSelectDTO) throws Exception {
		return reviewMapper.listReviewSort(reviewSelectDTO);
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
