package com.ssafy.enjoytrip.review.model.service;

import java.util.ArrayList;
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
	public int createReview(ReviewDTO reviewDto) throws Exception {
		return reviewMapper.createReview(reviewDto);
	}
	
	@Override
	public void createReviewImage(ReviewImageDTO reviewImageDto) throws Exception {
		reviewMapper.createReviewImage(reviewImageDto);
	}
	
	@Override
	public void deleteReviewImage(int deleteReviewImageIdx) throws Exception {
		reviewMapper.deleteReviewImage(deleteReviewImageIdx);
		
	}
	
	@Override
	public List<ReviewDTO> listReview() throws Exception {
		List<ReviewDTO> result = new ArrayList<ReviewDTO>();
		result = reviewMapper.listReview();
		
		for(int i = 0; i < result.size(); i++) {
			ReviewDTO now = result.get(i);
			List<ReviewImageDTO> images = new ArrayList<ReviewImageDTO>();
			images = reviewMapper.getImages(now.getReview_idx());
			now.setReview_image(images);
		}
		return result;
	}

	@Override
	public List<ReviewDTO> listReviewSort(ReviewSelectDTO reviewSelectDTO) throws Exception {
		List<ReviewDTO> result = reviewMapper.listReviewSort(reviewSelectDTO);
		
		for(int i = 0; i < result.size(); i++) {
			ReviewDTO now = result.get(i);
			List<ReviewImageDTO> images = new ArrayList<ReviewImageDTO>();
			images = reviewMapper.getImages(now.getReview_idx());
			now.setReview_image(images);
		}
		return result;
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
//		reviewMapper.deleteReviewComment(reviewIdx, 0);
		reviewMapper.deleteReviewImageAll(reviewIdx);
		reviewMapper.deleteReview(reviewIdx);
	}

	@Override
	public ReviewDTO getReview(int reviewIdx) throws Exception {
		ReviewDTO reviewDto = reviewMapper.getReview(reviewIdx);
		
		List<ReviewImageDTO> images = new ArrayList<ReviewImageDTO>();
		images = reviewMapper.getImages(reviewDto.getReview_idx());
		reviewDto.setReview_image(images);
		
		return reviewDto;
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
