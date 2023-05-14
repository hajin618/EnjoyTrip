package com.ssafy.enjoytrip.review.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;

public interface ReviewService {
	// 리뷰 작성
	public void createReview(ReviewDTO reviewDto) throws Exception;

	// 리뷰 리스트 조회
	public List<ReviewDTO> listReview(Map<String, String> param) throws Exception;
	
	// 공지사항 조회
	public ReviewDTO getReview(int reviewIdx) throws Exception;

	// 리뷰 조회수 증가
	public void updateHit(int reviewIdx) throws Exception;

	// 리뷰 수정
	public void modifyReview(ReviewDTO reviewDto) throws Exception;

	// 리뷰 삭제
	public void deleteReview(int reviewIdx) throws Exception;
	
	// 리뷰 댓글 조회
	public List<ReviewCommentDTO> getReviewComment(int reviewIdx);
	
	// 리뷰 댓글 생성
	public void createReviewComment(ReviewCommentDTO reviewCommentDto) throws Exception;
	
	// 리뷰 댓글 삭제
	public void deleteReviewComment(int reviewIdx, int review_comment_idx) throws Exception;
}
