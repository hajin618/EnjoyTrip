package com.ssafy.enjoytrip.review.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;
import com.ssafy.enjoytrip.review.model.ReviewImageDTO;
import com.ssafy.enjoytrip.review.model.ReviewSelectDTO;

@Mapper
public interface ReviewMapper {
	// 리뷰 작성
	public void createReview(ReviewDTO reviewDto) throws Exception;

	// 리뷰 이미지 추가
	public void createReviewImage(ReviewImageDTO reviewImageDto) throws Exception;
	
	// 리뷰 리스트 조회
	public List<ReviewDTO> listReview() throws Exception;
	
	// 리뷰 리스트 정제 조회
	public List<ReviewDTO> listReviewSort(ReviewSelectDTO reviewSelectDTO) throws Exception;

	// 리뷰 조회
	public ReviewDTO getReview(int reviewIdx) throws Exception;
	
	// 리뷰 조회수 증가
	public void updateHit(int reviewIdx) throws Exception;

	// 리뷰 수정
	public void modifyReview(ReviewDTO reviewDto) throws Exception;

	// 리뷰 삭제
	public void deleteReview(int reivewIdx) throws Exception;
	
	// 리뷰 댓글 조회
	public List<ReviewCommentDTO> getReviewComment(int reviewIdx);
	
	// 리뷰 댓글 생성
	public void createReviewComment(ReviewCommentDTO reviewCommentDto) throws Exception;
	
	// 리뷰 댓글 삭제
	public void deleteReviewComment(int reviewIdx, int review_comment_idx) throws Exception;
	
	// 리뷰 사진 삭제
	public void deleteReviewImage(int reviewIdx) throws Exception;
}
