package com.ssafy.enjoytrip.review.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;
import com.ssafy.enjoytrip.review.model.ReviewImageDTO;
import com.ssafy.enjoytrip.review.model.ReviewSelectDTO;

public interface ReviewService {
	// 리뷰 작성
	public int createReview(ReviewDTO reviewDto) throws Exception;
	
	// 리뷰 이미지 등록
	public void createReviewImage(ReviewImageDTO reviewImageDto) throws Exception;

	// 리뷰 이미지 삭제
	public void deleteReviewImage(int deleteReviewImageIdx) throws Exception;
	
	// 리뷰 리스트 조회
	public List<ReviewDTO> listReview() throws Exception;
	
	// 나의 리뷰 리스트 조회
	public List<ReviewDTO> myReviewList(int user_idx) throws Exception;
	
	// 리뷰 리스트 정제 조회
	public List<ReviewDTO> listReviewSort(ReviewSelectDTO reviewSelectDTO) throws Exception;
	
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
	public int createReviewComment(ReviewCommentDTO reviewCommentDto) throws Exception;
	
	// 리뷰 댓글 삭제
	public void deleteReviewComment(int reviewIdx, int review_comment_idx) throws Exception;

	// 리뷰 댓글 전체 삭제
	public void deleteAllReviewComment(int review_idx) throws Exception;
}
