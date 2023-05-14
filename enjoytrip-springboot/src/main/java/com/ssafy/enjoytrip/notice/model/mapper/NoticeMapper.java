package com.ssafy.enjoytrip.notice.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.notice.model.NoticeDTO;

@Mapper
public interface NoticeMapper {
	// 공지사항 작성
	public void createNotice(NoticeDTO noticeDto) throws Exception;

	// 공지사항 리스트 조회
	public List<NoticeDTO> listNotice(Map<String, Object> param) throws Exception;

	// 공지사항 조회
	public NoticeDTO getNotice(int noticeIdx) throws Exception;

	// 공지사항 수정
	public void modifyNotice(NoticeDTO noticeDto) throws Exception;

	// 공지사항 삭제
	public void deleteNotice(int noticeIdx) throws Exception;

	// 전체 공지사항 개수
	public int getTotalNoticeCount(Map<String, Object> param);

	// 조회수 증가
	public void updateHit(int noticeIdx);
}
